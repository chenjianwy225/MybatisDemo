package com.demo.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户详细信息
 * 
 * @author chenjian
 * @createDate 2019-01-14
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -4222362429780390355L;

	/**
	 * 用户编号
	 */
	private String userId;

	/**
	 * 证件类型(1:身份证,2:军官证)
	 */
	private String idType;

	/**
	 * 证件号码
	 */
	private String idNo;

	/**
	 * 个性签名
	 */
	private String idiograph;

	/**
	 * QQ号码
	 */
	private String qqNo;

	/**
	 * 微信号码
	 */
	private String weiXin;

	/**
	 * 微博号码
	 */
	private String weiBo;

	/**
	 * 职业
	 */
	private String occupation;

	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * 星座
	 */
	private String constellation;

	/**
	 * 学位
	 */
	private String degree;

	/**
	 * 毕业学校
	 */
	private String school;

	/**
	 * 公司(单位)名称
	 */
	private String company;

	/**
	 * 住址
	 */
	private String address;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdiograph() {
		return idiograph;
	}

	public void setIdiograph(String idiograph) {
		this.idiograph = idiograph;
	}

	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public String getWeiXin() {
		return weiXin;
	}

	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}

	public String getWeiBo() {
		return weiBo;
	}

	public void setWeiBo(String weiBo) {
		this.weiBo = weiBo;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}