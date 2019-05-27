package com.miaosha.result;

public class Result<T> {
    public int code;
    public String msg;
    public T data;

    /**
     * 成功时返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> sucess(T data){
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }

    public Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(CodeMsg cm){
        if(cm == null){
            return ;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
