package panter.gmbh.Echo2.RB.IF;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder_Simple;
import panter.gmbh.indep.exceptions.myException;

/**
 * interface fuer komplexe maskenobjekte, die beim speichervorgang eine komplette 
 * abfolge von sql-statements uebergeben
 * @author martin
 *
 */
public interface IF_RB_Component_Complex extends IF_RB_Component{
	
	
	public Vector<MyRECORD_IF_FILLABLE>   maskContents_Transfer_To_Record_And_Prepare_Save(MyE2_MessageVector oMV, RB_ComponentMap rb_maskThisBelongsTo) throws myException;
	
	/**
	 * 
	 * @return Vector mit statementbuilders ausserhalb der nomenklatur insert oder update (z.b. Deletes o.ä.)
	 * @throws myException
	 */
	public Vector<MySqlStatementBuilder>  get_vSQL_StatementBuilders_Others(MyE2_MessageVector oMV, RB_ComponentMap rb_maskThisBelongsTo) throws myException;
	
	/**
	 * 
	 * @param recordMother
	 * @param v_DaughterRecords
	 * @throws myException
	 */
	public MyE2_MessageVector makeBindingDaughterRecords_to_MotherTable(RB_Dataobject DataObjectMother, Vector<MyRECORD_IF_FILLABLE> v_DaughterRecords) throws myException;
	
}
