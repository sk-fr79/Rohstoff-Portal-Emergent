package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_Component_LAND_DROPDOWN extends MyE2_DB_SelectField 
{
	private E2_DropDownSettings ddLand = null;
	private SQLField  			oFieldLand = null;
	
	public DB_Component_LAND_DROPDOWN(SQLField osqlField, int iWidth) throws myException 
	{
		super(osqlField);
		this.__init(osqlField, iWidth, false);
	}
	
	public DB_Component_LAND_DROPDOWN(SQLField osqlField, int iWidth, boolean bSetDefaultToSQLField, XX_ActionAgent  oAgent) throws myException 
	{
		super(osqlField);
		
		
		this.__init(osqlField, iWidth, bSetDefaultToSQLField);
		
		if (oAgent!=null) {
			this.add_oActionAgent(oAgent);
		}
		
	}
	
	
	private void __init(SQLField osqlField, int iWidth, boolean bSetDefaultToSQLField) throws myException {
		this.oFieldLand = osqlField;
		this.ddLand = new E2_DropDownSettings( "JD_LAND", "LAENDERCODE||' ('||  NVL(LAENDERNAME,'-')||')'", "ID_LAND", "ISTSTANDARD", true);
		
		if (bSetDefaultToSQLField) {
			this.set_StandardValueToSQLField();
		}

		this.set_ListenInhalt(ddLand.getDD(), false);
		
		this.setWidth(new Extent(iWidth));
	}
	
	
	
	public void set_StandardValueToSQLField() throws myException {
		this.oFieldLand.set_cDefaultValueFormated(this.ddLand.getDefault());
	}

//	public DB_Component_LAND_DROPDOWN(SQLField osqlField, int iWidth, boolean bKurz) throws myException 
//	{
//		super(osqlField);
//		this.ddLand = bKurz?
//				new E2_DropDownSettings( "JD_LAND", "LAENDERCODE", "ID_LAND", "ISTSTANDARD", true)
//			:
//				new E2_DropDownSettings( "JD_LAND", "LAENDERCODE||' ('||  NVL(LAENDERNAME,'-')||')'", "ID_LAND", "ISTSTANDARD", true);
//		this.set_ListenInhalt(ddLand.getDD(), false);
//		
//		this.setWidth(new Extent(iWidth));
//		
//	}

	
	
	
}
