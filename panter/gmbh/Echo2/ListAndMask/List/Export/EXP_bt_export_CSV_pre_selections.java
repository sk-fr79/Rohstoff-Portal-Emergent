package panter.gmbh.Echo2.ListAndMask.List.Export;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Decision.IF_components_4_decision;
import panter.gmbh.Echo2.ActionEventTools.Decision.Std_action_popup_frage_yes_no;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class EXP_bt_export_CSV_pre_selections extends MyE2_Button  implements IF_components_4_decision {

	private Vector<XX_ActionAgent> 		v_storage_vector_4_action_agents = new Vector<>();
	private Vector<XX_ActionAgent> 		v_storage_vector_4_status_at_start = new Vector<>();
	private E2_NavigationList 			navigationlist = null;
	//private String 			  			c_primary_table_name = null;
	
	//hier koennen weitere record-klassen mit einer beziehungsvorschrift hinterlegt werden
	private Vector<EXP_addOnRecords>  	v_append_recs = new Vector<>();
	
	//hier werden felder hinterlegt, die nicht exportiert werden duerfen
	private Vector<IF_Field>   			v_fields_not_to_export = new Vector<>();
	
	private _TAB  						base_tab = null;
	
	
	public EXP_bt_export_CSV_pre_selections(E2_NavigationList p_navigationlist,  String authcode_button) {
		this(p_navigationlist, null, authcode_button);
	}
		
	public EXP_bt_export_CSV_pre_selections(E2_NavigationList p_navigationlist) {
		this(p_navigationlist, null, null);
	}


	
	
	public EXP_bt_export_CSV_pre_selections(E2_NavigationList p_navigationlist, String authcode_modul, String authcode_button) {
		super(new MyE2_String("Datenexport ins CSV-Format"));
		this.navigationlist = p_navigationlist;

		this.add_GlobalValidator(new ownValidator());
		
		if (S.isEmpty(authcode_button)) {
			authcode_button="CSV_EXPORT";
		}
		if (S.isEmpty(authcode_modul)) {
			this.add_GlobalAUTHValidator_AUTO(authcode_button);
		} else {
			this.add_GlobalAUTHValidator(authcode_modul, authcode_button);
		}
		
		//immer ausschliessen USER.PASSWED
		this.v_fields_not_to_export.add(USER.passwort);
		
		
		this.add_oActionAgent(new ownAction_start_yes_no(this));   //decision-Action
		this.add_oActionAgent(new ownActionAgentSelectCols());
		
		this.setToolTipText(new MyE2_String("Export der Daten aus der momentanen Selektion in eine CSV-Datei").CTrans());
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_action_agents() throws myException {
		return this.v_storage_vector_4_action_agents;
	}


	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_status_at_start() throws myException {
		return this.v_storage_vector_4_status_at_start;
	}
	
	
	private class ownActionAgentSelectCols extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			EXP_bt_export_CSV_pre_selections oThis = EXP_bt_export_CSV_pre_selections.this;
			String c_primary_table_name = oThis.navigationlist.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE();
			oThis.base_tab = _TAB.find_Table(c_primary_table_name);
			
			if (oThis.base_tab ==null) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Tabelle ist nicht identifiziert worden .."+c_primary_table_name)));
				return;
			}
			
			new EXP_bt_export_CSV_pre_selections_popupWindow(oThis.base_tab, oThis.navigationlist, oThis.v_append_recs,oThis.v_fields_not_to_export);
		}
		
	}
	
	
	
	private class ownAction_start_yes_no extends Std_action_popup_frage_yes_no {
		public ownAction_start_yes_no(IF_components_4_decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		protected MyE2_String get_popup_titel_string() throws myException {
			return new MyE2_String("Sind Sie sicher ?");
		}

		@Override
		protected void define_buttons_and_fill_grid_in_info_popup(MyE2_Button bt_ok, MyE2_Button bt_cancel,	MyE2_Grid grid_4_info) throws myException {
			bt_ok.setText(new MyE2_String("Ja").CTrans());;
			bt_cancel.setText(new MyE2_String("Nein").CTrans());
			int[] i_breite = {200,200};
			grid_4_info.set_Spalten(i_breite);
			grid_4_info.add(new E2_Grid4MaskSimple()
								.def_(new Alignment(Alignment.CENTER, Alignment.CENTER))
								.def_(E2_INSETS.I(2,5,2,5))
								.def_(2,1)
								.add_(new MyE2_Label(new MyE2_String("Die zu exportierende Anzahl von Tabellenzeilen ist: ")))
								.add_(new MyE2_Label(new MyE2_String(""+EXP_bt_export_CSV_pre_selections.this.navigationlist.get_vectorSegmentation().size()),new E2_FontBold(2)))
								.add_(new MyE2_Label(new MyE2_String("Dennoch fortfahren ? ")))
								.def_(E2_INSETS.I(2,15,2,5))
								.def_(1, 1)
								.add_(bt_ok)
								.add_(bt_cancel)
								.setSize_(i_breite)
					);
					
		}

		@Override
		protected Extent get_width_of_popup() { return new Extent(370); }

		@Override
		protected Extent get_height_of_popup() { return new Extent(180);}
		
	}

	
	
	private class ownValidator extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector  mv = new MyE2_MessageVector();
			EXP_bt_export_CSV_pre_selections oThis = EXP_bt_export_CSV_pre_selections.this;
			String c_primary_table_name = oThis.navigationlist.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE();
			
			if (S.isEmpty(c_primary_table_name)) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es konnte kein Tabellenname erkannt werden !")));
			}
			return mv;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}

	public Vector<EXP_addOnRecords> get_v_append_recs() {
		return v_append_recs;
	}

	public Vector<IF_Field> get_v_fields_not_to_export() {
		return v_fields_not_to_export;
	}

	
	
}
