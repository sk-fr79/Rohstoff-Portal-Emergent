package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_BasicModuleContainer;

public class FU__Mask_ZEIGE_GEFAHRGUT_Label
{

	
	public FU__Mask_ZEIGE_GEFAHRGUT_Label(MyE2IF__Component oComponentFomMask) throws myException
	{
		super();
		
		//zuerst die Maske beschaffen (Fuhre oder Fuhrenort)
		E2_BasicModuleContainer_MASK  oModuleContainerMask = oComponentFomMask.EXT().get_ModuleContainer_MASK_this_BelongsTo();
		
		Vector<MyE2IF__Component>  vWarnanzweige = oModuleContainerMask.get_vComponents(FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE);
		
		//muss was finden
		if (vWarnanzweige.size()!=1)
		{
			throw new myException(this,"Error finding GEFAHRGUTANZEIGE");
		}
		
		String cTablename = "JT_VPOS_TPA_FUHRE";
		if (oModuleContainerMask instanceof FUO_MASK_BasicModuleContainer)
		{
			cTablename = "JT_VPOS_TPA_FUHRE_ORT";
		}
		
		((Component)vWarnanzweige.get(0)).setVisible(false);
		
		Long lID_ARTIKEL = oModuleContainerMask.get_DBComponent(cTablename+".ID_ARTIKEL").EXT_DB().get_LActualDBValue(true,true,new Long(-1));
		if (lID_ARTIKEL.longValue() != -1)
		{
			RECORD_ARTIKEL recArtikel = new RECORD_ARTIKEL(lID_ARTIKEL.longValue());
			if (recArtikel.is_GEFAHRGUT_YES())
			{
				((Component)vWarnanzweige.get(0)).setVisible(true);
			}
		}
	}
}
