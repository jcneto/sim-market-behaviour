package sim.actuator;

import java.util.ArrayList;
import java.util.List;

import sim.enviroment.Ambiente;
import sim.enviroment.Modelo;
import app.localization.MovePlanner;
import app.localization.Point;
import app.model.Carrinho;
import app.model.Cestinha;
import app.model.Cliente;
import app.model.Prateleira;
import app.model.Produto;
import app.model.TipoProduto;
import app.statistics.StatisticsData;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.SimTime;

public class SimProcessCliente extends SimProcess {

	private Cliente cliente;
	private SimTime tempoPasso;
	private Modelo meuModelo;

	private Point ptAtual;

	private Point ptDestino;

	private List<Point> caminho = new ArrayList<Point>();

	ArrayList<Prateleira> listaPrateleirasAVisitar = new ArrayList<Prateleira>();

	public SimProcessCliente(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);

		meuModelo = (Modelo) owner;
		ptAtual = (Point) Ambiente.pontoInicialClientes.clone();

	}

	public SimProcessCliente(Model owner, String name, boolean showInTrace,
			Cliente cliente, SimTime simTime) {
		this(owner, name, showInTrace);
		this.setCliente(cliente);
		this.tempoPasso = simTime;

	}

	@Override
	public void lifeCycle() {

		setarPortaProduto();

		iniciarListaPrateleiras();

		realizarCompras();

		sairDoSistema();

		meuModelo.getStatsCollector().saiuCliente();

		if (meuModelo.getStatsCollector().getProcessados() == meuModelo
				.getStatsCollector().getTotalClientes()) {
			meuModelo.getExperiment().stop();
		}
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.cliente.getNome();
	}

	public Point getPosicao() {
		return ptAtual;
	}

	public void setPosicao(Point val) {
		this.ptAtual = val;
	}

	private void planejarCaminho(Point ptDestino) {
		caminho = MovePlanner.planejarCaminho(ptAtual, ptDestino);
		this.ptDestino = ptDestino;
	}

	private void seguirCaminho(boolean comCarrinho) {

		ptAtual = caminho.remove(0);

		if (comCarrinho && cliente.getPortaProduto() instanceof Carrinho) {
			((Carrinho) cliente.getPortaProduto()).setPosicao(ptAtual);
		}

		// Ambiente.pontosMoveis.add(ptAtual);

		hold(tempoPasso);
	}

	private boolean isOnDestino() {
		return (ptAtual.equals(ptDestino));
	}

	private void iniciarListaPrateleiras() {

		for (TipoProduto tp : cliente.getListaProdutos()) {
			for (Prateleira pt : meuModelo.prateleirasLista) {

				if (tp.equals(pt.getTipoProduto())) {
					listaPrateleirasAVisitar.add(pt);
					break;
				}
			}

		}

	}

	private void setarPortaProduto() {
		if (cliente.getListaProdutos().size() > 5) {
			// Enquanto o Cliente não possuir um carrinho
			planejarCaminho(Ambiente.pontoPegaCarrinhos);
			while (cliente.getPortaProduto() == null) {
				// Segue pelo caminho
				seguirCaminho(false);
				// Se estiver no ponto onde se pegam os carrinhos
				if (ptAtual.equals(Ambiente.pontoPegaCarrinhos)) {
					// Cliente pega o cliente
					cliente.setPortaProduto(new Carrinho(ptAtual, false));
				}
			}
		} else {
			cliente.setPortaProduto(new Cestinha());
		}

	}

	private void realizarCompras() {
		boolean comCarrinho = true;
		Point ptCarrinho = ptAtual;
		for (Prateleira pt : listaPrateleirasAVisitar) {
			planejarCaminho(pt.getPontoFrente());
			while (!isOnDestino()) {
				seguirCaminho(comCarrinho);
			}
			Produto prPego = pt.getProdutos().remove(0);

			verificarRetirada(pt);

			if (comCarrinho) {
				cliente.getPortaProduto().insereProduto(prPego);

			} else {
				planejarCaminho(ptCarrinho);
				while (!isOnDestino()) {
					seguirCaminho(false);
				}
				cliente.getPortaProduto().insereProduto(prPego);

			}

			if (cliente.getPortaProduto().getProdutos().size() > cliente
					.getPortaProduto().getLimite()) {
				comCarrinho = false;
				ptCarrinho = ptAtual;
			}
		}

	}

	private void verificarRetirada(Prateleira pt) {
		StatisticsData stats = meuModelo.getStatsCollector();

		stats.retirouProduto();

		boolean scanCliente = meuModelo.getSensor().verificarRetirada(pt, this,
				StatisticsData.CLIENTE_COM_SENSOR);
		boolean scanPProduto = meuModelo.getSensor().verificarRetirada(pt,
				this, StatisticsData.PORTA_PRODUTO_COM_SENSOR);

		if (scanCliente) {
			stats.getStatsCliente().maisAcerto();
		} else {
			stats.getStatsCliente().maisErro();
		}

		if (scanPProduto) {
			stats.getStatsPProduto().maisAcerto();
		} else {
			stats.getStatsPProduto().maisErro();
		}

		if (scanCliente) {
			stats.getStatsAmbos().maisAcerto();
		} else {
			if (scanPProduto) {
				stats.getStatsAmbos().maisAcerto();	
			}
			else{
				stats.getStatsAmbos().maisErro();
			}
		}

	}

	private void sairDoSistema() {
		ptDestino = Ambiente.pontoSairSistema;
		planejarCaminho(ptDestino);
		while (!isOnDestino()) {
			seguirCaminho(true);
		}

	}

}
