package com.demo.mvp_dagger2.network.api;

import com.demo.mvp_dagger2.network.bean.BaseResponse;
import com.demo.mvp_dagger2.network.bean.ProjectBean;
import com.demo.mvp_dagger2.network.bean.ProjectItem;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author :  lwb
 * Date: 2019/10/10
 * Desc:
 */
public interface DemoApi {


    // 注解里传入 网络请求 的部分URL地址
    @GET("project/tree/json")
    // getProject()是接受网络请求数据的方法
    //  RxJava 方式：Observable<..>接口形式
    Observable<ProjectBean> getProject();

    @GET("project/list/{pageIndex}/json")
    Observable<ProjectItem> getProjectItem(@Path("pageIndex") int pageIndex, @Query("cid") int cid);

    @POST("user/register")
    @FormUrlEncoded
    Maybe<BaseResponse> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    @POST("user/register")
    @FormUrlEncoded
    Maybe<BaseResponse> login(@Field("username") String username, @Field("password") String password);

}
