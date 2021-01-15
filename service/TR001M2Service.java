/**
	* Lower Test 게시판 Authentication Service 
	* @author 김영훈
	* @since 2021.01.07
	* @version 1.0
	* @see
	*
	* <pre>
	* << Test 게시판 (Service) >>
	*
	* 수정일 수정자 수정내용
	* ------- -------- ---------------------------
	* 2021.01.07 김영훈 최초 생성
	*
	* </pre>
	*/

package com.bizentro.unierp.module.training.tr.tr001m2.service;

import java.util.List;

import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2SearchVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface TR001M2Service {

	/**
	 * 목록 List를 가져온다.
	 *
	 * @param  vo
	 * @return EgovMap
	 * @throws Exception
	 */
	
	public List<EgovMap> selectTR001M2(TR001M2SearchVO vo) throws Exception;

	public void saveTR001M2(TR001M2GridRowVO vo)throws Exception;
	

}
