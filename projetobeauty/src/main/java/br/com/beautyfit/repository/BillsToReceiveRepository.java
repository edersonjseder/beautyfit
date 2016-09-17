package br.com.beautyfit.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.beautyfit.model.BillsToReceive;

@Transactional
public interface BillsToReceiveRepository extends CrudRepository<BillsToReceive, Integer> {
	
	public BillsToReceive findByCreditor(String creditor);
	public List<BillsToReceive> findAll();

}
