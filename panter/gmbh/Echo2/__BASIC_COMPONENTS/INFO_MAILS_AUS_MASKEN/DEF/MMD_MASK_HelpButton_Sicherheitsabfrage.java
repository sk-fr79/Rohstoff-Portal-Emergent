package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import java.util.Vector;

import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.E2_HelpButton_ABSTRACT;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.exceptions.myException;

public class MMD_MASK_HelpButton_Sicherheitsabfrage extends E2_HelpButton_ABSTRACT {

	private Vector<E2_HelpButton_ABSTRACT.TextInsets> vInfos = new Vector<E2_HelpButton_ABSTRACT.TextInsets>();
	
	public MMD_MASK_HelpButton_Sicherheitsabfrage() throws myException 
	{
		super();
		
		this.set_cFensterTitel(new MyE2_String("Info:"));
	}

	@Override
	public E2_BasicModuleContainer get_Container() throws myException {
		return new ownContainer();
	}

	@Override
	public MyE2_Grid get_GridWithInfos() throws myException {
		
		MyE2_Grid oGridInfos = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		Font oFontBold = new E2_FontBold();
		Font oFontNormal = new E2_FontPlain();

		this.vInfos.removeAllElements();
		
		
		vInfos.add(new TextInsets("Definition der Sicherheitsabfrage:",E2_INSETS.I(3,1,1,20),oFontBold));
		vInfos.add(new TextInsets("Es sind 3 Textbereiche zu übergeben: ",E2_INSETS.I(3,1,1,10),oFontNormal));
		vInfos.add(new TextInsets("1. Die Formulierung der Sicherheitsabfrage",E2_INSETS.I(3,1,1,15),oFontNormal));
		vInfos.add(new TextInsets("2. Was steht auf dem JA-Button",E2_INSETS.I(3,1,1,15),oFontNormal));
		vInfos.add(new TextInsets("3. Was steht auf dem NEIN-Button",E2_INSETS.I(3,1,1,15),oFontNormal));
		vInfos.add(new TextInsets("Die 3 Felder werden durch ein # - Zeichen getrennt.",E2_INSETS.I(3,10,1,15),oFontNormal));
		vInfos.add(new TextInsets("Beispiel: Sind Sie sicher ?#Ja#Nein",E2_INSETS.I(3,10,1,15),oFontNormal));
		vInfos.add(new TextInsets("Ist das Feld leer, dann entfällt die Sicherheitsabfrage !",E2_INSETS.I(3,20,1,1),oFontBold));

		this.fill_GridWithText_Without_Translation(oGridInfos, this.vInfos);
		
		return oGridInfos;
	}


	private class ownContainer extends E2_BasicModuleContainer {

		public ownContainer() throws myException {
			super();
			this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
		}
		
	}
		
		
}
