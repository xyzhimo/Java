package com.xyz.request.request;

import com.sun.xml.internal.ws.client.RequestContext;

public interface Action {
	
	BaseResponse execute(RequestContext context);
	
	String getName();
}
