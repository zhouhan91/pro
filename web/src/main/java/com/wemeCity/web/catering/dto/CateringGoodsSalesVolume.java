package com.wemeCity.web.catering.dto;

public class CateringGoodsSalesVolume {

	/** 商品id */
	private long goodsId;

	/** 商品名字 */
	private String goodsName;

	/** 销量 */
	private int salesVolume;

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

}
