package rohstoff.Echo2BusinessLogic._TAX.RULES;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectField_WithAutoToolTip;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB_SYNTAX;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic._TAX.RATE.TAX_DATATOVIEW_ACTIVE;

public class TR__LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public TR__LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		MyE2_SelectField	oSelectLandQuelle = 		new MyE2_SelectField(
																"select laendername,id_land from "+bibE2.cTO()+".jd_land order by laendername",
																false,true,false,false);
		
		MyE2_SelectField	oSelectLandZiel = 			new MyE2_SelectField(
																"select laendername,id_land from "+bibE2.cTO()+".jd_land order by laendername",
																false,true,false,false);
		
		MyE2_CheckBox       oCBBetrifftMandant = 		new MyE2_CheckBox();
		
		DD_VERANTWORTUNG    oSelect_TP_Verantwortung = 	new DD_VERANTWORTUNG(100);
		
//		MyE2_SelectField_WithAutoToolTip	oSelectSteuerDefEK = new MyE2_SelectField_WithAutoToolTip("SELECT '('||TO_CHAR(STEUERSATZ,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||' %)  '||DROPDOWN_TEXT,ID_TAX FROM "+bibE2.cTO()+".JT_TAX ORDER BY 1",false,true,false,false);
//		MyE2_SelectField_WithAutoToolTip	oSelectSteuerDefVK = new MyE2_SelectField_WithAutoToolTip("SELECT '('||TO_CHAR(STEUERSATZ,'fm9g900d0','NLS_NUMERIC_CHARACTERS = '',.''')||' %)  '||DROPDOWN_TEXT,ID_TAX FROM "+bibE2.cTO()+".JT_TAX ORDER BY 1",false,true,false,false);

		MyE2_SelectField_WithAutoToolTip	oSelectSteuerDefEK = new MyE2_SelectField_WithAutoToolTip(new TAX_DATATOVIEW_ACTIVE(false));
		MyE2_SelectField_WithAutoToolTip	oSelectSteuerDefVK = new MyE2_SelectField_WithAutoToolTip(new TAX_DATATOVIEW_ACTIVE(false));

		//2013-10-02: steuerdefinition fuer negative betraege auch selektierbar
		MyE2_SelectField_WithAutoToolTip	oSelectSteuerDefEK_NEGATIV = new MyE2_SelectField_WithAutoToolTip(new TAX_DATATOVIEW_ACTIVE(false));
		MyE2_SelectField_WithAutoToolTip	oSelectSteuerDefVK_NEGATIV = new MyE2_SelectField_WithAutoToolTip(new TAX_DATATOVIEW_ACTIVE(false));
		
		this.oSelVector.add(new E2_ListSelectorStandard(oSelectLandQuelle,			"(JT_HANDELSDEF.ID_LAND_QUELLE_JUR=#WERT# OR JT_HANDELSDEF.ID_LAND_QUELLE_GEO=#WERT#)",null,10));
		this.oSelVector.add(new E2_ListSelectorStandard(oSelectLandZiel,			"(JT_HANDELSDEF.ID_LAND_ZIEL_JUR=#WERT# OR JT_HANDELSDEF.ID_LAND_ZIEL_GEO=#WERT#)",null,10));
		this.oSelVector.add(new E2_ListSelectorStandard(oSelect_TP_Verantwortung,	"JT_HANDELSDEF.TP_VERANTWORTUNG='#WERT#'",null,10));
		
		this.oSelVector.add(new E2_ListSelectorStandard(oSelectSteuerDefEK,			"JT_HANDELSDEF.ID_TAX_QUELLE=#WERT#",null,10));
		this.oSelVector.add(new E2_ListSelectorStandard(oSelectSteuerDefVK,			"JT_HANDELSDEF.ID_TAX_ZIEL=#WERT#",null,10));

		this.oSelVector.add(new E2_ListSelectorStandard(oSelectSteuerDefEK_NEGATIV,		bibDB_SYNTAX.GENERATE_SIMPLE_WHERE(_DB.HANDELSDEF, _DB.HANDELSDEF$ID_TAX_NEGATIV_QUELLE,	"#WERT#", false),null,10));
		this.oSelVector.add(new E2_ListSelectorStandard(oSelectSteuerDefVK_NEGATIV,		bibDB_SYNTAX.GENERATE_SIMPLE_WHERE(_DB.HANDELSDEF, _DB.HANDELSDEF$ID_TAX_NEGATIV_ZIEL, 		"#WERT#", false),null,10));

		this.oSelVector.add(new E2_ListSelectorStandard(oCBBetrifftMandant,			"NVL(JT_HANDELSDEF.QUELLE_IST_MANDANT,'N')='Y' OR NVL(JT_HANDELSDEF.ZIEL_IST_MANDANT,'N')='Y'",null));

		int[] iBreite = {100,180,180};
		E2_ListSelektorMultiselektionStatusFeld_STD  oStatusVarianten_RC_EK_EinAus = new E2_ListSelektorMultiselektionStatusFeld_STD(iBreite,false,new MyE2_String("EK-Seite"),null);
		E2_ListSelektorMultiselektionStatusFeld_STD  oStatusVarianten_RC_VK_EinAus = new E2_ListSelektorMultiselektionStatusFeld_STD(iBreite,false,new MyE2_String("VK-Seite"),null);
		
		oStatusVarianten_RC_EK_EinAus.ADD_STATUS_TO_Selector(true,	"(NVL(JT_HANDELSDEF."+RECORD_HANDELSDEF.FIELD__SORTE_RC_QUELLE+",-2)=1)",		new MyE2_String("RC-Sorte"),   		new MyE2_String("Zeige Einträge mit RC-Sorten auf der Quell-Seite"));		
		oStatusVarianten_RC_EK_EinAus.ADD_STATUS_TO_Selector(true,	"(NVL(JT_HANDELSDEF."+RECORD_HANDELSDEF.FIELD__SORTE_RC_QUELLE+",-2)=-1)",		new MyE2_String("NICHT-RC-Sorte"), 	new MyE2_String("Zeige Einträge mit NICHT-RC-Sorten auf der Quell-Seite"));		
		oStatusVarianten_RC_EK_EinAus.ADD_STATUS_TO_Selector(true,	"(NVL(JT_HANDELSDEF."+RECORD_HANDELSDEF.FIELD__SORTE_RC_QUELLE+",-2)=0)",		new MyE2_String("RC Status egal"), 	new MyE2_String("Zeige Einträge mit dem Eintrag RC-Sorten EGAL auf der Quell-Seite"));		

		oStatusVarianten_RC_VK_EinAus.ADD_STATUS_TO_Selector(true,	"(NVL(JT_HANDELSDEF."+RECORD_HANDELSDEF.FIELD__SORTE_RC_ZIEL+",-2)=1)",			new MyE2_String("RC-Sorte"),   		new MyE2_String("Zeige Einträge mit RC-Sorten auf der Ziel-Seite"));		
		oStatusVarianten_RC_VK_EinAus.ADD_STATUS_TO_Selector(true,	"(NVL(JT_HANDELSDEF."+RECORD_HANDELSDEF.FIELD__SORTE_RC_ZIEL+",-2)=-1)",		new MyE2_String("NICHT-RC-Sorte"), 	new MyE2_String("Zeige Einträge mit NICHT-RC-Sorten auf der Ziel-Seite"));		
		oStatusVarianten_RC_VK_EinAus.ADD_STATUS_TO_Selector(true,	"(NVL(JT_HANDELSDEF."+RECORD_HANDELSDEF.FIELD__SORTE_RC_ZIEL+",-2)=0)",			new MyE2_String("RC Status egal"), 	new MyE2_String("Zeige Einträge mit dem Eintrag RC-Sorten EGAL auf der Ziel-Seite"));		
		
		this.oSelVector.add(oStatusVarianten_RC_EK_EinAus);
		this.oSelVector.add(oStatusVarianten_RC_VK_EinAus);

		//weitere Selektoren fuer aktiv/inaktiv
		TR__LIST_Selector_SelActiveOrNot oSelActiveInactive = new TR__LIST_Selector_SelActiveOrNot();
		this.oSelVector.add(oSelActiveInactive);
		
		//selektor auf singulaere / mehrfache regeln
		TR__LIST_Selector_SingleOrMulti  selSingleMulti = new TR__LIST_Selector_SingleOrMulti();
		this.oSelVector.add(selSingleMulti);
		
		int iSpalten[] = {80,80,80,80,460,80,80,100};
		MyE2_Grid oGridInnen = new MyE2_Grid(iSpalten,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oSelectLandQuelle.setWidth(new Extent(70));
		oSelectLandZiel.setWidth(new Extent(70));
		
		oSelect_TP_Verantwortung.setWidth(new Extent(100));
		oSelectSteuerDefEK.setWidth(new Extent(70));
		oSelectSteuerDefVK.setWidth(new Extent(70));
		
		oSelectSteuerDefEK_NEGATIV.setWidth(new Extent(70));
		oSelectSteuerDefVK_NEGATIV.setWidth(new Extent(70));

		
		oGridInnen.add(new ownPosHelper("Land Ladeseite:",		oSelectLandQuelle,				"Land Abladeseite:",				oSelectLandZiel,0, 3,10),E2_INSETS.I_0_0_0_0);
		oGridInnen.add(new ownPosHelper("TP-Verantwort.:",		oSelect_TP_Verantwortung,		"Betrifft Mandant:",		oCBBetrifftMandant,0, 3,10),E2_INSETS.I_0_0_0_0);
		oGridInnen.add(new ownPosHelper("Steuer Ladeseite:",	oSelectSteuerDefEK,				"Steuer Abladeseite:",				oSelectSteuerDefVK,0, 3,10),E2_INSETS.I_0_0_0_0);
		oGridInnen.add(new ownPosHelper("Ste.Neg.Ladeseite:",	oSelectSteuerDefEK_NEGATIV,									"Ste.Neg.Abladeseite:",			oSelectSteuerDefVK_NEGATIV,0, 3,10),E2_INSETS.I_0_0_0_0);
		oGridInnen.add(new ownPosHelper("RC Ladeseite:",		oStatusVarianten_RC_EK_EinAus.get_oComponentForSelection(),"RC Abladeseite:",oStatusVarianten_RC_VK_EinAus.get_oComponentForSelection(),0, 3,10),E2_INSETS.I_0_0_0_0);

		//2013-10-02: selecktor fuer aktiv/inaktiv
		oGridInnen.add(new ownPosHelper(oSelActiveInactive.get_oComponentWithoutText(),		0, 3,10),E2_INSETS.I_0_0_0_0);
		
		//2019-02-18: selektor fuer singulaere / merhfache
		oGridInnen.add(new ownPosHelper(selSingleMulti.get_oComponentWithoutText(),			0, 3,10),E2_INSETS.I_0_0_0_0);
		
		//2013-02-28: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);
		oGridInnen.add(oDB_BasedSelektor.get_oComponentForSelection(100), new Insets(4,2,10,2));

		
	}

	
	
	
	
	
	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	
	
	private class DD_VERANTWORTUNG extends MyE2_SelectField
	{

		public DD_VERANTWORTUNG(int iWidth) throws myException
		{
			
			String[][] cWerte = new String[4][2];

			
			cWerte[0][0] = "-";
			cWerte[0][1] = "";

			cWerte[1][0] = bibALL.get_RECORD_MANDANT().get___KETTE(bibVECTOR.get_Vector("NAME1", "NAME2"), "<Mandant>", "", "", " ");
			cWerte[1][1] = TAX_CONST.TP_VERANTWORTUNG_MANDANT;
			
			cWerte[2][0] = "Lieferant";
			cWerte[2][1] = TAX_CONST.TP_VERANTWORTUNG_QUELLE;
			
			cWerte[3][0] = "Abnehmer";
			cWerte[3][1] = TAX_CONST.TP_VERANTWORTUNG_ZIEL;

			
			this.set_ListenInhalt(cWerte, false);
			this.setWidth(new Extent(iWidth));
			
		}

	}

	
	
	private class ownPosHelper extends MyE2_Grid
	{
		
		
		public ownPosHelper(	String cText1, Component oComp4Selection1, 
								String cText2, Component oComp4Selection2,
								int iLeft, int iAbstand, int iRight)
		{
			super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			this.add(new MyE2_Label(new MyE2_String(cText1)), new Insets(iLeft,0,iAbstand,0));
			this.add(oComp4Selection1, new Insets(0,0,iRight,0));
			this.add(new MyE2_Label(new MyE2_String(cText2)), new Insets(iLeft,0,iAbstand,0));
			this.add(oComp4Selection2, new Insets(0,0,iRight,0));
		}


		public ownPosHelper(	Component oComp4Selection1, 
								int iLeft, int iAbstand, int iRight)
		{
			super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			this.add(oComp4Selection1, new Insets(0,0,iRight,0));
		}

		
		
	}
	
}
