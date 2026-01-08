package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_AuthValidatorEditAdress;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.utils.MyFuhreNG;

public class FU_LIST_BT_ED_IN_LIST_ADRESS_SPEDITION extends MyE2_DB_Button 
{

	public FU_LIST_BT_ED_IN_LIST_ADRESS_SPEDITION(SQLField osqlField) throws myException 
	{
		super(osqlField);
		
		this.add_GlobalValidator(new FS_AuthValidatorEditAdress());
		this.add_oActionAgent(new ownActionAgent());
		this.set_cSetTextWhenEmpty(new MyE2_String("--"));

	}
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FU_LIST_BT_ED_IN_LIST_ADRESS_SPEDITION oButtCopy = null;
		
		try 
		{
			oButtCopy = new FU_LIST_BT_ED_IN_LIST_ADRESS_SPEDITION(this.EXT_DB().get_oSQLField());
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
			
			String cID_ADRESSE_SPED = null;
			
			//zuerst pruefen, ob eine Transportauftrag uebergeordnet ist
			MyFuhreNG oFuhre = new MyFuhreNG( oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue("ID_VPOS_TPA_FUHRE"));
			
			if (oFuhre.get_hmVKopfTPA()!=null)
			{
				cID_ADRESSE_SPED = oFuhre.get_hmVKopfTPA().get_UnFormatedValue("ID_ADRESSE");
			}
			else
			{
				// dann schauen, ob es eine freie fuhre ist, wenn ja koennte es eine ID_ADRESSE direkt geben
				if (!bibALL.isEmpty((oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue("ID_ADRESSE_SPEDITION"))))
				{
					cID_ADRESSE_SPED = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue("ID_ADRESSE_SPEDITION");
				}
			}
			
			if (bibALL.isLong(cID_ADRESSE_SPED))
			{

				FS_ModulContainer_MASK oMask = new FS_ModulContainer_MASK();
				
				if (bibMSG.get_bHasAlarms())
				{
					oMask.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW, cID_ADRESSE_SPED);
					oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
				}
				else
				{
					oMask.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT, cID_ADRESSE_SPED);
					oMask.get_oRowForButtons().add(new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null));
					oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
				}
				
				MyE2_String Titel = new MyE2_String("Adresse Spedition");
				oMask.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), Titel);
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Keine Speditionsadresse vorhanden !"));
			}
		}
	}
	
	
	
}
