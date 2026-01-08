/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 10.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import java.math.BigDecimal;

import panter.gmbh.BasicInterfaces.IF_Executer3Parts;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButtons;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.SortButtonForResults;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditKontraktPos;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_KontraktAngebote;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.EK_VK_BEZUG;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSQL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VKopfKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_mandant;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_MaskController;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForMaskShapeSettings;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_McForValueSettingsOnMaskAction;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS3;

/**
 * @author martin
 * @date 10.01.2020
 *
 */
public class B2_SearchKontraktPosV2 extends RB_HL_Search_V2_KontraktAngebote {

	private EnPositionStation posLeftRight=null;;

	private ResultButtons     resultButtonsStore = null;


	/**
	 * @author martin
	 * @date 10.01.2020
	 *
	 * @param vorgangsart
	 * @throws myException
	 */
	public B2_SearchKontraktPosV2(EnPositionStation p_posLeftRight) throws myException {
		super(p_posLeftRight==EnPositionStation.LEFT?ENUM_VORGANGSART.EK_KONTRAKT:ENUM_VORGANGSART.VK_KONTRAKT);
		this.posLeftRight = p_posLeftRight;
		
		//kopie des resultstores fuer die sicherung der gesamten anzahl
		this.resultButtonsStore = new ResultButtons(getSaveSortSettingsKey());
		
		
		this._setAllowEmptySearchfield(true);
		this._setSearchIconAutomatikSearch();

		this._registerAfterValueChangeEvents(()-> {
			
			try {
				
//				new B2_McForValueSettingsOnMaskAction(this)._setArtikelAfterKontraktOrAngebotIsFound(posLeftRight);
//				new B2_McForValueSettingsOnMaskAction(this)._setAdresseAfterKontraktOrAngebot_IF_SINGULAR(posLeftRight);
//				new B2_McForValueSettingsOnMaskAction(this)._setMaskValuesAfterAdressIsSet(posLeftRight) ;
//
//				new B2_McForValueSettingsOnMaskAction(this)._setSorteOnOtherSideAfterAngebotOrKontrakt(posLeftRight,null) ;
//
//				
//				new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(posLeftRight);
//				new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
//				new B2_McForValueSettingsOnMaskAction(this)._clearAngebotIfKontraktIsFull(posLeftRight);
//				
//				new B2_McForValueSettingsOnMaskAction(this)._clearPreisFields(posLeftRight, bibMSG.MV());
//				new B2_McForValueSettingsOnMaskAction(this)._loadPreisAndWaehrungskursFromKontraktOrAngebot(posLeftRight,null);
//				new B2_McForValueSettingsOnMaskAction(this)._berechneFremdPreis(posLeftRight, null);
//				new B2_McForValueSettingsOnMaskAction(this)._setLieferbedingungen(posLeftRight, null);
//				new B2_McForValueSettingsOnMaskAction(this)._setZahlungsbedingungen(posLeftRight, null);
//
//
//				new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();
				
				new B2_McForValueSettingsOnMaskAction(this)._doActionsAfterKontraktPosWasChanged(this,posLeftRight, true);
				
			} catch (myException e) {
				e.printStackTrace();
				
				bibMSG.MV()._add(e.get_ErrorMessage());
			}
		});
		
		this._registerAfterFieldEraseEvents(()-> {
			
			try {
				
				new B2_McForValueSettingsOnMaskAction(this)._setArtikelAfterKontraktOrAngebotIsFound(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setAdresseAfterKontraktOrAngebot_IF_SINGULAR(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setMaskValuesAfterAdressIsSet(posLeftRight) ;

				new B2_McForValueSettingsOnMaskAction(this)._setSorteOnOtherSideAfterAngebotOrKontrakt(posLeftRight,null) ;

				
				new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(posLeftRight);
				new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
				new B2_McForValueSettingsOnMaskAction(this)._clearAngebotIfKontraktIsFull(posLeftRight);
				
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
		

		//suchbedingungen bei leerem feld am kontrakt auf der anderen seite ausrichten
		this._registerBeforeStartSearchEvent(()-> {
			try {
				B2_MaskController	mc = 					new  B2_MaskController(B2_SearchKontraktPosV2.this);
				EnPositionStation 	otherSide = 			posLeftRight==EnPositionStation.LEFT?EnPositionStation.RIGHT:EnPositionStation.LEFT;
				RB_KM  				atomKeyOtherSide = 		posLeftRight==EnPositionStation.LEFT?RecA2.key:RecA1.key;
				RB_KM  				stationKeyOtherSide = 	posLeftRight==EnPositionStation.LEFT?RecS3.key:RecS1.key;
				RB_KM  				atomKeySameSide = 		posLeftRight==EnPositionStation.LEFT?RecA1.key:RecA2.key;
				RB_KM  				stationKeySameSide = 	posLeftRight==EnPositionStation.LEFT?RecS1.key:RecS3.key;
				
//				Long idKontraktOtherSide = (Long)mc.getValueJustInTime(keyOtherSide, BG_ATOM.id_vpos_kon);
//				
//				if (idKontraktOtherSide!=null) {
//					Rec21_VPosKon recKonOtherSide = new Rec21_VPosKon()._fill_id(idKontraktOtherSide);
//					
//					Rec21_mandant recM = (Rec21_mandant)ENUM_MANDANT_SESSION_STORE.REC21_MANDANT.getValueFromSession();
//					int zahl = O.NN(recM.getLongDbValue(MANDANT.anr1_gleichheit_fuhre_stellen),4l).intValue();
//
//					if (this.isSearchFieldEmpty()) {
//						String partOfAnr1 = (recKonOtherSide.getUfs(VPOS_KON.anr1)+"                                                  ").substring(0, zahl).trim();
//						this.getAndStatementCollectorOneTimeCondition().and(new TermSimple("SUBSTR("+VPOS_KON.anr1.tnfn()+",1,"+zahl+")="+bibSQL.includeInTicks(partOfAnr1)));
//					}
//					
//				}
				if (this.isSearchFieldEmpty()) {
					Long idArtikelbezSameSide = (Long)mc.getValueJustInTime(atomKeySameSide, BG_ATOM.id_artikel_bez);
					Long idArtikelbezOtherSide = (Long)mc.getValueJustInTime(atomKeyOtherSide, BG_ATOM.id_artikel_bez);
					Long idAdresseLagerSameSide = (Long)mc.getValueJustInTime(stationKeySameSide, BG_STATION.id_adresse);
					
					if (idArtikelbezSameSide != null) {
						//dann nur kontrakte mit der passenden sorte suchen	
						Rec21_artikel_bez recArtBezsameSide = new Rec21_artikel_bez()._fill_id(idArtikelbezSameSide);
						this.getAndStatementCollectorOneTimeCondition().and(new vgl(VPOS_KON.id_artikel,recArtBezsameSide.getLongDbValue(ARTIKEL_BEZ.id_artikel).toString()));
					} else if (idArtikelbezOtherSide!=null) {
						Rec21_artikel_bez recArtBezOtherSide = new Rec21_artikel_bez()._fill_id(idArtikelbezOtherSide);
						
						Rec21_mandant recM = (Rec21_mandant)ENUM_MANDANT_SESSION_STORE.REC21_MANDANT.getValueFromSession();
						int zahl = O.NN(recM.getLongDbValue(MANDANT.anr1_gleichheit_fuhre_stellen),4l).intValue();
		
						String partOfAnr1 = (recArtBezOtherSide.__get_rec21_artikel().getUfs(ARTIKEL.anr1)+"                                                  ").substring(0, zahl).trim();
						this.getAndStatementCollectorOneTimeCondition().and(new TermSimple("SUBSTR("+VPOS_KON.anr1.tnfn()+",1,"+zahl+")="+bibSQL.includeInTicks(partOfAnr1)));
					}
			
					if (idAdresseLagerSameSide !=null) {
						Rec21_adresse recAdresse = new Rec21_adresse()._fill_id(idAdresseLagerSameSide)._getMainAdresse();
						SEL selSub = new SEL(VKOPF_KON.id_vkopf_kon).FROM(_TAB.vkopf_kon).WHERE(new vgl(VKOPF_KON.id_adresse, recAdresse.getIdLong().toString()));
						this.getAndStatementCollectorOneTimeCondition().and(new TermSimple(VPOS_KON.id_vkopf_kon.tnfn()+" IN ("+selSub.s()+")"));
					}
					
				}
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._addAlarm(S.ms("Fehler bei Sucheinschränkung  <cd5180d4-6229-11ea-bc55-0242ac130003>"));
			}
			
		});
		
		
		this._addListenerbeforeSearchResultGridRenderer(new OwnRenderer());
	}
	
	
	
	
	@Override
	public RB_SearchFieldV2 _setOwnShape() throws myException {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		getTextFieldSearchInput()._w(80);
		this._clear()
			._a(getTextFieldSearchInput(), gl)
			._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
			._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
			._a(new BtEditKontraktPos())
			._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
			._setSize(80,20,20,20,240);

		return this;
	}
	

	@Override
	public void renderResultOnMask(E2_Grid gridResults, Long id) throws myException {
		gridResults._clear()._setSize(65,330)._bo_dd();
		
		if (id!=null) {
			Rec21_VPosKon 	recVposKon = 		(Rec21_VPosKon)new Rec21_VPosKon()._fill_id(id);
			Rec21_VKopfKon 	recVkopf = 			new Rec21_VKopfKon(recVposKon.get_up_Rec21(VKOPF_KON.id_vkopf_kon));
			Rec21_adresse   recAdresse = 		new Rec21_adresse(recVkopf.get_up_Rec21(ADRESSE.id_adresse));
			Rec21 			einheit = 	null;
			try {
				einheit = recVposKon.get_up_Rec21(ARTIKEL.id_artikel).get_up_Rec21(EINHEIT.id_einheit);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			BigDecimal bdMenge = 				O.NN(recVposKon.get_raw_resultValue_bigDecimal(VPOS_KON.anzahl),BigDecimal.ZERO);
			
			BigDecimal bdMengeInFuhre = recVposKon.get_gesamt_fuhre_menge(this.getVorgangsArt()==ENUM_VORGANGSART.EK_KONTRAKT).get_bdWert();
			BigDecimal bdRest = new BigDecimal(0);
			if (bdMengeInFuhre.compareTo(bdMenge)<0) {
				bdRest = bdMenge.subtract(bdMengeInFuhre);
			}
			
			String buchungsnummer = recVposKon.getBuchungsNummer();
			String sorte = " ["+recVposKon.get_ufs_kette(" - " , VPOS_KON.anr1,VPOS_KON.anr2)+"] "+recVposKon.getUfs(VPOS_KON.artbez1);
			String mengeGesamt = new MyBigDecimal(bdMenge).get_FormatedRoundedNumber(1)+" "+(einheit!=null?einheit.getFs(EINHEIT.einheitkurz,""):"");
			String s_restMenge = new MyBigDecimal(bdRest).get_FormatedRoundedNumber(1)+" "+(einheit!=null?einheit.getFs(EINHEIT.einheitkurz,""):"");
			String firma = recAdresse.get__Name1Name2StrassePlzOrt("\n");
			String gueltig = recVposKon.getGueltigkeitsZeitraum();
			
			gridResults	._a(new RB_lab(S.ms("Kontrakt: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
						._a(new RB_lab(buchungsnummer)._fsa(-2)._ttt(firma), new RB_gld()._ins(0,1,1,1));
						;
			gridResults	._a(new RB_lab(S.ms("Gültig: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
						._a(new RB_lab(gueltig)._fsa(-2)._ttt(firma), new RB_gld()._ins(0,1,1,1));
						;
			gridResults	._a(new RB_lab(S.ms("Sorte: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
						._a(new RB_lab(sorte)._fsa(-2)._ttt(firma), new RB_gld()._ins(0,1,1,1));
						;
			gridResults	._a(new RB_lab(S.ms("Mg(offen): "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
						._a(new RB_lab(mengeGesamt+" ("+s_restMenge+")")._fsa(-2)._ttt(firma), new RB_gld()._ins(0,1,1,1));
						;
		}
	}

	
	
	
	private class BtEditKontraktPos extends E2_BtEditKontraktPos {

		public BtEditKontraktPos() throws myException {
			super(getVorgangsArt());
		}


		@Override
		public Long findIdVposKon() throws myException {
			B2_SearchKontraktPosV2	oThis = 	B2_SearchKontraktPosV2.this;
			B2_MaskController  		c = 		new B2_MaskController(oThis);
			
			Long idVposKon = null;
			
			if (getVorgangsArt()==ENUM_VORGANGSART.EK_KONTRAKT) {
				idVposKon = c.getLongLiveVal(RecA1.key,BG_ATOM.id_vpos_kon.fk());
			} else {
				idVposKon = c.getLongLiveVal(RecA2.key,BG_ATOM.id_vpos_kon.fk());
			}
			
			return idVposKon;
		}
		
	}
	
	
	/**
	 * hilfsleite am oberen rand, damit ein schalter zur idenitifikation von zugeordneten kontrakten moeglich ist
	 * @author martin
	 * @date 15.05.2020
	 *
	 */
	private class OwnRenderer implements IF_Executer3Parts<E2_Grid, ResultButtons, VEK<SortButtonForResults>> {
		@Override
		public void execute(E2_Grid grid, ResultButtons o2, VEK<SortButtonForResults> o3) {
			
			//jetzt nachsehen, ob links oder rechts
			Long idKontraktOtherSide = null; 
			try {
				B2_MaskController b2MaskController = new B2_MaskController(B2_SearchKontraktPosV2.this);
				EnPositionStation stationOtherSide = null;
				if (posLeftRight==EnPositionStation.LEFT) {
					idKontraktOtherSide = (Long) b2MaskController.getValueJustInTime(RecA2.key, BG_ATOM.id_vpos_kon);
					stationOtherSide = EnPositionStation.RIGHT;
				} else {
					idKontraktOtherSide = (Long) b2MaskController.getValueJustInTime(RecA1.key, BG_ATOM.id_vpos_kon);
					stationOtherSide = EnPositionStation.LEFT;
				}
				RB_gld layoutForButton = new RB_gld()._span(grid.getSize())._col(new E2_ColorBase())._ins(5, 5, 5, 5);
						
				if (idKontraktOtherSide!=null) {
					if (resultButtonsStore.size()==0) {
						grid._a(new OwnButtonShowOnlyRelatedKontrakts(grid, o2, o3,idKontraktOtherSide,stationOtherSide), layoutForButton);
					} else {
						grid._a(new OwnButtonShowAllFoundKontrakts(grid, o2, o3), layoutForButton);
					}
				}
				
				
			} catch (myException e) {
				e.printStackTrace();
			}
			
		}
	}

	
	private class OwnButtonShowOnlyRelatedKontrakts extends E2_Button {

		public OwnButtonShowOnlyRelatedKontrakts(E2_Grid grid, ResultButtons o2, VEK<SortButtonForResults> o3, Long idKontraktOtherSide, EnPositionStation stationOtherSide) {
			super();
			this._t(S.ms("Markiere alle zugeordneten Kontrakte"))
			._setShapeStyleStandard(new E2_FontBold(8), new E2_ColorDark(), new E2_ColorGray(200))
			._width(400)
			._f(new E2_FontBold(8));
			
			this._aaa(()-> {
				try {
					SEL sel = null;
					if (stationOtherSide==EnPositionStation.LEFT) {
						sel = new SEL(EK_VK_BEZUG.id_vpos_kon_vk).FROM(_TAB.ek_vk_bezug).WHERE(new vgl(EK_VK_BEZUG.id_vpos_kon_ek, idKontraktOtherSide.toString()));
					} else {
						sel = new SEL(EK_VK_BEZUG.id_vpos_kon_ek).FROM(_TAB.ek_vk_bezug).WHERE(new vgl(EK_VK_BEZUG.id_vpos_kon_vk, idKontraktOtherSide.toString()));
					}
					VEK<Object[]> results = bibDB.getResultLines(new SqlStringExtended(sel.s()), false);
					VEK<Long> ids = new VEK<>();
					for (Object[] ar_o: results) {
						ids._a( ((BigDecimal)ar_o[0]).longValue());
					}
					
					resultButtonsStore._clear()._a(getResultButtons());
					getResultButtons()._clear();
					for (ResultButton[] line: resultButtonsStore) {
						if (ids.contains(line[0].getRec21().getId())) {
							getResultButtons()._a(line);
						}
					}
					if (getResultButtons().size()==0) {
						bibMSG.MV()._addAlarm(S.ms("Keine zugeordeten Kontrakt gefunden !"));
						getResultButtons()._clear()._a(resultButtonsStore);
						resultButtonsStore._clear();
					}
					
					renderGrid4ResultPopup();
				} catch (Exception e) {
					e.printStackTrace();
					bibMSG.MV()._addAlarm(e.getLocalizedMessage());

				}
			});
		}
	}

	
	
	private class OwnButtonShowAllFoundKontrakts extends E2_Button {

		public OwnButtonShowAllFoundKontrakts(E2_Grid grid, ResultButtons o2, VEK<SortButtonForResults> o3) {
			super();
			this._t(S.ms("Zeige alle gefundenen zugeordneten Kontrakte"))
				._setShapeStyleStandard(new E2_FontBold(8), new E2_ColorDark(), new E2_ColorGray(200))
				._width(400)
				._f(new E2_FontBold(8));
				
				this._aaa(()-> {
					getResultButtons()._clear()._a(resultButtonsStore);
					resultButtonsStore._clear();
					renderGrid4ResultPopup();
				});

		}
		
	}
	
	
	
	
}
