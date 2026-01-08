package panter.gmbh.Echo2.BasicInterfaces;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.BasicInterfaces.IF__Reflections;

@SuppressWarnings("unchecked")
public interface IF_Style<T>  extends IF__Reflections {
	
	
	/**
	 * 
	 * @param layout 
	 * @return
	 */
	public default T _set_style(MutableStyle style) {
		if (this instanceof Component && this.CheckMethod(this.getClass().getMethods(),"setStyle")!=null) {
			try {
				this.CheckMethod(this.getClass().getMethods(),"setStyle").invoke(this, style);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return (T)this;
	}

	
	
	
}

	
	

