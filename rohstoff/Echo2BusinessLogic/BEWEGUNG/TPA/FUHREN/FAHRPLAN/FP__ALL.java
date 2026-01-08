package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;

public class FP__ALL {

	public enum CopyTyp {
		FAHRPLAN_KOPIE
		,FAHRPLANPOOL_KOPIE
		,FAHRPLANPOOL_PLANER
	}
	

	
	
	public static void set_BedingungenForFahrplanSQLFieldMAP(SQLFieldMAP  oMap, String cID_MASCHINE_LKW, String cDatum)
	{
		oMap.clear_BEDINGUNG_STATIC();
		oMap.add_BEDINGUNG_STATIC("NVL(JT_VPOS_TPA_FUHRE.FUHRE_AUS_FAHRPLAN,'N')='Y'");
		oMap.add_BEDINGUNG_STATIC("NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N'");
		oMap.add_BEDINGUNG_STATIC("NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'");
		oMap.add_BEDINGUNG_STATIC("NVL(JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP,0)="+cID_MASCHINE_LKW);
		oMap.add_BEDINGUNG_STATIC("NVL(TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_FAHRPLAN_FP,'dd.mm.yyyy'),'')="+bibALL.MakeSql(cDatum));

	}
	

	/*
	 * damit wird die list leer gemacht fuer geschlossene fahrplaene
	 */
	public static void set_BedingungenForFahrplanSQLFieldMAP_CLOSED(SQLFieldMAP  oMap)
	{
		oMap.clear_BEDINGUNG_STATIC();
		oMap.add_BEDINGUNG_STATIC("JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE=-1");
	}
	
	
	
}
