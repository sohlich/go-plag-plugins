// linseznam.cpp : Defines the entry point for the console application.
//

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

typedef struct {
	int vyska;
	float vaha, vek;
	char * jmeno;
} tClovek;

typedef struct tsDbClovek {
	tClovek clovek;
	struct tsDbClovek * ukDalsi;
} tDbClovek;

typedef tDbClovek * tUkDbClovek;

/**********************************************************/
/* insertFirst - vkladani noveho prvku na zacatek seznamu */
/**********************************************************/
tUkDbClovek insertFirst(tUkDbClovek db, tClovek clovek) {
	tUkDbClovek tmpUk;
	tmpUk=(tUkDbClovek)malloc(sizeof(tDbClovek));
	if (tmpUk == NULL) {
		printf("\nNelze naalokovat pamet!!!\n");
		exit(1);
	}
	tmpUk->ukDalsi=db;
	tmpUk->clovek=clovek;
	return tmpUk;
}
/********************************************************/
/* insertLast -  vkladani noveho prvku na konec seznamu */
/********************************************************/
tUkDbClovek insertLast(tUkDbClovek db, tClovek clovek) {
  tUkDbClovek tmpUk, tmpBeg;
	tmpBeg=db; /*prepokladam ze db ukazuje na zatek...*/
  while (db->ukDalsi!=NULL) {
    db=db->ukDalsi; /*proto dojdu az na konec seznamu*/
  }
	tmpUk=(tUkDbClovek)malloc(sizeof(tDbClovek));  /*vytvorim novy prvek*/
	if (tmpUk == NULL) {
		printf("\nNelze naalokovat pamet!!!\n");
		exit(1);
	}
	db->ukDalsi=tmpUk; /*a vlozim ho za posledni...*/
	tmpUk->clovek=clovek;
	tmpUk->ukDalsi=NULL;
	return tmpBeg;
}
/******************************************************************/
/* insert -  vkladani noveho prvku za prvek, na ktery ukazuje db */
/******************************************************************/
tUkDbClovek insert(tUkDbClovek db, tClovek clovek) {
  tUkDbClovek tmpUk;
	tmpUk=(tUkDbClovek)malloc(sizeof(tDbClovek));  /*vytvorim novy prvek*/
	if (tmpUk == NULL) {
		printf("\nNelze naalokovat pamet!!!\n");
		exit(1);
	}
	tmpUk->ukDalsi=db->ukDalsi; /*novy bude ukazovat tam, kam ukazoval db*/
	tmpUk->clovek=clovek;
	db->ukDalsi=tmpUk; /*a db bude ukazovat na novy*/
	return tmpUk;
}

/************************************************************/
/* search -  vyhledani prvku "clovek" v seznamu "db".		*/
/* Porovnavejte pouze inicializovane (nenulove) hodnoty!!!  */
/************************************************************/
tUkDbClovek search(tUkDbClovek  db, tClovek clovek) {
  tUkDbClovek tmpUk, tmpBeg=NULL;
  tClovek tmpCl;
  tmpUk=db;
  while (tmpUk !=NULL)  /* Prochazim cely seznam */
  {
	tmpCl=tmpUk->clovek;
	if( tmpCl.vyska!=0 && tmpCl.vyska==clovek.vyska )  /* Porovnava hledaneho cloveka se vsemi ostatnimi podle vsech kriterii */
		if( tmpCl.vaha!=0 && tmpCl.vaha==clovek.vaha )
			if( tmpCl.vek!=0 && tmpCl.vek==clovek.vek)
				if( tmpCl.jmeno!=0 && strcmp((tmpCl.jmeno), (clovek.jmeno)) ==0 )
					tmpBeg=tmpUk;  /* ulozi ukazatel na cloveka, ktery se hleda */
	tmpUk=tmpUk->ukDalsi;
  }
  return tmpBeg;
}

/********************************************************/
/* deleteFirst -  vymaz prvniho prvku v seznamu	        */
/********************************************************/
tUkDbClovek deleteFirst(tUkDbClovek db) {
	tUkDbClovek tmpUk;
	if (db==NULL) return NULL;				/* jestlize je seznam prazdny, pak okamzity navrat */
	tmpUk=db->ukDalsi;
	free(db);								/* uvolneni 1. prvku seznamu z dyn. pameti */
	return tmpUk;
}

/********************************************************/
/* deleteLast -  vymaz posledniho prvku v seznamu	    */
/********************************************************/
tUkDbClovek deleteLast(tUkDbClovek db) {
  tUkDbClovek tmpUk, tmpBeg;
	if (db==NULL) return NULL;				/* jestlize je seznam prazdny, pak okamzity navrat */
	tmpBeg=db; /*prepokladam ze db ukazuje na zatek...*/
  while (db->ukDalsi!=NULL) {
    tmpUk=db;
    db=db->ukDalsi; /*proto dojdu az na konec seznamu*/
  }
  tmpUk->ukDalsi=NULL;  /*v tmpUk mam ulozeny preposledni prvek seznamu a smazu ukazatel na posledni*/
  free(db); /*smazu posledni prvek*/
	return tmpBeg;
}

/********************************************************************************/
/* delete -  vymaz libovolneho prvku v seznamu za prvkem, na ktery ukazuje uk	*/
/********************************************************************************/
tUkDbClovek deleteAny(tUkDbClovek uk) {
  tUkDbClovek tmpUk;
  tmpUk=uk->ukDalsi;  /*v tmpUk je ten, ktery se ma zmazat*/
  uk->ukDalsi=tmpUk->ukDalsi; /*ukDalsi musi ukazovat az za prvek, ktery se smaze (tj. na ten, na tekry ukazuje tmpUk)*/
  free(tmpUk);
	return uk;
}

void main(int argc, char * argv[])
{	
	int i, test, vyska;
	float vaha, vek;
	char * jmeno;
	FILE *jm_soub;
	tClovek searchTest={0,0,0,"Matej Kocour"}, nacti[5];
	tUkDbClovek db=NULL, tmpUk;

	if (argc >=2)
	{
		if( (jm_soub = fopen(argv[1], "r")) == NULL) {
			fprintf(stderr, "Soubor %s se nepodarilo otevrit.\n", argv[1]);
			printf("Soubor %s se nepodarilo otevrit.\n", argv[1]);
			exit(1);
		}
			else {
				jm_soub = fopen(argv[1], "r");
				for(i=0; i<5; i++) {				
					fscanf(jm_soub, "%i %.1f %.1f %s\n", &nacti[i].vyska, &nacti[i].vaha, &nacti[i].vek, &nacti[i].jmeno);				
				}			
				fclose(jm_soub);
				printf("\nNacteni informaci ze souboru dokonceno");
			}
		
		db=insertFirst(db, nacti[0]);
		db=insertFirst(db, nacti[1]);
		db=insertLast(db, nacti[2]);
		db=insert(db, nacti[3]);
		db=insert(db, nacti[4]);
		printf("\nInformace ulozeny do seznamu");
		tmpUk=search(db, nacti[2]);
		if (tmpUk!=NULL && tmpUk->clovek.jmeno!=NULL) printf("Found: %s\n", tmpUk->clovek.jmeno);
		tmpUk=search(db, searchTest);
		if (tmpUk!=NULL && tmpUk->clovek.jmeno!=NULL) printf("Found: %s\n", tmpUk->clovek.jmeno);
		db=deleteLast(db);
		db=deleteAny(db);
		while ((db=deleteFirst(db))!=NULL); /* smazani celeho seznamu */
		printf("\nSeznam je smazan");
	}
	else printf("parametr \"jmeno souboru\" nebyl zadan!\n");
}