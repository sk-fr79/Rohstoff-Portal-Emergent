package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SIMPLE;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_Usersetting_SiteLength extends E2_UserSetting_SIMPLE
{

	private String cNAVI_LIST_AUTOMATC_GENERATED_KENNUNG = null;
	
	public E2_Usersetting_SiteLength(String NAVI_LIST_AUTOMATC_GENERATED_KENNUNG)
	{
		super(ENUM_USER_SAVEKEY.SESSION_HASH_USER_SAVE_LIST_SEITENLEANGE.name());
		this.cNAVI_LIST_AUTOMATC_GENERATED_KENNUNG = NAVI_LIST_AUTOMATC_GENERATED_KENNUNG;
	}

	
//	public E2_Usersetting_SiteLength(E2_NavigationList oNaviList)
//	{
//		super(XXX_UserSetting.SESSION_HASH_USER_SAVE_LIST_SEITENLEANGE);
//		this.oNaviList = oNaviList;
//	}
//	
	
	public int STORE(Object oModuleSettings) throws myException
	{
		int iRueck =super.STORE(this.cNAVI_LIST_AUTOMATC_GENERATED_KENNUNG, oModuleSettings);
		
		//System.out.println("Seitenlaenge gespeichert: "+cNAVI_LIST_AUTOMATC_GENERATED_KENNUNG+" --> "+(String)oModuleSettings);
		
		return iRueck;
	}
	
	
	
	public int get_StoredListLengt(int iLengthWhenNothing) throws myException
	{
		Integer iRueck = null;
		
		if (S.isFull(this.cNAVI_LIST_AUTOMATC_GENERATED_KENNUNG))
		{
			String cRueck = (String)this.get_Settings(this.cNAVI_LIST_AUTOMATC_GENERATED_KENNUNG);
			if (S.isFull(cRueck) && bibALL.isInteger(cRueck))
			{
				iRueck = new Integer(cRueck);
			}
		}
		if (iRueck == null)
		{
			iRueck = new Integer(iLengthWhenNothing);
		}
		
		return iRueck.intValue();
		
	}
	
	
}
