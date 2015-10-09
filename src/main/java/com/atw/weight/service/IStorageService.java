package com.atw.weight.service;

import java.util.Date;

import com.atw.weight.bean.storage.InOutType;
import com.atw.weight.vo.CommonResult;
import com.atw.weight.vo.storage.ExpressListResult;
import com.atw.weight.vo.storage.GoodsListResult;
import com.atw.weight.vo.storage.InOutTypeListResult;
import com.atw.weight.vo.storage.StorageInfoListResult;

public interface IStorageService {

	/**
	 * 下载出入库类型列表
	 * 
	 * @return
	 */
	public InOutTypeListResult getInOutTypes();

	/**
	 * 新增出入库类型
	 * 
	 * @param inOutType
	 * @return
	 */
	public CommonResult saveInOutType(InOutType inOutType);

	/**
	 * 下载产品列表
	 * 
	 * @return
	 */
	public GoodsListResult getGoods();

	/**
	 * 新增一种产品
	 * 
	 * @param goods
	 *            名称
	 * @param spec
	 *            规格
	 * @param memo
	 *            备注
	 * @param pGoods
	 *            从属
	 * @return
	 */
	public CommonResult saveGoods(String name, String spec, String memo, String pGoods);

	/**
	 * 下载快递单列表
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public ExpressListResult getExpresses(Date startDate, Date endDate);

	/**
	 * 新增一条发货单
	 * 
	 * @param code
	 *            快递单号
	 * @param sender
	 *            发货人
	 * @param sendAddr
	 *            发货地址
	 * @param sendTel
	 *            发货电话
	 * @param receiver
	 *            收件人
	 * @param receiveAddr
	 *            收件地址
	 * @param receiveTel
	 *            收件人电话
	 * @param goods
	 *            货品
	 * @param num
	 *            数量
	 * @param updateTime
	 *            发货时间
	 * @param memo
	 *            备注
	 * @return
	 */
	public CommonResult saveExpress(String code, String sender, String sendAddr, String sendTel, String receiver,
			String receiveAddr, String receiveTel, String[] goods, Double[] num, Date updateTime, String memo);

	/**
	 * 获取单品库存
	 * 
	 * @param goods
	 * @return
	 */
	public Double getStorage(String goods);

	/**
	 * 获取库存列表
	 * 
	 * @return
	 */
	public StorageInfoListResult getStorage();

	/**
	 * 录入期初、补录期初、补录期初红冲、采购入库、采购入库红冲、销售出库、销售出库红冲、采购退货、销售退货、组装入库
	 * 
	 * @param InOutType
	 * @param num
	 *            数量
	 * @param memo
	 *            备注
	 * @param goods
	 *            货名
	 * @return
	 */
	public CommonResult saveInOutHisotry(int inOutType, Double num, String memo, String goods);

	/**
	 * 产品组装
	 * 
	 * @param goods
	 * @param num
	 * @param newGoods
	 * @param newNum
	 * @return
	 */
	public CommonResult pack(String[] goods, Double[] num, String newGoods, Double newNum);

}
