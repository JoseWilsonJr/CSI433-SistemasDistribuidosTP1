package uws;

import java.net.*;
import java.io.*;

/*
    A classe ServerThread implementa as threads que são criadas pelo
    UDPServer para cada requisição realizada pelo Proxy. A thread recebe
    um pacote com a coordenada ou o nome da cidade, se receber uma coordenada
    busca o CEP mais próximo, caso contrário busca o CEP da cidade informada
    e envia para o Proxy. A busca do CEP é implementada pela classe UFOPServer.
*/
public class ServerThread extends Thread
{
	DatagramSocket aSocket = null;
    DatagramPacket request = null;

	public ServerThread(DatagramSocket aSocket, DatagramPacket request)
	{
		this.aSocket = aSocket;
		this.request = request;
		this.start();
	}

	public void run()
    {
        try {
            System.out.println("Recebeu: " + new String(request.getData()));
            String data = new String(request.getData());

            String cep;
            UFOPServer service = new UFOPServer();
            if (data.contains(";")) {
                Coordenada coordenada = new Coordenada();
                coordenada.desempacotar(data.trim());
                cep = service.buscaCEP(
                    coordenada.getLatitude(),
                    coordenada.getLongitude()
                );
            } else {
                cep = service.buscaCEP(data.trim());
            }

            DatagramPacket reply = new DatagramPacket(
                cep.getBytes(),
                cep.length(), 
                request.getAddress(),
                request.getPort()
            );

            aSocket.send(reply);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}