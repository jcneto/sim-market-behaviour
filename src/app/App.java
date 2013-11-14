package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.NumberFormat;

import sim.enviroment.Modelo;
import app.statistics.StatisticsData;
import desmoj.core.simulator.Experiment;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int MAX_CLIENTE = 50;
		int MAX_RAIO = 10;
		StringBuilder sb = new StringBuilder();
		sb
				.append("Tipo do Teste, Num. Cliente, Raio Sensor, Retiradas, Acertos, Erros, Taxa de Acerto\n");
		for (int numClientes = 0; numClientes <= MAX_CLIENTE; numClientes++) {
			for (int raio = 0; raio <= MAX_RAIO; raio++) {
				// Cria um modelo de simulação
				Modelo modelo = new Modelo(null, "MercadoUbiquo", false, false,
						raio, numClientes);
				// Cria o "experimento" a ser realizado
				Experiment simulacao = new Experiment(
						"Mercado Ubíquo - Num Clientes:" + numClientes
								+ " Raio:" + raio, false);

				// Liga o modelo a simulacao
				modelo.connectToExperiment(simulacao);

				// Inicia a simulação
				simulacao.start();
				StatisticsData sd = modelo.getStatsCollector();
				Format fr = NumberFormat.getPercentInstance();
				
				
				sb.append("CLIENTE_COM_SENSOR").append(",").append(numClientes)
						.append(",").append(raio).append(",").append(
								sd.getProdRetirados()).append(",").append(
								sd.getStatsCliente().acertos).append(",")
						.append(sd.getStatsCliente().erros).append(",").append(
								fr.format(sd.getStatsCliente().taxaAcerto(
										sd.getProdRetirados()))
										+ "%").append("\n");

				sb.append("PORTA_PRODUTO_COM_SENSOR").append(",").append(
						numClientes).append(",").append(raio).append(",")
						.append(sd.getProdRetirados()).append(",").append(
								sd.getStatsPProduto().acertos).append(",")
						.append(sd.getStatsPProduto().erros).append(",")
						.append(
								sd.getStatsPProduto().taxaAcerto(
										sd.getProdRetirados())
										+ "%").append("\n");

				sb.append("AMBOS_COM_SENSOR").append(",").append(numClientes)
						.append(",").append(raio).append(",").append(
								sd.getProdRetirados()).append(",").append(
								sd.getStatsAmbos().acertos).append(",").append(
								sd.getStatsAmbos().erros).append(",").append(
								sd.getStatsAmbos().taxaAcerto(
										sd.getProdRetirados())
										+ "%").append("\n");
				System.gc();
			}
		}

		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(new File(
					"resultadoSimulacao2.csv")));
			bf.append(sb.toString());
			bf.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n\n\nSimulação concluída");

	}
}
