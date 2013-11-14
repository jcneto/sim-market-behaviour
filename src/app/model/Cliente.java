package app.model;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nome;

	@Transient
	private PortaProduto portaProduto;

	@ManyToMany
	@JoinTable(name = "ListaCompras", joinColumns = { @JoinColumn(name="cliente_id") }, inverseJoinColumns = {@JoinColumn(name="produto_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<TipoProduto> listaCompras;

	{
		this.listaCompras = new ArrayList<TipoProduto>();
	}

	public Cliente() {

	}

	public Cliente(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int val) {
		this.id = val;
	}

	public List<TipoProduto> getListaProdutos() {
		return listaCompras;
	}

	public void setListaProdutos(ArrayList<TipoProduto> val) {
		this.listaCompras = val;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String val) {
		this.nome = val;
	}

	public void acresProduto(TipoProduto pr) {
		listaCompras.add(pr);
	}

	public void setPortaProduto(PortaProduto portaProduto) {
		this.portaProduto = portaProduto;
	}

	public PortaProduto getPortaProduto() {
		return portaProduto;
	}

}
