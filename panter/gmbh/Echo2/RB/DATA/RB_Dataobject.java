package panter.gmbh.Echo2.RB.DATA;

import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.IF.IF_RB_Part;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * daten-container fuer eine RB_MASK_MASE
 * @author martin
 *
 */
public interface RB_Dataobject extends IF_RB_Part<RB_DataobjectsCollector>{

	public RB_K  getMyKeyInCollection() throws myException;
	
	public RB_K setMyKeyInCollection(RB_K key) throws myException;

	
	public MyRECORD_NEW get_RecNEW() throws myException;


	public MyRECORD_IF_RECORDS get_RecORD() throws myException;

	
	public void set_implicit_status_new() throws myException;
	
	/**
	 * 2016-06-06: neue methode um direkt auf ein myRECORD zugreifen zu koennen
	 */
	public MyRECORD  get_MyRECORD() throws myException;
	
	
//	public void set_RecORD(MyRECORD_IF_RECORDS recORD);
	
	public MyRECORD_IF_FILLABLE  rb_relevant_record_to_fill() throws myException;
	
	
	
	/**
	 * rebuilds record when its not null, otherwise exception
	 * @deprecated 
	 * @throws myException
	 */
	public void rb_Rebuild_RECORD() throws myException;
	
	
	/**
	 * rebuilds record when its not null, otherwise exception
	 * @throws myException
	 */
	public void rb_RebuildRecord() throws myException;

	
	/**
	 * hier koennen beim save-vorgang die records (new oder edit) der complex-daughter-fields geparkt werden
	 * @return
	 */
	public Vector<MyRECORD_IF_FILLABLE> get_v_RECORD_IF_FILLABLE();

	public RB__CONST.MASK_STATUS rb_MASK_STATUS();
	
	public Vector<MySqlStatementBuilder> get_v_STATEMENTBUILDERS();

	
	/**
	 * abkuerzung zum Rec20-object
	 * @return
	 * @throws myException
	 */
	public default Rec20 rec20() throws myException {
		if (this instanceof RB_Dataobject_V2) {
			return ((RB_Dataobject_V2)this).get_rec20();
		}
		
		//weitere variante mit Rec21DataObject
		if (this instanceof RB_Dataobject_V21) {
			return (RB_Dataobject_V21)this;
		}
		
		//weitere variante mit Rec21DataObject
		if (this instanceof RB_Dataobject_V22) {
			return (RB_Dataobject_V22)this;
		}
	
		
		throw new myException(this,"get_rec20() only possible on RB_Dataobject_V2 / Rec21DataObject !!");
	}

	
	/**
	 * abkuerzung zum Rec21-object (falls vorhanden)
	 * @return
	 * @throws myException
	 */
	public default Rec21 rec21() throws myException {
		if (this instanceof RB_Dataobject_V2 && ((RB_Dataobject_V2)this).get_rec20() instanceof Rec21) {
			return (Rec21)((RB_Dataobject_V2)this).get_rec20();
		}
		//weitere variante mit Rec21DataObject
		if (this instanceof RB_Dataobject_V21) {
			return (RB_Dataobject_V21)this;
		}
		//weitere variante mit Rec22-DataObject
		if (this instanceof RB_Dataobject_V22) {
			return (RB_Dataobject_V22)this;
		}
		
		throw new myException(this,"get_rec21() only possible on RB_Dataobject_V2  / Rec21DataObject where Rec21 is used!!");
	}


	
	/**
	 * 20180105: martin (nur bei V2 (Rec20-based erlaubt) 
	 * @return true, when new-dataset
	 * @throws myException
	 */
	public default boolean isStatusNew() throws myException {
		return this.rb_MASK_STATUS().isStatusNew();
	}
	

	/**
	 * 20180105: martin (nur bei V2 (Rec20-based erlaubt) 
	 * @return true, when new-dataset
	 * @throws myException
	 */
	public default boolean isStatusCanBeSaved() throws myException {
		return this.rb_MASK_STATUS().isStatusCanBeSaved();
	}

	
	
	
	public void set_actualMASK_STATUS(RB__CONST.MASK_STATUS actualMaskStatus);

}
