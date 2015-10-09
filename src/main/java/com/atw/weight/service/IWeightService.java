package com.atw.weight.service;

import java.util.Date;

import com.atw.weight.bean.weight.BackupInfo;
import com.atw.weight.bean.weight.CarNo;
import com.atw.weight.bean.weight.Goods;
import com.atw.weight.bean.weight.Receiver;
import com.atw.weight.bean.weight.Sender;
import com.atw.weight.bean.weight.Spec;
import com.atw.weight.bean.weight.WeightInfo;
import com.atw.weight.vo.weight.AllWeightInfoResult;
import com.atw.weight.vo.weight.BackupInfoListResult;
import com.atw.weight.vo.weight.CarNoListResult;
import com.atw.weight.vo.weight.GoodsListResult;
import com.atw.weight.vo.weight.ReceiverListResult;
import com.atw.weight.vo.weight.SenderListResult;
import com.atw.weight.vo.weight.SpecListResult;
import com.atw.weight.vo.weight.WeightInfoListResult;

public interface IWeightService {

	/**
	 * 下载车号列表
	 * 
	 * @return
	 */
	public CarNoListResult getCarNos();

	/**
	 * 下载发货单位列表
	 * 
	 * @return
	 */
	public SenderListResult getSenders();

	/**
	 * 下载收货单位列表
	 * 
	 * @return
	 */
	public ReceiverListResult getReceivers();

	/**
	 * 下载货名列表
	 * 
	 * @return
	 */
	public GoodsListResult getGoods();

	/**
	 * 下载规格列表
	 * 
	 * @return
	 */
	public SpecListResult getSpecs();

	/**
	 * 下载备用列表
	 * 
	 * @param index
	 * @return
	 */
	public BackupInfoListResult getBackupInfos(int index);

	/**
	 * 新增车号
	 * 
	 * @param carNo
	 * @return
	 */
	public boolean saveCarNo(CarNo carNo);

	/**
	 * 新增发货单位
	 * 
	 * @param sender
	 * @return
	 */
	public boolean saveSender(Sender sender);

	/**
	 * 新增收货单位
	 * 
	 * @param receiver
	 * @return
	 */
	public boolean saveReceiver(Receiver receiver);

	/**
	 * 新增货名
	 * 
	 * @param goods
	 * @return
	 */
	public boolean saveGoods(Goods goods);

	/**
	 * 新增规格
	 * 
	 * @param spec
	 * @return
	 */
	public boolean saveSpec(Spec spec);

	/**
	 * 新增备用信息
	 * 
	 * @param backupInfo
	 * @return
	 */
	public boolean saveBackupInfo(int index, BackupInfo backupInfo);

	/**
	 * 下载称重记录
	 */
	public WeightInfoListResult getWeightInfos(Date startDate, Date endDate);

	/**
	 * 取单条称重记录明细
	 * 
	 * @param glideNo
	 * @return
	 */
	public AllWeightInfoResult getWeightInfo(String glideNo);

	/**
	 * 保存称重信息
	 * 
	 * @return
	 */
	public boolean saveWeightInfo(WeightInfo weightInfo);
}
