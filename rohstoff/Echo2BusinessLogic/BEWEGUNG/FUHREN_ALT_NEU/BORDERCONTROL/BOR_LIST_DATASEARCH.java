
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.vgl_like;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.BOR_CONST.TRANSLATOR;


public class BOR_LIST_DATASEARCH extends E2_DataSearch {

	public BOR_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException {
		super(_TAB.bordercrossing.n(), _TAB.scanner_settings.keyFieldName(), TRANSLATOR.LIST.get_modul().get_callKey());

		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);

		SEL selSearchQuelle = new SEL(BORDERCROSSING.id_bordercrossing).FROM(_TAB.bordercrossing)
									.INNERJOIN(_TAB.land,BORDERCROSSING.id_land_quelle, LAND.id_land)
									.WHERE(new vgl_like(LAND.laendername, "#WERT#"));
		
		SEL selSearchZiel = new SEL(BORDERCROSSING.id_bordercrossing).FROM(_TAB.bordercrossing)
									.INNERJOIN(_TAB.land,BORDERCROSSING.id_land_ziel, LAND.id_land)
									.WHERE(new vgl_like(LAND.laendername, "#WERT#"));
		
		this.addSearchDef(BORDERCROSSING.id_bordercrossing.fn(), "ID", true);
		this.add_SearchElement(selSearchQuelle.s(),new MyE2_String("Startland"));
		this.add_SearchElement(selSearchZiel.s(),new MyE2_String("Zielland"));
		this.addSearchDef(BORDERCROSSING.message.fn(), "Meldung", true);
		this.addSearchDef(BORDERCROSSING.title.fn(), "Titel/Betreff", true);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	private void addSearchDef(String cFieldName, String cInfoText, boolean bNumber) throws myException {

		String cSearch = "";
		if (bNumber) {
			cSearch = "SELECT id_bordercrossing FROM " + bibE2.cTO() + "." + _TAB.bordercrossing.n() + " WHERE TO_CHAR(" + _TAB.bordercrossing.n() + "." + cFieldName + ")='#WERT#'";
		} else {
			cSearch = "SELECT id_bordercrossing FROM " + bibE2.cTO() + "." + _TAB.bordercrossing.n() + " WHERE UPPER(" + _TAB.bordercrossing.n() + "." + cFieldName + ") like upper('%#WERT#%')";
		}

		this.add_SearchElement(cSearch, new MyE2_String(cInfoText));
	}


	
	
}
