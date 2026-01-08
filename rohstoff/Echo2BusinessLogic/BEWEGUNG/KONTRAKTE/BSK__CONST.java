package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;


public class BSK__CONST 
{
	
	// felder in allen datenbanktabellen, die global eingetragen werden
	public static String[] DATENBANK_EXCLUDES_IN_KOPF = {"ID_MANDANT","GEAENDERT_VON","LETZTE_AENDERUNG","BEMERKUNGEN_INTERN","FORMULARTEXT_ANFANG","FORMULARTEXT_ENDE"};
	public static String[] DATENBANK_EXCLUDES_IN_POS  = {"ID_MANDANT","GEAENDERT_VON","LETZTE_AENDERUNG","ARTBEZ2","EUNOTIZ"};
	public static String[] DATENBANK_EXCLUDES_IN_POSKONTRAKT  = {"ID_MANDANT","GEAENDERT_VON","LETZTE_AENDERUNG","BEMERKUNGEN_INTERN"};
	
	
	public static final String HASH_KEY_DATUM_VON_BIS = 							"HASH_KEY_DATUM_VON_BIS";
	public static final String HASH_KEY_ANZEIGE_LOCKED = 							"HASH_KEY_ANZEIGE_LOCKED";
	public static final String HASH_KEY_ANZEIGE_LOCKED_POS =						"HASH_KEY_ANZEIGE_LOCKED_POS";
	public static final String HASH_KEY_ANZEIGE_POSITIONS = 						"HASH_KEY_ANZEIGE_POSITIONS";
	
	
	public static final String HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS =				"HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS";
	public static final String HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS =				"HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS";
	
	public static E2_ResourceIcon LABEL_EMPTY = 									E2_ResourceIcon.get_RI("empty20.png")	;
	public static E2_ResourceIcon LABEL_KOPF_LOCKED  = 								E2_ResourceIcon.get_RI("locked.png")	;
	public static E2_ResourceIcon LABEL_POSITION_LOCKED  = 							E2_ResourceIcon.get_RI("locked.png");
	public static E2_ResourceIcon LABEL_POSITION_UNLOCKED  = 						E2_ResourceIcon.get_RI("unlocked.png");

	public static final String HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL = 				"HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL";

	public static final String HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_PRINTPROTOKOLL = 	"HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_PRINTPROTOKOLL";

	public static final String HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_LIEFERTERMIN = 	"HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_LIEFERTERMIN";
	public static final String HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_LAGERLIEFERUNG = 	"HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_LAGERLIEFERUNG";

	
	public static final String HASH_KEY_DAUGHTERTABLE_POSITIONS = 					"HASH_KEY_DAUGHTERTABLE_POSITIONS";
	

	public static final String HASH_KEY_DAUGHTERTABLE_LAGERLIEFERUNG = 				"HASH_KEY_DAUGHTERTABLE_LAGERLIEFERUNG";
	public static final String HASH_KEY_DAUGHTERTABLE_LIEFERTERMINE = 				"HASH_KEY_DAUGHTERTABLE_LIEFERTERMINE";
	public static final String HASH_KEY_DAUGHTERTABLE_AENDERUNGSPROTOKOLL = 		"HASH_KEY_DAUGHTERTABLE_AENDERUNGSPROTOKOLL";
	
	public static final String HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH = 			"HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH";
	public static final String HASH_KEY_POSITION_MASK_SET_NEXT_MONTH = 				"HASH_KEY_POSITION_MASK_SET_NEXT_MONTH";
	
//	public static final String HASH_KEY_KOPF_MASK_SET_ACTUAL_MONTH = 				"HASH_KEY_KOPF_MASK_SET_ACTUAL_MONTH";
//	public static final String HASH_KEY_KOPF_MASK_SET_NEXT_MONTH = 					"HASH_KEY_KOPF_MASK_SET_NEXT_MONTH";



	
	public static int  KONTRAKTPOS_OK = 					1000000;    // alles im Rahmen
	public static int  KONTRAKTPOS_UEBERLIEFERT_ABER_OK = 	1000001;    // ueberliefert, aber erlaubt
	public static int  KONTRAKTPOS_UEBERLIEFERT  = 			1000002;    // ueberliefert, aber verboten
	

	
	

	//2013-09-25: neue query-felder zum einblenden von kopf-infos in positionsmasken
	public static String HASHKEY_VK_INFO_FIRMA = 			"HASH_KEY_VK_INFO_FIRMA";
	public static String HASHKEY_VK_INFO_BUCHUNGNR = 		"HASHKEY_VK_INFO_BUCHUNGNR";
	public static String HASHKEY_VK_INFO_BUCHUNGPOSNR = 	"HASHKEY_VK_INFO_BUCHUNGPOSNR";
	public static String HASHKEY_VK_INFO_SORTE = 			"HASHKEY_VK_INFO_SORTE";
	public static String HASHKEY_VK_INFO_MENGE = 			"HASHKEY_VK_INFO_MENGE";
	public static String HASHKEY_VK_INFO_PREIS = 			"HASHKEY_VK_INFO_PREIS";
	
	
	// validatoren
	
	
	public static E2_Validator_ID_DBQuery          VALID_VPOS_KON_KEINEN_FUHREN_ZUGEORDNET =
							new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE " +
					                "   NVL(DELETED,'N')='N' AND ( ID_VPOS_KON_EK=#WERT# OR  ID_VPOS_KON_VK=#WERT#) ",
									bibALL.get_Array("0"),
									true, new MyE2_String("Aktion ist verboten, die Kontraktposition habt bereits zugeordnete Fuhren !"));

	
	
	public static E2_Validator_ID_DBQuery          VALID_VPOS_KON_KEINE_EK_VK_ZUORDNUNGEN =
					new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE "+
							"ID_VPOS_KON_EK=#WERT#" + " OR " +
							"ID_VPOS_KON_VK=#WERT#",
							bibALL.get_Array("0"),
							true, new MyE2_String("Aktion ist verboten, die Kontraktposition bereits EK-VK-Zuordnungen hat!"));
					
	public static E2_Validator_ID_DBQuery          VALID_VPOS_KON_KOPF_OFFEN =
							new E2_Validator_ID_DBQuery("SELECT    NVL(JT_VKOPF_KON.ABGESCHLOSSEN,'N') " +
									" FROM "+bibE2.cTO()+".JT_VPOS_KON, "+bibE2.cTO()+".JT_VKOPF_KON, "+bibE2.cTO()+".JT_VPOS_KON_TRAKT   " +
									" WHERE " +
									" JT_VPOS_KON_TRAKT.ID_VPOS_KON = JT_VPOS_KON.ID_VPOS_KON AND " +
									" JT_VPOS_KON.ID_VKOPF_KON  = JT_VKOPF_KON.ID_VKOPF_KON AND " +
									" JT_VPOS_KON.ID_VPOS_KON=#WERT#",
									bibALL.get_Array("N"),
									true, new MyE2_String("Aktion ist verboten, da der Kontrakt-Kopf bereits geschlossen ist!"));

	
	
	public static E2_Validator_ID_DBQuery          VALID_VPOS_KON_POSITION_OFFEN =
										new E2_Validator_ID_DBQuery("SELECT   NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N') " +
												" FROM "+bibE2.cTO()+".JT_VPOS_KON, "+bibE2.cTO()+".JT_VPOS_KON_TRAKT   " +
												" WHERE " +
												" JT_VPOS_KON_TRAKT.ID_VPOS_KON = JT_VPOS_KON.ID_VPOS_KON AND " +
												" JT_VPOS_KON.ID_VPOS_KON=#WERT#",
												bibALL.get_Array("N"),
												true, new MyE2_String("Aktion ist verboten, da Position bereits geschlossen ist!"));

	
	
	
	public static E2_Validator_ID_DBQuery          VALID_VPOS_KON_ARTIKELPOS_MENGE_PREIS_NOT_LEER = 
									new E2_Validator_ID_DBQuery("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON"+
												" WHERE POSITION_TYP = '"+myCONST.VG_POSITION_TYP_ARTIKEL+"' AND " +
												" ANZAHL IS NOT NULL AND EINZELPREIS IS NOT NULL AND "+
												" ID_VPOS_KON=#WERT#",
												bibALL.get_Array("1"),
												true, new MyE2_String("Nur bei komplett ausgefüllten Mengenpositionen erlaubt !"));

	
	
	
	
	public static String  			BUTTON_TO_LOAD_ADRESSE = "BUTTON_TO_LOAD_ADRESSE";
	
	
	public static String  			BUTTON_TO_TAKE_LIEFERORTE = "BUTTON_TO_TAKE_LIEFERORTE";

	
	//2011-12-05: Sprungbutton von kontraktlist in die fuhre
	public static String 			BUTTON_JUMP_VON_KOPF_TO_FUHRENZENTRALE = "BUTTON_JUMP_VON_KOPF_TO_FUHRENZENTRALE";
	
	

	
	public static GridLayoutData    get_GreenLayout4OpenCloseButton()
	{
		GridLayoutData  greenBack = new GridLayoutData();
		greenBack.setInsets(E2_INSETS.I_1_1_1_1);
		greenBack.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		greenBack.setBackground(Color.GREEN);
		
		return greenBack;
	}
	

	public static GridLayoutData    get_RedLayout4OpenCloseButton()
	{
		GridLayoutData  redBack = new GridLayoutData();
		redBack.setInsets(E2_INSETS.I_1_1_1_1);
		redBack.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		redBack.setBackground(Color.RED);
		
		return redBack;
	}
	


}

