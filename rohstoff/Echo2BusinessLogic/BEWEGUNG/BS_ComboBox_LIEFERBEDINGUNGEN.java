package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatzArea;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class BS_ComboBox_LIEFERBEDINGUNGEN extends 		MyE2_DB_ComboBoxErsatzArea
{
	public BS_ComboBox_LIEFERBEDINGUNGEN(SQLFieldMAP osqlFieldMap, boolean bBreit) throws myException
	{
		//aenderung 2013-07-25: lieferbedingungen mit aktiv-schalter
		super(osqlFieldMap.get_("LIEFERBEDINGUNGEN"), "SELECT KURZBEZEICHNUNG AS A,KURZBEZEICHNUNG AS B FROM "+bibE2.cTO()+".JT_LIEFERBEDINGUNGEN WHERE NVL(AKTIV,'N')='Y' ORDER BY KURZBEZEICHNUNG");
		if (bBreit)
		{
			this.set_WidthAndHeightOfDropDown(new Extent(400),new Extent(100),null, new Integer(400));
			this.get_oTextArea().setFont(new E2_FontPlain());
			this.get_oTextArea().set_iRows(3);

		}
		else
		{
			this.set_WidthAndHeightOfDropDown(new Extent(200),new Extent(100),null, new Integer(100));
			this.get_oTextArea().setFont(new E2_FontPlain(-2));
			this.get_oTextArea().set_iRows(3);

		}
		
		
	}
	
	public BS_ComboBox_LIEFERBEDINGUNGEN(SQLFieldMAP osqlFieldMap, int iWidthInPixel, int iHeightInRows) throws myException
	{
		super(osqlFieldMap.get_("LIEFERBEDINGUNGEN"), "SELECT KURZBEZEICHNUNG AS A,KURZBEZEICHNUNG AS B FROM "+bibE2.cTO()+".JT_LIEFERBEDINGUNGEN  WHERE NVL(AKTIV,'N')='Y'  ORDER BY KURZBEZEICHNUNG");
		this.set_WidthAndHeightOfDropDown(new Extent(iWidthInPixel),new Extent(100),null, new Integer(iWidthInPixel));
		this.get_oTextArea().setFont(new E2_FontPlain());
		this.get_oTextArea().set_iRows(iHeightInRows);
	}

	
	public BS_ComboBox_LIEFERBEDINGUNGEN(SQLField osqlField, int iWidthInPixel, int iHeightInRows) throws myException
	{
		super(osqlField, "SELECT KURZBEZEICHNUNG AS A,KURZBEZEICHNUNG AS B FROM "+bibE2.cTO()+".JT_LIEFERBEDINGUNGEN  WHERE NVL(AKTIV,'N')='Y'  ORDER BY KURZBEZEICHNUNG");
		this.set_WidthAndHeightOfDropDown(new Extent(iWidthInPixel),new Extent(100),null, new Integer(iWidthInPixel));
		this.get_oTextArea().setFont(new E2_FontPlain());
		this.get_oTextArea().set_iRows(iHeightInRows);
	}

	
	
}
