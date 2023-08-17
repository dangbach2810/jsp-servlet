package com.bachcoder.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


/*
 * Map param with model
 */
public class FormUtil {
	@SuppressWarnings("unchecked")
	public static <T> T toMapper(Class<T> c, HttpServletRequest req) {
		T object = null;
		try {
			object = c.newInstance();
			BeanUtils.populate(object, req.getParameterMap());
			return object;
		} catch (InstantiationException | InvocationTargetException | IllegalAccessException e ) {
			return null;		
		}
		
		
	}
	
}
