package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import java.util.HashMap;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FKR_FB_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public FKR_FB_LIST_SqlFieldMAP() throws myException 
	{
		super(_DB.FILTER, "", false);
		
//		this.add_SQLField(new SQLField("(SELECT NVL(F.BESCHREIBUNG,'<beschreibung>') FROM "+bibE2.cTO()+"."+_DB.FILTER+" F WHERE F.ID_FILTER="+
//									_DB.FIBU_KONTENREGEL_NEU+"."+_DB.FIBU_KONTENREGEL_NEU$ID_FILTER+")",
//									FKR_FB__CONST.FELD_FILTER_BESCHREIBUNG, new MyE2_String("Filterbeschreibung"), bibE2.get_CurrSession()), false);
		
		//nur filter anzeigen, die in einer kontenregel verwendet werden
		this.add_BEDINGUNG_STATIC("("+_DB.FILTER+"."+_DB.FILTER$ID_FILTER+" IN (SELECT "+_DB.FIBU_KONTENREGEL_NEU$ID_FILTER+" FROM "+bibE2.cTO()+
												"."+_DB.FIBU_KONTENREGEL_NEU+"))");
		
		this.add_JOIN_Table(	_DB.FIBU_KONTENREGEL_NEU, 
								_DB.FIBU_KONTENREGEL_NEU, 
								SQLFieldMAP.INNER, 
								_DB.FIBU_KONTENREGEL_NEU$ID_FILTER+":"+_DB.FIBU_KONTENREGEL_NEU$KOMMENTAR+":"+_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU+":"+_DB.FIBU_KONTENREGEL_NEU$AKTIV, 
								true,_DB.FILTER+"."+_DB.FILTER$ID_FILTER+"="+_DB.FIBU_KONTENREGEL_NEU+"."+_DB.FIBU_KONTENREGEL_NEU$ID_FILTER , "FKR_", null);
		

		HashMap<String, String> hmKtoZusatz = new HashMap<String, String>();
		hmKtoZusatz.put("FIBU_KONTO_GESAMT", _DB.Z_FIBU_KONTO$KONTO +"||' - '||"+_DB.Z_FIBU_KONTO$BESCHREIBUNG);
		
		this.add_JOIN_Table(	_DB.FIBU_KONTO, 
								_DB.FIBU_KONTO, 
								SQLFieldMAP.LEFT_OUTER, 
								_DB.FIBU_KONTO$KONTO+":"+_DB.FIBU_KONTO$BESCHREIBUNG,
								true,
								_DB.FIBU_KONTENREGEL_NEU+"."+_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTO+"="+_DB.FIBU_KONTO+"."+_DB.FIBU_KONTO$ID_FIBU_KONTO , "KTO_", hmKtoZusatz);

		
		this.initFields();
	}
	
}
