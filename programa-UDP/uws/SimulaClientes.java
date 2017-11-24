package uws;

import java.io.IOException;
import java.util.Scanner;

/*
	Ferramenta auxiliar que permita simular requisições de um cliente.
	Este simulador deve receber como argumento de programa o número de
	requisições que se deseja e o intervalo de tempo entre estas.
	Para cada requisição simulada é gerada uma thread para realização
	de requisições paralelas. 
*/
public class SimulaClientes
{
	public static void main(String[] args)
	{
		try {
			if (args.length < 2) {
				System.out.println("Parâmetros insuficientes");
			}

			for (int i = 0; i < Integer.parseInt(args[0]); i++) {
				SimulaClientesThread thread = new SimulaClientesThread();
				Thread.sleep(Integer.parseInt(args[1]));
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted Erro :" + e.getMessage());
		}
	}
}