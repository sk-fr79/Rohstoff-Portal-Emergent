/**
 * 
 */
package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;


import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;


public class AS_MASK extends MyE2_Column implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public AS_MASK(AS_MASK_ComponentMAP	  oE2_MAP_ADRESSE) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
		oTabbedPane.set_bAutoHeight(true);

		
		MyE2_Grid		oGridPage00	= new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		MyE2_Grid		oGridPage01A	= new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		MyE2_Grid		oGridPage02	= new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		//2013-06-21: erweiterung der tabelle mit den neuen codes Anhang7 3A und 3B 
		MyE2_Grid		oGridPage01	= new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER());
		
		this.vMaskGrids.add(oGridPage00);
		this.vMaskGrids.add(oGridPage01);
		this.vMaskGrids.add(oGridPage01A);
		this.vMaskGrids.add(oGridPage02);
		
		
		oTabbedPane.add_Tabb(new MyE2_String("Artikel-Angaben"),					oGridPage00, new tabbActionAgent(oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Nummern-Codes"),						oGridPage01, new tabbActionAgent(oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Kundenspezifische Bezeichnungen"),	oGridPage01A, new tabbActionAgent(oE2_MAP_ADRESSE));
		oTabbedPane.add_Tabb(new MyE2_String("Datenblätter"),						oGridPage02, new tabbActionAgent(oE2_MAP_ADRESSE));
	
		this.add(oE2_MAP_ADRESSE.get_Comp(AS___CONST.HASH_KEY_MASK_INFO_FIELD), E2_INSETS.I_2_2_2_2);
		
		this.add(oTabbedPane);
		
		//oGridPage00.setColumnWidth(0,new Extent(250));
		oGridPage01.setColumnWidth(0,new Extent(25,Extent.PERCENT));
		
		
		E2_MaskFiller oFiller = new E2_MaskFiller(oE2_MAP_ADRESSE,null,null,E2_INSETS.I_5_1_5_0,E2_INSETS.I_5_1_5_0, null);
		
		// Tabreiter artikelhautoseite
		int[] iRahmen = {70,60,120,60,120,60};
		MyE2_Grid oGridRahmen1 = new MyE2_Grid(iRahmen,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		oGridRahmen1.add(new MyE2_Label(new MyE2_String("AKTIV"), new E2_FontBold(2)), E2_INSETS.I(2,2,2,2));
		oGridRahmen1.add(oE2_MAP_ADRESSE.get_Comp(_DB.ARTIKEL$AKTIV), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,2,2,2)));
		oGridRahmen1.add(new MyE2_Label(new MyE2_String("GEFAHRGUT"), new E2_FontBold(2)), E2_INSETS.I(2,2,2,2));
		oGridRahmen1.add(oE2_MAP_ADRESSE.get_Comp(_DB.ARTIKEL$GEFAHRGUT), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,2,2,2)));
		oGridRahmen1.add(new MyE2_Label(new MyE2_String("LEERGUT"), new E2_FontBold(2)), E2_INSETS.I(2,2,2,2));
		oGridRahmen1.add(oE2_MAP_ADRESSE.get_Comp(_DB.ARTIKEL$IST_LEERGUT), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,2,2,2)));
		
		// Tabreiter artikelhautoseite
		MyE2_Grid oGridRahmen2 = new MyE2_Grid(iRahmen,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		oGridRahmen2.add(new MyE2_Label(new MyE2_String("Produkt"), new E2_FontBold(2)), E2_INSETS.I(2,2,2,2));
		oGridRahmen2.add(oE2_MAP_ADRESSE.get_Comp(_DB.ARTIKEL$IST_PRODUKT), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,2,2,2)));
		oGridRahmen2.add(new MyE2_Label(new MyE2_String("Dienstleistung"), new E2_FontBold(2)), E2_INSETS.I(2,2,2,2));
		oGridRahmen2.add(oE2_MAP_ADRESSE.get_Comp(_DB.ARTIKEL$DIENSTLEISTUNG), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,2,2,2)));
		oGridRahmen2.add(new MyE2_Label(new MyE2_String("End of Waste"), new E2_FontBold(2)), E2_INSETS.I(2,2,2,2));
		oGridRahmen2.add(oE2_MAP_ADRESSE.get_Comp(_DB.ARTIKEL$END_OF_WASTE), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I(2,2,2,2)));
	
		
		oGridPage00.setSize(3);
		oFiller.add_Line(oGridPage00,new MyE2_Label("Status"),1,oGridRahmen1,2);
		oFiller.add_Line(oGridPage00,new MyE2_Label("Typ"),1,oGridRahmen2,2);
		oGridPage00.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage00,new MyE2_String("Artikelgruppe"),					1,	"ID_ARTIKEL_GRUPPE|#  |",			2);
		//2014-09-09: Artikelgruppe fuer fibu
		oFiller.add_Line(oGridPage00,new MyE2_String("Artikelgruppe (für Fibu)"),		1,	"ID_ARTIKEL_GRUPPE_FIBU|#  |",			2);
		oGridPage00.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage00,new MyE2_String("ANR1"),							1,	"ANR1|#  |#ID-Artikel:|#  |ID_ARTIKEL",			2);
		oFiller.add_Line(oGridPage00,new MyE2_String("Artikelbez. 1 (intern)"),			1,	"ARTBEZ1",									2);
		oFiller.add_Line(oGridPage00,new MyE2_String("Artikelbez. 2 (intern)"),			1,	"ARTBEZ2",									2);
		oFiller.add_Line(oGridPage00,new MyE2_String("Einheit - Faktor - Preiseinheit"),1,	"ID_EINHEIT|MENGENDIVISOR|ID_EINHEIT_PREIS|#    |#Nachkommast. bei Berech.|GENAUIGKEIT_MENGEN",2);
		oGridPage00.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage00,new MyE2_String("AVV-Code Bar-Anlieferer"),		1,	"ID_EAK_CODE",								2);
		oGridPage00.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage00,new MyE2_String("AVV-Code Ausgang ",true,bibALL.get_RECORD_MANDANT().get_KURZNAME_cF_NN("Mandant"),false),		1,	"ID_EAK_CODE_EX_MANDANT",								2);
//		oGridPage00.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
//		oFiller.add_Line(oGridPage00,new MyE2_String("Artikeltyp"),						1,	"#Dienstleistung|DIENSTLEISTUNG|#  |#Produkt|IST_PRODUKT|#  |#End of Waste|END_OF_WASTE|",									2);
//		oGridPage00.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
//		oFiller.add_Line(oGridPage00,new MyE2_String("Leergut-Sorte"),					1,	_DB.ARTIKEL$IST_LEERGUT,								2);

		oGridPage00.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage00,new MyE2_String("Bemerkung (intern)"),1,			"BEMERKUNG_INTERN",								2);

		
		
		//Tabreiter Nummerncodes
		oGridPage01.setSize(3);
		oFiller.add_Line(oGridPage01,new MyE2_String("Basel-Code"),						1,	"BASEL_CODE|#  |"+AS___CONST.HASH_KEY_MASK_SEARCH_BASEL_CODE,								2);
		oFiller.add_Line(oGridPage01,new MyE2_String("Basel-Notiz"),					1,	"BASEL_NOTIZ",								2);
		oGridPage01.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage01,new MyE2_String("OECD-Code"),						1,	"EUCODE|#  |"+AS___CONST.HASH_KEY_MASK_SEARCH_OECD_CODE,									2);
		oFiller.add_Line(oGridPage01,new MyE2_String("OECD-Notiz"),						1,	"EUNOTIZ",									2);
		oGridPage01.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage01,new MyE2_String("Zolltarif-Nummer"),				1,	"ZOLLTARIFNR|#  |ID_ZOLLTARIFNUMMER",		2);
		oFiller.add_Line(oGridPage01,new MyE2_String("Zolltarif-Text"),					1,	"ZOLLTARIFNOTIZ",							2);

		//2013-06-21: neue Codes 
		oGridPage01.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage01,new MyE2_String("Anhang 7 (IIIA) Nummer"),			1,	_DB.ARTIKEL$ANHANG7_3A_CODE,				2);
		oFiller.add_Line(oGridPage01,new MyE2_String("Anhang 7 (IIIA) Text"),			1,	_DB.ARTIKEL$ANHANG7_3A_TEXT,				2);
		oGridPage01.add(new Separator(),3,E2_INSETS.I_0_1_0_0);
		oFiller.add_Line(oGridPage01,new MyE2_String("Anhang 7 (IIIB) Nummer"),			1,	_DB.ARTIKEL$ANHANG7_3B_CODE,				2);
		oFiller.add_Line(oGridPage01,new MyE2_String("Anhang 7 (IIIB) Text"),			1,	_DB.ARTIKEL$ANHANG7_3B_TEXT,				2);

		
		
		// Tabreiter Sortenbezeichner
		oGridPage01A.setSize(1);
		oFiller.add_Line(oGridPage01A,AS___CONST.HASHKEY_FULL_DAUGHTER_ARTBEZ,1);
		
		// Tabreiter Sortenbezeichner
		oGridPage02.setSize(1);
		oFiller.add_Line(oGridPage02,AS___CONST.HASH_KEY_MASK_DATENBLATT_TOCHTER,1);
		
		
		
		
		
	}
	
	/**
	 * actionAgents fuer die tab, die die Complexen tochter-felder aktivieren
	 */
	private class tabbActionAgent extends XX_ActionAgent
	{
		private E2_ComponentMAP 		oE2_MAP_ARTIKEL = null;
		
		public tabbActionAgent(E2_ComponentMAP oe2_map_artikel)
		{
			super();
			this.oE2_MAP_ARTIKEL = oe2_map_artikel;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				//2011-01-10: mehrere subquery-agents
				this.oE2_MAP_ARTIKEL.get_vComponentMapSubQueryAgents().fillComponents(oE2_MAP_ARTIKEL, null);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("FS_Mask:tabbActionAgent:doAction:Error setting maskinfo!",false));
			}
			
		}
		
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}

	
	
}