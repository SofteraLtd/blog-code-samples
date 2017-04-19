package io.softera.samples.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import io.softera.samples.model.Account;
import io.softera.samples.service.IService;
import io.softera.samples.service.InvoiceServiceWithCircuitBreaker;
import rx.Observable;

public class AccountCommand extends HystrixObservableCommand<Account> {
    
	private final Long accountId;
	
    private final IService service;

    public AccountCommand(Long accountId, IService service) {
        super(HystrixCommandGroupKey.Factory.asKey("AccountCmdGroup"));
        this.accountId = accountId;
        this.service = service;
    }

    @Override
    protected Observable<Account> construct() {
        return service.retrieveAccount(accountId);
    }

	@Override
	protected Observable<Account> resumeWithFallback() {
		return ((InvoiceServiceWithCircuitBreaker)service).fallbackAccount();
	}
  
}