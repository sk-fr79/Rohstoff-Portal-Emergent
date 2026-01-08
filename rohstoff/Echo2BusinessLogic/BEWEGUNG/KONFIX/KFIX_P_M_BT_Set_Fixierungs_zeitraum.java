package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_BT_Set_Fixierungs_zeitraum extends E2_Button{

	private RB_ComponentMap rbMASK;
	private Rec20 record_kopf = null;
	private String str_datum_von="";
	private String str_datum_bis="";
	
	public KFIX_P_M_BT_Set_Fixierungs_zeitraum(Rec20 record_kopf) throws myException {
		super();
		this.record_kopf = record_kopf;
		this._t("Fixierungs- zeitraum")._fsa(-2)._backDDark()._gld(new RB_gld()._al(E2_ALIGN.CENTER_MID));
		
		if(!(record_kopf==null) && record_kopf.is_yes_db_val(VKOPF_KON.ist_fixierung)){
		this._aaa(new ownActionAgent());
		}
		
	}
	
	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_P_M_BT_Set_Fixierungs_zeitraum othis = KFIX_P_M_BT_Set_Fixierungs_zeitraum.this;
			
			rbMASK = KFIX_P_M_BT_Set_Fixierungs_zeitraum.this.rb_ComponentMap_this_belongsTo();
			
			str_datum_von= record_kopf.get_fs_dbVal(VKOPF_KON.fix_von);
			str_datum_bis= record_kopf.get_fs_dbVal(VKOPF_KON.fix_bis);
			
			
			rbMASK._setValue(VPOS_KON_TRAKT.gueltig_von, othis.str_datum_von);
			rbMASK._setValue(VPOS_KON_TRAKT.gueltig_bis, othis.str_datum_bis);
		}
		
	}

}
