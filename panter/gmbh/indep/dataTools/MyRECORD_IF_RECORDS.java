package panter.gmbh.indep.dataTools;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;


/**
 * 2015-02-04: ausgelagert aus der klasse MyRECORDS
 * @author martin
 *
 */
public interface MyRECORD_IF_RECORDS  extends MyRECORD_IF_FILLABLE {
	
	public HashMap<String, MyRECORD.DATATYPES> get_HM_FIELDNAMES() throws myException;
	public MyRECORD_NEW  						get_RECORD_NEW() throws myException;
//	public String  								get_PRIMARY_KEY_NAME();
//	public String  								get_TABLENAME();
	public String 								get_PRIMARY_KEY_UF() throws myException;
	public long 								get_PRIMARY_KEY_VALUE() throws myException;
	public String 								get_SQL_UPDATE_STATEMENT(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException;
	public String 								get_SQL_UPDATE_STD() throws myException;
	public MyE2_MessageVector 					UPDATE(boolean bCommit) throws myException;
	public MyE2_MessageVector 					UPDATE(Vector<String> vFieldsNotInUpdate, boolean bOnlyChangedFields) throws myException;
	public MyE2_MessageVector 					DELETE() throws myException;
	public String 								get_DELETE_STATEMENT()  throws myException;
	public void 								REBUILD() throws myException;
	
	public HashMap<String, MyMetaFieldDEF> get_hm_FieldMetaDefs();
	public void set_Tablename_To_FieldMetaDefs(String tablename);
	public void set_cSQL_4_Build(String sql_4_Build);
	public void set_oConn(MyConnection conn);
	
	public MyRECORD_IF_RECORDS 					build_NEW_INSTANCE_ACTUAL_DATABASEVALUES() throws myException;

	
	/**
	 * 2016-11-09
	 * @throws myException
	 */
	public default void set_Tablename_To_FieldMetaDefs() throws myException{
		for (MyMetaFieldDEF def: this.get_hm_FieldMetaDefs().values()) {
			def.set_Tablename(this.get_TABLENAME(), false);
		}
	}

}
