package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;

/**
 * im fall der rechnungen/gutschriften werden als anlagen
 * alle Archivmedien mit bestimmten programmkennern moeglich gemacht 
 * @author martin
 *
 */
public class ES__AttachementSeeker_RG extends ES__AttachementSeeker {

	private String 				ID_TABLE = 		null;
	private String 				TABLENAME = 	null;
	
	public ES__AttachementSeeker_RG() {
		super();
	}

	@Override
	public void init_with_archivInfos(String tableBaseName, String id_table) throws myException {
		ID_TABLE = id_table;
		TABLENAME = tableBaseName;
	}

	@Override
	public Vector<String> get_vPossible_id_Archivmedien_to_send(RB_Dataobject dataObj) throws myException {
		
		ES_RB_DataObject  UsedData = (ES_RB_DataObject) dataObj;
		
		Vector<String>  vRueck = new Vector<String>();
		
		
		//zuerst feststellen, welche rechnung das betrifft
		RECORD_VKOPF_RG regRg = new RECORD_VKOPF_RG(this.ID_TABLE);
		Vector<String> v_allowed_programmkenner = new Vector<String>();
		v_allowed_programmkenner.add(Archiver_CONST.PROGRAMMKENNER.RECH_GUT_ANHANG.get_DB_WERT());
		if (regRg.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
			v_allowed_programmkenner.add(Archiver_CONST.PROGRAMMKENNER.RECHNUNG_ANHANG.get_DB_WERT());
		} else {
			v_allowed_programmkenner.add(Archiver_CONST.PROGRAMMKENNER.GUTSCHRIFT_ANHANG.get_DB_WERT());
		}
		
		//nur im status edit relevant
		if (UsedData.rb_MASK_STATUS().isStatusEdit()) {
			//feststellen, ob es ein email  mit original-datei ist
			RECORD_EMAIL_SEND_ext recEmailsend = new RECORD_EMAIL_SEND_ext((RECORD_EMAIL_SEND)UsedData.get_RecORD());
			if (recEmailsend.get_bContainsOriginalMedium()) {
				//dann alle archivmedien nach den medien mit o.g. kenner durchsuchen
				ES_RECLIST_ARCHIVMEDIEN rlArch = new ES_RECLIST_ARCHIVMEDIEN(this.TABLENAME, this.ID_TABLE);
				
				for (RECORD_ARCHIVMEDIEN rec: rlArch) {
					if (v_allowed_programmkenner.contains(rec.get_PROGRAMM_KENNER_cUF_NN(""))) {  //kopien koennen nicht angehaengt werden, da bereits das orignal dranhaengt
						if (!(Archiver_CONST.MEDIENKENNER.ORIG_KOPIE_EMAIL.get_DB_WERT().equals(rec.get_MEDIENKENNER_cUF_NN("")))) {
							vRueck.add(rec.get_ID_ARCHIVMEDIEN_cUF());
						}
					}
				}
			}
		}
		
		
		return vRueck;
	}



}
