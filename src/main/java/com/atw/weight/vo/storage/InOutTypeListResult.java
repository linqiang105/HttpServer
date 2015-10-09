package com.atw.weight.vo.storage;

import java.util.List;

import com.atw.weight.bean.storage.InOutType;

public class InOutTypeListResult {
	private int count;
	private List<InOutType> result;
	private int status;
	private String message;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<InOutType> getResult() {
		return result;
	}

	public void setResult(List<InOutType> result) {
		this.result = result;
	}

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

}
