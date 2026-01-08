package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FIBU__LIST_BT_JumpToRG_MultiSelect extends MyE2_ButtonInLIST
{

	private E2_NavigationList  	navi_list = null;
	private Vector<String>      v_targed_ids = new Vector<>();
	
	
	public FIBU__LIST_BT_JumpToRG_MultiSelect(E2_NavigationList p_navi_list) throws myException {
		super(E2_ResourceIcon.get_RI("kompass.png"));
		this.navi_list =	p_navi_list;
		this.setToolTipText(new MyE2_String("Springt ins Rechnungs- oder Gutschriftmodul zu allen ausgewählten FIBU-Zeilen").CTrans());
		this.add_oActionAgent(new ownActionJumpToRG());
		
		this.add_GlobalValidator(new ownValidator_only_single_type());
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		throw new myExceptionCopy("Error Copying FIBU__LIST_BT_JumpToRG_MultiSelect");
	}
	
	
	
	private class ownActionJumpToRG extends XX_ActionAgentJumpToTargetList 	{
		public ownActionJumpToRG() throws myException 	{
			super(null, "Rechnung oder Gutschrift");
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException	{
			return FIBU__LIST_BT_JumpToRG_MultiSelect.this.v_targed_ids;
		}
		
		
		//kann ueberschrieben werden wenn innerhalb der aktion noch etwas definiert werden muss ...
		public void OVERRIDE_SETTINGS_BEFORE_ACTION() throws myException {
			Vector<String>  v_ids_rechnung = new Vector<>();
			Vector<String>  v_ids_gutschrift = new Vector<>();
			Vector<String>  v_ids_sonst = new Vector<>();

			FIBU__LIST_BT_JumpToRG_MultiSelect.this.generate_ids(v_ids_rechnung, v_ids_gutschrift, v_ids_sonst);
			
			FIBU__LIST_BT_JumpToRG_MultiSelect.this.v_targed_ids.removeAllElements();
			
			// der validierer verindert gemischtes ankreuzen
			this.set_cModuleName(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_RECHNUNGEN_LIST.name());
			if (v_ids_rechnung.size()>0) {
				FIBU__LIST_BT_JumpToRG_MultiSelect.this.v_targed_ids.addAll(v_ids_rechnung);
			} else if (v_ids_gutschrift.size()>0) {
				FIBU__LIST_BT_JumpToRG_MultiSelect.this.v_targed_ids.addAll(v_ids_gutschrift);
				this.set_cModuleName(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_GUTSCHRIFTEN_LIST.name());
			}
		}

		
	}
	
	/**
	 * sieht nach, was aktuell selektiert ist
	 * @param v_ids_rechnung
	 * @param v_ids_gutschrift
	 * @param v_ids_sonst
	 * @throws myException
	 */
	private void generate_ids(Vector<String>  v_ids_rechnung, Vector<String>  v_ids_gutschrift, Vector<String>  v_ids_sonst) throws myException {
		Vector<String>  v_ids = this.navi_list.get_vSelectedIDs_Unformated();

		if (v_ids.size()>0) {
			for (String id: v_ids) {
				RECORD_FIBU  recFibu = new RECORD_FIBU(id);
				if (S.isEmpty(recFibu.get_ID_VKOPF_RG_cUF_NN(""))) {
					v_ids_sonst.add(recFibu.get_ID_VKOPF_RG_cUF_NN(""));
				} else {
					RECORD_VKOPF_RG rec_rg = recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
					if (rec_rg.get_VORGANG_TYP_cUF_NN("").equals(myCONST.VORGANGSART_RECHNUNG)) {
						v_ids_rechnung.add(recFibu.get_ID_VKOPF_RG_cUF_NN(""));
					} else {
						v_ids_gutschrift.add(recFibu.get_ID_VKOPF_RG_cUF_NN(""));
					}
				}
			}
		}
	}


	
	private class ownValidator_only_single_type extends XX_ActionValidator {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			Vector<String>  v_ids_rechnung = new Vector<>();
			Vector<String>  v_ids_gutschrift = new Vector<>();
			Vector<String>  v_ids_sonst = new Vector<>();
			
			FIBU__LIST_BT_JumpToRG_MultiSelect.this.generate_ids(v_ids_rechnung, v_ids_gutschrift, v_ids_sonst);
			
			if (v_ids_gutschrift.size()==0 && v_ids_rechnung.size()==0 && v_ids_sonst.size()==0) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie mindestens eine Zeile einer Rechnung oder Gutschrift aus !")));
			} else {
				if (v_ids_sonst.size()>0) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie mindestens nur Zeilen vom Typ Rechnung oder Gutschrift aus !")));
				} else {
					if (v_ids_rechnung.size()>0 && v_ids_gutschrift.size()>0) {
						mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte wählen Sie mindestens nur Zeilen vom Typ Rechnung oder Gutschrift (nicht gemischt) !")));
					}
				}
			}
			return mv;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}
	
}

	

