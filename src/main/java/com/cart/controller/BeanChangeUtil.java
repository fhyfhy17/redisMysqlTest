package com.cart.controller;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class BeanChangeUtil<T>
{
 
	@SuppressWarnings({"rawtypes"})
	public static <T> String contrastObj(T pojo1,T pojo2)
	{
		StringBuilder str=new StringBuilder();
		
		try
		{
			// 通过反射获取类的类类型及字段属性
			Class clazz=pojo1.getClass();
            Field[] fields=getAllField(clazz);
            int i=1;
			for(Field field : fields)
			{
				// 排除序列化属性
				if("serialVersionUID".equals(field.getName()))
				{
					continue;
				}
				
				
				PropertyDescriptor pd=new PropertyDescriptor(field.getName(),clazz);
				// 获取对应属性值
				Method getMethod=pd.getReadMethod();
				Object o1=getMethod.invoke(pojo1);
                Object o2=getMethod.invoke(pojo2);
                if(o1==null || o2==null)
				{
					continue;
				}
				if(!o1.toString().equals(o2.toString()))
				{
					str.append(i).append("、字段名称:").append(field.getName()).append(",旧值:").append(o1).append(",新值:").append(o2).append(";");
					i++;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return str.toString();
	}
	
	public static Field[] getAllField(Class<?> clazz)
	{
		ArrayList<Field> fieldList=new ArrayList<>();
		Field[] dFields=clazz.getDeclaredFields();
		if(dFields.length>0)
		{
			fieldList.addAll(Arrays.asList(dFields));
		}
		
		Class<?> superClass=clazz.getSuperclass();
		if(superClass!=Object.class)
		{
			Field[] superFields=getAllField(superClass);
			if(superFields.length>0)
			{
				for(Field field : superFields)
				{
					if(!isContain(fieldList,field))
					{
						fieldList.add(field);
					}
				}
			}
		}
		Field[] result=new Field[fieldList.size()];
		fieldList.toArray(result);
		return result;
	}
	
	private static boolean isContain(ArrayList<Field> fieldList,Field field)
	{
		for(Field temp : fieldList)
		{
			if(temp.getName().equals(field.getName()))
			{
				return true;
			}
		}
		return false;
	}
}