/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 06.08.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.math.BigDecimal;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;

/**
 * @author martin
 * @date 06.08.2020
 *
 */
public class PdServiceErmittleLagerPreis {

	public BigDecimal getLagerpreis(Rec21 lagerAdresse, Rec21 lagerSortenBez) {
		
		BigDecimal bdRet = new BigDecimal(123.45);
		
		bibMSG.MV()._addWarn(S.ms("Bitte den Service <PdServiceErmittleLagerPreis> noch implementieren ! "));
		
		
		
		return bdRet;
		
	}
	
}
