package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.COMP.RB_SimpleDaughter;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class ES_mask_complex_EMAIL_SEND_TARGETS extends RB_SimpleDaughter {

	private static String	HASK_KEY_DELETED_BUTTON = "HASK_KEY_DELETED_BUTTON";
//	private static String	HASK_KEY_MOVEUP_BUTTON = "HASK_KEY_MOVEUP_BUTTON";
	
	private MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
	private RB_ComponentMap rb_ComponentMap_outside = null;
	
	
	public ES_mask_complex_EMAIL_SEND_TARGETS(RB_ComponentMap oMaskOutside) throws myException {
		super();

		this.rb_ComponentMap_outside = oMaskOutside;
		
		Project_SQLFieldMAP sqlMAP = new Project_SQLFieldMAP(_DB.EMAIL_SEND_TARGETS,"",false);
		sqlMAP.initFields();
		
		sqlMAP.clear_ORDER_SEGMENT();
		sqlMAP.add_ORDER_SEGMENT(EMAIL_SEND_TARGETS.pos.fn());
		
		E2_ComponentMAP 				oMapEmail = 				new E2_ComponentMAP(sqlMAP);
		MyE2_ButtonMarkForDelete 		oButtonForDel = 			new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField				oTF_TargetMailAdresse = new MyE2_DB_TextField(sqlMAP.get_SQLField(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS),true,245,0,false);
		MyE2_DB_CheckBox        		cb_SendOK = new MyE2_DB_CheckBox(sqlMAP.get_SQLField(_DB.EMAIL_SEND_TARGETS$SEND_OK), true);
		DB_Component_USER_DROPDOWN_NEW 	sel_USER = 	new DB_Component_USER_DROPDOWN_NEW(sqlMAP.get_SQLField(_DB.EMAIL_SEND_TARGETS$ID_USER_SEND), true);
		MyE2_DB_TextField				oTF_SendingDate = new MyE2_DB_TextField(sqlMAP.get_SQLField(_DB.EMAIL_SEND_TARGETS$SENDING_TIME),true,100,0,true);
		
		oTF_TargetMailAdresse.EXT().set_oColExtent(new Extent(250));
		sel_USER.EXT().set_bDisabledFromBasic(true);
		
		bt_move_up bt_move = new bt_move_up(sqlMAP.get_SQLField(EMAIL_SEND_TARGETS.pos));
		
		oMapEmail.add_Component(ES_mask_complex_EMAIL_SEND_TARGETS.HASK_KEY_DELETED_BUTTON,oButtonForDel,new MyE2_String("?"));
		oMapEmail.add_Component(bt_move,new MyE2_String("Sort"));
		oMapEmail.add_Component(oTF_TargetMailAdresse,new MyE2_String("Mailadresse"));
		oMapEmail.add_Component(ES_CONST.HASHKEY_FIELDNAME_SEARCH_RELEVANT_MAILADRESSES,
								new ES_mask_complex_EMAIL_SEND_TARGETS_bt_FindCorrespondingEmails(this), new MyE2_String("Suche"));
		oMapEmail.add_Component(sel_USER,new MyE2_String("Wer ?"));
		oMapEmail.add_Component(oTF_SendingDate,new MyE2_String("Wann ?"));
		oMapEmail.add_Component(cb_SendOK,new MyE2_String("OK ?"));
		
		oMapEmail.set_oSubQueryAgent(new ownSubQueryAgentSendAdressesCanNotBeDeleted());
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		//this.set_oContainerExScrollHeight(new Extent(300));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));
		
		this.INIT_RB_Daughter(	oMaskOutside,
								_DB.EMAIL_SEND$ID_EMAIL_SEND,
								_DB.EMAIL_SEND_TARGETS$ID_EMAIL_SEND,
								oMapEmail,
								new ownRecordFactory(),
								E2_NavigationList.STYLE_THIN_BORDER());
		
	}

	
	private class ownRecordFactory extends  E2_ComponentMAP_Factory4Records {

		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_EMAIL_SEND_TARGETS(cID_MAINTABLE);
		}
		
	}


	public void set_bEnabled_For_Edit_Add_and_Del_Buttons(boolean bEnabled) throws myException {
		this.oButtonNEW.set_bEnabled_For_Edit(bEnabled);
		for (E2_ComponentMAP oMap: this.get_vE2_ComponentMAPs_Edit(true)) {
			((MyE2_ButtonMarkForDelete)oMap.get__Comp(ES_mask_complex_EMAIL_SEND_TARGETS.HASK_KEY_DELETED_BUTTON)).set_bEnabled_For_Edit(bEnabled);
		}
	}
	

	
	public boolean get_bEvenOneTargetWasSend() throws myException {
		for (E2_ComponentMAP oMap: this.get_vE2_ComponentMAPs_Edit(true)) {
			RECORD_EMAIL_SEND_TARGETS recTarget = (RECORD_EMAIL_SEND_TARGETS)oMap.get_Record4MainTable();
			if (recTarget.is_SEND_OK_YES()) {
				return true;
			}
		}
		return false;
	}

	
	public int get_NumberTargetAdresses() {
		int iCount = this.get_vE2_ComponentMAPs_Edit(true).size();
		iCount +=this.get_vE2_ComponentMAPs_New(true).size();
		
		return iCount;

	}
	
	
	

	
	/*
	 * subqueryagent in der Liste der Zieladressen: wurde eine zeile als verschickt erkannt, dann ist loeschen und aendern nicht moeglich in der zeile
	 */
	private class ownSubQueryAgentSendAdressesCanNotBeDeleted extends XX_ComponentMAP_SubqueryAGENT {

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException {
		}

		@Override
		public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
			RECORD_EMAIL_SEND_TARGETS recTarget = ((RECORD_EMAIL_SEND_TARGETS)oMAP.get_Record4MainTable());
			if (recTarget!=null) {
				if (recTarget.is_SEND_OK_YES()) {
					oMAP.get__Comp(ES_mask_complex_EMAIL_SEND_TARGETS.HASK_KEY_DELETED_BUTTON).EXT().set_bDisabledFromBasic(true);
					oMAP.get__Comp(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS).EXT().set_bDisabledFromBasic(true);
				}
			}
		}
	}


	public MyE2_Button get_oButtonNEW() {
		return oButtonNEW;
	}
	
	
	
	/**
	 * pseudo-db-field, das die sortierung mittraegt
	 * @author martin
	 *
	 */
	private class bt_move_up extends E2_Button implements E2_IF_Copy, MyE2IF__DB_Component{

		private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
		private int        				sort_value = 0;
		
		
		public bt_move_up(SQLField  field) throws myException {
			super();
			
			this.oEXTDB.set_bGivesBackValueToDB(true);
			this.oEXTDB.set_oSQLField(field);

			
			this._image(E2_ResourceIcon.get_RI("up_mini.png"),E2_ResourceIcon.get_RI("up_mini__.png"));
			this.add_oActionAgent(new ownActionMoveUp());
			this.add_oActionAgent(new ownActionSaveReopen());
			this.EXT().set_oLayout_ListElement(new RB_gld()._center_mid()._ins(2, 2, 2, 2));

			this._ttt(new MyE2_String("Diese eMail-Adresse auf die Position 1 schieben (als Hauptadresse markieren) !"));
		}
		
		
		private class ownActionMoveUp extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ES_mask_complex_EMAIL_SEND_TARGETS  oThis = ES_mask_complex_EMAIL_SEND_TARGETS.this;
				
				//jetzt nachsehen, ob es neue zeilen gibt, wenn ja, must erst gespeichert werden
				if (oThis.get_vE2_ComponentMAPs_New(false).size()>0) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst speichern !!")));
				} else {
					E2_ComponentMAP map_own = bt_move_up.this.EXT().get_oComponentMAP();
					Vector<E2_ComponentMAP>  v_maps = map_own.get_VectorComponentMAP_thisBelongsTo();
					
					//jetzt nummerieren
					int i = 2;
					for (E2_ComponentMAP map: v_maps) {
						bt_move_up bt = (bt_move_up)map.get__Comp(EMAIL_SEND_TARGETS.pos);
						if (map==map_own) {
							bt.set_sort_value(1);
						} else {
							bt.set_sort_value(i++);
						}
					}
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Ich sortiere  !!")));
				}
			}
		}


		@Override
		public Object get_Copy(Object objHelp) throws myExceptionCopy {
			try {
				return new bt_move_up(this.oEXTDB.get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.getMessage());
			}
		}


		@Override
		public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
			this.sort_value = new MyInteger(cText).get_iValue();
		}


		@Override
		public void prepare_ContentForNew(boolean bSetDefault) throws myException {
			this.sort_value=0;
		}


		@Override
		public String get_cActualMaskValue() throws myException {
			return ""+this.sort_value;
		}


		@Override
		public String get_cActualDBValueFormated() throws myException {
			return ""+this.sort_value;
		}


		@Override
		public void set_cActualMaskValue(String cText) throws myException {
			this.sort_value = new MyInteger(cText).get_iValue();
		}


		@Override
		public void set_bIsComplexObject(boolean bisComplex) {
		}


		@Override
		public boolean get_bIsComplexObject() {
			return false;
		}


		@Override
		public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
			return null;
		}


		@Override
		public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
			return null;
		}


		public MyE2EXT__DB_Component EXT_DB()							{		return this.oEXTDB;		}
		public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)			{		this.oEXTDB = oEXT_DB;	}


		
		public int get_sort_value() {
			return sort_value;
		}

		public void set_sort_value(int sort_value) throws myException		{
			DEBUG.System_println("NEUER SORT-WERT:"+sort_value+ " bei Adresse: "+this.EXT().get_oComponentMAP().get_cActualDBValueFormated(EMAIL_SEND_TARGETS.target_adress.fn()));
			this.sort_value = sort_value;
		}
		
		
	}
	
	
	private class ownActionSaveReopen extends RB_actionStandardSaveAndReopen {
		
		public ownActionSaveReopen() throws myException {
			super(new RB_KM(_TAB.email_send));
		}

		@Override
		public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(String id_record) throws myException {
			return new ES_RB_DataobjectsCollector(id_record, RB__CONST.MASK_STATUS.EDIT);		
		}

		@Override
		public RB_ModuleContainerMASK get_RB_ModuleContainerMASK() throws myException {
			return (RB_ModuleContainerMASK)ES_mask_complex_EMAIL_SEND_TARGETS.this.rb_ComponentMap_outside.rb_get_belongs_to().rb_get_belongs_to();
		}
		
	}
	

	
}