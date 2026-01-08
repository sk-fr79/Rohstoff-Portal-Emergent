package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.list.DefaultListCellRenderer;
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
public class SELECTOR_COMPONENT_FirmenAuswahl extends MyE2_Row 
{




	private MyE2_SelectField_WithAutoToolTip   		oSelKunden = null;
	private E2_SelectionComponentsVector 			oSelVector = null;
	private String    								cQuery4AdressListe = null;
	private String    								cQuery4AdressListe2 = null;
	private MyE2_Button 							oButtonLeseKundenNeuEin = null;
	
	
	//2012-06-19: inaktive eintrage (was immer auch inaktiv bedeuten kann) werden grau markiert
	private String   								cQuery4InaktiveEintraege = null;

	

	public SELECTOR_COMPONENT_FirmenAuswahl(	String  						Query4AdressListe,
												int 							iTextWidth, 
												int 							iDropdownWidth, 
												E2_SelectionComponentsVector 	SelVector, 
												MyString 						cBeschriftung) 
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		this.oSelKunden = 		new MyE2_SelectField_WithAutoToolTip();
		this.oSelKunden.setWidth(new Extent(iDropdownWidth));
		this.oSelVector = 					SelVector;
		this.cQuery4AdressListe = 			Query4AdressListe;

		this.oButtonLeseKundenNeuEin = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
		oButtonLeseKundenNeuEin.setToolTipText(new MyE2_String("Liste der beteiligten Firmen neu einlesen ...").CTrans());
		
		oButtonLeseKundenNeuEin.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						try
						{
							SELECTOR_COMPONENT_FirmenAuswahl.this.REFRESH_KundenSelektor();
							SELECTOR_COMPONENT_FirmenAuswahl.this.oSelVector.doActionPassiv();
						}
						catch (myException ex)
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error refreshing Customer-List",false)));
							bibMSG.add_MESSAGE(ex.get_ErrorMessage());
						}
						
					}
			
				});

		
		
		//jetzt die row fuellen
		this.add(new MyE2_Label(cBeschriftung).get_InBorderGrid(new Border(0,Color.BLACK,Border.STYLE_NONE), new Extent(iTextWidth), E2_INSETS.I_0_0_0_0), E2_INSETS.I_0_0_0_0);
		this.add(oButtonLeseKundenNeuEin,E2_INSETS.I_5_0_0_0);
		this.add(this.oSelKunden,E2_INSETS.I_5_0_0_0);
		
	}

	
	/**
	 * 2012-02-15: neue variante ohne die beschriftung
	 * @param Query4AdressListe
	 * @param iDropdownWidth
	 * @param SelVector
	 */
	public SELECTOR_COMPONENT_FirmenAuswahl(String Query4AdressListe, int iDropdownWidth, E2_SelectionComponentsVector SelVector)
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		this.oSelKunden = new MyE2_SelectField_WithAutoToolTip();
		this.oSelKunden.setWidth(new Extent(iDropdownWidth));
		this.oSelVector = SelVector;
		this.cQuery4AdressListe = Query4AdressListe;

		this.oButtonLeseKundenNeuEin = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
		oButtonLeseKundenNeuEin.setToolTipText(new MyE2_String("Liste der beteiligten Firmen neu einlesen ...").CTrans());

		oButtonLeseKundenNeuEin.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				try
				{
					SELECTOR_COMPONENT_FirmenAuswahl.this.REFRESH_KundenSelektor();
					SELECTOR_COMPONENT_FirmenAuswahl.this.oSelVector.doActionPassiv();
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error refreshing Customer-List", false)));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}

			}

		});

		// jetzt die row fuellen
		this.add(oButtonLeseKundenNeuEin, E2_INSETS.I_0_0_0_0);
		this.add(this.oSelKunden, E2_INSETS.I_5_0_0_0);

	}
	
	
	
	/**
	 * 2012-02-15: neue variante mit shadow-liste
	 * @param Query4AdressListe
	 * @param Query4AdressListe2    wird hinter die erste gehaengt (z.B. Archiv o.ä.)
	 * @param iDropdownWidth
	 * @param SelVector
	 */
	public SELECTOR_COMPONENT_FirmenAuswahl(String Query4AdressListe, String Query4Adressliste2, int iDropdownWidth, E2_SelectionComponentsVector SelVector)
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		this.oSelKunden = new MyE2_SelectField_WithAutoToolTip();
		this.oSelKunden.setWidth(new Extent(iDropdownWidth));
		this.oSelVector = SelVector;
		this.cQuery4AdressListe = Query4AdressListe;
		this.cQuery4AdressListe2 = Query4Adressliste2;

		this.oButtonLeseKundenNeuEin = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
		oButtonLeseKundenNeuEin.setToolTipText(new MyE2_String("Liste der beteiligten Firmen neu einlesen ...").CTrans());

		oButtonLeseKundenNeuEin.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				try
				{
					SELECTOR_COMPONENT_FirmenAuswahl.this.REFRESH_KundenSelektor();
					SELECTOR_COMPONENT_FirmenAuswahl.this.oSelVector.doActionPassiv();
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error refreshing Customer-List", false)));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}

			}

		});

		// jetzt die row fuellen
		this.add(oButtonLeseKundenNeuEin, E2_INSETS.I_0_0_0_0);
		this.add(this.oSelKunden, E2_INSETS.I_5_0_0_0);

	}
	
	
	
	/**
	 * 2012-06-19: neue variante mit formatierer, der inaktive grau macht
	 * @param Query4AdressListe
	 * @param QueryInaktive 
	 * @param iDropdownWidth
	 * @param SelVector
	 */
	public SELECTOR_COMPONENT_FirmenAuswahl(String Query4AdressListe,  int iDropdownWidth, String Query4Inaktive, E2_SelectionComponentsVector SelVector)
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());

		this.oSelKunden = new MyE2_SelectField_WithAutoToolTip();
		this.oSelKunden.setWidth(new Extent(iDropdownWidth));
		this.oSelVector = SelVector;
		this.cQuery4AdressListe = Query4AdressListe;
		this.cQuery4AdressListe2 = null;
		this.cQuery4InaktiveEintraege = Query4Inaktive;

		this.oButtonLeseKundenNeuEin = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
		oButtonLeseKundenNeuEin.setToolTipText(new MyE2_String("Liste der beteiligten Firmen neu einlesen ...").CTrans());

		oButtonLeseKundenNeuEin.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				try
				{
					SELECTOR_COMPONENT_FirmenAuswahl.this.REFRESH_KundenSelektor();
					SELECTOR_COMPONENT_FirmenAuswahl.this.oSelVector.doActionPassiv();
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error refreshing Customer-List", false)));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}

			}

		});

		// jetzt die row fuellen
		this.add(oButtonLeseKundenNeuEin, E2_INSETS.I_0_0_0_0);
		this.add(this.oSelKunden, E2_INSETS.I_5_0_0_0);

	}
	

	
	
	
	
	public SELECTOR_COMPONENT_FirmenAuswahl(	String  						Query4AdressListe,
												int 							iTextWidth, 
												int 							iDropdownWidth, 
												E2_SelectionComponentsVector 	SelVector, 
												MyString 						cBeschriftung,
												boolean                         bShowRefreshButton) 
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		this.oSelKunden = 		new MyE2_SelectField_WithAutoToolTip();
		this.oSelKunden.setWidth(new Extent(iDropdownWidth));
		this.oSelVector = 					SelVector;
		this.cQuery4AdressListe = 			Query4AdressListe;
		
		this.oButtonLeseKundenNeuEin = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
		oButtonLeseKundenNeuEin.setToolTipText(new MyE2_String("Liste der beteiligten Firmen neu einlesen ...").CTrans());
		
		oButtonLeseKundenNeuEin.add_oActionAgent(new XX_ActionAgent()
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				try
				{
					SELECTOR_COMPONENT_FirmenAuswahl.this.REFRESH_KundenSelektor();
					SELECTOR_COMPONENT_FirmenAuswahl.this.oSelVector.doActionPassiv();
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error refreshing Customer-List",false)));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage());
				}
			
			}
		
		});
		
		
		
		//jetzt die row fuellen
		this.add(new MyE2_Label(cBeschriftung).get_InBorderGrid(new Border(0,Color.BLACK,Border.STYLE_NONE), new Extent(iTextWidth), E2_INSETS.I_0_0_0_0), E2_INSETS.I_0_0_0_0);
		if (bShowRefreshButton)
		{
			this.add(oButtonLeseKundenNeuEin,E2_INSETS.I_5_0_0_0);
		}
		this.add(this.oSelKunden,E2_INSETS.I_5_0_0_0);
	
	}

	
	
	
	
	
	
	public MyE2_SelectField get_oSelKunden() 
	{
		return oSelKunden;
	}




	public E2_SelectionComponentsVector get_oSelVector() 
	{
		return oSelVector;
	}




	public String get_cQuery4AdressListe() 
	{
		return cQuery4AdressListe;
	}

	
	public void set_cQuery4AdressListe(String cNewQuery, boolean bRefresh) throws myException 
	{
		this.cQuery4AdressListe = cNewQuery;
		if (bRefresh)
		{
			this.REFRESH_KundenSelektor();
		}
	}

	
	
	
	public void REFRESH_KundenSelektor() throws myException
	{
		if (S.isFull(this.cQuery4AdressListe))
		{
			String[][] cResult = bibDB.EinzelAbfrageInArray(this.cQuery4AdressListe,"");
			
			if (cResult == null)
			{
				throw new myException("SELECTOR_COMPONENT_FirmenAuswahl:Error querying ADRESSE");
			}

			
			String[][] cResult2=null;
			
			if (S.isFull(this.cQuery4AdressListe2))
			{
				cResult2 = bibDB.EinzelAbfrageInArray(this.cQuery4AdressListe2,"");
				if (cResult2 == null)
				{
					throw new myException("SELECTOR_COMPONENT_FirmenAuswahl:Error querying ADRESSE, part 2");
				}
				
			}
			
			int iLen = cResult.length+1;
			
			if (cResult2!=null)
			{
				iLen+=cResult2.length;
			}
			
			String[][] cDropDown = new String[iLen][2];
			cDropDown[0][0]="-";
			cDropDown[0][1]="";
			
			int iCount=1;
			for (int i=0;i<cResult.length;i++)
			{
				cDropDown[iCount][0]=cResult[i][0];
				cDropDown[iCount][1]=cResult[i][1];
				iCount++;
			}
			
			if (cResult2!=null)
			{
				for (int i=0;i<cResult2.length;i++)
				{
					cDropDown[iCount][0]=cResult2[i][0];
					cDropDown[iCount][1]=cResult2[i][1];
					iCount++;
				}
			}
		
			
			this.oSelKunden.set_ListenInhalt(cDropDown,false);
			this.oSelKunden.set_ActiveInhalt_or_FirstInhalt("");


			//2012-06-19: inaktive koennen markiert werden
			if (S.isFull(this.cQuery4InaktiveEintraege))
			{
				String[][] cInaktive = bibDB.EinzelAbfrageInArray(this.cQuery4InaktiveEintraege,"");
				
				if (cInaktive!=null)
				{
					Vector<String> vInaktiveEintraege = new Vector<String>();
					for (int i=0;i<cInaktive.length;i++)
					{
						if (cInaktive[i].length>0)
						{
							vInaktiveEintraege.add(cInaktive[i][0]);
						}
					}
					
					this.oSelKunden.setCellRenderer(new MyE2_SelectField.ownListCellRenderer(vInaktiveEintraege));
					
				}
				else
				{
					this.oSelKunden.setCellRenderer(new DefaultListCellRenderer());
				}
			}
			
			
			
		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Neuaufbau nicht möglich ..."));
		}
		
	}


	public MyE2_Button get_oButtonLeseKundenNeuEin() 
	{
		return oButtonLeseKundenNeuEin;
	}

	
}
