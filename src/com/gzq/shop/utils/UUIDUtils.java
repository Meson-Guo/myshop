package com.gzq.shop.utils;

import java.util.UUID;

public class UUIDUtils {
/***
 * ��ȡ����ַ��Ĺ�����
 */
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
