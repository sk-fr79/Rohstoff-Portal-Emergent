package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.DEF;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC__CONST;
import panter.gmbh.Echo2.components.E2_HelpButton_ABSTRACT;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.bibGroovy;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.exceptions.myException;

public class MMD_MASK_HelpButton extends E2_HelpButton_ABSTRACT {

	private Vector<E2_HelpButton_ABSTRACT.TextInsets> vInfos = new Vector<E2_HelpButton_ABSTRACT.TextInsets>();
	private boolean bGroovyButton = false;
	
	public MMD_MASK_HelpButton(boolean b_GroovyButton) throws myException 
	{
		super();
		
		this.bGroovyButton = b_GroovyButton;
		this.set_cFensterTitel(new MyE2_String("Richtlinien zur Definition der Mail-Inhalte:"));
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
		
		if (this.bGroovyButton) {
			vInfos.add(new TextInsets("Hier wird ein Groovy-Script angegeben, in das via Binding die folgenden Parameter übergeben bekommt:",E2_INSETS.I(3,1,1,2),oFontNormal));
			vInfos.add(new TextInsets("Das Ergebnis wird in das Binding geschrieben unter dem Namen RETURNVALUE",E2_INSETS.I(3,1,1,2),oFontNormal));
			vInfos.add(new TextInsets("Die Bedingung ist erfüllt, wenn im Bindung unter dem Hash-Wert "+MMC__CONST.KEY_RETURNVALUE+"  der Wert \"Y\" steht.",E2_INSETS.I(3,1,1,2),oFontNormal));
			vInfos.add(new TextInsets("Eine Fehlermeldung für den Benutzer kann mit der Variable "+MMC__CONST.KEY_ERRORCODE+"  übergeben werden.",E2_INSETS.I(3,1,1,2),oFontNormal));
			
			HashMap<String, MyE2_String> hmVars  = bibGroovy.get_HashMap_ParameterUebergaben();
			vInfos.add(new TextInsets("Spezielle Variable für Groovy-Scripte:",E2_INSETS.I(3,15,1,2),oFontBold));
			for (String cKEY: hmVars.keySet()) {
				vInfos.add(new TextInsets(cKEY+"... : ... "+hmVars.get(cKEY),E2_INSETS.I(30,1,1,2),oFontNormal));
			}
			
			vInfos.add(new TextInsets("Vor der Ausführung werden die folgenden Platzhalter im Groovy-Quelltext ersetzt:",E2_INSETS.I(3,20,1,2),oFontBold));
		}
		
		vInfos.add(new TextInsets("Definition der Platzhalter:",E2_INSETS.I(3,1,1,2),oFontBold));
		vInfos.add(new TextInsets("Alle Felder in der Maske sind unter dem jeweiligen Datenbanknamen erreichbar",E2_INSETS.I(3,1,1,2),oFontNormal));
		vInfos.add(new TextInsets("Soll der aktuelle Maskenwert gezogen werden, dann gilt der folgende Platzhalter:",E2_INSETS.I(3,1,1,15),oFontNormal));
		vInfos.add(new TextInsets("#FELD@ACT_F#  (formatiert)   ",E2_INSETS.I(30,1,1,15),oFontNormal));
		vInfos.add(new TextInsets("Soll (wenn es möglich ist, d.h. nur bei NICHT-Neueingaben) der momentane Datenbankwert geholt",E2_INSETS.I(3,1,1,2),oFontNormal));
		vInfos.add(new TextInsets("werden, dann gilt der Platzhalter:",E2_INSETS.I(3,1,1,15),oFontNormal));
		vInfos.add(new TextInsets("#FELD@DB_F#  (formatiert)  ----  #FELD@DB_UF#  (unformatiert)  ",E2_INSETS.I(30,1,1,15),oFontNormal));
		vInfos.add(new TextInsets("Fest eingebaute Parameter sind:",E2_INSETS.I(3,15,1,15),oFontBold));
		vInfos.add(new TextInsets("#SYS_USERNAME#   = 	 Benutzername des Ausführenden",E2_INSETS.I(30,1,1,1),oFontNormal));
		vInfos.add(new TextInsets("#SYS_KUERZEL#    = 	 Kürzel  des Ausführenden",E2_INSETS.I(30,1,1,1),oFontNormal));
		vInfos.add(new TextInsets("#SYS_USERID#     = 	 ID_USER des Ausführenden",E2_INSETS.I(30,1,1,1),oFontNormal));
		vInfos.add(new TextInsets("#SYS_MANDANT_ID# = 	 ID_MANDANT in dem sich alles abspielt",E2_INSETS.I(30,1,1,1),oFontNormal));
		vInfos.add(new TextInsets("#SYS_ID_ADRESSE_MANDANT# = ID_ADRESSE des Mandanten",E2_INSETS.I(30,1,1,1),oFontNormal));
		vInfos.add(new TextInsets("#SYS_REPORTDATE# =    Ausführungsdatum im Format 31.12.2010",E2_INSETS.I(30,1,1,20),oFontNormal));
		
		//hier werden noch weitere sys-variable aus der groovy-definitionstabelle erzeugt
		LinkedHashMap<String, String[]> lhmGroovy = bibReplacer.get_ListOfReplaceFieldsOnlyGroovy_WITHOUT_PARAMETERS(true);
		for (String cHash: lhmGroovy.keySet()) {
			vInfos.add(new TextInsets(cHash+" = "+lhmGroovy.get(cHash)[0]+"("+lhmGroovy.get(cHash)[1]+")",E2_INSETS.I(30,1,1,2),oFontNormal));
		}

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
