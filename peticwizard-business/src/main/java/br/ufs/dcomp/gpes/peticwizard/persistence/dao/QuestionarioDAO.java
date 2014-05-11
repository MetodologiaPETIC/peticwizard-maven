package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Questionario;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade
 * {@link Questionario}. Um objeto dessa classe deve ser utilizado sempre
 * que for necessário realizar operações referentes a banco envolvendo um objeto
 * da classe <code>Questionario</code>.
 * </p>
 * 
 * @see Questionario
 */
@Stateless
public class QuestionarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Construtor padrão da classe {@link QuestionarioDAO}. Retorna um objeto da
	 * classe <code>QuestionarioDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes à entidade {@link Questionario}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private QuestionarioDAO daoQuestionario;</code>
	 * 
	 * @see Questionario
	 * @see EJB
	 */
	@Deprecated
	public QuestionarioDAO() {}
	
	/**
	 * Insere no banco de dados o <code>questionário</code> fornecido como
	 * argumento.
	 * 
	 * @param questionário
	 *            um objeto da classe {@link Questionario} representando o
	 *            questionário que deve ser inserido no banco de dados.
	 */
	public Questionario inserir(Questionario questionario) {
		entityManager.persist(questionario);
		return questionario;
	}
	
	/**
	 * Busca no banco de dados um questionário com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link Questionario} que
	 * representa esse questionário. Pode retornar <code>null</code>, caso não exista
	 * um questionário com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            representando o <code>id</code> do
	 *            questionário que deve ser buscado no banco de dados.
	 * 
	 * @return um objeto da classe <code>Questionario</code> representando o
	 *         questionário desejado, caso ele exista, ou <code>null</code>,
	 *         caso não.
	 */
	public Questionario buscar(Integer id) {
		TypedQuery<Questionario> query = entityManager.createNamedQuery(
				Questionario.BUSCAR_POR_ID, Questionario.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	/**
	 * Atualiza no banco de dados o <code>questionário</code> fornecido como
	 * argumento.
	 * 
	 * @param questionario
	 *            um objeto da classe {@link Questionario} representando o
	 *            questionário que deve ser atualizado no banco de dados.
	 */
	public void atualizar(Questionario questionario) {
		Questionario questionarioAntigo = buscar(questionario.getId());
		if (questionarioAntigo != null)
			entityManager.merge(questionario);
		else
			inserir(questionario);
	}
	
	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link Questionario}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>Questionario</code> encontrados.
	 *         Essa lista pode ser vazia (sem objetos), caso nenhum registro
	 *         seja encontrado.
	 * 
	 * @see Questionario
	 * @see List
	 */
	public List<Questionario> listarTodos() {
		return entityManager.createNamedQuery(Questionario.LISTAR_TODOS,
				Questionario.class).getResultList();
	}
	
	/**
	 * Remove do banco de dados a <code>questionário</code> fornecida como
	 * argumento.
	 * 
	 * @param questionario
	 *            um objeto da classe {@link Questionario} representando o
	 *            questionário que deve ser removido do banco de dados.
	 */
	public void remover(Questionario questionario) {
		entityManager.remove(buscar(questionario.getId()));
	}

}
