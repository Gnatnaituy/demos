//
// Created by Hasaker on 29/11/2017.
//

#include <stdio.h>
#include <string.h>


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

