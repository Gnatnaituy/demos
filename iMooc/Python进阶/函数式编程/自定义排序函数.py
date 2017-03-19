from functools import cmp_to_key

def cmp_ignore_case(s1, s2):
    u1, u2 = s1.upper(), s2.upper()
    return -1 if u1 < u2 else 1 if u1 > u2 else 0

# python2
# print(sorted(['bob', 'about', 'Zoo', 'Credit'], cmp_ignore_case))
# python3 
print(sorted(['bob', 'about', 'Zoo', 'Credit'], key=cmp_to_key(cmp_ignore_case)))