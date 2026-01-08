/**
 * panter.gmbh.Echo2.RB.HIGHLEVEL
 * @author martin
 * @date 10.01.2020
 * 
 */
package panter.gmbh.Echo2.RB.HIGHLEVEL;

import java.math.BigDecimal;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButton;
import panter.gmbh.Echo2.RB.COMP.SearchFieldV2.ResultButtons;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VKopfKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VKopfStd;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosStd;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 10.01.2020
 *
 */
public class RB_HL_Search_V2_KontraktAngebote extends RB_SearchFieldV2 {

	/**
	 * searchfeld fuer kontrakte und angebote
	 */
	private ENUM_VORGANGSART 	vorgangsArt = null;
	private _TAB 				tablePos = null;  
	
	/**
	 * @author martin
	 * @date 10.01.2020
	 *
	 * @throws myException
	 */
	public RB_HL_Search_V2_KontraktAngebote(ENUM_VORGANGSART vorgangsart) throws myException {
		
		if (		vorgangsart==ENUM_VORGANGSART.EK_KONTRAKT
				|| 	vorgangsart==ENUM_VORGANGSART.VK_KONTRAKT 
				|| 	vorgangsart==ENUM_VORGANGSART.ANGEBOT 
				|| 	vorgangsart==ENUM_VORGANGSART.ABNAHMEANGEBOT) {
			
		
			this.vorgangsArt = vorgangsart;
			
			this._setSaveSortSettingsKey(this.getClass().getCanonicalName());
			
			this._setOwnShape();
			
			tablePos = this.vorgangsArt.getTabPos();
			
			this._setTable(tablePos);
			
			//this.getButtonStartSearch()._ttt(S.ms("Such-Automatik: Wird der Suchbutton bei leerer Eingabe gedrückt, dann wird eine Vorauswahl aus Sorgengruppen angezeigt"));
			
	
			
			//hier unterscheiden zwischen ek- und vk - typen
			if (tablePos==_TAB.vpos_kon) {
				// basis-selektion
				this.getAndStatementCollectorForBasic().and(new vgl_YN(VPOS_KON.deleted, false));
				this.getAndStatementCollectorForBasic().and(new vgl_YN(VKOPF_KON.deleted, false));
				this.getAndStatementCollectorForBasic().and(new vgl_YN(VPOS_KON_TRAKT.abgeschlossen, false));
				this.getAndStatementCollectorForBasic().and(new vgl(VKOPF_KON.vorgang_typ, vorgangsArt.db_val()));
				
			} else {
				// basis-selektion
				this.getAndStatementCollectorForBasic().and(new vgl_YN(VPOS_STD.deleted, false));
				this.getAndStatementCollectorForBasic().and(new vgl_YN(VKOPF_STD.deleted, false));
				this.getAndStatementCollectorForBasic().and(new vgl(VKOPF_STD.vorgang_typ, vorgangsArt.db_val()));
			}
			
			this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
			this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
			this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.name3), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
			this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.strasse), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
			this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.plz), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
			this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,ADRESSE.ort), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
			
			
			if (tablePos==_TAB.vpos_kon) {
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new TermSimple("TO_CHAR("+VPOS_KON.id_vpos_kon.tnfn()+")"), COMP.EQ, new TermSimple("REPLACE('#WERT#','.','')")));
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VKOPF_KON.buchungsnummer), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VPOS_KON.anr1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VPOS_KON.anr2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VPOS_KON.artbez1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VPOS_KON.artbez2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
			} else {
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new TermSimple("TO_CHAR("+VPOS_STD.id_vpos_std.tnfn()+")"), COMP.EQ, new TermSimple("REPLACE('#WERT#','.','')")));
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VKOPF_STD.buchungsnummer), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
		
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VPOS_STD.anr1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VPOS_STD.anr2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VPOS_STD.artbez1), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
				this.getAndStatementCollectorForSearchInputs().or(new vgl(new FieldTerm(ATTRIBUTES.UPPER,VPOS_STD.artbez2), COMP.LIKE, new TermSimple("UPPER('%#WERT#%')")));
			}
			
			this.set_iMaxResults(5000);
			
			this.getSortButtonTextsHeaders()._a("BuchNr.")._a("F")._a("Id");
		} else {
			throw new myException("False type: only types angebot and kontrakt <c37cde68-35e3-11ea-978f-2e728ce88125>");
		}
	}

	
	
	
	@Override
	public SEL generateSELWithoutWhere() throws myException {
		if (tablePos==_TAB.vpos_kon) {
			return new SEL(_TAB.vpos_kon).FROM(_TAB.vpos_kon)
						.INNERJOIN(_TAB.vkopf_kon, VPOS_KON.id_vkopf_kon, VKOPF_KON.id_vkopf_kon)
						.INNERJOIN(_TAB.vpos_kon_trakt, VPOS_KON.id_vpos_kon, VPOS_KON_TRAKT.id_vpos_kon)
						.INNERJOIN(_TAB.adresse,   VKOPF_KON.id_adresse,  ADRESSE.id_adresse)
						;
		} else {
			return new SEL(_TAB.vpos_std).FROM(_TAB.vpos_std)
					.INNERJOIN(_TAB.vkopf_std, VPOS_STD.id_vkopf_std, VKOPF_STD.id_vkopf_std)
					.INNERJOIN(_TAB.vpos_std_angebot, VPOS_STD.id_vpos_std, VPOS_STD_ANGEBOT.id_vpos_std)
					.INNERJOIN(_TAB.adresse,   VKOPF_STD.id_adresse,  ADRESSE.id_adresse)
					;
			
		}
	}

	
	
	@Override
	public void renderResultOnMask(E2_Grid gridResults, Long id) throws myException {
		gridResults._clear()._setSize(65,330)._bo_dd();
		

		
		if (tablePos==_TAB.vpos_kon) {
		
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
				
				BigDecimal bdMengeInFuhre = recVposKon.get_gesamt_fuhre_menge(this.vorgangsArt==ENUM_VORGANGSART.EK_KONTRAKT).get_bdWert();
				BigDecimal bdRest = new BigDecimal(0);
				if (bdMengeInFuhre.compareTo(bdMenge)<0) {
					bdRest = bdMenge.subtract(bdMengeInFuhre);
				}
				
				String buchungsnummer = recVposKon.getBuchungsNummer();
				String sorte = " ["+recVposKon.get_ufs_kette(" - " , VPOS_KON.anr1,VPOS_KON.anr2)+"] "+recVposKon.getUfs(VPOS_KON.artbez1);
				String mengeGesamt = new MyBigDecimal(bdMenge).get_FormatedRoundedNumber(1)+" "+(einheit!=null?einheit.getFs(EINHEIT.einheitkurz,""):"");
				String s_restMenge = new MyBigDecimal(bdRest).get_FormatedRoundedNumber(1)+" "+(einheit!=null?einheit.getFs(EINHEIT.einheitkurz,""):"");
				String firma = recAdresse.get__FullNameAndAdress_Typ2();
				String gueltig = recVposKon.getGueltigkeitsZeitraum();
				
				gridResults	._a(new RB_lab(S.ms("Kontrakt: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(buchungsnummer)._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
				gridResults	._a(new RB_lab(S.ms("Gültig: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(gueltig)._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
				gridResults	._a(new RB_lab(S.ms("Sorte: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(sorte)._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
				gridResults	._a(new RB_lab(S.ms("Mg(offen): "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(mengeGesamt+" ("+s_restMenge+")")._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
				gridResults	._a(new RB_lab(S.ms("Firma: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(firma)._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
			}
		} else {   //angebot
			
			if (id!=null) {
				Rec21_VPosStd 	recVposStd = 		(Rec21_VPosStd)new Rec21_VPosStd()._fill_id(id);
				Rec21_VKopfStd 	recVkopf = 			new Rec21_VKopfStd(recVposStd.get_up_Rec21(VKOPF_STD.id_vkopf_std));
				Rec21_adresse   recAdresse = 		new Rec21_adresse(recVkopf.get_up_Rec21(ADRESSE.id_adresse));

				String buchungsnummer = recVposStd.getBuchungsNummer();
				String sorte = " ["+recVposStd.get_ufs_kette(" - " , VPOS_STD.anr1,VPOS_STD.anr2)+"] "+recVposStd.getUfs(VPOS_STD.artbez1);
				String firma = recAdresse.get__FullNameAndAdress_Typ2();
				String gueltig = recVposStd.getGueltigkeitsZeitraum();
				
				gridResults	._a(new RB_lab(S.ms("Angebot"))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(buchungsnummer)._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
				gridResults	._a(new RB_lab(S.ms("Gültig: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(gueltig)._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
				gridResults	._a(new RB_lab(S.ms("Sorte: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(sorte)._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
				gridResults	._a(new RB_lab(S.ms("Firma: "))._fsa(-2)._i(), new RB_gld()._ins(0,1,4,1))
							._a(new RB_lab(firma)._fsa(-2), new RB_gld()._ins(0,1,1,1));
							;
			}

		}
	}


	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2#createResultButtons(panter.gmbh.indep.dataTools.RECORD2.RecList21, panter.gmbh.Echo2.RB.COMP.SearchFieldV2.RB_SearchFieldV2)
	 */
	@Override
	public ResultButtons createResultButtons(RecList21 rlSearchResult, RB_SearchFieldV2 searchField) {

		E2_MutableStyle styleLeft = E2_Button.baseStyle(new E2_FontPlain(-2), new Alignment(Alignment.LEFT, Alignment.TRAILING));
		E2_MutableStyle styleRight = E2_Button.baseStyle(new E2_FontPlain(-2), new Alignment(Alignment.RIGHT, Alignment.TRAILING));

		E2_MutableStyle styleTitelLeft = E2_Button.baseStyle(new E2_FontBold(), new Alignment(Alignment.LEFT, Alignment.TRAILING));
		E2_MutableStyle styleTitelRight = E2_Button.baseStyle(new E2_FontBold(-2), new Alignment(Alignment.RIGHT, Alignment.TRAILING));
		
		if (this.tablePos==_TAB.vpos_kon) {
		

		
			ResultButtons results = new ResultButtons(this.getSaveSortSettingsKey());
			
			try {
				for (Rec21 r: rlSearchResult) {
					
				
					Rec21_VPosKon 	recVposKon = 		new Rec21_VPosKon(r);
					Rec21_VKopfKon 	recVkopf = 			new Rec21_VKopfKon(r.get_up_Rec21(VKOPF_KON.id_vkopf_kon));
					Rec21_adresse   recAdresse = 		new Rec21_adresse(recVkopf.get_up_Rec21(ADRESSE.id_adresse));
	
					BigDecimal bdMenge = 				O.NN(recVposKon.get_raw_resultValue_bigDecimal(VPOS_KON.anzahl),BigDecimal.ZERO);
					BigDecimal bdGeliefert = 			O.NN(recVposKon.get_gesamt_fuhre_menge().get_bdWert(), BigDecimal.ZERO);
					BigDecimal bdOffen = 				bdMenge.subtract(bdGeliefert);
					BigDecimal bdPreis = 				O.NN(recVposKon.get_raw_resultValue_bigDecimal(VPOS_KON.einzelpreis), BigDecimal.ZERO);
	
					
					
					ResultButton[] b = new   ResultButton[10];
					String sort_id = ("00000000000000000000000000000000").substring(r.getIdLong().toString().length())
							+ r.getIdLong().toString();
					
				
					String s00a = recVkopf.getFs(VKOPF_KON.buchungsnummer, "<"+recVkopf.getFs(VKOPF_KON.id_vkopf_kon)+">")+"-"+recVposKon.getFs(VPOS_KON.positionsnummer,"0");
					String s01a = recVkopf.getFs(VKOPF_KON.druckdatum, "-");
					String s02a = recAdresse.get__FullNameAndAdress_Typ2();
					
					String s03a = S.getNumberSortable(new MyBigDecimal(bdMenge).get_FormatedRoundedNumber(3));
					String s03b = new MyBigDecimal(bdMenge).get_FormatedRoundedNumber(3);
					
					String s04a = S.getNumberSortable(new MyBigDecimal(bdOffen).get_FormatedRoundedNumber(3));
					String s04b = new MyBigDecimal(bdOffen).get_FormatedRoundedNumber(3);
					
					String s05a = recVposKon.get_ufs_kette(" - " , VPOS_KON.anr1,VPOS_KON.anr2);
					String s06a = recVposKon.getUfs(VPOS_KON.artbez1);
					String s07a = new MyBigDecimal(bdPreis).get_FormatedRoundedNumber(2);
					String s08a = recVposKon.getGueltigkeitsZeitraum();
					
					String s09a = sort_id;
					String s09b = recVposKon.getFs(VPOS_KON.id_vpos_kon);
	

					
					b[ 0]= (ResultButton)new ResultButton(s00a,  r, this)._t(s00a)._style(styleLeft)._fsa(-2);
					b[ 1]= (ResultButton)new ResultButton(s01a,  r, this)._t(s01a)._style(styleLeft)._fsa(-2);
					b[ 2]= (ResultButton)new ResultButton(s02a,  r, this)._t(s02a)._style(styleLeft)._fsa(-2);
					b[ 3]= (ResultButton)new ResultButton(s03a,  r, this)._t(s03b)._style(styleRight)._fsa(-2);
					b[ 4]= (ResultButton)new ResultButton(s04a,  r, this)._t(s04b)._style(styleRight)._fsa(-2);
					b[ 5]= (ResultButton)new ResultButton(s05a,  r, this)._t(s05a)._style(styleLeft)._fsa(-2);
					b[ 6]= (ResultButton)new ResultButton(s06a,  r, this)._t(s06a)._style(styleLeft)._fsa(-2);
					b[ 7]= (ResultButton)new ResultButton(s07a,  r, this)._t(s07a)._style(styleRight)._fsa(-2);
					b[ 8]= (ResultButton)new ResultButton(s08a,  r, this)._t(s08a)._style(styleLeft)._fsa(-2);
					b[ 9]= (ResultButton)new ResultButton(s09a,  r, this)._t(s09b)._style(styleRight)._fsa(-2);
					
					results._a(b);
				}
			

			
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._add(e.get_ErrorMessage());
				results.clear();
			}

			this.getSortButtonTextsHeaders()._clear()	._a("BuchNr.")		._a("Belegdat.")	._a("Firma")		._a("Menge")		._a("Offen")		._a("Sorte")		._a("Bezeichnung")	._a("Preis")		._a("Gültigkeit")	._a("ID");
			this.getSortButtonStyles()._clear()			._a(styleTitelLeft)	._a(styleTitelLeft)	._a(styleTitelLeft)	._a(styleTitelRight)._a(styleTitelRight)._a(styleTitelLeft)._a(styleTitelLeft)._a(styleTitelRight)	._a(styleTitelLeft)._a(styleTitelRight);
			
			return results;
		
		} else {
			ResultButtons results = new ResultButtons(this.getSaveSortSettingsKey());
			
			try {
				for (Rec21 r: rlSearchResult) {
					
				
					Rec21_VPosStd 	recVposStd = 		new Rec21_VPosStd(r);
					Rec21_VKopfStd 	recVkopf = 			new Rec21_VKopfStd(r.get_up_Rec21(VKOPF_STD.id_vkopf_std));
					Rec21_adresse   recAdresse = 		new Rec21_adresse(recVkopf.get_up_Rec21(ADRESSE.id_adresse));
	
					BigDecimal bdPreis = 				O.NN(recVposStd.get_raw_resultValue_bigDecimal(VPOS_STD.einzelpreis), BigDecimal.ZERO);
	
					
					ResultButton[] b = new   ResultButton[8];
					String sort_id = ("00000000000000000000000000000000").substring(r.getIdLong().toString().length())
							+ r.getIdLong().toString();
					
				
					String s00a = recVposStd.getBuchungsNummer();
					String s01a = recVkopf.getFs(VKOPF_STD.druckdatum, "-");

					String s02a = recAdresse.get__FullNameAndAdress_Typ2();
					
					String s03a = recVposStd.get_ufs_kette(" - " , VPOS_STD.anr1,VPOS_STD.anr2);
					
					String s04a = recVposStd.getUfs(VPOS_STD.artbez1);
					
					String s05a = new MyBigDecimal(bdPreis).get_FormatedRoundedNumber(2);
					
					String s06a = recVposStd.getGueltigkeitsZeitraum();
					
					String s07a = sort_id;
					String s07b = recVposStd.getFs(VPOS_STD.id_vpos_std);
	
					
					b[ 0]= (ResultButton)new ResultButton(s00a,  r, this)._t(s00a)._style(styleLeft)._fsa(-2);
					b[ 1]= (ResultButton)new ResultButton(s01a,  r, this)._t(s01a)._style(styleLeft)._fsa(-2);
					b[ 2]= (ResultButton)new ResultButton(s02a,  r, this)._t(s02a)._style(styleLeft)._fsa(-2);
					b[ 3]= (ResultButton)new ResultButton(s03a,  r, this)._t(s03a)._style(styleLeft)._fsa(-2);
					b[ 4]= (ResultButton)new ResultButton(s04a,  r, this)._t(s04a)._style(styleLeft)._fsa(-2);
					b[ 5]= (ResultButton)new ResultButton(s05a,  r, this)._t(s05a)._style(styleRight)._fsa(-2);
					b[ 6]= (ResultButton)new ResultButton(s06a,  r, this)._t(s06a)._style(styleLeft)._fsa(-2);
					b[ 7]= (ResultButton)new ResultButton(s07a,  r, this)._t(s07b)._style(styleRight)._fsa(-2);
					
					results._a(b);
				}
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._add(e.get_ErrorMessage());
				results.clear();
			}
			
			this.getSortButtonTextsHeaders()._clear()	._a("BuchNr.")		._a("Belegdat.")	._a("Firma")		._a("Sorte")		._a("Bezeichnung")	._a("Preis")		._a("Gültigkeit")	._a("ID");
			this.getSortButtonStyles()._clear()			._a(styleTitelLeft)	._a(styleTitelLeft)	._a(styleTitelLeft)	._a(styleTitelLeft)._a(styleTitelLeft)._a(styleTitelRight)	._a(styleTitelLeft)._a(styleTitelRight);
		
			return results;

			
		}
	}

	
	
	@Override
	public E2_BasicModuleContainer generatePopupContainer() throws myException {
		return new OwnPopupContainer();
	}

	private class OwnPopupContainer extends E2_BasicModuleContainer {

		public OwnPopupContainer() {
			super();
			this.set_oExtWidth(new Extent(1400));
			this.set_oExtHeight(new Extent(700));
		}
		
	}


	
	public RB_SearchFieldV2 _setOwnShape() throws myException {
		RB_gld gl = new RB_gld()._ins(0,0,4,2)._left_top();
		this._clear()
			._a(getTextFieldSearchInput(), gl)
			._a(getButtonErase(), gl._c()._ins(0, 2, 2, 0))
			._a(getButtonStartSearch(), gl._c()._ins(0, 2, 2, 0))
			._a(this.getGridForResults(), gl._c()._ins(10,0,0,0))
			._setSize(100,20,20,400);

		return this;
	}




	public ENUM_VORGANGSART getVorgangsArt() {
		return vorgangsArt;
	}





	
	
}
