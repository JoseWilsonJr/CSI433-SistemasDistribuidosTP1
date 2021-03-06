package uws;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

public class UFOPServer
{
	private ArrayList<Cidade> cidades = new ArrayList<Cidade>();

	public UFOPServer()
	{
		BufferedReader conteudoCSV = null;

		try {
			conteudoCSV = new BufferedReader(new FileReader("br_postal_codes.csv"));

			String linha = "";
			String[] arrayLinha;
			Cidade cidade = new Cidade();
			while ((linha = conteudoCSV.readLine()) != null) {
				arrayLinha = linha.split(",");

				cidade.setCep(arrayLinha[0]);	
				cidade.setNome(arrayLinha[1]);
				cidade.setUf(arrayLinha[2]);
				cidade.setLatitude(Double.parseDouble(arrayLinha[3]));
				cidade.setLongitude(Double.parseDouble(arrayLinha[4]));

				this.cidades.add(cidade);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado: \n" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Erro: \n" + e.getMessage());
		} finally {
			if (conteudoCSV != null) {
				try {
					conteudoCSV.close();
				} catch (IOException e) {
					System.out.println("IO Erro: \n" + e.getMessage());
				}
			}
		}

		for (Cidade tmp : cidades) {
			System.out.println("Nome" + tmp.getNome());
		}
	}

	// public static void main(String[] args)
	// {
	// 	UFOPServer servico = new UFOPServer();
	// }

	public String buscaCEP(String nomeCidade)
	{
		String cep = "";
		for (Cidade cidade : this.cidades) {
			if (nomeCidade.equals(cidade.getNome())) {
				cep = cidade.getCep();
			}
		}
		return cep;
	}

	// public String buscaCEP(Double latitude, Double longitude)
	// {
	// 	String cep = "";	
	// 	Double distanciaAuxiliar;
	// 	Double distancia = distancia(
	// 		latitude,
	// 		longitude,
	// 		this.cidades.get(0).getLatitude(),
	// 		this.cidades.get(0).getLongitude()
	// 	);

	// 	for (Cidade cidade : this.cidades) {
	// 		distanciaAuxiliar = distancia(
	// 			latitude,
	// 			longitude,
	// 			cidade.getLatitude(),
	// 			cidade.getLongitude()
	// 		);

	// 		if (distanciaAuxiliar < distancia) {
	// 			distancia = distanciaAuxiliar;
	// 			cep = cidade.getCep();
	// 		}
	// 	}

	// 	return cep;
	// }

	private Double distancia(Double lat1, Double long1, Double lat2, Double long2)
	{
		int EARTH_RADIUS_KM = 6371;
		// Conversão de graus pra radianos das latitudes
		double firstLatToRad = Math.toRadians(lat1);
		double secondLatToRad = Math.toRadians(lat2);

		// Diferença das longitudes
		double deltaLongitudeInRad = Math.toRadians(long2 - long1);

		// Cálcula da distância entre os pontos
		return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
		* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
		* Math.sin(secondLatToRad))
		* EARTH_RADIUS_KM;
	}
}