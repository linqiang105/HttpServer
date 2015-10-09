package com.atw.weight.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atw.weight.bean.TestBean;
import com.atw.weight.dao.ITestDao;
import com.atw.weight.service.ITestService;

@Service("testService")
public class TestServiceImpl implements ITestService {

	@Resource(name = "testDao")
	private ITestDao testDao;

	public TestBean getTestBean() {
		// TODO Auto-generated method stub
		return testDao.get();
	}

}
