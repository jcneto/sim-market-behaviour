package app.model; 

import java.util.ArrayList;

import app.localization.Point;

public class Carrinho extends PortaProdutoImpl {

	private final int limite = 15;
	private int id;
	private Point posicao;

	private boolean solto;

	public ArrayList<Produto> produtos = new ArrayList<Produto>();

	public Carrinho() {
	}

	public Carrinho(Point ptAtual, boolean b) {
		this.posicao = ptAtual;
		this.solto = b;
	}

	public int getId() {
		return id;
	}

	public void setId(int val) {
		this.id = val;
	}

	public Point getPosicao() {
		return posicao;
	}

	public void setPosicao(Point val) {
		this.posicao = val;
	}

	public void setSolto(boolean solto) {
		this.solto = solto;
	}

	public boolean isSolto() {
		return solto;
	}

	@Override
	public int getLimite() {
		// TODO Auto-generated method stub
		return limite;
	}

}
