package io.softera.samples.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Invoice {
	
	private Long invoiceId;
	
	private LocalDate invoiceDate;
	
	private Account forAccount;
	
	private Order forOrder;
	
	private Boolean paid;
	
	@JsonCreator
	public Invoice(@JsonProperty("invoiceId") Long invoiceId, 
				   @JsonProperty("invoiceDate") LocalDate invoiceDate, 
				   @JsonProperty("account") Account forAccount, 
				   @JsonProperty("order") Order forOrder, 
				   @JsonProperty("paid") Boolean paid) {
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.forAccount = forAccount;
		this.forOrder = forOrder;
		this.paid = paid;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public Account getForAccount() {
		return forAccount;
	}

	public Order getForOrder() {
		return forOrder;
	}

	public Boolean getPaid() {
		return paid;
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", forAccount=" + forAccount
				+ ", forOrder=" + forOrder + ", paid=" + paid + "]";
	}
	
}
