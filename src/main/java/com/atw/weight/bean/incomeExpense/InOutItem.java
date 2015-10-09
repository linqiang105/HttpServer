package com.atw.weight.bean.incomeExpense;

/**
 * 收支条目
 * 
 * @author haibara
 *
 */

public class InOutItem {

	private int id;

	/**
	 * 快递费 软件款 常规支出 硬件支出 其他支出 其他收入 话费 差旅费 招待费
	 */
	private String name;

	/**
	 * 资金进出方向
	 */
	private int direction;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public InOutItem() {

	}

	public InOutItem(String name) {
		this.name = name;
	}

}
