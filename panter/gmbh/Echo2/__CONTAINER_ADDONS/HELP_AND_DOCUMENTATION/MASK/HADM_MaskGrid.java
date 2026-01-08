package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.MASK;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class HADM_MaskGrid extends RB_MaskGrid {

	public HADM_MaskGrid(HADM_ComponentMapCollector  mapColl) throws myException {
		super(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		HADM_ComponentMap  map1 = (HADM_ComponentMap) mapColl.get(new RB_KM(_TAB.hilfetext));
		
		E2_Grid4MaskSimple grid1 = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_DDARK_BORDER())
						.def_(80)
						.def_(E2_INSETS.I(2,2,4,2))
						.def_(80)
						.add_(new MyE2_Label(new MyE2_String("ID"),new E2_FontItalic(-2)))
						.def_(80)
						.def_(new Alignment(Alignment.RIGHT, Alignment.TOP))
						.add_(new MyE2_Label(new MyE2_String("Ticket"),new E2_FontItalic(-2)))
						.add_(new MyE2_Label(new MyE2_String("Freigabe"),new E2_FontItalic(-2)))
						.def_(new Alignment(Alignment.LEFT, Alignment.TOP))
						.def_(E2_INSETS.I(0,0,1,0))
						.add_(map1.getComponentEchoBase(new RB_KF(HILFETEXT.id_hilfetext)))
						.add_(map1.getComponentEchoBase(new RB_KF(HILFETEXT.ticketnummer)))
						.add_(map1.getComponentEchoBase(new RB_KF(HILFETEXT.abschlussdatum)))
						.setSize_(3);
						
		this.add(new MyE2_Label(new MyE2_String("Angaben")),1, E2_INSETS.I(2,2,2,2));
		this.add(grid1,2, E2_INSETS.I(2,2,2,4));
		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
						
		
		

		
//		this.add(new MyE2_Label(new MyE2_String("ID")),1, E2_INSETS.I(2,2,2,2));
//		this.add(map1.rb_Component(new RB_KF(HILFETEXT.id_hilfetext)),2, E2_INSETS.I(2,2,2,2));
//		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
//
		this.add(new MyE2_Label(new MyE2_String("Version")),1, E2_INSETS.I(2,2,2,2));
		this.add(map1.getRbComponent(new RB_KF(HILFETEXT.id_version)),2, E2_INSETS.I(2,2,2,2));
		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
		
		
		this.add(new MyE2_Label(new MyE2_String("Modul")),1, E2_INSETS.I(2,2,2,2));
		this.add(map1.getRbComponent(new RB_KF(HILFETEXT.modulkenner)),2, E2_INSETS.I(2,2,2,2));
		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));

//		this.add(new MyE2_Label(new MyE2_String("Verantwortlich/Ausführung")),1, E2_INSETS.I(2,2,2,2));
//		this.add(map1.rb_Component(new RB_KF(HILFETEXT.id_user_bearbeiter)),1, E2_INSETS.I(2,2,2,2));
//		this.add(map1.rb_Component(new RB_KF(HILFETEXT.id_user_ursprung)),1, E2_INSETS.I(2,2,2,2));
//		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
		
		E2_Grid4MaskSimple grid = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		grid.def_(E2_INSETS.I(2,2,4,2))
			.add_(new MyE2_Label(new MyE2_String("Typ"),new E2_FontItalic(-2)))
			.add_(new MyE2_Label(new MyE2_String("Status"),new E2_FontItalic(-2)))
			.add_(new MyE2_Label(new MyE2_String("Gemeldet von"),new E2_FontItalic(-2)))
			.add_(new MyE2_Label(new MyE2_String("Bearbeitet von"),new E2_FontItalic(-2)))
			.def_(E2_INSETS.I(0,0,4,0))
			.add_(map1.getComponentEchoBase(new RB_KF(HILFETEXT.typ)))
			.add_(map1.getComponentEchoBase(new RB_KF(HILFETEXT.status)))
			.add_(map1.getComponentEchoBase(new RB_KF(HILFETEXT.id_user_ursprung)))
			.add_(map1.getComponentEchoBase(new RB_KF(HILFETEXT.id_user_bearbeiter)))
			.setSize(4);
			;
			
			
		this.add(new MyE2_Label(new MyE2_String("")),1, E2_INSETS.I(2,2,2,2));
		this.add(grid,2, E2_INSETS.I(2,2,2,4));
		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
		
//		this.add(new MyE2_Label(new MyE2_String("Gemeldet von")),1, E2_INSETS.I(2,2,2,2));
//		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));

//		this.add(new MyE2_Label(new MyE2_String("Typ")),1, E2_INSETS.I(2,2,2,2));
//		this.add(map1.rb_Component(new RB_KF(HILFETEXT.typ)),2, E2_INSETS.I(2,2,2,2));
//		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
//
//		this.add(new MyE2_Label(new MyE2_String("Status")),1, E2_INSETS.I(2,2,2,2));
//		this.add(map1.rb_Component(new RB_KF(HILFETEXT.status)),2, E2_INSETS.I(2,2,2,2));
//		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
		
		this.add(new MyE2_Label(new MyE2_String("Titel")),1, E2_INSETS.I(2,2,2,2));
		this.add(map1.getRbComponent(new RB_KF(HILFETEXT.titel)),2, E2_INSETS.I(2,2,2,2));
		this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
		
		this.add(new MyE2_Label(new MyE2_String("Text")),1, E2_INSETS.I(2,2,2,2));
		this.add(map1.getRbComponent(new RB_KF(HILFETEXT.hilfetext)),2, E2_INSETS.I(2,2,2,2));
		if (bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
			this.add(map1.getRbComponent(new RB_KF(HILFETEXT.info_developer)),2, E2_INSETS.I(2,2,2,2));
		} else {
			this.add(new MyE2_Label(""),2, E2_INSETS.I(2,2,2,2));
		}
		
		
	}
  
	
}
