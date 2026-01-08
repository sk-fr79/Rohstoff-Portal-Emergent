package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.indep.exceptions.myException;

public class FP_ButtonUmbuchen_in_LISTE extends MyE2_ButtonInLIST
{
	private MyE2_Column ColContainer = null;             //ist das eigentliche element der E2_ComponentMAP und traegt damit auch die infos
	
	public FP_ButtonUmbuchen_in_LISTE(E2_ResourceIcon oIcon, MyE2_Column oColContainer, E2_NavigationList oNaviList) throws myException
	{
		super(oIcon, true);
		this.ColContainer = oColContainer; 
		this.add_oActionAgent(new ownActionAgent());             //zuerst die markierungen setzen
		this.add_oActionAgent(new FP_ActionAgent_UMBUCHEN_STD(oNaviList, null, null));   //dann das umbuchungspopup starten
		
		this.add_IDValidator(new FP_Validator_FAHRT_GELOESCHT_ODER_STORNIERT());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("FAHRT_UMBUCHEN"));
		
	}

	
	/*
	 * unmark alles und eigenes markieren
	 */
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FP_ButtonUmbuchen_in_LISTE oThis = FP_ButtonUmbuchen_in_LISTE.this;
			
			E2_ComponentMAP oMap = oThis.ColContainer.EXT().get_oComponentMAP();
			Vector<E2_ComponentMAP> vVectorComponentMapThisBelongsTo = null;
			
			//zuerst nachsehen, ob die komponente in einer componentMap ist
			if 	(oMap != null)
			{
				vVectorComponentMapThisBelongsTo = oMap.get_VectorComponentMAP_thisBelongsTo();
				
				//dann nachsehen, ob sich das ganze in einer NaviList abspielt
				if (vVectorComponentMapThisBelongsTo!=null)
				{
					//dann nachsehen, ob die eingen ComponentMAP eine CheckBox hat
					for (Map.Entry<String,MyE2IF__Component> oEntry: oMap.entrySet())
					{
						if (oEntry.getValue() instanceof E2_CheckBoxForList)
						{
							E2_CheckBoxForList oCB_For_List = (E2_CheckBoxForList)oEntry.getValue();
							
							if (oCB_For_List.isEnabled())
							{
								for (E2_ComponentMAP oMapSchleife: vVectorComponentMapThisBelongsTo)
								{
									oMapSchleife.setChecked_CheckBoxForList(false);   //alle aus 
								}
								oCB_For_List.setSelected(true);   //ausser ich
							}
						}
					}
				}
			}
		}
		
	}
}
	
