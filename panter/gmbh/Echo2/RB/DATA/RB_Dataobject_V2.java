package panter.gmbh.Echo2.RB.DATA;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

/**
 * daten-container fuer eine RB_MASK_MASE
 * @author martin
 *
 */
public class RB_Dataobject_V2 implements RB_Dataobject{

	private Rec20  							rec_20 = null;
	
	/*
	 * Records von complex-fields
	 */
	private Vector<MyRECORD_IF_FILLABLE>   	v_RECORD_IF_FILLABLE = new Vector<MyRECORD_IF_FILLABLE>();

	/*
	 * MySqlStatementBuilder, die aus den complex-fields uebergeben werden 
	 */
	private Vector<MySqlStatementBuilder>  v_STATEMENTBUILDERS = new Vector<MySqlStatementBuilder>();
	
	private RB__CONST.MASK_STATUS 			actualMASK_STATUS = null;
	
	private RB_DataobjectsCollector        rb_Dataobjects_Container_this_belongs_to = null;
	
	//schluessel unter dem das objekt bei der uebergeordneten collection registriert ist
	private RB_K  							my_key_in_collection = null;
	
	private _TAB    						tab = null;
	
	@Override
	public RB_K  getMyKeyInCollection() throws myException {
		return this.my_key_in_collection;
	}
	
	@Override
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		this.my_key_in_collection = key;
		return key;
	}


	
	public RB_Dataobject_V2(Rec20 rec, MASK_STATUS status)  throws myException {
		super();
		this.rec_20 = rec;
		this.actualMASK_STATUS = status;
		
		if (rec == null) {
			throw new myException(this," !!! Rec20 MUST be not null !!");
		}
		this.tab = this.rec_20.get_tab();
		
	}




	public RB_Dataobject_V2(_TAB p_tab) throws myException {
		super();
		if (p_tab == null) {
			throw new myException(this,"Error, empty table not allowed!");
		}
		
		this.tab = p_tab;

		this.rec_20 = new Rec20(this.tab);
		this.actualMASK_STATUS = RB__CONST.MASK_STATUS.NEW;
	}

	
	
	@Override
	public MyRECORD_NEW get_RecNEW()  throws myException  {
		return this.rec_20.gen_record_new();
	}


	@Override
	public MyRECORD_IF_RECORDS get_RecORD() throws myException {
		return (MyRECORD_IF_RECORDS)this.rec_20.gen_record(true);
	}

	
	/**
	 * 2016-06-06: neue methode um direkt auf ein myRECORD zugreifen zu koennen
	 * @param recORD
	 */
	@Override
	public MyRECORD  get_MyRECORD() throws myException {
		return this.rec_20.gen_record(true);
	}
	
	
	
	
	
	@Override
	public MyRECORD_IF_FILLABLE  rb_relevant_record_to_fill() throws myException{
		return this.rec_20;
	}
	
	
//	@Override
//	public void rb_Clear_RECNEW() throws myException {
//		this.RecORD.get_RECORD_NEW().RESET_ALL_NEWVALUES();
//	}
//	
	/**
	 * rebuilds record when its not null, otherwise exception
	 * @deprecated
	 * @throws myException
	 */
	@Override
	public void rb_Rebuild_RECORD() throws myException {
		if (!this.rec_20.is_newRecordSet()) {
			this.rec_20._rebuild();
		} else {
			throw new myException(this,"Dataobject of type new cannot be rebuilded!");
		}
	}
	
	
	/**
	 * rebuilds record when its not null, otherwise exception
	 * @throws myException
	 */
	@Override
	public void rb_RebuildRecord() throws myException {
		this.rec_20._rebuildRecord();
	}
	
	
	
	
//	@Override
//	public void RESET_ALL_NEWVALUES() throws myException {
//		this.rb_relevant_record_to_fill().RESET_ALL_NEWVALUES();
//	}
	
	
	/**
	 * hier koennen beim save-vorgang die records (new oder edit) der complex-daughter-fields geparkt werden
	 * @return
	 */
	@Override
	public Vector<MyRECORD_IF_FILLABLE> get_v_RECORD_IF_FILLABLE()
	{
		return v_RECORD_IF_FILLABLE;
	}

	@Override
	public RB__CONST.MASK_STATUS rb_MASK_STATUS()
	{
		return actualMASK_STATUS;
	}

	@Override
	public Vector<MySqlStatementBuilder> get_v_STATEMENTBUILDERS() {
		return v_STATEMENTBUILDERS;
	}

	@Override
	public void rb_set_belongs_to(RB_DataobjectsCollector dataObjectsContainer) throws myException {
		this.rb_Dataobjects_Container_this_belongs_to=dataObjectsContainer;
		
	}

	@Override
	public RB_DataobjectsCollector rb_get_belongs_to() throws myException {
		return this.rb_Dataobjects_Container_this_belongs_to;
	}


	@Override
	public void set_implicit_status_new() throws myException {
		this.rec_20._set_to_status_recnew();
	}

	public Rec20 get_rec20() {
		return rec_20;
	}
	
	
	public void set_rec20(Rec20 rec) {
		this.rec_20=rec;
	}

	public void set_actualMASK_STATUS(RB__CONST.MASK_STATUS actualMaskStatus) {
		this.actualMASK_STATUS = actualMaskStatus;
	}
	
	
	
	
	
}
