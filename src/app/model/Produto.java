package app.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(targetEntity = TipoProduto.class)
	private TipoProduto tipoProduto;
	
	
	public Produto() {
	}

	public Produto(TipoProduto tp) {
		this.tipoProduto = tp;
	}

	public int getId() {
		return id;
	}

	public void setId(int val) {
		this.id = val;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto val) {
		this.tipoProduto = val;
	}
	

}
