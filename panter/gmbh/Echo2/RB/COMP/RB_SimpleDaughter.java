package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.ADDING_FieldLoggingButtons;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Complex;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder_Simple;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import echopointng.able.Scrollable;

public class RB_SimpleDaughter extends MyE2_DBC_DaughterTable implements  IF_RB_Component_Complex {

	private String 		NamePrimaryKeyFieldMotherTable = null;
	private String 		NameForeignKeyFieldDaughterToMother = null;
	private RB_KF 	Key = null;

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}

	
	public RB_SimpleDaughter() throws myException {
		super();
	}

	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void mark_MustField() throws myException {}

	@Override
	public void mark_Disabled() throws myException {}

	@Override
	public void mark_FalseInput() throws myException {}


	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}


	public void INIT_RB_Daughter(	RB_ComponentMap		 					maskOutside, 
									String 							namePrimaryKeyFieldMotherTable,
									String   						nameForeignKeyFieldDaughterToMother,
									E2_ComponentMAP 				component_MAP_4_Daughter, 
									E2_ComponentMAP_Factory4Records componentMAP_Factory4Records,
									MutableStyle 					StyleForList
									) throws myException {
        
		
		this.NamePrimaryKeyFieldMotherTable = namePrimaryKeyFieldMotherTable;
		this.NameForeignKeyFieldDaughterToMother = nameForeignKeyFieldDaughterToMother;
		
        this.set_oComponentMAP_From_Mask(maskOutside);
        this.set_oOwnComponentMAP_ForList(component_MAP_4_Daughter);
        
        this.get_oOwnComponentMAP_ForList().set_Factory4Records(componentMAP_Factory4Records);
        
//		this.EXT_DB().set_bGivesBackValueToDB(false);
//		this.EXT_DB().set_oSQLField(this.MaskField.get_SQLFieldDummy());

		this.set_oNavigationList(new E2_NavigationList());
		this.get_oNavigationList().INIT_WITH_ComponentMAP(this.get_oOwnComponentMAP_ForList(), StyleForList, null);
        
		/*
		 * hier gibts keine navigatoren, es wird immer alles angezeigt
		 */
		this.get_oNavigationList().get_vectorSegmentation().set_bOnlyOneSegment(true);
		this.get_oNavigationList().hide_NavigationsElements();
		
		
		this.get_oContainerEx().setHeight(this.get_oContainerExScrollHeight());
		this.get_oContainerEx().setScrollBarPolicy(Scrollable.AUTO);
		this.get_oContainerEx().add(this.get_oNavigationList());
		
		this.add(this.get_oRow_In_TOP());
		this.add(this.get_oContainerEx(),new Insets(0,0,0,0));
		this.add(this.get_oRow_In_BOTTOM());
		
		
		/*
		 * jetzt alle EXT_DB()-komponenten unsortierbar schalten, da eine sortierung einen
		 * undefinierbaren effekt beim neuaufbau der liste haette
		 */
		this.get_oOwnComponentMAP_ForList().set_allDBComponents_Sortable(false);
		
		this.rb_VALIDATORS_4_INPUT().add(new ownFieldValidator_AfterReadInputMAP(this));
		
	}

	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException
	{
		
		if (dataObject.get_RecORD()==null) {
			this.rb_clear_component();
		} else {

			String cID_VALUE = ((MyRECORD)dataObject.get_RecORD()).get_UnFormatedValue(this.NamePrimaryKeyFieldMotherTable);
			
			/*
			 * maske fuellen mit der bedingung, die die verbindung zur muttertabelle liefert
			 */
			this.get_oNavigationList().get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_STATIC();
			this.get_oNavigationList().get_oComponentMAP__REF().get_oSQLFieldMAP().add_BEDINGUNG_STATIC(this.get_ZudatzWhere(cID_VALUE));
			 
			this.get_oNavigationList().get_vComponentMAPS_NEW().removeAllElements();
			this.get_oNavigationList().Fill_NavigationList("");
			 
			/*
			 * falls der status COPY vorliegt, dann muss in den componentMAPs der navigationlist 
			 * das actuelle resultmap auf null gesetzt werden und alle in den new-bereich uebertragen werden
			 */
			if (((RB_ComponentMap)this.EXT().get_oComponentMAP()).getRbDataObjectActual().rb_MASK_STATUS().get_VALUE().equals(RB__CONST.MASK_STATUS.NEW_COPY.get_VALUE()))  {
				 Vector<E2_ComponentMAP> vE2_ComponentMAPS = this.get_oNavigationList().get_vComponentMAPS();
				 for (int i=0;i<vE2_ComponentMAPS.size();i++) {
					 ((E2_ComponentMAP)vE2_ComponentMAPS.get(i)).set_InternResultMAP_TO_NULL();
				 }
				 
				 this.get_oNavigationList().get_vComponentMAPS_NEW().addAll( this.get_oNavigationList().get_vComponentMAPS());
				 this.get_oNavigationList().get_vComponentMAPS().removeAllElements();
				 this.get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);
			}
			
			
			//2015-09-23:martin: die aenderungslog-buttons auch hier anzeigen
			new ADDING_FieldLoggingButtons(this.get_oNavigationList().get_oDataGrid());
		}
	}

	
	private String get_ZudatzWhere(String cID_UF) {
		return this.get_oOwnComponentMAP_ForList().get_oSQLFieldMAP().get_cMAIN_TABLE()+"."+this.NamePrimaryKeyFieldMotherTable+"="+cID_UF;
	}
	
	
	
	
	private void rb_clear_component() throws myException {
		/*
		 * tabelle wird leer
		 */
		this.get_oNavigationList().get_vComponentMAPS_NEW().removeAllElements();
		this.get_oNavigationList().Show_Empty_NavigationList();
		
		 /*
		  * der sqlfieldmap wird eine bedingung mit leerer antwort uebergeben, damit ein wvtl.
		  * vorhandener sortierknopf keine falschen listeneintraege zurueckgibt beim neuaufbau.
		  * Im Zustand "Neueingabe" kann noch keine beschraenkung der liste vorhanden sein
 		  */
		 this.get_oNavigationList().get_oComponentMAP__REF().get_oSQLFieldMAP().clear_BEDINGUNG_STATIC();
		 this.get_oNavigationList().get_oComponentMAP__REF().get_oSQLFieldMAP().add_BEDINGUNG_STATIC(this.get_ZudatzWhere("-1"));
		
		this.set_cActual_STATUS_MAP(E2_ComponentMAP.STATUS_NEW_EMPTY);
	}


	
	

	
	/*
	 * eigene field-validator-class
	 */
	private class ownFieldValidator_AfterReadInputMAP extends RB_Validator_Component
	{
		
		private MyE2_DBC_DaughterTable oFieldDaughterTabler = null;


		public ownFieldValidator_AfterReadInputMAP(RB_SimpleDaughter odaughter)
		{
			super();
			oFieldDaughterTabler = odaughter;
		}
		
		@Override
		public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
			
			MyE2_MessageVector 			oMV = new MyE2_MessageVector();
			E2_NavigationList 			oList = this.oFieldDaughterTabler.get_oNavigationList();
			Vector<E2_ComponentMAP> 	vE2_ComponentMAPs = oList.get_vComponentMAPS();
			Vector<E2_ComponentMAP> 	vE2_ComponentMAPs_NEW = oList.get_vComponentMAPS_NEW();
			
			RB_ComponentMap  oMASK = (RB_ComponentMap)RB_SimpleDaughter.this.EXT().get_oComponentMAP();
			
			for (int i=0;i<vE2_ComponentMAPs.size();i++)
			{
				if (! oFieldDaughterTabler.get_bMapIsMarkedToDelete((E2_ComponentMAP)vE2_ComponentMAPs.get(i)))
				{
					((E2_ComponentMAP)vE2_ComponentMAPs.get(i)).MakeCompleteCycle_of_Validation_After_Input(null,oMV,oMASK.getRbDataObjectActual().rb_MASK_STATUS().get_oldStatus_E2_ComponentMAP());
				}
			}
			for (int i=0;i<vE2_ComponentMAPs_NEW.size();i++)
			{
				if (! oFieldDaughterTabler.get_bMapIsMarkedToDelete((E2_ComponentMAP)vE2_ComponentMAPs_NEW.get(i)))
				{
					((E2_ComponentMAP)vE2_ComponentMAPs_NEW.get(i)).MakeCompleteCycle_of_Validation_After_Input(null,oMV,oMASK.getRbDataObjectActual().rb_MASK_STATUS().get_oldStatus_E2_ComponentMAP());
				}
			}
			return oMV;
		}
		
	}


	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public Vector<MyRECORD_IF_FILLABLE> maskContents_Transfer_To_Record_And_Prepare_Save(MyE2_MessageVector oMV, RB_ComponentMap thisMask) throws myException {
		Vector<MyRECORD_IF_FILLABLE> vRueck = new Vector<MyRECORD_IF_FILLABLE>();
		
		Vector<E2_ComponentMAP> 	vE2_ComponentMAPs = 		this.get_oNavigationList().get_vComponentMAPS();
		Vector<E2_ComponentMAP>		vE2_ComponentMAPs_NEW = 	this.get_oNavigationList().get_vComponentMAPS_NEW();
			
		//zuerst die veraenderten 
		for (E2_ComponentMAP oMap: vE2_ComponentMAPs) {
			
			if (!this.get_bMapIsMarkedToDelete(oMap)) {
				SQLMaskInputMAP oInputMap = oMap.get_ActualInputMAP_And_MarkFalseInput();		// inputmap
				
				MyRECORD recUpdate = (MyRECORD)oMap.get_Record4MainTable();
				
				for (String field: oInputMap.keySet()) {
					if (recUpdate.containsKey(field)) {
						oMV.add_MESSAGE(recUpdate.set_NewValueForDatabase(field, oInputMap.get(field)));
					} else {
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Inputfield not found in record: ",true,field,false)));
					}
				}
				
				vRueck.add((MyRECORD_IF_FILLABLE)recUpdate);
			}				

		}
		
		
		//dann die neuen 
		for (E2_ComponentMAP oMap: vE2_ComponentMAPs_NEW) {
			
			if (!this.get_bMapIsMarkedToDelete(oMap)) {
				SQLMaskInputMAP oInputMap = oMap.get_ActualInputMAP_And_MarkFalseInput();		// inputmap
				
				MyRECORD_NEW recNew = (MyRECORD_NEW)oMap.get_RecordNew4MainTable();
				
				for (String field: oInputMap.keySet()) {
					if (recNew.containsKey(field)) {
						oMV.add_MESSAGE(recNew.set_NewValueForDatabase(field, oInputMap.get(field)));
					} else {
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Inputfield not found in record: ",true,field,false)));
					}
				}
				
				vRueck.add((MyRECORD_IF_FILLABLE)recNew);
			}				

		}

		
		
		return vRueck;
	}

	@Override
	public Vector<MySqlStatementBuilder> get_vSQL_StatementBuilders_Others(MyE2_MessageVector oMV, RB_ComponentMap thisMask) throws myException {
		Vector<MySqlStatementBuilder> vRueck = new Vector<MySqlStatementBuilder>();
		
		Vector<E2_ComponentMAP> 	vE2_ComponentMAPs = 		this.get_oNavigationList().get_vComponentMAPS();
		
		//hier die geloeschten sammeln
		for (E2_ComponentMAP oMap: vE2_ComponentMAPs) {
			if (this.get_bMapIsMarkedToDelete(oMap)) {
				String TABLENAME = this.get_oOwnComponentMAP_ForList().get_oSQLFieldMAP().get_cMAIN_TABLE();
				vRueck.add(new MySqlStatementBuilder_Simple("DELETE FROM "+bibE2.cTO()+"."+TABLENAME+
															" WHERE ID_"+TABLENAME.substring(3)+"="+oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()));
			}
		}
		return vRueck;
	}

	
	
	@Override
	public MyE2_MessageVector makeBindingDaughterRecords_to_MotherTable(RB_Dataobject DataObjectMother, Vector<MyRECORD_IF_FILLABLE> v_DaughterRecords) throws myException {
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String cAddonField = this.NameForeignKeyFieldDaughterToMother;
		String cAddonVal = null;
		if (DataObjectMother.rb_relevant_record_to_fill() instanceof MyRECORD_IF_RECORDS) {
			cAddonVal = ((MyRECORD)DataObjectMother.rb_relevant_record_to_fill()).get_UnFormatedValue(this.NamePrimaryKeyFieldMotherTable);
		} else {
			cAddonVal = "SEQ_"+DataObjectMother.get_RecNEW().get_TABLE_NAME().substring(3)+".CURRVAL";
		}

		for (MyRECORD_IF_FILLABLE rec: v_DaughterRecords) {
			rec.get_hm_Field_Value_pairs_from_outside().put(cAddonField, cAddonVal);
		}
		
		return oMV;
	}

	
	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		// TODO Auto-generated method stub
		
	}

	
}
