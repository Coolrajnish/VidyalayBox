package com.ms.shared.api.generic;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;

@Data
public class GenericDTO implements Serializable {/**
	@JsonRootName(value = "element")
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	
	private Object id;
	
	
	public String printObjectInfo(Object object) {
		StringBuilder stringBuilder = new StringBuilder();
		Method[] methods = object.getClass().getMethods();
		stringBuilder.append(object.getClass().getSimpleName()+" : [ ");
		
		for(Method method : methods) {
			String methodName = method.getName();
			if((methodName.startsWith("get") || methodName.startsWith("is")) && !methodName.equals("getClass")) {
				try {
					String varName = methodName.startsWith("get") ? methodName.replace("get", "") : methodName.replace("is", "");
					stringBuilder.append(varName+" =  "+method.invoke(object)+", ");	
				} catch (Exception e) {
					System.out.println("");
				}
			}
		}
		stringBuilder.append(" ]");
		
		return stringBuilder.toString();
	}

	@Override
	public String toString() {
		return printObjectInfo(this);
	}
}
