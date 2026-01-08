package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import java.util.HashMap;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class AS_LIST_SQLFieldMap extends Project_SQLFieldMAP
{

	
	public AS_LIST_SQLFieldMap() throws myException
	{
		super("JT_ARTIKEL",":ID_EINHEIT:",false);
		
		this.add_JOIN_Table("JT_EINHEIT", "JT_EINHEIT", SQLFieldMAP.LEFT_OUTER, 
						":ID_EINHEIT:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
						"JT_ARTIKEL.ID_EINHEIT=JT_EINHEIT.ID_EINHEIT", "E_", null);

		HashMap<String, String> hmZusatz = new HashMap<String, String>();
		hmZusatz.put("GRUPPE_KURZ", "SUBSTR(JT_ARTIKEL_GRUPPE.GRUPPE,1,50)");
		
		this.add_JOIN_Table("JT_ARTIKEL_GRUPPE", "JT_ARTIKEL_GRUPPE", SQLFieldMAP.LEFT_OUTER, 
				":ID_ARTIKEL_GRUPPE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
				"JT_ARTIKEL.ID_ARTIKEL_GRUPPE=JT_ARTIKEL_GRUPPE.ID_ARTIKEL_GRUPPE", "", hmZusatz);

		
		//code baranlieferer
		HashMap<String, String> hmZusatzIN = new HashMap<String, String>();
		hmZusatzIN.put("CODE_GES_IN", "NVL(JT_EAK_BRANCHE_1.KEY_BRANCHE,'-')||' '||NVL(JT_EAK_GRUPPE_1.KEY_GRUPPE,'-')||' '||NVL(JT_EAK_CODE_1.KEY_CODE,'-')||' '||TRANSLATE(NVL(JT_EAK_CODE_1.GEFAEHRLICH,'N'),'NYy',' **')");
		
		this.add_JOIN_Table("JT_EAK_CODE", "JT_EAK_CODE_1", SQLFieldMAP.LEFT_OUTER, 
				":ID_EAK_CODE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
				"JT_ARTIKEL.ID_EAK_CODE=JT_EAK_CODE_1.ID_EAK_CODE", "EAK_CODE_IN_", null);
		
		this.add_JOIN_Table("JT_EAK_GRUPPE", "JT_EAK_GRUPPE_1", SQLFieldMAP.LEFT_OUTER, 
				":ID_EAK_GRUPPE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
				"JT_EAK_CODE_1.ID_EAK_GRUPPE=JT_EAK_GRUPPE_1.ID_EAK_GRUPPE", "EAK_GRUPPE_IN_", null);
		
		this.add_JOIN_Table("JT_EAK_BRANCHE", "JT_EAK_BRANCHE_1", SQLFieldMAP.LEFT_OUTER, 
				":ID_EAK_BRANCHE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
				"JT_EAK_GRUPPE_1.ID_EAK_BRANCHE=JT_EAK_BRANCHE_1.ID_EAK_BRANCHE", "EAK_BRANCHE_IN_", hmZusatzIN);
		

		//code ex mandant
		HashMap<String, String> hmZusatzOUT = new HashMap<String, String>();
		hmZusatzOUT.put("CODE_GES_OUT", "NVL(JT_EAK_BRANCHE_2.KEY_BRANCHE,'-')||' '||NVL(JT_EAK_GRUPPE_2.KEY_GRUPPE,'-')||' '||NVL(JT_EAK_CODE_2.KEY_CODE,'-')||' '||TRANSLATE(NVL(JT_EAK_CODE_2.GEFAEHRLICH,'N'),'NYy',' **')");

		this.add_JOIN_Table("JT_EAK_CODE", "JT_EAK_CODE_2", SQLFieldMAP.LEFT_OUTER, 
				":ID_EAK_CODE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
				"JT_ARTIKEL.ID_EAK_CODE_EX_MANDANT=JT_EAK_CODE_2.ID_EAK_CODE", "EAK_CODE_OUT_", null);
		
		this.add_JOIN_Table("JT_EAK_GRUPPE", "JT_EAK_GRUPPE_2", SQLFieldMAP.LEFT_OUTER, 
				":ID_EAK_GRUPPE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
				"JT_EAK_CODE_2.ID_EAK_GRUPPE=JT_EAK_GRUPPE_2.ID_EAK_GRUPPE", "EAK_GRUPPE_OUT_", null);
		
		this.add_JOIN_Table("JT_EAK_BRANCHE", "JT_EAK_BRANCHE_2", SQLFieldMAP.LEFT_OUTER, 
				":ID_EAK_BRANCHE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:ERZEUGT_VON:ERZEUGT_AM:", false,
				"JT_EAK_GRUPPE_2.ID_EAK_BRANCHE=JT_EAK_BRANCHE_2.ID_EAK_BRANCHE", "EAK_BRANCHE_OUT_", hmZusatzOUT);
		
		
		//2011-01-12: zolltarifnummer und rc-vermerk einblenden in artikel
		this.add_JOIN_Table("JT_ZOLLTARIFNUMMER", "JT_ZOLLTARIFNUMMER", SQLFieldMAP.LEFT_OUTER, 
				":NUMMER:REVERSE_CHARGE:", true,
				"JT_ARTIKEL.ID_ZOLLTARIFNUMMER=JT_ZOLLTARIFNUMMER.ID_ZOLLTARIFNUMMER", "ZT_", null);
		
		
		//2016-08-08: spalte, die  fuer die aktiv-inaktiv-anzeige der zolltarifnummer benutzt wird
		this.add_SQLField(new SQLField(AS___CONST.SQL_FIELD_NAMES.ID2), false);
		
		this.initFields();
	}

}
