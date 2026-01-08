package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_GridWithLabel;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KREDITVERS_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KREDITVERS_ADRESSE;
import panter.gmbh.indep.exceptions.myException;

/**
 * Grid mit den zu dem Kreditvertrag gehörenden Firmen
 * 
 * @author manfred
 * @date   23.09.2011
 */
public class KV_GRID_VerknuepfteFirmen extends MyE2_GridWithLabel {

	String m_idKreditvers_kopf = null;
	
	public KV_GRID_VerknuepfteFirmen( int iWidth) {
		super(iWidth);
		

	}
	
	
	private void initMask(String idKreditversKopf) throws myException{
		this.removeAll();
		
		RECLIST_KREDITVERS_ADRESSE rlKreditversAdresse = new RECLIST_KREDITVERS_ADRESSE("ID_KREDITVERS_KOPF = " + idKreditversKopf, "ID_KREDITVERS_ADRESSE");
		for (RECORD_KREDITVERS_ADRESSE oRec : rlKreditversAdresse.values()){
			RECORD_ADRESSE oAdresse = new RECORD_ADRESSE(oRec.get_ID_ADRESSE_cUF() );
			this.add (new MyE2_Label(oAdresse.get_NAME1_cUF_NN("")+ " " + oAdresse.get_NAME2_cUF_NN("") + ", " + oAdresse.get_ORT_cUF_NN("") ,MyE2_Label.STYLE_NORMAL_PLAIN()) );
		}

	}
	
	
	public void refreshList(String idKreditversKopf){
		try {
			this.initMask(idKreditversKopf);
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Liste der zugeordneten Firmen konnte nicht geladen werden." )));
		}
	}
	
	

}
