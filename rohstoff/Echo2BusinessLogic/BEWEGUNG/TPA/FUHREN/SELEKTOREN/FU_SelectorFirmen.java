package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FU_SelectorFirmen extends  SELECTOR_COMPONENT_FirmenAuswahl
{
	private Vector<IF_CanBePopulated> vAngeschlossene = null;
	
	public FU_SelectorFirmen(E2_SelectionComponentsVector SelVector,	String cEK_VK, Vector<IF_CanBePopulated> Angeschlossene) throws myException
	{
		super("", 70, 180, SelVector, cEK_VK.equals("EK")?new MyE2_String("Lieferant: "):new MyE2_String("Abnehmer: "));
		
		this.vAngeschlossene = Angeschlossene;
		
		String cSqlSel =	" SELECT to_nchar('* HAUPTADRESSE: ') || NVL(AD.NAME1,'-')||'-'||  NVL(AD.NAME2,'-')|| "+
										" '-'||  NVL(AD.ORT,'-') ||' ('||TO_CHAR(  NVL(AD.ID_ADRESSE,0))||')' AS NAMEN , AD.ID_ADRESSE AS  ID_ADRESSE "+
										" FROM " + bibE2.cTO()+".JT_ADRESSE AD WHERE AD.ID_ADRESSE = " + bibALL.get_ID_ADRESS_MANDANT() + 
										" UNION "+ 
										" SELECT  NVL(AD.NAME1,'-')||'-'||  NVL(AD.NAME2,'-')|| "+
										" '-'||  NVL(AD.ORT,'-') ||' ('||TO_CHAR(  NVL(AD.ID_ADRESSE,0))||')' AS NAMEN , AD.ID_ADRESSE AS  ID_ADRESSE "+
										" FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FU.ID_ADRESSE_#STARTZIEL#=AD.ID_ADRESSE) "+
										" UNION "+ 
										" SELECT  NVL(AD.NAME1,'-')||'-'||  NVL(AD.NAME2,'-')|| "+
										" '-'||  NVL(AD.ORT,'-') ||' ('||TO_CHAR(  NVL(AD.ID_ADRESSE,0))||')' AS NAMEN , AD.ID_ADRESSE "+ 
										"	 FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT FUO LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE AD ON (FUO.ID_ADRESSE=AD.ID_ADRESSE)" +
											" WHERE NVL(FUO.DEF_QUELLE_ZIEL,' ')='#EKVK#' AND NVL(FUO.DELETED,'N')='N'"+
										" ORDER BY NAMEN";
		
		
		this.set_cQuery4AdressListe(
				bibALL.ReplaceTeilString(   cSqlSel,
						                    bibALL.get_Vector("#STARTZIEL#", "#EKVK#"),
						                    cEK_VK.equals("EK")?bibALL.get_Vector("START", "EK"):bibALL.get_Vector("ZIEL", "VK")), 
				false);
		
		this.get_oSelKunden().setWidth(new Extent(100));
	}
	
	public String get_cActual_ID_Adress() throws myException
	{
		return this.get_oSelKunden().get_ActualWert();
	}
	
	public Vector<IF_CanBePopulated> get_vAngeschlossene()
	{
		return this.vAngeschlossene;
	}

}
