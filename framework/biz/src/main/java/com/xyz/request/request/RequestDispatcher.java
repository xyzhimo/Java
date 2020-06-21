package com.xyz.request.request;

import com.mockuai.promotioncenter.common.constant.ResponseCode;
import com.mockuai.promotioncenter.common.util.JsonUtil;
import com.mockuai.promotioncenter.core.exception.ServerException;
import com.mockuai.promotioncenter.core.filter.FilterChain;
import com.mockuai.promotioncenter.core.filter.FilterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

@Service
public class RequestDispatcher {

	private static final Logger log = LoggerFactory.getLogger();
	/**
	 * 操作容器
	 */
	@Resource
	private ActionHolder actionHolder;

	@Resource
	private AppContext appContext;

	public AppContext getAppContext() {
		return appContext;
	}

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}

	public ActionHolder getActionHolder() {
		return actionHolder;
	}

	public void setActionHolder(ActionHolder actionHolder) {
		this.actionHolder = actionHolder;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseResponse dispatch(ServerRequest req) {
		if(req == null){
			throw new IllegalArgumentException("request is null!");
		}
		// 单例类限流措施
		ActionCall task = new ActionCall(req);
		BaseResponse response = null;
		try {
			response = task.call();
			return response;
		} catch (Throwable e) {
			log.error("call exception", e);
		}
		return response;
	}

	/**
	 * 取得业务code,如果只传了业务ID，也转下code,同时放到attribute里
	 * @param
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getAppCode(RequestContext context, ServerRequest req) {
		//TODO impl
		String appCode = null;
		return appCode;
	}
	
	private boolean allowAccess(String bizCode, Action action) {
		//TODO
		return true;
	}

	/**
	 * <pre>
	 * desc: action处理call 
	 * created: Oct 25, 2013 11:25:10 AM
	 * author: xiangfeng
	 * todo: 
	 * history:
	 * </pre>
	 */
	class ActionCall implements Callable<BaseResponse> {

		private ServerRequest req;

		public ActionCall(ServerRequest req) {
			super();
			this.req = req;
		}

		@Override
		public BaseResponse call() {

			// 查找具体的请求操作类型
			Action action = actionHolder.getAction(req.getCommand());
			if (null != action) {
				RequestContext context = new RequestContext(appContext, req);
				// set request here
				FilterManager filterManager = FilterManager.getInstance();
				BaseResponse re = new BaseResponse(true);
				//获取appCode
				String appCode = getAppCode(context, req);
				if (!allowAccess(appCode, action)) {
					re = new BaseResponse(ResponseCode.BIZ_E_REQUEST_FORBIDDEN);
					return re;
				}

				/**
				 * 以下时间变量用来统计整个执行过程中的filter.before,action以及filter.after的耗时
				 */
				long startTime = System.currentTimeMillis();
				long beforeFilterEndTime = 0L;
				long actionEndTime = 0L;
				long afterFilterEndTime = 0L;
				BaseResponse res = null;
				try {
					// FIXME pass the correct appCode
					FilterChain filterChain = filterManager.getFilterChain(appCode, action.getName());
					
					//1. 执行filter.before流程
					boolean beforeFilterResult = filterChain.before(context);
					beforeFilterEndTime = System.currentTimeMillis();
					
					
					//2. 如果filter.before流程成功，则执行action，否则不执行
					if (beforeFilterResult == true) {
						// 执行操作
						res = action.execute(context);
						context.setResponse(res);
					}else{
						res = context.getResponse();
					}
					actionEndTime = System.currentTimeMillis();

					return res;
				} catch (ServerException e) {
					res = new BaseResponse(e.getResponseCode(), e.getMessage());
					log.error("do action:" + req.getCommand() + " occur Exception:", e);
					return res;
				} catch (Exception e) {
					res = new BaseResponse(ResponseCode.SYS_E_SERVICE_EXCEPTION);
					log.error("do action:" + req.getCommand() + " occur Exception:", e);
					return res;
				}finally{
					log.info("result:"+res.getCode()+",filterBeforeCost:"+(beforeFilterEndTime-startTime)+
							",actionCost:"+(actionEndTime-beforeFilterEndTime)+",filterAfterCost:"+(afterFilterEndTime-actionEndTime));
					log.info("request:{}", JsonUtil.toJson(req));
					log.info("response:{}", JsonUtil.toJson(res));
				}
			} else {
				// 系统未建立相应的请求操作
				log.error("no action mapping for:" + req.getCommand());
				BaseResponse res = new BaseResponse(ResponseCode.PARAM_E_ACTION_NOT_EXIST);
				return res;
			}
		}

	}
}
