from hashlib import sha512
from warnings import warn
from os import urandom


class LLRandom(object):
    """Simple demo of random.py with function randrange and choice"""

    def __init__(self, x=None):
        self.seed(x)
        self.gauss_next = None

    def seed(self, a=None, version=2):
        if version == 2 and isinstance(a, (str, bytes, bytearray)):
            if isinstance(a, str):
                a = a.encode()
            a += sha512(a).digest()
            a = int.from_bytes(a, 'big')

        self.gauss_next = None

    def random(self):
        return (int.from_bytes(urandom(7), 'big') >> 3) * 2**-63

    # ----------------methods for integers----------------------#

    def _randbelow(self, n, maxsize=1 << 63):
        random = self.random

        if n > maxsize:
            warn('too large')
            return int(random() * n)

        rem = maxsize % n
        limit = (maxsize - rem) / maxsize
        r = random()
        while r > limit:
            r = random()

        return int(r*maxsize) % n

    def randrange(self, start, stop=None, step=1):
        # only start arg supplied
        istart = int(start)
        if istart != start:
            raise ValueError("non-integer arg start")
        if stop is None:
            if istart > 0:
                return self._randbelow(istart)
            raise ValueError('empty range')

        # stop arg supplied
        istop = int(stop)
        if istop != stop:
            raise ValueError("non-integer arg stop")
        width = istop - istart
        if step == 1 and width > 0:
            return istart + self._randbelow(width)
        if step == 1:
            raise ValueError('start should < stop')

        # Non-unit step arg supplied
        istep = int(step)
        if istep != step:
            raise ValueError("non-integer arg step")
        if istep > 0:
            n = (width + istep - 1) // istep
        elif istep < 0:
            n = (width + istep + 1) // istep
        else:
            raise ValueError('zero step not allowed')

        if n <= 0:
            raise ValueError('empty range for (%s %s %s)' % (istart, istop, step))

        return istart + istep * self._randbelow(n)

    # -----------------------methods for sequences-------------------------------- #

    def choice(self, seq):
        try:
            i = self._randbelow(len(seq))
        except ValueError:
            raise IndexError('can\'t choose from an empty sequence')
        return seq[i]


_ins = LLRandom()
randrange = _ins.randrange
choice = _ins.choice
