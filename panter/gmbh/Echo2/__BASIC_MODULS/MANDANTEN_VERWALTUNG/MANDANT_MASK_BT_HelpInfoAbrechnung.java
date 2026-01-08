package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.E2_HelpButton_ABSTRACT;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class MANDANT_MASK_BT_HelpInfoAbrechnung extends E2_HelpButton_ABSTRACT {

	private Vector<E2_HelpButton_ABSTRACT.TextInsets> vInfos = new Vector<E2_HelpButton_ABSTRACT.TextInsets>();
	
	public MANDANT_MASK_BT_HelpInfoAbrechnung() {
		super();
		
		Font  oFontTitel = new E2_FontBold();
		Font  oFontText = new E2_FontPlain(-2);
		Font  oFontKursive = new E2_FontBoldItalic(-2);
		
		
		this.vInfos.add(new TextInsets("Standardmethode zur Erzeugung des Belegdatums: ",E2_INSETS.I(2,10,2,10),oFontTitel));
		this.vInfos.add(new TextInsets("Es wird das aktuelle Datum vorgeschlagen und der Anwender kann dies korrigeren",E2_INSETS.I(2,2,2,2),oFontText));
		this.vInfos.add(new TextInsets("Das Zahlungsziel wird aus der Definition der Zahlungsbedingungen berechnet,",E2_INSETS.I(2,2,2,2),oFontText));
		this.vInfos.add(new TextInsets("wobei diese entweder auf dem neuesten Leistungs- oder dem gerade festgelegten Belegdatum basiert",E2_INSETS.I(2,2,2,2),oFontText));
		this.vInfos.add(new TextInsets("Alternative Methode zur Erzeugung des Belegdatums:",E2_INSETS.I(2,10,2,10),oFontTitel));
		this.vInfos.add(new TextInsets("Das Belegdatum ist das neueste vorkommende Leistungsdatum und wird automatisch erzeugt.",E2_INSETS.I(2,2,2,2),oFontText));
		this.vInfos.add(new TextInsets("Darauf zugrundegelegt wird die Berechung des Zahlungszieles nach den Zahlungsbedingungen gerechnet.",E2_INSETS.I(2,2,2,2),oFontText));
		this.vInfos.add(new TextInsets("Die minimale Zahlungsfrist ab dem Datum der Ausführung ist die Angabe in dem Feld:",E2_INSETS.I(2,2,2,2),oFontText));
		this.vInfos.add(new TextInsets("<Mindestens eingeräumte Zahlungsfrist>",E2_INSETS.I(2,2,2,2),oFontKursive));
		
		this.set_oPopupWidth(new Extent(600));
		this.set_oPopupHeight(new Extent(350));
	}

	@Override
	public E2_BasicModuleContainer get_Container() throws myException {
		return new ownBasicContainer();
	}

	@Override
	public MyE2_Grid get_GridWithInfos() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.fill_GridWithText_Without_Translation(oGridRueck, this.vInfos);
		return oGridRueck;
	}
	
	private class ownBasicContainer extends E2_BasicModuleContainer {
		
	}
	
	

	
}
