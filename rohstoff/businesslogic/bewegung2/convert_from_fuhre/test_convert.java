/**
 * rohstoff.businesslogic.bewegung2.convert_from_fuhre
 * @author manfred
 * @date 06.06.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;

/**
 * @author manfred
 * @date 06.06.2019
 *
 */
public class test_convert {
	public void runTest() throws myException{
		
		conv_helper helper = new conv_helper();
		jt_bg_vektor vek = new jt_bg_vektor(helper);
		vek.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_nextval());
		vek.setERZEUGT_AM(new Date());
		vek.setERZEUGT_VON("TEST");
		vek.setLETZTE_AENDERUNG(vek.getERZEUGT_AM().dValue);
		vek.setGEAENDERT_VON(vek.getERZEUGT_VON().ValuePlain());
		
		vek.setEN_TRANSPORT_TYP(EnTransportTyp.EINBUCHUNG);
		vek.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.AKTIV);
		vek.setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE.FUHRE);
		vek.setPLANMENGE(BigDecimal.TEN);
		
		SqlStringExtended sql =  vek.getInsertStatement();
		bibDB.ExecSQL(sql, true);
		
		
	}
	
	
	
	public void runTestSimple() throws myException{
		conv_helper helper = new conv_helper();
		
		jt_bg_vektor vek = new jt_bg_vektor(helper);
		vek.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_nextval());
		vek.setERZEUGT_AM(new Date());
		vek.setERZEUGT_VON("TEST");
		vek.setLETZTE_AENDERUNG(vek.getERZEUGT_AM().dValue);
		vek.setGEAENDERT_VON(vek.getERZEUGT_VON().ValuePlain());
		
		vek.setEN_TRANSPORT_TYP(EnTransportTyp.EINBUCHUNG);
		vek.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.AKTIV);
		vek.setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE.FUHRE);
		vek.setPLANMENGE(BigDecimal.TEN);
		
		SqlStringExtended sql =  vek.getInsertStatement();
		bibDB.ExecSQL(sql, true);
		
		
	}
	
}
