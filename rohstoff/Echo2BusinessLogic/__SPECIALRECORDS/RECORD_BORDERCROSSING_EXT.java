package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_Factory;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_USER_Entry_Data;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BORDERCROSSING_USERINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING_USERINFO;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingInfo;


public class RECORD_BORDERCROSSING_EXT extends RECORD_BORDERCROSSING {

	public RECORD_BORDERCROSSING_EXT(RECORD_BORDERCROSSING recordOrig) {
		super(recordOrig);
	}

	
	public boolean must_be_warned(BorderCrossingInfo wb) throws myException {
		
		//gewarnt werden muss: wenn beide laender im bordercrossing-satz vorhanden sind, dann muss die uebereinstimmung exakt sein
		//ansonsten muessen nur die not-null-laender uebereinstimmen
		
		boolean b_start_stimmt_ueberein = true;
		boolean b_ziel_stimmt_ueberein = true;
		
		if (S.isFull(this.get_ID_LAND_QUELLE_cUF_NN(""))) {
			if (!this.get_ID_LAND_QUELLE_cUF_NN("").equals(wb.f_id_land_start))  {
				b_start_stimmt_ueberein = false;
			}
		}
		
		if (S.isFull(this.get_ID_LAND_ZIEL_cUF_NN(""))) {
			if (!this.get_ID_LAND_ZIEL_cUF_NN("").equals(wb.f_id_land_ziel))  {
				b_ziel_stimmt_ueberein = false;
			}
		}
		
		return b_start_stimmt_ueberein && b_ziel_stimmt_ueberein;
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
							.AND(new vgl(REMINDER_DEF.reminder_kennung,wb.f_reminder_key));
		
		RECLIST_REMINDER_DEF  remdef = new RECLIST_REMINDER_DEF(sql.s());
		
		if (remdef.size()==0) {
			return false;
		}
		return true;
	}

	
	public MyE2_MessageVector write_reminder_def(BorderCrossingInfo wb) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		RECLIST_BORDERCROSSING_USERINFO rl_user = this.get_DOWN_RECORD_LIST_BORDERCROSSING_USERINFO_id_bordercrossing();
		
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
			String meldung = this.get_MESSAGE_cUF();
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
			int    i_offset = (int)this.l(BORDERCROSSING.offset_before_start, 0l).longValue();
			
			if (i_offset>0) {
				dateRelevant = dateRelevant.get_DatePlusDaysAsMyDate(-1*i_offset);
			}

			//das datum auf jeden fall auf Freitag setzen, wenn es samstag oder sonntag ist
			dateRelevant.set_Date_to_Friday_before_if_Sat_or_Sun();
			
			
			if (!new REMINDER_Factory(wb.f_table.baseTableName(), 
								wb.f_id_table, 
								modZiel, 
								this.get_TITLE_cUF(), 
								meldung, 
								this.get_INTERVALL_TAGE_lValue(1l), 
								dateRelevant.get_cDBFormatErgebnis(), 
								true, 
								this.is_ERINNERUNG_BEI_ANLAGE_YES(), 
								wb.f_reminder_key, 
								vUser
				).saveReminderDef()) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es konnte keine Erinnerung geschrieben werden !")));
				
			}
		}
		
		return mv;
		
	}
	
}
