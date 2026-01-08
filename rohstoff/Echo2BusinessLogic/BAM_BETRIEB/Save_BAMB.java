/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;

public class Save_BAMB
{
	private 	BAMB_MASK_ModulContainer 	oMaskContainer = null;
	private 	boolean						bIsOK = false;
	private 	String 						cActualMaskID_Unformated = null;
	
	private E2_NavigationList			oNaviList = null;

	
	public Save_BAMB(BAMB_MASK_ModulContainer container, E2_NavigationList oNavList) throws myException
	{
		super();
		oMaskContainer = container;
		this.oNaviList = oNavList;

		E2_vCombinedComponentMAPs 	vCombined_E2_ComponentMaps = 	oMaskContainer.get_vCombinedComponentMAPs();
		E2_ComponentMAP oMaskMAP = vCombined_E2_ComponentMaps.get_oE2_ComponentMAP_MAIN();
		
		//status der maske feststellen
		String cActualMaskStatus = E2_ComponentMAP.STATUS_NEW_EMPTY;
		if (oMaskMAP.get_oInternalSQLResultMAP()!=null)
			cActualMaskStatus = E2_ComponentMAP.STATUS_EDIT;
		
		
		if (cActualMaskStatus.equals( E2_ComponentMAP.STATUS_NEW_EMPTY))
		{
			String cNewID = vCombined_E2_ComponentMaps.SaveALL_E2_ComponentMAPS_AFTER_NEW(E2_ComponentMAP.STATUS_NEW_EMPTY);
			if (cNewID == null)
			{
				if (bibMSG.get_bIsOK())
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !")));
			}
			else
			{
				vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_NEW_EMPTY);
				vCombined_E2_ComponentMaps.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_NEW_EMPTY);
				this.bIsOK = true;
				this.cActualMaskID_Unformated = cNewID;
				this.oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(cNewID);
			}
		}
		else
		{
			if (!vCombined_E2_ComponentMaps.SaveALL_E2_ComponentMAPS_AFTER_EDIT(E2_ComponentMAP.STATUS_EDIT))
			{
				if (bibMSG.get_bIsOK())
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Speichern !")));
			}
			else
			{
				vCombined_E2_ComponentMaps.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_EDIT);
				vCombined_E2_ComponentMaps.Requery_All_ActualResultMAPs(E2_ComponentMAP.STATUS_VIEW);
				vCombined_E2_ComponentMaps.set_AllComponentsEnabled_For_Edit(false,E2_ComponentMAP.STATUS_VIEW);
				this.cActualMaskID_Unformated=oMaskMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				this.bIsOK = true;
			}
		}
	}
	
	
	public boolean 	get_bISOK()						{			return this.bIsOK;						}
	public String 	get_cActualMaskID_Unformated()	{			return cActualMaskID_Unformated;		}

}