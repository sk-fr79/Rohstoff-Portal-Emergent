package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSKLASSE_DEF;
import panter.gmbh.indep.exceptions.myException;

public class ADK_M_ComponentMapCollector extends RB_ComponentMapCollector {

	
	public ADK_M_ComponentMapCollector() throws myException {
		super();
		
		this.registerComponent(new ADK_M_Key(), new ADK_M_ComponentMAP());
	}
	
	
	public ownViewContainer get_ContainerWithElements() throws myException {
		return new ownViewContainer();
	}
	
	private class ownViewContainer extends MyE2_Grid implements IF_BaseComponent4Mask {
		ADK_M_ComponentMAP mask = ((ADK_M_ComponentMAP)ADK_M_ComponentMapCollector.this.get(new ADK_M_Key()));

		public ownViewContainer() throws myException {
			super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			this.add(new MyE2_Label(new MyE2_String("Adresseklasse erfassen/bearbeiten ..."),new E2_FontBold(3)),2, E2_INSETS.I(2,2,2,2));
			
			this.add(new MyE2_Label(new MyE2_String("ID")),1, E2_INSETS.I(2,2,2,2));
			this.add((Component)mask.getRbComponent(new RB_KF(ADRESSKLASSE_DEF.id_adressklasse_def)),1, E2_INSETS.I(2,2,2,2));
			
			this.add(new MyE2_Label(new MyE2_String("Kurzbezeichnung")),1, E2_INSETS.I(2,2,2,2));
			this.add((Component)mask.getRbComponent(new RB_KF(ADRESSKLASSE_DEF.kurzbezeichnung)),1, E2_INSETS.I(2,2,2,2));

			this.add(new MyE2_Label(new MyE2_String("Bezeichung")),1, E2_INSETS.I(2,2,2,2));
			this.add((Component)mask.getRbComponent(new RB_KF(ADRESSKLASSE_DEF.bezeichnung)),1, E2_INSETS.I(2,2,2,2));

			this.add(new MyE2_Label(new MyE2_String("Beschreibung/Lang")),1, E2_INSETS.I(2,2,2,2));
			this.add((Component)mask.getRbComponent(new RB_KF(ADRESSKLASSE_DEF.beschreibung)),1, E2_INSETS.I(2,2,2,2));

			int[] breite = {100,100,200};
			
			E2_Grid4Mask helpGrid = new E2_Grid4Mask().set_Cols(breite)
					.set_LayoutData(MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(1,1,1,1), new E2_ColorDDark(), 1, 1), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(1,1,1,1), new E2_ColorBase(), 1, 1))
					.add(new MyE2_Label(new MyE2_String("Kunde")), 		(Component)mask.getRbComponent(new RB_KF(ADRESSKLASSE_DEF.ist_kunde)))
					.add(new MyE2_Label(new MyE2_String("Lieferant")), 	(Component)mask.getRbComponent(new RB_KF(ADRESSKLASSE_DEF.ist_lieferant)))
					.add(new MyE2_Label(new MyE2_String("Wird as Standard benutzt")), 	(Component)mask.getRbComponent(new RB_KF(ADRESSKLASSE_DEF.ist_standard)));
			
			this.add(new MyE2_Label(new MyE2_String("Definition")),1, E2_INSETS.I(2,2,2,2));
			this.add(helpGrid,1, E2_INSETS.I(2,2,2,2));

			this.add(new MyE2_Label(new MyE2_String("Farbe")),1, E2_INSETS.I(2,2,2,2));
			this.add((Component)mask.getRbComponent(new RB_KF(ADK_CONST.COMP_NAMES.COLORSELEKTOR.name())),1, E2_INSETS.I(2,2,2,2));
			
		}
		
		
	}
	
}
