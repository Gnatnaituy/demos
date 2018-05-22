# coding: utf-8

def add(x, y, f):
   return f(x) + f(y)

print(add(3, -6, abs))

# lambda
fun = lambda x, y: x + y
print(fun(3, 5))

# map
def format_name(s):
    return s.title()

print(map(format_name,  ['Admain', 'Lan', 'Lisa']))

# reduce
from functools import reduce

def prod(x, y):
    return x + y

print(reduce(prod, [2, 3, 4, 5, 6, 76]))

#filter
import math

def is_sqr(x):
    a = int(math.sqrt(x))
    return a * a == x

print(filter(is_sqr, range(1, 101)))

#自定义排序函数
def reversed_cmp(x, y):
    if x > y:
        return -1
    if x < y:
        return 1
    return 0

print(sorted([23,34,56,71,11,2], reversed_cmp))

def cmp_ignore_case(s1, s2):
    u1 = s1.upper()
    u2 = s2.upper()
    if u1 < u2:
        return -1
    if u2 < u1:
        return 1
    return 0

print(sorted(['Sina', 'Tencent', 'Alibaba'], cmp_ignore_case))