package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_STD;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class FU___RECLIST_VPOS_STD_FOR_PREIS extends RECLIST_VPOS_STD
{

	RECLIST_VPOS_STD recListAngebote = new RECLIST_VPOS_STD();

	/**
	 * 
	 * @param cSQL_DB_VON : like "TO_DATE('30.01.2010','DD.MM.YYYY')";
	 * @param cSQL_DB_BIS : like "TO_DATE('30.01.2010','DD.MM.YYYY')";
	 * @param cID_ADRESSE : unformated
	 * @param cID_ARTIKEL : unformated
	 * @param bEK 
	 * @param Conn  (can be null)
	 * @throws myException
	 */
	public FU___RECLIST_VPOS_STD_FOR_PREIS(	String cSQL_DB_VON,
											String cSQL_DB_BIS, 
											String cID_ADRESSE, 
											String cID_ARTIKEL, 
											boolean bEK, 
											MyConnection Conn) 	throws myException
	{
		super("SELECT JT_VPOS_STD.* FROM "+bibE2.cTO()+".JT_VPOS_STD   " +
				" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT ON (JT_VPOS_STD_ANGEBOT.ID_VPOS_STD=JT_VPOS_STD.ID_VPOS_STD) " +
				" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_STD ON (JT_VPOS_STD.ID_VKOPF_STD=JT_VKOPF_STD.ID_VKOPF_STD) " +
						" WHERE " +
				" NVL(JT_VPOS_STD.DELETED,'N')='N' AND "+
				" NVL(JT_VKOPF_STD.DELETED,'N')='N' AND "+
				//2014-08-24: aenderung: nur noch abgeschlossene angebote werden gefunden (wenn im mandant dies eingestellt ist) 
				(bibALL.get_RECORD_MANDANT().is_PREISFIND_ANGEB_NUR_GEDRUCKT_YES()?" NVL(JT_VKOPF_STD.ABGESCHLOSSEN,'N')='Y' AND ":"") +
				"( 	(JT_VPOS_STD_ANGEBOT.GUELTIG_VON<="+cSQL_DB_VON +" AND "+cSQL_DB_VON+"<=JT_VPOS_STD_ANGEBOT.GUELTIG_BIS) OR  "+
				   "("+cSQL_DB_BIS+"<= JT_VPOS_STD_ANGEBOT.GUELTIG_VON  AND JT_VPOS_STD_ANGEBOT.GUELTIG_VON<="+cSQL_DB_BIS+") ) "+
				" AND JT_VKOPF_STD.ID_ADRESSE="+cID_ADRESSE+
				" AND JT_VPOS_STD.ID_ARTIKEL="+cID_ARTIKEL+
				" AND JT_VPOS_STD.EINZELPREIS IS NOT NULL "+
				(bEK?" AND JT_VKOPF_STD.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_ABNAHMEANGEBOT):" AND JT_VKOPF_STD.VORGANG_TYP="+bibALL.MakeSql(myCONST.VORGANGSART_ANGEBOT)), 
				Conn==null?bibALL.get_oConnectionNormal():Conn);
	}


	
	
	
	
}
