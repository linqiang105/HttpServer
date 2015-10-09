package com.atw.weight.service;

import java.util.Date;

import com.atw.weight.bean.incomeExpense.Customer;
import com.atw.weight.bean.incomeExpense.InOutItem;
import com.atw.weight.vo.CommonResult;
import com.atw.weight.vo.incomeExpense.InOutHistoryListResult;
import com.atw.weight.vo.incomeExpense.InOutItemListResult;
import com.atw.weight.vo.storage.CustomerListResult;

public interface IIncomeExpenseService {

	/**
	 * 未结账金额
	 * 
	 * @return
	 */
	public Double getBenefit();

	/**
	 * 已结账金额
	 * 
	 * @return
	 */
	public Double getBenefitOut();

	/**
	 * 当前可流动资金
	 * 
	 * @return
	 */
	public Double getLeft();

	/**
	 * 取一段时间的收支明细 
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public InOutHistoryListResult getHistory(Date startDate, Date endDate);

	/**
	 * 取单条收支明细
	 * 
	 * @param id
	 * @return
	 */
	public InOutHistoryListResult getOneHistory(int id);
	
	/**
	 * 取单条收支明细
	 * 
	 * @param id
	 * @return
	 */
	public InOutHistoryListResult getOneHistoryOut(int id);

	/**
	 * 保存一条收支记录
	 * 
	 * @param inOutHistory
	 * @return
	 */
	public CommonResult saveInOutHistory(String name, Date createTime1, int intOutType, String inOutItem, String desc,
			Double money, String memo, String project);

	/**
	 * 下载收支明目
	 * 
	 * @return
	 */
	public InOutItemListResult getInOutItems();

	/**
	 * 新增一种收支条目
	 * 
	 * @param inOutType
	 * @return
	 */
	public CommonResult saveInOutItem(InOutItem inOutItem);

	/**
	 * 下载客户列表
	 * 
	 * @return
	 */
	public CustomerListResult getCustomers();

	/**
	 * 新增一个客户
	 * 
	 * @param customer
	 * @return
	 */
	public CommonResult saveCustomer(Customer customer);
}
