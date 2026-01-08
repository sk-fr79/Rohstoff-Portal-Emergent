package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_ComponentExt;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_BT_FUHRE_EDIT_MASK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_FUS_Window;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Grid_ErfassteFuhren;

public class FUS_BT_BAUE_FUHREN extends MyE2_Button
{

	public FUS_BT_BAUE_FUHREN() throws myException
	{
		super(new MyE2_String("Fuhren aus der Merkliste erzeugen"), MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN());
		
		this.add_oActionAgent(new actionAddToList());
		this.add_GlobalValidator(new ownValidator());
		
		//am anfang immer disabled
		this.set_bEnabled_For_Edit(false);
	}

	
	private class actionAddToList extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FUS_Vector_FuhreRepraesentantInListe  vAlleRepraesentanten = 
				new _SEARCH_Grid_ErfassteFuhren().get_Found_FUS_Grid_ErfassteFuhren().get_vFuhren();
			
			E2_NavigationList  oNAviList = 
				new _SEARCH_FUS_Window().get_Found_FUS_Window().get_oNaviList();
			
			
			Vector<String> vIDs = new Vector<String>();
			Vector<String> vSQLInserts = new Vector<String>();

			for (int i=0; i<vAlleRepraesentanten.size();i++)
			{
				RECORDNEW_VPOS_TPA_FUHRE  recNewFuhre = vAlleRepraesentanten.get(i).get_RECORDNEW_VPOS_TPA_FUHRE();
				//zuerst das statement holen (erzeugt automatisch eine id_vpos_tpa_fuhre)
				
				vSQLInserts.add(recNewFuhre.get_InsertSQLStatementWith_Id_Field(false, true));
				vIDs.add(recNewFuhre.get_cLastSEQ_NUMBER());
			}
			
			MyE2_MessageVector  oMV = bibDB.ExecMultiSQLVector(vSQLInserts, true);
			
			if (oMV.get_bIsOK())
			{
				oNAviList.ADD_NEW_ID_TO_ALL_VECTORS_IN_FRONT(vIDs);
				oNAviList.set_SelectIDs(vIDs);
				new _SEARCH_FUS_Window().get_Found_FUS_Window().CLOSE_AND_DESTROY_POPUPWINDOW(true);
				
				//jetzt die neuen fuhren in der maske oeffnen
				new _Searcher_FU_LIST_BT_FUHRE_EDIT_MASK().get_Button().do_OnlyCode_from_OtherActionAgent(oExecInfo.get_MyActionEvent());
				
			}
			else
			{
				//dann wurde nix gespeichert
				bibMSG.add_MESSAGE(oMV);
			}
		}
	}
	
	
	
	//ein sucher-object fuer den Standard-listenbearbeitungsbutton fuer das direkte bearbeiten aller fuhren
	private class _Searcher_FU_LIST_BT_FUHRE_EDIT_MASK extends E2_RecursiveSearch_ComponentExt<FU_LIST_BT_FUHRE_EDIT_MASK>
	{

		public _Searcher_FU_LIST_BT_FUHRE_EDIT_MASK()
		{
			super(FU_LIST_BT_FUHRE_EDIT_MASK.class);
		}

		public FU_LIST_BT_FUHRE_EDIT_MASK get_Button()
		{
			if (this.get_vAllComponents().size()==1)    //nur wenn exact einer gefunden wird
			{
				return (this.get_vAllComponents().get(0));
			}
			return null;	
		}
		
	}
	
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException
		{
			return null;
		}
	}
	
}
