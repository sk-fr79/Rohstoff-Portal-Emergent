package panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BUTTON;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BUTTON;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.dataTools.query.Term;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public class VALID_ENUM_MODULES {
	
	public static String PREFIX_4_ATTACHMENT_POPUPS = "ATTACHMENT_"; 
	
	
	public static enum RANGE_KEY implements IF_enum_4_db{
	
	 	 ATTACHMENT_VKOPF_RG(					new MyE2_String("Anlagen zu Rechnungen/Gutschriften"), 					ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_ADRESSE(					new MyE2_String("Anlagen zu Firmenstamm"),								ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_ARTIKEL(					new MyE2_String("Anlagen zu Sortenstamm"),								ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_MASCHINEN(					new MyE2_String("Anlagen zu Maschinenstamm"), 							ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_VPOS_TPA_FUHRE(				new MyE2_String("Anlagen zu Fuhrensätzen"),	 							ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_FBAM(						new MyE2_String("Anlagen zu Beanstandungsmeldungen"),					ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_FBAM_DRUCK(					new MyE2_String("Anlagen zu Druckprotokoll (Beanstandungsmeldungen)"), 	ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_VKOPF_KON_DRUCK(			new MyE2_String("Anlagen zu Druckprotokoll (Kontrakte)"), 				ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_VKOPF_RG_DRUCK(				new MyE2_String("Anlagen zu Druckprotokoll (Rechungen/Gutschriften)"), 	ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_VKOPF_STD_DRUCK(			new MyE2_String("Anlagen zu Druckprotokoll (Angebote)"), 				ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_VKOPF_TPA_DRUCK(			new MyE2_String("Anlagen zu Druckprotokoll (Transportaufträge)"), 		ATTACHMENT_BUTTONS.values())
		,ATTACHMENT_VPOS_TPA_FUHRE_DRUCK(		new MyE2_String("Anlagen zu Druckprotokoll (Fuhren)"), 					ATTACHMENT_BUTTONS.values())		
		,ATTACHMENT_ALL_OTHERS(					new MyE2_String("Anlagen (Allgemein)"), 									ATTACHMENT_BUTTONS.values())
		
		
		;
		
		private String 							Lesbar4User = 	null;
		private Vector<IF_enum_4_db> 			vAKTION = 		new Vector<IF_enum_4_db>();
		
		private RANGE_KEY(String Lesbar, IF_enum_4_db... aktionen) {
			this.Lesbar4User = Lesbar;
			for (IF_enum_4_db en: aktionen) {
				this.vAKTION.add(en);
			}
		}
		
		private RANGE_KEY(MyE2_String Lesbar, IF_enum_4_db... aktionen) {
			this.Lesbar4User = Lesbar.CTrans();
			for (IF_enum_4_db en: aktionen) {
				this.vAKTION.add(en);
			}
		}

		
//		public String get_Lesbar4User() {
//			return this.Lesbar4User;
//		}
		
//		public String get_KEY_4_DB() {
//			return this.name();
//		}

		@Override
		public String db_val() {
			return this.name();
		}

		@Override
		public String user_text() {
			return this.Lesbar4User;
		}

		@Override
		public String user_text_lang() {
			return this.Lesbar4User;
		}

		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(RANGE_KEY.values(), emptyPairInFront);
		}
	}

	
	
	
	
	
	/**
	 * checking whether modulname contains Button-Key B (when not, then validation is refused)
	 * @param modul
	 * @param button
	 * @return
	 */
	public static boolean is_Module_Containing_Button(RANGE_KEY modul, IF_enum_4_db button) {
		for (IF_enum_4_db but:  modul.vAKTION) {
			if (but.equals(button)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static RANGE_KEY find_RANGE_KEY(String modulKeyName) {
		for (RANGE_KEY ev: RANGE_KEY.values()) {
			if (ev.name().equals(modulKeyName)) {
				return ev;
			}
		}
		return null;
	}
	
	/**
	 * checking whether modulname is registered here
	 * @param modulKenner
	 * @return
	 */
	public static boolean is_Modul_Registered(String modulKenner) {
		return (VALID_ENUM_MODULES.find_RANGE_KEY(modulKenner)!=null);
	}


	
	public static void create_DB_Button_Entrys_4_all_ModuleRanges(Vector<MyString> v4Meldungen) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		for (RANGE_KEY r_key: RANGE_KEY.values()) {
			for (IF_enum_4_db b_Key: r_key.vAKTION) {
				RECLIST_BUTTON  rl_bt = new RECLIST_BUTTON(
						new SELECT("*").from(_DB.BUTTON).
								where(Term.field(_DB.BUTTON$MODULKENNER),Term.value(r_key.db_val())).
								and(Term.field(_DB.BUTTON$BUTTONKENNER),Term.value(b_Key.db_val())));
				
				if (rl_bt.size()==0) {
					//dann reinschreiben
					RECORDNEW_BUTTON rn_bt = new RECORDNEW_BUTTON();
					rn_bt.set_NEW_VALUE_MODULKENNER(r_key.db_val());
					rn_bt.set_NEW_VALUE_BUTTONKENNER(b_Key.db_val());
					
					rn_bt.do_WRITE_NEW_BUTTON(mv);

					if (mv.get_bHasAlarms()) {
						break;
					} else {
						if (v4Meldungen==null) {
							mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(S.t("Geschrieben:"),S.ut(r_key.db_val()+"/"+b_Key.db_val()))));
						} else {
							v4Meldungen.add(new MyE2_String(S.t("Geschrieben:"),S.ut(r_key.db_val()+"/"+b_Key.db_val())));
						}
					}
				}
			}
		
			if (mv.get_bHasAlarms()) {
				break;
			}

		}
		
		bibMSG.add_MESSAGE(mv);
		
	}
	
	
}
 