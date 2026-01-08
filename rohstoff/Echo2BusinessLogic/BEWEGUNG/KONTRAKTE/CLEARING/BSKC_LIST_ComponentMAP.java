package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObjects_LISTHEADLINE;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSKC_LIST_ComponentMAP extends E2_ComponentMAP
{
	
	// fuer das refreshen nach der zuordnung im clearing muss hier die info stehen, ob es die haupt- oder die tochterliste ist
	
	private String cID_VPOS_KON__Gegen = null;
	
	/**
	 * 
	 * @param oModulContainer
	 * @param cEK_VK
	 * @param ID_VPOS_KON_Gegen  wird != null, wenn die liste als tochterausklappliste benutzt wird
	 * @throws myException
	 */
	public BSKC_LIST_ComponentMAP(E2_NavigationList navilist, BSKC_ModulContainerLIST oModulContainer, String cEK_VK, String ID_VPOS_KON_Gegen) throws myException
	{
		super(new BSKC_SQLFieldMAP(cEK_VK,ID_VPOS_KON_Gegen));
		
		this.cID_VPOS_KON__Gegen = ID_VPOS_KON_Gegen;
		
		BSKC_SQLFieldMAP oFM = (BSKC_SQLFieldMAP)this.get_oSQLFieldMAP();

		GridLayoutData  GridLayoutLeft = this.get_GRID_LeftHauptListe();
		GridLayoutData  GridLayoutRight = this.get_GRID_RightHauptListe();

		if (S.isFull(ID_VPOS_KON_Gegen))
		{
			GridLayoutLeft = this.get_GRID_LeftAusklappListe();
			GridLayoutRight = this.get_GRID_RightAusklappListe();
		}	
		else
		{
			this.add_Component("CHECK_BOX",new E2_CheckBoxForList(),new MyE2_String("?"));
		}
		
		if (S.isEmpty(ID_VPOS_KON_Gegen))
		{
			this.add_Component("EXTENDER",new E2_ListButtonExtendDaughterObject(false, null),new MyE2_String("+"));
		}
		
		this.add_Component(BSKC__CONST.HASH_LABEL_LOCKED, 				new MyE2_Label(BSKC__CONST.LABEL_EMPTY),new MyE2_String("A"));
		this.add_Component(BSKC__CONST.HASH_BUTTON_ZUORDNUNG, 			new BSKC_BT_ZUORDNUNG(),new MyE2_String("Z"));
		this.add_Component(BSKC__CONST.HASH_BUTTON_LOCK_UNLOCK_POS, 	new BSKC__BT_LOCK_UNLOCK_KONTRAKTPOS(null,null,true,oModulContainer.get_oNavigationList()),new MyE2_String("-"));
		
		//2012-10-26: sprungbutton in die fuhrenzentrale
		this.add_Component(BSKC__CONST.HASH_BUTTON_JUMP_TO_FUHRE, 		new BSKC_LIST_BT_JUMP_to_FuhrenMitKontrakt(cEK_VK),new MyE2_String("-"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GUELTIGKEITSZEITRAUM")), new MyE2_String("Gültigkeit"));

		/*20170608: START incoterms einfuegen*/
		this.add_Component(new MyE2_DB_Label(oFM.get_(VPOS_KON.lieferbedingungen), 			MyE2_Label.STYLE_SMALL_PLAIN()),new MyE2_String("Lf.Bd."));
		this.get__Comp(VPOS_KON.lieferbedingungen).EXT().set_oColExtent(new Extent(60));
		/*20170608: ENDE incoterms einfuegen*/
		
		this.add_Component(new MyE2_DB_Label(oFM.get_("K_NAME1"), 			MyE2_Label.STYLE_SMALL_PLAIN()),new MyE2_String(cEK_VK.equals("EK")?"Name Lieferant":"Name Abnehmer"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("K_ORT"), 			MyE2_Label.STYLE_SMALL_PLAIN()),new MyE2_String(cEK_VK.equals("EK")?"Ort Lieferant":"Ort Abnehmer"),false,true);
		this.add_Component(new MyE2_DB_Label(oFM.get_("ANR1_ANR2")),		new MyE2_String("ANR1-2"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ARTBEZ1")),			new MyE2_String("Artikelbez. 1"),false,true);
		this.add_Component(new MyE2_DB_Label(oFM.get_("ANZAHL")),			new MyE2_String("Menge"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("EINZELPREIS")),		new MyE2_String("E-Preis"));

		this.add_Component(BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE, 		new MyE2_Label(""),			new MyE2_String("Sum "+(cEK_VK.equals("EK")?"VKs":"EKs")),			true);
		this.add_Component(BSKC__CONST.HASH_MENGE_SUMME_LAGER, 				new MyE2_Label(""),			new MyE2_String("Sum Lag."),										true);

		
		this.add_Component(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_LAGER_IN_FUHRE.key(),			new BSK_GridWithNumber(navilist,BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_LAGER_IN_FUHRE, cEK_VK),			new MyE2_String("Lag.schon in Fuhre"),								true);
		this.add_Component(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_ZUORDNUNG.key(), 		new BSK_GridWithNumber(navilist,BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_ZUORDNUNG, cEK_VK),			new MyE2_String((cEK_VK.equals("EK")?"VKs":"EKs")+"+Lager(Plan)"),	true);
		this.add_Component(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_DIFFERENZ.key(),			new BSK_GridWithNumber(navilist,BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_DIFFERENZ, cEK_VK),			new MyE2_String("Fehl-/Übermenge"),									true);
		this.add_Component(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key(), 	new BSK_GridWithNumber(navilist,BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE, cEK_VK),	new MyE2_String("Fhr.-Mge Plan/Echt"),								true);
		this.add_Component(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_REALMENGE.key(), 	new BSK_GridWithNumber(navilist,BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_REALMENGE, cEK_VK),	new MyE2_String("Fhr.-Mge Echt"),									true);
		this.add_Component(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_RECH_GUT_POS.key(), 			new BSK_GridWithNumber(navilist,BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_RECH_GUT_POS, cEK_VK),			new MyE2_String((cEK_VK.equals("EK")?"Menge gutgeschrieben":"Menge abgerechnet")),									true);
		
		this.add_Component(new BSKC_BT_LIST_OPEN_KONTRAKT_POS(oFM,oModulContainer.get_oKontraktPosMASK_EK(),oModulContainer.get_oKontraktPosMASK_VK(),cEK_VK),new MyE2_String("ID Kont.Pos"));
		this.add_Component(new BSKC_BT_LIST_OPEN_KONTRAKT_KOPF(oFM,oModulContainer.get_oKontraktKopfMASK_EK(),oModulContainer.get_oKontraktKopfMASK_VK(),cEK_VK),new MyE2_String("ID Kon.Kopf"));
		this.add_Component(new BSKC_BT_LIST_OPEN_FIRMA(oFM,oModulContainer.get_oFirmenMASK(),oModulContainer,cEK_VK),new MyE2_String("ID Adresse"));
		
		//die layouts uebergeben
		this.get__Comp(BSKC__CONST.HASH_LABEL_LOCKED).EXT().set_oLayout_ListElement(GridLayoutLeft);
		this.get__Comp(BSKC__CONST.HASH_BUTTON_ZUORDNUNG).EXT().set_oLayout_ListElement(GridLayoutLeft);
		
		this.get__Comp("GUELTIGKEITSZEITRAUM").EXT().set_oLayout_ListElement(GridLayoutLeft);
		this.get__Comp("K_NAME1").EXT().set_oLayout_ListElement(GridLayoutLeft);
		this.get__Comp("K_ORT").EXT().set_oLayout_ListElement(GridLayoutLeft);
		this.get__Comp("ANR1_ANR2").EXT().set_oLayout_ListElement(GridLayoutLeft);
		this.get__Comp("ARTBEZ1").EXT().set_oLayout_ListElement(GridLayoutLeft);
		this.get__Comp("ANZAHL").EXT().set_oLayout_ListElement(GridLayoutRight);
		
		this.get__Comp(BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE).EXT().set_oLayout_ListElement(GridLayoutRight);
		this.get__Comp(BSKC__CONST.HASH_MENGE_SUMME_LAGER).EXT().set_oLayout_ListElement(GridLayoutRight);
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_LAGER_IN_FUHRE.key()).EXT().set_oLayout_ListElement(GridLayoutRight);
		
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_ZUORDNUNG.key()).EXT().set_oLayout_ListElement(GridLayoutRight);
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_DIFFERENZ.key()).EXT().set_oLayout_ListElement(GridLayoutRight);
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key()).EXT().set_oLayout_ListElement(GridLayoutRight);
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_RECH_GUT_POS.key()).EXT().set_oLayout_ListElement(GridLayoutRight);
		
		this.get__Comp("ID_VPOS_KON").EXT().set_oLayout_ListElement(GridLayoutRight);
		this.get__Comp("K_ID_VKOPF_KON").EXT().set_oLayout_ListElement(GridLayoutRight);
		this.get__Comp("AD_ID_ADRESSE").EXT().set_oLayout_ListElement(GridLayoutRight);
		
		BSKC__hm_ColExtend oExtendDef = new BSKC__hm_ColExtend();
		
		//die Spaltenbreiten festlegen, damit die ausklapplisten auf die gleiche breite kommen, wie die hauptliste
		this.get__Comp(BSKC__CONST.HASH_LABEL_LOCKED).EXT().set_oColExtent(oExtendDef.get(BSKC__CONST.HASH_LABEL_LOCKED));
		this.get__Comp(BSKC__CONST.HASH_BUTTON_ZUORDNUNG).EXT().set_oColExtent(oExtendDef.get(BSKC__CONST.HASH_BUTTON_ZUORDNUNG));
		
		this.get__Comp("GUELTIGKEITSZEITRAUM").EXT().set_oColExtent(oExtendDef.get("GUELTIGKEITSZEITRAUM"));
		this.get__Comp("K_NAME1").EXT().set_oColExtent(oExtendDef.get("K_NAME1"));
		this.get__Comp("K_ORT").EXT().set_oColExtent(oExtendDef.get("K_ORT"));
		this.get__Comp("ANR1_ANR2").EXT().set_oColExtent(oExtendDef.get("ANR1_ANR2"));
		this.get__Comp("ARTBEZ1").EXT().set_oColExtent(oExtendDef.get("ARTBEZ1"));
		this.get__Comp("ANZAHL").EXT().set_oColExtent(oExtendDef.get("ANZAHL"));
		
		this.get__Comp(BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE).EXT().set_oColExtent(oExtendDef.get(BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE));
		this.get__Comp(BSKC__CONST.HASH_MENGE_SUMME_LAGER).EXT().set_oColExtent(oExtendDef.get(BSKC__CONST.HASH_MENGE_SUMME_LAGER));
		
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_ZUORDNUNG.key()).EXT().set_oColExtent(oExtendDef.get(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_ZUORDNUNG.key()));
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_DIFFERENZ.key()).EXT().set_oColExtent(oExtendDef.get(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_DIFFERENZ.key()));
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key()).EXT().set_oColExtent(oExtendDef.get(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key()));
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_RECH_GUT_POS.key()).EXT().set_oColExtent(oExtendDef.get(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_RECH_GUT_POS.key()));
		
		this.get__Comp("ID_VPOS_KON").EXT().set_oColExtent(oExtendDef.get("ID_VPOS_KON"));
		this.get__Comp("K_ID_VKOPF_KON").EXT().set_oColExtent(oExtendDef.get("K_ID_VKOPF_KON"));
		this.get__Comp("AD_ID_ADRESSE").EXT().set_oColExtent(oExtendDef.get("AD_ID_ADRESSE"));
		
		
		//tooltips in die ueberschrift
		this.get__Comp(BSKC__CONST.HASH_MENGE_SUMME_GEGENSEITE).EXT().set_ToolTipStringForListHeader(new MyE2_String("Menge aller Kontrakt-Zuordnungen zu dieser Kontraktposition"));
		this.get__Comp(BSKC__CONST.HASH_MENGE_SUMME_LAGER).EXT().set_ToolTipStringForListHeader(new MyE2_String("Menge aller Lager-Zuordnungen zu dieser Kontraktposition"));
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_LAGER_IN_FUHRE.key()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Menge der Lagerzuordnungen, die bereits in einer Fuhre stehen (Lade/Ablademenge oder wenn diese noch leer ist, Planmenge)"));
		
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_ZUORDNUNG.key()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Menge aller Lager- und Kontrakt-Zuordnungen zu dieser Kontraktposition"));
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_MENGE_SUMME_DIFFERENZ.key()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Menge aller Lager- und Kontrakt-Zuordnungen zu dieser Kontraktposition (Differenz zur Erfüllung)"));
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_PLANMENGE.key()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Menge zu dieser Kontraktposition, die in einer Fuhre oder Fuhrenort auftaucht (Lade/Ablade- oder Planmenge falls leer)"));
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_EK_VK_FUHREN_REALMENGE.key()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Menge zu dieser Kontraktposition, die in einer Fuhre oder Fuhrenort auftaucht NUR LADE/ABLADE-MENGE, Planmengen werden nicht berücksichtigt"));
		this.get__Comp(BSKC__CONST.FILTERCOLUMN.HASH_R_SUMME_RECH_GUT_POS.key()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Menge zu dieser Kontraktposition, die bereits abgerechnet wurde"));
		
		
		if (S.isEmpty(ID_VPOS_KON_Gegen))     //dann ists die hauptliste
		{
			// extender
			this.set_List_EXPANDER_4_ComponentMAP(new BSKC_List_EXPANDER_4_ComponentMAP_Gengen_Kontrakt(oModulContainer,cEK_VK));
			// den header des extenders gleich vorbesetzen
			// in die extender-spalte einen header-button vorsehen
			//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
			MyE2IF__Component   oExtender = this.get__Comp("EXTENDER");
			oExtender.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oModulContainer.get_oNavigationList()));
		}

		this.set_oSubQueryAgent(new BSKC_MarkerSubQueryAgent(cEK_VK));
	
		
	}

	
	private GridLayoutData 	get_GRID_LeftHauptListe()				
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}

	private GridLayoutData 	get_GRID_RightHauptListe()				
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.RIGHT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorBase());
		return oLayout;	
	}

	private GridLayoutData 	get_GRID_LeftAusklappListe()				
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.LEFT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorLLight());
		return oLayout;	
	}

	private GridLayoutData 	get_GRID_RightAusklappListe()				
	{	
		GridLayoutData oLayout = new GridLayoutData();
		bibALL.set_LayoutParts(oLayout, new Alignment(Alignment.RIGHT,Alignment.TOP), E2_INSETS.I_3_1_6_1, new E2_ColorLLight());
		return oLayout;	
	}


	public String get_cID_VPOS_KON__Gegen()
	{
		return cID_VPOS_KON__Gegen;
	}

	
	
}
