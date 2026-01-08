package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;

public class FPT_BUTTON_MOVE_BACK_TO_POOL extends MyE2_Button
{
	private FU_LIST_ModulContainer ModulContainer = null;

	public FPT_BUTTON_MOVE_BACK_TO_POOL(FU_LIST_ModulContainer Modulcontainer)
	{
		super(new MyE2_String("<<POOL"));
		this.ModulContainer = Modulcontainer;
		this.add_oActionAgent(new ownActionAgentMoveBack());
		this.setToolTipText(new MyE2_String("Die markierten Fahrten aus dem Fahrplan zurück in den Pool verschieben ...").CTrans());
		this.add_IDValidator(new FP_Validator_FAHRT_GELOESCHT_ODER_STORNIERT());
	}


	
	/*
	 * neue Zeile
	 */
	private class ownActionAgentMoveBack extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_NavigationList oNavList = FPT_BUTTON_MOVE_BACK_TO_POOL.this.ModulContainer.get_oNavList();
			Vector<String> vIDsToMove = oNavList.get_vSelectedIDs_Unformated();
			
			if (vIDsToMove.size() != 1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau einen Datensatz zum Zurückbuchen auswählen !!"));
			}
			else
			{
				bibMSG.add_MESSAGE( FPT_BUTTON_MOVE_BACK_TO_POOL.this.valid_IDValidation(vIDsToMove));
				if (bibMSG.get_bIsOK())
				{					
					E2_ConfirmBasicModuleContainer oConfirm = new E2_ConfirmBasicModuleContainer(
							new MyE2_String("Sicherheitsabfrage"),
							new MyE2_String("Zurückbuchen ?"),
							new MyE2_String(""),
							new MyE2_String("JA"),
							new MyE2_String("NEIN"),
							new Extent(300),
							new Extent(200)
							);
					oConfirm.set_ActionAgentForOK(new ownActionAgentConfirmBookBack(vIDsToMove,oNavList));
					oConfirm.show_POPUP_BOX();
				}
			}
				
		}
	}

	
	private class ownActionAgentConfirmBookBack extends XX_ActionAgent
	{
		
		private Vector<String> 		vIDs_To_book_back = null;
		private E2_NavigationList 	oNaviList = null;
		
		
		public ownActionAgentConfirmBookBack(Vector<String> vIDs_To_bookback, E2_NavigationList onaviList)
		{
			super();
			this.vIDs_To_book_back=vIDs_To_bookback;
			this.oNaviList = onaviList;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
				
			String cSQLStatement = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET DAT_FAHRPLAN_FP=NULL,ID_MASCHINEN_LKW_FP=NULL, SORTIERUNG_FP=NULL "+
									" WHERE ID_VPOS_TPA_FUHRE =";
			
			Vector<String> vStatements = new Vector<String>();
			
			for (int i=0;i<this.vIDs_To_book_back.size();i++)
			{
				vStatements.add(cSQLStatement+(String)this.vIDs_To_book_back.get(i));
			}
			
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			bibMSG.add_MESSAGE(oDB.ExecMultiSQLVector(vStatements,true));
			if (bibMSG.get_bIsOK())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message(""+this.vIDs_To_book_back.size()+"  Datensätz(e) wurde(n) zurückgebucht !"),true);
				try
				{
					oNaviList.get_vActualID_Segment().removeAll(this.vIDs_To_book_back);
					oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();						// liste bauen
					oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen
				}
				catch (myException ex)
				{
					ex.printStackTrace();
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(),true);
				}
			}
		}
	}


	
	
}
