package com.atw.weight.bean.storage;

public class SendInfo {
	private Goods goods;
	private Double count;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public SendInfo() {
	}

	@Override
	public String toString() {
		return "物品 [名称=" + goods + ", 数量=" + count + "]";
	}
}
