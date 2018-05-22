//
// Created by Hasaker on 29/11/2017.
//

#include <stdio.h>

// 写程序结果
void practice_11()
{
    int k=6,n=0;
    while(k>0)
    {
        switch(--k)
        {
            case 1:   // case 1 and case 3 no break !!!
                n+=k;
            case 2:
            case 3:
                n+=k;
            default:
                break;
        }
        k--;
    }
    printf("%d",n);
}

void practice_12()
{
    int i,j,x=0;
    for(i=0;i<2;i++)
    {
        x++;
        for(j=0;j<3;j++)
        {
            if(j%2)
                continue;
            x++;
        }
        x++;
    }
    printf("x=%d\n",x);
}

int base_13(int n)
{
    return n/10+n%10;
}

void practice_13()
{
    int x=1234,y;
    y=base_13(base_13(base_13(x)));
    printf("%d\n",y);
}

int base_14(int x,int y)
{
    static int m=0,i=2;
    i+=m+1;
    m=i+x+y;
    return m;
}

void practice_14()
{
    int j=1,m=1,k;
    k=base_14(j,m);
    printf("%3d",k);
    k=base_14(j,m);
    printf("%3d",k);
}

int base_15(int n)
{
    static int s=1;
    s*=n;
    return s;
}

void practice_15()
{
    int i,s=0;
    for(i=1;i<=4;i++)
        s+=base_15(i);
    printf("%d\n",s);
}

void base_16(char *x,char *y)
{
    char t;
    t=*x;*x=*y;*y=t;
}

void practice_16()
{
    char *p="abc",*q="123";
    base_16(p,q);
    printf("%s,%s\n",p,q);
}

void base_17(char *x,char *y)
{
    char *t;
    t=x;x=y;y=t;
}

void practice_17()
{
    char *p="one",*q="two";
    base_17(p,q);
    printf("%s,%s\n",p,q);
}

void practice_18()
{
    float x=123.4567;
    printf("%f\n",(int)(x*100+0.5)/100.0);
}

void practice_19()
{
    int a=0,b=0,c=0;
    c=(a+=a-=5),(a=b,b+3);
    printf("%d,%d,%d\n",a,b,c);
}

void practice_20()
{
    int x=10,y=10;
    int a,b,c;
    a=--x==y?++y:--x;
    b=x++;
    c=y;
    printf("%d %d %d\n",a,b,c);
}

void practice_21()
{
    int x=7;
    do
    {
        printf("*");
        x--;
    }
    while(!(x%2));
}

void practice_22()   // Two-Dimensional Array
{
    int a[5][3]={{0},{1},{2}};
    int b[3][3] = {1,2,3};

    int c[2][3]={1,2,3,4,5,6};
    int i, *p;
    p = &c[0][0];
    for(i = 0; i < 6; i++)
        printf("%d", *(p + i));

    int d[3][2] = {10,20,30,40,50,60}, (*e)[2];
    e = d;
    int f = *(*(e+2)+1);
    int *g = *(e+2);
    int h = *(g+1);

    char ch[3][5]={"AAAA","BBB","CC"};
}

void practice_23()
{
    char str[]="aeiou",*p=str;
    for(int i=0; i < 6; i++)
        printf("%c\n", *p + i);
}

void base_24(int k){
    if(k > 0)
        base_24(k - 1);
    printf("%d", k);
}

void practice_24(){
    int k = 5;
    base_24(k);
}
