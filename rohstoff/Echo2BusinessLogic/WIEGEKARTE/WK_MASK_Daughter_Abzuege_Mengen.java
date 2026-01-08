package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE_GEBINDE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ABZUGSGRUND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE_MGE_ABZ;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class WK_MASK_Daughter_Abzuege_Mengen extends MyE2_DBC_DaughterTable {
	
	private RECLIST_WIEGEKARTE_GEBINDE m_listGebinde = null;
	private E2_ComponentMAP m_oMapAbzug = null;
	
	public WK_MASK_Daughter_Abzuege_Mengen(SQLFieldMAP fieldMAPMotherTable, E2_ComponentMAP ocomponentMAP_from_Mother) throws myException 
	{
	
		super();
		
		SQLFieldMAP oSQLFieldMapAbzug = new SQLFieldMAP("JT_WIEGEKARTE_MGE_ABZ",	bibE2.get_CurrSession());
		
		oSQLFieldMapAbzug.addCompleteTable_FIELDLIST( "JT_WIEGEKARTE_MGE_ABZ",
							":ID_WIEGEKARTE_MGE_ABZ:ID_WIEGEKARTE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false, true, "");
		
		oSQLFieldMapAbzug.add_SQLField(new SQLFieldForPrimaryKey( "JT_WIEGEKARTE_MGE_ABZ", "ID_WIEGEKARTE_MGE_ABZ", "ID_WIEGEKARTE_MGE_ABZ",
									new MyE2_String("ID-Abzug"), bibE2.get_CurrSession(),
									"SELECT " + bibALL.get_TABLEOWNER()	+ ".SEQ_WIEGEKARTE_MGE_ABZ.NEXTVAL FROM DUAL", true), false); 

		oSQLFieldMapAbzug.add_SQLField(new SQLFieldJoinOutside(	"JT_WIEGEKARTE_MGE_ABZ", "ID_WIEGEKARTE", "ID_WIEGEKARTE",
									new MyE2_String("ID-Wiegekarte"), false, bibE2.get_CurrSession(),
									fieldMAPMotherTable.get_SQLField("ID_WIEGEKARTE")), false);

		oSQLFieldMapAbzug.add_BEDINGUNG_STATIC("NVL(JT_WIEGEKARTE_MGE_ABZ.ANZEIGE_WIEGEKARTE,'N') = 'Y'");
		
		// Mussfelder 
		oSQLFieldMapAbzug.initFields();

		
		
		E2_DropDownSettings oDDValues = new E2_DropDownSettings("JT_ABZUGSGRUND", 
															RECORD_ABZUGSGRUND.FIELD__ABZUGSGRUND,	
															RECORD_ABZUGSGRUND.FIELD__ID_ABZUGSGRUND,
															"NVL("+RECORD_ABZUGSGRUND.FIELD__ANZEIGE_WIEGEKARTE+",'N')='Y'", null, true, 
															RECORD_ABZUGSGRUND.FIELD__ABZUGSGRUND);

		E2_DropDownSettings oDDValuesShadow = new E2_DropDownSettings("JT_ABZUGSGRUND", 
															RECORD_ABZUGSGRUND.FIELD__ABZUGSGRUND,	
															RECORD_ABZUGSGRUND.FIELD__ID_ABZUGSGRUND,
															"NVL("+RECORD_ABZUGSGRUND.FIELD__ANZEIGE_WIEGEKARTE+",'N')='N'", null, true, 
															RECORD_ABZUGSGRUND.FIELD__ABZUGSGRUND);

		
		m_oMapAbzug = new E2_ComponentMAP( oSQLFieldMapAbzug );

		MyE2_DB_TextArea   		oTF_Langtext = new MyE2_DB_TextArea(		oSQLFieldMapAbzug.get_SQLField(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__LANGTEXT),200,3,false,new E2_FontPlain(-2));
		MyE2_DB_TextField 		oTF_Abzug_Menge = new MyE2_DB_TextField(	oSQLFieldMapAbzug.get_SQLField(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE),true,50);
		MyE2_DB_TextField 		oTF_Abzug_prozent = new MyE2_DB_TextField(	oSQLFieldMapAbzug.get_SQLField(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT),true,50);
		
		MyE2_DB_SelectField oSelectTyp = new MyE2_DB_SelectField(	oSQLFieldMapAbzug.get_(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ID_ABZUGSGRUND),	oDDValues.getDD(), false);
		oSelectTyp.set_odataToViewShadow(new dataToView(oDDValuesShadow.getDD(), false, bibE2.get_CurrSession()));
		oSelectTyp.add_oActionAgent(new actionSelect());

		oTF_Abzug_prozent.EXT().add_Field_Validator_Check_Input_and_MarkFalseValues(new XX_FieldSetter_AND_Validator()
		{
			
			@Override
			public MyE2_MessageVector isValid(String cSTATUS_MAP,MyE2EXT__Component EXT_own) throws myException
			{
				MyE2_MessageVector  oMV = new MyE2_MessageVector();
				BigDecimal bdProzent = EXT_own.get_oComponentThisBelongsTo().EXT().get_oComponentMAP().get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, new BigDecimal(-1), new BigDecimal(-1));
				
				if (bdProzent.compareTo(new BigDecimal(100))>0)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mehr als 100 Prozent kann nicht abgezogen werden !!!")));
				}
				return oMV;
			}
		});
		
		
		this.m_oMapAbzug.set_allDBComponents_Sortable(false);

		MyE2_ButtonMarkForDelete oButtonForDel = new MyE2_ButtonMarkForDelete();
		
		m_oMapAbzug.add_Component("DELETE_SELECTOR",  oButtonForDel, new MyE2_String("?"));
		
		m_oMapAbzug.add_Component(oSelectTyp, new MyE2_String("Auswahl..."));
		m_oMapAbzug.add_Component(oTF_Abzug_Menge, new MyE2_String("Menge"));
		m_oMapAbzug.add_Component(oTF_Abzug_prozent, new MyE2_String("Prozent"));
		m_oMapAbzug.add_Component(oTF_Langtext, new MyE2_String("Zusatztext"));

		MyE2_Button oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"), true);

		oButtonNEW.add_oActionAgent(new ownActionRefresh_before_new());
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this,	true));
		
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40, Extent.PERCENT));

		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),	ocomponentMAP_from_Mother, m_oMapAbzug, null);

		
	}
	
	

	private class ownActionRefresh_before_new extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			WK_MASK_Daughter_Abzuege_Mengen.this.refresh_list();
		}
	}
	
	
	/**
	 * in der methode wird in der liste dafuer gesorgt, dass nich gleichzeitig abzug_menge und abzug_prozent vorhanden sind
	 * @throws myException
	 */
	public void refresh_list() throws myException
	{
		Vector<E2_ComponentMAP>  vAllMaps = new Vector<E2_ComponentMAP>();
		
		Vector<E2_ComponentMAP> vE2_ComponentMAPs_OLD = 	new Vector<E2_ComponentMAP>();
		Vector<E2_ComponentMAP> vE2_ComponentMAPs_NEW = 	new Vector<E2_ComponentMAP>();
		vE2_ComponentMAPs_OLD.addAll(this.get_oNavigationList().get_vComponentMAPS());
		vE2_ComponentMAPs_NEW.addAll(this.get_oNavigationList().get_vComponentMAPS_NEW());
		
		vAllMaps.addAll(this.get_vE2_ComponentMAPs_NewAndEdit_WithoutDeleteMarker());
		
		for (E2_ComponentMAP  oMap: vAllMaps)
		{
			//wenn falsche zahlen oder negative werte drinstehen oder 0, dann leer 
			if (oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE, new BigDecimal(-1), new BigDecimal(-1)).compareTo(BigDecimal.ZERO)<=0)
			{
				oMap.set_cActualDatabaseValueToMask(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE, "");
			}
			if (oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, new BigDecimal(-1), new BigDecimal(-1)).compareTo(BigDecimal.ZERO)<=0)
			{
				oMap.set_cActualDatabaseValueToMask(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, "");
			}

			//wenn beide gefuellt sind, dann gilt die menge
			if (oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE, new BigDecimal(-1), new BigDecimal(-1)).compareTo(BigDecimal.ZERO)>0 &&
				oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, new BigDecimal(-1), new BigDecimal(-1)).compareTo(BigDecimal.ZERO)>0)
			{
				oMap.set_cActualDatabaseValueToMask(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, "");  //abzug-prozent wird leer
			}
			
			//wenn beide leer sind, dann wird die zeile geloescht
			if (oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE, new BigDecimal(0), new BigDecimal(0)).compareTo(BigDecimal.ZERO)==0 &&
				oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, new BigDecimal(0), new BigDecimal(0)).compareTo(BigDecimal.ZERO)==0)
			{
				//nachschauen, ob alt oder neu
				oMap.set_AllComponentsAsNormal();
				oMap.set_AllComponentsEnabled_For_Edit(true,E2_ComponentMAP.STATUS_UNDEFINED);
				if (!this.bCheckObOK(oMap))
				{
					if (vE2_ComponentMAPs_OLD.contains(oMap))
					{
					    this.set_bMapIsMarkedToDelete(oMap, true);
					    
					    //die alten muessen auch noch als deleted (oder nicht) markiert werden
						oMap.set_AllComponentsAsDeleted();
						oMap.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_UNDEFINED);

					}
					else
					{
						vE2_ComponentMAPs_NEW.remove(oMap);
					}
				}
			}
			
			this.get_oNavigationList().get_vComponentMAPS().removeAllElements();
			this.get_oNavigationList().get_vComponentMAPS().addAll(vE2_ComponentMAPs_OLD);
			this.get_oNavigationList().get_vComponentMAPS_NEW().removeAllElements();
			this.get_oNavigationList().get_vComponentMAPS_NEW().addAll(vE2_ComponentMAPs_NEW);
			
			
		}
		
		this.get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);
	}
	
	
	
	private boolean bCheckObOK(E2_ComponentMAP oMap) throws myException
	{
		//wenn falsche zahlen oder negative werte drinstehen oder 0, dann leer 
		if (oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE, new BigDecimal(-1), new BigDecimal(-1)).compareTo(BigDecimal.ZERO)<=0)
		{
			oMap.set_cActualDatabaseValueToMask(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE, "");
		}
		if (oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, new BigDecimal(-1), new BigDecimal(-1)).compareTo(BigDecimal.ZERO)<=0)
		{
			oMap.set_cActualDatabaseValueToMask(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, "");
		}

		//wenn sowohl menge als auch prozent gefuellt ist, dann gilt die menge , prozent wird geloescht
		if (oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE, new BigDecimal(-1), new BigDecimal(-1)).compareTo(BigDecimal.ZERO)>0 &&
			oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, new BigDecimal(-1), new BigDecimal(-1)).compareTo(BigDecimal.ZERO)>0)
		{
			oMap.set_cActualDatabaseValueToMask(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, "");  //abzug-prozent wird leer
		}
		
		//wenn beide leer sind, dann wird die zeile geloescht
		if (oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_MENGE, new BigDecimal(0), new BigDecimal(0)).compareTo(BigDecimal.ZERO)==0 &&
			oMap.get_bdActualDBValue(RECORD_WIEGEKARTE_MGE_ABZ.FIELD__ABZUG_PROZENT, new BigDecimal(0), new BigDecimal(0)).compareTo(BigDecimal.ZERO)==0)
		{
			return false;
		}
		
		return true;
	}
	
	
	
	
	private class actionSelect extends XX_ActionAgent{
		public actionSelect() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			WK_MASK_Daughter_Abzuege_Mengen oThis = WK_MASK_Daughter_Abzuege_Mengen.this;
		}
		
	}
	

}
