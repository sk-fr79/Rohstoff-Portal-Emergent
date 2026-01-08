package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.COMP.BETA.RB_TextArea_Selector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.TEXTBLOCK;
import panter.gmbh.basics4project.DB_ENUMS.TEXTBLOCK_KAT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_position_zusatzinfo_TextArea extends RB_TextArea_Selector {

	private String belegTyp = "";

	public KFIX_P_M_position_zusatzinfo_TextArea(String oBelegTyp, int iWidthInPixel, int iRows) throws myException {
		super(iWidthInPixel, iRows,true);
		this.belegTyp = oBelegTyp;
	}

	@Override
	public String queryForPopup() throws myException {

		SEL query = new SEL()
				.ADDFIELD(TEXTBLOCK.texttitel.fieldName(), "A")
				.ADDFIELD(TEXTBLOCK.text.fieldName(), "B")
				.FROM(_TAB.textblock.fullTableName()+", " +_TAB.textblock_kat.fullTableName(), "")
				.WHERE(new vgl(TEXTBLOCK.id_mandant, ""+bibALL.get_ID_MANDANT()))
				.AND(new vgl(TEXTBLOCK.id_textblock_kat, TEXTBLOCK_KAT.id_textblock_kat));

		if(this.rb_KF().get_HASHKEY().equals(VPOS_KON_TRAKT.bemerkung_extern.fieldName())){
			query.AND(new vgl(TEXTBLOCK_KAT.bezeichnung, "KONTRAKT_POS_BEMERKUNGEN_BELEG_INT"));
		}else if(this.rb_KF().get_HASHKEY().equals(VPOS_KON_TRAKT.fixierungsbedingungen.fieldName())){
			if(belegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)){
				query.AND(new vgl(TEXTBLOCK_KAT.bezeichnung, "EK_KONTRAKT_POS_FIXIERUNGSBED"));
			}else{
				query.AND(new vgl(TEXTBLOCK_KAT.bezeichnung, "VK_KONTRAKT_POS_FIXIERUNGSBED"));
			}

		}
		return query.s();
	}

	@Override
	public String get_kategorie_if_no_eintraege() throws myException {
		if(this.rb_KF().get_HASHKEY().equals(VPOS_KON_TRAKT.fixierungsbedingungen.fieldName())){
			if(belegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)){
				return "EK_KONTRAKT_POS_FIXIERUNGSBED";
			}else{
				return "VK_KONTRAKT_POS_FIXIERUNGSBED";	
			}
		}else if(this.rb_KF().get_data_field().equals(VPOS_KON_TRAKT.bemerkung_extern)){
			return "KONTRAKT_POS_BEMERKUNGEN_BELEG_INT";
		}else{ 
			return "";
			}
	}

}
