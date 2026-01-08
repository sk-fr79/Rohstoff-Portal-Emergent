/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class BAMF_SQLFieldMAP extends SQLFieldMAP
{

	
	private String  					idVPOS_TPA_FUHRE = null;
	private String  					idVPOS_TPA_FUHRE_ORT = null;

	
	/**
	 * @param entweder wird ein fuhrenort oder eine fuhre gezogen (bei neueingabe)
	 * @throws myException
	 */
	public BAMF_SQLFieldMAP(String cID_FUHRE, String cID_FUHRE_ORT) throws myException 	{
		super("JT_FBAM",bibE2.get_CurrSession());
		
		this.idVPOS_TPA_FUHRE = 		cID_FUHRE;
		this.idVPOS_TPA_FUHRE_ORT = 	cID_FUHRE_ORT;
		
		this.addCompleteTable_FIELDLIST("JT_FBAM",":ID_FBAM:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true, "");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_FBAM","ID_FBAM","ID_FBAM",new MyE2_String("ID"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_FBAM.NEXTVAL FROM DUAL",true), false);

		if (S.isFull(cID_FUHRE))
		{
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_FBAM","ID_VPOS_TPA_FUHRE",		"ID_VPOS_TPA_FUHRE",	new MyE2_String("ID-Fuhre"),cID_FUHRE,bibE2.get_CurrSession()), true);
		}
		if (S.isFull(cID_FUHRE_ORT))
		{
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_FBAM","ID_VPOS_TPA_FUHRE_ORT",	"ID_VPOS_TPA_FUHRE_ORT",new MyE2_String("ID-Fuhre-Ort"),cID_FUHRE_ORT,bibE2.get_CurrSession()), true);
		}
		
		/*
		 * ein infofield, wird nur in der liste gebraucht, um den status der BAM datzustellen
		 */
		this.add_SQLField(new SQLField(" NVL(JT_FBAM.ABGESCHLOSSEN_BEHEBUNG,'-')|| NVL(JT_FBAM.ABGESCHLOSSEN_KONTROLLE,'-')",
				BAMF_LIST_ModulContainer.NAME_OF_SQL_INFOFIELD,new MyE2_String("Info"),bibE2.get_CurrSession()), false);

		String cSUBQUERY1 = "CASE "+ 
				" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE IS NOT NULL  THEN "+
				" (SELECT NVL(ART.ANR1,'<anr1>')||' '||NVL(ART.ARTBEZ1,'<artbez1>') FROM JT_VPOS_TPA_FUHRE FU LEFT OUTER JOIN JT_ARTIKEL ART ON (FU.ID_ARTIKEL=ART.ID_ARTIKEL) WHERE FU.ID_VPOS_TPA_FUHRE=JT_FBAM.ID_VPOS_TPA_FUHRE) "+
				" WHEN  JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NOT NULL  THEN "+
				" (SELECT NVL(ART.ANR1,'<anr1>')||' '||NVL(ART.ARTBEZ1,'<artbez1>') FROM JT_VPOS_TPA_FUHRE_ORT FUO LEFT OUTER JOIN JT_ARTIKEL ART ON (FUO.ID_ARTIKEL=ART.ID_ARTIKEL) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
				" ELSE "+
				" NULL "+
				" END ";
		
		this.add_SQLField(new SQLField(cSUBQUERY1,
							BAMF_LIST_ModulContainer.NAME_OF_SORTE,new MyE2_String("Sorte"),bibE2.get_CurrSession()), false);
		
		
		String Subquery_lieferant = "(CASE "+
					" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE IS NOT NULL THEN "+
					"    (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>') "+
					"         FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE  FU  "+
					"         INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_START=AD.ID_ADRESSE) WHERE FU.ID_VPOS_TPA_FUHRE=JT_FBAM.ID_VPOS_TPA_FUHRE) "+
					" "+
					" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NOT NULL THEN "+
					"    ( "+
					"    CASE WHEN (SELECT NVL(FO.DEF_QUELLE_ZIEL,'EK') FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FO WHERE FO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT)='EK' THEN "+
					"        (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>')  "+
					"             FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO "+
					"             INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FUO.ID_ADRESSE=AD.ID_ADRESSE) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
					"     ELSE "+
					"        (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>')  "+
					"             FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO "+
					"             INNER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU ON (FU.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE) "+
					"             INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_START=AD.ID_ADRESSE) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
					"     END   "+
					"    ) "+
					" ELSE "+
					"   null "+
					" END)";


		String Subquery_Abnehmer = "(CASE "+
					" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE IS NOT NULL THEN "+
					"    (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>') "+
					"         FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE  FU  "+
					"         INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_ZIEL=AD.ID_ADRESSE) WHERE FU.ID_VPOS_TPA_FUHRE=JT_FBAM.ID_VPOS_TPA_FUHRE) "+
					" "+
					" WHEN JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NOT NULL THEN "+
					"    ( "+
					"    CASE WHEN (SELECT NVL(FO.DEF_QUELLE_ZIEL,'EK') FROM JT_VPOS_TPA_FUHRE_ORT FO WHERE FO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT)='VK' THEN "+
					"        (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>') "+
					"             FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO "+
					"             INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FUO.ID_ADRESSE=AD.ID_ADRESSE) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
					"     ELSE "+
					"        (SELECT NVL(AD.NAME1,'<name1>')||',  '||NVL(AD.ORT,'<ort>') "+
					"             FROM  "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO "+
					"             INNER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU ON (FU.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE) "+
					"             INNER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_ZIEL=AD.ID_ADRESSE) WHERE FUO.ID_VPOS_TPA_FUHRE_ORT=JT_FBAM.ID_VPOS_TPA_FUHRE_ORT) "+
					"     END "+
					"    ) "+
					" ELSE "+
					"   null "+
					" END)";

		this.add_SQLField(new SQLField(Subquery_lieferant,
				BAMF_LIST_ModulContainer.NAME_LISTFELD_LIEFERANT,new MyE2_String("Lieferant"),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField(Subquery_Abnehmer,
				BAMF_LIST_ModulContainer.NAME_LISTFELD_ABNEHMER,new MyE2_String("Abnehmer"),bibE2.get_CurrSession()), false);
		
		
		
		
//		/*
//		 * jetzt einige info-felder ueber die fuhre zufuegen
//		 * ID_VPOS_TPA_FUHRE
//		 */
//		String cFeldListe = ":L_NAME1:L_ORT:A_NAME1:A_ORT:TRANSPORTKENNZEICHEN:ANHAENGERKENNZEICHEN:ID_ARTIKEL:MENGE_VORGABE_KO:MENGE_AUFLADEN_KO:MENGE_ABLADEN_KO:EK_KONTRAKTNR_ZUSATZ:VK_KONTRAKTNR_ZUSATZ:";
//		
//		this.addCompleteTable_FIELDLIST("JT_VPOS_TPA_FUHRE",cFeldListe,true,false,"F_");
//		this.add_SQLField(new SQLFieldForPrimaryKey("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE","F_ID_VPOS_TPA_FUHRE",new MyE2_String("ID (Fuhre)"),bibE2.get_CurrSession(),"SELECT SEQ_VPOS_TPA_FUHRE.NEXTVAL FROM DUAL",false), false);
//
//		this.addCompleteTable_FIELDLIST("JT_ARTIKEL","ANR1:ARTBEZ1",true,false,"A_");
//		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ARTIKEL","ID_ARTIKEL","A_ID_ARTIKEL",new MyE2_String("ID (Artikel)"),bibE2.get_CurrSession(),"SELECT SEQ_ARTIKEL.NEXTVAL FROM DUAL",false), false);
//		
//		this.add_InnerConnector(new SQLConnectorInnerTables("JT_FBAM.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE"));
//		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VPOS_TPA_FUHRE.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL (+)"));
		
		/*
		 * fuhre MUSS besetzt sein
		 */
		this.clear_BEDINGUNG_STATIC();
		this.add_BEDINGUNG_STATIC("(JT_FBAM.ID_VPOS_TPA_FUHRE IS NOT NULL OR JT_FBAM.ID_VPOS_TPA_FUHRE_ORT IS NOT NULL )");
		
		this.initFields();
	}


	public String getIdVPOS_TPA_FUHRE() {
		return idVPOS_TPA_FUHRE;
	}


	public String getIdVPOS_TPA_FUHRE_ORT() {
		return idVPOS_TPA_FUHRE_ORT;
	}

	
}