package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN;

import java.math.BigDecimal;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOMMUNIKATIONS_TYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._4_ALL.TelFaxIcon;

public class ConditionDaughterComponentMap extends MyE2_DBC_DaughterTable
{

	private static String     HASHKEY_TELEFON_QUALIFIER = "HASHKEY_TELEFON_QUALIFIER";
	private static String     HASHKEY_TELEFON_SYMBOL = "HASHKEY_TELEFON_SYMBOL";
	
	
	
	public ConditionDaughterComponentMap(	SQLFieldMAP 			fieldMAPMotherTable, 
												E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMapTelefon = new SQLFieldMAP("JT_FILTER");
//		oSQLFieldMapTelefon.addCompleteTable_FIELDLIST("JT_FILTER",":ID_KOMMUNIKATION:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");

//		oSQLFieldMapTelefon.add_SQLField(
//			new SQLFieldForPrimaryKey("JT_FILTER","ID_FILTER","ID_F",new MyE2_String("ID-Kommunikation"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_FILTER.NEXTVAL FROM DUAL",true), 
//			true);

		oSQLFieldMapTelefon.add_SQLField(
				new SQLFieldJoinOutside("JT_FILTER","ID_FILTER","ID_FILTER",new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_FILTER")), false);
	
//		oSQLFieldMapTelefon.get_("ID_KOMMUNIKATIONS_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
//		oSQLFieldMapTelefon.initFields();
		
//		E2_DropDownSettings oDDValues = new E2_DropDownSettings("JT_KOMMUNIKATIONS_TYP", "KURZBEZEICHNUNG", "ID_KOMMUNIKATIONS_TYP", "IST_STANDARD", true);

		
		E2_ComponentMAP 			oMapKommunikation = new E2_ComponentMAP(oSQLFieldMapTelefon);
//		oMapKommunikation.set_oMAPSettingAgent(new ownMapSetter());
		
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
//		MyE2_DB_TextField			oTF_LaenderVorwahl = 	new MyE2_DB_TextField(oSQLFieldMapTelefon.get_SQLField("WERT_LAENDERVORWAHL"));
//		MyE2_DB_TextField			oTF_Vorwahl = 			new MyE2_DB_TextField(oSQLFieldMapTelefon.get_SQLField("WERT_VORWAHL"));
//		MyE2_DB_TextField			oTF_Rufnummer = 		new MyE2_DB_TextField(oSQLFieldMapTelefon.get_SQLField("WERT_RUFNUMMER"));
//		MyE2_DB_TextField			oTF_Sonstige = 			new MyE2_DB_TextField(oSQLFieldMapTelefon.get_SQLField("WERT_SONSTIGE"));
//		MyE2_DB_SelectField			oSelectTyp	=			new MyE2_DB_SelectField(oSQLFieldMapTelefon.get_("ID_KOMMUNIKATIONS_TYP"),oDDValues.getDD(),false);
//		MyE2_DB_CheckBox			oIstStd	=				new MyE2_DB_CheckBox(oSQLFieldMapTelefon.get_("IST_STANDARD"));
		MyE2_TextField			oTF_LaenderVorwahl = 	new MyE2_TextField();

		
		//2012-02-06: kommunikations-typen
//		ConditionDaughterQualifierGrid  oTelQualifier =       new ConditionDaughterQualifierGrid(oSQLFieldMapTelefon.get_SQLField(RECORD_KOMMUNIKATION.FIELD__ID_KOMMUNIKATION), "JT_KOMMUNIKATION");
		
//		oSelectTyp.add_oActionAgent(new ownAction_QualifierFax4Mahnung());
		
		oMapKommunikation.set_allDBComponents_Sortable(false);
		
		oTF_LaenderVorwahl.set_iWidthPixel(100);
		//oTF_Vorwahl.set_iWidthPixel(100);
		//oTF_Rufnummer.set_iWidthPixel(130);
		oTF_LaenderVorwahl.set_iWidthPixel(100);
		
		
		oMapKommunikation.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
//		oMapKommunikation.add_Component(oTF_LaenderVorwahl,new MyE2_String("Länd.Vorw."));
		//oMapKommunikation.add_Component(oTF_Vorwahl,new MyE2_String("Vorwahl"));
		//oMapKommunikation.add_Component(oTF_Rufnummer,new MyE2_String("Rufnummer"));
		oMapKommunikation.add_Component(ConditionDaughterComponentMap.HASHKEY_TELEFON_SYMBOL,new MyE2_Grid(1,0),new MyE2_String(""));
		//oMapKommunikation.add_Component(oTF_Sonstige,new MyE2_String("Beschreibung"));
//		oMapKommunikation.add_Component(oSelectTyp,new MyE2_String("Kommunikations-Typ"));
		// oMapKommunikation.add_Component(oIstStd,new MyE2_String("Std."));
	
		//2012-02-06: kommunikations-typen
//		oMapKommunikation.add_Component(ConditionDaughterComponentMap.HASHKEY_TELEFON_QUALIFIER,oTelQualifier,new MyE2_String("Merkmale"));
		
	
		//TODO: So einen Validierer brauchen wir auch für die CONDITION
//		oMapKommunikation.register_Interactiv_settings_validation(_DB.KOMMUNIKATION$ID_KOMMUNIKATIONS_TYP, new ownSettingIcons());
		
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, true));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapKommunikation,
							null);
		
	}

	/**
	 * wird eine selektion eines kommunikationstyps ausgewaehlt, dann muss geprueft werden, ob es ein fax-typ ist
	 * und falls nicht evtl. im qualifier der typ fax fuer mahnungen ausgeschaltet werden
	 * @author martin
	 *
	 */
	private class ownAction_QualifierFax4Mahnung extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_SelectField  oSelField = (MyE2_SelectField)(oExecInfo.get_MyActionEvent().getSource());
			
			E2_ComponentMAP  oMap = oSelField.EXT().get_oComponentMAP();
			
			boolean bIstEinFax = false;
			
			long lID_KommunikationsTyp = oMap.get_LActualDBValue(_DB.KOMMUNIKATION$ID_KOMMUNIKATIONS_TYP, new Long(-1), new Long(-1)).longValue();
			
			if (lID_KommunikationsTyp>0)
			{
				RECORD_KOMMUNIKATIONS_TYP recKommunikations_TYP = new RECORD_KOMMUNIKATIONS_TYP(lID_KommunikationsTyp);
				
				if (recKommunikations_TYP.is_IST_FAX_NUMMER_YES())
				{
					bIstEinFax = true;
				}
			}

			if (!bIstEinFax)
			{
				//dann muss im qualifier der fax-schalter ausgeschaltet werden
				ConditionDaughterQualifierGrid oQuali = (ConditionDaughterQualifierGrid) oMap.get__Comp(ConditionDaughterComponentMap.HASHKEY_TELEFON_QUALIFIER);
				
				oQuali.set_selected(ConditionDaughterQualifierGrid.QU_TELEFON_TYP_4_MAHNUNG_FAX, false);
			}
		}
	}
	
	
	private class ownMapSetter extends XX_MAP_SettingAgent
	{

		@Override
		public void __doSettings_BEFORE(E2_ComponentMAP oMap,String STATUS_MASKE) throws myException 
		{
			E2_ComponentMAP  oMapMother = ConditionDaughterComponentMap.this.EXT().get_oComponentMAP();
			
			int idLand = oMapMother.get_bdActualDBValue("ID_LAND", new BigDecimal(-1), new BigDecimal(-1)).intValue();
			

			if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
			{
			
				if (idLand!=-1)
				{
					RECORD_LAND  recLand = new RECORD_LAND(idLand);
					
					String cLaendervorwahl = recLand.get_LAENDERVORWAHL_cUF_NN("");
					if (S.isEmpty(oMap.get_cActualDBValueFormated("WERT_LAENDERVORWAHL")))
					{
						oMap.set_cActualDatabaseValueToMask("WERT_LAENDERVORWAHL", cLaendervorwahl);
					}
				}
			}
			
		}

		@Override
		public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE)	throws myException 
		{
			
		}
		
	}

	
	private class ownSettingIcons extends XX_MAP_Set_And_Valid {

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			return this.set_on_load(oMAP, ActionType, oExecInfo, oInputMap);
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			return this.set_on_load(oMAP, ActionType, oExecInfo, oInputMap);
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			return this.set_on_load(oMAP, ActionType, oExecInfo, oInputMap);
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			return this.set_on_load(oMAP, ActionType, oExecInfo, oInputMap);
		}
	
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			return this.set_on_load(oMAP, ActionType, oExecInfo, oInputMap);
		}

		
		private MyE2_MessageVector set_on_load(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION || ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION) {
				long lActualValueTelefonTyp = oMAP.get_LActualDBValue(_DB.KOMMUNIKATION$ID_KOMMUNIKATIONS_TYP, 0l, 0l);
				
				if (lActualValueTelefonTyp>0) {
					RECORD_KOMMUNIKATIONS_TYP  recTyp = new RECORD_KOMMUNIKATIONS_TYP(lActualValueTelefonTyp);
					
					MyE2_Grid  oGrid = (MyE2_Grid) oMAP.get__Comp(ConditionDaughterComponentMap.HASHKEY_TELEFON_SYMBOL);
					oGrid.removeAll();
					
					oGrid.add(new TelFaxIcon(recTyp.is_IST_TEL_NUMMER_YES(), recTyp.is_IST_FAX_NUMMER_YES(), recTyp.get_BEZEICHNUNG_cUF_NN(""), true), E2_INSETS.I(0,0,0,0));
				}
			}
			return oMV;
		}
		
	}

	
}
