package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class FUO_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	public FUO_MASK_SQLFieldMAP(String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException 
	{
		super("JT_VPOS_TPA_FUHRE_ORT", "", false);

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_TPA_FUHRE_ORT","DEF_QUELLE_ZIEL","DEF_QUELLE_ZIEL",new MyE2_String("Quelle-Ziel"),bibALL.MakeSql(cEK_VK),bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_TPA_FUHRE_ORT","ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE",new MyE2_String("ID-Fuhre"),"0",bibE2.get_CurrSession()), true);

		this.get_("DELETED").set_cDefaultValueFormated("N");

		this.get_("ABZUG_MENGE").set_cDefaultValueFormated("0");
		
//		this.get_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__INTRASTAT_MELD).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_(RECORD_VPOS_TPA_FUHRE_ORT.FIELD__INTRASTAT_MELD).set_cDefaultValueFormated(TAX_CONST.INTRASTAT_MELDEN_UDEF);

		
		
		this.initFields();
	}
	
	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}


}
