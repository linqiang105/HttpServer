package com.atw.weight.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.atw.weight.service.IWeightService;

@Controller("weightController")
public class WeightController {

	@Resource(name="weightService")
	private IWeightService weightService;
}
