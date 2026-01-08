 
package rohstoff.businesslogic.bewegung2.mask;
  
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_Grid;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_GridDefinition;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnumMaskSonderLabel;
import rohstoff.businesslogic.bewegung2.global.TK;
import rohstoff.businesslogic.bewegung2.list.B2_ConstEnumNumValues;
import rohstoff.businesslogic.bewegung2.list.B2_TransportHashMapAddons;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_ButtonPreisUndSteuerZauber;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;
 
public class B2_MaskGridV2 extends E2_Grid  {
 	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //wird benutzt, falls mehr als ein E2_Grid verwendung findet
    private MyE2_TabbedPane  ta  = new MyE2_TabbedPane(600);
    

    /*
	 * vector nimmt alle container auf, die reale felder enthalten
	 * und die zugehoerigen tab-texte (falls mehr als ein container noetig ist) 
	 */
	private VEK<RB_Grid>   										fieldContainers = 	new VEK<RB_Grid>();
    private VEK<MyString>  										tabText = 			new VEK<MyString>();
    
    private B2_MaskComponentMap_VEKTOR  	mapV;
    private B2_MaskComponentMap_STATION  	mapS1;
    private B2_MaskComponentMap_ATOM  		mapA1;
    private B2_MaskComponentMap_STATION  	mapS2;
    private B2_MaskComponentMap_ATOM  		mapA2;
    private B2_MaskComponentMap_STATION  	mapS3;

    
    
    public B2_MaskGridV2(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = B2_ConstEnumNumValues.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                             B2_ConstEnumNumValues.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        this._setSize(1600)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        mapV = 		(B2_MaskComponentMap_VEKTOR) this.m_tpHashMap.getMaskComponentMapCollector().get(RecV.key);
        mapS1 = 	(B2_MaskComponentMap_STATION) this.m_tpHashMap.getMaskComponentMapCollector().get(RecS1.key);
        mapA1 = 	(B2_MaskComponentMap_ATOM) this.m_tpHashMap.getMaskComponentMapCollector().get(RecA1.key);
        mapS2 = 	(B2_MaskComponentMap_STATION) this.m_tpHashMap.getMaskComponentMapCollector().get(RecS2.key);
        mapA2 = 	(B2_MaskComponentMap_ATOM) this.m_tpHashMap.getMaskComponentMapCollector().get(RecA2.key);
        mapS3 = 	(B2_MaskComponentMap_STATION) this.m_tpHashMap.getMaskComponentMapCollector().get(RecS3.key);
        
        RB_gld lyoTitel = new RB_gld()._ins(5, 2, 2, 10)._span(2)._col_back_d()._left_mid();
        RB_gld lyoFrontText = new RB_gld()._ins(2, 2, 2, 1)._left_top()._col_back_d();
        RB_gld lyoComp = new RB_gld()._ins(4, 1, 4, 1)._left_top();
        
        int rowHeight = 20;
        int textWidth = 100;
        
        int compWidth1 = 300;
        

        E2_GridDefinition  defSeite1 = new E2_GridDefinition()	._addColW(textWidth)._addColW(compWidth1)
        														._addColW(textWidth)._addColW(compWidth1)
        														._addColW(60)
        														._addColW(textWidth)._addColW(compWidth1)
																._setBaseRowHeight(rowHeight)
																._setPlaceHolderComponent(new RB_lab(""));


        
        int focus = 1;
       
        
        //spalte 0 und 1
        defSeite1._add( 0, 0, mapV.getComp(TK.gen(EnumMaskSonderLabel.titel_0_0)),	30,100,	lyoTitel);
        
        E2_Grid titelWichtig = new E2_Grid()._bo_no()._s(1)	._a(mapV.getComp(TK.gen(EnTabKeyInMask.V,BG_VEKTOR.en_transport_typ)),			lyoFrontText._c()._ins(0,2,0,6))
															._a(mapV.getComp(TK.gen(EnTabKeyInMask.V,BG_VEKTOR.transportverantwortung)),	lyoFrontText._c()._ins(0,2,0,6))
															._a(mapV.getComp(TK.gen(EnTabKeyInMask.V,BG_VEKTOR.planmenge)),				lyoFrontText._c()._ins(0,2,0,6))
															._a(mapV.getComp(TK.gen(EnTabKeyInMask.V,BG_VEKTOR.datum_planung_von)),		lyoFrontText._c()._ins(0,2,0,6))
															._setRowHight(rowHeight+3,rowHeight+3,rowHeight+3)._w100()
															;

        E2_Grid mengePlan = new E2_Grid()._s(2)._a((Component)mapV.get__Comp(BG_VEKTOR.planmenge)._setFI(focus++))
															._a(mapV.getComp(TK.gen(EnumMaskSonderLabel.einheit_0_0)), new RB_gld()._ins(5, 0, 0, 0))
															;

        
        RB_Grid fieldsWichtig = (RB_Grid)new RB_Grid()._bo_no()._s(1)._a((Component)mapV.get__Comp(BG_VEKTOR.en_transport_typ)._setFI(focus++),lyoComp._c()._ins(0, 0, 0, 4))
        													._a((Component)mapV.get__Comp(BG_VEKTOR.transportverantwortung)._setFI(focus++),lyoComp._c()._ins(0, 0, 0, 4))
															._a(mengePlan,lyoComp._c()._ins(0, 0, 0, 4))
															._a(	new E2_Grid()._s(2)
																	._a((Component)mapV.get__Comp(BG_VEKTOR.datum_planung_von)._setFI(focus++))
																	._a((Component)mapV.get__Comp(BG_VEKTOR.datum_planung_bis)._setFI(focus++)),lyoComp._c()._ins(0, 0, 0, 4))
															._setRowHight(rowHeight,rowHeight,rowHeight)
														;
        defSeite1._add(1,0, titelWichtig, lyoFrontText._c()._span_r(4));
        defSeite1._add(1,1, fieldsWichtig, lyoComp._c()._span_r(4));
        
        _add( 5, 0 ,defSeite1, mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.id_transportmittel);
        _add( 6, 0 ,defSeite1, mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.transportmittel);
        _add( 7, 0 ,defSeite1, mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.transportkennzeichen);
        _add( 8, 0 ,defSeite1, mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.anhaengerkennzeichen);
        _add( 9, 0 ,defSeite1, mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.id_adresse_fremdware);
        _add(10, 0 ,defSeite1, mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.id_land_transit1);
        _add(11, 0 ,defSeite1, mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.id_land_transit2);
        _add(12, 0 ,defSeite1, mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.id_land_transit3);
        
        _add( 13, 0 ,defSeite1, mapV, lyoFrontText, 	lyoComp,focus++, BG_VEKTOR.id_adresse_spedition);
        _add( 14, 0 ,defSeite1, mapA1,lyoFrontText, 	lyoComp,focus++, BG_ATOM.kontraktzwang);
        _add( 15, 0 ,defSeite1, mapA2,lyoFrontText, 	lyoComp,focus++, BG_ATOM.kontraktzwang);
        _add( 16, 0 ,defSeite1, mapV, lyoFrontText, 	lyoComp,focus++, BG_VEKTOR.ek_vk_zuord_zwang);
        _add( 17, 0 ,defSeite1, mapV, lyoFrontText, 	lyoComp,focus++, BG_VEKTOR.ek_vk_sorte_lock);

        
        defSeite1._add(18, 0, new Separator(), new RB_gld()._span(2)._center_mid());
        
        
        _add( 19, 0 ,defSeite1, mapV, lyoFrontText, 	lyoComp,focus++, BG_VEKTOR.print_anhang7);
        _add(20,0,defSeite1,mapV, lyoFrontText, 		lyoComp,focus++, BG_VEKTOR.ah7_menge,BG_VEKTOR.ah7_volumen);
        _add(21, 0 ,defSeite1, mapV, 		lyoFrontText,lyoComp, focus++, BG_VEKTOR.ah7_ausstellung_datum);
        _add(22, 0 ,defSeite1, mapA1, 		lyoFrontText,lyoComp, focus++, BG_ATOM.id_bg_pruefport_gelangensbest);
        _add(23, 0 ,defSeite1, mapA2, 		lyoFrontText,lyoComp, focus++, BG_ATOM.id_bg_pruefport_gelangensbest);
           
        
        
        defSeite1._add(24, 0, new Separator(), new RB_gld()._span(2)._center_mid());
        
        _add(25, 0 ,defSeite1, mapS1, lyoFrontText, 		lyoComp,focus++, BG_STATION.id_adresse_besitz_ldg);
        _add(26, 0 ,defSeite1, mapS2, lyoFrontText, 		lyoComp,focus++, BG_STATION.id_adresse_besitz_ldg);
        _add(27, 0 ,defSeite1, mapS3, lyoFrontText, 		lyoComp,focus++, BG_STATION.id_adresse_besitz_ldg);
       
        //spalte 3 und 4
        defSeite1._add( 0, 2, 	mapV.getComp(TK.gen(EnumMaskSonderLabel.titel_0_1)),	30,100,	lyoTitel);
        _add( 1, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_vpos_kon);
        _add( 2, 2 ,defSeite1, mapS1, lyoFrontText, 		lyoComp,focus++, BG_STATION.id_adresse);
        _add( 3, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_vpos_std);
        _add( 4, 2 , defSeite1, mapS1, lyoFrontText,lyoComp, focus++, BG_STATION.umsatzsteuerlkz, BG_STATION.umsatzsteuerid);
        _add( 5, 2 ,defSeite1, mapS1, lyoFrontText, 		lyoComp,focus++, BG_STATION.telefon);
        _add( 6, 2 ,defSeite1, mapS1, lyoFrontText, 		lyoComp,focus++, BG_STATION.fax);
        _add( 7, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.bestellnummer);
        _add( 8, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.postennummer);
        _add( 9, 2 ,defSeite1, mapS1, lyoFrontText, 		lyoComp,focus++, BG_STATION.oeffnungszeiten);
        _add(10, 2 , defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.id_artikel_bez);
        _add(11, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_eak_code);
        _add(12, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.notifikation_nr);
        _add(13, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.datum_ausfuehrung);
        _add(14, 2 , defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.id_zahlungsbedingungen, BG_ATOM.zahlungsbedingungen);
        _add(15, 2 , defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.id_lieferbedingungen, BG_ATOM.lieferbedingungen);
        _add(16,2,	defSeite1, lyoFrontText,lyoComp, focus++, TK.gen(EnTabKeyInMask.A1, BG_ATOM.menge),mapA1.getComp(BG_ATOM.menge),mapV.getComp(TK.gen(EnumMaskSonderLabel.einheit_0_1)));
        _add(17, 2 ,defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.menge_abzug, BG_ATOM.menge_verteilung);
        _add(18, 2 ,defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.menge_netto, BG_ATOM.menge_abrech);
        _add(19, 2 ,defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.e_preis_basiswaehrung, BG_ATOM.manuell_preis);
        _add(20,2,	defSeite1, lyoFrontText,lyoComp, focus++, TK.gen(EnTabKeyInMask.A1, BG_ATOM.id_waehrung),		 mapA1.getComp(BG_ATOM.id_waehrung)
        																										,mapA1.getComp(BG_ATOM.waehrungskurs)
        																										,mapV.getComp(TK.gen(EnumMaskSonderLabel.waehrung_0_1))
        																										);
        _add(21, 2 ,defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.e_preis_fremdwaehrung);
        _add(22, 2 ,defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.id_tax, BG_ATOM.steuersatz);
        _add(23, 2 ,defSeite1, mapA1, lyoFrontText,lyoComp, focus++,	BG_ATOM.eu_steuer_vermerk);
        _add(24, 2 ,defSeite1, mapA1, lyoFrontText,lyoComp, focus++, BG_ATOM.intrastat_meld, BG_ATOM.transit_meld,BG_ATOM.eu_vertrag_check);
		_add(25, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_bg_pruefprot_menge);
		_add(26, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_bg_pruefprot_preis);
		_add(27, 2 ,defSeite1, mapA1, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_bg_pruefprot_abschluss);

        

        //TOOLSPALTE 4
        defSeite1._add( 0, 4,  new RB_lab(""),								30,30,		lyoTitel._c()._span(1));
        defSeite1._add( 22, 4, mapV.getComp(B2_ButtonPreisUndSteuerZauber.key()),	30,30,		lyoComp._c()._span(1)._center_mid()._ins(0,0,5,0));
        defSeite1._add( 23, 4, mapV.getComp(BG_VEKTOR.id_handelsdef),		60,60,		lyoComp._c()._span(1)._span_r(2)._center_mid()._ins(0,0,5,0));
        
        
        //spalte 5 und 6
        defSeite1._add( 0, 5, mapV.getComp(TK.gen(EnumMaskSonderLabel.titel_0_2)),	30,100,		lyoTitel);
//        

        _add( 1, 5 ,defSeite1, mapA2, lyoFrontText, lyoComp,focus++, BG_ATOM.id_vpos_kon);
        _add( 2, 5 ,defSeite1, mapS3, lyoFrontText, lyoComp,focus++, BG_STATION.id_adresse);
        _add( 3, 5 ,defSeite1, mapA2, lyoFrontText, lyoComp,focus++, BG_ATOM.id_vpos_std);
        _add( 4, 5 , defSeite1, mapS3, lyoFrontText,lyoComp, focus++, BG_STATION.umsatzsteuerlkz, BG_STATION.umsatzsteuerid);
        _add( 5, 5 ,defSeite1, mapS3, lyoFrontText, lyoComp,focus++, BG_STATION.telefon);
        _add( 6, 5 ,defSeite1, mapS3, lyoFrontText, lyoComp,focus++, BG_STATION.fax);
        _add( 7, 5 ,defSeite1, mapA2, lyoFrontText, lyoComp,focus++, BG_ATOM.bestellnummer);
        _add( 8, 5 ,defSeite1, mapA2, lyoFrontText, lyoComp,focus++, BG_ATOM.postennummer);
        _add( 9, 5 ,defSeite1, mapS3, lyoFrontText, lyoComp,focus++, BG_STATION.oeffnungszeiten);
        _add(10, 5 , defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.id_artikel_bez);
        _add(11, 5 ,defSeite1, mapA2, lyoFrontText, lyoComp,focus++, BG_ATOM.id_eak_code);
        _add(12, 5 ,defSeite1, mapA2, lyoFrontText, lyoComp,focus++, BG_ATOM.notifikation_nr);
        _add(13, 5 ,defSeite1, mapA2, lyoFrontText, lyoComp,focus++, BG_ATOM.datum_ausfuehrung);
        _add(14, 5 , defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.id_zahlungsbedingungen, BG_ATOM.zahlungsbedingungen);
        _add(15, 5 , defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.id_lieferbedingungen, BG_ATOM.lieferbedingungen);
        _add(16,5,	defSeite1, lyoFrontText,lyoComp, focus++, TK.gen(EnTabKeyInMask.A2, BG_ATOM.menge),mapA2.getComp(BG_ATOM.menge),mapV.getComp(TK.gen(EnumMaskSonderLabel.einheit_0_2)));
        _add(17, 5 ,defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.menge_abzug, BG_ATOM.menge_verteilung);
        _add(18, 5 ,defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.menge_netto, BG_ATOM.menge_abrech);
        _add(19, 5 ,defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.e_preis_basiswaehrung, BG_ATOM.manuell_preis);
        _add(20,5,	defSeite1, lyoFrontText,lyoComp, focus++, TK.gen(EnTabKeyInMask.A2, BG_ATOM.id_waehrung),		 mapA2.getComp(BG_ATOM.id_waehrung)
        																										,mapA2.getComp(BG_ATOM.waehrungskurs)
        																										,mapV.getComp(TK.gen(EnumMaskSonderLabel.waehrung_0_2))
        																										);
        _add(21, 5 ,defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.e_preis_fremdwaehrung);
        _add(22, 5 ,defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.id_tax, BG_ATOM.steuersatz);
        _add(23, 5 ,defSeite1, mapA2, lyoFrontText,lyoComp, focus++,	BG_ATOM.eu_steuer_vermerk);
        _add(24, 5 ,defSeite1, mapA2, lyoFrontText,lyoComp, focus++, BG_ATOM.intrastat_meld, BG_ATOM.transit_meld,BG_ATOM.eu_vertrag_check);
		_add(25, 5 ,defSeite1, mapA2, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_bg_pruefprot_menge);
		_add(26, 5 ,defSeite1, mapA2, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_bg_pruefprot_preis);
		_add(27, 5 ,defSeite1, mapA2, lyoFrontText, 		lyoComp,focus++, BG_ATOM.id_bg_pruefprot_abschluss);

        //beginn erster tab
 

//        E2_GridDefinition  defSeite2 = new E2_GridDefinition()	._addColW(textWidth)._addColW(compWidth1)
//        														._addColW(60)
//        														._addColW(textWidth)._addColW(compWidth1)
//        														._setBaseRowHeight(rowHeight)
//        														._setPlaceHolderComponent(new RB_lab(""));

        E2_GridDefinition  defSeite2 = new E2_GridDefinition()	._addColW(textWidth)._addColW(compWidth1)
																._addColW(textWidth)._addColW(compWidth1)
																._addColW(60)
																._addColW(textWidth)._addColW(compWidth1)
																._setBaseRowHeight(rowHeight)
																._setPlaceHolderComponent(new RB_lab(""));


        //spalten 0 und 1
        defSeite2._add(		 0,  0, mapV.getComp(TK.gen(EnumMaskSonderLabel.titel_1_0)),	30,100,		lyoTitel);
        
        _add(1, 0 ,defSeite2, mapV, lyoFrontText,lyoComp, focus++, BG_VEKTOR.id_verpackungsart);
        _add(2, 0 ,defSeite2, mapV, lyoFrontText,lyoComp, focus++, BG_VEKTOR.id_verarbeitung);
        _add(3,  0 ,defSeite2, mapV, lyoFrontText._c()._span_r(4),lyoComp._c()._span_r(4), focus++, BG_VEKTOR.bemerkung);
        _add(7,  0 ,defSeite2, mapV, lyoFrontText._c()._span_r(4),lyoComp._c()._span_r(4), focus++, BG_VEKTOR.bemerkung_sachbearbeiter);
        _add(11, 0 ,defSeite2, mapV, lyoFrontText._c()._span_r(4),lyoComp._c()._span_r(4), focus++, BG_VEKTOR.bemerkung_fuer_kunde);
        
        
        //spalten 2 und 3 
        	
        int ind = 0;
        defSeite2._add(     ind++, 2, mapV.getComp(TK.gen(EnumMaskSonderLabel.titel_1_1)), 30,100, lyoTitel);

        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.name1,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.name2,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.name3,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.strasse,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.hausnummer,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.id_land,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.plz,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.ort,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapS1, BG_STATION.ortzusatz,   lyoFrontText,lyoComp, focus++);
        
        _add2( ind++, 2 ,defSeite2, mapA1, BG_ATOM.anr1,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapA1, BG_ATOM.anr2,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapA1, BG_ATOM.artbez1,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapA1, BG_ATOM.artbez2,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 2 ,defSeite2, mapA1, BG_ATOM.id_zolltarifnummer,   lyoFrontText,lyoComp, focus++);

        
        //spalte 4 platzhalter
        defSeite2._add( 0, 4,  new E2_Grid()._s(1)._w(60),			lyoTitel._c()._span(1));
        
        //spalten 4 und 5
        ind = 0;
        defSeite2._add( 	ind++, 5, mapV.getComp(TK.gen(EnumMaskSonderLabel.titel_1_2)),	30,100, lyoTitel);
        
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.name1,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.name2,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.name3,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.strasse,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.hausnummer,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.id_land,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.plz,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.ort,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapS3, BG_STATION.ortzusatz,   lyoFrontText,lyoComp, focus++);
        
        _add2( ind++, 5 ,defSeite2, mapA2, BG_ATOM.anr1,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapA2, BG_ATOM.anr2,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapA2, BG_ATOM.artbez1,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapA2, BG_ATOM.artbez2,   lyoFrontText,lyoComp, focus++);
        _add2( ind++, 5 ,defSeite2, mapA2, BG_ATOM.id_zolltarifnummer,   lyoFrontText,lyoComp, focus++);
       
        
        this.m_tpHashMap._setToExtender(B2_TransportHashMapAddons.MASK_BASIC_GRID, fieldContainers);
        
        tabText._a(S.ms("Primärangaben"));
        tabText._a(S.ms("Zusatzangaben"));
    
        RB_Grid seite1 = new RB_Grid();
        RB_Grid seite2 = new RB_Grid();
        
        fieldContainers._ar((RB_Grid)defSeite1.applyTo(seite1));        
        fieldContainers._ar((RB_Grid)defSeite2.applyTo(seite2));        
     
        this.renderMask();
        
        this.resize(B2_ConstEnumNumValues.MASKPOPUP_WIDTH.getValue(),
        			  B2_ConstEnumNumValues.MASKPOPUP_HEIGHT.getValue());
        

    }
    
    
    private void _add(	int 				zeile, 		
    					int 				startSpalte,
    					E2_GridDefinition 	gridDef, 
    					RB_ComponentMap 	map, 
    					GridLayoutData 		layoutText, 
    					GridLayoutData 		layoutData, 
    					int focusNumber,
    					IF_Field ... 		field) throws myException {
    	
    	try {
    		EnTabKeyInMask enumTab = EnTabKeyInMask.findEnumTab(map.getOwnMaskKey());

			IF_Field firstField = field[0];      //das erste feld bestimmt die beschriftung aus der json-datei

			Component text =	mapV.getComp(TK.gen(enumTab, firstField));
			
			Component dataField = null;
			if (field.length==1) {
				dataField = map.getComp(firstField);
			} else {
				E2_Grid grid = new E2_Grid()._s(field.length);
				for (IF_Field f: field) {
					grid._a(map.getComp(f), new RB_gld()._ins(0, 0, 5, 0));
				}
				dataField = grid;
			}
			
			gridDef._addPair(zeile, startSpalte, text, dataField, layoutText, layoutData);
		} catch (Exception e) {
			e.printStackTrace();
			gridDef._addPair(zeile, startSpalte, new RB_lab("@err"), new RB_lab("@err"), layoutText, layoutData);
		}
    }
    

    private void _add(	int 				zeile, 		
						int 				startSpalte,
						E2_GridDefinition 	gridDef, 
						GridLayoutData 		layoutText, 
						GridLayoutData 		layoutData, 
						int 				focusNumber,
						TK  				textKey,
						Component ... 		field) throws myException {
		
		try {
			
			Component text =	mapV.getComp(textKey);
			
			Component dataField = null;
			if (field.length==1) {
				dataField =field[0];
			} else {
				E2_Grid grid = new E2_Grid()._s(field.length);
				for (Component f: field) {
					grid._a(f, new RB_gld()._ins(0, 0, 5, 0));
				}
				dataField = grid;
			}
			
			gridDef._addPair(zeile, startSpalte, text, dataField, layoutText, layoutData);
		} catch (Exception e) {
			e.printStackTrace();
			gridDef._addPair(zeile, startSpalte, new RB_lab("@err"), new RB_lab("@err"), layoutText, layoutData);
		}
	}

    
    
    


    private void renderMask() throws myException {
    
    	if (this.fieldContainers.size()==1) {
    		this._a(this.fieldContainers.get(0));
    	} else {
    		for (int i=0; i<this.fieldContainers.size(); i++) {
    			MyString s_tab = this.tabText.size()>i?S.NN(this.tabText.get(i), S.ms("..")):S.ms("Tab Nr: ").ut(" "+(i+1));
    			this.ta.add_Tabb(s_tab, this.fieldContainers.get(i));
    		}
    		this._a(this.ta);
    	}
    }
  
  
  	 /*
  	  * zieht bei groessenaenderungen der maske die interne tab-kompontente mit
  	  */
     public void resize(int width, int height) {
	   this.ta.setWidth(new Extent(width-60));
	   this.ta.setHeight(new Extent(height-170));
	 }

     
     
     
 	@Override
 	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException {

 		Vector<IF_ADDING_Allowed> v_rueck = new Vector<>();
 		v_rueck.addAll(this.fieldContainers);
 		return v_rueck;
 	}


    private void _add2(	int 				zeile, 		
				int 				startSpalte,
				E2_GridDefinition 	gridDef, 
				RB_ComponentMap 	map, 
				IF_Field 			field, 
				GridLayoutData 		layoutText, 
				GridLayoutData 		layoutData,
				int focusNumber) throws myException {
	
	try {
		EnTabKeyInMask enumTab = EnTabKeyInMask.findEnumTab(map.getOwnMaskKey());
		
		//RB_KF textKey = 	B2_Lib.genTextKey(map.getOwnMaskKey(), field);
		Component text =	mapV.getComp(TK.gen(enumTab, field));
		Component dbComp = 	map.getComp(field);
		dbComp.setFocusTraversalIndex(focusNumber);
		
		gridDef._addPair(zeile, startSpalte, text, dbComp, layoutText, layoutData);
		} catch (Exception e) {
		e.printStackTrace();
		gridDef._addPair(zeile, startSpalte, new RB_lab("@err"), new RB_lab("@err"), layoutText, layoutData);
		}
	
	}


    
}
 
 
