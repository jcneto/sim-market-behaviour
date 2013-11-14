package app.model;
 
import java.util.ArrayList;

public abstract class PortaProdutoImpl implements PortaProduto {

	public ArrayList<Produto> produtos = new ArrayList<Produto>();

	@Override
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	@Override
	public void insereProduto(Produto pr) {
		produtos.add(pr);
	}

	@Override
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

}
