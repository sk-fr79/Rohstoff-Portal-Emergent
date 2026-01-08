package panter.gmbh.Echo2.ListAndMask.List.Settings;

import java.util.HashMap;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;

/**
 * 2012-09-05: usersetting-objekt zum speichern der listeneinstellungen betreff multiselektion und markierung in der liste
 * @author martin
 *
 */
public class E2_Usersetting_ListSettings extends E2_UserSetting_HashMap
{

	public static String SESSIONHASH_SAVE_LISTESETTINGS = "@@@LIST_MARKER_MULTISELECT_HANDLING###";
	

	public E2_Usersetting_ListSettings()
	{
		super(E2_Usersetting_ListSettings.SESSIONHASH_SAVE_LISTESETTINGS,E2_NavigationList.NAVILIST_SETTINGS_HASHMAP);
	}

	
	public static Color get_oColorFromListSetting(HashMap<String, String> hmValues)
	{
		MyInteger  intRED = new MyInteger(hmValues.get(E2_NavigationList.NAVILIST_MARK_COLOR_RED));
		MyInteger  intGREEN = new MyInteger(hmValues.get(E2_NavigationList.NAVILIST_MARK_COLOR_GREEN));
		MyInteger  intBLUE = new MyInteger(hmValues.get(E2_NavigationList.NAVILIST_MARK_COLOR_BLUE));
		
		int iRed= 		0;
		int iGreen=		0;
		int iBlue=		0;
		
		if (intRED.get_oInteger()!=null) 	{ iRed = intRED.get_iValue();}
		if (intGREEN.get_oInteger()!=null) 	{ iGreen = intGREEN.get_iValue();}
		if (intBLUE.get_oInteger()!=null) 	{ iBlue = intBLUE.get_iValue();}
		
		return new Color(iRed, iGreen, iBlue);
		
	}
	

	public static Font  get_oFontFromListSetting(HashMap<String, String> hmValues)
	{
		Font oFontRueck = new E2_FontPlain();
		
		String cSize = S.NN(hmValues.get(E2_NavigationList.NAVILIST_MARK_FONTSIZE));
		String cBold =  S.NN(hmValues.get(E2_NavigationList.NAVILIST_MARK_FONT_BOLD));
		String cItalic = S.NN(hmValues.get(E2_NavigationList.NAVILIST_MARK_FONT_ITALIC));
		
		if (S.isFull(cSize) && S.isFull(cBold) && S.isFull(cItalic) && bibALL.isInteger(cSize))
		{
			if (cSize.trim().equals("0"))
			{
				//dann ist der font undefiniert
				return null;
			}
			
			int iSize = 10;
			MyInteger intConvert = new MyInteger(cSize);
			if (intConvert.get_oInteger()!=null)
			{
				iSize=intConvert.get_iValue();
			}
			boolean bBold = cBold.equals("Y");
			boolean bItalic = cItalic.equals("Y");

			if (!bBold && !bItalic)
			{
				oFontRueck = new Font(Font.ARIAL, Font.PLAIN,new Extent(iSize, Extent.PT));
			}
			else if (bBold && !bItalic)
			{
				oFontRueck = new Font(Font.ARIAL, Font.BOLD,new Extent(iSize, Extent.PT));
			}
			else if (!bBold && bItalic)
			{
				oFontRueck = new Font(Font.ARIAL, Font.ITALIC,new Extent(iSize, Extent.PT));
			}
			else
			{
				oFontRueck = new Font(Font.ARIAL, Font.BOLD+Font.ITALIC,new Extent(iSize, Extent.PT));
			}
		}
		
		return oFontRueck;
		
	}
	
	
}
