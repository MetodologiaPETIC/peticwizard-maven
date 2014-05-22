package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa a entidade Questionário definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_questionario</code> no banco de dados. Implementa
 * métodos <i>getters</i> e <i>setters</i> para seus atributos privados, não
 * sendo capaz de realizar nenhuma operação referente a banco de dados, como
 * inserção, consulta, atualização, remoção e listagem de registros. Para
 * realizar tais operações deve-se utilizar um objeto da classe {@link QuestionarioDAO}.
 * 
 * @see QuestionarioDAO
 * @see Entity
 */

@Entity
@Table(name = "petic_questionario")
@NamedQueries({
	@NamedQuery(name = Questionario.LISTAR_TODOS, query = "SELECT q FROM Questionario q"),
	@NamedQuery(name = Questionario.BUSCAR_POR_ID, query = "SELECT q FROM Questionario q WHERE q.id = :id")})
public class Questionario implements Serializable, Comparable<Questionario> {

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODOS = "Questionario.listarTodos";
	public static final String BUSCAR_POR_ID = "Questionario.buscarPorID";
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataModificacao;
	
	private String editor;
	
	@OneToMany(mappedBy = "questionario")
	private List<SolucaoQuestao> solucoesQuestoes = new ArrayList<SolucaoQuestao>();
	
	@OneToOne(fetch = FetchType.EAGER)
	private Processo processo;
	
	public Integer getId() {
		return id;
	}
	
	public String getEditor() {
		return editor;
	}
	
	public Calendar getDataModificacao() {
		return dataModificacao;
	}
	
	public List<SolucaoQuestao> getSolucoesQuestoes() {
		return solucoesQuestoes;
	}
	
	public Processo getProcesso() {
		return processo;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	public void setDataModificacao(Calendar dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	@Override
	public int compareTo(Questionario outroQuestionario) {
		return this.id.compareTo(outroQuestionario.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Questionario outroQuestionario = (Questionario) obj;
		if (outroQuestionario != null) {
			return (outroQuestionario.getId().equals(this.id));
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
		return processo.getProcessoGenerico().toString() + ", editor: " + editor;
	}

}
