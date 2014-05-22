package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa a entidade Ação definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_acao</code> no banco de dados. Implementa
 * métodos <i>getters</i> e <i>setters</i> para seus atributos privados, não
 * sendo capaz de realizar nenhuma operação referente a banco de dados, como
 * inserção, consulta, atualização, remoção e listagem de registros. Para
 * realizar tais operações deve-se utilizar um objeto da classe {@link AcaoDAO}.
 * 
 * @see AcaoDAO
 * @see Entity
 */

@Entity
@Table(name = "petic_acao")
@NamedQueries({
	@NamedQuery(name = Acao.LISTAR_TODOS, query = "SELECT a FROM Acao a"),
	@NamedQuery(name = Acao.BUSCAR_POR_ID, query = "SELECT a FROM Acao a WHERE a.id = :id")})
public class Acao implements Serializable, Comparable<Acao> {

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODOS = "Acao.listarTodos";
	public static final String BUSCAR_POR_ID = "Acao.buscarPorID";
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String responsavel;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataFim;
	
	private double custo;
	
	private double esforco;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataModificacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private AcaoGenerica acaoGenerica;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Processo processo;
	
	public Integer getId() {
		return id;
	}
	
	public String getResponsavel() {
		return responsavel;
	}
	
	public Calendar getDataInicio() {
		return dataInicio;
	}
	
	public Calendar getDataFim() {
		return dataFim;
	}
	
	public double getCusto() {
		return custo;
	}
	
	public double getEsforco() {
		return esforco;
	}
	
	public Calendar getDataModificacao() {
		return dataModificacao;
	}
	
	public AcaoGenerica getAcaoGenerica() {
		return acaoGenerica;
	}
	
	public Processo getProcesso() {
		return processo;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setResposavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	
	public void setCusto(double custo) {
		this.custo = custo;
	}
	
	public void setEsforco(double esforco) {
		this.esforco = esforco;
	}
	
	public void setDataModificacao(Calendar dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	public void setAcaoGenerica(AcaoGenerica acaoGenerica) {
		this.acaoGenerica = acaoGenerica;
	}
	
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	@Override
	public int compareTo(Acao outraAcao) {
		return this.id.compareTo(outraAcao.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Acao outraAcao = (Acao) obj;
		if (outraAcao != null) {
			return (outraAcao.getId().equals(this.id));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public String toString() {
		return acaoGenerica.toString() + ", responsável: " + responsavel;
	}

}
