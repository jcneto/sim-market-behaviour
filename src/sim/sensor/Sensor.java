package sim.sensor;

import java.util.List;

import sim.actuator.SimProcessCliente;
import sim.enviroment.Modelo;
import app.localization.Point;
import app.model.Carrinho;
import app.model.Prateleira;
import app.statistics.StatisticsData;

public class Sensor {

	private int raio;

	public Sensor(int raioSensor) {

		this.raio = raioSensor;

	}

	public boolean verificarRetirada(Prateleira pr,
			SimProcessCliente simProcessCliente, int tipoSensor) {

		Point ptBase = pr.getPontoFrente();

		List<Point> ptList = ptBase.calcularAdjacentes(raio);

		switch (tipoSensor) {

		case StatisticsData.CLIENTE_COM_SENSOR: {
			return verificarRetiradaCliente(pr, simProcessCliente, ptList);
		}
		case StatisticsData.PORTA_PRODUTO_COM_SENSOR: {
			return verificarRetiradaPortaProduto(pr, simProcessCliente, ptList);
		}
		case StatisticsData.AMBOS_COM_SENSOR: {
			if (!verificarRetiradaCliente(pr, simProcessCliente, ptList)) {
				return verificarRetiradaPortaProduto(pr, simProcessCliente,
						ptList);
			}
			return true;
		}
		}
		// Se chegou até aqui é porque houve algo errado e não conseguiu
		// detectar nada
		return false;
	}

	private boolean verificarRetiradaPortaProduto(Prateleira pr,
			SimProcessCliente simProcessCliente, List<Point> ptList) {
		for (Point pt : ptList) {
			for (SimProcessCliente cl : Modelo.listaClientes) {
				Point pos = new Point();
				if (cl.getCliente().getPortaProduto() instanceof Carrinho) {
					pos = ((Carrinho) cl.getCliente().getPortaProduto())
							.getPosicao();
				} else {
					pos = cl.getPosicao();
				}

				if (pos.equals(pt)) {
					if (!cl.getCliente().getPortaProduto().equals(
							simProcessCliente.getCliente().getPortaProduto())) {
						return false;
					} else {
						return true;
					}
				}
			}
		}
		// Se chegou até aqui não conseguiu detectar nada
		return false;
	}

	private boolean verificarRetiradaCliente(Prateleira pr,
			SimProcessCliente simProcessCliente, List<Point> ptList) {
		for (Point pt : ptList) {
			for (SimProcessCliente cl : Modelo.listaClientes) {
				if (cl.getPosicao().equals(pt)) {
					if (!cl.getCliente().equals(simProcessCliente.getCliente())) {
						return false;
					} else {
						return true;
					}

				}
			}
		}
		// Se chegou até aqui não conseguiu detectar nada
		return false;
	}

}
