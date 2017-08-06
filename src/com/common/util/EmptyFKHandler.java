package com.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.base.model.BaseModel;

public class EmptyFKHandler {

	public static void handle(BaseModel model){
		Field[] fields=model.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName=field.getName();
			String fieldGetterName="get"+String.valueOf(fieldName.charAt(0)).toUpperCase()+fieldName.substring(1);
			try {
				Method getter=model.getClass().getMethod(fieldGetterName);
				try {
					Object fieldObj=getter.invoke(model);
					//System.out.println(fieldObj);
					if(fieldObj instanceof BaseModel){
						BaseModel baseModel=(BaseModel)fieldObj;
						if("".equals(baseModel.getId())){
							String fieldSetterName="set"+String.valueOf(fieldName.charAt(0)).toUpperCase()+fieldName.substring(1);
							Method setter=model.getClass().getMethod(fieldSetterName,new Class[]{fieldObj.getClass()});
							setter.invoke(model, new Object[]{null});
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				//e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
}
