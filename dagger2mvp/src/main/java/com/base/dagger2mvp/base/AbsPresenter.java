package com.base.dagger2mvp.base;

public abstract class AbsPresenter<M extends BaseModel, V extends BaseView> {
    private static final String TAG = AbsPresenter.class.getSimpleName();

    protected M mModel;

    protected V mView;

}
