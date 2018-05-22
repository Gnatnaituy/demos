#!/usr/bin/env python
# -*- coding: utf-8 -*-

import random


def get_data():
    return random.sample(range(10), 3)


def consume():
    running_sum = 0
    data_items_seen = 0

    while True:
        print('Comsumer Waiting...')
        data = yield
        data_items_seen += len(data)
        running_sum += sum(data)
        print('Comsumed, Average Time: {}'.format(
            running_sum / float(data_items_seen)))


def produce(consumer):
    """产生序列集合,传递给消费函数"""
    while True:
        data = get_data()
        print('Produced{}'. format(data))
        consumer.send(data)
        yield


if __name__ == '__main__':
    consumer = consume()
    consumer.send(None)
    producer = produce(consumer)

    for _ in range(3):
        print('Producing......')
        next(producer)
