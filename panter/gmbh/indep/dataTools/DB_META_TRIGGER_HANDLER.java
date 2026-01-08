package panter.gmbh.indep.dataTools;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
//import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class DB_META_TRIGGER_HANDLER {

	public enum STATUS_TRIGGER {
		VALID
		,MISSING
		,INVALID
	}
	
	private String trigger_count = "SELECT count(*) FROM   ALL_OBJECTS WHERE  UPPER(OBJECT_NAME) = UPPER('#TRIGGERNAME#') AND    OBJECT_TYPE = 'TRIGGER' AND UPPER(OWNER)=UPPER('"+bibE2.cTO()+"')";
	private String trigger_query = "SELECT * FROM   ALL_OBJECTS WHERE  UPPER(OBJECT_NAME) = UPPER('#TRIGGERNAME#') AND    OBJECT_TYPE = 'TRIGGER' AND UPPER(OWNER)=UPPER('"+bibE2.cTO()+"')";
	private String trigger_del   = "DROP TRIGGER #TRIGGERNAME#";

	
	private String trigger_name = null;
	private String trigger_def  = null;             //sql-anweisung, um den trigger zu erzeugen

	private STATUS_TRIGGER  actual_status = null;
	private String          trigger_praefix = "TRIGG_";    //namens-praefix fuer die erzeugten trigger
	private boolean         b_add_mandant_id = true;

	
	/**
	 * Datenbankklasse, um trigger zu pruefen und gegebenfalls neu zu erstellen
	 */
	public DB_META_TRIGGER_HANDLER() {
		super();
	}

	
	public DB_META_TRIGGER_HANDLER _set_TriggerName(String name) {
		this.trigger_name = name;
		return this;
	}
	
	
	public DB_META_TRIGGER_HANDLER _set_TriggerDef(String sql_def) throws myException {
		this.trigger_def = sql_def;
		
		if (!this.trigger_def.contains("#TRIGGERNAME#")) {
			throw new myException(this,"Triggerdef MUST contain the String #TRIGGERNAME# !");
		}
		
		return this;
	}
	
	public DB_META_TRIGGER_HANDLER _set_TriggerPraefix(String praefix) {
		this.trigger_praefix = praefix;
		return this;
	}

	public DB_META_TRIGGER_HANDLER _set_add_mandant_id(boolean add_mandant_id) {
		this.b_add_mandant_id = add_mandant_id;
		return this;
	}

	
	public DB_META_TRIGGER_HANDLER _init() throws myException {
		if (S.isEmpty(this.trigger_name) || S.isEmpty(this.trigger_def)) {
			throw new myException(this,"Empty name or def in trigger not allowed !");
		}
		
		String complete_name = this.trigger_praefix+this.trigger_name+(this.b_add_mandant_id?bibALL.get_ID_MANDANT():"");
		String complete_query = bibALL.ReplaceTeilString(this.trigger_count, "#TRIGGERNAME#", complete_name);
		
		if (bibDB.EinzelAbfrage(complete_query).equals("0")) {
			this.actual_status = STATUS_TRIGGER.MISSING;
		} else {
		
			try {
				MyRECORD rec = new MyRECORD(bibALL.ReplaceTeilString(this.trigger_query, "#TRIGGERNAME#", complete_name));
				
				if (rec.size()>0) { 
					
					String status = rec.ufs("STATUS","MISSING").toUpperCase();
					
					if (status.equals("VALID")) {
						this.actual_status=STATUS_TRIGGER.VALID;
					} else if (status.equals("INVALID")){
						this.actual_status=STATUS_TRIGGER.INVALID;
					} else {
						this.actual_status=STATUS_TRIGGER.MISSING;
					}
				}
			} catch (Exception e) {
				this.actual_status = STATUS_TRIGGER.MISSING;
				e.printStackTrace();
			}
		}
		return this;
	}
	
	
	public DB_META_TRIGGER_HANDLER _generate_trigger(MyE2_MessageVector mv) {
		String complete_name = this.trigger_praefix+this.trigger_name+(this.b_add_mandant_id?bibALL.get_ID_MANDANT():"");
		String complete_statement = bibALL.ReplaceTeilString(this.trigger_def, "#TRIGGERNAME#", complete_name);
		
		
//		DEBUG.System_println(complete_statement);
		mv.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(bibVECTOR.get_Vector(complete_statement), true));
		if (mv.get_bIsOK()) {
			mv.clear();
			mv.add_MESSAGE(new MyE2_Info_Message(S.mt("Der Trigger ist erfolgreich erstellt worden !")));
		}
		return this;
	}


	public DB_META_TRIGGER_HANDLER _drop_trigger(MyE2_MessageVector mv) {
		String complete_statement = get_delStatement();
		
//		DEBUG.System_println(complete_statement);
		mv.add_MESSAGE(bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(bibVECTOR.get_Vector(complete_statement), true));
		if (mv.get_bIsOK()) {
			mv.clear();
			mv.add_MESSAGE(new MyE2_Info_Message(S.mt("Der Trigger ist erfolgreich gelöscht worden !")));
		}
		return this;
	}
	
	
	
	

	public STATUS_TRIGGER get_actual_status() {
		return actual_status;
	}

	
	public String get_trigger_praefix() {
		return trigger_praefix;
	}


	public String get_delStatement() {
		String complete_name = this.trigger_praefix+this.trigger_name+(this.b_add_mandant_id?bibALL.get_ID_MANDANT():"");
		String complete_statement = bibALL.ReplaceTeilString(this.trigger_del, "#TRIGGERNAME#", complete_name);
		return complete_statement;
	}


	
	
}
