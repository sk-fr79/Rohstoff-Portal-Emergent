/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG;

import java.util.Vector;

import panter.gmbh.BasicInterfaces.Service.PdServiceAdressCurrency;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_waehrung;

/**
 * @author martin
 *
 */
public class FUB_OwnInfoGridWaehrungen extends E2_Grid {

	//sammeln der noetigen anzeigen
	private VEK<infoBlock> 	vInfoBlocks = new VEK<infoBlock>();
	private boolean 		hasSomethingRelevant = false;
	
	public FUB_OwnInfoGridWaehrungen(){
		super();
		this._bo_ddd();
	}
	
	
	public void _init(Vector<String> v_idfuhren) throws myException {
		
		Long lidAdresseMandant = bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null);
		
		if (lidAdresseMandant==null) {
			throw new myException(this,"Error! no mandant-adress-id present !");
		}
		long idAdresseMandant = lidAdresseMandant.longValue();
		
		
		for (String s: v_idfuhren) {
			Rec21 rf = new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(s);
			
			if (rf.is_no_db_val(VPOS_TPA_FUHRE.deleted) && rf.is_no_db_val(VPOS_TPA_FUHRE.ist_storniert)) {
				//ladeseite 
				if (rf.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_start).longValue()!=idAdresseMandant) {
					vInfoBlocks._a(new infoBlock(rf, VPOS_TPA_FUHRE.id_adresse_start, VPOS_TPA_FUHRE.id_vpos_kon_ek, VPOS_TPA_FUHRE.id_vpos_std_ek, S.ms("FU: Gutschrift")));
				}
				
				//abladeseite 
				if (rf.getLongDbValue(VPOS_TPA_FUHRE.id_adresse_ziel).longValue()!=idAdresseMandant) {
					vInfoBlocks._a(new infoBlock(rf, VPOS_TPA_FUHRE.id_adresse_ziel, VPOS_TPA_FUHRE.id_vpos_kon_vk, VPOS_TPA_FUHRE.id_vpos_std_vk, S.ms("FU: Rechnung")));
				}
				
				//fuhrenorte
				RecList21 rlFuo = rf.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre);
				for (Rec21 rfuo: rlFuo) {
					if (rfuo.is_no_db_val(VPOS_TPA_FUHRE_ORT.deleted)) {
						if (rfuo.getLongDbValue(VPOS_TPA_FUHRE_ORT.id_adresse).longValue()!=idAdresseMandant) {
							vInfoBlocks._a(new infoBlock(rfuo, VPOS_TPA_FUHRE_ORT.id_adresse, VPOS_TPA_FUHRE_ORT.id_vpos_kon, VPOS_TPA_FUHRE_ORT.id_vpos_std, 
									rfuo.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equals("EK")?S.ms("ZO: Gutschrift"):S.ms("ZO: Rechnung")));
						}
					}
				}
			}
		}
		
		
		//jetzt die infobloecke durchiterieren und die infos eintragen
		//idFuhre, idfuhrenort, Bereich, vorschlagswaehrung, kundenfremdwaehrungen, infostodo
		this._setSize(50,50,100,50,50,70,330);
		RB_gld ldTitel = new RB_gld()._ins(2)._col_back_d()._left_top();
		RB_gld ldInnen = new RB_gld()._ins(2)._left_top();
		
		this._a(new RB_lab(S.ms("Währungen ..."))._fsa(-2),		ldTitel._c()._span(this.getSize()));
		this._a(new RB_lab(S.ms("ID-Fuh."))._fsa(-2),			ldTitel)
			._a(new RB_lab(S.ms("ID-Ort"))._fsa(-2),			ldTitel)
			._a(new RB_lab(S.ms("RG-Position "))._fsa(-2),		ldTitel)
			._a(new RB_lab(S.ms("Währ. Beleg."))._fsa(-2),		ldTitel)
			._a(new RB_lab(S.ms("Währ. Adr."))._fsa(-2),		ldTitel)
			._a(new RB_lab(S.ms("Währungen"))._fsa(-2),			ldTitel)
			._a(new RB_lab(S.ms("Hinweise"))._fsa(-2),			ldTitel);
		for (infoBlock ib: vInfoBlocks) {
			if (ib.getInfoAndWarnText()!=null) {					//warntext ist inidikator, ob bei der buchung etwas angezeigt werden muss
				this.hasSomethingRelevant=true;
				this._a(new RB_lab(ib.getIdFuhre())._fsa(-2),		ldInnen)
					._a(new RB_lab(ib.getIdFuhreOrt())._fsa(-2),	ldInnen)
					._a(new RB_lab(ib.m_infoBereich)._fsa(-2),		ldInnen)
					._add_raw(ib.getLabelBelegWaehrung())
					._add_raw(ib.getLabelAdressBaseWaehrung())
					._add_raw(ib.getLabelAdressAddoncurrencys())
					._a(new RB_lab(ib.getInfoAndWarnText())._fsa(-2),				ldInnen)
					;
			}
		}
	}
	
	public boolean hasSomethingToShow() {
		return this.hasSomethingRelevant;
	}
	

	
	
	
	private class infoBlock {
		private Rec21        		m_recFuhreFuhreOrt = 		null;
		private MyE2_String  		m_infoBereich = 			null;
		private Rec21_waehrung		m_recWaehrungfuerBeleg = 	null;
		private Rec21_waehrung 		m_recWaehrungBuchungAusKontraktOderAngebot = 	null;
		private Rec21_waehrung 		m_recWaehrungAdresseStd = 	null;
		private VEK<Rec21_waehrung> 	m_recsFremdWaehrungen = 	new VEK<>();      
		
		private PdServiceAdressCurrency service = null;
		
		public infoBlock(Rec21 recFuhreFuhreOrt, IF_Field fieldAdress, IF_Field fieldKon, IF_Field fieldStd, MyE2_String infoBereich) throws myException {
			super();
			this.m_recFuhreFuhreOrt = recFuhreFuhreOrt;
			this.m_infoBereich = infoBereich;
			
			this.service = new PdServiceAdressCurrency(recFuhreFuhreOrt.getLongDbValue(fieldAdress).longValue());
			
			//zuerst den kontrakt/angebot pruefen
			Rec21 recVposKon = null;
			Rec21 recVposStd = null;
			
			if (recFuhreFuhreOrt.getLongDbValue(fieldKon)!=null) {
				recVposKon=new Rec21(_TAB.vpos_kon)._fill_id(recFuhreFuhreOrt.getLongDbValue(fieldKon));
			}
			if (recFuhreFuhreOrt.getLongDbValue(fieldStd)!=null) {
				recVposStd=new Rec21(_TAB.vpos_std)._fill_id(recFuhreFuhreOrt.getLongDbValue(fieldStd));
			}
			
			Rec21_waehrung 		recWaehrungStdAdresse = service.getRecAdressBaseCurrency();
			VEK<Rec21_waehrung>	vFremdwaehrungen = 		service.getAdressForeignCurrencys();
			Rec21_waehrung recWaehrungKontraktAngebot = null;
			if (recVposStd!=null) {
				recWaehrungKontraktAngebot = new Rec21_waehrung(recVposStd.get_up_Rec21(VPOS_STD.id_waehrung_fremd,WAEHRUNG.id_waehrung,true));
			}
			if (recVposKon!=null) {
				recWaehrungKontraktAngebot = new Rec21_waehrung(recVposKon.get_up_Rec21(VPOS_KON.id_waehrung_fremd,WAEHRUNG.id_waehrung,true));
			}
			
			this.m_recWaehrungAdresseStd=						recWaehrungStdAdresse;
			this.m_recWaehrungBuchungAusKontraktOderAngebot=	recWaehrungKontraktAngebot;
			this.m_recsFremdWaehrungen._a(vFremdwaehrungen);

			//feststellen, welche waehrung fuer die buchung rangezogen wird
			this.m_recWaehrungfuerBeleg = this.m_recWaehrungAdresseStd;
			if (this.m_recWaehrungBuchungAusKontraktOderAngebot!=null) {
				this.m_recWaehrungfuerBeleg = this.m_recWaehrungBuchungAusKontraktOderAngebot;
			}
			
			
		}
		
		public MyE2_String getInfoAndWarnText() throws myException {
			if (this.m_recWaehrungfuerBeleg==null) {		//darf nicht sein
				throw new myException("Error finding Currency !!");
			}
			
			if (this.m_recWaehrungBuchungAusKontraktOderAngebot!=null && this.m_recWaehrungfuerBeleg.getId()== this.service.getLongSystemCurrency()  && this.service.isAdressOwningForeighCurrency()) {
				return S.ms("Die Belegwährung kommt aus einem Angebot/Kontrakt und ist <").ut(this.m_recWaehrungfuerBeleg.getUfs(WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung))
													.t(">! Die Adresse besitzt Fremdwährungen!");     
			} else if (this.m_recWaehrungBuchungAusKontraktOderAngebot==null && this.m_recWaehrungfuerBeleg.getId()== this.service.getLongSystemCurrency()  && this.service.isAdressOwningForeighCurrency()) {
				return S.ms("Die Belegwährung (Vorschlag) ist die Basiswährung <").ut(this.m_recWaehrungfuerBeleg.getUfs(WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung))
													.t(">! Die Adresse besitzt Fremdwährungen! Bitte korrekte Währung einstellen und gegebenenfalls Kurs erfassen!");     
			} else if (this.m_recWaehrungBuchungAusKontraktOderAngebot==null && this.m_recWaehrungfuerBeleg.getId()!= this.service.getLongSystemCurrency() && this.service.getAdressAddonCurrencysExceptBaseCurrency().size()>0) {
				return S.ms("Die Belegwährung (Vorschlag) ist <").ut(this.m_recWaehrungfuerBeleg.getUfs(WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung))
													.t(">! Die Adresse besitzt eine Fremdwährung als Standard und weitere Fremdwährungen! Bitte korrekte Währung und Kurs in den Positionen erfassen!");     
			} else if (this.m_recWaehrungBuchungAusKontraktOderAngebot==null && this.m_recWaehrungfuerBeleg.getId()!= this.service.getLongSystemCurrency() && this.service.getAdressAddonCurrencysExceptBaseCurrency().size()==0) {
				return S.ms("Die Belegwährung ist <").ut(this.m_recWaehrungfuerBeleg.getUfs(WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung))
													.t(">! Die Adresse besitzt eine Fremdwährung als Standard! Bitte korrekten Kurs in den Positionen erfassen!");     
			} else if (this.service.isAdressOwningForeighCurrency() && this.m_recWaehrungfuerBeleg.isBaseCurrency())  {
				return S.ms("Die Belegwährung ist <").ut(this.m_recWaehrungfuerBeleg.getUfs(WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung))
						.t(">! Die Adresse besitzt Fremdwährungen! Bitte Währung und Kurs prüfen!");     
			} else if (!this.m_recWaehrungfuerBeleg.isBaseCurrency())  {
				return S.ms("Die Belegwährung ist <").ut(this.m_recWaehrungfuerBeleg.getUfs(WAEHRUNG.waehrungssymbol,WAEHRUNG.kurzbezeichnung))
						.t(">! Bitte korrekten Kurs in den Positionen erfassen!");    
			}
			return null;
		}
		
		
		public String getIdFuhre() throws myException {
			if (this.m_recFuhreFuhreOrt.get_tab()==_TAB.vpos_tpa_fuhre) {
				return this.m_recFuhreFuhreOrt.getFs(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre);
			} else {
				return this.m_recFuhreFuhreOrt.getFs(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre);
			}
		}

		public String getIdFuhreOrt() throws myException {
			if (this.m_recFuhreFuhreOrt.get_tab()==_TAB.vpos_tpa_fuhre) {
				return "-";
			} else {
				return this.m_recFuhreFuhreOrt.getFs(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort);
			}
		}
		
		
		public RB_lab getLabelBelegWaehrung()  throws myException{
			RB_lab l = new RB_lab()._lw();
			if (this.m_recWaehrungfuerBeleg==null) {
				l._t("-")._set_ld(new RB_gld()._ins(2)._left_top());
			} else {
				l._t(m_recWaehrungfuerBeleg.getSymbol())._set_ld(new RB_gld()._ins(2)._left_top());
				if (!m_recWaehrungfuerBeleg.isBaseCurrency()) {
					l._set_ld(new RB_gld()._ins(2)._left_top()._col_back_alarm());
				}
			}
			return l;
		}
		
		public RB_lab getLabelAdressBaseWaehrung()  throws myException{
			RB_lab l = new RB_lab()._lw();
			if (this.m_recWaehrungAdresseStd==null) {
				l._t("-")._set_ld(new RB_gld()._ins(2)._left_top());
			} else {
				l._t(m_recWaehrungAdresseStd.getSymbol())._set_ld(new RB_gld()._ins(2)._left_top());
				if (!m_recWaehrungAdresseStd.isBaseCurrency()) {
					l._set_ld(new RB_gld()._ins(2)._left_top()._col_back_alarm());
				}
			}
			return l;
		}
		
		
		
		public RB_lab getLabelAdressAddoncurrencys() throws myException {
			RB_lab l = new RB_lab()._lw();
			if (this.service!=null && this.service.getAdressAddonCurrencysExceptBaseCurrency()==null || this.service.getAdressAddonCurrencysExceptBaseCurrency().size()==0) {
				l._t("-")._set_ld(new RB_gld()._ins(2)._left_top());
			} else {
				String waehrungen = "";
				boolean hasFremdWaehrung = false;
				for (Rec21_waehrung r: this.service.getAdressAddonCurrencysExceptBaseCurrency()) {
					waehrungen+=(""+r.getSymbol()+" / ");
					hasFremdWaehrung = hasFremdWaehrung||r.isForeignCurrency();
				}
				if (waehrungen.endsWith(" / ")) {
					waehrungen=waehrungen.substring(0,waehrungen.lastIndexOf(" / "));
				}
				
				l._t(waehrungen)._set_ld(new RB_gld()._center_mid());
				if (hasFremdWaehrung) {
					l._set_ld(new RB_gld()._ins(2)._left_top()._col_back_alarm());
				}
			}
			
			return l;
		}

	
	}
	

}
