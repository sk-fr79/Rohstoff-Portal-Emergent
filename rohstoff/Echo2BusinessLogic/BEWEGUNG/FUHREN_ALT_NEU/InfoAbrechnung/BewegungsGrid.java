/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.InfoAbrechnung
 * @author martin
 * @date 03.12.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.InfoAbrechnung;

import java.text.SimpleDateFormat;
import java.util.Date;

import nextapp.echo2.app.Color;
import panter.gmbh.BasicInterfaces.Service.PdServiceRechneZahlungsZiel;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.indep.MyGregorianCalendar;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposRg;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_ZahlungsBedingung;

/**
 * @author martin
 * @date 03.12.2019
 * definiert ein grid fuer die statusanzeige eines bewegungs-satz-teils
 */
public class BewegungsGrid extends E2_Grid {

	
	public static int spaltenVoll = 80;
	public static int spalteHalb = 40;
	//public static int spalteViertel = 35;
	
	public static int gridHeight = 40;
	//public static int gridHeightInt = 18;

	/**
	 * @author martin
	 * @date 03.12.2019
	 *
	 */
	public BewegungsGrid() {
		this._h(gridHeight);
	}

	
	/**
	 * fuer lager-seiten
	 * @author martin
	 * @date 03.12.2019
	 *
	 * @return
	 */
	public BewegungsGrid _setShapeHome() {
		this._setSize(spalteHalb);
		this._a(new RB_lab()._icon("firma.png")._ttt(S.ms("Lager")), new RB_gld()._center_mid()._colL()._span_r(2));
		this._bo_ddd();
		return this;
	}
	
	
	/**
	 * fuer lager-seiten
	 * @author martin
	 * @date 03.12.2019
	 *
	 * @return
	 */
	public BewegungsGrid _setShapeLeer() {
		this._setSize(spalteHalb);
		this._a(new RB_lab()._t("0")._fo_italic()._fsa(-2)._ttt(S.ms("Position hat den Wert 0")), new RB_gld()._center_mid()._colL()._span_r(2));
		this._bo_ddd();

		return this;
	}
	
	/**
	 * fuer lager-seiten
	 * @author martin
	 * @date 03.12.2019
	 *
	 * @return
	 */
	public BewegungsGrid _setShapeVerbindlichkeit() {
		this._setSize(spalteHalb);
		this._a(new RB_lab()._t("<VB>")._fo_italic()._fsa(-2)._ttt(S.ms("Position ist eine Verbindlichkeit")), new RB_gld()._center_mid()._colL()._span_r(2));
		this._bo_ddd();
	
		return this;
	}

	public BewegungsGrid _setShapeVerlaengerteFaktFrist() {
		this._setSize(spalteHalb);
		this._a(new RB_lab()._t("<VFF>")._fo_italic()._fo_bold()._fsa(-2)._ttt(S.ms("Für diese Position gilt eine verlängerte Fakturierungsfrist ! Bitte dort verifizieren !")), new RB_gld()._center_mid()._col_back_help()._span_r(2));
		this._bo_b();
	
		return this;
	}
	
	public BewegungsGrid _setShapeForderung(Date leistungsDatum, Rec21_ZahlungsBedingung zahlungsBedingung, Rec21_VposRg  rgPos, boolean ek)  throws Exception {
		this._setSize(spalteHalb);
		this._bo_ddd();

		PAIR<RB_lab, RB_gld> labelDefFuhre = new PAIR<RB_lab, RB_gld>();
		PAIR<RB_lab, RB_gld> labelDefRgPos = new PAIR<RB_lab, RB_gld>();
		
		if (rgPos==null) {
			//dann ist der status der fuhre und der position gleich
			if (leistungsDatum==null || zahlungsBedingung==null) {
				labelDefFuhre	._setVal1(new RB_lab()._t("??")._fsa(-2)._ttt(S.ms(ek?"Bewertung Fuhre: Entweder kein Ladedatum oder keine EK-Zahlungsbedingung !": "Bewertung Fuhre: Entweder kein Abladedatum oder keine VK-Zahlungsbedingung !")))
								._setVal2(new RB_gld()._col_back_help()._center_mid());
				labelDefRgPos	._setVal1(new RB_lab()._t("??")._fsa(-2)._ttt(S.ms(ek?"Bewertung RG-Position: Entweder kein Ladedatum oder keine EK-Zahlungsbedingung !": "Bewertung RG-Position: Entweder kein Abladedatum oder keine VK-Zahlungsbedingung !")))
								._setVal2(new RB_gld()._col_back_help()._center_mid());
				
			} else {
				//falls noch keine position vorhanden ist, dann wird der status der position und der fuhre gleich behandelt
				labelDefFuhre = getLabelDef(zahlungsBedingung,leistungsDatum);
				labelDefRgPos = getLabelDef(zahlungsBedingung,leistungsDatum);
			}
			
		} else {
			
			labelDefFuhre ._setVal1(new RB_lab()._t("OK")._ttt(S.ms("Es existiert eine RG-Position zur Hauptfuhre-"+(ek ?"Ladeseite":"Abladeseite")))._fsa(-2))
						  ._setVal2(new RB_gld()._col_back_green()._center_mid());
			
			if (rgPos.istAbgerechnet()) {
				labelDefRgPos ._setVal1(new RB_lab()._t("OK")._ttt(S.ms("Es existiert eine abgerechnete RG-Position zur Hauptfuhre-"+(ek ?"Ladeseite":"Abladeseite")))._fsa(-2))
							  ._setVal2(new RB_gld()._col_back_green()._center_mid());
			} else {
			
				Date leistungsDatumRg = rgPos.getDateDbValue(VPOS_RG.ausfuehrungsdatum);
				Rec21 zahlungsBedingungRg = rgPos.get_up_Rec21(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen);
				
				if (leistungsDatumRg==null || zahlungsBedingungRg==null) {
					labelDefRgPos 	._setVal1(new RB_lab()._t("??")._fsa(-2)._ttt(S.ms("RG-Position besitzt entweder kein Ladedatum oder keine EK-Zahlungsbedingung !")))
									._setVal2(new RB_gld()._col_back_help()._center_mid());
				} else {
					labelDefRgPos = getLabelDef(new Rec21_ZahlungsBedingung(zahlungsBedingungRg),leistungsDatumRg);
				}
			}
		}

		this._a(labelDefFuhre.getVal1(),labelDefFuhre.getVal2()._ins(2))
			._a(labelDefRgPos.getVal1(),labelDefRgPos.getVal2()._ins(2))
			;

		
		return this;
	}
	
	
	
	private  PAIR<RB_lab, RB_gld>  getLabelDef(Rec21_ZahlungsBedingung zahlungsBedingung, Date leistungsDatum) throws Exception {
		//zahlungsziel ausrechnen
		Date zahlungsZiel = new PdServiceRechneZahlungsZiel().getZahlungsZiel(leistungsDatum, leistungsDatum, zahlungsBedingung);
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		//die differenz zwischen leistungdatum (Rechnungsdatum) und aktuellem datum rechnen
		Long restTageBisAbrechnung = myDateHelper.get_DayDifference_Date2_MINUS_Date1(new MyGregorianCalendar(new Date()), new MyGregorianCalendar(zahlungsZiel));
		
		Long lYellowBorder = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_GELB.getCheckedValue());
		Long lRedBorder =    Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_ROT.getCheckedValue());

		Color col = Color.GREEN;
		if (restTageBisAbrechnung.longValue()>lRedBorder.longValue() &&  restTageBisAbrechnung.longValue()<=lYellowBorder.longValue()) {
			col = Color.YELLOW;
		} else if (restTageBisAbrechnung.longValue()<=lRedBorder.longValue()) {
			col = Color.RED;
		}
		
		if (restTageBisAbrechnung.longValue()>=0) {
			return new PAIR<RB_lab, RB_gld>()._setVal1(new RB_lab()._t(""+restTageBisAbrechnung)._fsa(-2)
					._ttt(		S.ms("Errechnetes Zahlungsziel: ")
							.ut(""+df.format(zahlungsZiel)).ut("\n")
							.t("Resttage bis zum Zahlungsziel: ").ut(""+restTageBisAbrechnung.toString())
							))
					._setVal2(new RB_gld()._col(col)._center_mid());
			
		} else {
			
			return new PAIR<RB_lab, RB_gld>()._setVal1(new RB_lab()._t(""+Math.abs(restTageBisAbrechnung)+"*")._fsa(-2)
					._ttt(		S.ms("Errechnetes Zahlungsziel: ")
							.ut(""+df.format(zahlungsZiel)).ut("\n")
							.t("Zahlungsziel überschritten um: ").ut(""+Math.abs(restTageBisAbrechnung)).t(" Tage")
							))
					._setVal2(new RB_gld()._col(col)._center_mid());
			
		}
	}

}
