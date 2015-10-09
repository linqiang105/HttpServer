package com.atw.weight.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atw.weight.bean.incomeExpense.InOutItem;
import com.atw.weight.service.IIncomeExpenseService;
import com.atw.weight.vo.CommonResult;
import com.atw.weight.vo.incomeExpense.BenefitResult;
import com.atw.weight.vo.incomeExpense.InOutHistoryListResult;
import com.atw.weight.vo.incomeExpense.InOutItemListResult;

@Controller("incomeExpenseController")
@RequestMapping("incomeExpense")
public class IncomeExpenseController {

	private static Logger log = LoggerFactory.getLogger(IncomeExpenseController.class);

	@Resource(name = "incomeExpenseService")
	private IIncomeExpenseService incomeExpenseService;

	@RequestMapping("benefit.do")
	@ResponseBody
	public BenefitResult getBenefit(HttpServletRequest request, HttpServletResponse response) {
		log.info("取未入账信息");
		BenefitResult benefit = new BenefitResult();
		benefit.setStatus(0);
		benefit.setResult(incomeExpenseService.getBenefit());
		return benefit;
	}

	@RequestMapping("benefitOut.do")
	@ResponseBody
	public BenefitResult getBenefitOut(HttpServletRequest request, HttpServletResponse response) {
		log.info("取已入账信息");
		BenefitResult benefit = new BenefitResult();
		benefit.setStatus(0);
		benefit.setResult(incomeExpenseService.getBenefitOut());
		return benefit;
	}

	@RequestMapping("left.do")
	@ResponseBody
	public BenefitResult getLeft(HttpServletRequest request, HttpServletResponse response) {
		log.info("取当前可流动资金");
		BenefitResult benefit = new BenefitResult();
		benefit.setStatus(0);
		benefit.setResult(incomeExpenseService.getLeft());
		return benefit;
	}

	@RequestMapping("inOutItem.do")
	@ResponseBody
	public InOutItemListResult getInOutItem(HttpServletRequest request, HttpServletResponse response) {
		log.info("取收支条目");
		InOutItemListResult inOutItemList = incomeExpenseService.getInOutItems();
		inOutItemList.setStatus(0);
		return inOutItemList;
	}

	@RequestMapping(value = "saveInOutItem.do", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult saveInOutItem(@RequestParam("name") String name, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("新增收支条目");
		CommonResult commonResult = new CommonResult();
		try {
			name = URLDecoder.decode(name, "utf-8");
			commonResult = incomeExpenseService.saveInOutItem(new InOutItem(name));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			commonResult.setStatus(1);
			commonResult.setMessage("UnsupportedEncodingException");
		}
		return commonResult;
	}

	@RequestMapping(value = "saveInOutHistory.do", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult saveInOutHistory(@RequestParam("name") String name,
			@RequestParam("createTime") String createTime, @RequestParam("inOutType") int inOutType,
			@RequestParam("inOutItem") String inOutItem, @RequestParam("desc") String desc,
			@RequestParam("money") Double money, @RequestParam("memo") String memo,
			@RequestParam("project") String project, HttpServletRequest request, HttpServletResponse response) {
		log.info("新增收支数据");
		CommonResult commonResult = new CommonResult();
		try {
			name = URLDecoder.decode(name, "utf-8");
			Date createTime1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(createTime);
			inOutItem = URLDecoder.decode(inOutItem, "utf-8");
			memo = URLDecoder.decode(memo, "utf-8");
			project = URLDecoder.decode(project, "utf-8");
			commonResult = incomeExpenseService.saveInOutHistory(name, createTime1, inOutType, inOutItem, desc, money,
					memo, project);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			commonResult.setStatus(1);
			commonResult.setMessage("UnsupportedEncodingException");
		} catch (ParseException e) {
			e.printStackTrace();
			commonResult.setStatus(1);
			commonResult.setMessage("ParseException");
		}
		return commonResult;
	}

	@RequestMapping(value = "inOutHistory.do")
	@ResponseBody
	public InOutHistoryListResult getInOutHistory(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, HttpServletRequest request, HttpServletResponse response) {
		log.info("获取收支数据");
		InOutHistoryListResult inOutHistoryList = new InOutHistoryListResult();
		try {
			Date startDate1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(startDate);
			Date endDate1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(endDate);
			inOutHistoryList = incomeExpenseService.getHistory(startDate1, endDate1);
			inOutHistoryList.setStatus(0);
		} catch (ParseException e) {
			e.printStackTrace();
			inOutHistoryList.setStatus(1);
			inOutHistoryList.setMessage("ParseException");
		}
		return inOutHistoryList;
	}

	@RequestMapping(value = "inOut.do")
	@ResponseBody
	public InOutHistoryListResult getInOut(@RequestParam("id") int id, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("获取收支数据明细");
		InOutHistoryListResult inOutHistoryList = new InOutHistoryListResult();

		inOutHistoryList = incomeExpenseService.getOneHistory(id);
		inOutHistoryList.setStatus(0);

		return inOutHistoryList;
	}

	@RequestMapping(value = "inOutOut.do")
	@ResponseBody
	public InOutHistoryListResult getInOutOut(@RequestParam("id") int id, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("获取已入账收支数据明细");
		InOutHistoryListResult inOutHistoryList = new InOutHistoryListResult();

		inOutHistoryList = incomeExpenseService.getOneHistoryOut(id);
		inOutHistoryList.setStatus(0);

		return inOutHistoryList;
	}

}
