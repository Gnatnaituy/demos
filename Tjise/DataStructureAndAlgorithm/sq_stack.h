//
// Created by Hasaker on 2018/10/19.
//

#ifndef DATASTRUCTUREANDALGORITHM_SQ_STACK_H
#define DATASTRUCTUREANDALGORITHM_SQ_STACK_H

#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0
#define OVERFLOW -2

typedef int ElementType;
typedef int Status;

#define STACK_INIT_SIZE 10	// 栈的初始长度
#define STACK_INCREMENT 5	// 栈的增长步长


typedef struct {
    ElementType * base; // 栈储存数据的地址
    int top;	        // 表示栈顶位置
    int size;	        // 栈的长度
    int increment;	    // 增长步长
} SqStack;

Status InitStack(SqStack &S);

Status StackEmpty(SqStack &S);

Status Push(SqStack &S, ElementType e);

Status Pop(SqStack &S, ElementType &e);

void OutStack(SqStack S);

void test_sq_stack();

#endif //DATASTRUCTUREANDALGORITHM_SQ_STACK_H
