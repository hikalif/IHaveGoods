package cn.edu.xmu.nongge.ihavegoods.mvp.presenter;

import android.util.Log;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Goods;
import cn.edu.xmu.nongge.ihavegoods.entity.Item;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import cn.edu.xmu.nongge.ihavegoods.mvp.model.GoodsModel;
import cn.edu.xmu.nongge.ihavegoods.mvp.model.IGoodsModel;
import cn.edu.xmu.nongge.ihavegoods.mvp.view.IGoodsPreview;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yui on 2016/11/7.
 */
public class GoodsPresenter {
    private IGoodsModel mGoodsModel;
    private IGoodsPreview mGoodsPreview;

    public GoodsPresenter(IGoodsPreview goodsPreview){
        mGoodsModel = new GoodsModel();
        mGoodsPreview = goodsPreview;
    }

    public void publishGoods(String request){
        Callback<HttpResponse> callback = new Callback<HttpResponse>() {
            @Override
            public void onResponse(Call<HttpResponse> call, Response<HttpResponse> response) {
                if (response.code() == 200) {
                    Log.i("Http", "成功："+response.code());
                    HttpResponse body = response.body();
                    if (body != null) {
                        if (body.getCode().equals("100")) {
                            mGoodsPreview.publishGoods();
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
            public void onFailure(Call<HttpResponse> call, Throwable t) {
                Log.i("Http", "错误："+t.getMessage());
            }
        };
        mGoodsModel.publishGoods(callback, request);
    }

    public void getGoodsList(String request){
        Callback<HttpResponse<List<Goods>>> callback = new Callback<HttpResponse<List<Goods>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<Goods>>> call, Response<HttpResponse<List<Goods>>> response) {
                if (response.code() == 200) {
                    Log.i("Http", "成功："+response.code());
                    HttpResponse<List<Goods>> body = response.body();
                    if (body != null) {
                        if (body.getCode().equals("100")) {
                            mGoodsPreview.getGoodsList(body.getData());
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
            public void onFailure(Call<HttpResponse<List<Goods>>> call, Throwable t) {
                Log.i("Http", "错误："+t.getMessage());
            }
        };
        mGoodsModel.getGoodsList(callback, request);
    }

    public void getItemList(String request){
        Callback<HttpResponse<List<Item>>> callback = new Callback<HttpResponse<List<Item>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<Item>>> call, Response<HttpResponse<List<Item>>> response) {
                if (response.code() == 200) {
                    Log.i("Http", "成功："+response.code());
                    HttpResponse<List<Item>> body = response.body();
                    if (body != null) {
                        if (body.getCode().equals("100")) {
                            mGoodsPreview.getItemList(body.getData());
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
            public void onFailure(Call<HttpResponse<List<Item>>> call, Throwable t) {
                Log.i("Http", "错误："+t.getMessage());
            }
        };
        mGoodsModel.getItemList(callback, request);
    }
}
