package app.statistics;

public class DataContainer {
	public int erros;
	public int acertos;

	public double taxaAcerto(double prodRetirados) {

		return (Double.valueOf(acertos)) / (prodRetirados) * 100;
	}

	public void maisAcerto() {
		acertos++;
	}

	public void maisErro() {
		erros++;
	}

}