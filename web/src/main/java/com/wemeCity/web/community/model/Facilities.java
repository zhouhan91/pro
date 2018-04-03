package com.wemeCity.web.community.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Facilities实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-16 新建
 */
public class Facilities implements Serializable {

	private static final long serialVersionUID = -8557095106732997125L;

	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (name,VARCHAR(100), null)名字 */
	private String name;

	/** (file_id,BIGINT(20), null)关联上传的图片 */
	private long fileId;

	/** (file_path,VARCHAR(500), null)图片路径 */
	private String filePath;

	/** (busi_code,VARCHAR(100), null)业务编码 */
	private String busiCode;

	/** (busi_id,BIGINT(100), null)业务主键 */
	private long busiId;

	/** (sort_num,INT(11), null)排序字段(升序) */
	private int sortNum;

	/** (is_deleted,VARCHAR(1), null)是否已删除 */
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

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public long getFileId() {
		return this.fileId;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getBusiCode() {
		return this.busiCode;
	}

	public void setBusiId(long busiId) {
		this.busiId = busiId;
	}

	public long getBusiId() {
		return this.busiId;
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

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

}