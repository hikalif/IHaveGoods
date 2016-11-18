package cn.edu.xmu.nongge.ihavegoods.mvp.model;

import cn.edu.xmu.nongge.ihavegoods.entity.Quotation;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Callback;

/**
 * Created by Yui on 2016/11/18.
 */
public interface IWaybillModel {
    void getQuotationDetail(Callback<HttpResponse<Quotation>> callback, String request);

    void agreeQuotation(Callback<HttpResponse> callback, String request);
}
