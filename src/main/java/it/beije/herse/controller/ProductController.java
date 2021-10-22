package it.beije.herse.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import it.beije.herse.Carrello;
import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;

	@RequestMapping(path = "/catalogo", method = RequestMethod.POST)
	public String acquisto(Model model, @RequestParam String id, @RequestParam String qty) {
		Product p;
		Carrello carr=new Carrello();
		
		List<Carrello> carrello=(List<Carrello>)model.getAttribute("carrello");
		System.out.println("Sono il carrello "+carrello);
		p=productRepository.getOne(Integer.parseInt(id));
		System.out.println("OKKKKKKKKKKKK");
		if(p==null)
			return null;
		//productService
		carr.setId_product(Integer.parseInt(id));
		carr.setQty(Integer.parseInt(qty));
		carrello.add(carr);
		System.out.println(carr.getId_product()+" "+carr.getQty());
		model.addAttribute("carrello", carrello);
		if(carrello!=null)
		for(Carrello c:carrello) {
			System.out.print(c.getId_product());
		}
		
		return "product/catalogo";
		
	}
}