package com.heimat.luoyer.ui.zhihu.bean;

/**
 * Created by code5 on 2017/4/19.
 */
public class ResultResponse<T> {
    public boolean has_more;
    public String message;
    public T data;

    public ResultResponse(boolean more, String _message, T result) {
        has_more = more;
        message = _message;
        data = result;
    }
}
