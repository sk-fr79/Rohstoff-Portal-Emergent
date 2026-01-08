package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED;


import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB_SYNTAX;
import panter.gmbh.indep.exceptions.myException;


/**
 * suche des aktuellen Lieferbedingungswertes, wie er in der statistik verwendet wuerde (falls alternative lieferbed. exisitert, dann den nehmen, sonst den
 * aus der RG-Pos (nur in der Liste zur besseren Uebersicht)
 * @author martin
 *
 */
public class FU_AL_LieferbedZeigeAktuellStatistik
{
	
	private String  ID_VPOS_TPA_FUHRE = 	null;
	private String  ID_VPOS_TPA_FUHRE_ORT = null;
	private String  EK_VK = null;
	
	private String  cLIEF_BED_STATISTIK = "";
	

	public FU_AL_LieferbedZeigeAktuellStatistik(String iD_VPOS_TPA_FUHRE_UF, String iD_VPOS_TPA_FUHRE_ORT_UF, String ek_VK) throws myException
	{
		super();
		this.ID_VPOS_TPA_FUHRE = iD_VPOS_TPA_FUHRE_UF;
		this.ID_VPOS_TPA_FUHRE_ORT = iD_VPOS_TPA_FUHRE_ORT_UF;
		this.EK_VK = ek_VK;

		if (S.isEmpty(this.EK_VK)) {
				throw new myException(this,"Please define EK or VK");
		}
		
		
		
		boolean bEK = this.EK_VK.equals("EK");
		
		if (   (S.isFull(this.ID_VPOS_TPA_FUHRE) && S.isFull(this.ID_VPOS_TPA_FUHRE_ORT))  ||
			   (S.isEmpty(this.ID_VPOS_TPA_FUHRE) && S.isEmpty(this.ID_VPOS_TPA_FUHRE_ORT)) 
			) {
			throw new myException(this,"only one ID_VPOS_TPA_FUHRE or ID_VPOS_TPA_FUHRE_ORT MUST be full!!");
		}
		
		
		
		Vector<String>  vRechPosWhere = new Vector<String>();
		
		vRechPosWhere.add(_DB.VPOS_RG$ID_VPOS_RG_STORNO_VORGAENGER+" IS NULL ");
		vRechPosWhere.add(_DB.VPOS_RG$ID_VPOS_RG_STORNO_NACHFOLGER+" IS NULL ");
		bibDB_SYNTAX.GENERATE_YES_NO_WHERE(_DB.VPOS_RG, _DB.VPOS_RG$DELETED, "N");
		
		RECLIST_VPOS_RG  	rlRechGut = null;
		
		if (S.isFull(this.ID_VPOS_TPA_FUHRE)) {
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(this.ID_VPOS_TPA_FUHRE);
			
			this.cLIEF_BED_STATISTIK = bEK?recFuhre.get_LIEFERBED_ALTERNATIV_EK_cUF_NN(""):recFuhre.get_LIEFERBED_ALTERNATIV_VK_cUF_NN("");
			
			if (S.isEmpty(cLIEF_BED_STATISTIK)) {
				rlRechGut = recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(
						bibALL.Concatenate(vRechPosWhere, " AND ", "1=1")+
						" AND "+_DB.VPOS_RG$LAGER_VORZEICHEN+(bEK?"=1":"=-1")+
						" AND "+_DB.VPOS_RG$ID_VPOS_TPA_FUHRE_ORT_ZUGEORD+" IS NULL",
						null,
						true);
				if (rlRechGut.get_vKeyValues().size()==1) {
					this.cLIEF_BED_STATISTIK=rlRechGut.get(0).get_LIEFERBEDINGUNGEN_cUF_NN("");
				}
			}
		} else {
			RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(this.ID_VPOS_TPA_FUHRE_ORT);
			
			this.cLIEF_BED_STATISTIK = recFuhreOrt.get_LIEFERBED_ALTERNATIV_cUF_NN("");
			
			if (S.isEmpty(cLIEF_BED_STATISTIK)) {
				rlRechGut = recFuhreOrt.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord(
						bibALL.Concatenate(vRechPosWhere, " AND ", "1=1"),
						null,
						true);
				if (rlRechGut.get_vKeyValues().size()==1) {
					this.cLIEF_BED_STATISTIK=rlRechGut.get(0).get_LIEFERBEDINGUNGEN_cUF_NN("");
				}
			}
		}
	}
	
	
	public String get_cLIEFERFBED_STATISTIK(){
		return cLIEF_BED_STATISTIK;
	}
	
}
