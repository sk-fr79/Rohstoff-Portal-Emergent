/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 21.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

/**
 * @author manfred
 * @date 21.10.2020
 *
 */
public class ContactPerson extends xml_element {
	
	xml_element _contactPersonName =new xml_element("contactPersonName");
	Address _Address= new Address();

	public ContactPerson() {
		super("ContactPerson");
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();
		
		addElement(_contactPersonName);
		addElement(_Address);
		
	}

}
