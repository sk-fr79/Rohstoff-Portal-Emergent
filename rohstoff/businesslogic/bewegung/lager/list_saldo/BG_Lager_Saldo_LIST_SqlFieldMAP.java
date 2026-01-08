 
package rohstoff.businesslogic.bewegung.lager.list_saldo;
  
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.BG_Lager_Saldo_NAMES;
  

@Deprecated
public class BG_Lager_Saldo_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public BG_Lager_Saldo_LIST_SqlFieldMAP() throws myException 
    {
        super(""/*_TAB.bg_ladung.n()*/, "", false);
        
        final Vector<IF_Field> vFieldsSTATION = new Vector<IF_Field>()
        {{
        	add(BG_STATION.name1);
        	add(BG_STATION.name2);
        	add(BG_STATION.plz);
        	add(BG_STATION.ort);
        	add(BG_STATION.strasse);
        	add(BG_STATION.id_adresse);
        	add(BG_STATION.id_adresse_basis);
        	add(BG_STATION.id_wiegekarte);
        	add(BG_STATION.wiegekartenkenner);
        }};
        
        final Vector<IF_Field> vFieldsEHG = new Vector<IF_Field>()
        {{
        	add(EINHEIT.einheitkurz);
        	add(EINHEIT.id_einheit);
        }};
        
        // Station und zugehörige Adresse
//        this._join(BG_STATION._tab()	, "S1"		, BG_STATION.id_bg_station			, SQLFieldMAP.INNER			, BG_LADUNG.id_bg_station	, BG_LADUNG._tab().fullTableName()	, "", vFieldsSTATION, "S1_");
        this._join(ADRESSE._tab()		, "ADR"		, ADRESSE.id_adresse				, SQLFieldMAP.INNER			, BG_STATION.id_adresse		, "S1"								, "", null, "ADR_");
        this._join(LIEFERADRESSE._tab()	, "ADRL"	, LIEFERADRESSE.id_adresse_liefer	, SQLFieldMAP.LEFT_OUTER	, ADRESSE.id_adresse		, "ADR"								, "", null, "ADRL_");

//        this._join(ARTIKEL._tab()		, "ART"		, ARTIKEL.id_artikel				, SQLFieldMAP.INNER			, BG_LADUNG.id_artikel		, BG_LADUNG._tab().fullTableName()	, "", null, "ADR_");
        this._join(EINHEIT._tab()		, "EINH"	, EINHEIT.id_einheit				, SQLFieldMAP.INNER			, ARTIKEL.id_einheit		, "ART"								, "", vFieldsEHG, "EINH_");
        
		
		// Eingrenzung der Adressen auf die Lageradressen
		String sJoinTable = "(" + 
				" SELECT LA.ID_ADRESSE_LIEFER as LA_ID_ADRESSE_LIEFER" +
				", LA.BESCHREIBUNG as LA_BESCHREIBUNG" +
				" FROM " + bibE2.cTO() + ".JT_LIEFERADRESSE LA" +
				" INNER JOIN " + bibE2.cTO() + ".JT_ADRESSE ADRL ON LA.ID_ADRESSE_LIEFER = ADRL.ID_ADRESSE" +
				" " +
				"      WHERE LA.ID_ADRESSE_BASIS = " + bibALL.get_ID_ADRESS_MANDANT() +  
				"        AND ( NVL(ADRL.SONDERLAGER,'STRECKE') = 'STRECKE'  )" +
				" UNION " +
				"    SELECT " + bibALL.get_ID_ADRESS_MANDANT() +  ", null FROM  DUAL"  + 
				" ) " ;
		
		
		this.add_JOIN_Table(sJoinTable, 
				"ADR_LIEFER", 
				SQLFieldMAP.INNER, 
				":LA_ID_ADRESSE_LIEFER:LA_BESCHREIBUNG:", 
				true, 
				"S1.ID_ADRESSE=LA_ID_ADRESSE_LIEFER", 
				"",
				null);
		

		
		String sJoinMAX_IDs = 
				   " ( "  + 
				   "  SELECT MAX(LAD.ID_BG_LADUNG) as ID_LADUNG_MAX,     " + 
				   "    LAD.ID_ARTIKEL as ID_ARTIKEL_MAX,  " +
				   "    S2.ID_ADRESSE as ID_ADRESSE_MAX  " +
				   "         FROM   JT_BG_LADUNG LAD " +
				   "         INNER JOIN   JT_BG_STATION S2 ON LAD.ID_BG_STATION = S2.ID_BG_STATION " +
				   "          WHERE  S2.ID_ADRESSE IN ( " +
				   "                 SELECT LA.ID_ADRESSE_LIEFER " +
				   "                  FROM   " + bibE2.cTO() + ".JT_LIEFERADRESSE LA " +
				   "                  INNER JOIN   " + bibE2.cTO() + ".JT_ADRESSE ADRL ON LA.ID_ADRESSE_LIEFER = ADRL.ID_ADRESSE   AND LA.ID_MANDANT = ADRL.ID_MANDANT " +
				   "                  WHERE LA.ID_ADRESSE_BASIS =  " +
				   "                          (   " +
				   "                          SELECT M.EIGENE_ADRESS_ID FROM  " + bibE2.cTO() + ".JD_MANDANT M " +
				   "                          WHERE M.ID_MANDANT =  " + bibALL.get_ID_MANDANT() + 
				   "                          )    " +
				   "				   AND (ADRL.SONDERLAGER is null OR ADRL.SONDERLAGER = 'STRECKE') " +
				   "                   UNION    " +
				   "                   SELECT M.EIGENE_ADRESS_ID  " +
				   "                   FROM  " + bibE2.cTO() + ".JD_MANDANT M   " +
				   "                   WHERE M.ID_MANDANT =   " + bibALL.get_ID_MANDANT() + 
				   "         ) " +
				   "     GROUP BY S2.id_adresse, LAD.ID_ARTIKEL  " +
				   " ) "  ;

		this.add_JOIN_Table(sJoinMAX_IDs, 
				"LADUNG_MAX", 
				SQLFieldMAP.INNER, 
				"", 
				true, 
				" JT_BG_LADUNG.ID_BG_LADUNG = ID_LADUNG_MAX ", 
				"",
				null);
		

		
        
        
        
		// Lageradresse
		this.add_SQLField(
				new SQLField(" NVL(ADR.ORT,'') || nvl2(ADR.PLZ,' (' || ADR.PLZ || '), ' ,'') || NVL(ADR.NAME1,'') ||' ' || NVL(ADR.NAME2,'') || NVL2(ADRL.BESCHREIBUNG, ' - ' || ADRL.BESCHREIBUNG,'') ",
				BG_Lager_Saldo_NAMES.ADRESS_INFO.name(), 
				new MyE2_String(BG_Lager_Saldo_NAMES.ADRESS_INFO.name()), 
				bibE2.get_CurrSession()), 
				true);

        
		// Artikelinfo
		this.add_SQLField(
				new SQLField("NVL(ART.ANR1,'') || ' - ' ||NVL(ART.ARTBEZ1,'')",
				BG_Lager_Saldo_NAMES.ART_INFO.name(), 
				new MyE2_String(BG_Lager_Saldo_NAMES.ART_INFO.name()), 
				bibE2.get_CurrSession()), 
				true);    
        
		
		this.add_ORDER_SEGMENT(" NVL(ADR.ORT,'') || nvl2(ADR.PLZ,' (' || ADR.PLZ || '), ' ,'') || NVL(ADR.NAME1,'') ||' ' || NVL(ADR.NAME2,'') || NVL2(ADRL.BESCHREIBUNG, ' - ' || ADRL.BESCHREIBUNG,'')" +
				",ART.ANR1");
        
        this.initFields();
    }
    
    

    
    private void _join(_TAB table2join, String join_alias, IF_Field field2join,  int joinType ,IF_Field parentField, String parent_alias, String sAdditionalJoin, Vector<IF_Field> vFieldList, String fieldAlias) throws myException{
    	
    	String sJoinBedingung = parentField.fn(parent_alias) + " = " + field2join.fn(join_alias);
    	sJoinBedingung += bibALL.isEmpty(sAdditionalJoin) ? "" : " " + sAdditionalJoin;
    	
    	// fieldlist aufbauen
    	String sFieldList = "";
    	if(vFieldList != null){
    		for (IF_Field f : vFieldList){
    			sFieldList += f.fn() + ":";
    		}
    		if (sFieldList.length()>0) sFieldList = ":"+sFieldList;
    	}
    	
    	
    	this.add_JOIN_Table(bibE2.cTO(), table2join.fullTableName(), join_alias, joinType, sFieldList, true, sJoinBedingung, fieldAlias, null);
    }
    
    
    
}
 
