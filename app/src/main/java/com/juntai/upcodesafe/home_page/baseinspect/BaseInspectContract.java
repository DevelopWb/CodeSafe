package com.juntai.upcodesafe.home_page.baseinspect;

import com.juntai.disabled.basecomponent.mvp.IView;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/18 16:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/18 16:41
 */
public interface BaseInspectContract {



      String PUNISH_INFO = "处罚信息";
      String RECTIFY_NOTICE = "整改信息";

      String  TAB_SUPERVISE_CHECK = "监管检查";
      String  TAB_TERRITORY_SUPERVISE_CHECK = "属地检查";
      String  TAB_GRID_SUPERVISE_CHECK = "网格检查";
      String  TAB_CHECK_SELF = "企业自查";
      String  TAB_ALL = "全部";
      String  TAB_WAIT_FOR_CHECK = "待检查";
      String  TAB_CHECKED = "已检查";
      String  TAB_RECTIFYING = "整改中";
      String  TAB_WAIT_FOR_ACCEPT = "待验收";
      String  TAB_TITLES = "tabtitles";


      String  IS_OK = "是否合格";

      String REMARK = "备注";
      String INSPECTION_SITE = "巡检点";
      String INSPECTION_ADDR = "巡检地址";
      String INSPECTION_RESPONSIBLE = "安全责任人";
      String INSPECTION_RESPONSIBLE_TEL = "安全责任人电话";
      String INSPECTION_TEL = "联系电话";
      String INSPECTION_LEGAL_TEL = "法人电话";
      String INSPECTION_SPARE_PERSON = "备用联系人";
      String INSPECTION_SPARE_PERSON_TEL = "备用联系人电话";
      String CHECK_DES = "检查情况描述";

      String  RESPONSE_LIST = "本单位责任清单";
      String  CHECK_RECORD = "检查记录";
      String  REPAIRE_NOTICE = "整改通知书";
      String  TRAIN_PLAN = "培训计划";
      String  START_CHECK_SELF = "开始自查";
      String  START_CHECK = "开始检查";

      String INSPECTION_UNIT_NAME = "单位名称";
      String INSPECTION_UNIT_AREA = "单位区域";
      String INSPECTION_UNIT_ADDR_DETAIL = "详细地址";
      String INSPECTION_UNIT_LEGAL_PERSON = "单位法人";
      String INSPECTION_UNIT_UCC = "社会信用代码";
      String UNIT_TYPE = "行业类型";
      String UNIT_SIZE = "单位规模";
      String UNIT_RISK = "单位风险";
      String UNIT_DIRECTOR = "安全生产主管";
      String UNIT_SUPERVISE = "安全生产直接监管";
      String UNIT_TERRITORY_SUPERVISE = "属地监管";
      String UNIT_UNIT_SUPERVISE_PEOPLE = "监督(管理)人";
      String BUSINESS_PRODUCTION_DEPARTMENT = "行业安全生产主管部门";
      String BUSINESS_PRODUCTION_DIRECT_DEPARTMENT = "行业安全生产直接监管责任部门";
      String UNIT_GRID_SUPERVISE = "所属网格";




      int  BASE_RECYCLERVIEW_TYPE_TEXT_VALUE = 0;//textvalue。类型
      int  BASE_RECYCLERVIEW_TYPE_RESPONSIBILITY_CONTENT = 1;//责任书内容类型





    interface IInspectView extends IView {}

    interface IInspectPresent {}
}
