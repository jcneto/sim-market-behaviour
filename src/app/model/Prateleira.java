package app.model;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import app.localization.Point;

@Entity
@Table(name = "Prateleira")
public class Prateleira {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "prateleira_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Produto> produtos;

	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name = "x", column = @Column(name = "ptPosicaoX")),
			@AttributeOverride(name = "y", column = @Column(name = "ptPosicaoY")) })
	private Point posicao;

	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name = "x", column = @Column(name = "ptFrenteX")),
			@AttributeOverride(name = "y", column = @Column(name = "ptFrenteY")) })
	private Point pontoFrente;

	@OneToOne(targetEntity = TipoProduto.class)
	private TipoProduto tipoProduto;

	{
		this.produtos = new ArrayList<Produto>();

	}

	public Prateleira() {
	}

	public Prateleira(Point ponto) {
		this.posicao = ponto;
	}

	public Prateleira(Point ponto, Point ptFrente) {
		this(ponto);
		this.pontoFrente = ptFrente;
	}

	public Prateleira(Point ponto, TipoProduto tp1) {
		this(ponto);
		this.tipoProduto = tp1;
	}

	public Prateleira(Point ponto, TipoProduto tp1, Point ptFrente) {
		this(ponto, tp1);
		this.pontoFrente = ptFrente;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> val) {
		this.produtos = val;
	}

	public void insereProduto(Produto pr) {
		produtos.add(pr);
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public Point getPontoFrente() {
		return pontoFrente;
	}

	public void setPontoFrente(Point pontoFrente) {
		this.pontoFrente = pontoFrente;
	}

	@Override
	public boolean equals(Object obj) {
		Prateleira pt = (Prateleira) obj;
		if(pt.getId() == this.getId()){
			return true;
		}else{
			return false;
		}
		
	}
	
}
