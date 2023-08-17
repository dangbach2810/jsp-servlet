package com.bachcoder.utils;

import javax.servlet.http.HttpServletRequest;

/*
 * để tránh trường hợp lảng phí bộ nhớ :
 * + kiểm tra đối tượng có tồn tại hay chưa
 * + nếu tồn tại thì dùng lại nếu chưa thì mới khởi tạo
 * 
 * session dùng để duy trì dl người dùng, xóa khi ng dùng out hệ thống, lấy dl
 * 
 */
public class SessionUtil {
	private static SessionUtil sessionUtil = null;
	
	public static SessionUtil getInstance() {
		if(sessionUtil == null) {
			return sessionUtil = new SessionUtil();
		}
		return sessionUtil;
	}

	public void putValue(HttpServletRequest rq, String key, Object value) {rq.getSession().setAttribute(key, value);}
	
	public void removeValue(HttpServletRequest rq , String key) {rq.getSession().removeAttribute(key);}
	
	public Object getValue(HttpServletRequest rq, String key) {return rq.getSession().getAttribute(key);}
}
