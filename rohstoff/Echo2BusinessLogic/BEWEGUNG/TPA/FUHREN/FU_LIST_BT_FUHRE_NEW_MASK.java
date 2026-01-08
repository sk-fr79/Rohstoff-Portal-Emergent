package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentNEW;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;

public class FU_LIST_BT_FUHRE_NEW_MASK extends MyE2_Button
{
	private MyE2_SelectField  				oSelectLKW = null;
	private E2_DateBrowser  				oDateBrowser = null;
	private E2_BasicModuleContainer_MASK 	omaskContainer = null;

	public FU_LIST_BT_FUHRE_NEW_MASK(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK maskContainer, MyE2_SelectField  SelectLKW, E2_DateBrowser  DateBrowser) throws myException
	{
		super(E2_ResourceIcon.get_RI("new.png") , true);
		
		this.oDateBrowser = DateBrowser;
		this.oSelectLKW = SelectLKW;
		this.omaskContainer = maskContainer;
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,omaskContainer,this));
		this.add_GlobalAUTHValidator_AUTO("NEUEINGABE_FUHRE");

		if (this.oDateBrowser != null && this.oSelectLKW != null)
		{
			this.add_GlobalValidator(new XX_ActionValidator()
			{

				@Override
				public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
				{
					FU_LIST_BT_FUHRE_NEW_MASK oThis = FU_LIST_BT_FUHRE_NEW_MASK.this;
					
					MyE2_MessageVector oMV = new MyE2_MessageVector();
					
					try
					{
						String cID_MASCHINE_LKW = new MyLong(oThis.oSelectLKW.get_ActualWert(),new Long(0), new Long(0)).get_cF_LongString();
						String cDatum			= oThis.oDateBrowser.DO_EvaluateActualTextAndGiveBackFormatedText("dd.MM.yyyy"); 
						
						if (cID_MASCHINE_LKW.equals("0") || cDatum == null)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte LKW und Datum des Fahrplans definieren !"));
						}
					}
					catch (myException ex)
					{
						oMV.add_MESSAGE(ex.get_ErrorMessage());
					}
					return oMV;
				}

				@Override
				protected MyE2_MessageVector isValid(String unformated)	throws myException
				{
					return new MyE2_MessageVector();
				}
				
			});
			
		}
		
		
	}
	
	private class ownActionAgent extends ButtonActionAgentNEW
	{
		public ownActionAgent(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer, MyE2_Button oownButton) throws myException
		{
			super(new MyE2_String("Neueingabe einer Transport-Position"), onavigationList, omaskContainer, oownButton, null, null);
//			this.get_oButtonMaskSave().get_vSQL_STACK_DAEMON().add(new FUHREN_SQL_DAEMON());

		}
		
		//ueberschreiber der methode und falls es aus fahrplan ist, die SQLFields praeparieren
		public boolean do_prepareMaskForActualID() 
		{
			FU_LIST_BT_FUHRE_NEW_MASK oThis = FU_LIST_BT_FUHRE_NEW_MASK.this;
			
			try
			{
				if (oThis.oDateBrowser==null || oThis.oSelectLKW==null)   //im normalen modus, d.h. beim Fahrtenpool oder der fuhrenzentrale
				{
					oThis.omaskContainer.get_vCombinedComponentMAPs().get(0).get_oSQLFieldMAP().get_("DAT_FAHRPLAN_FP").set_cDefaultValueFormated("");
					oThis.omaskContainer.get_vCombinedComponentMAPs().get(0).get_oSQLFieldMAP().get_("ID_MASCHINEN_LKW_FP").set_cDefaultValueFormated("");
				}
				else
				{
					String cID_MASCHINE_LKW = new MyLong(oThis.oSelectLKW.get_ActualWert(),new Long(0), new Long(0)).get_cF_LongString();
					String cDatum			= oThis.oDateBrowser.DO_EvaluateActualTextAndGiveBackFormatedText("dd.MM.yyyy"); 
					
					if (cID_MASCHINE_LKW.equals("0") || cDatum == null)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte LKW und Datum des Fahrplans definieren !"));
					}

					oThis.omaskContainer.get_vCombinedComponentMAPs().get(0).get_oSQLFieldMAP().get_("DAT_FAHRPLAN_FP").set_cDefaultValueFormated(cDatum);
					oThis.omaskContainer.get_vCombinedComponentMAPs().get(0).get_oSQLFieldMAP().get_("ID_MASCHINEN_LKW_FP").set_cDefaultValueFormated(cID_MASCHINE_LKW);
				}
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			return bibMSG.get_bIsOK();
		}
	}
	
}
