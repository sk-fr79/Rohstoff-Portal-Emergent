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
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_DEF_ABRECHNUNGSTYP;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.FUHREN_CO2_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.TimeTools;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec21SaveOnlyChanged;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.is_null;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Const;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Lib;

public class Rec21_VposTpaFuhre  extends Rec21{

	private ENUM_DEF_ABRECHNUNGSTYP  abrechTypLadeseite = null;
	private ENUM_DEF_ABRECHNUNGSTYP  abrechTypAbladeseite = null;

	
	private boolean  				zahlungsBedingungLadeseiteErmittelt = false;
	private boolean 				zahlungsBedingungAbladeseiteErmittelt = false;
	private Rec21_ZahlungsBedingung zahlungsBedingungLadeseite = null;
	private Rec21_ZahlungsBedingung zahlungsBedingungAbladeseite = null;

	private boolean  				aktiveRG_Positions_Ermittelt = false;
	private Rec21_VposRg            aktiveRG_PositionLadeSeite = null;
	private Rec21_VposRg            aktiveRG_PositionAbladeSeite = null;
	
	
	public Rec21_VposTpaFuhre() throws myException{
		super (_TAB.vpos_tpa_fuhre);
	}
	
	public Rec21_VposTpaFuhre(Rec21 baseRec) throws myException{
		super(baseRec);
	}

	public MyBigDecimal get_myBigDecimal_plan_menge(boolean isEk, int iNachKomma) throws myException{
		MyBigDecimal bd_plan_menge = null;
		if(isEk){
			bd_plan_menge = this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief, 
					this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_lief,
							this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_planmenge_lief, new MyBigDecimal(0))));
		}else{
			bd_plan_menge = this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, 
					this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_abn,
							this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_planmenge_abn, new MyBigDecimal(0))));
		}
		return bd_plan_menge;
	}

	public MyBigDecimal get_myBigDecimal_real_menge(boolean isEk, int iNachKomma) throws myException{
		MyBigDecimal bd_real_menge = null;
		if(isEk){
			bd_real_menge = this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief,new MyBigDecimal(0));
		}else{
			bd_real_menge = this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, new MyBigDecimal(0));
		}
		return bd_real_menge;
	}
	
	public RB_lab get_rbLabel_plan_menge(boolean isEk, int iNachKomma) throws myException{
		RB_lab lbl = new RB_lab();
		
		String tooltip = "";
		MyBigDecimal bd_plan_menge = get_myBigDecimal_plan_menge(isEk, iNachKomma);

		if(isEk){
			tooltip = "Lademenge oder Planmenge Ladeseite";
		}else{
			tooltip = "Abademenge oder Planmenge Abladeseite";
		}
		
		lbl._t(bd_plan_menge.get_FormatedRoundedNumber(iNachKomma) + " " + this.get_fs_dbVal(VPOS_TPA_FUHRE.einheit_mengen));
		
		lbl._ttt(tooltip);
		
		return lbl;
	}
	
	public RB_lab get_rbLabel_real_menge(boolean isEk, int iNachKomma) throws myException{
		RB_lab lbl = new RB_lab();
		
		String tooltip = "";
		MyBigDecimal bd_real_menge = get_myBigDecimal_real_menge(isEk, iNachKomma);

		if(isEk){
			tooltip = "Lademenge oder Planmenge Ladeseite";
		}else{
			tooltip = "Abademenge oder Planmenge Abladeseite";
		}
		
		lbl._t(bd_real_menge.get_FormatedRoundedNumber(iNachKomma) + " " + this.get_fs_dbVal(VPOS_TPA_FUHRE.einheit_mengen));
		
		lbl._ttt(tooltip);
		
		return lbl;
	}
	
	
	/**
	 * je nach schalterstellung den gutschriftspreis
	 * @author martin
	 * @date 18.06.2019
	 *
	 * @return null undefined or menge x epreis
	 * @throws myException
	 */
	public BigDecimal getGesamtPreisGutschrift() throws myException {
		BigDecimal bd = null;
		
		bd = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_lademenge_lief);
		if (this.is_no_db_val(VPOS_TPA_FUHRE.lademenge_gutschrift)) {
			bd = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_ablademenge_lief);
		}
		
		BigDecimal gutschriftsPreis = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.einzelpreis_ek);
		
		if (bd==null || gutschriftsPreis==null) {
			return null;
		} else { 		
			return O.NN(bd,BigDecimal.ZERO).multiply(O.NN(gutschriftsPreis,BigDecimal.ZERO), new MathContext(2, RoundingMode.HALF_UP));
		}
	}
	
	
	/**
	 * je nach schalterstellung den gutschriftspreis
	 * @author martin
	 * @date 18.06.2019
	 *
	 * @return null undefined or menge je nach schalter
	 * @throws myException
	 */
	public BigDecimal getGutschriftMenge() throws myException {
		BigDecimal bd = null;
		
		bd = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_lademenge_lief);
		if (this.is_no_db_val(VPOS_TPA_FUHRE.lademenge_gutschrift)) {
			bd = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_ablademenge_lief);
		}
		
		return bd;
		
	}
	
	
	/**
	 * je nach schalterstellung den gutschriftspreis
	 * @author martin
	 * @date 18.06.2019
	 *
	 * @return null undefined or menge je nach schalter
	 * @throws myException
	 */
	public BigDecimal getRechnungsMenge() throws myException {
		BigDecimal bd = null;
		
		bd = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_ablademenge_abn);
		if (this.is_no_db_val(VPOS_TPA_FUHRE.ablademenge_rechnung)) {
			bd = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_lademenge_abn);
		}
		
		return bd;
		
	}
	
	
	/**
	 * je nach schalterstellung die rechnungspreis
	 * @author martin
	 * @date 18.06.2019
	 *
	 * @return null undefined or menge x epreis
	 * @throws myException
	 */
	public BigDecimal getGesamtPreisRechnung() throws myException {
		BigDecimal bd = null;
		
		bd = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_ablademenge_abn);
		if (this.is_no_db_val(VPOS_TPA_FUHRE.ablademenge_rechnung)) {
			bd = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_lademenge_abn);
		}
		
		BigDecimal rechnungsPreis = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.einzelpreis_vk);
		
		if (bd==null || rechnungsPreis==null) {
			return null;
		} else { 		
			return O.NN(bd,BigDecimal.ZERO).multiply(O.NN(rechnungsPreis,BigDecimal.ZERO), new MathContext(2, RoundingMode.HALF_UP));
		}
	}
	
	
	
	
	
	
	
	public E2_Grid  getStatusFakturierungsFristPruefungGutschrift() throws Exception {
		Check<BigDecimal> checker = (bd)-> {return (bd.compareTo(BigDecimal.ZERO)<0);};
		return getStatusFakturierungsFrist(VPOS_TPA_FUHRE.id_adresse_start,
											is_yes_db_val(VPOS_TPA_FUHRE.lademenge_gutschrift)?VPOS_TPA_FUHRE.anteil_lademenge_lief:VPOS_TPA_FUHRE.anteil_ablademenge_lief, 
											VPOS_TPA_FUHRE.einzelpreis_ek,
											VPOS_TPA_FUHRE.datum_aufladen,
											checker,
											false);
		
	}

	
	public E2_Grid  getStatusFakturierungsFristPruefungRechnung() throws Exception {
		Check<BigDecimal> checker = (bd)-> {return (bd.compareTo(BigDecimal.ZERO)>0);};
		return getStatusFakturierungsFrist(VPOS_TPA_FUHRE.id_adresse_ziel,
											is_yes_db_val(VPOS_TPA_FUHRE.ablademenge_rechnung)?VPOS_TPA_FUHRE.anteil_ablademenge_abn:VPOS_TPA_FUHRE.anteil_lademenge_abn, 
											VPOS_TPA_FUHRE.einzelpreis_vk,
											VPOS_TPA_FUHRE.datum_abladen,
											checker,
											true);
		
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
					.AND(new is_null(VPOS_RG.id_vpos_tpa_fuhre_ort_zugeord))
					.AND(new vgl_YN(VPOS_RG.deleted, false))
					.AND(new vglParam(VPOS_RG.lager_vorzeichen))
					.AND(new vglParam(VPOS_RG.id_vpos_tpa_fuhre_zugeord))
					;
		
		SqlStringExtended sql = new SqlStringExtended(sel.s())
										._addParameter(new Param_Long(vk?-1:1))
										._addParameter(new Param_Long(this.getLongDbValue(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)));
		
		RecList21 rl = new RecList21(_TAB.vpos_rg)._fill(sql);
		
		if (rl.size()==1) {
			return new Rec21_VposRg(rl.get(0));
		}
		
		return null;
	}
	
	
	
	private E2_Grid getStatusFakturierungsFrist(IF_Field feldAdresse, IF_Field fieldMenge, IF_Field fieldPreis,IF_Field fieldDatum, Check<BigDecimal> checker, boolean vk) throws Exception {

	
		//jetzt die grenzen aus den mandantensettings rauslesen
		Long lYellowBorder = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_GELB.getCheckedValue());
		Long lRedBorder = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_ROT.getCheckedValue());

		
		Long idAdresse = this.getLongDbValue(feldAdresse);
		Date datum = (Date) this.getRawVal(fieldDatum);

		String s_datum = datum!=null?new SimpleDateFormat("dd.MM.yyyy").format(datum):"-";
		
		if (idAdresse == null) {
			
			return this.getInfoSchild("?", new E2_ColorAlarm(), null, "Error: Adress was not found: Code:<162c9676-9404-11e9-bc42-526af7764f64>",true);
			
		} else if (this.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung)
				|| this.getRawVal(VPOS_TPA_FUHRE.id_adresse_fremdauftrag) != null
				|| this.is_yes_db_val(VPOS_TPA_FUHRE.deleted) || this.is_yes_db_val(VPOS_TPA_FUHRE.ist_storniert)
				|| this.getUfs(feldAdresse).equals(bibALL.get_ID_ADRESS_MANDANT())) {
				
			return this.getInfoSchild("-", null, null, S.ms("Fuhre: "+(vk?"Rechte Seite: ":"Linke Seite: ")+" Kein Fakturabeleg").CTrans(),false);

		} else {
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
						
						return this.getInfoSchild("<?>", null, new E2_FontPlain(-2), "Fuhre "+idAdresse+" kann noch nicht bewertet werden, da entweder Menge, Preis oder Datum fehlt!",true);

					} else {
						
						boolean hatVerlaengerteFaktFristSchalterImKontrakt = vk?this.hatVerlaengerteFaktFristSchalterImKontraktWennForderungVK():this.hatVerlaengerteFaktFristSchalterImKontraktWennForderungEK();

						if (hatVerlaengerteFaktFristSchalterImKontrakt) {
						
							// jetzt nachsehen, ob zum leistungszeitpunkt eine kreditversicherung existiert
	//						SqlStringExtended query = KV_Lib.getSQLExtKreditversicherungMitFakturierungsFrist("MIN(" + KREDITVERS_KOPF.fakturierungsfrist.tnfn() + ")",	this.getLongDbValue(feldAdresse), (Date) this.getRawVal(fieldDatum));
							SqlStringExtended query = KV_Lib.getSQLExtKreditversicherungMitFakturierungsFrist(KREDITVERS_KOPF.fakturierungsfrist.tnfn(),	this.getLongDbValue(feldAdresse), (Date) this.getRawVal(fieldDatum));
	//						String[][] kvTage = bibDB.EinzelAbfrageInArray(query);
							VEK<Object[]> fristen = bibDB.getResultLines(query, true);
	
							if (fristen == null) {
	
								return this.getInfoSchild("@ERROR", new E2_ColorAlarm(), null, "Error querying Kreditversicherungen for idadresse: " + idAdresse +"Code:<71c04b48-9406-11e9-bc42-526af7764f64>\n"+query.getSqlString(),true);
	
							} else {
								if (fristen.size() == 0) {
	
									String meldung = S.ms("Adresse").CTrans()+" "+idAdresse+" "+S.ms("besitzt keine Kreditversicherungen mit verlängerter Fakturierungsfrist, die am")+" "+s_datum+" "+S.ms(" gilt!");
									return this.getInfoSchild("-", null, null,meldung,false);
	
								} else if (fristen.size() >1)  {
									String meldung = S.ms("Adresse").CTrans()+" "+idAdresse+" "+S.ms("besitzt mehrere Kreditversicherungen mit verlängerter Fakturierungsfrist, die am")+" "+s_datum+" "+S.ms(" gelten ! Das ist ein Fehler !!");
									return this.getInfoSchild("<Fehler>", new E2_ColorAlarm(), null,meldung,false);
									
								} else {
	
									Long f_frist = ((BigDecimal)fristen.get(0)[0]).longValue();
									if (f_frist==0) {
										String meldung = S.ms("Adresse").CTrans()+" "+idAdresse+" "+S.ms("besitzt keine Kreditversicherungen mit verlängerter Fakturierungsfrist, die am")+" "+s_datum+" "+S.ms(" gilt!");
										return this.getInfoSchild("-", null, null,meldung,false);
									} else {
									
										BigDecimal bdEndPreis = this.getBigDecimalDbValue(fieldMenge).multiply(this.getBigDecimalDbValue(fieldPreis),new MathContext(2, RoundingMode.HALF_UP));
										
										if (checker.isOk(bdEndPreis)) {
		
											//hier wurde ein zutreffender sachverhalt festgestellt
											
											long faktFrist = f_frist;
											Long tageBisHeute = TimeTools.getDiffInTage(datum, new Date()).longValue();
											Long daysRest = faktFrist - tageBisHeute;
											String tooltipsBase = S.ms("Leistungsdatum").CTrans()+": "+s_datum+"\n";
											tooltipsBase = tooltipsBase+S.ms("Zeit seit Leistungsdatum bis heute").CTrans()+": "+tageBisHeute+" Tage\n";
											tooltipsBase = tooltipsBase+S.ms("Verlängerte Fakturierungsfrist").CTrans()+": "+faktFrist+" Tage\n";
		
											
											// hier jetzt den abrechnungszustand checken
											if (O.NN(this.getLongDbValue(VPOS_TPA_FUHRE.status_buchung), 0l).longValue() == myCONST.STATUS_FUHRE__GANZGEBUCHT) {
												
												String tooltips = new String(tooltipsBase);
												tooltips = tooltips+ S.ms("\nFuhre wurde verbucht.").CTrans();
												E2_Grid g1 = this.getInfoSchild("OK", Color.GREEN, new E2_FontPlain(-2), tooltips, true);
												
												// dann den rechnungsbeleg checken
												Rec21_VposRg rg = this.getRecPosWithoutStorno(vk);
		
												E2_Grid g2 = new E2_Grid();
												
												
												if (rg == null) {
													
													g2 = this.getInfoSchild("Error", new E2_ColorAlarm(), null, S.ms("Fehler! Obwohl Fuhre verbucht ist, kann die relevante Rechnungsposition nicht identifiziert werden").CTrans(), true);
													
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
		
												return this.getInfoSchild(""+daysRest, col, new E2_FontPlain(-2), tooltips, true);
		
											}
										} else {
											return this.getInfoSchild("-", null, null,S.ms("Beleg ist keine Forderung !").CTrans(),false);
										}
									}
								}
	
							}
						} else {
							return this.getInfoSchild("-", null, null,S.ms("Besitzt keinen Kontrakt mit Fakturierungsfrist-Schalter!").CTrans(),false);
						}
					}
				}
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

	
	
	
	/**
	 * returns number of nondelete fuhrenorte or null on error
	 * @author martin
	 * @date 10.07.2019
	 *
	 * @return
	 */
	public Long getCountOfFuhrenOrte() {
		Long fo_anzahl = null;
		
		try {
			SEL sel = new SEL("COUNT(*)").FROM(_TAB.vpos_tpa_fuhre_ort).WHERE(new vglParam(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre)).AND(new vgl_YN(VPOS_TPA_FUHRE_ORT.deleted, false));

			SqlStringExtended sql = new SqlStringExtended(sel.s());
			sql._addParameter(new Param_Long(this.getIdLong()));
			
			VEK<Object[] > results = bibDB.getResultLines(sql, true);
			if (results.size()==1 && results.get(0).length==1) {
				BigDecimal bdZahl = (BigDecimal)results.get(0)[0];
				fo_anzahl = bdZahl.longValueExact();
			} else {
				fo_anzahl = null;
			}
		} catch (Exception e) {
			fo_anzahl = null;
			e.printStackTrace();
		}
		
		return fo_anzahl;
	}
	
	
	
	
	
	
	
	public enum TYPLKW {
		UNDEFINIERT
		,LKW_OHNE_ANHAENGER
		,LKW_MIT_ANHAENGER
	}
	
	public TYPLKW getTypLKW() {
	   TYPLKW typ = TYPLKW.UNDEFINIERT;
	   
	   try {
		if (S.isAllFull(this.getUfs(VPOS_TPA_FUHRE.transportkennzeichen),this.getUfs(VPOS_TPA_FUHRE.anhaengerkennzeichen))) {
			   typ = TYPLKW.LKW_MIT_ANHAENGER;
		   } else if (S.isFull(this.getUfs(VPOS_TPA_FUHRE.transportkennzeichen))) {
			   typ = TYPLKW.LKW_OHNE_ANHAENGER;
		   } else {
			   typ = TYPLKW.UNDEFINIERT;
		   }
		} catch (myException e) {
			e.printStackTrace();
		}
	   
	   return typ;
	}
	
	
	
	/**
	 * TransportHomogen heisst nach aller wahrscheinlichkeit nur eine fuhre, da in der gesamten fuhren-fuhrenort-struktur nur je ein ort links und rechts auftaucht
	 * @author martin
	 * @date 10.07.2019
	 *
	 * @return
	 * 
	 */
	@SuppressWarnings("deprecation")
	public Boolean isTransportHomogen() {
		if (this.getCountOfFuhrenOrte()==0) {
			return true;
		}
		try {
			SEL sel = new SEL(VPOS_TPA_FUHRE_ORT.id_adresse_lager).ADD_Distinct()
					.FROM(_TAB.vpos_tpa_fuhre_ort)	.WHERE(new vglParam(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre))
													.AND(new vglParam(VPOS_TPA_FUHRE_ORT.def_quelle_ziel))
													.AND(new vgl_YN(VPOS_TPA_FUHRE_ORT.deleted, false));

			SqlStringExtended sqlEK = new SqlStringExtended(sel.s());
			sqlEK._addParameter(new Param_Long(this.getIdLong()));
			sqlEK._addParameter(new Param_String("","EK"));
			
			SqlStringExtended sqlVK = new SqlStringExtended(sel.s());
			sqlVK._addParameter(new Param_Long(this.getIdLong()));
			sqlVK._addParameter(new Param_String("","VK"));
			

			VEK<Object[]>  idsAdresseFuoLadeseite = bibDB.getResultLines(sqlEK, true);
			VEK<Object[]>  idsAdresseFuoAbladeseite = bibDB.getResultLines(sqlVK, true);
			
			VEK<Long> vIdsEK = new VEK<Long>()._a(this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_lager_start));
			VEK<Long> vIdsVK = new VEK<Long>()._a(this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_lager_ziel));
			
			for (Object[] arr: idsAdresseFuoLadeseite) {
				vIdsEK._addIfNotIn( ((BigDecimal)arr[0]).longValueExact());
			}

			for (Object[] arr: idsAdresseFuoAbladeseite) {
				vIdsVK._addIfNotIn( ((BigDecimal)arr[0]).longValueExact());
			}
			
			if (vIdsEK.size()==1 && vIdsVK.size()==1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		return false;
	}
	
	
	/**
	 * gibt die ID der Mengeneinheit zurück
	 * @author manfred
	 * @date 19.07.2019
	 *
	 * @return
	 */
	public Long getIDEinheit(){
		Long idEinheit = null;
		try {
			idEinheit = this.get_up_Rec21(ARTIKEL.id_artikel).get_raw_resultValue_Long(ARTIKEL.id_einheit);
			
		} catch (myException e) {
			idEinheit  = null;
		}
		return idEinheit;
	}
	
	
	/**
	 * berechnet die gesamte ablademenge in tonnen
	 * @author martin
	 * @date 11.07.2019
	 *
	 * @return summe aller abladegewichte in tonnen
	 */
	public BigDecimal getAbladeMengeInTonnen() {
		BigDecimal bdTonnen = new BigDecimal(0);
		
		try {
			VEK<BigDecimal>  vTonnen = new VEK<>();
			
			BigDecimal mengendivisor = (BigDecimal) this.get_up_Rec21(ARTIKEL.id_artikel).getRawVal(ARTIKEL.mengendivisor);
			BigDecimal bdMengeHauptfuhre = (BigDecimal)this.getRawVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, BigDecimal.ZERO);
			
			vTonnen._a(bdMengeHauptfuhre.divide(mengendivisor, MathContext.DECIMAL128));
			
			SEL sel = new SEL("*").FROM(_TAB.vpos_tpa_fuhre_ort).WHERE(new vglParam(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre))
																.AND(new vglParam(VPOS_TPA_FUHRE_ORT.def_quelle_ziel))
																.AND(new vgl_YN(VPOS_TPA_FUHRE_ORT.deleted, false));

			SqlStringExtended sqlVK = new SqlStringExtended(sel.s());
			sqlVK._addParameter(new Param_Long(this.getIdLong()));
			sqlVK._addParameter(new Param_String("","VK"));

			RecList21 rlFuhrenOrteAblade = new RecList21(_TAB.vpos_tpa_fuhre_ort)._fill(sqlVK);
			
			for (Rec21 rFO: rlFuhrenOrteAblade) {
				BigDecimal mengendivisorFO = (BigDecimal) rFO.get_up_Rec21(ARTIKEL.id_artikel).getRawVal(ARTIKEL.mengendivisor);
				BigDecimal bdMengeFO =       (BigDecimal) rFO.getRawVal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge, BigDecimal.ZERO);
				
				vTonnen._a(bdMengeFO.divide(mengendivisorFO, MathContext.DECIMAL128));
			}
			

			bdTonnen = new BigDecimal(0);
			for (BigDecimal bd: vTonnen) {
				bdTonnen = bdTonnen.add(bd);
			}
			bdTonnen.setScale(6, RoundingMode.HALF_UP);
			
			
		} catch (Exception e) {
			try {
				DEBUG._print("ID-Fuhre:"+this.getId());
			} catch (myException e1) {
			}
			e.printStackTrace();
			bdTonnen=null;
		}
		
		return bdTonnen;
	}
	
	
	/**
	 * liest das bestehende CO2-Profil zur Fuhre aus oder gibt ein leeres zurueck, wenn noch keines vorhanden ist
	 * @author martin
	 * @date 11.07.2019
	 *
	 * @return
	 */
	public Rec21SaveOnlyChanged getRecFuhrenCo2Profil() {
	
		try {
			Rec21SaveOnlyChanged ret = new Rec21SaveOnlyChanged(_TAB.fuhren_co2_profil);
			
			SEL sel = new SEL("*").FROM(_TAB.fuhren_co2_profil).WHERE(new vglParam(FUHREN_CO2_PROFIL.id_vpos_tpa_fuhre));

			SqlStringExtended sql = new SqlStringExtended(sel.s());
			sql._addParameter(new Param_Long(this.getIdLong()));
			ret._fill_sql(sql);

			return ret;
			
		} catch (myException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	/**
	 * return s normalized kennzeichen lkw (OGMP1009) oder null on empty value or error
	 * @author martin
	 * @date 11.07.2019
	 *
	 * @return
	 */
	public String getNormalizedLkwKennzeichen() {
		String positivListe = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ0123456789";
		
		String kennzeichenNormalized = null;
		
		try {
			String kennzeichenFuhre = this.getUfs(VPOS_TPA_FUHRE.transportkennzeichen);
			
			if (S.isFull(kennzeichenFuhre)) {
				kennzeichenNormalized = bibTEXT.CleanStringKeepOnlyCharList(kennzeichenFuhre.toUpperCase().trim(), positivListe,true);
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return kennzeichenNormalized;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public boolean isOwnTransport() throws myException {
		boolean ownTransport = false;
		
		VEK<String> normalizedLKWs = (VEK<String>)ENUM_MANDANT_SESSION_STORE.ALLE_EIGENEN_KENNZEICHEN.getValueFromSession();
		
		String ownKennzeichen = S.NN(this.getNormalizedLkwKennzeichen());
		
		if (S.isFull(ownKennzeichen)) {
			for (String mandantenLKW: normalizedLKWs) {
				if (mandantenLKW.length()>5 && ownKennzeichen.startsWith(mandantenLKW)) {
					ownTransport=true;
					break;
				}
			}
		}
		
		return ownTransport;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 11.07.2019
	 *
	 * @return s VEK<Long> with idAdresses where coordinates are missing or null on error
	 */
	public VEK<Long> getAdressenOhneGeocodierung() {
		
		try {
			Rec21_adresse adresseStart =  new Rec21_adresse(this.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_lager_start,ADRESSE.id_adresse,true));
			Rec21_adresse adresseZiel =   new Rec21_adresse(this.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_lager_ziel,ADRESSE.id_adresse,true));
			
			VEK<Long> ret = new VEK<>();
			
			if (!S.isAllFull(adresseStart.getUfs(ADRESSE.longitude), adresseStart.getUfs(ADRESSE.latitude))) {
				ret._a(adresseStart._getMainAdresse().getIdLong());
			}
			if (!S.isAllFull(adresseZiel.getUfs(ADRESSE.longitude), adresseZiel.getUfs(ADRESSE.latitude))) {
				ret._a(adresseZiel._getMainAdresse().getIdLong());
			}
			
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	
	
	
	
	
	/**
	 * returns nondeleted fuhrenorte or null on error
	 * @author martin
	 * @date 10.07.2019
	 *
	 * @return VEK with records or emtpy VEK or null on error
	 */
	public VEK<Rec21_VposTpaFuhreOrt> getFuhrenOrte() {
		try {
			SEL sel = new SEL("*").FROM(_TAB.vpos_tpa_fuhre_ort).WHERE(new vglParam(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre)).AND(new vgl_YN(VPOS_TPA_FUHRE_ORT.deleted, false));

			SqlStringExtended sql = new SqlStringExtended(sel.s());
			sql._addParameter(new Param_Long(this.getIdLong()));
			
			RecList21 fuhrenOrt = new RecList21(_TAB.vpos_tpa_fuhre_ort)._fill(sql);
			
			VEK<Rec21_VposTpaFuhreOrt> ret = new VEK<Rec21_VposTpaFuhreOrt>();
			for (Rec21 r: fuhrenOrt) {
				Rec21_VposTpaFuhreOrt rf = new Rec21_VposTpaFuhreOrt(r)._setRecFu(this);
				ret._a(rf);
			}
			return ret;
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
//	/**
//	 * 
//	 * @author martin
//	 * @date 29.11.2019
//	 * Erzeugt ein PAIR<VEK<Rec21>,VEK<Rec21>>. Der Vektor besteht aus
//	 * [n][0]  immer eine eingangsseite und an [n][1] eine ausgangsseite steht
//	 * Ist eine position eine forderung, dann steht entweder ein Rec21_VposTpaFuhre (in der ersten Zeile) oder eine Rec21_VposTpaFuhreOrt ab zeile 2,
//	 * wobei ab zeile 2 immer eine position (je nach def_quelle_ziel) leer ist.
//	 *
//	 *
//	 *
//	 * @return  bei fehler null. 
//	 */
//	public Rec21[][] getAnzahlRechnungsPositionen() {
//		try {
//			
//			if (this.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung) || this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)!=null) {
//				return new Rec21[0][2];
//			}
//			
//
//			boolean ekIsLager = this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_start).toString().equals(bibALL.get_ID_ADRESS_MANDANT());
//			boolean vkIsLager = this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_ziel).toString().equals(bibALL.get_ID_ADRESS_MANDANT());
//			
//			//feststellen, ob es einen negativen einkauf oder positiven verkauf gibt
//			MyBigDecimal eingangsMenge = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief, this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_planmenge_lief)),new MyBigDecimal(0));
//			MyBigDecimal ausgangsMenge = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_planmenge_abn)),new MyBigDecimal(0));
//			
//			//wenn der preis noch null ist, wird er erstmal positiv prognostiziert
//			MyBigDecimal einkaufsPreis = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.einzelpreis_ek), new MyBigDecimal(1l));
//			MyBigDecimal verkaufsPreis =  O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.einzelpreis_vk), new MyBigDecimal(1l));
//			
//
//			if (eingangsMenge.isOK() && ausgangsMenge.isOK() && einkaufsPreis.isOK() && verkaufsPreis.isOK()) {
//
//				Rec21[][] partFuhre = new Rec21[1][2];
//
//				BigDecimal bdFaktorEinkauf = eingangsMenge.get_bdWert().multiply(einkaufsPreis.get_bdWert());
//				BigDecimal bdFaktorVerkauf = ausgangsMenge.get_bdWert().multiply(verkaufsPreis.get_bdWert());
//				if (bdFaktorEinkauf.compareTo(BigDecimal.ZERO)<0 && !ekIsLager) {
//					partFuhre[0][0] = this;
//				} 
//				
//				if (bdFaktorVerkauf.compareTo(BigDecimal.ZERO)>0 && !vkIsLager) {
//					partFuhre[0][1] = this;
//				}
//
//				VEK<Rec21_VposTpaFuhreOrt> rlOrte = this.getFuhrenOrte();
//				
//				Rec21[][] partFuhrenOrte = new Rec21[rlOrte.size()][2];
//
//				int iCount = 0;
//				for (Rec21_VposTpaFuhreOrt ort: rlOrte) {
//					Boolean isRechnung = ort.isRechnungsPosition();
//					if (isRechnung!=null) {
//						if (isRechnung) {
//							if (ort.isEK()) {
//								partFuhrenOrte[iCount][0] = ort;
//							} else {
//								partFuhrenOrte[iCount][1] = ort;
//							}
//						}
//					} else {
//						return null;    //fehlerstatus
//					}
//					iCount++;
//				}
//				
//				Rec21[][] arrayRet = new Rec21[1+rlOrte.size()][2];
//				
//				arrayRet[0][0] = partFuhre[0][0];
//				arrayRet[0][1] = partFuhre[0][1];
//				
//				for (int i=0; i<partFuhrenOrte.length;i++) {
//					arrayRet[i+1][0] = partFuhrenOrte[i][0];
//					arrayRet[i+1][1] = partFuhrenOrte[i][1];
//				}
//				return arrayRet;
//				
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//	}

	public ENUM_DEF_ABRECHNUNGSTYP getAbrechTypLadeseite() throws Exception {
		if (this.abrechTypAbladeseite==null || this.abrechTypLadeseite==null) {
			initAbrechnungsTypen();
		}
		return abrechTypLadeseite;
	}

	public ENUM_DEF_ABRECHNUNGSTYP getAbrechTypAbladeseite()  throws Exception {
		if (this.abrechTypAbladeseite==null || this.abrechTypLadeseite==null) {
			initAbrechnungsTypen();
		}
		return abrechTypAbladeseite;
	}
	
	private void initAbrechnungsTypen() throws Exception {
		if (this.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung) || this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)!=null) {
			this.abrechTypLadeseite=ENUM_DEF_ABRECHNUNGSTYP.LEER_ZU_NULL;
			this.abrechTypAbladeseite=ENUM_DEF_ABRECHNUNGSTYP.LEER_ZU_NULL;
		} else {

			boolean ekIsLager = this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_start).toString().equals(bibALL.get_ID_ADRESS_MANDANT());
			boolean vkIsLager = this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_ziel).toString().equals(bibALL.get_ID_ADRESS_MANDANT());

			if (ekIsLager) {
				this.abrechTypLadeseite=ENUM_DEF_ABRECHNUNGSTYP.LAGER;
			} else {
				MyBigDecimal eingangsMenge = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief, this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_planmenge_lief)),new MyBigDecimal(0));
				MyBigDecimal einkaufsPreis = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.einzelpreis_ek), new MyBigDecimal(1l));
				BigDecimal   bdFaktorEinkauf = eingangsMenge.get_bdWert().multiply(einkaufsPreis.get_bdWert());
				if (bdFaktorEinkauf.compareTo(BigDecimal.ZERO)==0) {
					abrechTypLadeseite=ENUM_DEF_ABRECHNUNGSTYP.LEER_ZU_NULL;
				} else if (bdFaktorEinkauf.compareTo(BigDecimal.ZERO)<0) {
					abrechTypLadeseite=ENUM_DEF_ABRECHNUNGSTYP.FORDERUNG;
				} else {
					abrechTypLadeseite=ENUM_DEF_ABRECHNUNGSTYP.VERBINDLICHKEIT;
				}
			}
			
			if (vkIsLager) {
				this.abrechTypAbladeseite=ENUM_DEF_ABRECHNUNGSTYP.LAGER;
			} else {
				MyBigDecimal ausgangsMenge = O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_planmenge_abn)),new MyBigDecimal(0));
				MyBigDecimal verkaufsPreis =  O.NN(this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.einzelpreis_vk), new MyBigDecimal(1l));
				BigDecimal bdFaktorVerkauf = ausgangsMenge.get_bdWert().multiply(verkaufsPreis.get_bdWert());
				if (bdFaktorVerkauf.compareTo(BigDecimal.ZERO)==0) {
					abrechTypAbladeseite=ENUM_DEF_ABRECHNUNGSTYP.LEER_ZU_NULL;
				} else if (bdFaktorVerkauf.compareTo(BigDecimal.ZERO)>0) {
					abrechTypAbladeseite=ENUM_DEF_ABRECHNUNGSTYP.FORDERUNG;
				} else {
					abrechTypAbladeseite=ENUM_DEF_ABRECHNUNGSTYP.VERBINDLICHKEIT;
				}
			}
		}

	}
	
	
	
	public Date getLeistungsDatumLadeSeite() throws Exception {
		return this.getDateDbValue(VPOS_TPA_FUHRE.datum_aufladen);
	}
	public Date getLeistungsDatumAbladeSeite() throws Exception {
		return this.getDateDbValue(VPOS_TPA_FUHRE.datum_abladen);
	}
	
	

	
	public Rec21_ZahlungsBedingung getZahlungsBedingungLadeSeite() throws Exception {
		if (!zahlungsBedingungLadeseiteErmittelt ) {
			zahlungsBedingungLadeseiteErmittelt=true;
			//zuerst kontrakt
			try { zahlungsBedingungLadeseite = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE.id_vpos_kon_ek, VPOS_KON.id_vpos_kon, true)
													.get_up_Rec21(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen)); } catch (Exception e) {}
			
			//dann angebot
			if (zahlungsBedingungLadeseite==null) {
				try { zahlungsBedingungLadeseite = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE.id_vpos_std_ek, VPOS_STD.id_vpos_std, true)
						  .get_up_Rec21(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen)); } catch (Exception e) {}
			}
			
			//dann stammsatz
			if (zahlungsBedingungLadeseite==null) {
				try {zahlungsBedingungLadeseite  = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_start, ADRESSE.id_adresse,true)
													.get_up_Rec21(ADRESSE.id_zahlungsbedingungen, ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen, true)); } catch (Exception e) {}
			}
		}
		
		return zahlungsBedingungLadeseite;
	}
	
	
	public Rec21_ZahlungsBedingung getZahlungsBedingungAbladeSeite() throws Exception {
		if (!zahlungsBedingungAbladeseiteErmittelt ) {
			zahlungsBedingungAbladeseiteErmittelt=true;
			//zuerst kontrakt
			try { zahlungsBedingungAbladeseite = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE.id_vpos_kon_vk, VPOS_KON.id_vpos_kon, true)
													.get_up_Rec21(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen)); } catch (Exception e) {}
			
			//dann angebot
			if (zahlungsBedingungAbladeseite==null) {
				try { zahlungsBedingungAbladeseite = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE.id_vpos_std_vk, VPOS_STD.id_vpos_std, true)
						  .get_up_Rec21(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen)); } catch (Exception e) {}
			}
			
			//dann stammsatz
			if (zahlungsBedingungAbladeseite==null) {
				try {zahlungsBedingungAbladeseite  = new Rec21_ZahlungsBedingung(this.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_ziel, ADRESSE.id_adresse,true)
													.get_up_Rec21(ADRESSE.id_zahlungsbedingungen_vk, ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen, true)); } catch (Exception e) {}
			}
		}
		
		return zahlungsBedingungAbladeseite;
	}

	
	
	
	public Rec21_VposRg getAktiveRG_PositionLadeSeite() throws Exception {
		if (!aktiveRG_Positions_Ermittelt) {
			aktiveRG_Positions_Ermittelt = 	true;
			ermittleRgPositions();
		}
		return aktiveRG_PositionLadeSeite;
	}


	
	
	
	public Rec21_VposRg getAktiveRG_PositionAbladeSeite() throws Exception  {
		if (!aktiveRG_Positions_Ermittelt) {
			aktiveRG_Positions_Ermittelt = 	true;
			ermittleRgPositions();
		}
		return aktiveRG_PositionAbladeSeite;
	}
	
	
	
	private void ermittleRgPositions() throws myException {
		SEL selRg = new SEL(_TAB.vpos_rg).FROM(_TAB.vpos_rg).WHERE(	new vglParam(VPOS_RG.id_vpos_tpa_fuhre_zugeord))
				.AND(	new VglNull(VPOS_RG.id_vpos_tpa_fuhre_ort_zugeord))
				.AND(	new VglNull(VPOS_RG.id_vpos_rg_storno_vorgaenger))
				.AND(	new VglNull(VPOS_RG.id_vpos_rg_storno_nachfolger))
				.AND(	new vgl_YN(VPOS_RG.deleted,false))
				;
		
		SqlStringExtended sql = new SqlStringExtended(selRg.s());
		
		sql._addParameters(new VEK<ParamDataObject>()._a(new Param_Long(this.getId())));
		
		RecList21 rlRg = new RecList21(_TAB.vpos_rg)._fill(sql);
		
		if (rlRg.size()==0) {
			aktiveRG_PositionLadeSeite = null;
			aktiveRG_PositionAbladeSeite = null;
		} else if (rlRg.size()>0 && rlRg.size()<=2) {
			for (Rec21 r: rlRg) {
				if (r.getLongDbValue(VPOS_RG.lager_vorzeichen).longValue()==1) {
					aktiveRG_PositionLadeSeite = new Rec21_VposRg(r);
				} else {
					aktiveRG_PositionAbladeSeite = new Rec21_VposRg(r);
				}
			}
		} else {
			throw new myException("Error: <173b2f24-14f0-11ea-8d71-362b9e155667>: Only 2 active RG-Positions for main-fuhre are possible!");
		}
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 14.02.2020
	 *
	 * @return true, wenn EK Seite eine forderung ergibt im Kontrakt der schalter verlangerte FaktFrist eingeschaltet ist
	 * @throws Exception
	 */
	public boolean hatVerlaengerteFaktFristSchalterImKontraktWennForderungEK() throws Exception {
		boolean bhatVerlaengerteFaktFristSchalterImKontrakt = false;
		
		boolean isForderung = true;    //falls unbestimmt, dann wie eine forderung behandeln
		
		BigDecimal preis = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.einzelpreis_ek);
		if (preis != null && preis.compareTo(BigDecimal.ZERO)>=0) {
			//positiver preis im EK heisst verbindlichkeit
			isForderung = false;
		}
		
		if (isForderung) {
    		Rec21 vposKontrakt = this.get_up_Rec21(VPOS_TPA_FUHRE.id_vpos_kon_ek, VPOS_KON.id_vpos_kon,true);
    		if (vposKontrakt!=null) {
    			Rec21 vPosKontrakt = new Rec21_VPosKon(vposKontrakt).getVposKonTrakt();
    			bhatVerlaengerteFaktFristSchalterImKontrakt = vPosKontrakt.is_yes_db_val(VPOS_KON_TRAKT.verlaengerte_fakt_frist);
		    }
		}
		
		return bhatVerlaengerteFaktFristSchalterImKontrakt;
	}
	
	/*
	 * 
	 * @author martin
	 * @date 14.02.2020
	 *
	 * @return true, wenn VK Seite eine forderung ergibt im Kontrakt der schalter verlangerte FaktFrist eingeschaltet ist
	 * @throws Exception
	 */
	public boolean hatVerlaengerteFaktFristSchalterImKontraktWennForderungVK() throws Exception {
		boolean bhatVerlaengerteFaktFristSchalterImKontrakt = false;
		
		boolean isForderung = true;    //falls unbestimmt, dann wie eine forderung behandeln
		
		BigDecimal preis = this.getBigDecimalDbValue(VPOS_TPA_FUHRE.einzelpreis_vk);
		if (preis != null && preis.compareTo(BigDecimal.ZERO)<0) {
			//negativer preis im VK heisst verbindlichkeit
			isForderung = false;
		}
		
		if (isForderung) {
	   		Rec21 vposKontrakt = this.get_up_Rec21(VPOS_TPA_FUHRE.id_vpos_kon_vk, VPOS_KON.id_vpos_kon,true);
	   		if (vposKontrakt!=null) {
	   			Rec21 vPosKontrakt = new Rec21_VPosKon(vposKontrakt).getVposKonTrakt();
	   			bhatVerlaengerteFaktFristSchalterImKontrakt = vPosKontrakt.is_yes_db_val(VPOS_KON_TRAKT.verlaengerte_fakt_frist);
		    }
		}
		
		return bhatVerlaengerteFaktFristSchalterImKontrakt;
	}
	
	
	//2021-03-01: martin
	public Boolean isFahrplanFuhre() throws Exception {
		return this.is_yes_db_val(VPOS_TPA_FUHRE.fuhre_aus_fahrplan);
	}
	
	
	//2021-03-01: martin
	public Boolean isGeplantInFahrplan() throws Exception {
		Boolean ret = false;
		
		if (this.getDateDbValue(VPOS_TPA_FUHRE.dat_fahrplan_fp)!=null && this.getLongDbValue(VPOS_TPA_FUHRE.id_maschinen_lkw_fp)!=null) {
			ret = true;
		}
		
		return ret;
	}
	
	
	public Boolean isFahrplanFuhreDurchgefuehrt() throws myException{
		Boolean istDurch = false;
		try {
			Long idAdresseMandant = bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null);
			
			boolean isWE = this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_ziel).longValue()==idAdresseMandant.longValue();
			boolean isWA = this.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_start).longValue()==idAdresseMandant.longValue();

			RecList22 rlWiegekarte = new RecList22(_TAB.wiegekarte)._fill(
					new SEL(_TAB.wiegekarte).FROM(_TAB.wiegekarte).WHERE(new vgl(WIEGEKARTE.id_vpos_tpa_fuhre, this.getIdLong().toString()))
					.AND(new vgl_YN(WIEGEKARTE.storno,false))
					);
			
			BigDecimal bdMenge = BigDecimal.ZERO;
			if (isWE && !isWA) {
				bdMenge = O.NN(this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_ablademenge_abn),BigDecimal.ZERO);
				istDurch = bdMenge.compareTo(BigDecimal.ZERO)>0 || rlWiegekarte.size()>0;
			} else if (!isWE && isWA){
				bdMenge = O.NN(this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_lademenge_lief),BigDecimal.ZERO);
				istDurch = bdMenge.compareTo(BigDecimal.ZERO)>0 || rlWiegekarte.size()>0;
			} else {  
				//unkare situation, abgeschlossen werst, wenn beide mengen >0 sind
				istDurch = O.NN(this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_lademenge_lief),BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0 &&
						O.NN(this.getBigDecimalDbValue(VPOS_TPA_FUHRE.anteil_ablademenge_abn),BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0;
				
			}
			
		} catch (myException e) {
			e.printStackTrace();
			throw e;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
		}
		return istDurch;
	}
	
	
	
	
	
}
