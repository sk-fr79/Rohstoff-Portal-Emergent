package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MZ_MASK_BEZ_LIST_ComponentMAP extends E2_ComponentMAP
{
   
 
	public MZ_MASK_BEZ_LIST_ComponentMAP() throws myException
	{
		super(new MZ_MASK_BEZ_LIST_SQLFieldMap());
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

	    MyDropDownSettings  oDDErlaubteFeldName = new MyDropDownSettings(bibE2.cTO(),"JD_MANDANT_ZUSATZ_FELDNAMEN","FIELDNAME ||' ('||NVL(RELATION_INFO,'')||')'", "ID_MANDANT_ZUSATZ_FELDNAMEN", null, null, true, "FIELDNAME");
		MyE2_DB_SelectField  oSelFieldTyp = new MyE2_DB_SelectField(oSQLFM.get_("ID_MANDANT_ZUSATZ_FELDNAMEN"), oDDErlaubteFeldName.getDD(),false);
	    oSelFieldTyp.add_oActionAgent(new actionFillWithDefaults());
	    oSelFieldTyp.setFont(new E2_FontPlain(-2));
	    oSelFieldTyp.setWidth(new Extent(200));
		
	    this.add_Component(oSelFieldTyp, new MyE2_String("ID"));
	    
		this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("FIELDNAME"),true,280,null, new E2_FontPlain(-2)),new MyE2_String("Feldname"));
		this.add_Component(new MyE2_DB_TextArea(oSQLFM.get_("TEXT_VALUE"),500,6,false,new E2_FontPlain(-2)),new MyE2_String("Textwert"));

		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("YES_NO_VALUE")),new MyE2_String("Ja/Nein"));
		this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("LONG_VALUE"),true,200),new MyE2_String("Ganzzahl-Wert"));
		this.add_Component(new MyE2_DB_TextField(oSQLFM.get_("FLOAT_VALUE"),true,200),new MyE2_String("Fliess-Komma-Wert"));
		
		this.add_Component(new MyE2_DB_Label(oSQLFM.get_("ID_MANDANT")),new MyE2_String("ID-Mandant"));
		this.add_Component(new MyE2_DB_Label(oSQLFM.get_("ID_MANDANT_ZUSATZ")),new MyE2_String("ID-Zusatz"));

		this.get__Comp("FIELDNAME").EXT().set_oColExtent(new Extent(300));
		this.get__Comp("TEXT_VALUE").EXT().set_oColExtent(new Extent(500));
		this.get__Comp("ID_MANDANT_ZUSATZ_FELDNAMEN").EXT().set_oColExtent(new Extent(150));
		
		this.get__Comp("ID_MANDANT").EXT().set_bIsVisibleInList(false);
		this.get__Comp("ID_MANDANT_ZUSATZ").EXT().set_bIsVisibleInList(false);
		this.get__Comp("YES_NO_VALUE").EXT().set_bIsVisibleInList(false);
		this.get__Comp("LONG_VALUE").EXT().set_bIsVisibleInList(false);
		this.get__Comp("FLOAT_VALUE").EXT().set_bIsVisibleInList(false);

		this.get__Comp("FIELDNAME").EXT().set_bDisabledFromBasic(true);
		
		
	}
	
	
	private class actionFillWithDefaults extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			
			MyE2_DB_SelectField 				DDTyp = 						(MyE2_DB_SelectField)oExecInfo.get_MyActionEvent().getSource();
			E2_ComponentMAP                     oMap = DDTyp.EXT().get_oComponentMAP();
			
			RECORD_MANDANT_ZUSATZ_FELDNAMEN  	recZusatzFeldnamen = 			new RECORD_MANDANT_ZUSATZ_FELDNAMEN(bibALL.ReplaceTeilString(DDTyp.get_ActualWert(),".",""));
			
			oMap.get__DBComp("FIELDNAME").set_cActualMaskValue(recZusatzFeldnamen.get_FIELDNAME_cF_NN(""));
			oMap.get__DBComp("TEXT_VALUE").set_cActualMaskValue(recZusatzFeldnamen.get_DEFAULT_TEXT_VALUE_cF_NN(""));
			oMap.get__DBComp("YES_NO_VALUE").set_cActualMaskValue(recZusatzFeldnamen.get_DEFAULT_YES_NO_VALUE_cF_NN(""));
			oMap.get__DBComp("LONG_VALUE").set_cActualMaskValue(recZusatzFeldnamen.get_DEFAULT_LONG_VALUE_cF_NN(""));
			oMap.get__DBComp("FLOAT_VALUE").set_cActualMaskValue(recZusatzFeldnamen.get_DEFAULT_FLOAT_VALUE_cF_NN(""));
			
		}
	}
	

}
