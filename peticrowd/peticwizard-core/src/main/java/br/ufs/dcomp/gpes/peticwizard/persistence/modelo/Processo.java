package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa a entidade Processo definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_processo</code> no banco de dados. Implementa
 * métodos <i>getters</i> e <i>setters</i> para seus atributos privados, não
 * sendo capaz de realizar nenhuma operação referente a banco de dados, como
 * inserção, consulta, atualização, remoção e listagem de registros. Para
 * realizar tais operações deve-se utilizar um objeto da classe {@link ProcessoDAO}.
 * 
 * @see ProcessoDAO
 * @see Entity
 */

@Entity
@Table(name = "petic_processo")
@NamedQueries({
	@NamedQuery(name = Processo.LISTAR_TODOS, query = "SELECT p FROM Processo p"),
	@NamedQuery(name = Processo.BUSCAR_POR_ID, query = "SELECT p FROM Processo p WHERE p.id = :id"),
	@NamedQuery(name = Processo.LISTAR_CORRENTES_EMPRESA, query = "SELECT p FROM Processo p ")})
public class Processo implements Serializable, Comparable<Processo> {

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODOS = "Processo.listarTodos";
	public static final String BUSCAR_POR_ID = "Processo.buscarPorId";
	public static final String LISTAR_CORRENTES_EMPRESA = "Processo.listarCorrentesEmpresa";
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String responsavel;
	
	private String stakeholders;
	
	private String entrada;
	private String saida;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataModificacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ProcessoGenerico processoGenerico;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Artefato artefato;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Questionario questionario;
	
	@OneToMany(mappedBy = "processo")
	private List<Acao> acoes = new ArrayList<Acao>();	
	
	public Integer getId() {
		return id;
	}
	
	public String getResponsavel() {
		return responsavel;
	}
	
	public String getStakeholders() {
		return stakeholders;
	}
	
	public String getEntrada() {
		return entrada;
	}
	
	public String getSaida() {
		return saida;
	}
	
	public Calendar getDataModificacao() {
		return dataModificacao;
	}
	
	public ProcessoGenerico getProcessoGenerico() {
		return processoGenerico;
	}
	
	public Artefato getArtefato() {
		return artefato;
	}
	
	public Questionario getQuestionario() {
		return questionario;
	}
	
	public List<Acao> getAcoes() {
		return acoes;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	public void setStakeholders(String stakeholders) {
		this.stakeholders = stakeholders;
	}
	
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	
	public void setSaida(String saida) {
		this.saida = saida;
	}
	
	public void setDataModificacao(Calendar dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	public void setProcessoGenerico(ProcessoGenerico processoGenerico) {
		this.processoGenerico = processoGenerico;
	}
	
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	
	public void setArtefato(Artefato artefato) {
		this.artefato = artefato;
	}
	
	@Override
	public int compareTo(Processo outroProcesso) {
		return this.id.compareTo(outroProcesso.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Processo outroProcesso = (Processo) obj;
		if (outroProcesso != null) {
			return (outroProcesso.getId().equals(this.id));
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
		return processoGenerico.toString() + ", responsavel: " + responsavel;
	}

}
