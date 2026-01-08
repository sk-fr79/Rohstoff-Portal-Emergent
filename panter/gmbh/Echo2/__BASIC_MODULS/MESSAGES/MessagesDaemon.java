package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Calendar;
import java.util.GregorianCalendar;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.E2_Refreshable;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_TAG;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class MessagesDaemon  implements E2_Refreshable
{

	@Override
	public void RefreshInfo() throws myException
	{
		
		
		// 2016-04-18 
		// Prüfung, ob die Sofortnachrichten angezeigt werden sollen, oder ob eine Anzeigenpause besteht
		Calendar cal = bibSES.get_Messages_Popup_TimeStamp();
		if (cal != null && cal.compareTo(GregorianCalendar.getInstance()) > 0) {
			return;
		}
		
		
		
		/**
		 * 2012-02-10: als Datum einer Nachricht, die sofort angezeigt wird, wird das das datum der Tomcat-Server genommen (als Vorschlag)
		 * 
		 * ist der Datenbank-Server nicht zeitgleich, dann werden nachrichten nicht angezeigt
		 * Deswegen sollte die Zeit sowohl auf der maske als auch in der pruefung die gleiche quelle haben
		 * 
		 */
//		RECLIST_NACHRICHT recList = new RECLIST_NACHRICHT(	" SELECT * FROM "+bibE2.cTO()+".JT_NACHRICHT WHERE " +
//															" NVL(BESTAETIGT,'N')='N' " +
//															" AND NVL(AKTIV_AB, to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') ) <= to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') " +
//															" AND NVL(SOFORTANZEIGE,'Y') = 'Y' 	" +
//															" AND NVL(GELOESCHT,'N') = 'N' " +
//															" AND ID_USER="+bibALL.get_ID_USER());
//		
		
		
		// prüfen, ob schon einmal eine Sofortanzeige vorhanden ist, wenn ja, abbruch
		E2_RecursiveSearch_TAG oSearch = new E2_RecursiveSearch_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.NACHRICHTEN_EDITOR_SOFORT);
		if (oSearch.get_vAllComponents().size() > 0) {
			// abbrechen
			return;
		}
		
		
		
		String SystemDatum = "'"+bibALL.get_cDateNOWInverse("-")+"'";
		
		RECLIST_NACHRICHT recList = new RECLIST_NACHRICHT(	" SELECT * FROM "+bibE2.cTO()+".JT_NACHRICHT WHERE " +
				" NVL(BESTAETIGT,'N')='N' " +
				" AND NVL(AKTIV_AB, to_date( to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') ) <= to_date("+ SystemDatum + ",'YYYY-MM-DD')" +
				" AND NVL(SOFORTANZEIGE,'Y') = 'Y' 	" +
				" AND NVL(GELOESCHT,'N') = 'N' " +
				" AND ID_USER="+bibALL.get_ID_USER());

		
		if (recList.size()>0)
		{
			// ruft den MessageEditor so auf, dass nur die Sofortnachrichten angezeigt werden.
			new MESSAGE_Editor(recList);
		}
			
	}

		
	
}
