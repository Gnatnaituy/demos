#include <stdio.h>
#include <memory.h>
#include "Problems/2013_exam.c"
#include "Problems/fun_quiz.c"


int fun233(int x,int y)
{
    static int m=0,i=2;
    i+=m+1;
    m=i+x+y;
    printf("%3d",m);
}


int main()
{

    practice_4();

//    int i, x, y;
//    i=x=y=0;
//    do {
//        ++i;
//        if(i%2 != 0) {
//            x = x + i;
//            i++;
//        }
//        y = y + i++;
//    } while (i <= 7);
//    printf("%d\t%d", x, y);


//    int *p[4];
//    int (*q)[4];


//    fun233(1,1);
//    fun233(1,1);
//    fun233(1,1);


//    int a =0;
//    char ch;
//    scanf("%d%3c", &a, &ch);
//    printf("%d,%c", a, ch);


//    int x = 0, y;
//    if(x)
//        if(x > 0) y = 1;
//    else if(x < 0) y = -1;
//    else y = 0;


//    char a[] = "0123498765", *p = a + 4;
//    char b = *p++;
//    char c = *p;


//    struct studentinfo {
//        char name[10];
//        int SNO;
//        char sex;
//    } student, *ps = &student;
//    printf("%d,%d", sizeof(student), sizeof(ps));


//    char str[20], i;
//    for(i = 0, str[i] = getchar(); str[i] != '\n';)
//        str[++i] = getchar();
//    for(i = 0, str[i]=getchar(); str[i] != '\0'; i++)
//        str[i] = scanf("%c", str[i]);


//    int a[3][4] = {1,3,5,7,9,11,13,15,17,19,21,13};
//    int (*ptr)[4];
//    int sum = 0;
//    ptr = a;
//    for(int i = 0; i < 3; i++)
//        for(int j = 0; j < 2; j++) {
//            printf("%d\t", *(*(ptr + i) + j));
//            sum += *(*(ptr + i) + j);
//        }
//    printf("\n%d", sum);


    return 0;
}
