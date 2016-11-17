package cn.edu.xmu.nongge.ihavegoods.http.response;

/**
 * Created by Yui on 2016/11/7.
 */
public class HttpResponse<T> {
    private String code;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
