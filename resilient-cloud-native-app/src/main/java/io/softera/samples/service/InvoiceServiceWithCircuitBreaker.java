package io.softera.samples.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.softera.samples.command.AccountCommand;
import io.softera.samples.command.OrderCommand;
import io.softera.samples.model.Account;
import io.softera.samples.model.Invoice;
import io.softera.samples.model.Order;
import rx.Observable;
import rx.schedulers.Schedulers;

@Service
public class InvoiceServiceWithCircuitBreaker implements IService {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceWithCircuitBreaker.class);
	
	/* (non-Javadoc)
	 * @see io.softera.samples.service.IService#create(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Observable<Invoice> create(Long accountId, Long orderId){
		
		return Observable.zip(new AccountCommand(accountId, this).toObservable(),
								new OrderCommand(orderId, this).toObservable(), 
									(account, order) -> {
										return new Invoice(NXT_INVOICE_ID.incrementAndGet(), LocalDate.now(), account, order, false);
									});
   	}
	
	/* (non-Javadoc)
	 * @see io.softera.samples.service.IService#retrieveAccount(java.lang.Long)
	 */
	@Override
	public Observable<Account> retrieveAccount(Long accountId) {
		
		String accountUri = String.format("%s/accounts/%d",BASE_URL,accountId);
		
        return Observable.fromCallable(() -> restTemplate.getForObject(accountUri, Account.class))
        		.doOnNext(n -> log.debug(String.format("Account [%d] retrieved", n.getAccountId())))
        		.subscribeOn(Schedulers.io());//fork a separate thread
	}
	
	/* (non-Javadoc)
	 * @see io.softera.samples.service.IService#retrieveOrder(java.lang.Long)
	 */
	@Override
	public Observable<Order> retrieveOrder(Long orderId) {
		
        String orderUri = String.format("%s/orders/%d",BASE_URL,orderId);
        
        return Observable.fromCallable(() -> restTemplate.getForObject(orderUri, Order.class))
        		.doOnNext(n -> log.debug(String.format("Order [%d] retrieved", n.getOrderId())))
        		.subscribeOn(Schedulers.io());//fork a separate thread
	}
	
	
	public Observable<Account> fallbackAccount() {
		
		String accountUri = String.format("%s/fallbacks/accounts",BASE_URL);
		
        return Observable.fromCallable(() -> restTemplate.getForObject(accountUri, Account.class))
        		.doOnNext(n -> log.debug(String.format("Account [%d] retrieved", n.getAccountId())))
        		.subscribeOn(Schedulers.io());//fork a separate thread
	}
	
	public Observable<Order> fallbackOrder() {
		
        String orderUri = String.format("%s/fallbacks/orders",BASE_URL);
        
        return Observable.fromCallable(() -> restTemplate.getForObject(orderUri, Order.class))
        		.doOnNext(n -> log.debug(String.format("Order [%d] retrieved", n.getOrderId())))
        		.subscribeOn(Schedulers.io());//fork a separate thread
	}	
}
