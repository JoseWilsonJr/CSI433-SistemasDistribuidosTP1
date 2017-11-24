package uws;

/*
	Estrutura de dados para gravar o IP dos servidores
	e quantas requisições foram processadas em cada um.
*/
public class Servidor
{
	private String ip;
	private int qtdeRequisicao;

	public Servidor(String ip)
	{
		this.ip = ip;
		this.qtdeRequisicao = 0;
	}

	public String getIp()
	{
		return ip;
	}

	public int getQtdeRequisicao()
	{
		return qtdeRequisicao;
	}

	public void addRequisicao()
	{
		qtdeRequisicao++;
	}
}