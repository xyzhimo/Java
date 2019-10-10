package com.xyz.request.request;

import com.sun.xml.internal.ws.client.RequestContext;

/**
 * Description: 执行的服务底层接口
 *
 * PackageName: com.sxc.jotunheim.trade.infrastructure.base.request
 * FileName: Action.java
 * Copyright: Copyright (c)2019. songxiaocai
 *
 * @author: xyz
 * @create: 2019-08-08
 */
public interface Action {
	
	BaseResponse execute(RequestContext context);
	
	String getName();
}
