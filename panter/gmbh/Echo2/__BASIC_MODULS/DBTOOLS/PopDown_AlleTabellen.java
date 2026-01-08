package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class PopDown_AlleTabellen extends MyE2_PopUpMenue
{

	//allgemeiner popup-button fuer einzelaktionen (z.B. Sequence erstellen, View erstellen, Trigger erstellen)
	
	
	public PopDown_AlleTabellen(XX_ActionAgent oAgent) throws myException 
	{
		super(E2_ResourceIcon.get_RI("popup.png"), E2_ResourceIcon.get_RI("popup__.png"),true, new Extent(300),new Extent(500),-6,450);
		
		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(),true),false);
		
		for (int i=0;i<cTabellen.length;i++)
		{
			MyE2_Button  oButton = new MyE2_Button(cTabellen[i][0]);
			oButton.EXT().set_C_MERKMAL(cTabellen[i][0]);
			oButton.setFont(new E2_FontPlain());
			oButton.add_oActionAgent(oAgent);
			this.addButton(oButton, true);
			
		}
	}

}
