package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.SANKTION.SANKTION_Adresse_scan_popUp;
import panter.gmbh.indep.S;

public class FS_ListBtAdressesSanktionPruefung extends MyE2_Button {

	private E2_NavigationList   	oNaviList = null;

	public FS_ListBtAdressesSanktionPruefung(E2_NavigationList  naviList){
		super(S.ms("Adressen auf Sanktionen prüfen"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		this.oNaviList = naviList;
		this._aaa(()-> new SANKTION_Adresse_scan_popUp(oNaviList, "Adressen auf Sanktionen prüfen"));
	}

	
}
