package com.atw.weight.vo.storage;

import java.util.List;

import com.atw.weight.bean.incomeExpense.Customer;

public class CustomerListResult {

	private int count;
	private List<Customer> result;
	private int status;
	private String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Customer> getResult() {
		return result;
	}

	public void setResult(List<Customer> result) {
		this.result = result;
	}

}
