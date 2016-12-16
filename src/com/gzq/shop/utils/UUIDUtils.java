package com.gzq.shop.utils;

import java.util.UUID;

public class UUIDUtils {
/***
 * 获取随机字符的工具类
 */
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
