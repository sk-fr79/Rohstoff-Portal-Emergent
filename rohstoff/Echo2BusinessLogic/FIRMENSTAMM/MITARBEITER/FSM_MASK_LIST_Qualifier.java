package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DB_Button;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DEF_Element;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FSM_MASK_LIST_Qualifier extends Q_DB_Button
{

	public FSM_MASK_LIST_Qualifier(SQLField oSQLField, boolean IsUsedInListMode) throws myException
	{
		super(new MyE2_String("Zuständig für: "),new MyE2_String("Bitte geben Sie die Zuständigkeit an ...."),oSQLField, IsUsedInListMode);
		
		this.set_iNumberCheckboxRowsInPopup(1);
	}

	
	@Override
	public E2_BasicModuleContainer get_oModuleContainer() throws myException
	{
		return new ownE2Container();
	}

	@Override
	public Vector<Q_DEF_Element> build_vQ_DEF_Elements(MyE2IF__Component  oMotherComp) throws myException
	{
		Vector<Q_DEF_Element>  vQDEF = new Vector<Q_DEF_Element>();
		
		vQDEF.add(new Q_DEF_Element("TAG_A", "Variante A", "VA", null,oMotherComp, "Mitarbeiter der Variante A"));
		vQDEF.add(new Q_DEF_Element("TAG_B", "Variante B", "VB", null,oMotherComp, "Mitarbeiter der Variante B"));
		vQDEF.add(new Q_DEF_Element("TAG_C", "Variante C", "VC", null, oMotherComp, "Mitarbeiter der Variante C"));

		return vQDEF;
	}

	@Override
	public String get_cTABLE_NAME() throws myException
	{
		return "ADRESSE";
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			FSM_MASK_LIST_Qualifier  oButton = new FSM_MASK_LIST_Qualifier(this.EXT_DB().get_oSQLField(),this.get_bIsUsedInListMode());
			oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
			return oButton;
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}

	
	private class ownE2Container extends E2_BasicModuleContainer
	{
		public ownE2Container()
		{
			super();
		}
	}


	@Override
	public String get_cCLASS_KEY()
	{
		return Q_QUALIFIER_KEYS.QUALIFIER_KEY_TEST_MITARBEITER;
	}
	
}
