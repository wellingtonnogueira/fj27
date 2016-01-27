package br.com.casadocodigo.loja.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("/products/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("products/form");
		mv.addObject("types", BookType.values());
		return mv;
	}
	
	@RequestMapping("/products")
	@Transactional //the method must be **public**
	public String save(Product products) {
		System.out.println("Cadastrando o produto: " + products);
		productDAO.save(products);
		return "products/ok";
	}
}
