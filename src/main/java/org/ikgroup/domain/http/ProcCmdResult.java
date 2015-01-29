package org.ikgroup.domain.http;

/**
 * 
 * 
 * @author glenlivet
 *
 */
public class ProcCmdResult {

	/**
	 * 操作 成功失败
	 */
	private boolean success;
	
	/**
	 * 附带信息
	 */
	private String message;
	
	/**
	 * 流程对象
	 */
	private ProcInstRepresentation procInst;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ProcInstRepresentation getProcInst() {
		return procInst;
	}

	public void setProcInst(ProcInstRepresentation procInst) {
		this.procInst = procInst;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
