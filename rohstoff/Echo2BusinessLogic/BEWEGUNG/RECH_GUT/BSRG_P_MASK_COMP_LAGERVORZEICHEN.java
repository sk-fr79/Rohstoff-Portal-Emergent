package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyAdressLIGHT;

public class BSRG_P_MASK_COMP_LAGERVORZEICHEN extends MyE2_DB_SelectField
{
	private static String[][] DropDown = {{"-",""},{"Gutschrift","1"},{"Rechnung","-1"}};
	
	public BSRG_P_MASK_COMP_LAGERVORZEICHEN(SQLFieldMAP osqlFieldGroup, boolean bWithActionAgent) throws myException
	{
		super(osqlFieldGroup.get_("LAGER_VORZEICHEN"), DropDown, true);
		this.EXT().set_MaskEnabled_Combination(true,true,false,false,false);
		
		if (bWithActionAgent)
		{
			this.add_oActionAgent(new ownActionAgent());
		}
		
	}

	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_ComponentMAP oMAP = BSRG_P_MASK_COMP_LAGERVORZEICHEN.this.EXT().get_oComponentMAP();
			
			//nachsehen, ob es eine Adress-ID gibt, wenn ja, diese auf den OHNE_STEUER_ZUSTAND pruefen
			
			
			
			String cID_ADRESSE = bibALL.null2leer(((MyE2IF__DB_Component)oMAP.get__Comp("ID_ADRESSE")).get_cActualMaskValue());
			cID_ADRESSE = bibALL.ReplaceTeilString(cID_ADRESSE, ".", "");
			
			
			if (bibALL.isLong(cID_ADRESSE))
			{
				MyAdressLIGHT oAdress = new MyAdressLIGHT(cID_ADRESSE);
				
				if (BSRG_P_MASK_COMP_LAGERVORZEICHEN.this.get_ActualWert().equals("1"))
				{
					((MyE2_DB_CheckBox)oMAP.get__Comp("OHNE_STEUER")).set_cActualMaskValue(oAdress.get_b_OHNE_SteuerBeiWarenEingang()?"Y":"N");
				}
				if (BSRG_P_MASK_COMP_LAGERVORZEICHEN.this.get_ActualWert().equals("-1"))
				{
					((MyE2_DB_CheckBox)oMAP.get__Comp("OHNE_STEUER")).set_cActualMaskValue(oAdress.get_b_OHNE_SteuerBeiWarenAusgang()?"Y":"N");
				}
			}
		}
	}
}
