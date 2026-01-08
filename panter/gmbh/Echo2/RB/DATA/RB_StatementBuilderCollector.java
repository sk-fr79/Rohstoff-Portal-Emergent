package panter.gmbh.Echo2.RB.DATA;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MASK_DUMMY_KEY;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder_primitiv;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

/**
 * sammelt die MySqlStatementBuilder-objekte jeweils aus einer RB_MASK und bekommt aus KEY den Mask-Key aus der RB_MASK_HM
 * Container-klasse 
 * @author martin
 *
 */
public class RB_StatementBuilderCollector implements Iterable<Vector<MySqlStatementBuilder>>{

	private LinkedHashMap<RB_KM,Vector<MySqlStatementBuilder>>  hmVectorsOfStatementBuilders = new LinkedHashMap<RB_KM,Vector<MySqlStatementBuilder>>();
	
	public RB_StatementBuilderCollector() {
		super();
	}
	
	public RB_StatementBuilderCollector(Vector<MySqlStatementBuilder> v_statements) throws myException {
		RB_KM key = new RB_MASK_DUMMY_KEY();
		for (MySqlStatementBuilder sb: v_statements) {
			this.add(key, sb);
		}
	}
	
	
	public Vector<MySqlStatementBuilder> get(RB_KM key) {
		return this.hmVectorsOfStatementBuilders.get(key);
	}

	
	
	public void add(RB_KM maskKey, MySqlStatementBuilder stmb) {
		if (this.hmVectorsOfStatementBuilders.get(maskKey)==null) {
			this.hmVectorsOfStatementBuilders.put(maskKey, new Vector<MySqlStatementBuilder>());
		}
		
		this.hmVectorsOfStatementBuilders.get(maskKey).add(stmb);
	}

	
	public void addAll(RB_KM maskKey, Vector<MySqlStatementBuilder> v_stmb) {
		if (this.hmVectorsOfStatementBuilders.get(maskKey)==null) {
			this.hmVectorsOfStatementBuilders.put(maskKey, new Vector<MySqlStatementBuilder>());
		}
		
		this.hmVectorsOfStatementBuilders.get(maskKey).addAll(v_stmb);
	}

	
	
	
	public boolean bCheck_4_concurrent_changes_all_OK() throws myException {
		boolean bRueck = true;

		Vector<MyRECORD_IF_FILLABLE> vRecSets = new Vector<MyRECORD_IF_FILLABLE>();
		for (Vector<MySqlStatementBuilder> vStmb: this.hmVectorsOfStatementBuilders.values()) {
			for (MySqlStatementBuilder stmb: vStmb) {
				if (stmb.get_RecordCorrelated()!=null) {
					vRecSets.add(stmb.get_RecordCorrelated());
				}
			}
		}
		
		HashMap<String, String> hmComp = new HashMap<String, String>();

		for (MyRECORD_IF_FILLABLE record: vRecSets) {
			if (record instanceof MyRECORD_IF_RECORDS) {
				MyRECORD_IF_RECORDS recActual = ((MyRECORD_IF_RECORDS)record).build_NEW_INSTANCE_ACTUAL_DATABASEVALUES();
				
				MyRECORD recOrig = 	(MyRECORD) record;
				MyRECORD recKopie = (MyRECORD) recActual;
	
				recOrig.compareTo(recKopie, hmComp);
			}
		}
		
		if (hmComp.size()>0) {
			if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES()) {
				for (String cHASH: hmComp.keySet()) {
					System.out.println(S.NN(cHASH)+" -------- "+S.NN(hmComp.get(cHASH)));
				}
			}
			bRueck = false;
		}
		
		return bRueck;
	}

	
	
	/**
	 * 
	 * @param maskKey
	 * @param tableName
	 * @return aus der Teilmenge der statementbuilder zur Maske maskKey alle statementBuilder zur Tabelle tablename
	 * @throws myException wenn der maskkey nicht enthalten ist
	 */
	public Vector<MySqlStatementBuilder> get_vAllStatements_from_Table(RB_KM maskKey, String tableName) throws myException{
		if (this.hmVectorsOfStatementBuilders==null) {
			return null;
		} else {
			Vector<MySqlStatementBuilder> vRueck = new Vector<MySqlStatementBuilder>();
			Vector<MySqlStatementBuilder> vStatementBuilders = this.hmVectorsOfStatementBuilders.get(maskKey);
		
			if (vStatementBuilders==null) {
				throw new myException(this,"maskkey not found in statementBuilder-collection");
			}
			for (MySqlStatementBuilder stmb: vStatementBuilders) {
				if (stmb.get_RecordCorrelated()!=null && stmb.get_RecordCorrelated().get_TABLENAME().equals(tableName)) {
					vRueck.add(stmb);
				}
			}
			return vRueck;
		}
	}



	@Override
	public Iterator<Vector<MySqlStatementBuilder>> iterator() {
		return this.hmVectorsOfStatementBuilders.values().iterator();
	}
	
	
	
    public Vector<String> get_v_sqlstatements() throws myException {
		//jetzt die sql-statements generieren
		Vector<String>   vSQL = new Vector<String>();
		
		for (Vector<MySqlStatementBuilder> vSqlStatementBuilder: this) {
			for (MySqlStatementBuilder stmb: vSqlStatementBuilder) {
				if (stmb.get_RecordCorrelated() != null) {
					if (stmb.get_RecordCorrelated() instanceof MyRECORD_IF_RECORDS) {
						//update
						MyRECORD_IF_RECORDS rec = (MyRECORD_IF_RECORDS)stmb.get_RecordCorrelated();
						vSQL.add(stmb.get_CompleteUPDATEString(	rec.get_TABLENAME(), 
																bibE2.cTO(), 
																rec.get_PRIMARY_KEY_NAME()+"="+rec.get_PRIMARY_KEY_UF(), 
																bibVECTOR.get_Vector(rec.get_PRIMARY_KEY_NAME())));
						
					} else if (stmb.get_RecordCorrelated() instanceof MyRECORD_IF_FILLABLE) {
						//insert-records
						MyRECORD_IF_FILLABLE rec = (MyRECORD_IF_FILLABLE)stmb.get_RecordCorrelated();
						//hier den sequencer einbauen
						stmb.addSQL_Paar(rec.get_PRIMARY_KEY_NAME(), "SEQ_"+rec.get_TABLENAME().substring(3)+".NEXTVAL");
						
						vSQL.add(stmb.get_CompleteInsertString(rec.get_TABLENAME(),bibE2.cTO()));
					
					} else {
						throw new myException(this,"dataobjects_to_database(): undefined Interface !");
					}
				} else {
					if (stmb instanceof MySqlStatementBuilder_primitiv) {
						vSQL.add( ((MySqlStatementBuilder_primitiv)stmb).get_SQL_Single());
					} else {
						throw new myException(this,"dataobjects_to_database(): undefined Statementbuilder !");
					}
				}
			}
		}
		return vSQL;
    }

	
    public int size() {
    	return this.hmVectorsOfStatementBuilders.size();
    }
    
}
