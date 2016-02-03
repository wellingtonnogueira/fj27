package br.com.casadocodigo.loja.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.PaymentData;
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
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(@RequestParam("productId") Integer id, @RequestParam BookType bookType) {
		ModelAndView mv = new ModelAndView("redirect:/products");
		ShoppingItem item = createItem(id, bookType);
		shoppingCart.add(item);
		
		return mv;
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public Callable<String> checkout() {
		System.out.println("Checkout");
		
		return () -> {
			
			BigDecimal total = shoppingCart.getTotal();
			
			String uriToPay = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
				try{Thread.sleep(1000);} catch(Exception e){}
				System.out.println(response);
				
				shoppingCart.getList().clear();
				
				return "redirect:/products";
				
			} catch (HttpClientErrorException e) {
				//e.printStackTrace();
				System.out.println("Ocorreu um erro ao criar o pagamento: " + e.getMessage());
				return "redirect:/shopping";
			}
			
		};
		
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list() {
		return "shoppingCart/items";
	}

	private ShoppingItem createItem(Integer id, BookType bookType) {
		Product product = productDAO.find(id);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}
}
