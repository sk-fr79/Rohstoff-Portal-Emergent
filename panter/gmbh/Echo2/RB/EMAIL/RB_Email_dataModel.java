package panter.gmbh.Echo2.RB.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.exceptions.myException;

public class RB_Email_dataModel {
	private String betreff 			= "";
	private SEND_TYPE mailTyp 		= SEND_TYPE.SINGLE;
	private String absender 		= "";
	private String text				= "";

	private String mailId			= "";

	private String 	table_base_name = "";
	private String 	id_table = "";

	private Vector<String> zielAdresseListe 		= new Vector<>();

	private RECLIST_ARCHIVMEDIEN anlage_reclist;

	private ES_CONST.SEND_STATUS send_status = SEND_STATUS.SEND_NONE;

	private boolean muss_update_ziel_adresse = false;
	private boolean muss_update_anlage = false;

	public RB_Email_dataModel() throws myException {
		anlage_reclist= new RECLIST_ARCHIVMEDIEN();
	}

	public String getBetreff() {
		return betreff;
	}


	public void setBetreff(String betreff) {
		this.betreff = betreff;
	}


	public SEND_TYPE getMailTyp() {
		return mailTyp;
	}


	public void setMailTyp(SEND_TYPE mailTyp) {
		this.mailTyp = mailTyp;
	}


	public String getAbsender() {
		return absender;
	}


	public void setAbsender(String absender) {
		this.absender = absender;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getMailId() {
		return mailId;
	}


	public void setMailId(String mailId) {
		this.mailId = mailId;
	}


	public Vector<String> getZielAdresseListe() {
		return zielAdresseListe;
	}


	public void setZielAdresseListe(Vector<String> zielAdresseListe) {
		this.zielAdresseListe = zielAdresseListe;
	}

	public RECLIST_ARCHIVMEDIEN getAnlageVector() {
		return anlage_reclist;
	}


	public void setAnlageVector(RECLIST_ARCHIVMEDIEN anlageVector) {
		this.anlage_reclist = anlageVector;
	}


	public ES_CONST.SEND_STATUS getSendStatus() {
		return send_status;
	}


	public void setSendStatus(ES_CONST.SEND_STATUS send_status) {
		this.send_status = send_status;
	}


	public String get_table_base_name() {
		return table_base_name;
	}


	public void set_table_base_name(_TAB table_base_name) {
		if(table_base_name == null){
			this.table_base_name ="";
		}else{
			this.table_base_name = table_base_name.baseTableName();
		}
	}


	public String get_id_table() {
		return id_table;
	}


	public void set_table_id(String id_table) {
		if(id_table==null){
			this.id_table = "";
		}else{
			this.id_table = id_table;
		}
	}


	public boolean muss_update_ziel_adresse() {
		return muss_update_ziel_adresse;
	}


	public boolean muss_update_anlage() {
		return muss_update_anlage;
	}


	/**
	 * mail1==mail2
	 * @param mail2
	 * @return
	 */
	public boolean equals(RB_Email_dataModel mail2){
		boolean update_email_send = false;

		boolean target_list_equal = false;

		boolean anlage_list_equal = false;

		//block 1
		if(this.absender.equals(mail2.absender)){
			update_email_send = true;
			if(this.betreff.equals(mail2.betreff)){
				update_email_send = true;
				if(this.text.equals(mail2.getText())){
					update_email_send = true;
					if(this.mailTyp ==mail2.mailTyp){
						update_email_send = true;
					}else
						update_email_send = false;
				}else{
					update_email_send = false;
				}
			}else{
				update_email_send = false;
			}
		}else{
			update_email_send = false;
		}

		//bloc 2
		if(this.zielAdresseListe.size() != mail2.zielAdresseListe.size()){
			target_list_equal = false;
			muss_update_ziel_adresse = true;
		}else{
			for(String ziel: zielAdresseListe){
				if(! mail2.zielAdresseListe.contains(ziel)){
					target_list_equal = false;
					muss_update_ziel_adresse = true;
					break;
				}else{
					target_list_equal = true;
					muss_update_ziel_adresse = false;
				}
			}
		}

		if(this.anlage_reclist.size() != mail2.anlage_reclist.size()){
			anlage_list_equal = false;
			muss_update_anlage = true;
		}else{
			Vector<String> resultVector = new Vector<>();
			for(String ziel: this.anlage_reclist.get_vKeyValues()){
				if(! mail2.anlage_reclist.containsKey(ziel)){
					anlage_list_equal = true;
					resultVector.add(ziel);
				}else{
					anlage_list_equal=false;
				}
			}
			if(resultVector.size()>0){
				anlage_list_equal = false;
				muss_update_anlage = true;
			}else{
				muss_update_anlage = false;
				anlage_list_equal = true;
			}
		}


		return (update_email_send && target_list_equal && anlage_list_equal);
	}
}
