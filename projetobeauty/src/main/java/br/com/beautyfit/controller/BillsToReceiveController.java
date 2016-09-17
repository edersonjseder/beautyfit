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
import br.com.beautyfit.model.BillsToReceive;
import br.com.beautyfit.repository.BillsToReceiveRepository;

@RequestMapping(value = Path.urls.BILLSTORECEIVE)
@RestController
public class BillsToReceiveController {
	
	@Autowired
	private BillsToReceiveRepository billsToReceiveRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BillsToReceive>> listALL() {
		
		List<BillsToReceive> listBillsToReceive = billsToReceiveRepository.findAll();
		
		if(listBillsToReceive != null){
			return new ResponseEntity<List<BillsToReceive>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<BillsToReceive>>(listBillsToReceive, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{billsToReceiveId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BillsToReceive> getBillsToPayById(@PathVariable Integer billsToReceiveId) {

		BillsToReceive billsToReceive = billsToReceiveRepository.findOne(billsToReceiveId);
		
		if (billsToReceive != null) {
		
			return new ResponseEntity<BillsToReceive>(billsToReceive, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(billsToReceiveId, Messages.MESSAGE_SEARCH_FAIL);
			
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<BillsToReceive> create(@RequestBody BillsToReceive billsToReceive) {
		
		billsToReceiveRepository.save(billsToReceive);
		
		return new ResponseEntity<BillsToReceive>(billsToReceive, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{billsToReceiveId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<BillsToReceive> update(@PathVariable Integer billsToReceiveId, @RequestBody BillsToReceive billsToReceive) {

		BillsToReceive billsToReceiveUpdated = billsToReceiveRepository.findOne(billsToReceiveId);

		if (billsToReceiveUpdated != null) {
			
			billsToReceiveUpdated.setCreditor(billsToReceive.getCreditor());
			billsToReceiveUpdated.setNumberParcels(billsToReceive.getNumberParcels());
			billsToReceiveUpdated.setNumberReceivedParcels(billsToReceive.getNumberReceivedParcels());
			billsToReceiveUpdated.setNumberParcelsToReceive(billsToReceive.getNumberParcelsToReceive());
			billsToReceiveUpdated.setBillsToReceiveStatus(billsToReceive.getBillsToReceiveStatus());
			billsToReceiveUpdated.setDocument(billsToReceive.getDocument());
			billsToReceiveUpdated.setAmountToReceive(billsToReceive.getAmountToReceive());
			billsToReceiveUpdated.setParcelAmount(billsToReceive.getParcelAmount());
			billsToReceiveUpdated.setAmountReceived(billsToReceive.getAmountReceived());
			billsToReceiveUpdated.setTotalToReceive(billsToReceive.getTotalToReceive());
			billsToReceiveUpdated.setDueDate(billsToReceive.getDueDate());
			billsToReceiveUpdated.setReceiptDate(billsToReceive.getReceiptDate());
									
			billsToReceiveRepository.save(billsToReceiveUpdated);
			
			return new ResponseEntity<BillsToReceive>(billsToReceive, HttpStatus.OK);
			
		} else {
			
			throw new KeywordNotFoundException(billsToReceiveId, Messages.MESSAGE_UPDATE_FAIL);
			
		}
	}
	
	@RequestMapping(value = "/{billsToReceiveId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Integer billsToReceiveId) {

		BillsToReceive billsToReceive = billsToReceiveRepository.findOne(billsToReceiveId);

		if (billsToReceive != null) {

			billsToReceiveRepository.delete(billsToReceive);

		} else {
			
			throw new KeywordNotFoundException(billsToReceiveId, Messages.MESSAGE_DELETE_FAIL);
			
		}
	}

}
