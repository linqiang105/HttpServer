package com.atw.weight.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atw.weight.bean.incomeExpense.InOutHistory;
import com.atw.weight.bean.incomeExpense.InOutItem;
import com.atw.weight.bean.incomeExpense.Project;
import com.atw.weight.bean.incomeExpense.User;
import com.atw.weight.dao.IIncomeExpenseDao;
import com.atw.weight.vo.CommonResult;

@Repository("incomeExpenseDao")
public class IncomeExpenseDaoImpl implements IIncomeExpenseDao {

	private static Logger log = LoggerFactory.getLogger(IncomeExpenseDaoImpl.class);

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public Double getBenefit() {

		String sql = "";
		List<Map<String, Object>> list = null;
		Double income, expense;

		// 取总收入金额
		sql = "select sum(price) as c from income_expense.dbo.tbl_income_expense where (type = 0) ";
		list = jdbcTemplate.queryForList(sql, new Object[] {});
		if ((list != null) && (list.size() > 0)) {
			if (null == list.get(0).get("c")) {
				income = 0.0;
			} else {
				income = Double.valueOf(list.get(0).get("c").toString());
			}
		} else {
			income = 0.0;
		}

		// 取总支出金额
		sql = "select sum(price) as c from income_expense.dbo.tbl_income_expense where type = 1";
		list = jdbcTemplate.queryForList(sql, new Object[] {});
		if ((list != null) && (list.size() > 0)) {
			if (null == list.get(0).get("c")) {
				expense = 0.0;
			} else {
				expense = Double.valueOf(list.get(0).get("c").toString());
			}
		} else {
			expense = 0.0;
		}

		return income - expense;
	}

	public Double getBenefitOut() {

		String sql = "";
		List<Map<String, Object>> list = null;
		Double income, expense;

		// 取总收入金额
		sql = "select sum(price) as c from income_expense.dbo.tbl_income_expense_out where (type = 0) ";
		list = jdbcTemplate.queryForList(sql, new Object[] {});
		if ((list != null) && (list.size() > 0)) {
			if (null == list.get(0).get("c")) {
				income = 0.0;
			} else {
				income = Double.valueOf(list.get(0).get("c").toString());
			}
		} else {
			income = 0.0;
		}

		// 取总支出金额
		sql = "select sum(price) as c from income_expense.dbo.tbl_income_expense_out where type = 1";
		list = jdbcTemplate.queryForList(sql, new Object[] {});
		if ((list != null) && (list.size() > 0)) {
			if (null == list.get(0).get("c")) {
				expense = 0.0;
			} else {
				expense = Double.valueOf(list.get(0).get("c").toString());
			}
		} else {
			expense = 0.0;
		}

		return income - expense;
	}

	public Double getLeft() {
		// TODO Auto-generated method stub
		return getBenefit() + getBenefitOut();
	}

	public List<InOutItem> getInOutItems() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,name from income_expense.dbo.tbl_item order by name");
		List<InOutItem> listInOutItem = new ArrayList<InOutItem>();
		if ((null != list) && (list.size() > 0)) {
			for (int i = 0; i < list.size(); i++) {
				InOutItem inOutItem = new InOutItem();
				inOutItem.setId(Integer.valueOf(list.get(i).get("id").toString()));
				inOutItem.setName(list.get(i).get("name").toString());
				inOutItem.setDirection(0);
				listInOutItem.add(inOutItem);
			}
		}
		return listInOutItem;
	}

	public CommonResult saveInOutItem(InOutItem inOutItem) {
		// TODO Auto-generated method stub
		CommonResult commonResult = new CommonResult();

		try {
			int ret = jdbcTemplate.update("insert into income_expense.dbo.tbl_item(name) values(?)",
					new Object[] { inOutItem.getName() });

			commonResult.setStatus(ret > 0 ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
			commonResult.setStatus(1);
			commonResult.setMessage(e.getCause().getMessage());
		}

		return commonResult;
	}

	public CommonResult save(InOutHistory inOutHistory) {
		// TODO Auto-generated method stub
		CommonResult commonResult = new CommonResult();

		String sql = "insert into income_expense.dbo.tbl_income_expense("
				+ "create_user,create_time,type,project,reason,price,memo,"
				+ "update_user,update_time,deleted,item) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			int ret = jdbcTemplate.update(sql,
					new Object[] { inOutHistory.getCreateUser().getId(), inOutHistory.getCreateTime(),
							inOutHistory.getInOutItem().getDirection(), inOutHistory.getProject().getId(),
							inOutHistory.getDesc(), inOutHistory.getMoney(), inOutHistory.getMemo(),
							inOutHistory.getUpdateUser().getId(), inOutHistory.getUpdateTime(), false,
							inOutHistory.getInOutItem().getId() });
			commonResult.setStatus(ret > 0 ? 0 : 1);
		} catch (Exception e) {
			e.printStackTrace();
			commonResult.setStatus(1);
			commonResult.setMessage(e.getCause().getMessage());
		}

		return commonResult;
	}

	public List<InOutHistory> getHistory(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList(
						"select id,create_user,create_time,type,project,reason,price,num,memo,"
								+ "update_user,update_time,item " + "from income_expense.dbo.tbl_income_expense "
								+ "where create_time between ? and ? order by id desc",
						new Object[] { startDate, endDate });
		List<InOutHistory> listInOutHistory = new ArrayList<InOutHistory>();
		if ((null != list) && (list.size() > 0)) {
			for (int i = 0; i < list.size(); i++) {
				InOutHistory inOutHistory = new InOutHistory();

				inOutHistory.setId(Integer.valueOf(list.get(i).get("id").toString()));
				inOutHistory.setCreateUser(getUser(Integer.valueOf(list.get(i).get("create_user").toString())));
				inOutHistory.setUpdateUser(getUser(Integer.valueOf(list.get(i).get("update_user").toString())));
				try {
					inOutHistory.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
							.parse(list.get(i).get("create_time").toString()));
					inOutHistory.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
							.parse(list.get(i).get("update_time").toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inOutHistory.setProject(getProject(Integer.valueOf(list.get(i).get("project").toString())));
				inOutHistory.setDesc(list.get(i).get("reason").toString());
				inOutHistory.setMoney(Double.valueOf(list.get(i).get("price").toString()));
				inOutHistory.setMemo(list.get(i).get("memo").toString());
				InOutItem inOutItem = getInOutItem(Integer.valueOf(list.get(i).get("item").toString()));
				inOutItem.setDirection(Integer.valueOf(list.get(i).get("type").toString()));
				inOutHistory.setInOutItem(inOutItem);

				listInOutHistory.add(inOutHistory);
			}
		}
		return listInOutHistory;
	}

	public User getUser(int id) {
		// TODO Auto-generated method stub
		User user = new User();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select id,username,password,update_time from income_expense.dbo.tbl_user where id=?",
				new Object[] { id });
		if ((null != list) && (list.size() > 0)) {
			user.setId(Integer.valueOf(list.get(0).get("id").toString()));
			user.setUsername(list.get(0).get("username").toString());
			user.setPassword(list.get(0).get("password").toString());
			try {
				user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.parse(list.get(0).get("update_time").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	public InOutItem getInOutItem(int id) {
		// TODO Auto-generated method stub
		InOutItem inOutItem = new InOutItem();
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,name,memo from income_expense.dbo.tbl_item where id=?", new Object[] { id });
		if ((null != list) && (list.size() > 0)) {
			inOutItem.setId(Integer.valueOf(list.get(0).get("id").toString()));
			inOutItem.setName(list.get(0).get("name").toString());
		}
		return inOutItem;
	}

	public Project getProject(int id) {
		// TODO Auto-generated method stub
		Project project = new Project();
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,name from income_expense.dbo.tbl_project where id=?", new Object[] { id });
		if ((null != list) && (list.size() > 0)) {
			project.setId(Integer.valueOf(list.get(0).get("id").toString()));
			project.setName(list.get(0).get("name").toString());
		}
		return project;
	}

	public InOutItem getInOutItemByName(String name) {
		// TODO Auto-generated method stub
		InOutItem inOutItem = new InOutItem();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select id,name,memo from income_expense.dbo.tbl_item where name=?", new Object[] { name });
		if ((null != list) && (list.size() > 0)) {
			inOutItem.setId(Integer.valueOf(list.get(0).get("id").toString()));
			inOutItem.setName(list.get(0).get("name").toString());
		}
		return inOutItem;
	}

	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		User user = new User();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select id,username,password,update_time from income_expense.dbo.tbl_user where username=?",
				new Object[] { name });
		if ((null != list) && (list.size() > 0)) {
			user.setId(Integer.valueOf(list.get(0).get("id").toString()));
			user.setUsername(list.get(0).get("username").toString());
			user.setPassword(list.get(0).get("password").toString());
			try {
				user.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.parse(list.get(0).get("update_time").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	public Project getProjectByName(String name) {
		// TODO Auto-generated method stub
		Project project = new Project();
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,name from income_expense.dbo.tbl_project where name=?", new Object[] { name });
		if ((null != list) && (list.size() > 0)) {
			project.setId(Integer.valueOf(list.get(0).get("id").toString()));
			project.setName(list.get(0).get("name").toString());
		}
		return project;
	}

	public List<InOutHistory> getHistoryOut(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList(
						"select id,create_user,create_time,type,project,reason,price,num,memo,"
								+ "update_user,update_time,item " + "from income_expense.dbo.tbl_income_expense_out "
								+ "where create_time between ? and ? order by id desc",
						new Object[] { startDate, endDate });
		List<InOutHistory> listInOutHistory = new ArrayList<InOutHistory>();
		if ((null != list) && (list.size() > 0)) {
			for (int i = 0; i < list.size(); i++) { 
				InOutHistory inOutHistory = new InOutHistory();

				inOutHistory.setId(Integer.valueOf(list.get(0).get("id").toString()));
				try {
					inOutHistory.setCreateUser(getUser(Integer.valueOf(list.get(0).get("create_user").toString())));
				} catch (Exception e) {
					log.info("create_user is null");
				}
				try {
					inOutHistory.setUpdateUser(getUser(Integer.valueOf(list.get(0).get("update_user").toString())));
				} catch (Exception e) {
					log.info("update_user is null");
				}
				try {
					inOutHistory.setProject(getProject(Integer.valueOf(list.get(0).get("project").toString())));

				} catch (Exception e) {
					log.info("project is null");
				}
				try {
					InOutItem inOutItem = getInOutItem(Integer.valueOf(list.get(0).get("item").toString()));
					inOutItem.setDirection(Integer.valueOf(list.get(0).get("type").toString()));
					inOutHistory.setInOutItem(inOutItem);
				} catch (Exception e) {
					log.info("inOutItem is null");
				}

				try {
					inOutHistory.setMoney(Double.valueOf(list.get(0).get("price").toString()));
				} catch (Exception e) {
					log.info("money is null");
				}

				try {
					inOutHistory.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
							.parse(list.get(0).get("create_time").toString()));
					inOutHistory.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
							.parse(list.get(0).get("update_time").toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inOutHistory.setDesc(list.get(0).get("reason").toString());
				inOutHistory.setMemo(list.get(0).get("memo").toString());

				listInOutHistory.add(inOutHistory);
			}
		}
		return listInOutHistory;
	}

	public List<InOutHistory> getOneHistory(int id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,create_user,create_time,type,project,reason,price,num,memo,"
						+ "update_user,update_time,item " + "from income_expense.dbo.tbl_income_expense "
						+ "where id = ?", new Object[] { id });
		List<InOutHistory> listInOutHistory = new ArrayList<InOutHistory>();
		if ((null != list) && (list.size() > 0)) {
			InOutHistory inOutHistory = new InOutHistory();

			inOutHistory.setId(Integer.valueOf(list.get(0).get("id").toString()));
			try {
				inOutHistory.setCreateUser(getUser(Integer.valueOf(list.get(0).get("create_user").toString())));
			} catch (Exception e) {
				log.info("create_user is null");
			}
			try {
				inOutHistory.setUpdateUser(getUser(Integer.valueOf(list.get(0).get("update_user").toString())));
			} catch (Exception e) {
				log.info("update_user is null");
			}
			try {
				inOutHistory.setProject(getProject(Integer.valueOf(list.get(0).get("project").toString())));

			} catch (Exception e) {
				log.info("project is null");
			}
			try {
				InOutItem inOutItem = getInOutItem(Integer.valueOf(list.get(0).get("item").toString()));
				inOutItem.setDirection(Integer.valueOf(list.get(0).get("type").toString()));
				inOutHistory.setInOutItem(inOutItem);
			} catch (Exception e) {
				log.info("inOutItem is null");
			}

			try {
				inOutHistory.setMoney(Double.valueOf(list.get(0).get("price").toString()));
			} catch (Exception e) {
				log.info("money is null");
			}

			try {
				inOutHistory.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.parse(list.get(0).get("create_time").toString()));
				inOutHistory.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.parse(list.get(0).get("update_time").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inOutHistory.setDesc(list.get(0).get("reason").toString());
			inOutHistory.setMemo(list.get(0).get("memo").toString());

			listInOutHistory.add(inOutHistory);
		}
		return listInOutHistory;
	}

	public List<InOutHistory> getOneHistoryOut(int id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,create_user,create_time,type,project,reason,price,num,memo,"
						+ "update_user,update_time,item " + "from income_expense.dbo.tbl_income_expense_out "
						+ "where id = ?", new Object[] { id });
		List<InOutHistory> listInOutHistory = new ArrayList<InOutHistory>();
		if ((null != list) && (list.size() > 0)) {
			InOutHistory inOutHistory = new InOutHistory();

			inOutHistory.setId(Integer.valueOf(list.get(0).get("id").toString()));
			try {
				inOutHistory.setCreateUser(getUser(Integer.valueOf(list.get(0).get("create_user").toString())));
			} catch (Exception e) {
				log.info("create_user is null");
			}
			try {
				inOutHistory.setUpdateUser(getUser(Integer.valueOf(list.get(0).get("update_user").toString())));
			} catch (Exception e) {
				log.info("update_user is null");
			}
			try {
				inOutHistory.setProject(getProject(Integer.valueOf(list.get(0).get("project").toString())));

			} catch (Exception e) {
				log.info("project is null");
			}
			try {
				InOutItem inOutItem = getInOutItem(Integer.valueOf(list.get(0).get("item").toString()));
				inOutItem.setDirection(Integer.valueOf(list.get(0).get("type").toString()));
				inOutHistory.setInOutItem(inOutItem);
			} catch (Exception e) {
				log.info("inOutItem is null");
			}

			try {
				inOutHistory.setMoney(Double.valueOf(list.get(0).get("price").toString()));
			} catch (Exception e) {
				log.info("money is null");
			}

			try {
				inOutHistory.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.parse(list.get(0).get("create_time").toString()));
				inOutHistory.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.parse(list.get(0).get("update_time").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inOutHistory.setDesc(list.get(0).get("reason").toString());
			inOutHistory.setMemo(list.get(0).get("memo").toString());

			listInOutHistory.add(inOutHistory);
		}
		return listInOutHistory;
	}

}
