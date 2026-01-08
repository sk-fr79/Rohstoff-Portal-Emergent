package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_ARTBEZ;

import java.util.Vector;

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
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOSTENTYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOSTEN_ARTBEZ_LIEF;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSK_Daughter_Kosten_Artbez_Lief extends MyE2_DBC_DaughterTable {
	
//	private RECLIST_WIEGEKARTE_GEBINDE m_listGebinde = null;
	private E2_ComponentMAP m_oMapKostenLieferbedAdr = null;
	
	public FSK_Daughter_Kosten_Artbez_Lief(SQLFieldMAP fieldMAPMotherTable, E2_ComponentMAP ocomponentMAP_from_Mother) throws myException 
	{
	
		super();
		
		SQLFieldMAP oSQLFieldMapKosten = new SQLFieldMAP("JT_KOSTEN_ARTBEZ_LIEF",	bibE2.get_CurrSession());
		
		oSQLFieldMapKosten.addCompleteTable_FIELDLIST( "JT_KOSTEN_ARTBEZ_LIEF",
						":ID_KOSTEN_ARTBEZ_LIEF:ID_ARTIKELBEZ_LIEF:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false, true, "");
		
		oSQLFieldMapKosten.add_SQLField(new SQLFieldForPrimaryKey( "JT_KOSTEN_ARTBEZ_LIEF", "ID_KOSTEN_ARTBEZ_LIEF", "ID_KOSTEN_ARTBEZ_LIEF",
									new MyE2_String("ID-Transportkosten"), bibE2.get_CurrSession(),
									"SELECT " + bibALL.get_TABLEOWNER()	+ ".SEQ_KOSTEN_ARTBEZ_LIEF.NEXTVAL FROM DUAL", true), false); 

		oSQLFieldMapKosten.add_SQLField(new SQLFieldJoinOutside(	"JT_KOSTEN_ARTBEZ_LIEF", "ID_ARTIKELBEZ_LIEF", "ID_ARTIKELBEZ_LIEF",
									new MyE2_String("ID Artikelbez Lief"), false, bibE2.get_CurrSession(),
									fieldMAPMotherTable.get_SQLField("ID_ARTIKELBEZ_LIEF")), false);

		

		// Mussfelder 
		oSQLFieldMapKosten.initFields();
		
		// Dropdown auf Kostentyp
		E2_DropDownSettings oDDValuesEinheit = new E2_DropDownSettings(RECORD_KOSTENTYP.TABLENAME, 
															RECORD_KOSTENTYP.FIELD__BEZ_KURZ + " || ' (' || case when " + RECORD_KOSTENTYP.FIELD__PAUSCHAL + " = 'Y' then 'einmalig' else 'pro EH' end || ')' ",	
															RECORD_KOSTENTYP.FIELD__ID_KOSTENTYP,
															"", null, true, 
															RECORD_KOSTENTYP.FIELD__BEZ_KURZ);

		
		m_oMapKostenLieferbedAdr = new E2_ComponentMAP( oSQLFieldMapKosten );

		MyE2_DB_TextField 		oTF_Kosten 			= new MyE2_DB_TextField( oSQLFieldMapKosten.get_SQLField(RECORD_KOSTEN_ARTBEZ_LIEF.FIELD__BETRAG),true,50);
		MyE2_DB_TextField 		oTF_KostenStrecke 	= new MyE2_DB_TextField( oSQLFieldMapKosten.get_SQLField(RECORD_KOSTEN_ARTBEZ_LIEF.FIELD__BETRAG_STRECKE),true,50);
		MyE2_DB_TextField 		oTF_Bemerkung 		= new MyE2_DB_TextField( oSQLFieldMapKosten.get_SQLField(RECORD_KOSTEN_ARTBEZ_LIEF.FIELD__BEMERKUNG),true,200);
			
		MyE2_DB_SelectField oSelectKostentyp = 	new MyE2_DB_SelectField(	oSQLFieldMapKosten.get_(RECORD_KOSTEN_ARTBEZ_LIEF.FIELD__ID_KOSTENTYP),	oDDValuesEinheit.getDD(), false);
		
//		oSelectEinheit.add_oActionAgent(new actionSelect());
	
//		oSelectTyp.set_odataToViewShadow(new dataToView(oDDValuesShadow.getDD(), false, bibE2.get_CurrSession()));
		

		this.m_oMapKostenLieferbedAdr.set_allDBComponents_Sortable(false);

		MyE2_ButtonMarkForDelete oButtonForDel = new MyE2_ButtonMarkForDelete();
		
		m_oMapKostenLieferbedAdr.add_Component("DELETE_SELECTOR",  oButtonForDel, new MyE2_String("?"));
		
		m_oMapKostenLieferbedAdr.add_Component(oSelectKostentyp		, new MyE2_String("Kostentyp"));
		m_oMapKostenLieferbedAdr.add_Component(oTF_Kosten			, new MyE2_String("Kosten Lager"));
		m_oMapKostenLieferbedAdr.add_Component(oTF_KostenStrecke 	, new MyE2_String("Kosten Strecke"));
		m_oMapKostenLieferbedAdr.add_Component(oTF_Bemerkung 		, new MyE2_String("Bemerkung"));

		
		MyE2_Button oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"), true);

		oButtonNEW.add_oActionAgent(new ownActionRefresh_before_new());
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this,	true));
		
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40, Extent.PERCENT));

		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),	ocomponentMAP_from_Mother, m_oMapKostenLieferbedAdr, null);

		
	}
	
	

	private class ownActionRefresh_before_new extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FSK_Daughter_Kosten_Artbez_Lief.this.refresh_list();
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
		
		this.get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);
	}
	
	
	
	

}
