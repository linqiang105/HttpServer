package com.atw.weight.vo.storage;

import java.util.List;

import com.atw.weight.bean.storage.Express;

public class ExpressListResult {

	private int count;
	private List<Express> result;
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

	public List<Express> getResult() {
		return result;
	}

	public void setResult(List<Express> result) {
		this.result = result;
	}

}
