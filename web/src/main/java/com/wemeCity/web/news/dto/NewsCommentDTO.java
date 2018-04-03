package com.wemeCity.web.news.dto;

public class NewsCommentDTO {

	/** 用户唯一标识 */
	private String userKey;

	/** 新闻id */
	private long newsId;

	/** 新闻评论内容 */
	private String content;

	/** 父评论id */
	private long parentId;

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

}
