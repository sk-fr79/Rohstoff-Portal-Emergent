
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Search.XX_SearchAddonBedingung;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO.ADI_CONST.TRANSLATOR;





public class ADI_LIST_DATASEARCH extends E2_DataSearch {

	public ADI_LIST_DATASEARCH(E2_NavigationList oNaviList, RECORD_ADRESSE  p_rec_adresse) throws myException {
		super(_TAB.adresse_info.n(), _TAB.scanner_settings.keyFieldName(), TRANSLATOR.LIST.get_modul().get_callKey());

		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);

		this.addSearchDefDate(ADRESSE_INFO.datumeintrag.fn(), 	"Datum Erfassung");
		this.addSearchDefDate(ADRESSE_INFO.datumereignis.fn(), 	"Datum Ereignis");
		this.addSearchDefDate(ADRESSE_INFO.folgedatum.fn(), 	"Folgedatum");
		this.addSearchDef(ADRESSE_INFO.id_adresse_info.fn(), 	"ID Adressinfo", true);
		this.addSearchDef(ADRESSE_INFO.text.fn(), 				"Wortlauf der Information", false);
		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
		/**
		 * dafuer sorgen, dass die suche nur ergebnisse aus dem inneren range findet 
		 */
		this.add_XX_SearchAddonBedingung_to_all_SearchDefinitions(new XX_SearchAddonBedingung() {
			@Override
			public String get_AddOnBedingung() throws myException {
				return "("+new vgl(ADRESSE_INFO.id_adresse, p_rec_adresse.get_ID_ADRESSE_cUF()).s()+" AND NVL("+ADRESSE_INFO.message_typ.tnfn()+",'ALLGEMEIN')='ALLGEMEIN'"+")";
			}
		});
	}

	private void addSearchDef(String cFieldName, String cInfoText, boolean bNumber) throws myException {

		String cSearch = "";
		if (bNumber) {
			cSearch = "SELECT id_adresse_info  FROM " + bibE2.cTO() + "." + _TAB.adresse_info.n() + " WHERE TO_CHAR(" + _TAB.adresse_info.n() + "." + cFieldName + ")='#WERT#'";
		} else {
			cSearch = "SELECT id_adresse_info  FROM " + bibE2.cTO() + "." + _TAB.adresse_info.n() + " WHERE UPPER(" + _TAB.adresse_info.n() + "." + cFieldName + ") like upper('%#WERT#%')";
		}

		this.add_SearchElement(cSearch, new MyE2_String(cInfoText));
	}

	private void addSearchDefDate(String cFieldName, String cInfoText) throws myException {

		String cSearch = "SELECT id_adresse_info  FROM " + bibE2.cTO() + "." + _TAB.adresse_info.n() + " WHERE TO_CHAR(" + _TAB.adresse_info.n() + "." + cFieldName + ",'DD.MM.YYYY')='#WERT#'";
		this.add_SearchElement(cSearch, new MyE2_String(cInfoText));
	}
	
	
	
}
