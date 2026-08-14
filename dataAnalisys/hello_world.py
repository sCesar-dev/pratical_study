#!/usr/bin/python3
# -*- coding: utf-8 -*-

tup = (1, 2, 3)

a, b, c = tup

b_list = ['foo', 'peekaboo', 'baz']
b_list.append('home')

b_list.insert(2, 'thunder')

b_list.pop(1)

b_list2 = [a, b, c, tup]

b_list += b_list2

empty_dict = {"a": b_list, "b": tup}

word = ["apple", "bat", "bar", "atom"]