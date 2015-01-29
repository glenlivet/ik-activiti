package org.ikgroup.controller;

import org.ikgroup.domain.http.ProcCmdResult;
import org.ikgroup.domain.http.ProcStartCommand;

/**
 * 通用流程命令控制器
 * 
 * @author glenlivet
 *
 */
public interface ICommonProcCommandController {

	/**
	 * 简单无参数启动流程
	 * @return
	 */
	public ProcCmdResult simpleStart();
	
	/**
	 * 多参数启动流程
	 * 
	 * @param procStartCommand 流程启动指令
	 * @return
	 */
	public ProcCmdResult start(ProcStartCommand procStartCommand);
}
