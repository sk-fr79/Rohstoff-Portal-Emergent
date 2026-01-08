/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 10.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import java.util.Date;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditAngebot;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_KontraktAngebote;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VKopfStd;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosStd;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_MaskController;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForMaskShapeSettings;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValueSettingsOnMaskAction;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 10.01.2020
 *
 */
public class B2_SearchAngebotsPosV2 extends RB_HL_Search_V2_KontraktAngebote {

	
	/**
	 * @author martin
	 * @date 10.01.2020
	 *
	 * @param vorgangsart
	 * @throws myException
	 */
	public B2_SearchAngebotsPosV2(ENUM_VORGANGSART vorgangsart) throws myException {
		super(vorgangsart);
		
	
		//angebote nur suchen lassen, wenn es mindestens ein planungsdatum oder leistungsdatum gibt
		this.getButtonStartSearch()._addValidator(() -> {
			
			B2_MaskController mc = new B2_MaskController(B2_SearchAngebotsPosV2.this);
			
			Date planungVon = null;
			Date leistungsDatum = null;
			
			
			planungVon = mc.getDateLiveVal(RecV.key, BG_VEKTOR.datum_planung_von.fk());
			if (vorgangsart==ENUM_VORGANGSART.ABNAHMEANGEBOT) {
				leistungsDatum = mc.getDateLiveVal(RecA1.key, BG_ATOM.datum_ausfuehrung.fk());
			} else {
				leistungsDatum = mc.getDateLiveVal(RecA2.key, BG_ATOM.datum_ausfuehrung.fk());
			}
			
			Date datumCheck = leistungsDatum;
			if (datumCheck==null) {
				datumCheck = planungVon;
			}
			
			MyE2_MessageVector mv = bibMSG.getNewMV();
			String meldung = "";
			if (datumCheck==null) {
				// mv._addAlarm(S.ms("Es muss entweder das Leistungsdatum oder mindestens das Plandatum gesetzt sein !"));
				meldung = meldung +"Es muss entweder das Leistungsdatum oder mindestens das Plandatum vorhanden sein !";
			}
			
			if (vorgangsart==ENUM_VORGANGSART.ABNAHMEANGEBOT) {
				if (mc.getStartAdresse()==null) {
					if (S.isFull(meldung)) {
						meldung = meldung +" / ";
					}
					meldung = meldung +"Bitte zuerst die Ladestation / Firma erfassen";
				}
			} else {
				if (mc.getStartAdresse()==null) {
					if (S.isFull(meldung)) {
						meldung = meldung +" / ";
					}
					meldung = meldung +"Bitte zuerst die Abladestation / Firma erfassen";
				}
			}
			
			if (S.isAllFull(meldung)) {
				mv._addAlarm(meldung);
			}
			
			return mv;
			
		});
		
		
		this._registerBeforeStartSearchEvent(()-> {
			
			try {
				B2_MaskController mc = new B2_MaskController(B2_SearchAngebotsPosV2.this);
				
				Date planungVon = null;
				Date leistungsDatum = null;
				
				planungVon = mc.getDateLiveVal(RecV.key, BG_VEKTOR.datum_planung_von.fk());
				
				boolean quelle = (vorgangsart==ENUM_VORGANGSART.ABNAHMEANGEBOT);
				
				if (quelle) {
					leistungsDatum = mc.getDateLiveVal(RecA1.key, BG_ATOM.datum_ausfuehrung.fk());
				} else {
					leistungsDatum = mc.getDateLiveVal(RecA2.key, BG_ATOM.datum_ausfuehrung.fk());
				}
				
				Date datumCheck = leistungsDatum;
				if (datumCheck==null) {
					datumCheck = planungVon;
				}
				
				Long lStation = quelle?mc.getLongLiveVal(RecS1.key,BG_STATION.id_adresse):mc.getLongLiveVal(RecS3.key,BG_STATION.id_adresse);
				Long lArtikelBez = quelle?mc.getLongLiveVal(RecA1.key,BG_ATOM.id_artikel_bez):mc.getLongLiveVal(RecA2.key,BG_ATOM.id_artikel_bez);
				if (lStation!=null) {
					Rec21_adresse ra = new Rec21_adresse()._fill_id(lStation)._getMainAdresse();
					getAndStatementCollectorOneTimeCondition().and(new vgl(ADRESSE.id_adresse,ra.getLongDbValue(ADRESSE.id_adresse).toString()));
				}
				if (lArtikelBez!=null) {
					Rec21_artikel rArt = new Rec21_artikel_bez()._fill_id(lArtikelBez)._getRec21Artikel();
					getAndStatementCollectorOneTimeCondition().and(new vgl(VPOS_STD.id_artikel,rArt.getLongDbValue(ARTIKEL.id_artikel).toString()));
				}
				
				
				if (datumCheck==null) {    //sollte durch den validierer ausgeschlossen sein
					getAndStatementCollectorOneTimeCondition().and(new TermSimple("1=2"));
					bibMSG.MV()._addAlarm("Error: empty date not allowed !");
				} else {
					MyDate md = new MyDate(datumCheck);
					getAndStatementCollectorOneTimeCondition().and(new TermSimple("TO_CHAR("+VPOS_STD_ANGEBOT.gueltig_von.tnfn()+",'YYYY-MM-DD')<="+md.get_cDBFormatErgebnis_4_SQLString()));
					getAndStatementCollectorOneTimeCondition().and(new TermSimple("TO_CHAR("+VPOS_STD_ANGEBOT.gueltig_bis.tnfn()+",'YYYY-MM-DD')>="+md.get_cDBFormatErgebnis_4_SQLString()));
				}
				
			} catch (myException e) {
				
				e.printStackTrace();
			}
		});
		

		
		this._registerAfterValueChangeEvents(()-> {
			
			try {
				EnPositionStation posLeftRight =(vorgangsart==ENUM_VORGANGSART.ABNAHMEANGEBOT?EnPositionStation.LEFT:EnPositionStation.RIGHT);

//				new B2_McForValueSettingsOnMaskAction(this)._setArtikelAfterKontraktOrAngebotIsFound(posLeftRight);
//				new B2_McForValueSettingsOnMaskAction(this)._setAdresseAfterKontraktOrAngebot_IF_SINGULAR(posLeftRight);
//				new B2_McForValueSettingsOnMaskAction(this)._setMaskValuesAfterAdressIsSet(posLeftRight) ;
//
//				new B2_McForValueSettingsOnMaskAction(this)._setSorteOnOtherSideAfterAngebotOrKontrakt(posLeftRight,null) ;
//				
//				new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(posLeftRight);
//				new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
//				new B2_McForValueSettingsOnMaskAction(this)._clearKontraktIfAngebotIsFull(posLeftRight);
//				
//				new B2_McForValueSettingsOnMaskAction(this)._clearPreisFields(posLeftRight, bibMSG.MV());
//				new B2_McForValueSettingsOnMaskAction(this)._loadPreisAndWaehrungskursFromKontraktOrAngebot(posLeftRight,null);
//				new B2_McForValueSettingsOnMaskAction(this)._berechneFremdPreis(posLeftRight, null);
//				new B2_McForValueSettingsOnMaskAction(this)._setLieferbedingungen(posLeftRight, null);
//				new B2_McForValueSettingsOnMaskAction(this)._setZahlungsbedingungen(posLeftRight, null);
//
//
//				new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();

				new B2_McForValueSettingsOnMaskAction(this)._doActionsAfterAngebotPosWasChanged(this,posLeftRight, true);

				
				
			} catch (myException e) {
				e.printStackTrace();
				
				bibMSG.MV()._add(e.get_ErrorMessage());
			}
		});

		this._registerAfterFieldEraseEvents(()-> {
			try {
				EnPositionStation posLeftRight =(vorgangsart==ENUM_VORGANGSART.ABNAHMEANGEBOT?EnPositionStation.LEFT:EnPositionStation.RIGHT);
				
				new B2_McForValueSettingsOnMaskAction(this)._setArtikelAfterKontraktOrAngebotIsFound(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setAdresseAfterKontraktOrAngebot_IF_SINGULAR(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setMaskValuesAfterAdressIsSet(posLeftRight) ;

				new B2_McForValueSettingsOnMaskAction(this)._setSorteOnOtherSideAfterAngebotOrKontrakt(posLeftRight,null) ;
				
				new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
				new B2_McForValueSettingsOnMaskAction(this)._clearKontraktIfAngebotIsFull(posLeftRight);
				
				new B2_McForValueSettingsOnMaskAction(this)._clearPreisFields(posLeftRight, bibMSG.MV());
				new B2_McForValueSettingsOnMaskAction(this)._loadPreisAndWaehrungskursFromKontraktOrAngebot(posLeftRight,null);
				new B2_McForValueSettingsOnMaskAction(this)._berechneFremdPreis(posLeftRight, null);
				new B2_McForValueSettingsOnMaskAction(this)._setLieferbedingungen(posLeftRight, null);
				new B2_McForValueSettingsOnMaskAction(this)._setZahlungsbedingungen(posLeftRight, null);
				
				new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();
				
			} catch (myException e) {
				e.printStackTrace();
				
				bibMSG.MV()._add(e.get_ErrorMessage());
			}
		});
		
		this._setAllowEmptySearchfield(true);
		this._setSearchIconAutomatikSearch();
		
	}

	
	@Override
	public RB_SearchFieldV2 _setOwnShape() throws myException {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		getTextFieldSearchInput()._w(80);
		this._clear()
			._a(getTextFieldSearchInput(), gl)
			._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
			._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
			._a(new BtEditAngebotsPos())
			._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
			._setSize(80,20,20,20,240);

		return this;
	}

	
	@Override
	public void renderResultOnMask(E2_Grid gridResults, Long id) throws myException {
		gridResults._clear()._setSize(65,330)._bo_dd();
			
		if (id!=null) {
			Rec21_VPosStd 	recVposStd = 		(Rec21_VPosStd)new Rec21_VPosStd()._fill_id(id);
			Rec21_VKopfStd 	recVkopf = 			new Rec21_VKopfStd(recVposStd.get_up_Rec21(VKOPF_STD.id_vkopf_std));
			Rec21_adresse   recAdresse = 		new Rec21_adresse(recVkopf.get_up_Rec21(ADRESSE.id_adresse));

			String buchungsnummer = recVposStd.getBuchungsNummer();
			String sorte = " ["+recVposStd.get_ufs_kette(" - " , VPOS_STD.anr1,VPOS_STD.anr2)+"] "+recVposStd.getUfs(VPOS_STD.artbez1);
			String firma = recAdresse.get__Name1Name2StrassePlzOrt("\n");
			String gueltig = recVposStd.getGueltigkeitsZeitraum();
			
			gridResults	._a(new RB_lab(S.ms("Angebot"))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
						._a(new RB_lab(buchungsnummer)._fsa(-2)._ttt(firma), new RB_gld()._ins(0,1,1,1));
						;
			gridResults	._a(new RB_lab(S.ms("Gültig: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
						._a(new RB_lab(gueltig)._fsa(-2)._ttt(firma), new RB_gld()._ins(0,1,1,1));
						;
			gridResults	._a(new RB_lab(S.ms("Sorte: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
						._a(new RB_lab(sorte)._fsa(-2)._ttt(firma), new RB_gld()._ins(0,1,1,1));
						;
		}
	}
	
	
	
	private class BtEditAngebotsPos extends E2_BtEditAngebot {

		public BtEditAngebotsPos() throws myException {
			super(getVorgangsArt());
		}

		@Override
		public Long findIdVposStd() throws myException {
			B2_SearchAngebotsPosV2	oThis = 	B2_SearchAngebotsPosV2.this;
			B2_MaskController  		c = 		new B2_MaskController(oThis);
			
			Long idAngebot = null;
			
			if (getVorgangsArt()==ENUM_VORGANGSART.ABNAHMEANGEBOT) {
				idAngebot = c.getLongLiveVal(RecA1.key,BG_ATOM.id_vpos_std.fk());
			} else {
				idAngebot = c.getLongLiveVal(RecA2.key,BG_ATOM.id_vpos_std.fk());
			}
			
			return idAngebot;
		}
		
	}

}
