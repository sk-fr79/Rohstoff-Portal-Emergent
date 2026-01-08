package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_DateBrowser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;

public class FPT_BUTTON_OPENFAHRPLAN extends MyE2_Button
{
	private FU_LIST_ModulContainer  ModulContainer = null;
	private MyE2_SelectField  		oSelectLKW = null;
	private E2_DateBrowser  		oDateBrowser = null;
	private FPT_SELECTFIELD_SET_ANHAENGER 	oDDFieldToSetAnhenger = null;
	private FPT_SELECTFIELD_SET_FAHRER    	oDDFieldToSetFahrer = null;
	
	public FPT_BUTTON_OPENFAHRPLAN(	FU_LIST_ModulContainer 			Modulcontainer, 
									MyE2_SelectField  				SelectLKW, 
									E2_DateBrowser  				DateBrowser,
									FPT_SELECTFIELD_SET_ANHAENGER 	DDFieldToSetAnhenger,
									FPT_SELECTFIELD_SET_FAHRER    	DDFieldToSetFahrer)
	{
		super(new MyE2_String("FP öffnen"));
		this.ModulContainer = Modulcontainer;
		this.oDateBrowser = DateBrowser;
		this.oSelectLKW = SelectLKW;
		
		this.oDDFieldToSetAnhenger = DDFieldToSetAnhenger;
		this.oDDFieldToSetFahrer = DDFieldToSetFahrer;
		
		this.add_oActionAgent(new actionAgentOpenFahrplan());
		this.setToolTipText(new MyE2_String("Fahrplan für den eingestellten Tag/LKW öffnen oder neu aufbauen ...").CTrans());
		
	}

	
	/*
	 * action nach oeffnen des fahrplans
	 */
	private class actionAgentOpenFahrplan extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FPT_BUTTON_OPENFAHRPLAN oThis = FPT_BUTTON_OPENFAHRPLAN.this;
			
			SQLFieldMAP  oSQLFieldMap = oThis.ModulContainer.get_oNavList().get_oComponentMAP__REF().get_oSQLFieldMAP();
			
			String cID_MASCHINE_LKW = new MyLong(oThis.oSelectLKW.get_ActualWert(),new Long(0), new Long(0)).get_cUF_LongString();
			String cDatum			= oThis.oDateBrowser.DO_EvaluateActualTextAndGiveBackFormatedText("dd.MM.yyyy"); 
			
			if (cID_MASCHINE_LKW.equals("0") || cDatum == null)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte LKW und Datum des Fahrplans definieren !"));
			}
			else
			{
				FP__ALL.set_BedingungenForFahrplanSQLFieldMAP(oSQLFieldMap, cID_MASCHINE_LKW, cDatum);
				oThis.ModulContainer.get_oNavList()._REBUILD_COMPLETE_LIST("");
				
				oThis.oDDFieldToSetAnhenger.setSelectedIndex(0);
				oThis.oDDFieldToSetFahrer.setSelectedIndex(0);
				
			}	
		}
		
	}

	
}
