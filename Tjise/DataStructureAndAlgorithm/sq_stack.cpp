//
// Created by Hasaker on 2018/10/18.
//

#include <stdio.h>
#include <stdlib.h>
#include "sq_stack.h"


Status InitStack(SqStack &S)
{
    // 申请一个长度为STACK_INIT_SIZE * ElementType内存空间并把内存空间起始地址赋值给栈S的element
    S.base = (ElementType *) malloc(STACK_INIT_SIZE * sizeof(ElementType));
    // 申请失败 抛出异常
    if (!S.base)
        exit(OVERFLOW);
    // 将栈顶初始化为0
    S.top = 0;
    // 初始化栈的长度和增长步长
    S.size = STACK_INIT_SIZE;
    S.increment = STACK_INCREMENT;
    return OK;
}


Status StackEmpty(SqStack &S)
{
    if (S.top == 0)
        return TRUE;
    else
        return FALSE;
}


/*
*入栈操作
*S表示要操作的栈,e表示要插入的元素
*/
Status Push(SqStack &S, ElementType e)
{
    // 如果栈顶到栈底的距离大于或等于栈的长度了,表示栈满了,因此要申请一个更大的内存空间来存放这个栈
    if (S.top >= S.size) {
        // realloc 是 reallocate的缩写 及'重新分配'
        ElementType * newBase = (ElementType *) realloc(S.base, (S.size + S.increment) * sizeof(ElementType));
        if (newBase == nullptr)
            return OVERFLOW;
        // 把栈头指向从新分配后的内存空间
        S.base = newBase;
        S.size += S.increment;
    }
    // 先将S.base[S.top]出的内容设置为e,再将top+1,表示栈顶在从栈底开始的第1个元素处
    S.base[S.top++] = e;

    return OK;
}


// &e表示对e的引用 例如int &a = b; 其实a就是b,只是给b取了另一个名字
Status Pop(SqStack &S, ElementType &e)
{
    if (StackEmpty(S) == FALSE) {
        S.top--;
        e = S.base[S.top];
    } else {
        printf("已经到栈底啦!\n");
    }

    return OK;
}


void OutStack(SqStack S)
{
    // 用于接收出栈的值和作为printf的参数
    ElementType e;

    if (StackEmpty(S) == TRUE) {
        printf("这是一个空栈!\n");
    } else {
        while (StackEmpty(S) == FALSE) {
            Pop(S, e);
            printf("|%d|\n", e);
        }
        printf("\n");
    }
}


void test_sq_stack()
{
    SqStack s;
    ElementType a;
    int choice;

    printf("第一次使用必须初始化!\n");
    do {
        printf("\n-----菜单-----\n");
        printf("1 初始化顺序栈\n");
        printf("2 插入一个元素\n");
        printf("3 删除栈顶元素\n");
        printf("4 结束程序运行\n");
        printf("-------------\n");
        printf("请输入你的选择: ");

        scanf("%d", &choice);
        switch (choice) {
            case 1:
                InitStack(s);
                OutStack(s);
                break;
            case 2:
                printf("请输入要插入的元素数据: ");
                scanf("%d", &a);
                Push(s, a);
                printf("入栈之后的栈:\n");
                OutStack(s);
                printf("\n");
                break;
            case 3:
                Pop(s, a);
                printf("栈顶元素%d出栈之后的栈:\n", a);
                OutStack(s);
                printf("\n");
                break;
            case 4:
                exit(0);
            default:
                break;
        }
    } while (choice <= 4);
}


