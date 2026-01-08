package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


/* 
 * sqlfieldmap fuer die in die maske platzierte liste der Artikelbezeichnungen
 */
public class MZ_MASK_BEZ_LIST_SQLFieldMap extends Project_SQLFieldMAP
{ 

	public MZ_MASK_BEZ_LIST_SQLFieldMap() throws myException
	{
		super("JD_MANDANT_ZUSATZ",":ID_MANDANT_ZUSATZ:",false);
		
		this.add_SQLField(new SQLFieldForRestrictTableRange("JD_MANDANT_ZUSATZ","ID_MANDANT","ID_MANDANT",new MyE2_String("ID-Mandant"),
									"NULL",bibE2.get_CurrSession()), false);
	
		this.add_JOIN_Table(MANDANT_ZUSATZ_FELDNAMEN.fullTabName(),
							MANDANT_ZUSATZ_FELDNAMEN.fullTabName(), 
							SQLFieldMAP.INNER, MANDANT_ZUSATZ_FELDNAMEN.field_type.fn(), true, 
						"JD_MANDANT_ZUSATZ.ID_MANDANT_ZUSATZ_FELDNAMEN=JD_MANDANT_ZUSATZ_FELDNAMEN.ID_MANDANT_ZUSATZ_FELDNAMEN", 
						null, null);
		
		
		//2015-11-10: nur solche zusatzfelder anzeigen, die nicht in der YES_NO_Kreuztabelle sind
		this.add_BEDINGUNG_STATIC("NVL("+MANDANT_ZUSATZ_FELDNAMEN.field_type.fn()+",'UNDEF')<>'YES_NO'"); 
		
		this.initFields();
	} 

}
