package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/*
 * ein listenselektor mit zwei drop-downs fuer die monate und jahre eines datumsfeldes
 */

public class KFIX_K_L_Selektor_MonatJahr extends XX_ListSelektor 
{

	private MyE2_SelectField oSelJahre = null;
	private MyE2_SelectField oSelMonate = null;
	
	
	private MyE2_Row		oBaseComponent = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	
	/**
	 * @param cfieldForSelektion
	 */
	public KFIX_K_L_Selektor_MonatJahr(int iWidthMonat, int iWidthJahr) throws myException
	{
		super();

		this.oSelJahre = new MyE2_SelectField(bibALL.get_cSelJahre(),"",false);
		this.oSelMonate = new MyE2_SelectField(bibALL.get_cSelArrayMonate(),"",false);

		this.oSelJahre.setWidth(new Extent(iWidthJahr));
		this.oSelMonate.setWidth(new Extent(iWidthMonat));

		this.oBaseComponent.add(oSelMonate, E2_INSETS.I_0_0_10_0);
		this.oBaseComponent.add(oSelJahre, E2_INSETS.I_0_0_10_0);
	}

	/*
	 * der eigene actionlistener loescht die eingaben, wenn ein datum nicht korrekt war 
	 */
	public void doInternalCheck()
	{
	}


	public String get_WhereBlock() throws myException
	{
		String cDBTextMonat = this.oSelMonate.get_ActualWert().trim();
		String cDBTextJahr = this.oSelJahre.get_ActualWert().trim();

		if (!cDBTextMonat.equals("") && cDBTextJahr.equals(""))
		{
			return "ID_VKOPF_KON IN (" +
					"SELECT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE " +
					" JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND "+
					" TO_NUMBER(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'MM')) = "+cDBTextMonat+")";
			
		}
		else if (cDBTextMonat.equals("") && !cDBTextJahr.equals(""))
		{
			return 	"ID_VKOPF_KON IN (" +
					"SELECT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE " +
					" JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND "+
					" TO_NUMBER(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'YYYY')) = "+cDBTextJahr+")";
		}
		else if (!cDBTextMonat.equals("") && !cDBTextJahr.equals(""))
		{
			return 	"ID_VKOPF_KON IN (" +
					"SELECT JT_VPOS_KON.ID_VKOPF_KON FROM "+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT WHERE " +
					" JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND "+
					" TO_NUMBER(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'MM')) = "+cDBTextMonat+" AND " +
					" TO_NUMBER(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'YYYY')) = "+cDBTextJahr+")";
		}
		return "";
		
	}


	public Component get_oComponentForSelection()
	{
		return this.oBaseComponent;
	}

	public Component get_oComponentWithoutText()
	{
		return this.oBaseComponent;
	}
	

	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) 
	{
		this.oSelJahre.add_oActionAgent(oAgent);
		this.oSelMonate.add_oActionAgent(oAgent);
	}
}
