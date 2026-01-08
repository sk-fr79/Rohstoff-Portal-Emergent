package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.TOOLS.RB_cld;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_TPA;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU_LIST_ComponentMAP_SubQueryAgentCheckLieferscheinDruck extends XX_ComponentMAP_SubqueryAGENT {

	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException {

	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
		
		//falls das es eine buchungsnummer gibt, aber noch keinen aktiven liferschein-druck, dann 
		//die buchungsnummer mit farbe hervorheben
		HashMap<String, MyE2IF__Component>  hmRealComponents = oMAP.get_hmRealComponents();
		
		RB_cld ld4tpa = new RB_cld()._center_mid();
		RB_cld ld4fuhre = new RB_cld()._center_mid();
		
		MyE2_DB_Label_INROW  c4tpa = 	(MyE2_DB_Label_INROW)hmRealComponents.get(VKOPF_TPA.buchungsnummer.fn());
		MyE2_DB_Label_INROW  c4fuhre = 	(MyE2_DB_Label_INROW)hmRealComponents.get(VPOS_TPA_FUHRE.buchungsnr_fuhre.fn());

		if (oUsedResultMAP.get_LActualDBValue(FU___CONST.addonFields.AA_ZAHL_LIEFERSCHEIN_DRUCKE.alias(), true).longValue()==0) {
			if (S.isFull(oUsedResultMAP.get_FormatedValue(VPOS_TPA_FUHRE.buchungsnr_fuhre.fn()))) {
				ld4fuhre.setBackground(bibALL.get_colorMaskHighLight());
			    c4fuhre.get_oErsatzButton()._ttt("Fuhre hat noch keinen Lieferscheindruck hinterlegt !!");
			}
		}

		c4tpa.get_oRowContainer().setLayoutData(ld4tpa);
		c4fuhre.get_oRowContainer().setLayoutData(ld4fuhre);
	}

}
