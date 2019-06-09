from multiprocessing import Queue, Process
from time import time


def single_thread():
    total = 0
    number_list = [x for x in range(1, 100000001)]
    start = time()

    for num in number_list:
        total += num
    print(total)

    end = time()
    print('Single thread executing time:', (end - start), 's', sep='')


def multi_thread():
    processes = []
    number_list = [x for x in range(1, 100000001)]
    result_queue = Queue()
    index = 0

    for _ in range(8):
        p = Process(target=task_handler, args=(number_list[index:index + 12500000], result_queue))
        index += 12500000
        processes.append(p)
        p.start()

    start = time()
    for p in processes:
        p.join()
    total = 0
    while not result_queue.empty():
        total += result_queue.get()
    print(total)
    end = time()
    print("Multi thread executing time: ", (end - start), 's', sep='')


def task_handler(curr_list, result_queue):
    total = 0
    for num in curr_list:
        total += num
    result_queue.put(total)


if __name__ == '__main__':
    single_thread()
    multi_thread()
