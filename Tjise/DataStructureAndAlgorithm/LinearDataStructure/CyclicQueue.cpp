//
// Created by Hasaker on 2018/10/23.
//

#include <cstdio>
#include <cstdlib>
#include "LinearDataStructure.h"


Status InitQueue_Sq(SqQueue &Q, int size) {
    Q.element = (ElementType *) malloc(size * sizeof(ElementType));
    if (Q.element == nullptr)
        return OVERFLOW;
    Q.maxSize = size;
    Q.front = Q.rear = 0;
    return OK;
}

Status DestoryQueue_Sq(SqQueue &Q) {
    free(Q.element);
    return OK;
}

void ClearQueue_Sq(SqQueue &Q) {
    Q.element = nullptr;
    Q.front = Q.rear = 0;
    Q.maxSize = 0;
}

Status QueueEmpty_Sq(SqQueue Q) {
    if (Q.front == Q.rear)
        return TRUE;
    else
        return FALSE;
}

int QueueLength_Sq(SqQueue Q) {

}

Status GetHead_Sq(SqQueue Q, ElementType &e) {
    if (QueueEmpty_Sq(Q) == TRUE)
        return ERROR;
    else {
        e = Q.element[Q.front];
        return OK;
    }
}

Status EnQueue_Sq(SqQueue &Q, ElementType e) {
    if ((Q.rear + 1) % Q.maxSize == Q.front)
        return ERROR;
    Q.element[Q.rear] = e;
    Q.rear = (Q.rear + 1) % Q.maxSize;
    return OK;
}

Status DeQueue_Sq(SqQueue &Q, ElementType &e) {
    if (Q.front == Q.rear)
        return ERROR;
    e = Q.element[Q.rear];
    Q.front = (Q.front + 1) % Q.maxSize;
    return OK;
}