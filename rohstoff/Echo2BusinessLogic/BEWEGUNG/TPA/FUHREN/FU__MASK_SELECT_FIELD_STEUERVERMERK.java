package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_STEUERVERMERK_HASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_baueListeSteuervermerke;

public class FU__MASK_SELECT_FIELD_STEUERVERMERK extends MyE2_SelectField
{
	private MyE2_DB_TextArea  		oDBFieldSteuervermerk = null;
	private MyE2IF__DB_Component 	oFieldSteuer = null;

	
	
	
    private String[][] 				Auswahlliste = null;
    
    public String[][] get_Auswahlliste()
	{
		return Auswahlliste;
	}


	private BS_STEUERVERMERK_HASH  hmVermerke = null;

	public BS_STEUERVERMERK_HASH get_hmVermerke()
	{
		return hmVermerke;
	}
    
//									{"-",""},
//									{"Innergemeinschaftliche Lieferung",bibALL.get_RECORD_MANDANT().get_EU_STEUER_VERMERK_cF_NN("")},
//									{"Steuerfreie Aussenlieferung",bibALL.get_RECORD_MANDANT().get_AUSSEN_STEUER_VERMERK_cUF_NN("")},
//									{"Kein Vermerk in RE/GUT",FU___CONST.EU_STEUERVERMERK_LEER},
//									{"Lager",FU___CONST.EU_STEUERVERMERK_LAGER},
//									{"-",""}};                   //leeres am schluss, damit die Maske auf einen Klick immer reagiert (bei jedem maskenaufbau wird die komponente in diese position gesetzt
	


	public FU__MASK_SELECT_FIELD_STEUERVERMERK(	MyE2_DB_TextArea fieldSteuervermerk, String cEK_VK, MyE2IF__DB_Component FieldSteuer)throws myException
	{
		super();
		oDBFieldSteuervermerk = fieldSteuervermerk;
		oFieldSteuer = FieldSteuer;

		//2013-01-04: umstellung auf die verbesserte tooltip-technik
		this.set_bSetToolTipsToActiveListValue(true);
		
		
		if (cEK_VK.equals("EK"))
		{
			BS_baueListeSteuervermerke oBSVM = new BS_baueListeSteuervermerke(true, true,"EK");
			this.Auswahlliste = oBSVM.get_cSteuerVermerke();
			this.hmVermerke = oBSVM.get_hmSteuervermerke();
		}
		else if  (cEK_VK.equals("VK"))
		{
			BS_baueListeSteuervermerke oBSVM = new BS_baueListeSteuervermerke(true, true,"VK");
			this.Auswahlliste = oBSVM.get_cSteuerVermerke();
			this.hmVermerke = oBSVM.get_hmSteuervermerke();
		}
		else
		{
			throw new myException("Error false fieldlabel !!!");
		}
		
		this.set_ListenInhalt(this.Auswahlliste,true);
		this.set_ActiveValue_OR_FirstValue("");
		
		this.setWidth(new Extent(70));
		
		//this.add_oActionAgent(new ownActionAgent());
	}

	
//	private class ownActionAgent extends XX_ActionAgent
//	{
//
//		@Override
//		public void executeAgentCode(ExecINFO execInfo) throws myException
//		{
//			FU__MASK_SELECT_FIELD_STEUERVERMERK oThis = FU__MASK_SELECT_FIELD_STEUERVERMERK.this;
//			oThis.oDBFieldSteuervermerk.setText(oThis.get_ActualWert());
//			
//			int iIndex = oThis.getSelectedIndex();
//			String cHashKey = oThis.Auswahlliste[iIndex][0];
//			
//			BS_STEUERVERMERK  bsVermerk = hmVermerke.get(cHashKey);
//			
//			if (bsVermerk.getcSteuersatzFormated()!=null && oThis.oFieldSteuer!=null)
//			{
//				oThis.oFieldSteuer.set_cActualMaskValue(S.NN(bsVermerk.getcSteuersatzFormated()));
//			}
//		}
//	}
	
	
	public void set_Neutral()
	{
		this.setSelectedIndex(this.Auswahlliste.length-1);
	}
	
}
