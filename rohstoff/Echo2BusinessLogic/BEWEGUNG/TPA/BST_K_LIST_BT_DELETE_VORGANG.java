package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class BST_K_LIST_BT_DELETE_VORGANG extends MyE2_Button
{

	public BST_K_LIST_BT_DELETE_VORGANG(E2_NavigationList onavigationList) throws myException
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	"JT_VKOPF_TPA",
				"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
				bibALL.get_Array("N","N"),
				true, new MyE2_String("Nur erlaubt bei Transportaufträgen, die NICHT abgeschlossen und NICHT geloescht sind !")));

		
		String cSQLControlNoBuchungen = 
				" SELECT "+ 
			    "   NVL(COUNT(JT_VPOS_RG.ID_VPOS_RG),-1) "+
			   " FROM "+ 
			    bibE2.cTO()+".JT_VPOS_TPA_FUHRE,"+ 
			    bibE2.cTO()+".JT_VPOS_RG,"+ 
			    bibE2.cTO()+".JT_VPOS_TPA"+ 
			    " WHERE "+ 
			    " NVL(JT_VPOS_RG.DELETED,'N')        = 'N'"+
			    " AND JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ZUGEORD = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE"+
			    " AND JT_VPOS_TPA_FUHRE.ID_VPOS_TPA        = JT_VPOS_TPA.ID_VPOS_TPA"+ 
			    " AND JT_VPOS_TPA.ID_VKOPF_TPA             = #WERT#"; 
		
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(cSQLControlNoBuchungen,bibALL.get_Array("0"),true, new MyE2_String("Nur erlaubt bei Transportaufträgen, deren Fuhren noch keine eingetragenen Buchungen enthalten !")));
		
		//2012-08-02: neue schaerfere validierer fuer das loeschen von tpa-positionen
		this.add_IDValidator(new ownID_Validator_CheckFuhren());
		
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_TPA_LIST,"LOESCHEN_TPA"));
		this.setToolTipText(new MyE2_String("Löschen eines (oder mehrerer) Transportaufträg(e)").CTrans());
	}
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList, "JT_VKOPF_TPA", false);
		}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}
	
	
	
	/*
	 * 2012-08-02: neue schaerfere validierer fuer das loeschen von tpa-positionen
	 */
	private class ownID_Validator_CheckFuhren extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VKOPF_TPA) throws myException
		{
			MyE2_MessageVector oMVRueck = new MyE2_MessageVector();
			
			RECORD_VKOPF_TPA  recVKOPF = new RECORD_VKOPF_TPA(cID_VKOPF_TPA);
			
			RECLIST_VPOS_TPA  reclistVPOSTPA = recVKOPF.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa();
			
			for (RECORD_VPOS_TPA  recVPOS: reclistVPOSTPA.values())
			{
				PRUEF_RECORD_VPOS_TPA_FUHRE recFuhre = new PRUEF_RECORD_VPOS_TPA_FUHRE(recVPOS.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0),true);
				
				oMVRueck.add_MESSAGE(recFuhre.Check_if_can_be_deleted());
			}
			return oMVRueck;
		}
	}
	
}
