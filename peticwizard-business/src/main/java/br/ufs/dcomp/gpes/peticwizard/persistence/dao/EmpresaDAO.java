package br.ufs.dcomp.gpes.peticwizard.persistence.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufs.dcomp.gpes.peticwizard.persistence.modelo.Empresa;

/**
 * DAO (sigla de Data Access Object) é um padrão de projeto (<i>design
 * pattern</i>) que define a separação das regras de negócio das regras de
 * acesso a banco de dados. Todas as operações referentes a bancos de dados,
 * tais como inserção, consulta, atualização, remoção e listagem de registros
 * são realizadas por classes projetadas específicas para executá-las.
 * 
 * <p>
 * Essa classe representa um DAO específico para a entidade
 * {@link Empresa}. Um objeto dessa classe deve ser utilizado sempre
 * que for necessário realizar operações referentes a banco envolvendo um objeto
 * da classe <code>Empresa</code>.
 * </p>
 * 
 * @see Empresa
 */
@Stateless
public class EmpresaDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Construtor padrão da classe {@link EmpresaDAO}. Retorna um objeto da
	 * classe <code>EmpresaDAO</code> capaz de gerenciar no banco de dados
	 * objetos correspondentes à entidade {@link Empresa}.
	 * 
	 * @deprecated Deve-se utilizar injeção para construir objetos dessa classe,
	 *             como em:
	 *             <code>@EJB private EmpresaDAO daoEmpresa;</code>
	 * 
	 * @see Empresa
	 * @see EJB
	 */
	@Deprecated
	public EmpresaDAO() {}
	
	/**
	 * Insere no banco de dados a <code>empresa</code> fornecida como
	 * argumento.
	 * 
	 * @param empresa
	 *            um objeto da classe {@link Empresa} representando a
	 *            empresa que deve ser inserida no banco de dados.
	 */
	public Empresa inserir(Empresa empresa) {
		entityManager.persist(empresa);
		return empresa;
	}
	
	/**
	 * Busca no banco de dados uma empresa com o <code>nome</code> fornecido
	 * como argumento e retorna um objeto da classe {@link Empresa} que
	 * representa essa empresa. Pode retornar <code>null</code>, caso não exista
	 * uma empresa com o <code>nome</code> fornecido.
	 * 
	 * @param nome
	 *            representando o <code>nome</code> da
	 *            empresa que deve ser buscada no banco de dados.
	 * 
	 * @return um objeto da classe <code>Empresa</code> representando a
	 *         empresa desejada, caso ela exista, ou <code>null</code>,
	 *         caso não.
	 */
	public Empresa buscar(String nome) {
		TypedQuery<Empresa> query = entityManager.createNamedQuery(
				Empresa.BUSCAR, Empresa.class);
		query.setParameter("descricao", nome);
		return query.getSingleResult();
	}
	
	/**
	 * Atualiza no banco de dados a <code>empresa</code> fornecida como
	 * argumento.
	 * 
	 * @param empresa
	 *            um objeto da classe {@link Empresa} representando a
	 *            empresa que deve ser atualizada no banco de dados.
	 */
	public void atualizar(Empresa empresa) {
		Empresa empresaAntiga = buscar(empresa.getDescricao());
		if (empresaAntiga != null)
			entityManager.merge(empresa);
		else
			inserir(empresa);
	}
	
	/**
	 * Busca no banco de dados registros correspondentes à entidade
	 * {@link Empresa}. Retorna um objeto {@link List} tipado contendo
	 * todos os registros encontrados. Pode retornar uma lista vazia (sem
	 * objetos), caso nenhum registro seja encontrado.
	 * 
	 * @return um objeto <code>List</code> tipado contendo todos os registros
	 *         correspondentes à entidade <code>Empresa</code> encontrados.
	 *         Essa lista pode ser vazia (sem objetos), caso nenhum registro
	 *         seja encontrado.
	 * 
	 * @see Empresa
	 * @see List
	 */
	public List<Empresa> listarTodos() {
		return entityManager.createNamedQuery(Empresa.LISTAR_TODOS,
				Empresa.class).getResultList();
	}
	
	/**
	 * Remove do banco de dados a <code>empresa</code> fornecida como
	 * argumento.
	 * 
	 * @param empresa
	 *            um objeto da classe {@link Empresa} representando a
	 *            empresa que deve ser removida do banco de dados.
	 */

	public void remover(Empresa empresa) {
		entityManager.remove(buscar(empresa.getDescricao()));
	}

}
