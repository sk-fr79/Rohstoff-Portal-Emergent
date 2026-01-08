package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQL_Insert_AGENT;


public class FP_SQL_Insert_Agent extends SQL_Insert_AGENT
{

	public Vector<String> get_vZusatzStatements(SQLFieldMAP hmActualMAP,SQLMaskInputMAP oInputMAP, String cNewPrimaryKEY)
	{
		Vector<String> vRueck = new Vector<String>();
		
		vRueck.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET FAHRPLANGRUPPE_FP=SEQ_"+bibALL.get_ID_MANDANT()+"_FAHRPLANGRUPPE.NEXTVAL WHERE NVL(FUHRE_AUS_FAHRPLAN,'N')='Y' AND FAHRPLANGRUPPE_FP IS NULL AND ID_VPOS_TPA_FUHRE="+cNewPrimaryKEY);
		vRueck.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET EAN_CODE_FP='FU-'||TO_CHAR(ID_VPOS_TPA_FUHRE) WHERE ID_VPOS_TPA_FUHRE="+cNewPrimaryKEY);
		vRueck.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET DAT_VORGEMERKT_ENDE_FP=DAT_VORGEMERKT_FP WHERE  NVL(FUHRE_AUS_FAHRPLAN,'N')='Y' AND  DAT_VORGEMERKT_ENDE_FP IS NULL AND ID_VPOS_TPA_FUHRE="+cNewPrimaryKEY);
		
		return vRueck;
	}

}
