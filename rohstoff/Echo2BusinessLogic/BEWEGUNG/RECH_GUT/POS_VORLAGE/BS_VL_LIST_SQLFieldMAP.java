package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class BS_VL_LIST_SQLFieldMAP extends Project_SQLFieldMAP
{

	/*
	 * im standalone-betrieb (cID_VPOS_TPA_FUHRE=null) werden alle, die noch keinen kopf-satz habe angezeigt,
	 * im nicht-standalone-betrieb tauchen nur positionen auf, die zu einer fuhre gehoeren
	 */
	public BS_VL_LIST_SQLFieldMAP() throws myException
	{
		super("JT_VPOS_RG_VL", "ID_VPOS_TPA_FUHRE_ZUGEORD", false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_RG_VL.ANR1,'--')||'-'||  NVL(JT_VPOS_RG_VL.ANR2,'--')","ANR1_ANR2",new MyE2_String("ANR1-2"),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_RG_VL.EINHEIT_PREIS_KURZ,'--')||' ('||TO_CHAR(  NVL(JT_VPOS_RG_VL.MENGENDIVISOR,0))||')'","EINH_PREIS",new MyE2_String("Pr.Einh."),bibE2.get_CurrSession()), false);
		this.initFields();
	}
}
