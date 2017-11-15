#include <stdio.h>
#include <memory.h>
/* 专升本 */


swap1(int x,int y)
{
    int w;                //交换x与y的值
    w=x; x=y; y=w;
}
swap2(int *p1,int *p2)
{
    int *p;               //交换p1与p2里存的地址
    p=p1; p1=p2; p2=p;
}
swap3(int *pa, int *pb)
{
    int p;                //交换pa与pb所对应变量的值
    p=*pa; *pa=*pb; *pb=p;
}
practice_1()    // different type of swap function
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

practice_2()    // what is the value of n at the end ?
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

practice_3()    // what is the output ?
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

practice_4()    // what is the output ?
{
    char a[]="Language",b[]="Programe";
    char *p1,*p2; int k;
    p1=a; p2=b;
    for(k=0;k<=7;k++)
        if(*(p1+k)==*(p2+k))
            printf("%c",*(p1+k));
}

practice_5()    // what is the output ?
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


// 程序填空题
practice_6()    // calculate 1-2+3-4+5-.....+99-100
{
    int i = 1, j = 1, s = 0;
    while(i <= 100) //while(____)
    {
        s += i * j; // s += ____
        i++;
        j = -j; // ____
    }
    printf("The result: %d\n", s);

}

practice_7()    // calculate t = 1 + 1/(2^2) + 1/(3^2) + .... + 1/(n^2)
{
    int j,n;
    double t=1.0;
    printf("Please input n:\n");
    scanf("%d", &n);                // scanf(____);
    for(j = 2; j <= n; j++)         // for(____;j<=n;j++)
        t += 1.0/(j * j);           //     ____;
    printf("Result:%lf\n",t);
}

practice_8()    // reverse string and output it
{
    int i, j;
    char st[60], ch;
    scanf("%s", st);
    for(i=0, j=strlen(st)-1; i<j; i++, j--)   //for(i=0,j=____;____;i++,j--)
    {
        ch = st[i];
        st[i] = st[j];                        //____;
        st[j] = ch;
    }
    printf("%s\n", st);
}

practice_9()    // input string and output its digits only
{ char st[80];
    int i=0,j=0;
    scanf("%s",st);
    while(st[i])
    {
        if(st[i] >= '0' && st[i] <= '9')//if(____)
            st[j++] = st[i];            //st[____]=st[i];
        i++;
    }
    st[j] = '\0';                       //____;
    printf("%s\n",st); }

practice_10()   // convert uppercase to lowercase in string
{
    int i=0;
    char str[80];
    scanf("%s", str);                //scanf("____",str);
    while(i<80)
    { if(str[i]>='A'&&str[i]<='Z')
        str[i] = str[i] + 32;        //str[i]=____;
        i++;                         //____;
    }
    printf("%s\n",str);
}


// 写程序结果
practice_11()
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

int main(void)
{
    practice_11();
}
