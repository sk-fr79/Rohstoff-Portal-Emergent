package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.M2N;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_M2N;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_M2N;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class RECLIST_M2N_ext extends RECLIST_M2N {

	private _TAB tab_1 = null;
	private _TAB tab_2 = null;
	
	
	public RECLIST_M2N_ext(_TAB tab1, _TAB tab2) throws myException {
		this(tab1,tab2,null,null);
	}
	

	public RECLIST_M2N_ext(_TAB tab1, _TAB tab2, String id_1, String id_2) throws myException {
		super();
		
		this.tab_1 = tab1;
		this.tab_2 = tab2;
		
		SEL sel = new SEL(_TAB.m2n).FROM(_TAB.m2n).WHERE(new vgl(M2N.table_base_1, tab1.baseTableName()))
													.AND(new vgl(M2N.table_base_2, tab2.baseTableName()));
		
		if (S.isFull(id_1)) {
			sel.AND(new vgl(M2N.table_id_1, id_1));
		}
		if (S.isFull(id_2)) {
			sel.AND(new vgl(M2N.table_id_2, id_2));
		}
		
		this.set_cQueryString(sel.s());
		this.REFRESH();
		
	}

	
	
	
	/**
	 * liefert einen vector aus Long-Werten der gefundenen ID_1 - Werten der relation
	 * @return
	 * @throws myException
	 */
	public Vector<Long> v_id_l_tab1() throws myException {
		Vector<Long> v_ids = new Vector<Long>();
		v_ids.addAll(this.get_TABLE_ID_1_hmLong(0l).values());
		return v_ids;
	}

	/**
	 * liefert einen vector aus Long-Werten der gefundenen ID_2 - Werten der relation
	 * @return
	 * @throws myException
	 */
	public Vector<Long> v_id_l_tab2() throws myException {
		Vector<Long> v_ids = new Vector<Long>();
		v_ids.addAll(this.get_TABLE_ID_2_hmLong(0l).values());
		return v_ids;
	}
	
	/**
	 * liefert einen vector aus UF-String-Werten der gefundenen ID_1 - Werten der relation
	 * @return
	 * @throws myException
	 */
	public Vector<String> v_id_s_tab1() throws myException {
		Vector<String> v_ids = new Vector<String>();
		v_ids.addAll(this.get_TABLE_ID_1_hmString_UnFormated("").values());
		return v_ids;
	}

	/**
	 * liefert einen vector aus UF-String-Werten der gefundenen ID_2 - Werten der relation
	 * @return
	 * @throws myException
	 */
	public Vector<String> v_id_s_tab2() throws myException {
		Vector<String> v_ids = new Vector<String>();
		v_ids.addAll(this.get_TABLE_ID_2_hmString_UnFormated("").values());
		return v_ids;
	}

	
	public MyE2_MessageVector  clear_not_referenced_records() throws myException {
		MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
		
		if (this.tab_1 == null || this.tab_2 == null) {
			throw new myException(this,"!!! clearing only allowed with setted tables !");
		}
		
		String cSQL = "DELETE FROM "+bibE2.cTO()+"."+_TAB.m2n.fullTableName()+" WHERE "+
						new vgl(M2N.table_base_1,this.tab_1.baseTableName()).s()+" AND "+
						new vgl(M2N.table_base_2,this.tab_2.baseTableName()).s()+" AND "+
						"( "+
						  "( "+
							  M2N.table_id_1+" NOT IN (SELECT "+this.tab_1.keyFieldName()+" FROM "+this.tab_1.fullTableName()+")"+
						  ") OR ("+
						  	  M2N.table_id_2+" NOT IN (SELECT "+this.tab_2.keyFieldName()+" FROM "+this.tab_2.fullTableName()+")"+
						  ")"+
						")"; 
						  		
		
		DEBUG.System_println(cSQL);
		boolean b_ok= bibDB.ExecSQL(cSQL, true);
		if (!b_ok) {
			mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Bereinigen der M2N-Zuordnungen!")));
		}
		
		this.REFRESH();
		
		return mv_rueck;
	}
	
	/**
	 * loescht alle doppelt vorkommenden werte
	 */
	public MyE2_MessageVector clear__doubleValues() throws myException {
		Vector<String> v_existing_combis = new Vector<String>();
		String trenn_zeichen = "@##@";
		
		MyE2_MessageVector  mv_rueck = new MyE2_MessageVector();
		
		int iCountDeleted = 0;
		
		for (RECORD_M2N r_mn: this) {
			if (	S.isFull(r_mn.get_TABLE_BASE_1_cUF_NN("")) && 
					S.isFull(r_mn.get_TABLE_BASE_2_cUF_NN("")) &&
					S.isFull(r_mn.get_TABLE_ID_1_cUF_NN(""))   &&
					S.isFull(r_mn.get_TABLE_ID_2_cUF_NN(""))  
					) {
				String cTest = 	 trenn_zeichen+r_mn.get_TABLE_BASE_1_cUF_NN("")
								+trenn_zeichen+r_mn.get_TABLE_BASE_2_cUF_NN("")
								+trenn_zeichen+r_mn.get_TABLE_ID_1_cUF_NN("")
								+trenn_zeichen+r_mn.get_TABLE_ID_2_cUF_NN("")
								+trenn_zeichen;
				if (v_existing_combis.contains(cTest)) {
					mv_rueck.add_MESSAGE(r_mn.DELETE());
					iCountDeleted ++;
				} else {
					v_existing_combis.add(cTest);
				}
			}
		}
		
		this.REFRESH();
		
		if (mv_rueck.get_bIsOK() && iCountDeleted>0) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurden doppelte Zuordnungen gelöscht: Anzahl: ",true, ""+iCountDeleted,false)));
		}
		
		return mv_rueck;
		
	}

}
