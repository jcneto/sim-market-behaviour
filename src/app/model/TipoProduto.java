package app.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TipoProduto")
public class TipoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nome;

	public TipoProduto() {
		// TODO Auto-generated constructor stub
	}

	public TipoProduto(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int val) {
		this.id = val;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String val) {
		this.nome = val;
	}


	@Override
	public boolean equals(Object obj) {
		TipoProduto aux_obj = (TipoProduto) obj;
		if (aux_obj.getId() == this.id) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
