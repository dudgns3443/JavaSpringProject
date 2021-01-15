package com.bizentro.unierp.module.training.tr.tr001m2.service.impl;

import java.util.List;

import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2GridListVO;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2GridRowVO;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2SearchVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
* Training Mapper
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
* 2019.02.14   younghun    initial
* 
* </pre>
*/
@Mapper
public interface TR001M2Mapper {
	/**
	 * 목록 List를 가져온다.
	 *
	 * @param vo
	 * @return EgovMap
	 * @throws Exception
	 */

	public List<EgovMap> selectTR001M2(TR001M2SearchVO vo) throws Exception;
	
	public int insertTR001M2(TR001M2GridListVO saveGridData);

	public int updateTR001M2(TR001M2GridListVO saveGridData);

	public int deleteTR001M2(TR001M2GridListVO saveGridData);



}

