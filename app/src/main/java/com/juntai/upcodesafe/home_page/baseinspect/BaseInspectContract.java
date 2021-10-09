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

      String REMARK = "备注";
      String INSPECTION_SITE = "巡检点";
      String INSPECTION_ADDR = "巡检地址";
      String INSPECTION_RESPONSIBLE = "安全责任人";
      String INSPECTION_TEL = "联系电话";
      String INSPECTION_SPARE_PERSON = "备用联系人";
      String INSPECTION_SPARE_PERSON_TEL = "备用联系人电话";

      String  RESPONSE_LIST = "本单位责任清单";
      String  CHECK_RECORD = "检查记录";
      String  REPAIRE_NOTICE = "整改通知书";
      String  TRAIN_PLAN = "培训计划";



    interface IInspectView extends IView {}

    interface IInspectPresent {}
}
