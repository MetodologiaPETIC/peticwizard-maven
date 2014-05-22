package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Processo;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade
 * {@link Processo}. Um objeto dessa classe deve ser utilizado sempre
 * que for necessário realizar operações referentes a banco envolvendo um objeto
 * da classe <code>Processo</code>.
 * </p>
 * 
 * @see Processo
 */
@Stateless
public class ProcessoDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Construtor padrão da classe {@link ProcessoDAO}. Retorna um objeto da
	 * classe <code>ProcessoDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes à entidade {@link Processo}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private ProcessoDAO daoProcesso;</code>
	 * 
	 * @see Processo
	 * @see EJB
	 */
	@Deprecated
	public ProcessoDAO() {}
	
	/**
	 * Insere no banco de dados o <code>processo</code> fornecido como
	 * argumento.
	 * 
	 * @param processo
	 *            um objeto da classe {@link Processo} representando o
	 *            processo que deve ser inserido no banco de dados.
	 */
	public Processo inserir(Processo processo) {
		entityManager.persist(processo);
		return processo;
	}
	
	/**
	 * Busca no banco de dados um processo com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link Processo} que
	 * representa esse processo. Pode retornar <code>null</code>, caso não exista
	 * um processo com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            representando o <code>id</code> do
	 *            processo que deve ser buscado no banco de dados.
	 * 
	 * @return um objeto da classe <code>Processo</code> representando o
	 *         processo desejado, caso ele exista, ou <code>null</code>,
	 *         caso não.
	 */
	public Processo buscar(Integer id) {
		TypedQuery<Processo> query = entityManager.createNamedQuery(
				Processo.BUSCAR_POR_ID, Processo.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	/**
	 * Atualiza no banco de dados o <code>processo</code> fornecido como
	 * argumento.
	 * 
	 * @param processo
	 *            um objeto da classe {@link Processo} representando o
	 *            processo que deve ser atualizado no banco de dados.
	 */
	public void atualizar(Processo processo) {
		Processo processoAntigo = buscar(processo.getId());
		if (processoAntigo != null)
			entityManager.merge(processo);
		else
			inserir(processo);
	}
	
	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link Processo}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>Processo</code> encontrados.
	 *         Essa lista pode ser vazia (sem objetos), caso nenhum registro
	 *         seja encontrado.
	 * 
	 * @see Processo
	 * @see List
	 */
	public List<Processo> listarTodos() {
		return entityManager.createNamedQuery(Processo.LISTAR_TODOS,
				Processo.class).getResultList();
	}
	
	public List<Processo> listarCorrentesDaEmpresa(Integer idEmpresa) {
		return entityManager.createNamedQuery(Processo.LISTAR_CORRENTES_EMPRESA,
				Processo.class).getResultList();
	}
	
	/**
	 * Remove do banco de dados a <code>processo</code> fornecida como
	 * argumento.
	 * 
	 * @param processo
	 *            um objeto da classe {@link Processo} representando o
	 *            processo que deve ser removido do banco de dados.
	 */
	public void remover(Processo processo) {
		entityManager.remove(buscar(processo.getId()));
	}

}
