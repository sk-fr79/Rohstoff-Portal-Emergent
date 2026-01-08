/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung
 * @author martin
 * @date 02.10.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.AbrechnungDienstleistung;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.DLP_JOIN_WARENBEWG;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_SONDER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.__FU_ButtonOpenFuhre;
import rohstoff.Echo2BusinessLogic.DienstleistungsProfile.DL_ENUM_TYP_MENGEN_CALC;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_DlpJoinWarenbewegung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_DlpProfil;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_DlpJoinWarenbewegung;

/**
 * @author martin
 * @date 02.10.2019
 *
 */
public class ADL_gridWithDienstleistungen extends E2_Grid {

	private ADL__ListComponentFuhre listComponent=null;
	
	private OwnBasicContainer 				popup = 	null;
	private ADL_ClearingDlpRelationsFuhre  	clearing = 	null;
	private RecList21_DlpJoinWarenbewegung  rlJoins = null; 
	
	/**
	 * @author martin
	 * @date 02.10.2019
	 *
	 */
	public ADL_gridWithDienstleistungen(ADL__ListComponentFuhre listComponent, ADL_ClearingDlpRelationsFuhre p_clearing) {
		this.listComponent = listComponent;
		this.clearing = this.listComponent.getClearing();
		refreshListe();
	}

	
	private void refreshListe() {
		
		clearing = listComponent.getClearing();
		rlJoins = listComponent.getClearing().getRecListDlpJoinWarenbeweg();
		
		this._clear()._s(7);
		
		
		
		//alle hier aktiven oder zu verwendenden profile durchgehen
		VEK<Long>  allIdProfiles = new VEK<>();
		allIdProfiles	._addIfNotIN(listComponent.getClearing().getDlpProfilesIdsCorrectExisting().getArray())
						._addIfNotIN(listComponent.getClearing().getDlpProfilesIdsCorrectExistingActiveWithUndeletedFuhre().getArray())
						._addIfNotIN(listComponent.getClearing().getDlpProfilesIdsFalseExisting().getArray())
						._addIfNotIN(listComponent.getClearing().getDlpProfilesIdsFalseExistingWithUndeletedFuhre().getArray())
						._addIfNotIN(listComponent.getClearing().getDlpProfilesIdsMissing().getArray())
						;
		
		this	._a(new RB_lab(S.ms("Statusanzeige")), 				new RB_gld()._left_top()._ins(10,2,10,2)._col_back_ddd())
				._a(new RB_lab(S.ms("Profilinfo")), 				new RB_gld()._left_top()._ins(10,2,10,2)._col_back_ddd())
				._a(new RB_lab(S.ms("aktiv")), 						new RB_gld()._left_top()._ins(10,2,10,2)._col_back_ddd())
				._a(new RB_lab(S.ms("Quelle (nach Profil)")), 		new RB_gld()._left_top()._ins(10,2,10,2)._col_back_ddd())
				._a(new RB_lab(S.ms("Ziel (nach Profil)")), 		new RB_gld()._left_top()._ins(10,2,10,2)._col_back_ddd())
				._a(new RB_lab(S.ms("Dienstleistung (Artikel)")), 	new RB_gld()._left_top()._ins(10,2,10,2)._col_back_ddd())
				._a(new RB_lab(S.ms("Aktion")), 					new RB_gld()._left_top()._ins(10,2,10,2)._col_back_ddd())
				;
		
		
		for (Long idDlpProfile: allIdProfiles) {
			int pos=0;
			try {
				Rec21_DlpProfil 				dlpProfil = new Rec21_DlpProfil()._fill_id(idDlpProfile);
				ADL_STATUS_PROFIL 				enProfil = 	listComponent.getClearing().getStatusProfil(dlpProfil.getIdLong());
				Rec21_DlpJoinWarenbewegung  	joinRec = 	listComponent.getClearing().getRecListDlpJoinWarenbeweg().getRec21JoinWarenbeweg(idDlpProfile);
				Boolean aktiv = rlJoins.isActive(idDlpProfile);
				Component  aktivKomp = null;
				
				if (aktiv == null) {
					//das ist ein fehler, da jede relation einen eintrag in der liste haben muss
					aktivKomp = new RB_lab()._t("@ERR");
				} else {
					aktivKomp = new CbAktivesProfil(joinRec)._setSelected(aktiv);
					
					//wenn es eine fuhre gibt, die noch nicht geloescht ist, dann ist der schalter deaktiviert
					Rec21 recFuhreDL=joinRec.get_up_Rec21(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, true);
					
					if (recFuhreDL!=null && recFuhreDL.is_no_db_val(VPOS_TPA_FUHRE.deleted)) {
						aktivKomp.setEnabled(false);
					}
				}
						
				
				//Button 1
				// es kann mehrere Aktionen geben:
				// 1. bei korrektem profil ohne DL-Fuhre: Fuhre anlegen 
				// 2. bei korrektem profil mit DL-Fuhre: Fuhre öffnen
				// 3. bei fehlendem profil ohne DL-Fuhre: profil anlegen
				// 4. bei falschem profil ohne DL-Fuhre: profil loeschen
				// 5. bei falschem profil mit DL-Fuhre: DL-Fuhre öffnen und so aendern, dass sie geloescht werden kann !!!
				
				//Button 2
				// wenn ein profil keine fuhre hat, aber angelegt ist, dann kann es inaktiv geschaltet werden (ausser
				// bei  fehlerhaftem profil ohne DL-Fuhre, dieses kann ge
				E2_Button aktionMoeglich = null;
				
				switch (listComponent.getClearing().getStatusProfil(idDlpProfile)) {
				case FEHLERSTATUS:
					aktionMoeglich = new E2_Button()._standard_text_button();
					aktionMoeglich._t(S.ms("Fehler"))._aaa((e)-> {popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);});
					
					break;
				case PROFIL_FALSCH_OHNE_FUHRE:
					aktionMoeglich = new E2_Button()._standard_text_button();;
					aktionMoeglich._t(S.ms("Löschen der Relation"))._lw()._aaa((e)-> {
						Rec21_DlpJoinWarenbewegung recRelation = listComponent.getClearing().getRecListDlpJoinWarenbeweg().getRec21JoinWarenbeweg(idDlpProfile);
						if (recRelation==null) {
							bibMSG.MV()._addAlarm(S.ms("Systemfehler").ut(": Code:<687a8950-ced7-4e27-b485-2a43face772c>"));
						} else if (recRelation.getLongDbValue(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl)!=null) {
							bibMSG.MV()._addAlarm(S.ms("Es exisistert bereits eine Dienstleistungsfuhre !").ut(": Code:<60d63736-4a50-425d-a4cb-9a85d4ed0524>"));
						} else {
							recRelation.DELETE(true);
							
						}
						listComponent.populate(null);
						refreshListe();
						
					});

					
					
					break;
				
				
				case PROFIL_FALSCH_MIT_FUHRE:
					Rec21 recDl_Fuhre = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(joinRec.getLongDbValue(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl));
					aktionMoeglich = new __FU_ButtonOpenFuhre(recDl_Fuhre)._t(S.ms("Fuhre öffnen"))._standard_text_button();;
					aktionMoeglich._t(S.ms("Fuhre öffnen !"));
					
					break;

				

				case PROFIL_KORREKT:
					//unterscheiden zwischen korrektem profil mit oder ohne fuhre
					if (joinRec.getLongDbValue(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl)==null) {
						aktionMoeglich = new E2_Button()._standard_text_button();;
						aktionMoeglich._t(S.ms("Fuhre erstellen"));
						aktionMoeglich._aaa(()-> {
							try {
								generateDLFuhreFromDLProfile(dlpProfil, joinRec);
								listComponent.populate(null);
								refreshListe();
							} catch (Exception e1) {
								e1.printStackTrace();
								bibMSG.MV()._addAlarm(e1.getLocalizedMessage());
							}
						});
					} else {
						Rec21 recDlFuhre = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(joinRec.getLongDbValue(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl));
						aktionMoeglich = new __FU_ButtonOpenFuhre(recDlFuhre)._t(S.ms("Fuhre öffnen"))._standard_text_button();
					}
					
					break;

				case PROFIL_KORREKT_DEAKTIVIERT:
					aktionMoeglich = new E2_Button()._t("-")._aaa((e)->{bibMSG.MV()._addAlarm(S.ms("Profil ist inaktiv !"));});;
					
					break;
				}
				
				
				
				this._a(enProfil.getLabel(), 															new RB_gld()._ins(10,2,10,2)._left_top()._col(enProfil.getBackColor()));
				pos++;
				this._a(new RB_lab()._t(dlpProfil.getFs(DLP_PROFIL.beschreibung)), 						new RB_gld()._ins(10,2,10,2)._left_top()._col(enProfil.getBackColor()));
				pos++;
				this._a(aktivKomp, 																		new RB_gld()._ins(10,2,10,2)._left_top()._col(enProfil.getBackColor()));
				pos++;
				this._a(new RB_lab()._t(dlpProfil.getMatchingQuelle().get__FullNameAndAdress_mit_id()), new RB_gld()._ins(10,2,10,2)._left_top());
				pos++;
				this._a(new RB_lab()._t(dlpProfil.getMatchingZiel().get__FullNameAndAdress_mit_id()), 	new RB_gld()._ins(10,2,10,2)._left_top());
				pos++;
				this._a(new RB_lab()._t(dlpProfil.getMatchingSorte().__get_anr1_artbez1(true)), 		new RB_gld()._ins(10,2,10,2)._left_top());
				pos++;
				this._a(aktionMoeglich,						 											new RB_gld()._ins(10,2,10,2)._left_top());
				pos++;
					
			} catch (myException e) {
				e.printStackTrace();
				this._a(new RB_lab()._t(e.getOriginalMessage()), new RB_gld()._span(this.getSize()-pos)._col_back_alarm());
			}
		}
		
		
	}
	

	
	private class CbAktivesProfil extends RB_cb {
		private Rec21_DlpJoinWarenbewegung recJoin=null;

		public CbAktivesProfil(Rec21_DlpJoinWarenbewegung joinRec) {
			super();
			recJoin = joinRec;
			
			this._aaa(()-> {
				boolean oldVal = recJoin.is_yes_db_val(DLP_JOIN_WARENBEWG.aktiv);
				recJoin._setNewVal(DLP_JOIN_WARENBEWG.aktiv, oldVal?"N":"Y", bibMSG.MV());
				if (bibMSG.MV().isOK()) {
					recJoin._SAVE(true, bibMSG.MV());
					listComponent.populate(null);
					refreshListe();
				}
			});
			
		}
		
	}
	
	
	
	
	private class OwnBasicContainer extends E2_BasicModuleContainer {
	}
	
	
	public ADL_gridWithDienstleistungen _showInPopup() throws myException {
		popup = new OwnBasicContainer();
		
		popup.add(this, E2_INSETS.I(5));
		popup.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Gefundene Dienstleistungszuordnungen"),1050,200);
		
		
		return this;
	}
	
	
	
	private Rec21 generateDLFuhreFromDLProfile(Rec21 rDlProfil, Rec21 recDlpJoin) throws myException {
		
		Rec21 recFuhreOriginal = clearing.getFuhre();
		Long newFuhreId = Long.parseLong(_TAB.vpos_tpa_fuhre.getNextVal());
		Rec21 recDlFuhre = new Rec21(_TAB.vpos_tpa_fuhre);
		MyE2_MessageVector mv = bibMSG.getNewMV();
		
		
		Rec21_adresse recAdresseLagerStart = new Rec21_adresse()._fill_id(rDlProfil.getLongDbValue(DLP_PROFIL.id_adresse_buchungslager));
		Rec21_adresse recZielAdresse = new Rec21_adresse()._fill_id(rDlProfil.getLongDbValue(DLP_PROFIL.id_adresse_rechnung));
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.l_name1, 		recAdresseLagerStart.getUfs(ADRESSE.name1), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.l_name2, 		recAdresseLagerStart.getUfs(ADRESSE.name2), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.l_name3, 	   	recAdresseLagerStart.getUfs(ADRESSE.name3), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.l_strasse, 	recAdresseLagerStart.getUfs(ADRESSE.strasse), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.l_hausnummer, 	recAdresseLagerStart.getUfs(ADRESSE.hausnummer), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.l_plz, 		recAdresseLagerStart.getUfs(ADRESSE.plz), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.l_ort, 		recAdresseLagerStart.getUfs(ADRESSE.ort), mv);
		
		Rec21 recLand = recAdresseLagerStart.get_up_Rec21(LAND.id_land);
		if (recLand!=null) {
			recDlFuhre._setNewVal(VPOS_TPA_FUHRE.l_laendercode, recLand.getUfs(LAND.laendercode), mv);
		}
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.a_name1, 		recZielAdresse.getUfs(ADRESSE.name1), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.a_name2, 		recZielAdresse.getUfs(ADRESSE.name2), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.a_name3, 	   	recZielAdresse.getUfs(ADRESSE.name3), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.a_strasse, 	recZielAdresse.getUfs(ADRESSE.strasse), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.a_hausnummer, 	recZielAdresse.getUfs(ADRESSE.hausnummer), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.a_plz, 		recZielAdresse.getUfs(ADRESSE.plz), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.a_ort, 		recZielAdresse.getUfs(ADRESSE.ort), mv);
		
		Rec21 recLandAb = recZielAdresse.get_up_Rec21(LAND.id_land);
		if (recLandAb!=null) {
			recDlFuhre._setNewVal(VPOS_TPA_FUHRE.a_laendercode, recLandAb.getUfs(LAND.laendercode), mv);
		}
		
		
		recDlFuhre._setNewVal( VPOS_TPA_FUHRE.id_adresse_start, 		Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()), mv);
		recDlFuhre._setNewVal( VPOS_TPA_FUHRE.id_adresse_lager_start, 	rDlProfil.getLongDbValue(DLP_PROFIL.id_adresse_buchungslager), mv);
		
		
		recDlFuhre._setNewVal( VPOS_TPA_FUHRE.id_adresse_ziel, 			rDlProfil.getLongDbValue(DLP_PROFIL.id_adresse_rechnung), mv);
		recDlFuhre._setNewVal( VPOS_TPA_FUHRE.id_adresse_lager_ziel, 	rDlProfil.getLongDbValue(DLP_PROFIL.id_adresse_rechnung), mv);
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.ablademenge_rechnung, "Y", mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.lademenge_gutschrift, "Y", mv);
		
		Rec21 recArtikelBez = rDlProfil.get_up_Rec21(DLP_PROFIL.id_artikel_bez_dl, ARTIKEL_BEZ.id_artikel_bez, true);
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.id_artikel_bez_ek, recArtikelBez.getId(), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.id_artikel_bez_vk, recArtikelBez.getId(), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.id_artikel, 		recArtikelBez.getLongDbValue(ARTIKEL_BEZ.id_artikel), mv);

		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.anr1_ek, 		recArtikelBez.get_up_Rec21(ARTIKEL.id_artikel).getUfs(ARTIKEL.anr1), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.anr2_ek, 		recArtikelBez.getUfs(ARTIKEL_BEZ.anr2), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.artbez1_ek, 	recArtikelBez.getUfs(ARTIKEL_BEZ.artbez1), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.artbez2_ek, 	recArtikelBez.getUfs(ARTIKEL_BEZ.artbez2), mv);

		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.anr1_vk, 		recArtikelBez.get_up_Rec21(ARTIKEL.id_artikel).getUfs(ARTIKEL.anr1), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.anr2_vk, 		recArtikelBez.getUfs(ARTIKEL_BEZ.anr2), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.artbez1_vk, 	recArtikelBez.getUfs(ARTIKEL_BEZ.artbez1), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.artbez2_vk, 	recArtikelBez.getUfs(ARTIKEL_BEZ.artbez2), mv);
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.tp_verantwortung, 	new String(TAX_CONST.TP_VERANTWORTUNG_MANDANT), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.status_buchung, 	new Long(myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.transportmittel, 	"Dienstleistung", mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.anzahl_container_fp, new Long(0), mv);
		
		//kundenspezifischer artikel
		RecList21 rlArtikelBezLief = recZielAdresse.getKundenSpezifischeArtikelBez();
		
		if (rlArtikelBezLief.size()>0) {
			for (Rec21 rABL: rlArtikelBezLief) {
				if (rABL.getUfs(ARTIKELBEZ_LIEF.id_artikel_bez).equals(""+recArtikelBez.getId())) {
					if (rABL.getUfs(ARTIKELBEZ_LIEF.artbez_typ).equals("VK")) {
						recDlFuhre._setNewVal(VPOS_TPA_FUHRE.artbez2_vk, 	rABL.getUfs(ARTIKELBEZ_LIEF.artbez2_alternativ), mv);
					}
				}
			}
		}

		
		//preise suchen 
		RecList21 rlVkPreise = recZielAdresse.getPreise(new Date(), false);
		VEK<Rec21> gefundeneAngebote = new VEK<>();
		for (Rec21 recAngebotPos: rlVkPreise) {
			
			
			if (recAngebotPos.getLongDbValue(VPOS_STD.id_artikel_bez).longValue()==recArtikelBez.getId()) {
				gefundeneAngebote._a(recAngebotPos);
			}
		}
		
		if (gefundeneAngebote.size()==0) {
			bibMSG.MV()._addWarn(S.ms("Kein passendes Verkaufsangebot gefunden ! Bitte den Preis manuell erfassen !"));
		} else if (gefundeneAngebote.size()>1) {
			bibMSG.MV()._addWarn(S.ms("Mehre passende Verkaufsangebote gefunden ! Bitte den Preis manuell erfassen !"));
		} else {
			recDlFuhre._setNewVal(VPOS_TPA_FUHRE.einzelpreis_vk, gefundeneAngebote.get(0).getBigDecimalDbValue(VPOS_STD.einzelpreis), mv);
			recDlFuhre._setNewVal(VPOS_TPA_FUHRE.id_vpos_std_vk, gefundeneAngebote.get(0).getIdLong(), mv);
		}
		
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.typ_strecke_lager_mixed, 	new Long(2), mv);
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.abgeschlossen, 	"N", mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.deleted, 	"N", mv);
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.datum_abholung, new Date(), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.datum_abholung_ende, new Date(), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.datum_anlieferung, new Date(), mv);
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.datum_anlieferung_ende, new Date(), mv);
		
		DL_ENUM_TYP_MENGEN_CALC  calcTyp = DL_ENUM_TYP_MENGEN_CALC.PRO_LADUNG.getEnum(rDlProfil.getUfs(DLP_PROFIL.typ_mengen_calc));
		
		switch (calcTyp) {
			case PRO_LADUNG:
				recDlFuhre._setNewVal( VPOS_TPA_FUHRE.anteil_planmenge_lief, 	BigDecimal.ONE, mv);
				recDlFuhre._setNewVal( VPOS_TPA_FUHRE.anteil_lademenge_lief, 	BigDecimal.ONE, mv);
				recDlFuhre._setNewVal( VPOS_TPA_FUHRE.anteil_planmenge_abn, 	BigDecimal.ONE, mv);
				recDlFuhre._setNewVal( VPOS_TPA_FUHRE.anteil_ablademenge_abn, 	BigDecimal.ONE, mv);
				
				break;
		
			case PRO_MENGE:
				BigDecimal fuhrenMenge = O.NN(recFuhreOriginal.getRawResultValueAsBigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn), BigDecimal.ZERO);
				//zuerst den umrechnungsfaktor
				BigDecimal bdUmrechnungsFaktor = O.NN(rDlProfil.getRawResultValueAsBigDecimal(DLP_PROFIL.umrech_mge_quelle_ziel), BigDecimal.ONE);
				
				BigDecimal bdAnteilNachProfil = O.NN(rDlProfil.getRawResultValueAsBigDecimal(DLP_PROFIL.anteil_menge), BigDecimal.ONE); 
				
				BigDecimal bdMengeDlFuhre = fuhrenMenge.divide(bdUmrechnungsFaktor, 4, RoundingMode.HALF_UP);
				
				bdMengeDlFuhre = bdMengeDlFuhre.multiply(bdAnteilNachProfil);
				bdMengeDlFuhre = bdMengeDlFuhre.setScale(4,RoundingMode.HALF_UP);
				recDlFuhre._setNewVal( VPOS_TPA_FUHRE.anteil_planmenge_lief, 	bdMengeDlFuhre, mv);
				recDlFuhre._setNewVal( VPOS_TPA_FUHRE.anteil_lademenge_lief, 	bdMengeDlFuhre, mv);
				recDlFuhre._setNewVal( VPOS_TPA_FUHRE.anteil_planmenge_abn, 	bdMengeDlFuhre, mv);
				recDlFuhre._setNewVal( VPOS_TPA_FUHRE.anteil_ablademenge_abn, 	bdMengeDlFuhre, mv);
				
				break;
				
				
			default:
				throw new myException("Unknown-Error: not allowed typ: <e1d7ff66-0a08-11ea-8d71-362b9e155667>");
			}
		
		recDlFuhre._setNewVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, newFuhreId, mv);

		//jetzt nachsehen, ob es eine sonderdefinition gibt mit ohne_kontakt, aber mit abrechnung, wenn ja diese eintrage
		RecList21 rlSonderDef = new RecList21(_TAB.vpos_tpa_fuhre_sonder)._fillWithAll();
		Rec21 recSonder = null;
		for (Rec21 r: rlSonderDef) {
			if (r.is_yes_db_val(VPOS_TPA_FUHRE_SONDER.kein_kontrakt_noetig) && r.is_no_db_val(VPOS_TPA_FUHRE_SONDER.ohne_abrechnung) ) {
				recSonder = r;
				break;
			}
		}
		
		if (recSonder!=null) {
			recDlFuhre._setNewVal( VPOS_TPA_FUHRE.id_vpos_tpa_fuhre_sonder, 	recSonder.getIdLong(), mv);
			recDlFuhre._setNewVal( VPOS_TPA_FUHRE.ohne_abrechnung, 				"N", 		mv);
			recDlFuhre._setNewVal( VPOS_TPA_FUHRE.kein_kontrakt_noetig, 		"Y", 		mv);
		}
		
		
		if (mv.isOK()) {
			recDlFuhre._addRecWatch(clearing.getFuhreWatch());
			recDlpJoin._setNewVal(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl, newFuhreId, mv);

			VEK<Rec21> saveStack = new VEK<>();
			saveStack._a(recDlFuhre);
			saveStack._a(recDlpJoin);
			
			bibDB.saveRecords(saveStack, true, mv);
			
			bibMSG.MV()._add(mv);
		}
		
		return recDlFuhre.getRec21LastRead();
	}
	
}
