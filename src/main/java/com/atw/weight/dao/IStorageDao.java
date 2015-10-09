package com.atw.weight.dao;

import java.util.Date;
import java.util.List;

import com.atw.weight.bean.storage.Express;
import com.atw.weight.bean.storage.Goods;
import com.atw.weight.bean.storage.InOutType;

public interface IStorageDao {

	/**
	 * 取出入库类型
	 * 
	 * @return
	 */
	public List<InOutType> getInOutTypes();

	/**
	 * 新增出入库类型
	 * 
	 * @return
	 */
	public boolean saveInOutType(InOutType inOutType);

	/**
	 * 取货名信息
	 * 
	 * @return
	 */
	public List<Goods> getGoods();

	/**
	 * 根据名称取货名信息
	 * 
	 * @param id
	 * @return
	 */
	public Goods getGoodsByName(String name);

	/**
	 * 根据ID取货品信息
	 * 
	 * @param id
	 * @return
	 */
	public Goods getGoodsById(int id);

	/**
	 * 新增货名
	 * 
	 * @param goods
	 * @return
	 */
	public boolean saveGoods(Goods goods);

	/**
	 * 取快递单信息
	 * 
	 * @return
	 */
	public List<Express> getExpress(Date startDate, Date endDate);
}
