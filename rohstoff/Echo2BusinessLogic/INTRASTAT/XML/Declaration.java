/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 05.11.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

import java.util.ArrayList;

/**
 * @author manfred
 * @date 05.11.2020
 *
 */
public class Declaration extends xml_element {

	
	xml_element _declaration_id 			= new xml_element("declarationId").isOptional(true);
	DateTime    _DateTime 					= (DateTime) new DateTime().isOptional(true);
	xml_element _referencePeriod 			= new xml_element("referencePeriod");
	xml_element _PSIId 						= new xml_element("PSIId");
	Function 	_Function 					= new Function();
	xml_element _declarationTypeCode		= new xml_element("declarationTypeCode");
	xml_element _flowCode 					= new xml_element("flowCode");
	xml_element _currencyCode 				= new xml_element("currencyCode").isOptional(true);
	xml_element _firstLast 					= new xml_element("firstLast").isOptional(true);
	xml_element _totalNetMass				= new xml_element("totalNetMass").isOptional(true);
	xml_element _totalInvoicedAmount 		= new xml_element("totalInvoicedAmount").isOptional(true);
	
	ArrayList<xml_element> _Items 			= new ArrayList<xml_element>();
	
	xml_element _totalNumberLines 			= new xml_element("totalNumberLines").isOptional(true);
	xml_element _totalNumberDetailedLines 	= new xml_element("totalNumberDetailedLines").isOptional(true);


	
 	
	/**
	 * @author manfred
	 * @date 05.11.2020
	 *
	 * @param node
	 */
	public Declaration(String node) {
		super("Declaration");

		// default für Deutschland: 
		_Function.get_functionCode().setText("O");
	
		
		
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();

		addElement(_declaration_id);
		addElement(_DateTime);
		addElement(_referencePeriod);
		addElement(_PSIId);
		addElement(_Function);
		addElement(_declarationTypeCode);
		addElement(_flowCode);
		addElement(_currencyCode);
		addElement(_firstLast);
		addElement(_totalNetMass);
		addElement(_totalInvoicedAmount);

		for (xml_element item: _Items) {
			addElement(item);
		}

//		addElement(_totalNumberLines);
//		addElement(_totalNumberDetailedLines);

	}

	// ein Item in die Deklaration einbringen
	public Declaration addItem(xml_element item) {
		_Items.add(item);
		return this;
	}
	
	
	public xml_element get_declaration_id() {
		return _declaration_id;
	}

	public DateTime get_DateTime() {
		return _DateTime;
	}




	public xml_element get_referencePeriod() {
		return _referencePeriod;
	}




	public xml_element get_PSIId() {
		return _PSIId;
	}




	public Function get_Function() {
		return _Function;
	}




	public xml_element get_declarationTypeCode() {
		return _declarationTypeCode;
	}




	public xml_element get_flowCode() {
		return _flowCode;
	}




	public xml_element get_currencyCode() {
		return _currencyCode;
	}




	public xml_element get_firstLast() {
		return _firstLast;
	}




	public xml_element get_totalNetMass() {
		return _totalNetMass;
	}




	public xml_element get_totalInvoicedAmount() {
		return _totalInvoicedAmount;
	}




	public ArrayList<xml_element> get_Items() {
		return _Items;
	}




	public xml_element get_totalNumberLines() {
		return _totalNumberLines;
	}




	public xml_element get_totalNumberDetailedLines() {
		return _totalNumberDetailedLines;
	}

}
