package br.com.casadocodigo.loja.validation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Price;
import br.com.casadocodigo.loja.models.Product;

public class ProductValidator implements Validator {

	private ProductDAO productDAO;
	
	public ProductValidator(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product p = (Product) target;
		if(productDAO.contains(p)) {
			errors.rejectValue("title", "title.duplicated");
		}
		//TODO Validar preços
		List<Price> prices = p.getPrices();
		BigDecimal printedValue = null;
		BigDecimal ebookValue = null;
		BigDecimal comboValue = null;
		for (Price price : prices) {
			if(price.getBookType().equals(BookType.PRINTED)) {
				System.out.println("1");
				printedValue = price.getValue();
			} else if(price.getBookType().equals(BookType.EBOOK)) {
				System.out.println("2");
				ebookValue = price.getValue();
			} else {
				System.out.println("3");
				comboValue = price.getValue();
			}
		}
		System.out.println("ENUM");
		if(comboValue != null && ebookValue != null && printedValue != null) {
			System.out.println("4");
			final int res;
			if((res = comboValue.compareTo(printedValue.add(ebookValue))) > 0) {
				System.out.println("5");
				//TODO: ADD ERROR MESSAGE
				//errors.rejectValue("COMBO", "combo.invalid");
			}
			System.out.println("Res: " + res);
		}

	}

}
