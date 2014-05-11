package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Acao;
import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Questao;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade
 * {@link Acao}. Um objeto dessa classe deve ser utilizado sempre
 * que for necessário realizar operações referentes a banco envolvendo um objeto
 * da classe <code>Acao</code>.
 * </p>
 * 
 * @see Acao
 */
@Stateless
public class AcaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Construtor padrão da classe {@link AcaoDAO}. Retorna um objeto da
	 * classe <code>AcaoDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes à entidade {@link Acao}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private AcaoDAO daoAcao;</code>
	 * 
	 * @see Acao
	 * @see EJB
	 */
	@Deprecated
	public AcaoDAO() {}
	
	/**
	 * Insere no banco de dados a <code>ação</code> fornecida como
	 * argumento.
	 * 
	 * @param acao
	 *            um objeto da classe {@link Acao} representando a
	 *            ação que deve ser inserida no banco de dados.
	 */
	public Acao inserir(Acao acao) {
		entityManager.persist(acao);
		return acao;
	}
	
	/**
	 * Busca no banco de dados uma ação com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link Acao} que
	 * representa essa ação. Pode retornar <code>null</code>, caso não exista
	 * uma ação com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            representando o <code>id</code> da
	 *            ação que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>Acao</code> representando a
	 *         ação desejada, caso ele exista, ou <code>null</code>,
	 *         caso não.
	 */
	public Acao buscar(Integer id) {
		TypedQuery<Acao> query = entityManager.createNamedQuery(
				Acao.BUSCAR_POR_ID, Acao.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	/**
	 * Atualiza no banco de dados a <code>ação</code> fornecida como
	 * argumento.
	 * 
	 * @param acao
	 *            um objeto da classe {@link Acao} representando a
	 *            ação que deve ser atualizada no banco de dados.
	 */
	public void atualizar(Acao acao) {
		Acao acaoAntiga = buscar(acao.getId());
		if (acaoAntiga != null)
			entityManager.merge(acao);
		else
			inserir(acao);
	}
	
	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link Acao}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>Acao</code> encontrados.
	 *         Essa lista pode ser vazia (sem objetos), caso nenhum registro
	 *         seja encontrado.
	 * 
	 * @see Acao
	 * @see List
	 */
	public List<Acao> listarTodos() {
		return entityManager.createNamedQuery(Acao.LISTAR_TODOS,
				Acao.class).getResultList();
	}
	
	/**
	 * Remove do banco de dados a <code>acão</code> fornecida como
	 * argumento.
	 * 
	 * @param acao
	 *            um objeto da classe {@link Acao} representando a
	 *            ação que deve ser removida do banco de dados.
	 */
	public void remover(Acao acao) {
		entityManager.remove(buscar(acao.getId()));
	}
	
}
