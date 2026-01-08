package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.LinkedHashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class ES__verify_table_name_and_id {
	
		
	private String 							id_email_send = 				null;
	private LinkedHashMap<String, String> 	hm_replacing_list_FromDataSets = new LinkedHashMap<>();
	
	private name_id_pair                    pair_from_outside = 			null;   			//paar wird aus maskenumgebung gebildet
	private name_id_pair                    pair_from_record_email_send = 	null;   			//paar wird aus dem record email-send gebildet (wenn es vorhanden ist)
	private Vector<name_id_pair>            v_pair_from_attachments = 		new Vector<>();		//vektoren mit paaren aus der anlageliste (nur moeglich, wenn es ein record-email-send gibt
	
	private Vector<name_id_pair>            v_pair_vergleich = new Vector<>();
	
	
	/**
	 * methode vergleich die uebergebene tablename- und id mit der Liste der angehaengten JT_ARCHIVMEDIEN und deren tablename- und id-kombinationen.
	 * alle muessen uebereinsstimmen, sonst wird nur die sonst bleibt die datenbank-replacer-liste leer (keine datenbank-felder) und es wird eine message ausgeloest (status WARNING)
	 * @param p_tablebasename (can be null)
	 * @param p_id_table (can be null)
	 * @param p_id_email_send (can be null)
	 * @param mv   (MUST NOT BE NULL !!!)
	 * @throws myException
	 */
	public ES__verify_table_name_and_id(String p_tablebasename, String p_id_table, String p_id_email_send, MyE2_MessageVector mv) throws myException { 
		super();
		
		this.id_email_send = p_id_email_send;
		
		if (S.isAllFull(p_tablebasename, p_id_table)) {
			this.pair_from_outside = new name_id_pair(p_tablebasename, p_id_table);
		}
		if (S.isFull(p_id_email_send)) {
			RECORD_EMAIL_SEND  rec_email_send = new RECORD_EMAIL_SEND(this.id_email_send);
			if (S.isAllFull(rec_email_send.ufs(EMAIL_SEND.table_base_name), rec_email_send.ufs(EMAIL_SEND.id_table))) {
				pair_from_record_email_send = new name_id_pair(rec_email_send.ufs(EMAIL_SEND.table_base_name),  rec_email_send.ufs(EMAIL_SEND.id_table));
			}
			RECLIST_EMAIL_SEND_ATTACH  rl_attach = rec_email_send.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send();
			for (RECORD_EMAIL_SEND_ATTACH  ra: rl_attach) {
				RECORD_ARCHIVMEDIEN am = ra.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien();
				this.v_pair_from_attachments.add(new name_id_pair(am.ufs(ARCHIVMEDIEN.tablename),  am.ufs(EMAIL_SEND.id_table)));
			}
		}
		
		//jetzt wird geprueft, ob in allen vorhandenen paaren die gleichen tablename und id stecken
		this.add_if_not_inside(this.pair_from_outside);
		this.add_if_not_inside(this.pair_from_record_email_send);
		for (name_id_pair p: this.v_pair_from_attachments) {
			this.add_if_not_inside(p);
		}
//		mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("test test test !!")));

		if (this.v_pair_vergleich.size()>1) {
			mv.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung ! Der Tabelleneintrag auf den sich die eMail bezieht, ist nicht eindeutig !!")));
		} else {
			if (this.v_pair_vergleich.size()==1) {
				this.hm_replacing_list_FromDataSets.putAll(new ES__generatorReplaceList(this.v_pair_vergleich.get(0).tablebasename, this.v_pair_vergleich.get(0).id_table).get_hm_replacement_terms());
			}
		}
		
	}

	
	public LinkedHashMap<String, String>  get_hm_replacing_list_FromDataSets() {
		return this.hm_replacing_list_FromDataSets;
	}
	


	private class name_id_pair {
		private String 							id_table = 			null;
		private String 							tablebasename = 	null;
		
		public name_id_pair(String p_tablebasename, String p_id_table) {
			super();
			this.id_table = p_id_table;
			this.tablebasename = p_tablebasename;
		}
	
		
	    @Override
	    public boolean equals(Object o){
	        if(o instanceof name_id_pair){
	        	name_id_pair other = (name_id_pair)o;
	        	
				if (		S.NN(other.id_table,"A").equals(S.NN(this.id_table,"B")) 
						&& 	S.NN(other.tablebasename,"%").equals(S.NN(this.tablebasename,"?"))) {
					return true;
				} else {
					return false;
				}
	        	
	        }
	        return false;
	    }
	}
	

	
	private void add_if_not_inside(name_id_pair pair) {
		if (pair!=null && (!this.v_pair_vergleich.contains(pair))) {
			this.v_pair_vergleich.add(pair);
		}
	}
	
	
	
	
	
}
