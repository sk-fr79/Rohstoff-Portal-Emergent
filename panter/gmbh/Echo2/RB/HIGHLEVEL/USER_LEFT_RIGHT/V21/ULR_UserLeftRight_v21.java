package panter.gmbh.Echo2.RB.HIGHLEVEL.USER_LEFT_RIGHT.V21;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Complex;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_CB2;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_Container2;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_IF_wrap_Component;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder_Simple;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_user;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_User;


public abstract class ULR_UserLeftRight_v21 extends LR_Container2 implements IF_RB_Component_Complex, LR_IF_wrap_Component {

	
	// der pool an moeglichen usern
	private RecList21_User					rl_user_pool = null;
	
	private SqlStringExtended				selectUserList 							= null; //SqlStringExt - Objekt, definiert welche user zur verfuegung gestellt werden (die auswahl, z.b. nur buerouser)
	private IF_Field    					ref_pk_field_main_table_in_crosstable 	= null;	//z.B. laufzettel_user.id_laufzettel
	
	private Vector<Rec21_user>  			rec_defined_crosstable_after_load 		= new Vector<>();   //hier werden die nach dem laden vorhandenen RECORD_<crosstable> abgelegt
	private Vector<RB_Validator_Component> 	vVALIDATORS_4_INPUT 					= new Vector<RB_Validator_Component>();
	
	private RB_KF       					key 									= null;

	
	public ULR_UserLeftRight_v21() throws myException {
		super();
	}

	
	// MUSS ausggefuehrt werden
	public void _init(SqlStringExtended _selectUserList, IF_Field _ref_pk_field_main_table_in_crosstable) throws myException {
		this.selectUserList = 							_selectUserList; 
		this.rl_user_pool = new RecList21_User(selectUserList);

		this.ref_pk_field_main_table_in_crosstable = 	_ref_pk_field_main_table_in_crosstable;
	}

	
	@Override
	public abstract Vector<MyRECORD_IF_FILLABLE> maskContents_Transfer_To_Record_And_Prepare_Save(MyE2_MessageVector oMV, RB_ComponentMap rb_maskThisBelongsTo) throws myException;

	
	@Override
	public Vector<MySqlStatementBuilder> get_vSQL_StatementBuilders_Others(MyE2_MessageVector oMV, RB_ComponentMap rb_maskThisBelongsTo) throws myException {
		Vector<MySqlStatementBuilder> v_statemend = new Vector<>();
		
		_TAB crossTable = _TAB.find_Table(this.ref_pk_field_main_table_in_crosstable.fullTableName());
		
		//alle bisherigen loeschen
		for (Rec21 rec: this.rec_defined_crosstable_after_load) {
			String del_string = "DELETE FROM "+bibE2.cTO()+"."+crossTable.fullTableName()+" WHERE "+crossTable.keyFieldName()+"="+rec.get_ufs_dbVal(crossTable.key());
			
			//debug
			DEBUG.System_println(del_string);
			
			MySqlStatementBuilder_Simple sim = new MySqlStatementBuilder_Simple(del_string);
			v_statemend.add(sim);
		}
		
		return v_statemend;
	}

	
	public abstract Vector<Rec21_user> query_rosstable_records_to_main_id(String id_bordercrossing) throws myException; 
	
	public abstract Rec21_user extract_user_from_crosstable(Rec21_user rec_cross_tab) throws myException;

	@Override
	public abstract Component component_4_list(LR_CB2 cb, LR_ObjectExtender place_4_everything) throws myException;

	@Override
	public abstract void rb_set_db_value_manual(String id_main_table) throws myException;

	@Override
	public MyE2_MessageVector makeBindingDaughterRecords_to_MotherTable(RB_Dataobject DataObjectMother, Vector<MyRECORD_IF_FILLABLE> v_DaughterRecords) throws myException {
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		String cAddonField = this.ref_pk_field_main_table_in_crosstable.fn();
		String cAddonVal = null;
		if (DataObjectMother.rb_relevant_record_to_fill() instanceof MyRECORD_IF_RECORDS) {
			cAddonVal = ((Rec21)DataObjectMother.rb_relevant_record_to_fill()).get_ufs_dbVal(this.rb_KF().get_data_field()) ;//get_UnFormatedValue(this.rb_KF().get_REALNAME());
		} else {
			cAddonVal = "SEQ_"+DataObjectMother.get_RecNEW().get_TABLE_NAME().substring(3)+".CURRVAL";
		}

		for (MyRECORD_IF_FILLABLE rec: v_DaughterRecords) {
			rec.get_hm_Field_Value_pairs_from_outside().put(cAddonField, cAddonVal);
		}
		
		return oMV;
	}

	
	public void set_selectUserList(SqlStringExtended _selectUserList) {
		this.selectUserList = _selectUserList;
	}

	public void set_ref_pk_field_main_table_in_crosstable(IF_Field _ref_pk_field_main_table_in_crosstable) {
		this.ref_pk_field_main_table_in_crosstable = _ref_pk_field_main_table_in_crosstable;
	}

	
	public Vector<Rec21_user> get_vector_defined_crosstable_after_load() {
		return rec_defined_crosstable_after_load;
	}

	@Override
	public LR_ObjectExtender generate_object_extender() throws myException {
		return null;
	}


	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		String id_main_table = null;
		
		if (((Rec21)dataObject.rec21())!=null) {
			//sonst neueintrag
			id_main_table = dataObject.rec21().getUfs(this.rb_KF().get_data_field());
		}
		this.rb_set_db_value_manual(id_main_table);
	}
	

	
	@Override
	public RB_KF rb_KF() throws myException {
		return this.key;
	}

	@Override
	public void set_rb_RB_K(RB_KF _key) throws myException {
		this.key=_key;
	}


	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}

	@Override
	public void mark_Neutral() throws myException {
		
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}


	@Override
	public void set_bEnabled_For_Edit(boolean Enabled) throws myException {
		for (LR_CB2 cb: this.get_v_cb_left() ) {
			cb.set_bEnabled_For_Edit(Enabled);
		}
		for (LR_CB2 cb: this.get_v_cb_right() ) {
			cb.set_bEnabled_For_Edit(Enabled);
		}
	}


	public RecList21_User get_rl_user_pool() {
		return rl_user_pool;
	}

}
