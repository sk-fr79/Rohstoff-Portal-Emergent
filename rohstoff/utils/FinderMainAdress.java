package rohstoff.utils;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;

public class FinderMainAdress
{
	private String			 	cID_MAIN_ADRESS_UNFORMATED = null;
	private RECORD_ADRESSE 		recMAIN_ADRESSE = null;
	/**
	 * 
	 * @param cID_ADRESS_TO_TEST
	 *   findet die Hauptadress-id, auch wenn es eine lieferadresse ist
	 * @throws myException
	 */
	public FinderMainAdress(String cID_ADRESS_TO_TEST) throws myException
	{
		super();
		
		this.recMAIN_ADRESSE = new RECORD_ADRESSE(new MyLong(cID_ADRESS_TO_TEST).get_cUF_LongString());
		if (this.recMAIN_ADRESSE.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().size()>0)
		{
			this.recMAIN_ADRESSE = this.recMAIN_ADRESSE.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
		}

		this.cID_MAIN_ADRESS_UNFORMATED = this.recMAIN_ADRESSE.get_ID_ADRESSE_cUF();
	}
	public String get_cID_MAIN_ADRESS_UNFORMATED()
	{
		return cID_MAIN_ADRESS_UNFORMATED;
	}
	public RECORD_ADRESSE get_recMAIN_ADRESSE()
	{
		return recMAIN_ADRESSE;
	}
	
	
	
}
