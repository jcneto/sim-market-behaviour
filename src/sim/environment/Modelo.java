package sim.environment;

import java.util.ArrayList;
import java.util.List;

import sim.actuator.SimProcessCliente;
import sim.sensor.Sensor;
import app.model.Cliente;
import app.model.Prateleira;
import app.persistence.dao.ClienteDAOImpl;
import app.persistence.dao.PrateleiraDAOImpl;
import app.statistics.StatisticsData;
import desmoj.core.dist.RealDistUniform;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public class Modelo extends Model {

	// Gera uma distrib. uniforme pseudo-aleatoria para os tempos de chegada
	private desmoj.core.dist.RealDistUniform clienteTempoChegada;
	// Gera uma distrib. uniforme pseudo-aleatoria para os tempos de passo
	private desmoj.core.dist.RealDistUniform clienteTempoPasso;

	public static List<SimProcessCliente> listaClientes = new ArrayList<SimProcessCliente>();

	// protected List<Produto> produtosDisponiveis;
	public List<Prateleira> prateleirasLista;

	private Sensor sensor;
	private int numClientes;
	private StatisticsData statsCollector;

	// Construtor
	public Modelo(Model owner, String name, boolean showInReport,
			boolean showIntrace) {
		super(owner, name, showInReport, showIntrace);
		statsCollector = new StatisticsData();
	}

	public Modelo(Model owner, String name, boolean showInReport,
			boolean showIntrace, int raioSensor, int numCliente) {
		this(owner, name, showInReport, showIntrace);
		this.sensor = new Sensor(raioSensor);
		this.numClientes = numCliente;

	}

	@Override
	public String description() {
		return "Fazendo o teste de detecção";
	}

	@Override
	public void doInitialSchedules() {
		double aux_tempo = 0;

		ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
		PrateleiraDAOImpl prateleiraDAO = new PrateleiraDAOImpl();

		prateleirasLista = new ArrayList<Prateleira>();

		List<Prateleira> auxPrateleiras = prateleiraDAO.getList();

		for (Prateleira pt : auxPrateleiras) {
			Ambiente.pontosFixos.add(pt.getPosicao());
			if (pt.getTipoProduto().getId() != 99) {
				prateleirasLista.add(pt);
			}
		}

		List<Cliente> lsCliente = clienteDAO.getList();

		for (Cliente clIt : lsCliente) {
			SimProcessCliente cliente = new SimProcessCliente(this, clIt
					.getNome(), false, clIt,
					new SimTime(getClienteTempoPasso()));
			cliente.activate(new SimTime(aux_tempo));
			aux_tempo = aux_tempo + getClienteTempoChegada();
			listaClientes.add(cliente);
			statsCollector.entrouCliente();
			if (statsCollector.getTotalClientes() >= numClientes) {
				break;
			}
		}

	}

	@Override
	public void init() {
		// Inicializa o tempo de Passo
		// 0.5 -> Minimo
		// 2 -> Máximo
		clienteTempoPasso = new RealDistUniform(this, "ClientePasso", 0.5, 2,
				false, false);

		// Inicializa o tempo de chegada
		// 0 -> Minimo
		// 10 -> Máximo
		clienteTempoChegada = new RealDistUniform(this, "ClienteChegada", 0.0,
				10, false, false);
	}

	// Métodos utilizados para acessar as vars aleatórias
	public double getClienteTempoChegada() {
		return clienteTempoChegada.sample();
	}

	public double getClienteTempoPasso() {
		return clienteTempoPasso.sample();
	}

	public List<Prateleira> getPrateleirasLista() {
		return prateleirasLista;
	}

	public void setPrateleirasLista(List<Prateleira> prateleirasLista) {
		this.prateleirasLista = prateleirasLista;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public StatisticsData getStatsCollector() {
		return statsCollector;
	}

}
