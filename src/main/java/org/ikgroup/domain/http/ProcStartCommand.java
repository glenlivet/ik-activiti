package org.ikgroup.domain.http;

import java.util.HashMap;
import java.util.Map;

public class ProcStartCommand {
	
	//通过process instance key来启动
	public static final int START_TYPE_KEY = 0;
	//通过message来启动
	public static final int START_TYPE_MESSAGE = 1;
	
	/**
	 * 启动类型：
	 */
	private int startType = START_TYPE_KEY;
	
	/**
	 * 初始流程参数
	 */
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	private String businessKey;
	

}
