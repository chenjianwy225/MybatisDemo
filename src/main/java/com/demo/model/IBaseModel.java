package com.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 * 
 * @author chenjian
 * @createDate 2019-01-14
 */
public class IBaseModel implements Serializable {

	private static final long serialVersionUID = -1095810865459213629L;

	/**
	 * 编号(主键)
	 */
	private String keyId;

	/**
	 * 创建日期
	 */
	private Date createDate;

	/**
	 * 更新日期
	 */
	private Date modifyDate;

	/**
	 * 是否删除(0:否,1:是)
	 */
	private Boolean deleteStatus = false;

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
}