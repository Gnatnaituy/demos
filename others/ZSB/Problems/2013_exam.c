//
// Created by Hasaker on 16/12/2017.
//
#include <stdio.h>

void p_5() {
    int i;
    for(i=1; i<=5; i++) {
        if(i%2)
            printf("*");
        else
            continue;
        printf("#");
    }
}

void p_8() {
    char a[] = "0987654321", *p = a  + 4;
    printf("%c", *p++);
}

void p_4_2() {
    int i, f1 = 1, f2 = 1;
    for (i = 1; i <= 5; i++) {
        printf("\t%d\t%d", f1, f2);
        f1 = f1 + f2;
        f2 = f1 + f2;
    }
    printf("%d,%d", f1, f2);
}

void fun(int n) {
    static int fn = 1;
    fn = fn * n;
    printf("\t%d", fn);
}

void p_4_3() {
    int i;
    for(i = 2; i <= 5; i++)
        fun(i);
}

typedef struct {
    char name[20];
    int count;
} item;
item mans[5] = {{"aaa", 0}, {"bbb", 0}, {"ccc", 0}, {"ddd", 0}, {"eee", 0}};

void statistic(const char *ticket) {
    int valid_count = 0;
    int is_valid = 0;
    for(int i = 0; i < 5; i++)
        is_valid += ticket[i] - 48;
    if(is_valid == 3) {
        for(int i = 0; i < 5; i++)
            if(ticket[i] - 48 == 1)
                mans[i].count += 1;
        valid_count += 1;
    }

    printf("name\tcount\n");
    for(int i = 0; i < 5; i++)
        printf("%s\t\t%d\n", mans[i].name, mans[i].count);
    printf("Total valid tickets: %d\n", valid_count);
}

int abc(char * ps) {
    char *p;
    p = ps;
    while (*p++);
    return (p - ps);
}