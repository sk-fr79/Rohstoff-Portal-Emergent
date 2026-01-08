package rohstoff.utils.ECHO2;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class DB_SEARCH_Bank extends MyE2_DB_MaskSearchField
{
	
	private 	E2_ContentPane 		oContentPane = null;
	private 	SQLField 			osqlField = null;
	private	 	SQLFieldMAP 		osqlFieldGroup = null;
	
	
	public DB_SEARCH_Bank(  SQLField osqlfield, 
							SQLFieldMAP osqlFieldgroup) throws myException
	{
		super(		osqlfield, 
					"JT_BANKENSTAMM.ID_BANKENSTAMM,JT_BANKENSTAMM.BANKLEITZAHL,JT_BANKENSTAMM.SWIFTCODE,JT_ADRESSE.NAME1,JT_ADRESSE.NAME2,JT_ADRESSE.ORT", 
					bibE2.cTO()+".JT_ADRESSE, "+bibE2.cTO()+".JT_BANKENSTAMM ", 
					"NAME1,ORT",
					"JT_ADRESSE.ID_ADRESSE=JT_BANKENSTAMM.ID_ADRESSE AND JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_BANK, 
					"UPPER(NAME1) LIKE UPPER('%#WERT#%') OR " +
					"UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
					"OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
					"OR BANKLEITZAHL LIKE '%#WERT#%'  " +
					"OR IBAN_NR LIKE '%#WERT#%'  " +
					"OR UPPER(PLZ) LIKE UPPER('%#WERT#%') " +
					"OR TO_CHAR(JT_BANKENSTAMM.ID_BANKENSTAMM)='#WERT#'", 
					null,
					null,
					"SELECT trim(NVL(NAME1,'-'))|| '   BLZ: ' || trim(NVL(BANKLEITZAHL,'-'))|| ', ' || trim(NVL(ORT,'-'))  || " +
					"' (ID: '||trim(TO_CHAR(JT_ADRESSE.ID_ADRESSE))||')' FROM " + bibE2.cTO() + ".JT_ADRESSE,"+bibE2.cTO()+".JT_BANKENSTAMM WHERE JT_ADRESSE.ID_ADRESSE=JT_BANKENSTAMM.ID_ADRESSE AND ID_BANKENSTAMM=#WERT#", 
					new Insets(4,2,2,4), 
					false);
		
		this.osqlField = 		osqlfield;
		this.osqlFieldGroup=	osqlFieldgroup;
		this.set_oPosX(null);
		this.set_oPosY(null);

		
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			DB_SEARCH_Bank oRueck = new DB_SEARCH_Bank(this.osqlField,this.osqlFieldGroup);
			
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("DB_SEARCH_Adress:get_Copy:Error building copy-object: "+ex.get_ErrorMessage().get_cMessage().CTrans());
		}
		
	}



	public E2_ContentPane get_oContentPane()
	{
		return oContentPane;
	}




	public SQLField get_oSqlField()
	{
		return osqlField;
	}



	public SQLFieldMAP get_oSqlFieldGroup()
	{
		return osqlFieldGroup;
	}

	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}
	

	
}
