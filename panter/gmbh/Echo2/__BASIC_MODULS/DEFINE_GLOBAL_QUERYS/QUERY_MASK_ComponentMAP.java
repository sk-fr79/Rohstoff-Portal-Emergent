package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public QUERY_MASK_ComponentMAP() throws myException
	{
		super(new QUERY_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
		String[] cFieldsStandard = {"ANZEIGESQL","BESCHREIB1","BESCHREIB2","BESCHREIB3","BESCHREIB4","DOWNLOAD","NAME","PARAM01","PARAM02","PARAM03","PARAM04","PARAM05","PARAM06",
									"PARAMDROPDOWNDEF01","PARAMDROPDOWNDEF02","PARAMDROPDOWNDEF03","PARAMDROPDOWNDEF04","PARAMDROPDOWNDEF05","PARAMDROPDOWNDEF06","DEFAULT01",
									"DEFAULT02","DEFAULT03","DEFAULT04","DEFAULT05","DEFAULT06","SQLFELDLISTE","SQLFROMBLOCK","SQLINDEXFELD","SQLORDERBLOCK","SQLWHEREBLOCK",
									"LISTE_TITEL"}; 
		String[] cFieldsInfolabs = {"ANZEIGESQL","BESCHREIB1","BESCHREIB2","BESCHREIB3","BESCHREIB4","DOWNLOAD","NAME","PARAM01","PARAM02","PARAM03","PARAM04","PARAM05","PARAM06",
									"PARAMDROPDOWNDEF01","PARAMDROPDOWNDEF02","PARAMDROPDOWNDEF03","PARAMDROPDOWNDEF04","PARAMDROPDOWNDEF05","PARAMDROPDOWNDEF06","DEFAULT01",
									"DEFAULT02","DEFAULT03","DEFAULT04","DEFAULT05","DEFAULT06","SQLFELDLISTE","SQLFROMBLOCK","SQLINDEXFELD","SQLORDERBLOCK","SQLWHEREBLOCK",
									"LISTE_TITEL"}; 

		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 700);

		((MyE2_DB_TextArea)this.get__Comp("SQLFELDLISTE")).set_iWidthPixel(700);
		((MyE2_DB_TextArea)this.get__Comp("SQLFROMBLOCK")).set_iWidthPixel(700);
		((MyE2_DB_TextArea)this.get__Comp("SQLWHEREBLOCK")).set_iWidthPixel(700);
		((MyE2_DB_TextArea)this.get__Comp("SQLORDERBLOCK")).set_iWidthPixel(700);
		((MyE2_DB_TextArea)this.get__Comp("LISTE_TITEL")).set_iWidthPixel(700);
		
		((MyE2_DB_TextArea)this.get__Comp("SQLFROMBLOCK")).set_iRows(5);
		((MyE2_DB_TextArea)this.get__Comp("SQLWHEREBLOCK")).set_iRows(5);
		((MyE2_DB_TextArea)this.get__Comp("SQLORDERBLOCK")).set_iRows(5);
		((MyE2_DB_TextArea)this.get__Comp("LISTE_TITEL")).set_iRows(3);

		E2_FontPlain oFont = new E2_FontPlain(-2);
		((MyE2_DB_TextArea)this.get__Comp("SQLFELDLISTE")).setFont(oFont);
		((MyE2_DB_TextArea)this.get__Comp("SQLFROMBLOCK")).setFont(oFont);
		((MyE2_DB_TextArea)this.get__Comp("SQLWHEREBLOCK")).setFont(oFont);
		((MyE2_DB_TextArea)this.get__Comp("SQLORDERBLOCK")).setFont(oFont);


		//die areas fuer die defaults definieren
		for (int i=1;i<=6;i++)
		{
			((MyE2_DB_TextArea)this.get__Comp("PARAMDROPDOWNDEF0"+i)).set_iWidthPixel(400);
			((MyE2_DB_TextArea)this.get__Comp("PARAMDROPDOWNDEF0"+i)).set_iRows(4);
			((MyE2_DB_TextArea)this.get__Comp("PARAMDROPDOWNDEF0"+i)).setFont(oFont);
			((MyE2_DB_TextField)this.get__Comp("PARAM0"+i)).set_iWidthPixel(250);
			((MyE2_DB_TextField)this.get__Comp("DEFAULT0"+i)).set_iWidthPixel(250);
		}
		
		
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_QUERY")), new MyE2_String("ID"));
		this.add_Component(QUERY_LIST_BasicModuleContainer.NAME_OF_MASK_TEILNEHMER, new QUERY_MASK_CROSS_TEILNEHMER(oFM),new MyE2_String("Benutzer für diese Query"));
		
		this.set_oMAPSettingAgent(new QUERY_MASK_MapSettingAgent());
		this.add_oMAPValidator(new QUERY_MASK_MAPVALIDATOR());
		
	}
	
}
