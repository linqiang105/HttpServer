package com.atw.weight.bean.storage;

import java.util.Date;

/**
 * 货物进出历史
 * 
 * @author haibara
 *
 */
public class InOutHistory {

	private int id;
	private Goods goods;
	private Double count;
	private Date updateTime;
	private InOutType inOutType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public InOutType getInOutType() {
		return inOutType;
	}

	public void setInOutType(InOutType inOutType) {
		this.inOutType = inOutType;
	}

}
