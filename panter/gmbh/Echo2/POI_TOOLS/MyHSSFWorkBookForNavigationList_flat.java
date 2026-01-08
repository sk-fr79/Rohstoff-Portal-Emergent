package panter.gmbh.Echo2.POI_TOOLS;

import nextapp.echo2.app.Component;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2IF__ComponentContainer;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentRow;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myTempFile;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.TestingDate;

public class MyHSSFWorkBookForNavigationList_flat extends
		MyHSSFWorkBookForNavigationList {
	
	public MyHSSFWorkBookForNavigationList_flat(myTempFile tempfile, String downloadName, E2_NavigationList naviList) throws myException {
		super(tempfile,downloadName,naviList);
	}

	/*
	 * ueberschriften definieren
	 */
	
	protected int makeTitle(HSSFSheet ActualSheet, int iAbsoluteRowNumber, E2_ComponentMAP oMap)
	{

		int iiAbsoluteRowNumber = iAbsoluteRowNumber;
		
		
		// den zeilenblock erzeugen fuer die aktuelle DatenRow
		
//		HSSFRow[] oRows = new HSSFRow[this.iMaxZeilenInGroup];
//		for (int i=0;i<this.iMaxZeilenInGroup;i++)
//			oRows[i]=ActualSheet.createRow(iiAbsoluteRowNumber++);
		HSSFRow[] oRows = new HSSFRow[1];
		oRows[0] = ActualSheet.createRow(iiAbsoluteRowNumber++);
		
		
		int iSpalte = 0;
	
		
		// alle potentiellen zeilen aus den mehrfach-Spalten durchgehen und fuellen
		for (int i=0;i<oMap.get_vComponentHashKeys().size();i++)
		{
			MyE2IF__Component oComp = (MyE2IF__Component) oMap.get(oMap.get_vComponentHashKeys().get(i));
			if (oComp.EXT().get_bIsVisibleInList())
			{
				//2015-05-20: Alle MyE2IF__ComponentContainer wie MyE2_DB_MultiComponentColumn behandeln
				if (oComp instanceof MyE2IF__ComponentContainer)
				{
					MyE2IF__ComponentContainer MCCol = (MyE2IF__ComponentContainer)  oComp;
					for (int k=0;k<MCCol.get_vComponents().size();k++)
					{
						HSSFCell  oCell = oRows[0].createCell(iSpalte);
						oCell.setCellValue(this.build_HeaderText((Component)MCCol.get_vComponents().get(k)));
						iSpalte++;
					}
				}
				else
				{
					HSSFCell  oCell = oRows[0].createCell(iSpalte);
					String cHelp = this.build_HeaderText((Component)oComp);
					oCell.setCellValue(cHelp);
					iSpalte++;
				}
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
		
		HSSFRow[] oRows = new HSSFRow[1];
		oRows[0]=ActualSheet.createRow(iiAbsoluteRowNumber++);
		
		int iSpalte = 0;
		
		// alle potentiellen zeilen aus den mehrfach-Spalten durchgehen und fuellen
		for (int i=0;i<oMap.get_vComponentHashKeys().size();i++)
		{
			MyE2IF__Component oComp = (MyE2IF__Component) oMap.get(oMap.get_vComponentHashKeys().get(i));
			if (oComp.EXT().get_bIsVisibleInList())
			{
				//2015-05-20: Alle MyE2IF__ComponentContainer wie MyE2_DB_MultiComponentColumn behandeln
				if (oComp instanceof MyE2IF__ComponentContainer)
				{
					MyE2IF__ComponentContainer MCCol = (MyE2IF__ComponentContainer)  oComp;
					for (int k=0;k<MCCol.get_vComponents().size();k++)
					{
						HSSFCell  oCell = oRows[0].createCell(iSpalte);
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
						else{
							oCell.setCellValue("--ERROR--");
						}
						
						iSpalte++;
						
					}
				}
				else if (oComp instanceof MyE2_Grid)           //2011-03-08: grid wird hier mitbehandelt, alles wird in eine zelle geschrieben
				{
					MyE2_Grid grid = (MyE2_Grid)  oComp;
					
					HSSFCell  oCell = oRows[0].createCell(iSpalte);
					
					String cValue4Cell = "";
					int itemp =grid.getComponents().length;
					
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
					
					iSpalte++;
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
					
					iSpalte++;
				}
			}
		}
		return iiAbsoluteRowNumber;
	}

	
	
	
}
