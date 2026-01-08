package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;


/*
 * sqlfieldmap fuer die in die maske platzierte liste der Artikelbezeichnungen
 */
public class AS_MASK_BEZ_LIST_SQLFieldMap extends Project_SQLFieldMAP
{

	public AS_MASK_BEZ_LIST_SQLFieldMap() throws myException
	{
		super("JT_ARTIKEL_BEZ",":ID_ARTIKEL:",false);
		
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_ARTIKEL_BEZ","ID_ARTIKEL","ID_ARTIKEL",new MyE2_String("ID-Artikel"),
									"NULL",bibE2.get_CurrSession()), false);
	
		this.get_("ANR2").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		
		//NEU_09
		this.get_("AKTIV").set_cDefaultValueFormated("Y");

		
		this.initFields();
	}

}
