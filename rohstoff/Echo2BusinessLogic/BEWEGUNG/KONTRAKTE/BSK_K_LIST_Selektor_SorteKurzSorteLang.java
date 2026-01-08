package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BSK_K_LIST_Selektor_SorteKurzSorteLang extends XX_ListSelektor 
{
	private MyE2_SelectField				oSelectSorteKurz = null;
	private MyE2_SelectField				oSelectSorteExact = null;
	
	private int    							iSorteKurzZeichen = 2;
	
	private MyE2_Grid     				    oGridSelComponent = new MyE2_Grid(3,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	public BSK_K_LIST_Selektor_SorteKurzSorteLang(int iWidthSorteKurz, int iWidthSorteLang, int iLenSorteKurzZeichen) throws myException 
	{
		super();
		
		this.iSorteKurzZeichen=iLenSorteKurzZeichen;
		
		this.oSelectSorteKurz = new MyE2_SelectField("select DISTINCT SUBSTR(ANR1,1,"+this.iSorteKurzZeichen+"),SUBSTR(ANR1,1,"+this.iSorteKurzZeichen+") FROM "+bibE2.cTO()+".JT_ARTIKEL ORDER BY SUBSTR(ANR1,1,2)",
				false,true,false,false,new Extent(iWidthSorteKurz));
				
		this.oSelectSorteKurz.add_oActionAgent(new ownActionAgent());
		
		this.oSelectSorteExact = new MyE2_SelectField("select DISTINCT ANR1||' '||NVL(ARTBEZ1,'') AS A,ANR1 AS B FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ANR1 IS NOT NULL ORDER BY ANR1",
				false,true,false,false,new Extent(iWidthSorteLang));

		
		this.oGridSelComponent.add(new MyE2_Label(new MyE2_String("Sorten: ")),E2_INSETS.I_0_0_5_0);
		this.oGridSelComponent.add(this.oSelectSorteKurz,E2_INSETS.I_0_0_5_0);
		this.oGridSelComponent.add(this.oSelectSorteExact,E2_INSETS.I_0_0_5_0);
		
		
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSK_K_LIST_Selektor_SorteKurzSorteLang oThis = BSK_K_LIST_Selektor_SorteKurzSorteLang.this; 
			
			String cANR1_KURZ=oThis.oSelectSorteKurz.get_ActualWert();
			if (S.isEmpty(oThis.oSelectSorteKurz.get_ActualWert()))
			{
				oThis.oSelectSorteExact.set_Fuelle_Neu("select DISTINCT ANR1||' '||NVL(ARTBEZ1,'') AS A,ANR1 AS B FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE ANR1 IS NOT NULL ORDER BY ANR1");
			}
			else
			{
				oThis.oSelectSorteExact.set_Fuelle_Neu("select DISTINCT ANR1||' '||NVL(ARTBEZ1,'') AS A,ANR1 AS B FROM "+
									bibE2.cTO()+".JT_ARTIKEL WHERE SUBSTR(ANR1,1,"+oThis.iSorteKurzZeichen+")="+bibALL.MakeSql(cANR1_KURZ)+" ORDER BY ANR1");
			}
			
			
		}
	}


	@Override
	public String get_WhereBlock() throws myException 
	{
		String cANR1_KURZ = this.oSelectSorteKurz.get_ActualWert().trim();
		String cANR1 =		this.oSelectSorteExact.get_ActualWert().trim();

		String cSel_in_Pos_helper = "SELECT DISTINCT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT " +
												" WHERE JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON " +
												"    AND JT_VPOS_KON.POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"'";

		
		if (S.isFull(cANR1))
		{
			return ("ID_VKOPF_KON IN ("+cSel_in_Pos_helper+" AND JT_VPOS_KON.ANR1 = "+bibALL.MakeSql(cANR1)+")");
		}
		else if (S.isFull(cANR1_KURZ))
		{
			return ("ID_VKOPF_KON IN ("+cSel_in_Pos_helper+" AND SUBSTR(JT_VPOS_KON.ANR1,1,"+this.iSorteKurzZeichen+") = "+bibALL.MakeSql(cANR1_KURZ)+")");
		}
		else
		{
			return "";
		}
	}


	@Override
	public Component get_oComponentForSelection() 
	{
		return this.oGridSelComponent;
	}


	@Override
	public Component get_oComponentWithoutText() 
	{
		return this.oGridSelComponent;
	}


	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) 
	{
		this.oSelectSorteExact.add_oActionAgent(oAgent);
		this.oSelectSorteKurz.add_oActionAgent(oAgent);
	}


	@Override
	public void doInternalCheck() 
	{
		
	}
	
	
	public String get_cActualSorteKurz() throws myException
	{
		return  this.oSelectSorteKurz.get_ActualWert().trim();
	}
	
	public String get_cActualSorteGanz() throws myException
	{
		return  this.oSelectSorteExact.get_ActualWert().trim();
	}
	


}
