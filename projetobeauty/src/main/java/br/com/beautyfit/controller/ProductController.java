package br.com.beautyfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.beautyfit.endpoint.Path;
import br.com.beautyfit.exceptions.KeywordNotFoundException;
import br.com.beautyfit.messages.Messages;
import br.com.beautyfit.model.Products;
import br.com.beautyfit.repository.ProductsRepository;

@RequestMapping(value = Path.urls.PRODUCTS)
@RestController
public class ProductController {
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Products>> listALL() {
		
		List<Products> product = productsRepository.findAll();
		
		if(product.isEmpty()){
			
			return new ResponseEntity<List<Products>>(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<List<Products>>(product, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Products> getProductsById(@PathVariable Integer productId) {

		Products product = productsRepository.findOne(productId);
		
		if (product != null) {
		
			return new ResponseEntity<Products>(product, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(productId, Messages.MESSAGE_SEARCH_FAIL);
			
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Products> create(@RequestBody Products product) {
		
		productsRepository.save(product);
		
		return new ResponseEntity<Products>(product, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Products> update(@PathVariable Integer productId, @RequestBody Products product) {

		Products productUpdated = productsRepository.findOne(productId);

		if (productUpdated != null) {
			
			productUpdated.setName(product.getName());
			productUpdated.setDescription(product.getDescription());
			productUpdated.setPrice(product.getPrice());
									
			productsRepository.save(productUpdated);
			
			return new ResponseEntity<Products>(product, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(productId, Messages.MESSAGE_UPDATE_FAIL);
			
		}
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Integer productId) {

		Products product = productsRepository.findOne(productId);

		if (product != null) {

			productsRepository.delete(product);

		} else {
			
			throw new KeywordNotFoundException(productId, Messages.MESSAGE_DELETE_FAIL);
			
		}
	}

}
