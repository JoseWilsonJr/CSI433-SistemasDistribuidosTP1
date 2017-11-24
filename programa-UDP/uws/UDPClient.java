package uws;

import java.net.*;
import java.io.*;

/*
	Classe implementada para fazer a requisição do cliente para o Proxy.
	Ela recebe via parâmetro o IP do Proxy, o nome da cidade ou a coordenada
	geográfica (latitude e longitude), faz o empacotamento caso necessário e
	envia para o Proxy. O empacotamento só é necessário para requisição
	realizada por coordenada geográfica.
*/
public class UDPClient
{
    public static void main(String args[])
    {
    	// args give message contents and destination hostname
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();

			byte[] mensagem;
			int length;
			if (args.length == 3) {
				Coordenada coordenada = new Coordenada(
					Double.parseDouble(args[1]),
					Double.parseDouble(args[2])
				);
				length = coordenada.empacotar().length();
				mensagem = coordenada.empacotar().getBytes();
			} else {
				length = args[1].length();
				mensagem = args[1].getBytes();
			}

			InetAddress aHost = InetAddress.getByName(args[0]);
			int serverPort = 6789;
			DatagramPacket request = new DatagramPacket(
				mensagem,
				length,
				aHost,
				serverPort
			);
			aSocket.send(request);			                        
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(
				reply.getData(),
				reply.getOffset(),
				reply.getLength()
			));
		} catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e){
			System.out.println("IO: " + e.getMessage());
		} finally {
			if(aSocket != null) {
				aSocket.close();
			}
		}
	}		      	
}