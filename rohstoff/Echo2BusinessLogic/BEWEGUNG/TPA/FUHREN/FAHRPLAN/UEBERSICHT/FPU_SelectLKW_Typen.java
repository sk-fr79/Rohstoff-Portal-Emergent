package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class FPU_SelectLKW_Typen extends MyE2_SelectField 
{

	private FPU_BasicModuleContainer oModuleContainer = null; 
	
	public FPU_SelectLKW_Typen(FPU_BasicModuleContainer moduleContainer)  throws myException
	{
		super();
		this.oModuleContainer = moduleContainer;
		
		String cQueryLKWTypen = "SELECT MASCHINENTYP,ID_MASCHINENTYP FROM "+
										bibE2.cTO()+".JT_MASCHINENTYP WHERE " +
										" JT_MASCHINENTYP.IST_LKW='Y' ORDER BY MASCHINENTYP";
		
		String[][] cQuery = bibDB.EinzelAbfrageInArray(cQueryLKWTypen,"");
		
		String[][] cVarianten = null; 
		
		if (cQuery == null)
		{
			throw new myException("FPU_SelectLKW_Typen:Constuctor: Error building Type-List");
		}
		else
		{
			cVarianten = new String[cQuery.length+1][2];
			cVarianten[0][0]="-"; 
			cVarianten[0][1]="";
			
			for (int i=0;i<cQuery.length;i++)
			{
				cVarianten[i+1][0]=cQuery[i][0];cVarianten[i+1][1]=cQuery[i][1];
			}
		}
		this.set_ListenInhalt(cVarianten,false);
		this.add_oActionAgent(new ownActionAgent());
		
	}


	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			FPU_SelectLKW_Typen oThis = FPU_SelectLKW_Typen.this;
			MyE2_SelectField oSel = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			try
			{
				String cID_MASCHINENTYP = bibALL.ReplaceTeilString(oSel.get_ActualWert(),".","");
				Vector<LKW_CheckBox> vCheckBoxen = oThis.oModuleContainer.get_vLKW_CheckBox();
				for (int i=0;i<vCheckBoxen.size();i++)
				{
					LKW_CheckBox oCB = vCheckBoxen.get(i);
					if (cID_MASCHINENTYP.equals(""))    // dann werden alle angekreuzt
					{
						oCB.setSelected(true);
						continue;
					}
					
					if (oCB.get_recLKW().get_ID_MASCHINENTYP_cUF_NN("").equals(cID_MASCHINENTYP))   // das merkmal 3 der checkboxen enthaelt die ID_MASCHINENTYP
						oCB.setSelected(true);
					else
						oCB.setSelected(false);
				}

				// jetzt den knopf fuer den neuaufbau druecken
				oThis.oModuleContainer.get_oButtonRefreshLists().doActionPassiv();
				
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}
	

}
