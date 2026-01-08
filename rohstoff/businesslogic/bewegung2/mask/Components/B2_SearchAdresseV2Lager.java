/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 07.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Adresse;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VKopfKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
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
 * @date 07.01.2020
 *
 */
public class B2_SearchAdresseV2Lager extends RB_HL_Search_V2_Adresse {

	
	//hier ein paar query fuer das leere suchfeld
	String searchHaeufigste100startAdressen=
			"SELECT ID_ADRESSE FROM ("+
			"		SELECT COUNT(ID_VPOS_TPA_FUHRE), ID_ADRESSE_START FROM V"+bibALL.get_ID_MANDANT()+"_VPOS_TPA_FUHRE "+ 
			"		WHERE EXTRACT(YEAR FROM DATUM_ABHOLUNG)>=(SELECT EXTRACT(YEAR FROM SYSDATE) FROM DUAL)-5 "+ 
			"		GROUP BY ID_ADRESSE_START "+
			"		ORDER BY COUNT(ID_VPOS_TPA_FUHRE) DESC "+
			"		) WHERE ROWNUM<100 ";

		
	String searchLetzte100StartAdressen = 
			" SELECT ID_ADRESSE FROM ( "+
			"   SELECT "+BG_STATION.id_adresse.tnfn()+" FROM "+BG_ATOM.fullTabName() + 
		    "     INNER JOIN "+BG_STATION.fullTabName()+" ON ("+BG_ATOM.id_bg_station_quelle.tnfn()+"="+BG_STATION.id_bg_station.tnfn()+") " + 
		    "     INNER JOIN "+BG_VEKTOR.fullTabName()+" ON ("+BG_VEKTOR.id_bg_vektor.tnfn()+"="+BG_ATOM.id_bg_vektor.tnfn()+") " +
			"       WHERE " +BG_ATOM.pos_in_mask.tnfn()+ "  = 'A1' "+
			"       AND   " +BG_ATOM.id_mandant.tnfn()+ "  = "+bibALL.get_ID_MANDANT()+
			"       AND   " +BG_VEKTOR.en_vektor_quelle.tnfn()+ "  = "+EN_VEKTOR_QUELLE.NATIV.dbVal4SqlTerm()+
			"         ORDER BY "+BG_ATOM.id_bg_atom+" DESC "+
			") WHERE ROWNUM<100 ";
	
	String searchLetzte100ZielAdressen = 
			" SELECT ID_ADRESSE FROM ( "+
			"   SELECT "+BG_STATION.id_adresse.tnfn()+" FROM "+BG_ATOM.fullTabName() + 
		    "     INNER JOIN "+BG_STATION.fullTabName()+" ON ("+BG_ATOM.id_bg_station_ziel.tnfn()+"="+BG_STATION.id_bg_station.tnfn()+") " + 
		    "     INNER JOIN "+BG_VEKTOR.fullTabName()+" ON ("+BG_VEKTOR.id_bg_vektor.tnfn()+"="+BG_ATOM.id_bg_vektor.tnfn()+") " +
			"       WHERE " +BG_ATOM.pos_in_mask.tnfn()+ "  = 'A2' "+
			"       AND   " +BG_ATOM.id_mandant.tnfn()+ "  = "+bibALL.get_ID_MANDANT()+
			"       AND   " +BG_VEKTOR.en_vektor_quelle.tnfn()+ "  = "+EN_VEKTOR_QUELLE.NATIV.dbVal4SqlTerm()+
			"         ORDER BY "+BG_ATOM.id_bg_atom+" DESC "+
			") WHERE ROWNUM<100 ";
	
	

	
	/**
	 * @author martin
	 * @date 07.01.2020
	 *
	 * @throws myException
	 */
	public B2_SearchAdresseV2Lager(EnPositionStation posLeftRight) throws myException {
		
		super();
		
	
		this._setShapeForBewegung2();
		
		this._setSearchIconAutomatikSearch();
		
		this._registerAfterValueChangeEvents(()-> {
			settingsAfter(posLeftRight);
		});
		
		this._registerAfterFieldEraseEvents(()-> {
			settingsAfter(posLeftRight);
		});
		
		this._registerBeforeStartSearchEvent(()-> {
			settingsBeforeSearch(posLeftRight);
		});
		
	}

	private void settingsAfter(EnPositionStation posLeftRight) {
		try {
			new B2_McForValueSettingsOnMaskAction(this)._setMaskValuesAfterAdressIsSet(posLeftRight) ;
			new B2_McForValueSettingsOnMaskAction(this)._setMaskValueAVVCode(posLeftRight);
			new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
			new B2_McForValueSettingsOnMaskAction(this)._clearPreisFields(posLeftRight, bibMSG.MV());
			new B2_McForValueSettingsOnMaskAction(this)._loadPreisAndWaehrungskursFromKontraktOrAngebot(posLeftRight,null);
			new B2_McForValueSettingsOnMaskAction(this)._berechneFremdPreis(posLeftRight, null);
			new B2_McForValueSettingsOnMaskAction(this)._setLieferbedingungen(posLeftRight, null);
			new B2_McForValueSettingsOnMaskAction(this)._setZahlungsbedingungen(posLeftRight, null);
			
			if (posLeftRight==EnPositionStation.LEFT) {
				RB_MaskController con = new RB_MaskController(this);
				String actualTransportArt = (String)con.getValueJustInTime(RecV.key, BG_VEKTOR.en_transport_typ);
				if (S.NN(actualTransportArt).equals(EnTransportTyp.UMBUCHUNG.dbVal())) {
					Long actualAdresseLeft =  (Long)con.getValueJustInTime(RecS1.key, BG_STATION.id_adresse);
					String s_actualAdressLeft = actualAdresseLeft==null?"":actualAdresseLeft.toString();
					con.set_maskVal(RecS3.key, BG_STATION.id_adresse, s_actualAdressLeft , bibMSG.MV());
					new B2_McForValueSettingsOnMaskAction(this)._setMaskValuesAfterAdressIsSet(EnPositionStation.RIGHT) ;
					new B2_McForValueSettingsOnMaskAction(this)._setBesitzer();
				}
			}
			new B2_McForMaskShapeSettings(this)._setAllMaskShapeSettings();
			
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._add(e.get_ErrorMessage());
		}
	}
	
	
	public RB_SearchFieldV2 _setShapeForBewegung2() throws myException {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		
		getTextFieldSearchInput()._w(80);
		this._clear()
			._a(getTextFieldSearchInput(), gl)
			._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
			._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
			._a(getEditAdresse(), gl._c()._ins(0, 2, 2, 0))
			._a(this.getGridForResults(), gl._c()._ins(0,0,0,0))
			._setSize(80,20,20,20,240);

		return this;
	}
	
	@Override
	public void renderResultOnMask(E2_Grid gridForResults, Long id) throws myException {
		gridForResults._clear()._setSize(240)._bo_dd();
		if (id!=null) {
			Rec21_adresse  recAd = new Rec21_adresse()._fill_id(id);
			PAIR<String,String>  result = recAd.getName1OrtInfoZuFirma();
			PAIR<String,String>  resultLang = recAd.getVornameName1Name2OrtInfoZuFirma();
			String toolTips = S.NN(resultLang.getVal1(),"-")+"\n"+S.NN(resultLang.getVal2(),"-");
			gridForResults	._a(new RB_lab(result.getVal1())._i()._fsa(-2)._ttt(toolTips), new RB_gld()._left_top()._ins(2,0,2,0))
							._a(new RB_lab(result.getVal2())._i()._fsa(-2)._ttt(toolTips), new RB_gld()._left_top()._ins(2,0,2,0))
			;
		}
	}
	
	
	@Override
	public RB_SearchFieldV2 _setSearchIconAutomatikSearch() {
		getButtonStartSearch().__setImages(bibE2.getIcon("suche_automatik.png"), bibE2.getIcon("suche_automatik__.png"));
		return this;
	}
	
	@Override
	public RB_SearchFieldV2 _setSearchIconNormalSearch() {
		getButtonStartSearch().__setImages(bibE2.getIcon("suche.png"), bibE2.getIcon("suche__.png"));
		return this;
	}

	
	
	private void settingsBeforeSearch(EnPositionStation posLeftRight) {
		this._setInfoAufSuchherkunft(null);

		try {
			B2_MaskController mc = new B2_MaskController(this);
			

			EnTransportTyp art = EnTransportTyp.WA.getEnum(mc.get_liveVal(RecV.key, BG_VEKTOR.en_transport_typ));

			((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindAllAdresses();
			((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindAllAdresses();

			
			if (art!=null) {
				switch (art) {

				case WA:
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindAllOtherButMandantenAdresses();
					
					break;

				case WA_L:    //verkauf Fremdware, die im Lager bleibt
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					
					break;
					
				case WE:
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindAllOtherButMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					
					break;

				case WE_L:          //einkauf fredmware die bereits im lager ist
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();

					break;
					
					
				case STRECKE:
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindAllOtherButMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindAllOtherButMandantenAdresses();
					
					break;
					
				case LAGER_LAGER:
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();

					break;

				case LEERGUTRANSPORT: 
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindAllAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindAllAdresses();
					
					break;
					
				case TESTSTELLUNG: 
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindAllOtherButMandantenAdresses();
					break;
					
				case FREMDWARENTRANSPORT: 
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindAllAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindAllAdresses();
					break;
					
				case EINBUCHUNG:
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenSonderlager();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					break;
					
				case AUSBUCHUNG:
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenSonderlager();
					break;
					
				case UMBUCHUNG:
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS1.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					((B2_SearchAdresseV2Lager)mc.get_comp(RecS3.key, BG_STATION.id_adresse.fk()))._setFindOnlyMandantenAdresses();
					break;
				default:
					break;
				}
			}
			
			Long idVposKon = new B2_MaskController(this).getLongLiveVal(posLeftRight==EnPositionStation.LEFT?RecA1.key:RecA2.key,BG_ATOM.id_vpos_kon);
			if (idVposKon!=null) {
				Rec21_VPosKon 	recKon =		(Rec21_VPosKon)new Rec21_VPosKon()._fill_id(idVposKon);
				Rec21_VKopfKon 	recKopfKon = 	recKon.getVkopfKon();
				Long 			idAdresse = 	recKopfKon.getLongDbValue(VKOPF_KON.id_adresse);
				
				SEL sel = new SEL(LIEFERADRESSE.id_adresse_liefer).FROM(_TAB.lieferadresse).WHERE(new vgl(LIEFERADRESSE.id_adresse_basis, idAdresse.toString()));
				TermSimple adressTerm = new TermSimple("("+ADRESSE.id_adresse.tnfn()+" IN ("+sel.s()+") OR "+ADRESSE.id_adresse.tnfn()+"="+idAdresse.toString()+")");
				this.getAndStatementCollectorOneTimeCondition().and(adressTerm);
				_setInfoAufSuchherkunft("");
				this._setAllowEmptySearchfield(true);
			} else {
				if (!this.is_allow_empty_searchfield()) {
					// bisher keine erlaubnis fuer eine leer-suche vorhanden war, dann diese mit einer hotline erlauben, wenn das feld leer is
					if (this.isSearchFieldEmpty()) {
						//	dann wird hier bei einer leeren suche nach einer hotline gesucht
						String subQuery = posLeftRight==EnPositionStation.LEFT?searchLetzte100StartAdressen:searchLetzte100ZielAdressen;
						this.getAndStatementCollectorOneTimeCondition().and( new TermSimple("("+ADRESSE.id_adresse.tnfn()+" IN ("+subQuery+"))"));
						this._setAllowEmptySearchfield(true);
						_setInfoAufSuchherkunft("Suchergebnis basiert auf den Adressen der letzten 100 Warenbewegungen");
					}
				}
			}

			
			
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._addAlarm(S.ms("Fehler bei der Suchdefinition: "+e.get_ErrorCode()+" <7bc8e4bc-5fa1-11ea-bc55-0242ac130003>"));
		}
		
		
		
	}
	

}
