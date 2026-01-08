package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;


/*
 * interface-methoden fuer felder, die in datenbank-strukturen benutzt werden,
 * aber kein SQL-Field zugewiesen bekommen
 */
public interface IF_UB_Fields
{
	public MyE2_MessageVector 	get_MV_InputOK() throws myException;
	public void    	mark_ErrorInput(boolean bOK);
	public boolean  get_bEmptyAllowd();
	public void  	set_bEmptyAllowd(boolean bAllowed);
	
	public String   get_cDBFieldName();
	public void     set_cDBFieldName(String cFieldName);
	
	public void     set_StyleForInput(boolean bEnabled);
	
	public void   	set_StartValue(String cStartDBValue) throws myException;
	
	/*
	 * gibt den fuer die datenbank formatierten wert zurueck
	 */
	public String   get_cInsertValuePart() throws myException;
	public String   get_cUpdatePart() throws myException;
	public String   get_cString4Database() throws myException;
	public String   get_cText() throws myException;
	
	public boolean  get_bFieldHasChanged()  throws myException;
	
	public boolean  get_bIsEmpty()  throws myException;
	
	public void add_InputValidator(XX_ValidateInput oValidator);
	public Vector<XX_ValidateInput>  get_vInputValidator();
	
}
