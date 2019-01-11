package cn.sky999.common.response;

/**
 * 响应状态码
 * 
 */
public class RespCode {

	/**
	 * 操作成功
	 */
	public final static int SUCCESS = 200;

	/**
	 * 操作失败
	 */
	public final static int FAIL = 500;

	/**
	 * 未登录授权
	 */
	public final static int NO_AUTH = 300;
	
	/**
	 * 无操作权限
	 */
	public final static int NO_PERMISSIONS = 100;

}
