package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField_LabelInList;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_Component_LAND_DROPDOWN_LabelInList extends MyE2_DB_SelectField_LabelInList
{


	public DB_Component_LAND_DROPDOWN_LabelInList(SQLField osqlField, int iWidth, boolean bUseInList) throws myException 
	{
		super(osqlField);
		E2_DropDownSettings ddLand = new E2_DropDownSettings( "JD_LAND", "LAENDERCODE||' ('||  NVL(LAENDERNAME,'-')||')'", "ID_LAND", "ISTSTANDARD", true);
		this.set_ListenInhalt(ddLand.getDD(), false);
		
		this.setWidth(new Extent(iWidth));
		
		this.set_bUseInList(bUseInList);
		
	}

	public DB_Component_LAND_DROPDOWN_LabelInList(SQLField osqlField, int iWidth, boolean bKurz, boolean bUseInList) throws myException 
	{
		super(osqlField);
		E2_DropDownSettings ddLand = bKurz?
				new E2_DropDownSettings( "JD_LAND", "LAENDERCODE", "ID_LAND", "ISTSTANDARD", true)
			:
				new E2_DropDownSettings( "JD_LAND", "LAENDERCODE||' ('||  NVL(LAENDERNAME,'-')||')'", "ID_LAND", "ISTSTANDARD", true);
		this.set_ListenInhalt(ddLand.getDD(), false);
		
		this.setWidth(new Extent(iWidth));
		
		this.set_bUseInList(bUseInList);
	}


	
}
