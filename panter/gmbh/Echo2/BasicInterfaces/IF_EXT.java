package panter.gmbh.Echo2.BasicInterfaces;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.components.MyE2IF__Component;

@SuppressWarnings("unchecked")
public interface IF_EXT<T>  extends IF__Reflections {
	
	/**
	 * 
	 * @param ext 
	 * @return
	 */
	public default T _colExt(Extent ext) {
		if (this instanceof MyE2IF__Component) {
			((MyE2IF__Component)this).EXT().set_oColExtent(ext);
		}
		return (T)this;
	}


	/**
	 * 
	 * @param ext 
	 * @return
	 */
	public default T _titleLayout(LayoutData layout_ListTitelElement) {
		if (this instanceof MyE2IF__Component) {
			((MyE2IF__Component)this).EXT().set_oLayout_ListTitelElement(layout_ListTitelElement);
		}
		return (T)this;
	}

	/**
	 * 
	 * @param iLeft
	 * @return
	 */
	public default T _titleLayout(int iLeft, int iTop, int iRight, int iBtm, Alignment align, Color background) {
		if (this instanceof MyE2IF__Component) {
			GridLayoutData  gld = new GridLayoutData();
			gld.setInsets(new Insets(iLeft, iTop, iRight, iBtm));
			if (align!=null) {
				gld.setAlignment(align);
			}
			if (background!=null) {
				gld.setBackground(background);
			}
			((MyE2IF__Component)this).EXT().set_oLayout_ListTitelElement(gld);
		}
		return (T)this;
	}


}

	
	

