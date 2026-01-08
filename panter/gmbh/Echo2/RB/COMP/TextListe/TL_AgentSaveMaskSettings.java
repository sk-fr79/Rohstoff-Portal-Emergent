/**
 * panter.gmbh.Echo2.RB.COMP.TextListe
 * @author martin
 * @date 19.02.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 19.02.2020
 *
 */
public class TL_AgentSaveMaskSettings extends XX_ActionAgent {

	private RB_ComponentMap  		map = null;
	private EnumTableTranslator     tabTrans = null;
	
	/**
	 * @author martin
	 * @date 19.02.2020
	 *
	 */
	public TL_AgentSaveMaskSettings(RB_ComponentMap  p_map, EnumTableTranslator p_tabTrans) {
		map=p_map;
		this.tabTrans= p_tabTrans;
	}

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		RB_MaskController mc = new RB_MaskController(map);
		
		VEK<String>  toSave = new VEK<>();
		
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.fontsize_titel_text),"10"));
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.fontsize_aufzaehl_text),"10"));
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.fontsize_lang_text),"10"));

		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.bold_titel_text),"N"));
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.bold_aufzaehl_text),"N"));
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.bold_lang_text),"N"));
		
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.italic_titel_text),"N"));
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.italic_aufzaehl_text),"N"));
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.italic_lang_text),"N"));

		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.underline_titel_text),"N"));
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.underline_aufzaehl_text),"N"));
		toSave._a(S.NN(mc.get_liveVal(TEXT_LISTE.underline_lang_text),"N"));
		
		int count = new TL_SaveSettingsOnMask(tabTrans).STORE_Vector(toSave);
		if (count != 1) {
			bibMSG.MV()._addWarn(S.ms("Probleme beim Speichern der Einstellungen ...").ut("<853d5d22-5333-11ea-8d77-2e728ce88125>"));
		}
		
		
	}

}
