package io.softera.samples.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.softera.samples.model.Account;

@RestController
@RequestMapping("api/v1/")
public class AccountController {
	
	@RequestMapping(value = "accounts", method = RequestMethod.GET)
	public List<Account> list() {
		return AccountStub.list();
	}
	
	@RequestMapping(value = "accounts/{id}", method = RequestMethod.GET)
	public Account get(@PathVariable Long id) {
		return AccountStub.get(id);
	}
	
	@RequestMapping(value = "fallbacks/accounts", method = RequestMethod.GET)
	public Account fallbackGet() {
		return AccountStub.get(1L);//default to id=1 for instance
	}
}
