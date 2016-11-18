package cn.edu.xmu.nongge.ihavegoods.http;


import java.util.concurrent.TimeUnit;

import cn.edu.xmu.nongge.ihavegoods.http.service.AddressService;
import cn.edu.xmu.nongge.ihavegoods.http.service.GoodsService;
import cn.edu.xmu.nongge.ihavegoods.http.service.WaybillService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yui on 2016/11/7.
 */
public class RetrofitUtil {
    //public static final String BASE_URL= "http://172.16.20.23:8080";
    public static final String BASE_URL= "http://172.16.19.183:8080";
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit mRetrofit;

    private GoodsService mGoodsService;
    private AddressService mAddressService;
    private WaybillService mWaybillService;
    /**
     * 私有构造方法
     */
    private RetrofitUtil() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder)
                .addConverterFactory(JsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mGoodsService = mRetrofit.create(GoodsService.class);
        mAddressService = mRetrofit.create(AddressService.class);
        mWaybillService = mRetrofit.create(WaybillService.class);
    }

    //在访问RetrofitUtil时创建单例
    private static class SingletonHolder{
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    //获取单例
    public static RetrofitUtil getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public GoodsService getGoodsService() {
        return mGoodsService;
    }

    public AddressService getMyAddressService() {
        return mAddressService;
    }

    public WaybillService getmWaybillService() { return mWaybillService; }
}
