package panter.gmbh.Echo2.Container;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.WindowPane;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.components.E2_Tabb_Sheet_For_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.E2_TabbedPaneForFirstContainer;
import panter.gmbh.indep.bibALL;

public class E2_ContentPane extends ContentPane
{


	/*
	 * ist die contentpane basispane eines popup-fensters, dann wird hier der timestamp des rufenden containers mitgenommen
	 */
	private String   				MILLISECONDSTAMP = null;
	

	public E2_ContentPane(boolean bFormat)
	{
		super();
		if (bFormat) this.setStyle(new Style_ContentPane_Normal());
	}

	
	public void add(Component oComp)
	{
		super.add(oComp);
		
		
		// falls es ein windowpane ist, immer den zindex wert weiter hoch setzen
		if (oComp instanceof WindowPane)
		{
			((WindowPane)oComp).setZIndex(bibALL.get_iZIndexNEXT());
		}
		
	}

	
	public String get_MILLISECONDSTAMP() 
	{
		return this.MILLISECONDSTAMP;
	}

	public void set_MILLISECONDSTAMP(String millisecondstamp) 
	{
		this.MILLISECONDSTAMP = millisecondstamp;
	}
	
	
	private class Style_ContentPane_Normal extends MutableStyle
	{

		public Style_ContentPane_Normal()
		{
			super();
			
			this.setProperty( ContentPane.PROPERTY_BACKGROUND, new E2_ColorBase(0));
		}
		
	}


	
	public E2_BasicModuleContainer get_NewestActivMessageBasicModuleContainer()
	{
		E2_BasicModuleContainer oContainer4Messages = null;
		
		//test, ob die reihenfolge angezeigt wird
		Component[] arrayComp = this.getComponents();
		for (int i=arrayComp.length-1;i>=0;i--)
		{
			if (arrayComp[i] instanceof MyE2_WindowPane)
			{
				MyE2_WindowPane  oWindowPane = (MyE2_WindowPane)arrayComp[i];
				
				if (oWindowPane.get_oContainerThisBelongsTo()!=null)
				{
					if (oWindowPane.get_oContainerThisBelongsTo().get_bMessageBereichEin() && 
						oWindowPane.get_oContainerThisBelongsTo().get_bPopUpWasShown())
					{
						oContainer4Messages = oWindowPane.get_oContainerThisBelongsTo();
						break;
					}
				}
			}
		}
		
		
		//wenn an dieser stelle noch nix gefunden wurde, dann auf den Tab-container mit dessen aktivem tab zurueckgreifen
		if (oContainer4Messages==null)
		{
			E2_TabbedPaneForFirstContainer oTabbedPane = bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION();
			
			if (oTabbedPane.isVisible())
			{
				if (oTabbedPane.get_vTabbs().size()>0)
				{
					int iActivTab = oTabbedPane.getSelectedIndex();
					if (iActivTab>=0 && iActivTab<oTabbedPane.get_vTabbs().size())
					{
						E2_Tabb_Sheet_For_BasicModuleContainer  oActivTabSheet = oTabbedPane.get_vTabbs().get(iActivTab);
						
						if (oActivTabSheet.get_oModuleContainer().get_bMessageBereichEin())
						{
							oContainer4Messages = oActivTabSheet.get_oModuleContainer();
						}
					}
				}
			}
		}
		return oContainer4Messages;
	}
	
	
	public void clean_oldMessages()
	{
		
		//test, ob die reihenfolge angezeigt wird
		Component[] arrayComp = this.getComponents();
		for (int i=arrayComp.length-1;i>=0;i--)
		{
			if (arrayComp[i] instanceof MyE2_WindowPane)
			{
				MyE2_WindowPane  oWindowPane = (MyE2_WindowPane)arrayComp[i];
				
				if (oWindowPane.get_oContainerThisBelongsTo()!=null)
				{
					if (oWindowPane.get_oContainerThisBelongsTo().get_bMessageBereichEin() && 
						oWindowPane.get_oContainerThisBelongsTo().get_bPopUpWasShown())
					{
						oWindowPane.get_oContainerThisBelongsTo().cleanMessages();
					}
				}
			}
		}
		
		//wenn an dieser stelle noch nix gefunden wurde, dann auf den Tab-container mit dessen aktivem tab zurueckgreifen
		E2_TabbedPaneForFirstContainer oTabbedPane = bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION();
		
		if (oTabbedPane.isVisible())
		{
			if (oTabbedPane.get_vTabbs().size()>0)
			{
				for (int i=0;i<oTabbedPane.get_vTabbs().size();i++)
				{
					if (oTabbedPane.get_vTabbs().get(i).get_oModuleContainer().get_bMessageBereichEin())
					{
						oTabbedPane.get_vTabbs().get(i).get_oModuleContainer().cleanMessages();
					}
				}
			}
		}
	}

}
