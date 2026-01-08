package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/*
select jt_vpos_tpa_fuhre.DATUM_AUFLADEN, datum_abladen  from jt_vpos_tpa_fuhre where 
(datum_aufladen>=to_date('10.01.2011','DD.MM.YYYY')-5  AND datum_aufladen<=to_date('10.01.2011','DD.MM.YYYY')+5 )
or
(datum_abladen>=to_date('10.01.2011','DD.MM.YYYY')-5  AND datum_abladen<=to_date('10.01.2011','DD.MM.YYYY')+5 )
*/


public abstract class E2_ListSelector_Date_plus_Umkreis extends XX_ListSelektor
{


	private MyE2_TextField_DatePOPUP_OWN   oDatePopup = null;  
	private MyE2_SelectField               oSelFieldTage = null;
	


	private String 						   cWhereSchablone = null;
	
	@Override
	public abstract Component get_oComponentForSelection();

	@Override
	public abstract Component get_oComponentWithoutText();


	/**
	 * 
	 * @param cWhereSchablone MUSS mindestens einen platzhalter #WERT# der das datum in der notation 'dd.mm.yyyy' uebernimmt und
	 *                                        einen platzhalter #TAGE# der die auswahl der tagesselektion uebernimmt
	 * @throws myException
	 */
	public E2_ListSelector_Date_plus_Umkreis(String WhereSchablone, String cStartDatum) throws myException
	{
		super();
		
		/*
		 * beispiel fuer cWhereSchablone:
		   	(datum_aufladen>=to_date('#WERT#','DD.MM.YYYY')-#TAGE#  AND datum_aufladen<=to_date('#WERT#','DD.MM.YYYY')+#TAGE# )
			or
			(datum_abladen>=to_date('#WERT#','DD.MM.YYYY')-#TAGE#   AND datum_abladen<=to_date('#WERT#','DD.MM.YYYY')+#TAGE#  )
		 */
		
		this.cWhereSchablone=WhereSchablone;
		
		this.oDatePopup = new MyE2_TextField_DatePOPUP_OWN(cStartDatum, 100, true, true);
		
		String[][] cTage = {{"0","0"},{"1","1"},{"2","2"},{"3","3"},{"4","4"},{"5","5"},{"6","6"},{"7","7"},{"8","8"},{"9","9"},{"10","10"},{"15","15"},{"20","20"},{"30","30"}};

		this.oSelFieldTage = new MyE2_SelectField(cTage, "1", false);
		
	}

	@Override
	public String get_WhereBlock() throws myException
	{
		String cRueck = "";
		
		if (this.oDatePopup.get_oDateFromTextField()!=null)
		{
			String cDatum = this.oDatePopup.get_oDateFromTextField().get_cDateStandardFormat();
			String cTage =  this.oSelFieldTage.get_ActualWert();
			cRueck = bibALL.ReplaceTeilString(this.cWhereSchablone, bibALL.get_Vector("#WERT#", "#TAGE#"), bibALL.get_Vector(cDatum, cTage));
		}
		
		return cRueck;
	}


	public MyE2_TextField_DatePOPUP_OWN get_oDatePopup()
	{
		return oDatePopup;
	}

	public MyE2_SelectField get_oSelFieldTage()
	{
		return oSelFieldTage;
	}

	public String get_cWhereSchablone()
	{
		return cWhereSchablone;
	}
	
	

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
	{
		this.oDatePopup.get_vActionAgentsZusatz().add(oAgent);
		this.oDatePopup.get_oButtonEraser().add_oActionAgent(oAgent);
		
		this.oSelFieldTage.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck()
	{

	}

	
	public void setToolTipText(String cToolTip)
	{
		this.get_oDatePopup().get_oTextField().setToolTipText(cToolTip);
		this.get_oDatePopup().get_oButtonCalendar().setToolTipText(cToolTip);
		this.get_oDatePopup().get_oButtonEraser().setToolTipText(cToolTip);
		this.get_oSelFieldTage().setToolTipText(cToolTip);
	}
	
	
}
