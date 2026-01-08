package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.exceptions.myException;

/*
* maskenvariante mit TabbedPane 
* .... MUSS Umbenannt werden in QUERY_MASK
*/
public class QUERY_MASK extends MyE2_Column   implements IF_BaseComponent4Mask	
{
	
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public QUERY_MASK(QUERY_MASK_ComponentMAP oMap) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null, new Insets(5,4,5,0), new Insets(5,2,5,2),null);
		E2_MaskFiller  oFiller1 = new E2_MaskFiller(oMap,null,null, new Insets(5,1,5,0), new Insets(5,1,5,0),null);

		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		
		// 5 Grids
		MyE2_Grid oGrid0 = new MyE2_Grid(4,0);
		MyE2_Grid oGrid1 = new MyE2_Grid(4,0);
		MyE2_Grid oGrid2 = new MyE2_Grid(4,0);
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Grunddefinition"), oGrid0);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Parameter"), oGrid1);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Benutzererlaubnis"), oGrid2);
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_0_0_0_0);
		
		//hier kommen die Felder	
		oFiller1.add_Line(oGrid0, new MyE2_String("ID"), 							1, "ID_QUERY|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Name der Abfrage"), 				1, "NAME|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Beschreibung (1)"), 				1, "BESCHREIB1|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Beschreibung (2)"), 				1, "BESCHREIB2|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Beschreibung (3)"), 				1, "BESCHREIB3|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Beschreibung (4)"), 				1, "BESCHREIB4|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Selectblock (ohne SELECT) (1)"), 1, "SQLFELDLISTE|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("From-Block (ohne FROM)"), 		1, "SQLFROMBLOCK|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Where-Block (ohne WHERE) (2)"), 	1, "SQLWHEREBLOCK|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Order-Block (ohne ORDER BY)"), 	1, "SQLORDERBLOCK|#  |",3);
		oFiller1.add_Line(oGrid0, new MyE2_String("Feldnamen in Liste (getrennt mit |"), 1, "LISTE_TITEL|#  |",3);
		
		oFiller.add_Line(oGrid1, new MyE2_String("Parameterliste (jeweils in SELECT - und WHERE - Block benutzbar)"), 4);
		oFiller.add_Line(oGrid1, new MyE2_String(" "), 4);
		oFiller.add_Line(oGrid1, new MyE2_String(" "), 1,new MyE2_String("Text für Benutzer"), 1,new MyE2_String("Definition für DropDown"), 1,new MyE2_String("Vorgabewert"), 1);
		
		oFiller.add_Line(oGrid1, new MyE2_String("(#1#)"), 1, "PARAM01",1, "PARAMDROPDOWNDEF01",1,"DEFAULT01",1);
		oFiller.add_Line(oGrid1, new MyE2_String("(#2#)"), 1, "PARAM02",1, "PARAMDROPDOWNDEF02",1,"DEFAULT02",1);
		oFiller.add_Line(oGrid1, new MyE2_String("(#3#)"), 1, "PARAM03",1, "PARAMDROPDOWNDEF03",1,"DEFAULT03",1);
		oFiller.add_Line(oGrid1, new MyE2_String("(#4#)"), 1, "PARAM04",1, "PARAMDROPDOWNDEF04",1,"DEFAULT04",1);
		oFiller.add_Line(oGrid1, new MyE2_String("(#5#)"), 1, "PARAM05",1, "PARAMDROPDOWNDEF05",1,"DEFAULT05",1);
		oFiller.add_Line(oGrid1, new MyE2_String("(#6#)"), 1, "PARAM06",1, "PARAMDROPDOWNDEF06",1,"DEFAULT06",1);
		oFiller.add_Line(oGrid1, new MyE2_String(" "), 		  1, "#Download erlaubt|#  |DOWNLOAD|#  |",3);

		oGrid2.add(oMap.get_Comp(QUERY_LIST_BasicModuleContainer.NAME_OF_MASK_TEILNEHMER), E2_INSETS.I_0_5_5_5);
		
		this.vMaskGrids.add(oGrid0);
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);

	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}

	

	
}
