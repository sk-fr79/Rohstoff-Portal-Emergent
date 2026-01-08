/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components
 * @author manfred
 * @date 22.06.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 22.06.2020
 *
 */
public class WK_RB_tfHinweisAllgemein extends RB_TextArea{
	public static RB_KF key = new RB_KF()._setHASHKEY("8a56c78a-1650-4944-be00-db23b46e63a9")._setREALNAME(S.ms("Allgemeiner Hinweis").CTrans());

	/**
	 * @author manfred
	 * @date 22.06.2020
	 *
	 */
	public WK_RB_tfHinweisAllgemein() {
		this.setForeground(Color.RED);
		this.setFont(new E2_FontBold(0));
		this.setBackground(new E2_ColorBase());
		this.setBorder(new Border(0, new E2_ColorBase(),Border.STYLE_NONE));
//		this._bord_d();
	
		try {
			this.set_bEnabled_For_Edit(false);
		} catch (myException e) {}
		
	}


	/**
	 * @author manfred
	 * @date 22.06.2020
	 *
	 * @param i_width
	 * @param i_rows
	 */
	public WK_RB_tfHinweisAllgemein(int i_width, int i_rows) {
		super(i_width, i_rows);
		
		this.setForeground(Color.RED);
		this.setFont(new E2_FontBold(0));
		this.setBackground(new E2_ColorBase());
		
		try {
			this.set_bEnabled_For_Edit(false);
		} catch (myException e) {}

	}

	
	
	

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return "";
	}

	
	
	/**
	 * Löscht den Hinweistext
	 * @author manfred
	 * @date 22.06.2020
	 *
	 * @return
	 */
	public WK_RB_tfHinweisAllgemein clear() {
		this.setText("");
		return this;
	}
	
	/**
	 * Setzt den Hinweistext
	 * @author manfred
	 * @date 22.06.2020
	 *
	 * @param text
	 * @return
	 */
	public WK_RB_tfHinweisAllgemein setInfoText(String text) {
		this.setText(text);
		return this;
	}
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_TextArea#set_bEnabled_For_Edit(boolean)
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		super.set_bEnabled_For_Edit(false);
		this.setBackground(new E2_ColorBase());
		this.setBorder(new Border(0, new E2_ColorBase(),Border.STYLE_NONE));
	}
}
