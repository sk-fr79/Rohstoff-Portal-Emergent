package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class E2_ListButtonExtendDaughterObject extends MyE2_ButtonInLIST
{
	private static final E2_ResourceIcon oIconClosed = E2_ResourceIcon.get_RI("expandopen_small.png");
	private static final E2_ResourceIcon oIconOpend = E2_ResourceIcon.get_RI("expandclose_small.png");
	private static final E2_ResourceIcon oIconDisabled = E2_ResourceIcon.get_RI("expanddisabled_small.png");
	
	
	private boolean  bCanBeOpened = true;
	
	/**
	 * 
	 * @param bAddHeader4ExtendAll
	 * @param oNaviList
	 *   WENN bAddHeader4ExtendAll=true und oNaviList != null, dann wird ein ausklapp-Alle-Button in den header geschrieben
	 */
	public E2_ListButtonExtendDaughterObject(boolean bAddHeader4ExtendAll, E2_NavigationList oNaviList)
	{
		//super(E2_ResourceIcon.get_RI("new.png"),E2_ResourceIcon.get_RI("new.png"));
		this.__setImages(E2_ListButtonExtendDaughterObject.oIconClosed,E2_ListButtonExtendDaughterObject.oIconClosed);
		
		// in die extender-spalte einen header-button vorsehen
		//jetzt der spalte mit dem Tochterlisten-extender einen header verpassen
		if (bAddHeader4ExtendAll && oNaviList != null)
		{
			this.EXT().set_oCompTitleInList(new E2_ListButtonExtendDaughterObjects_LISTHEADLINE(oNaviList));
		}

		
		/*
		 * action-agent, der markiert 
		 */
		this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo)
				{
					if (! E2_ListButtonExtendDaughterObject.this.bCanBeOpened)   //falls oeffnen verboten ist, dann nichts tun
					{
						return;
					}
					
					
					E2_ComponentMAP oMap = ((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_oComponentMAP();
					if (oMap.get_List_EXPANDER_4_ComponentMAP() != null)
					{
						/*
						 * dann diesem button auch den messageagent uebergeben
						 */
						E2_ListButtonExtendDaughterObject oThis = E2_ListButtonExtendDaughterObject.this;

						oThis.set_bIsOpen(!oThis.get_bIsOpen());
						
						/*
						 * dann liste in der aktuellen ansicht neu aufbauen 
						 */
						try
						{
							oMap.get_List_EXPANDER_4_ComponentMAP().get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);
						}
						catch (myException ex)
						{
							ex.printStackTrace();
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Neuaufbau der Liste !!!"));
						}
					}
				}
			});

	}



	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		E2_ListButtonExtendDaughterObject oButton = new E2_ListButtonExtendDaughterObject(false, null);

		// die folgenden anweisung scheint schwachsinnig, ist aber noetig, da
		// sonst die buttons in einigen faellen verschwinden
		oButton.__setImages(this.get_oImgEnabled(),this.get_oImgDisabled());
		return oButton;
	}
	
	

	public void set_bIsOpen(boolean IsOpen)
	{
		E2_ComponentMAP oMap = this.EXT().get_oComponentMAP();

		if (IsOpen)
			this.__setImages(E2_ListButtonExtendDaughterObject.oIconOpend,E2_ListButtonExtendDaughterObject.oIconOpend);
		else
			this.__setImages(E2_ListButtonExtendDaughterObject.oIconClosed,E2_ListButtonExtendDaughterObject.oIconClosed);

		
		if (oMap.get_List_EXPANDER_4_ComponentMAP()!=null)
			oMap.get_List_EXPANDER_4_ComponentMAP().set_bIsOpen(IsOpen);
		
	}


	//2010-12-21: liste auf automatisches oeffnen stellen
	public void set_bButtonAnsichtOpen(boolean IsOpen)
	{
		if (IsOpen)
		{
			this.__setImages(E2_ListButtonExtendDaughterObject.oIconOpend,E2_ListButtonExtendDaughterObject.oIconOpend);
		}
		else
		{
			if (this.bCanBeOpened)
			{
				this.__setImages(E2_ListButtonExtendDaughterObject.oIconClosed,E2_ListButtonExtendDaughterObject.oIconClosed);
			}
			else
			{
				this.__setImages(E2_ListButtonExtendDaughterObject.oIconDisabled,E2_ListButtonExtendDaughterObject.oIconDisabled);
			}
		}

	}


	
	
	public boolean get_bIsOpen()
	{
		boolean bRueck = false;
		E2_ComponentMAP oMap = this.EXT().get_oComponentMAP();
		
		if (oMap.get_List_EXPANDER_4_ComponentMAP()!=null)
			if (oMap.get_List_EXPANDER_4_ComponentMAP().get_bIsOpen())
				bRueck=true;
		
		
		return bRueck;
	}



	public boolean get_bCanBeOpened()
	{
		return bCanBeOpened;
	}



	public void set_bCanBeOpened(boolean canBeOpened)
	{
		if (canBeOpened)
		{
			this.__setImages(E2_ListButtonExtendDaughterObject.oIconClosed,E2_ListButtonExtendDaughterObject.oIconClosed);
		}
		else
		{
			this.__setImages(E2_ListButtonExtendDaughterObject.oIconDisabled,E2_ListButtonExtendDaughterObject.oIconDisabled);
		}
		
		bCanBeOpened = canBeOpened;
	}



	
	
}
