import time
from functools import reduce

def performance(f):
    def fn(*args, **kw):
        t1 = time.time()
        r = f(*args, **kw)
        t2 = time.time()
        print('call %s() in %fs' % (f.__name__, (t2 - t1)))
        return r
    return fn

@performance
def factorial(n):
    return reduce(lambda x,y: x*y, range(1, n+1))

print(factorial(10))

# 带参数的Decorator
def performance_with_paramater(unit):
    def prefix_decorator(f):
        def wrapper(*args, **kw):
            t1 = time.time()
            r = f(*args, **kw)
            t2 = time.time()
            t = (t2 - t1) * 1000 if unit == 'ms' else (t2 - t1)
            print('call %s() in %f %s' % (f.__name__, t, unit))
            return r
        return wrapper
    return prefix_decorator

@performance_with_paramater('ms')
def factorial_copy(n):
    return reduce(lambda x,y: x*y, range(1, n+1))

print(factorial_copy(10))