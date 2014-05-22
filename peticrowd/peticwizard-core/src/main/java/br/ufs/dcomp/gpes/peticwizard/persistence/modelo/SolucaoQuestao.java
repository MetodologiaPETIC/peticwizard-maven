package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Representa a entidade Solução de Questão definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_solucao_questao</code> no banco de dados. Implementa
 * métodos <i>getters</i> e <i>setters</i> para seus atributos privados, não
 * sendo capaz de realizar nenhuma operação referente a banco de dados, como
 * inserção, consulta, atualização, remoção e listagem de registros. Para
 * realizar tais operações deve-se utilizar um objeto da classe {@link SolucaoQuestaoDAO}.
 * 
 * @see SolucaoQuestaoDAO
 * @see Entity
 */

@Entity
@Table(name = "petic_solucao_questao")
@NamedQueries({
	@NamedQuery(name = SolucaoQuestao.LISTAR_TODOS, query = "SELECT s FROM SolucaoQuestao s"),
	@NamedQuery(name = SolucaoQuestao.BUSCAR_POR_ID, query = "SELECT s FROM SolucaoQuestao s WHERE s.id = :id")})
public class SolucaoQuestao implements Serializable, Comparable<SolucaoQuestao> {

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODOS = "SolucaoQuestao.listarTodos";
	public static final String BUSCAR_POR_ID = "SolucaoQuestao.buscarPorId";
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Questao questao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Questionario questionario;
	
	@NotNull
	private boolean marcado;
	
	public Integer getId() {
		return id;
	}
	
	public Questao getQuestao() {
		return questao;
	}
	
	public Questionario getQuestionario() {
		return questionario;
	}
	
	public boolean getMarcado() {
		return marcado;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	
	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	
	@Override
	public int compareTo(SolucaoQuestao outraSolucaoQuestao) {
		return this.id.compareTo(outraSolucaoQuestao.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		SolucaoQuestao outraSolucaoQuestao = (SolucaoQuestao) obj;
		if (outraSolucaoQuestao != null) {
			return (outraSolucaoQuestao.getId().equals(this.id));
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
		return questionario.toString() + ", " + questao.toString() + ", marcado: " + marcado;
	}

}
