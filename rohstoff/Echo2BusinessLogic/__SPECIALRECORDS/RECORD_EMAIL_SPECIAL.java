package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.indep.exceptions.myException;


public class RECORD_EMAIL_SPECIAL extends RECORD_EMAIL
{
	public RECORD_EMAIL_SPECIAL(RECORD_EMAIL recordOrig)
	{
		super(recordOrig);
	}

	
	/**
	 * gibt die selektierten schluessel fuer diesen email-eintrag zurueck
	 * @return
	 * @throws myException 
	 */
	public Vector<String>  get_vSelectedKeys() throws myException
	{
		RECLIST_QUALIFIER_EMAIL_SPECIAL  reclistMailQuali = new RECLIST_QUALIFIER_EMAIL_SPECIAL(this.get_ID_EMAIL_cUF());
		
		Vector<String>  vRueck = new Vector<String>();
		
		vRueck.addAll(reclistMailQuali.get_DATENBANKTAG_hmString_Formated("").values());
		
		return vRueck;
	}

}
