package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.LinkedHashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;

public class ES_MASK_bt_show_fields_for_sending extends E2_Button {

	private String 							table_base_name = 	null;
	private String 							id_table = 			null;
	

	/**
	 * 
	 * @param p_table_base_name
	 * @param p_id_table
	 * @throws myException 
	 */
	public ES_MASK_bt_show_fields_for_sending(String p_table_base_name, String p_id_table ) throws myException {
		super();
		this.table_base_name = 		p_table_base_name;
		this.id_table = 			p_id_table;
		
		this._image(E2_ResourceIcon.get_RI("help.png"))
			._aaa(new own_action_agent())
			._ttt(new MyE2_String("Zeigt die Felder Betreff- und Text an, wie sie verschickt werden (mit den jeweiligen Ersetzungen)"));
		
	}
	
	
	
	private class own_action_agent extends XX_ActionAgent {

		private E2_Grid                         grid_info = new E2_Grid();
		
		private RB_TextField tf_betreff2send = 	new RB_TextField()._sBDD();
		private RB_TextArea  tf_text2send = 	new RB_TextArea()._sBDD();
		
		public own_action_agent() throws myException {
			super();
			
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			ES_MASK_bt_show_fields_for_sending oThis = ES_MASK_bt_show_fields_for_sending.this;
			
			//jetzt festestellen, ob es eine id_email_send gibt oder ob neueingabe vorliegt
			MyRECORD_IF_RECORDS recTest = oThis.rb_ComponentMap_this_belongsTo().rb_get_belongs_to().rb_Actual_DataobjectCollector().get(new RB_KM(_TAB.email_send)).get_RecORD();
			
			String id_email_send = null;
			if (recTest!=null) {
				id_email_send = ((MyRECORD)recTest).ufs(EMAIL_SEND.id_email_send);
			}
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			LinkedHashMap<String, String>  db_replacer = new  ES__verify_table_name_and_id(oThis.table_base_name, oThis.id_table, id_email_send, mv).get_hm_replacing_list_FromDataSets(); // ES__generatorReplaceList(oThis.table_base_name, oThis.id_table).get_hm_replacement_terms();
			bibMSG.add_MESSAGE(mv);
			
			//jetzt die felder mit platzhalter rausziehen
			ES_MASK_bt_show_fields_for_sending ooThis = ES_MASK_bt_show_fields_for_sending.this;
			
			RB_TextField f_betreff = 	(RB_TextField)ooThis._find_component_in_neighborhood(EMAIL_SEND.betreff);
			RB_TextArea  f_text = 		(RB_TextArea)ooThis._find_component_in_neighborhood(EMAIL_SEND.text);
			
			String betreff = 	bibReplacer.ReplaceSysvariablesInStrings(S.NN(f_betreff.getText()), db_replacer);
			String text = 		bibReplacer.ReplaceSysvariablesInStrings(S.NN(f_text.getText()), db_replacer);

			this.tf_betreff2send.setWidth(f_betreff.getWidth());
			this.tf_text2send.setWidth(f_text.getWidth());
			this.tf_text2send.setHeight(f_text.getHeight());
			
			this.tf_betreff2send.setText(betreff);
			this.tf_text2send.setText(text);
			
			this.grid_info._clear()._setSize(500)._a(this.tf_betreff2send, new RB_gld()._ins(4))._a(this.tf_text2send, new RB_gld()._ins(4));
			new own_popup(this.grid_info);
		}

		

		
		private class own_popup extends E2_BasicModuleContainer {

			public own_popup(E2_Grid  g_info) throws myException {
				super();
				this.add(g_info, E2_INSETS.I(2,2,2,2));
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(400), new MyE2_String("Die Texte, die in der eMail versendet werden, sehen so aus ..."));
			}
			
		}


		
	}
	


	
	
	
}
