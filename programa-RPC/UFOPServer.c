#include "UFOPWebService.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Cidade{
	char cep[10];
	char nome[50];
	char uf[50];
	double latitude;
	double longitude;	
};

struct Cidade cidades[5523];

palavra * buscacepnome_1_svc(palavra *argp, struct svc_req *rqstp){

	static palavra cep;

	printf("Buscando CEP de: %s\n", argp->texto);
	printf("Comparando com: %s\n", cidades[0].nome);
	printf("%d\n",strcmp((char*)cidades[0].nome,(char*)argp->texto)==0);

	for(int i=0; i<5523; i++){
		//if(strcmp(cidades[0].nome,argp->texto)==0){
		if(strcmp(argp->texto,cidades[i].nome)){
			printf("Entrou!!!\n");
			printf("%s \n",cidades[i].nome);
			fflush(stdout);
		}
		//printf("%d: %s\n",i,cidades[i].nome);	
	}
	
	return &cep;
}

palavra * buscacepcoordenadas_1_svc(coordenadas *argp, struct svc_req *rqstp){

	static palavra * cep;

	printf("Compadando coordenadas:\n - Lat: %.4f\n - Lon: %.4f",argp->latitude, argp->longitude);

	return cep;
}

int * learquivo_1_svc(void * argp, struct svc_req *rqstp){

	static int teste;
	const char s[2] = ",";
	char *token;
	char linha[90];
	char *result; 

	FILE * arquivo;
	
	if ((arquivo = fopen("br_postal_codes.csv","r")) == NULL){
		printf("\nErro ao abrir o arquivo!\n");

		teste = 0;
	}else{

		token = strtok(arquivo, s);
		//Inicializa i para inserir em array
		int i = 0;
		while(!feof(arquivo) && i < 5530){
			
			result = fgets(linha, 90, arquivo);

			if(result){
				token = strtok(result, s);
			}
			//Aloca aqui a estrutura de dados para um elemento
			int j = 0;
			while(token != NULL){
				// Faz aqui a inserção campo a campo do elemento
				if(j==0){
					strcpy(cidades[i].cep,token);
				}
				if(j==1){
					strcpy(cidades[i].nome,token);
				}
				if(j==2){
					strcpy(cidades[i].uf,token);
				}
				if(j==3){
					cidades[i].latitude = strtod(token, NULL);			
				}
				if(j==4){
					cidades[i].longitude = strtod(token, NULL);
				}

				token = strtok(NULL, s);
				j++;
			}
			//Passa aqui para o proximo elemento da lita.
			i++;
		}

		fclose(arquivo);
		printf("\nArquivo carregado com sucesso!\n");
		
		printf("Nome Cidade: %s\n", cidades[0].nome);
		printf("CEP: %s\n", cidades[0].cep);
		printf("UF: %s\n", cidades[0].uf);
		printf("latitude: %.4f\n", cidades[0].latitude);
		printf("longitude: %.4f\n", cidades[0].longitude);

		teste = 1;
	}

	return &teste;
}