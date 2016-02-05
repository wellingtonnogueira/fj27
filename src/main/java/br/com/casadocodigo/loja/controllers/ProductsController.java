package br.com.casadocodigo.loja.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.validation.ProductValidator;

@RequestMapping("/products")
@Controller
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileSaver fileSaver;

	@Secured("hasRole('ADMIN')")
	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView mv = new ModelAndView("products/form");
		mv.addObject("types", BookType.values());
		mv.addObject("product", product);
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional //the method must be **public**
	@CacheEvict(value="lastProducts", allEntries=true)
	public ModelAndView save(
			@RequestParam("summary") MultipartFile summary,
			@Valid Product product, BindingResult result, 
			Model model, RedirectAttributes ra) {
		System.out.println("Cadastrando o produto: " + product);
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return form(product);
		}
		
		String webPath = fileSaver.write("upload-summaries", summary);
		
		product.setSummaryPath(webPath);
		
		productDAO.save(product);
		ra.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:products");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable(value="lastProducts")
	public ModelAndView list() {
		System.out.println("list");
		ModelAndView mv = new ModelAndView("products/list");
		mv.addObject("products", productDAO.list());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ModelAndView show(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("products/show");
		mv.addObject("product", productDAO.find(id));
		
		return mv;
	}
	
	@InitBinder
	public void addBinders(WebDataBinder binder) {
		binder.addValidators(new ProductValidator(productDAO));
	}
}
