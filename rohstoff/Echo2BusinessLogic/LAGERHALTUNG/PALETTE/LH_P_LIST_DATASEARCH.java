
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE.LH_P_CONST.TRANSLATOR;

public class LH_P_LIST_DATASEARCH extends E2_DataSearch {


	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;


	public LH_P_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
		super(_TAB.lager_palette.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());

		this.m_tpHashMap = p_tpHashMap;        


		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
		this.set_oSearchAgent(oSearchAgent);

		this.addSearchDef(LAGER_PALETTE.bruttomenge.fn(),			LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.bruttomenge),			true);
		this.addSearchDef(LAGER_PALETTE.chargennummer.fn(),			LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.chargennummer),			true);
		this.addSearchDef(LAGER_PALETTE.datum_verarbeitet.fn(),		LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.datum_verarbeitet),		true);
		this.addSearchDef(LAGER_PALETTE.endkontrolle_von.fn(),		LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.endkontrolle_von),		true);
		this.addSearchDef(LAGER_PALETTE.gepresst_von.fn(),			LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.gepresst_von),			true);
		this.addSearchDef(LAGER_PALETTE.id_artikel_bez.fn(),		LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_artikel_bez),			true);
		this.addSearchDef(LAGER_PALETTE.id_lager_box.fn(),			LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_lager_box),			true);
		this.addSearchDef(LAGER_PALETTE.id_lager_palette.fn(),		LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_lager_palette),		true);
		this.addSearchDef(LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fn(),	LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_vpos_tpa_fuhre_aus),	true);
		this.addSearchDef(LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fn(),	LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.id_vpos_tpa_fuhre_ein),	true);
		this.addSearchDef(LAGER_PALETTE.ist_palette.fn(),			LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.ist_palette),			true);
		this.addSearchDef(LAGER_PALETTE.nettomenge.fn(),			LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.nettomenge),				true);
		this.addSearchDef(LAGER_PALETTE.palettiert_von.fn(),		LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.palettiert_von),			true);
		this.addSearchDef(LAGER_PALETTE.taramenge.fn(),				LH_P_READABLE_FIELD_NAME.getReadable(LAGER_PALETTE.taramenge),				true);

//		this.addSearchDef(VPOS_TPA_FUHRE.buchungsnr_fuhre.fn(),		LH_P_READABLE_FIELD_NAME.getReadable(VPOS_TPA_FUHRE.buchungsnr_fuhre),		true);

		this.add_SearchElement(new SEL().ADDFIELD(LAGER_PALETTE.id_lager_palette).FROM(_TAB.lager_palette)
						.LEFTOUTER(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
						.WHERE(new vgl(VPOS_TPA_FUHRE.buchungsnr_fuhre, COMP.LIKE, "%#WERT#%"))
						.OR(new vgl(LAGER_PALETTE.buchungsnr_hand, COMP.LIKE, "%#WERT#%") )
						
						.s(), S.ms(LH_P_READABLE_FIELD_NAME.getReadable(VPOS_TPA_FUHRE.buchungsnr_fuhre)));

		//20180523: datenbank gestuetzte suche zufuegen
		this.initAfterConstruction();
	}


	private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {

		String cSearch = null;
		if (searchWithLike) {
//			if(cFieldName.equals(VPOS_TPA_FUHRE.buchungsnr_fuhre.fn())) {
//				cSearch = 
//						new SEL().ADDFIELD(LAGER_PALETTE.id_lager_palette).FROM(_TAB.lager_palette)
//						.LEFTOUTER(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
//						.WHERE(new vgl(VPOS_TPA_FUHRE.buchungsnr_fuhre, COMP.LIKE, "%#WERT#%"))
//						.OR(new vgl(LAGER_PALETTE.buchungsnr_hand, COMP.LIKE, "%#WERT#%") )
//						
//						.s();
//			}else {
				cSearch = "SELECT id_LAGER_PALETTE  FROM "+bibE2.cTO()+"."+_TAB.lager_palette.n()+" WHERE UPPER(TO_CHAR("+_TAB.lager_palette.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
//			}

		} else {
//			if(cFieldName.equals(VPOS_TPA_FUHRE.buchungsnr_fuhre.fn())) {
//				cSearch = 
//						new SEL().ADDFIELD(LAGER_PALETTE.id_lager_palette).FROM(_TAB.lager_palette)
//						.LEFTOUTER(_TAB.vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
//						.WHERE(new vgl(VPOS_TPA_FUHRE.buchungsnr_fuhre, "%#WERT#%"))
//						.OR(new vgl(LAGER_PALETTE.buchungsnr_hand, "%#WERT#%") )
//						.s();
//			}else {
				cSearch = "SELECT id_LAGER_PALETTE  FROM "+bibE2.cTO()+"."+_TAB.lager_palette.n()+" WHERE UPPER(TO_CHAR("+_TAB.lager_palette.n()+"."+cFieldName+"))=UPPER('#WERT#')";
//			}

		}           

		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


}


