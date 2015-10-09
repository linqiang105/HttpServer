package com.atw.weight.bean.storage;

/**
 * 进出库货物
 * 
 * @author haibara
 *
 */
public class Goods {

	private int id;
	private String name;
	private String spec;
	private String memo;
	private Goods parentGoods;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Goods getParentGoods() {
		return parentGoods;
	}

	public void setParentGoods(Goods parentGoods) {
		this.parentGoods = parentGoods;
	}

	@Override
	public String toString() {
		return "货名 [id=" + id + ", 名称=" + name + ", 规格=" + spec + ", 备注=" + memo + ", 从属=" + parentGoods + "]";
	}

}
