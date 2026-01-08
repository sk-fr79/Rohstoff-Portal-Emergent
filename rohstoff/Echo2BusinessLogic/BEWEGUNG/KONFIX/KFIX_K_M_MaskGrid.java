package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughter;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_K_M_MaskGrid extends E2_Grid4Mask {

	private KFIX_K_M__ComponentMapCollector mapColl ;


	IF_wrappedMulticomponentsInGrid  wrap = (Component... comps )-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._gld(new RB_gld()._ins(0,0,10,0))._a_cm(c);} return g._s(comps.length); };

	IF_wrappedMulticomponentsInGrid  wrap_r = (Component... comps )-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._gld(new RB_gld()._ins(5,0,5,0))._a_rm(c);} return g._s(comps.length); };

	private int breite_beschriftung = 150;

	private VORGANGSART vorgangart = null;
	
	public KFIX_K_M_MaskGrid(KFIX_K_M_MaskModulContainer oContainer, KFIX_K_M__ComponentMapCollector  oMapColl, E2_NavigationList naviList) throws myException {

		this._s(1)._st(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		this.vorgangart = oMapColl.get_vorgangtyp_ek_oder_vk();
		
		mapColl = oMapColl;

		KFIX_K_M_ComponentMap  map1 = (KFIX_K_M_ComponentMap) oMapColl.get(new RB_KM(_TAB.vkopf_kon));

		MyE2_TabbedPane oTabbed = new MyE2_TabbedPane(null);//map1.rb_Comp(BSK_K_CONST.ADDITIONNAL_COMP.TAB_FIXIERUNG.key());
		oTabbed.set_bAutoHeight(true);

		if(mapColl.isFixierungsKontrakte()){
			oTabbed.add_Tabb(new MyE2_String("Adresse / Fixierung"), 	build_Adresse(map1));
		}else{
			oTabbed.add_Tabb(new MyE2_String("Adresse"), 				build_Adresse(map1));
		}
		oTabbed.add_Tabb(new MyE2_String("Weiteres.."), 				buildWeiteresTab(map1));
		oTabbed.add_Tabb(new MyE2_String("Texte/Bem."), 				buildBemerkungTab(map1));  
		oTabbed.add_Tabb(new MyE2_String("Positionen"), 				buildPositionTab(map1, naviList));
		oTabbed.add_Tabb(new MyE2_String("Druckprotokoll"),				buildDruckTab(map1));

		this.add(oTabbed);
	}



	private E2_Grid buildPositionTab(KFIX_K_M_ComponentMap map, E2_NavigationList naviList) throws myException {
		
		return new KFIX_K_M_masklist_position_grid_4_tab(map, naviList);
	}

	

	private E2_Grid buildWeiteresTab(KFIX_K_M_ComponentMap map1) throws myException {

		RB_gld gld_firstline = 		new RB_gld()._ins(2,10,2,2);
		RB_gld gld = 				new RB_gld()._ins(2);

		
		int[] breite = {this.breite_beschriftung,250,250,250,250};
		
		E2_Grid weiteresTab = new E2_Grid()._gld(new RB_gld()._ins(E2_INSETS.I(2)))._setSize(breite);

		KFIX_K_M_BT_Select_Fremd_Ansprechpartner bt_fremd_ansprechPartner = new KFIX_K_M_BT_Select_Fremd_Ansprechpartner();
		bt_fremd_ansprechPartner.EXT().set_oComponentMAP(map1);

		//zeile fuer zeile ein block
		weiteresTab	._a(new RB_lab("Erfasser Vorgang"),												gld_firstline)
					._a(map1.getComp(VKOPF_KON.id_user),											gld_firstline)
					._a(map1.getComp(VKOPF_KON.name_bearbeiter_intern),								gld_firstline)
					._a(wrap.grid(new RB_lab("Tel:"),map1.getComp(VKOPF_KON.tel_bearbeiter_intern)),gld_firstline)
					._a(wrap.grid(new RB_lab("Fax:"),map1.getComp(VKOPF_KON.fax_bearbeiter_intern)),gld_firstline);

		weiteresTab	._a(new RB_lab("Händler"),														gld)
					._a(map1.getComp( VKOPF_KON.id_user_ansprech_intern),							gld)
					._a(map1.getComp( VKOPF_KON.name_ansprech_intern),								gld)
					._a(wrap.grid( new RB_lab("Tel:"),map1.getComp(VKOPF_KON.tel_ansprech_intern)),	gld)
					._a(wrap.grid( new RB_lab("Fax:"),map1.getComp(VKOPF_KON.fax_ansprech_intern)),	gld);

		weiteresTab	._a(new RB_lab("Sachbearbeiter"))
					._a(map1.getComp(VKOPF_KON.id_user_sachbearb_intern),							gld)
					._a(map1.getComp(VKOPF_KON.name_sachbearb_intern),								gld)
					._a(wrap.grid(new RB_lab("Tel:"),map1.getComp(VKOPF_KON.tel_sachbearb_intern)),	gld)
					._a(wrap.grid(new RB_lab("Fax:"),map1.getComp(VKOPF_KON.fax_sachbearb_intern)),	gld);


		weiteresTab._a(new Separator(), new RB_gld()._span(weiteresTab.getSize())._ins(0,5,0,5));

		weiteresTab	._a(new RB_lab("Lieferbedingungen"), 											gld)
					._endLine(map1.getComp(VKOPF_KON.lieferbedingungen), 						gld);
					
		weiteresTab	._a(new RB_lab("Zahlungsbedingungen"), 											gld)
					._a(map1.getComp(VKOPF_KON.id_zahlungsbedingungen), 							gld)
					._a(map1.getComp(VKOPF_KON.zahlungsbedingungen), 								gld)
					._endLine(wrap.grid(map1.getComp(VKOPF_KON.zahltage),map1.getComp(VKOPF_KON.fixmonat),map1.getComp(VKOPF_KON.fixtag),map1.getComp(VKOPF_KON.skonto_prozent)),gld);
							
		weiteresTab._a(new Separator(), new RB_gld()._span(weiteresTab.getSize())._ins(0,5,0,5));

		weiteresTab._a(new RB_lab("Name Ansprechpartner"), 											gld)
					._endLine(wrap.grid(map1.getComp(VKOPF_KON.name_ansprechpartner),map1.getComp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.MASK_ANSPRECHPARTNER_BT.key())),gld);

		weiteresTab._a(new RB_lab("Telefon auf Formular"), 											gld)
					._endLine(map1.getComp(VKOPF_KON.telefon_auf_formular),					gld);
		
		weiteresTab._a(new RB_lab("Telefax auf Formular"), 											gld)
					._endLine(map1.getComp(VKOPF_KON.telefax_auf_formular),					gld);
		
		weiteresTab._a(new RB_lab("e-Mail auf Formular"),											gld)
					._endLine(map1.getComp(VKOPF_KON.email_auf_formular), 						gld);
		
		return weiteresTab;
	}


	private E2_Grid buildBemerkungTab(KFIX_K_M_ComponentMap map1) throws myException {
		
		RB_gld gld_firstline = 		new RB_gld()._ins(2,10,2,2);
		RB_gld gld = 				new RB_gld()._ins(2);

		E2_Grid bemerkungGrid = new E2_Grid()._setSize(this.breite_beschriftung,600);
		
		bemerkungGrid	._a(new RB_lab("Formulartext (Anfang)"),						gld_firstline)
						._a(map1.getComp(VKOPF_KON.formulartext_anfang),				gld_firstline);
		
		bemerkungGrid	._a(new RB_lab("Formulartext (Ende)"),							gld)
						._a(map1.getComp(VKOPF_KON.formulartext_ende),		gld);
		
		bemerkungGrid	._a(new RB_lab("Bemerkungen (Intern)"),							gld)
						._a(map1.getComp(VKOPF_KON.bemerkungen_intern),		gld);
		
		return bemerkungGrid;
		
	}


	private E2_Grid build_Adresse(KFIX_K_M_ComponentMap map1) throws myException {

		RB_gld gld = 				new RB_gld()._ins(2)._span(2);
		RB_gld gld_beschriftung = 	new RB_gld()._ins(2);
		RB_gld gld_adressblock = 	new RB_gld()._ins(1,2,2,2)._span(2)._left_top();
		
		E2_Grid adresse_grd = new E2_Grid();

		E2_Grid innenGrd = new E2_Grid()._setSize(this.breite_beschriftung-3, 150, 150, 159, 150, 150)._bo_d();
		innenGrd
		._a(new RB_lab("Vorgang-ID")		._i(), new RB_gld()._ins(2)._col(new E2_ColorDark()))
		._a(new RB_lab("Kontrakt-Nr.")		._i(), new RB_gld()._ins(2)._col(new E2_ColorDark()))
		._a(new RB_lab("Abgeschlossen")		._i(), new RB_gld()._ins(2)._col(new E2_ColorDark()))
		._a(new RB_lab("Fixierungskontrakt")._i(), new RB_gld()._ins(2)._col(new E2_ColorDark()))
		._a(new RB_lab("Druckdatum")		._i(), new RB_gld()._ins(2)._col(new E2_ColorDark()))
		._a(new RB_lab("Erstellungdatum")	._i(), new RB_gld()._ins(2)._col(new E2_ColorDark()));
		innenGrd
		._a(map1.getComp(VKOPF_KON.id_vkopf_kon), 		new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(VKOPF_KON.buchungsnummer), 	new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(VKOPF_KON.abgeschlossen),		new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(VKOPF_KON.ist_fixierung) ,		new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(VKOPF_KON.druckdatum),			new RB_gld()._ins(2)._left_top())
		._a(map1.getComp(VKOPF_KON.erstellungsdatum),	new RB_gld()._ins(2)._left_top())
		;
		adresse_grd._a(innenGrd, new RB_gld()._span(3)._ins(2));
			
		String adressansprache = (this.vorgangart==VORGANGSART.EK_KONTRAKT?"Lieferant":"Abnehmer");
		
		adresse_grd._a(new RB_lab()._t(adressansprache),								gld_beschriftung)
		._a(map1.getComp(VKOPF_KON.id_adresse),	gld_adressblock);

		adresse_grd._a(new RB_lab()._t("Währung"),										gld_beschriftung)
					._a(map1.getComp( VKOPF_KON.id_waehrung_fremd), 	gld);
		
		adresse_grd._a(new MyE2_Label("Kunden-Nummer"),									gld_beschriftung)
					._a(map1.getComp( VKOPF_KON.kdnr), 				gld);
		
		adresse_grd._a(new RB_lab()._t("Vorname"),										gld_beschriftung)
					._a(map1.getComp( VKOPF_KON.vorname), 			gld);
					
		adresse_grd._a(new RB_lab()._t("Name1"),										gld_beschriftung)
					._a(map1.getComp( VKOPF_KON.name1), 				gld);
		
		adresse_grd._a(new RB_lab()._t("Name2"),										gld_beschriftung)
					._a(map1.getComp( VKOPF_KON.name2),	 			gld);

		adresse_grd._a(new RB_lab()._t("Name3"),										gld_beschriftung)
					._a(map1.getComp( VKOPF_KON.name3), 				gld);

		adresse_grd._a(new RB_lab()._t("Strasse"),										gld_beschriftung)
					._a(wrap.grid(map1.getComp( VKOPF_KON.strasse),new RB_lab("Hausnummer"),map1.getComp( VKOPF_KON.hausnummer)), gld);

		adresse_grd._a(new RB_lab()._t("Land - PLZ - Ort"),								gld_beschriftung)
					._a(wrap.grid(map1.getComp( VKOPF_KON.laendercode),map1.getComp( VKOPF_KON.plz),map1.getComp( VKOPF_KON.ort)), gld);

		adresse_grd._a(new RB_lab()._t("Ortzusatz"),									gld_beschriftung)
					._a(map1.getComp( VKOPF_KON.ortzusatz), 			gld);

		adresse_grd._a(new RB_lab()._t("PLZ - Postfach"),								gld_beschriftung)
					._a(wrap.grid(map1.getComp( VKOPF_KON.plz_pob) ,map1.getComp( VKOPF_KON.pob),new RB_lab("Postfach aktiv?"),map1.getComp(VKOPF_KON.is_pob_active)), gld);

		if(map1.isFixiert()){
			fixierung_add_on(adresse_grd, map1);
		}
		
		adresse_grd._setSize(this.breite_beschriftung,1000);
		
		return adresse_grd;
	}

	private void fixierung_add_on(E2_Grid adresse_grid, KFIX_K_M_ComponentMap map1) throws myException{
		
		RB_gld gld 				= new RB_gld()._ins(2)._span(2);
		RB_gld gld_beschriftung = new RB_gld()._ins(2);
		RB_gld lyout_title 		= new RB_gld()._right_top()._col(new E2_ColorDark())._ins(2,2,4,2);
		RB_gld lyout_data 		= new RB_gld()._right_top()._ins(2,2,4,2);
		
		adresse_grid._a(new Separator(), new RB_gld()._span(adresse_grid.getSize())._ins(0,5,0,5));
		
		adresse_grid._a(new RB_lab("Laufzeit Fix.vertrag"),	gld_beschriftung)
					._a(wrap.grid(	map1.getComp(VKOPF_KON.fix_von)
									,new RB_lab("bis")
									,map1.getComp(VKOPF_KON.fix_bis)
									,map1.getComp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.HASH_KEY_BT_SET_ACTUAL_MONTH.key())
									,map1.getComp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_ONE.key())
									,map1.getComp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_TWO.key()))
						, new RB_gld()._ins(2)._span(3));

		adresse_grid._a(new RB_lab("Eigene Fixierungs-Nr."),	gld_beschriftung);
		adresse_grid._a(new E2_Grid()._setSize(128,162,150)
						._a(map1.getComp(VKOPF_KON.fixnummer_eigen))
						._a(new RB_lab("Fremde Fixierungs-Nr."), new RB_gld()._right_top()._ins(0,0,5,0))
						._a(map1.getComp(VKOPF_KON.fixnummer_fremd))
						, gld);

		adresse_grid._a(new RB_lab()._t("Prozentuale Diff."),  gld_beschriftung);	
		adresse_grid._a(new E2_Grid()._setSize(125,15, 150, 125, 50)
						._a(map1.getComp(VKOPF_KON.boerse_diff_proz))
						._a(new RB_lab()._t("%"))		
						._a(new RB_lab()._t("Prämie/Abschlag"), new RB_gld()._right_top()._ins(0,0,5,0))
						._a(map1.getComp(VKOPF_KON.boerse_diff_abs))		
						._a(new RB_lab()._t("Eur/t")), gld);

		adresse_grid._a(new RB_lab("Sorte"), gld_beschriftung)
		._a(map1.getComp(VKOPF_KON.fix_id_artbez), gld);
				
		E2_Grid  g_innen = new E2_Grid()._bo_dd()._setSize(130,139,150,150,150);
		
		g_innen	._a(new RB_lab("Gesamte Menge")._i(), 				lyout_title)
				._a(new RB_lab("Fixierte Menge")._i(), 			lyout_title)
				._a(wrap_r.grid(new RB_lab("Mge. nicht fixiert")._i(), 	new KFIX_K_M_BT_fixierung_rechnen(map1, true)), lyout_title)	
				._a(new RB_lab("Gelieferte Mge.")._i(), 				lyout_title)
				._a(wrap_r.grid(new RB_lab("Restmenge")._i(), 		new KFIX_K_M_BT_fixierung_rechnen(map1, false)),lyout_title)
				._a(map1.getComp(VKOPF_KON.fix_menge_gesamt), 										lyout_data)
				._a(map1.getComp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT.key()), 				lyout_data)
				._a(map1.getComp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT_RESTMENGE.key()), 	lyout_data)
				._a(map1.getComp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT.key()), 			lyout_data)
				._a(map1.getComp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key()),	lyout_data)
				;

		adresse_grid._a(new RB_lab()._t("Fixierungsangaben"), new RB_gld()._ins(0,2,0,2))		
					._a(g_innen, new RB_gld()._span(3));
				
		adresse_grid._a(new RB_lab("Fixierungsbedingungen"))
					._a(map1.getComp(VKOPF_KON.bemerkung_fix1.fk()), 	gld);
		
		adresse_grid._a()
					._a(wrap.grid(map1.getComp(VKOPF_KON.kopie_bemerkung_auf_pos), new RB_lab("Fixierungsbedingungen auf Position übernehmen")));
	}

	private E2_Grid buildDruckTab(KFIX_K_M_ComponentMap map1) throws myException {

		int[] iBreite = {160,150,150,500};

		E2_Grid druckTab = new E2_Grid();

//		MyE2_ContainerEx containerWithList = new MyE2_ContainerEx();
//		containerWithList.setWidth(new Extent(100));
//		containerWithList.setHeight(new Extent(100));
//		containerWithList.add((Component)map1.get__Comp(KFIX___CONST.ADDITIONNAL_COMP.DAUGHTERTABLE_PRINTPROTOKOLL.name()));
		
		MyE2_ContainerEx containerWithList = ((RB_MaskDaughter)map1.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.DAUGHTERTABLE_PRINTPROTOKOLL.name())).get_me_in_container_ex();
		
		druckTab._setSize(iBreite)._a(new RB_lab("Anzahl Entsperrungen"), new RB_gld()._ins(2))
					._a(new E2_Grid()._setSize(110,100,240)
					._a(map1.getComp(VKOPF_KON.zaehler_entsperrung), 	new RB_gld()._ins(2,3,2,3))
					._a(new RB_lab("Druckzähler: "), 					new RB_gld()._ins(2,3,5,3)._right_mid())
					._a(map1.getComp(VKOPF_KON.druckzaehler), 			new RB_gld()._ins(2,3,2,3))
			, new RB_gld()._span(3))
		
		._a(new RB_lab("Druckprotokoll"), new RB_gld()._ins(2))
		._a(containerWithList, new RB_gld()._ins(2)._span(3)) ;

		return druckTab;
	}








}

