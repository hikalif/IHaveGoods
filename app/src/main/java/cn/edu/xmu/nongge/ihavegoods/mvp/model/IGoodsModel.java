package cn.edu.xmu.nongge.ihavegoods.mvp.model;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Goods;
import cn.edu.xmu.nongge.ihavegoods.entity.Item;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Callback;

/**
 * Created by Yui on 2016/11/7.
 */
public interface IGoodsModel {
    void publishGoods(Callback<HttpResponse> callback, String request);

    void getGoodsList(Callback<HttpResponse<List<Goods>>> callback, String request);

    void getItemList(Callback<HttpResponse<List<Item>>> callback, String request);
}
