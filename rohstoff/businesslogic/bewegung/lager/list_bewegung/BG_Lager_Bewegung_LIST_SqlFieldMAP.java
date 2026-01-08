 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


@Deprecated
public class BG_Lager_Bewegung_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public BG_Lager_Bewegung_LIST_SqlFieldMAP() throws myException 
    {
        super(/*_TAB.bg_ladung.n()*/"", "", false);
        
        
        // fieldlists
//        final Vector<IF_Field> vFIeldsAtom = new Vector<IF_Field>()
//        {{
//        	add(BG_ATOM.en_atom_typ);
//        	add(BG_ATOM.e_preis);
//        	add(BG_ATOM.kosten_transport);
//        	add(BG_ATOM.id_vpos_tpa_fuhre);
//        	add(BG_ATOM.id_vpos_tpa_fuhre_ort);
//        }};
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
        final Vector<IF_Field> vFieldsART = new Vector<IF_Field>()    {{  	add(ARTIKEL.anr1);     }};
        final Vector<IF_Field> vFieldsARTBEZ = new Vector<IF_Field>() {{  	add(ARTIKEL_BEZ.anr2); }};
        
        final Vector<IF_Field> vFieldsVEKTOR = new Vector<IF_Field>() 
        {{  	
        	add(BG_VEKTOR.bemerkung);
        	add(BG_VEKTOR.bemerkung_fuer_kunde);
        	add(BG_VEKTOR.bemerkung_sachbearbeiter);
        }};
        
        final Vector<IF_Field> vFieldsLA = new Vector<IF_Field>()
        {{
        	add(LIEFERADRESSE.id_adresse_basis);
        	add(LIEFERADRESSE.id_adresse_liefer);
        	add(LIEFERADRESSE.id_adresse_fremdware);
        }};
        
        
        final Vector<IF_Field> vFieldsEHG = new Vector<IF_Field>()
        {{
        	add(EINHEIT.einheitkurz);
        	add(EINHEIT.id_einheit);
        }};
        
        final Vector<IF_Field> vFieldsEHP = new Vector<IF_Field>()
        {{
        	add(EINHEIT.einheitkurz);
        	add(EINHEIT.id_einheit);
        }};
        
        
        final Vector<IF_Field> vFieldsADRKD = new Vector<IF_Field>()
        {{
        	add(ADRESSE.id_adresse);
        	add(ADRESSE.name1);
        	add(ADRESSE.name2);
        	add(ADRESSE.plz);
        	add(ADRESSE.ort);
        }};
        
        
        // Atom und Vektor
//        this._join(BG_ATOM._tab()		, "A"		, BG_ATOM.id_bg_atom			, SQLFieldMAP.INNER			, BG_LADUNG.id_bg_atom		, BG_LADUNG._tab().fullTableName(), "", vFIeldsAtom, "A_");
//        this._join(BG_VEKTOR._tab()		, "V"		, BG_VEKTOR.id_bg_vektor		, SQLFieldMAP.INNER			, BG_ATOM.id_bg_vektor		, "A", "", vFieldsVEKTOR, "V_");
//        
//        // Station und zugehörige Adresse
//        this._join(BG_STATION._tab()	, "S1"		, BG_STATION.id_bg_station		, SQLFieldMAP.INNER			, BG_LADUNG.id_bg_station	, BG_LADUNG._tab().fullTableName(), "", vFieldsSTATION, "S1_");
//        this._join(ADRESSE._tab()		, "ADR"		, ADRESSE.id_adresse			, SQLFieldMAP.INNER			, BG_STATION.id_adresse		, "S1", "", null, "ADR_");
//        this._join(LIEFERADRESSE._tab()	, "ADRL"	, LIEFERADRESSE.id_adresse_liefer	, SQLFieldMAP.LEFT_OUTER, ADRESSE.id_adresse		, "ADR", "", vFieldsLA, "ADRL_");
//        
//        // Gegenüberliegende Ladung und Station für die Bestitzverhältnisse
//        this._join(BG_LADUNG._tab()		, "L2"		, BG_LADUNG.id_bg_atom			, SQLFieldMAP.INNER			, BG_ATOM.id_bg_atom		, "A", 	" AND L2."+BG_LADUNG.mengenvorzeichen.fn() + " = " + BG_LADUNG.mengenvorzeichen.tnfn() + " * -1 ", null, "L2_");
//        this._join(BG_STATION._tab()	, "S2"		, BG_STATION.id_bg_station		, SQLFieldMAP.INNER			, BG_LADUNG.id_bg_station	,  "L2", "", vFieldsSTATION, "S2_");
//        this._join(ADRESSE._tab()		, "ADR_KD"	, ADRESSE.id_adresse			, SQLFieldMAP.INNER			, BG_STATION.id_adresse		, "S2", "", vFieldsADRKD, "ADR_KD_");
//        this._join(ADRESSE._tab()		, "ADR_KD_B", ADRESSE.id_adresse			, SQLFieldMAP.INNER			, BG_STATION.id_adresse_basis, "S2", "", vFieldsADRKD, "ADR_KD_B_");
//        
//        
//        // Artikel 
//        this._join(ARTIKEL._tab()		, "ART"		, ARTIKEL.id_artikel			, SQLFieldMAP.LEFT_OUTER	, BG_LADUNG.id_artikel		, BG_LADUNG._tab().fullTableName()	, "", vFieldsART, "ART_");
//        
//        // Artikel-BEZ
//		this._join(ARTIKEL_BEZ._tab()	,"JT_ARTIKEL_BEZ" ,ARTIKEL_BEZ.id_artikel_bez, SQLFieldMAP.LEFT_OUTER	, BG_LADUNG.id_artikel_bez	,BG_LADUNG._tab().fullTableName(),"",vFieldsARTBEZ, "ARTBEZ_");  
//       
		// Einheit-Gewicht
		this._join(EINHEIT._tab()		,"EG" 		,EINHEIT.id_einheit				, SQLFieldMAP.LEFT_OUTER	, ARTIKEL.id_einheit		,"ART",			"",vFieldsEHG, "EG_");
		
		// Einheit-Preis
		this._join(EINHEIT._tab()		,"EP" 		,EINHEIT.id_einheit				, SQLFieldMAP.LEFT_OUTER	, ARTIKEL.id_einheit_preis	,"ART",			"",vFieldsEHP, "EP_");
		
		
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
		
		
		
		/**
		 * Abrechenbar anhand des Besitzuebergangs ermitteln
		 */
//		String sSqlFieldAbrechenbar = 
//				" CASE WHEN (" 
//				+ 	BG_LADUNG.id_adresse_besitzer.tnfn() + " = " + bibALL.get_ID_ADRESS_MANDANT() + "OR L2." + BG_LADUNG.id_adresse_besitzer.fn() + " = " + bibALL.get_ID_ADRESS_MANDANT() 
//				+ " 		) "
//				+ " AND " + BG_LADUNG.id_adresse_besitzer.tnfn() +" !=  L2." + BG_LADUNG.id_adresse_besitzer.fn() 
//				+ "				THEN 'Y' "
//				+ "             ELSE 'N' " 
//				+ " END ";
		
		
//		this.add_SQLField(
//				new SQLField(sSqlFieldAbrechenbar,
//				"ABRECHENBAR", 
//				new MyE2_String("ABRECHENBAR"), 
//				bibE2.get_CurrSession()), 
//				true);

		
		// Lageradresse
		this.add_SQLField(
				new SQLField(" NVL2(ADR.ORT,ADR.ORT,'') ||  NVL2(ADR.PLZ,' (' || ADR.PLZ || '), ','') || NVL(ADR.NAME1,'') ||' ' || NVL(ADR.NAME2,'')  || NVL2(ADR_LIEFER.LA_BESCHREIBUNG, ' - ' || ADR_LIEFER.LA_BESCHREIBUNG,'')",
				"LAGER_INFO", 
				new MyE2_String("LAGER_INFO"), 
				bibE2.get_CurrSession()), 
				true);
		

		// Kundenadresse
		this.add_SQLField(
				new SQLField(" ADR_KD.NAME1 || NVL2(ADR_KD.ORT,', ' || ADR_KD.ORT,'') || ' ('|| ADR_KD.ID_ADRESSE || ')' ",
				"KUNDEN_INFO", 
				new MyE2_String("KUNDEN_INFO"), 
				bibE2.get_CurrSession()), 
				true);

		// Kundeninfo Basis
		this.add_SQLField(
				new SQLField(" ADR_KD_B.NAME1 || NVL2(ADR_KD_B.ORT,', ' || ADR_KD_B.ORT,'') || ' ('|| ADR_KD_B.ID_ADRESSE || ')' ",
				"KUNDEN_INFO_BASIS", 
				new MyE2_String("KUNDEN_INFO"), 
				bibE2.get_CurrSession()), 
				true);
		
		
		this.add_SQLField(
				new SQLField("NVL(ART.ANR1,'') || '-' ||NVL(JT_ARTIKEL_BEZ.ANR2,'') || ' - ' ||NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'')",
				"ART_INFO", 
				new MyE2_String("Artikelinfo"), 
				bibE2.get_CurrSession()), 
				true);

    
        
		/**
		 * Eigenware Fremdware
		 */
//		String sSqlFieldEWFW = 
//				  " CASE WHEN (" + BG_LADUNG.id_adresse_besitzer.tnfn() + "  = " + bibALL.get_ID_ADRESS_MANDANT() 
//				+ " OR L2." + BG_LADUNG.id_adresse_besitzer.fn() + " = " + bibALL.get_ID_ADRESS_MANDANT() + " ) " 
//				+ " 				THEN 'Y' "
//				+ "                 ELSE 'N' " 
//				+ " END ";
//		
//		this.add_SQLField(
//				new SQLField(sSqlFieldEWFW,
//				"EW_FW", 
//				new MyE2_String("EW_FW"), 
//				bibE2.get_CurrSession()), 
//				true);
        
		
//		/**
//		 * WENetto
//		 */
//		String sSqlFieldWENetto = "CASE WHEN " + BG_LADUNG.mengenvorzeichen.tnfn() + " = 1  THEN " +  BG_LADUNG.menge_netto.tnfn() + " ELSE null END "; 
//		this.add_SQLField(
//				new SQLField(sSqlFieldWENetto,
//				BG_Lager_Bewegung_CONST.MENGE_NETTO_WE,
//				new MyE2_String("WE Netto"), 
//				bibE2.get_CurrSession()), 
//				true);
//		
//		/**
//		 * WEBrutto
//		 */
//		String sSqlFieldWEBrutto = "CASE WHEN " + BG_LADUNG.mengenvorzeichen.tnfn() + " = 1  THEN " +  BG_LADUNG.menge.tnfn() + " ELSE null END "; 
//		this.add_SQLField(
//				new SQLField(sSqlFieldWEBrutto,
//				BG_Lager_Bewegung_CONST.MENGE_BRUTTO_WE, 
//				new MyE2_String("WE Brutto"), 
//				bibE2.get_CurrSession()), 
//				true);
//
//		/**
//		 * WANetto
//		 */
//		String sSqlFieldWANetto = "CASE WHEN " + BG_LADUNG.mengenvorzeichen.tnfn() + " = -1  THEN " +  BG_LADUNG.menge_netto.tnfn()  + " ELSE null END ";  
//		this.add_SQLField(
//				new SQLField(sSqlFieldWANetto,
//				BG_Lager_Bewegung_CONST.MENGE_NETTO_WA, 
//				new MyE2_String("WA Netto"), 
//				bibE2.get_CurrSession()), 
//				true);
//
//		/**
//		 * WABrutto
//		 */
//		String sSqlFieldWABrutto = "CASE WHEN " + BG_LADUNG.mengenvorzeichen.tnfn() + " = -1  THEN " +  BG_LADUNG.menge.tnfn() + " ELSE null END "; 
//		this.add_SQLField(
//				new SQLField(sSqlFieldWABrutto,
//				BG_Lager_Bewegung_CONST.MENGE_BRUTTO_WA, 
//				new MyE2_String("WA Brutto"), 
//				bibE2.get_CurrSession()), 
//				true);
//
//		/**
//		 * MENGE_VZ
//		 */
//		String MENGE_VZ =  BG_LADUNG.menge.tnfn() + " * " + BG_LADUNG.mengenvorzeichen.tnfn() ; 
//		this.add_SQLField(
//				new SQLField(MENGE_VZ,
//						BG_Lager_Bewegung_CONST.MENGE_BRUTTO_VZ, 
//				new MyE2_String("Menge"), 
//				bibE2.get_CurrSession()), 
//				true);
//		
//		/**
//		 * MENGE_VZ
//		 */
//		String MENGE_NETTO_VZ =  BG_LADUNG.menge_netto.tnfn() + " * " + BG_LADUNG.mengenvorzeichen.tnfn() ; 
//		this.add_SQLField(
//				new SQLField(MENGE_NETTO_VZ,
//				BG_Lager_Bewegung_CONST.MENGE_NETTO_VZ,
//				new MyE2_String("Menge netto"), 
//				bibE2.get_CurrSession()), 
//				true);
//		
//		
//		/**
//		 * MENGE_VZ
//		 */
//		String STORNO =  
//				  " CASE WHEN (" + BG_LADUNG.id_bg_storno_info.tnfn() + "  is null  ) " 
//				+ " 				THEN 'N' "
//				+ "                 ELSE 'Y' " 
//				+ " END ";
// 
//		this.add_SQLField(
//				new SQLField(STORNO,
//				BG_Lager_Bewegung_CONST.LADUNG_STORNO,
//				new MyE2_String("Storniert"), 
//				bibE2.get_CurrSession()), 
//				true);
//		
//		
//		
//		
//		/**
//		 * MENGE_VZ
//		 */
//		String DELETED =  
//				  " CASE WHEN (" + BG_LADUNG.id_bg_del_info.tnfn() + "  is null  ) " 
//				+ " 				THEN 'N' "
//				+ "                 ELSE 'Y' " 
//				+ " END ";
// 
//		this.add_SQLField(
//				new SQLField(DELETED,
//				BG_Lager_Bewegung_CONST.LADUNG_DELETED,
//				new MyE2_String("Gelöscht"), 
//				bibE2.get_CurrSession()), 
//				true);
//		
//		

		
		
		

        
		
		// keine gelöschten Ladungen selektieren
		// this.add_BEDINGUNG_STATIC(BG_LADUNG.id_bg_del_info.tnfn() + " is null ");

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
 
