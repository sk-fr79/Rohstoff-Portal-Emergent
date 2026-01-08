package panter.gmbh.Echo2.Container;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.exceptions.myException;

public class E2_BasicContainer extends E2_BasicModuleContainer {

	
	private MyE2_String  titleText = null;

	
	/**
	 * 
	 */
	public E2_BasicContainer() {
		super();
	}

	
	public E2_BasicContainer _setTitle(MyE2_String text) {
		this.titleText=text;
		if (this.get_oWindowPane()!=null) {
			this.get_oWindowPane().set_oTitle(text);
		}
		return this;
	}
	
	
	/**
	 * 
	 * @param text untranslated
	 * @return
	 */
	public E2_BasicContainer _setTitle(String text) {
		this.titleText=new MyE2_String(text,false);
		if (this.get_oWindowPane()!=null) {
			this.get_oWindowPane().set_oTitle(this.titleText);
		}
		return this;
	}
	
	/**
	 * 
	 * @param text translated
	 * @return
	 */
	public E2_BasicContainer _setTitleTr(String text) {
		this.titleText=new MyE2_String(text);
		if (this.get_oWindowPane()!=null) {
			this.get_oWindowPane().set_oTitle(this.titleText);
		}
		return this;
	}
	
	public E2_BasicContainer _setColor4PopupBackground(Color col) {
		if (this.get_oContentPaneAussen()!=null) {this.get_oContentPaneAussen().setBackground(col);}
		if (this.get_oContentPaneInnen()!=null) {this.get_oContentPaneInnen().setBackground(col);}
		if (this.get_oContentPaneOfWindowButtons()!=null) {this.get_oContentPaneOfWindowButtons().setBackground(col);}
		return this;
	}
	
	
	public E2_BasicContainer _doPopup(int iWidth, int iHeight) throws myException {
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(iWidth), new Extent(iHeight), this.titleText==null?new MyE2_String(""):this.titleText);
		return this;
	}


	public E2_BasicContainer _add(Component comp) {
		this.add(comp);
		return this;
	}
	
	
	public E2_BasicContainer _add(Component comp, Insets i) {
		this.add(comp,i);
		return this;
	}
	
	public E2_BasicContainer _add(Component comp, int left, int top, int right, int bottom) {
		this.add(comp,new Insets(left, top, right, bottom));
		return this;
	}
	

	
}
