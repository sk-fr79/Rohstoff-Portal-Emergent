package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.GENERATE.GenTERM;
import panter.gmbh.indep.exceptions.myException;


public class ES_RECORD_ARCHIVMEDIEN extends RECORD_ARCHIVMEDIEN {

	private boolean 						IsAddedOnSave = 				false;
	private ownCheckBox 					ownCheckBox4EmailSendList = 	null;
	
	private ES_mask_complex_LISTE_ANLAGEN 	maskcomplex_LISTE_ANLAGEN = null; 
	/**
	 * 
	 * @param recordOrig
	 * @param mask_complex_LISTE_ANLAGEN  can be null
	 * @throws myException
	 */
	public ES_RECORD_ARCHIVMEDIEN(RECORD_ARCHIVMEDIEN recordOrig, ES_mask_complex_LISTE_ANLAGEN mask_complex_LISTE_ANLAGEN) throws myException {
		super(recordOrig);
		this.maskcomplex_LISTE_ANLAGEN = mask_complex_LISTE_ANLAGEN;
		this.ownCheckBox4EmailSendList = new ownCheckBox(this);
		if (this.is_IST_ORIGINAL_YES()) {
			this.ownCheckBox4EmailSendList.set_bEnabled_For_Edit(false);
		}
	}

	
	public String get_Statement4EmailSendList(RB_Dataobject db_obj_email_send) throws myException {
		String cRueck = null;         //wird uebergeben, wenn es nix zu tun gibt
		MyRECORD_IF_FILLABLE  rec = db_obj_email_send.rb_relevant_record_to_fill();
		boolean checked = this.ownCheckBox4EmailSendList.isSelected();

		MySqlStatementBuilder stmt_add = new MySqlStatementBuilder();
		if (rec instanceof MyRECORD_IF_RECORDS) {
			//edit
			RECLIST_EMAIL_SEND_ATTACH rlAttaches_status_quo = ((RECORD_EMAIL_SEND)rec).get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send(null,null,true);
			Vector<String>  vID_ARCHIVMEDIEN  = new Vector<String>();
			vID_ARCHIVMEDIEN.addAll(rlAttaches_status_quo.get_ID_ARCHIVMEDIEN_hmString_UnFormated("").values());
			
			if (checked && !vID_ARCHIVMEDIEN.contains(this.get_ID_ARCHIVMEDIEN_cUF())) {
				//einfuegen
				stmt_add.addSQL_Paar(_DB.EMAIL_SEND_ATTACH$ID_EMAIL_SEND_ATTACH, _DB.EMAIL_SEND_ATTACH$$SEQ_NEXT);
				stmt_add.addSQL_Paar(_DB.EMAIL_SEND_ATTACH$ID_EMAIL_SEND, ((RECORD_EMAIL_SEND)rec).get_ID_EMAIL_SEND_cUF());
				stmt_add.addSQL_Paar(_DB.EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN, this.get_ID_ARCHIVMEDIEN_cUF());
				cRueck = stmt_add.get_CompleteInsertString(_DB.EMAIL_SEND_ATTACH);
			} else if (!checked && vID_ARCHIVMEDIEN.contains(this.get_ID_ARCHIVMEDIEN_cUF())){
				//loeschen
				GenTERM  term = new GenTERM();
				term.AppendTerm(_DB.EMAIL_SEND_ATTACH$ID_EMAIL_SEND, "=", ((RECORD_EMAIL_SEND)rec).get_ID_EMAIL_SEND_cUF());
				term.AppendTerm(_DB.EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN, "=", this.get_ID_ARCHIVMEDIEN_cUF());
				cRueck = "DELETE FROM "+bibE2.cTO()+"."+_DB.EMAIL_SEND_ATTACH+" WHERE "+term.get_TERMS_WITH("AND");
			}
		} else {
			if (checked) {
				//new
				stmt_add.addSQL_Paar(_DB.EMAIL_SEND_ATTACH$ID_EMAIL_SEND_ATTACH, _DB.EMAIL_SEND_ATTACH$$SEQ_NEXT);
				stmt_add.addSQL_Paar(_DB.EMAIL_SEND_ATTACH$ID_EMAIL_SEND, _DB.EMAIL_SEND$$SEQ_CURR);
				stmt_add.addSQL_Paar(_DB.EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN, this.get_ID_ARCHIVMEDIEN_cUF());
				cRueck = stmt_add.get_CompleteInsertString(_DB.EMAIL_SEND_ATTACH);
			}
		}
		return cRueck;
	}
	
	/**
	 * prueft, ob ein attachment angehaengt werden darf
	 * ein original darf nur an eine emial-send angehaengt sein 
	 * @return
	 * @throws myException 
	 */
	public boolean allowAdding(RB_Dataobject  ownDataObject) throws myException {
		boolean bRueck = true;
		
		if (this.IsAddedOnSave) {
			if (this.is_IST_ORIGINAL_YES()) {

				String cWhere = "";
				//wie oft schon hinterlegt ?
				if (ownDataObject.get_RecORD()!=null) {   //bearbeitung, nicht neueingabe
					GenTERM term = new GenTERM();
					term.AppendTerm(_DB.EMAIL_SEND_ATTACH$ID_EMAIL_SEND, "<>", ((RECORD_EMAIL_SEND)ownDataObject.get_RecORD()).get_ID_EMAIL_SEND_cUF());
					cWhere = term.get_TERMS_WITH("AND");
				}
				RECLIST_EMAIL_SEND_ATTACH recAttach = this.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_archivmedien(cWhere,"",true);
				if (recAttach.get_vKeyValues().size()==1) {
					bRueck = false;
				}
			}
		}
		return  bRueck;
	}
	
	
	public class ownCheckBox extends MyE2_CheckBox {
		private ES_RECORD_ARCHIVMEDIEN recAMLocal = null;

		public ownCheckBox(ES_RECORD_ARCHIVMEDIEN RecAMLocal) throws myException {
			super();
			this.recAMLocal = RecAMLocal;

			if (this.recAMLocal.is_IST_ORIGINAL_YES()) {
				this.set_bEnabled_For_Edit(false);
			} else {
				this.add_oActionAgent(new ownActionAgent());
			}
		}
		
		private class ownActionAgent extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (ES_RECORD_ARCHIVMEDIEN.this.maskcomplex_LISTE_ANLAGEN!=null) {
					ES_RECORD_ARCHIVMEDIEN.this.maskcomplex_LISTE_ANLAGEN.fill_Grid(null);
				}
			}
		}
	}


	public ownCheckBox get_ownCheckBox4EmailSendList() {
		return ownCheckBox4EmailSendList;
	}

}
