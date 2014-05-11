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

import org.hibernate.annotations.Index;



/**
 * Representa a entidade Empresa definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_empresa</code> no banco de dados. Implementa
 * métodos <i>getters</i> e <i>setters</i> para seus atributos privados, não
 * sendo capaz de realizar nenhuma operação referente a banco de dados, como
 * inserção, consulta, atualização, remoção e listagem de registros. Para
 * realizar tais operações deve-se utilizar um objeto da classe {@link EmpresaDAO}.
 * 
 * @see EmpresaDAO
 * @see Entity
 */

@Entity
@Table(name = "petic_empresa")
@NamedQueries({
	@NamedQuery(name = Empresa.LISTAR_TODOS, query = "SELECT e FROM Empresa e"),
	@NamedQuery(name = Empresa.BUSCAR, query = "SELECT e FROM Empresa e WHERE e.descricao = :descricao")})
public class Empresa implements Serializable, Comparable<Empresa> {

	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODOS = "Empresa.listarTodos";
	public static final String BUSCAR = "Empresa.buscar";
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private String descricao;
	private Byte logomarca;
	
	@NotNull
	@Index(name = "loginIndex")
	private String login;
	
	@NotNull
	private String senha;
	
	@OneToMany(mappedBy = "empresa")
	private List<Artefato> artefatos = new ArrayList<Artefato>();
	
	public Integer getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Byte getLogomarca() {
		return logomarca;
	}
	
	public String getLogin() {
		return login;
	}
	
	/*public String getSenha() {
		return senha;
	}*/
	
	public List<Artefato> getArtefatos() {
		return artefatos;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setLogomarca(Byte logomarca) {
		this.logomarca = logomarca;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public int compareTo(Empresa outraEmpresa) {
		return this.id.compareTo(outraEmpresa.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Empresa outraEmpresa = (Empresa) obj;
		if (outraEmpresa != null) {
			return (outraEmpresa.getId().equals(this.id));
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
		return id + " - " + descricao;
	}

}
