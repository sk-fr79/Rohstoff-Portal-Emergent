package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_STEUERVERMERK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_STEUERVERMERK_HASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_baueListeSteuervermerke;

public class BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK extends MyE2_SelectField
{
	private MyE2_DB_TextArea  oDBFieldSteuervermerk = null;
	private MyE2IF__DB_Component oFieldSteuer = null;
	
    private String[][] 				Auswahlliste = 		null;
    private BS_STEUERVERMERK_HASH  	hmSteuerVermerk = 	null;
    
//							{"-",""},
//							{"Innergemeinschaftliche Lieferung",bibALL.get_RECORD_MANDANT().get_EU_STEUER_VERMERK_cF_NN("")},
//							{"Steuerfreie Aussenlieferung",bibALL.get_RECORD_MANDANT().get_AUSSEN_STEUER_VERMERK_cUF_NN("")},
//							{"-",""}};                   //leeres am schluss, damit die Maske auf einen Klick immer reagiert (bei jedem maskenaufbau wird die komponente in diese position gesetzt


	public BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK(	MyE2_DB_TextArea fieldSteuervermerk, MyE2IF__DB_Component FieldSteuer) throws myException
	{
		super();
		oDBFieldSteuervermerk = fieldSteuervermerk;
		oFieldSteuer = FieldSteuer;
		
		//in der RECH/GUT-Maske werden alle Steuervermerke (auch EK / VK vorgeschlagen)
		BS_baueListeSteuervermerke oSteuerliste = new BS_baueListeSteuervermerke(false, false,"ALLE");
		this.Auswahlliste = 	oSteuerliste.get_cSteuerVermerke();
		this.hmSteuerVermerk = 	oSteuerliste.get_hmSteuervermerke();
		
		
		this.set_ListenInhalt(this.Auswahlliste,true);
		this.set_ActiveValue_OR_FirstValue("");
		
		this.setWidth(new Extent(50));
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK oThis = BSRG_P_MASK_SELECT_FIELD_STEUERVERMERK.this;
			oThis.oDBFieldSteuervermerk.setText(oThis.get_ActualWert());
			
			int iIndex = oThis.getSelectedIndex();
			String cHashKey = oThis.Auswahlliste[iIndex][0];
			
			BS_STEUERVERMERK  bsVermerk = hmSteuerVermerk.get(cHashKey);
			
			if (bsVermerk.getcSteuersatzFormated()!=null && oThis.oFieldSteuer!=null)
			{
				oThis.oFieldSteuer.set_cActualMaskValue(bsVermerk.getcSteuersatzFormated());
			}
		}
		
	}
	
	
	
	public void set_Neutral()
	{
		this.setSelectedIndex(this.Auswahlliste.length-1);
	}



	public String[][] get_Auswahlliste() {
		return Auswahlliste;
	}



	public BS_STEUERVERMERK_HASH get_hmSteuerVermerk() {
		return hmSteuerVermerk;
	}

	
	
}
