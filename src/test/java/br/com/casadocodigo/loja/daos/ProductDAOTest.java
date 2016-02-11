package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.casadocodigo.loja.builders.ProductsBuilder;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ProductDAO.class,JPAConfiguration.class})
public class ProductDAOTest {

	@Autowired
	private ProductDAO dao;
	
	@Test
	@Transactional
	public void shouldSumAllPricesOfEachBookPerType() {
		List<Product> printedBooks = ProductsBuilder
			.newProductBuilder(BookType.PRINTED, BigDecimal.TEN)
			.more(4).buildAll();
		
		printedBooks.stream().forEach(dao::save); //stream since Java 8
		
		List<Product> ebookBooks = ProductsBuilder
				.newProductBuilder(BookType.EBOOK, BigDecimal.TEN)
				.more(4).buildAll();
			
		ebookBooks.stream().forEach(dao::save);
		
		BigDecimal value = dao.sumPricesPerType(BookType.PRINTED);
		
		Assert.assertEquals(new BigDecimal(50).setScale(2), value);
		
		
	}
}
