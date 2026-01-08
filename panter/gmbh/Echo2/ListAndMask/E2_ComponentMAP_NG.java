package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public abstract class E2_ComponentMAP_NG<E extends MyRECORD> extends E2_ComponentMAP
{
	private E 	oRecord = null;
	
	public E2_ComponentMAP_NG()	{
		super();
	}


	
	public E2_ComponentMAP_NG(SQLFieldMAP sqlfieldMAP) {
		super(sqlfieldMAP);
	}

	
	/**
	 * umsetzer von z.b. MyRecord auf RECORD_ADRESSE  return RECORD_ADRESSE.build_Instance(MyRECORD recordOrig);
	 * @param ownRecord
	 * @return
	 * @throws myException
	 */
	public abstract E create__LocalRecord() throws myException;
	public abstract String get__BaseTableName() throws myException;
	public abstract String get__BaseIDField() throws myException;

	
	/**
	 * 
	 * @return unformated rowId from SQLResultMAP or internal Record, null when nothing exists
	 * @throws myException
	 */
	public String get__ID_BASE_TABLE() throws myException {
		if (this.oRecord != null) {
			return this.oRecord.get_UnFormatedValue(this.get__BaseIDField());
		} else {
			if (this.get_bIs_Neueingabe()) {
				return null;
			} else {
				return this.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			}
		}
	}
	

	
	public void set__LocalRecord(E oRec) throws myException {
		this.oRecord = oRec;
	}
	
	
	/**
	 * 
	 * @param bCreateWhenEmpty
	 * @return s record (null when not defined), if bCreateWhenEmpty then tries to create first
	 * @throws myException
	 */
	public E get__LocalRecord(boolean bCreateWhenEmpty) throws myException {
		if (bCreateWhenEmpty) {
			if (this.oRecord==null) {
				this.oRecord = this.create__LocalRecord();
			}
		}
		return this.oRecord;
	}
	
	
	
	public abstract Object get_Copy(Object objHelp) throws myExceptionCopy;

	
	
	
	
}
