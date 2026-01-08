package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch_CONST;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ELEMENT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAT_ELEMENT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAT_SPEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FS_LIST_DATASEARCH extends E2_DataSearch
{
	String cWhereZusatz = "JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO;

	public FS_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_ADRESSE","ID_ADRESSE", MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		String cTO = bibE2.cTO();
		
		
		this.addSearchDefADRESSE("VORNAME","Vorname",false);
		this.addSearchDefADRESSE("NAME1","Name 1",false);
		this.addSearchDefADRESSE("NAME2","Name 2",false);
		this.addSearchDefADRESSE("NAME3","Name 3",false);
		this.addSearchDefADRESSE("STRASSE","Strasse",false);
		this.addSearchDefADRESSE("PLZ","PLZ",false);
		this.addSearchDefADRESSE("ORT","Ort",false);
		this.addSearchDefADRESSE("ORTZUSATZ","Ort-Zusatz",false);
		
		this.addSearchDefADRESSE("ID_ADRESSE","KD-Nr",true);
		this.addSearchDefADRESSE("BEMERKUNGEN","Bemerkungen (Adre.)",false);

		this.addSearchDefFIRMENINFO("BESCHREIBUNG","Bemerkungen (Firmeninfos)",false);
		
		this.add_SearchElement("SELECT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JT_EMAIL WHERE JT_ADRESSE.ID_ADRESSE=JT_EMAIL.ID_ADRESSE AND UPPER(JT_EMAIL.EMAIL) LIKE UPPER('%#WERT#%')"+" AND "+this.cWhereZusatz,new MyE2_String("e-Mail"));
		this.add_SearchElement("SELECT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JT_INTERNET WHERE JT_ADRESSE.ID_ADRESSE=JT_INTERNET.ID_ADRESSE AND UPPER(JT_INTERNET.INTERNET) LIKE UPPER('%#WERT#%')"+" AND "+this.cWhereZusatz,new MyE2_String("Internet-Adresse"));
		this.add_SearchElement("SELECT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JT_KOMMUNIKATION WHERE " +
										" JT_ADRESSE.ID_ADRESSE=JT_KOMMUNIKATION.ID_ADRESSE AND   NVL(JT_KOMMUNIKATION.wert_laendervorwahl,'-')||  NVL(JT_KOMMUNIKATION.wert_vorwahl,'-')||  NVL(JT_KOMMUNIKATION.wert_rufnummer,'-') LIKE '%#WERT#%'"+" AND "+this.cWhereZusatz,new MyE2_String("Telefon-Nummer"));

		this.add_SearchElement("SELECT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JT_ADRESSE_INFO WHERE JT_ADRESSE.ID_ADRESSE=JT_ADRESSE_INFO.ID_ADRESSE AND UPPER(JT_ADRESSE_INFO.TEXT) LIKE UPPER('%#WERT#%')"+" AND "+this.cWhereZusatz,new MyE2_String("Adress-Info (extern)"));
		this.add_SearchElement("SELECT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JD_USER WHERE JT_ADRESSE.ID_USER=JD_USER.ID_USER AND (UPPER(JD_USER.NAME) like UPPER('%#WERT#%') OR UPPER(JD_USER.VORNAME) like UPPER('%#WERT#%'))"+" AND "+this.cWhereZusatz,new MyE2_String("Bearbeiter"));

		this.add_SearchElement("select JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE,"+cTO+".JT_FIRMENINFO,"+cTO+".JT_BRANCHE where JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE and JT_FIRMENINFO.ID_BRANCHE=JT_BRANCHE.ID_BRANCHE and UPPER(JT_BRANCHE.KURZBEZEICHNUNG) like UPPER('%#WERT#%')"+" AND "+this.cWhereZusatz,new MyE2_String("Branche"));
		
		this.add_SearchElement("SELECT distinct  JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSKLASSE_DEF,"+cTO+".JT_ADRESSE,"+cTO+".JT_ADRESSKLASSE WHERE "+ 
                " JT_ADRESSE.ID_ADRESSE = JT_ADRESSKLASSE.ID_ADRESSE AND JT_ADRESSKLASSE.ID_ADRESSKLASSE_DEF = JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF "+
                " AND UPPER(JT_ADRESSKLASSE_DEF.KURZBEZEICHNUNG) like UPPER('%#WERT#%')"+" AND "+this.cWhereZusatz,new MyE2_String("Adressklasse"));

		this.add_SearchElement("SELECT  DISTINCT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE_POTENTIAL,"+cTO+".JT_ADRESSE WHERE  JT_ADRESSE.ID_ADRESSE_POTENTIAL = JT_ADRESSE_POTENTIAL.ID_ADRESSE_POTENTIAL AND UPPER(JT_ADRESSE_POTENTIAL.KURZBESCHREIBUNG) LIKE UPPER('%###WERT###%')"+" AND "+this.cWhereZusatz,new MyE2_String("Kundenpotential"));
		this.add_SearchElement("SELECT  DISTINCT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE_MERKMAL1, "+cTO+".JT_ADRESSE WHERE  JT_ADRESSE.ID_ADRESSE_MERKMAL1 = JT_ADRESSE_MERKMAL1.ID_ADRESSE_MERKMAL1 AND UPPER(JT_ADRESSE_MERKMAL1.KURZBESCHREIBUNG) LIKE UPPER('%###WERT###%')"+" AND "+this.cWhereZusatz,new MyE2_String("Kundenmerkmal 1"));
		this.add_SearchElement("SELECT  DISTINCT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE_MERKMAL2, "+cTO+".JT_ADRESSE WHERE  JT_ADRESSE.ID_ADRESSE_MERKMAL2 = JT_ADRESSE_MERKMAL2.ID_ADRESSE_MERKMAL2 AND UPPER(JT_ADRESSE_MERKMAL2.KURZBESCHREIBUNG) LIKE UPPER('%###WERT###%')"+" AND "+this.cWhereZusatz,new MyE2_String("Kundenmerkmal 2"));
		this.add_SearchElement("SELECT  DISTINCT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE_MERKMAL3, "+cTO+".JT_ADRESSE WHERE  JT_ADRESSE.ID_ADRESSE_MERKMAL3 = JT_ADRESSE_MERKMAL3.ID_ADRESSE_MERKMAL3 AND UPPER(JT_ADRESSE_MERKMAL3.KURZBESCHREIBUNG) LIKE UPPER('%###WERT###%')"+" AND "+this.cWhereZusatz,new MyE2_String("Kundenmerkmal 3"));
		this.add_SearchElement("SELECT  DISTINCT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE_MERKMAL4, "+cTO+".JT_ADRESSE WHERE  JT_ADRESSE.ID_ADRESSE_MERKMAL4 = JT_ADRESSE_MERKMAL4.ID_ADRESSE_MERKMAL4 AND UPPER(JT_ADRESSE_MERKMAL4.KURZBESCHREIBUNG) LIKE UPPER('%###WERT###%')"+" AND "+this.cWhereZusatz,new MyE2_String("Kundenmerkmal 4"));
		this.add_SearchElement("SELECT  DISTINCT JT_ADRESSE.ID_ADRESSE FROM "+cTO+".JT_ADRESSE_MERKMAL5, "+cTO+".JT_ADRESSE WHERE  JT_ADRESSE.ID_ADRESSE_MERKMAL5 = JT_ADRESSE_MERKMAL5.ID_ADRESSE_MERKMAL5 AND UPPER(JT_ADRESSE_MERKMAL5.KURZBESCHREIBUNG) LIKE UPPER('%###WERT###%')"+" AND "+this.cWhereZusatz,new MyE2_String("Kundenmerkmal 5"));

		String cHelp = "SELECT DISTINCT A.ID_ADRESSE "+
						" FROM "+cTO+".JT_ADRESSE A,"+cTO+".JT_ADRESSE B,"+cTO+".JT_MITARBEITER "+
						" WHERE 	JT_MITARBEITER.ID_ADRESSE_BASIS			=	A.ID_ADRESSE "+
						" AND     	JT_MITARBEITER.ID_ADRESSE_MITARBEITER 	= 	B.ID_ADRESSE "+
						" AND     	(UPPER(  NVL(b.name1,'-')) LIKE  UPPER('%#WERT#%') "+
								" OR UPPER(  NVL(b.vorname,'-')) LIKE UPPER('%#WERT#%') "+
								" OR UPPER(  NVL(b.name2,'-')) LIKE UPPER('%#WERT#%') ) " +
						" AND A.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO;
		
		this.add_SearchElement(cHelp,new MyE2_String("Mitarbeiter-Name"));
		
		String cHelpSorte= "SELECT ABL.ID_ADRESSE FROM "+cTO+".JT_ARTIKELBEZ_LIEF ABL WHERE ABL.ID_ARTIKEL_BEZ IN ("+
							"SELECT DISTINCT JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ"+
							" FROM "+
							" "+cTO+".JT_ARTIKEL_BEZ LEFT OUTER JOIN "+cTO+".JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL)"+
							" WHERE "+
							" UPPER(JT_ARTIKEL.ANR1||'-'||JT_ARTIKEL_BEZ.ANR2) LIKE UPPER('#WERT#%') )";

		this.add_SearchElement(cHelpSorte,new MyE2_String("Sortenbezeichner (BSP: 0104-01)"));

		
		
		
		this.add_SearchElement("select JT_ADRESSE.ID_ADRESSE from "+cTO+".JT_ADRESSE,"+cTO+".JD_LAND where JT_ADRESSE.ID_LAND=JD_LAND.ID_LAND and upper(JD_LAND.LAENDERNAME) like upper('%#WERT#%')"+" AND "+this.cWhereZusatz,new MyE2_String("Land"));
		
		// komplexe blocksuche in relationen, hilfsstring
		String cCHelp = " ( UPPER(A2.VORNAME) like UPPER('%#WERT#%') OR UPPER(A2.NAME1) like UPPER('%#WERT#%') OR UPPER(A2.NAME2) like UPPER('%#WERT#%') OR UPPER(A2.STRASSE) like UPPER('%#WERT#%')  OR UPPER(A2.PLZ) like UPPER('%#WERT#%')  OR UPPER(A2.ORT) like UPPER('%#WERT#%'))";
		
		// geschaeftsbeziehung suchen
		String cQueryGeshBez = "select A1.ID_ADRESSE from "+cTO+".JT_ADRESSE A1,"+cTO+".JT_ADRESSE A2, "+cTO+".JT_BEZIEHUNG BZ " +
										" WHERE A1.ID_ADRESSE=BZ.ID_ADRESSE_1 AND A2.ID_ADRESSE=BZ.ID_ADRESSE_2 AND " +cCHelp;
		this.add_SearchElement(cQueryGeshBez,new MyE2_String("Geschäftsbeziehung zu ..."));
		this.DO_EnableSearchFieldWithLabelText("Geschäftsbeziehung zu ...",false);
		
		// mitarbeiter suchen
		String cQueryMitarbeiter = "select A1.ID_ADRESSE from "+cTO+".JT_ADRESSE A1,"+cTO+".JT_ADRESSE A2, "+cTO+".JT_MITARBEITER MI " +
										" WHERE A1.ID_ADRESSE=MI.ID_ADRESSE_BASIS AND A2.ID_ADRESSE=MI.ID_ADRESSE_MITARBEITER AND " +cCHelp;
		this.add_SearchElement(cQueryMitarbeiter,new MyE2_String("Mitarbeiter (Adresse)"));
		this.DO_EnableSearchFieldWithLabelText("Mitarbeiter (Adresse)",false);
		

		// lieferadressen suchen
		String cQueryLieferadresse = "select A1.ID_ADRESSE from "+cTO+".JT_ADRESSE A1,"+cTO+".JT_ADRESSE A2, "+cTO+".JT_LIEFERADRESSE LI " +
										" WHERE A1.ID_ADRESSE=LI.ID_ADRESSE_BASIS AND A2.ID_ADRESSE=LI.ID_ADRESSE_LIEFER AND " +cCHelp;
		this.add_SearchElement(cQueryLieferadresse,new MyE2_String("Lieferadresse"));
		this.DO_EnableSearchFieldWithLabelText("Lieferadresse",false);
		
		//Sortensuche deaktivieren
		this.DO_EnableSearchFieldWithLabelText("Sortenbezeichner (BSP: 0104-01)",false);
		
		this.addSearchDefADRESSE("LIEF_NR","Lieferanten-Nr (alt)",false);
		this.addSearchDefADRESSE("ABN_NR","Abnehmer-Nr (alt)",false);
		
		//NEU_09  ---  Suche nach geschenk
		String cQueryGeschenk = "SELECT JT_MITARBEITER.ID_ADRESSE_BASIS FROM "+
										cTO+".JT_ADRESSE, "+cTO+".JT_MITARBEITER, "+cTO+".JT_ADRESSE_GESCHENK  " +
									    " WHERE  JT_MITARBEITER.ID_ADRESSE_MITARBEITER = JT_ADRESSE.ID_ADRESSE " +
									    " AND JT_ADRESSE.ID_ADRESSE = JT_ADRESSE_GESCHENK.ID_ADRESSE " +
									    " AND  UPPER(  NVL(JT_ADRESSE_GESCHENK.GESCHENK,'-')) LIKE UPPER('%#WERT#%')"; 
		
		this.add_SearchElement(cQueryGeschenk,new MyE2_String("Mitarbeitergeschenk"));
		this.DO_EnableSearchFieldWithLabelText("Mitarbeitergeschenk",false);
		//NEU_09 --ENDE
		
		this.addSearchDefFIRMENINFO("DEBITOR_NUMMER","Debitor-Nummer",false);
		this.addSearchDefFIRMENINFO("KREDITOR_NUMMER","Kreditor-Nummer",false);

		
		//aenderung 2010-11-19: suche in jt_adresse_info
		String cHelpInfo= "SELECT ID_ADRESSE FROM "+cTO+".JT_ADRESSE_INFO  WHERE "+
									" UPPER(JT_ADRESSE_INFO.TEXT)  like UPPER('%#WERT#%')";

		this.add_SearchElement(cHelpInfo,new MyE2_String("Fortlaufende Bemerkungen"));

		
		
		//2012-02-06: test fuer fulltext-search
		String cSuchTextPartAdresse = 		E2_DataSearch_CONST.get_SearchTextBlockFulltext(RECORD_ADRESSE.HM_FIELDS, "JT_ADRESSE", null);
		String cSuchTextPartFirmenInfo = 	"SELECT JT_FIRMENINFO.ID_ADRESSE FROM "+bibE2.cTO()+".JT_FIRMENINFO WHERE "+E2_DataSearch_CONST.get_SearchTextBlockFulltextOnlyWhere(RECORD_FIRMENINFO.HM_FIELDS, "JT_FIRMENINFO", null);
		
		this.add_SearchElement(cSuchTextPartAdresse+" UNION "+cSuchTextPartFirmenInfo,new MyE2_String("Volltextsuche in Adresse"),true);
		
		
		//2012-02-06: volltextsuche fuer materialspezifikationen
		//felder fuer die matspez+artikel-felder
		Vector<String>  vWhereBlocksMatSpez = new Vector<String>();
		vWhereBlocksMatSpez.addAll(E2_DataSearch_CONST.get_vWhereStatementes4FullText(RECORD_MAT_SPEZ.HM_FIELDS, "MZ"));
		vWhereBlocksMatSpez.addAll(E2_DataSearch_CONST.get_vWhereStatementes4FullText(bibDB.get_HM_FieldsWithName(RECORD_ARTIKEL.HM_FIELDS, 
																																bibVECTOR.get_Vector(RECORD_ARTIKEL.FIELD__ANR1, 
																																RECORD_ARTIKEL.FIELD__ARTBEZ1)), "ART"));
		String cWhereBlockMatSpez = bibALL.Concatenate(vWhereBlocksMatSpez, " OR ", "1=1");
		
		//felder fuer die mat_element+chemisches-element-feld
		Vector<String>  vWhereBlocksMatElement = new Vector<String>();
		vWhereBlocksMatElement.addAll(E2_DataSearch_CONST.get_vWhereStatementes4FullText(RECORD_MAT_ELEMENT.HM_FIELDS, "ME"));
		vWhereBlocksMatElement.addAll(E2_DataSearch_CONST.get_vWhereStatementes4FullText(bibDB.get_HM_FieldsWithName(RECORD_ELEMENT.HM_FIELDS, 
																																bibVECTOR.get_Vector(RECORD_ELEMENT.FIELD__KURZ, 
																																		RECORD_ELEMENT.FIELD__LANG)), "EL"));
		String cWhereBlockMatElement = bibALL.Concatenate(vWhereBlocksMatElement, " OR ", "1=1");

		String cSuchTextPartMatspez_1 = 	"SELECT MZ.ID_ADRESSE FROM "+bibE2.cTO()+".JT_MAT_SPEZ MZ LEFT OUTER JOIN JT_ARTIKEL ART ON (MZ.ID_ARTIKEL=ART.ID_ARTIKEL) WHERE "+cWhereBlockMatSpez;
		String cSuchTextPartMatspez_2 = 	"SELECT JT_MAT_SPEZ.ID_ADRESSE FROM "+bibE2.cTO()+".JT_MAT_SPEZ WHERE JT_MAT_SPEZ.ID_MAT_SPEZ IN ("+
												"SELECT ME.ID_MAT_SPEZ  FROM "+bibE2.cTO()+".JT_MAT_ELEMENT ME   LEFT OUTER JOIN JT_ELEMENT EL ON (EL.ID_ELEMENT=ME.ID_ELEMENT)  WHERE "+cWhereBlockMatElement+")";

		this.add_SearchElement(cSuchTextPartMatspez_1+" UNION "+cSuchTextPartMatspez_2, new MyE2_String("Volltextsuche in Material-Spezifikation"),true);
		
		
		//20180518: suche nach id_adresse einer lieferadresse
		String sqlSucheIdLieferadresse = "select LA.ID_ADRESSE_BASIS FROM JT_LIEFERADRESSE  LA WHERE TO_CHAR(LA.ID_ADRESSE_LIEFER)='#WERT#' "
											+ " OR TRIM(TO_CHAR (LA.ID_ADRESSE_LIEFER, '999G999G999G999', 'NLS_NUMERIC_CHARACTERS = '',.'))='#WERT#'";
		this.add_SearchElement(sqlSucheIdLieferadresse, S.ms("Adress-ID einer Liefer-/Lageradresse"));

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	private void addSearchDefADRESSE(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE TO_CHAR(JT_ADRESSE."+cFieldName+")='#WERT#' AND "+this.cWhereZusatz;
		else
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE WHERE UPPER(JT_ADRESSE."+cFieldName+") like upper('%#WERT#%') AND "+this.cWhereZusatz;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefFIRMENINFO(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND TO_CHAR(JT_FIRMENINFO."+cFieldName+")='#WERT#' AND "+this.cWhereZusatz;
		else
			cSearch = "SELECT JT_ADRESSE.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND UPPER(JT_FIRMENINFO."+cFieldName+") like upper('%#WERT#%') AND "+this.cWhereZusatz;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	
}
