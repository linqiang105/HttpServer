package com.atw.weight.dao;

import java.util.Date;
import java.util.List;

import com.atw.weight.bean.incomeExpense.InOutHistory;
import com.atw.weight.bean.incomeExpense.InOutItem;
import com.atw.weight.bean.incomeExpense.Project;
import com.atw.weight.bean.incomeExpense.User;
import com.atw.weight.vo.CommonResult;

public interface IIncomeExpenseDao {

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
	 * 流动资金
	 * 
	 * @return
	 */
	public Double getLeft();

	/**
	 * 下载收支明目
	 * 
	 * @return
	 */
	public List<InOutItem> getInOutItems();

	/**
	 * 取单条收支明目
	 * 
	 * @param id
	 * @return
	 */
	public InOutItem getInOutItem(int id);

	/**
	 * 根据名称取收支明目
	 * 
	 * @param name
	 * @return
	 */
	public InOutItem getInOutItemByName(String name);

	/**
	 * 新增一种收支条目
	 * 
	 * @param inOutType
	 * @return
	 */
	public CommonResult saveInOutItem(InOutItem inOutItem);

	/**
	 * 取一段时间的收支明细 
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<InOutHistory> getHistory(Date startDate, Date endDate);

	/**
	 * 取一段时间的收支明细 
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<InOutHistory> getHistoryOut(Date startDate, Date endDate);

	/**
	 * 取单条收支数据
	 * 
	 * @param id
	 * @return
	 */
	public List<InOutHistory> getOneHistory(int id);

	public List<InOutHistory> getOneHistoryOut(int id);

	/**
	 * 保存一条收支数据
	 * 
	 * @param inOutHistory
	 * @return
	 */
	public CommonResult save(InOutHistory inOutHistory);

	/**
	 * 取用户信息
	 */
	public User getUser(int id);

	/**
	 * 根据姓名取用户信息
	 * 
	 * @param name
	 * @return
	 */
	public User getUserByName(String name);

	/**
	 * 取项目信息
	 * 
	 * @param id
	 * @return
	 */
	public Project getProject(int id);

	/**
	 * 根据名称取项目信息
	 * 
	 * @param name
	 * @return
	 */
	public Project getProjectByName(String name);
}
