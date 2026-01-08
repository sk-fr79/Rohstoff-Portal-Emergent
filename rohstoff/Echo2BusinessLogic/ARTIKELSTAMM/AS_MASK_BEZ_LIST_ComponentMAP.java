package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class AS_MASK_BEZ_LIST_ComponentMAP extends E2_ComponentMAP
{


	//NEU_09   -- uebergabe von AS_BasicModuleContainerMASK 	ContainingModulContainerMask
	public AS_MASK_BEZ_LIST_ComponentMAP(AS_BasicModuleContainerMASK ContainingModulContainerMask	) throws myException
	{
		super(new AS_MASK_BEZ_LIST_SQLFieldMap());
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

		this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("ANR2"),true,50),new MyE2_String("ANR 2"));
		this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("ARTBEZ1"),true,350),new MyE2_String("Artikelbez.1 (für Belege)"));
		this.add_Component(new MyE2_DB_TextArea(oSQLFM.get_("ARTBEZ2"),300,4),new MyE2_String("Artikelbez.2 (für Belege)"));
		this.add_Component(new MyE2_DB_TextArea(oSQLFM.get_("BEMERKUNG_INTERN")),new MyE2_String("Interne Bemerkung"));
		
		//this.add_Component(AS___CONST.HASHKEY_FULL_DAUGHTER_ARTBEZ_MWST_FIELD,new AS_MASK_BEZ_LIST_CROSS_STEUERSAETZE(oSQLFM),new MyE2_String("MWSt.Sätze"));
		
		//NEU_09
		// aktiv-button wird geschuetzt durch buttonkenner
		MyE2_DB_CheckBox oDBAktiv = new MyE2_DB_CheckBox(oSQLFM.get_("AKTIV"));
		oDBAktiv.add_GlobalValidator(new E2_ButtonAUTHValidator(ContainingModulContainerMask.get_MODUL_IDENTIFIER(),"AKTIVIEREN_ARTIKELBEZ"));
		oDBAktiv.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) {}      // dummy-action, damit der globale validator gezogen wird
		});
		this.add_Component(oDBAktiv,new MyE2_String("Aktiv"));
		
		this.add_Component(new MyE2_DB_Label(oSQLFM.get_("ID_ARTIKEL")),new MyE2_String("ID-Artikel"));
		this.add_Component(new MyE2_DB_Label(oSQLFM.get_("ID_ARTIKEL_BEZ")),new MyE2_String("ID-Art.Bez"));
		//ENDE NEU_09

		
		
		// formatieren
//		((MyE2_DB_TextField)this.get__Comp("ANR2")).set_iWidthPixel(50);
//		((MyE2_DB_TextField)this.get__Comp("ARTBEZ1")).set_iWidthPixel(200);
//		
//		((MyE2_DB_TextArea)this.get__Comp("ARTBEZ2")).set_iWidthPixel(300);
		((MyE2_DB_TextArea)this.get__Comp("ARTBEZ2")).setFont(new E2_FontPlain(-2));
//		((MyE2_DB_TextArea)this.get__Comp("ARTBEZ2")).set_iRows(4);
		
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_INTERN")).set_iWidthPixel(200);
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_INTERN")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextArea)this.get__Comp("BEMERKUNG_INTERN")).set_iRows(5);

		
		this.get__Comp("ID_ARTIKEL").EXT().set_bIsVisibleInList(false);
		//NEU_09
		this.get__Comp("ID_ARTIKEL_BEZ").EXT().set_bIsVisibleInList(false);
		this.get__Comp("BEMERKUNG_INTERN").EXT().set_bIsVisibleInList(false);


	}

}
