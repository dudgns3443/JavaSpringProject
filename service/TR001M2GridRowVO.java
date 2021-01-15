package com.bizentro.unierp.module.training.tr.tr001m2.service;

import java.util.List;

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

public class TR001M2GridRowVO extends BaseSaveRowData {
	
	/** grid Save Data List **/
	private List<TR001M2GridListVO> gridTR001M2;

	public List<TR001M2GridListVO> getGridTR001M2() {
		return gridTR001M2;
	}

	public void setGridTR001M2(List<TR001M2GridListVO> gridTR001M2) {
		this.gridTR001M2 = gridTR001M2;
	}



	
}

