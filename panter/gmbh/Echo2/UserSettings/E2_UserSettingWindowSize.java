package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSettingWindowSize extends XXX_UserSetting {



	public boolean prepareContainer(E2_BasicModuleContainer oContainer) throws myException
	{
		boolean bRueck = false;
		
		if (this.Check_if_storable(oContainer))
		{

			Extent[] oExt = (Extent[])this.get_Settings(this.Extract_ModuleKenner_From_Classname(oContainer)+S.NN(oContainer.get_cADDON_TO_CLASSNAME()));
			if (oExt != null)
			{
				if (oExt[0]!=null) oContainer.get_oWindowPane().setPositionX(oExt[0]);
				if (oExt[1]!=null) oContainer.get_oWindowPane().setPositionY(oExt[1]);
				if (oExt[2]!=null) oContainer.get_oWindowPane().setWidth(oExt[2]);
				if (oExt[3]!=null) oContainer.get_oWindowPane().setHeight(oExt[3]);
				
				oContainer.set_oExtLeftPos(oContainer.get_oWindowPane().getPositionX());
				oContainer.set_oExtTopPos(oContainer.get_oWindowPane().getPositionY());
				oContainer.set_oExtWidth(oContainer.get_oWindowPane().getWidth());
				oContainer.set_oExtHeight(oContainer.get_oWindowPane().getHeight());
				
				
				// /hier nachsehen, ob der container ein MyE2_TabbedPane mit autosize=true enthaelt, wenn ja nachjustieren


				E2_RecursiveSearch_Component oSearchComp = new E2_RecursiveSearch_Component(
						oContainer,
						bibALL.get_Vector(MyE2_TabbedPane.class.getName(), E2_Selection_Row_With_Multi_Cols.class.getName()),
						null);


				if (oSearchComp.get_vAllComponents().size()>=1)
				{
					//wenn der container eine angabe zur hoehe des tabbs im verhaeltnis zum fenster hat, dann diese nehmen, sonst die aus dem USER
					if (oContainer.get_iVerticalOffsetForTabbedPane() != null)
					{
						Integer  iOffset= oContainer.get_iVerticalOffsetForTabbedPane();

						if (oSearchComp.get_vAllComponents().get(0) instanceof MyE2_TabbedPane)
						{
							if ( ((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0)).get_bAutoHeight())
							{
								((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0)).setHeight(new Extent(oExt[3].getValue()-iOffset));
							}
						}
						else if (oSearchComp.get_vAllComponents().get(0) instanceof E2_Selection_Row_With_Multi_Cols)
						{
							if ( ((E2_Selection_Row_With_Multi_Cols)oSearchComp.get_vAllComponents().get(0)).get_bAutoHeight())
							{
								((E2_Selection_Row_With_Multi_Cols)oSearchComp.get_vAllComponents().get(0)).set_iHeight_in_Pixel(oExt[3].getValue()-iOffset);
							}
						}
					}
					else
					{
						if (oSearchComp.get_vAllComponents().get(0) instanceof MyE2_TabbedPane)
						{
							if ( ((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0)).get_bAutoHeight())
							{
								((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0)).setHeight(new Extent(oExt[3].getValue()-(int)bibALL.get_RECORD_USER().get_VERTICAL_OFFSET_MASKTABS_lValue(new Long(180)).longValue()));
							}
						}
						else if (oSearchComp.get_vAllComponents().get(0) instanceof E2_Selection_Row_With_Multi_Cols)
						{
							if ( ((E2_Selection_Row_With_Multi_Cols)oSearchComp.get_vAllComponents().get(0)).get_bAutoHeight())
							{
								((E2_Selection_Row_With_Multi_Cols)oSearchComp.get_vAllComponents().get(0)).set_iHeight_in_Pixel(oExt[3].getValue()-(int)bibALL.get_RECORD_USER().get_VERTICAL_OFFSET_MASKTABS_lValue(new Long(180)).longValue());
							}
						}
					}
				}
				
				
				//2011-07-05: resizing programmierbar
				if (oContainer.get_oResizeHelper()!=null)
				{
					oContainer.get_oResizeHelper().do_actionAfterResizeWindow(oContainer);
				}
				
			}
			bRueck = true;
		}

		return bRueck;
	}
	
	
	
	public int storeContainer(E2_BasicModuleContainer oContainer) throws myException
	{
		int iRueck = 0;
		
		if (oContainer.get_oWindowPane() != null)
		{
			
			if (this.Check_if_storable(oContainer))
			{
				Extent[] arrayExt = new Extent[4];
				arrayExt[0] = oContainer.get_oWindowPane().getPositionX(); 
				arrayExt[1] = oContainer.get_oWindowPane().getPositionY(); 
				arrayExt[2] = oContainer.get_oWindowPane().getWidth();
				arrayExt[3] = oContainer.get_oWindowPane().getHeight();
				
				iRueck = this.STORE(this.Extract_ModuleKenner_From_Classname(oContainer)+S.NN(oContainer.get_cADDON_TO_CLASSNAME()), arrayExt);
				
				//gleich einstellen
				this.prepareContainer(oContainer);
			}
		}
		return iRueck;
	}

	/**
 	 * 20180307: weitere speichermethode
 	 * Speichern, ohne bestehends popupWindow
	 * @param oContainer
	 * @param left
	 * @param top
	 * @param width
	 * @param height
	 * @return
	 * @throws myException
	 */
	public int storeContainer(E2_BasicModuleContainer oContainer, Extent left, Extent top, Extent width, Extent height) throws myException {
		int iRueck = 0;
		
		if (this.Check_if_storable(oContainer)) {
			Extent[] arrayExt = new Extent[4];
			arrayExt[0] = left; 
			arrayExt[1] = top; 
			arrayExt[2] = width;
			arrayExt[3] = height;
			iRueck = this.STORE(this.Extract_ModuleKenner_From_Classname(oContainer)+S.NN(oContainer.get_cADDON_TO_CLASSNAME()), arrayExt);
		}
		return iRueck;
	}

	
	
	public String get_SessionHash() 
	{
		return ENUM_USER_SAVEKEY.SESSION_HASH_USER_WINDOWSIZE.name();
	}


	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
	{

		String cRueck = "";
		try
		{
			Extent[] oWindowSize = (Extent[])oSetting;
			
			
			for (int i=0;i<4;i++)
			{
				if (oWindowSize[i]==null)
				{
					cRueck += "@";
				}
				else
				{
					cRueck += oWindowSize[i].getValue();
				}
				
				if (i<3) cRueck +="|";
			}
			
		}
		catch (Exception ex)
		{
			throw new myException(this.getClass().getName()+": get_DatabaseValueForSettings:"+ex.getLocalizedMessage());
		}
		return cRueck;
	}



	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting) throws myException 
	{
		Extent[] oRueck = null;
		
		try
		{
			Vector<String> vTeile = bibALL.TrenneZeile(cDatabaseSetting, "|");
			oRueck = new Extent[4];

			for (int i=0;i<4;i++)
			{
				if (bibALL.isEmpty((String)vTeile.get(i)) || ((String)vTeile.get(i)).equals("@"))
				{
					oRueck[i] = null;
				}
				else
				{
					oRueck[i] = new Extent(Integer.parseInt(((String)vTeile.get(i))));
				}
			}
		}
		catch (Exception ex)
		{
			throw new myException(this.getClass().getName()+": get_DatabaseValueForSettings:"+ex.getLocalizedMessage());
		}
		
		return oRueck;
	}




}
