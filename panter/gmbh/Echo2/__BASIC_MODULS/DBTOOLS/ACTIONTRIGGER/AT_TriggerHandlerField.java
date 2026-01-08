package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.basics4project.BasicTools.TextFileLoader;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META_TRIGGER_HANDLER;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class AT_TriggerHandlerField extends DB_META_TRIGGER_HANDLER {

	
	private Rec20 rec20_trigger_def = null;
	
	/**
	 * @throws myException 
	 * 
	 */
	public AT_TriggerHandlerField(Rec20 rec) throws myException {
		super();
		this.rec20_trigger_def=rec;
		String sql_trigger =  new TextFileLoader(AT_LIST_fieldButtonGenTrigger.class, "trigger_field.sql").get_loadedText();
		sql_trigger = bibALL.ReplaceTeilString(sql_trigger, "#TABLENAME#", _TAB.find_TableFromBasename(rec.get_fs_dbVal(TRIGGER_ACTION_DEF.table_basename)).n());
		sql_trigger = bibALL.ReplaceTeilString(sql_trigger, "#ID_MANDANT#", bibALL.get_ID_MANDANT());
		sql_trigger = bibALL.ReplaceTeilString(sql_trigger, "#TABLEBASENAME#", rec.get_ufs_dbVal(TRIGGER_ACTION_DEF.table_basename));
		sql_trigger = bibALL.ReplaceTeilString(sql_trigger, "#ID_TRIGGER_ACTION_DEF#",rec.get_ufs_dbVal(TRIGGER_ACTION_DEF.id_trigger_action_def));
		sql_trigger = bibALL.ReplaceTeilString(sql_trigger, "#FIELDNAME#",rec.get_ufs_dbVal(TRIGGER_ACTION_DEF.field_name,""));

		
		this._set_TriggerPraefix(AT_CONST.LOGTRIGGER_PREFIX)._set_TriggerDef(sql_trigger)._set_add_mandant_id(true);
		this._set_TriggerName(rec.get_ufs_dbVal(TRIGGER_ACTION_DEF.trigger_name,""));
		this._init();

	}

	public Rec20 get_rec20_trigger_def() {
		return rec20_trigger_def;
	}

}
