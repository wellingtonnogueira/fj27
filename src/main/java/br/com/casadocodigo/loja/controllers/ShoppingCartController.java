package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(@RequestParam("productId") Integer id, @RequestParam BookType bookType) {
		ModelAndView mv = new ModelAndView("redirect:/products");
		ShoppingItem item = createItem(id, bookType);
		shoppingCart.add(item);
		
		return mv;
	}

	private ShoppingItem createItem(Integer id, BookType bookType) {
		Product product = productDAO.find(id);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}
}
