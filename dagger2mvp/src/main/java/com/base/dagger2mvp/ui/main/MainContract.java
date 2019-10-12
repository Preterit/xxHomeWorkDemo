package com.base.dagger2mvp.ui.main;

import com.base.dagger2mvp.base.BaseModel;
import com.base.dagger2mvp.base.BasePresenter;
import com.base.dagger2mvp.base.BaseView;
import com.base.dagger2mvp.ui.main.bean.MainData;

import io.reactivex.Observable;

public interface MainContract {


    interface Presenter extends BasePresenter {

        void getData();
    }


    interface View extends BaseView<Presenter> {
        /**
         * 注册成功
         *
         * @param result
         */
        void getDataSuccess(MainData result);
    }

    /**
     * 对于Model层也是数据层。它区别于MVC架构中的Model，在这里不仅仅只是数据模型。
     * 在MVP架构中Model它负责对数据的存取操作，例如对数据库的读写，网络的数据的请求等
     */
    interface Model extends BaseModel {
        String getMsg();
        boolean isSucess();
        Observable<MainData> getData();
    }
}
