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
import br.com.beautyfit.model.BillsToPay;
import br.com.beautyfit.repository.BillsToPayRepository;

@RequestMapping(value = Path.urls.BILLSTOPAY)
@RestController
public class BillsToPayController {
	
	@Autowired
	private BillsToPayRepository billsToPayRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BillsToPay>> listALL() {
		
		List<BillsToPay> listBillsToPay = billsToPayRepository.findAll();
		
		if(listBillsToPay != null){
			return new ResponseEntity<List<BillsToPay>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<BillsToPay>>(listBillsToPay, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{billsToPayId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillsToPay> getBillsToPayById(@PathVariable Integer billsToPayId) {

		BillsToPay billsToPay = billsToPayRepository.findOne(billsToPayId);
		
		if (billsToPay != null) {
		
			return new ResponseEntity<BillsToPay>(billsToPay, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(billsToPayId, Messages.MESSAGE_SEARCH_FAIL);
			
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<BillsToPay> create(@RequestBody BillsToPay billsToPay) {
		
		billsToPayRepository.save(billsToPay);
		
		return new ResponseEntity<BillsToPay>(billsToPay, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{billsToPayId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<BillsToPay> update(@PathVariable Integer billsToPayId, @RequestBody BillsToPay billsToPay) {

		BillsToPay billsToPayUpdated = billsToPayRepository.findOne(billsToPayId);

		if (billsToPayUpdated != null) {
			
			billsToPayUpdated.setCreditor(billsToPay.getCreditor());
			billsToPayUpdated.setNumberParcels(billsToPay.getNumberParcels());
			billsToPayUpdated.setNumberPaidParcels(billsToPay.getNumberPaidParcels());
			billsToPayUpdated.setNumberParcelsToPay(billsToPay.getNumberParcelsToPay());
			billsToPayUpdated.setBillsToPayStatus(billsToPay.getBillsToPayStatus());
			billsToPayUpdated.setDocument(billsToPay.getDocument());
			billsToPayUpdated.setAmountToPay(billsToPay.getAmountToPay());
			billsToPayUpdated.setParcelAmount(billsToPay.getParcelAmount());
			billsToPayUpdated.setAmountPaid(billsToPay.getAmountPaid());
			billsToPayUpdated.setTotalToPay(billsToPay.getTotalToPay());
			billsToPayUpdated.setDueDate(billsToPay.getDueDate());
			billsToPayUpdated.setPaymentDate(billsToPay.getPaymentDate());
									
			billsToPayRepository.save(billsToPayUpdated);
			
			return new ResponseEntity<BillsToPay>(billsToPay, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(billsToPayId, Messages.MESSAGE_UPDATE_FAIL);
			
		}
	}
	
	@RequestMapping(value = "/{billsToPayId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Integer billsToPayId) {

		BillsToPay billsToPay = billsToPayRepository.findOne(billsToPayId);

		if (billsToPay != null) {

			billsToPayRepository.delete(billsToPay);

		} else {
			
			throw new KeywordNotFoundException(billsToPayId, Messages.MESSAGE_DELETE_FAIL);
			
		}
	}

}
