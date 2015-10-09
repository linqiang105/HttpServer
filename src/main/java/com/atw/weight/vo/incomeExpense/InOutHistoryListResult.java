package com.atw.weight.vo.incomeExpense;

import java.util.List;

import com.atw.weight.bean.incomeExpense.InOutHistory;

public class InOutHistoryListResult {

	private int status;
	private String message;

	private int count;
	private List<InOutHistory> result;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<InOutHistory> getResult() {
		return result;
	}

	public void setResult(List<InOutHistory> result) {
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
