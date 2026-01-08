/**
 * panter.gmbh.Echo2.components.DB
 * @author martin
 * @date 03.12.2018
 * 
 */
package panter.gmbh.Echo2.components.DB;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 03.12.2018
 *
 */
public abstract class E2_CheckBoxWithToggleActionForLists extends RB_cb implements MyE2IF_DB_SimpleComponent, E2_IF_Copy {

	private IF_Field  	field = null;
	private Long 		rowId = null;
	
	public E2_CheckBoxWithToggleActionForLists() {
		super();
		this._aaa(new OwnActionToggle());
	}

	public E2_CheckBoxWithToggleActionForLists(MyE2_String text) {
		super(text);
		this._aaa(new OwnActionToggle());
	}

	public E2_CheckBoxWithToggleActionForLists(String text) {
		super(text);
		this._aaa(new OwnActionToggle());
	}

	public MyE2_String getMessageWasSetActiv(String id) {
		return S.ms("Datensatz wurde eingeschaltet (ist bereits gespeichert). ID: ").ut(id);
	}
	public MyE2_String getMessageWasSetInActiv(String id) {
		return S.ms("Datensatz wurde ausgeschaltet (ist bereits gespeichert). ID: ").ut(id);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP resultmap)	throws myException {
		
		if (field==null) {
			throw new myException("checkbox in list is no initialized with a field: Error-ID: 2aab99bc-f724-11e8-8eb2-f2801f1b9fd1");
		}
		
		String ret = S.NN(resultmap.getUfs(this.field),"N");
		if (ret.trim().equals("Y")) {
			this.setSelected(true);
		} else if (ret.trim().equals("N")) {
			this.setSelected(false);
		} else {
			throw new myException("Only values Y or N allowed in this checkbox: Error-ID: 2aab99bc-f724-11e8-8eb2-f2801f1b9fd1");
		}
		MyLong id = new MyLong(resultmap.get_cUNFormatedROW_ID());
		
		if (id.isNotOK()) {
			throw new myException("Cannot find row-ID: Error-ID: 2aab99bc-f724-11e8-8eb2-f2801f1b9fd1");
		} else {
			this.rowId= id.get_oLong();
		}
		
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.setSelected(false);
	}

	public IF_Field getField() {
		return field;
	}

	public E2_CheckBoxWithToggleActionForLists _setField(IF_Field field) {
		this.field = field;
		return this;
	}

	
	protected abstract Rec21 getRec21(Long id) throws myException;
	
	
	protected class OwnActionToggle extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Rec21 rec = getRec21(rowId);
			MyE2_String meldung = null;
			if (rec==null || rec.is_newRecordSet()) {
				throw new myException("Cannot build rec21 from row-ID: Error-ID: 2aab99bc-f724-11e8-8eb2-f2801f1b9fd1");
			} else {
				if (S.NN(rec.getUfs(field),"N").equals("N")) {
					rec._nv(field, "Y", bibMSG.MV());
					meldung=getMessageWasSetActiv(rowId.toString());
				} else if (S.NN(rec.getUfs(field),"N").equals("Y")) {
					rec._nv(field, "N", bibMSG.MV());
					meldung=getMessageWasSetInActiv(rowId.toString());
				} else {
					throw new myException("Cannot set new value to checkbox in database field: Error-ID: 2aab99bc-f724-11e8-8eb2-f2801f1b9fd1");
				}
				if (bibMSG.MV().get_bIsOK()) {
					rec._SAVE(true, bibMSG.MV());
					rec._rebuildRecord();
					bibMSG.MV()._addInfo(meldung);
				}
			}
			
			//zeile refreshen
			EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, false, false);
		}
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		//ist immer enabled
	}
	
	
	
	
}
