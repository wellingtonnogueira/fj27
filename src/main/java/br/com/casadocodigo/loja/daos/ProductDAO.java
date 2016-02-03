package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;
	@Transactional
	public void save(Product products) {
		manager.persist(products);
	}
	
	public List<Product> list() {
		return manager.createQuery(
				"select distinct(p) "
				+ "from Product p "
				+ "join fetch p.prices where p.releaseDate is not null and p.summaryPath is not null",Product.class).getResultList();
	}

	public boolean contains(Product p) {
		Query query = manager.createQuery("select p from Product p where UPPER(p.title)=UPPER(:pTitle)");
		query.setParameter("pTitle", p.getTitle());
		
		return !query.getResultList().isEmpty();
	}

	public Product find(Integer id) {
		TypedQuery<Product> query = manager.createQuery(
				"select distinct(p) "
				+ "from Product p "
				+ "join fetch p.prices where p.id = :id", Product.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
