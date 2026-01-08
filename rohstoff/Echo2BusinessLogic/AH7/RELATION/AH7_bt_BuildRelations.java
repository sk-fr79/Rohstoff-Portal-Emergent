package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.is_not_null;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;

public class AH7_bt_BuildRelations extends E2_Button {

	
	private E2_NavigationList naviList=null;

	/**
	 * aufbau der moeglichen relationen innerhalb der tabelle 
	 * @throws myException 
	 */
	public AH7_bt_BuildRelations(E2_NavigationList p_naviList) throws myException {
		super();
		this.naviList = p_naviList;
		this._tr("Fuhrenprofile -> AH7-Druck-Steuertabelle")._width(200)._lw()._s_BorderText()._ttt("In Warenbewegungen nach neuen Relation suchen und hier zufügen");
		this._aaa(new ownActionAgent());
		this.add_GlobalValidator(ENUM_VALIDATION.AH7_STEUER_REFRESHLIST.getValidator());
	}

	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			new E2_ServerPushMessageContainer(new Extent(600),new Extent(160),new MyE2_String("Prüfung der Relationen läuft ..."),true,false,true,2000) {

				@Override
				public void Run_Loop() throws myException {
					
					String selFuAH7 = "SELECT DISTINCT "+VPOS_TPA_FUHRE.id_adresse_lager_start.fn()+
														","+VPOS_TPA_FUHRE.id_adresse_lager_ziel.fn()+
														","+VPOS_TPA_FUHRE.id_adresse_start.fn()+
														","+VPOS_TPA_FUHRE.id_adresse_ziel.fn()
								   		+" FROM V"+bibALL.get_ID_MANDANT()+"_FUHREN"
								   		+ " WHERE NVL("+VPOS_TPA_FUHRE.deleted.fn()+",'N')='N'"
								   		+ " AND   NVL("+VPOS_TPA_FUHRE.ist_storniert.fn()+",'N')='N'"
								   		+ " AND   NVL("+VPOS_TPA_FUHRE.print_eu_amtsblatt.fn()+",'N')='Y'"
								   		+"  AND "+VPOS_TPA_FUHRE.id_adresse_lager_start.fn()+" is not null"
								   		+"  AND "+VPOS_TPA_FUHRE.id_adresse_lager_ziel.fn()+" is not null"							   		
								   		;
					
									
					String selAtomAH7 = "SELECT DISTINCT "	+BEWEGUNG_ATOM.id_adresse_wb_start.tnfn()
														+","+BEWEGUNG_ATOM.id_adresse_wb_ziel.tnfn()
														+",NVL("+ADRESSE.id_adresse.t("AST").s()+","+BEWEGUNG_ATOM.id_adresse_wb_start.tnfn()+")"
														+",NVL("+ADRESSE.id_adresse.t("AZ").s()+","+BEWEGUNG_ATOM.id_adresse_wb_ziel.tnfn()+")"
										+" FROM "+BEWEGUNG_ATOM.fullTabName()
										+" LEFT OUTER JOIN "+BEWEGUNG_VEKTOR.fullTabName()	+" ON ("+BEWEGUNG_ATOM.id_bewegung_vektor.tnfn()+"="+BEWEGUNG_VEKTOR.id_bewegung_vektor.tnfn()+")"
										+" LEFT OUTER JOIN "+LIEFERADRESSE.T("LA").s()	+" ON ("+LIEFERADRESSE.id_adresse_liefer.fn("LA")+"="+BEWEGUNG_ATOM.id_adresse_wb_start.tnfn()+")"
										+" LEFT OUTER JOIN "+ADRESSE.T("AST").s()		+" ON ("+LIEFERADRESSE.id_adresse_basis.fn("LA")+"="+ADRESSE.id_adresse.t("AST").s()+")"
										+" LEFT OUTER JOIN "+LIEFERADRESSE.T("LZ").s()	+" ON ("+LIEFERADRESSE.id_adresse_liefer.fn("LZ")+"="+BEWEGUNG_ATOM.id_adresse_wb_ziel.tnfn()+")"
										+" LEFT OUTER JOIN "+ADRESSE.T("AZ").s()		+" ON ("+LIEFERADRESSE.id_adresse_basis.fn("LZ")+"="+ADRESSE.id_adresse.t("AZ").s()+")"
										+" WHERE "+new vgl_YN(BEWEGUNG_ATOM.storniert, false).s()
										+" AND "+new vgl_YN(BEWEGUNG_VEKTOR.print_eu_amtsblatt, true).s()
										+" AND "+new vgl_YN(BEWEGUNG_ATOM.storniert, false).s()
										+" AND "+new is_not_null(BEWEGUNG_ATOM.id_adresse_wb_start).s()
										+" AND "+new is_not_null(BEWEGUNG_ATOM.id_adresse_wb_start).s()
										+" AND "+new is_not_null(BEWEGUNG_ATOM.id_adresse_wb_ziel).s();
					
					
					SEL selVorhanden = new SEL(	AH7_STEUERDATEI.id_adresse_geo_start, AH7_STEUERDATEI.id_adresse_geo_ziel
												,AH7_STEUERDATEI.id_adresse_jur_start, AH7_STEUERDATEI.id_adresse_jur_ziel)
											.FROM(_TAB.ah7_steuerdatei);

					this.showInfo(S.ms("Abfrage der Fuhren mit AH-7-Schalter"));
					String[][] idFuAH7 = bibDB.EinzelAbfrageInArray(selFuAH7, "");
					
					this.showInfo(S.ms("Abfrage der Atome mit AH-7-Schalter"));
					String[][] idAtAH7 = bibDB.EinzelAbfrageInArray(selAtomAH7, "");
					
					
					this.showInfo(S.ms("Abfrage des Bestandes"));
					String[][] idVorhanden = bibDB.EinzelAbfrageInArray(selVorhanden.s(), "");
					
					ownHash hmListAH7 = 	new ownHash();
					ownHash hmVorhanden = 	new ownHash();
					ownHash hmMustBeProfiled = new ownHash();
					
					for (int i=0;i<idFuAH7.length;i++) {
						hmListAH7._add(new AdressRelation(idFuAH7[i]));
					}
					for (int i=0;i<idAtAH7.length;i++) {
						hmListAH7._add(new AdressRelation(idAtAH7[i]));
					}
					
					for (int i=0;i<idVorhanden.length;i++) {
						hmVorhanden._add(new AdressRelation(idVorhanden[i]));
					}			
					

					//schleife 1: schreiben der saetze
					int iSave =  0;
					int iSkip = 0;
					int iFehler = 0;
					int iGesamt = hmListAH7.values().size();
					
					int iCount = 0;
					int iNew = 0;
					
					for (String key: hmListAH7.keySet()) {
						iCount ++;
						AdressRelation p = hmListAH7.get(key);
						
						if (!hmVorhanden.containsKey(key)) {
							MyE2_MessageVector mv = new MyE2_MessageVector();
							new Rec21(_TAB.ah7_steuerdatei)
									._nv(AH7_STEUERDATEI.id_adresse_geo_start, p.id_start_geo, mv)
									._nv(AH7_STEUERDATEI.id_adresse_geo_ziel, p.id_ziel_geo, mv)
									._nv(AH7_STEUERDATEI.id_adresse_jur_start, p.id_start_jur, mv)
									._nv(AH7_STEUERDATEI.id_adresse_jur_ziel, p.id_ziel_jur, mv)
									._nv(AH7_STEUERDATEI.drucke_blatt2, "N", mv)
									._nv(AH7_STEUERDATEI.drucke_blatt3, "N", mv)
									._nv(AH7_STEUERDATEI.status_relation, AH7__ENUM_STATUSRELATION.UNDEF.db_val(), mv)
									._SAVE(true, mv);
							
							
							if (mv.get_bHasAlarms()) {
								iFehler++;
							} else {
								iNew++;
								hmMustBeProfiled._add(p);
							}
						} else {
							iSkip++;
						}
					
						if ((iSave+iSkip)%200==0) {
							
							E2_Grid status = new E2_Grid()._setSize(400,150)._bo_dd();
							status	._a(new RB_lab()._tr("Übertragung der Fuhren-Relationen auf die Anhang7-Steuertabelle"), 	
																											new RB_gld()._left_mid()._ins(2)._col_back_d()._span(2))
									._a(new RB_lab()._tr("Zähler"), 										new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+iCount), 										new RB_gld()._center_mid()._ins(2))
									._a(new RB_lab()._tr("Gesamte AH7-Varianten Warenbewegungen (Quelle)"), new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+iGesamt), 										new RB_gld()._center_mid()._ins(2))
									._a(new RB_lab()._tr("Übersprungen (Relation schon vorhanden)"), 		new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+iSkip),			 								new RB_gld()._center_mid()._ins(2))
									._a(new RB_lab()._tr("Neu geschriebene Relationen"), 					new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+iNew),			 								new RB_gld()._center_mid()._ins(2))
									._a(new RB_lab()._tr("Fehler bei der Bewertung"), 						new RB_gld()._left_mid()._ins(2))
									._a(new RB_lab()._t(""+iFehler),			 							new RB_gld()._center_mid()._ins(2))
									
									;
							
							this.get_oGridBaseForMessages()._clear()._add(status, new RB_gld()._ins(5));
							this.get_oWindowPane().setWidth(new Extent(600));
							this.get_oWindowPane().setHeight(new Extent(300));

						}
					}
					
					
					E2_Grid status = new E2_Grid()._setSize(400,150)._bo_dd();
					status	._a(new RB_lab()._tr("Übertragung der Fuhren-Relationen auf die Anhang7-Steuertabelle"), 	
																									new RB_gld()._left_mid()._ins(2)._col_back_d()._span(2))
							._a(new RB_lab()._tr("Zähler"), 										new RB_gld()._left_mid()._ins(2))
							._a(new RB_lab()._t(""+iCount), 										new RB_gld()._center_mid()._ins(2))
							._a(new RB_lab()._tr("Gesamte AH7-Varianten Warenbewegungen (Quelle)"), new RB_gld()._left_mid()._ins(2))
							._a(new RB_lab()._t(""+iGesamt), 					new RB_gld()._center_mid()._ins(2))
							._a(new RB_lab()._tr("Übersprungen (Relation schon vorhanden)"), 		new RB_gld()._left_mid()._ins(2))
							._a(new RB_lab()._t(""+iSkip),			 								new RB_gld()._center_mid()._ins(2))
							._a(new RB_lab()._tr("Neu geschriebene Relationen"), 					new RB_gld()._left_mid()._ins(2))
							._a(new RB_lab()._t(""+iNew),			 								new RB_gld()._center_mid()._ins(2))
							._a(new RB_lab()._tr("Fehler bei der Bewertung"), 						new RB_gld()._left_mid()._ins(2))
							._a(new RB_lab()._t(""+iFehler),			 							new RB_gld()._center_mid()._ins(2))
							
							;
					
					this.get_oGridBaseForMessages()._clear()._add(status, new RB_gld()._ins(5));
					this.get_oWindowPane().setWidth(new Extent(600));
					this.get_oWindowPane().setHeight(new Extent(300));

					
					AH7_bt_BuildRelations.this.naviList._REBUILD_COMPLETE_LIST("");
				}
			
			
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
					
				}
				
				private void showInfo(MyE2_String info) {
					this.get_oGridBaseForMessages().removeAll();
					this.get_oGridBaseForMessages().add(new RB_lab()._t(info));
				}
				
				
			};
		}
	}
	
	
	private class AdressRelation {
		public String id_start_geo=null;
		public String id_ziel_geo=null;
		public String id_start_jur=null;
		public String id_ziel_jur=null;
		
		/**
		 * @param idStartZiel (array[4]: id_start_geo, id_ziel_geo, id_start_jur, id_ziel_jur)
		 */
		public AdressRelation(String[] idStartZiel) {
			super();
			this.id_start_geo = idStartZiel[0];
			this.id_ziel_geo = 	idStartZiel[1];
			this.id_start_jur = idStartZiel[2];
			this.id_ziel_jur = 	idStartZiel[3];
			
		}
		
		public String getKey() {
			return S.NN(this.id_start_geo)+"|"+S.NN(this.id_ziel_geo)+"|"+S.NN(this.id_start_jur)+"|"+S.NN(this.id_ziel_jur);
		}
	}

	private class ownHash extends HashMap<String, AdressRelation> {
		public ownHash _add(AdressRelation p){
			this.put(p.getKey(), p);
			return this;
		}
	}
	
}
