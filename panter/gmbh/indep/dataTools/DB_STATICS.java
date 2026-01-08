package panter.gmbh.indep.dataTools;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class DB_STATICS {
	
	/**
	 * sonderfelder, die von den table-Triggern erzeugt werden
	 * @author martin
	 *
	 */
	public enum TRIGGER_FIELDS {
		ERZEUGT_VON,
		ERZEUGT_AM;
	}
	
	
	
	public enum AUTOMATC_FIELDS {
		 ID_MANDANT
		,GEAENDERT_VON
		,LETZTE_AENDERUNG
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param bMandant
	 * @param bLetzteAenderung
	 * @param bGeaendertVon
	 * @param cID_MANDANT
	 * @param cUserKuerzel
	 * @return
	 */
	public static String[][] get_DB_ZusatzFelder(boolean bMandant, boolean bLetzteAenderung, boolean bGeaendertVon, String cID_MANDANT, String cUserKuerzel)
	{
		// ende ersetzungstabellen für die views 
		// hier werden die zusatzfelder für das objekt oDB erzeugt und übergeben
		
		int iAnzahl = 0;
		if (bMandant) iAnzahl++;
		if (bLetzteAenderung) iAnzahl++;
		if (bGeaendertVon) iAnzahl++;
		
		if (iAnzahl==0)
			return null;
			
		
		String[][] cZusatzFelder = new String[iAnzahl][2];
		
		int iZaehler = 0;
		
		if (bMandant)
		{
//			cZusatzFelder [iZaehler] [0] = "ID_MANDANT";   	
			cZusatzFelder [iZaehler] [0] = AUTOMATC_FIELDS.ID_MANDANT.toString();   	
			cZusatzFelder [iZaehler] [1] = cID_MANDANT;
			iZaehler++;
		}
		if (bGeaendertVon)
		{
//			cZusatzFelder [iZaehler] [0] = "GEAENDERT_VON"; 	
			cZusatzFelder [iZaehler] [0] = AUTOMATC_FIELDS.GEAENDERT_VON.toString(); 	
			cZusatzFelder [iZaehler] [1] = bibALL.MakeSql(cUserKuerzel);
			iZaehler++;
		}
		if (bLetzteAenderung)
		{
			//cZusatzFelder [iZaehler] [0] = "LETZTE_AENDERUNG";
			cZusatzFelder [iZaehler] [0] = AUTOMATC_FIELDS.LETZTE_AENDERUNG.toString();
			cZusatzFelder [iZaehler] [1] = DB_META.get_tStampString((String)bibALL.getSessionValue("DBKENNUNG"));
		}

		return cZusatzFelder;
		
	}

	
	/**
	 * war vorher in der session unter dem Hash "ZUSATZFELDER" abgelegt
	 * @return
	 */
	public static  String[][] get_DB_ZusatzFelder_STD() {
		
//		try {
//			return DB_STATICS.get_DB_ZusatzFelder(	true, 
//														true, 
//														true, 
//														bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF().trim(), 
//														bibALL.get_RECORD_USER().get_KUERZEL_cUF());
			//DEBUG.System_println("get_DB_ZusatzFelder_STD: idMandant:"+bibSES.get_ID_MANDANT_UF().trim()+"   userKuerzel:"+bibSES.get_USER_KUERZEL());
		
			//2015-05-11: infos id_mandant und user_kuerzel liegen in der session
			return DB_STATICS.get_DB_ZusatzFelder(	true, 
													true, 
													true, 
													bibSES.get_ID_MANDANT_UF().trim(), 
													bibSES.get_USER_KUERZEL());
//		} catch (myException e) {
//			throw null;    //darf nicht sein, recordmandant und recorduser sind IMMER in der sessen
//		}
	}
	
	
	
	/**
	 * @return s Vector ID_MANDANT,LETZTE_AENDERUNG,GEAENDERT_VON,ERZEUGT_VON,ERZEUGT_AM
	 */
	public static Vector<String> get_AutomaticWrittenFields_STD()
	{
		
//		//2015-05-07: umstellung, nur noch aus den MyDBToolBox-methoden
//		Vector<String> vRueck = new Vector<String>();
//		MyDBToolBox tb_temp = MyDBToolBox_FAB.generate_STANDARD_DBToolBox();
//		vRueck.addAll(tb_temp.get_AutomaticWrittenFields());
//		bibALL.destroy_myDBToolBox(tb_temp);
		Vector<String>  vRueck = new Vector<String>();
		vRueck.add(DB_STATICS.AUTOMATC_FIELDS.ID_MANDANT.toString());
		vRueck.add(DB_STATICS.AUTOMATC_FIELDS.LETZTE_AENDERUNG.toString());
		vRueck.add(DB_STATICS.AUTOMATC_FIELDS.GEAENDERT_VON.toString());
		vRueck.add(DB_STATICS.TRIGGER_FIELDS.ERZEUGT_VON.toString());
		vRueck.add(DB_STATICS.TRIGGER_FIELDS.ERZEUGT_AM.toString());
		return vRueck;

	}
	
	
	
	public static HashMap<String,String>  get_hmZusatzFelder_STD()
	{
		//2015-05-07: ersetzt durch direkten zugriff
		//String[][] cHelp = (String[][]) bibALL.getSessionValue("ZUSATZFELDER");
		String[][] cHelp = DB_STATICS.get_DB_ZusatzFelder_STD();
		HashMap<String,String> hmRueck = new HashMap<String,String>();

		for (int i=0;i<cHelp.length;i++) {
			hmRueck.put(cHelp[i][0],cHelp[i][1]);
		}
		return hmRueck;
		
	}

	
	
	/**
	 * erzeugt metafieldDef-objekt aus der _TABLES
	 * @param table
	 * @param field
	 * @return
	 * @throws myException 
	 */
	public static MyMetaFieldDEF  get_MetaDef(_TAB  table, IF_Field field) throws myException {
		
		for (IF_Field f: table.get_fields()) {
			if (f.fieldName().equals(field.fieldName())) {
				return f.generate_MetaFieldDef();
			}
		}
		throw new myException("DB_STATICS:get_MetaDef"+field.fieldName()+" is not in table "+table.fullTableName());
	}
	
	/**
	 * erzeugt metafieldDef-objekt aus der _TABLES
	 * @param table
	 * @param field
	 * @return
	 * @throws myException 
	 */
	public static MyMetaFieldDEF  get_MetaDef(String  table, String field) throws myException {
		
		_TAB tab = _TAB.find_Table(table);
		
		for (IF_Field f: tab.get_fields()) {
			if (f.fieldName().equals(field)) {
				return f.generate_MetaFieldDef();
			}
		}
		throw new myException("DB_STATICS:get_MetaDef"+field+" is not in table "+tab.fullTableName());
	}

	
	
	/**
	 * erzeugt metafieldDef-objekt aus der _DBE
	 * @param table
	 * @return
	 * @throws myException 
	 */
	public static HashMap<String, MyMetaFieldDEF>  get_hmMetaDefs(_TAB  table) throws myException {
		
		HashMap<String, MyMetaFieldDEF> hm = new HashMap<String, MyMetaFieldDEF>(); 
		
		for (IF_Field f: table.get_fields()) {
			if (f.fullTableName().equals(table.fullTableName())) {
				hm.put(f.fieldName(), DB_STATICS.get_MetaDef(table, f));
			}
		}
		
		if (hm.size()==0) {
			throw new myException("No fields where found in tabledef: "+table.fullTableName());
		}
		
		return hm;
	}
	
	
	
	
	/**
	 * erzeugt eine mit sep getrennte namensliste der uebergebenen felder
	 * @param sep
	 * @param fields
	 * @return
	 */
	public static String listOfFields(String sep, IF_Field...  fields) {
		String c_rueck = sep;
		
		for (IF_Field f: fields) {
			c_rueck = c_rueck+f.fieldName()+sep;
		}
		return c_rueck;
	}
	
	
	
	
	
	
}
