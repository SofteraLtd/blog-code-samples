package io.softera.samples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.softera.samples.model.Invoice;
import io.softera.samples.service.IService;

@RestController
@RequestMapping("api/v1/")
public class InvoiceController {
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);
	
	@Autowired
	private IService invoiceService;
	
	@Autowired
	private IService invoiceServiceWithCircuitBreaker;
	
	@RequestMapping(value = "invoices", method = RequestMethod.POST)
	public DeferredResult<Invoice> create(@RequestParam("accountId") Long accountId, 
						 				  @RequestParam("orderId") Long orderId) {
		
		DeferredResult<Invoice> deferredResult = new DeferredResult<>();
		invoiceService.create(accountId, orderId)
			   .subscribe(
					   n -> {
						   log.info("Invoice created: " + n);	
						   deferredResult.setResult(n);  	
					   },
					   e -> log.error("Fatal error: ",e));
		
		return deferredResult;
	}
	
	@RequestMapping(value = "invoices-cb", method = RequestMethod.POST)
	public DeferredResult<Invoice> createWithServiceVariant(@RequestParam("accountId") Long accountId, 
						 				   					@RequestParam("orderId") Long orderId) {
		
		DeferredResult<Invoice> deferredResult = new DeferredResult<>();
		invoiceServiceWithCircuitBreaker.create(accountId, orderId)
			   .subscribe(
					   n -> {
						   log.info("Invoice created with variant service: " + n);	
						   deferredResult.setResult(n);  	
					   },
					   e -> log.error("Fatal error in variant service: ",e));
		
		return deferredResult;
	}	
}
