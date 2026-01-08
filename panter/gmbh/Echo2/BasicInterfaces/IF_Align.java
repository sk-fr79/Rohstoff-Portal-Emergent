package panter.gmbh.Echo2.BasicInterfaces;



import java.lang.reflect.Method;

import nextapp.echo2.app.Alignment;
import panter.gmbh.BasicInterfaces.IF__Reflections;

@SuppressWarnings("unchecked")

public interface IF_Align<T> extends IF__Reflections{

	/**
	 * 
	 * @param align
	 * @return onw class
	 */
	public default T _alignComponent(Alignment align){
		if (align==null) {
			return (T)this;
		}

		Method m =this.CheckMethod(this.getClass().getMethods(), "setAlignment");
		if (m!=null) {
			try {
				m.invoke(this, align);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (T)this;
	}

	/**
	 * align left 
	 * @return onw class
	 */
	public default T _al_left(){
		return this._alignComponent(new Alignment(Alignment.LEFT, Alignment.CENTER));
	}
	
	/**
	 * align center 
	 * @return onw class
	 */
	public default T _al_center(){
		return this._alignComponent(new Alignment(Alignment.CENTER, Alignment.CENTER));
	}

	/**
	 * align right 
	 * @return onw class
	 */
	public default T _al_right(){
		return this._alignComponent(new Alignment(Alignment.RIGHT, Alignment.CENTER));
	}



}