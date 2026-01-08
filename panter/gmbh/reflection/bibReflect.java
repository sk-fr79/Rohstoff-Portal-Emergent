package panter.gmbh.reflection;

import panter.gmbh.indep.exceptions.myException;

public class bibReflect {

	public static Object createInstanceClassForName( String classNameWithPath ) throws myException
	{
		Object o;
		try {
			@SuppressWarnings("rawtypes")
			Class c = Class.forName( classNameWithPath );
			if( null == c )
				throw new myException( "Error: Unable to access class '" + classNameWithPath + "'." );
			o = c.newInstance();
			if( null == o )
				throw new myException( "Error: Unable to create instance of class '" + classNameWithPath + "'." );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new myException("bibReflect:createInstanceClassForName: Class:"+classNameWithPath+" was not found ---> "+e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new myException("bibReflect:createInstanceClassForName: Class:"+classNameWithPath+" could not be instantiationed ---> "+e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new myException("bibReflect:createInstanceClassForName: Class:"+classNameWithPath+" Illegal Access ---> "+e.getMessage());
		}
		return o;
	}
}
