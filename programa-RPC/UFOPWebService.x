#define VERSION_NUMBER 1

struct coordenadas {
	double latitude;
	double longitude;
};
struct palavra{
	char texto[50];
};

program UWS_PROG {
   version UWS_VERSION {
   		palavra BUSCACEPNOME(palavra) = 1;
    	palavra BUSCACEPCOORDENADAS(coordenadas) = 2;
   		int LEARQUIVO() = 3;
	} = VERSION_NUMBER;
} = 555555678;