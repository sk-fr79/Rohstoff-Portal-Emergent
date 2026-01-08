package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.COMP.BETA.RB_TextArea_Selector;
import panter.gmbh.basics4project.DB_ENUMS.TEXTBLOCK;
import panter.gmbh.basics4project.DB_ENUMS.TEXTBLOCK_KAT;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_Formular_TextArea extends RB_TextArea_Selector{

	private boolean isAnfang = false;
	private boolean isEk = false;
	
	public KFIX_K_M_Formular_TextArea(boolean isEk, int iWidthInPixel, int iRows) throws myException {
		super(iWidthInPixel, iRows, true);

		this.isEk = isEk;
	}

	@Override
	public String queryForPopup() throws myException {
		
		if(this.rb_KF().get_HASHKEY().equals(VKOPF_KON.formulartext_anfang.fieldName())){
			isAnfang = true;
		}
		
		SEL query = new SEL()
				.ADDFIELD(TEXTBLOCK.texttitel.fieldName(), "A")
				.ADDFIELD(TEXTBLOCK.text.fieldName(), "B")
				.FROM(_TAB.textblock.fullTableName()+", " +_TAB.textblock_kat.fullTableName(), "")
				.WHERE(new vgl(TEXTBLOCK.id_mandant, ""+bibALL.get_ID_MANDANT()))
				.AND(new vgl(TEXTBLOCK.id_textblock_kat, TEXTBLOCK_KAT.id_textblock_kat));
		
		String anfangPrefix = "_KONTRAKTANFANG";
		String endePrefix 	= "_KONTRAKTENDE";
		
		String ek_vk_pre 		= isEk?"EK":"VK";
		
		if(isAnfang){
			query.WHERE(new vgl(TEXTBLOCK_KAT.bezeichnung,ek_vk_pre + anfangPrefix));
		}else{
			query.WHERE(new vgl(TEXTBLOCK_KAT.bezeichnung,ek_vk_pre + endePrefix));
		}
		
		return query.s();
	}

	@Override
	public String get_kategorie_if_no_eintraege() {
		String ek_vk_pre 		= isEk?"EK":"VK";
		String kategorie = "";
		
		if(isAnfang){
			kategorie=ek_vk_pre + "_KONTRAKTANFANG";
		}else{
			kategorie=ek_vk_pre + "_KONTRAKTENDE";
		}
		return kategorie;
	}

}
