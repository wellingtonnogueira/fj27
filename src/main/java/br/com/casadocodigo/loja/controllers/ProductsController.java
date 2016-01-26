package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.models.Product;

@Controller
public class ProductsController {

	@RequestMapping("/products/form")
	private String form() {
		return "products/form";
	}
	
	@RequestMapping("/products")
	private String save(Product products) {
		System.out.println("Cadastrando o produto: " + products);
		return "products/ok";
	}
}
