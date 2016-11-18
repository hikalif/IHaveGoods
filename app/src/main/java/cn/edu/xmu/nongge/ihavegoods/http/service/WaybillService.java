package cn.edu.xmu.nongge.ihavegoods.http.service;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Quotation;
import cn.edu.xmu.nongge.ihavegoods.http.response.HttpResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Yui on 2016/11/18.
 */
public interface WaybillService {
    @POST("waybill/employer/getquotationdetail")
    Call<HttpResponse<Quotation>> getQuotationDetail(@Body String request);

    @POST("waybill/employer/agreequotation")
    Call<HttpResponse> agreeQuotation(@Body String request);
}
