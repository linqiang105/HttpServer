package com.atw.weight.dao.impl;

import org.springframework.stereotype.Repository;

import com.atw.weight.bean.TestBean;
import com.atw.weight.dao.ITestDao;

@Repository("testDao")
public class TestDaoImpl implements ITestDao {

	public TestBean get() {
		// TODO Auto-generated method stub
		TestBean testBean = new TestBean();
		testBean.setId(1);
		testBean.setName("haibaralin");
		return testBean;
	}

}
