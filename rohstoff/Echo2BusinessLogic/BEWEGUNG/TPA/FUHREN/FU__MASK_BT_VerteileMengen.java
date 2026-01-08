package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_TAG;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS.SEARCH_TAGS;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

/**
 * 2012-03-26: verteilerbutton fuer die mengen in der fuhrenmaske
 * @author martin
 *
 */
public class FU__MASK_BT_VerteileMengen extends MyE2_Button
{
	private Vector<MyE2_DB_TextField>  	vFuellFelder = new Vector<MyE2_DB_TextField>();
	private Vector<MyE2_String>    		vBeschriftung = new Vector<MyE2_String>();

	public FU__MASK_BT_VerteileMengen(MyString cText, SEARCH_TAGS AusgangsmengenFeld) throws myException
	{
		super(cText, MyE2_Button.StyleTextButton_LOOK_like_LABEL(new E2_FontItalic(-2)));
		
		this.vFuellFelder.removeAllElements();
		this.vBeschriftung.removeAllElements();
		
		boolean bOK = true;
		bOK = bOK && pruefe_tag(AusgangsmengenFeld, SEARCH_TAGS.FUHRENMASKE_ANTEIL_PLANMENGE_LIEF, 		"Anteil Planmenge links");
		bOK = bOK && pruefe_tag(AusgangsmengenFeld, SEARCH_TAGS.FUHRENMASKE_ANTEIL_LADEMENGE_LIEF, 		"Anteil Lademenge links");
		bOK = bOK && pruefe_tag(AusgangsmengenFeld, SEARCH_TAGS.FUHRENMASKE_ANTEIL_ABLADEMENGE_LIEF, 	"Anteil Ablademenge links");
		bOK = bOK && pruefe_tag(AusgangsmengenFeld, SEARCH_TAGS.FUHRENMASKE_ANTEIL_PLANMENGE_ABN, 		"Anteil Planmenge rechts");
		bOK = bOK && pruefe_tag(AusgangsmengenFeld, SEARCH_TAGS.FUHRENMASKE_ANTEIL_LADEMENGE_ABN, 		"Anteil Lademenge rechts");
		bOK = bOK && pruefe_tag(AusgangsmengenFeld, SEARCH_TAGS.FUHRENMASKE_ANTEIL_ABLADEMENGE_ABN, 	"Anteil Ablademenge rechts");

		//nur anzeigen, wenn mindestens 1 komplementaerfeld enabled ist
		if (bOK && this.vFuellFelder.size()>0)
		{
			new ownPopup().CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(300), new MyE2_String("Mengen übernehmen"));
		}
	}
	
	
	private boolean pruefe_tag(SEARCH_TAGS oTagAusgangsfeld,SEARCH_TAGS oTagPruefFeld, String cBeschiftung) throws myException
	{
		boolean bRueck = true;
		
		if (oTagAusgangsfeld!=oTagPruefFeld)
		{
			E2_RecursiveSearch_TAG oSearch = new E2_RecursiveSearch_TAG(oTagPruefFeld);
			
			if (oSearch.get_SingleFoundComponent()!=null && oSearch.get_SingleFoundComponent() instanceof MyE2_DB_TextField)
			{
				MyE2_DB_TextField  oDBTF = (MyE2_DB_TextField)oSearch.get_SingleFoundComponent();

				if (oDBTF.isEnabled())
				{
					this.vFuellFelder.add((MyE2_DB_TextField)oSearch.get_SingleFoundComponent());
					this.vBeschriftung.add(new MyE2_String(cBeschiftung));
				}
			}
			else
			{
				bRueck = false;
			}
		}
		return bRueck;
	}
	
	
	
	private class ownPopup extends E2_BasicModuleContainer
	{
		public ownPopup()
		{
			super();
			
			
		}
	}
	
}
