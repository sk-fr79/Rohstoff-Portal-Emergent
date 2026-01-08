package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_FORMULAR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.BELEG_VALIDIERER.BELEGTYP4VALID;

public class FIBU_LIST_BT_PrintMail_Button extends MyE2_Button implements DS_IF_components4decision {
	
	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
	
	private Vector<String> selectedFibuIds;
	private RECORD_FIBU_FORMULAR fibu_record_formular;
	
	public FIBU_LIST_BT_PrintMail_Button(String oText, Vector<String> v_selectedbuchungsblock, RECORD_FIBU_FORMULAR o_fibu_record_formular) {
		super(oText);
		
		this.selectedFibuIds 		= v_selectedbuchungsblock;
		this.fibu_record_formular 	= o_fibu_record_formular;

		this.add_oActionAgent(new Fibu_validation_actionAgent(this));
	}
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return storage_vector_4_all_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return storage_vector_4_standard_agents;
	}

	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return storage_vector_4_decision_agents;
	}

	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		return hm_descision_containers;
	}
	
	public void refresh_fibu_formular(String wert) throws myException{
		fibu_record_formular = new RECORD_FIBU_FORMULAR(wert);
	}
	
	private class Fibu_validation_actionAgent extends DS_ActionAgent{

		MyE2_MessageVector omv;
		private own_fibu_validation_popUp pop;
		
		public Fibu_validation_actionAgent(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
			 omv= new MyE2_MessageVector();
			 pop = new  own_fibu_validation_popUp(omv);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			RECLIST_FIBU recListFibu = new RECLIST_FIBU(FIBU.buchungsblock_nr.fieldName() + "='" + selectedFibuIds.get(0)+"'", FIBU.id_fibu.fieldName());
			omv.clear();
			for(RECORD_FIBU recFibu: recListFibu){
				omv.addAll(BELEGTYP4VALID.valid_4_Beleg(fibu_record_formular, recFibu));
			}
			return omv.get_bHasWarnings();
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent)
				throws myException {
			return pop;
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
			pop.refreshMessage(omv);
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {return null;}
		
	}
	
	private class own_fibu_validation_popUp extends DS_PopupContainer4Decision{

		private MyE2_Grid mGrid;

		public own_fibu_validation_popUp(MyE2_MessageVector omv) {
			super(FIBU_LIST_BT_PrintMail_Button.this);
			
			mGrid = new MyE2_Grid(1);
					
			MyE2_ContainerEx cont  = new MyE2_ContainerEx();
			
			cont.add(mGrid);
			cont.setHeight(new Extent(200));
			
			E2_Grid4MaskSimple gs=new E2_Grid4MaskSimple().def_(E2_INSETS.I(2,2,20,2)).add_(this.get_bt_OK()).add_(this.get_bt_NO());
			
			this.add(cont,E2_INSETS.I(2,2,2,10));
			this.add(gs,E2_INSETS.I(2,10,2,2));
			
			this.get_bt_OK().setText(new MyE2_String("Trotzdem fortfahren").CTrans());
			this.get_bt_OK().setFont(new E2_FontBold(2));
			
			this.get_bt_NO().setText(new MyE2_String("Abbrechen, überprüfen").CTrans());
			this.get_bt_NO().setFont(new E2_FontBold(2));
			
		}

		@Override
		public int get_width_in_pixel() {return 700;}

		@Override
		public int get_height_in_pixel() {return 400;}

		@Override
		public MyE2_String get_titleText4PopUp() {	return null;	}
		
		public void refreshMessage(MyE2_MessageVector mv){
			mGrid.removeAll();
			for(MyE2_Message message: mv){
				mGrid.add(new MyE2_Label(message.get_cMessage()));
			}
		}
		
	}

}
