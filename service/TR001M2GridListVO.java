package com.bizentro.unierp.module.training.tr.tr001m2.service;

import org.apache.ibatis.type.Alias;

import com.bizentro.appframework.web.model.base.BaseSaveRowData;
/**
* 개발자 실습 목록 search Value object 
* @author 
* @since 2021.01.14
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* Changed Date Changed by  Change content
* ------- -------- ---------------------------
* 2021.01.14   younghun    initial
* 
* </pre>
*/
@Alias("TR001M2GridListVO")
public class TR001M2GridListVO extends BaseSaveRowData {
	private String testNm;
	private String testCd;
	private String coCd;
	private String addr1;
	private String addr2;
	private String basicCombo;
	private String basicComboCode;
	private String basicDt;
	private String customCombo;
	private String endDt;
	private String floatNum;
	private String intNum;
	private String lowerCombo;
	private String remarkTxt;
	private String startDt;
	private String upperCombo;
	private String useYn;
	private String zipCode;
	private String multiCombo;

	public String getMultiCombo() {
		return multiCombo;
	}
	public void setMultiCombo(String multiCombo) {
		this.multiCombo = multiCombo;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getBasicCombo() {
		return basicCombo;
	}
	public void setBasicCombo(String basicCombo) {
		this.basicCombo = basicCombo;
	}
	public String getBasicComboCode() {
		return basicComboCode;
	}
	public void setBasicComboCode(String basicComboCode) {
		this.basicComboCode = basicComboCode;
	}

	public String getBasicDt() {
		return basicDt;
	}
	public void setBasicDt(String basicDt) {
		this.basicDt = basicDt;
	}
	public String getCustomCombo() {
		return customCombo;
	}
	public void setCustomCombo(String customCombo) {
		this.customCombo = customCombo;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getFloatNum() {
		return floatNum;
	}
	public void setFloatNum(String floatNum) {
		this.floatNum = floatNum;
	}
	public String getIntNum() {
		return intNum;
	}
	public void setIntNum(String intNum) {
		this.intNum = intNum;
	}
	public String getLowerCombo() {
		return lowerCombo;
	}
	public void setLowerCombo(String lowerCombo) {
		this.lowerCombo = lowerCombo;
	}
	public String getRemarkTxt() {
		return remarkTxt;
	}
	public void setRemarkTxt(String remarkTxt) {
		this.remarkTxt = remarkTxt;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getUpperCombo() {
		return upperCombo;
	}
	public void setUpperCombo(String upperCombo) {
		this.upperCombo = upperCombo;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCoCd() {
		return coCd;
	}
	public void setCoCd(String coCd) {
		this.coCd = coCd;
	}
	
	
	public String getTestNm() {
		return testNm;
	}
	public void setTestNm(String testNm) {
		this.testNm = testNm;
	}
	public String getTestCd() {
		return testCd;
	}
	public void setTestCd(String testCd) {
		this.testCd = testCd;
	}

	
}
