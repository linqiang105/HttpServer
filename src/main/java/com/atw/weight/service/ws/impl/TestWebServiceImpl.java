package com.atw.weight.service.ws.impl;

import org.springframework.stereotype.Service;

import com.atw.weight.service.ws.ITestWebService;

@Service("testWebService")
public class TestWebServiceImpl implements ITestWebService {

	public String sayHi(String who) {
		// TODO Auto-generated method stub
		return "Hi, " + who;
	}

}
