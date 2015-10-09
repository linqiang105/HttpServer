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

import com.atw.weight.bean.storage.Express;
import com.atw.weight.bean.storage.Goods;
import com.atw.weight.bean.storage.InOutType;
import com.atw.weight.bean.storage.SendInfo;
import com.atw.weight.dao.IStorageDao;

@Repository("storageDao")
public class StorageDaoImpl implements IStorageDao {

	private static Logger log = LoggerFactory.getLogger(StorageDaoImpl.class);

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public List<InOutType> getInOutTypes() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,name,type from income_expense.dbo.tbl_goods_inout_item");
		List<InOutType> listInOutType = new ArrayList<InOutType>();
		if ((null != list) && (list.size() > 0)) {
			for (int i = 0; i < list.size(); i++) {
				InOutType inOutType = new InOutType();
				inOutType.setId(Integer.valueOf(list.get(i).get("id").toString()));
				inOutType.setName(list.get(i).get("name").toString());
				inOutType.setDirection(Integer.valueOf(list.get(i).get("type").toString()));
				listInOutType.add(inOutType);
			}
		}
		log.info(listInOutType.toString());
		return listInOutType;
	}

	public boolean saveInOutType(InOutType inOutType) {
		// TODO Auto-generated method stub
		String sql = "insert into income_expense.dbo.tbl_goods_inout_item(name,type) values(?,?)";
		return jdbcTemplate.update(sql, new Object[] { inOutType.getName(), inOutType.getDirection() }) > 0 ? true
				: false;
	}

	public Goods getGoodsById(int id) {
		Goods goods = new Goods();

		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select id,name,spec,memo,pid from income_expense.dbo.tbl_goods where id = ?", new Object[] { id });
		if ((null != list) && (list.size() > 0)) {
			goods.setId(Integer.valueOf(list.get(0).get("id").toString()));
			goods.setName(list.get(0).get("name").toString());
			goods.setSpec(list.get(0).get("spec").toString());
			goods.setMemo(list.get(0).get("memo").toString());
			Goods pGoods = list.get(0).get("pid") == null ? null
					: getGoodsById(Integer.valueOf(list.get(0).get("pid").toString()));
			goods.setParentGoods(pGoods);
		}

		return goods;
	}

	public List<Goods> getGoods() {
		// TODO Auto-generated method stub
		List<Goods> listGoods = new ArrayList<Goods>();
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("select id,name,spec,memo,pid from income_expense.dbo.tbl_goods");
		if ((null != list) && (list.size() > 0)) {
			for (int i = 0; i < list.size(); i++) {
				Goods goods = new Goods();
				goods.setId(Integer.valueOf(list.get(i).get("id").toString()));
				goods.setName(list.get(i).get("name").toString());
				goods.setSpec(list.get(i).get("spec").toString());
				goods.setMemo(list.get(i).get("memo").toString());
				Goods pGoods = list.get(i).get("pid") == null ? null
						: getGoodsById(Integer.valueOf(list.get(i).get("pid").toString()));
				goods.setParentGoods(pGoods);
				listGoods.add(goods);

				log.info(goods.toString());
			}
		}
		return listGoods;
	}

	@Override
	public Goods getGoodsByName(String name) {
		Goods goods = new Goods();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select id,name,spec,memo,pid from income_expense.dbo.tbl_goods where name=?", new Object[] { name });
		if ((null != list) && (list.size() > 0)) {
			goods.setId(Integer.valueOf(list.get(0).get("id").toString()));
			goods.setName(list.get(0).get("name") == null ? "" : String.valueOf(list.get(0).get("name")));
			goods.setSpec(list.get(0).get("spec") == null ? "" : String.valueOf(list.get(0).get("spec")));
			goods.setMemo(list.get(0).get("memo") == null ? "" : String.valueOf(list.get(0).get("memo")));
			goods.setParentGoods(getGoodsById(
					list.get(0).get("pid") == null ? 0 : Integer.valueOf((list.get(0).get("pid").toString()))));
		}
		return goods;
	}

	@Override
	public boolean saveGoods(Goods goods) {
		// TODO Auto-generated method stub
		String sql = "insert into income_expense.dbo.tbl_goods(name,spec,memo,pid) values(?,?,?,?)";
		return jdbcTemplate
				.update(sql,
						new Object[] { goods.getName(), goods.getSpec(), goods.getMemo(),
								goods.getParentGoods() == null ? null : goods.getParentGoods().getId() }) > 0 ? true
										: false;
	}

	@Override
	public List<Express> getExpress(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		// 取快递主单
		List<Map<String, Object>> listHead = jdbcTemplate.queryForList(
				"select no,receiver,count(1) as c,tel,addr,min(time) as time from income_expense.dbo.tbl_express "
						+ "where time between ? and ? group by no,receiver,tel,addr",
				new Object[] { startDate, endDate });
		List<Express> listExpress = new ArrayList<Express>();
		if ((null != listHead) && (listHead.size() > 0)) {
			for (int i = 0; i < listHead.size(); i++) {
				Express express = new Express();
				express.setId(i);
				express.setNo(listHead.get(i).get("no").toString());
				express.setReceiver(
						listHead.get(i).get("receiver") == null ? "" : listHead.get(i).get("receiver").toString());
				int count = Integer.valueOf(listHead.get(i).get("c").toString());
				express.setReceiveTel(listHead.get(i).get("tel") == null ? "" : listHead.get(i).get("tel").toString());
				express.setReceiveAddr(
						listHead.get(i).get("addr") == null ? "" : listHead.get(i).get("addr").toString());
				try {
					express.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
							.parse(listHead.get(i).get("time").toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 取快递单明细
				List<SendInfo> listSendInfo = new ArrayList<SendInfo>();
				for (int j = 0; j < count; j++) {
					List<Map<String, Object>> listBody = jdbcTemplate.queryForList(
							"select goods,num from income_expense.dbo.tbl_express where no = ?",
							new Object[] { express.getNo() });
					if ((null != listBody) && (listBody.size() > 0)) {
						for (int k = 0; k < listBody.size(); k++) {
							SendInfo sendInfo = new SendInfo();
							sendInfo.setGoods(getGoodsByName(listBody.get(k).get("goods") == null ? ""
									: listBody.get(k).get("goods").toString()));
							sendInfo.setCount(listBody.get(k).get("num") == null ? 0.0
									: Double.valueOf(listBody.get(k).get("num").toString()));
							listSendInfo.add(sendInfo);
						}
					}
				}
				express.setSendInfo(listSendInfo);
				log.info(express.toString());
				listExpress.add(express);
			}
		}
		return listExpress;
	}

}
