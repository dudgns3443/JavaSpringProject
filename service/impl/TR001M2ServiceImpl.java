package com.bizentro.unierp.module.training.tr.tr001m2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bizentro.appframework.enums.WebConst;
import com.bizentro.appframework.exception.BCase;
import com.bizentro.appframework.web.services.impl.BaseBLServiceImpl;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2GridListVO;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2GridRowVO;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2SearchVO;
import com.bizentro.unierp.module.training.tr.tr001m2.service.TR001M2Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
* training service implementation TR001M2
* @author 
* @since 2021.01.06
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* Changed Date Changed by  Change content
* ------- -------- ---------------------------
* 2021.01.12   younghun    initial
* 
* </pre>
*/

@Service
public class TR001M2ServiceImpl extends BaseBLServiceImpl implements TR001M2Service {
	
	@Autowired
	private TR001M2Mapper tr001m2Mapper;
	
	public enum Step { master, detail };
	
	/**
	 * 목록 List를 가져온다.
	 *
	 * @param  vo
	 * @return EgovMap
	 * @throws Exception
	 */
	
	@Override
	public List<EgovMap> selectTR001M2(TR001M2SearchVO vo) throws Exception {
		
		return tr001m2Mapper.selectTR001M2(vo);
	}
	
	/**
	 * 목록 정보 추가, 삭제, 수정
	 *
	 * @param  vo
	 * @return int
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void saveTR001M2(TR001M2GridRowVO searchVO) throws Exception {
		
		List<TR001M2GridListVO> saveGridList = null;
		TR001M2GridListVO saveGridData = null;
		Step step = null;
		int resultCnt = 0;
		
		saveGridList = searchVO.getGridTR001M2();
		int iSaveCnt = saveGridList.size();

		try {
			for(int i = 0; i < iSaveCnt; i++) {
				saveGridData = saveGridList.get(i);
				
				saveGridData.setTenantId(searchVO.getTenantId());
				saveGridData.setSpNm(searchVO.getSpNm());
				saveGridData.setIpAddr(searchVO.getIpAddr());
				saveGridData.setClientId(searchVO.getClientId());
				
				switch ( saveGridData.getCudChar().toUpperCase() ) {
				case WebConst.Create :
					
					resultCnt += tr001m2Mapper.insertTR001M2(saveGridData);
					break;
					
				case WebConst.Update :
					resultCnt += tr001m2Mapper.updateTR001M2(saveGridData);
					
					break;

				case WebConst.Delete :
					resultCnt += tr001m2Mapper.deleteTR001M2(saveGridData);
					break;

				default :
					this.processBMessage("900005"); // 필수 입력필드를 입력하세요
					break;

				}

				if ( resultCnt == 0 ) {
					this.processBMessage("990024"); // 입력 자료 저장에 실패했습니다.
				}
			}
		}catch ( DataAccessException e ) {

			egovLogger.error("### DataAccessException : {} ###", e.getMessage());

			BCase bCase = new BCase();

			bCase.ZC00.setMsgCode("990024"); // Insert 문장으로 에러날 경우 처리 메시지
			bCase.ZC02.setMsgCode("999999");
			bCase.ZC03.setMsgCode("999997");

			bCase.ZC00.setMsgCode("990023"); // Insert 문장으로 에러날 경우 처리 메시지
			bCase.ZU02.setMsgCode("999999"); // Update 문장으로 에러날 경우 처리 메시지
			bCase.ZU03.setMsgCode("999997");

			bCase.ZD00.setMsgCode("999999"); // Delete 문장으로 에러날 경우 처리 메시지
			bCase.ZD01.setMsgCode("999997");

			this.processBCase(e, bCase, (step == Step.master ?  WebConst.Update : searchVO.getCudChar().toUpperCase()));
		}
	}
}

