import random


state = random.getstate()
random.setstate(state=state)
random.getrandbits(1)       # n bits

# Function for Integers
random.randrange(3)             # (stop)
random.randrange(1, 100, 2)     # (start, stop [, step])
random.randint(1, 100)          # alias randrange(1, 101)

# Function for Sequences
sequence = [1, 2, 3, 4, 'mm', 'll']
random.choice(sequence)          # return one random element from sequence
random.choices(sequence, k=3)    # return k random elements from sequence
random.choices(sequence, weights=[1, 2, 3, 4, 5, 6], k=3)        # relative weight
random.choices(sequence, cum_weights=[1, 2, 3, 4, 5, 6], k=3)    # cumulative weight
random.shuffle(sequence)         # I don't know
random.sample(sequence, 3)
random.sample(range(1000), k=60)

# Real-valued Distributions
random.random()                 # [0.0, 1.0)
random.uniform(1, 5)            # return a float number in [a, b]
# some function useless for me now...


