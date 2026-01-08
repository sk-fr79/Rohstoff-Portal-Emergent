
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.LH_CONST.TRANSLATOR;

public class LH_LIST_DATASEARCH extends E2_DataSearch {


	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;


	public LH_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
		super(_TAB.lager_box.n(),_TAB.lager_box.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());

		this.m_tpHashMap = p_tpHashMap;        


		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
		this.set_oSearchAgent(oSearchAgent);

		this.addSearchDef(LAGER_BOX.id_lager_box.fn(),	LH_READABLE_FIELD_NAME.getReadable(LAGER_BOX.id_lager_box),	true);
		this.addSearchDef(LAGER_BOX.boxnummer.fn(),		LH_READABLE_FIELD_NAME.getReadable(LAGER_BOX.boxnummer)	,	true);
		this.addSearchDef(LAGER_BOX.beschreibung.fn(),	LH_READABLE_FIELD_NAME.getReadable(LAGER_BOX.beschreibung),	true);

		this.add_SearchElement(
				new SEL().ADDFIELD("id_lager_box").FROM(_TAB.lager_palette)
				.LEFTOUTER(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
				.WHERE("JT_VPOS_TPA_FUHRE.id_vpos_tpa_fuhre", COMP.LIKE.ausdruck(), "UPPER('%#WERT#%')").s(), S.ms("Fuhre id"));

		this.add_SearchElement( new SEL().ADDFIELD("id_lager_box").FROM(_TAB.lager_palette)
				.LEFTOUTER(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
				.WHERE("UPPER(JT_VPOS_TPA_FUHRE.buchungsnr_fuhre)", COMP.LIKE.ausdruck(), "UPPER('%#WERT#%')")
				.OR("UPPER(JT_LAGER_PALETTE.buchungsnr_hand)", COMP.LIKE.ausdruck(), "UPPER('%#WERT#%')" )
				.s(), S.ms("Buchungsnr."));

		//20180523: datenbank gestuetzte suche zufuegen
		this.initAfterConstruction();
	}


	private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {

		String cSearch = null;
		if (searchWithLike) {
			cSearch = "SELECT id_LAGER_BOX  FROM "+_TAB.lager_box.n()+" WHERE UPPER(TO_CHAR("+_TAB.lager_box.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
		} else {
			cSearch = "SELECT id_LAGER_BOX  FROM "+_TAB.lager_box.n()+" WHERE UPPER(TO_CHAR("+_TAB.lager_box.n()+"."+cFieldName+"))=UPPER('#WERT#')";
		}           

		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


}


