/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 09.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditArtikel;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Artbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_MaskController;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForMaskShapeSettings;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValueSettingsOnMaskAction;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;

/**
 * @author martin
 * @date 09.01.2020
 *
 */
public class B2_SearchArtbezV2 extends RB_HL_Search_V2_Artbez {
	
	private EnPositionStation m_position = null;
	private E2_Grid           grid4RC = new E2_Grid()._setSize(14);

	
	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @throws myException
	 */
	public B2_SearchArtbezV2(EnPositionStation posLeftRight) throws myException {
		super();
		
		this.m_position = posLeftRight; 
		
		this._setAllowEmptySearchfield(true)._setSearchIconAutomatikSearch();
		
		
		this._setShapeForBewegung2();
		
		this._registerAfterValueChangeEvents(()-> {
			try {
				new B2_McForValueSettingsOnMaskAction(this)._setArtikelDatenAfterLoad(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setSortenAusSortenBez();
				new B2_McForValueSettingsOnMaskAction(this)._setSortenGleichIfNeeded(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(EnPositionStation.LEFT);
				new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(EnPositionStation.RIGHT);
				
				new B2_McForValueSettingsOnMaskAction(this)._clearPreisFields(posLeftRight, bibMSG.MV());
				new B2_McForValueSettingsOnMaskAction(this)._loadPreisAndWaehrungskursFromKontraktOrAngebot(posLeftRight,null);
				new B2_McForValueSettingsOnMaskAction(this)._berechneFremdPreis(posLeftRight, null);

				new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();
				
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._add(e.get_ErrorMessage());
			}
		});
		
		this._registerAfterFieldEraseEvents(()-> {
			try {
				new B2_McForValueSettingsOnMaskAction(this)._setArtikelDatenAfterLoad(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setSortenAusSortenBez();
				new B2_McForValueSettingsOnMaskAction(this)._setSortenGleichIfNeeded(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(EnPositionStation.LEFT);
				new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(EnPositionStation.RIGHT);
				
				new B2_McForValueSettingsOnMaskAction(this)._clearPreisFields(posLeftRight, bibMSG.MV());
				new B2_McForValueSettingsOnMaskAction(this)._loadPreisAndWaehrungskursFromKontraktOrAngebot(posLeftRight,null);
				new B2_McForValueSettingsOnMaskAction(this)._berechneFremdPreis(posLeftRight, null);

				new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();
				
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._add(e.get_ErrorMessage());
			}
		});
		
		this.getButtonStartSearch()._addValidator(()-> {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			Rec21_adresse adresse = (this.m_position==EnPositionStation.LEFT?new B2_MaskController(this).getStartAdresse():new B2_MaskController(this).getZielAdresse());
			if (adresse==null) {
				mv._addAlarm(S.ms("Bitte laden Sie zuerst eine Adresse !"));
			}
			return mv;
		});
		
		
		this._registerBeforeStartSearchEvent(()-> {
			this.setSearchBedingung();
		});
		
	}
	
	
	
	private void setSearchBedingung() {
		try {
			//abhaengig vom momentanen maskenzustand definierte zusatzbedingungen setzen
			B2_MaskController mc = new B2_MaskController(this);
			
			Rec21_adresse adresse = mc.getAdresse(m_position);
			Rec21_VPosKon rKon = (this.m_position==EnPositionStation.LEFT?mc.getKontraktPosStart():mc.getKontraktPosZiel());
			Rec21         rAng = (this.m_position==EnPositionStation.LEFT?mc.getAngebotPosStart():mc.getAngebotPosZiel());
			
			Long 				idArtikelBezOtherSide = (this.m_position==EnPositionStation.RIGHT?(Long)mc.getValueJustInTime(RecA1.key, BG_ATOM.id_artikel_bez):(Long)mc.getValueJustInTime(RecA2.key, BG_ATOM.id_artikel_bez));
			Rec21_artikel_bez  	rArtikelBezOtherSide = null;
			if (idArtikelBezOtherSide != null) {
				rArtikelBezOtherSide = new Rec21_artikel_bez()._fill_id(idArtikelBezOtherSide);
			}
			
			this.getAndStatementCollectorScreenSetters().clear();

			boolean allAreAllowed = true;
			
			//linksseitig werden bei ungebundenen suchen die artikel auf die kundenspezifischen sorten gesetzt 
			if (adresse!=null && m_position==EnPositionStation.LEFT && !adresse.isAdressOfMandant() && rKon==null && rAng==null) {
				SEL sel = new SEL(ARTIKELBEZ_LIEF.id_artikel_bez)
								.FROM(_TAB.artikelbez_lief)
								.WHERE(new vgl(ARTIKELBEZ_LIEF.artbez_typ,"EK"))
								.AND(new vgl(ARTIKELBEZ_LIEF.id_adresse,adresse._getMainAdresse().getIdLong().toString()));
				
				this.getAndStatementCollectorScreenSetters().and(new TermSimple(ARTIKEL_BEZ.id_artikel_bez.tnfn()+" IN ("+sel.s()+")"));
				allAreAllowed = false;
			}
			
			if (rKon!=null && rKon.getLongDbValue(VPOS_KON.id_artikel_bez)!=null) {
				this.getAndStatementCollectorScreenSetters().and(new TermSimple(ARTIKEL_BEZ.id_artikel_bez.tnfn()+" = "+rKon.getLongDbValue(VPOS_KON.id_artikel_bez).toString()));
				allAreAllowed = false;
			} else if (rAng!=null && rAng.getLongDbValue(VPOS_KON.id_artikel_bez)!=null) {
				this.getAndStatementCollectorScreenSetters().and(new TermSimple(ARTIKEL_BEZ.id_artikel_bez.tnfn()+" = "+rAng.getLongDbValue(VPOS_STD.id_artikel_bez).toString()));
				allAreAllowed = false;
			} else if (rArtikelBezOtherSide!=null && isSearchFieldEmpty()) {
				if (mc.getEnTransportTyp()!=null && mc.getEnTransportTyp()==EnTransportTyp.UMBUCHUNG) {
					allAreAllowed=true;
				} else {
					allAreAllowed = false;
					this.getAndStatementCollectorScreenSetters().and(new TermSimple(ARTIKEL_BEZ.id_artikel.tnfn()+" = "+rArtikelBezOtherSide.getLongDbValue(ARTIKEL_BEZ.id_artikel)));
				}
			}
			
			//falls alle erlaubt sind, dann den artikelgruppen-selector aktivieren
			if (allAreAllowed) {
				this._activatePopupPreSelectArtikel(true);
			} else {
				this._activatePopupPreSelectArtikel(false);
			}
			
			
		} catch (myException e) {

			e.printStackTrace();
			
			bibMSG.MV()._addAlarm("Error setting special conditions ! "+e.getOriginalMessage()+ " <b5812a68-32e0-11ea-850d-2e728ce88125 >");
		}
		
		
		
		
	}
	

	
	
	
	public RB_SearchFieldV2 _setShapeForBewegung2() throws myException {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		
		getTextFieldSearchInput()._w(80);
		this._clear()
			._a(getTextFieldSearchInput(), gl)
			._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
			._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
			._a(new BtEditArtikel(), gl._c()._ins(0, 2, 2, 0))
			._a(this.grid4RC, gl._c()._ins(0,0,0,0))
			._a(this.getGridForResults(), gl._c()._ins(4,0,0,0)._left_mid())
			._setSize(80,20,20,20,18,231);

		return this;
	}
	
	@Override
	public void renderResultOnMask(E2_Grid gridForResults, Long id) throws myException {
		gridForResults._clear()._setSize(40,25,231-40-25-10)._bo_dd();
		
		if (id!=null) {
			Rec21 recArtBez = new Rec21(_TAB.artikel_bez)._fill_id(id);
			Rec21 recArtikel = recArtBez.get_up_Rec21(ARTIKEL.id_artikel);
			
			String anr1 = 		recArtikel.getFs(ARTIKEL.anr1, "<anr1>");
			String anr2 = 		recArtBez.getFs(ARTIKEL_BEZ.anr2, "<anr2>");
			String artbez1 = 	recArtBez.getFs(ARTIKEL_BEZ.artbez1, "<artbez1>");
			
			gridForResults._a(		new RB_lab(anr1)._fsa(-2))
								._a(new RB_lab(anr2)._fsa(-2))
								._a(new RB_lab(artbez1)._lw()._fsa(-2));
			
		}
	}

	
	
	
	
	
	private class BtEditArtikel extends E2_BtEditArtikel {

		public BtEditArtikel() throws myException {
			super();
		}

		@Override
		public Long findIdArtikel() throws myException {
			B2_SearchArtbezV2 oThis = B2_SearchArtbezV2.this;
			B2_MaskController  c = new B2_MaskController(oThis);
			
			Long idArtikelBez = null;
			
			if (m_position==EnPositionStation.LEFT) {
				idArtikelBez = c.getLongLiveVal(RecA1.key,BG_ATOM.id_artikel_bez.fk());
			} else {
				idArtikelBez = c.getLongLiveVal(RecA2.key,BG_ATOM.id_artikel_bez.fk());
			}

			if (idArtikelBez!=null) {
				Rec21 rAB = new Rec21(_TAB.artikel_bez)._fill_id(idArtikelBez);
				return rAB.getLongDbValue(ARTIKEL_BEZ.id_artikel);
			}
			
			return null;
		}
		
	}

	public EnPositionStation getPosition() {
		return m_position;
	}

	public B2_SearchArtbezV2 _setRC(boolean isRC) {
		this.grid4RC._clear();
		this.grid4RC._setSize(20)._setRowH(0, 20);
		if (isRC) {
			Color col = Color.RED;
			try {
				col = new E2_ColorMaskHighlight();
			} catch (myException e) {
				e.printStackTrace();
			}
			this.grid4RC._a(new RB_lab()._t("RC")._b()._fsa(-2), new RB_gld()._ins(0)._col_back(col)._center_mid());
		}
		return this;
	}
	
}
