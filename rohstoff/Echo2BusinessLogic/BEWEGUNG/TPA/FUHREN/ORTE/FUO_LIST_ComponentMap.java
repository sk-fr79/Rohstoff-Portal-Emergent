package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_BT_CONNECT_FUHRE_to_WK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_BT_CONNECT_to_FUHRE;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_BT_JUMP_to_FUHRE;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_CONST;

public class FUO_LIST_ComponentMap extends E2_ComponentMAP 
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	public FUO_LIST_ComponentMap(String cEK_VK , FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(new FUO_LIST_SqlFieldMAP(cEK_VK, FUO_DaugherComponent));

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(FUO_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(FUO_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
		
		
		//2017-09-29: verbindung der Wiegekarte zur Fuhre
		if (ENUM_MANDANT_DECISION.USE_BUTTON_CONNECT_WK_FUHRE.is_YES()){
			// Fuhren-IDs
			MyE2_DB_MultiComponentColumn oColWK = new MyE2_DB_MultiComponentColumn();
			oColWK.add_Component(new WK_LIST_BT_CONNECT_FUHRE_to_WK(VPOS_TPA_FUHRE_ORT.fullTabName() ),new MyE2_String("WK"),FUO__CONST.HASH_LIST_BUTTON_SUCHE_WK,LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_5_0_5_0));
			this.add_Component(FUO__CONST.HASH_ROW_LIST_BUTTONS_WK,oColWK, new MyE2_String("WK"));
		}
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR1")), 					new MyE2_String("ANR1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR2")), 					new MyE2_String("ANR2"));
		if (cEK_VK.equals("EK"))
		{
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANTEIL_LADEMENGE")), 	new MyE2_String("Lade-Mg."));
		}
		else
		{
			this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANTEIL_ABLADEMENGE")), new MyE2_String("Ablade-Mg."));
		}
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ1")), 				new MyE2_String("Artikelbezeichnung"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINZELPREIS")),			new MyE2_String("Preis/EH"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("NAME1")), 					new MyE2_String("Name"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ORT")), 					new MyE2_String("Ort"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BUCHUNGSNUMMER_ZUSATZ")), 	new MyE2_String("Zusatz-Nr"));
		
		this.set_oSubQueryAgent(new FUO_LIST_FORMATING_Agent());
	}

	
	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}

}
