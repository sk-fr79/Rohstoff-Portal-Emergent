package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_SortenAuswahl;
import panter.gmbh.indep.exceptions.myException;

public class FU_SelectorSorten extends  SELECTOR_COMPONENT_SortenAuswahl
{
	
	public FU_SelectorSorten(E2_SelectionComponentsVector SelVector,	String cEK_VK) throws myException
	{
		super("",  cEK_VK.equals("EK")?95:30, 100, SelVector, cEK_VK.equals("EK")?new MyE2_String("Lade-Sorte: "):new MyE2_String("Abl.: "),false);
		
		String cSql = " SELECT DISTINCT NVL(SO.ANR1,'<ANR1>')||'-'||NVL(SO.ARTBEZ1,'<ARTBEZ1>'), SO.ID_ARTIKEL "+
					  " FROM "+ 
					  " "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU "+  
					  " INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ABEZ  ON (FU.ID_ARTIKEL_BEZ_"+cEK_VK+"=ABEZ.ID_ARTIKEL_BEZ) "+
					  " INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL     SO    ON (ABEZ.ID_ARTIKEL=SO.ID_ARTIKEL) "+
					  " UNION "+
					  " select DISTINCT NVL(SO.ANR1,'<anr1>')||'-'||NVL(SO.ARTBEZ1,'<artbez1>'), SO.ID_ARTIKEL "+
					  " from "+ 
					  " JT_VPOS_TPA_FUHRE_ORT FUO "+
					  " INNER JOIN JT_ARTIKEL_BEZ ABEZ ON (FUO.ID_ARTIKEL_BEZ=ABEZ.ID_ARTIKEL_BEZ) "+
					  " INNER JOIN JT_ARTIKEL         SO    ON (ABEZ.ID_ARTIKEL=SO.ID_ARTIKEL) "+
					  " WHERE NVL(FUO.DEF_QUELLE_ZIEL,' ')='"+cEK_VK+"' AND NVL(FUO.DELETED,'N')='N' "+					  
					  " ORDER BY 1 ";
		
		this.set_cQuery4Sorten(cSql,true);
		
		if (cEK_VK.equals("EK"))
		{
			this.get_oSelSorten().setToolTipText(new MyE2_String("Auswahl der Ladesorte (EK)").CTrans());
		}
		else
		{
			this.get_oSelSorten().setToolTipText(new MyE2_String("Auswahl der Abladesorte (VK)").CTrans());
		}
		
		//this.get_oSelSorten().setWidth(new Extent(200));
	}
	
	public String get_cActual_ID_Adress() throws myException
	{
		return this.get_oSelSorten().get_ActualWert();
	}
	

}
