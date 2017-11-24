package uws;
/*
	Classe que fornece a estrutura de dados para
	UFOPServer armazenar os dados do arquivo csv.

*/
public class Cidade
{
	private String cep;
	private String nome;
	private String uf;
	private Double latitude;
	private Double longitude;

	public String getCep()
	{
		return this.cep;
	}

	public void setCep(String cep)
	{
		this.cep = cep;
	}

	public String getNome()
	{
		return this.nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getUf()
	{
		return this.uf;
	}

	public void setUf(String uf)
	{
		this.uf = uf;
	}

	public Double getLatitude()
	{
		return this.latitude;
	}

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public Double getLongitude()
	{
		return this.longitude;
	}

	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}
}