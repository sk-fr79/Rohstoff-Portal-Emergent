package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class FUO_MASK_BUTTON_LoadAVV_CodeFromFuhre extends MyE2_Button
{

	private String 					EK_VK = null;
	private FU_DAUGHTER_ORTE  		FUO_DaughterComponent = null;

	
	public FUO_MASK_BUTTON_LoadAVV_CodeFromFuhre(String cEK_VK, FU_DAUGHTER_ORTE oFUO_DaugherComponent) throws myException
	{
		super(new MyE2_String("AVV aus Fuhre"));
		this.FUO_DaughterComponent=oFUO_DaugherComponent;
		this.EK_VK = cEK_VK;
		this.setToolTipText(new MyE2_String("Übernimmt den AVV-Code aus der Fuhre").CTrans());
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				new FUO__CopySortenCodesFromMainMap(FUO_MASK_BUTTON_LoadAVV_CodeFromFuhre.this.FUO_DaughterComponent.EXT().get_oComponentMAP(),
													FUO_MASK_BUTTON_LoadAVV_CodeFromFuhre.this.EXT().get_oComponentMAP());
				
			}
		});
		

	}


	public String get_EK_VK()
	{
		return EK_VK;
	}


	
	
}
