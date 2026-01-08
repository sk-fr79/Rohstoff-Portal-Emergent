/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import panter.gmbh.BasicInterfaces.Service.PdServiceAdressCurrency;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_waehrung;

/**
 * @author martin
 *
 */
public abstract class Warenbewegung_InfoBlockFremdWaehrung extends E2_Grid {  

	
	public Warenbewegung_InfoBlockFremdWaehrung() {
		super();
	}
	

	public void fill() {
		this._clear();
			
		try {
			
			VEK<Triple<Long>> v_triple = this.getIdAdressAndIdVposKonAndIdVposAngebot(); 
			
			this._clear()._setRowHight(this.getBlockHeigth());
			
			if (v_triple.size()>=1) {   //fuhre: size=2, fuhrenort: size=1
				this._setSize(this.getSpaltenBreite());
				int[] i_breiten = new int[v_triple.size()];
				for (int i=0;i<v_triple.size();i++) {
					i_breiten[i]=this.getSpaltenBreite();
				}
				this._setSize(i_breiten)._setRowHight(this.getBlockHeigth());

				for (Triple<Long> p: v_triple) {
					this._a(this.getInfoComponent(p));
				}
				
				
			} else {
				throw new myException(this,"Error querying Adress from mask");
			}
			
			
			
		} catch (myException e) {
			e.printStackTrace();
			//fehler
			this._clear();
			this._setSize(this.getSpaltenBreite())._setRowHight(this.getBlockHeigth());
			this._a("@ERR", new RB_gld()._col_back_alarm()._span(this.getSize()));
		}
	}
	
	
	private Component getInfoComponent(Triple<Long> p) throws myException {
		Component c = new RB_lab("");
		
		//zuerst die sonderfaelle abdecken (adresse null)
		//wenn die adress-id nicht gefunden wird, ein fragezeichen einblenden
		if (p.getVal1()== null || bibALL.get_RECORD_MANDANT()==null || bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null)==null) {
//			c = new E2_Grid()._bo_ddd()	._setSize(this.getSpaltenBreite())
//					._a(new RB_lab("?")	._f(this.getBlockTextFont())
//										._ttt(S.ms("Unbestimmte Währung !"))
//									, new RB_gld()._center_mid()._col_back(this.getBlockBackColorHighlight()))
//					._setRowHight(this.getBlockHeigth())
//					;
			return buildUndefAnzeige();
		} 
		
		//bei seiten mit lager des mandanten ein home-symbol anzeigen
		if (p.getVal1()!=null && p.getVal1().longValue()==bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null)) {
			c = new E2_Grid()._bo_ddd()	._setSize(this.getSpaltenBreite())
										._setRowHight(this.getBlockHeigth())
										._a(new RB_lab(E2_ResourceIcon.get_RI("lager.png"))
												._ttt(S.ms("Lagerseite, keine Währungsangabe")),new RB_gld()._center_mid())
											;
			return c;
		}
		
		
		
		//adresse muss da sein !
		if (p.getVal1()!=null) {
			
			PdServiceAdressCurrency pds = new PdServiceAdressCurrency(p.getVal1());
			
			//2021-03-04:martin: fehler, wenn eine idAdresse keine hauptadresse ist oder keine id_waehrung enthaelt, dann kann das system raus
			//deswegen einige vorgelagerte pruefungen
			//wenn es keine adresse gibt, oder die adresse keine waehrung hat, dann den status "unbestimmt" anzeigen
			if (pds.getRecAdress()==null || pds.getRecAdress().get_up_Rec21(WAEHRUNG.id_waehrung)==null) {
				return buildUndefAnzeige();
			}

			
			
			Rec21_waehrung   		wAdressBase = pds.getRecAdressBaseCurrency();
			Rec21_waehrung 			waehrungFremd=pds.getFirstFoundForeignCurrency();
			Rec21_waehrung 			waehrungSystem=pds.getRecSystemBaseCurrency();
			
			if (p.getVal2()!=null || p.getVal3()!=null) {
				//sowohl adresse als auch kontraktpos ist vorhanden
				
				Rec21 recVposKon = null;
				Rec21 recVposAng = null;
				if (p.getVal2()!=null) {
					recVposKon = new Rec21(_TAB.vpos_kon)._fill_id( p.getVal2());
				} else {
					recVposAng = new Rec21(_TAB.vpos_std)._fill_id( p.getVal3());
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
					c = new E2_Grid()._bo_ddd()	._setSize(this.getSpaltenBreite())
												._a(new RB_lab(recWaehrungKontraktOderAngebot.getSymbol("?"))
																	._f(this.getBlockTextFont())
																	._ttt(S.ms("<").ut(pds.getRecAdress().__get_name1_ort()).ut(">: ").t("Im "+kontraktOderAngebot+" ist die Standard-Währung hinterlegt"))
													, new RB_gld()._center_mid()._col_back(this.getBlockBackColorNormal()))
												._setRowHight(this.getBlockHeigth())
												;
				} else {
					//kontrakt = fremdwaehrung,dann einen label mit highlight-hintergrund
					c = new E2_Grid()._bo_ddd()	._setSize(this.getSpaltenBreite())
												._a(new RB_lab(recWaehrungKontraktOderAngebot.getSymbol("?"))
																	._f(this.getBlockTextFont())
																	._ttt(S.ms("<").ut(pds.getRecAdress().__get_name1_ort()).ut(">: ").t("Im "+kontraktOderAngebot+" ist eine Fremdwährung hinterlegt"))
													, new RB_gld()._center_mid()._col_back(this.getBlockBackColorHighlight()))
												._setRowHight(this.getBlockHeigth())
												;
				}
				
			} else {
				if (waehrungFremd==null) {
					c = new E2_Grid()._bo_ddd()	._setSize(this.getSpaltenBreite())
							._a(new RB_lab(pds.getRecAdressBaseCurrency().getSymbol("?"))
																	._f(this.getBlockTextFont())
																	._ttt(S.ms("<").ut(pds.getRecAdress().__get_name1_ort()).ut(">: ").t("besitzt keine Fremdwährung"))
											, new RB_gld()._center_mid()._col_back(this.getBlockBackColorNormal()))
							._setRowHight(this.getBlockHeigth())
							;
				} else {
					MyE2_String info = S.ms("<").ut(pds.getRecAdress().__get_name1_ort()).ut(">: ")	.t("besitzt mindestens eine Fremdwährung" )
																									.ut(", ")
																									.t(" die Vorschlagswährung bei der Übernahme in die freien Positionen ist: ")
																									.ut(pds.getRecSystemBaseCurrency().getSymbol("?"));
					c = new E2_Grid()._bo_ddd()._bo_green()	._setSize(this.getSpaltenBreite())
							._a(
									new E2_Grid()._s(1)	._a(new RB_lab(waehrungSystem.getSymbol("?"))._f(this.getBlockTextFont())._ttt(info), new RB_gld()._ins(2,1,2,0)._center_mid())
														._a(new RB_lab("("+wAdressBase.getSymbol("?")+")")._f(this.getBlockTextFont())._ttt(info), new RB_gld()._ins(2,0,2,1)._center_mid())
									,new RB_gld()._center_mid()._col_back(this.getBlockBackColorHighlight()))
							._setRowHight(this.getBlockHeigth())
							;
				}
			}
		}
		
		return c;
	}
	
	
	private Component buildUndefAnzeige() {
		return new E2_Grid()._bo_ddd()	._setSize(this.getSpaltenBreite())
				._a(new RB_lab("?")	._f(this.getBlockTextFont())
									._ttt(S.ms("Unbestimmte Währung !"))
								, new RB_gld()._center_mid()._col_back(this.getBlockBackColorHighlight()))
				._setRowHight(this.getBlockHeigth())
				;
	}
	

	/**
	 * 
	 * @return s an Vector with pair of longs (adresse and vpos_kon), on error resturns vector of pair(0,0)
	 */
	protected abstract VEK<Triple<Long>> getIdAdressAndIdVposKonAndIdVposAngebot();

	protected abstract int getSpaltenBreite() ;
	
	protected abstract int getBlockHeigth();
	
	protected abstract Color  getBlockBackColorHighlight();
	protected abstract Color  getBlockBackColorNormal();
	protected abstract Font   getBlockTextFont();
	
}
