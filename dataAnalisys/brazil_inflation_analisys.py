#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Mar 21 14:30:41 2026

@author: s-cesar
"""

import numpy as np
import pandas as pd

inicio = '01/06/2023'
fim = '28/02/2026'

df_data_dictionary = pd.read_csv(
    "datasets/inflacao.csv",
    index_col="referencia"
)

def buscar_dados_bcb(cod_bcb, data_inicio, data_fim):
    url = f'https://api.bcb.gov.br/dados/serie/bcdata.sgs.{cod_bcb}/dados?formato=json&dataInicial={data_inicio}&dataFinal={data_fim}'
    
    df = pd.read_json(url)
    
    df['data'] = pd.to_datetime(df['data'], format='%d/%m/%Y')
    df.rename(columns={'data': 'referencia'}, inplace=True)
    df.set_index('referencia', inplace=True)
    return df

def move_column(df, origin, destination):
    cols = list(df.columns)
    col = cols.pop(origin)
    cols.insert(destination, col)
    return df[cols]

#referencia,ano,mes,ipca_variacao,ipca_acumulado_ano
#ipca_acumulado_doze_meses
#inpc_variacao,inpc_acumulado_ano
#inpc_acumulado_doze_meses
#selic_meta,selic_ano,juros_reais,salario_minimo

df = df_data_dictionary

df_ipca_mensal = buscar_dados_bcb(433, inicio, fim)
df_ipca_12m = buscar_dados_bcb(13522, inicio, fim)
df_inpc_mensal = buscar_dados_bcb(188, inicio, fim)
df_selic_meta = buscar_dados_bcb(432, inicio, fim)
df_selic_meta = df_selic_meta.resample('BMS').first()
df_selic_ano = buscar_dados_bcb(11, inicio, fim)
df_selic_ano = df_selic_ano.resample('BMS').first()
df_salario_minimo = buscar_dados_bcb(1619, inicio, fim)

df_ipca_mensal.rename(columns={'valor': 'ipca_variacao'}, inplace=True)
df_ipca_12m.rename(columns={'valor': 'ipca_acumulado_doze_meses'}, inplace=True)
df_inpc_mensal.rename(columns={'valor': 'inpc_variacao'}, inplace=True)
df_selic_meta.rename(columns={'valor': 'selic_meta'}, inplace=True)
df_selic_ano.rename(columns={'valor': 'selic_ano'}, inplace=True)
df_salario_minimo.rename(columns={'valor': 'salario_minimo'}, inplace=True)

df_selic_ano['selic_ano'] = (((1 + (df_selic_ano['selic_ano'] / 100)) ** 252) - 1) * 100
df_selic_ano['selic_ano'] = df_selic_ano['selic_ano'].round(2)

df_ano = df_ipca_12m.copy()
df_ano['ano'] = df_ano.index.year
df_ano = df_ano.drop(columns=['ipca_acumulado_doze_meses'])
df_ano.rename(columns={'ipca_acumulado_doze_meses': 'ano'}, inplace=True)

df_mes = df_ipca_12m.copy()
df_mes['mes'] = df_ano.index.month
df_mes = df_mes.drop(columns=['ipca_acumulado_doze_meses'])
df_mes.rename(columns={'ipca_acumulado_doze_meses': 'mes'}, inplace=True)

dfs = [df_ano, df_mes, df_ipca_mensal, df_ipca_12m, df_inpc_mensal, df_selic_meta, df_selic_ano, df_salario_minimo]
for i in range(len(dfs)):
    dfs[i].index = dfs[i].index.to_period('M')

df_atualization = pd.concat(dfs, axis=1)
df_completo = pd.concat([df, df_atualization])
df_completo.index = pd.to_datetime(df_completo.index, errors='coerce')
df_completo['ipca_acumulado_ano'] = df_completo.groupby(df_completo.index.strftime('%Y'))['ipca_variacao'].transform(
    lambda x: ((1 + x / 100).cumprod() - 1) * 100
)
# df_completo = move_column(df_completo, 8, 3)

df_completo['inpc_acumulado_ano'] = df_completo.groupby(df_completo.index.year)['inpc_variacao'].transform(
    lambda x: ((1 + x / 100).cumprod() - 1) * 100
)
# df_completo = move_column(df_completo, 11, 6)

df_completo['juros_reais'] = (
    ((1 + (df_completo['selic_ano'] / 100)) / (1 + (df_completo['ipca_acumulado_doze_meses'] / 100))) - 1
) * 100
# df_completo = move_column(df_completo, 10, 9)

df_completo['inpc_acumulado_doze_meses'] = ((1 + df_completo['inpc_variacao'] / 100).rolling(window=12, min_periods=12).apply(np.prod, raw=True) - 1) * 100

#df_completo.to_csv('datasets/inflacao_atualizado.csv', float_format="%.2f")