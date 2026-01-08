package panter.gmbh.Echo2.POI_TOOLS;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Vector;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Component;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_Download;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentRow;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.TestingDate;


public class MyHSSFWorkBookForNavigationList extends HSSFWorkbook
{
	
	public static int   			ZEILEN_PRO_SHEET 	= 30000;
	
	protected myTempFile  			oTempfile 			= null;
	protected String				cDownloadName 		= null;
	protected E2_NavigationList		oNaviList 			= null;
	
	protected E2_ComponentMAP		oComponentMAP 		= null;
	protected MyDBToolBox			oDB 				= bibALL.get_myDBToolBox();

	protected int  					iMaxZeilenInGroup 	= 1;
	
	
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}

	
	public MyHSSFWorkBookForNavigationList(myTempFile tempfile, String downloadName, E2_NavigationList naviList) throws myException
	{
		super();
		try
		{
			this.oTempfile = tempfile;
			this.cDownloadName = downloadName;
			this.oNaviList = naviList;
			
			this.oComponentMAP = (E2_ComponentMAP)this.oNaviList.get_oComponentMAP__REF().get_Copy(null);
			
			
			// zuerst feststellen, wieviele zeilen und spalten die "datenzeile" in der componentMap hat
			for (int i=0;i<this.oComponentMAP.get_vComponentHashKeys().size();i++)
			{
				Component oComp = (Component) this.oComponentMAP.get(this.oComponentMAP.get_vComponentHashKeys().get(i));
				
				if (oComp instanceof MyE2_DB_MultiComponentColumn)
				{
					int iHelp = (int)((MyE2_DB_MultiComponentColumn)oComp).get_vComponents().size();
					if (iMaxZeilenInGroup<iHelp)
						iMaxZeilenInGroup=iHelp;
				}
			}
			
			
			// festellen, wieviele sheets benoetigt werden
			int 	iNumberDataRows = this.oNaviList.get_vectorSegmentation().size();
			long  	iNumberSheetRows = iNumberDataRows*iMaxZeilenInGroup;
			
			int iNumberOfSheets = 1;    // mindestens 1
			
			if (iNumberSheetRows > MyHSSFWorkBookForNavigationList.ZEILEN_PRO_SHEET)
				iNumberOfSheets += (iNumberSheetRows/MyHSSFWorkBookForNavigationList.ZEILEN_PRO_SHEET);
			
			
			// jetzt die sheets erzeugen:
			HSSFSheet[] oSheetArray = new HSSFSheet[iNumberOfSheets];
			
			for (int i=0;i<iNumberOfSheets;i++)
				oSheetArray[i]=this.createSheet("TAB"+i);
			
			
			// wieviele segmentvectoren gibt es
			int iNumberOfSegments = this.oNaviList.get_vectorSegmentation().get_iZahlSegmente();
			
			boolean 	bHeadlineIsWriten = false;
			int 		iActualSheet = 0;
			int 		iAbsoluteRowNumber = 0;
			
			HSSFSheet	oSheet = oSheetArray[iActualSheet++];
			
			for (int i=0;i<iNumberOfSegments;i++)
			{
				Vector<String> vIDs = this.oNaviList.get_vectorSegmentation().get_vSegment(i);
				Vector<E2_ComponentMAP> vComponents = this.BUILD_ComponentMAP_Vector_from_ActualSegment(vIDs);
				
				if (!bHeadlineIsWriten)
				{
					bHeadlineIsWriten = true;
					iAbsoluteRowNumber = this.makeTitle(oSheet,iAbsoluteRowNumber,this.oComponentMAP);
				}
				for (int l=0;l<vComponents.size();l++)
				{
					E2_ComponentMAP oMap = (E2_ComponentMAP)vComponents.get(l);
					iAbsoluteRowNumber = this.makeContent(oSheet,iAbsoluteRowNumber,oMap);
				}
				
				
				if (iAbsoluteRowNumber>MyHSSFWorkBookForNavigationList.ZEILEN_PRO_SHEET)
				{
					// naechstes sheet
					oSheet = oSheetArray[iActualSheet++];
					iAbsoluteRowNumber = 0;
					bHeadlineIsWriten = false;
				}
				
				
			}
			
			
			FileOutputStream oFileOut = new FileOutputStream(this.oTempfile.getFile());
			this.write(oFileOut);
			oFileOut.close();
			
			E2_Download oDown = new E2_Download();
			if (!bibALL.isEmpty(this.cDownloadName))
			{	
				if (!this.cDownloadName.toUpperCase().endsWith(".XLS"))
					this.cDownloadName+=".XLS";
				oDown.starteFileDownload(this.oTempfile.getFileName(),this.cDownloadName,JasperFileDef.MIMETYP_XLS);
			}
			else
				oDown.starteFileDownload(this.oTempfile.getFileName(),JasperFileDef.MIMETYP_XLS);
			
	
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("MyHSSFWorkBookForNavigationList:Error building XLS-File: "+ex.getLocalizedMessage());
		}
		
		
		
	}
	
	
	
	
	/**
	 * baut die liste auf mit dem actuell vorhandenen segment neu auf
	 */
	protected Vector<E2_ComponentMAP> BUILD_ComponentMAP_Vector_from_ActualSegment(Vector<String> vIDs) throws myException, SQLException
	{
		String cQuerySegment = this.oComponentMAP.get_oSQLFieldMAP().get_CompleteSQLQueryFor_SEGMENT(vIDs);
		Vector<E2_ComponentMAP> vRueck = new Vector<E2_ComponentMAP>();

		MyDBResultSet oRS = this.oDB.OpenResultSet(cQuerySegment);
		
		if (oRS.RS != null)
		{
			/*
			 * alle E2_ComponentMAP - objekte bauen und fuellen
			 */
			try 
			{
				while (oRS.RS.next())
				{
					E2_ComponentMAP oMap = (E2_ComponentMAP)this.oComponentMAP.get_Copy(null);

					//2015-09-29: hier der komponente die info mitgeben, welche navilist zu der komponente gehoert, damit die
					//            komponenten, die nur gebaut werden, wenn sie eingeblendet sind, die info haben, ob eingeblendet oder nicht !
					oMap.set_oNavigationList_This_Belongs_to(this.oNaviList);
					//ende der aenderung 2015-09-29:

					
					oMap.fill_ComponentMapFromDB(oRS.RS,null, E2_ComponentMAP.STATUS_VIEW,false,null);
					
					
					
					//2012-12-13: settings-collection anwenden
					
					if (oMap.get_hmInteractiv_settings_validation().size() > 0){
						oMap.get_hmInteractiv_settings_validation().execute_ComponentMAP_interactiv_settings(oMap);
					}
					
					
					vRueck.add(oMap);
				}
			} 
			catch (SQLException e) 
			{
				oRS.Close();
				throw e;
			}
			oRS.Close();
			
		}
		return vRueck;

	}
	

	/*
	 * ueberschriften definieren
	 */
	@SuppressWarnings("deprecation")
	protected int makeTitle(HSSFSheet ActualSheet, int iAbsoluteRowNumber, E2_ComponentMAP oMap)
	{

		int iiAbsoluteRowNumber = iAbsoluteRowNumber;
		
		
		// den zeilenblock erzeugen fuer die aktuelle DatenRow
		
		HSSFRow[] oRows = new HSSFRow[this.iMaxZeilenInGroup];
		for (int i=0;i<this.iMaxZeilenInGroup;i++)
			oRows[i]=ActualSheet.createRow(iiAbsoluteRowNumber++);
		
		
		short iSpalte = 0;
	
		
		// alle potentiellen zeilen aus den mehrfach-Spalten durchgehen und fuellen
		for (int i=0;i<oMap.get_vComponentHashKeys().size();i++)
		{
			MyE2IF__Component oComp = (MyE2IF__Component) oMap.get(oMap.get_vComponentHashKeys().get(i));
			if (oComp.EXT().get_bIsVisibleInList())
			{
				if (oComp instanceof MyE2_DB_MultiComponentColumn)
				{
					MyE2_DB_MultiComponentColumn MCCol = (MyE2_DB_MultiComponentColumn)  oComp;
					for (int k=0;k<MCCol.get_vComponents().size();k++)
					{
						HSSFCell  oCell = oRows[k].createCell(iSpalte);
						oCell.setCellValue(this.build_HeaderText((Component)MCCol.get_vComponents().get(k)));
					}
				}
				else if (oComp instanceof MyE2_DB_MultiComponentRow)
				{
					// mehrfach-ROW wird in eine zelle geschrieben
					HSSFCell  oCell = oRows[0].createCell(iSpalte);
					MyE2_DB_MultiComponentRow MCRow = (MyE2_DB_MultiComponentRow)  oComp;

					String cHelp = "";
					for (int k=0;k<MCRow.get_vComponents().size();k++)
					{
						cHelp += this.build_HeaderText((Component)MCRow.get_vComponents().get(k));
					}
					oCell.setCellValue(cHelp);
				}
				else
				{
					HSSFCell  oCell = oRows[0].createCell(iSpalte);
					String cHelp = this.build_HeaderText((Component)oComp);
					oCell.setCellValue(cHelp);
				}
				
				iSpalte++;
			}
		}
		return iiAbsoluteRowNumber;
	}
	
	
	
	
	/*
	 * inhalte definieren
	 */
	protected int makeContent(HSSFSheet ActualSheet, int iAbsoluteRowNumber, E2_ComponentMAP oMap)
	{

		int iiAbsoluteRowNumber = iAbsoluteRowNumber;

		HSSFCellStyle cellStyle = this.createCellStyle();
	    cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
	    
		
		// den zeilenblock erzeugen fuer die aktuelle DatenRow
		HSSFRow[] oRows = new HSSFRow[this.iMaxZeilenInGroup];
		for (int i=0;i<this.iMaxZeilenInGroup;i++)
			oRows[i]=ActualSheet.createRow(iiAbsoluteRowNumber++);
		
		int iSpalte = 0;
		
		// alle potentiellen zeilen aus den mehrfach-Spalten durchgehen und fuellen
		for (int i=0;i<oMap.get_vComponentHashKeys().size();i++)
		{
			MyE2IF__Component oComp = (MyE2IF__Component) oMap.get(oMap.get_vComponentHashKeys().get(i));
			if (oComp.EXT().get_bIsVisibleInList())
			{
				if (oComp instanceof MyE2_DB_MultiComponentColumn)
				{
					MyE2_DB_MultiComponentColumn MCCol = (MyE2_DB_MultiComponentColumn)  oComp;
					for (int k=0;k<MCCol.get_vComponents().size();k++)
					{
						HSSFCell  oCell = oRows[k].createCell(iSpalte);
						Object oHelp = this.build_Content((Component)MCCol.get_vComponents().get(k));
						if (oHelp instanceof String)
							oCell.setCellValue((String)oHelp);
						else if (oHelp instanceof DotFormatter)
							oCell.setCellValue(((DotFormatter)oHelp).getDoubleValue());
						else if (oHelp instanceof TestingDate)
						{
							oCell.setCellValue(((TestingDate)oHelp).get_date());
							oCell.setCellStyle(cellStyle);
						}
						else
							oCell.setCellValue("--ERROR--");
					}
				}
				else if (oComp instanceof MyE2_DB_MultiComponentRow)
				{
					// mehrfach-ROW wird in eine zelle geschrieben
					HSSFCell  oCell = oRows[0].createCell(iSpalte);
					MyE2_DB_MultiComponentRow MCRow = (MyE2_DB_MultiComponentRow)  oComp;

					String cHelp = "";
					for (int k=0;k<MCRow.get_vComponents().size();k++)
					{
						Object oHelp = this.build_Content((Component)MCRow.get_vComponents().get(k));
						if (oHelp instanceof String)
							cHelp+= (String)oHelp+" ";
						else if (oHelp instanceof DotFormatter)
							cHelp+= ((DotFormatter)oHelp).getStringFormated()+" ";
						else if (oHelp instanceof TestingDate)
							cHelp+= ((TestingDate)oHelp).get_FormatedDateString("dd.mm.yyyy");
						else
							cHelp+= "--ERROR-- ";
					}
					oCell.setCellValue(cHelp);
				}
				else if (oComp instanceof MyE2_Grid)           //2011-03-08: grid wird hier mitbehandelt, alles wird in eine zelle geschrieben
				{
					MyE2_Grid grid = (MyE2_Grid)  oComp;
					
					HSSFCell  oCell = oRows[0].createCell(iSpalte);
					
					String cValue4Cell = "";
					for (int k=0;k<grid.getComponents().length;k++)
					{
						String cWert = "";
						
						Object oHelp = this.build_Content((Component)grid.getComponents()[k]);
						if (oHelp instanceof String)
						{
							cWert= (String)oHelp;
						}
						else if (oHelp instanceof DotFormatter)
						{
							cWert= ((DotFormatter)oHelp).getStringFormated();
						}
						else if (oHelp instanceof TestingDate)
						{
							cWert = ((TestingDate)oHelp).get_FormatedDateString("dd.mm.yyyy");
						}
						else
						{
							oCell.setCellValue("--ERROR--");
						}
						if (S.isFull(cWert))
						{
							cValue4Cell += ("//"+cWert);
						}
					}
					cValue4Cell = cValue4Cell.trim();
					if (cValue4Cell.startsWith("//")) {cValue4Cell = cValue4Cell.substring(2);};
					if (cValue4Cell.endsWith("//")) {cValue4Cell = cValue4Cell.substring(0,cValue4Cell.length()-2);};
					
					oCell.setCellValue(cValue4Cell);
				}
				else
				{

					HSSFCell  oCell = oRows[0].createCell(iSpalte);

					Object oHelp = this.build_Content((Component)oComp);
					if (oHelp instanceof String)
						oCell.setCellValue((String)oHelp);
					else if (oHelp instanceof DotFormatter)
						oCell.setCellValue(((DotFormatter)oHelp).getDoubleValue());
					else if (oHelp instanceof TestingDate)
					{
						oCell.setCellValue(((TestingDate)oHelp).get_date());
						oCell.setCellStyle(cellStyle);
					}
					else
						oCell.setCellValue("--ERROR--");
				}
				
				iSpalte++;
			}
		}
		return iiAbsoluteRowNumber;
	}

	
	
	
	
	
	protected String build_HeaderText(Component oComp)
	{
		
		MyString cHelp = ((MyE2IF__Component)oComp).EXT().get_cList_or_Mask_Titel();
		if (cHelp == null) 
			return "--";
		else
			return cHelp.CTrans();

	}


	
	protected Object build_Content(Component oComp)
	{
		
		String cRueck = "";
		
		if (oComp instanceof MyE2_DB_Label)
		{
			MyE2_DB_Label oDBLabel = (MyE2_DB_Label)oComp;
			cRueck = oDBLabel.getText();
			try
			{
				if (oDBLabel.EXT_DB().get_oSQLField() != null &&  oDBLabel.EXT_DB().get_oSQLField().get_oFieldMetaDef()!=null)
				{
					MyMetaFieldDEF oMI = oDBLabel.EXT_DB().get_oSQLField().get_oFieldMetaDef();
					if (oMI.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
					{
						DotFormatter DF = new DotFormatter(cRueck,oMI.get_FieldDecimals(),Locale.GERMAN,true,3);
						if (DF.doFormat())
						{
							return DF;
						}
					}
					else if (oMI.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
					{
						TestingDate TD = new TestingDate(cRueck);
						if (TD.testing())
						{
							return TD;
						}
					}
					
				}
			}
			catch (myException ex) {}
		}
		else if (oComp instanceof MyE2_DB_TextField)
		{
			MyE2_DB_TextField oDBText = (MyE2_DB_TextField)oComp;
			cRueck = oDBText.getText();
			try
			{
				if (oDBText.EXT_DB().get_oSQLField() != null &&  oDBText.EXT_DB().get_oSQLField().get_oFieldMetaDef()!=null)
				{
					MyMetaFieldDEF oMI = oDBText.EXT_DB().get_oSQLField().get_oFieldMetaDef();
					if (oMI.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
					{
						DotFormatter DF = new DotFormatter(cRueck,oMI.get_FieldDecimals(),Locale.GERMAN,true,3);
						if (DF.doFormat())
						{
							return DF;
						}
					}
					else if (oMI.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
					{
						TestingDate TD = new TestingDate(cRueck);
						if (TD.testing())
						{
							return TD;
						}
					}
				}
			}
			catch (myException ex) {}
		}
		else if (oComp instanceof MyE2_Label)
		{
			cRueck = ((MyE2_Label)oComp).getText();
			
			//2011-02-28: bei standard-labels auch die pruefung auf zahlen machen
			DotFormatter DF = new DotFormatter(cRueck,3,Locale.GERMAN,true,3);
			if (DF.doFormat())
			{
				return DF;
			}
			
		}
		else if (oComp instanceof MyE2_TextArea)
		{
			cRueck = ((MyE2_TextArea)oComp).getText();

			//2011-02-28: bei standard-labels auch die pruefung auf zahlen machen
			DotFormatter DF = new DotFormatter(cRueck,3,Locale.GERMAN,true,3);
			if (DF.doFormat())
			{
				return DF;
			}

		}
		else if (oComp instanceof MyE2_TextField)
		{
			cRueck = ((MyE2_TextField)oComp).getText();

			//2011-02-28: bei standard-labels auch die pruefung auf zahlen machen
			DotFormatter DF = new DotFormatter(cRueck,3,Locale.GERMAN,true,3);
			if (DF.doFormat())
			{
				return DF;
			}

		}
		else if (oComp instanceof MyE2_CheckBox)
		{
			if (((MyE2_CheckBox)oComp).isSelected())
				cRueck = new MyE2_String("Ja").CTrans();
			else
				cRueck = new MyE2_String("Nein").CTrans();

		}
		//2015-04-20: selectfield auch einblenden
		else if (oComp instanceof MyE2_SelectField)
		{
			try {
				cRueck =((MyE2_SelectField)oComp).get_ActualView();
			} catch (myException e) {
				cRueck = "<error>";
				e.printStackTrace();
			}
		}
		//201-03-08: auch buttons in den export mitaufnehmen
		else if (oComp instanceof Button)
		{
			cRueck = ((Button)oComp).getText();

			//2011-02-28: bei standard-labels auch die pruefung auf zahlen machen
			DotFormatter DF = new DotFormatter(cRueck,3,Locale.GERMAN,true,3);
			if (DF.doFormat())
			{
				return DF;
			}
		}
		if (cRueck==null)
			cRueck = "";

		return cRueck;
	}

	
	
	
	
	
	
}
