package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS;

import java.util.Vector;

import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;

public class REMINDER_Handler_Kreditversicherung_Ablauf extends REMINDER_Handler_Base {

	public REMINDER_Handler_Kreditversicherung_Ablauf( ) {
		super();
		m_Modulname = E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK;
		m_ModulZusatz = FS_CONST.TABTEXT_FINANZEN_IN_ADRESSMASK;
		m_KategorieKennung = MESSAGE_CONST.REMINDER_Kennung.MESSAGE_KREDITVERSICHERUNG_ABLAUF.toString();
	}

	
	@Override
	protected void initReminder(){
		// SELECT von Kennung, UserID, TITEL,  Text, Folgedatum 
		Long lKadenz = bib_Settigs_Mandant.get__Value(ENUM_MANDANT_ZUSATZ_FELDNAMEN.KREDITVERSICHERUNG_WARNUNG_BEI_ABLAUF_KADENZZEIT.toString(), 14L);
		String sKadenz = Long.toString(lKadenz);
		if (!bibALL.isLong(sKadenz)){
			sKadenz = "14";
		}
		
		String sSql = 
				"select * from (" +
				"		select ('JT_KREDITVERS_#' || P.ID_KREDITVERS_KOPF || '#' || P.ID_KREDITVERS_POS || '#' || K.ID_ADRESSE_VERS || '#' || to_char(P.GUELTIG_BIS,'YYYY-MM-DD') ) as wv_kennung,	" +
				"		'Kreditversicherung-Ablauf: ' ||AKV.NAME1 ||' ' || AKV.NAME2 || ', ' ||AKV.ORT    as titel,	" +
				"		AKV.NAME1 ||' ' || AKV.NAME2 || ', ' ||AKV.ORT ||' \nVers.NR: ' || K.VERS_NR || ' \nAblaufdatum: ' || to_char(P.GUELTIG_BIS,'DD.MM.YYYY') || ' \nBetrag: ' || to_char(P.BETRAG,'999G999G999D99') || ' \nBemerkung: ' || K.BEMERKUNG  as text ,	" +
				" 		to_char(sysdate ,'YYYY-MM-DD'), " +
				"		AKV.ID_ADRESSE	"+
				"		from " + m_cTO + ".JT_KREDITVERS_KOPF  K	" +
				"		inner join  " + m_cTO + ".JT_KREDITVERS_POS P ON K.ID_KREDITVERS_KOPF = P.ID_KREDITVERS_KOPF and K.ID_MANDANT = P.ID_MANDANT	" +
				"		left outer join  " + m_cTO + ".JT_KREDITVERS_ADRESSE KA ON KA.ID_KREDITVERS_KOPF = K.ID_KREDITVERS_KOPF	" +
				"		left outer join  " + m_cTO + ".JT_ADRESSE AKV ON AKV.ID_ADRESSE = KA.ID_ADRESSE	" +
				"		where nvl(K.AKTIV,'N') = 'Y'	" +
				"		and nvl(P.AKTIV,'N') = 'Y'	" +
				"		and P.GUELTIG_BIS is not null	" +
				"		and P.GUELTIG_BIS <= sysdate + " + sKadenz + " " +
				"		) where wv_kennung not in (	" +
				"			select distinct nvl(N.WV_KENNUNG,'*') from JT_NACHRICHT N  where N.WV_KENNUNG like 'JT_KREDITVERS%' " + 
				"		)	" +
				"         " ;
		
		
				String [][] sArrDaten = new String[0][0];
				sArrDaten =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
				
				// die gefundenen Daten in die DB einfügen für die Adressen
				this.initEntries(sArrDaten);

	}
	
	
	
	
	/**
	 * Füllen der Daten in das Datenobjekt
	 * @param sArrDaten
	 * @param value_type
	 */
	private void initEntries(String[][] sArrDaten) {

		int j = 0;
		

		// Abbruch, wenn array leer
		if (sArrDaten == null || sArrDaten.length <= 0){
			return;
		}
		RECLIST_USER rlUsers = null;
		// Abbruch, wenn kein zu benachrichtigender User vorhanden
		try {
			rlUsers = new RECLIST_USER("nvl(MELDUNG_KREDITVERS_ABLAUF,'N') = 'Y'", "");
			if (rlUsers.size() <= 0){
				return;
			}
		} catch (myException e1) {
			return;
		}
		
		
		for( RECORD_USER o : rlUsers.values()){
			String sIDUser;
			try {
				sIDUser = o.get_ID_USER_cUF();
				m_vIDUser.add(sIDUser);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
		if (sArrDaten != null){
			// sonst den Vektor füllen
			for (int i = 0; i < sArrDaten.length; i++){
				String wv_kennung 	= sArrDaten[i][0];
				String titel		= sArrDaten[i][1];
				String text 		= sArrDaten[i][2];
				String datum		= sArrDaten[i][3];
				String idAdresseInfo= sArrDaten[i][4];
				
				MESSAGE_Entry_Target oTarget = new MESSAGE_Entry_Target(idAdresseInfo, m_Modulname, m_ModulZusatz, "Springe zu Kreditversicherungseinträgen der Firma");
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
