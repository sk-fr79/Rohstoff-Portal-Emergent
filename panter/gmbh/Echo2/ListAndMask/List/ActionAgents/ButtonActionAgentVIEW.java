package panter.gmbh.Echo2.ListAndMask.List.ActionAgents;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class ButtonActionAgentVIEW extends XX_ButtonActionAgent_FromListToMask
{
	
	private MyString				cMessageStartEdit = new MyE2_String("Anzeige Datensatz : ");
	private Vector<Component>		Zusatzkomponenten = new Vector<Component>();
	
	
	public ButtonActionAgentVIEW(	MyString 						actionName,
									E2_NavigationList 				onavigationList,
									E2_BasicModuleContainer_MASK 	omaskContainer,
									MyE2_Button						oownButton, 
									Vector<Component> VZusatzKomponenten)
	{
		super(actionName,onavigationList,omaskContainer,oownButton,true,true, false);
		if (VZusatzKomponenten != null)
		{
			this.Zusatzkomponenten.addAll(VZusatzKomponenten);
		}
		
	}

	

	
	

	
	public void do_innerAction() throws myException
	{
		E2_vCombinedComponentMAPs vComponentMAPs = this.get_oMaskContainer().get_vCombinedComponentMAPs();
		
		
		bibMSG.add_MESSAGE(this.get_oOwnButton().valid_IDValidation(bibALL.get_Vector(this.get_cActualID_Unformated())));
		if (bibMSG.get_bIsOK())
		{
			vComponentMAPs.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,this.get_cActualID_Unformated());
			bibMSG.add_MESSAGE(new MyE2_Info_Message(this.cMessageStartEdit.CTrans()+"  ID:"+this.get_cActualID_Unformated(),false),true);
		}
		else
		{
			/*
			 * beenden
			 */
			this.get_oMaskContainer().CLOSE_AND_DESTROY_POPUPWINDOW(false);
			vComponentMAPs.do_MapSettings_AFTER(E2_ComponentMAP.STATUS_VIEW);
		}
	}
	

	public boolean do_prepareMaskForActualID() throws myException
	{
		return true;
	}


	public void showPopupWindow() throws myException
	{
		this.get_oMaskContainer().CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Maskenansicht"));		
		this.get_oMaskContainer().get_oWindowPane().set_oTitle(new MyE2_String("Maskenansicht"));
	}

	public Component build_ComponentWithMaskButtons() throws myException
	{
		E2_ComponentGroupHorizontal oCompGroup= new E2_ComponentGroupHorizontal(E2_INSETS.I_0_2_10_2);
		oCompGroup.add(new maskButtonCancel());
		if (this.Zusatzkomponenten.size()>0)
		{
			for (int i=0;i<this.Zusatzkomponenten.size();i++)
			{
				oCompGroup.add(this.Zusatzkomponenten.get(i));
			}
			
		}
		
		return oCompGroup;
	}
	
	public void set_cMessageStartEdit(MyString messageStartEdit) 
	{
		cMessageStartEdit = messageStartEdit;
	}
	

	/*
	 * 2014-08-25: private geaendert in protected 
	 */
	private class maskButtonCancel extends MyE2_Button
	{
		
		public maskButtonCancel()
		{
			super(E2_ResourceIcon.get_RI("cancel.png"), true);
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ButtonActionAgentVIEW.this.get_oMaskContainer().CLOSE_AND_DESTROY_POPUPWINDOW(false);
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Aktion abgebrochen ..."));
				try
				{
					ButtonActionAgentVIEW.this.get_oMaskContainer().get_vCombinedComponentMAPs().do_MapSettings_AFTER(E2_ComponentMAP.STATUS_VIEW);
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT("maskButtonCancel:ownActionAgent:doAction:"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			}
		}
	}




	public Vector<Component> get_vZusatzkomponenten()
	{
		return Zusatzkomponenten;
	}
	
	
}
