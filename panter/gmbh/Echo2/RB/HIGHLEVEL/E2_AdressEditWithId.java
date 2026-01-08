package panter.gmbh.Echo2.RB.HIGHLEVEL;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

public class E2_AdressEditWithId extends E2_BtEditAdress {

	
	private String id_uf = null;
	

	/**
	 * 
	 * @param idUf  (unformated id_adresse (lager- or main-adress) 
	 * @throws myException
	 */
	public E2_AdressEditWithId(String idUf) throws myException {
		super();
		this.id_uf = idUf;
	}

	@Override
	public MyLong find_id_adress() throws myException {
		Rec20_adresse  ra = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(new MyLong(this.id_uf).get_lValue()));
		return new MyLong(ra.__get_main_adresse().get_ufs_dbVal(ADRESSE.id_adresse));
	}

}
