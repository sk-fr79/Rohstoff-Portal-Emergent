/**
 * rohstoff.Echo2BusinessLogic.__SPECIALREC20
 * @author martin
 * @date 19.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
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
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.TimeTools;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Const;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Lib;

/**
 * @author martin
 * @date 19.06.2019
 *
 */
public class Rec21_VposRg extends Rec21 {

	private Rec21 recVkopfRg = null;
	
	public Rec21_VposRg() throws myException {
		super(_TAB.vpos_rg);
	}

	public Rec21_VposRg(Rec21 baseRec) throws myException {
		super(baseRec);
		if (baseRec.get_tab()!=_TAB.vpos_rg) {
			throw new myException("Error: <72e53d18-9289-11e9-bc42-526af7764f64> only _TAB.vpos_rg allwoed !");
		}
	}
	
	
	public Rec21 getVkopfRg() throws myException {
		if (this.recVkopfRg==null) {
			this.recVkopfRg = this.get_up_Rec21(VKOPF_RG.id_vkopf_rg);
		}
		return this.recVkopfRg;
	}
	
	
	public boolean isAddedToHead() throws myException {
		return (this.getVkopfRg()!=null);
	}
	
	public boolean isAddedToHeadAnPrinted() throws myException {
		if (this.isAddedToHead()) {
			if (this.getVkopfRg().is_yes_db_val(VKOPF_RG.abgeschlossen)) {
				return true;
			}
		} 
		return false;
	}
	

	/**
	 * 
	 * @author martin
	 * @date 25.06.2019
	 *
	 * @return PAIR<BigDecimal,BigDecimal> with Gesamtpreis-Basis / Gesamtpreis-Fremdwaehrung, bei null- werten wird BigDecimal.ZERO, ausser beim Mengendivisor, dort BigDecimal.ONE 
	 *                                     angenommen
	 * @throws myException
	 */
	public PAIR<BigDecimal,BigDecimal>  getGesamtBetragEigenwaehrungFremdWaehrung() throws myException {
		PAIR<BigDecimal,BigDecimal> pair = new PAIR<>();
		
		BigDecimal minusEins  = new BigDecimal(-1);
		BigDecimal lagerVorzeichen = 	O.NN(this.getBigDecimalDbValue(VPOS_RG.lager_vorzeichen),	BigDecimal.ZERO);
		BigDecimal anzahl = 			O.NN(this.getBigDecimalDbValue(VPOS_RG.anzahl),				BigDecimal.ZERO);
		BigDecimal e_preis = 			O.NN(this.getBigDecimalDbValue(VPOS_RG.einzelpreis),		BigDecimal.ZERO);
		BigDecimal mengendivisor = 		O.NN(this.getBigDecimalDbValue(VPOS_RG.mengendivisor),		BigDecimal.ONE);
		BigDecimal waehrungsKurs =      O.NN(this.getBigDecimalDbValue(VPOS_RG.waehrungskurs),		BigDecimal.ZERO);
		
		MathContext math = new MathContext(22, RoundingMode.HALF_UP);
		
		BigDecimal e_preis_fw = e_preis.multiply(waehrungsKurs,math).setScale(2,RoundingMode.HALF_UP);
		
		BigDecimal posGesamt = 		anzahl.multiply(e_preis, 	math).divide(mengendivisor,math).multiply(lagerVorzeichen, math).multiply(minusEins,math).setScale(2,RoundingMode.HALF_UP);
		BigDecimal posGesamtFW = 	anzahl.multiply(e_preis_fw, math).divide(mengendivisor,math).multiply(lagerVorzeichen, math).multiply(minusEins,math).setScale(2,RoundingMode.HALF_UP);

		pair._setVal1(posGesamt)._setVal2(posGesamtFW);
		
		return pair;
	}
	
	
	
	public E2_Grid getStatusFakturierungsFrist() throws Exception {
		
		if (this.isAddedToHeadAnPrinted()) {
			return this.getInfoSchild("OK", Color.GREEN, new E2_FontPlain(-2), S.ms("Position ist einem Rechnungskopf zugeordnet und gedruckt!").CTrans(), true);
		} else {
		
			//dann muss die fakturierungsfrist abgefragt werden
		
			Long idAdresse = this.getLongDbValue(VPOS_RG.id_adresse);
			if (idAdresse==null) {
				Rec21 kopf = this.getVkopfRg();
				if (kopf!=null) {
					idAdresse=kopf.getLongDbValue(VKOPF_RG.id_adresse);
				} else {
					return this.getInfoSchild("@ERROR", new E2_ColorAlarm(), new E2_FontPlain(-2), "Error: Cannot find Adresse to RG-Pos "+this.getId()+": Code <71ae0d9e-942d-11e9-bc42-526af7764f64>",true);
				}
			}
			
			Date datum = (Date) this.getRawVal(VPOS_RG.ausfuehrungsdatum);
			String s_datum = datum!=null?new SimpleDateFormat("dd.MM.yyyy").format(datum):"-";
	
			if (datum==null) {
				return this.getInfoSchild("@ERROR", new E2_ColorAlarm(), new E2_FontPlain(-2), "Error: Cannot find Leistungsdatum to RG-Pos "+this.getId()+": Code <71ae0d9e-942d-1111-bc42-526af7764f64>",true);
			}
			
			
			//jetzt pruefen, ob es eine Forderung ist
			BigDecimal bdPreis = this.getBigDecimalDbValue(VPOS_RG.gesamtpreis).multiply(this.getBigDecimalDbValue(VPOS_RG.lager_vorzeichen));
			
			if (bdPreis.compareTo(BigDecimal.ZERO)>=0) {
				return this.getInfoSchild("-", null, new E2_FontPlain(-2),S.ms("Position ist keine Forderung !").CTrans(),false);
			} else {
				// jetzt nachsehen, ob zum leistungszeitpunkt eine kreditversicherung existiert

				//jetzt schauen, ob ein kontrakt mit fakt-frist-schalter existiert
				if (this.hatVerlaengerteFaktFristSchalterImKontrakt()) {
				
					SqlStringExtended query = KV_Lib.getSQLExtKreditversicherungMitFakturierungsFrist(KREDITVERS_KOPF.fakturierungsfrist.tnfn()
												,idAdresse,
												(Date) this.getRawVal(VPOS_RG.ausfuehrungsdatum));
					VEK<Object[]> fristen = bibDB.getResultLines(query, true);
					
					if (fristen == null) {
						return this.getInfoSchild("@ERROR", new E2_ColorAlarm(), new E2_FontPlain(-2), "Error querying Kreditversicherungen for idadresse: " + idAdresse +"Code:<71c04b48-9406-11e9-bc42-526af7764f64>\n"+query.getSqlString(),true);
					} else if (fristen.size()>1) {
						String meldung = S.ms("Adresse").CTrans()+" "+idAdresse+" "+S.ms("besitzt mehrere Kreditversicherungen mit verlängerter Fakturierungsfrist, die am")+" "+s_datum+" "+S.ms(" gelten ! Das ist ein Fehler !!");
						return this.getInfoSchild("<Fehl.>", new E2_ColorAlarm(), null, meldung,true);
					} else {
						if (fristen.size()==0 || ((BigDecimal)fristen.get(0)[0]).longValue()==0) {
							String meldung = S.ms("Adresse").CTrans()+" "+idAdresse+" "+S.ms("besitzt keine Kreditversicherungen mit verlängerter Fakturierungsfrist, die am")+" "+s_datum+" "+S.ms(" gilt!");
							return this.getInfoSchild("-", null, new E2_FontPlain(-2),meldung,false);
						} else {
							return getStatusFakturierungsFrist(((BigDecimal)fristen.get(0)[0]).longValue());
						}
					}
				} else {
					String meldung = S.ms("Besitzt keinen Kontrakt mit Fakturierungsfrist-Schalter!").CTrans();
					return this.getInfoSchild("-", null, new E2_FontPlain(-2),meldung,false);
				
				}
			}
		}
	}
	
	
	
	public E2_Grid getStatusFakturierungsFrist(Long faktFrist) throws Exception {

		//jetzt die grenzen aus den mandantensettings rauslesen
		Long lYellowBorder = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_GELB.getCheckedValue());
		Long lRedBorder = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_ROT.getCheckedValue());

		Date leistungsDatum = this.get_myDate_dbVal(VPOS_RG.ausfuehrungsdatum).getDate();
		String s_datum = leistungsDatum!=null?new SimpleDateFormat("dd.MM.yyyy").format(leistungsDatum):"-";

		
		Long tageBisHeute = TimeTools.getDiffInTage(leistungsDatum, new Date()).longValue();
		Long daysRest = faktFrist - tageBisHeute;
		String tooltipsBase = S.ms("Leistungsdatum").CTrans()+": "+s_datum+"\n";
		tooltipsBase = tooltipsBase+S.ms("Zeit seit Leistungsdatum bis heute").CTrans()+": "+tageBisHeute+" Tage\n";
		tooltipsBase = tooltipsBase+S.ms("Verlängerte Fakturierungsfrist").CTrans()+": "+faktFrist+" Tage\n";

		
		String toolTips = new String(tooltipsBase);
		
		Color col = Color.GREEN;


		
		if (this.isAddedToHeadAnPrinted()) {
			return this.getInfoSchild("OK", Color.GREEN,  new E2_FontPlain(-2), toolTips+"\n"+S.ms("Position ist einem Rechnungskopf zugeordnet und gedruckt!").CTrans(), true);
		} else if (this.isAddedToHead()) {
			String tooltips = new String(tooltipsBase)+"\n"+S.ms("Position ist einem Rechnungskopf zugeordnet, ABER NOCH NICHT GEDRUCKT !!!").CTrans()+"\n";

			//hier unterscheiden, in welchem bereich der beleg ist (gruen, gelb, rot)
			if (daysRest.longValue()<=lYellowBorder.longValue() && daysRest.longValue()>lRedBorder.longValue()) {
				col = new E2_ColorHelpBackground();
				tooltips = tooltips+ S.ms("Position steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an !").CTrans();
			} else if (daysRest.longValue()<=lRedBorder.longValue()) {
				col = new E2_ColorAlarm();
				if (daysRest.longValue()<0) {
					tooltips = tooltips+ S.ms("Position war vor  ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) fällig!").CTrans();
				} else {
					tooltips = tooltips+ S.ms("Position steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an!").CTrans();
				}
			} else {
				tooltips = tooltips+ S.ms("Position steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an!").CTrans();
			}

			return this.getInfoSchild(""+daysRest, col, new E2_FontPlain(-2), tooltips, true);

		} else {
			String tooltips = new String(tooltipsBase)+"\n";

			//hier unterscheiden, in welchem bereich der beleg ist (gruen, gelb, rot)
			if (daysRest.longValue()<=lYellowBorder.longValue() && daysRest.longValue()>lRedBorder.longValue()) {
				col = new E2_ColorHelpBackground();
				tooltips = tooltips+ S.ms("Position steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an !").CTrans();
			} else if (daysRest.longValue()<=lRedBorder.longValue()) {
				col = new E2_ColorAlarm();
				if (daysRest.longValue()<0) {
					tooltips = tooltips+ S.ms("Position war vor  ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) fällig!").CTrans();
				} else {
					tooltips = tooltips+ S.ms("Position steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an!").CTrans();
				}
			} else {
				tooltips = tooltips+ S.ms("Position steht in ").CTrans()+daysRest+S.ms(" Tagen (mit verlängerter Fakturierungsfrist) zur Fakturierung an!").CTrans();
			}

			return this.getInfoSchild(""+daysRest, col, new E2_FontPlain(-2), tooltips, true);

		}
		
		
	}
	
	
	
	private E2_Grid getInfoSchild(String schildText, Color backColor, Font font, String toolTips, boolean border) {
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
	

	
	
	
	/**
	 * 
	 * @author martin
	 * @date 29.11.2019
	 *
	 * @return information, ob es eine formale rechnungsposition ist (forderung), on error null
	 */
	public Boolean isForderung() throws Exception {
		BigDecimal bdFaktor = this.getBigDecimalDbValue(VPOS_RG.anzahl).multiply(this.getBigDecimalDbValue(VPOS_RG.einzelpreis));
		bdFaktor = bdFaktor.multiply(new BigDecimal(this.getLongDbValue(VPOS_RG.lager_vorzeichen)));
		return bdFaktor.compareTo(BigDecimal.ZERO)<0;
	}
	
	
	
	public boolean istAbgerechnet() throws Exception  {
		if (this.getVkopfRg()!=null) {
			if (this.getVkopfRg().is_yes_db_val(VKOPF_RG.abgeschlossen)) {
				return true;
			}
		}
		return false;
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 14.02.2020
	 *
	 * @return verlaengerte-faktfrist-schalter unabhaengig von status forderung (wichtig fuer pruefungen von rechnungskopf-saetzen
	 * @throws Exception
	 */
	public boolean hatVerlaengerteFaktFristSchalterImKontrakt() throws Exception {
		boolean bhatVerlaengerteFaktFristSchalterImKontrakt = false;
		
    	Rec21 recVposKon = this.get_up_Rec21(VPOS_RG.id_vpos_kon_zugeord,VPOS_KON.id_vpos_kon,true); 
    	if (recVposKon!=null) {
    		bhatVerlaengerteFaktFristSchalterImKontrakt = new Rec21_VPosKon(recVposKon).get_rec_vpos_kon_trakt().is_yes_db_val(VPOS_KON_TRAKT.verlaengerte_fakt_frist);
    	}
	    
		return bhatVerlaengerteFaktFristSchalterImKontrakt;
	}
	
}
