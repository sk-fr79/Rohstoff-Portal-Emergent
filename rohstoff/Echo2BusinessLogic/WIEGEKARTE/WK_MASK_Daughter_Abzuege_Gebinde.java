package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE_GEBINDE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE_GEBINDE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class WK_MASK_Daughter_Abzuege_Gebinde extends MyE2_DBC_DaughterTable {
	
	private RECLIST_WIEGEKARTE_GEBINDE m_listGebinde = null;
	private E2_ComponentMAP m_oMapAbzug = null;
	
	public WK_MASK_Daughter_Abzuege_Gebinde(SQLFieldMAP fieldMAPMotherTable, E2_ComponentMAP ocomponentMAP_from_Mother) throws myException {
		super();
		
		// liste aller Gebinde des Mandanten aufbauen...
		m_listGebinde = new RECLIST_WIEGEKARTE_GEBINDE("", "");
		

		SQLFieldMAP oSQLFieldMapAbzug = new SQLFieldMAP("JT_WIEGEKARTE_ABZUG_GEB",	bibE2.get_CurrSession());
		
		oSQLFieldMapAbzug.addCompleteTable_FIELDLIST( "JT_WIEGEKARTE_ABZUG_GEB",
							":ID_WIEGEKARTE_ABZUG_GEB:ID_WIEGEKARTE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false, true, "");
		
		oSQLFieldMapAbzug.add_SQLField(new SQLFieldForPrimaryKey( "JT_WIEGEKARTE_ABZUG_GEB", "ID_WIEGEKARTE_ABZUG_GEB", "ID_WIEGEKARTE_ABZUG_GEB",
									new MyE2_String("ID-Abzug"), bibE2.get_CurrSession(),
									"SELECT " + bibALL.get_TABLEOWNER()	+ ".SEQ_WIEGEKARTE_ABZUG_GEB.NEXTVAL FROM DUAL", true), false); 

		oSQLFieldMapAbzug.add_SQLField(new SQLFieldJoinOutside(	"JT_WIEGEKARTE_ABZUG_GEB", "ID_WIEGEKARTE", "ID_WIEGEKARTE",
									new MyE2_String("ID-Wiegekarte"), false, bibE2.get_CurrSession(),
									fieldMAPMotherTable.get_SQLField("ID_WIEGEKARTE")), false);

		// Mussfelder 
		oSQLFieldMapAbzug.get_("GEBINDE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapAbzug.get_("GEWICHT_EINZEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapAbzug.get_("MENGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		oSQLFieldMapAbzug.initFields();

		
		E2_DropDownSettings oDDValues = new E2_DropDownSettings("JT_WIEGEKARTE_GEBINDE", "KURZBEZ",	"ID_WIEGEKARTE_GEBINDE","NVL(JT_WIEGEKARTE_GEBINDE.AKTIV,'N') = 'Y'", null, true, "SORTIERUNG");

		m_oMapAbzug = new E2_ComponentMAP( oSQLFieldMapAbzug );

		MyE2_ButtonMarkForDelete oButtonForDel = new MyE2_ButtonMarkForDelete();

		MyE2_DB_TextField oTF_Gebinde = new MyE2_DB_TextField(	oSQLFieldMapAbzug.get_SQLField("GEBINDE"));
		MyE2_DB_TextField oTF_Gewicht = new MyE2_DB_TextField(	oSQLFieldMapAbzug.get_SQLField("GEWICHT_EINZEL"));
		oTF_Gewicht.setAlignment(Alignment.ALIGN_RIGHT);
		MyE2_DB_TextField oTF_Menge = new MyE2_DB_TextField(	oSQLFieldMapAbzug.get_SQLField("MENGE"));
		oTF_Menge.setAlignment(Alignment.ALIGN_RIGHT);
		
		
		MyE2_DB_SelectField oSelectTyp = new MyE2_DB_SelectField(	oSQLFieldMapAbzug.get_("ID_WIEGEKARTE_GEBINDE"),	oDDValues.getDD(), false);
		oSelectTyp.add_oActionAgent(new actionSelect());

		oTF_Gebinde.EXT_DB().set_bIsSortable(false);
		oTF_Gewicht.EXT_DB().set_bIsSortable(false);
		oTF_Menge.EXT_DB().set_bIsSortable(false);
		oSelectTyp.EXT_DB().set_bIsSortable(false);
		
		

		oTF_Gebinde.set_iWidthPixel(250);
		oTF_Gewicht.set_iWidthPixel(50);
		oTF_Menge.set_iWidthPixel(50);
		

		m_oMapAbzug.add_Component("DELETE_SELECTOR", oButtonForDel, new MyE2_String("?"));
		
		m_oMapAbzug.add_Component(oSelectTyp, new MyE2_String("Auswahl..."));
		m_oMapAbzug.add_Component(oTF_Gebinde, new MyE2_String("Gebinde"));
		m_oMapAbzug.add_Component(oTF_Gewicht, new MyE2_String("Gewicht(Kg)"));
		m_oMapAbzug.add_Component(oTF_Menge, new MyE2_String("Menge"));

		MyE2_Button oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"), true);

		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this,	true));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		// this.set_oContainerExScrollHeight(new Extent(170));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40, Extent.PERCENT));

		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),	ocomponentMAP_from_Mother, m_oMapAbzug, null);

	}
	
	
	
	
	private class actionSelect extends XX_ActionAgent{
		public actionSelect() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_MASK_Daughter_Abzuege_Gebinde oThis = WK_MASK_Daughter_Abzuege_Gebinde.this;
			
			MyE2_DB_SelectField oSel = (MyE2_DB_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			String id = oSel.get_cActualDBValueFormated().replace(".", "");
			E2_ComponentMAP oMap = oSel.EXT().get_oComponentMAP();
			
			MyE2_DB_TextField o = null;

		
			RECORD_WIEGEKARTE_GEBINDE oRec = null;
			String sGebinde = null;
			String sGewicht = null;
			
			if (!bibALL.isEmpty(id) ){
				oRec = oThis.m_listGebinde.get(id);
				sGebinde = oRec.get_BEZEICHNUNG_cUF();
				sGewicht = oRec.get_GEWICHT_cUF();
			}
			
			
			o  = (MyE2_DB_TextField)oMap.get__Comp("GEWICHT_EINZEL");
			o.setText(sGewicht);
//			o.set_bEnabled_For_Edit(sGewicht == null);
			
			o  = (MyE2_DB_TextField)oMap.get__Comp("GEBINDE");
			o.setText(sGebinde);
//			o.set_bEnabled_For_Edit(sGebinde == null);
			
			if (sGebinde == null){
				ApplicationInstance.getActive().setFocusedComponent((Component) oMap.get__Comp("GEBINDE"));
			} else if (sGewicht == null){
				ApplicationInstance.getActive().setFocusedComponent((Component) oMap.get__Comp("GEWICHT_EINZEL"));
			} else {
				ApplicationInstance.getActive().setFocusedComponent((Component) oMap.get__Comp("MENGE"));
			}
			
		}
		
	}
	

}
