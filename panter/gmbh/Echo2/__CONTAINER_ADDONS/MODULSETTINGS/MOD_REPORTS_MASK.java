package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_HelpButtonWithHelpWindow;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.REPORT;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class MOD_REPORTS_MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private MOD_REPORTS_ComponentMAPMASK oComponentMAPMASK = null;

	private Insets		oInsetsLeft = new Insets(2,2,10,2);
	private Insets		oInsetsRight = new Insets(2,2,2,2);
	
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public MOD_REPORTS_MASK(MOD_REPORTS_ComponentMAPMASK  	oCompomentMAPMask) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		this.oComponentMAPMASK = oCompomentMAPMask;
		
		MyE2_Grid 		oGrid1 = 	new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER());
		MyE2_Grid		oGrid2 = 	new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER());
		MyE2_TabbedPane oTabbed = 	new MyE2_TabbedPane(null);
		oTabbed.set_bAutoHeight(true);
		
		oTabbed.add_Tabb(new MyE2_String("Report"),oGrid1);
		oTabbed.add_Tabb(new MyE2_String("Parameter"),oGrid2);
		
		this.add(oTabbed);
		
		this.add_pair("ID","ID_REPORT",oGrid1);
		//this.add_pair("Modul","MODULE_KENNER",oGrid1);
		oGrid1.add(new Separator(),2,new Insets(2,0,0,2));
		
		this.add_pair("AKTIV?",REPORT.aktiv.fn(),oGrid1);
		oGrid1.add(new Separator(),2,new Insets(2,0,0,2));
		
		this.add_pair("Titel des Reports","TITEL",oGrid1);
		this.add_pair("Beschreibung","BESCHREIBUNG",oGrid1);
		this.add_pair("Text auf Knopf","BUTTONTEXT",oGrid1);
		this.add_pair("Button-Kenner","BUTTON_AUTH_KENNER",oGrid1);
		this.add_pair("Report-Datei","NAME_OF_REPORTFILE",oGrid1);
		
		oGrid1.add(new Separator(),2,new Insets(2,0,0,2));
		oGrid1.add(new MyE2_Label(new MyE2_String("ID-Übergabe:")),this.oInsetsLeft);
		oGrid1.add(new E2_ComponentGroupHorizontal(0,	new MyE2_Label(new MyE2_String("Keine:")),oCompomentMAPMask.get_Comp("PASS_NO_ID"),
														new MyE2_Label(new MyE2_String("Einzel:")),oCompomentMAPMask.get_Comp("PASS_SINGLE_ID"),
														new MyE2_Label(new MyE2_String("Mehrfach:")),oCompomentMAPMask.get_Comp("PASS_MULTI_ID"), 
														new Insets(0,0,5,0)),this.oInsetsRight);
		
		oGrid1.add(new Separator(),2,new Insets(2,0,0,2));
		oGrid1.add(new MyE2_Label(new MyE2_String("Zugriffsbeschränkung:")),this.oInsetsLeft);
		oGrid1.add(new E2_ComponentGroupHorizontal(0,oComponentMAPMASK.get_Comp("SUPERVISOR"),this.oComponentMAPMASK.get_Comp("GESCHAEFTSFUEHRER"),E2_INSETS.I_0_0_10_0),this.oInsetsLeft);
		
		
		oGrid1.add(new Separator(),2,new Insets(2,0,0,2));
		
		MyE2_Grid oGridHelp = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		oGridHelp.add(new MyE2_Label(new MyE2_String("PDF")), 	new Insets(2));
		oGridHelp.add(new MyE2_Label(new MyE2_String("XLS")), 	new Insets(2));
		oGridHelp.add(new MyE2_Label(new MyE2_String("HTML")), 	new Insets(2));
		oGridHelp.add(new MyE2_Label(new MyE2_String("TXT")), 	new Insets(2));
		oGridHelp.add(new MyE2_Label(new MyE2_String("XML")), 	new Insets(2));

		oGridHelp.add(this.oComponentMAPMASK.get_Comp("ALLOW_PDF"), 	new Insets(2));
		oGridHelp.add(this.oComponentMAPMASK.get_Comp("ALLOW_XLS"), 	new Insets(2));
		oGridHelp.add(this.oComponentMAPMASK.get_Comp("ALLOW_HTML"), 	new Insets(2));
		oGridHelp.add(this.oComponentMAPMASK.get_Comp("ALLOW_TXT"), 	new Insets(2));
		oGridHelp.add(this.oComponentMAPMASK.get_Comp("ALLOW_XML"), 	new Insets(2));

		oGrid1.add(new MyE2_Label(new MyE2_String("Mögliche Ausgabeformate")),this.oInsetsLeft);
		oGrid1.add(oGridHelp,oInsetsRight);
		
		MyE2_Grid oGridHelp2 = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		oGridHelp2.add(new MyE2_Label(new MyE2_String("PDF")), 	new Insets(2));
		oGridHelp2.add(new MyE2_Label(new MyE2_String("XLS")), 	new Insets(2));
		oGridHelp2.add(new MyE2_Label(new MyE2_String("HTML")), 	new Insets(2));
		oGridHelp2.add(new MyE2_Label(new MyE2_String("TXT")), 	new Insets(2));
		oGridHelp2.add(new MyE2_Label(new MyE2_String("XML")), 	new Insets(2));

		oGridHelp2.add(this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_PDF), 	new Insets(2));
		oGridHelp2.add(this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_XLS), 	new Insets(2));
		oGridHelp2.add(this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_HTML), 	new Insets(2));
		oGridHelp2.add(this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_TXT), 	new Insets(2));
		oGridHelp2.add(this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_XML), 	new Insets(2));
		
		ActionAgent_RadioFunction_CheckBoxList AA_RB = new ActionAgent_RadioFunction_CheckBoxList(false);
		AA_RB.add_CheckBox((MyE2_DB_CheckBox)this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_PDF));
		AA_RB.add_CheckBox((MyE2_DB_CheckBox)this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_XLS));
		AA_RB.add_CheckBox((MyE2_DB_CheckBox)this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_HTML));
		AA_RB.add_CheckBox((MyE2_DB_CheckBox)this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_TXT));
		AA_RB.add_CheckBox((MyE2_DB_CheckBox)this.oComponentMAPMASK.get_Comp(_DB.REPORT$PREFER_XML));
		
		oGrid1.add(new MyE2_Label(new MyE2_String("Bevorzugtes Ausgabeformat")),this.oInsetsLeft);
		oGrid1.add(oGridHelp2,oInsetsRight);
		
		
		oGrid1.add(new Separator(),2,new Insets(2,0,0,2));
		oGrid1.add(new MyE2_Label(new MyE2_String("Erlaubte Maileingabe:")),this.oInsetsLeft);
		oGrid1.add(new E2_ComponentGroupHorizontal(0,	new MyE2_Label(new MyE2_String("Freie:")),oCompomentMAPMask.get_Comp("ALLOW_EMAIL_FREE"),
														new MyE2_Label(new MyE2_String("Firmenstamm:")),oCompomentMAPMask.get_Comp("ALLOW_EMAIL_SEARCH_CUSTOMER"),
														new MyE2_Label(new MyE2_String("Mitarbeiter:")),oCompomentMAPMask.get_Comp("ALLOW_EMAIL_EMPLOYES"), 
														new Insets(0,0,5,0)),this.oInsetsRight);
		
		this.add_pair("Feste Adressen","STATIC_MAIL_ADRESSES",oGrid1);
		
		

		ownHelpButton oHelpButton = new ownHelpButton(new Extent(700), new Extent(500));
		
		oGrid2.add(new MyE2_Label(new MyE2_String("Parameter, die dem Report übergeben werden ...")), new Insets(3));
		oGrid2.add(oHelpButton, new Insets(3));
		
		oGrid2.add(oCompomentMAPMask.get_Comp("DAUGHTER_PARAMETERS"),2, new Insets(3));
		
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
		
	}

	
	
	private class ownHelpButton extends MyE2_HelpButtonWithHelpWindow
	{
		
		public ownHelpButton(Extent ExtWidth, Extent ExtHeight)
		{
			super(ExtWidth, ExtHeight);
		}

		@Override
		public E2_BasicModuleContainer createOwnBasicModuleContainer() throws myException
		{
			return new ownBasicContainer();
		}
		private class ownBasicContainer extends E2_BasicModuleContainer
		{
		}
		@Override
		public void fillInternalColumn(MyE2_Column oColumn) throws myException
		{
			oColumn.removeAll();
			
			// hilfe fuer die parameter-definition (select-Feld)
			Vector<MyString> vHelpText = new Vector<MyString>();
			vHelpText.add(new MyE2_String("Information zur Parameter-Definition:"));
			vHelpText.add(new MyE2_String("-------------------------------------------------------------------------"));
			vHelpText.add(new MyE2_String("Der Wert <Beschreibung des Parameters> taucht als Hilfe in der Eingabe-Maske"));
			vHelpText.add(new MyE2_String("bei der Parameter-Abfrage auf (falls er nicht leer ist)."));
			vHelpText.add(new MyE2_String("-------------------------------------------------------------------------"));
			vHelpText.add(new MyE2_String("Im Wert <Definition für Dropdown-Feld> kann eine Definition erzeugt werden,"));
			vHelpText.add(new MyE2_String("mit der eine Eingabe via DropDown-Feld an den Report übergeben werden kann."));
			vHelpText.add(new MyE2_String("Dies kann eine Abfrage sein:"));
			vHelpText.add(new MyE2_String("<SELECT ANZEIGE,WERT FROM REFERENZTABELLE ...> wobei der Eintrag in der WERT- "));
			vHelpText.add(new MyE2_String("Spalte an der Report übergeben wird."));
			vHelpText.add(new MyE2_String("-------------------------------------------------------------------"));
			
			vHelpText.add(new MyE2_String("Eine DropDown-Auswahl kann mit einem 2-spaltigen Array definiert werden:"));
			vHelpText.add(new MyE2_String("|Anzeige1:Wert1|Anzeige2:Wert2|Anzeige3:Wert3|Anzeige3:Wert3|"));
			vHelpText.add(new MyE2_String("Der erste Wert in jedem Block ist in der Anzeige zu sehen, der 2. Wert wird an"));
			vHelpText.add(new MyE2_String("den Report übergeben."));
			vHelpText.add(new MyE2_String("-------------------------------------------------------------------"));
			vHelpText.add(new MyE2_String("Eine Checkbox, die Y oder N übergibt, erzeugt der Eintrag #CHECKBOX# im Feld  <Definition für Dropdown-Feld> !"));
			vHelpText.add(new MyE2_String("-------------------------------------------------------------------"));
			
			MyE2_Column  colInfoblock1 = new MyE2_Column();
			MyE2_HelpButtonWithHelpWindow.add_StringsToColumn(vHelpText, colInfoblock1, true, new E2_FontPlain(-2));
			oColumn.add(colInfoblock1);
			
			//jetzt ein grid mit den moeglichen platzhaltern
			int iBreite[] = {100,200,200};
			MyE2_Grid oGridInfoBlock2 = new MyE2_Grid(iBreite, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS()) ;
			oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("In den Vorgabewerten und Abfragen koennen folgende Platzhalter stehen:",true),new E2_FontBold(-2)),3,E2_INSETS.I_1_0_1_0);
			
			LinkedHashMap<String, String[]> lhashMap = bibReplacer.get_ListOfReplaceFields(null);
			
			oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("Platzhalter"),		new E2_FontBold(-2)),E2_INSETS.I_1_0_1_0);
			oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("Momentaner Wert"),	new E2_FontBold(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
			oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("Beschreibung"),		new E2_FontBold(-2)),E2_INSETS.I_1_0_1_0);
			
			for (String cKey: lhashMap.keySet())
			{
				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(cKey),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(lhashMap.get(cKey)[1]),new E2_FontItalic(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(lhashMap.get(cKey)[0]),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
			}

			
			String modulkenner = MOD_REPORTS_MASK.this.oComponentMAPMASK.getModulkennerListBelongsTo();
			Vector<ENUM_Selector_Report_Params> selector_params = Selector_Report_Params.getParamForModul(E2_MODULNAME_ENUM.find_Corresponding_Enum(modulkenner));
			for (ENUM_Selector_Report_Params param: selector_params){
				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(param.get_Name_For_Param()),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("-"),new E2_FontItalic(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(param.get_Description()),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
			}
			
			
			oColumn.add(oGridInfoBlock2);
			
		}
	}

	
	private void add_pair(String cText, String cHASH, MyE2_Grid oGrid) throws myException
	{
		oGrid.add(new MyE2_Label(new MyE2_String(cText)),this.oInsetsLeft);
		oGrid.add(this.oComponentMAPMASK.get_Comp(cHASH),this.oInsetsRight);
	}


	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
	
}
