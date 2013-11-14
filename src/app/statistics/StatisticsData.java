package app.statistics;

public class StatisticsData {

	public static final int CLIENTE_COM_SENSOR = 0;
	public static final int PORTA_PRODUTO_COM_SENSOR = 1;
	public static final int AMBOS_COM_SENSOR = 2;

	private DataContainer statsCliente = new DataContainer();
	private DataContainer statsPProduto = new DataContainer();
	private DataContainer statsAmbos = new DataContainer();

	int prodRetirados;
	int processados;
	int totalClientes;

	public int getProdRetirados() {
		return prodRetirados;
	}

	public void setProdRetirados(int prodRetirados) {
		this.prodRetirados = prodRetirados;
	}

	public int getProcessados() {
		return processados;
	}

	public int getTotalClientes() {
		return totalClientes;
	}

	public DataContainer getStatsCliente() {
		return statsCliente;
	}

	public void setStatsCliente(DataContainer statsCliente) {
		this.statsCliente = statsCliente;
	}

	public DataContainer getStatsPProduto() {
		return statsPProduto;
	}

	public void setStatsPProduto(DataContainer statsPProduto) {
		this.statsPProduto = statsPProduto;
	}

	public DataContainer getStatsAmbos() {
		return statsAmbos;
	}

	public void setStatsAmbos(DataContainer statsAmbos) {
		this.statsAmbos = statsAmbos;
	}

	public void retirouProduto() {
		prodRetirados++;
	}

	public void saiuCliente() {
		processados++;
	}

	public void entrouCliente() {
		totalClientes++;
	}

}
