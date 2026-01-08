package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SONDERDEF;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU_SONDER_LIST_ComponentMap extends E2_ComponentMAP 
{

	public FU_SONDER_LIST_ComponentMap() throws myException
	{
		super(new FU_SONDER_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(FU_SONDER_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(FU_SONDER_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_("AUSNAHME")), 					new MyE2_String("Sonderfall"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("KEIN_KONTRAKT_NOETIG")), 		new MyE2_String("Kein VK-Kon"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("OHNE_ABRECHNUNG")), 				new MyE2_String("Keine Mengen-Abrechnung"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("OHNE_AVV_VERTRAG_CHECK")), 		new MyE2_String("Keine AVV-Vertrags-Prüfung"));
				
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_VPOS_TPA_FUHRE_SONDER")), 	new MyE2_String("ID"));

		this.set_oSubQueryAgent(new FU_SONDER_LIST_FORMATING_Agent());
	}

}
