package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ENUM_BEWEGUNG_TYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;
import panter.gmbh.indep.enumtools.IF_enum_persistable;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_BEWEGUNGTYP  implements IF_enum_4_db_specified<ENUM_BEWEGUNGTYP>, IF_enum_persistable<ENUM_BEWEGUNGTYP> {
	
	
	STANDARD(		true, 	"Standardbewegung", 				"Standardbewegung")
	,FAHRPLAN(		true, 	"Fahrplan",							"Fahrplan, bestehend aus mehreren Vektoren" )
	,FUHRE_EINFACH(	false, 	"Umgesetzte Fuhre, Standardfall", 	"Umgesetzte Fuhre, Standardfall")
	,FUHRE_1_ZU_N(	false, 	"Umgesetzte Fuhre, Nx1",			"Umgesetzte Fuhre, mehrere Ladestationen, eine Abladestation")
	,FUHRE_N_ZU_1(	false, 	"Umgesetzte Fuhre, 1xN",			"Umgesetzte Fuhre, eine Ladestation, mehrere Abladestationen")
	,FUHRE_N_ZU_M(	false, 	"Umgesetzte Fuhre, NxM", 			"Umgesetzte Fuhre, mehrere Ladestationen, mehrere Abladestationen")
	
	;

	private boolean atombasiert = true;	        //true=neuer Bewegungssatz, false=konvert aus fuhre
	private String  text = null;
	private String  text_lang = null;
	
	private ENUM_BEWEGUNGTYP(boolean p_atombasiert, String p_text, String p_text_lang) {
		this.atombasiert = 	p_atombasiert;
		this.text = 		p_text;
		this.text_lang = 	p_text_lang;
	}
	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text_lang() {
		return S.NN(this.text_lang,this.user_text());
	}
	
	
	@Override
	public String user_text() {
		return S.NN(this.text,this.name());
	}
	
	
	@Override
	public _TAB get_tab() {
		return _TAB.enum_bewegung_typ;
	}

	@Override
	public boolean enum_equals_record(ENUM_BEWEGUNGTYP en, Rec20 rec_enumTab) throws myException {
		boolean b_gleich = true;
		if (en.atombasiert!=rec_enumTab.is_yes_db_val(ENUM_BEWEGUNG_TYP.atombasiert)) {
			b_gleich = false;
		}
		if (!en.db_val().equals(rec_enumTab.get_ufs_dbVal(ENUM_BEWEGUNG_TYP.bewegung_typ))){
			b_gleich = false;
		}
		if (!en.user_text().equals(rec_enumTab.get_ufs_dbVal(ENUM_BEWEGUNG_TYP.text))){
			b_gleich = false;
		}
		if (!en.user_text_lang().equals(rec_enumTab.get_ufs_dbVal(ENUM_BEWEGUNG_TYP.text_lang))){
			b_gleich = false;
		}
		return b_gleich;
	}

	@Override
	public ENUM_BEWEGUNGTYP[] get_Values() {
		return ENUM_BEWEGUNGTYP.values();
	}

	@Override
	public Rec20 get_new_rec20_with_enum_vals(MyE2_MessageVector mv) throws myException {
		Rec20 r = new Rec20(_TAB.enum_bewegung_typ);
		
		r	.nv(ENUM_BEWEGUNG_TYP.atombasiert, this.atombasiert?"Y":"N", 	mv)
			.nv(ENUM_BEWEGUNG_TYP.bewegung_typ, this.db_val(), 				mv)
			.nv(ENUM_BEWEGUNG_TYP.text, this.user_text(), 					mv)
			.nv(ENUM_BEWEGUNG_TYP.text_lang, this.user_text_lang(), 		mv)
		;
		
		return r;	}

	public boolean is_atombasiert() {
		return atombasiert;
	}




}
