package com.wemeCity.web.news.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * News实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-19 新建
 */
public class News implements Serializable {

	private static final long serialVersionUID = 3005664749388598405L;

	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (title,VARCHAR(500), null)标题 */
	private String title;

	/** (summary,VARCHAR(2000), null)摘要 */
	private String summary;

	/** (cover_picture,VARCHAR(500), null)封面图片 */
	private String coverPicture;

	/** (author,VARCHAR(200), null)作者/来源 */
	private String author;

	/** (key_words,VARCHAR(200), null)关键词(英文逗号分隔) */
	private String keyWords;

	/** (navigation_code,VARCHAR(50), null)栏目编码 */
	private String navigationCode;

	/** (navigation_name,VARCHAR(50), null)栏目名称 */
	private String navigationName;

	/** (sub_navigation_code,VARCHAR(50), null)子栏目编码 */
	private String subNavigationCode;

	/** (sub_navigation_name,VARCHAR(50), null)子栏目名称 */
	private String subNavigationName;

	/** (read_count,INT(11), null)阅读数 */
	private int readCount;

	/** (like_count,INT(11), null)点赞数 */
	private int likeCount;

	/** (comment_count,INT(11), null)评论数 */
	private int commentCount;

	/** (mark_count,INT(11), null)收藏数 */
	private int markCount;

	/** (status,VARCHAR(255), null)状态(1：新建，2：发布) */
	private String status;

	/** (publish_time,DATETIME, null)发布时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime publishTime;

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

	/** 新闻图文内容 */
	private NewsContent content;

	/** 巴别塔 */
	private List<NewsBabieta> lstNewsBabieta;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public String getCoverPicture() {
		return this.coverPicture;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getKeyWords() {
		return this.keyWords;
	}

	public void setNavigationCode(String navigationCode) {
		this.navigationCode = navigationCode;
	}

	public String getNavigationCode() {
		return this.navigationCode;
	}

	public void setNavigationName(String navigationName) {
		this.navigationName = navigationName;
	}

	public String getNavigationName() {
		return this.navigationName;
	}

	public void setSubNavigationCode(String subNavigationCode) {
		this.subNavigationCode = subNavigationCode;
	}

	public String getSubNavigationCode() {
		return this.subNavigationCode;
	}

	public void setSubNavigationName(String subNavigationName) {
		this.subNavigationName = subNavigationName;
	}

	public String getSubNavigationName() {
		return this.subNavigationName;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getReadCount() {
		return this.readCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getLikeCount() {
		return this.likeCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getCommentCount() {
		return this.commentCount;
	}

	public void setMarkCount(int markCount) {
		this.markCount = markCount;
	}

	public int getMarkCount() {
		return this.markCount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public LocalDateTime getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(LocalDateTime publishTime) {
		this.publishTime = publishTime;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}

	public long getCreateBy() {
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setModifyBy(long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public LocalDateTime getModifyTime() {
		return this.modifyTime;
	}

	public NewsContent getContent() {
		return content;
	}

	public void setContent(NewsContent content) {
		this.content = content;
	}

	public List<NewsBabieta> getLstNewsBabieta() {
		return lstNewsBabieta;
	}

	public void setLstNewsBabieta(List<NewsBabieta> lstNewsBabieta) {
		this.lstNewsBabieta = lstNewsBabieta;
	}

}