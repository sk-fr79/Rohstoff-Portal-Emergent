package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR;


import nextapp.echo2.app.Border;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class FZ_LIST__Selector extends E2_ExpandableRow {
	
	private E2_SelectionComponentsVector 		oSelVector = null;
	
	private FZ_LIST_SEL_StatusVektor  			sel_cb_bewegung_status = 	null;
	private FZ_LIST_SEL_VektorTyp  				selVektorTyp = 				null;
	private FZ_LIST_SEL_DelUndel  				sel_cb_del_status = 		null;
	private FZ_LIST_SEL_AnzeigeAltNeu  			sel_alt_neu = 				null;
	private FZ_LIST_SEL_Besitzer 				selStartBesitzer = 			null;
	private FZ_LIST_SEL_Besitzer 				selZielBesitzer = 			null;
	private FZ_LIST_SEL_Sorte 					selSorte = 					null;
	private FZ_LIST_SEL_RechGutPos  			selAbrechnungsTyp = 		null;
	
	private FZ_LIST_SEL_Filter   				selFilter = 				null;

	public FZ_LIST__Selector(E2_NavigationList oNavigationList) throws myException 	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		this.oSelVector.add(this.sel_cb_bewegung_status=		new FZ_LIST_SEL_StatusVektor());

		this.oSelVector.add(this.selVektorTyp=					new FZ_LIST_SEL_VektorTyp());
		this.oSelVector.add(this.selSorte =						new FZ_LIST_SEL_Sorte());
		this.oSelVector.add(this.selStartBesitzer =				new FZ_LIST_SEL_Besitzer(true));
		this.oSelVector.add(this.selZielBesitzer =				new FZ_LIST_SEL_Besitzer(false));

		this.oSelVector.add(this.sel_cb_del_status = 			new FZ_LIST_SEL_DelUndel());
		this.oSelVector.add(this.sel_alt_neu = 					new FZ_LIST_SEL_AnzeigeAltNeu());
		this.oSelVector.add(this.selAbrechnungsTyp =			new FZ_LIST_SEL_RechGutPos());
		this.oSelVector.add(this.selFilter =					new FZ_LIST_SEL_Filter());
		
		RB_gld ld_s01 = new RB_gld()._ins(0, 3, 3, 2);
		RB_gld ld_s02 = new RB_gld()._ins(0, 0, 0, 2);

		RB_gld ld_s11 = new RB_gld()._ins(0, 5, 0, 2);
		RB_gld ld_s12 = new RB_gld()._ins(0, 1, 0, 8);
		
		RB_gld ld_s21 = new RB_gld()._ins(0, 5, 0, 2);
		RB_gld ld_s22 = new RB_gld()._ins(0, 5, 0, 2);
		
		
		E2_Grid grid = new E2_Grid()._setSize(350,280,270,270);
		
		E2_Grid  g_help1 = new E2_Grid()._setSize(150,210)
										._a(new RB_lab()._tr("Bewegung-Typ")._i(),		ld_s01)._a(this.selVektorTyp.get_oComponentForSelection(), 		ld_s02)
										._a(new RB_lab()._tr("Sorte")._i(),				ld_s01)._a(this.selSorte.get_oComponentForSelection(), 			ld_s02)
										._a(new RB_lab()._tr("Besitzer Start")._i(),	ld_s01)._a(this.selStartBesitzer.get_oComponentForSelection(), 	ld_s02)
										._a(new RB_lab()._tr("Besitzer Ende")._i(),		ld_s01)._a(this.selZielBesitzer.get_oComponentForSelection(), 	ld_s02);
		
		E2_Grid  g_help2 = new E2_Grid()._setSize(40,210)	._a(new RB_lab()._tr("Alt/Neu")._i(),	ld_s12)._a(this.sel_alt_neu.get_oComponentForSelection(), ld_s12)
															._a(new RB_lab()._tr("Zeige")._i(),		ld_s12)._a(this.sel_cb_del_status.get_oComponentForSelection(), ld_s12)
															._a(new RB_lab()._tr("Rech/Gut")._i(),	ld_s12)._a(this.selAbrechnungsTyp.get_oComponentForSelection(),ld_s12);
		
		E2_Grid  g_help3 = new E2_Grid()._setSize(40,200)	._a(new RB_lab()._tr("Status")._i(),	ld_s12)._a(this.sel_cb_bewegung_status.get_oComponentForSelection(), ld_s12);
		

		E2_Grid  g_help4 = new E2_Grid()._setSize(200)		._a(this.selFilter.get_oComponentForSelection(),ld_s12)
															._a(this.oSelVector.get_AktivPassivComponent(),	ld_s12);
		
		
		grid._a(g_help1, new RB_gld()._ins(0, 2, 20, 2))._a(g_help2, new RB_gld()._ins(0, 0, 20, 0))._a(g_help3, new RB_gld()._ins(0, 0, 20, 0))._a(g_help4, new RB_gld()._ins(0, 0, 20, 0));
		
//		grid.add(new MyE2_Grid(2,	this.selVektorTyp.get_oComponentForSelection(),
//									this.selSorte.get_oComponentForSelection())			,MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,20,2)));
//		grid.add(this.sel_alt_neu.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,20,2),null,1,2));
//		grid.add(this.sel_cb_bewegung_status.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,20,2),null,1,2));
//		grid.add(this.sel_cb_del_status.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,20,2),null,1,2));
//		grid.add(new MyE2_Grid(2,	this.selStartBesitzer.get_oComponentForSelection(),
//									this.selZielBesitzer.get_oComponentForSelection()),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,20,2)));
//		grid.add(this.selAbrechnungsTyp.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,20,2)));
//		grid.add(this.selFilter.get_oComponentForSelection(),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,20,2)));
//		grid.add(this.oSelVector.get_AktivPassivComponent(),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(2,2,20,2)));
		
		this.add(grid);
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	public FZ_LIST_SEL_VektorTyp get_selVektorTyp() {
		return selVektorTyp;
	}
	

	
}
