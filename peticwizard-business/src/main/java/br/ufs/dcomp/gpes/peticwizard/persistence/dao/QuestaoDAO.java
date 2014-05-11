package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Questao;

/**
 * DAO (sigla de Data Access Object) � um padr�o de projeto (<i>design
 * pattern</i>) que define a separa��o das regras de neg�cio das regras de
 * acesso a banco de dados. Todas as opera��es referentes a bancos de dados,
 * tais como inser��o, consulta, atualiza��o, remo��o e listagem de registros
 * s�o realizadas por classes projetadas espec�ficas para execut�-las.
 * 
 * <p>
 * Essa classe representa um DAO espec�fico para a entidade
 * {@link Questao}. Um objeto dessa classe deve ser utilizado sempre
 * que for necess�rio realizar opera��es referentes a banco envolvendo um objeto
 * da classe <code>Questao</code>.
 * </p>
 * 
 * @see Questao
 */
@Stateless
public class QuestaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Construtor padr�o da classe {@link QuestaoDAO}. Retorna um objeto da
	 * classe <code>QuestaoDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes � entidade {@link Questao}.
	 * 
	 * @deprecated Deve-se utilizar inje��o para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private QuestaoDAO daoQuestao;</code>
	 * 
	 * @see Questao
	 * @see EJB
	 */
	@Deprecated
	public QuestaoDAO() {}
	
	/**
	 * Insere no banco de dados a <code>quest�o</code> fornecida como
	 * argumento.
	 * 
	 * @param questao
	 *            um objeto da classe {@link Questao} representando a
	 *            quest�o que deve ser inserida no banco de dados.
	 */
	public Questao inserir(Questao questao) {
		entityManager.persist(questao);
		return questao;
	}
	
	/**
	 * Busca no banco de dados uma quest�o com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link Questao} que
	 * representa essa quest�o. Pode retornar <code>null</code>, caso n�o exista
	 * uma quest�o com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            representando o <code>id</code> da
	 *            quest�o que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>Questao</code> representando a
	 *         quest�o desejada, caso ele exista, ou <code>null</code>,
	 *         caso n�o.
	 */
	public Questao buscar(Integer id) {
		TypedQuery<Questao> query = entityManager.createNamedQuery(
				Questao.BUSCAR_POR_ID, Questao.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	/**
	 * Atualiza no banco de dados a <code>quest�o</code> fornecida como
	 * argumento.
	 * 
	 * @param questao
	 *            um objeto da classe {@link Questao} representando a
	 *            quest�o que deve ser atualizada no banco de dados.
	 */
	public void atualizar(Questao questao) {
		Questao questaoAntiga = buscar(questao.getId());
		if (questaoAntiga != null)
			entityManager.merge(questao);
		else
			inserir(questao);
	}
	
	/**
	 * Busca no banco de dados registros correspondentes � entidade
	 * {@link Questao}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes � entidade <code>Questao</code> encontrados.
	 *         Essa lista pode ser vazia (sem objetos), caso nenhum registro
	 *         seja encontrado.
	 * 
	 * @see Questao
	 * @see List
	 */
	public List<Questao> listarTodos() {
		return entityManager.createNamedQuery(Questao.LISTAR_TODOS,
				Questao.class).getResultList();
	}
	
	/**
	 * Remove do banco de dados a <code>quest�o</code> fornecida como
	 * argumento.
	 * 
	 * @param questao
	 *            um objeto da classe {@link Questao} representando a
	 *            quest�o que deve ser removida do banco de dados.
	 */
	public void remover(Questao questao) {
		entityManager.remove(buscar(questao.getId()));
	}

}
