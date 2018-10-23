//
// Created by Hasaker on 2018/10/23.
//

#ifndef DATASTRUCTUREANDALGORITHM_LINEAR_DATA_STRUCTURE_H
#define DATASTRUCTUREANDALGORITHM_LINEAR_DATA_STRUCTURE_H

#define OK          1
#define ERROR       0
#define TRUE        1
#define FALSE       0
#define OVERFLOW   -2

#define STACK_INIT_SIZE 10
#define STACK_INCREMENT 5

typedef int ElementType;
typedef char CharElementType;
typedef int Status;

typedef struct {
    ElementType * base;
    int top;
    int size;
    int increment;
} SqStack;

typedef struct {
    CharElementType * base;
    int top;
    int size;
    int increment;
} SqStack_Sq;

typedef struct {
    ElementType * element;
    int front;
    int rear;
    int maxSize;
} SqQueue;

// CyclicQueue.cpp
Status InitQueue_Sq(SqQueue &Q, int size);
Status DestoryQueue_Sq(SqQueue &Q);
void ClearQueue_Sq(SqQueue &Q);
Status QueueEmpty_Sq(SqQueue Q);
int QueueLength_Sq(SqQueue Q);
Status GetHead_Sq(SqQueue Q, ElementType &e);
Status EnQueue_Sq(SqQueue &Q, ElementType e);
Status DeQueue_Sq(SqQueue &Q, ElementType &e);

// LinearStack.cpp
Status InitStack(SqStack &S);
Status StackEmpty(SqStack &S);
Status Push(SqStack &S, ElementType e);
Status Pop(SqStack &S, ElementType &e);
void OutStack(SqStack S);
Status InitStack_Sq(SqStack_Sq &S, int size, int increment);
Status StackEmpty_Sq(SqStack_Sq &S);
Status Push_Sq(SqStack_Sq &S, CharElementType e);
Status Pop_Sq(SqStack_Sq &S, CharElementType &e);
Status GetTop_Sq(SqStack_Sq &S, CharElementType &e);
void test_sq_stack();

// bracket_match.cpp
Status bracket_matching(char * expression, int n);

// decimal_conversion.cpp
void conversion();

#endif //DATASTRUCTUREANDALGORITHM_LINEAR_DATA_STRUCTURE_H
