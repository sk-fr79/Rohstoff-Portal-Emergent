package rohstoff.Echo2BusinessLogic._4_ALL;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2_Label;

public class TelFaxIcon extends MyE2_Label {

	/**
	 * 
	 * @param bIsTel
	 * @param bIsFax
	 * @param cTextDescript (die eintraege MOBIL und HANDY werden ausgewertet)
	 * @param bMini
	 */
	public TelFaxIcon(boolean bIsTel, boolean bIsFax, String cTextDescript, boolean bMini) {
		super();
		
		if (bMini) {
			if (bIsTel) {
				if (cTextDescript.toUpperCase().indexOf("HANDY")>=0 || cTextDescript.toUpperCase().indexOf("MOBIL")>=0)	{
					this.setIcon(E2_ResourceIcon.get_RI("handy_mini.png"));
				} else {
					this.setIcon(E2_ResourceIcon.get_RI("telefon_mini.png"));
				}
			} else if (bIsFax) {
				this.setIcon(E2_ResourceIcon.get_RI("fax_mini.png"));
			} else {
				this.setText("");
			}
		} else {
			if (bIsTel) {
				if (cTextDescript.toUpperCase().indexOf("HANDY")>=0 || cTextDescript.toUpperCase().indexOf("MOBIL")>=0)	{
					this.setIcon(E2_ResourceIcon.get_RI("handy.png"));
				} else {
					this.setIcon(E2_ResourceIcon.get_RI("telefon.png"));
				}
			} else if (bIsFax) {
				this.setIcon(E2_ResourceIcon.get_RI("fax.png"));
			} else {
				this.setText("");
			}
		}
	}
}
