package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import panter.gmbh.Echo2.E2_ResourceIcon;

public class BSAAL__CONST 
{
	public enum SONDERSPALTEN {
		A_ID_ADRESSE
		,WWW_BUTTON
		
	}
	
	public static final String HASH_KEY_ANZEIGE_VORMONAT = 		"HASH_KEY_ANZEIGE_VORMONAT";
	public static final String HASH_KEY_ANZEIGE_SYMBOL = 		"HASH_KEY_ANZEIGE_SYMBOL";
	public static final String HASH_KEY_DATUM_VON_BIS = 		"HASH_KEY_DATUM_VON_BIS";
	public static final String HASH_KEY_ANZEIGE_LOCKED = 		"HASH_KEY_ANZEIGE_LOCKED";
	
	public static final String BUTTON_EDIT_PRICELIST = 			"BUTTON_EDIT_PRICELIST";
	public static final String BUTTON_DELETE_POSITION = 		"BUTTON_DELETE_POSITION";
	public static final String BUTTON_UNLOCK_POSITION = 		"BUTTON_UNLOCK_POSITION";
	public static final String BUTTON_AENDERE_DATUM_VON_BIS = 	"BUTTON_AENDERE_DATUM_VON_BIS";
	public static final String BUTTON_CALC_VORMONATSPREIS = 	"BUTTON_CALC_VORMONATSPREIS";
	public static final String BUTTON_BUILD_ANGEBOTE = 			"BUTTON_BUILD_ANGEBOTE";
	public static final String BUTTON_CREATE_ORACLE_SQL = 		"BUTTON_CREATE_ORACLE_SQL";
	public static final String BUTTON_RELOAD_LISTE = 			"BUTTON_RELOAD_LISTE";
	public static final String BUTTON_COPY_POSITIONS = 			"BUTTON_COPY_POSITIONS";
	public static final String BUTTON_CREATE_AND_SEND_PAPERS =	"BUTTON_CREATE_AND_SEND_PAPERS";
	
	
	public static E2_ResourceIcon LABEL_EMPTY = E2_ResourceIcon.get_RI("empty20.png")	;
	public static E2_ResourceIcon LABEL_START_POSITION = E2_ResourceIcon.get_RI("liststart.png")	;
	public static E2_ResourceIcon LABEL_MIDDLE_POSITION  = E2_ResourceIcon.get_RI("listmiddle.png")	;
	public static E2_ResourceIcon LABEL_POSITION_DELETED  = E2_ResourceIcon.get_RI("deleted.png");

	public static E2_ResourceIcon LABEL_END_POSITION  = E2_ResourceIcon.get_RI("listend.png")	;
	public static E2_ResourceIcon LABEL_ONE_POSITION  = E2_ResourceIcon.get_RI("listone.png")	;
	public static E2_ResourceIcon LABEL_POSITION_LOCKED  = E2_ResourceIcon.get_RI("locked.png");
	
	// warnschwelle zum vormonatswert
	public static double WarningVormonat = 0.2;
	
	//2015-12-14: neue spalte in der preisliste
	public static final String H_ID_VPOS_STD = 					"H_ID_VPOS_STD";

	
	
	

}
