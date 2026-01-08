package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_UNICODE;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_MaskGrid extends E2_Grid4Mask {

	private KFIX_P_M__ComponentMap  		map_kon = null;
	private KFIX_P_M__ComponentMapAddon 	map_kon_trakt = null;
	private String belegTyp ="";
	
	IF_wrappedMulticomponentsInGrid  wrap = (Component... comps )-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._gld(new RB_gld()._ins(0,0,10,0))._a_cm(c);} return g._s(comps.length); };
	
	
	public KFIX_P_M_MaskGrid(KFIX_P_M__ComponentMapCollector  mapColl, String oBelegTyp, boolean is_fix_kopf) throws myException {

		super();
		this._s(1)._st(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		this.map_kon 		= (KFIX_P_M__ComponentMap) mapColl.get(_TAB.vpos_kon.rb_km());
		this.map_kon_trakt 	= (KFIX_P_M__ComponentMapAddon) mapColl.get(_TAB.vpos_kon_trakt.rb_km());
		this.belegTyp 		= oBelegTyp;
		
		
		MyE2_TabbedPane	oTabbed = new MyE2_TabbedPane(null);
		oTabbed.set_bAutoHeight(true);

		oTabbed.add_Tabb(new MyE2_String("Positionangaben"), buildPositionAngabenTab(is_fix_kopf));
		oTabbed.add_Tabb(new MyE2_String("Zusatzinfos"), buildZusatzInfoPanel(is_fix_kopf));
		oTabbed.add_Tabb(new MyE2_String("Liefertermine"), buildLiefertermine());
		oTabbed.add_Tabb(new MyE2_String("Änderungen (Menge/Preis)"), buildAenderung());
		
		this.add(map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_GRID_INFO.key()));
		this.add(oTabbed);

	}

	private E2_Grid buildPositionAngabenTab(boolean is_fixierungs_position) throws myException{

		RB_gld gld = new RB_gld()._ins(2,2,2,2);

		IF_wrappedMulticomponentsInGrid  wrap = (Component... comps )-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._gld(gld)._a_cm(c);} return g._s(comps.length); };
		IF_wrappedMulticomponentsInGrid  wrap_2x2 = (Component... comps )-> {E2_Grid g = new E2_Grid()._s(2); g._gld(gld);for (Component c: comps) {g._a_lm(c);} return g; };

		E2_Grid grd = new E2_Grid()._setSize(140,420,300,140)._gld(new RB_gld()._ins(2,2,2,2))


		._a_lm(new RB_lab("IDs (Kopf/Pos)"))
		._endLine(
				new E2_Grid()._setSize(90,90,160,60,60,60,120,40)._gld(new RB_gld()._ins(2,2,2,2))
				._a_cm(map_kon.getComp(VPOS_KON.id_vkopf_kon))
				._a_cm(map_kon.getComp(VPOS_KON.id_vpos_kon))
				._a_rm(new RB_lab("Positionsnummer:"))
				._a_lm(map_kon.getComp(VPOS_KON.positionsnummer))
				._a_rm(new RB_lab("Typ:"))
				._a_rm(map_kon.getComp(VPOS_KON.position_typ))
				._a_rm(new RB_lab("Abgeschlossen"))
				._a_rm(map_kon_trakt.getComp(VPOS_KON_TRAKT.abgeschlossen))
				, new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,2,2,2)
				);

		grd
		._gld(new RB_gld()._span(4)._ins(2,2,2,2))
		._a_cm(new Separator())
		._gld(new RB_gld()._span(1)._ins(2,2,2,2))
		;

		grd._a_lm(new RB_lab("Gültigkeit:"))
		._endLine(
				
				new E2_Grid()._setSize(130,60,130,80,80,80,80,100,100)._gld(new RB_gld()._ins(2,2,2,2))
				._a_lm(map_kon_trakt.getComp(VPOS_KON_TRAKT.gueltig_von))
				._a_cm(new RB_lab("Bis"))
				._a_lm(map_kon_trakt.getComp(VPOS_KON_TRAKT.gueltig_bis))
				._a(is_fixierungs_position?map_kon_trakt.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASH_KEY_BT_SET_FIXIERUNGS_ZEITRAUM.key()):new RB_lab("")
						, new RB_gld()._center_mid())
				._a_cm(map_kon_trakt.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASH_KEY_BT_SET_ACTUAL_MONTH.key()))
				._a_cm(map_kon_trakt.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_ONE.key()))
				._a_cm(map_kon_trakt.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_TWO.key()))
				._a_rm(new RB_lab("Lieferant"))
				._a_lm(map_kon_trakt.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_LIEFERANT.key()))
				, new RB_gld()._al(E2_ALIGN.LEFT_MID)._ins(2,2,2,2)
				)
		;
		
		grd
		._gld(new RB_gld()._span(4)._ins(2,2,2,2))
		._a_cm(new Separator())
		._gld(new RB_gld()._span(1)._ins(2,2,2,2))
		;
		
		grd
		._a_lt(new RB_lab("Sorte"))
		._endLine( 
				new E2_Grid()._s(8)._gld(new RB_gld()._ins(2,2,2,2))
				._a_lt(map_kon.getComp(VPOS_KON.id_artikel_bez))
				._a_lt(new RB_lab("("))
				._a_lt(map_kon.getComp(VPOS_KON.anr1))
				._a_lt(new RB_lab("/"))
				._a_lt(map_kon.getComp(VPOS_KON.anr2))
				._a_lt(new RB_lab(")"))
				._a_lt(new RB_lab("ID Artikel: "))
				._a_lt(map_kon.getComp(VPOS_KON.id_artikel))
				, new RB_gld()._al(E2_ALIGN.LEFT_TOP)._ins(2, 2, 2, 2)
				)
		;
		
		grd
		._gld(new RB_gld()._span(4)._ins(2,2,2,2))
		._a_cm(new Separator())
		._gld(new RB_gld()._span(1)._ins(2,2,2,2))
		;
		
		grd
		._a_lt(new RB_lab("Anzahl"))
		._gld(new RB_gld()._ins(2,2,2,2))
		._a_lt(new RB_lab("Beschreibung"))
		._gld(new RB_gld()._ins(2,2,2,2))
		._a_rt(new RB_lab("Einzelpreis"))
		._endLine(new RB_lab("Gesamtpreis "+ENUM_UNICODE.EUR.getCode()), new RB_gld()._al(E2_ALIGN.RIGHT_MID))
		._a_lt( wrap.grid(
					map_kon.getComp(VPOS_KON.anzahl)
					,map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_AUTOFILL_ANZAHL_BT.key())
					)._setSize(100,20)
				)
		._gld(new RB_gld()._ins(2,2,2,2))
		._a_lt(map_kon.getComp(VPOS_KON.artbez1))
		._gld(new RB_gld()._ins(2,2,2,2))
		._a_rt(map_kon.getComp(VPOS_KON.einzelpreis))
		._endLine(map_kon.getComp(VPOS_KON.gesamtpreis),  new RB_gld()._al(E2_ALIGN.RIGHT_MID))
		;
		
		grd
		._a_lt(wrap_2x2.grid(
				new RB_lab("Überlieferung erlaubt ?")
				,map_kon_trakt.getComp(VPOS_KON_TRAKT.ueberliefer_ok)
				,new RB_lab("Toleranz Mengen in %")
				,map_kon.getComp(VPOS_KON.mengen_toleranz_prozent)
				)._setSize(85,40)._bo_dd())
		._gld(gld)
		._a_lt(map_kon.getComp(VPOS_KON.artbez2))

		._endLine(
				new E2_Grid()._setSize(120,140)._gld(gld)
				._a_rm(new RB_lab("Kurs EUR")._fsa(-2)._i())
				._a_rm(new RB_lab("Fremdwährung")._fsa(-2)._i())
				._a_rm(map_kon.getComp(VPOS_KON.waehrungskurs))
				._a_rm(map_kon.getComp(VPOS_KON.id_waehrung_fremd))
				._a_rm(new RB_lab("Einzelpreis FW")._fsa(-2)._i())
				._a_rm(new RB_lab("Gesamtpreis FW")._fsa(-2)._i())
				._a_rm(map_kon.getComp(VPOS_KON.einzelpreis_fw))
				._a_rm(map_kon.getComp(VPOS_KON.gesamtpreis_fw))
				._gld(new RB_gld()._span(2)._ins(2,2,2,2))
				._a_cm( wrap.grid(
						map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_BERECHNUNG.key()),
						map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_KURS_HOLEN.key()),
						map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_RECHNEN.key())
						)._bo_dd())
				, new RB_gld()._al(E2_ALIGN.RIGHT_MID)
				)

		._gld(gld)

		._a_lm(new RB_lab("Einheinten"))
		._endLine(new E2_Grid()._setSize(50,60,100,65,90,60,90,100)._gld(new RB_gld()._ins(2, 2, 2, 2))
				._a_rm(new RB_lab("Menge:"))
				._a_lm(map_kon.getComp(VPOS_KON.einheitkurz))
				._a_rm(new RB_lab("Mengen-Divisor"))
				._a_lm(map_kon.getComp(VPOS_KON.mengendivisor))
				._a_rm(new RB_lab("Preiseinheit"))
				._a_lm(map_kon.getComp(VPOS_KON.einheit_preis_kurz))
				,new RB_gld()._al(E2_ALIGN.LEFT_MID))

		._a_lt(new RB_lab("Lieferbed."))
		._gld(new RB_gld()._ins(2,2,2,2))
		._endLine(map_kon.getComp(VPOS_KON.lieferbedingungen),new RB_gld()._al(E2_ALIGN.LEFT_TOP)._ins(2,2,2,2))

		._a_lm(new RB_lab("Lieferort"))
		._endLine(wrap.grid(
				map_kon_trakt.getComp(VPOS_KON_TRAKT.lieferort),
				map_kon_trakt.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_LIEFERORT.key()
				)), new RB_gld()._ins(0,2,2,2)
				)

		._a_lm(new RB_lab("Lieferzeit"))
		._endLine(map_kon_trakt.getComp(VPOS_KON_TRAKT.lieferzeit), new RB_gld()._ins(2,2,2,2))

		._a_lm(new RB_lab("Bestellnummer extern"))
		._endLine(	
				new E2_Grid()._setSize(250,140,500)._gld(new RB_gld()._ins(0,2,2,2))
				._a_lm(map_kon.getComp(VPOS_KON.bestellnummer))
				._a_rm(new RB_lab("Transportmittel"))
				._a_lm(map_kon_trakt.getComp(VPOS_KON_TRAKT.transportmittel))
				, 
				new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

		//2020-02-12: schalter fuer verlaengerte faktuerungsfrist bei verkaufskontrakten
		grd._a_lt(new RB_lab("Verl.Fakturierungsfrist"))._a(map_kon_trakt.getComp(VPOS_KON_TRAKT.verlaengerte_fakt_frist))._endLine(new RB_gld());
		
		
		
		grd._a_lt(new RB_lab("Zahlungsbed."))
		._endLine(new E2_Grid()._setSize(240,350,60,60,60,60)._gld(new RB_gld()._ins(2,2,2,2))
				._a_lm(map_kon.getComp(VPOS_KON.id_zahlungsbedingungen))
				._a_rm(map_kon.getComp(VPOS_KON.zahlungsbedingungen))
				._a_rm(map_kon.getComp(VPOS_KON.zahltage))
				._a_rm(map_kon.getComp(VPOS_KON.fixmonat))
				._a_rm(map_kon.getComp(VPOS_KON.fixtag))
				._a_rm(map_kon.getComp(VPOS_KON.skonto_prozent))
				, new RB_gld()
				);

		
		if(is_fixierungs_position){
			grd._gld(new RB_gld()._span(4)._ins(2,2,2,2))
			._a_cm(new Separator())
			._gld(new RB_gld()._span(1)._ins(2,2,2,2));
			
			fixierung_addOn(grd, is_fixierungs_position);
		}
		
		
		return grd;
	}
	
	private E2_Grid buildZusatzInfoPanel(boolean is_fixierung) throws myException{
		int[] iBreite = {160,600};
		
		E2_Grid zusatzGrid = new E2_Grid()._setSize(iBreite)._gld(new RB_gld()._ins(2))
		._a_lt(new RB_lab("Bermerkung intern"))
		._a_lm(map_kon_trakt.getComp(VPOS_KON_TRAKT.bemerkung_intern))
		._a_lt(new RB_lab("Bemerkung für Beleg"))
		._a_lm(map_kon_trakt.getComp(VPOS_KON_TRAKT.bemerkung_extern))
		._a_lt(new RB_lab(is_fixierung?"":"Fixierungsbedingungen"))
		._a_lm(is_fixierung?new RB_lab():map_kon_trakt.getComp(VPOS_KON_TRAKT.fixierungsbedingungen))
		._a_lt(new RB_lab("AVV-Code"))
		._a_lt(map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_AVV_CODE_LIST.key()));
		return zusatzGrid;
	}
	
	
	private E2_Grid buildLiefertermine() throws myException{
		E2_Grid lieferung_grid = new E2_Grid()._s(1);
		
		KFIX_P_M_masklist_liefertermine liefertermine = (KFIX_P_M_masklist_liefertermine)map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_LIEFERTERMINE.key());

		KFIX_P_M_masklist_lieferung_zum_lager lagerlieferungen = (KFIX_P_M_masklist_lieferung_zum_lager)map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_LIEFERUNG_LIST.key());
	
		liefertermine	.get_me_in_container_ex().setHeight(new Extent(400));
		lagerlieferungen.get_me_in_container_ex().setHeight(new Extent(400));
		
		lieferung_grid = new E2_Grid()._setSize(150,900)._gld(new RB_gld()._ins(2,2,2,2));
		
		lieferung_grid	._a_lt(new RB_lab()._t("Liefertermine"))
						._a_lt(liefertermine.get_me_in_container_ex())._a()._h(new Extent(400));
		
		lieferung_grid._a(new Separator(), new RB_gld()._span(lieferung_grid.getSize()+1)._ins(0,5,0,5));
		
		lieferung_grid	._a_lt(new RB_lab("Lieferung "+ (this.belegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)?" ZUM ": " VOM ") +"Lager"))
						._endLine(lagerlieferungen.get_me_in_container_ex(), new RB_gld()._ins(2,2,2,2)._left_top());
		
		return lieferung_grid;
	}

	private E2_Grid buildAenderung() throws myException{
		int[] iBreite  ={900};
		
		E2_Grid lieferTermine = new E2_Grid()._setSize(iBreite)._gld(new RB_gld()._ins(2,2,2,2))
				._a_lm(map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_AENDERUNG.key()));
						
		return lieferTermine;
	}
	
	private void fixierung_addOn(E2_Grid position_angabe_grid, boolean is_fixierung) throws myException{
		E2_Grid grd_fur_fix = new E2_Grid()._setSize(140,125,12,20,40,20,170,300);
		
		Component comp_fur_webseite = null;
		if(ENUM_MANDANT_DECISION.USE_WEBSEITE_WAEHRUNGSKURS_KNOPF.is_YES()){
			comp_fur_webseite = new KFIX__BT_Waehrungs_kurs();
		}else{
			comp_fur_webseite = new RB_lab()._t("");
		}

		grd_fur_fix	._a("Schichttag Fixierung",new RB_gld()._ins(0,2,2,2)._left_mid())
					._a(map_kon.getComp(VPOS_KON.fixierungstag), new RB_gld()._ins(0,2,2,2)._span(5))
					._a("Fixierungsbedingungen",new RB_gld()._right_top()._ins(2,5,5,2))
					._a(is_fixierung?map_kon_trakt.getComp(VPOS_KON_TRAKT.fixierungsbedingungen):new RB_lab() , new RB_gld()._left_top()._ins(2)._span_r(5));
		
		grd_fur_fix	._a("Kurs aus Schichttag"													, new RB_gld()._ins(0,2,2,2)._left_mid())
					._a(map_kon.getComp(VPOS_KON.boersenkurs)									, new RB_gld()._ins(0,2,2,2)._left_mid()) 
					._a(new RB_lab()._t("$")													, new RB_gld()._ins(2,2,2,2)._left_mid())
					._a(new RB_lab()._t("/")													, new RB_gld()._ins(2,2,2,2)._center_mid())
					._a(map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_EINHEIT.key())	, new RB_gld()._ins(2,2,2,2)._left_mid()._span(3));
		
		grd_fur_fix	._a("Wechselkurs"															, new RB_gld()._ins(0,2,2,2)._left_mid())
					._a(map_kon.getComp(VPOS_KON.wechselkurs)									, new RB_gld()._ins(0,2,2,2)._left_mid())
					._a(new RB_lab()._t("$")													, new RB_gld()._ins(2,2,2,2)._left_mid())
					._a(new RB_lab()._t("=")													, new RB_gld()._ins(2,2,2,2)._center_mid())
					._a(new RB_lab()._t("1 "+ENUM_UNICODE.EUR.getCode())						, new RB_gld()._ins(2,2,2,2)._left_mid())
					._a(comp_fur_webseite														, new RB_gld()._ins(2,2,2,2)._left_mid()._span(2))
					;
		grd_fur_fix	._a("Prozentuale Diff."														, new RB_gld()._ins(0,2,2,2)._left_mid())
					._a(map_kon.getComp(VPOS_KON.boerse_diff_proz) 								, new RB_gld()._ins(0,2,2,2)._left_mid())
					._a(new RB_lab()._t("%")													, new RB_gld()._ins(2,2,2,2)._left_mid())
					._a(""																		, new RB_gld()._ins(2,2,2,2)._center_mid())
					._a(""																		, new RB_gld()._ins(2,2,2,2)._left_mid()._span(3));
		
		grd_fur_fix	._a("Prämie/Abschlag",new RB_gld()._ins(0,2,2,2)._left_mid())
					._a(map_kon.getComp(VPOS_KON.boerse_diff_abs) 								, new RB_gld()._ins(0,2,2,2)._left_mid())
					._a(new RB_lab()._t(ENUM_UNICODE.EUR.getCode())								, new RB_gld()._ins(2,2,2,2)._left_mid())
					._a(new RB_lab()._t("/")													, new RB_gld()._ins(2,2,2,2)._center_mid())
					._a(map_kon.getComp(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_EINHEIT_2.key())	, new RB_gld()._ins(2,2,2,2)._left_mid()._span(3));

		grd_fur_fix	._a("Preis manuell"															, new RB_gld()._ins(2,2,2,2)._left_mid())
					._a(map_kon.getComp(VPOS_KON.preis_manuell) 								, new RB_gld()._ins(2,2,2,2)._left_mid()._span(4));
					
		position_angabe_grid._a(grd_fur_fix, new RB_gld()._span(4));
		
	}
}

