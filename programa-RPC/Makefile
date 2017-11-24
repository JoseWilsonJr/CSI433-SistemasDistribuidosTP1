.c.o:
	gcc -c -Wall -DRPC_SVC_FG $<

all: client server

client: UFOPClient.o UFOPWebService_clnt.o UFOPWebService_xdr.o
	gcc -o client UFOPClient.o UFOPWebService_clnt.o UFOPWebService_xdr.o -lnsl

server: UFOPServer.o UFOPWebService_svc.o UFOPWebService_xdr.o
	gcc -o server UFOPServer.o UFOPWebService_svc.o UFOPWebService_xdr.o -lrpcsvc -lnsl

UFOPWebService.h: UFOPWebService.x
	rpcgen -C UFOPWebService.x

clean:
	rm *.o UFOPWebService.h UFOPWebService_clnt.* UFOPWebService_svc.* UFOPWebService_xdr.*

UFOPClient.c: UFOPWebService.h
UFOPServer.c: UFOPWebService.h