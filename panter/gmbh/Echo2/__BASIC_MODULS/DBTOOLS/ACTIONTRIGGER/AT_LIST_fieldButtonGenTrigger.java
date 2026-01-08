package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_onlyWhenVisisble;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_META_TRIGGER_HANDLER;
import panter.gmbh.indep.dataTools.DB_META_TRIGGER_HANDLER.STATUS_TRIGGER;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class AT_LIST_fieldButtonGenTrigger extends MyE2_DB_PlaceHolder_onlyWhenVisisble {

	private DB_META_TRIGGER_HANDLER  trigger_handler = null;  
	
	public AT_LIST_fieldButtonGenTrigger(SQLField osqlField) throws myException {
		super(osqlField);
		this.setWidth(new Extent(100, Extent.PERCENT));
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		//feststellen welchen status der trigger hat
		Rec20 rec = new Rec20(_TAB.trigger_action_def)._fill_id(oResultMAP.get_cUNFormatedROW_ID());
		
		if (S.isEmpty(rec.get_ufs_dbVal(TRIGGER_ACTION_DEF.field_name,""))) {
			this.trigger_handler = new AT_TriggerHandlerAllgemein(rec);
		} else {
			this.trigger_handler = new AT_TriggerHandlerField(rec);
		}
		
		
		String text = "Inaktiv";
		Color colBorder = Color.RED;
		if (this.trigger_handler.get_actual_status()==STATUS_TRIGGER.VALID) {
			colBorder=Color.GREEN;
			text = "Aktiv";
		} else if (this.trigger_handler.get_actual_status()==STATUS_TRIGGER.INVALID) {
			colBorder = Color.YELLOW;
			text = "Undefiniert";
		}
		
		RB_cb cb = new RB_cb()._t(S.mt(text));
		cb._aaa(new ownActionAgent(oResultMAP.get_cUNFormatedROW_ID(),cb));
		
		
		cb.setSelected(rec.is_yes_db_val(TRIGGER_ACTION_DEF.aktiv));
		
		this._clear()._add(	cb , new RB_gld()._left_mid()._col(colBorder)._ins(3, 1, 1, 3));
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new AT_LIST_fieldButtonGenTrigger(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	private class ownActionAgent extends XX_ActionAgent {

		private String id_row = null;
		
		private RB_cb cb = null;
		/**
		 * @param p_id_row
		 */
		public ownActionAgent(String p_id_row,  RB_cb p_cb) {
			super();
			this.id_row = p_id_row;
			this.cb = p_cb;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			AT_LIST_fieldButtonGenTrigger oThis = AT_LIST_fieldButtonGenTrigger.this;
			MyE2_MessageVector mv = new MyE2_MessageVector();
			MyE2_MessageVector mv_trigger = new MyE2_MessageVector();
			boolean status_cb_after_click = this.cb.isSelected();
			
			Rec20 rec_trigger = new Rec20(_TAB.trigger_action_def)._fill_id(this.id_row);
			if (status_cb_after_click) { 
				//schalter wurde eingeschaltet
				rec_trigger._nv(TRIGGER_ACTION_DEF.aktiv, "Y", mv);
				rec_trigger._SAVE(true, mv);
				
				oThis.trigger_handler._generate_trigger(mv_trigger);
			} else {
				//trigger loeschen
				rec_trigger._nv(TRIGGER_ACTION_DEF.aktiv, "N", mv);
				rec_trigger._SAVE(true, mv);

				oThis.trigger_handler._drop_trigger(mv_trigger);
			}

			//falls die datenbank-operation fehlgeschlagen ist, dann den trigger auf jeden fall loeschen
			if (!mv.get_bIsOK()) {
				oThis.trigger_handler._drop_trigger(mv_trigger);
			}
			
			E2_ComponentMAP map = AT_LIST_fieldButtonGenTrigger.this.EXT().get_oComponentMAP();
			map._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);

			bibMSG.add_MESSAGE(mv);
			bibMSG.add_MESSAGE(mv_trigger);
		}
		
	}
	
	
	
	
	
}
