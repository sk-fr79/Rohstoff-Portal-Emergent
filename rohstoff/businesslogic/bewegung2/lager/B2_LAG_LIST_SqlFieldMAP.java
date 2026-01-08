 
package rohstoff.businesslogic.bewegung2.lager;
  
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_AUSWERT;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST.B2_LAG_NAMES;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST.Table_alias;
  
public class B2_LAG_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    /*
    /					VEKTOR
    /			ATOM1				ATOM2
    /STATION1			STATION2			STATION3
     */
    public B2_LAG_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
    	
        super(_TAB.bg_atom.n(),"",false);
        
        this.m_tpHashMap = p_tpHashMap;        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
        
        String vektor_alias = 	Table_alias.VEKTOR	.name();
        String atom1_alias =	Table_alias.ATOM	.tab().n();
        String station_links = 	Table_alias.STATION1.tab().n();
        String station_rechts = Table_alias.STATION2.name();
        String join_artbez = 	Table_alias.ART_BEZ	.name();
        String join_art = 		Table_alias.ART		.name();
        String join_einheit = 	Table_alias.EINHEIT	.name();
        String join_waehrung =	Table_alias.WAEHRUNG.name();
       
        String atom1_pos_a1 = 	new vgl(atom1_alias, BG_ATOM.pos_in_mask,EnTabKeyInMask.A1.dbVal()).s();
        
        this.add_JOIN_Table(_TAB.bg_vektor.n()
        		,vektor_alias
        		,INNER
        		,":"+BG_VEKTOR.planmenge.fn()+":"+BG_VEKTOR.en_transport_typ.fn()+":"
        		,true
        		,BG_VEKTOR.id_bg_vektor.fn(vektor_alias) + "=" + BG_ATOM.id_bg_vektor.fn(atom1_alias)
        		,""
        		,null);
			
        this.add_JOIN_Table(_TAB.bg_station.n() 
				,station_links 
				,INNER 
				,":"+BG_STATION.id_bg_station.fn(station_links)+":"
				,true 
				,BG_STATION.id_bg_station.fn(station_links)+" = " + BG_ATOM.id_bg_station_quelle.fn(atom1_alias)
				,"" 
				,null);
        
        this.add_JOIN_Table(_TAB.bg_station.n() 
				,station_rechts 
				,INNER 
				,":"+BG_STATION.id_bg_station.fn(station_rechts)+":"
				,true 
				,BG_STATION.id_bg_station.fn(station_rechts)+" = " + BG_ATOM.id_bg_station_ziel.fn(atom1_alias)
				,"" 
				,null);
        
        String join_bed_artbez = BG_ATOM.id_artikel_bez.fn(atom1_alias);
		this.add_JOIN_Table(B2_LAG_CONST.Table_alias.ART_BEZ.tab().fullTableName()
				,join_artbez 
				,LEFT_OUTER
				,":"+ARTIKEL_BEZ.id_artikel_bez.fn(join_artbez)+":"+ARTIKEL_BEZ.anr2.fn(join_artbez)+":"+ARTIKEL_BEZ.artbez1.fn(join_artbez)+":"+":"+ARTIKEL_BEZ.artbez2.fn(join_artbez)+":"
				,true 
				,ARTIKEL_BEZ.id_artikel_bez.fn(join_artbez) +"=" + join_bed_artbez 
				,"" 
				,null);
		
		this.add_JOIN_Table(B2_LAG_CONST.Table_alias.ART.tab().fullTableName()
				,join_art 
				,LEFT_OUTER
				,":"+ARTIKEL.id_artikel.fn(join_art)+":"+":"+ARTIKEL.anr1.fn(join_art)+":"
        		,true 
        		,ARTIKEL.id_artikel.fn(join_art) +"="+ "NVL(" + BG_ATOM.id_artikel.fn(atom1_alias)+","+ARTIKEL_BEZ.id_artikel.fn(join_artbez)+")"
				,"" 
				,null);

		this.add_JOIN_Table(B2_LAG_CONST.Table_alias.EINHEIT.tab().fullTableName()
				,join_einheit 
				,LEFT_OUTER
				,":"+EINHEIT.einheitlang.fn()+":"+EINHEIT.einheitkurz.fn()+":"
				,true 
				,EINHEIT.id_einheit.fn(join_einheit) +"="+ ARTIKEL.id_einheit.fn(join_art) 
				,"" 
				,null);
        
		this.add_JOIN_Table(
				_TAB.bg_auswert.n()
				,_TAB.bg_auswert.n()
				,INNER
				,":STATION_KZ:" 
				,true
				,"(JT_BG_AUSWERT.STATION_KZ<>0 AND " + BG_AUSWERT.id_bg_atom.tnfn() + "=" + BG_ATOM.id_bg_atom.fn(atom1_alias)+")"
				,""
				,null);
		
		this.add_JOIN_Table("JT_LIEFERADRESSE", 
				"LA", 
				SQLFieldMAP.LEFT_OUTER, 
				":"	, 
				true, 
				"JT_BG_AUSWERT.ID_ADRESSE=LA.ID_ADRESSE_LIEFER", 
				"", 
			null);
		
		
        String join_waehrung_art = BG_ATOM.id_waehrung.fn(atom1_alias);
        this.add_JOIN_Table(_TAB.waehrung.fullTableName()
				,join_waehrung 
				,LEFT_OUTER
				,":"+WAEHRUNG.kurzbezeichnung.fn()+":"+WAEHRUNG.bezeichnung.fn()+":"
				,true 
				,WAEHRUNG.id_waehrung.fn(join_waehrung) +"="+ join_waehrung_art
				,"" 
				,null);
        
		String sSqlFieldEWFW = " CASE WHEN " 
		+ BG_STATION.id_adresse_besitz_ldg.fn(station_links) +"=" 	+ bibALL.get_ID_ADRESS_MANDANT() + " OR "
		+ BG_STATION.id_adresse_besitz_ldg.fn(station_rechts) +"="	+ bibALL.get_ID_ADRESS_MANDANT() 
		+ " THEN 'Y' ELSE 'N' END ";
		this.add_SQLField(
				new SQLField(sSqlFieldEWFW,
				"EW_FW", 
				S.ms("EW_FW"), 
				bibE2.get_CurrSession()), 
				true);
		
		String sSqlFieldAbrechenbar = " CASE WHEN "
		+ "(" + BG_STATION.id_adresse_besitz_ldg.fn(station_links)+ "=" + bibALL.get_ID_ADRESS_MANDANT() + " OR " 
		+ BG_STATION.id_adresse_besitz_ldg.fn(station_rechts) +"=" + bibALL.get_ID_ADRESS_MANDANT() + ")" +" AND "
		+ "(" + BG_STATION.id_adresse_besitz_ldg.fn(station_links)+" != " + BG_STATION.id_adresse_besitz_ldg.fn(station_rechts)
		+ "	) THEN 'Y' ELSE 'N' END ";
		this.add_SQLField(
				new SQLField(sSqlFieldAbrechenbar,
				"ABRECHENBAR_BESITZ", 
				S.ms("ABRECHENBAR_BESITZ"), 
				bibE2.get_CurrSession()), 
				true);
		
		
		this.add_SQLField(new SQLField(BG_VEKTOR.id_bg_vektor.fn(vektor_alias),  											B2_LAG_NAMES.SHOW_TYPE.db_val(), B2_LAG_NAMES.SHOW_TYPE.user_text_ms()),true);
		this.add_SQLField(new SQLField(ARTIKEL_BEZ.artbez2.fn(join_artbez), ARTIKEL_BEZ.artbez2.fn(), S.ms("Artbez2"), bibE2.get_CurrSession()), true);
		
		String art_id = "NVL("+ARTIKEL.id_artikel.fn(join_art)+",NVL2(" + BG_ATOM.id_artikel.fn(atom1_alias)+","+ARTIKEL_BEZ.id_artikel.fn(join_artbez)+",0))";
		this.add_SQLField(new SQLField(art_id,B2_LAG_NAMES.SORTE_INFO.db_val(), S.ms("Artikelinfo"), bibE2.get_CurrSession()), true);
		
		String station1_bed ="(case when "+atom1_pos_a1 + " then " + BG_STATION.id_adresse.fn(station_links) + " else " + "null end)";
		this.add_SQLField(new SQLField(station1_bed,		B2_LAG_NAMES.STARTLAGER_ID.db_val(), B2_LAG_NAMES.STARTLAGER_ID.user_text_ms(), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField(station1_bed,		B2_LAG_NAMES.STARTLAGER_ADRESSE.db_val(), B2_LAG_NAMES.STARTLAGER_ADRESSE.user_text_ms(), bibE2.get_CurrSession()), true);
		
		String station2_bed ="(case when "+atom1_pos_a1 + " then " + BG_STATION.id_adresse.fn(station_rechts) + " else " 
				+ BG_STATION.id_adresse.fn(station_links)+" end)";
		this.add_SQLField(new SQLField(station2_bed,	B2_LAG_NAMES.ZWISCHENLAGER_ID.db_val()		, B2_LAG_NAMES.ZWISCHENLAGER_ID.user_text_ms(), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField(station2_bed,	B2_LAG_NAMES.ZWISCHENLAGER_ADRESSE.db_val()	, B2_LAG_NAMES.ZWISCHENLAGER_ADRESSE.user_text_ms(), bibE2.get_CurrSession()), true);

		String station3_bed ="(case when "+atom1_pos_a1 + " then null else " + BG_STATION.id_adresse.fn(station_rechts) + " end)";
		this.add_SQLField(new SQLField(station3_bed,	B2_LAG_NAMES.ZIELLAGER_ID.db_val(), B2_LAG_NAMES.ZIELLAGER_ID.user_text_ms(), bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField(station3_bed,	B2_LAG_NAMES.ZIELLAGER_ADRESSE.db_val(), B2_LAG_NAMES.ZIELLAGER_ADRESSE.user_text_ms(), bibE2.get_CurrSession()), true);
		
		String we_mge_rech 	= "(case when station_kz=1 " +" then " +BG_ATOM.menge.fn(atom1_alias) + " else null end )";
		//String mge_brutto_bed = "JT_BG_AUSWERT.station_kz*" + BG_ATOM.menge.fn(atom1_alias);
		this.add_SQLField(new SQLField(we_mge_rech,			B2_LAG_NAMES.WE_MENGE_BRUTTO.db_val(),	S.ms("Menge_brutto"), 		bibE2.get_CurrSession()), true);
		
		String we_mge_abz 	= "(case when station_kz=1 " +" then " +BG_ATOM.menge_abzug.fn(atom1_alias) + " else null end )";
		//String mge_abzug_bed = "JT_BG_AUSWERT.station_kz*" + BG_ATOM.menge_abzug.fn(atom1_alias);
		this.add_SQLField(new SQLField(we_mge_abz,		B2_LAG_NAMES.WE_MENGE_ABZUG.db_val(), 	S.ms("Menge_abzug"), 		bibE2.get_CurrSession()), true);
		
		String we_mge_netto 	= "(case when station_kz=1 " +" then " +BG_ATOM.menge_netto.fn(atom1_alias) + " else null end )";
		//String mge_netto_bed = "JT_BG_AUSWERT.station_kz*" + BG_ATOM.menge_netto.fn(atom1_alias);
		this.add_SQLField(new SQLField(we_mge_netto,		B2_LAG_NAMES.WE_MENGE_NETTO.db_val(), 	S.ms("Menge_netto"), 		bibE2.get_CurrSession()), true);
		
		String wa_mge_rech 	= "(case when station_kz=-1 " +" then -1*" +BG_ATOM.menge.fn(atom1_alias) + " else null end )";
		//String mge_brutto_bed = "JT_BG_AUSWERT.station_kz*" + BG_ATOM.menge.fn(atom1_alias);
		this.add_SQLField(new SQLField(wa_mge_rech,			B2_LAG_NAMES.WA_MENGE_BRUTTO.db_val(),	S.ms("Menge_brutto"), 		bibE2.get_CurrSession()), true);
		
		String wa_mge_abz 	= "(case when station_kz=-1 " +" then -1*" +BG_ATOM.menge_abzug.fn(atom1_alias) + " else null end )";
		//String mge_abzug_bed = "JT_BG_AUSWERT.station_kz*" + BG_ATOM.menge_abzug.fn(atom1_alias);
		this.add_SQLField(new SQLField(wa_mge_abz,		B2_LAG_NAMES.WA_MENGE_ABZUG.db_val(), 	S.ms("Menge_abzug"), 		bibE2.get_CurrSession()), true);
		
		String wa_mge_netto 	= "(case when station_kz=-1 " +" then -1*" +BG_ATOM.menge_netto.fn(atom1_alias) + " else null end )";
		//String mge_netto_bed = "JT_BG_AUSWERT.station_kz*" + BG_ATOM.menge_netto.fn(atom1_alias);
		this.add_SQLField(new SQLField(wa_mge_netto,		B2_LAG_NAMES.WA_MENGE_NETTO.db_val(), 	S.ms("Menge_netto"), 		bibE2.get_CurrSession()), true);
		
		/*
		//WE -> menge brutto
		String we_mge_rech 	= "(case"+ " when " + atom1_pos_a2.s() +" then " +BG_ATOM.menge.fn(atom1_alias) + " else null end )";
		this.add_SQLField(new SQLField(we_mge_rech, 		B2_LAG_NAMES.WE_MENGE_BRUTTO.db_val(),	B2_LAG_NAMES.WE_MENGE_BRUTTO.user_text_ms(),bibE2.get_CurrSession()), true);
		//WE -> menge netto
		String we_mge_netto_rech = "(case " + " when " + atom1_pos_a2.s() +" then " +BG_ATOM.menge_netto.fn(atom1_alias) +" else null end)";
		this.add_SQLField(new SQLField(we_mge_netto_rech, 	B2_LAG_NAMES.WE_MENGE_NETTO.db_val(),	B2_LAG_NAMES.WE_MENGE_NETTO.user_text_ms(), bibE2.get_CurrSession()), true);
		//WE -> abzug menge
		String we_mge_abzug_rech = "(case " + " when " + atom1_pos_a2.s() +" then " +BG_ATOM.menge_abzug.fn(atom1_alias) +" else null end)";
		this.add_SQLField(new SQLField(we_mge_abzug_rech, 	B2_LAG_NAMES.WE_MENGE_ABZUG.db_val(),	B2_LAG_NAMES.WE_MENGE_ABZUG.user_text_ms(), bibE2.get_CurrSession()), true);
		//WE -> menge brutto - abzug menge
		String mge_we_rech 			= "(case" + " when " + atom1_pos_a2.s() +" then " +BG_ATOM.menge.fn(atom1_alias)+"-NVL(" +BG_ATOM.menge_abzug.fn(atom1_alias)+",0)" + " else null end)";
		this.add_SQLField(new SQLField(mge_we_rech, 		B2_LAG_NAMES.WE_MENGE.db_val(),			B2_LAG_NAMES.WE_MENGE.user_text_ms(),bibE2.get_CurrSession()), true);
		
		//WA <- menge brutto
		String wa_mge_rech = "(case" + " when " + atom1_pos_a1.s() +" then "+BG_ATOM.menge.fn(atom1_alias)  + " else  null end) ";
		this.add_SQLField(new SQLField(wa_mge_rech, 		B2_LAG_NAMES.WA_MENGE_BRUTTO.db_val(),	B2_LAG_NAMES.WA_MENGE_BRUTTO.user_text_ms(),bibE2.get_CurrSession()), true);
		//WA <- menge netto
		String wa_mge_netto_rech 	= "(case"+ " when " + atom1_pos_a1.s() +" then "+BG_ATOM.menge_netto.fn(atom1_alias)+" else null end) ";
		this.add_SQLField(new SQLField(wa_mge_netto_rech,	B2_LAG_NAMES.WA_MENGE_NETTO.db_val(),	B2_LAG_NAMES.WA_MENGE_NETTO.user_text_ms(), bibE2.get_CurrSession()), true);
		//WA <- menge abzug
		String wa_mge_abzug_rech 	= "(case" + " when " + atom1_pos_a1.s() +" then "+ BG_ATOM.menge_abzug.fn(atom1_alias)+" else null  end) ";
		this.add_SQLField(new SQLField(wa_mge_abzug_rech,	B2_LAG_NAMES.WA_MENGE_ABZUG.db_val(),	B2_LAG_NAMES.WA_MENGE_ABZUG.user_text_ms(), bibE2.get_CurrSession()), true);
		//WA <- menge brutto - abzug menge
		String mge_wa_rech 			= "(case" + " when " + atom1_pos_a1.s() +" then "+ BG_ATOM.menge.fn(atom1_alias)+"-NVL(" +BG_ATOM.menge_abzug.fn(atom1_alias)+",0) else null end) ";
		this.add_SQLField(new SQLField(mge_wa_rech, 		B2_LAG_NAMES.WA_MENGE.db_val(),			B2_LAG_NAMES.WA_MENGE.user_text_ms(),bibE2.get_CurrSession()), true);
		*/
		//PLANMENGE
		String menge = "(case when " + atom1_pos_a1 +" then "+ BG_VEKTOR.planmenge.fn(vektor_alias)+" else -1*"+ BG_VEKTOR.planmenge.fn(vektor_alias) +" end) ";
		this.add_SQLField(new SQLField(menge, BG_VEKTOR.planmenge.fn(), S.ms("Planmenge"),bibE2.get_CurrSession()), true);
		this.add_SQLField(new SQLField(EINHEIT.einheitkurz.fn(Table_alias.EINHEIT.getAlias()), B2_LAG_NAMES.EINHEIT_KURZ.db_val(),	B2_LAG_NAMES.EINHEIT_KURZ.user_text_ms(),bibE2.get_CurrSession()), true);
		
        this.initFields();
    }
}
 
 
