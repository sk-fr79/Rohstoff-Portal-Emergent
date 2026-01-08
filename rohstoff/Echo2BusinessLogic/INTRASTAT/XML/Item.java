/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 21.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

import panter.gmbh.basics4project.DB_ENUMS.INTRASTAT_MELDUNG;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.INTRASTAT.INSTAT_Handler.ENUM_IntrastatType;

/**
 * @author manfred
 * @date 21.10.2020
 * 
 * 
 * 
 * 	<xsd:element name = "Item">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "itemNumber"/>
				<xsd:element ref = "CN8" minOccurs = "0"/>
				<xsd:element ref = "goodsDescription" minOccurs = "0"/>
				<xsd:element ref = "MSConsDestCode" minOccurs = "0"/>
				<xsd:element ref = "countryOfOriginCode" minOccurs = "0"/>
				<xsd:element ref = "netMass" minOccurs = "0"/>
				<xsd:element ref = "quantityInSU" minOccurs = "0"/>
				<xsd:element ref = "invoicedAmount" minOccurs = "0" maxOccurs = "2"/>
				<xsd:element ref = "statisticalValue" minOccurs = "0"/>
				<xsd:element ref = "invoiceNumber" minOccurs = "0"/>
				<xsd:element ref = "partnerId" minOccurs = "0"/>
				<xsd:element ref = "statisticalProcedureCode" minOccurs = "0"/>
				<xsd:element ref = "NatureOfTransaction" minOccurs = "0"/>
				<xsd:element ref = "modeOfTransportCode" minOccurs = "0"/>
				<xsd:element ref = "regionCode" minOccurs = "0"/>
				<xsd:element ref = "portAirportInlandportCode" minOccurs = "0"/>
				<xsd:element ref = "DeliveryTerms" minOccurs = "0"/>
				<xsd:element ref = "numberOfConsignments" minOccurs = "0"/>
			</xsd:sequence>
		</xsd:complexType>
	</xsd:element>
	<xsd:element name = "itemNumber" type = "xsd:integer"/>
	<xsd:element name = "CN8">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "CN8Code"/>
				<xsd:element ref = "SUCode" minOccurs = "0"/>
				<xsd:element ref = "additionalGoodsCode" minOccurs = "0"/>
			</xsd:sequence>
		</xsd:complexType>
	</xsd:element>
	<xsd:element name = "CN8Code" type = "xsd:string"/>
	<xsd:element name = "SUCode" type = "xsd:string"/>
	<xsd:element name = "additionalGoodsCode" type = "xsd:string"/>
	<xsd:element name = "goodsDescription" type = "xsd:string"/>
	<xsd:element name = "MSConsDestCode" type = "xsd:string"/>
	<xsd:element name = "countryOfOriginCode" type = "xsd:string"/>
	<xsd:element name = "netMass" type = "xsd:integer"/>
	<xsd:element name = "quantityInSU" type = "xsd:integer"/>
	<xsd:element name = "invoicedAmount">
		<xsd:complexType>
			<xsd:simpleContent>
				<xsd:extension base = "xsd:decimal">
					<xsd:attribute name = "currencyCode" use = "optional" type = "xsd:string"/>
				</xsd:extension>
			</xsd:simpleContent>
		</xsd:complexType>
	</xsd:element>
	<xsd:element name = "statisticalValue" type = "xsd:decimal"/>
	<xsd:element name = "invoiceNumber" type = "xsd:string"/>
	<xsd:element name = "partnerId" type = "xsd:string"/>
	<xsd:element name = "statisticalProcedureCode" type = "xsd:string"/>
	<xsd:element name = "NatureOfTransaction">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "natureOfTransactionACode"/>
				<xsd:element ref = "natureOfTransactionBCode" minOccurs = "0"/>
			</xsd:sequence>
		</xsd:complexType>
	</xsd:element>
	<xsd:element name = "natureOfTransactionACode" type = "xsd:string"/>
	<xsd:element name = "natureOfTransactionBCode" type = "xsd:string"/>
	<xsd:element name = "modeOfTransportCode" type = "xsd:string"/>
	<xsd:element name = "regionCode" type = "xsd:string"/>
	<xsd:element name = "portAirportInlandportCode" type = "xsd:string"/>
	<xsd:element name = "DeliveryTerms">
		<xsd:complexType>
			<xsd:sequence>
				<xsd:element ref = "TODCode"/>
				<xsd:element ref = "locationCode" minOccurs = "0"/>
				<xsd:element ref = "TODPlace" minOccurs = "0"/>
				<xsd:element ref = "TODDetails" minOccurs = "0"/>
			</xsd:sequence>
		</xsd:complexType>
	</xsd:element>
	<xsd:element name = "TODCode" type = "xsd:string"/>
	<xsd:element name = "locationCode" type = "xsd:string"/>
	<xsd:element name = "TODPlace" type = "xsd:string"/>
	<xsd:element name = "TODDetails" type = "xsd:string"/>
	<xsd:element name = "numberOfConsignments" type = "xsd:integer"/>

 *
 */
public class Item extends xml_element {


	xml_element 		_itemNumber = new xml_element("itemNumber");
	CN8 				_CN8 = new CN8("CN8");
	xml_element 		_goodsDescription = new xml_element("goodsDescription").isOptional(true);
	xml_element 		_MSConsDestCode = new xml_element("MSConsDestCode");
	xml_element 		_countryOfOriginCode = new xml_element("countryOfOriginCode");
	xml_element 		_netMass = new xml_element("netMass");
	xml_element 		_quantityInSU = new xml_element("quantityInSU").isOptional(true);
	InvoicedAmount 		_invoicedAmount = new InvoicedAmount();
	xml_element 		_statisticalValue = new xml_element("statisticalValue");
	xml_element 		_invoiceNumber = new xml_element("invoiceNumber").isOptional(true);
	xml_element 		_partnerId = new xml_element("partnerId").isOptional(true);
	xml_element 		_statisticalProcedureCode = new xml_element("statisticalProcedureCode").isOptional(true);
	NatureOfTransaction _NatureOfTransaction = new NatureOfTransaction();
	xml_element 		_modeOfTransportCode = new xml_element("modeOfTransportCode");
	xml_element 		_regionCode = new xml_element("regionCode");
	xml_element 		_portAirportInlandportCode = new xml_element("portAirportInlandportCode").isOptional(true);
	DeliveryTerms 		_DeliveryTerms = (DeliveryTerms) new DeliveryTerms().isOptional(true);
	xml_element 		_numberOfConsignments = new xml_element("numberOfConsignments").isOptional(true);                    


	
	public Item() {
		super("Item");
		
	}
	
	
	public Item fillFrom(Rec21_Intrastat_Meldung recIntrastatMeldung) throws myException {
		
		_itemNumber.setText(Integer.toString(Integer.parseInt(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.paginiernummer))) );
		_CN8._CN8Code.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.warennr));
		_netMass.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.eigenmasse));
		_quantityInSU.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.masseinheit));
		_invoicedAmount.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.rechbetrag));
		_statisticalValue.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.statistischer_wert));
		
		_NatureOfTransaction.get_natureOfTransactionACode().setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.geschaeftsart).substring(0,1));
		_NatureOfTransaction.get_natureOfTransactionBCode().setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.geschaeftsart).substring(1, 2));
		
		_modeOfTransportCode.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.verkehrszweig));
		_regionCode.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.bestimm_region));
		_MSConsDestCode.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.bestimm_land));
		
		_countryOfOriginCode.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.land_ursprung));

		String sUST = recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.umsatzsteuer_lkz) + recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.umsatzsteuerid);
		_partnerId.setText(sUST);
		
//		_.setText(recIntrastatMeldung.get_ufs_dbVal(INTRASTAT_MELDUNG.));
		return this;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.INTRASTAT.XML.xml_element#init()
	 */
	@Override
	protected void init() {
		super.init();
		addElement(_itemNumber);
		
		addElement(_CN8);
		addElement(_goodsDescription );
		addElement(_MSConsDestCode );
		addElement(_countryOfOriginCode );
		addElement(_netMass );
		addElement(_quantityInSU );
		addElement(_invoicedAmount );
		addElement(_statisticalValue );
		addElement(_invoiceNumber );
		addElement(_partnerId );
		addElement(_statisticalProcedureCode );
		addElement(_NatureOfTransaction );
		addElement(_modeOfTransportCode );
		addElement(_regionCode );
		addElement(_portAirportInlandportCode );
		addElement(_DeliveryTerms );
		addElement(_numberOfConsignments );                    
	}
	

	
	
	

}
