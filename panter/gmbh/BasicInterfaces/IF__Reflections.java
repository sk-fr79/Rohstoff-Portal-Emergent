package panter.gmbh.BasicInterfaces;

import java.lang.reflect.Method;

public interface IF__Reflections {
	
	public default Method CheckMethod(Method[] methods, String methodNameToCheck) {
		for (Method m: methods) {
			if (m.getName().equals(methodNameToCheck)) {
				return m;
			}
		}
		return null;
	}

	public default Method CheckMethod(Object o, String methodNameToCheck) {
		for (Method m: o.getClass().getMethods()) {
			if (m.getName().equals(methodNameToCheck)) {
				return m;
			}
		}
		return null;
	}

}
