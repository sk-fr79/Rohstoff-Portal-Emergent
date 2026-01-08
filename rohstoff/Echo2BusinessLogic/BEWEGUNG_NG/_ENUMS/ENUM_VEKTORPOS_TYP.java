package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS.ENUM_VEKTOR_POS_TYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;
import panter.gmbh.indep.enumtools.IF_enum_persistable;
import panter.gmbh.indep.exceptions.myException;

/**
 * eintraege, die aus den erfassungmaskentypen im Feld JT_BEWEGUNG_VEKTOR_POS.POS_TYP geschrieben werden 
 * @author martin
 *
 */
public enum ENUM_VEKTORPOS_TYP implements   IF_enum_4_db_specified<ENUM_VEKTORPOS_TYP>, IF_enum_persistable<ENUM_VEKTORPOS_TYP>{

	 WE_MAIN(						true,	false,	"Hauptatom eines Wareneingangs")
	,WE_HIDDEN_SEP(					false,	false,	"Internes Umbuchungspaar in einem Wareneingangsvektor")
	,WE_MENGEN_DIFF_LIEFERANT_LAGER(false,	true, 	"Internes Mengendifferenz-Buchungspaar bei Wareneingängen")
	,LL_MAIN(						true, 	false,	"Lager-Lager-Bewegung")
	,LL_CHANGE_SORT(				false, 	true,	"Lager-Lager-Sortenwechsel")
	,LL_CHANGE_MENGE(				false,	true,	"Lager-Lager-Mengendifferenz")
	,LG_MAIN( 						true,  	false,	"Leergutfuhre")
	,ST_MAIN_LEFT(					true, 	false,	"Linke Seite einer Strecken-Bewegung")
	,ST_MAIN_RIGHT(					true, 	false,	"Rechte Seite einer Strecken-Bewegung")
	,ST_CHANGE_SORT(				false, 	true,	"Strecken-Sortenwechsel")
	,ST_CHANGE_MENGE(				false, 	true,	"Strecken-Mengendifferenz")
	,WA_MAIN(						true, 	false, 	"Hauptatom eines Warenausgang")
	,TS_MAIN(						true, 	false, 	"Test/Probe Fuhre")
	
	//23-08-2017 @ Sebastien: 2 neue typ fuer die Umbuchung
	,WE_UMB(						false, true, "Wareneingang Umbuchung")
	,WA_UMB(						false, true, "Warenausgang Umbuchung")
	;

	//definiert, ob es eine Maskenzeile zur Bearbeitung ist  
	private boolean 		f_is_edit_row = false;
	private boolean         f_delete_and_write_at_savecycle = false;
	private String  		f_info_text = null; 
	
	

	private ENUM_VEKTORPOS_TYP(boolean is_editRow, boolean delete_and_write_at_savecycle, String info_text) {
		this.f_is_edit_row = 					is_editRow;
		this.f_delete_and_write_at_savecycle = 	delete_and_write_at_savecycle;
		this.f_info_text = 						info_text;
	}

	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	/**
	 * return s translated text 
	 */
	public String user_text() {
		return new MyE2_String(this.f_info_text).CTrans();
	}

	/**
	 * return s untranslated text 
	 */
	public String user_text_ut() {
		return this.f_info_text;
	}

	
	
	@Override
	public String user_text_lang() {
		return this.f_info_text;
	}

	
	public boolean is_edit_row() {
		return this.f_is_edit_row;
	}

//	/**
//	 * wenn true, dann ist diese position involviert in die bestimmung der "hauptwarenbewegung" 
//	 */
//	public boolean is_beinhaltet_haupt_ort() {
//		return f_beinhaltet_haupt_ort;
//	}
	
	/**
	 * statische methode um den postyp anhand des datenbank-eintrags zu identifizieren
	 * @param db_key
	 * @return
	 */
	public static ENUM_VEKTORPOS_TYP get_vektor_pos_typ(String db_key) {
		ENUM_VEKTORPOS_TYP pos_typ = null;
		
		for (ENUM_VEKTORPOS_TYP pt: ENUM_VEKTORPOS_TYP.values()) {
			if (pt.db_val().equals(db_key)) {
				pos_typ=pt;
				break;
			}
		}
		return pos_typ;
	}
	
	

	/**
	 * stellt fest, ob ein RECORD_BEWEGUNG_VEKTOR_POS einen automatischen loesch-schreib-zyklus erfuellt
	 * @param rec_bewegung_vektor_pos
	 * @return
	 * @throws myException
	 */
	public static boolean is_autoDelete_at_saveCycle(RECORD_BEWEGUNG_VEKTOR_POS rec_bewegung_vektor_pos) throws myException {
		ENUM_VEKTORPOS_TYP pos_typ = ENUM_VEKTORPOS_TYP.get_vektor_pos_typ(rec_bewegung_vektor_pos.get_POS_TYP_cUF_NN(""));
		if (pos_typ!=null) {
			return pos_typ.f_delete_and_write_at_savecycle;
		}
		return false;
	}
	
	
	
	/**
	 * statische methode um den postyp anhand des datenbank-eintrags zu identifizieren
	 * @param db_key
	 * @return
	 */
	public static boolean  is_pos_typ_4_mask(String db_key) throws myException {
		ENUM_VEKTORPOS_TYP pos_typ = ENUM_VEKTORPOS_TYP.get_vektor_pos_typ(db_key);
		
		if (pos_typ==null) {
			throw new myException("Cannot identify VEKTOR_POS_TYP");
		}
		return pos_typ.is_edit_row();
	}

	
	public boolean is_delete_and_write_at_savecycle() {
		return f_delete_and_write_at_savecycle;
	}
	
	
	
	public static And  get_where_statement_query_all_automatic_types() throws myException {
		And  and = new And();
		
		for (ENUM_VEKTORPOS_TYP v: ENUM_VEKTORPOS_TYP.values()) {
			if (v.f_delete_and_write_at_savecycle) {
				and.or(new vgl(BEWEGUNG_VEKTOR_POS.pos_typ, v.db_val()));
			}
		}
		
		return and;
	}


	@Override
	public _TAB get_tab() {
		return _TAB.enum_vektor_pos_typ;
	}


	@Override
	public boolean enum_equals_record(ENUM_VEKTORPOS_TYP en, Rec20 rec_enumTab) throws myException {
		boolean b_gleich = true;
		if (en.f_delete_and_write_at_savecycle!=rec_enumTab.is_yes_db_val(ENUM_VEKTOR_POS_TYP.automatic_delete_write)) {
			b_gleich = false;
		}
		if (en.f_is_edit_row!=rec_enumTab.is_yes_db_val(ENUM_VEKTOR_POS_TYP.edit_row)) {
			b_gleich = false;
		}
		if (!en.f_info_text.equals(rec_enumTab.get_ufs_dbVal(ENUM_VEKTOR_POS_TYP.info))){
			b_gleich = false;
		}
		if (!en.f_info_text.equals(rec_enumTab.get_ufs_dbVal(ENUM_VEKTOR_POS_TYP.info))){
			b_gleich = false;
		}
		if (!en.db_val().equals(rec_enumTab.get_ufs_dbVal(ENUM_VEKTOR_POS_TYP.name))){
			b_gleich = false;
		}
		return b_gleich;
	}


	@Override
	public ENUM_VEKTORPOS_TYP[] get_Values() {
		return ENUM_VEKTORPOS_TYP.values();
	}


	@Override
	public Rec20 get_new_rec20_with_enum_vals(MyE2_MessageVector mv) throws myException {
		Rec20 r = new Rec20(_TAB.enum_vektor_pos_typ);
		
		r	.nv(ENUM_VEKTOR_POS_TYP.automatic_delete_write, this.f_delete_and_write_at_savecycle?"Y":"N", mv)
			.nv(ENUM_VEKTOR_POS_TYP.edit_row, this.f_is_edit_row?"Y":"N", mv)
			.nv(ENUM_VEKTOR_POS_TYP.info, this.f_info_text, mv)
			.nv(ENUM_VEKTOR_POS_TYP.name, this.db_val(), mv)
		;
		
		return r;
	}

	
	
}
