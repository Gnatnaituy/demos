# 内层函数引用了外层函数的变量（参数也算变量），然后返回内层函数的情况，称为闭包（Closure）。
# 返回函数不要引用任何循环变量，或者后续会发生变化的变量。
def count():
    fs = []
    for i in range(1, 4):
        def f(j):
            def g():
                return j * j
            return g
        r = f(i)
        fs.append(r)
    return fs

f1, f2, f3 = count()
print(f1(), f2(), f3())