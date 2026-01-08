/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER
 * @author manfred
 * @date 23.03.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 23.03.2016
 *
 */
public class RECLIST_REMINDER_DEF_Ext extends RECLIST_REMINDER_DEF {

	public static String colName_DatumReminder = "DATUM_REMINDER";
	public static String colName_KeyReminder   = "KEY_REMINDER";
	
	
	protected static String sSQLWhereDay = 		
			  "    WHERE mod((DAT.DATE_ORI - trunc(D.ERINNERUNG_AB)) , D.INTERVALL ) = 0 "
			+ "    and DAT.DATE_ORI >= trunc(D.ERINNERUNG_AB) "
			+ "    and DAT.DATE_ORI <= sysdate ";
	
	protected static String sSQLWhereWeek = 
			  "    WHERE mod((DAT.DATE_ORI - trunc(D.ERINNERUNG_AB)) , D.INTERVALL * 7 ) = 0 "
			+ "    and DAT.DATE_ORI >= trunc(D.ERINNERUNG_AB)"
			+ "    and DAT.DATE_ORI <= sysdate ";
	
	protected static String sSQLWhereMonth = 
			  "    WHERE mod(months_between(trunc(DAT.DATE_ORI,'MM') , TRUNC(D.ERINNERUNG_AB,'MM')) ,  D.INTERVALL ) = 0 "
			+ "    and DAT.DAY = "
			+ "        case when (  extract(DAY from last_day(DAT.DATE_ORI)) - extract( DAY from D.ERINNERUNG_AB ) < 0 ) "
			+ "        then "
			+ "	         extract(DAY from last_day(DAT.DATE_ORI))"
			+ "        else "
			+ "          extract(DAY from D.ERINNERUNG_AB)"
			+ "        end "
			+ "    and DAT.DATE_ORI >= trunc(D.ERINNERUNG_AB)"
			+ "    and DAT.DATE_ORI <= sysdate ";
	
	protected static String sSQLWhereMonthNEG = 
			  "    WHERE mod(months_between(trunc(DAT.DATE_ORI,'MM') , TRUNC(  D.ERINNERUNG_AB,'MM')) ,  D.INTERVALL ) = 0 "
			+ "    and DAT.DAYS_LEFT_IN_MONTH = NVL(D.INTERVALL_PARAM,0) "
			+ "    and DAT.DATE_ORI >= trunc(D.ERINNERUNG_AB)"
			+ "    and DAT.DATE_ORI <= sysdate ";

	
	protected static String sSQLWhereYear = 
			  "    WHERE mod(months_between(trunc(DAT.DATE_ORI,'MM') , TRUNC(D.ERINNERUNG_AB,'MM')) ,  D.INTERVALL * 12 ) = 0 "
			+ "    and DAT.DAY = "
			+ "        case when (  extract(DAY from last_day(DAT.DATE_ORI)) - extract( DAY from D.ERINNERUNG_AB ) < 0 ) "
			+ "        then "
			+ "	         extract(DAY from last_day(DAT.DATE_ORI))"
			+ "        else "
			+ "          extract(DAY from D.ERINNERUNG_AB)"
			+ "        end "
			+ "    and DAT.DATE_ORI >= trunc(D.ERINNERUNG_AB)"
			+ "    and DAT.DATE_ORI <= sysdate ";

	
	
	
	protected static String sBaseSQL = 
			  " SELECT D.* ,"
			+ " to_char( ( select  max(DAT.DATE_ORI)"
			+ "    from " + bibE2.cTO() + ".JD_DATUM DAT"
			+ " ##WHERE## " 
			+ " ),'yyyy-mm-dd') "
			+ " as " + colName_DatumReminder 

			+ ", '#REMINDER#' || D.ID_REMINDER_DEF || '#' ||"
			+ " to_char( ( select  max(DAT.DATE_ORI)"
			+ "    from " + bibE2.cTO() + ".JD_DATUM DAT"
			+ " ##WHERE## "
			+ " ),'yyyy-mm-dd') || '#' "
			+ " as " + colName_KeyReminder 
			+ " FROM " + bibE2.cTO() + ".JT_REMINDER_DEF D"
			+ " ";

	
	
	public enum ENUM_REMINDER_WIEDERHOLUNG  {
		Day(new MyE2_String("Täglich"))
		,Week (new MyE2_String("Wöchentlich"))
		,Month(new MyE2_String("Monatlich"))
		,Year(new MyE2_String("Jährlich"))
		,Month_Neg(new MyE2_String("Anzahl-Tage vor Monatsende"))
		;
		
		private MyE2_String Description = null;
		
		
		 /*
		  *  @author manfred
		  * @date 24.03.2016
		  *
		  */
		private ENUM_REMINDER_WIEDERHOLUNG(MyE2_String sWiederholung) {
			this.Description = sWiederholung;
		}

		public String Description() {
			if (S.isEmpty(this.Description())) {
				return this.name();
			} else {
				return this.Description.CTrans();
			}
		}
		
		/**
		 * Name des Wiederholungstyps in der DB als Uppercase
		 * @return
		 */
		public String DB_Entry(){
			return this.name().toUpperCase();
		}
		
	}
	
	private static String cTO = bibE2.cTO()  ;

	
	
	private static String getStatementFor(ENUM_REMINDER_WIEDERHOLUNG enumWdh){
		String sRet = null;
		String sBaseWdh = "";
		
		switch (enumWdh) {
		case Day:
			
			// Default ist intervall-Typ TAG, also auch wenn feld leer
			sBaseWdh = sBaseSQL  
								+ " WHERE D.ABGESCHLOSSEN_AM IS NULL "
								+ " AND   D.ERINNERUNG_AB <= TRUNC(SYSDATE) "
								+ " AND   NVL( D." + REMINDER_DEF.intervall_typ.fn() + ",'" + ENUM_REMINDER_WIEDERHOLUNG.Day.DB_Entry() + "' ) = '" + ENUM_REMINDER_WIEDERHOLUNG.Day.DB_Entry() + "' "
								+ " " ;
			
			sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereDay);
			
			break;

		case Week:
			sBaseWdh = sBaseSQL  
			+ " WHERE D.ABGESCHLOSSEN_AM IS NULL "
			+ " AND   D.ERINNERUNG_AB <= TRUNC(SYSDATE) "
			+ " AND   D." + REMINDER_DEF.intervall_typ.fn() + " = '" + ENUM_REMINDER_WIEDERHOLUNG.Week.DB_Entry() + "' "
			+ " " ;
			
			sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereWeek);
			
			break;

		case Month:
			sBaseWdh = sBaseSQL  
			+ " WHERE D.ABGESCHLOSSEN_AM IS NULL "
			+ " AND   D.ERINNERUNG_AB <= TRUNC(SYSDATE) "
			+ " AND   D." + REMINDER_DEF.intervall_typ.fn() + " = '" + ENUM_REMINDER_WIEDERHOLUNG.Month.DB_Entry()+ "' " 
			+ " " ;
			
			sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereMonth);
			
			break;
			
		case Month_Neg:
			sBaseWdh = sBaseSQL  
			+ " WHERE D.ABGESCHLOSSEN_AM IS NULL "
			+ " AND   D.ERINNERUNG_AB <= TRUNC(SYSDATE) "
			+ " AND   D." + REMINDER_DEF.intervall_typ.fn() + " = '" + ENUM_REMINDER_WIEDERHOLUNG.Month_Neg.DB_Entry() + "' "
			+ " " ;
			
			sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereMonthNEG);
			
			break;

		case Year:
			sBaseWdh = sBaseSQL  
			+ " WHERE D.ABGESCHLOSSEN_AM IS NULL "
			+ " AND   D.ERINNERUNG_AB <= TRUNC(SYSDATE) "
			+ " AND   D." + REMINDER_DEF.intervall_typ.fn() + " = '" + ENUM_REMINDER_WIEDERHOLUNG.Year.DB_Entry() + "' " 
			+ " " ;
			
			sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereYear);
			
			break;
			
		default:
			break;
		}
		

		if (sBaseWdh != null){
			sRet = " SELECT * FROM ( " + sBaseWdh + ") WHERE "
					+ " " + colName_KeyReminder + " NOT IN (SELECT L.reminder_key from " + cTO + ".JT_REMINDER_LOG L WHERE L.reminder_key is not null)";
		}
		
		return sRet;
	}
	
	
	
	/**
	 * Konstruktur für die Liste der Reminder, die noch offenen Wiederholungen haben
	 * @author manfred
	 * @date 23.03.2016
	 *
	 * @throws myException
	 */
	public RECLIST_REMINDER_DEF_Ext(ENUM_REMINDER_WIEDERHOLUNG enumWdh ) throws myException {
		super(getStatementFor(enumWdh));
	}

	
	

	/**
	 * Spezielles Statement für genau eine ID
	 * @param id
	 * @return
	 * @throws myException 
	 */
	private static String getStatementForID(String id) throws myException{
		String sRet = null;
		if (id == null) {
			throw new myException("RECLIST_REMINDER_DEF_Ext::getStatementForID : ID nicht initialisiert!");
		}
		
		// herausfinden, welcher Reminder-Typ der Datensatz beschreibt:
		String intervall_typ = null;
		
		RECLIST_REMINDER_DEF rl = new RECLIST_REMINDER_DEF( REMINDER_DEF.id_reminder_def.fn() + " = " + id, "");
		if (rl.size() > 0){
			RECORD_REMINDER_DEF r = rl.get(0);
			intervall_typ = r.get_INTERVALL_TYP_cUF_NN(ENUM_REMINDER_WIEDERHOLUNG.Day.DB_Entry());
		
			// erzeugen des SQL
			String sBaseWdh = sBaseSQL;
			if (intervall_typ.equals(ENUM_REMINDER_WIEDERHOLUNG.Day.DB_Entry())){
				sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereDay);
			} else if (intervall_typ.equals(ENUM_REMINDER_WIEDERHOLUNG.Week.DB_Entry())) {
				sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereWeek);
			} else if (intervall_typ.equals(ENUM_REMINDER_WIEDERHOLUNG.Month.DB_Entry())) {
				sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereMonth);
			} else if (intervall_typ.equals(ENUM_REMINDER_WIEDERHOLUNG.Month_Neg.DB_Entry())) {
				sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereMonthNEG);
			} else if (intervall_typ.equals(ENUM_REMINDER_WIEDERHOLUNG.Year.DB_Entry())) {
				sBaseWdh = sBaseWdh.replaceAll("##WHERE##", sSQLWhereYear);
			} else {
				sBaseWdh = null;
			}
			
			String sSqlSingle = sBaseWdh  + " WHERE D.ID_REMINDER_DEF =  " + id ;
			sRet = " SELECT * FROM ( " + sSqlSingle + ")  ";
			
		}
		
		if (sRet== null){
			throw new myException("RECLIST_REMINDER_DEF_Ext::getStatementForID : Kein Reminder ermittelt.");
		}
		return sRet;
	}
	
	
	/**
	 * Konstruktor für eine Liste mit genau einem Reminder.
	 * Workaround, damit die Zusatz-Felder DATUM_REMINDER und KEY_REMINDER im Datensatz auftauchen
	 * @param idRReminderDef
	 * @throws myException
	 */
	public RECLIST_REMINDER_DEF_Ext(String idRReminderDef) throws myException{
		super(getStatementForID(idRReminderDef) );
	}
	
	
	
	
	
	
}
