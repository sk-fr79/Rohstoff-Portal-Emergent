/**
 * 
 */
package panter.gmbh.basics4project.objectstore.SingularSettings;

import java.lang.ref.WeakReference;

import panter.gmbh.indep.bibSES;

/**
 * @author martin
 * die enum enthaelt store-codes fuer garantiert in einer session nur einmal vorhandene informationen (z.B. den modulkenner einer
 * Listen-Einstellung. Da jede Listeneinstellung ueber ein modales fenster verfuegt, kann zur gleichen zeit nur genau ein wert hinterlegt
 * werden).
 */
public enum ENUM_SINGULAR_SETTINGS {
	
	;
	
	
	private String s_info = null;
	
	private ENUM_SINGULAR_SETTINGS(String info) {
		this.s_info=info;
	}
	
	@SuppressWarnings("rawtypes")
	public void setToSession(Class clas, Object object) {
		WeakReference<Object>  wr = new WeakReference<Object>(object);
		bibSES.setSessionValue(clas, this, wr);
	}

	@SuppressWarnings("rawtypes")
	public Object readFromSession(Class clas) {
		WeakReference<Object>  wr = bibSES.getSessionValue(clas, this);
		return wr.get();
	}

	public String getInfo() {
		return s_info;
	}
	
}
