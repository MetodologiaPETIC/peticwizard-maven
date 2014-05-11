package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Artefato;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade
 * {@link Artefato}. Um objeto dessa classe deve ser utilizado sempre
 * que for necessário realizar operações referentes a banco envolvendo um objeto
 * da classe <code>Artefato</code>.
 * </p>
 * 
 * @see Artefato
 */
@Stateless
public class ArtefatoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Construtor padrão da classe {@link ArtefatoDAO}. Retorna um objeto da
	 * classe <code>ArtefatoDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes à entidade {@link Artefato}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private ArtefatoDAO daoArtefato;</code>
	 * 
	 * @see Artefato
	 * @see EJB
	 */
	@Deprecated
	public ArtefatoDAO() {}
	
	/**
	 * Insere no banco de dados o <code>artefato</code> fornecida como
	 * argumento.
	 * 
	 * @param artefato
	 *            um objeto da classe {@link Artefato} representando o
	 *            artefato que deve ser inserido no banco de dados.
	 */
	public Artefato inserir(Artefato artefato) {
		entityManager.persist(artefato);
		return artefato;
	}
	
	/**
	 * Busca no banco de dados um artefato com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link Artefato} que
	 * representa esse artefato. Pode retornar <code>null</code>, caso não exista
	 * um artefato com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            representando o <code>id</code> do
	 *            artefato que deve ser buscado no banco de dados.
	 * 
	 * @return um objeto da classe <code>Artefato</code> representando o
	 *         artefato desejado, caso ele exista, ou <code>null</code>,
	 *         caso não.
	 */
	public Artefato buscar(Integer id) {
		TypedQuery<Artefato> query = entityManager.createNamedQuery(
				Artefato.BUSCAR_POR_ID, Artefato.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	/**
	 * Atualiza no banco de dados o <code>artefato</code> fornecido como
	 * argumento.
	 * 
	 * @param artefato
	 *            um objeto da classe {@link Artefato} representando o
	 *            artefato que deve ser atualizado no banco de dados.
	 */
	public void atualizar(Artefato artefato) {
		Artefato artefatoAntigo = buscar(artefato.getId());
		if (artefatoAntigo != null)
			entityManager.merge(artefato);
		else
			inserir(artefato);
	}
	
	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link Artefato}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>Artefato</code> encontrados.
	 *         Essa lista pode ser vazia (sem objetos), caso nenhum registro
	 *         seja encontrado.
	 * 
	 * @see Artefato
	 * @see List
	 */
	public List<Artefato> listarTodos() {
		return entityManager.createNamedQuery(Artefato.LISTAR_TODOS,
				Artefato.class).getResultList();
	}
	
	/**
	 * Remove do banco de dados a <code>artefato</code> fornecida como
	 * argumento.
	 * 
	 * @param artefato
	 *            um objeto da classe {@link Artefato} representando o
	 *            artefato que deve ser removido do banco de dados.
	 */

	public void remover(Artefato artefato) {
		entityManager.remove(buscar(artefato.getId()));
	}

}
