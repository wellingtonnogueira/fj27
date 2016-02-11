package br.com.casadocodigo.loja.builders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Price;
import br.com.casadocodigo.loja.models.Product;

public class ProductsBuilder {
	
	private List<Product> products = new ArrayList<Product>();
	
	private ProductsBuilder(Product product) {
		products.add(product);
	}
	
	private static int count = 0;

	public static ProductsBuilder newProductBuilder(BookType bookType, BigDecimal value) {
		return new ProductsBuilder(create("Book " + (++count), bookType, value));
	}
	
	public static ProductsBuilder newProduct() {
		return new ProductsBuilder(create("Book " + (++count), BookType.COMBO, BigDecimal.TEN));
	}

	private static Product create(String bookName, BookType bookType, BigDecimal value) {
		Product book = new Product();
		book.setTitle(bookName);
		book.setReleaseDate(Calendar.getInstance());
		book.setNumberOfPages(150);
		book.setDescription("Great book about testing");
		
		Price price = new Price();
		price.setBookType(bookType);
		price.setValue(value);
		
		book.getPrices().add(price);
		
		return book;
	}
	
	public ProductsBuilder more(int number) {
		Product base = products.get(0);
		Price price = base.getPrices().get(0);
		
		for (int i = 0; i < number; i++) {
			products.add(create("OtherBook" + i, price.getBookType(), price.getValue()));
		}
		
		return this;
	}
	
	public Product buildOne() {
		return products.get(0);
	}
	
	public List<Product> buildAll() {
		return products;
	}
}
