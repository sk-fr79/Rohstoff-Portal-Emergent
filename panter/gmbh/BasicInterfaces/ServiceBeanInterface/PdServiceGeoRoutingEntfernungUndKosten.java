/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import java.math.BigDecimal;
import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface PdServiceGeoRoutingEntfernungUndKosten {


	/**
	 * 
	 * @param startLaditude
	 * @param startLongitude
	 * @param zielLaditude
	 * @param zielLongitude
	 * @param kfzKennzeichenLkw (can be null)
	 * @param kfzKennzeichenAnhaenger (can be null)
	 * @return
	 * @throws myException
	 */
	public HashMap<ENUM_ROUTING_TYP, BigDecimal> getDistanceTimeAmdCosts(	BigDecimal 	startLaditude
																,BigDecimal startLongitude
																,BigDecimal zielLaditude
																,BigDecimal zielLongitude
																,String     kfzKennzeichenLkw
																,String     kfzKennzeichenAnhaenger
																,MyE2_MessageVector mv
																) throws myException;
	
	public enum ENUM_ROUTING_TYP{
		ENTFERNUNG
		,ZEIT_SEK
		,ZEIT_MIN
		,KOSTEN_KM
		;
	}	

}
