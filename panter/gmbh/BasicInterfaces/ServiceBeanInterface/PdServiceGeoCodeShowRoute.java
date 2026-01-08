package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import java.math.BigDecimal;

import panter.gmbh.indep.exceptions.myException;

public interface PdServiceGeoCodeShowRoute {
public void get_route_preview(
		BigDecimal 	bdLatitudeStart
		,BigDecimal bdLongitudeStart
		,BigDecimal bdLatitudeZiel
		,BigDecimal bdLongitudeZiel) throws myException;
}
