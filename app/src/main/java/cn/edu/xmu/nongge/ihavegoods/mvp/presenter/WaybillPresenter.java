package cn.edu.xmu.nongge.ihavegoods.mvp.presenter;

import android.util.Log;

import cn.edu.xmu.nongge.ihavegoods.entity.Quotation;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import cn.edu.xmu.nongge.ihavegoods.mvp.model.IWaybillModel;
import cn.edu.xmu.nongge.ihavegoods.mvp.model.WaybillModel;
import cn.edu.xmu.nongge.ihavegoods.mvp.view.IQuotationDetailPreview;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yui on 2016/11/18.
 */
public class WaybillPresenter {
    private IWaybillModel mWaybillModel;
    private IQuotationDetailPreview mQuotationDetailPreview;

    public WaybillPresenter(IQuotationDetailPreview quotationDetailPreview){
        this.mWaybillModel = new WaybillModel();
        this.mQuotationDetailPreview = quotationDetailPreview;
    }

    public void getQuotationDetail(String request){
        Callback<HttpResponse<Quotation>> callback = new Callback<HttpResponse<Quotation>>() {
            @Override
            public void onResponse(Call<HttpResponse<Quotation>> call, Response<HttpResponse<Quotation>> response) {
                if(response.code() == 200) {
                    Log.i("Http", "成功："+response.code());
                    HttpResponse<Quotation> body = response.body();
                    if(body != null){
                        if(body.getCode().equals("100")){
                            mQuotationDetailPreview.getQuotationDetail(body.getData());
                        }
                    } else{
                        Log.i("Http", "none");
                    }
                } else{
                    Log.i("Http", "错误码："+response.code());
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<Quotation>> call, Throwable t) {
                Log.i("Http", "错误："+t.getMessage());
            }
        };
        mWaybillModel.getQuotationDetail(callback, request);
    }

    public void agreeQuotation(String request){
        Callback<HttpResponse> callback = new Callback<HttpResponse>() {
            @Override
            public void onResponse(Call<HttpResponse> call, Response<HttpResponse> response) {
                if(response.code() == 200) {
                    Log.i("Http", "成功："+response.code());
                    HttpResponse<Quotation> body = response.body();
                    if(body != null){
                        if(body.getCode().equals("100")){
                            mQuotationDetailPreview.agreeQuotation();
                        }
                    } else{
                        Log.i("Http", "none");
                    }
                } else{
                    Log.i("Http", "错误码："+response.code());
                }
            }

            @Override
            public void onFailure(Call<HttpResponse> call, Throwable t) {
                Log.i("Http", "错误："+t.getMessage());
            }
        };
        mWaybillModel.agreeQuotation(callback, request);
    }
}
