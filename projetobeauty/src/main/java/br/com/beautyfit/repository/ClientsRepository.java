package br.com.beautyfit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.beautyfit.model.Clients;


@Transactional
public interface ClientsRepository extends CrudRepository<Clients, Long> {

	public Clients findByName(String name);
	public List<Clients> findAll();
}