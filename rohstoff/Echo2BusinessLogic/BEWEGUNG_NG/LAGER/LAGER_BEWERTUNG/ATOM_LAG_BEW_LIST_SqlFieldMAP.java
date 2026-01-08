package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM_VERBUCHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM_VERBUCHT_K;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_SETZKASTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_SETZKASTEN_K;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_BEW_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public ATOM_LAG_BEW_LIST_SqlFieldMAP(boolean bPreiseMitKosten) throws myException {
		
		super("JT_BEWEGUNG_ATOM", "", false);
	
		// INNER JOIN -> JT_BEWEGUNG_STATION (die Wareneingänge mit Mengenvorzeichen == 1) 
		this.add_JOIN_Table("JT_BEWEGUNG_STATION", 
				"S_LAG", 
				SQLFieldMAP.INNER, 
				":ID_ADRESSE:MENGENVORZEICHEN:",
				true, 
				"JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM=S_LAG.ID_BEWEGUNG_ATOM AND S_LAG.MENGENVORZEICHEN = 1 ", 
				"", 
				null);
				
		// INNER JOIN -> JT_BEWEGUNG_ATOM (die Lieferseite des Wareneingangs des ATOMS mit Mengenvorzeichen == -1) 
		this.add_JOIN_Table("JT_BEWEGUNG_STATION", 
				"S_LIEF", 
				SQLFieldMAP.INNER, 
				"",
				true, 
				"JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM=S_LIEF.ID_BEWEGUNG_ATOM AND S_LIEF.MENGENVORZEICHEN = -1 ", 
				"", 
				null);
		
		// INNER JOIN -> JT_BEWEGUNG_VEKTOR (Der Vektor für die Kosten) 
		this.add_JOIN_Table("JT_BEWEGUNG_VEKTOR", 
				"V", 
				SQLFieldMAP.INNER, 
				"",
				true, 
				"JT_BEWEGUNG_ATOM.ID_BEWEGUNG_VEKTOR=V.ID_BEWEGUNG_VEKTOR", 
				"", 
				null);
		
		// INNER JOIN -> JT_ARTIKEL (Nur Atome mit Artikeln sind relevant)
		this.add_JOIN_Table("JT_ARTIKEL", 
				"ART", 
				SQLFieldMAP.INNER, 
				//":ANR1:ARTBEZ:ID_ARTIKEL:",
				"",
				
				true, 
				"JT_BEWEGUNG_ATOM.ID_ARTIKEL=ART.ID_ARTIKEL", 
				"", 
				bibALL.get_HashMap("ART_INFO", " NVL(ART.ANR1,'') || ' - ' ||NVL(ART.ARTBEZ1,'')"));
		
				
		// LEFT OUTER JOIN -> JT_ADRESSE (Lageradresse) 
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_LAG", 
				SQLFieldMAP.LEFT_OUTER, 
				//":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:",
				"",
				true, 
				"S_LAG.ID_ADRESSE=ADR_LAG.ID_ADRESSE", 
				"", 
    		bibALL.get_HashMap("ADDRESS_INFO", " NVL(ADR_LAG.ORT,'') || ' (' || NVL(ADR_LAG.PLZ,'') || '), ' || NVL(ADR_LAG.NAME1,'') ||' ' || NVL(ADR_LAG.NAME2,'') "));

		
		// LEFT OUTER JOIN -> JT_ADRESSE (Lageradresse) 
		this.add_JOIN_Table("JT_ADRESSE", 
				"ADR_LIEF", 
				SQLFieldMAP.LEFT_OUTER, 
				//":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:",
				"",
				true, 
				"S_LIEF.ID_ADRESSE=ADR_LIEF.ID_ADRESSE", 
				"", 
    		bibALL.get_HashMap("LIEFER_INFO", " NVL(ADR_LIEF.ORT,'') || ' (' || NVL(ADR_LIEF.PLZ,'') || '), ' || NVL(ADR_LIEF.NAME1,'') ||' ' || NVL(ADR_LIEF.NAME2,'') "));

		
		// LEFT OUTER JOIN -> JT_BEWEGUNG 
		this.add_JOIN_Table("JT_BEWEGUNG", 
				"JT_BEWEGUNG", 
				SQLFieldMAP.LEFT_OUTER, 
//				":ID_VPOS_TPA_FUHRE:NAME2:PLZ:ORT:",
				"",
				true, 
				"JT_BEWEGUNG_ATOM.ID_BEWEGUNG=JT_BEWEGUNG.ID_BEWEGUNG", 
				"",
				null);
	
		// LEFT OUTER JOIN -> JT_BEWEGUNG_ATOM_VERBUCHT
		
		String sTableVerbucht = bPreiseMitKosten ? RECORD_BEWEGUNG_ATOM_VERBUCHT_K.TABLENAME : RECORD_BEWEGUNG_ATOM_VERBUCHT.TABLENAME;
		this.add_JOIN_Table(sTableVerbucht, 
				"AV", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true, 
				"JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM=AV.ID_BEWEGUNG_ATOM", 
				"",
				null);
				
		String sTableSetzkasten = bPreiseMitKosten ? RECORD_BEWEGUNG_SETZKASTEN_K.TABLENAME : RECORD_BEWEGUNG_SETZKASTEN.TABLENAME;
		
		this.add_SQLField(
				new SQLField(" (SELECT sum(menge) FROM " + bibE2.cTO()+ "." + sTableSetzkasten + " K WHERE K.ID_ATOM_EINGANG = JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM)  ",
				"VERBUCHT",
				new MyString("Verbucht"),
				bibE2.get_CurrSession()), 
				true);
		
		this.add_SQLField(new SQLField("( nvl(JT_BEWEGUNG_ATOM.MENGE_NETTO , 0.0) - " +
				"(SELECT sum(menge) FROM " + bibE2.cTO()+ "." + sTableSetzkasten + " K WHERE K.ID_ATOM_EINGANG = JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM) ) " ,
				"OFFEN",
				new MyString("OFFEN"),
				bibE2.get_CurrSession()), true);				
				
		this.add_SQLField(
				new SQLField("NVL(S_LIEF.NAME1, ADR_LIEF.NAME1) || NVL2(S_LIEF.NAME2,' ' || S_LIEF.NAME2,'') || ', ' ||  NVL(S_LIEF.ORT,'') || ' (' || TO_CHAR(S_LIEF.ID_ADRESSE) || ')' ",
				"NAME_GEGEN", 
				new MyE2_String("Ladeort"), 
				bibE2.get_CurrSession()), 
				true
				);

		/**
		 * Preis
		 */
		
		String sColumnPreis = bPreiseMitKosten ? "case when nvl(jt_bewegung_atom.MENGE_NETTO,0) != 0 then ROUND(((JT_BEWEGUNG_ATOM.E_PREIS_RESULT_NETTO_MGE * (JT_BEWEGUNG_ATOM.MENGE_NETTO/NVL(ART.MENGENDIVISOR,1)) ) + NVL(V.KOSTEN_TRANSPORT_WE,0) + NVL(V.KOSTEN_PRODUKT_WE,0) ) / NVL(JT_BEWEGUNG_ATOM.MENGE_NETTO / NVL(ART.MENGENDIVISOR,1),1),2) else null end " : "JT_BEWEGUNG_ATOM.E_PREIS_RESULT_NETTO_MGE" ;
		this.add_SQLField(
				new SQLField(sColumnPreis ,
				"PREIS", 
				new MyE2_String("Preis"), 
				bibE2.get_CurrSession()), 
				true
				);
				
				 				
				
//				this.add_SQLField(new SQLField("ADR_GEGEN.ID_ADRESSE_GEGEN","LA_ID_ADRESSE_LIEFER", new MyE2_String("ID Kunde"), bibE2.get_CurrSession()), true);

		this.add_BEDINGUNG_STATIC(" S_LAG.ID_ADRESSE IN ( select id_adresse from " + bibE2.cTO() + ".V_FIRMENADRESSEN_FLACH where ID_ADRESSE_BASIS = " + bibALL.get_ID_ADRESS_MANDANT() +  " and V_FIRMENADRESSEN_FLACH.SONDERLAGER is null)" );
		this.add_BEDINGUNG_STATIC(" nvl(JT_BEWEGUNG_ATOM.DELETED,'N') = 'N'" );
		this.add_BEDINGUNG_STATIC(" nvl(JT_BEWEGUNG_ATOM.STORNIERT,'N') = 'N'" );
		this.add_BEDINGUNG_STATIC(" JT_BEWEGUNG_ATOM.LEISTUNGSDATUM IS NOT NULL " );
		
		
		this.add_ORDER_SEGMENT(	"ROUND(((JT_BEWEGUNG_ATOM.E_PREIS_RESULT_NETTO_MGE * (JT_BEWEGUNG_ATOM.MENGE_NETTO/NVL(ART.MENGENDIVISOR,1)) ) + NVL(V.KOSTEN_TRANSPORT_WE,0) + NVL(V.KOSTEN_PRODUKT_WE,0) ) / NVL((case when JT_BEWEGUNG_ATOM.MENGE_NETTO=0 then 1 else JT_BEWEGUNG_ATOM.MENGE_NETTO end) / NVL(ART.MENGENDIVISOR,1),1),2), ADR_LAG.NAME1,    JT_BEWEGUNG_ATOM.MENGE_NETTO");

				 
		this.initFields();
	}
	
	/*
	
	private static final long serialVersionUID = 333486641275451569L;

	public ATOM_LAG_BEW_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_LAGER_KONTO", "", false);
		
		this.add_JOIN_Table("JT_ARTIKEL", 
				"JT_ARTIKEL", 
				SQLFieldMAP.LEFT_OUTER, 
				":ANR1:ARTBEZ:ID_ARTIKEL:",
				true, 
				"JT_LAGER_KONTO.ID_ARTIKEL_SORTE = JT_ARTIKEL.ID_ARTIKEL ", "", 
				bibALL.get_HashMap("ART_INFO", " NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,'')"));
		
		
		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:NAME2:PLZ:ORT:ID_ADRESSE:",
				true,
				"JT_LAGER_KONTO.ID_ADRESSE_LAGER=JT_ADRESSE.ID_ADRESSE ", "", 
//			bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.NAME1,'') || ' '|| NVL(JT_ADRESSE.NAME2,'')|| ', '|| NVL(JT_ADRESSE.PLZ,'') || ' ' || NVL(JT_ADRESSE.ORT,'')"));
     		bibALL.get_HashMap("ADDRESS_INFO", " NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') "));

		this.add_JOIN_Table("JT_VPOS_TPA_FUHRE", 
				"JT_VPOS_TPA_FUHRE", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE = JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE ", "",null); 
		
		this.add_JOIN_Table("JT_LIEFERADRESSE", 
				"JT_LIEFERADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":BEZEICHNUNG:", 
				true, 
				" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER ",
				"", null
				);

		
		
		//	bibALL.get_HashMap("FUHRE_INFO", " NVL(JT_VPOS_TPA_FUHRE.L_NAME1,'') || ' '|| NVL(JT_VPOS_TPA_FUHRE.L_NAME2,'')|| ', '|| NVL(JT_VPOS_TPA_FUHRE.L_PLZ,'') || ' ' || NVL(JT_VPOS_TPA_FUHRE.L_ORT,'')"));
		this.add_SQLField(new SQLField("NVL(JT_VPOS_TPA_FUHRE.L_NAME1,'') || ' '|| NVL(JT_VPOS_TPA_FUHRE.L_NAME2,'')|| ', '|| NVL(JT_VPOS_TPA_FUHRE.L_PLZ,'') || ' ' || NVL(JT_VPOS_TPA_FUHRE.L_ORT,'')"  ,
				"LIEFERANT_INFO",
				new MyString("Lieferant"),
				bibE2.get_CurrSession()), true);

		
		
		this.add_JOIN_Table("JT_VPOS_TPA_FUHRE_ORT", 
				"JT_VPOS_TPA_FUHRE_ORT", 
				SQLFieldMAP.LEFT_OUTER, 
				"",
				true,
				" JT_LAGER_KONTO.ID_VPOS_TPA_FUHRE_ORT = JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE_ORT ", "", 
			bibALL.get_HashMap("FUHRE_ORT_INFO", " NVL(JT_VPOS_TPA_FUHRE_ORT.NAME1,'') || ' '|| NVL(JT_VPOS_TPA_FUHRE_ORT.NAME2,'')|| ', '|| NVL(JT_VPOS_TPA_FUHRE_ORT.PLZ,'') || ' ' || NVL(JT_VPOS_TPA_FUHRE_ORT.ORT,'')"));
		
		
		
		
		
		this.add_SQLField(new SQLField("(Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " + 
										" FROM JT_LAGER_BEWEGUNG WHERE JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO )",
										"VERBUCHT",
										new MyString("Verbucht"),
										bibE2.get_CurrSession()), true);
		
		
		
		this.add_SQLField(new SQLField("( nvl(JT_LAGER_KONTO.MENGE,0.0) - " +
										"(Select sum(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0.0)) " + 
										" FROM JT_LAGER_BEWEGUNG WHERE JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO ) " +
										") ",
										"OFFEN",
										new MyString("OFFEN"),
										bibE2.get_CurrSession()), true);
		
		// Wherebedingung um die Werte vor der Inventur nicht zu berücksichtigen
		String sWhereInventurdatum = 
			" AND NVL( (SELECT NVL(max(I.BUCHUNGSDATUM), to_date('2000-01-01','yyyy-MM-dd') ) AS INVENTURDATUM " +
			" FROM JT_LAGER_INVENTUR I " +
			" WHERE I.ID_ADRESSE_LAGER = JT_LAGER_KONTO.ID_ADRESSE_LAGER AND I.ID_ARTIKEL_SORTE = JT_LAGER_KONTO.ID_ARTIKEL_SORTE " +
			" GROUP BY I.ID_ADRESSE_LAGER, I.ID_ARTIKEL_SORTE " +
			") , to_date('2000-01-01','yyyy-MM-dd') )  < JT_LAGER_KONTO.BUCHUNGSDATUM ";

		String sWhere =    " JT_LAGER_KONTO.BUCHUNGSTYP      = 'WE' " +
						   " AND ( " +
						   "     JT_LAGER_KONTO.STORNO is null"+ 
						   "     or JT_LAGER_KONTO.STORNO = 'N'"+
						   " ) " +
						   sWhereInventurdatum;
					
	
		this.add_BEDINGUNG_STATIC(sWhere);

		//		this.add_ORDER_SEGMENT("NVL(JT_ADRESSE.NAME1,'') || ' '|| NVL(JT_ADRESSE.NAME2,'')|| ', '|| NVL(JT_ADRESSE.PLZ,'') || ' ' || NVL(JT_ADRESSE.ORT,''),  NVL(JT_ARTIKEL.ANR1,'') || ' - ' ||NVL(JT_ARTIKEL.ARTBEZ1,''),PREIS");
		this.add_ORDER_SEGMENT("NVL(JT_ADRESSE.ORT,'') || ' (' || NVL(JT_ADRESSE.PLZ,'') || '), ' || NVL(JT_ADRESSE.NAME1,'') ||' ' || NVL(JT_ADRESSE.NAME2,'') || NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,''),PREIS");
	 */
}
