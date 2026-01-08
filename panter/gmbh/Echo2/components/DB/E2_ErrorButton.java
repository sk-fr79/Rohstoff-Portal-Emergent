/**
 * panter.gmbh.Echo2.components.DB
 * @author martin
 * @date 29.11.2019
 * 
 */
package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 29.11.2019
 *
 */
public class E2_ErrorButton extends E2_Button {


	public E2_ErrorButton(Exception e) {
		
		StackTraceElement[] stack = e.getStackTrace();
		
		VEK<String>  errors = new VEK<>();
		
		for (StackTraceElement te: stack) {
			errors._a(te.getClassName()+" / "+te.getMethodName()+" Line "+te.getLineNumber());
		}
		
		this.init(errors);
	}

	
	public E2_ErrorButton(VEK<String> errors) {
		this.init(errors);	
	}
	

	private void init(VEK<String> errors) {
		this._t(S.ms("Fehler"))._fsa(3);
		this._setShapeStandardTextButtonLeftMidFontNormal(new E2_ColorAlarm(),Color.BLACK);
		
		this._aaa(()-> {
			new OwnPopup(errors);
		});
	
	}
	
	
	
	@Override
	public void setEnabled(boolean newValue) {
		
	}
	
	
	
	private class OwnPopup extends E2_BasicModuleContainer {

		public OwnPopup(VEK<String> messagesToShow) {
			super();
			
			E2_Grid g_aussen = new E2_Grid()._setSize(400)._bo_red();
			g_aussen.setWidth(new Extent(100,Extent.PERCENT));
			
			E2_Grid g_messages = new E2_Grid()._setSize(390);
			g_aussen.setWidth(new Extent(100,Extent.PERCENT));

			g_aussen._a(g_messages, new RB_gld()._ins(5)._col_back_alarm());
			
			
			for (String m : messagesToShow) {
				RB_lab label = new RB_lab()._t(S.NN(m))._lw();
				g_messages._a(label);
			}

			this.add(g_aussen, E2_INSETS.I(5));
			
			try {
				this.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Unerwarteter Fehler !"), 410,500);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
