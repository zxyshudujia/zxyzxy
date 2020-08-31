package com.oracle.gdms.util;

import java.util.ResourceBundle;

public class Factory {
	private Factory() { }
	private static Factory fac = null;
	
	public static Factory getInstacance() {
		fac = fac == null ? new Factory() : fac;
		return fac;
	}
	
	private static ResourceBundle rb = null;
	static {
		rb = ResourceBundle.getBundle("config/application");	//��ȡ�����ļ�
	}
	public Object getObject(String key) {
		String clsname = rb.getString(key); //����key�ҵ���ص���·��������
		try {
			return Class.forName(clsname).newInstance();
		} catch (InstantiationException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}
}