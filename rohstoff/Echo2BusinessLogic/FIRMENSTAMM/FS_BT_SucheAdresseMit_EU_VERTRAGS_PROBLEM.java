package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.messageBox.E2_MessageBox;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import panter.gmbh.indep.myVectors.bibVECTOR;
import panter.gmbh.indep.myVectors.vectorForSegmentation;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_DecisionHat_EU_VERTRAG_warenbewegung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_DecisionHat_EU_VERTRAG;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR.FS__Selector_flexible;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;



public class FS_BT_SucheAdresseMit_EU_VERTRAGS_PROBLEM extends MyE2_Button implements DS_IF_components4decision{

	private FS__Selector_flexible 	ownSelector_Flexible = null;
		
	private MyE2_CheckBox			cb_only_last_year = new MyE2_CheckBox(new MyE2_String("Nur Fuhren des letzten Jahres berücksichtigen ...")) ;
	
	public FS_BT_SucheAdresseMit_EU_VERTRAGS_PROBLEM(FS__Selector_flexible selector_Flexible) {
		super(new MyE2_String("Suche Adressen mit EU-Vertrags-Fehlern"));
		this.ownSelector_Flexible = selector_Flexible;
		
		this.add_oActionAgent(new ownWarnActionAgent(this));
		
		this.add_oActionAgent(new ownActionStartSearch());
		
	}


	
	//2016-04-18: fehler, es wurden nur fuhren mit fuhrenorten gefunden
	private class ownActionStartSearch extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			//selektion aller fuhren mit mindestens einem ort im ausland
			String id_germany = bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF();
			
			if (S.isEmpty(id_germany)) {
				throw new myException(this,"Error: Mandant has no ID_LAND !!");
			}

			String view_name = "V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN";

			vectorForSegmentation v_segment = new vectorForSegmentation();
			v_segment.set_iSegmentGroesse(500);
			
			String sql_from_block =  " FROM "+bibE2.cTO()+"."+view_name+" FU "
								    +" INNER JOIN  "+bibE2.cTO()+".JT_ADRESSE AST  ON ( FU.ID_ADRESSE_LAGER_START=AST.ID_ADRESSE) " 
								    +" INNER JOIN  "+bibE2.cTO()+".JT_ADRESSE AZI  ON ( FU.ID_ADRESSE_LAGER_ZIEL=AZI.ID_ADRESSE) "
								    +" WHERE "
						            +" AST.ID_LAND <> AZI.ID_LAND "        
								    +" AND NVL(FU.DELETED,'N')='N' "
								    +" AND NVL(FU.IST_STORNIERT,'N')='N' "
								    +" AND NVL(FU.OHNE_ABRECHNUNG,'N')='N'"
								    +" AND NVL(FU.STATUS_BUCHUNG,1)>=0";

			String sql_ids = "SELECT DISTINCT FU.ID_VPOS_TPA_FUHRE "+sql_from_block;
			if (FS_BT_SucheAdresseMit_EU_VERTRAGS_PROBLEM.this.cb_only_last_year.isSelected()) {
				sql_ids=sql_ids+" AND FU.DATUM_ABHOLUNG>=SYSDATE-365";
			}
				
			
			String[][] ids = bibDB.EinzelAbfrageInArray(sql_ids);
			
			v_segment.addAll(bibVECTOR.get_VectorFromArray(ids));

			
			Vector<DA_DecisionHat_EU_VERTRAG_warenbewegung>  v_gefundene_fehler_relationen = new Vector<>();
			VectorSingle id_adressen_with_error = new VectorSingle();
			int i_zaehler = 0;

			
			for (int  i=0; i<v_segment.get_iZahlSegmente();i++) {
				
				Vector<String>  v_seg = v_segment.get_vSegment(i);
	
				String sql = " SELECT TO_CHAR(FU.ID_VPOS_TPA_FUHRE,'0000000000')||to_char(NVL(FU.ID_VPOS_TPA_FUHRE_ORT,0),'0000000000') AS ID_ID "
									+","+VPOS_TPA_FUHRE.id_adresse_lager_start.fn("FU")
									+","+VPOS_TPA_FUHRE.id_adresse_lager_ziel.fn("FU")
									+","+VPOS_TPA_FUHRE.deleted.fn("FU")
									+","+VPOS_TPA_FUHRE.ist_storniert.fn("FU")
									+","+VPOS_TPA_FUHRE.ohne_abrechnung.fn("FU")
									+","+VPOS_TPA_FUHRE.status_buchung.fn("FU")
									+","+VPOS_TPA_FUHRE.datum_abholung.fn("FU")
									+","+VPOS_TPA_FUHRE.datum_abholung_ende.fn("FU")
									+","+VPOS_TPA_FUHRE.datum_anlieferung.fn("FU")
									+","+VPOS_TPA_FUHRE.datum_anlieferung_ende.fn("FU")
									+","+VPOS_TPA_FUHRE.datum_aufladen.fn("FU")
									+","+VPOS_TPA_FUHRE.datum_abladen.fn("FU")
									+","+VPOS_TPA_FUHRE.id_artikel_bez_ek.fn("FU")
									+","+VPOS_TPA_FUHRE.id_artikel_bez_vk.fn("FU")
									+","+VPOS_TPA_FUHRE.ohne_avv_vertrag_check.fn("FU")
									+sql_from_block+" AND FU.ID_VPOS_TPA_FUHRE IN ("+bibALL.Concatenate(v_seg, ",", "-1")+")";
				
				RECLIST_VPOS_TPA_FUHRE rlf = new RECLIST_VPOS_TPA_FUHRE(sql,bibALL.get_oConnectionNormal(),0,"ID_ID");
	
				
				if (rlf.size()>0) {
					Vector<DA_DecisionHat_EU_VERTRAG_warenbewegung> v_sammler = new Vector<>();

					for (RECORD_VPOS_TPA_FUHRE rf: rlf) {
						DA_DecisionHat_EU_VERTRAG_warenbewegung wb_decision = new DA_DecisionHat_EU_VERTRAG_warenbewegung(rf);
						v_sammler.add(wb_decision);
						if ((i_zaehler++)%100==0) {
							DEBUG.System_println(""+i_zaehler);
						}
					}
					
					ownDecision od = new ownDecision(v_sammler);
					od.make_decision_when_true_then_popup();
					v_gefundene_fehler_relationen.addAll(od.get_problem_relations());
					
				}
						
				//jetzt die gefundenen fehler in den filter injizerien
				for (DA_DecisionHat_EU_VERTRAG_warenbewegung decision_wb: v_gefundene_fehler_relationen) {
					if (decision_wb.has_fehlerstatus_ladeseite()) {
						id_adressen_with_error.add(new RECORD_ADRESSE_extend(decision_wb.adress_start).get_main_Adress().get_ID_ADRESSE_cUF());
					}
					if (decision_wb.has_fehlerstatus_abladeseite()) {
						id_adressen_with_error.add(new RECORD_ADRESSE_extend(decision_wb.adress_ziel).get_main_Adress().get_ID_ADRESSE_cUF());
					}
				}
			
			}
			
			
			if (id_adressen_with_error.size()==0) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Ich habe keine Adresse gefunden, die nach dem momentanen Stand der Fuhrenstatistik einen fehlerhaften EU-Vertragsstatus hat !")));
			} else {
				String c_id_list = bibALL.Concatenate(id_adressen_with_error, ",", "0");

				DEBUG.System_println("vorher: "+ c_id_list);
				
				// evtl. wurden inaktive gefunden
				SEL  sel_aktive_adressen = new SEL(_TAB.adresse).FROM(_TAB.adresse).WHERE(new vgl_YN(ADRESSE.aktiv,true)).AND(ADRESSE.id_adresse, "IN", "("+c_id_list+")");
				RECLIST_ADRESSE rl_aktiv = new RECLIST_ADRESSE(sel_aktive_adressen.s());
				
				Vector<String>  anzahl_gefundene_aktive = bibVECTOR.get_Vector(rl_aktiv.get_ID_ADRESSE_hmString_UnFormated("0").values());
				c_id_list = bibALL.Concatenate(anzahl_gefundene_aktive,",","0");
				
				DEBUG.System_println("nachher: "+ c_id_list);
				
				FS_BT_SucheAdresseMit_EU_VERTRAGS_PROBLEM.this.ownSelector_Flexible.add_orLine_from_external(ADRESSE.id_adresse, COMP.IN, c_id_list, true, true);

				new ownMessageBox(anzahl_gefundene_aktive);
				
			}
		}
	}
	
	
	
	
	/**
	 * benutzt die bewertungklasse der druckbuttons
	 * @author martin
	 *
	 */
	private class ownDecision extends DA_DecisionHat_EU_VERTRAG {
		
		private Vector<DA_DecisionHat_EU_VERTRAG_warenbewegung> v_warenbeweg = null;
		
		public ownDecision(Vector<DA_DecisionHat_EU_VERTRAG_warenbewegung> v_warenbewegung) {
			super(null);
			this.v_warenbeweg = v_warenbewegung;
			this.set_collector_relations(new own_collector());
		}
		
		private class own_collector extends collector_Relations {
			@Override
			public ArrayList<DA_DecisionHat_EU_VERTRAG_warenbewegung> get_datasets() throws myException {
				ArrayList<DA_DecisionHat_EU_VERTRAG_warenbewegung> al_ret = new ArrayList<>();
				al_ret.addAll(ownDecision.this.v_warenbeweg);
				return al_ret;
			}
		}
		
		public Vector<DA_DecisionHat_EU_VERTRAG_warenbewegung> get_problem_relations() {
			Vector<DA_DecisionHat_EU_VERTRAG_warenbewegung> v_ret = new Vector<>();
			for (DA_DecisionHat_EU_VERTRAG_warenbewegung wb: this.v_warenbeweg) {
				if (wb.has_fehlerstatus()) {
					v_ret.add(wb);
				}
			}
			return v_ret;
		}
	}
	
	
	

	
	
	private class ownMessageBox extends E2_MessageBox {
		
		private Vector<String>  v_found_id_adresse = null;

		private GridLayoutData layoutData4Message = null;
		
		private MyE2_SelectField  sel_order = new MyE2_SelectField();
		
		public ownMessageBox(Vector<String> vfound_id_adresse) throws myException {
			super();
			this.v_found_id_adresse = vfound_id_adresse;
			this.set_titel(new MyE2_String("Adressen mit fehlenden/alten EU-Verträgen"));
	
			String[][] sort = new String[2][2];
			sort[0][0] = "ID-Adresse";
			sort[0][1] = "1";
			sort[1][0] = "Name";
			sort[1][1] = "2";
			
			this.sel_order.set_ListenInhalt(sort, false);
			this.sel_order.set_ActiveValue_OR_FirstValue("1");
			
			this.a(new MyE2_String("Es existieren ").ut(""+vfound_id_adresse.size()).t(" Adressen,"))
													.t("die in mindestens einer Station einen")
													.t("fehlenden oder veralteten EU-Vertrag enthalten.")
													.t("Soll eine Liste gedruckt werden ?");
			
			this.layoutData4Message= new RB_gld()._left_top()._span(2)._ins(E2_INSETS.I(0,2,0,2));
			
			this.get_buttonOK().set_Text(new MyE2_String("Ja-DRUCKEN"));
			this.get_buttonCancel().set_Text(new MyE2_String("Nein-Abbruch"));
			this.render_message_box();
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(300), this.get_window_title());
		}

		@Override
		public void render_message_box() throws myException {
			
			MyE2_Grid  grid = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			for (MyE2_String s: this.get_infos()) {
				grid._add(new MyE2_Label(s), this.layoutData4Message);
			}

			grid._add(new MyE2_Label(new MyE2_String("Sortierung")),new RB_gld()._left_top()._ins(E2_INSETS.I(0,10,0,10) ))
				._add(this.sel_order, new RB_gld()._left_top()._ins(E2_INSETS.I(0,10,0,10)));

			
			grid._add(new E2_Grid4MaskSimple()._add(this.get_buttonOK(), new RB_gld()._ins(E2_INSETS.I(0,0,0,0)))._add(this.get_buttonCancel() , new RB_gld()._ins(E2_INSETS.I(10,0,0,0))), new RB_gld()._ins(E2_INSETS.I(0,10,0,0)));

			grid.set_Spalten(bibARR.ia(200,200));

			this.add(grid,E2_INSETS.I(10,10,10,10));
			
			
			this.get_buttonOK().setStyle(MyE2_Button.StyleTextButtonSTD(new E2_FontBold()));
			this.get_buttonCancel().setStyle(MyE2_Button.StyleTextButtonSTD(new E2_FontBold()));

			this.get_buttonOK().add_oActionAgent(new ownActionAgentPrint());
			this.get_buttonOK().add_oActionAgent(new ownActionClose(true));
			
			this.get_buttonCancel().add_oActionAgent(new ownActionClose(false));
		}
		

		private class ownActionClose extends XX_ActionAgent {
			private boolean ok_or_cancel = true;
			
			public ownActionClose(boolean ok) {
				super();
				this.ok_or_cancel=ok;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ownMessageBox.this.CLOSE_AND_DESTROY_POPUPWINDOW(this.ok_or_cancel);
			}
		}

		private class ownActionAgentPrint extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				E2_JasperHASH_STD  hash = new E2_JasperHASH_STD("liste_adressen_fehlende_eu_vertraege.jasper", new JasperFileDef_PDF());
				
				hash.set_TYPE_PRINT();
				hash.set_cDownloadAndSendeNameStaticPart("ADRESSEN_fehlende_eu_vertraege");
				hash.put("id_list", "("+bibALL.Concatenate(ownMessageBox.this.v_found_id_adresse, ",", "0")+")");
				hash.put("SORTSTRING",ownMessageBox.this.sel_order.get_ActualWert()) ;

				hash.Build_tempFile(false);
				
				hash.get_oTempFileWithSendeName().Download_File();
			}
		}

	}
	
	
	
	//warnungspopup als decision
	private class ownWarnActionAgent extends DS_ActionAgent {

		public ownWarnActionAgent(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent)throws myException {
			return new ownContainer(activeComponent);
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		}

		private class ownContainer extends DS_PopupContainer4Decision {

			public ownContainer(DS_IF_components4decision p_motherComponent) throws myException {
				super(p_motherComponent);
				this.get_bt_OK().set_Text(new MyE2_String("OK - Ich warte"));				
				this.get_bt_NO().set_Text(new MyE2_String("Nein - Ich breche ab"));
				
				E2_Grid4MaskSimple grid = new E2_Grid4MaskSimple()
						.def_(2, 1)
						.add_(new MyE2_Label(new MyE2_String("Die Suche nach Adressen mit problematischen EU-Verträgen kann eine Weile dauern !"),new E2_FontBold(2), true))
						.def_(1, 1)._setSize(95,95)
						._add(FS_BT_SucheAdresseMit_EU_VERTRAGS_PROBLEM.this.cb_only_last_year, new RB_gld()._span(2)._ins(E2_INSETS.I(2,10,10,2)))
						._add(this.get_bt_OK(),new RB_gld()._ins(E2_INSETS.I(2,10,10,2)))
						._add(this.get_bt_NO(),new RB_gld()._ins(E2_INSETS.I(2,10,10,2)));
				this.add(grid, E2_INSETS.I(5,5,5,5));
			}

			@Override
			public int get_width_in_pixel() {
				return 400;
			}

			@Override
			public int get_height_in_pixel() {
				return 230;
			}

			@Override
			public MyE2_String get_titleText4PopUp() {
				return new MyE2_String("Bitte um Bestätigung !");
			}
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}
		
		
	}
	
	
	
	//2016-01-29: descision-pruefungen
	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
	
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return this.storage_vector_4_all_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return this.storage_vector_4_standard_agents;
	}

	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return this.storage_vector_4_decision_agents;
	}

	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		return this.hm_descision_containers;
	}

}
