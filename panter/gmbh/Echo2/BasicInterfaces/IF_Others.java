package panter.gmbh.Echo2.BasicInterfaces;



import java.lang.reflect.Method;

import panter.gmbh.BasicInterfaces.IF__Reflections;

@SuppressWarnings("unchecked")

public interface IF_Others<T> extends IF__Reflections{
	
	/**
	 * hides 
	 * @param backcolor
	 * @return
	 */
	public default T _setHidden() {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setVisible");

		if (m!=null) {
			try {
				m.invoke(this, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}

	/**
	 * shows (setVisible
	 * @param backcolor
	 * @return
	 */
	public default T _setVisible() {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setVisible");

		if (m!=null) {
			try {
				m.invoke(this, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}

	
}