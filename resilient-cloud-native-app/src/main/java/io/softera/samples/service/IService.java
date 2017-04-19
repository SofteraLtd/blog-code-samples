package io.softera.samples.service;

import java.util.concurrent.atomic.AtomicLong;

import io.softera.samples.model.Account;
import io.softera.samples.model.Invoice;
import io.softera.samples.model.Order;
import rx.Observable;

public interface IService {

	String BASE_URL = "http://localhost:8080/api/v1";
	
	AtomicLong NXT_INVOICE_ID = new AtomicLong();//for sequential id gen - transient, demo use only

	Observable<Invoice> create(Long accountId, Long orderId);
	
	Observable<Account> retrieveAccount(Long accountId);
	
	Observable<Order> retrieveOrder(Long orderId);
}