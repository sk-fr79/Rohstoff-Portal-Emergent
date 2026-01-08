package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FU__MASK_CB_NormaleAbrechnungsMenge extends MyE2_DB_CheckBox
{
	private String cFIELDNAME_GEGENMENGE = null; 
	private String cFIELDNAME_NORMALMENGE = null;
	

	public FU__MASK_CB_NormaleAbrechnungsMenge(SQLField osqlField,String FIELDNAME_NORMALMENGE,String FIELDNAME_GEGENMENGE, boolean EK) 	throws myException
	{
		super(osqlField);
		
		this.cFIELDNAME_GEGENMENGE = FIELDNAME_GEGENMENGE;
		this.cFIELDNAME_NORMALMENGE = FIELDNAME_NORMALMENGE;
		
		this.setToolTipText(new MyE2_String(EK?
									"Anteilige Lademenge für die Gutschrift verwenden":
									"Anteilige Ablademenge für die Rechnung verwenden").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU__MASK_CB_NormaleAbrechnungsMenge oThis = FU__MASK_CB_NormaleAbrechnungsMenge.this;
			
			//die normale menge formal enablen, damit der Style neu gezogen wird
			oThis.EXT().get_oComponentMAP().get__Comp(oThis.cFIELDNAME_NORMALMENGE).set_bEnabled_For_Edit(true);   //die normale ist immer enabled, wenn diese aktion startet
			
			
			oThis.EXT().get_oComponentMAP().get__Comp(oThis.cFIELDNAME_GEGENMENGE).EXT().set_bDisabledFromInteractive(oThis.isSelected());
			oThis.EXT().get_oComponentMAP().get__Comp(oThis.cFIELDNAME_GEGENMENGE).set_bEnabled_For_Edit(!oThis.isSelected());
			
			if (oThis.isSelected())  //dann die gegenmenge loeschen
			{
				oThis.EXT().get_oComponentMAP().get__DBComp(oThis.cFIELDNAME_GEGENMENGE).prepare_ContentForNew(false);
			}
		}
	}
	
}
