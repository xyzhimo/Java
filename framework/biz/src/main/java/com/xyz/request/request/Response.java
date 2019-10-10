package com.xyz.request.request;

import java.io.Serializable;


public interface Response<T> extends Serializable {
	T getData();
	
	int getCode();

	String getSubCode();
	
	String getMsg();

	boolean isSuccess();
}
