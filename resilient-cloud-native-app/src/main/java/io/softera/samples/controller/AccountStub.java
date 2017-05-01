package io.softera.samples.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import io.softera.samples.model.Account;

public class AccountStub {
	
	protected static AtomicLong nextAccountId = new AtomicLong();
	
	private static Map<Long, Account> accounts = new HashMap<>();
	
	static {
		Account a1 = new Account(nextAccountId.incrementAndGet(), "Alba Trading", "Waterloo Street, London, SW1");
		accounts.put(a1.getAccountId(), a1);
		Account a2 = new Account(nextAccountId.incrementAndGet(), "Premier Products", "Whiteinch Glasgow, G14");
		accounts.put(a2.getAccountId(), a2);
		Account a3 = new Account(nextAccountId.incrementAndGet(), "Locus Corp", "Queens Street, Edinburgh, EH2");
		accounts.put(a3.getAccountId(), a3);
	}
	
	public static List<Account> list() {
		return new ArrayList<Account>(accounts.values());
	}
	
	public static Account get(Long id) {
		return accounts.get(id);
	}
}
