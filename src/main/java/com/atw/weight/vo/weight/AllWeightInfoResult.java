package com.atw.weight.vo.weight;

import com.atw.weight.bean.weight.WeightImg;
import com.atw.weight.bean.weight.WeightInfo;

public class AllWeightInfoResult {

	private WeightInfo weightInfo;
	private WeightImg weightImg;
	private int status;
	private String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WeightInfo getWeightInfo() {
		return weightInfo;
	}

	public void setWeightInfo(WeightInfo weightInfo) {
		this.weightInfo = weightInfo;
	}

	public WeightImg getWeightImg() {
		return weightImg;
	}

	public void setWeightImg(WeightImg weightImg) {
		this.weightImg = weightImg;
	}

}
