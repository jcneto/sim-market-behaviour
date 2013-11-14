package app.model;
 
import java.util.ArrayList;

public interface PortaProduto {

	public void setProdutos(ArrayList<Produto> produtos);

	public ArrayList<Produto> getProdutos();

	public void insereProduto(Produto pr);

	public int getLimite();
	
}