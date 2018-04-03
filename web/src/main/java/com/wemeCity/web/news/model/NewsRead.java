
package com.wemeCity.web.news.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * NewsRead实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
public class NewsRead {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (news_id,BIGINT(20), null)新闻id */
	private long newsId;

	/** (author,VARCHAR(200), null)作者 */
	private String author;

	/** (navigation_code,VARCHAR(50), null)栏目编码 */
	private String navigationCode;

	/** (navigation_name,VARCHAR(50), null)栏目名称 */
	private String navigationName;

	/** (sub_navigation_code,VARCHAR(50), null)子栏目编码 */
	private String subNavigationCode;

	/** (sub_navigation_name,VARCHAR(50), null)子栏目名称 */
	private String subNavigationName;

	/** (key_words,VARCHAR(200), null)关键词 */
	private String keyWords;

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

	public void setUserId(long userId){
		this.userId=userId;
	}

	public long getUserId(){
		return this.userId;
	}

	public void setNewsId(long newsId){
		this.newsId=newsId;
	}

	public long getNewsId(){
		return this.newsId;
	}

	public void setAuthor(String author){
		this.author=author;
	}

	public String getAuthor(){
		return this.author;
	}

	public void setNavigationCode(String navigationCode){
		this.navigationCode=navigationCode;
	}

	public String getNavigationCode(){
		return this.navigationCode;
	}

	public void setNavigationName(String navigationName){
		this.navigationName=navigationName;
	}

	public String getNavigationName(){
		return this.navigationName;
	}

	public void setSubNavigationCode(String subNavigationCode){
		this.subNavigationCode=subNavigationCode;
	}

	public String getSubNavigationCode(){
		return this.subNavigationCode;
	}

	public void setSubNavigationName(String subNavigationName){
		this.subNavigationName=subNavigationName;
	}

	public String getSubNavigationName(){
		return this.subNavigationName;
	}

	public void setKeyWords(String keyWords){
		this.keyWords=keyWords;
	}

	public String getKeyWords(){
		return this.keyWords;
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