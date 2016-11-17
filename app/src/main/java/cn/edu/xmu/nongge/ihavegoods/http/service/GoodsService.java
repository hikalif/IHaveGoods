package cn.edu.xmu.nongge.ihavegoods.http.service;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Goods;
import cn.edu.xmu.nongge.ihavegoods.entity.Item;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Yui on 2016/11/7.
 */
public interface GoodsService {

    @POST("goods/employer/publish_goods")
    Call<HttpResponse> publishGoods(@Body String request);

    @POST("goods/getmygoodslist")
    Call<HttpResponse<List<Goods>>> getGoodsList(@Body String request);

    @POST("goods/employer/getmygoodslist")
    Call<HttpResponse<List<Item>>> getItemList(@Body String request);
}