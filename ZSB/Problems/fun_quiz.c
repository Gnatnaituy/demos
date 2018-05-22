//
// Created by Hasaker on 29/11/2017.
//

#include <stdio.h>


void swap1(int x,int y)
{
    int w;                //交换x与y的值
    w=x; x=y; y=w;
}
void swap2(int *p1,int *p2)
{
    int *p;               //交换p1与p2里存的地址
    p=p1; p1=p2; p2=p;
}
void swap3(int *pa, int *pb)
{
    int p;                //交换pa与pb所对应变量的值
    p=*pa; *pa=*pb; *pb=p;
}

void practice_1()    // different type of swap function
{
    int a = 5,b = 7, *ptr1, *ptr2;
    ptr1=&a; ptr2=&b;
    swap1(*ptr1,*ptr2);
    printf("data1=%d\tdata2=%d\n",*ptr1,*ptr2);
    swap2(ptr1,ptr2);
    printf("data3=%d\tdata4=%d\n",*ptr1,*ptr2);
    printf("data5=%d\tdata6=%d\n",a,b);
    swap3(ptr1,ptr2);
    printf("data7=%d\tdata8=%d\n",*ptr1,*ptr2);
    printf("data9=%d\tdata10=%d\n",a,b);
}

void practice_2()    // what is the value of n eventually ?
{
    int i,n=0;
    for( i=2;i<5;i++){
        do{
            if(i%3)
                continue;
            n++;
        }while(!i);
        n++;
    }
    printf("n=%d\n",n);
}

void practice_3()    // what is the output ?
{
    int a[][3]={1,2,3,4,5,6};
    int i, j, (*p)[3];	p=a;
    printf("%d %d %d\n",p[0][0],*(p[0]+1),(*p)[2]);
    printf("%d %d %d\n",p[1][0],*(p[1]+1),(*(p+1))[2]);
    for (i=0; i<2; i++)
        for (j=0; j<3; j++)
            printf("%d ",*(*(p+i)+j));
    printf("\n");
}

void practice_4()    // what is the output ?
{
    char a[]="Language",b[]="Programe";
    char *p1,*p2; int k;
    p1=a; p2=b;
    for(k=0;k<=7;k++)
        if(*(p1+k)==*(p2+k))
            printf("%c",*(p1+k));
}

void practice_5()    // what is the output ?
{	int i,j,k,a=5,m[6][6];
    i=1;
    k=(a+1)/2;
    for (j=1;j<=a*a;j++)
    {	m[i][k]=j;
        if (j%a==0)
        {
            i=(i%a)+1;
            continue;
        }
        k=(k%a)+1;
        i--;
        if (i==0) i=a;
    }
    for (i=1;i<=a;i++)
    {
        for (j=1;j<=a;j++)
            printf("%4d",m[i][j]);
        printf("\n");
    }
}
