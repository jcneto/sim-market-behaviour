package app.localization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sim.enviroment.Ambiente;


public abstract class MovePlanner {

	// Planeja o caminho
	public static List<Point> planejarCaminho(Point ptInicial, Point ptDestino) {

		List<Point> caminho = new ArrayList<Point>();

		Point ptAtual = (Point) ptInicial.clone();

		int voltador = 0;
		while (!ptAtual.equals(ptDestino)) {

			Point ptTest = calculaPontoMaisProximo(ptAtual, ptDestino, caminho);
			if (ptTest != null || ptTest == ptAtual) {
				ptAtual = ptTest;
				caminho.add(ptAtual);
				voltador = 0;
			} else {
				voltador++;
				if (voltador < caminho.size()) {
					ptAtual = caminho.get(caminho.size() - voltador);
				} else {
					ptAtual = caminho.get(0);
				}
			}

		}

		return caminho;
	}

	// Retorna o ponto livre mais próximo do destino
	static public Point calculaPontoMaisProximo(Point ptInicial,
			Point ptDestino, List<Point> caminho) {

		List<Point> adjacentes = ptInicial.calcularAdjacentes(false);

		Map<Integer, Point> distanciaAdjacentesPossiveis = new HashMap<Integer, Point>();

		int menor = Integer.MAX_VALUE;
		int dif = 0;

		// Itera nos pontos possiveis
		for (Point ptTeste : adjacentes) {

			// Se o ponto for o ponto destino
			if (ptTeste.equals(ptDestino)) {
				menor = 0;
				// Adiciona ptTeste ao map das distancias com key=menor
				distanciaAdjacentesPossiveis.put(menor, ptTeste);
				// Se o ponto estiver fora dos limites volta pro começo
				break;
			} else if (ptTeste.getX() < 0 || ptTeste.getY() < 0
					|| ptTeste.getX() > Ambiente.limiteX
					|| ptTeste.getY() > Ambiente.limiteY) {
				continue;
				// Se o ponto estiver livre e não estiver no caminho
			} else if (Ambiente.isLivre(ptTeste)
					&& !caminho.contains(ptTeste)) {

				// Calcula a diferença entre o ponto de teste e ponto destino
				dif = ptDestino.calcularDiferenca(ptTeste);

				// Insere o ponto no map com a dif como chave
				distanciaAdjacentesPossiveis.put(dif, ptTeste);

				if (menor >= dif) {
					menor = dif;
				}

			}

		}
		// Pega a distância que tiver o menor indice
		return (distanciaAdjacentesPossiveis.get(menor));
	}

}
