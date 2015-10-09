package com.atw.weight.bean.storage;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.atw.weight.config.JsonDateSerializer;

/**
 * 快递单信息
 * 
 * @author haibara
 *
 */
public class Express {

	private int id;
	private String no;
	private String sender;
	private String sendAddr;
	private String sendTel;
	private String receiver;
	private String receiveAddr;
	private String receiveTel;
	private List<SendInfo> sendInfo;
	private Date updateTime;
	private String memo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendAddr() {
		return sendAddr;
	}

	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}

	public String getSendTel() {
		return sendTel;
	}

	public void setSendTel(String sendTel) {
		this.sendTel = sendTel;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiveAddr() {
		return receiveAddr;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

	public String getReceiveTel() {
		return receiveTel;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}

	public List<SendInfo> getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(List<SendInfo> sendInfo) {
		this.sendInfo = sendInfo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Express() {
	}

	@Override
	public String toString() {
		return "发货信息 [id=" + id + ", 快递单号=" + no + ", 发货人=" + sender + ", 发货地点=" + sendAddr + ", 发货人电话=" + sendTel
				+ ", 收件人=" + receiver + ", 收件地址=" + receiveAddr + ", 收件人电话=" + receiveTel + ", 发货明细=" + sendInfo
				+ ", 发货时间=" + updateTime + ", 备注=" + memo + "]";
	}

}
