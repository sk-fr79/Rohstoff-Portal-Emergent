package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/*
 * klasse sorgt dafuer, dass in einem Maskenkopf die waehrung nur 
 * veraendert werden darf, wenn noch keine positionen vorhanden sind
 */
public class BS_MASK_KOPF_WAEHRUNGSSETTER
{
	
	private String  			cTableKennung = null;
	private E2_ComponentMAP 	oMap = null;
	
	
	/**
	 * @param TableKennung (STD,RG,KON oder TPA)
	 * @param map
	 * @throws myException
	 */
	public BS_MASK_KOPF_WAEHRUNGSSETTER(String tableKennung, E2_ComponentMAP map) throws myException
	{
		super();
		cTableKennung = tableKennung;
		oMap = map;
		
		boolean  bNeueingabe = (oMap.get_oInternalSQLResultMAP()==null);
		
		if (!(this.cTableKennung.equals("STD") || this.cTableKennung.equals("RG") || this.cTableKennung.equals("KON") || this.cTableKennung.equals("TPA")))
			throw new myException(this,"False Tablekennung: "+bibALL.null2leer(this.cTableKennung)+" is not allowed !");


		boolean bHasPositions = false;
		
		if (!bNeueingabe)
		{
			String cID_VKOPF = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			if (this.cTableKennung.equals("STD"))
			{
				bHasPositions = new RECORD_VKOPF_STD(cID_VKOPF).get_DOWN_RECORD_LIST_VPOS_STD_id_vkopf_std("NVL(DELETED,'N')='N'",null,false).get_vKeyValues().size()>0;	
			}
			if (this.cTableKennung.equals("RG"))
			{
				bHasPositions = new RECORD_VKOPF_RG(cID_VKOPF).get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'",null,false).get_vKeyValues().size()>0;	
			}
			if (this.cTableKennung.equals("KON"))
			{
				bHasPositions = new RECORD_VKOPF_KON(cID_VKOPF).get_DOWN_RECORD_LIST_VPOS_KON_id_vkopf_kon("NVL(DELETED,'N')='N'",null,false).get_vKeyValues().size()>0;	
			}
			if (this.cTableKennung.equals("TPA"))
			{
				bHasPositions = new RECORD_VKOPF_TPA(cID_VKOPF).get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa("NVL(DELETED,'N')='N'",null,false).get_vKeyValues().size()>0;	
			}
		}
		
		if (bHasPositions)
		{
			this.oMap.get__Comp("ID_WAEHRUNG_FREMD").EXT().set_bDisabledFromInteractive(true);
		}
		
		
		
	}
	


}
