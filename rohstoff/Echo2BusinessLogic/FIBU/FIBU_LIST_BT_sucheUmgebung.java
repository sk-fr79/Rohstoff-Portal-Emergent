package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS.FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.FIBU_CONST.BUCHUNGSTYP;
import rohstoff.Echo2BusinessLogic.FIBU.SUCHE.VerbundenFibuIdSuche;

public class FIBU_LIST_BT_sucheUmgebung extends MyE2_Button {

	private E2_NavigationList  			navi_list = null;
	private FIBU_LIST_Selector_Filter 	filterSelektor = null;
	private MyE2_Button          		 bt_resetSearchBlock = null;

	private String selectedFibuId = "";

	public FIBU_LIST_BT_sucheUmgebung(E2_NavigationList p_navi_list, FIBU_LIST_Selector_Filter  p_filterSelektor, MyE2_Button p_bt_resetSearchBlock) {
		super(E2_ResourceIcon.get_RI("suche_rg.png"), true);

		this.navi_list = p_navi_list;
		this.filterSelektor = p_filterSelektor;
		this.bt_resetSearchBlock = p_bt_resetSearchBlock;

		this.add_GlobalValidator(new ownValidator());
		this.setToolTipText(new MyE2_String("Suche nach Belegen, die in einem Zusammenhang mit dem ausgewählten Belegt stehen!").CTrans());
		this.add_oActionAgent(new ownActionAgent());


	}



	private class ownValidator extends XX_ActionValidator {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv_rueck = new MyE2_MessageVector();

			if (FIBU_LIST_BT_sucheUmgebung.this.navi_list.get_vSelectedIDs_Unformated_Select_the_one_and_only().size()!=1) {
				mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte genau eine Eintrag vom Typ Rechnung/Gutschrift auswählen !")));
			} 			
			return mv_rueck;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
	}


	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			if (!bibALL.get_bDebugMode()) {
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Umgebungsuche ist noch in der Entwicklung ...")));
//			} else {
				//alle sucheinstellungen leeren
				if (FIBU_LIST_BT_sucheUmgebung.this.navi_list.get_vSelectedIDs_Unformated().size()==1) {
					selectedFibuId = FIBU_LIST_BT_sucheUmgebung.this.navi_list.get_vSelectedIDs_Unformated().get(0);
	
					FIBU_LIST_BT_sucheUmgebung.this.bt_resetSearchBlock.doActionPassiv();
	
					RECORD_FIBU  rf = new RECORD_FIBU(selectedFibuId);
	
					if (!(BUCHUNGSTYP.find_buchungstyp(rf.get_BUCHUNGSTYP_cUF())!=null && BUCHUNGSTYP.find_buchungstyp(rf.get_BUCHUNGSTYP_cUF()).is_beleg())) {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Umgebungssuche ist nur mit Einträgen vom Typ Rechnung oder Gutschrift möglich !")));
					} else {
						VerbundenFibuIdSuche VerFibuIdsSuche = new VerbundenFibuIdSuche();
	
						//hier steht die suchklasse ... mit dem ergebnis
						Vector<String> v_suchergebniss = VerFibuIdsSuche.sucheBelegenFibuIds(selectedFibuId);
	
						if(v_suchergebniss.size()>0){
						
						FIBU_LIST_BT_sucheUmgebung.this.filterSelektor.add_orLine_from_external(
								FIBU.id_fibu, 
								COMP.IN, bibALL.Concatenate(v_suchergebniss, ",", ""), 
								true, true);
						}else{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Keine zusammenghörenden Belege gefunden.")));
						}
					}
				} else {
					//sollte vom validator abgefangen werden
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Nur eine Zeile !!!")));
				}
//			}
		}
	}

}
