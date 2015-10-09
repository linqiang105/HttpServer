package com.atw.weight.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atw.weight.bean.weight.BackupInfo;
import com.atw.weight.bean.weight.CarNo;
import com.atw.weight.bean.weight.Goods;
import com.atw.weight.bean.weight.Receiver;
import com.atw.weight.bean.weight.Sender;
import com.atw.weight.bean.weight.Spec;
import com.atw.weight.bean.weight.WeightInfo;
import com.atw.weight.dao.IWeightDao;
import com.atw.weight.service.IWeightService;
import com.atw.weight.vo.weight.AllWeightInfoResult;
import com.atw.weight.vo.weight.BackupInfoListResult;
import com.atw.weight.vo.weight.CarNoListResult;
import com.atw.weight.vo.weight.GoodsListResult;
import com.atw.weight.vo.weight.ReceiverListResult;
import com.atw.weight.vo.weight.SenderListResult;
import com.atw.weight.vo.weight.SpecListResult;
import com.atw.weight.vo.weight.WeightInfoListResult;

@Service("weightService")
public class WeightServiceImpl implements IWeightService {

	@Resource(name = "weightDao")
	private IWeightDao weightDao;

	public CarNoListResult getCarNos() {
		// TODO Auto-generated method stub
		return null;
	}

	public SenderListResult getSenders() {
		// TODO Auto-generated method stub
		return null;
	}

	public ReceiverListResult getReceivers() {
		// TODO Auto-generated method stub
		return null;
	}

	public GoodsListResult getGoods() {
		// TODO Auto-generated method stub
		return null;
	}

	public SpecListResult getSpecs() {
		// TODO Auto-generated method stub
		return null;
	}

	public BackupInfoListResult getBackupInfos(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveCarNo(CarNo carNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveSender(Sender sender) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveReceiver(Receiver receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveGoods(Goods goods) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveSpec(Spec spec) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveBackupInfo(int index, BackupInfo backupInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	public WeightInfoListResult getWeightInfos(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public AllWeightInfoResult getWeightInfo(String glideNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveWeightInfo(WeightInfo weightInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
