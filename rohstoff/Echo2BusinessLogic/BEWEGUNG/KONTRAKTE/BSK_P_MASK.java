package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import echopointng.Separator;


/*
 * enthaelt die definition der maske
 */
public class BSK_P_MASK extends MyE2_Column implements IF_BaseComponent4Mask	
{
	private BSK_P_MASK__ComponentMAP oMaskMAP = null;

	private int   iBreiteSpalte1= 160;

	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BSK_P_MASK(BSK_P_MASK__ComponentMAP maskMAP,BS__SETTING oSetting) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		this.oMaskMAP = maskMAP;
		
		E2_MaskFiller oFill = new E2_MaskFiller(this.oMaskMAP,this.oMaskMAP.get_oVPOS_KON_TRAKT_ComponentMAP(),null,E2_INSETS.I_0_2_10_2,E2_INSETS.I_0_2_10_2, new Alignment(Alignment.LEFT,Alignment.CENTER));
		
		/*
		 * grid seite 1
		 */
		MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
		oTabbedPane.set_bAutoHeight(true);

		
		MyE2_Grid oGrid1 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oTabbedPane.add_Tabb(new MyE2_String("Positionsangaben"),oGrid1);
		oGrid1.setColumnWidth(0, new Extent(this.iBreiteSpalte1));
		
		MyE2_Grid oGrid2 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oTabbedPane.add_Tabb(new MyE2_String("Zusatzinfos"),oGrid2);

		MyE2_Grid oGrid3 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oTabbedPane.add_Tabb(new MyE2_String("Liefertermine"),oGrid3);
		
			
		MyE2_Grid oGrid4 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oTabbedPane.add_Tabb(new MyE2_String("Änderungen (Menge/Preis)"),oGrid4);
		
		MyE2_Row oGroupIDs = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oGroupIDs.add(maskMAP.get_Comp("ID_VKOPF_KON"),E2_INSETS.I_0_0_10_0);
		oGroupIDs.add(maskMAP.get_Comp("ID_VPOS_KON"),E2_INSETS.I_0_0_10_0);
		oGroupIDs.add(new MyE2_Label(new MyE2_String("Positionsnummer:")),E2_INSETS.I_30_0_0_0);
		oGroupIDs.add(maskMAP.get_Comp("POSITIONSNUMMER"),E2_INSETS.I_5_0_0_0);
		oGroupIDs.add(new MyE2_Label(new MyE2_String("Typ:")),E2_INSETS.I_30_0_0_0);
		oGroupIDs.add(maskMAP.get_Comp("POSITION_TYP"),E2_INSETS.I_5_0_0_0);
		//2016-02-29: abgeschlossen anzeigen
		oGroupIDs.add(new MyE2_Label(new MyE2_String("Abgeschlossen:")),E2_INSETS.I_30_0_0_0);
		oGroupIDs.add(this.oMaskMAP.get_oVPOS_KON_TRAKT_ComponentMAP().get_Comp(VPOS_KON_TRAKT.abgeschlossen.fn()),E2_INSETS.I(5,0,0,0));
		
		oGrid1.add(new MyE2_Label(new MyE2_String("IDs (Kopf/Pos)")),E2_INSETS.I_0_2_10_2);
		oGrid1.add(oGroupIDs,6,E2_INSETS.I_0_2_10_2);

		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		
		oFill.add_Line(oGrid1, "#Gültigkeit", 1, "GUELTIG_VON|#|#bis|#|GUELTIG_BIS|"+
				BSK__CONST.HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH+"|#|"+
				BSK__CONST.HASH_KEY_POSITION_MASK_SET_NEXT_MONTH+"|#  |#Lieferant|"+BSK__CONST.BUTTON_TO_LOAD_ADRESSE, 6);
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		
		MyE2_Row oGroupSorte = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oGroupSorte.add(maskMAP.get_Comp("ID_ARTIKEL_BEZ"),E2_INSETS.I_0_0_10_0);
		oGroupSorte.add(new MyE2_Label("("),E2_INSETS.I_0_0_2_0);
		oGroupSorte.add(maskMAP.get_Comp("ANR1"),E2_INSETS.I_0_0_2_0);
		oGroupSorte.add(new MyE2_Label(" / "));
		oGroupSorte.add(maskMAP.get_Comp("ANR2"),E2_INSETS.I_0_0_2_0);
		oGroupSorte.add(new MyE2_Label(")"),E2_INSETS.I_0_0_10_0);
		oGroupSorte.add(new MyE2_Label(new MyE2_String("ID Artikel:")),E2_INSETS.I_0_0_2_0);
		oGroupSorte.add(maskMAP.get_Comp("ID_ARTIKEL"));

		oGrid1.add(new MyE2_Label(new MyE2_String("Sorte")),E2_INSETS.I_0_2_10_2);
		oGrid1.add(oGroupSorte,6,E2_INSETS.I_0_2_10_2);
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));

		
		//zusatzgrid fuer die plazierung der schalter ueberliefert ok und mengentoleranz unterhalt des mengefeldes (links neben artbez2)
		int[] iBreiten = {60,20};
		MyE2_Grid oGridMengenZusatz = new MyE2_Grid(iBreiten, MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		Insets oIN = new Insets(2,5,2,5);
		oGridMengenZusatz.add_raw(new MyE2_Label(new MyE2_String("Überlieferung erlaubt ?"), new E2_FontPlain(), true), LayoutDataFactory.get_GridLayoutGridLeftTOP(oIN));
		oGridMengenZusatz.add_raw(this.oMaskMAP.get_oVPOS_KON_TRAKT_ComponentMAP().get_Comp("UEBERLIEFER_OK"), LayoutDataFactory.get_GridLayoutGridRight_MID(oIN));
		oGridMengenZusatz.add_raw(new MyE2_Label(new MyE2_String("Toleranz Mengen in %"), new E2_FontPlain(), true), LayoutDataFactory.get_GridLayoutGridLeftTOP(oIN));
		oGridMengenZusatz.add_raw(this.oMaskMAP.get_Comp("MENGEN_TOLERANZ_PROZENT"), LayoutDataFactory.get_GridLayoutGridRight_MID(oIN));
		
		//den inneren block einfuegen
		oGrid1.add(BS__CompMap_FieldMAP_Gemeinsamkeiten.get_MASK_GRID_Preise(maskMAP,"KON", new Integer(this.iBreiteSpalte1-2),oGridMengenZusatz),6,E2_INSETS.I_0_0_2_0);

		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));

		MyE2_Row oGroupEinheiten = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oGroupEinheiten.add(new MyE2_Label("Menge:"),E2_INSETS.I_0_0_2_0);
		oGroupEinheiten.add(maskMAP.get_Comp("EINHEITKURZ"),E2_INSETS.I_0_0_10_0);
		oGroupEinheiten.add(new MyE2_Label("Mengen-Divisor:"),E2_INSETS.I_0_0_2_0);
		oGroupEinheiten.add(maskMAP.get_Comp("MENGENDIVISOR"),E2_INSETS.I_0_0_10_0);
		oGroupEinheiten.add(new MyE2_Label("Preiseinheit:"),E2_INSETS.I_0_0_2_0);
		oGroupEinheiten.add(maskMAP.get_Comp("EINHEIT_PREIS_KURZ"),E2_INSETS.I_0_0_20_0);

		oGroupEinheiten.add(new MyE2_Label("Steuersatz:"),E2_INSETS.I_0_0_2_0);
		oGroupEinheiten.add(maskMAP.get_Comp("STEUERSATZ"));
		
		
		oGrid1.add(new MyE2_Label(new MyE2_String("Einheiten")),E2_INSETS.I_0_2_10_2);
		oGrid1.add(oGroupEinheiten,6,E2_INSETS.I_0_2_10_2);
		
//		oFill.add_Line(oGrid1,new MyE2_String("Steuersatz "),1,			"STEUERSATZ",2);
		oFill.add_Line(oGrid1,new MyE2_String("Lieferbed. "),1,			"LIEFERBEDINGUNGEN",2);
		oFill.add_Line(oGrid1,"#Lieferort",1,"LIEFERORT|"+BSK__CONST.BUTTON_TO_TAKE_LIEFERORTE,6);
		oFill.add_Line(oGrid1,"#Lieferzeit",1,"LIEFERZEIT",6);
//		oFill.add_Line(oGrid1,"#Überlieferung erlaubt ?",1,"UEBERLIEFER_OK|# |#<L113L>Bestellnummer extern:|#|BESTELLNUMMER",6);
		oFill.add_Line(oGrid1,"#Bestellnummer extern",1,"BESTELLNUMMER|#<L40L>Transportmittel|TRANSPORTMITTEL",6);
		
		//2020-02-12: setzen von schalter, ob eine verlaengerte fakturierungsfrist benutzt wird
		//            nur in vk-kontrakt
//		if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_VK_KONTRAKT)) {
		oFill.add_Line(oGrid1,"#Verl.Fakturierungsfrist",1,VPOS_KON_TRAKT.verlaengerte_fakt_frist.fn(),6);
//		}
		
		
		oFill.add_Line(oGrid1,new MyE2_String("Zahlungsbed."),1,		"ID_ZAHLUNGSBEDINGUNGEN|#   |ZAHLUNGSBEDINGUNGEN|# |ZAHLTAGE|# |FIXMONAT|# |FIXTAG|# |SKONTO_PROZENT",2);

		
		
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		
		if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
		{		
			oGrid1.add(new MyE2_Label(new MyE2_String("Lieferungen VOM Lager")),new ownGridLayoutNormal(1,E2_INSETS.I_0_10_10_10)); 
			oGrid1.add(maskMAP.get_Comp(BSK__CONST.HASH_KEY_DAUGHTERTABLE_LAGERLIEFERUNG),new ownGridLayoutNormal(6,E2_INSETS.I_0_2_10_2)); 
		}
		else
		{
			oGrid1.add(new MyE2_Label(new MyE2_String("Lieferungen ZUM Lager")),new ownGridLayoutNormal(1,E2_INSETS.I_0_10_10_10)); 
			oGrid1.add(maskMAP.get_Comp(BSK__CONST.HASH_KEY_DAUGHTERTABLE_LAGERLIEFERUNG),new ownGridLayoutNormal(6,E2_INSETS.I_0_2_10_2)); 
		}
		

		// tab nummer 2
		
//		oFill.add_Line(oGrid2,"#Transportmittel",1,"TRANSPORTMITTEL",6);
		oFill.add_Line(oGrid2,"#Bemerkung intern",1,"BEMERKUNG_INTERN",6);
		oFill.add_Line(oGrid2,"#Bemerkung für Beleg",1,"BEMERKUNG_EXTERN",6);
		
		oFill.add_Line(oGrid2,"#Fixierungsbedingungen",1,"FIXIERUNGSBEDINGUNGEN",6);
		
		
		//oFill.add_Line(oGrid2,"#Mengentoleranz in %",1,"MENGEN_TOLERANZ_PROZENT",6);
		
		oGrid3.add(new MyE2_Label(new MyE2_String("Liefertermine")),new ownGridLayoutNormal(7,E2_INSETS.I_0_2_10_2)); 
		oGrid3.add(maskMAP.get_Comp(BSK__CONST.HASH_KEY_DAUGHTERTABLE_LIEFERTERMINE),new ownGridLayoutNormal(7,E2_INSETS.I_0_2_10_2)); 
		
		
		
		oGrid4.add(new MyE2_Label(new MyE2_String("Änderungen von Mengen oder Preisen")),new ownGridLayoutNormal(7,E2_INSETS.I_0_2_10_2)); 
		oGrid4.add(maskMAP.get_Comp(BSK__CONST.HASH_KEY_DAUGHTERTABLE_AENDERUNGSPROTOKOLL),new ownGridLayoutNormal(7,E2_INSETS.I_0_2_10_2)); 

		//2013-09-25: die infozeile des kontraktkopfs einblenden
		int[] iBreiteInfo = {120,50,20,150,80,150,80};
		MyE2_Grid oGrid4Infos = new MyE2_Grid(iBreiteInfo, MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		GridLayoutData oGL = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,5,2), new E2_ColorDDark(), 1, 1);
		GridLayoutData oGC = MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(2,2,5,2), new E2_ColorDDark(), 1);
		oGrid4Infos.add(new MyE2_Label(new MyE2_String("Positionsinformation:"),new E2_FontBold()),	oGL);
		oGrid4Infos.add(maskMAP.get_Comp(BSK__CONST.HASHKEY_VK_INFO_BUCHUNGNR),							oGL);
		oGrid4Infos.add(maskMAP.get_Comp(BSK__CONST.HASHKEY_VK_INFO_BUCHUNGPOSNR),						oGC);
		oGrid4Infos.add(maskMAP.get_Comp(BSK__CONST.HASHKEY_VK_INFO_FIRMA),								oGL);
		oGrid4Infos.add(maskMAP.get_Comp(BSK__CONST.HASHKEY_VK_INFO_MENGE),								oGC);
		oGrid4Infos.add(maskMAP.get_Comp(BSK__CONST.HASHKEY_VK_INFO_SORTE),								oGL);
		oGrid4Infos.add(maskMAP.get_Comp(BSK__CONST.HASHKEY_VK_INFO_PREIS),								oGC);
		
		
		this.add(oGrid4Infos,E2_INSETS.I(2,0,0,1));
		this.add(oTabbedPane,E2_INSETS.I_0_0_0_0);
		
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
		this.vMaskGrids.add(oGrid3);
		this.vMaskGrids.add(oGrid4);
		
	}
	
	
	private class ownGridLayoutNormal extends GridLayoutData
	{
		public ownGridLayoutNormal(int iColSpan, Insets oInsets)
		{
			super();
						
			if (oInsets != null)
				this.setInsets(oInsets);
			
			if (iColSpan>1)
				this.setColumnSpan(iColSpan);
			
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		}
		
	}


	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}

	
	
}
