package panter.gmbh.Echo2.RB.DATA;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

/**
 * daten-container fuer eine RB_MASK_MASE
 * @author martin
 *
 */
public class RB_Dataobject_V1 implements RB_Dataobject{

	/*
	 *  MyRECORD_NEW muss immer vorhanden sein, recORD nur bei editier-masken (bei new-Zustaenden leer)
	 */
	private MyRECORD_NEW  					RecNEW = null;
	private MyRECORD_IF_RECORDS  			RecORD = null;
	
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
	
	
	@Override
	public RB_K  getMyKeyInCollection() throws myException {
		return this.my_key_in_collection;
	}
	
	@Override
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		this.my_key_in_collection = key;
		return key;
	}


	
	public RB_Dataobject_V1(MyRECORD_NEW recNEW, MyRECORD_IF_RECORDS recORD, RB__CONST.MASK_STATUS status)  throws myException {
		super();
		this.RecNEW = recNEW;
		this.RecORD = recORD;
		this.actualMASK_STATUS = status;
	}

	public RB_Dataobject_V1(MyRECORD_NEW recNEW)  throws myException {
		super();
		this.RecNEW = recNEW;
		this.RecORD = null;
		this.actualMASK_STATUS = RB__CONST.MASK_STATUS.NEW;
	}

	
	public RB_Dataobject_V1(MyRECORD_IF_RECORDS recORD, RB__CONST.MASK_STATUS status)  throws myException {
		super();
		this.RecNEW = new MyRECORD_NEW(recORD.get_TABLENAME());
		this.RecORD = recORD;
		this.actualMASK_STATUS = status;
	}

	
	public RB_Dataobject_V1(String tablename) throws myException {
		super();
		this.RecNEW = new MyRECORD_NEW(tablename);
		this.RecORD = null;
		this.actualMASK_STATUS = RB__CONST.MASK_STATUS.NEW;
	}

	
	@Override
	public MyRECORD_NEW get_RecNEW() {
		return RecNEW;
	}

//	@Override
//	public void set_RecNEW(MyRECORD_NEW recNEW) {
//		RecNEW = recNEW;
//	}

	@Override
	public MyRECORD_IF_RECORDS get_RecORD() {
		return RecORD;
	}

	
	/**
	 * 2016-06-06: neue methode um direkt auf ein myRECORD zugreifen zu koennen
	 * @param recORD
	 */
	@Override
	public MyRECORD  get_MyRECORD() throws myException {
		if (RecORD instanceof MyRECORD) {
			return (MyRECORD)RecORD;
		}
		throw new myException("RB_DataObject: Strange Error: no Object of typ MyRECORD");
	}
	
	

	
	@Override
	public MyRECORD_IF_FILLABLE  rb_relevant_record_to_fill() {
		if (this.actualMASK_STATUS.isStatusNew()) {
			return this.RecNEW;
		} else {
			return this.RecORD;
		}
	}
	
	
//	@Override
//	public void rb_Clear_RECNEW() throws myException {
//		this.RecNEW.RESET_ALL_NEWVALUES();
//	}
	
	/**
	 * rebuilds record when its not null, otherwise exception
	 * @throws myException
	 */
	@Override
	public void rb_Rebuild_RECORD() throws myException {
		if (this.RecORD!=null) {
			this.RecORD.REBUILD();
		} else {
			throw new myException(this,"RecORD is null !");
		}
	}
	
	/**
	 * rebuilds record when its not null, otherwise exception
	 * @throws myException
	 */
	@Override
	public void rb_RebuildRecord() throws myException {
		this.rb_Rebuild_RECORD();
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

	
//	/**
//	 * 2016-07-20: setter hinzuefuegt
//	 * @param recNEW
//	 */
//	@Override
//	public void rb_set_recNEW(MyRECORD_NEW recNEW) {
//		this.RecNEW = recNEW;
//	}

	
//	/**
//	 * 2016-07-20: setter hinzuefuegt
//	 * @param recORD
//	 */
//	@Override
//	public void rb_set_recORD(MyRECORD_IF_RECORDS recORD) {
//		this.RecORD = recORD;
//	}

	@Override
	public void set_implicit_status_new() throws myException {
		this.RecORD=null;
	}
	
	public void set_actualMASK_STATUS(RB__CONST.MASK_STATUS actualMaskStatus) {
		this.actualMASK_STATUS = actualMaskStatus;
	}

}
