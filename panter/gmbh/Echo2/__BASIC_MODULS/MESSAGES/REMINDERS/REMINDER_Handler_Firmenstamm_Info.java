package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS;

import java.util.Vector;

import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;

public class REMINDER_Handler_Firmenstamm_Info extends REMINDER_Handler_Base{

	// idUser muss in die Reminder-Kennung eingetragen werden
	String m_idUser = null;
	
	public REMINDER_Handler_Firmenstamm_Info( String sIDuser) {
		super();
		m_idUser = sIDuser;
		m_vIDUser.add(sIDuser);
		
		m_Modulname = E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK_INFOLISTE;
		m_ModulZusatz = null;
		m_KategorieKennung = MESSAGE_CONST.REMINDER_Kennung.MESSAGE_FIRMENSTAMM_INFO.toString();
	}

	
	@Override
	protected void initReminder(){
		
		
		// SELECT von Kennung, UserID, TITEL,  Text, Folgedatum 
		String sSql = 
				"select * from (" +
				"												" +
				"			 SELECT DISTINCT" +
				"				('JT_ADRESSE#' || ID_ADRESSE || '#' || ID_ADRESSE_INFO || '#' || '" + m_idUser + "' || '#' || to_char(folgedatum_2,'YYYY-MM-DD') ) as wv_kennung," +
//								 m_idUser + "," +
				"				 NAME1 || ' ' || NAME2 || ' ' || NAME3  as NAME," +
				"				 TEXT," +
				"				 to_char(folgedatum_2 ,'YYYY-MM-DD'), " +
				"				 ID_ADRESSE_INFO	" +
				"				 FROM ( " +
				"				 										" +
				"						SELECT  " +
				"							ID_ADRESSE as ID_ADRESSE_WV,  " +
				"							ID_ADRESSE_INFO, " +
				"							TEXT, " +
				"							FOLGEDATUM," +
				"							ID_USER as ID_USER_INFO," +
				"							ID_USER_ERSATZ as ID_USER_ERSATZ_INFO," +
				"							ID_USER_SACHBEARBEITER as ID_USER_SACHBEARBEITER_INFO," +
				"							CASE " +
				"					 			WHEN folgedatum >= add_months(sysdate,-1) then folgedatum " +
				"				 				WHEN (folgedatum < sysdate  and nvl(WIEDERHOLUNGMONATLICH,'N') = 'Y') then to_date(to_char(sysdate,'YYYY')  ||  '-'  ||  to_char(sysdate,'MM')      ||  '-01' ,'YYYY-MM-DD') + 	(least(to_number(to_char(folgedatum,'DD')) , to_number(TO_CHAR(last_day(SYSDATE),'DD')) ) -1) " +
				"					 			WHEN (folgedatum < sysdate  and nvl(WIEDERHOLUNGJAEHRLICH,'N') = 'Y') then to_date(to_char(sysdate,'YYYY')  ||  '-'  ||  to_char(folgedatum,'MM')   ||  '-01' ,'YYYY-MM-DD') + ( -1 + least ( to_number(TO_CHAR(folgedatum,'DD')) , to_number(TO_CHAR(last_day(to_date(TO_CHAR(add_months(SYSDATE,1),'YYYY') || '-' || TO_CHAR(folgedatum,'MM') || '-01' ,'YYYY-MM-DD')),'DD')) ) ) " +
				"							END AS folgedatum_2" +
				"							FROM " + m_cTO + ".jt_adresse_info  " +
				"							WHERE folgedatum >= add_months(sysdate,-1) " +
				"							OR (folgedatum is not null  AND nvl(WIEDERHOLUNGMONATLICH,'N') = 'Y')" +
				"							OR (folgedatum is not null  AND nvl(WIEDERHOLUNGJAEHRLICH,'N') = 'Y')" +
				"						UNION " +
				"					 	SELECT ID_ADRESSE, ID_ADRESSE_INFO, text, folgedatum," +
				"							ID_USER," +
				"							ID_USER_ERSATZ," +
				"							ID_USER_SACHBEARBEITER," +
				"					 		CASE " +
				"					     		WHEN (folgedatum < sysdate  and nvl(WIEDERHOLUNGMONATLICH,'N') = 'Y') THEN to_date(TO_CHAR(add_months(SYSDATE,1),'YYYY') || '-' || TO_CHAR(add_months(SYSDATE,1),'MM') || '-01' ,'YYYY-MM-DD') 	+ (least(	to_number(to_char(folgedatum,'DD')),	to_number(TO_CHAR(last_day(add_months(SYSDATE,1)),'DD')) )-1) " +
				"							    WHEN (folgedatum < sysdate  and nvl(WIEDERHOLUNGJAEHRLICH,'N') = 'Y') THEN to_date(TO_CHAR(add_months(SYSDATE,1),'YYYY') || '-' || TO_CHAR(folgedatum,'MM') || '-01' ,'YYYY-MM-DD') 			+ ( -1 + least ( to_number(TO_CHAR(folgedatum,'DD')) , to_number(TO_CHAR(last_day(to_date(TO_CHAR(add_months(SYSDATE,1),'YYYY') || '-' || TO_CHAR(folgedatum,'MM') || '-01' ,'YYYY-MM-DD')),'DD')) ) ) " +
				"							END as folgedatum_2" +
				"						FROM  " + m_cTO + ".jt_adresse_info " +
				"						WHERE  ((folgedatum is not null  AND nvl(WIEDERHOLUNGMONATLICH,'N') = 'Y') OR (folgedatum is not null  AND nvl(WIEDERHOLUNGJAEHRLICH,'N') = 'Y') )" +
				"	 			)			" +
				"	 			INNER JOIN JT_ADRESSE adr ON adr.ID_ADRESSE = ID_ADRESSE_WV			" +
				"	 				WHERE folgedatum_2 IS NOT NULL			" +
				"    				AND folgedatum_2 <= add_months(sysdate,1)     " +
				"   				AND (     " +
				"           		 	nvl(ID_USER_INFO,adr.id_USER) = " + m_idUser +
				"       		     	OR nvl(ID_USER_ERSATZ_INFO, adr.ID_USER_ERSATZ) =  " + m_idUser + 
				"		             	OR nvl(ID_USER_SACHBEARBEITER_INFO,adr.ID_USER_SACHBEARBEITER) = " + m_idUser +
				"        			)      " +
				"			) WHERE wv_kennung  NOT in (          " +
				"                    SELECT DISTINCT NVL(n.WV_KENNUNG,'*') from  " + m_cTO + ".JT_NACHRICHT n          " +
				"                    WHERE n.ID_USER  = " + m_idUser +
				")          " +
				"";
		
		
				String [][] sArrDaten = new String[0][0];
				sArrDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);

				initEntries(sArrDaten);

	}

	
	/**
	 * Erzeugen des Arrays mit den Reminder-Entries
	 */
	private void initEntries(String[][] sArrDaten) {
		
		if (sArrDaten != null){
			// sonst den Vektor füllen
			for (int i = 0; i < sArrDaten.length; i++){
				String wv_kennung 	= sArrDaten[i][0];
				String titel		= sArrDaten[i][1];
				String text 		= sArrDaten[i][2];
				String datum		= sArrDaten[i][3];
				String idAdresseInfo= sArrDaten[i][4];
				
				MESSAGE_Entry_Target oTarget = new MESSAGE_Entry_Target(idAdresseInfo, m_Modulname, m_ModulZusatz, "Springe zu Firmenstamm-Info");
				Vector<MESSAGE_Entry_Target> vTarget = new Vector<MESSAGE_Entry_Target>();
				vTarget.add(oTarget);
				
				MESSAGE_Entry oEntry  = new MESSAGE_Entry()
					.set_Titel(titel)
					.set_Nachricht(text)
					.set_vIdEmpfaenger(m_vIDUser)
					.set_Sofortanzeige(false)
					.set_DtAnzeigeAb(datum)
					.set_KeyReminder(wv_kennung)
					.set_vTargets(vTarget)
					.set_Kategorie(m_KategorieKennung);
				
				m_vReminderEntries.add(oEntry);
			}
		}
	}

	
	


	
}
