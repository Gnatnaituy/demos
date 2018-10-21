//
// Created by Hasaker on 2018/10/20.
//

#include <cstdlib>
#include <cstdio>
#include "sq_stack.h"


Status InitStack_Sq(SqStack_Sq &S, int size, int increment){
    S.base = (CharElementType *) malloc(size * sizeof(CharElementType));
    if (S.base == nullptr)
        return OVERFLOW;
    S.top = 0;
    S.size = size;
    S.increment = increment;

    return OK;
}


Status StackEmpty_Sq(SqStack_Sq &S) {
    if (S.top == 0)
        return TRUE;
    else
        return FALSE;
}


Status Push_Sq(SqStack_Sq &S, CharElementType e) {
    CharElementType * newBase;
    if (S.top >= S.size) {
        newBase = (CharElementType *) realloc(S.base, (S.size + S.increment) * sizeof(CharElementType));
        if (newBase == nullptr)
            return OVERFLOW;
        S.base = newBase;
        S.size += S.increment;
    }
    S.base[S.top++] = e;
    return OK;
}


Status Pop_Sq(SqStack_Sq &S, CharElementType &e) {
    if (StackEmpty_Sq(S) == FALSE) {
        S.top--;
        e = S.base[S.top];
    } else {
        printf("This stack is already empty !");
    }

    return OK;
}


Status GetTop_Sq(SqStack_Sq &S, CharElementType &e) {
    if (StackEmpty_Sq(S) == FALSE) {
        e = S.base[S.top - 1];
    } else {
        printf("This is a empty stack !");
    }
    return OK;
}


Status bracket_matching(char * expression, int n) {
    int i = 0;
    CharElementType e;
    SqStack_Sq S;
    InitStack_Sq(S, n, 5);

    while (i < n) {
        switch (expression[i]) {
            case '(':
            case '[':
                Push_Sq(S, expression[i]);
                i++;
                break;
            case ')':
            case ']':
                if (StackEmpty_Sq(S) == TRUE) {
                    return ERROR;
                } else {
                    GetTop_Sq(S, e);
                    if ((expression[i] == ')' && e == '(') || (expression[i] == ']' && e == '[')) {
                        Pop_Sq(S, e);
                        i++;
                    } else {
                        return ERROR;
                    }
                }
                break;
            default:
                i++;
                break;
        }
    }

    if (StackEmpty_Sq(S) == TRUE) {
        printf("This is a right bracket sequence.");
        return OK;
    } else {
        printf("This is a wrong bracket sequence.");
        return ERROR;
    }
}