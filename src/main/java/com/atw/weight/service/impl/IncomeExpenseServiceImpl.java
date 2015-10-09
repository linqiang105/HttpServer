package com.atw.weight.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atw.weight.bean.incomeExpense.Customer;
import com.atw.weight.bean.incomeExpense.InOutHistory;
import com.atw.weight.bean.incomeExpense.InOutItem;
import com.atw.weight.dao.IIncomeExpenseDao;
import com.atw.weight.service.IIncomeExpenseService;
import com.atw.weight.vo.CommonResult;
import com.atw.weight.vo.incomeExpense.InOutHistoryListResult;
import com.atw.weight.vo.incomeExpense.InOutItemListResult;
import com.atw.weight.vo.storage.CustomerListResult;

@Service("incomeExpenseService")
public class IncomeExpenseServiceImpl implements IIncomeExpenseService {

	private static Logger log = LoggerFactory.getLogger(IncomeExpenseServiceImpl.class);

	@Resource(name = "incomeExpenseDao")
	private IIncomeExpenseDao incomeExpenseDao;

	public Double getBenefit() {
		// TODO Auto-generated method stub
		return incomeExpenseDao.getBenefit();
	}

	public Double getBenefitOut() {
		// TODO Auto-generated method stub
		return incomeExpenseDao.getBenefitOut();
	}

	public Double getLeft() {
		// TODO Auto-generated method stub
		return incomeExpenseDao.getLeft();
	}

	public InOutHistoryListResult getHistory(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		InOutHistoryListResult inOutHistoryList = new InOutHistoryListResult();
		List<InOutHistory> list1 = incomeExpenseDao.getHistory(startDate, endDate);
		List<InOutHistory> list2 = incomeExpenseDao.getHistoryOut(startDate, endDate);
		List<InOutHistory> listAll = new ArrayList<InOutHistory>();
		listAll.addAll(list1);
		listAll.addAll(list2);
		inOutHistoryList.setCount(listAll.size());
		inOutHistoryList.setResult(listAll);
		return inOutHistoryList;
	}

	public InOutItemListResult getInOutItems() {
		// TODO Auto-generated method stub
		InOutItemListResult inOutItemList = new InOutItemListResult();
		List<InOutItem> list = incomeExpenseDao.getInOutItems();
		inOutItemList.setCount(list.size());
		inOutItemList.setResult(list);
		return inOutItemList;
	}

	public CommonResult saveInOutItem(InOutItem inOutItem) {
		// TODO Auto-generated method stub
		return incomeExpenseDao.saveInOutItem(inOutItem);
	}

	public CustomerListResult getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public CommonResult saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public CommonResult saveInOutHistory(String name, Date createTime1, int intOutType, String inOutItem, String desc,
			Double money, String memo, String project) {

		CommonResult result = new CommonResult();

		InOutHistory inOutHistory = new InOutHistory();
		InOutItem ioItem = incomeExpenseDao.getInOutItemByName(inOutItem);
		ioItem.setDirection(intOutType);
		inOutHistory.setInOutItem(ioItem);
		inOutHistory.setProject(incomeExpenseDao.getProjectByName(project));
		inOutHistory.setMoney(money);
		inOutHistory.setDesc(desc);
		inOutHistory.setCreateUser(incomeExpenseDao.getUserByName(name));
		inOutHistory.setCreateTime(createTime1);
		inOutHistory.setUpdateUser(incomeExpenseDao.getUserByName(name));
		inOutHistory.setUpdateTime(createTime1);
		inOutHistory.setMemo(memo);

		if ((inOutHistory.getCreateUser() == null) || (inOutHistory.getCreateUser().getId() == 0)) {
			log.info("user is null");
			result.setStatus(1);
			result.setMessage("user is null");
		} else if ((inOutHistory.getProject() == null) || (inOutHistory.getProject().getId() == 0)) {
			log.info("project is null");
			result.setStatus(1);
			result.setMessage("project is null");
		} else {
			result = incomeExpenseDao.save(inOutHistory);
		}

		return result;
	}

	public InOutHistoryListResult getOneHistory(int id) {
		// TODO Auto-generated method stub
		InOutHistoryListResult inOutHistoryList = new InOutHistoryListResult();
		List<InOutHistory> list = incomeExpenseDao.getOneHistory(id);
		inOutHistoryList.setCount(list.size());
		inOutHistoryList.setResult(list);
		return inOutHistoryList;
	}

	public InOutHistoryListResult getOneHistoryOut(int id) {
		// TODO Auto-generated method stub
		InOutHistoryListResult inOutHistoryList = new InOutHistoryListResult();
		List<InOutHistory> list = incomeExpenseDao.getOneHistoryOut(id);
		inOutHistoryList.setCount(list.size());
		inOutHistoryList.setResult(list);
		return inOutHistoryList;
	}

}
