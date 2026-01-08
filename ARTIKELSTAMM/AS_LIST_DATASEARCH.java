package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class AS_LIST_DATASEARCH extends E2_DataSearch
{

	public AS_LIST_DATASEARCH( E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_ARTIKEL","ID_ARTIKEL",MODULE_KENNER); 
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDefARTIKEL("ANR1","ANR1",false);
		this.addSearchDefARTIKEL("ARTBEZ1","ArtBez 1 (Basis)",false);
		this.addSearchDefARTIKEL("ARTBEZ2","ArtBez 2 (Basis)",false);
		this.addSearchDefARTIKEL("ZOLLTARIFNR","Zolltarifnummer",false);
		this.addSearchDefARTIKEL("EUCODE","OECD-Code",false);
		this.addSearchDefARTIKEL("EUNOTIZ","OECD-Notiz",false);
		this.addSearchDefARTIKEL("BASEL_CODE","BASEL-Code",false);
		this.addSearchDefARTIKEL("BASEL_NOTIZ","BASEL-Notiz",false);
		this.addSearchDefARTIKEL(_DB.ARTIKEL$ID_ARTIKEL,"ID_Artikel",true);

		
		
		this.addSearchDefARTIKELBEZEICHUNG("ANR2","ANR2",false);
		this.addSearchDefARTIKELBEZEICHUNG("ARTBEZ1","ArtBez 1 (Kund.Spez.)",false);
		this.addSearchDefARTIKELBEZEICHUNG("ARTBEZ2","ArtBez 2 (Kund.Spez.)",false);
		
		this.addSearchAVV_Code(ARTIKEL.id_eak_code, 				"AVV-Code Kleinanlieferer");
		this.addSearchAVV_Code(ARTIKEL.id_eak_code_ex_mandant, 		"AVV-Code ex Mandant");
		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
		
	}

	private void addSearchDefARTIKEL(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_ARTIKEL.ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE TO_CHAR(JT_ARTIKEL."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_ARTIKEL.ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE UPPER(JT_ARTIKEL."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefARTIKELBEZEICHUNG(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT DISTINCT JT_ARTIKEL.ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKEL,"+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE JT_ARTIKEL.ID_ARTIKEL=JT_ARTIKEL_BEZ.ID_ARTIKEL AND TO_CHAR(JT_ARTIKEL_BEZ."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT DISTINCT JT_ARTIKEL.ID_ARTIKEL FROM "+bibE2.cTO()+".JT_ARTIKEL,"+bibE2.cTO()+".JT_ARTIKEL_BEZ WHERE JT_ARTIKEL.ID_ARTIKEL=JT_ARTIKEL_BEZ.ID_ARTIKEL AND UPPER(JT_ARTIKEL_BEZ."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	
	
	
	private void addSearchAVV_Code(IF_Field avv_field, String info) throws myException {
		String c_query = "SELECT AR.ID_ARTIKEL "+
								", TRIM(AVVB.KEY_BRANCHE||' '||AVVG.KEY_GRUPPE||' '||AVV.KEY_CODE||' '||TRANSLATE(NVL(AVV.GEFAEHRLICH,'N'),'NYy',' **')) AS V1 "+
								", TRIM(AVVB.KEY_BRANCHE||''||AVVG.KEY_GRUPPE||''||AVV.KEY_CODE||' '||TRANSLATE(NVL(AVV.GEFAEHRLICH,'N'),'NYy',' **')) AS V2 "+
								", TRIM(AVVB.KEY_BRANCHE||'-'||AVVG.KEY_GRUPPE||'-'||AVV.KEY_CODE||' '||TRANSLATE(NVL(AVV.GEFAEHRLICH,'N'),'NYy',' **')) AS V3 "+
								", TRIM(AVVB.KEY_BRANCHE||'.'||AVVG.KEY_GRUPPE||'.'||AVV.KEY_CODE||' '||TRANSLATE(NVL(AVV.GEFAEHRLICH,'N'),'NYy',' **')) AS V4 "+
								"  FROM JT_ARTIKEL AR "+
								"   LEFT OUTER JOIN JT_EAK_CODE    AVV  ON (AR."+avv_field.fn()+" =AVV.ID_EAK_CODE) "+
								"   LEFT OUTER JOIN JT_EAK_GRUPPE  AVVG ON (AVV.ID_EAK_GRUPPE=AVVG.ID_EAK_GRUPPE) "+
								"   LEFT OUTER JOIN JT_EAK_BRANCHE AVVB ON (AVVG.ID_EAK_BRANCHE=AVVB.ID_EAK_BRANCHE) ";
		
		c_query = "SELECT ID_ARTIKEL FROM ("+c_query+") WHERE V1 like '%#WERT#%'   OR    V2 like '%#WERT#%'    OR   V3 like '%#WERT#%'   OR   V4 like '%#WERT#%' ";
		this.add_SearchElement(c_query,new MyE2_String(info));

	}
	
	
}
