package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_ComponentMAP_Factory4Records {
	
	private E2_ComponentMAP  E2_ComponentMap_This_BelongsTo = null;

	public E2_ComponentMAP_Factory4Records() {
		super();
	}
	
	
	public MyRECORD_IF_FILLABLE  get_RecordNew() throws myException {
		if (this.E2_ComponentMap_This_BelongsTo!=null && S.isFull(this.E2_ComponentMap_This_BelongsTo.get_oSQLFieldMAP().get_cMAIN_TABLE())) {
			return new MyRECORD_NEW(this.E2_ComponentMap_This_BelongsTo.get_oSQLFieldMAP().get_cMAIN_TABLE());
		} else {
			throw new myException(this, "Cannot create MyRECORD_NEW: No tablename present");
		}
	}
	
	/**
	 * 
	 * @return s RECORD_xxx for main-table
	 * @throws myException
	 */
	public abstract MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException;


	public E2_ComponentMAP get_E2_ComponentMap_This_BelongsTo() {
		return E2_ComponentMap_This_BelongsTo;
	}


	public void set_E2_ComponentMap_This_BelongsTo(E2_ComponentMAP e2_ComponentMap_This_BelongsTo) {
		E2_ComponentMap_This_BelongsTo = e2_ComponentMap_This_BelongsTo;
	}
	
}
