package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_TOOLS;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_AB_Basis;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_onlyWhenVisisble;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class WF_HEAD_LIST_BT_editInList extends MyE2_DB_PlaceHolder_onlyWhenVisisble {
	
	public WF_HEAD_LIST_BT_editInList(SQLField osqlField) throws myException {
		super(osqlField);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		this.removeAll();
		this.setSize(1);
		this.add((ownEditButton)new ownEditButton()._aaa(new ownActionEdit()));
	}
	
	private class ownEditButton extends MyE2_Button {
		public ownEditButton() {
			super(E2_ResourceIcon.get_RI("edit.png") , E2_ResourceIcon.get_RI("leer.png"));
		}
		
	}
	
	public class ownActionEdit extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			String id = (WF_HEAD_LIST_BT_editInList.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()!=null?
							WF_HEAD_LIST_BT_editInList.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID():"");
			
			if (S.isFull(id)) {
				//hier werden recursive die buttons edit und view gesucht und dann gestartet
				Vector<Component> v_bt_edit = new E2_RecursiveSearch_AB_Basis(bibVECTOR.get_Vector(WF_HEAD_LIST_BT_EDIT.class.getName())).get_vAllComponents();
				Vector<Component> v_bt_view = new E2_RecursiveSearch_AB_Basis(bibVECTOR.get_Vector(WF_HEAD_LIST_BT_VIEW.class.getName())).get_vAllComponents();
				
				if ((v_bt_edit.size()==1) && v_bt_view.size()==1) {
					WF_HEAD_LIST_BT_EDIT bt_edit = (WF_HEAD_LIST_BT_EDIT)v_bt_edit.get(0);
					WF_HEAD_LIST_BT_VIEW bt_view = (WF_HEAD_LIST_BT_VIEW)v_bt_view.get(0);
					
					E2_ComponentMAP map = WF_HEAD_LIST_BT_editInList.this.EXT().get_oComponentMAP();
					if (E2_ComponentMAP_TOOLS.check_id_uncheck_others(map, id)) {
						if (bt_edit.valid_GlobalValidation().get_bIsOK()) {
							bt_edit.doActionPassiv();
						} else {
							if (bt_view.valid_GlobalValidation().get_bIsOK()) {
								bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Editieren ist nicht erlaubt !")));
								bt_view.doActionPassiv();
							} else {
								bibMSG.add_MESSAGE(bt_edit.valid_GlobalValidation());
								bibMSG.add_MESSAGE(bt_view.valid_GlobalValidation());
							}
						}
					}
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Editierfunktion konnte nicht gefunden werden !!")));
				}
			}
		}
		
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new WF_HEAD_LIST_BT_editInList(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

}
