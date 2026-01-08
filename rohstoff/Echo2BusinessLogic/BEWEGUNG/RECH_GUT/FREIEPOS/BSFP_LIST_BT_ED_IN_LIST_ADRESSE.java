package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_AuthValidatorEditAdress;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class BSFP_LIST_BT_ED_IN_LIST_ADRESSE extends MyE2_DB_Button 
{

	public BSFP_LIST_BT_ED_IN_LIST_ADRESSE(SQLField osqlField) throws myException 
	{
		super(osqlField);
		
		this.add_GlobalValidator(new FS_AuthValidatorEditAdress());
		this.add_oActionAgent(new ownActionAgent());
	}
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		BSFP_LIST_BT_ED_IN_LIST_ADRESSE oButtCopy = null;
		
		try 
		{
			oButtCopy = new BSFP_LIST_BT_ED_IN_LIST_ADRESSE(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		return oButtCopy;
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_DB_Button oThis = (MyE2_DB_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			String cID_ADRESSE = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue("ID_ADRESSE");
			
			if (bibALL.isLong(cID_ADRESSE))
			{

				FS_ModulContainer_MASK oMask = new FS_ModulContainer_MASK();
				
				if (bibMSG.get_bHasAlarms())
				{
					oMask.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW, cID_ADRESSE);
					oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
				}
				else
				{
					oMask.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT, cID_ADRESSE);
					oMask.get_oRowForButtons().add(new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null));
					oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
				}
				
				MyE2_String Titel = new MyE2_String("Adresse ");
				oMask.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), Titel);
			}
		}
	}
	
	
	
}
