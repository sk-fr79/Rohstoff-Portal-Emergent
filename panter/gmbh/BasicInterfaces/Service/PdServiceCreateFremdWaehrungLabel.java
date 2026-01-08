/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 04.03.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;


import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_waehrung;

/**
 * @author martin
 * @date 04.03.2020
 *
 */
public class PdServiceCreateFremdWaehrungLabel {

	
	private int 	height = 				20;
	private int 	widthSpalte = 			40;
	private Color 	colorBackHighLight = 	new E2_ColorAlarm();
	private Color 	colorBackHighNormal = 	new E2_ColorBase();
	private Font  	font = 					new E2_FontPlain();
	
	private boolean embedded =				true;
	private boolean oneLine = 				false;
	
	/**
	 * @author martin
	 * @date 04.03.2020
	 *
	 */
	public PdServiceCreateFremdWaehrungLabel() {
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param idAdresse
	 * @param idVposKon
	 * @param idVposStdAngebot
	 * @return component (label or grid) with info on currency
	 * @throws Exception
	 */
	public Component createLabel(Long idAdresse, Long idVposKon, Long idVposStdAngebot) throws Exception {
		Component c = new RB_lab("");
		
		
		
		//zuerst die sonderfaelle abdecken (adresse null)
		//wenn die adress-id nicht gefunden wird, ein fragezeichen einblenden
		if (idAdresse== null || bibALL.get_RECORD_MANDANT()==null || bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null)==null) {
//			c = new E2_Grid()._bo_ddd()	._setSize(this.widthSpalte)
//					._a(new RB_lab("?")	._f(this.font)
//										._ttt(S.ms("Unbestimmte Währung !"))
//									, new RB_gld()._center_mid()._col_back(this.colorBackHighLight))
//					._setRowHight(this.height)
//					;
//			return c;
			return buildUndefAnzeige();
		} 
		
		//bei seiten mit lager des mandanten ein home-symbol anzeigen
		if (idAdresse!=null && idAdresse.longValue()==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null)) {
			c = new E2_Grid()._bo_ddd()	._setSize(this.widthSpalte)
										._setRowHight(this.height)
										._a(new RB_lab(E2_ResourceIcon.get_RI("lager.png"))
												._ttt(S.ms("Lagerseite, keine Währungsangabe")),new RB_gld()._center_mid())
											;
			return c;
		}
		
		
		
		//adresse muss da sein !
		if (idAdresse!=null) {
			
			PdServiceAdressCurrencyV2 pds = new PdServiceAdressCurrencyV2(idAdresse);
			
			//2021-03-04:martin: fehler, wenn eine idAdresse keine hauptadresse ist oder keine id_waehrung enthaelt, dann kann das system raus
			//deswegen einige vorgelagerte pruefungen
			//wenn es keine adresse gibt, oder die adresse keine waehrung hat, dann den status "unbestimmt" anzeigen
			if (pds.getRecAdress()==null || pds.getRecAdress().get_up_Rec21(WAEHRUNG.id_waehrung)==null) {
				return buildUndefAnzeige();
			}
			
			
			Rec21_waehrung   		wAdressBase = pds.getRecAdressBaseCurrency();
			Rec21_waehrung 			waehrungFremd=pds.getFirstFoundForeignCurrency();
			Rec21_waehrung 			waehrungSystem=pds.getRecSystemBaseCurrency();
			
			if (idVposKon!=null || idVposStdAngebot!=null) {
				//sowohl adresse als auch kontraktpos ist vorhanden
				
				Rec21 recVposKon = null;
				Rec21 recVposAng = null;
				if (idVposKon!=null) {
					recVposKon = new Rec21(_TAB.vpos_kon)._fill_id( idVposKon);
				} else {
					recVposAng = new Rec21(_TAB.vpos_std)._fill_id( idVposStdAngebot);
				}
				
				//relevant ist die waehrung des kontrakts
				String kontraktOderAngebot = null;
				Rec21_waehrung recWaehrungKontraktOderAngebot = null;
				if (recVposKon!=null) {
					recWaehrungKontraktOderAngebot = new Rec21_waehrung(recVposKon.get_up_Rec21(VPOS_KON.id_waehrung_fremd, WAEHRUNG.id_waehrung,true));
					kontraktOderAngebot="Kontrakt";
				} else {
					recWaehrungKontraktOderAngebot = new Rec21_waehrung(recVposAng.get_up_Rec21(VPOS_STD.id_waehrung_fremd, WAEHRUNG.id_waehrung,true));
					kontraktOderAngebot="Angebot";
				}
				
				if (recWaehrungKontraktOderAngebot.getId()==pds.getRecSystemBaseCurrency().getId()) {
					//kontrakt = basiswaehrung, dann einen label mit standard-hintergrund
					c = new E2_Grid()._bo_ddd()	._setSize(this.widthSpalte)
												._a(new RB_lab(recWaehrungKontraktOderAngebot.getSymbol("?"))
																	._f(this.font)
																	._ttt(S.ms("<").ut(pds.getRecAdress().__get_name1_ort()).ut(">: ").t("Im "+kontraktOderAngebot+" ist die Standard-Währung hinterlegt"))
													, new RB_gld()._center_mid()._col_back(this.colorBackHighNormal))
												._setRowHight(this.height)
												;
				} else {
					//kontrakt = fremdwaehrung,dann einen label mit highlight-hintergrund
					c = new E2_Grid()._bo_ddd()	._setSize(this.widthSpalte)
												._a(new RB_lab(recWaehrungKontraktOderAngebot.getSymbol("?"))
																	._f(this.font)
																	._ttt(S.ms("<").ut(pds.getRecAdress().__get_name1_ort()).ut(">: ").t("Im "+kontraktOderAngebot+" ist eine Fremdwährung hinterlegt"))
													, new RB_gld()._center_mid()._col_back(this.colorBackHighLight))
												._setRowHight(this.height)
												;
				}
				
			} else {
				if (waehrungFremd==null) {
					c = new E2_Grid()._bo_ddd()	._setSize(this.widthSpalte)
							._a(new RB_lab(pds.getRecAdressBaseCurrency().getSymbol("?"))
																	._f(this.font)
																	._ttt(S.ms("<").ut(pds.getRecAdress().__get_name1_ort()).ut(">: ").t("besitzt keine Fremdwährung"))
											, new RB_gld()._center_mid()._col_back(this.colorBackHighNormal))
							._setRowHight(this.height)
							;
				} else {
					MyE2_String info = S.ms("<").ut(pds.getRecAdress().__get_name1_ort()).ut(">: ")	.t("besitzt mindestens eine Fremdwährung" )
																									.ut(", ")
																									.t(" die Vorschlagswährung bei der Übernahme in die freien Positionen ist: ")
																									.ut(pds.getRecSystemBaseCurrency().getSymbol("?"));
					c = new E2_Grid()._bo_ddd()._setSize(this.widthSpalte)
							._a(
									new E2_Grid()._s(oneLine?2:1)	._a(new RB_lab(waehrungSystem.getSymbol("?"))._f(this.font)._ttt(info), new RB_gld()._ins(2,1,2,0)._center_mid())
														._a(new RB_lab("("+wAdressBase.getSymbol("?")+")")._f(this.font)._ttt(info), new RB_gld()._ins(2,0,2,1)._center_mid())
									,new RB_gld()._center_mid()._col_back(this.colorBackHighLight))
							._setRowHight(this.height)
							;
				}
			}
		}
		
		if (c!=null && c instanceof E2_Grid && embedded) {
			((E2_Grid)c)._w100()._bo_no();
		}
//		if (c!=null && c instanceof E2_Grid && oneLine) {
//			((E2_Grid)c)._setSize(100,100);
//		}

		
		return c;

	}

	
	
	private Component buildUndefAnzeige() {
		return new E2_Grid()._bo_ddd()	._setSize(this.widthSpalte)
				._a(new RB_lab("?")	._f(this.font)
									._ttt(S.ms("Unbestimmte Währung !"))
								, new RB_gld()._center_mid()._col_back(this.colorBackHighLight))
				._setRowHight(this.height)
				;

	}

	public PdServiceCreateFremdWaehrungLabel _setHeight(int height) {
		this.height = height;
		return this;
	}


	public PdServiceCreateFremdWaehrungLabel _setWidthSpalte(int widthSpalte) {
		this.widthSpalte = widthSpalte;
		return this;
	}


	public PdServiceCreateFremdWaehrungLabel _setColorBackHighLight(Color colorBackHighLight) {
		this.colorBackHighLight = colorBackHighLight;
		return this;
	}


	public PdServiceCreateFremdWaehrungLabel _setColorBackHighNormal(Color colorBackHighNormal) {
		this.colorBackHighNormal = colorBackHighNormal;
		return this;
	}


	public PdServiceCreateFremdWaehrungLabel _setFont(Font font) {
		this.font = font;
		return this;
	}


	public PdServiceCreateFremdWaehrungLabel _setEmbedded(boolean embedded) {
		this.embedded = embedded;
		return this;
	}


	public PdServiceCreateFremdWaehrungLabel _setOneLine(boolean oneLine) {
		this.oneLine = oneLine;
		return this;
	}



}
