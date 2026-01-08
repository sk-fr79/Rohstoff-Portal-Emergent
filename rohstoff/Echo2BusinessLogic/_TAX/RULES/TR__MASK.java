package rohstoff.Echo2BusinessLogic._TAX.RULES;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.Factorys.StyleFactory_Grid;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class TR__MASK extends RB_MaskGrid 
{
	private MyE2_Grid oGridMaskContent = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	//zusatzgrid fuer eingeblendete Infos bein editieren
	private MyE2_Grid oGridAddonContent = new MyE2_Grid(1,  MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	
	public TR__MASK(TR__MASK_ComponentMAP oMap) throws myException
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		//this.setBorder(new Border(2, Color.RED, Border.STYLE_SOLID));
		
		this.add(this.oGridMaskContent,MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,0,0)));
		
		//einstellung, ob mit oder ohne unterscheuidung der Mandantenvermerke gearbeitet wird
		boolean bBeruecksichtigeMandantenVermerke = bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_NO();

		
		//teil 1: Bedingungen, die aus der fuhre heraus geprueft werden
		int[] iBreite1 = {1120};
		RB_MaskGrid oGrid1 = new RB_MaskGrid(iBreite1, StyleFactory_Grid.STYLE_GRID_BLACK_BORDER_NO_INSETS());
		RB_MaskGrid oGrid2 = new RB_MaskGrid(iBreite1, StyleFactory_Grid.STYLE_GRID_BLACK_BORDER_NO_INSETS());
		RB_MaskGrid oGrid3 = new RB_MaskGrid(iBreite1, StyleFactory_Grid.STYLE_GRID_BLACK_BORDER_NO_INSETS());

		
		int[] iBreite1a = {400,360,360};
		RB_MaskGrid oGrid1a = new RB_MaskGrid(iBreite1a, StyleFactory_Grid.STYLE_GRID_DD_BORDER_NO_INSETS_W100());
		
		int[] iBreite2b = {400,360,360};
		RB_MaskGrid oGrid2b = new RB_MaskGrid(iBreite2b, StyleFactory_Grid.STYLE_GRID_DD_BORDER_NO_INSETS_W100());

		int[] iBreite3b = {400,360,360};
		RB_MaskGrid oGrid3b = new RB_MaskGrid(iBreite3b, StyleFactory_Grid.STYLE_GRID_DD_BORDER_NO_INSETS_W100());

		oGrid1.add(oGrid1a, E2_INSETS.I_0_0_0_0);
		oGrid2.add(oGrid2b, E2_INSETS.I_0_0_0_0);
		oGrid3.add(oGrid3b, E2_INSETS.I_0_0_0_0);
		
		
		//anordnen im oberen grid
		oGridMaskContent.add(new E2_ComponentGroupHorizontal(0, new  MyE2_Label("ID:"),oMap.get_Comp(_DB.HANDELSDEF$ID_HANDELSDEF) , E2_INSETS.I_0_0_10_0),4,E2_INSETS.I_5_5_5_5);
		
		
		oGridMaskContent.add(oGrid1,4,E2_INSETS.I_0_0_0_20);
		oGridMaskContent.add(oGrid2,4,E2_INSETS.I_0_0_0_20);
		oGridMaskContent.add(oGrid3,4,E2_INSETS.I_0_0_0_0);
		
		GridLayoutData  glLeft_I0 = MyE2_Grid.LAYOUT_LEFT_TOP(new Insets(30, 5, 0, 5), new E2_ColorBase(), 1);
		GridLayoutData  glLeft_I1 = MyE2_Grid.LAYOUT_LEFT_TOP(new Insets(5, 5, 0, 5), new E2_ColorDDark(), 1);
		GridLayoutData  glLeft_I3 = MyE2_Grid.LAYOUT_LEFT_TOP(new Insets(5, 5, 0, 5), new E2_ColorDDark(), 3);
		GridLayoutData  glCenter1 = MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I_2_2_2_2, null, 1);
		
		GridLayoutData  glLeft1 = 			MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(10,2,2,2), null, 1);
		GridLayoutData  glLeft2 = 			MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(10,2,2,2), null, 2);
		GridLayoutData  glLeft_Schalter = 	MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(100,2,2,2), null, 1);
		
		GridLayoutData  glCenter_I1 = MyE2_Grid.LAYOUT_CENTER_TOP(new Insets(5, 5, 0, 5), new E2_ColorDDark(), 1);
		
		oGrid1a.add(new MyE2_Label(new MyE2_String("Vergleichskriterien mit der Fuhre / mit dem Bewegungssatz..."),new E2_FontBold()),glLeft_I1);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$VERSIONSZAEHLER),glCenter_I1);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$AKTIV),glCenter_I1);
		
		
		oGrid1a.add(new MyE2_Label(new MyE2_String("")),1, E2_INSETS.I_2_2_2_2);
		
//		oGrid1a.add(new MyE2_Label(new MyE2_String("Gutschrift-/Ladeseite"),new E2_FontBold()),glCenter1);
//		oGrid1a.add(new MyE2_Label(new MyE2_String("Rechnung-/Abladeseite"),new E2_FontBold()),glCenter1);
		oGrid1a.add((MyE2_Label)oMap.get_Comp(TR__CONST.KEY_MASK_TITEL_EK),glLeft_I0);
		oGrid1a.add((MyE2_Label)oMap.get_Comp(TR__CONST.KEY_MASK_TITEL_VK),glLeft_I0);

		
		if (bBeruecksichtigeMandantenVermerke) {
			oGrid1a.add(new MyE2_Label(new MyE2_String("Eigene Stationen: "+bibALL.get_RECORD_MANDANT().get___KETTE(bibVECTOR.get_Vector("NAME1", "NAME2"), "<Mandant>", "", "", " "),false)),	1, E2_INSETS.I_5_2_2_2);
			oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$QUELLE_IST_MANDANT),					glLeft_Schalter);
			oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$ZIEL_IST_MANDANT),						glLeft_Schalter);
		}
		
		
		oGrid1a.add(new MyE2_Label(new MyE2_String("Land Handelspartner, juristisch")),	1, E2_INSETS.I_5_2_2_2);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$ID_LAND_QUELLE_JUR),					glLeft1);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR),						glLeft1);

		oGrid1a.add(new MyE2_Label(new MyE2_String("Land Stationen, geografisch")),		1, E2_INSETS.I_5_2_2_2);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$ID_LAND_QUELLE_GEO),					glLeft1);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$ID_LAND_ZIEL_GEO),						glLeft1);


		
		
		//2014-02-24: andere anordnung, einfuegen von end of waste
		E2_Subgrid_4_Mask  oGridSortenLadeseite =  (E2_Subgrid_4_Mask)oMap.get__Comp(TR__CONST.KEY_MASK_SORTENTYPBLOCK_EK);
		E2_Subgrid_4_Mask  oGridSortenAbladeseite =  (E2_Subgrid_4_Mask)oMap.get__Comp(TR__CONST.KEY_MASK_SORTENTYPBLOCK_VK);
		oGridSortenLadeseite.FILL_WITH(oMap, 
										"Abfall|Produkt|End of Waste|Dienstl.", 
										TR__CONST.KEY_MASK_SCHROTTSELEKTOR_EK+"|"+
										_DB.HANDELSDEF$SORTE_PRODUKT_QUELLE+"|"+
										_DB.HANDELSDEF$SORTE_EOW_QUELLE+"|"+
										_DB.HANDELSDEF$SORTE_DIENSTLEIST_QUELLE,
										LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I(0,1,17,1)), 
										LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I(0,1,17,1)));
		
		oGridSortenAbladeseite.FILL_WITH(oMap, 
										"Abfall|Produkt|End of Waste|Dienstl.", 
										TR__CONST.KEY_MASK_SCHROTTSELEKTOR_VK+"|"+
										_DB.HANDELSDEF$SORTE_PRODUKT_ZIEL+"|"+
										_DB.HANDELSDEF$SORTE_EOW_ZIEL+"|"+
										_DB.HANDELSDEF$SORTE_DIENSTLEIST_ZIEL,
										LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I(0,1,17,1)), 
										LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I(0,1,17,1)));

		
		oGrid1a.add(new MyE2_Label(new MyE2_String("Sortendefinition")),		1, E2_INSETS.I_5_2_2_2);
		oGrid1a.add(oGridSortenLadeseite,				glLeft1);
		oGrid1a.add(oGridSortenAbladeseite,				glLeft1);
		
		oGrid1a.add(new MyE2_Label(new MyE2_String("Reverse Charge (aus Länderliste aktiv)")),		1, E2_INSETS.I_5_2_2_2);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$SORTE_RC_QUELLE),						glLeft1);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$SORTE_RC_ZIEL),						glLeft1);
		
		
		
		
		oGrid1a.add(new MyE2_Label(new MyE2_String("Steuerlicher Unternehmer (wenn Nein=Privatkunde)")),	1, E2_INSETS.I_5_2_2_2);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$UST_TEILNEHMER_QUELLE),				glLeft1);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$UST_TEILNEHMER_ZIEL),					glLeft1);
		
		oGrid1a.add(new MyE2_Label(new MyE2_String("Transportverantwortung hat: ")),	1, E2_INSETS.I_5_2_2_2);
		oGrid1a.add(oMap.get_Comp(_DB.HANDELSDEF$TP_VERANTWORTUNG),						glLeft2);


		oGrid1a.set_all_rows_Height(25);
		
		
		oGrid2b.add(new MyE2_Label(new MyE2_String("Fuhrenfelder werden wie folgt gesetzt:"),new E2_FontBold()),glLeft_I3);
		
		oGrid2b.add(new MyE2_Label(new MyE2_String("Steuerdefinition: ")),	1, E2_INSETS.I_5_2_2_2);
		oGrid2b.add(oMap.get_Comp(_DB.HANDELSDEF$ID_TAX_QUELLE),			glLeft1);
		oGrid2b.add(oMap.get_Comp(_DB.HANDELSDEF$ID_TAX_ZIEL),				glLeft1);
		
		oGrid2b.add(new MyE2_Label(new MyE2_String("Steuerdef.(negativer Preis): ")),	1, E2_INSETS.I_5_2_2_2);
		oGrid2b.add(oMap.get_Comp(_DB.HANDELSDEF$ID_TAX_NEGATIV_QUELLE),	glLeft1);
		oGrid2b.add(oMap.get_Comp(_DB.HANDELSDEF$ID_TAX_NEGATIV_ZIEL),		glLeft1);
		
		oGrid2b.add(new MyE2_Label(new MyE2_String("Intrastat melden: ")),	1, E2_INSETS.I_5_2_2_2);
		oGrid2b.add(oMap.get_Comp(_DB.HANDELSDEF$INTRASTAT_MELD_IN),		glLeft_Schalter);
		oGrid2b.add(oMap.get_Comp(_DB.HANDELSDEF$INTRASTAT_MELD_OUT),		glLeft_Schalter);

		oGrid2b.add(new MyE2_Label(new MyE2_String("Transitgeschäft melden: ")),	1, E2_INSETS.I_5_2_2_2);
		oGrid2b.add(oMap.get_Comp(_DB.HANDELSDEF$TRANSIT_EK),				glLeft_Schalter);
		oGrid2b.add(oMap.get_Comp(_DB.HANDELSDEF$TRANSIT_VK),		 		glLeft_Schalter);

		oGrid2b.set_all_rows_Height(25);

		
		oGrid3b.add(new MyE2_Label(new MyE2_String("Information nach der Anwendung der Regel:"),new E2_FontBold()),glLeft_I3);
		
		oGrid3b.add(new MyE2_Label(new MyE2_String("Art der Information: ")),	1, E2_INSETS.I_5_2_2_2);
		oGrid3b.add(oMap.get_Comp(_DB.HANDELSDEF$TYP_MELDUNG),				glLeft2);
		
		oGrid3b.add(new MyE2_Label(new MyE2_String("")),		1, E2_INSETS.I_5_2_2_2);
		oGrid3b.add(oMap.get_Comp(_DB.HANDELSDEF$MELDUNG_FUER_USER),			glLeft2);
		
	}


	public MyE2_Grid get_oGridWithMaskContent() {
		return oGridMaskContent;
	}

	
	public void activate_AddonContent() {
		if (this.getSize()==1) {
			this.setSize(2);
			this.add(this.oGridAddonContent,MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(0,0,0,0)));
		}
	}
	
	
	
	public MyE2_Grid get_oGridAddOnContent() {
		return this.oGridAddonContent;
	}

	
	
	
}
