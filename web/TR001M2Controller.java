/**
	* Lower Test 게시판 Authentication Service Implementation
	* @author 이동현
	* @since 2021.01.07
	* @version 1.0
	* @see
	*
	* <pre>
	* << Test 게시판 (Controller) >>
	*
	* 수정일 수정자 수정내용
	* ------- -------- ---------------------------
	* 2021.01.07 이동현 최초 생성
	*
	* </pre>
	*/

package com.bizentro.unierp.module.training.tr.tr001m2.web;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bizentro.appframework.web.controller.BaseUIPage;
import com.bizentro.appframework.web.model.ResultMessage;
import com.bizentro.unierp.module.training.sample.fa0000.service.FA0000GridRowVO;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2GridRowVO;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2SearchVO;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
@RequestMapping(value = "/module/training/tr/")
public class TR001M2Controller extends BaseUIPage {

	@Autowired
	private TR001M2Service tr001m2Service;

	/**
	 * 테스트 페이지 호출
	 * 
	 * @return page
	 * @throws Exception
	 */
	@RequestMapping("TR001M2.do")
	public String loadUnitMgtPopup() throws Exception {

		return "module/training/tr/TR001M2";
		
}
	/**
	 * 목록 조회
	 * 
	 * @return page
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "selectTR001M2.do")
	public ResultMessage selectTR001M2(@RequestBody TR001M2SearchVO vo, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			this.getBLService().processBMessage(bindingResult);
		}

		List<EgovMap> resultlist = tr001m2Service.selectTR001M2(putWhoColumns(vo));
		
		return this.addResult(resultlist);
	}
	
	/**
	 * It's sample method, Please remove it. [Save]
	 * @return ResultMessage
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "saveTR001M2.do")
	public ResultMessage saveTR001M2(@RequestBody TR001M2GridRowVO vo, BindingResult bindingResult) throws Exception {
		
		/* 1. 조회조건 검사 */
		if (bindingResult.hasErrors()) {
			this.getBLService().processBMessage(bindingResult);
		}

		/* 2. 서비스 호출 */
		tr001m2Service.saveTR001M2(putWhoColumns(vo));
		
		/* 3. 결과 전달 */
		return this.addResult(true);

	}


}


