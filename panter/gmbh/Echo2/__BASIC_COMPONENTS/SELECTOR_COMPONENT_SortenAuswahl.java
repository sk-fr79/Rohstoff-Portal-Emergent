package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectField_WithAutoToolTip;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * spezielle komponente fuer liste-selektoren wo id_adresse - Werte selektiert werden, die 
 * mit einem Refresh-button versehen sind
 */
public class SELECTOR_COMPONENT_SortenAuswahl extends MyE2_Row 
{




	private MyE2_SelectField_WithAutoToolTip   		oSelSorten = null;
	private E2_SelectionComponentsVector 			oSelVector = null;
	private String    								cQuery4Sorten = null;
	private MyE2_Button 							oButtonLeseSortenNeuEin = null;
	
	

	
	
	
	public SELECTOR_COMPONENT_SortenAuswahl(	String  						Query4AdressListe,
												int 							iTextWidth, 
												int 							iDropdownWidth, 
												E2_SelectionComponentsVector 	SelVector, 
												MyString 						cBeschriftung,
												boolean                         bShowRefreshButton) 
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		this.oSelSorten = 				new MyE2_SelectField_WithAutoToolTip();
		this.oSelSorten.setWidth(new Extent(iDropdownWidth));
		this.oSelVector = 				SelVector;
		this.cQuery4Sorten = 			Query4AdressListe;
		
		this.oButtonLeseSortenNeuEin = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
		oButtonLeseSortenNeuEin.setToolTipText(new MyE2_String("Liste der beteiligten Sorten neu einlesen ...").CTrans());
		
		oButtonLeseSortenNeuEin.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				try
				{
					SELECTOR_COMPONENT_SortenAuswahl.this.REFRESH_SortenSelektor();
					SELECTOR_COMPONENT_SortenAuswahl.this.oSelVector.doActionPassiv();
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error refreshing Sort-List",false)));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			
			}
		
		});
		
		
		
		//jetzt die row fuellen
		this.add(new MyE2_Label(cBeschriftung).get_InBorderGrid(new Border(0,Color.BLACK,Border.STYLE_NONE), new Extent(iTextWidth), E2_INSETS.I_0_0_0_0), E2_INSETS.I_0_0_0_0);
		if (bShowRefreshButton)
		{
			this.add(oButtonLeseSortenNeuEin,E2_INSETS.I_5_0_5_0);
		}
		this.add(this.oSelSorten,E2_INSETS.I_0_0_0_0);
	
	}

	
	
	
	
	
	
	public MyE2_SelectField get_oSelSorten() 
	{
		return oSelSorten;
	}




	public E2_SelectionComponentsVector get_oSelVector() 
	{
		return oSelVector;
	}




	public String get_cQuery4Sorten() 
	{
		return cQuery4Sorten;
	}

	
	public void set_cQuery4Sorten(String cNewQuery, boolean bRefresh) throws myException 
	{
		this.cQuery4Sorten = cNewQuery;
		if (bRefresh)
		{
			this.REFRESH_SortenSelektor();
		}
	}

	
	
	
	public void REFRESH_SortenSelektor() throws myException
	{
		if (S.isFull(this.cQuery4Sorten))
		{
			String[][] cResult = bibDB.EinzelAbfrageInArray(this.cQuery4Sorten,"");
			if (cResult == null)
				throw new myException("SELECTOR_COMPONENT_SortenAuswahl:Error querying SORTEN");
			
			String[][] cDropDown = new String[cResult.length+1][2];
			cDropDown[0][0]="-";
			cDropDown[0][1]="";
			
			for (int i=0;i<cResult.length;i++)
			{
				cDropDown[i+1][0]=cResult[i][0];
				cDropDown[i+1][1]=cResult[i][1];
			}
			
			this.oSelSorten.set_ListenInhalt(cDropDown,false);
			this.oSelSorten.set_ActiveInhalt_or_FirstInhalt("");

		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Neuaufbau nicht möglich ..."));
		}
		
	}


	public MyE2_Button get_oButtonLeseSortenNeuEin() 
	{
		return oButtonLeseSortenNeuEin;
	}

	
}
