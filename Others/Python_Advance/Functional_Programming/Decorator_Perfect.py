import time
import functools
from functools import reduce


def performance(unit):
    def prefix_decorator(f):
        @functools.wraps(f)
        def wrapper(*args, **kw):
            t1 = time.time()
            r = f(*args, **kw)
            t2 = time.time()
            t = (t2 - t1) * 1000 if unit == 'ms' else (t2 - t1)
            print('call %s() in %f %s' % (f.__name__, t, unit))
            return r
        return wrapper
    return prefix_decorator


@performance('ms')
def factorial(n):
    return reduce(lambda x, y: x * y, range(1, n+1))


print(factorial.__name__)
