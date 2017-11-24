package uws;

import java.net.*;
import java.io.*;

/*
    Servidor implementado para receber as requisições do Proxy e enviar a resposta
    da requisição para o Proxy. Cada requisição do Proxy é criada uma Thread para
    processamento paralelo das requisições.
*/
public class UDPServer
{
    public static void main(String args[])
    {   
        DatagramSocket aSocket = null;
		try{
	    	aSocket = new DatagramSocket(6790);
			// create socket at agreed port
			byte[] buffer;
 			while (true) {
                buffer = new byte[1000];
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  				aSocket.receive(request);

                ServerThread serverThread = new ServerThread(aSocket, request);
    		}
		} catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
		} finally {
            if(aSocket != null) {
                aSocket.close();
            }
        }
    }
}