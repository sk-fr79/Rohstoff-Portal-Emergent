package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.POSITIONSLISTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_PosList_SQLFieldMap extends Project_SQLFieldMAP {
	
	public static String FIELDLIST_FROM_VKOPF_RG = 		_DB.VKOPF_RG$ID_ADRESSE+":"+
														_DB.VKOPF_RG$BUCHUNGSNUMMER+":"+
														_DB.VKOPF_RG$DRUCKDATUM+":"+
														_DB.VKOPF_RG$ID_ZAHLUNGSBEDINGUNGEN+":"+
														_DB.VKOPF_RG$DELETED+":"+
														_DB.VKOPF_RG$ABGESCHLOSSEN;
		
	public static String FIELDLIST_FROM_FIBU = 			_DB.FIBU$ENDBETRAG_FREMD_WAEHRUNG+":"+
														_DB.FIBU$BUCHUNGSINFO+":"+
														_DB.FIBU$ZAHLUNGSZIEL+":"+
														_DB.FIBU$BUCHUNG_GESCHLOSSEN;

	
	
	
	public FS_PosList_SQLFieldMap() throws myException {
		super(_DB.VPOS_RG,_DB.VPOS_RG$ID_VPOS_RG,false);
		
		this.add_JOIN_Table(_DB.VKOPF_RG, _DB.VKOPF_RG, SQLFieldMAP.LEFT_OUTER, FS_PosList_SQLFieldMap.FIELDLIST_FROM_VKOPF_RG, true, 
				_DB.VPOS_RG+"."+_DB.VPOS_RG$ID_VKOPF_RG+"="+_DB.VKOPF_RG+"."+_DB.VKOPF_RG$ID_VKOPF_RG, "K_", null);
		
		this.add_JOIN_Table(_DB.FIBU, _DB.FIBU, SQLFieldMAP.LEFT_OUTER, FS_PosList_SQLFieldMap.FIELDLIST_FROM_FIBU, true, 
				_DB.VKOPF_RG+"."+_DB.VKOPF_RG$ID_VKOPF_RG+"="+_DB.FIBU+"."+_DB.FIBU$ID_VKOPF_RG, "F_", null);
	
		this.add_JOIN_Table(_DB.WAEHRUNG, _DB.WAEHRUNG, SQLFieldMAP.LEFT_OUTER, "WAEHRUNGSSYMBOL", true, 
				_DB.VPOS_RG+"."+_DB.VPOS_RG$ID_WAEHRUNG_FREMD+"="+_DB.WAEHRUNG+"."+_DB.WAEHRUNG$ID_WAEHRUNG, "", null);
	
		
		
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_RG.ANR1,'--')||'-'||  NVL(JT_VPOS_RG.ANR2,'--')","ANR1_ANR2",new MyE2_String("ANR1-2"),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_RG.EINHEIT_PREIS_KURZ,'--')||' ('||TO_CHAR(  NVL(JT_VPOS_RG.MENGENDIVISOR,0))||')'","EINH_PREIS",new MyE2_String("Pr.Einh."),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANZAHL,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'-')",	"C_ANZAHL", new MyE2_String("Menge"),bibE2.get_CurrSession()),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(ANZAHL_ABZUG,'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.'''),'-')",	"C_ANZAHL_ABZUG", new MyE2_String("Menge-Abzug"),bibE2.get_CurrSession()),false);

		this.add_SQLField(new SQLField("  NVL(TO_CHAR(NVL(GESAMTPREIS_FW,0)-NVL(GESAMTPREIS_ABZUG_FW,0),'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'-')",	"ZAHLBETRAG_FW", new MyE2_String("Zahlbetrag"),bibE2.get_CurrSession()),false);
		this.add_SQLField(new SQLField("  NVL(TO_CHAR(NVL(GESAMTPREIS_FW,0)-NVL(GESAMTPREIS_ABZUG_FW,0),'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'-')",	"BEZAHLT_FW", new MyE2_String("Zahlbetrag"),bibE2.get_CurrSession()),false);

		String cSQL_EK_VK = " CASE WHEN "+_DB.Z_VPOS_RG$LAGER_VORZEICHEN+"=1 THEN CSCONVERT('WE','NCHAR_CS') ELSE CSCONVERT('WA','NCHAR_CS') END ";
		this.add_SQLField(new SQLField(cSQL_EK_VK,"WE_WA",new MyE2_String("WE_WA"),bibE2.get_CurrSession()), false);

		this.initFields();
	}

}
