from functools import reduce
import math


# Map
def format_name(s):
    return s[0].upper() + s[1:].lower()


print(list(map(format_name, ['AAA', 'vbvb'])))


# Reduce
def prod(x, y):
    return x * y


print(reduce(prod, (1, 2, 3, 4, 5)))


# Filter
def is_sqrt(x):
    r = int(math.sqrt(x))
    return r * r  == x


print(list(filter(is_sqrt, range(1, 101))))