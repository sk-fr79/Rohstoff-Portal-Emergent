package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FU__MASK_CB_ManuellPreis extends MyE2_DB_CheckBox
{

	private String cFieldNameOf_EINZELPREIS = "";
	private String cFieldNameOf_ID_VPOS_STD = "";
	
	
	/**
	 * 
	 * @param osqlField
	 * @param fieldNameOf_EINZELPREIS
	 * @param fieldNameOf_ID_VPOS_STD
	 * @param cAUTH_VALID_KEY
	 * @throws myException
	 */
	public FU__MASK_CB_ManuellPreis(	SQLField 	osqlField,	
										String 		fieldNameOf_EINZELPREIS, 
										String 		fieldNameOf_ID_VPOS_STD,	
										String 		cAUTH_VALID_KEY) throws myException
	{
		super(osqlField,new MyE2_String("manuell"),null);
						cFieldNameOf_EINZELPREIS = fieldNameOf_EINZELPREIS;
						cFieldNameOf_ID_VPOS_STD = fieldNameOf_ID_VPOS_STD;
						this.add_GlobalAUTHValidator_AUTO(cAUTH_VALID_KEY);
						this.add_oActionAgent(new ownActionAgent());
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU__MASK_CB_ManuellPreis oThis = FU__MASK_CB_ManuellPreis.this;
			if (oThis.isSelected())
			{
				oThis.EXT().get_oComponentMAP().get__DBComp(cFieldNameOf_ID_VPOS_STD).prepare_ContentForNew(false);   //suchfeld leermachen
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Preisfindung erfolgt nur noch manuell (ein evtl. Kontrakt bleibt unberücksichtigt !"));
			}
			else
			{
				oThis.EXT().get_oComponentMAP().get__DBComp(cFieldNameOf_EINZELPREIS).prepare_ContentForNew(false);   //suchfeld leermachen
			}
		}
	}
	
}
