package io.softera.samples.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import io.softera.samples.model.Order;
import io.softera.samples.service.IService;
import io.softera.samples.service.InvoiceServiceWithCircuitBreaker;
import rx.Observable;

public class OrderCommand extends HystrixObservableCommand<Order> {
    
	private final Long orderId;
	
    private final IService service;

    public OrderCommand(Long orderId, IService service) {
        super(HystrixCommandGroupKey.Factory.asKey("OrderCmdGroup"));
        this.orderId = orderId;
        this.service = service;
    }

    @Override
    protected Observable<Order> construct() {
        return service.retrieveOrder(orderId);
    }
    
	@Override
	protected Observable<Order> resumeWithFallback() {
		return ((InvoiceServiceWithCircuitBreaker)service).fallbackOrder();
	}
}