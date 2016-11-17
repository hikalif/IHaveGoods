package cn.edu.xmu.nongge.ihavegoods.mvp.model;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Goods;
import cn.edu.xmu.nongge.ihavegoods.entity.Item;
import cn.edu.xmu.nongge.ihavegoods.http.RetrofitUtil;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Yui on 2016/11/7.
 */
public class GoodsModel implements IGoodsModel {

    @Override
    public void publishGoods(Callback<HttpResponse> callback, String request) {
        Call<HttpResponse> call = RetrofitUtil
                .getInstance()
                .getGoodsService()
                .publishGoods(request);
        call.enqueue(callback);
    }

    @Override
    public void getGoodsList(Callback<HttpResponse<List<Goods>>> callback, String request) {
        Call<HttpResponse<List<Goods>>> call = RetrofitUtil
                .getInstance()
                .getGoodsService()
                .getGoodsList(request);
        call.enqueue(callback);
    }

    @Override
    public void getItemList(Callback<HttpResponse<List<Item>>> callback, String request) {
        Call<HttpResponse<List<Item>>> call = RetrofitUtil
                .getInstance()
                .getGoodsService()
                .getItemList(request);
        call.enqueue(callback);
    }

}
