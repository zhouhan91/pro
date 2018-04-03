
package com.wemeCity.web.catering.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringGoods实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-23 新建
 */
public class CateringGoods {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (restaurant_id,BIGINT(20), null)店铺id */
	private long restaurantId;

	/** (category_id,BIGINT(20), null)商品分类id */
	private long categoryId;

	/** (category_code,VARCHAR(50), null)商品分类编码 */
	private String categoryCode;

	/** (name,VARCHAR(200), null)商品名称 */
	private String name;

	/** (price,DECIMAL(15,4), null)价格 */
	private BigDecimal price;

	/** (discount_price,DECIMAL(15,4), null)折扣价 */
	private BigDecimal discountPrice;

	/** (cover_picture,VARCHAR(500), null)封面图片 */
	private String coverPicture;

	/** (sort_num,INT(11), null)排序号 */
	private int sortNum;

	/** (recommend_flag,VARCHAR(255), null)是否推荐(Y/N) */
	private String recommendFlag;

	/** (recommend_sort_num,INT(11), null)推荐排序号 */
	private int recommendSortNum;

	/** (stock,INT(11), null)库存 */
	private int stock;

	/** (sales_volume,INT(11), null)销量 */
	private int salesVolume;

	/** (month_sales_volume,INT(11), null)最高月销量 */
	private int monthSalesVolume;

	/** (current_month_sales_volume,INT(11), null)本月销量 */
	private int currentMonthSalesVolume;

	/** (status,VARCHAR(255), null)状态(Y：上架，N：未上架) */
	private String status;

	/** (is_deleted,VARCHAR(255), null)是否已删除(Y/N) */
	private String isDeleted;

	/** (create_by,BIGINT(20), null)创建人 */
	private long createBy;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/** (modify_by,BIGINT(20), null)最后修改人 */
	private long modifyBy;

	/** (modify_time,DATETIME, null)最后修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setRestaurantId(long restaurantId){
		this.restaurantId=restaurantId;
	}

	public long getRestaurantId(){
		return this.restaurantId;
	}

	public void setCategoryId(long categoryId){
		this.categoryId=categoryId;
	}

	public long getCategoryId(){
		return this.categoryId;
	}

	public void setCategoryCode(String categoryCode){
		this.categoryCode=categoryCode;
	}

	public String getCategoryCode(){
		return this.categoryCode;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

	public void setPrice(BigDecimal price){
		this.price=price;
	}

	public BigDecimal getPrice(){
		return this.price;
	}

	public void setDiscountPrice(BigDecimal discountPrice){
		this.discountPrice=discountPrice;
	}

	public BigDecimal getDiscountPrice(){
		return this.discountPrice;
	}

	public void setCoverPicture(String coverPicture){
		this.coverPicture=coverPicture;
	}

	public String getCoverPicture(){
		return this.coverPicture;
	}

	public void setSortNum(int sortNum){
		this.sortNum=sortNum;
	}

	public int getSortNum(){
		return this.sortNum;
	}

	public void setRecommendFlag(String recommendFlag){
		this.recommendFlag=recommendFlag;
	}

	public String getRecommendFlag(){
		return this.recommendFlag;
	}

	public void setRecommendSortNum(int recommendSortNum){
		this.recommendSortNum=recommendSortNum;
	}

	public int getRecommendSortNum(){
		return this.recommendSortNum;
	}

	public void setStock(int stock){
		this.stock=stock;
	}

	public int getStock(){
		return this.stock;
	}

	public void setSalesVolume(int salesVolume){
		this.salesVolume=salesVolume;
	}

	public int getSalesVolume(){
		return this.salesVolume;
	}

	public void setMonthSalesVolume(int monthSalesVolume){
		this.monthSalesVolume=monthSalesVolume;
	}

	public int getMonthSalesVolume(){
		return this.monthSalesVolume;
	}

	public void setCurrentMonthSalesVolume(int currentMonthSalesVolume){
		this.currentMonthSalesVolume=currentMonthSalesVolume;
	}

	public int getCurrentMonthSalesVolume(){
		return this.currentMonthSalesVolume;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setIsDeleted(String isDeleted){
		this.isDeleted=isDeleted;
	}

	public String getIsDeleted(){
		return this.isDeleted;
	}

	public void setCreateBy(long createBy){
		this.createBy=createBy;
	}

	public long getCreateBy(){
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime){
		this.createTime=createTime;
	}

	public LocalDateTime getCreateTime(){
		return this.createTime;
	}

	public void setModifyBy(long modifyBy){
		this.modifyBy=modifyBy;
	}

	public long getModifyBy(){
		return this.modifyBy;
	}

	public void setModifyTime(LocalDateTime modifyTime){
		this.modifyTime=modifyTime;
	}

	public LocalDateTime getModifyTime(){
		return this.modifyTime;
	}

}