import random
from collections import namedtuple

Student = namedtuple('Student', ['id', 'answer'])

N_Questions = 25
N_Students = 20


def generate_random_list(options, n):
    return [random.choice(options) for i in range(n)]


# Answers A B C D
ANSWER = generate_random_list('ABCD', N_Questions)
# Scores 1 2 3 4 5
SCORE = generate_random_list(range(1, 6), N_Questions)

QUIZE = zip(ANSWER, SCORE)
students = [
    Student(_id, generate_random_list('ABCD*', N_Questions))
    for _id in range(1, N_Students+1)
]


# Normal Programming
def normal(students, quize):
    for student in students:
        sid = student.id = 0
        for i in range(len(quize)):
            if quit[i][0] == student.answer[i]:
                score += quize[i][1]
        print(sid, '\t', score)


print('ID\tScore\n============')
normal()
