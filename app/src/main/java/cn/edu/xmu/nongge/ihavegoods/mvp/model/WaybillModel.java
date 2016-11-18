package cn.edu.xmu.nongge.ihavegoods.mvp.model;

import cn.edu.xmu.nongge.ihavegoods.entity.Quotation;
import cn.edu.xmu.nongge.ihavegoods.http.RetrofitUtil;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Yui on 2016/11/18.
 */
public class WaybillModel implements IWaybillModel {
    @Override
    public void getQuotationDetail(Callback<HttpResponse<Quotation>> callback, String request) {
        Call<HttpResponse<Quotation>> call = RetrofitUtil
                .getInstance()
                .getmWaybillService()
                .getQuotationDetail(request);
        call.enqueue(callback);
    }

    @Override
    public void agreeQuotation(Callback<HttpResponse> callback, String request) {
        Call<HttpResponse> call = RetrofitUtil
                .getInstance()
                .getmWaybillService()
                .agreeQuotation(request);
        call.enqueue(callback);
    }
}
