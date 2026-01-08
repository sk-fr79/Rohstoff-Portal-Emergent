package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE;


/**
 * prueft, ob der button Sammellieferschein aktiv ist
 * @author martin
 *
 */
public class ___BUTTON_LIST_BT_Mail_and_Print_ValidSammellieferscheinAllowed {

	private ___Sammler_ID_VPOS_TPA_FUHRE o_Sammler = null;
	
	public ___BUTTON_LIST_BT_Mail_and_Print_ValidSammellieferscheinAllowed(___Sammler_ID_VPOS_TPA_FUHRE oSammler) {
		super();
		this.o_Sammler = oSammler;
	}

	
	public boolean get_bSammelLieferscheinOK() throws myException {
		Vector<String>  vIDs = this.o_Sammler.get_vID_VPOS_TPA_FUHRE();
		
		String cSQL = "SELECT COUNT(*) FROM "
							+ "(SELECT DISTINCT "
							+ " V."+_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL
							+ ", V."+_DB.VPOS_TPA_FUHRE$A_NAME1
							+ ", V."+_DB.VPOS_TPA_FUHRE$A_NAME2
							+ ", V."+_DB.VPOS_TPA_FUHRE$A_STRASSE
							+ ", V."+_DB.VPOS_TPA_FUHRE$A_HAUSNUMMER
							+ ", V."+_DB.VPOS_TPA_FUHRE$A_PLZ
							+ ", V."+_DB.VPOS_TPA_FUHRE$A_ORT
							+ ", V."+_DB.VPOS_TPA_FUHRE$A_LAENDERCODE
							+ ", V."+_DB.VPOS_TPA_FUHRE$TRANSPORTKENNZEICHEN
							+ ", NVL(V."+_DB.VPOS_TPA_FUHRE$OHNE_ABRECHNUNG+",'N')"
							+ ", NVL(NVL(V."+_DB.VPOS_TPA_FUHRE$ID_ADRESSE_SPEDITION+", TPA_K."+_DB.VKOPF_TPA$ID_ADRESSE+"),-1)"
							+" FROM "+bibE2.cTO()+".V"+bibALL.get_ID_MANDANT()+"_FUHREN V "
							+" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_TPA TPA_P ON (V.ID_VPOS_TPA=TPA_P.ID_VPOS_TPA) "
							+" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_TPA TPA_K ON (TPA_K.ID_VKOPF_TPA=TPA_P.ID_VKOPF_TPA) "
						+" WHERE V."+_DB.VPOS_TPA_FUHRE$ID_VPOS_TPA_FUHRE+" IN ("+bibALL.ConcatenateWithoutException(vIDs, ",", "-1")+") AND "
						+" NVL(V."+_DB.VPOS_TPA_FUHRE$DELETED+",'N')='N')";
		
		String cAnzahlZiele = bibDB.EinzelAbfrage(cSQL,"@","@","@").trim();
		
		if (cAnzahlZiele.equals("@")) {
			throw new myException(this,"Error Query "+cSQL);
		}
		
		return cAnzahlZiele.equals("1");
		
		
		
		
	}
	
}











