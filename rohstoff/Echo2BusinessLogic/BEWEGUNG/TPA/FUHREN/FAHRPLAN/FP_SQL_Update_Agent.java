package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.SQL_Update_AGENT;




/*
 * update-agent, sorgt dafuer, dass es immer ein DAT_VORGEMERKT_ENDE gibt, das gleich ist wie das vormerkdatum von, wenn es leer ist
 */
public class FP_SQL_Update_Agent extends SQL_Update_AGENT
{
	public Vector<String> get_vZusatzStatements(SQLFieldMAP hmActualMAP, SQLResultMAP oActualResult, SQLMaskInputMAP oMaskInputValues)
	{
		Vector<String> vRueck = new Vector<String>();
		String cNewPrimaryKEY = oActualResult.get_cUNFormatedROW_ID();
		
		vRueck.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET FAHRPLANGRUPPE_FP=SEQ_"+bibALL.get_ID_MANDANT()+"_FAHRPLANGRUPPE.NEXTVAL WHERE NVL(FUHRE_AUS_FAHRPLAN,'N')='Y' AND FAHRPLANGRUPPE_FP IS NULL AND ID_VPOS_TPA_FUHRE="+cNewPrimaryKEY);
		vRueck.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET EAN_CODE_FP='FU-'||TO_CHAR(ID_VPOS_TPA_FUHRE) WHERE ID_VPOS_TPA_FUHRE="+cNewPrimaryKEY);
		vRueck.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION+"UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET DAT_VORGEMERKT_ENDE_FP=DAT_VORGEMERKT_FP WHERE  NVL(FUHRE_AUS_FAHRPLAN,'N')='Y' AND  DAT_VORGEMERKT_ENDE_FP IS NULL AND ID_VPOS_TPA_FUHRE="+cNewPrimaryKEY);
		
		return vRueck;
	}
}
