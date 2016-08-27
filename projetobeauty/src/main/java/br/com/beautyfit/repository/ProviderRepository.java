package br.com.beautyfit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.beautyfit.model.Providers;

@Transactional
public interface ProviderRepository extends CrudRepository<Providers, Long> {
	
	public List<Providers> findAll();

}
