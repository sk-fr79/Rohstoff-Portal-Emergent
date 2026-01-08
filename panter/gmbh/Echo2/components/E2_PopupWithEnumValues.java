/**
 * 
 */
package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
@SuppressWarnings("rawtypes")
public abstract class E2_PopupWithEnumValues extends E2_BasicModuleContainer {

	private VEK<ButtonContainsEnum> buttons = new VEK<ButtonContainsEnum>();
	private MyE2_String             title = S.ms("Bitte wählen");
	private IF_enumForDb[] values = null; 
	
	public abstract XX_ActionAgent   generateAgent(IF_enumForDb val) throws myException;
	
	/**
	 * 
	 * @param p_values
	 * @param p_width
	 * @param p_height
	 * @throws myException
	 */
	public E2_PopupWithEnumValues( IF_enumForDb[] p_values, int p_width, int p_height) throws myException {
		super();
		this.set_oExtWidth( new Extent(p_width));
		this.set_oExtHeight( new Extent(p_height));
		this.values = p_values;
		
		this.set_bVisible_Row_For_Messages(false);   //ohne titelblock
		
		this._renderContent();
	}
	
	/**
	 * 
	 * @param p_values
	 * @param p_width
	 * @throws myException
	 */
	public E2_PopupWithEnumValues( IF_enumForDb[] p_values, int p_width) throws myException {
		super();
		this.set_oExtWidth( new Extent(p_width));
		int iHeight=p_values.length*25+50;
		if (iHeight>900) {
			iHeight=900;
		}
		this.set_oExtHeight( new Extent(iHeight));
		this.values = p_values;
		
		this.set_bVisible_Row_For_Messages(false);   //ohne titelblock
		
		this._renderContent();
	}
	

	public E2_PopupWithEnumValues _renderContent() throws myException {
		
		this.RESET_Content();
		
		E2_Grid g = new E2_Grid()._setSize(this.get_oExtWidth().getValue()-15);
		
		for (IF_enumForDb v: this.values) {
			g._a(new ButtonContainsEnum(v)._width(new Extent(this.get_oExtWidth().getValue()-35)),new RB_gld()._ins(5, 2, 5, 2));
		}
		
		this.add(g, E2_INSETS.I(0,0,0,0));

		return this;
	}
	
	public E2_PopupWithEnumValues _popUp() throws myException {
		this.CREATE_AND_SHOW_POPUPWINDOW(this.title);
		return this;
	}
	
	
	public class ButtonContainsEnum extends E2_Button {
		private IF_enumForDb val = null;
		public ButtonContainsEnum(IF_enumForDb p_val) throws myException {
			super();
			this.val = p_val;
			this._style(E2_Button.StyleTextButton())._tr(this.val.userText());
			this._aaa(E2_PopupWithEnumValues.this.generateAgent(p_val));
			this._aaa(()->{ E2_PopupWithEnumValues.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);});
		}
		public IF_enumForDb getVal() {
			return val;
		}
	}


	public VEK<ButtonContainsEnum> getButtons() {
		return buttons;
	}

	public E2_PopupWithEnumValues _setTitle(MyE2_String p_title) {
		this.title = p_title;
		return this;
	}



	public  E2_PopupWithEnumValues setWidth(int width) throws myException {
		this.set_oExtWidth( new Extent(width));
		this._renderContent();
		return this;
	}


	public  E2_PopupWithEnumValues setHeight(int height) throws myException {
		this.set_oExtHeight( new Extent(height));
		this._renderContent();
		return this;
}
	
}
