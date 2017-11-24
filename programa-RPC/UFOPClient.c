#include "UFOPWebService.h"  /* Created for us by rpcgen - has everything we need ! */

#include <stdio_ext.h>
#include <stdlib.h>
#include <string.h>

palavra * buscacepnome(CLIENT *clnt, palavra nome) {

  static palavra * palavra;

  printf("NomeNafuncao:%s\n", nome.texto);

  strcpy(palavra->texto,buscacepnome_1(&nome,clnt)->texto);
  printf("palavra Texto: %s\n", palavra->texto);

  if (palavra==NULL) {
    fprintf(stderr,"Trouble calling remote procedure\n");
    exit(0);
  }
  return palavra;
}

/*char * buscacepcoordenadas(CLIENT *clnt, char nome[]) {

  coordenadas coords;
  static char cep[10];

  strcpy(cep,buscacepnome_1(nome,clnt));  

  if (cep==NULL) {
    fprintf(stderr,"Trouble calling remote procedure\n");
    exit(0);
  }
  return cep;
}*/

/* Wrapper function takes care of calling the RPC procedure */

/*resultado resultadofinal(CLIENT *clnt) {
  resultado *result;

  // Call the client stub created by rpcgen 
  result = resultadofinal_1(NULL,clnt);
  if (result==NULL) {
    fprintf(stderr,"Trouble calling remote procedure\n");
    exit(0);
  }
  return (*result);

}*/

int * learquivo(CLIENT *clnt){

  static int * result;
  // Call the client stub created by rpcgen 
  result = learquivo_1(NULL,clnt);
  if(result==NULL) {
    fprintf(stderr,"Trouble calling remote procedure\n");
    exit(0);
  }
  return result;
}

int main( int argc, char *argv[]) {
  CLIENT *clnt;
  if (argc!=2) {
    fprintf(stderr,"Usage: %s hostname\n",argv[0]);
    exit(0);
  }

  /* Create a CLIENT data structure that reference the RPC
     procedure SIMP_PROG, version SIMP_VERSION running on the
     host specified by the 1st command line arg. */

  clnt = clnt_create(argv[1], UWS_PROG, UWS_VERSION, "udp");

  if (clnt == (CLIENT *) NULL) {
    clnt_pcreateerror(argv[1]);
    exit(1);
  }

  int operacao; 
  palavra palavra;
  int * leituraArquivo;
  

  leituraArquivo = learquivo(clnt);
  
  if(*leituraArquivo == 1){
    printf("\nIndice carregador com sucesso!\n");
  }else{
    printf("\nFalha na construção do indice!!\n");
  }
  
  printf("\nMenu:");
  printf("\nOpção 1: Buscar CEP a partir do nome da cidade.");
  printf("\nOpção 2: Buscar CEP a partir das coordenadas.");
  printf("\nOpcao: ");
  scanf("%d",&operacao);

  if(operacao == 1){
    printf("\nEntre com o nome da cidade: ");
    __fpurge(stdin);
    fgets(palavra.texto,50,stdin);
    printf("nome: %s\n",palavra.texto);
    printf("%s\n", buscacepnome(clnt,palavra)->texto);
    //strcpy(palavra.texto, buscacepnome(clnt,palavra)->texto);
    printf("CEP: %s\n",palavra.texto);

  }

  return(0);
}