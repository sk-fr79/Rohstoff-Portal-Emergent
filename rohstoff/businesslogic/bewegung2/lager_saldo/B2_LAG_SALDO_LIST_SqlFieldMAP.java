package rohstoff.businesslogic.bewegung2.lager_saldo;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP_ext4ReadOnly;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;

public class B2_LAG_SALDO_LIST_SqlFieldMAP extends SQLFieldMAP_ext4ReadOnly  {

	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;

	private static String base_sql ="( " +
			"	SELECT " +
			"            max(id_bg_auswert)  as ID_BASE,  " +
			"            ID_ADRESSE AS SUBADRESSE, " +
			"            ID_ARTIKEL AS SUBARTIKEL ," + 
			"			 ID_MANDANT as SUBMANDANT" +
			"              " +
			"       FROM  JT_BG_AUSWERT  " +
			"       WHERE 1=1 "			+ 
			"		AND ID_BG_DEL_INFO IS NULL "		+ 
			"		AND ID_BG_STORNO_INFO IS NULL "			+ 
			"		AND ID_ARTIKEL IS NOT NULL " +
			"		AND DATUM_AUSFUEHRUNG IS NOT NULL " +
			"       AND ID_ADRESSE IN " +
			"            ( " +
			"               SELECT V.ID_ADRESSE FROM V_FIRMENADRESSEN_FLACH V " +
			"                WHERE V.ID_ADRESSE_BASIS = " + bibALL.get_ID_ADRESS_MANDANT() + " AND NVL( V.SONDERLAGER,'STRECKE') = 'STRECKE' AND V.ID_MANDANT =  " + bibALL.get_ID_MANDANT() +
			" 	         )											" +        
			"        group by ID_MANDANT, ID_ADRESSE, ID_ARTIKEL 	" +
			" 														" +        
			"        having id_artikel is not null 					" +
			" " +        
			"        ) " ; 
	
	
	
	public B2_LAG_SALDO_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
		super( base_sql , "BASE") ;
		
		
		this.m_tpHashMap = p_tpHashMap;        
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);

		this.addCompleteBase_Table_FIELDLIST(":SUBADRESSE:SUBARTIKEL:",true);
		this.add_SQLField(new SQLFieldForPrimaryKey("BASE","ID_BASE","ID_BASE",new MyE2_String("ID_BASE"),bibE2.get_CurrSession(),"",true), false);
		
		this.add_JOIN_Table(_TAB.adresse.n(), 
				"ADR", 
				SQLFieldMAP.INNER, 
				":NAME1:ORT:", 
				true, 
				"BASE.SUBADRESSE  = " + ADRESSE.id_adresse.fn("ADR") ,
				"", 
				 bibALL.get_HashMap("ADRESSE_INFO", " NVL(ADR.ORT,'') || nvl2(ADR.PLZ,' (' || ADR.PLZ || '), ' ,'') || NVL(ADR.NAME1,'') ||' ' || NVL(ADR.NAME2,'') || NVL2(LA.BESCHREIBUNG, ' - ' || LA.BESCHREIBUNG,'') "));
		
		this.add_JOIN_Table(_TAB.artikel.n(), 
				"ART", 
				SQLFieldMAP.LEFT_OUTER, 
				":ID_ARTIKEL:ANR1:ARTBEZ1:", 
				true, 
				"BASE.SUBARTIKEL = " + ARTIKEL.id_artikel.fn("ART"), 
				"", 
				bibALL.get_HashMap("ART_INFO", "NVL(ART.ANR1,'') || ' - ' ||NVL(ART.ARTBEZ1,'')"));
		
		this.add_JOIN_Table(_TAB.einheit.n(), 
				"EH", 
				SQLFieldMAP.LEFT_OUTER, 
				":EINHEITKURZ:", 
				true, 
				ARTIKEL.id_einheit.fn("ART") + "="+ EINHEIT.id_einheit.fn("EH") , 
				"",
				null);
		
		this.add_JOIN_Table(_TAB.lieferadresse.n(), 
				"LA", 
				SQLFieldMAP.LEFT_OUTER, 
				":BEZEICHNUNG:", 
				true, 
				ADRESSE.id_adresse.fn("ADR")+ "=" +  LIEFERADRESSE.id_adresse_liefer.fn("LA"),
				"", null
				);
		

		// initiale Sortierung
		this.add_ORDER_SEGMENT(
				" NVL(ADR.ORT,'') || nvl2(ADR.PLZ,'  (' || ADR.PLZ || '), ' ,'') || NVL(ADR.NAME1,'') ||' ' || NVL(ADR.NAME2,'') || NVL2(LA.BESCHREIBUNG, ' - ' || LA.BESCHREIBUNG,'')" 
						+",ART.ANR1"
				);
		this.initFields();
	}

	private String convert_2_fieldlist(IF_Field ... fields) throws myException{
		String rueck = ":";
		for(IF_Field f : fields) {
			rueck += f.tnfn() + ":";
		}
		return rueck;
	}

}


