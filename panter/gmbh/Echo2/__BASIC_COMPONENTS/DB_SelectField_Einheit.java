package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_SelectField_Einheit extends MyE2_DB_SelectField
{

	public DB_SelectField_Einheit(SQLField osqlField) throws myException
	{
		super(osqlField,new E2_DropDownSettings( "JT_EINHEIT", "  NVL(EINHEITKURZ,'-')", "ID_EINHEIT", null, true).getDD(),false);
	}

	
	public DB_SelectField_Einheit(SQLField osqlField, int iWidth) throws myException
	{
		super(osqlField,new E2_DropDownSettings( "JT_EINHEIT", "  NVL(EINHEITKURZ,'-')", "ID_EINHEIT", null, true).getDD(),false);
		this.setWidth(new Extent(iWidth));
	}
	

	
	public DB_SelectField_Einheit(SQLField osqlField, int iWidth, boolean bNurLaengenEinheiten) throws myException
	{
		super(osqlField,new E2_DropDownSettings( "JT_EINHEIT", "  NVL(EINHEITKURZ,'-')", "ID_EINHEIT", bNurLaengenEinheiten?"NVL(IST_LAENGE,'N')='Y'":"",null, true,"EINHEITKURZ").getDD(),false);
		this.setWidth(new Extent(iWidth));
		
		//hier automatisch die anderen werte in den shadow-bereich legen
		
		if (bNurLaengenEinheiten)
		{
			dataToView dwShadow = new dataToView(new E2_DropDownSettings( "JT_EINHEIT", "  NVL(EINHEITKURZ,'-')", "ID_EINHEIT", "NVL(IST_LAENGE,'N')='N'",null, true,"EINHEITKURZ").getDD(),
										false, bibE2.get_CurrSession());
			this.set_odataToViewShadow(dwShadow);
		}
		
	}

}
