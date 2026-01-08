package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import java.util.GregorianCalendar;
import java.util.HashMap;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class SELECTOR_MONATJAHR_bis_MONATJAHR extends MyE2_Grid 
{

	private MyE2_SelectField				oSelMonateJahrVon  = null;
	private MyE2_SelectField				oSelMonateJahrBis = null;

	private String   						cBedingungsWhereblockVON = null;
	private String   						cBedingungsWhereblockBIS = null;
	

	private HashMap<String,String>   		hmStartMonat =   	new HashMap<String, String>();
	private HashMap<String,String> 			hmEndMonat = 		new HashMap<String, String>();

	
	/**
	 * 
	 * @param iTextWidth
	 * @param iDropdownWidthMonatJahr
	 * @param cBeschriftung
	 * @param cBeschriftungTrenner
	 * @param BedingungsWhereblockVON
	 * @param BedingungsWhereblockBIS
	 * @throws myException
	 */
	public SELECTOR_MONATJAHR_bis_MONATJAHR(int 		iTextWidth, 
											int 		iDropdownWidthMonatJahr,
											MyString 	cBeschriftung,
											MyString 	cBeschriftungTrenner,
											String		BedingungsWhereblockVON,
											String  	BedingungsWhereblockBIS
											) throws myException
	{
		super(4,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		this.cBedingungsWhereblockVON = BedingungsWhereblockVON;
		this.cBedingungsWhereblockBIS = BedingungsWhereblockBIS;
		
		String[][] 				arrayMonate = 	new String[72][2];           	//die letzten 72 monate als start der selektion
	
		arrayMonate[0][0]="*";
		arrayMonate[0][1]="ALLE";
		
		hmStartMonat.put("ALLE", "");
		hmEndMonat.put("ALLE", "");

		
		GregorianCalendar  oCalStart = myDateHelper.Find_First_Day_OfMonth(new GregorianCalendar());
		String cDateFormated = myDateHelper.FormatDateNormal(oCalStart.getTime()).substring(3);          // 12.2009
		String cDateForSort  = cDateFormated.substring(3)+"-"+cDateFormated.substring(0,2);              // 2009-21
		arrayMonate[1][0]=arrayMonate[1][1]=cDateFormated;
		
		hmStartMonat.put(cDateFormated, 	this.cBedingungsWhereblockVON+ bibALL.MakeSql(cDateForSort));
		hmEndMonat.put(  cDateFormated,    	this.cBedingungsWhereblockBIS+ bibALL.MakeSql(cDateForSort));
		
		for (int i=2;i<72;i++)
		{
			oCalStart = myDateHelper.Find_First_Day_PreviousMonth(oCalStart);
			cDateFormated = myDateHelper.FormatDateNormal(oCalStart.getTime()).substring(3);          // 12.2009
			cDateForSort  = cDateFormated.substring(3)+"-"+cDateFormated.substring(0,2);              // 2009-21
			arrayMonate[i][0]=arrayMonate[i][1]=cDateFormated;

			hmStartMonat.put(cDateFormated, this.cBedingungsWhereblockVON+bibALL.MakeSql(cDateForSort));
			hmEndMonat.put(  cDateFormated, this.cBedingungsWhereblockBIS+bibALL.MakeSql(cDateForSort));
			
		}
		oSelMonateJahrVon = new MyE2_SelectField(arrayMonate,"ALLE",false);
		oSelMonateJahrBis = new MyE2_SelectField(arrayMonate,"ALLE",false);
		
		oSelMonateJahrVon.setWidth(new Extent(iDropdownWidthMonatJahr));
		oSelMonateJahrBis.setWidth(new Extent(iDropdownWidthMonatJahr));
		

		this.add(new MyE2_Label(cBeschriftung).get_InBorderGrid(new Border(0,Color.BLACK,Border.STYLE_NONE), new Extent(iTextWidth), E2_INSETS.I_0_0_0_0),E2_INSETS.I_0_0_0_0);
		this.add(this.oSelMonateJahrVon, E2_INSETS.I_10_0_0_0);
		if (S.isFull(cBeschriftungTrenner))
		{
			this.add(new MyE2_Label(cBeschriftungTrenner), E2_INSETS.I_10_0_0_0);
		}
		else
		{
			this.add(new MyE2_Label(""), E2_INSETS.I_0_0_0_0);
		}
		this.add(this.oSelMonateJahrBis, E2_INSETS.I_10_0_0_0);

	}
	
	
	
	/**
	 * 
	 * @param iDropdownWidthMonatJahr
	 * @param cBeschriftungTrenner
	 * @param BedingungsWhereblockVON
	 * @param BedingungsWhereblockBIS
	 * @throws myException
	 */
	public SELECTOR_MONATJAHR_bis_MONATJAHR(int 		iDropdownWidthMonatJahr,
											MyString 	cBeschriftungTrenner,
											String		BedingungsWhereblockVON,
											String  	BedingungsWhereblockBIS
											) throws myException
	{
		super(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.cBedingungsWhereblockVON = BedingungsWhereblockVON;
		this.cBedingungsWhereblockBIS = BedingungsWhereblockBIS;
		
		String[][] 				arrayMonate = 	new String[72][2];           	//die letzten 72 monate als start der selektion
		
		arrayMonate[0][0]="*";
		arrayMonate[0][1]="ALLE";
		
		hmStartMonat.put("ALLE", "");
		hmEndMonat.put("ALLE", "");
		
		
		GregorianCalendar  oCalStart = myDateHelper.Find_First_Day_OfMonth(new GregorianCalendar());
		String cDateFormated = myDateHelper.FormatDateNormal(oCalStart.getTime()).substring(3);          // 12.2009
		String cDateForSort  = cDateFormated.substring(3)+"-"+cDateFormated.substring(0,2);              // 2009-21
		arrayMonate[1][0]=arrayMonate[1][1]=cDateFormated;
		
		hmStartMonat.put(cDateFormated, 	this.cBedingungsWhereblockVON+ bibALL.MakeSql(cDateForSort));
		hmEndMonat.put(  cDateFormated,    	this.cBedingungsWhereblockBIS+ bibALL.MakeSql(cDateForSort));
		
		for (int i=2;i<72;i++)
		{
		oCalStart = myDateHelper.Find_First_Day_PreviousMonth(oCalStart);
		cDateFormated = myDateHelper.FormatDateNormal(oCalStart.getTime()).substring(3);          // 12.2009
		cDateForSort  = cDateFormated.substring(3)+"-"+cDateFormated.substring(0,2);              // 2009-21
		arrayMonate[i][0]=arrayMonate[i][1]=cDateFormated;
		
		hmStartMonat.put(cDateFormated, this.cBedingungsWhereblockVON+bibALL.MakeSql(cDateForSort));
		hmEndMonat.put(  cDateFormated, this.cBedingungsWhereblockBIS+bibALL.MakeSql(cDateForSort));
		
		}
		oSelMonateJahrVon = new MyE2_SelectField(arrayMonate,"ALLE",false);
		oSelMonateJahrBis = new MyE2_SelectField(arrayMonate,"ALLE",false);
		
		oSelMonateJahrVon.setWidth(new Extent(iDropdownWidthMonatJahr));
		oSelMonateJahrBis.setWidth(new Extent(iDropdownWidthMonatJahr));
		
		this.add(this.oSelMonateJahrVon, E2_INSETS.I_10_0_0_0);
		if (S.isFull(cBeschriftungTrenner))
		{
			this.add(new MyE2_Label(cBeschriftungTrenner), E2_INSETS.I_10_0_0_0);
		}
		else
		{
			this.add(new MyE2_Label(""), E2_INSETS.I_0_0_0_0);
		}
		this.add(this.oSelMonateJahrBis, E2_INSETS.I_10_0_0_0);
	
	}

	
	
	
	public MyE2_SelectField get_oSelMonateJahrVon() 
	{
		return oSelMonateJahrVon;
	}

	public MyE2_SelectField get_oSelMonateJahrBis() 
	{
		return oSelMonateJahrBis;
	}

	public HashMap<String, String> get_hmStartMonat() 
	{
		return hmStartMonat;
	}

	public HashMap<String, String> get_hmEndMonat() 
	{
		return hmEndMonat;
	}

	
}
