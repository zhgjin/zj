package cn.sky999.common.response;

import java.io.Serializable;

/**
 * 返回操作信息
 * 
 */
public class OperInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean ok = true;// 响应成功
	private int code = RespCode.SUCCESS;// 响应状态码
	private String msg = "操作成功";// 响应提示信息
	private Object vo;// 响应数据

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		if (code != RespCode.SUCCESS) {// 状态码不等于操作成功，设ok为失败
			this.ok = false;
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getVo() {
		if (vo == null)
			vo = "";
		return vo;
	}

	public void setVo(Object vo) {
		this.vo = vo;
	}

}
