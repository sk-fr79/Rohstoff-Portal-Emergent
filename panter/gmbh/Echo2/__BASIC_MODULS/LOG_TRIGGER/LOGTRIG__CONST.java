package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;

public class LOGTRIG__CONST
{

	/**
	 * methode nimmt die Logtrigger auf, die vom Programm vorgegeben werden
	 * @return
	 */
	public static Vector<String> get_StaticLogTriggersSQL()
	{
		Vector<String> vRueck = new Vector<String>();
		
		Vector<LOGTRIG__CONST.TFelder>  vDEFs = new Vector<LOGTRIG__CONST.TFelder>();
		
		vDEFs.add(new TFelder(RECORD_VPOS_TPA_FUHRE.TABLENAME.substring(3), RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE, "Schalter: Lademengen freigeben in der Fuhre (linke Seite)"));
		vDEFs.add(new TFelder(RECORD_VPOS_TPA_FUHRE.TABLENAME.substring(3), RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE, "Schalter: Ablademengen freigeben in der Fuhre (rechte Seite)"));
		vDEFs.add(new TFelder(RECORD_VPOS_TPA_FUHRE_ORT.TABLENAME.substring(3), RECORD_VPOS_TPA_FUHRE_ORT.FIELD__PRUEFUNG_MENGE, "Schalter: Mengen freigeben im Fuhrenzusatzort"));
		
		
		for (LOGTRIG__CONST.TFelder oFeld: vDEFs)
		{
			vRueck.add("INSERT INTO "+bibE2.cTO()+".JD_TRIGGER_DEF ("+
					RECORD_TRIGGER_DEF.FIELD__ID_TRIGGER_DEF+","+
					RECORD_TRIGGER_DEF.FIELD__TABELLE+","+
					RECORD_TRIGGER_DEF.FIELD__SPALTE+","+
					RECORD_TRIGGER_DEF.FIELD__BESCHREIBUNG+","+
					RECORD_TRIGGER_DEF.FIELD__TRIGG_NAME+","+
					RECORD_TRIGGER_DEF.FIELD__VORDEFINIERT+") VALUES("+
					"SEQ_TRIGGER_DEF.NEXTVAL,"+
					bibALL.MakeSql(oFeld.TABELLE)+","+
					bibALL.MakeSql(oFeld.SPALTE)+","+
					bibALL.MakeSql(oFeld.BESCHREIBUNG)+","+
					bibALL.MakeSql(oFeld.TRIGGER_NAME)+
					",'Y')");
		}
		
		return vRueck;
	}
	
	
	public static String generateTriggerName(String cTablenameBase, String cFieldName)
	{
		String cTableID = bibDB.EinzelAbfrage(DB_META.get_TableID(cTablenameBase,false));
		String cFieldID = bibDB.EinzelAbfrage(DB_META.get_FieldID(cTablenameBase,cFieldName,false));
		
		String pTablename = cTablenameBase;
		
		if (pTablename.length()>=18)
		{
			pTablename=pTablename.substring(0,13)+cTableID;
		}
		
		String cTriggerName="TRG_LG_"+pTablename+"_"+cFieldID;
		
		return cTriggerName;
	}
	
	
	
	private static class TFelder
	{
		String TABELLE = null;
		String SPALTE = null;
		String BESCHREIBUNG = null;
		String TRIGGER_NAME = null;
		
		public TFelder(String tABELLE_ohne_PRAEFIX, String sPALTE, String bESCHREIBUNG)
		{
			super();
			TABELLE = tABELLE_ohne_PRAEFIX;
			SPALTE = sPALTE;
			BESCHREIBUNG = bESCHREIBUNG;
			
			
			// ermitteln der Spaltennummer der gegebenen Spalte
			TRIGGER_NAME = LOGTRIG__CONST.generateTriggerName(TABELLE, SPALTE) ;
		}
		
		
	}
	
}
