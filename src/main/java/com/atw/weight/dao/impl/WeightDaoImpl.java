package com.atw.weight.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atw.weight.dao.IWeightDao;

@Repository("weightDao")
public class WeightDaoImpl implements IWeightDao {

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

}
