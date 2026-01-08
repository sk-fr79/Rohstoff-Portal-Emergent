package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import java.util.ArrayList;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Auswertungen.XX_Auswertungen;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.UserSettings.IF_Saveable_differentTypes;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.M2N;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_M2N;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_M2N;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_M2N;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort.IP_EditPasswortAndHelpText;
import echopointng.Separator;

public class AW2__Tab_Reiter extends MyE2_Grid {

	private Component                oComponentMitZusatzSteuerung = null;
	/*
	 * Basiscontainer fuer auswertungen, die vor dem start des reports komplexe
	 * auswertemassnahmen erfordern (z.B. aufbau einer temporaeren tabelle)
	 */
	private AW2_VectorAuswertungen   vAuswertungen = null;
	private MyE2_ContainerEx         oContainerEX_mit_Auswertungen = new MyE2_ContainerEx();

	private Extent                   oBreiteButtonListe = null;
	private Extent                   oHoeheButtonListe = new Extent(500);

	private MyE2_Grid  		         oGrid_WithAuswerteButtons = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_TextArea            oTF_Hilfen = new MyE2_TextArea();
	private MyE2_Grid             	 oGrid4StartButton = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

	private AW2_BedienPanel          bedienPanel = null;
	
	private RECORD_REPORT_REITER     recRReiter = null;
	
	private AW2__BasicContainer_RohstoffAuswertungen 	callingTabContainer = null;
	
	private AW2_RECLIST_M2N          reclist_M2N_actual = null;
	
	private boolean       			 b_ist_verteiler_tab = false;
	
	private ArrayList<IF_Saveable_differentTypes>   v_savables = new ArrayList<IF_Saveable_differentTypes>();
	
	
	public AW2__Tab_Reiter(		RECORD_REPORT_REITER   						p_recRReiter, 
								Component 									p_componentMitZusatzSteuerung, 
								AW2__BasicContainer_RohstoffAuswertungen 	p_callingTabContainer) throws myException {
		super(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.recRReiter = p_recRReiter;
		this.callingTabContainer = p_callingTabContainer;
		
		this.b_ist_verteiler_tab = (this.recRReiter==null);
		
		this.bedienPanel = new AW2_BedienPanel(this,this.b_ist_verteiler_tab);
		
		this.oComponentMitZusatzSteuerung = p_componentMitZusatzSteuerung;
		this._init();
	}

	
	private void _init() throws myException {
		if (this.oBreiteButtonListe!=null)  {
			this.oContainerEX_mit_Auswertungen.setWidth(this.oBreiteButtonListe);
		}
		
		this.oContainerEX_mit_Auswertungen.setHeight(this.oHoeheButtonListe);
		this.oContainerEX_mit_Auswertungen.add(this.oGrid_WithAuswerteButtons);
		
		
		this.oTF_Hilfen.setStyle(MyE2_TextArea.STYLE_ANZEIGE_FIELD(-4));
		this.oTF_Hilfen.EXT().set_bDisabledFromBasic(true);
		this.oTF_Hilfen.set_iWidthPixel(500);
		this.oTF_Hilfen.setHeight(this.oHoeheButtonListe);
		
		this.removeAll();
		
		if (this.oComponentMitZusatzSteuerung != null) {
			this.add(this.oComponentMitZusatzSteuerung,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_5_10_5,3));
		}
		
		//zeile 1
		this.add(this.bedienPanel,LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_5_10_5));
		this.add(new MyE2_Label(),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_5_10_5));
		this.add(new MyE2_Label(),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_5_10_5));
		
		//zeile 2
		this.add(this.oContainerEX_mit_Auswertungen,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 5, 5, 10)));
		this.add(this.oTF_Hilfen,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 5, 5, 10)));
		this.add(this.oGrid4StartButton,LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 5, 5, 10)));
		
		this._rebuild_ButtonListe();
		
		
		this._refresh_ButtonAnzeige();
	}
	
	
	
	public void _rebuild_ButtonListe() throws myException {
		this.reclist_M2N_actual = new AW2_RECLIST_M2N();
		
		if (this.b_ist_verteiler_tab) {
			this.vAuswertungen=new AW2_VectorAuswertungen();
			this.reclist_M2N_actual.clear_not_referenced_records();   //alle evtl. nur so rumstehenden m2n-records loeschen
		} else {
			this.vAuswertungen=new AW2_VectorAuswertungen(this.reclist_M2N_actual.get_v_reports_from_reiter(this.recRReiter));
		}
		
		//hier muessen die savables zum speicherstatus gesammelt werdn
		this._rebuild_saveables();

		//jetzt speicherstatus wiederherstellen
		new AW2_StatusSaver(this.recRReiter, this.v_savables).RESTORE();
	}
	
	
	private void _rebuild_saveables() throws myException {
		//jetzt den saveable-vector aufbauen
		this.v_savables.clear();
		this.v_savables.add(this.bedienPanel.get_cb_ZeigeNurSelektierte());
		this.v_savables.add(this.bedienPanel.get_tfSuchfeld());
		
		this.bedienPanel.get_cb_ZeigeNurSelektierte().add_oActionAgent(new ownActionStoreSetting());
		this.bedienPanel.get_btSuche().add_oActionAgent(new ownActionStoreSetting());

		for (XX_Auswertungen ausw: this.vAuswertungen) {
			this.v_savables.add(ausw.get_oCB_Anzeigen());
			ausw.get_oCB_Anzeigen().add_oActionAgent(new ownActionStoreSetting());
		}

	}

	
	public void _refresh_ButtonAnzeige() throws myException {
		String cFilter = S.NN(this.bedienPanel.get_tfSuchfeld().getText());
	
		this.oGrid_WithAuswerteButtons.removeAll();
		
		this.oTF_Hilfen.setText("");
		this.oGrid4StartButton.removeAll();
		
		String cAuswerteGruppeAlt = "";

		boolean is_base = this.vAuswertungen.is_baseVector();
		boolean is_ceo_user = bibALL.get_RECORD_USER().is_DEVELOPER_YES() ||  bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES(); 
		
		
		if (is_ceo_user) {
			this.oGrid_WithAuswerteButtons.setSize(4);
		} else {
			this.oGrid_WithAuswerteButtons.setSize(3);
		}

		
		
		for (int i=0;i<this.vAuswertungen.size();i++) 	{
			XX_Auswertungen  oAuswert = this.vAuswertungen.get(i);
			
			MyE2_Button  oButton =oAuswert.get_ListButton();
				
			oButton.add_oActionAgent(new ownActionWaehleAuswertungUndZeigeStartknopf(oAuswert));
			
			if (oAuswert.get_GlobalValidators4ListButton()!=null && oAuswert.get_GlobalValidators4ListButton().size()>0){
				oButton.add_GlobalValidator(oAuswert.get_GlobalValidators4ListButton());
			}
			
			
			boolean bAdd = true;
			
			
			if (	S.isFull(cFilter) && 
					oButton.getText().toUpperCase().indexOf(cFilter.trim().toUpperCase())==-1  && 
					S.NN(oAuswert.get_cAuswerteGruppe()).toUpperCase().indexOf(cFilter.trim().toUpperCase())==-1){
				bAdd=false;
			}

			
			//2016-07-13: weiteres ausschlusskriterium fuer inaktive reports
			RECORD_REPORT rr = new RECORD_REPORT(oAuswert.get_id_report());
			if (rr.is_AKTIV_NO()) {
				bAdd=false;
			}
			
			
			
			Insets oIN_StartButtonsLeft = new Insets(2, 3, 7, 3);
			Insets oIN_StartButtons = new Insets(2, 3, 2, 3);
			
			//jetzt noch die checkbox beruecksichtigen
			if (this.bedienPanel.get_cb_ZeigeNurSelektierte().isSelected() && !oAuswert.get_oCB_Anzeigen().isSelected())	{
				bAdd = false;
			}
			
			
			if (bAdd) {
				if (this.b_ist_verteiler_tab) {
					if (S.isEmpty(oAuswert.get_cAuswerteGruppe())) {          //reports zum auswertemodul, sonst die zusammengesuchten
						this.oGrid_WithAuswerteButtons.add(oAuswert.get_oCB_Anzeigen(),1, oIN_StartButtons);
						this.oGrid_WithAuswerteButtons.add(oButton,2, oIN_StartButtons);
						
					} else {
						//beim wechsel der eigenen auf fremde auswertungen einen strich malen
						if (S.isEmpty(cAuswerteGruppeAlt))	{
							this.oGrid_WithAuswerteButtons.add(new Separator(),this.oGrid_WithAuswerteButtons.getSize(),E2_INSETS.I_2_10_2_10);
							cAuswerteGruppeAlt=oAuswert.get_cAuswerteGruppe();
						}
	
						this.oGrid_WithAuswerteButtons.add(oAuswert.get_oCB_Anzeigen(),1, oIN_StartButtons);
						this.oGrid_WithAuswerteButtons.add(new MyE2_Label(oAuswert.get_cAuswerteGruppe(), new E2_FontItalic()),1,oIN_StartButtonsLeft);
						this.oGrid_WithAuswerteButtons.add(oButton,1, oIN_StartButtons);
					}
				} else {
					this.oGrid_WithAuswerteButtons.add(oAuswert.get_oCB_Anzeigen(),1, oIN_StartButtons);
					this.oGrid_WithAuswerteButtons.add(oButton,2, oIN_StartButtons);
				}
				
				if (is_ceo_user) {
					if (is_base) {
						
						int i_count = -1;    //umbestimmt
					
						//im base-vector noch die anzeige hinzufuegen, wie oft verteilt wurde
						MyLong  m_l = new MyLong(oAuswert.get_id_report());
						if (m_l.get_oLong()!=null && m_l.get_oLong().longValue()>0) {
							i_count = this.reclist_M2N_actual.countNumber(m_l.get_oLong().intValue());
						}
						
						AW2_InfoLabel  l_info_anzahl = new AW2_InfoLabel();
						if (i_count>=0) {
							l_info_anzahl.set_number(i_count);
							l_info_anzahl.setToolTipText(this.reclist_M2N_actual.get_tooltip_info_verteilung(m_l.get_oLong().intValue()));
						}
						IP_EditPasswortAndHelpText bt_edit_passwd = new IP_EditPasswortAndHelpText(oAuswert.get_id_report());
						bt_edit_passwd.setToolTipText(new MyE2_String("Passwort und Hilfetext zum Report eingeben").CTrans());
						
						 RB_lab lbl_id = new RB_lab()._fsa(-1);
						
						 boolean showId = new RECORD_MANDANT(bibSES.get_ID_MANDANT_UF()).is_SHOW_IDS_ON_REPORT_LABELS_YES();
						 
						 if(showId){
							 lbl_id._t("(ID: "+oAuswert.get_id_report()+")");
						 }
						 
						this.oGrid_WithAuswerteButtons.add(
								new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER())
												.def_(showId?65:1)
												.def_(new Alignment(Alignment.CENTER,Alignment.CENTER))
												.add_(lbl_id)
												.def_(50)
												.def_(new Alignment(Alignment.CENTER,Alignment.TOP))
												.add_without_setting_layout_data_(l_info_anzahl)
												.def_(10)
												.add_(bt_edit_passwd)
												.add_(new bt_Verteile(oAuswert,l_info_anzahl)));
						
					} else {
						this.oGrid_WithAuswerteButtons.add(new bt_Delete(oAuswert));
					}
				}

			}
		}
	}
	
	
	private class bt_Delete extends MyE2_Button {

		private XX_Auswertungen  auswertung = null;
		
		public bt_Delete(XX_Auswertungen  p_auswertung) {
			super(E2_ResourceIcon.get_RI("delete2.png"));
			this.auswertung = p_auswertung;
			this.setToolTipText(new MyE2_String("Diese Zuordnung entfernen !").CTrans());
			this.add_oActionAgent(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				String id_report_reiter = null;
				String id_report = null;
				
				if (AW2__Tab_Reiter.this.recRReiter != null) {
					id_report_reiter = AW2__Tab_Reiter.this.recRReiter.get_ID_REPORT_REITER_cUF();
					
					if (bt_Delete.this.auswertung!=null && S.isFull(bt_Delete.this.auswertung.get_id_report())) {
						
						id_report = bt_Delete.this.auswertung.get_id_report();
						
						SEL sel = new SEL(_TAB.m2n).FROM(_TAB.m2n)
								.WHERE(new vgl(M2N.table_base_1,_TAB.report.baseTableName()))
								.AND(new vgl(M2N.table_base_2,_TAB.report_reiter.baseTableName()))
								.AND(new vgl(M2N.table_id_1,id_report))
								.AND(new vgl(M2N.table_id_2,id_report_reiter));
						
						RECLIST_M2N rmn = new RECLIST_M2N(sel.s());
						for (RECORD_M2N r: rmn) {
							bibMSG.add_MESSAGE(r.DELETE());
						}
						if (bibMSG.get_bIsOK()) {
							RECORD_REPORT  				rr = new RECORD_REPORT(id_report);
							RECORD_REPORT_REITER  		rrr = AW2__Tab_Reiter.this.recRReiter;
							bibMSG.add_MESSAGE(new MyE2_Info_Message(	new MyE2_String("Der Report: ",true,
																		"<"+rr.get_BUTTONTEXT_cUF_NN("<report unbeschriftet>")+">",false,
																		" wurde aus dem Reiter: ",true,
																		"<"+rrr.get_REITERNAME_cUF_NN("<unbenannter Reiter>")+">",false,
																		" entfernt !",true)));
						}
						AW2__Tab_Reiter.this._rebuild_ButtonListe();
						AW2__Tab_Reiter.this._refresh_ButtonAnzeige();
					}
				}
			}
			
		}
		
	}

	private class bt_Verteile extends MyE2_Button {

		private XX_Auswertungen  	oAuswert  = null;
		private AW2_InfoLabel       label_4_anzeige_verteiler = null;
		private RECORD_REPORT 		recRep = null; 
		
		public bt_Verteile(XX_Auswertungen  p_auswert,  AW2_InfoLabel  p_label_4_anzeige_verteiler) throws myException {
			super(E2_ResourceIcon.get_RI("verteiler.png"));
			this.oAuswert = p_auswert;
			this.label_4_anzeige_verteiler = p_label_4_anzeige_verteiler;
			this.recRep = new RECORD_REPORT(bt_Verteile.this.oAuswert.get_id_report()); 
			
			this.setToolTipText(new MyE2_String("Den Report auf andere Gruppen verteilen ...").CTrans());
			
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					new ownBasicContainer();
				}
			});
			
		}
		
		
		private class ownBasicContainer extends E2_BasicModuleContainer {

			public ownBasicContainer() throws myException {
				super();
				
				MyE2_Grid  grid = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				this.add(grid,E2_INSETS.I(2,2,2,2));
				
				//anzeige aller reportgruppen
				AW2_RECLIST_REPORT_REITER  rr = new AW2_RECLIST_REPORT_REITER();
				
				
				grid.add(new MyE2_Label(new MyE2_String("Verteilen:")), E2_INSETS.I(2,2,2,2));
				grid.add(new MyE2_Label(recRep.get_BUTTONTEXT_cF_NN("<buttontext>"), new E2_FontBold()), E2_INSETS.I(2,2,2,10));
				
				for (String id: rr.get_vKeyValues()) {
					RECORD_REPORT_REITER  rrr = rr.get(id);
					grid.add(new ownButtonZuordnen(rrr), E2_INSETS.I(2,2,2,2));
				}
				
				this.add_CloseActions(new ownActionCloseZuordnungsButton(this));
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(400), new MyE2_String("Zuordnung Report zu Gruppe"));
			}
			
			
			private class ownButtonZuordnen extends MyE2_Button {
				private RECORD_REPORT_REITER  rrr = null;
				public ownButtonZuordnen(RECORD_REPORT_REITER  p_rrr ) throws myException {
					super();
					this.rrr=p_rrr;
					this.setStyle(MyE2_Button.StyleTextButton());
					this.set_Text(this.rrr.get_REITERNAME_cUF_NN("<reiterbezeichnung>"));
					this.add_oActionAgent(new ownAction());
				}
				
				private class ownAction extends XX_ActionAgent {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						if (S.isEmpty(bt_Verteile.this.oAuswert.get_id_report()) || S.isEmpty(ownButtonZuordnen.this.rrr.get_ID_REPORT_REITER_cUF())) {
							throw new myException(this,"Error: no ID present ...");
						}
						
						AW2_RECLIST_M2N rlm2n = 
								new AW2_RECLIST_M2N(bt_Verteile.this.oAuswert.get_id_report(), ownButtonZuordnen.this.rrr.get_ID_REPORT_REITER_cUF());
						
						if (rlm2n.get_vKeyValues().size()==0) {
							//sonst war der report schon in der gruppe
							RECORDNEW_M2N rn = new RECORDNEW_M2N();
							rn.set_NEW_VALUE_TABLE_BASE_1(_TAB.report.baseTableName());
							rn.set_NEW_VALUE_TABLE_BASE_2(_TAB.report_reiter.baseTableName());
							rn.set_NEW_VALUE_TABLE_ID_1(bt_Verteile.this.oAuswert.get_id_report());
							rn.set_NEW_VALUE_TABLE_ID_2(ownButtonZuordnen.this.rrr.get_ID_REPORT_REITER_cUF());
							RECORD_M2N rm2n = rn.do_WRITE_NEW_M2N(null);
							if (rm2n != null) {
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Zuordnung erfolgreich ...")));
								ownBasicContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							}
						} else {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zuordnung existiert bereits !")));
						}
						
						
					}
				}
			}
			
			
			private class ownActionCloseZuordnungsButton extends XX_ActionAgentWhenCloseWindow {
				public ownActionCloseZuordnungsButton(E2_BasicModuleContainer container) {
					super(container);
				}

				@Override
				public void executeAgentCode(ExecINFO oExecInfo)	throws myException {
					AW2_RECLIST_M2N rl_help = new AW2_RECLIST_M2N(); 
					int icount =  rl_help.countNumber(bt_Verteile.this.recRep.get_ID_REPORT_lValue(0L).intValue());
					bt_Verteile.this.label_4_anzeige_verteiler.set_number(icount);
					bt_Verteile.this.label_4_anzeige_verteiler.setToolTipText(rl_help.get_tooltip_info_verteilung(bt_Verteile.this.recRep.get_ID_REPORT_lValue(0L).intValue()));
				}
			}
			
		}
	}



	
	private class ownActionWaehleAuswertungUndZeigeStartknopf extends XX_ActionAgent
	{
		private XX_Auswertungen oAuswertung = null;
		
		public ownActionWaehleAuswertungUndZeigeStartknopf(XX_Auswertungen Auswertung) 
		{
			super();
			this.oAuswertung = Auswertung;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			AW2__Tab_Reiter oThis = AW2__Tab_Reiter.this;
			
			oThis.oTF_Hilfen.setText("");
			if (this.oAuswertung.get_cAuswertungsErlaeuterung()!=null)
			{
				oThis.oTF_Hilfen.setText(this.oAuswertung.get_cAuswertungsErlaeuterung().CTrans());
			}
			
			oThis.oGrid4StartButton.removeAll();
			oThis.oGrid4StartButton.add(this.oAuswertung.get_StartButton(),LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 0, 0, 5)));
			
			//2011-08-04: weiteres element (wahlweise) unterhalb des startbuttons anzeigen
			if (this.oAuswertung.get_Zusatzkomponente() != null)
			{
				oThis.oGrid4StartButton.add(this.oAuswertung.get_Zusatzkomponente(),LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(0, 0, 0, 5)));;
			}
			
		}
	}





	public AW2__BasicContainer_RohstoffAuswertungen get_CallingTabContainer() {
		return callingTabContainer;
	}


	public RECORD_REPORT_REITER get_recRReiter() {
		return recRReiter;
	}

	
	private class ownActionStoreSetting extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new AW2_StatusSaver(AW2__Tab_Reiter.this.recRReiter, AW2__Tab_Reiter.this.v_savables).SAVE();

		}
	}
	
	
}
