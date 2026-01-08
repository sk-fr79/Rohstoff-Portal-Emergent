package panter.gmbh.Echo2.ListAndMask.List;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class E2_CheckBoxForList extends XX_CheckBoxForList
{
	
	public E2_CheckBoxForList()
	{
		super();
	}

	
	public E2_CheckBoxForList(int iColWidth_inNaviList)
	{
		super();
		this.EXT().set_oColExtent(new Extent(iColWidth_inNaviList));
	}


	@Override
	public MyE2IF__Component get_TitleComponent4Checkbox(E2_NavigationList oNaviList) throws myException
	{
		return new E2_SelectAllNoneInvert(oNaviList);
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		E2_CheckBoxForList oCheckBoxForList = new E2_CheckBoxForList();
		
		oCheckBoxForList.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCheckBoxForList));
		oCheckBoxForList.__setText(this.get_oText());
		try
		{
			oCheckBoxForList.set_bEnabled_For_Edit(this.isEnabled());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}

		oCheckBoxForList.setStyle(this.getStyle());
		oCheckBoxForList.setFocusTraversalParticipant(this.isFocusTraversalParticipant());
		
		
		return oCheckBoxForList;
	}

	
	
}
