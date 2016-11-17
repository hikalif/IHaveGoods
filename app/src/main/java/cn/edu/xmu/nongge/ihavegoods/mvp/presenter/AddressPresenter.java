package cn.edu.xmu.nongge.ihavegoods.mvp.presenter;

import android.util.Log;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Address;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import cn.edu.xmu.nongge.ihavegoods.mvp.model.AddressModel;
import cn.edu.xmu.nongge.ihavegoods.mvp.model.IAddressModel;
import cn.edu.xmu.nongge.ihavegoods.mvp.view.IAddressPreview;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yui on 2016/11/7.
 */
public class AddressPresenter {
    private IAddressModel mAddressModel;
    private IAddressPreview mAddressPreview;

    public AddressPresenter(IAddressPreview addressPreview)
    {
        mAddressPreview = addressPreview;
        mAddressModel = new AddressModel();
    }

    public void getMyAddressList(String request){
        Callback<HttpResponse<List<Address>>> callback = new Callback<HttpResponse<List<Address>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<Address>>> call, Response<HttpResponse<List<Address>>> response) {
                if (response.code() == 200) {
                    Log.i("Http", "成功："+response.code());
                    HttpResponse<List<Address>> body = response.body();
                    if (body != null) {
                        if (body.getCode().equals("100")) {
                            mAddressPreview.getMyAddressList(body.getData());
                        }
                    }
                    else{
                        Log.i("Http", "none");
                    }
                } else {
                    Log.i("Http", "错误码："+response.code());
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<List<Address>>> call, Throwable t) {
                Log.i("Http", "错误："+t.getMessage());
            }
        };
        mAddressModel.getMyAddressList(callback, request);
    }
}
