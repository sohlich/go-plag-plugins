#include <stdio.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>





    void vypisPole(int *pole, int delka)
    {
        int i;
        for(i=0;i<delka;i++)
        {
            if(i==(delka-1))
            {
                printf("%d\n",pole[i]);
            }else{
                printf("%d,",pole[i]);
            }
        }
    }


    void initPole(int *pole, int *delka)
        {
        char bubu[100];
                printf("Aktualni obsah pole:\n");
        vypisPole(pole,*delka);
        printf("Zadat jine hodnoty? (A/N)\n");
        char *bubucpy=fgets(bubu, 100, stdin);
        if(bubucpy==NULL)
        {
            printf("Neocekavany konec souboru\n");
            exit(0);
        }
        char c=0;
        if(strlen(bubu)!=0)
        {
            c=bubu[0];
        }


        char digit[100];
        char *tempch;
        int ok=1;
        int i;


        if((c=='A')||(c=='a'))
        {
            printf("Zadavejte hodnoty oddelene novym radkem, konec=K:\n");
            *delka=0;
            int digitint;
            char k=0;

            do
            {

                tempch=fgets(digit, 100, stdin);
                if(tempch!=NULL)
                {
                  if (digit[strlen(digit)-1]=='\n')
                  {
                      digit[strlen(digit)-1]='\0';
                  }
                }else{
                    printf("Neocekavany konec souboru\n");
                    exit(0);
                }


                if(strlen(digit)!=0)
                {
                    k=digit[0];
                }
                if((k=='k')||(k=='K'))
                {
                    break;
                }



                if(strlen(digit)==0)
                {
                    ok=0;
                    printf("Nespravny format, zkuste znovu\n");
                }else{
                    ok=1;
                }

                for (i=0;i<strlen(digit);++i)
                {
                    if (!isdigit(digit[i]))
                    {
                        ok=0;
                        printf("Nespravny format, zkuste znovu\n");
                        break;
                    }
                }

                if(ok==1)
                {
                    digitint=atoi(digit);
                    pole[*delka]=digitint;
                    (*delka)=(*delka) +1;
                }


            }while(1);

        }

        }
