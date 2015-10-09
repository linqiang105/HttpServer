package com.atw.weight.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atw.weight.bean.TestBean;
import com.atw.weight.service.ITestService;

@Controller("testController")
public class TestController {

	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Resource(name = "testService")
	private ITestService testService;

	@RequestMapping("test.do")
	public String getTestBean(HttpServletRequest request, HttpServletResponse response) {

		TestBean testBean = testService.getTestBean();
		request.setAttribute("testBeanId", testBean.getId());
		request.setAttribute("testBeanName", testBean.getName());
		return "test";

	}

	@ResponseBody
	@RequestMapping("testJson.do")
	public TestBean getTestBeanJson(HttpServletRequest request, HttpServletResponse response) {
		logger.info("get Json String");
		TestBean testBean = testService.getTestBean();
		return testBean;

	}

	class ListBean {
		private int count;
		private List<TestBean> listBean;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public List<TestBean> getListBean() {
			return listBean;
		}

		public void setListBean(List<TestBean> listBean) {
			this.listBean = listBean;
		}

	}

	@ResponseBody
	@RequestMapping("testJsonList.do")
	public ListBean getTestBeanJsonList(HttpServletRequest request, HttpServletResponse response) {
		logger.info("get Json List");
		TestBean testBean1 = testService.getTestBean();
		TestBean testBean2 = testService.getTestBean();
		List<TestBean> listBean = new ArrayList<TestBean>();
		listBean.add(testBean1);
		listBean.add(testBean2);
		ListBean listBean1 = new ListBean();
		listBean1.count = listBean.size();
		listBean1.listBean = listBean;
		return listBean1;

	}
}
