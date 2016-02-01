package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
				+ "join fetch p.prices",Product.class).getResultList();
	}

	public boolean contains(Product p) {
		Query query = manager.createQuery("select p from Product p where UPPER(p.title)=UPPER(:pTitle)");
		query.setParameter("pTitle", p.getTitle());
		
		return !query.getResultList().isEmpty();
	}

}
