package com.demo.model.user;

import com.demo.model.IBaseModel;

/**
 * 用户基础信息实体类
 * 
 * @author chenjian
 * @createDate 2019-01-214
 */
public class User extends IBaseModel {

	private static final long serialVersionUID = -3796985923943532262L;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 用户密码
	 */
	private String userPassword;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 真实姓名
	 */
	private String trueName;

	/**
	 * 性别(1:男,2:女,3:保密)
	 */
	private String sex = "3";

	/**
	 * 头像
	 */
	private String photo;

	/**
	 * 是否实名认证(0:否,1:是,2:待审核)
	 */
	private String isReal = "0";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIsReal() {
		return isReal;
	}

	public void setIsReal(String isReal) {
		this.isReal = isReal;
	}
}