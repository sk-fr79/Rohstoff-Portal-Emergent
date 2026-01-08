/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 24.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.TimeTools;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Const;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Lib;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_idAdresseUndRecVposRg;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_IdKvPosUndFristTage;
import rohstoff.Echo2BusinessLogic._4_ALL.BL_CalcZahlungsDatum;

/**
 * @author martin
 * @date 24.06.2019
 *
 */
public class Rec21_VkopfRG extends Rec21 {

	public Rec21_VkopfRG() throws myException {
		super(_TAB.vkopf_rg);
	}

	
	public Rec21_VkopfRG(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab()!=_TAB.vkopf_rg) {
			throw new myException("Error: <72e53d18-9289-11e9-bc42-5345af7764f64> only _TAB.vkopf_rg allwoed !");
		}
	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 25.06.2019
	 *
	 * @return fw-betrag berechnet aus mengen und preisen, positiv bei forderungen negativ bei verbindlichkeiten
	 * @throws myException
	 */
	public PAIR<BigDecimal,BigDecimal> getBetragEigenwaehrungFremdwaehrung() throws myException {
		MathContext math = new MathContext(22, RoundingMode.HALF_UP);

		BigDecimal  bdSummeForderungEigenWaehrung = new BigDecimal(0,math);
		BigDecimal  bdSummeForderungFremdWaehrung = new BigDecimal(0,math);
		
		RecList21 positions = this.get_down_reclist21(VPOS_RG.id_vkopf_rg);

		if (positions.size()>0) {
			for (Rec21 r: positions) {
				PAIR<BigDecimal,BigDecimal> preise = new Rec21_VposRg(r).getGesamtBetragEigenwaehrungFremdWaehrung();	
				bdSummeForderungEigenWaehrung=bdSummeForderungEigenWaehrung.add(preise.getVal1());
				bdSummeForderungFremdWaehrung=bdSummeForderungFremdWaehrung.add(preise.getVal2());
			}
		}
		return new PAIR<BigDecimal,BigDecimal>()._setVal1(bdSummeForderungEigenWaehrung)._setVal2(bdSummeForderungFremdWaehrung);
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 24.06.2019
	 *
	 * @return s true, wenn der ganze beleg (egal ob rechnung oder gutschrift) eine forderung ist
	 * @throws myException
	 */
	public boolean isForderung() throws myException {
		boolean ret = false;

		BigDecimal  bdSummeForderung = this.getBetragEigenwaehrungFremdwaehrung().getVal1();
		
	//	DEBUG._print("Summe: "+bdSummeForderung);
		
		if (bdSummeForderung.compareTo(BigDecimal.ZERO)>0) {
			ret = true;
		}
		
		return ret;
	}
	
	
	
	

	/**
	 * durchlaufen aller teilnehmenden rechnungspositionen und berechnung der jeweiligen zahlungsziele.
	 * zurueckgegeben wird das groesste zahlungsziel
	 * Beim Berechnen des zahlungsziels wird auch der schalter in den Zahlungsbedingungen "zahldat_calc_rechdat" beruecksichtigt
	 * (dann wird das zahlungsziel nicht nach dem jeweiligen leistungsdatum, sondern nach dem rechnungsdatum berechnet)
	 * @author martin
	 * @date 24.06.2019
	 *
	 * @param geplantesRechnungsDatum
	 * @return s groesstes zahlungsziel aller positionen oder null, wenn keine position vorhanden
	 * @throws myException
	 */
	public Date getZahlungsZiel(Date geplantesRechnungsDatum) throws myException {
		
		
		Date zahlungsZiel = null;
		if (geplantesRechnungsDatum==null) {
			geplantesRechnungsDatum=new Date();	  //dann das tagesdatum
		}
		
		
		zahlungsZiel = null;
		SimpleDateFormat 	formatMensch = 			new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat 	formatMaschine = 		new SimpleDateFormat("yyyy-MM-dd");
		VEK<String>         sammlerDates = 	new VEK<>();  				
		
		RecList21 rlPos = this.get_down_reclist21(VPOS_RG.id_vkopf_rg) ;
		
		for (Rec21 pos: rlPos) {
			
			if (pos.is_no_db_val(VPOS_RG.deleted)) {
				
				Date leistungsDatum = (Date)pos.getRawVal(VPOS_RG.ausfuehrungsdatum);
				if (pos.getRawVal(VPOS_RG.ausfuehrungsdatum)==null) {
					throw new myException("Error: Code <2d3cdf9a-9690-11e9-bc42-526af7764f64>: Position without AUSFUEHRUNGSDATUM not allowed !");
				}
				
				Rec21 recZahlungsbedingung = pos.get_up_Rec21(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen);
				if (recZahlungsbedingung==null) {
					throw new myException("Error: <ad5be90c-968e-11e9-bc42-526af7764f64>: Position without Zahlungsbedingung not allowed");
				}
				
				if (recZahlungsbedingung.is_yes_db_val(ZAHLUNGSBEDINGUNGEN.zahldat_calc_rechdat)) {
					Date zahlDat = new BL_CalcZahlungsDatum(formatMensch.format(geplantesRechnungsDatum), 
															recZahlungsbedingung.getLongDbValue(ZAHLUNGSBEDINGUNGEN.zahltage), 
															recZahlungsbedingung.getLongDbValue(ZAHLUNGSBEDINGUNGEN.fixmonat),
															recZahlungsbedingung.getLongDbValue(ZAHLUNGSBEDINGUNGEN.fixtag)
															).getZahlungsdatum();
					sammlerDates._a(formatMaschine.format(zahlDat))	;
				} else {
					Date zahlDat = new BL_CalcZahlungsDatum(formatMensch.format(leistungsDatum), 
							recZahlungsbedingung.getLongDbValue(ZAHLUNGSBEDINGUNGEN.zahltage), 
							recZahlungsbedingung.getLongDbValue(ZAHLUNGSBEDINGUNGEN.fixmonat),
							recZahlungsbedingung.getLongDbValue(ZAHLUNGSBEDINGUNGEN.fixtag)
							).getZahlungsdatum();
					
					sammlerDates._a(formatMaschine.format(zahlDat))	;
				}
			}
		}
		
		if (sammlerDates.size()>0) {
		
			//jetzt den vektor sortieren
			sammlerDates.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			
			String c_zahlungsZiel = sammlerDates.get(sammlerDates.size()-1);
			
			try {
				zahlungsZiel  = formatMaschine.parse(c_zahlungsZiel);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new myException("Error: <b6e08bd8-9691-11e9-8550-526af7764f64>: Cannot interpret found date !");
			}
			
		}
		return zahlungsZiel;
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 24.06.2019
	 *
	 * @returns groesstes leistungsdatum aller positionen oder null, wenn keine position vorhanden
	 * @throws myException
	 */
	public Date getBiggestLeitungsDatum() throws myException {
		Date leistungsDatumOfBeleg = null;
		SimpleDateFormat 			format = 		new SimpleDateFormat("yyyy-MM-dd");
		VEK<String>         		sammlerDates = 	new VEK<>();  				
		
		RecList21 rlPos = this.get_down_reclist21(VPOS_RG.id_vkopf_rg) ;
		
		for (Rec21 pos: rlPos) {
			
			if (pos.is_no_db_val(VPOS_RG.deleted)) {
				
				Date leistungsDatum = (Date)pos.getRawVal(VPOS_RG.ausfuehrungsdatum);
				if (pos.getRawVal(VPOS_RG.ausfuehrungsdatum)==null) {
					throw new myException("Error: Code <2d3cdf9a-9690-11e9-xxxx-526af7764f64>: Position without AUSFUEHRUNGSDATUM not allowed !");
				}
				
				sammlerDates._a(format.format(leistungsDatum))	;
			}
		}
		
		if (sammlerDates.size()>0) {
		
			//jetzt den vektor sortieren
			sammlerDates.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			
			String c_leistungsDatumOfBeleg = sammlerDates.get(sammlerDates.size()-1);
			
			try {
				leistungsDatumOfBeleg  = format.parse(c_leistungsDatumOfBeleg);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new myException("Error: <b6e08bd8-9691-11e9-8550-526af7764f64>: Cannot interpret found date !");
			}
		}
		
		return leistungsDatumOfBeleg;
	}


	
	
	
	/**
	 * Abfrage alle Kreditversicherungs-position und deren fakturierungsfristen aus dem zugehoerenden Kopf (mit distinct keine doppelten)
	 * um festzustelle, ob eine doppelung innerhalb einer position oder eines belegs vorliegt. doppelung heisst auch:
	 * eine position mit und eine ohne fakturierungsfrist 
	 * 
	 * 
	 * @author martin
	 * @date 27.06.2019
	 * 
	 * @return VEK<KV_IdKvPosUndFristTage> is vector of pairs with id_kredivers_pos, anzahl Tage
	 * Bei einem Beleg darf nur ein einziger kreditversicherungsvertrag mit fakturierungsfrist vorkommen, keine mehrfachen und
	 * auch keine mischformen aus position mit vertrag und ohne vertrag 
	 * @throws myException
	 */
	public  VEK<KV_IdKvPosUndFristTage> getAllFaktFristIdsAndVals() throws myException {
		VEK<KV_idAdresseUndRecVposRg> v_idAdresseUndRecVposRg = new VEK<KV_idAdresseUndRecVposRg>();
		
		RecList21 positions = this.get_down_reclist21(VPOS_RG.id_vkopf_rg);
		
		for (Rec21 p: positions) {
			if (p.is_no_db_val(VPOS_RG.deleted)) {
				v_idAdresseUndRecVposRg.add(new KV_idAdresseUndRecVposRg(this.getLongDbValue(VKOPF_RG.id_adresse),new Rec21_VposRg(p)));
			}
		}
		
		return KV_Lib.getAllFaktFristIdsAndVals(v_idAdresseUndRecVposRg);
	}

	
	
	
	
	public E2_Grid getStatusFakturierungsFrist() throws Exception {
		
		
		//jetzt nachsehen, ob der kunde ueberhaupt eine faktfrist hat
		if (KV_Lib.getCountKreditVersicherungsPositionenMitFaktFrist( this.getLongDbValue(VKOPF_RG.id_adresse))>0) {
			
			if (this.isForderung()) {
				VEK<KV_IdKvPosUndFristTage> v_kvpos_id_kf_frist = getAllFaktFristIdsAndVals();
			
				if (v_kvpos_id_kf_frist.size()>1) { 		//erlaubt ist 1 (entweder alle rechnungspositionen haben die gleiche id oder es steht 0 drin, dann sind alle OHNE Vertrag
					return Rec21_VkopfRG.getInfoSchild("<Fehl.>", new E2_ColorAlarm() , null,
								S.ms("Der Beleg besitzt Kreditversicherungspositionen aus mehreren Verträgen oder es"
										+ " existieren mehrere Kreditversicherungen im gleichen Zeitraum! Bitte prüfen Sie diesen Sachverhalt!").CTrans(), true);
				} else if (v_kvpos_id_kf_frist.size()==0) {
					return Rec21_VkopfRG.getInfoSchild("-", null , null, S.ms("Beleg besitzt keine Positionen").CTrans(), true);
				} else {  //hier ist exact ein wert gefunden worden
					
					Long faktFrist = 	v_kvpos_id_kf_frist.get(0).anzahlTage;

					if (faktFrist.longValue()==0) {
						//muesste durch die abfrage ausgeschlossen sein 
						return Rec21_VkopfRG.getInfoSchild("-", null, null, S.ms("Kunde besitzt keine verlängerten Fakturierungsfristen oder im Kontrakt ist die Fakturierungsfrist ausgeschaltet !").CTrans(), false);
					} else {
					
					
						//jetzt die grenzen aus den mandantensettings rauslesen
						Long lYellowBorder = 	Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_GELB.getCheckedValue());
						Long lRedBorder = 		Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_ROT.getCheckedValue());
	
						Date leistungsDatum = 	this.getBiggestLeitungsDatum();
						String s_datum = 		leistungsDatum!=null?new SimpleDateFormat("dd.MM.yyyy").format(leistungsDatum):"-";
						
						Long tageBisHeute = TimeTools.getDiffInTage(leistungsDatum, new Date()).longValue();
						Long daysRest = faktFrist - tageBisHeute;
						String tooltipsBase = S.ms("Leistungsdatum").CTrans()+": "+s_datum+"\n";
						tooltipsBase = tooltipsBase+S.ms("Zeit seit Leistungsdatum bis heute").CTrans()+": "+tageBisHeute+" Tage\n";
						tooltipsBase = tooltipsBase+S.ms("Verlängerte Fakturierungsfrist").CTrans()+": "+faktFrist+" Tage\n";
	
						String labelText = ""+daysRest; 
						
						Color col = Color.GREEN;
						
						if (this.is_yes_db_val(VKOPF_RG.abgeschlossen)) {
							labelText = "OK";
							String tooltips = new String(tooltipsBase)+S.ms("Beleg ist ist geschlossen!!!").CTrans()+"\n";
							return Rec21_VkopfRG.getInfoSchild(labelText, col, null, tooltips, true);

						} else {
						
							String tooltips = new String(tooltipsBase)+S.ms("Beleg ist noch nicht abschliessend gedruckt !!!").CTrans()+"\n";
		
							//hier unterscheiden, in welchem bereich der beleg ist (gruen, gelb, rot)
							if (daysRest.longValue()<=lYellowBorder.longValue() && daysRest.longValue()>lRedBorder.longValue()) {
								col = new E2_ColorHelpBackground();
								tooltips = tooltips+ S.ms("Beleg steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an !").CTrans();
							} else if (daysRest.longValue()<=lRedBorder.longValue()) {
								col = new E2_ColorAlarm();
								if (daysRest.longValue()<0) {
									tooltips = tooltips+ S.ms("Beleg war vor  ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) fällig!").CTrans();
								} else {
									tooltips = tooltips+ S.ms("Beleg steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an!").CTrans();
								}
							} else {
								tooltips = tooltips+ S.ms("Beleg steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an!").CTrans();
							}
							
							return Rec21_VkopfRG.getInfoSchild(labelText, col, null, tooltips, true);
						}
					}
				}
			} else {
				return Rec21_VkopfRG.getInfoSchild("-", null , null, S.ms("Beleg ist keine Forderung").CTrans(), false);
			}
		} else {
			return Rec21_VkopfRG.getInfoSchild("-", null, null, S.ms("Kunde besitzt keine verlängerten Fakturierungsfristen").CTrans(), false);
		}
	}
	
	
	
	public static E2_Grid getInfoSchild(String schildText, Color backColor, Font font, String toolTips, boolean border) {
		E2_Grid grid = new E2_Grid()._setSize(KV_Const.breiteWarnGrid)._h(KV_Const.hoeheWarnGrid);
		if (border) {
			grid._bo_dd();
		}
		
		if (font==null) {
			font = new E2_FontPlain(-2);
		}
		
		try {
			grid._a(new RB_lab()._t(schildText)._f(font)._ttt(toolTips), new RB_gld()._center_mid()._col_back(backColor));
		} catch (Exception e) {
			e.printStackTrace();
			grid._a(new RB_lab()._t("Runtime-Error <d438d9fa-9403-11e9-bc42-526af7764f64>"), new RB_gld()._center_mid()._col_back(new E2_ColorAlarm()));
		}
		
		return grid;
	}

	
	
	public RecList21 getRecPositionsUndeleted() throws myException {
		RecList21 rlPos = null;
		
		And bedingung = new And(new vgl_YN(VPOS_RG.deleted, false));
		rlPos = this.get_down_reclist21(VPOS_RG.id_vkopf_rg,bedingung.s(),null);
		
		return rlPos;
		
	}
	
	
}
