/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 19.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

/**
 * @author manfred
 * @date 19.10.2020
 *
 */
public class DateTime  extends xml_element{
	
	
	private xml_element		_Date 	= new xml_element("date").isOptional(true);
	private xml_element		_Time 	= new xml_element("time").isOptional(true);
	
	/**
	 * @author manfred
	 * @date 20.10.2020
	 *
	 * @param node
	 */
	public DateTime() {
		super("DateTime");
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();
		addElement(_Date);
		addElement(_Time);
	}

	
	public xml_element get_Date() {
		return _Date;
	}

	public void set_Date(xml_element _Date) {
		this._Date = _Date;
	}

	public xml_element get_Time() {
		return _Time;
	}

	public void set_Time(xml_element _Time) {
		this._Time = _Time;
	}
	
	
}


