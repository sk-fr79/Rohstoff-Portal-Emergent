/**
 * panter.gmbh.indep.dataTools.RECORD2
 * @author martin
 * @date 26.11.2018
 * 
 */
package panter.gmbh.Echo2.RB.DATA;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 26.11.2018
 * Eine rec21, die gleichzeitig als RB_Dataobect fungiert
 */
public class RB_Dataobject_V22 extends Rec22  implements RB_Dataobject {

    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;

	
	/*
	 * MySqlStatementBuilder, die aus den complex-fields uebergeben werden 
	 */
	private Vector<MySqlStatementBuilder>  v_STATEMENTBUILDERS = new Vector<MySqlStatementBuilder>();
	
	private RB__CONST.MASK_STATUS 			actualMASK_STATUS = null;
	
	private RB_DataobjectsCollector        rb_Dataobjects_Container_this_belongs_to = null;
	
	//schluessel unter dem das objekt bei der uebergeordneten collection registriert ist
	private RB_K  							my_key_in_collection = null;

	/*
	 * Records von complex-fields
	 */
	private Vector<MyRECORD_IF_FILLABLE>   	v_RECORD_IF_FILLABLE = new Vector<MyRECORD_IF_FILLABLE>();


	/**
	 * @author martin
	 * @date 26.11.2018
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public RB_Dataobject_V22(_TAB p_tab, MASK_STATUS status) throws myException {
		super(p_tab);
		this.set_actualMASK_STATUS(status);
	}

	/**
	 * dataobject status new
	 * @author martin
	 * @date 16.10.2020
	 *
	 * @param p_tab
	 * @throws myException
	 */
	public RB_Dataobject_V22(_TAB p_tab) throws myException {
		super(p_tab);
		this.set_actualMASK_STATUS(MASK_STATUS.NEW);
	}

	
	public RB_Dataobject_V22(Rec22 baseRec, MASK_STATUS status) throws myException {
		super(baseRec);
		this.set_actualMASK_STATUS(status);
	}


	public RB_Dataobject_V22(_TAB p_tab, Long id, MASK_STATUS status) throws myException {
		super(p_tab);
		this._fill_id(id);
		this.set_actualMASK_STATUS(status);
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
	public RB_K  getMyKeyInCollection() throws myException {
		return this.my_key_in_collection;
	}
	
	@Override
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		this.my_key_in_collection = key;
		return key;
	}


	
	@Override
	public MyRECORD_NEW get_RecNEW()  throws myException  {
		return this.gen_record_new();
	}


	@Override
	public MyRECORD_IF_RECORDS get_RecORD() throws myException {
		return (MyRECORD_IF_RECORDS)this.gen_record(true);
	}


	@Override
	public void set_implicit_status_new() throws myException {
		this._set_to_status_recnew();
	}


	/**
	 * 2016-06-06: neue methode um direkt auf ein myRECORD zugreifen zu koennen
	 * @param recORD
	 */
	@Override
	public MyRECORD  get_MyRECORD() throws myException {
		return this.gen_record(true);
	}
	

	
	@Override
	public MyRECORD_IF_FILLABLE  rb_relevant_record_to_fill() throws myException{
		return this;
	}
	

	/**
	 * rebuilds record when its not null, otherwise exception
	 * @deprecated
	 * @throws myException
	 */
	@Override
	public void rb_Rebuild_RECORD() throws myException {
		if (!this.is_newRecordSet()) {
			this._rebuild();
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
		this._rebuildRecord();
	}
	
	

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
	
	
	public void set_actualMASK_STATUS(RB__CONST.MASK_STATUS actualMaskStatus) {
		this.actualMASK_STATUS = actualMaskStatus;
	}
	
	

	public RB_TransportHashMap getTransportHashMap() {
		return m_tpHashMap;
	}

	public RB_Dataobject_V22 _setTransportHashMap(RB_TransportHashMap p_tpHashMap) {
		this.m_tpHashMap = p_tpHashMap;
		return this;
	}
	
	
}
