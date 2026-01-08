package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import java.util.ArrayList;
import java.util.HashMap;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BAM_IMPORT_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BAM_IMPORT_INFO;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class BAM_IMPORT_LIST_EXPANDER extends XX_List_EXPANDER_4_ComponentMAP 
{
	
	public  E2_ResourceIcon 						oIconLeer = E2_ResourceIcon.get_RI("leer.png");
	
	private BAM_IMPORT_LIST_BasicModuleContainer   	oBAM_IMPORT_LIST_BasicModuleConainer = 	null;
	private RECORD_BAM_IMPORT 						recBAMImport = 		null; 

	
	private MyE2_Row	  							oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	private String 		 							ID_ROW_Unformated_Parent = null;
	
	
	public BAM_IMPORT_LIST_EXPANDER(	BAM_IMPORT_LIST_BasicModuleContainer KopfModulcontainerList) throws myException
	{
		super(KopfModulcontainerList.get_oNaviList() );
//		this.set_iLeftColumnOffset(1);
		this.oBAM_IMPORT_LIST_BasicModuleConainer = KopfModulcontainerList;
		this.set_iLeftColumnOffset(3);
	}
	

	public Component get_ComponentDaughter(String cID_ROW_Unformated) throws myException 
	{
//		return null;    //hier wird die variante 2 benutzt
		this.ID_ROW_Unformated_Parent = cID_ROW_Unformated;
		this.FillComponent();
		return this.oRow;
	}

	
	/**
	 * Füllt erzeugt das Child-Grid 
	 * @throws myException
	 */
	private void FillComponent() throws myException
	{
		this.oRow.removeAll();
		
		// sicherheitsabfrage
		if (bibALL.isEmpty(this.ID_ROW_Unformated_Parent))
			return;
		
		recBAMImport = new RECORD_BAM_IMPORT(ID_ROW_Unformated_Parent);
		RECLIST_BAM_IMPORT_INFO 	recList_BAMImportInfo = this.recBAMImport.get_DOWN_RECORD_LIST_BAM_IMPORT_INFO_id_bam_import();
		
		int numCols = 6 ; //RECORD_BAM_IMPORT_INFO.HM_FIELDS.size();
		MyE2_Grid oGrid = new MyE2_Grid(numCols, MyE2_Grid.STYLE_GRID_DDARK_BORDER());      //NEU_09 grid um eine spalte erweitert wegen anzeige storno und gefahr
		oGrid.setBackground(new E2_ColorLLight());

		
		try
		{
			// Überschrift
			oGrid.add(this.makeLabel("id",				true, 	new GridLayoutLight_RIGHT(), false));
			oGrid.add(this.makeLabel("id_abzugsgrund",	true, 	new GridLayoutLight_RIGHT(), false));
			oGrid.add(this.makeLabel("Abzugsgrund",		true, 	new GridLayoutLight_RIGHT(), false));
			oGrid.add(this.makeLabel("Zusatztext",		true, 	new GridLayoutLight_RIGHT(), false));
			oGrid.add(this.makeLabel("Gewicht",			true, 	new GridLayoutLight_RIGHT(), false));
			oGrid.add(this.makeLabel("Anhaftend",		true, 	new GridLayoutLight_RIGHT(), false));
			
			for (int i=0;i<recList_BAMImportInfo.get_vKeyValues().size();i++)
			{
				RECORD_BAM_IMPORT_INFO oREC = recList_BAMImportInfo.get(i);
				
				String sAnhaftend = oREC.get_ANHAFTEND_cUF_NN("");
				switch (sAnhaftend) {
				case "Y":
					sAnhaftend = "Ja";
					break;
				case "N":
					sAnhaftend = "Nein";
					break;
				default:
					sAnhaftend = "?";
					break;
				}
				
				// zeile  ------------- 
				oGrid.add(this.makeLabel(oREC.get_ID_BAM_IMPORT_INFO_cUF_NN("?") ,false,  new GridLayoutLLight_RIGHT(),false));
				oGrid.add(this.makeLabel(oREC.get_ID_ABZUGSGRUND_cUF_NN("-"),		false,  new GridLayoutLLight_RIGHT(), false));
				oGrid.add(this.makeLabel(oREC.get_TEXT_cUF_NN("-"),		false,  new GridLayoutLLight_LEFT(), false));
				oGrid.add(this.makeLabel(oREC.get_ZUSATZINFOS_cUF_NN("-"),	false,  new GridLayoutLLight_RIGHT(), false));
				oGrid.add(this.makeLabel(oREC.get_GEWICHT_cF_NN("-"),	false,  new GridLayoutLLight_RIGHT(), false));
				oGrid.add(this.makeLabel(sAnhaftend, false, new GridLayoutLLight_RIGHT(), false));
				
			}
			
			// spaltenbreiten auseinanderziehen
//			oGrid.setColumnWidth(0, new Extent(20));
//			oGrid.setColumnWidth(1, new Extent(20));
//			oGrid.setColumnWidth(2, new Extent(60));
//			oGrid.setColumnWidth(3, new Extent(60));
//			oGrid.setColumnWidth(4, new Extent(60));
			
			

		}
		catch (myException ex)
		{
			ex.printStackTrace();
			oGrid.removeAll();
			oGrid.add(new MyE2_Label(new MyE2_String("E R R O R !!")),6,E2_INSETS.I_5_0_5_0);
		}

		this.oRow.add(oGrid,new Insets(0));
	}

	
	
	
	private MyE2_Label makeLabel(String cText,boolean bIsTitle, GridLayoutData oLayout, boolean bDeleted)
	{
		String ccText = "";
		
		if (cText.equals("###"))
			ccText = "-";
		else
			ccText = cText;
		
		MyE2_Label oLabRueck = null;
		
		E2_Font oDelFont = new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2);

		
		if (bIsTitle)
		{
			oLabRueck = new MyE2_Label(new MyE2_String(ccText));
			oLabRueck.setFont(new E2_FontItalic(-2));
		}
		else
		{
			oLabRueck = new MyE2_Label(ccText);
			if (bDeleted)
				oLabRueck.setFont(oDelFont);
			else
				oLabRueck.setFont(new E2_FontPlain(-2));
		}
		
		oLabRueck.setLayoutData(oLayout);
			
		return oLabRueck;
	}

	private class GridLayoutLight_LEFT extends GridLayoutData
	{
		public GridLayoutLight_LEFT()
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			this.setBackground(new E2_ColorLight());
		}
	}

	private class GridLayoutLight_RIGHT extends GridLayoutData
	{
		public GridLayoutLight_RIGHT()
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
			this.setBackground(new E2_ColorLight());
		}
	}
	
	private class GridLayoutLLight_LEFT extends GridLayoutData
	{
		public GridLayoutLLight_LEFT()
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			this.setBackground(new E2_ColorLLight());
		}
	}

	private class GridLayoutLLight_RIGHT extends GridLayoutData
	{
		public GridLayoutLLight_RIGHT()
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
			this.setBackground(new E2_ColorLLight());
		}
	}

	private class GridLayoutLLLight_LEFT extends GridLayoutData
	{
		public GridLayoutLLLight_LEFT(int iColSpan)
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			this.setBackground(new E2_ColorLLLight());
			this.setColumnSpan(iColSpan);
		}
	}

	
	
	@Override
	public ArrayList<HashMap<String, Component>>  get_ComponentListDaughter(String cID_ARTIKEL) throws myException
	{
		return null;
//		this.recBAMImport = new RECORD_BAM_IMPORT(cID_ARTIKEL);
//		
//		ArrayList<HashMap<String,Component>> arrayRueck = new ArrayList<HashMap<String,Component>>();
//		
//		
//		//jetzt die sortenbezeichnungen eintragen
//		RECLIST_BAM_IMPORT_INFO 	recList_BAMImportInfo = this.recBAMImport.get_DOWN_RECORD_LIST_BAM_IMPORT_INFO_id_bam_import();
//		
//		GridLayoutData glLeft = new GridLayoutData();
//		glLeft.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
//		glLeft.setInsets(new Insets(3,1,6,1));
//		
//		GridLayoutData glRight = new GridLayoutData();
//		glRight.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
//		glRight.setInsets(new Insets(3,1,6,1));
//		
//		GridLayoutData glCenter = new GridLayoutData();
//		glCenter.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
//		glCenter.setInsets(new Insets(3,1,6,1));
//		
//
//		GridLayoutData glLeftLast = new GridLayoutData();
//		glLeftLast.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
//		glLeftLast.setInsets(new Insets(3,1,6,10));
//
//		GridLayoutData glRightLast = new GridLayoutData();
//		glRightLast.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
//		glRightLast.setInsets(new Insets(3,1,6,10));
//
//		GridLayoutData glCenterLast = new GridLayoutData();
//		glCenterLast.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
//		glCenterLast.setInsets(new Insets(3,1,6,10));
//
//
//		if (recList_BAMImportInfo.get_vKeyValues().size()>0)
//		{
//			for (int i=0;i<recList_BAMImportInfo.get_vKeyValues().size();i++)
//			{
//				HashMap<String, Component> hmZeile = new HashMap<String, Component>();
//				arrayRueck.add(hmZeile);
//				
//				RECORD_BAM_IMPORT_INFO recBamInfo = recList_BAMImportInfo.get(i);
//				
//				boolean bLastRec = (i==(recList_BAMImportInfo.get_vKeyValues().size()-1));
//				
//				this.put_mit_Layout(hmZeile, "ID_BAM_IMPORT_INFO", 	new MyE2_Label(recBamInfo.get_ID_BAM_IMPORT_INFO_cUF_NN("<ID>")), 		bLastRec?glRightLast:glRight);
//				this.put_mit_Layout(hmZeile, "ID_BAM_IMPORT", 		new MyE2_Label(recBamInfo.get_ID_BAM_IMPORT_cUF_NN("<ID_BAM>")), 		bLastRec?glLeftLast:glLeft);
//				this.put_mit_Layout(hmZeile, "ID_AZUGSGRUND", 		new MyE2_Label(recBamInfo.get_ID_ABZUGSGRUND_cUF_NN("<Abzugsgrund>")), 	bLastRec?glLeftLast:glLeft);
//				this.put_mit_Layout(hmZeile, "TEXT",   				new MyE2_Label(recBamInfo.get_TEXT_cUF_NN("<text>")),					bLastRec?glCenterLast:glCenter);
//				this.put_mit_Layout(hmZeile, "ZUSATZINFOS",   		new MyE2_Label(recBamInfo.get_ZUSATZINFOS_cUF_NN("<->")),				bLastRec?glCenterLast:glCenter);
//				this.put_mit_Layout(hmZeile, "GEWICHT",   			new MyE2_Label(recBamInfo.get_GEWICHT_cF_NN("-")),						bLastRec?glCenterLast:glCenter);
//				
//			}
//		}
//		else
//		{
//			HashMap<String, Component> hmZeile = new HashMap<String, Component>();
//			arrayRueck.add(hmZeile);
//			this.put_mit_Layout(hmZeile, "ID_BAM_IMPORT_INFO", 	new MyE2_Label("--"), glRightLast);
//			this.put_mit_Layout(hmZeile, "ID_BAM_IMPORT", 		new MyE2_Label("--"), glRightLast);
//			this.put_mit_Layout(hmZeile, "ID_AZUGSGRUND", 		new MyE2_Label("--"), glRightLast);
//			this.put_mit_Layout(hmZeile, "TEXT",   				new MyE2_Label("--"), glRightLast);
//			this.put_mit_Layout(hmZeile, "ZUSATZINFOS",   		new MyE2_Label("--"), glRightLast);
//			this.put_mit_Layout(hmZeile, "GEWICHT",   			new MyE2_Label("--"), glRightLast);
//		}
//		
//		return arrayRueck;
	}
	
	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			BAM_IMPORT_LIST_EXPANDER oCopy = new BAM_IMPORT_LIST_EXPANDER(this.oBAM_IMPORT_LIST_BasicModuleConainer);
			return oCopy;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
	}


	public void refreshDaughterComponent() throws myException
	{
		this.get_oNavigationList()._REBUILD_ACTUAL_SITE(null);
	}
	

	
	private void put_mit_Layout(HashMap<String, Component> oHM, String cHashKey,Component oComp, LayoutData oLayout)
	{
		oComp.setLayoutData(oLayout);
		oHM.put(cHashKey, oComp);
	}
	
	
	


	public BAM_IMPORT_LIST_BasicModuleContainer get_oBAM_IMPORT_LIST_BasicModulContainer()
	{
		return oBAM_IMPORT_LIST_BasicModuleConainer;
	}


}
