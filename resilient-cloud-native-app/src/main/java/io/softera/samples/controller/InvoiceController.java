package io.softera.samples.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public String create(@RequestParam("accountId") Long accountId, 
						 @RequestParam("orderId") Long orderId) {
		
		invoiceService.create(accountId, orderId)
			   .subscribe(
					   n -> log.info("Invoice created: "+n),
					   e -> log.error("Fatal error: ",e));
		
		return "Request received, in async processing phase.";
	}
	
	@RequestMapping(value = "invoices2", method = RequestMethod.POST)
	public String createWithServiceVariant(@RequestParam("accountId") Long accountId, 
						 				   @RequestParam("orderId") Long orderId) {
		
		invoiceServiceWithCircuitBreaker.create(accountId, orderId)
			   .subscribe(
					   n -> log.info("Invoice created by variant service: "+n),
					   e -> log.error("Fatal error in variant service: ",e));
		
		return "Request received, in async processing phase.";
	}	
}
