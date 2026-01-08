package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.BasicInterfaces.Check;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_DEF_ABRECHNUNGSTYP;
import panter.gmbh.basics4project.ENUM_DEF_EK_VK;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.TimeTools;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.is_null;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Const;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Lib;

public class Rec21_VposTpaFuhreOrt  extends Rec21{

	
	private Rec21_VposTpaFuhre  recFu = null;

	private ENUM_DEF_ABRECHNUNGSTYP  abrechTyp = null;

	
	private Rec21_ZahlungsBedingung zahlungsBedingung = null;

	private Rec21_VposRg            aktiveRG_Position= null;
	

	
	
	public Rec21_VposTpaFuhreOrt() throws myException{
		super (_TAB.vpos_tpa_fuhre_ort);
	}
	
	public Rec21_VposTpaFuhreOrt(Rec21 baseRec) throws myException{
		super(baseRec);
	}

	
	public Rec21_VposTpaFuhre getRec21Fuhre() throws myException {
		if (this.recFu==null) {
			this.recFu = new Rec21_VposTpaFuhre(this.get_up_Rec21(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre));
		}
		return this.recFu;
	}
	

	public Rec21_VposTpaFuhreOrt _setRecFu(Rec21_VposTpaFuhre recFu) {
		this.recFu = recFu;
		return this;
	}
	
	public boolean isEK() throws myException {
		return this.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equals(ENUM_DEF_EK_VK.EK.dbVal());
	}
	
	
//	/**
//	 * 
//	 * @author martin
//	 * @date 29.11.2019
//	 *
//	 * @return true wenn ein berechneter Warenausgang vorhanden ist (auch in fuhrenorten), sonst false; bei fehler null. 
//	 */
//	public Boolean isRechnungsPosition() {
//		try {
//			
//			if (this.is_yes_db_val(VPOS_TPA_FUHRE_ORT.ohne_abrechnung)) {
//				return false;
//			}
//
//			boolean ret = false;
//
//			MyBigDecimal menge = null;
//			MyBigDecimal preis = null;
//			
//			boolean isLager = this.getLongDbValue(VPOS_TPA_FUHRE_ORT.id_adresse).toString().equals(bibALL.get_ID_ADRESS_MANDANT());
//			
//			if (!isLager) {
//				if (this.isEK()) {
//					menge = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_lademenge, this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_planmenge)),new MyBigDecimal(0));
//					preis = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.einzelpreis), new MyBigDecimal(1l));
//					
//					BigDecimal faktor = menge.get_bdWert().multiply(preis.get_bdWert());
//					if (faktor.compareTo(BigDecimal.ZERO)<0) {
//						ret=true;    //negativer einkauf ist rechnung!!
//					} 
//					
//				} else {
//					
//					menge =  O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge, this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_planmenge)),new MyBigDecimal(0));
//					preis = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.einzelpreis), new MyBigDecimal(1l));
//					
//					BigDecimal faktor = menge.get_bdWert().multiply(preis.get_bdWert());
//					if (faktor.compareTo(BigDecimal.ZERO)>0) {
//						ret=true;    //positiver verkauf ist rechnung!!
//					} 
//					
//				}
//			}
//			return ret;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//	}


	
	public ENUM_DEF_ABRECHNUNGSTYP getAbrechTyp()  throws Exception {
		if (this.abrechTyp==null) {
			initAbrechnungsTyp();
		}
		return abrechTyp;
	}


	private void initAbrechnungsTyp() throws Exception {
		if (this.is_yes_db_val(VPOS_TPA_FUHRE_ORT.ohne_abrechnung) || this.getRec21Fuhre().getLongDbValue(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)!=null) {
			this.abrechTyp=ENUM_DEF_ABRECHNUNGSTYP.LEER_ZU_NULL;
		} else {

			boolean isLager = this.getLongDbValue(VPOS_TPA_FUHRE_ORT.id_adresse).toString().equals(bibALL.get_ID_ADRESS_MANDANT());

			if (isLager) {
				this.abrechTyp=ENUM_DEF_ABRECHNUNGSTYP.LAGER;
			} else {
				MyBigDecimal menge = O.NN(this.get_myBigDecimal_dbVal(
						(isEK()?VPOS_TPA_FUHRE_ORT.anteil_lademenge:VPOS_TPA_FUHRE_ORT.anteil_ablademenge), 
						this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.anteil_planmenge)),new MyBigDecimal(0));
				MyBigDecimal preis = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE_ORT.einzelpreis), new MyBigDecimal(1l));
				BigDecimal   faktor = menge.get_bdWert().multiply(preis.get_bdWert());
				if (faktor.compareTo(BigDecimal.ZERO)==0) {
					abrechTyp=ENUM_DEF_ABRECHNUNGSTYP.LEER_ZU_NULL;
				} else if (faktor.compareTo(BigDecimal.ZERO)<0) {
					abrechTyp=(isEK()?ENUM_DEF_ABRECHNUNGSTYP.FORDERUNG:ENUM_DEF_ABRECHNUNGSTYP.VERBINDLICHKEIT);
				} else {
					abrechTyp=(isEK()?ENUM_DEF_ABRECHNUNGSTYP.VERBINDLICHKEIT:ENUM_DEF_ABRECHNUNGSTYP.FORDERUNG);
				}
			}
		}
	}
	

	
	
	
	
	public Date getLeistungsDatum() throws Exception {
		return this.getDateDbValue(VPOS_TPA_FUHRE_ORT.datum_lade_ablade);
	}
	
	

	
	public Rec21_ZahlungsBedingung getZahlungsBedingung() throws Exception {
		if (zahlungsBedingung==null ) {
			//zuerst kontrakt
			try { zahlungsBedingung = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE_ORT.id_vpos_kon, VPOS_KON.id_vpos_kon, true)
													.get_up_Rec21(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen)); } catch (Exception e) {}
			
			//dann angebot
			if (zahlungsBedingung==null) {
				try { zahlungsBedingung = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE_ORT.id_vpos_std, VPOS_STD.id_vpos_std, true)
						  .get_up_Rec21(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen)); } catch (Exception e) {}
			}
			
			//dann stammsatz
			if (zahlungsBedingung==null) {
				try {zahlungsBedingung  = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE_ORT.id_adresse, ADRESSE.id_adresse,true)
													.get_up_Rec21(ADRESSE.id_zahlungsbedingungen_vk, ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen, true)); } catch (Exception e) {}
			}
		}
		
		return zahlungsBedingung;
	}
	
	
	
	


	
	
	
	public Rec21_VposRg getAktiveRG_Position() throws Exception  {
		if (aktiveRG_Position == null) {
			SEL selRg = new SEL(_TAB.vpos_rg).FROM(_TAB.vpos_rg)
					.WHERE(	new vglParam(VPOS_RG.id_vpos_tpa_fuhre_zugeord))
					.AND(	new vglParam(VPOS_RG.id_vpos_tpa_fuhre_ort_zugeord))
					.AND(	new VglNull(VPOS_RG.id_vpos_rg_storno_vorgaenger))
					.AND(	new VglNull(VPOS_RG.id_vpos_rg_storno_nachfolger))
					.AND(	new vgl_YN(VPOS_RG.deleted,false))
					;
			SqlStringExtended sql = new SqlStringExtended(selRg.s());
			sql._addParameters(
				new VEK<ParamDataObject>()	._a(new Param_Long(this.getRec21Fuhre().getId()))
											._a(new Param_Long(this.getId()))
				);

			RecList21 rlRg = new RecList21(_TAB.vpos_rg)._fill(sql);

			if (rlRg.size() == 0) {
				aktiveRG_Position = null;
			} else if (rlRg.size() == 1) {
				aktiveRG_Position = new Rec21_VposRg(rlRg.get(0));
			} else {
				throw new myException("Only one active RG-position on fuhren-ort allowed < 538dac3e-14f2-11ea-8d71-362b9e155667 >");
			}
		}
		return aktiveRG_Position;
	}

	
	
	/*
	 * 
	 * @author martin
	 * @date 14.02.2020
	 *
	 * @return true, der ort eine forderung ergibt und im Kontrakt der schalter verlangerte FaktFrist eingeschaltet ist
	 * @throws Exception
	 */
	public boolean hatVerlaengerteFaktFristSchalterImKontraktWennForderung() throws Exception {
		boolean bhatVerlaengerteFaktFristSchalterImKontrakt = false;
		
		boolean isForderung = true;    //falls unbestimmt, dann wie eine forderung behandeln
		
		BigDecimal preis = this.getBigDecimalDbValue(VPOS_TPA_FUHRE_ORT.einzelpreis);
		if (preis != null && preis.compareTo(BigDecimal.ZERO)>0 && this.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equals("EK")) {
			isForderung = false;
		}
		if (preis != null && preis.compareTo(BigDecimal.ZERO)<=0 && this.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equals("VK")) {
			isForderung = false;
		}
		
		if (isForderung) {
	   		Rec21 vposKontrakt = this.get_up_Rec21(VPOS_TPA_FUHRE_ORT.id_vpos_kon, VPOS_KON.id_vpos_kon,true);
	   		if (vposKontrakt!=null) {
	   			Rec21 vPosKontrakt = new Rec21_VPosKon(vposKontrakt).getVposKonTrakt();
	   			bhatVerlaengerteFaktFristSchalterImKontrakt = vPosKontrakt.is_yes_db_val(VPOS_KON_TRAKT.verlaengerte_fakt_frist);
		    }
		}
		
		return bhatVerlaengerteFaktFristSchalterImKontrakt;
	}
	
	
	
	
	
	public boolean isRelevantForWarnungFakturierungsFrist() throws myException {
		boolean ret = false;
		
		if (	this.is_no_db_val(VPOS_TPA_FUHRE_ORT.ohne_abrechnung) 
			 && (!this.getUfs(VPOS_TPA_FUHRE_ORT.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT()))
			 && this.is_no_db_val(VPOS_TPA_FUHRE_ORT.deleted)
			 && this.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equals("VK")
			) {
			
			BigDecimal ablademenge = (BigDecimal)this.getRawVal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge);
			if (ablademenge.compareTo(BigDecimal.ZERO)>0) {
				ret = true;
			}
		}
		return ret;
	}
	
	
	
	

	/**
	 * 
	 * @author martin
	 * @date 19.06.2019
	 *
	 * @param vk
	 * @return s Rec21 from vpos_rg, without storno-kennzeichen, null when not a singular record found
	 * @throws myException
	 */
	public Rec21_VposRg getRecPosWithoutStorno(boolean vk) throws myException {
		SEL sel = new SEL(_TAB.vpos_rg)
					.FROM(_TAB.vpos_rg)
					.WHERE(new is_null(VPOS_RG.id_vpos_rg_storno_vorgaenger))
					.AND(new is_null(VPOS_RG.id_vpos_rg_storno_nachfolger))
					.AND(new vgl_YN(VPOS_RG.deleted, false))
					.AND(new vglParam(VPOS_RG.lager_vorzeichen))
					.AND(new vglParam(VPOS_RG.id_vpos_tpa_fuhre_zugeord))
					.AND(new vglParam(VPOS_RG.id_vpos_tpa_fuhre_ort_zugeord))
					;
		
		SqlStringExtended sql = new SqlStringExtended(sel.s())
										._addParameter(new Param_Long(vk?-1:1))
										._addParameter(new Param_Long(this.getLongDbValue(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre)))
										._addParameter(new Param_Long(this.getLongDbValue(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort)))
										;
		
		RecList21 rl = new RecList21(_TAB.vpos_rg)._fill(sql);
		
		if (rl.size()==1) {
			return new Rec21_VposRg(rl.get(0));
		}
		
		return null;
	}
	
	

	
	
	
	public E2_Grid getStatusFakturierungsFrist() throws Exception {

		//jetzt die grenzen aus den mandantensettings rauslesen
		Long lYellowBorder = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_GELB.getCheckedValue());
		Long lRedBorder = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_ROT.getCheckedValue());

		
		IF_Field feldAdresse = VPOS_TPA_FUHRE_ORT.id_adresse;
		IF_Field fieldPreis = VPOS_TPA_FUHRE_ORT.einzelpreis;
		IF_Field fieldDatum = VPOS_TPA_FUHRE_ORT.datum_lade_ablade;
		IF_Field fieldMenge = null;
		Check<BigDecimal> checker = null;

		if (this.isEK()) {
			fieldMenge = is_yes_db_val(VPOS_TPA_FUHRE_ORT.lademenge_gutschrift) ? VPOS_TPA_FUHRE_ORT.anteil_lademenge: VPOS_TPA_FUHRE_ORT.anteil_ablademenge;
			checker = (bd) -> {	return (bd.compareTo(BigDecimal.ZERO) < 0);	};
		} else {
			fieldMenge = is_yes_db_val(VPOS_TPA_FUHRE_ORT.ablademenge_rechnung) ? VPOS_TPA_FUHRE_ORT.anteil_ablademenge	: VPOS_TPA_FUHRE_ORT.anteil_lademenge;
			checker = (bd) -> {	return (bd.compareTo(BigDecimal.ZERO) > 0); };
		}

		Long idAdresse = this.getLongDbValue(feldAdresse);
		Date datum = (Date) this.getRawVal(fieldDatum);
		String s_datum = new SimpleDateFormat("dd.MM.yyyy").format(datum);

		boolean vk = this.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equals("VK");
		
		Rec21_VposTpaFuhre recFu = this.getRec21Fuhre();

		
		if (idAdresse == null) {
			
			return this.getInfoSchild("?", new E2_ColorAlarm(), null, "Error: Adress was not found: Code:<5887d73c-940f-11e9-bc42-526af7764f64>",true);
			
		} else if (this.is_yes_db_val(VPOS_TPA_FUHRE_ORT.ohne_abrechnung)
				|| recFu.getRawVal(VPOS_TPA_FUHRE.id_adresse_fremdauftrag) != null
				|| this.is_yes_db_val(VPOS_TPA_FUHRE_ORT.deleted) || recFu.is_yes_db_val(VPOS_TPA_FUHRE.ist_storniert)
				|| this.getUfs(feldAdresse).equals(bibALL.get_ID_ADRESS_MANDANT())) {

			return this.getInfoSchild("-", null, null, S.ms("Fuhrenort: Kein Fakturabeleg").CTrans(),false);

		} else {
			
			if (this.hatVerlaengerteFaktFristSchalterImKontraktWennForderung())  {
			
				SqlStringExtended sql = KV_Lib.getSqlQueryKreditversicherungWithFaktFristExists(idAdresse);
				String[][] anzahl = bibDB.EinzelAbfrageInArray(sql);
	
				if (anzahl == null || anzahl.length == 0 || anzahl[0].length == 0) {
					
					return this.getInfoSchild("@ERROR", new E2_ColorAlarm(), null, "Error counting Kreditversicherungen for idadresse: " + idAdresse +"Code:<c87e4314-9405-11e9-bc42-526af7764f64>\n"+sql.getSqlString(),true);
	
				} else {
					Integer zahl = Integer.parseInt(anzahl[0][0]);
	
					if (zahl.intValue() == 0) {
	
						return this.getInfoSchild("-", null, null, "Adresse "+idAdresse+" besitzt keine Kreditversicherungen mit verlängerter Fakturierungsfrist",false);
	
					} else {
						// dann schauen, ob relevante beleg (positive rechnung/negative gutschrift)
						if (this.getRawVal(fieldMenge) == null || this.getRawVal(fieldPreis) == null || this.getRawVal(fieldDatum) == null) {
	
							return this.getInfoSchild("<?>", null, new E2_FontPlain(-2), "Fuhren-Ort "+idAdresse+" kann noch nicht bewertete werden, da entweder Menge, Preis oder Datum fehlt!",true);
	
						} else {
							// jetzt nachsehen, ob zum leistungszeitpunkt eine kreditversicherung existiert
							SqlStringExtended query = KV_Lib.getSQLExtKreditversicherungMitFakturierungsFrist(	"MIN(" + KREDITVERS_KOPF.fakturierungsfrist.tnfn() + ")",
																													this.getLongDbValue(feldAdresse), 
																													(Date) this.getRawVal(fieldDatum));
							String[][] kvTage = bibDB.EinzelAbfrageInArray(query);
	
							if (kvTage == null) {
	
								return this.getInfoSchild("@ERROR", new E2_ColorAlarm(), null, "Error querying Kreditversicherungen for idadresse: " + idAdresse +"Code:<fc58ac96-9410-11e9-bc42-526af7764f64>\n"+query.getSqlString(),true);
	
							} else {
								if (kvTage.length == 0 || kvTage[0].length == 0 || S.isEmpty(kvTage[0][0])|| kvTage[0][0].trim().equals("0")) {
	
									String meldung = S.ms("Adresse").CTrans()+" "+idAdresse+" "+S.ms("besitzt keine Kreditversicherungen mit verlängerter Fakturierungsfrist, die am")+" "+s_datum+" "+S.ms(" gilt!");
									return this.getInfoSchild("-", null, null,meldung,true);
	
								} else {
										
									BigDecimal bdEndPreis = this.getBigDecimalDbValue(fieldMenge).multiply(this.getBigDecimalDbValue(fieldPreis), new MathContext(2, RoundingMode.HALF_UP));
										
									if (checker.isOk(bdEndPreis)) {
	
										long faktFrist = Integer.parseInt(kvTage[0][0]);
										Long tageBisHeute = TimeTools.getDiffInTage(datum, new Date()).longValue();
										Long daysRest = faktFrist - tageBisHeute;
										String tooltipsBase = S.ms("Leistungsdatum").CTrans()+": "+s_datum+"\n";
										tooltipsBase = tooltipsBase+S.ms("Zeit seit Leistungsdatum bis heute").CTrans()+": "+tageBisHeute+" Tage\n";
										tooltipsBase = tooltipsBase+S.ms("Verlängerte Fakturierungsfrist").CTrans()+": "+faktFrist+" Tage\n";
	
										
										if (O.NN(recFu.getLongDbValue(VPOS_TPA_FUHRE.status_buchung), 0l).longValue() == myCONST.STATUS_FUHRE__GANZGEBUCHT) {
		
											String tooltips = new String(tooltipsBase);
											tooltips = tooltips+ S.ms("\nFuhrenort wurde verbucht.").CTrans();
											E2_Grid g1 = this.getInfoSchild("OK", Color.GREEN, new E2_FontPlain(-2), tooltips, true);
	
											// dann den rechnungsbeleg checken
											Rec21_VposRg rg = this.getRecPosWithoutStorno(vk);
	
											E2_Grid g2 = new E2_Grid();
											if (rg == null) {
													
												g2 = this.getInfoSchild("Error", new E2_ColorAlarm(), null, S.ms("Fehler! Obwohl Fuhrenort verbucht ist, kann die relevante Rechnungsposition nicht identifiziert werden").CTrans(), true);
													
											} else {
												g2 = rg.getStatusFakturierungsFrist(faktFrist);
											}
	
											return new E2_Grid()._setSize(KV_Const.breiteWarnGrid)._a(g1)._a(g2);
		
										} else {
												
											Color  col = Color.GREEN;
											String tooltips = new String(tooltipsBase);
	
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
	
											//Fehlerfix: 2019-12-03: martin: return this.getInfoSchild(""+TimeTools.getDiffInTage(datum, new Date()).longValue(), col, new E2_FontPlain(), tooltips, true);
											return this.getInfoSchild(""+daysRest, col, new E2_FontPlain(-2), tooltips, true);
											
										}
									} else {
										return this.getInfoSchild("-", null, null,S.ms("Beleg ist keine Forderung !").CTrans(),false);
									}
								}
							} 
						}
					}
				}
			} else {
				return this.getInfoSchild("-", null, null,S.ms("Besitzt keinen Kontrakt mit Fakturierungsfrist-Schalter!").CTrans(),false);
			}
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

	
}
