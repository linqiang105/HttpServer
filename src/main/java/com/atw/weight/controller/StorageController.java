package com.atw.weight.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atw.weight.bean.storage.InOutType;
import com.atw.weight.service.IStorageService;
import com.atw.weight.vo.CommonResult;
import com.atw.weight.vo.storage.ExpressListResult;
import com.atw.weight.vo.storage.GoodsListResult;
import com.atw.weight.vo.storage.InOutTypeListResult;
import com.atw.weight.vo.storage.StorageInfoListResult;
import com.atw.weight.vo.storage.StorageResult;

@Controller("storageController")
@RequestMapping("storage")
public class StorageController {

	private static Logger log = LoggerFactory.getLogger(StorageController.class);

	@Resource(name = "storageService")
	private IStorageService storageService;

	@RequestMapping("inOutType.do")
	@ResponseBody
	public InOutTypeListResult getInOutItem(HttpServletRequest request, HttpServletResponse response) {
		log.info("取出入库类型条目");
		InOutTypeListResult inOutTypeList = storageService.getInOutTypes();
		inOutTypeList.setStatus(0);
		return inOutTypeList;
	}

	@RequestMapping("saveInOutType/{name}/{direction}")
	@ResponseBody
	public CommonResult saveInOutItem(@PathVariable("name") String name, @PathVariable("direction") int direction,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("新增入库类型条目");
		CommonResult commonResult = new CommonResult();

		if ((direction != 0) || (direction != 1)) {
			commonResult.setStatus(1);
			commonResult.setMessage("出入库类型不正确");
		} else {
			commonResult = storageService.saveInOutType(new InOutType(name, direction));
		}

		return commonResult;
	}

	@RequestMapping("goods.do")
	@ResponseBody
	public GoodsListResult getGoods(HttpServletRequest request, HttpServletResponse response) {
		log.info("取货品条目");
		GoodsListResult goodsListResult = storageService.getGoods();
		goodsListResult.setStatus(0);
		return goodsListResult;
	}

	@RequestMapping("saveGoods.do")
	@ResponseBody
	public CommonResult saveGoods(@RequestParam("name") String name, @RequestParam("pName") String pName,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("新增货名");
		String spec = request.getParameter("spec");
		String memo = request.getParameter("memo");
		CommonResult commonResult = storageService.saveGoods(name, spec, memo, pName);
		return commonResult;
	}

	@RequestMapping("express.do")
	@ResponseBody
	public ExpressListResult getExpress(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, HttpServletRequest request, HttpServletResponse response) {
		log.info("取快递单信息");
		ExpressListResult expressListResult = new ExpressListResult();
		try {
			Date startDate1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(startDate);
			Date endDate1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(endDate);
			expressListResult = storageService.getExpresses(startDate1, endDate1);
			expressListResult.setStatus(0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			expressListResult.setStatus(1);
			expressListResult.setMessage("ParseException");
		}
		return expressListResult;
	}

	@RequestMapping(value = "saveExpress.do", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult saveExpress(@RequestParam("code") String code, @RequestParam("sender") String sender,
			@RequestParam("sendAddr") String sendAddr, @RequestParam("sendTel") String sendTel,
			@RequestParam("receiver") String receiver, @RequestParam("receiveAddr") String receiveAddr,
			@RequestParam("receiveTel") String receiveTel, @RequestParam("goods") String[] goods,
			@RequestParam("num") Double[] num, @RequestParam("updateTime") String updateTime,
			@RequestParam("memo") String memo, HttpServletRequest request, HttpServletResponse response) {
		log.info("新增快递单");
		CommonResult commonResult = new CommonResult();
		try {
			Date updateTime1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(updateTime);

			commonResult = storageService.saveExpress(code, sender, sendAddr, sendTel, receiver, receiveAddr,
					receiveTel, goods, num, updateTime1, memo);
			commonResult.setStatus(0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			commonResult.setStatus(1);
			commonResult.setMessage("ParseException");
		}
		return commonResult;
	}

	@RequestMapping("storage/{name}")
	@ResponseBody
	public StorageResult getStorage(@PathVariable("name") String name, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("取单品货存");
		StorageResult storageResult = new StorageResult();
		storageResult.setStatus(0);
		storageResult.setGoods(name);
		storageResult.setLeft(storageService.getStorage(name));
		return storageResult;
	}

	@RequestMapping("storage.do")
	@ResponseBody
	public StorageInfoListResult getStorageList(HttpServletRequest request, HttpServletResponse response) {
		log.info("取货存信息");
		StorageInfoListResult storageInfoListResult = storageService.getStorage();
		return storageInfoListResult;
	}

	@RequestMapping("saveInOutHistory.do")
	@ResponseBody
	public CommonResult saveInOutHistory(@RequestParam("inOutType") int inOutType, @RequestParam("num") Double num,
			@RequestParam("memo") String memo, @RequestParam("goods") String goods, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("普通出入库操作");
		CommonResult commonResult = storageService.saveInOutHisotry(inOutType, num, memo, goods);
		return commonResult;
	}

	@RequestMapping(value = "pack.do", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult pack(@RequestParam("goods") String[] goods, @RequestParam("num") Double[] num,
			@RequestParam("newGoods") String newGoods, @RequestParam("newNum") Double newNum,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("产品组装");
		CommonResult commonResult = storageService.pack(goods, num, newGoods, newNum);
		return commonResult;
	}

}
