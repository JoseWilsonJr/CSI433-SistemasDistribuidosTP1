package uws;

import java.io.IOException;
import java.util.Scanner;

/*
	Implementa as threads que são geradas pelo SimulaClientes.
	A thread faz uma requisição do UDPClient passando os parâmetros
	127.0.0.1 e Ipatinga que refere ao IP do Proxy e nome da cidade.
	A requisição é realizada pelo método Runtime.getRuntime().exec()
	do Java para simular uma requisição do terminal. 
*/
public class SimulaClientesThread extends Thread
{
	public SimulaClientesThread()
	{	
		this.start();
	}

	public void run()
    {
    	try {
	       	Process processo = Runtime.getRuntime().exec("java uws.UDPClient 127.0.0.1 Ipatinga");
			Scanner ler = new Scanner(processo.getInputStream());
			System.out.println(ler.nextLine().toString());
		} catch (IOException e) {
			System.out.println("IO Erro: " + e.getMessage());
		}
   	}
}