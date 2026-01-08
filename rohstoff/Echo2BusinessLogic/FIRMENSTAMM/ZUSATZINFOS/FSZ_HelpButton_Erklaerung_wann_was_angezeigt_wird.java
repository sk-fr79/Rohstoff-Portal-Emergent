package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS;

import java.util.Vector;

import echopointng.Separator;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlainMonospace;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlanCourier;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpButtonWithHelpWindow;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.CB_DirectPrint;

public class FSZ_HelpButton_Erklaerung_wann_was_angezeigt_wird extends MyE2_PopUpButtonWithHelpWindow
{
	
//	HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_ALLGEMEIN, 			"Allgemeine Info");
//	HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO, 		"Info zu EK- und VK-Kontrakten");
//	HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO_EK, 	"Info zu EK-Kontrakten");
//	HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO_VK, 	"Info zu VK-Kontrakten");
//	HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_TPA_INFO, 			"Info zu Transportaufträgen");
//	HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_RECH_GUT_INFO, 		"Info zu Rechnungen/Gutschriften");
//	HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_RECHNUNG_INFO, 		"Info zu Rechnungen");
//	HM_ADRESS_INFO_TYP.put(myCONST.ADRESS_INFO_TYP_GUTSCHRIFT_INFO, 	"Info zu Gutschriften");
//	HM_ADRESS_INFO_TYP.put(myCONST. ADRESS_INFO_TYP_ANGEBOT_INFO, 		"Info zu Angeboten");
//	HM_ADRESS_INFO_TYP.put(myCONST. ADRESS_INFO_TYP_ABGEBOT_EK_INFO, 	"Info zu Abnahme-Angeboten");
//	HM_ADRESS_INFO_TYP.put(myCONST. ADRESS_INFO_TYP_ANGEBOT_VK_INFO, 	"Info zu Verkaufs-Angeboten");
//	HM_ADRESS_INFO_TYP.put(myCONST. ADRESS_INFO_TYP_EINKAUF,			"Info zu Einkauf (Allgemein/Fuhren)");
//	HM_ADRESS_INFO_TYP.put(myCONST. ADRESS_INFO_TYP_VERKAUF, 			"Info zu Verkauf (Allgemein/Fuhren)");
//	HM_ADRESS_INFO_TYP.put(myCONST. ADRESS_INFO_TYP_FIBU, 				"Info FIBU/Buchhaltung");

	
	public FSZ_HelpButton_Erklaerung_wann_was_angezeigt_wird()
	{
		super(new Vector<MyString>(), new Extent(800), new Extent(400), new E2_FontPlanCourier(-2));
	}

	
	
	@Override
	public E2_BasicModuleContainer createOwnBasicModuleContainer() throws myException
	{
		return new ownBasicContainer();
	}
	
	private class ownBasicContainer extends E2_BasicModuleContainer
	{
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FSZ_HelpButton_Erklaerung_wann_was_angezeigt_wird oButton = new FSZ_HelpButton_Erklaerung_wann_was_angezeigt_wird();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		
		return oButton;

	}
	
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		//immer enabled
	}

	
	
    public void fillColumnInnen(MyE2_Column ColInnen)
    {
    	ColInnen.removeAll();
    	
    	MyE2_Grid oGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
    	
		oGrid.add(new MyE2_Label(new MyE2_String("Wann werden welche Texte angezeigt:")), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_1_1_1_1,null,2));
		oGrid.add(new Separator(), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_1_1_1_1,null,2));
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_ALLGEMEIN,"Wird in allen Modulen angezeigt, wo Popup-Meldungen erscheinen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO,"Wird im der Kopfmaske von EK- UND VK-Kontrakten gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO_EK,"Wird im der Kopfmaske von EK-Kontrakten gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_KONTRAKT_INFO_VK,"Wird im der Kopfmaske von VK-Kontrakten gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_TPA_INFO,"Wird im der Kopfmaske von Transportaufträgen gezogen (beim Laden dieser Adresse als Spedition)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_RECH_GUT_INFO,"Wird im der Kopfmaske von Rechnungen und Gutschriften gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_RECHNUNG_INFO,"Wird im der Kopfmaske von Rechnungen gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_GUTSCHRIFT_INFO,"Wird im der Kopfmaske von Gutschriften gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_ANGEBOT_INFO,"Wird im der Kopfmaske von Abnahme- und Verkaufsangeboten gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_ABGEBOT_EK_INFO,"Wird im der Kopfmaske von Abnahmeangeboten gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_ANGEBOT_VK_INFO,"Wird im der Kopfmaske von Verkaufsangeboten gezogen (beim Laden dieser Adresse)");

		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_EINKAUF,"Wird in den Modulen: EK-Kontrakt-Kopf/Gutschrift-Kopf/Fuhrenzentrale (Lade-Seite) gezogen (beim Laden dieser Adresse)");
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_VERKAUF,"Wird in den Modulen: VK-Kontrakt-Kopf/Rechnung-Kopf/Fuhrenzentrale (Ablade-Seite) gezogen (beim Laden dieser Adresse)");
		
		this.gleichlang(oGrid,myCONST.ADRESS_INFO_TYP_FIBU,"Sonderfall: Wird nur in der Fibu-Liste in der zugehörigen Spalte gezogen");

    	
    	ColInnen.add(oGrid);
    }
 	
	private void gleichlang(MyE2_Grid oGrid, String Typ, String Zusatztext)
	{
//		int iBreit = 40;
//		String cTypBeschreibung = bibALL.MindestLaenge(myCONST.HM_ADRESS_INFO_TYP.get(Typ), iBreit)+": " ;
		
		oGrid.add(new MyE2_Label(myCONST.HM_ADRESS_INFO_TYP.get(Typ)+":"), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_1_1_10_1));
		oGrid.add(new MyE2_Label(new MyE2_String(Zusatztext),true), MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_1_1_1_1));
		
		
	}


}
