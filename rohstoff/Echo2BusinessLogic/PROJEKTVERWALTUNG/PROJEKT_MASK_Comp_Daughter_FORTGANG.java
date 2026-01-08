package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_MASK_Comp_Daughter_FORTGANG extends MyE2_DBC_DaughterTable 
{

	public PROJEKT_MASK_Comp_Daughter_FORTGANG(SQLFieldMAP fieldMAPMotherTable, E2_ComponentMAP oComponentMapMother) throws myException
	{
		super();
		
				
		SQLFieldMAP oSQLFieldMap = new SQLFieldMAP("JT_PROJEKTINFO",bibE2.get_CurrSession());
		oSQLFieldMap.addCompleteTable_FIELDLIST("JT_PROJEKTINFO",":ID_PROJEKTINFO:ID_PROJEKT:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMap.add_SQLField(new SQLFieldForPrimaryKey("JT_PROJEKTINFO","ID_PROJEKTINFO","ID_PROJEKTINFO",new MyE2_String("ID-Projekt-Adressen"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_PROJEKTINFO.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMap.add_SQLField(new SQLFieldJoinOutside("JT_PROJEKTINFO","ID_PROJEKT","ID_PROJEKT",
											new MyE2_String("ID-Projekt"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_PROJEKT")), false);

		oSQLFieldMap.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED());
		oSQLFieldMap.get_("INFO_DATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		
		oSQLFieldMap.get_("ID_USER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMap.get_("INFO_TEXT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMap.get_("INFO_DATUM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		oSQLFieldMap.initFields();
		

		
		E2_ComponentMAP 			oMapInfos = 		new E2_ComponentMAP(oSQLFieldMap);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		oMapInfos.set_oMAPSettingAgent(new ownMapSettingAgent());
		
		oMapInfos.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapInfos.add_Component(new MyE2_DB_TextField(oSQLFieldMap.get_("INFO_DATUM")), new MyE2_String("Datum"));
		oMapInfos.add_Component(new MyE2_DB_TextArea(oSQLFieldMap.get_("INFO_TEXT")), new MyE2_String("Info Forgang"));
		oMapInfos.add_Component(new DB_Component_USER_DROPDOWN(oSQLFieldMap.get_("ID_USER")), new MyE2_String("Projektmitarbeiter"));
	
		((MyE2_DB_TextArea)oMapInfos.get__Comp("INFO_TEXT")).setFont(new E2_FontPlain(-2));
		((MyE2_DB_TextArea)oMapInfos.get__Comp("INFO_TEXT")).set_iWidthPixel(500);
		((MyE2_DB_TextArea)oMapInfos.get__Comp("INFO_TEXT")).set_iRows(7);
		
		
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, true));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_oContainerExScrollHeight(new Extent(400));
		
		
		this.INIT_DAUGHTER(	fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							oComponentMapMother,
							oMapInfos,
							null);

	}

	/*
	 * subqery-agent, der dafuer sorgt, dass beim editieren nur eigene eintraege geandert werden duerfen 
	 */
	private class ownMapSettingAgent extends XX_MAP_SettingAgent
	{
		public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
		{
		}

		public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
		{
			// zuerst resett
			oMap.get__Comp("ID_USER").EXT().set_bDisabledFromBasic(false);
			oMap.get__Comp("INFO_DATUM").EXT().set_bDisabledFromBasic(false);
			oMap.get__Comp("INFO_TEXT").EXT().set_bDisabledFromBasic(false);
			
			if (!bibALL.get_bIST_SUPERVISOR())
			{
				oMap.get__Comp("ID_USER").EXT().set_bDisabledFromBasic(true);
				oMap.get__Comp("ID_USER").set_bEnabled_For_Edit(false);
				
				String cUserInList = ""+((MyE2IF__DB_Component) oMap.get__Comp("ID_USER")).EXT_DB().get_LActualDBValue(true, false, new Long(0)).intValue();
				if (!cUserInList.equals(bibALL.get_ID_USER()))
				{
					oMap.get__Comp("INFO_DATUM").EXT().set_bDisabledFromBasic(true);
					oMap.get__Comp("INFO_DATUM").set_bEnabled_For_Edit(false);
					oMap.get__Comp("INFO_TEXT").EXT().set_bDisabledFromBasic(true);
					oMap.get__Comp("INFO_TEXT").set_bEnabled_For_Edit(false);
				}
				
			}
			
		}
		
	}
	
	
}
