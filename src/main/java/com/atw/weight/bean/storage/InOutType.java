package com.atw.weight.bean.storage;

/**
 * 进出类型
 * 
 * @author haibara
 *
 */
public class InOutType {

	private int id;

	/**
	 * 录入期初 补录期初 补录期初红冲 采购入库 采购入库红冲 销售出库 销售出库红冲 采购退货 销售退货 组装入库
	 */
	private String name;

	/**
	 * 进出方向，0：入库；1：出库
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

	public InOutType(String name, int direction) {
		super();
		this.name = name;
		this.direction = direction;
	}

	public InOutType() {
		// TODO Auto-generated constructor stub
	}

}
