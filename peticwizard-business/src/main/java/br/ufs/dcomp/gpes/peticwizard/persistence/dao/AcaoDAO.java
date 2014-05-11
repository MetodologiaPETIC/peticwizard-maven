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
 * DAO (sigla de Data Access Object) � um padr�o de projeto (<i>design
 * pattern</i>) que define a separa��o das regras de neg�cio das regras de
 * acesso a banco de dados. Todas as opera��es referentes a bancos de dados,
 * tais como inser��o, consulta, atualiza��o, remo��o e listagem de registros
 * s�o realizadas por classes projetadas espec�ficas para execut�-las.
 * 
 * <p>
 * Essa classe representa um DAO espec�fico para a entidade
 * {@link Acao}. Um objeto dessa classe deve ser utilizado sempre
 * que for necess�rio realizar opera��es referentes a banco envolvendo um objeto
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
	 * Construtor padr�o da classe {@link AcaoDAO}. Retorna um objeto da
	 * classe <code>AcaoDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes � entidade {@link Acao}.
	 * 
	 * @deprecated Deve-se utilizar inje��o para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private AcaoDAO daoAcao;</code>
	 * 
	 * @see Acao
	 * @see EJB
	 */
	@Deprecated
	public AcaoDAO() {}
	
	/**
	 * Insere no banco de dados a <code>a��o</code> fornecida como
	 * argumento.
	 * 
	 * @param acao
	 *            um objeto da classe {@link Acao} representando a
	 *            a��o que deve ser inserida no banco de dados.
	 */
	public Acao inserir(Acao acao) {
		entityManager.persist(acao);
		return acao;
	}
	
	/**
	 * Busca no banco de dados uma a��o com o <code>id</code> fornecido
	 * como argumento e retorna um objeto da classe {@link Acao} que
	 * representa essa a��o. Pode retornar <code>null</code>, caso n�o exista
	 * uma a��o com o <code>id</code> fornecido.
	 * 
	 * @param id
	 *            representando o <code>id</code> da
	 *            a��o que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>Acao</code> representando a
	 *         a��o desejada, caso ele exista, ou <code>null</code>,
	 *         caso n�o.
	 */
	public Acao buscar(Integer id) {
		TypedQuery<Acao> query = entityManager.createNamedQuery(
				Acao.BUSCAR_POR_ID, Acao.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
	
	/**
	 * Atualiza no banco de dados a <code>a��o</code> fornecida como
	 * argumento.
	 * 
	 * @param acao
	 *            um objeto da classe {@link Acao} representando a
	 *            a��o que deve ser atualizada no banco de dados.
	 */
	public void atualizar(Acao acao) {
		Acao acaoAntiga = buscar(acao.getId());
		if (acaoAntiga != null)
			entityManager.merge(acao);
		else
			inserir(acao);
	}
	
	/**
	 * Busca no banco de dados registros correspondentes � entidade
	 * {@link Acao}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes � entidade <code>Acao</code> encontrados.
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
	 * Remove do banco de dados a <code>ac�o</code> fornecida como
	 * argumento.
	 * 
	 * @param acao
	 *            um objeto da classe {@link Acao} representando a
	 *            a��o que deve ser removida do banco de dados.
	 */
	public void remover(Acao acao) {
		entityManager.remove(buscar(acao.getId()));
	}
	
}
