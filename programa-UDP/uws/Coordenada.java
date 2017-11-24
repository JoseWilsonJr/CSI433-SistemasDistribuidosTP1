package uws;

/*
	Classe responsável pelo empacotamento e desempacotamento
	da latitude e longitude de um ponto.
*/
public class Coordenada
{
	private Double latitude;
	private Double longitude;
	private String delimitador = ";";

	public Coordenada() {}

	public Coordenada(Double latitude, Double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public Double getLongitude()
	{
		return longitude;
	}

	public String empacotar()
	{
		return latitude + delimitador + longitude;
	}

	public void desempacotar(String pacote)
	{
		String array[] = pacote.split(delimitador);

		latitude = Double.parseDouble(array[0]);
		longitude = Double.parseDouble(array[1]);
	}
}