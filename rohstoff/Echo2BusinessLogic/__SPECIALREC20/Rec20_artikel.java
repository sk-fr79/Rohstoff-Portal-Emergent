package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_artikel extends Rec20 {

	public Rec20_artikel() throws myException {
		super(_TAB.artikel);
	}

	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_artikel(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	public Rec20_artikel _fill_id(long id) throws myException {
		super._fill_id(id);
		//this.execute_query("SELECT * FROM "+bibE2.cTO()+"."+this.get_tab().fullTableName()+" WHERE "+this.get_tab().keyFieldName()+"="+id);
		return this;
	}


	public String get_id_eak_code() throws myException{
		return this.get_ufs_dbVal(ARTIKEL.id_eak_code, "");
	}

	/**
	 * @return einheitkurz
	 * @throws myException
	 */
	public String get_einheit_k() throws myException{
		return this.get_up_Rec20(EINHEIT.id_einheit).get_fs_dbVal(EINHEIT.einheitkurz,"");
	}
	
	/**
	 * 
	 * @return einheitlang
	 * @throws myException
	 */
	public String get_einheit_l() throws myException{
		return this.get_up_Rec20(EINHEIT.id_einheit).get_fs_dbVal(EINHEIT.einheitlang,"");
	}
	
	public String __get_anr1_artbez1(boolean idInKlammern) throws myException {
		String c = "";
		c=this.get_ufs_kette(" - ", ARTIKEL.anr1,ARTIKEL.artbez1);
		if (idInKlammern) {
			c = c +" ("+this.get_fs_dbVal(ARTIKEL.id_artikel)+")";
		}
		return c;
	}


}
