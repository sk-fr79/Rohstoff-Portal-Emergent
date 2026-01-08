package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.Container.E2_ComponentDisabler;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorter;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL_ButtonEditList extends MyE2_Button 
{

	private BSAAL__ModulContainerLIST	oModulContainerList = null;
	
	
	
	public BSAAL_ButtonEditList(BSAAL__ModulContainerLIST	oModulContainer) 
	{
		super(E2_ResourceIcon.get_RI("edit.png"), true);
		
		this.oModulContainerList = oModulContainer;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Eingeben der neuen Preise für die Abnahme-Angebote ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_EDIT_PRICELIST));

		this.add_IDValidator(new BSAAL_Validator_Kopf_IS_NOT_CLOSED());
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));
		
		//2015-12-15: die infobutton-jump-funktion ausschalten
		this.add_oActionAgent(new BSAAL_ActionSetInfoButtonsInListe_Status(this.oModulContainerList.get_oNaviList(), false));
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)  throws myException
		{
			BSAAL_ButtonEditList oThis = BSAAL_ButtonEditList.this;
			

			
			// welche IDs 
			Vector<E2_ComponentMAP> vComponentMaps = oThis.oModulContainerList.get_oNaviList().get_vComponentMAPS();
			
			int iZaehlerOK = 0;
			int iZaehlerVerboten = 0;
			int iZaehlerExceptions = 0;
			
			for (int i=0;i<vComponentMaps.size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)vComponentMaps.get(i);
				if (((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).valid_IDValidation(
						bibALL.get_Vector(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID())
						).size()==0)
				{
					try
					{
						oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_EDIT,true,new Boolean(true));
						iZaehlerOK ++;
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
						iZaehlerExceptions ++;
					}
				}
				else
				{
					iZaehlerVerboten ++;
				}
			}
			if (iZaehlerOK==vComponentMaps.size())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Preise können bearbeitet werden ."));
			}
			else
			{
				MyE2_String oMess = new MyE2_String("Erlaubt zum Bearbeiten: ");
				oMess.addUnTranslated(" "+iZaehlerOK+"   -->");
				oMess.addString(new MyE2_String("Bereits abgeschlossen: "));
				oMess.addUnTranslated(" "+iZaehlerVerboten+"   -->");
				oMess.addString(new MyE2_String("Sonstige Fehler: "));
				oMess.addUnTranslated(" "+iZaehlerExceptions);
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String(oMess.CTrans(),false)));
				
			}
			
			//zuerst die Save und cancel-buttons raussuchen 
			E2_RecursiveSearch_Component oSearchSaveAndCancel = new E2_RecursiveSearch_Component(oThis.oModulContainerList,
																					bibALL.get_Vector(BSAAL_ButtonSaveEdit.class.getName(), 
																									  BSAAL_ButtonCancelEdit.class.getName()),
																					null);
			
			//dann die buttons mit der gueltigkeit-popup-eingabe raussuchen
			E2_RecursiveSearch_Component oSearchGueltigkeitsbuttons = new E2_RecursiveSearch_Component(oThis.oModulContainerList,
																			bibALL.get_Vector(BSAAL_ButtonChangeGueltigkeit_IN_LIST.class.getName()),
																			null);
			
			//dann die sortierbuttons mit den sortierbuttons
			E2_RecursiveSearch_Component oSearchSortsbuttonsInnen = new E2_RecursiveSearch_Component(oThis.oModulContainerList,
																			bibALL.get_Vector(	E2_ButtonListSorter.ownButton.class.getName(),
																								BSAAL_ComponentMAP_List.SortName.class.getName()),
																			null);
			E2_RecursiveSearch_Component oSearchSortsbuttonsAussen = new E2_RecursiveSearch_Component(oThis.oModulContainerList,
																			bibALL.get_Vector(	E2_ButtonListSorter.class.getName(),
																								BSAAL_ComponentMAP_List.SortName.class.getName()),
																			null);
			

			
			//dann alle komponenten in der NaviGrid - liste suchen
			E2_RecursiveSearch_Component oSearchComponentsInNavigrid = new E2_RecursiveSearch_Component(oThis.oModulContainerList.get_oNaviList().get_oDataGrid(),
																										bibALL.get_Vector(Component.class.getName()),
																										null);
			
			//Jetzt Vector mit allen Componenten, die NICHT inaktiv werden duerfen:
			Vector<Component> vUntouchable = new Vector<Component>();
			vUntouchable.addAll(oSearchComponentsInNavigrid.get_vAllComponents());
			
			vUntouchable.removeAll(oSearchGueltigkeitsbuttons.get_vAllComponents());    // die buttons muessen INAKTIV sein waehrend editiert wird
			vUntouchable.removeAll(oSearchSortsbuttonsInnen.get_vAllComponents());   		 // die Sort-buttons muessen INAKTIV sein waehrend editiert wird
			vUntouchable.removeAll(oSearchSortsbuttonsAussen.get_vAllComponents());   		 // die Sort-buttons muessen INAKTIV sein waehrend editiert wird
			
			vUntouchable.addAll(oSearchSaveAndCancel.get_vAllComponents());
			
			E2_ComponentDisabler oDisabler = new E2_ComponentDisabler(null);
			oDisabler.INIT(vUntouchable);
			
			oDisabler.get_vComplement().addAll(oSearchSaveAndCancel.get_vAllComponents());   //diese sind komplementaer
			oDisabler.setDisabled();
			oThis.oModulContainerList.set_oDisabler(oDisabler);
			
			
		}
	}
	
	
}
