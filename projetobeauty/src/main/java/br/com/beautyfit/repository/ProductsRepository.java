package br.com.beautyfit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.beautyfit.model.Products;

@Transactional
public interface ProductsRepository extends CrudRepository<Products, Integer> {
	
	public List<Products> findAll();

}
