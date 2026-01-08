/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS
 * @author manfred
 * @date 21.06.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS;

import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_Factory;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_USER_Entry_Data;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_USERINFO;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BORDERCROSSING_USERINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING_USERINFO;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossing_RecordList.Fields;

/**
 * @author manfred
 * @date 21.06.2018
 *
 * Abbild eines Bordercrossing-Records mit einem zugeordneten Artikel für die Prüfung auf Grenzübertritt  
 *
 */
public class BorderCrossing_Record extends HashMap<Fields, String>{
	
	/**
	 * @author manfred
	 * @date 21.06.2018
	 *
	 */
	public BorderCrossing_Record() {
	
	}
	
	
	/**
	 * setzt den Wert des Feldes im Record 
	 * @author manfred
	 * @date 21.06.2018
	 *
	 * @param field
	 * @param value
	 * @return
	 */
	public BorderCrossing_Record setValue(Fields field, String value){
		this.put(field, value);
		return this;
	}
	
	
	/**
	 * gibt den Wert des Feldes als String zurück wenn vorhanden, sonst null
	 * @author manfred
	 * @date 21.06.2018
	 *
	 * @param field
	 * @return
	 */
	public String getValue(Fields field){
		if (this.containsKey(field)){
			return this.get(field);
		} else {
			return null;
		}
	}
	
	/**
	 * gibt den Wert des Feldes als String zurück wenn vorhanden, sonst null
	 * @author manfred
	 * @date 21.06.2018
	 *
	 * @param field
	 * @return
	 */
	public String getValue(Fields field, String whenNull){
		String retVal = whenNull;
		
		if (this.containsKey(field)){
			if (this.get(field) != null) {
				retVal = this.get(field);
			}
		} 
		return retVal;
	}
	
	
	/**
	 * Hilfsmethode einfache Ermittlung TITLE
	 * @author manfred
	 * @date 22.06.2018
	 *
	 * @param whenNull
	 * @return
	 */
	public String getTitle(String whenNull){
		return getValue(Fields.TITLE,whenNull);
	}
	
	/**
	 * Hilfsmethode einfache Ermittlung MESSAGE
	 * @author manfred
	 * @date 22.06.2018
	 *
	 * @param whenNull
	 * @return
	 */
	public String getMessage(String whenNull){
		return getValue(Fields.MESSAGE,whenNull);
	}
	
	
	/**
	 * löscht die Daten im Record
	 */
	public void clear(){
		this.clear();
	}
	
	/**
	 * erweitert den Reminder-Key mit der Artikel-ID falls eine vorhanden ist.
	 * Alternativ könnte man den Reminder key auch aus der Fuhre/Wiegekarte erzeugen, dann wird aber bei einer änderung der Sorte 
	 * auch immer wieder eine Meldung geschickt, auch wenn nur einseitig eine andere Sorte geändert wird, und die relevante gleich bleibt.
	 * Deshalb hier nur die relevante Sorte in den Kenner eintragen, egal ob Start- oder Zielsorte
	 * @author manfred
	 * @date 22.06.2018
	 *
	 * @param wb
	 * @return
	 */
	private String getReminderKey(BorderCrossingInfo wb){
		String reminderkey = wb.f_reminder_key;
		
		if (S.isFull(this.getValue(Fields.ID_ARTIKEL,""))){
			if ( this.getValue(Fields.ID_ARTIKEL, "").equals(wb.idStartSorte) || 
				 this.getValue(Fields.ID_ARTIKEL, "").equals(wb.idZielSorte) ) {
			
				reminderkey += "@" + StringUtils.leftPad(this.getValue(Fields.ID_ARTIKEL,""),10,"0") ;
			}
		}
		
		
		return reminderkey;
	}
	
	/**
	 * prüfen, ob gewarnt werden muss...
	 * @author manfred
	 * @date 21.06.2018
	 *
	 * @param wb
	 * @return
	 * @throws myException
	 */
	public boolean must_be_warned(BorderCrossingInfo wb) throws myException {
		
		//gewarnt werden muss: wenn beide laender im bordercrossing-satz vorhanden sind, dann muss die uebereinstimmung exakt sein
		//ansonsten muessen nur die not-null-laender uebereinstimmen
		
		boolean b_start_stimmt_ueberein = true;
		boolean b_ziel_stimmt_ueberein = true;
		boolean b_sorte_stimmt_ueberein = true;
		
		if (S.isFull(this.getValue(Fields.ID_LAND_QUELLE,""))) {
			if (!this.getValue(Fields.ID_LAND_QUELLE,"").equals(wb.f_id_land_start))  {
				b_start_stimmt_ueberein = false;
			}
		}
		
		if (S.isFull(this.getValue(Fields.ID_LAND_ZIEL,""))) {
			if (!this.getValue(Fields.ID_LAND_ZIEL,"").equals(wb.f_id_land_ziel))  {
				b_ziel_stimmt_ueberein = false;
			}
		}
		
		if (S.isFull(this.getValue(Fields.ID_ARTIKEL,""))){
			if ( !this.getValue(Fields.ID_ARTIKEL, "").equals(wb.idStartSorte) && 
				 !this.getValue(Fields.ID_ARTIKEL, "").equals(wb.idZielSorte) ) {
				b_sorte_stimmt_ueberein = false;
			}
		}
		
		
		return b_start_stimmt_ueberein && b_ziel_stimmt_ueberein && b_sorte_stimmt_ueberein ;
	}

	
	/**
	 * prueft, ob der reminder bereits existiert
	 * @param wb
	 * @return
	 * @throws myException
	 */
	public boolean reminder_even_exists(BorderCrossingInfo wb) throws myException {
		SEL  sql = new SEL(_TAB.reminder_def)
							.FROM(_TAB.reminder_def)
							.WHERE(REMINDER_DEF.table_name,"'"+wb.f_table.baseTableName()+"'")
							.AND(REMINDER_DEF.id_table,wb.f_id_table)
							.AND(new vgl(REMINDER_DEF.reminder_kennung, getReminderKey(wb) ));
		
		RECLIST_REMINDER_DEF  remdef = new RECLIST_REMINDER_DEF(sql.s());
		
		if (remdef.size()==0) {
			return false;
		}
		return true;
	}

	
	
	
	
	public MyE2_MessageVector write_reminder_def(BorderCrossingInfo wb) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		// user zum Bordercrossing lesen...
		RECLIST_BORDERCROSSING_USERINFO rl_user = new RECLIST_BORDERCROSSING_USERINFO(BORDERCROSSING_USERINFO.id_bordercrossing.fn() + " = " + this.getValue(Fields.ID_BORDERCROSSING,"-1"),"") ;
		
		if (rl_user.size()==0) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ohne Benutzer kann keine Erinnerung angelegt werden !")));
		} else {
		
			Vector<REMINDER_USER_Entry_Data> vUser = new Vector<>();
			for (RECORD_BORDERCROSSING_USERINFO u: rl_user) {
				vUser.addElement(new REMINDER_USER_Entry_Data()
							.set_id_user(u.get_ID_USER_cUF())
							.set_allow_close(u.is_ALLOW_CLOSE_YES())
							.set_allow_edit(false)
							.set_send_mail(true).set_sofortanzeige(true));
			}
			
			MODUL modZiel = MODUL.NAME_MODUL_FUHRENFUELLER;
			String meldung = this.getValue(Fields.MESSAGE,"") ;
			
			if (wb.f_table==_TAB.vpos_tpa_fuhre) {
				meldung +="\n"+new MyE2_String("ID-Fuhre").CTrans()+": "+wb.f_id_table;
			} else if (wb.f_table==_TAB.bewegung_vektor){
				meldung +="\n"+new MyE2_String("ID-Bewegung-Vektor").CTrans()+": "+wb.f_id_table;
			} else if (wb.f_table==_TAB.wiegekarte) {
				meldung +="\n"+new MyE2_String("ID-Wiegekarte").CTrans()+": "+wb.f_id_table;
				modZiel = MODUL.NAME_MODUL_WIEGEKARTE_LISTE;
			} else {
				meldung +="\n"+new MyE2_String("Tabelle: ",true,wb.f_table.baseTableName(),false).CTrans()+": ID: "+wb.f_id_table;
			}
			meldung +="\n"+new MyE2_String("von ").CTrans()+": "+wb.startOrt+" ("+wb.startSorte+")";
			meldung +="\n"+new MyE2_String("nach ").CTrans()+": "+wb.zielOrt+" ("+wb.zielSorte+")";
			
			//2016-04-18: falls der reminder ein negatives offset hat, die tage zurueckrechnen
			MyDate dateRelevant = wb.f_relevant_date;
			int    i_offset = (int) Long.parseLong(this.getValue(Fields.OFFSET_BEFORE_START,"0"));
			
			if (i_offset>0) {
				dateRelevant = dateRelevant.get_DatePlusDaysAsMyDate(-1*i_offset);
			}

			//das datum auf jeden fall auf Freitag setzen, wenn es samstag oder sonntag ist
			dateRelevant.set_Date_to_Friday_before_if_Sat_or_Sun();
			
			
			if (!new REMINDER_Factory(wb.f_table.baseTableName(), 
								wb.f_id_table, 
								modZiel, 
								this.getValue(Fields.TITLE, ""),
								meldung,
								Long.parseLong(this.getValue(Fields.INTERVALL_TAGE,"1")),
								dateRelevant.get_cDBFormatErgebnis(), 
								true, 
								this.getValue(Fields.ERINNERUNG_BEI_ANLAGE, "N").equals("Y"),
								getReminderKey(wb), 
								vUser
				).saveReminderDef()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es konnte keine Erinnerung geschrieben werden !")));
				
			}
		}
		
		return mv;
		
	}
	
}
