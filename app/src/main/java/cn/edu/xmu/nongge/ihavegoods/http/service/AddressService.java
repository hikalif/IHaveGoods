package cn.edu.xmu.nongge.ihavegoods.http.service;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Address;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Yui on 2016/11/7.
 */
public interface AddressService {
    @POST("employer/getaddresslist")
    Call<HttpResponse<List<Address>>> getMyAddressList(@Body String request);
}
