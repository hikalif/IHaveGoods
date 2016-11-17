package cn.edu.xmu.nongge.ihavegoods.http;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import com.alibaba.fastjson.JSON;

import cn.edu.xmu.nongge.ihavegoods.utils.DataSecret;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Yui on 2016/11/7.
 */
public class JsonConverterFactory extends Converter.Factory {
    public static JsonConverterFactory create() {
        return new JsonConverterFactory();
    }
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConverter<>();
    }
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseBodyConverter<>(type);
    }
    class FastJsonRequestBodyConverter<T>  implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private final Charset UTF_8 = Charset.forName("UTF-8");
        @Override
        public RequestBody convert(T value) throws IOException {
            String data = value.toString();
            String secretData = "";
            try {
                secretData = DataSecret.encryptDES(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("hello2",secretData);
            return RequestBody.create(MEDIA_TYPE, secretData);
        }
    }
    class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Type type;
        FastJsonResponseBodyConverter(Type type){
            this.type = type;
        }
        @Override
        public T convert(ResponseBody value) throws IOException {
            BufferedSource bufferedSource = Okio.buffer(value.source());
            String tempStr = bufferedSource.readUtf8();
            bufferedSource.close();
            try {
                String data = DataSecret.decryptDES(tempStr);
                return JSON.parseObject(data, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
