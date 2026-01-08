
package rohstoff.businesslogic.bewegung2.list;

import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_BewegungArt;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_Land;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_PlanDatumVonBis;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_Ursprung;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_firmaBesitzer;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_firmaLager;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_haendler;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_kontrakt;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_loeschen;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_sorten;
import rohstoff.businesslogic.bewegung2.list.listSelector.B2_listSelector_uma;
import rohstoff.businesslogic.bewegung2.list.listSelector.IF_CanBePopulated_NG;
public class B2_ListSelector extends E2_ExpandableRow {

	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */

	private E2_SelectionComponentsVector     oSelVector = null;

	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;


	public B2_ListSelector(RB_TransportHashMap  p_tpHashMap) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		this.m_tpHashMap = p_tpHashMap;       
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );

		VEK<IF_CanBePopulated_NG> vAngeschlosseneStart = new VEK<IF_CanBePopulated_NG>();
		VEK<IF_CanBePopulated_NG> vAngeschlosseneZiel = new VEK<IF_CanBePopulated_NG>();
		
		this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());

		B2_listSelector_firmaLager sel_lager_start 		= new B2_listSelector_firmaLager(oSelVector,EnTabKeyInMask.S1);
		vAngeschlosseneStart._a(sel_lager_start);

		B2_listSelector_firmaLager sel_lager_ziel 		= new B2_listSelector_firmaLager( oSelVector,EnTabKeyInMask.S3);
		vAngeschlosseneZiel._a(sel_lager_ziel);
	
		B2_listSelector_firmaBesitzer start_besitzer_sel= new B2_listSelector_firmaBesitzer(sel_lager_start, EnTabKeyInMask.S1);
		
		B2_listSelector_firmaBesitzer ziel_besitzer_sel = new B2_listSelector_firmaBesitzer(sel_lager_ziel, EnTabKeyInMask.S3);
		
		B2_listSelector_BewegungArt sel_bew_art 		=  new B2_listSelector_BewegungArt();
		B2_listSelector_sorten sel_sorte_ek 			= new B2_listSelector_sorten(oSelVector, EnTabKeyInMask.A1);
		B2_listSelector_sorten sel_sorte_vk 			= new B2_listSelector_sorten(oSelVector, EnTabKeyInMask.A2);

		B2_listSelector_kontrakt 				sel_kontrakt_ek 	= new B2_listSelector_kontrakt(oSelVector, EnTabKeyInMask.A1);
		B2_listSelector_kontrakt 				sel_kontrakt_vk 	= new B2_listSelector_kontrakt(oSelVector, EnTabKeyInMask.A2);

		B2_listSelector_Land 					sel_start_land 		= new B2_listSelector_Land(EnTabKeyInMask.S1);
		B2_listSelector_Land 					sel_ziel_land 		= new B2_listSelector_Land(EnTabKeyInMask.S3);

		B2_listSelector_haendler 				sel_haendler		= new B2_listSelector_haendler();
		
		B2_listSelector_loeschen 				sel_loeschen 		= new B2_listSelector_loeschen(true);
		B2_listSelector_uma 					sel_uma 			= new B2_listSelector_uma();
		B2_listSelector_PlanDatumVonBis 		selPlanDatumVonBis 	= new B2_listSelector_PlanDatumVonBis();
		B2_listSelector_Ursprung   				selUrsprung   		= new B2_listSelector_Ursprung();
		
		
//		B2_listSelector_storno sel_storno = new B2_listSelector_storno();
		

		this.oSelVector.add(start_besitzer_sel);
		this.oSelVector.add(sel_lager_start);

		this.oSelVector.add(ziel_besitzer_sel);
		this.oSelVector.add(sel_lager_ziel);

		this.oSelVector.add(sel_bew_art);
		this.oSelVector.add(sel_sorte_ek);
		this.oSelVector.add(sel_sorte_vk);
		this.oSelVector.add(sel_kontrakt_ek);
		this.oSelVector.add(sel_kontrakt_vk);
		this.oSelVector.add(sel_start_land);
		this.oSelVector.add(sel_ziel_land);
		this.oSelVector.add(sel_haendler);
		this.oSelVector.add(sel_loeschen);
		this.oSelVector.add(sel_uma);
		this.oSelVector.add(selPlanDatumVonBis);
		this.oSelVector.add(selUrsprung);
		
		
		this.oSelVector.get_oCheckPassiv().setFont(new E2_FontPlain(-2));
		
		int fontSize = -2;

		sel_lager_start.populate(new VEK<String>());
		sel_lager_ziel.populate(new VEK<String>());
		
		E2_Grid oGridInnen = new E2_Grid()._setSize(100,175,100,130,100,130,100,130,100,100)._bo_dd();
		
		RB_gld gld_lm_1010 = new RB_gld()._ins(1,0,1,0)._left_mid();
//		RB_gld gld_cm_1010 = new RB_gld()._ins(1,0,1,0)._center_mid();
		RB_gld gld_rm_1010 = new RB_gld()._ins(1,0,1,0)._right_mid();

		//Linie 1
		oGridInnen._clear()
		._a(new RB_lab()._t("Lieferant")._fsa(fontSize)			, gld_lm_1010)
		._a(start_besitzer_sel.get_oComponentForSelection()		, gld_lm_1010)
		._a(new RB_lab()._t("Wiege/Plan: von")._fsa(fontSize)	, gld_lm_1010)
		._a(selPlanDatumVonBis.get_oTFDatumVon()				, gld_lm_1010)
		._a(new RB_lab()._t("Bis")._fsa(fontSize)				, gld_rm_1010)
		._a(selPlanDatumVonBis.get_oTFDatumBis()				, gld_lm_1010)
		._a(new RB_lab()._t("Bew. Typ:")._fsa(fontSize)			, new RB_gld()._ins(6,0,1,0)._left_mid())
		._a(sel_bew_art.get_oComponentForSelection()			, new RB_gld()._ins(6,0,1,0)._left_mid())
		._a(new RB_lab()._t("Händler:")._fsa(fontSize)			, gld_lm_1010)
		._a(sel_haendler.get_oComponentForSelection()			, gld_lm_1010)
		;
		
		//Linie 2
		oGridInnen
		._a(new RB_lab()._t("Ladeort")._fsa(fontSize)			, gld_lm_1010)
		._a(sel_lager_start.get_oComponentForSelection()		, gld_lm_1010)
		._a(new RB_lab()._t("Lade-Sorte:")._fsa(fontSize)		, gld_lm_1010)
		._a(sel_sorte_ek.get_oComponentForSelection()			, gld_lm_1010)
		._a(new RB_lab()._t("Abl.:")._fsa(fontSize)				, gld_rm_1010)
		._a(sel_sorte_vk.get_oComponentForSelection()			, gld_lm_1010)
		._a(sel_loeschen.get_oComponentForSelection()			, new RB_gld()._span(2)._left_mid()._ins(1,0,1,0))
		._a(selUrsprung.get_oComponentForSelection()			, new RB_gld()._span(2)._left_mid()._ins(1,0,1,0))
		;
		
		//Linie 3
		oGridInnen
		._a(new RB_lab()._t("EK-Kontrakt")._fsa(fontSize)		, gld_lm_1010)
		._a(sel_kontrakt_ek.get_oComponentForSelection()		, gld_lm_1010)
		._a(new RB_lab()._t("Startland:")._fsa(fontSize)		, gld_lm_1010)
		._a(sel_start_land.get_oComponentForSelection()			, gld_lm_1010)
		._a(new RB_lab()._t("Zieland:")._fsa(fontSize)			, gld_rm_1010)
		._a(sel_ziel_land.get_oComponentForSelection()			, gld_lm_1010)
		._a(sel_uma.get_oComponentForSelection()				,  new RB_gld()._span(2)._left_mid()._ins(1,0,1,0))
		._a()
		._a()
		;
		
		//Linie 4
		oGridInnen
		._a(new RB_lab()._t("Abnehmer")._fsa(fontSize)		, gld_lm_1010)
		._a(ziel_besitzer_sel.get_oComponentForSelection()	, gld_lm_1010)
		._a()
		._a()
		._a()
		._a()
		._a()
		._a()
		._a()
		._a()
		;
		
		//Linie 5
		oGridInnen
		._a(new RB_lab()._t("Abladeort")._fsa(fontSize)	, gld_lm_1010)
		._a(sel_lager_ziel.get_oComponentForSelection()	, gld_lm_1010)
		._a()
		._a()
		._a()
		._a()
		._a()
		._a()
		._a()
		._a()
		;
		
		//Linie 6
		oGridInnen
		._a(new RB_lab()._t("VK-Kontrakt")._fsa(fontSize)	, gld_lm_1010)
		._a(sel_kontrakt_vk.get_oComponentForSelection()	, gld_lm_1010)
		._a()
		._a()
		._a()
		._a()
		._a()
		._a()
		._a()
		._a(this.oSelVector.get_AktivPassivComponent(), new RB_gld()._ins(1,0,1,0)._left_bottom())
		;
		
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
	}
	
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}


	
}


