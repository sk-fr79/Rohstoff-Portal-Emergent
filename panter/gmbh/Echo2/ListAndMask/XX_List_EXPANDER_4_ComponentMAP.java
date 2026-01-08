package panter.gmbh.Echo2.ListAndMask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.List.E2_ListButtonExtendDaughterObject;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_List_EXPANDER_4_ComponentMAP implements E2_IF_Copy
{
	private boolean 			bIsOpen	= 				false;
	private int 				iLeftColumnOffset = 	1;                   	// wieviele spalten wird links eingerueckt ?
	private E2_NavigationList 	oNavigationList = 		null;
	private E2_ComponentMAP	   	oComponentMAP_this_BelongsTo = null;     		// zu welcher map gehoert die tochter
	
	
	//2011-11-29: dem Expander-Objekt wird waehrend des kopierens ein E2_ListButtonExtendDaughterObject - zugewiesen, wenn die mutter einen solchen beinhaltet
	private E2_ListButtonExtendDaughterObject    oExpanderButton = null;  
	
	
	
	/*
	 * methode liefert eine komponente, die zu einer zeile (ID) einer liste gehoert. Wenn diese Version null liefert, dann wird die naechste geladen 
	 */
	public abstract Component 								get_ComponentDaughter(String cID_ROW_Unformated) throws myException;
	

	/*
	 * methode liefert eine ArrayList mit Hashmaps<MyE2IF_Component,Component>.
	 * wobei jede zeile des arrays fuer eine Ausgeklappte Listenzeile steht und als HashKey immer
	 * das objekt, das an der jeweiligen stelle in der uebergeordneten liste steht 
	 */
	public abstract ArrayList<HashMap<String, Component>> 			get_ComponentListDaughter(String cID_ROW_Unformated) throws myException;
	
	
	/*
	 * wichtig !! Refresh funktioniert nur uneingeschraenkt fuer komponenten des Type XX_ComponentMAP_DaughterObjectForList .TYPE_ONE_COMPONENT
	 * da nur dort in der liste ein einzelnes element vorhanden ist. bei allen anderen werden die elemente in die normale Liste einge-
	 * blendet und sind so nicht mehr zu akatualisieren, da evtl. dazugenommene Zeilen keine container mehr haben.
	 * Hier muss die E2_NavigationList neu aufgebaut werden. Dann ist im RefreshDaughterComponent ein komplettes Seiten-Refresh der liste zu machen
	 */
	public abstract void	 	refreshDaughterComponent()  throws myException;


	public XX_List_EXPANDER_4_ComponentMAP(E2_NavigationList 	NavigationList) throws myException
	{
		this.oNavigationList = NavigationList;
	}


	public boolean get_bIsOpen()
	{
		//aenderung 2010-12-21: open auch dann, wenn die E2_Componentmap den schalter fuer automatisch-oeffnen auf Y stellt
		boolean openAusMap = false;
		if (this.oComponentMAP_this_BelongsTo!=null)
		{
			openAusMap = this.oComponentMAP_this_BelongsTo.get_bExtendSublistsAutomatic();
			
			//wenn das open aus dem automatismus kommt, muss auch der button auf open gesetzt werden
			if (openAusMap)
			{
				Iterator<MyE2IF__Component> iterator = this.oComponentMAP_this_BelongsTo.get_hmRealComponents().values().iterator();
				while (iterator.hasNext())
				{
					MyE2IF__Component oComp = (MyE2IF__Component)iterator.next();
					if (oComp instanceof E2_ListButtonExtendDaughterObject)
					{
						((E2_ListButtonExtendDaughterObject)oComp).set_bButtonAnsichtOpen(true);
					}
				}
			}
		}
		
		return bIsOpen || openAusMap;
	}

	
	
	public void set_bIsOpen(boolean isOpen)
	{
		bIsOpen = isOpen;
		
		//aenderung 2010-12-21: open auch dann, wenn die E2_Componentmap den schalter fuer automatisch-oeffnen auf Y stellt
		//wird eine tochtermap einmal auf false gesetzt, dann wird die beinhaltende map auch wieder resetted, die info ob permanent-ausklapp wird nur in der Referenz-Map gespeichert
		if (!isOpen)
		{
			if (this.oComponentMAP_this_BelongsTo!=null)
			{
				this.oComponentMAP_this_BelongsTo.set_bExtendSublistsAutomatic(false);
			}
		}
		
		
	}

	public int get_iLeftColumnOffset()
	{
		return iLeftColumnOffset;
	}

	public void set_iLeftColumnOffset(int leftColumnOffset)
	{
		iLeftColumnOffset = leftColumnOffset;
	}

	public E2_NavigationList get_oNavigationList()
	{
		return oNavigationList;
	}

	public void set_oNavigationList(E2_NavigationList navigationList)
	{
		oNavigationList = navigationList;
	}


	public E2_ComponentMAP get_E2_ComponentMAP_this_BelongsTo()
	{
		return oComponentMAP_this_BelongsTo;
	}

	public void set_E2_ComponentMAP_this_BelongsTo(	E2_ComponentMAP componentMAP_this_BelongsTo)
	{
		oComponentMAP_this_BelongsTo = componentMAP_this_BelongsTo;
	}


	//2011-11-29: dem Expander-Objekt wird waehrend des kopierens ein E2_ListButtonExtendDaughterObject - zugewiesen, wenn die mutter einen solchen beinhaltet
	public E2_ListButtonExtendDaughterObject get_ownExpanderButton()
	{
		return oExpanderButton;
	}


	//2011-11-29: dem Expander-Objekt wird waehrend des kopierens ein E2_ListButtonExtendDaughterObject - zugewiesen, wenn die mutter einen solchen beinhaltet
	public void set_ownExpanderButton(E2_ListButtonExtendDaughterObject oExpanderButton)
	{
		this.oExpanderButton = oExpanderButton;
	}
	
}
