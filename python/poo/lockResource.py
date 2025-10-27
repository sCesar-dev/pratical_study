#!/usr/bin/env python3

class LockResource:
    
    def __init__(self):
        pass

    def block(self):
        print("Resource locked!")

    def open(self):
        print("Resource openned!")

if __name__ == '__main__':
    lock = LockResource()
    try:
        lock.block()
        # Faça algo aqui, e simule um erro
        raise RuntimeError("Algo deu errado!")
    finally:
        # Este código sempre será executado
        lock.open()