package com.xyz.request.request;

import java.io.Serializable;


public class BaseResponse<T> implements Response<T>, Serializable {

    private static final long serialVersionUID = 8295766534182699773L;

    private T module;
    private int code;
    private String msg;
    private long totalCount = 0;

    public BaseResponse(ResponseCode responseCode, String msg) {
        this.code = responseCode.getCode();
        this.msg = msg;
    }

    public BaseResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getComment();
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 只有当module是集合类型时候才需要将集合大小赋值给totalCount字段
     *
     * @param module
     */
    public BaseResponse(T module) {
        this.module = module;
    	/*if(module != null){
    		if(module instanceof Collection){
    			totalCount = ((Collection)module).size();
    		}else{
    			totalCount = 0;
    		}
    	}*/
        this.code = ResponseCode.RESPONSE_SUCCESS.getCode();
        this.msg = ResponseCode.RESPONSE_SUCCESS.getComment();
    }

    public T getModule() {
        return module;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isSuccess() {
        return ResponseCode.RESPONSE_SUCCESS.getCode() == this.getCode();
    }

    @Override
    public String toString() {
        return "code : " + this.code + " msg: " + this.msg + " totalCount: " + this.totalCount + "module : " + this.module;
    }

    public int getCode() {
        return this.code;
    }
}
