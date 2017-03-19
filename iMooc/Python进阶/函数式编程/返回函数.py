def calc_prod(lst):
    def internal():
        x = 1
        for i in lst:
            x *= i
        return x
    return internal

f = calc_prod([1, 2, 3, 4])
print(f())