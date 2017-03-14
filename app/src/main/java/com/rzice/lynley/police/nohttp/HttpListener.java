
package com.rzice.lynley.police.nohttp;


import com.yanzhenjie.nohttp.rest.Response;

/**
 * 接受回调结果
 * </br>
 * Created in Nov 4, 2015 12:54:50 PM
 *
 * @author YOLANDA
 */
public interface HttpListener<T> {

    /**
     * 请求失败
     */
    void onSucceed(int what, Response<T> response);

    /**
     * 请求成功
     */
    void onFailed(int what, String url, Object tag, Exception e, int responseCode, long networkMillis);

}
