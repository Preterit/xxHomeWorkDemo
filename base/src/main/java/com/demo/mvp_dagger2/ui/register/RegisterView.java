package com.demo.mvp_dagger2.ui.register;

import com.demo.mvp_dagger2.base.BaseView;
import com.demo.mvp_dagger2.network.bean.ProjectBean;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
public interface RegisterView extends BaseView {

    void showData(ProjectBean bean);

}
