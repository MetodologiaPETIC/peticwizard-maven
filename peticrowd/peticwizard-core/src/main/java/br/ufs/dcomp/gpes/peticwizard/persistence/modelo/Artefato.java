package br.ufs.dcomp.gpes.peticwizard.persistence.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Representa a entidade Artefato definida no modelo de domínio da aplicação.
 * Configura uma entidade JPA (uma classe anotada com {@link Entity}) mapeada
 * para uma tabela de nome <code>petic_artefato</code> no banco de dados. Implementa
 * métodos <i>getters</i> e <i>setters</i> para seus atributos privados, não
 * sendo capaz de realizar nenhuma operação referente a banco de dados, como
 * inserção, consulta, atualização, remoção e listagem de registros. Para
 * realizar tais operações deve-se utilizar um objeto da classe {@link ArtefatoDAO}.
 * 
 * @see ArtefatoDAO
 * @see Entity
 */

@Entity
@Table(name = "petic_artefato")
@NamedQueries({
	@NamedQuery(name = Artefato.LISTAR_TODOS, query = "SELECT a FROM Artefato a"),
	@NamedQuery(name = Artefato.BUSCAR_POR_ID, query = "SELECT a FROM Artefato a WHERE a.id = :id")})
public class Artefato implements Serializable, Comparable<Artefato> {
	
	private static final long serialVersionUID = 1L;
	public static final String LISTAR_TODOS = "Artefato.listarTodos";
	public static final String BUSCAR_POR_ID = "Artefato.buscarPorId";
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataTermino;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Empresa empresa;
	
	@OneToMany(mappedBy = "artefato")
	private List<Processo> processos = new ArrayList<Processo>();
	
	public Integer getId() {
		return id;
	}
	
	public Calendar getDataInicio() {
		return dataInicio;
	}
	
	public Calendar getDataTermino() {
		return dataTermino;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public List<Processo> getProcessos() {
		return processos;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@Override
	public int compareTo(Artefato outroArtefato) {
		return this.id.compareTo(outroArtefato.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Artefato outroArtefato = (Artefato) obj;
		if (outroArtefato != null) {
			return (outroArtefato.getId().equals(this.id));
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
		return id + " - " + dataInicio.toString() + " a " + dataTermino.toString();
	}

}
