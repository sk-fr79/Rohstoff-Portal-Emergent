package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

//done

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_K_LIST_BT_DELETE_VORGANG extends MyE2_Button
{

	public BSRG_K_LIST_BT_DELETE_VORGANG(E2_NavigationList onavigationList, BS__SETTING SETTING)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));

		this.add_GlobalValidator(new E2_ButtonAUTHValidator(SETTING.get_cMODULCONTAINER_LIST_IDENTIFIER(),"LOESCHEN_VORGANG"));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VKOPF_RG",
										"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N'),  NVL(BUCHUNGSNUMMER,'-')",
										bibALL.get_Array("N","N","-"),
										true, 
										new MyE2_String("Nur erlaubt bei Vorgängen, die NICHT abgeschlossen, NICHT geloescht sind und noch keine Buchungsnummer haben!")));
		
		this.add_IDValidator(new ownValidatorLoeschenNurOhneStornoAnteil());
		
		this.setToolTipText(new MyE2_String("Löschen eines (oder mehrerer) Vorgänge").CTrans());
	}
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(onavigationList,  "JT_VKOPF_RG", false);
		}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {return null;}
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException {}
	}
	
	
	private class ownValidatorLoeschenNurOhneStornoAnteil extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String id_KopfRGUnformated) throws myException {
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			RECORD_VKOPF_RG  recRG = new RECORD_VKOPF_RG(id_KopfRGUnformated);
			
			for (RECORD_VPOS_RG  recPosRg: recRG.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg().values()) {
				if (recPosRg.is_DELETED_NO()) {
					if (S.isFull(recPosRg.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) ||
						S.isFull(recPosRg.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN(""))) {
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Rechnung ...",true,recRG.get_ID_VKOPF_RG_cUF(),false," besitzt eine Position aus einem Stornozyklus! Kann nicht gelöscht werden!",true)));
					}
				}
			}
			
			return oMV;
		}
		
	}
	
}
