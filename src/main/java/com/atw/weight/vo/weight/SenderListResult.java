package com.atw.weight.vo.weight;

import java.util.List;

import com.atw.weight.bean.weight.Sender;

public class SenderListResult {
	private int count;
	private List<Sender> result;
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

	public List<Sender> getResult() {
		return result;
	}

	public void setResult(List<Sender> result) {
		this.result = result;
	}

}
