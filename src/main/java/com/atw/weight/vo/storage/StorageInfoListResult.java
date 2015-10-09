package com.atw.weight.vo.storage;

import java.util.List;

public class StorageInfoListResult {

	class Storage {
		private String goods;
		private Double count;

		public String getGoods() {
			return goods;
		}

		public void setGoods(String goods) {
			this.goods = goods;
		}

		public Double getCount() {
			return count;
		}

		public void setCount(Double count) {
			this.count = count;
		}

	}

	private int count;
	private List<Storage> result;
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

	public List<Storage> getResult() {
		return result;
	}

	public void setResult(List<Storage> result) {
		this.result = result;
	}

}
