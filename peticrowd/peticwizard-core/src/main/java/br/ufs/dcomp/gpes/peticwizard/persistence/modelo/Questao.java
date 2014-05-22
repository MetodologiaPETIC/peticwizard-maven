package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Representa a entidade Questão definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_questao</code> no banco de dados. Implementa
 * métodos <i>getters</i> e <i>setters</i> para seus atributos privados, não
 * sendo capaz de realizar nenhuma operação referente a banco de dados, como
 * inserção, consulta, atualização, remoção e listagem de registros. Para
 * realizar tais operações deve-se utilizar um objeto da classe {@link QuestaoDAO}.
 * 
 * @see QuestaoDAO
 * @see Entity
 */

@Entity
@Table(name = "petic_questao")
@NamedQueries({
	@NamedQuery(name = Questao.LISTAR_TODOS, query = "SELECT q FROM Questao q"),
	@NamedQuery(name = Questao.BUSCAR_POR_ID, query = "SELECT q FROM Questao q WHERE q.id = :id")})
public class Questao implements Serializable, Comparable<Questao> {
	
	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODOS = "Questao.listarTodos";
	public static final String BUSCAR_POR_ID = "Questao.buscarPorId";
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private Integer nivel;
	
	@NotNull
	private String descricao;
	
	@OneToMany(mappedBy = "questao")
	private List<SolucaoQuestao> solucoesQuestao = new ArrayList<SolucaoQuestao>();

	public Integer getId() {
		return id;
	}
	
	public Integer getNivel() {
		return nivel;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public List<SolucaoQuestao> getSolucoesQuestao() {
		return solucoesQuestao;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	public void setDescicao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public int compareTo(Questao outraQuestao) {
		return this.id.compareTo(outraQuestao.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Questao outraQuestao = (Questao) obj;
		if (outraQuestao != null) {
			return (outraQuestao.getId().equals(this.id));
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
		return nivel + " - " + descricao;
	}

}
