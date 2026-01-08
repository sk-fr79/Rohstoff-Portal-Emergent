package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import java.util.Vector;

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
*/
public class PROJEKT_MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public PROJEKT_MASK(PROJEKT_MASK_ComponentMAP oMap) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		oTabbedPaneMaske.set_bAutoHeight(true);

		// 5 Grids
		MyE2_Grid oGrid0 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid1 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid2 = new MyE2_Grid(2,0);
		MyE2_Grid oGrid3 = new MyE2_Grid(2,0);
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Projektangaben"), oGrid0);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Projektfortgang"), oGrid1);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Projektmitarbeiter"), oGrid2);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Beteiligte Firmen"), oGrid3);
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);
		
		//hier kommen die Felder	
		oFiller.add_Line(oGrid0, new MyE2_String("ID"), 1, 				"ID_PROJEKT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Projektname"), 1, 	"PROJEKTNAME|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Beschreibung"), 1, 	"PROJEKTBESCHREIBUNG|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Projektleiter:"), 1, 	"ID_USER|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Beginn"), 1, 			"PROJEKTBEGIN|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Deadline"), 1, 		"PROJEKTDEADLINE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Wiedervorlage"), 1, 	"WIEDERVORLAGE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Projektgewicht"), 1, 	"ID_PROJEKTGEWICHT|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Status"), 1, 			"ID_PROJEKTSTATUSVARIANTE|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Aktiv"), 1, 			"AKTIV|#  |",3);
		oFiller.add_Line(oGrid0, new MyE2_String("Beendet"), 1, 		"BEENDET|#  |",3);

		oGrid1.add(oMap.get_Comp(PROJEKT_LIST_BasicModuleContainer.NAME_OF_FORTGANG_DAUGHTER),  E2_INSETS.I_2_2_2_2);
		oGrid2.add(oMap.get_Comp(PROJEKT_LIST_BasicModuleContainer.NAME_OF_MITARBEITER_DAUGHTER),  E2_INSETS.I_2_2_2_2);
		oGrid3.add(oMap.get_Comp(PROJEKT_LIST_BasicModuleContainer.NAME_OF_FIRMEN_DAUGHTER),  E2_INSETS.I_2_2_2_2);
		
		this.vMaskGrids.add(oGrid0);
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
		this.vMaskGrids.add(oGrid3);
		
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}


	

	
}
