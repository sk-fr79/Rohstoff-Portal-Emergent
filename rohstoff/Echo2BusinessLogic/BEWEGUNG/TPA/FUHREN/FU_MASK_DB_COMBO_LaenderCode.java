package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_DB_COMBO_LaenderCode extends MyE2_DB_ComboBoxErsatz
{

	public FU_MASK_DB_COMBO_LaenderCode(SQLField osqlField) throws myException
	{
		super(	osqlField, 
				"SELECT LAENDERCODE FROM "+bibE2.cTO()+".JD_LAND ORDER BY LAENDERCODE ", true);
		
		this.set_WidthAndHeightOfDropDown(new Extent(100),new Extent(200),null,new Integer(40));
	
		this.get_oPopUp().add_ActionAgentToPopupButtons(new ownActionAgent(), false);
		this.set_bTextFieldIsWriteable(false);
		this.get_oTextField().set_iWidthPixel(30);
		this.get_oTextField().setFont(new E2_FontPlain(-2));
		
	}


	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			new FU__MaskSettingsAfterInput__Plausibilitaet(FU_MASK_DB_COMBO_LaenderCode.this);
		}
	}
	
}
