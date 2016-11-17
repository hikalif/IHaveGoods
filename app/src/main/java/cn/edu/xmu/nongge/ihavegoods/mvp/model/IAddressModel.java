package cn.edu.xmu.nongge.ihavegoods.mvp.model;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Address;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Callback;

/**
 * Created by Yui on 2016/11/7.
 */
public interface IAddressModel {
    void getMyAddressList(Callback<HttpResponse<List<Address>>> callback, String request);
}
