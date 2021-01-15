package com.bizentro.unierp.module.training.tr.tr001m2.service;


import org.apache.ibatis.type.Alias;

import com.bizentro.appframework.web.model.base.BaseConditionModel;

/**
* Sample search Value object for FCM module
* @author 
* @since 2021.01.11
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* Changed Date Changed by  Change content
* ------- -------- ---------------------------
* 2021.01.11   younghun    initial
* 
* </pre>
*/
@Alias("TR001M2SearchVO")
public class TR001M2SearchVO extends BaseConditionModel {

	private String coCdSearch;
	private String testNmSearch;
	
	
	public String getCoCdSearch() {
		return coCdSearch;
	}

	public void setCoCdSearch(String coCdSearch) {
		this.coCdSearch = coCdSearch;
	}
	
	public String getTestNmSearch() {
		return testNmSearch;
	}

	public void setTestNmSearch(String testNmSearch) {
		this.testNmSearch = testNmSearch;
	}

	
}
