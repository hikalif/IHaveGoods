package cn.edu.xmu.nongge.ihavegoods.mvp.model;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Address;
import cn.edu.xmu.nongge.ihavegoods.http.RetrofitUtil;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by Yui on 2016/11/7.
 */
public class AddressModel implements IAddressModel {
    @Override
    public void getMyAddressList(Callback<HttpResponse<List<Address>>> callback, String request) {
        Call<HttpResponse<List<Address>>> call = RetrofitUtil
                .getInstance()
                .getMyAddressService()
                .getMyAddressList(request);
        call.enqueue(callback);
    }
}
