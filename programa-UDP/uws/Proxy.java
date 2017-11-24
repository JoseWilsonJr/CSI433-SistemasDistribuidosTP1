package uws;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
    Classe recebe o IP dos servidores que estarão disponíveis para serviço.
    Cria um HashMap de servidores para o Proxy fazer o balanceamento de carga.
    O balanceamento de carga é realizada pela quantidade de requisições que um
    servidor processou, ou seja, quando o cliente envia uma requisição para
    o proxy ele envia a requisição para o servidor com o menor número de
    requisições processadas
*/
public class Proxy
{
    private static HashMap<Integer, Servidor> servidores = new HashMap<Integer, Servidor>();

    public static void main(String args[])
    {
        if (args.length == 0) {
            System.out.println("Informe os IP's dos servidores corretamente");
            return;
        }

        for (int i = 0; i < args.length; i++) {
            servidores.put(i, new Servidor(args[i]));
        }

        DatagramSocket aSocket = null;
        try{
            aSocket = new DatagramSocket(6789);
                    // create socket at agreed port
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                System.out.println(new String(request.getData()));

                int idServer = 0;
                int qtdeRequisicaoAux;
                String ipServer = servidores.get(0).getIp();
                int qtdeRequisicao = servidores.get(0).getQtdeRequisicao();
                for (Integer key : servidores.keySet()) {
                	qtdeRequisicaoAux = servidores.get(key).getQtdeRequisicao();
                    if (qtdeRequisicaoAux < qtdeRequisicao) {
                        qtdeRequisicao = qtdeRequisicaoAux;
                        ipServer = servidores.get(key).getIp();
                        idServer = key;
                        break;
                    }
                }

                InetAddress aHost = InetAddress.getByName(ipServer);
                int serverPort = 6790;
                DatagramPacket requestServer = new DatagramPacket(
                    request.getData(),
                    request.getLength(),
                    aHost,
                    serverPort
                );
                aSocket.send(requestServer);                                  
                buffer = new byte[1000];
                DatagramPacket replyServer = new DatagramPacket(buffer, buffer.length);   
                aSocket.receive(replyServer);
                servidores.get(idServer).addRequisicao();
                
                DatagramPacket replyProxy = new DatagramPacket(
                    replyServer.getData(),
                    replyServer.getLength(), 
                    request.getAddress(),
                    request.getPort()
                );
                aSocket.send(replyProxy);
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