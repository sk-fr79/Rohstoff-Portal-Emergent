/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components
 * @author manfred
 * @date 22.06.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

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
public class WK_RB_tfSortenHinweis extends RB_TextArea{
	public static RB_KF key = new RB_KF()._setHASHKEY("58c42e20-3057-4dec-b2c1-526a933ab6e9")._setREALNAME(S.ms("Sorteninfo").CTrans());

	/**
	 * @author manfred
	 * @date 22.06.2020
	 *
	 */
	public WK_RB_tfSortenHinweis() {
		this.setForeground(Color.BLACK);
		this.setFont(new E2_FontBold(0));
		this.setBackground(new E2_ColorBase());
		this._bord_dd();
	
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
	public WK_RB_tfSortenHinweis(int i_width, int i_rows) {
		super(i_width, i_rows);
		
		this.setForeground(Color.BLACK);
		this.setFont(new E2_FontBold(0));
		this.setBackground(new E2_ColorBase());
		
		try {
			this.set_bEnabled_For_Edit(false);
		} catch (myException e) {}

	}

	private void setFieldColor() {
		if (S.isFull(this.getText())){
			this.setBackground(Color.RED);
		} else {
			this.setBackground(new E2_ColorBase());
		}
		
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
	public WK_RB_tfSortenHinweis clear() {
		this.setText("");
		setFieldColor();
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
	public WK_RB_tfSortenHinweis setInfoText(String text) {
		this.setText(text);
		setFieldColor();
		return this;
	}
	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_TextArea#set_bEnabled_For_Edit(boolean)
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		super.set_bEnabled_For_Edit(false);
//		this.setBackground(new E2_ColorBase());
		this.setFieldColor();
	}
}
