package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.dataTools.query.U;

public class TP_KST_SELECT_Normalisierte_Orte extends SELECT {

	
	
	public TP_KST_SELECT_Normalisierte_Orte(boolean bIstStartAdresse) {
		this.select(	new U("DISTINCT "+ "RPAD("+TP_KST__CONST.get_KernStringNormierterOrt("ADR")+"||' ',30,'_')||' '||"+"SUBSTR(UPPER(NVL(JD_LAND.LAENDERNAME,'---')),1,3)"+" AS TEXT,"+
												TP_KST__CONST.get_KernStringNormierterOrt("ADR")+" AS WERT"));

//		this.select(	new U("DISTINCT "+ "SUBSTR(UPPER(NVL(JD_LAND.LAENDERNAME,'---')),1,3)"+"||' - '||"+TP_KST__CONST.get_KernStringNormierterOrt("ADR")+" AS TEXT,"+
//												TP_KST__CONST.get_KernStringNormierterOrt("ADR")+" AS WERT"));
		
		this.from(bibE2.cTO()+"."+_DB.VPOS_TPA);
		this.join(bibE2.cTO()+"."+_DB.VPOS_TPA_FUHRE).on(	_DB.Z_VPOS_TPA_FUHRE$ID_VPOS_TPA, 			_DB.Z_VPOS_TPA$ID_VPOS_TPA);
		this.join(bibE2.cTO()+"."+_DB.VKOPF_TPA).on(		_DB.Z_VKOPF_TPA$ID_VKOPF_TPA, 				_DB.Z_VPOS_TPA$ID_VKOPF_TPA);
		this.join(bibE2.cTO()+"."+_DB.ADRESSE, "ADR").on(	(bIstStartAdresse?
															_DB.Z_VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_START:
															_DB.Z_VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL), "ADR."+_DB.ADRESSE$ID_ADRESSE);
		this.join(bibE2.cTO()+"."+_DB.LAND).on(				_DB.Z_LAND$ID_LAND, "ADR."+_DB.ADRESSE$ID_LAND);

		this.join(bibE2.cTO()+"."+_DB.ADRESSE, "ADR2").on(	(bIstStartAdresse?
															_DB.Z_VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL:
															_DB.Z_VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_START), "ADR2."+_DB.ADRESSE$ID_ADRESSE);
		
		this.where("1", new U("1"));
		this.and(new U("NVL("+_DB.Z_VPOS_TPA$DELETED+",'N')"), new U("'N'"));
		this.and(new U(TP_KST__CONST.get_KernStringNormierterOrt("ADR")),"is",new U("NOT NULL"));
		
		DEBUG.System_println("SELECT-Klasse-Debug:  "+this.toString(),DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
		
//		
	}
	

	
	
}
