package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;


import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	private String[][] 		 	arrEigene = null;
	private MyE2_SelectField  	oSelEigene = null;
	
	private String[][] 		 	arrMsgType = null;
	private MyE2_SelectField	oSelMessageType = null;
	
	private MESSAGE_SelectField_Kategorie oSelKategorie = null;
	private MESSAGE_LIST_Selektor_Bestaetigt oSelBestaetigt = null;
	
//	private MyE2_CheckBox       oCBAbgeschlossen = null;
	private MyE2_CheckBox		oCBZukuenftige = null;
	private MyE2_CheckBox		oCBGeloeschte = null;
	private cDatum				oDatumVon = null;
	private cDatum				oDatumBis = null;
	
	
	public MESSAGE_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		// Wareneingang/ -Ausgang
		arrEigene = new String[][]{
				{new MyE2_String("-").CTrans(),""},
				{new MyE2_String("An mich").CTrans(),"jt_nachricht.ID_USER = " +  bibALL.get_ID_USER()},
				{new MyE2_String("Von mir").CTrans(),"jt_nachricht.ID_USER_SENDER = " +  bibALL.get_ID_USER() }};
		
		oSelEigene = new MyE2_SelectField(arrEigene, null, false,new Extent(200));
		oSelVector.add(new E2_ListSelectorStandard(this.oSelEigene, "#WERT#", null, null));		
		
		
		oSelMessageType = new MESSAGE_SelectField_MessageTyp(200);
		oSelVector.add(new E2_ListSelectorStandard(oSelMessageType, "#WERT#", null, null));		
		
		oSelKategorie = new MESSAGE_SelectField_Kategorie(200, true, null);
		oSelVector.add(new E2_ListSelectorStandard(oSelKategorie, "NVL(JT_NACHRICHT.ID_NACHRICHT_KATEGORIE,-1) = #WERT#", null,null));
		
		
//		oCBAbgeschlossen = new MyE2_CheckBox("Bestätigte Nachrichten anzeigen",oStyle);
//		oSelVector.add(new E2_ListSelectorStandard(oCBAbgeschlossen, "", "NVL( jt_nachricht.BESTAETIGT,'N') = 'N'") );
		oSelBestaetigt =  new MESSAGE_LIST_Selektor_Bestaetigt();
		oSelVector.add(oSelBestaetigt);
		
		oCBZukuenftige = new MyE2_CheckBox("Zukünftige Nachrichten anzeigen",oStyle);
		oSelVector.add(new E2_ListSelectorStandard(oCBZukuenftige, "", "jt_nachricht.AKTIV_AB <= to_date(to_char(sysdate,'YYYY-MM-DD'),'YYYY-MM-DD') ") );
		
		oCBGeloeschte = new MyE2_CheckBox("Gelöschte Nachrichten anzeigen", oStyle);
		oSelVector.add(new E2_ListSelectorStandard(oCBGeloeschte, "", "NVL(jt_nachricht.GELOESCHT,'N') = 'N' "));

		oDatumVon = new cDatum(null, true);
		oDatumBis = new cDatum(null, true);
		oSelVector.add(new E2_ListSelectorStandard(oDatumVon,"to_char(JT_NACHRICHT.AKTIV_AB,'yyyy-MM-dd') >= '#WERT#' ",null));
		oSelVector.add(new E2_ListSelectorStandard(oDatumBis,"to_char(JT_NACHRICHT.AKTIV_AB,'yyyy-MM-dd') <= '#WERT#' ",null));
		
		
		
		int[] iSpaltenGridMain 	= {100,300,100,100};
		MyE2_Grid oGridAussen = new MyE2_Grid(3,0);
		this.add(oGridAussen, E2_INSETS.I_4_4_4_4);

		MyE2_Grid oGridSpalte1 = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridAussen.add(oGridSpalte1,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);

		oGridSpalte1.setSize(2);
		oGridSpalte1.setColumnWidth(0, new Extent(iSpaltenGridMain[0]));
		//Z1
		oGridSpalte1.add(new MyE2_Label(new MyE2_String("Nachrichten:")),1,1,E2_INSETS.I_0_3_0_0,Alignment.ALIGN_TOP);
		oGridSpalte1.add(oSelEigene,3,E2_INSETS.I_0_0_5_0);
		// Z2
		oGridSpalte1.add(new MyE2_Label(new MyE2_String("Typ:")),1 ,E2_INSETS.I_0_0_0_0);
		oGridSpalte1.add(oSelMessageType,3, E2_INSETS.I_0_0_0_0);
		// Z3
		oGridSpalte1.add(new MyE2_Label(new MyE2_String("Kategorie:")),1 ,E2_INSETS.I_0_0_0_0);
		oGridSpalte1.add(oSelKategorie,3, E2_INSETS.I_0_0_0_0);
		
		
		MyE2_Grid oGridSpalte3  = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridAussen.add(oGridSpalte3,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		oGridSpalte3.setSize(4);
		oGridSpalte3.add(new MyE2_Label(new MyE2_String("Anzeige ab:")),1 ,E2_INSETS.I_0_0_0_0);
		oGridSpalte3.add(oDatumVon,1,E2_INSETS.I_5_0_0_0);
		oGridSpalte3.add(new MyE2_Label(new MyE2_String("bis:")),1 ,E2_INSETS.I_5_0_0_0);
		oGridSpalte3.add(oDatumBis,1,E2_INSETS.I_5_0_0_0);
		
		

		MyE2_Grid oGridSpalte2 = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridAussen.add(oGridSpalte2,1,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);
		
		oGridSpalte2.setSize(1);
		oGridSpalte2.setColumnWidth(0, new Extent(iSpaltenGridMain[1]));
		
//		oGridSpalte2.add(oCBAbgeschlossen,3,E2_INSETS.I_0_0_5_0);
		oGridSpalte2.add(oSelBestaetigt.get_oComponentForSelection(),3,E2_INSETS.I_0_0_5_0);
		oGridSpalte2.add(oCBZukuenftige,3,E2_INSETS.I_0_0_5_0);
		oGridSpalte2.add(oCBGeloeschte,3,E2_INSETS.I_0_0_5_0);
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	

	/**
	 * private Klasse für die Abfrage der von-Bis-Datumswerte
	 * @author manfred
	 * @date 13.07.2015
	 *
	 */
	private class cDatum extends MyE2_TextField_DatePOPUP_OWN
	{
		public cDatum( String cStartWert, boolean bEnabled4Edit) throws myException
		{
			super(cStartWert, 100,true,true);
			this.set_bEnabled_For_Edit(bEnabled4Edit);
			
		}
	}
}
