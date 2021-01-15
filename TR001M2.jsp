<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance" %>
<layout:extends name="views/shared/TabBase.Master">
<layout:put block="header" type="REPLACE">

<script type="text/javascript">
require(['jquery'
    , 'domReady'
    , 'TabViewBase'
    , 'Message'
    , 'EnumeratedTypes'
    , 'Utils'
    , 'Form'
    , 'Grid'
    , 'Popup'
    , 'Button'
    , 'Selectbox'
    , 'CommonVariable'
    , 'CommonPopup'
], function($, domReady, viewbase, message, enumDef, utils, form, grid, popup, button, selectbox, userVar,commonPopup){
	
	// object 생성
	var gQueryParams;
	var flexGrid;
	var formTR001M2Search; 
	var formTR001M2;
	
	var gCoCd = userVar.get("coCd"); //회사코드
    var gCoNm = userVar.get("coNm"); //회사명
    
    var path = utils.getContextFullPath();
	
	domReady(function() {
		
		//1. form 생성
		fncCreateFormObject();
		
		//2. 검색조건 셋팅 (모든 회사에게 동일하게 적용되는 검색조건)
		fncInitSearchForm();
		
		//3. form, grid setting(회사별로)
		fncOnLoadView();
		
	});
	
	/** 1. form 생성 **/
	function fncCreateFormObject() {
		
		// 조회조건
		formTR001M2Search = new form('formTR001M2Search');
		
		// 그리드영역
		formTR001M2 = new form('formTR001M2');
		formTR001M2.setBeforeSubmit(fncBeforeSubmit);// submit 전에 실행되는 함수 지정
		formTR001M2.setAfterSubmit(fncAfterSubmit);  // submit 완료후 실행되는 함수 지정
		
	}
	
	/** 1-1. form submit 전에 실행되는 함수 **/
	function fncBeforeSubmit() {
		//formTR001M2.setAction(path+'/module/zm/za/ZA203M1/saveRoleGridList.do');
		formTR001M2.setAction(path+'/module/training/tr/saveTR001M2.do');
		//Step 2.확인 버튼을 클릭한다 - 입력된 내용이 저장된다.
		message.DisplayMessageBox('TX1017', enumDef.MessageBoxButtons.YesNo,' ', function() {
			formTR001M2.submit(true); //true를 넘겨야 beforeSubmit을 skip할수 있음.
		});
	}
	
	/** 1-2. form 저장 후 **/
	function fncAfterSubmit(data) {
		//성공적으로 저장되었습니다. & reload
		message.DisplayMessageBox('CR0804', enumDef.MessageBoxButtons.OK, '', function(data) {
			selectedRow = flexGrid.getSelectedRowIndex();
			flexGrid.query();
		});
	}
	
	/** 2. 검색조건 셋팅 (모든 회사에게 동일하게 적용되는 검색조건) **/
	function fncInitSearchForm() {
		var comboParams  = {};
		var comboOptions = {afterChange: fncAfterChange_coCd};
		
		selectbox.loadSelectBoxRemoteAuto(formTR001M2Search, 'coCdCombo', path + '/module/zm/common/selectCompanyList.do', comboParams, comboOptions);
		
		formTR001M2Search.val("coCdSearch", gCoCd);
		
		formTR001M2Search.val("testNmSearch");
	}
	
	/** 2-1. 회사명 변경 시 자동 조회 **/
    function fncAfterChange_coCd(pSelectedItem) {
		if ( !utils.isEmpty(pSelectedItem) ) {
			gCoCd  = pSelectedItem.id;
			formTR001M2Search.val("coCdSearch", gCoCd);
			fncSearch();
		} else {
			flexGrid.clearData(); //초기화
		}
    }
	
	/** 3. form, grid setting(회사별로) **/
	function fncOnLoadView() {
		// 3-3. 모든  event 추가 (버튼 등..)
        fncAddEvent();
		
		// 3-4. grid 초기화 
		fncInitGrid();
		
		// 3-5. 조회
		fncSearch();
	}
	
	/** 3-3. 모든 event 추가 (버튼 등..) **/
	function fncAddEvent() {
		//조회 버튼 클릭시
		viewbase.on('click','#btnSearch',function(evt) {
            if (formTR001M2Search.isValid()) {
                fncSearch();
            }
        });
		
	}
	
	/** 3-4. grid 초기화 **/
	function fncInitGrid() {
		//flexGrid = new grid('ZA203M1','gridZA203M1');
		flexGrid = new grid('TR001M2','gridTR001M2');
		// wizmo grid option
        var toolbarOtions = {
        		       buttons: ['add', 'clone', 'remove', 'undo'] // or buttons :['all'] <-- 모든 grid standard button을 표시     
	                 , customButton : [{  id   : 'btnSave'
	                	 				, name : '저장'
	                	 				, icon : 'fa-save'
	                	 			  },]
					 , click : {  'add'    : fncRowAdd     // 행추가
						 		, 'clone'  : fncRowClone   // 행복사
	        	   				, 'remove' : fncRowRemove  // 행삭제
	        	   				, 'btnSave': fncSave       // 저장
	        	   			   }
		};
		 
		flexGrid.setToolbar(toolbarOtions);
		
		var params = {
				coCd : formTR001M2Search.val("coCdSearch"),
				useYn: 'Y',
				mnuType: 'M'
		};
		
		flexGrid.setSelectBoxRemote('basicCombo',path+'/module/zm/common/selectLanguageList.do',{})
		flexGrid.setSelectBoxRemote('upperCombo',path+'/module/zm/common/selectMnuComboList.do',params)
			params = {
				coCd: formTR001M2Search.val("coCdSearch"),
				useYn: 'Y',
				mnuType: 'P'

		};
		flexGrid.setSelectBoxRemote('lowerCombo',path+'/module/zm/common/selectMnuComboList.do',params)
		flexGrid.setSelectBoxRemote('basicComboCode',path+'/module/zm/common/selectLanguageList.do',{},'id','text',fncLangCdComboTemplate,'id')
		flexGrid.setSelectBoxFilter('lowerCombo',fncFilterFnc);
		flexGrid.setSelectBoxAutoComplete("customCombo",path + "/module/zm/common/selectMnuComboList.do",{},'text','text',fncLangCdComboTemplate,'text','customCombo');
		//flexGrid.setSelectBoxRemote("multiCombo",path + "module/zm/common/selectLanguageList.do",{})
		
		flexGrid.addEvenHandler('cellEditEnding', fncGridCellEditEnded);
		//flexGrid.setSelectBox("multiCombo",path + "/module/zm/common/selectLanguageList.do",'id','text',null,'text','langNm',{});
		flexGrid.on('click','[data-lang-id="btnSearch"]', function(e,ht) {
			commonPopup.showZipCode(fncOpenSearchZipCode);
		});
		flexGrid.setCellReadOnlyEvent( fncGridEditControl );

		flexGrid.initializeGrid();
	}
	function fncFilterFnc(currentComboData, currentGridRowData){
		return (currentComboData.upperMnuId === currentGridRowData.upperCombo);
	}
	function fncOpenSearchZipCode(objResult){
	      
	      //현재 선택된 row index
	      var selectedRowIndex = flexGrid.getSelectedRowIndex();
	      
	      //setValue(rowIndex, columnName, value)
	      flexGrid.setValue(selectedRowIndex,"zipCode",objResult.zipCd);
	      flexGrid.setValue(selectedRowIndex,"addr1",objResult.address);
	}

	function fncLangCdComboTemplate(){
    	return  '<table>'+
    		'<tr>'+
    			'<td width=100>{id}</td>'+
    			'<td width=200>{text}</td>'+
    		'</tr>'+
    	'</table>';
	}

	/** 3-4-1. 행추가  **/
	function fncRowAdd() {
		var addData = {
				coCd : gCoCd,
				useYn : 0
		};
		
		// 1. 마지막 로우에 추가
		flexGrid.addRow(addData);
	}
	
	/** 3-4-1. 행복사  **/
	function fncRowClone(e) {
		// 3. 현재 row 아래에 clone
  		flexGrid.cloneRowAfterCurrentRow(['userCnt']);
	}
	
	/** 3-4-1. 행삭제  **/
	function fncRowRemove(e) {
		// 1. 선택된 모든 row 삭제
		var addData = {
				coCd : gCoCd
		};
		flexGrid.deleteSelectedRow(addData);
		console.log(flexGrid.getJsonData())
	}
	
	/** 3-4-1. 저장  **/
	function fncSave(e) {
		formTR001M2.submit();
	}
	
	/** 3-4-2. grid event(beginningEdit) **/
	function fncGridEditControl(colIndex, colName, colData, rowIndex, rowData) {
		var tenantId = utils.defaultValue(rowData.tenantId, ''); //tenant Id value
		if(tenantId == 'training'||tenantId == 'training02'){
			if(colName == 'testCd')
				return true
			else
				return false
		}
		
		return false;
		
	}
	
	/** 3-4-3. grid event(cellEditEnded) **/
	function fncGridCellEditEnded(colIndex, colName, colData, rowIndex, rowData, selectedItems) {
		if(colName == "basicComboCode"){
      	     flexGrid.setValue(rowIndex,"basicComboName", selectedItems.text);
      	}
		
	}
	
	/** 3-5. 조회 **/
	function fncSearch() {
		var params = formTR001M2Search.serializeObject(false,true);
    	flexGrid.setParameters(params, false);
		flexGrid.query();
	}
});
</script>
</layout:put>
<layout:put block="MainContent">
 <div class="h1tit-wrap">
   <h1 class="h1tit">
      <div class="tit-fav">
          <input type="checkbox" id="h1tit"/>
          <label for="h1tit">개발자 실습</label>
      </div>
   </h1>
</div>

<form id="formTR001M2Search">
<div class="search-wrap">
  <div class="row">
     <div class="col-md-12 col-lg-11">  
       <div class="row">
			<div class="col-12 col-md-6 col-lg-3">
                <div class="search-group">
                    <label class="col-4 search-tit" data-lang="coCdSearch" for="coCdSearch">회사</label>
					<div class="col-8 search-content">
						<div class="form-group">
							<div id="coCdCombo" data-target-id="coCdSearch">
							</div>
							<input type="text" class="hide" id="coCdSearch" name="coCdSearch"
							       required
							       data-lang="coCdSearchErrorMessage"
							       data-required-error="회사는 필수 입력항목입니다." >
						</div>
					</div>
                </div>
            </div>
             <div class="col-12 col-md-6 col-lg-3">
	     <div class="search-group">
	         <label class="col-4 search-tit" for="testNmSearch" data-lang="testNmSearch">명칭</label>
	         <div class="col-8 search-content">
	             <input type="text" class="form-contr ol" id="testNmSearch" name="testNmSearch">
	         </div>
	     </div>
     </div>
	   </div>
	 </div>
	 <div class="col-md-12 col-lg-1">
         <div class="btn-search-wrap">
             <button class="btn-search" data-lang="btnSearch"><i class="icon fa fa-search"  id="btnSearch"></i>조회</button>
         </div>
	 </div>
	</div>
</div>
</form>

<form id ="formTR001M2">
	<div class="panel mt-4">
		<div class="table-responsive">
             <div id="gridTR001M2" class="table table-bordered table-striped" role="grid"></div>
		</div>
	</div>
</form>

</layout:put>
</layout:extends>