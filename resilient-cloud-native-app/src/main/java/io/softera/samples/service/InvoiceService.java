package io.softera.samples.service;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.softera.samples.model.Account;
import io.softera.samples.model.Invoice;
import io.softera.samples.model.Order;
import rx.Observable;
import rx.schedulers.Schedulers;

@Service
public class InvoiceService implements IService {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
	
	/* (non-Javadoc)
	 * @see io.softera.samples.service.IService#create(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Observable<Invoice> create(Long accountId, Long orderId){
		
		return Observable.zip(retrieveAccount(accountId),
								retrieveOrder(orderId), 
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
        		.doOnNext(n -> log.info(String.format("Account id [%d] retrieved", n.getAccountId())))
        		.doOnError(ex -> log.warn("Error " + ex))
        	    .retryWhen(ex -> ex.delay(1000, TimeUnit.MILLISECONDS))
        	    .timeout(3, TimeUnit.SECONDS)
        	    .subscribeOn(Schedulers.io());
	}
	
	/* (non-Javadoc)
	 * @see io.softera.samples.service.IService#retrieveOrder(java.lang.Long)
	 */
	@Override
	public Observable<Order> retrieveOrder(Long orderId) {
		
        String orderUri = String.format("%s/orders/%d",BASE_URL,orderId);
        
        return Observable.fromCallable(() -> restTemplate.getForObject(orderUri, Order.class))
        		.doOnNext(n -> log.info(String.format("Order id [%d] retrieved", n.getOrderId())))
        		.doOnError(ex -> log.warn("Error " + ex))
        	    .retryWhen(ex -> ex.delay(1000, TimeUnit.MILLISECONDS))
        	    .timeout(3, TimeUnit.SECONDS)
        	    .subscribeOn(Schedulers.io());
	}
}
