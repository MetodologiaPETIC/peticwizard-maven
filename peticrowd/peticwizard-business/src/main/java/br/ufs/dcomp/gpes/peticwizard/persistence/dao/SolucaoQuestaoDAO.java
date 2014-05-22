package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.SolucaoQuestao;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade
 * {@link SolucaoQuestao}. Um objeto dessa classe deve ser utilizado sempre
 * que for necessário realizar operações referentes a banco envolvendo um objeto
 * da classe <code>SolucaoQuestao</code>.
 * </p>
 * 
 * @see SolucaoQuestao
 */
@Stateless
public class SolucaoQuestaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Construtor padrão da classe {@link SolucaoQuestaoDAO}. Retorna um objeto da
	 * classe <code>SolucaoQuestaoDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes à entidade {@link SolucaoQuestao}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private SolucaoQuestaoDAO daoSolucaoQuestao;</code>
	 * 
	 * @see SolucaoQuestao
	 * @see EJB
	 */
	@Deprecated
	public SolucaoQuestaoDAO() {}
	
	/**
	 * Insere no banco de dados a <code>solução de questão</code> fornecida como
	 * argumento.
	 * 
	 * @param solucaoQuestao
	 *            um objeto da classe {@link SolucaoQuestao} representando a
	 *            solução de questão que deve ser inserida no banco de dados.
	 */
	public SolucaoQuestao inserir(SolucaoQuestao solucaoQuestao) {
		entityManager.persist(solucaoQuestao);
		return solucaoQuestao;
	}
	
	/**
	 * Busca no banco de dados uma solução de questão com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link SolucaoQuestao} que
	 * representa essa solução de questão. Pode retornar <code>null</code>, caso não exista
	 * uma solução de questão com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            representando o <code>id</code> da solução de
	 *            questão que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>SolucaoQuestao</code> representando a
	 *         solução de questão desejada, caso ele exista, ou <code>null</code>,
	 *         caso não.
	 */
	public SolucaoQuestao buscar(Integer id) {
		TypedQuery<SolucaoQuestao> query = entityManager.createNamedQuery(
				SolucaoQuestao.BUSCAR_POR_ID, SolucaoQuestao.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	/**
	 * Atualiza no banco de dados a <code>solução de questão</code> fornecida como
	 * argumento.
	 * 
	 * @param solucaoQuestao
	 *            um objeto da classe {@link SolucaoQuestao} representando a
	 *            solução de questão que deve ser atualizada no banco de dados.
	 */
	public void atualizar(SolucaoQuestao solucaoQuestao) {
		SolucaoQuestao solucaoQuestaoAntiga = buscar(solucaoQuestao.getId());
		if (solucaoQuestaoAntiga != null)
			entityManager.merge(solucaoQuestao);
		else
			inserir(solucaoQuestao);
	}
	
	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link SolucaoQuestao}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>SolucaoQuestao</code> encontrados.
	 *         Essa lista pode ser vazia (sem objetos), caso nenhum registro
	 *         seja encontrado.
	 * 
	 * @see SolucaoQuestao
	 * @see List
	 */
	public List<SolucaoQuestao> listarTodos() {
		return entityManager.createNamedQuery(SolucaoQuestao.LISTAR_TODOS,
				SolucaoQuestao.class).getResultList();
	}
	
	/**
	 * Remove do banco de dados a <code>solução de questão</code> fornecida como
	 * argumento.
	 * 
	 * @param solucaoQuestao
	 *            um objeto da classe {@link SolucaoQuestao} representando a
	 *            solução de questão que deve ser removida do banco de dados.
	 */
	public void remover(SolucaoQuestao solucaoQuestao) {
		entityManager.remove(buscar(solucaoQuestao.getId()));
	}

}
