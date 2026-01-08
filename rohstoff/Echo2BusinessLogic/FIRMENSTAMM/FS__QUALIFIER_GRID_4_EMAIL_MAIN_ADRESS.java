package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DB_CheckBoxGrid;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DEF_CheckBox;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DEF_Element;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_QUALIFIER_KEYS;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * qualifiziererklasse, um zu definieren, wofuer eine mail-adresse gebraucht wird
 * @author martin
 *
 */
public class FS__QUALIFIER_GRID_4_EMAIL_MAIN_ADRESS extends Q_DB_CheckBoxGrid
{
	private 		String 		cTableName = null;


	/**
	 * 
	 * @param oSQLField
	 * @param TableName
	 * @throws myException
	 */
	public FS__QUALIFIER_GRID_4_EMAIL_MAIN_ADRESS(SQLField oSQLField, String TableName) throws myException
	{
		super(oSQLField, false, 2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL(),MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_5_0), new E2_FontPlain(-2),true);
		this.cTableName = TableName;
		//der schalter "eMailadresse fuer direkte re/gut-versendung wird nicht mit allen anderen aktiviert
		this.get_vKeys_Not_To_Select_All().add(myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT);   
	}

	
	@Override
	public Vector<Q_DEF_Element> build_vQ_DEF_Elements(MyE2IF__Component oMotherComponent) throws myException
	{
		return new __VECTOR_QUALIFIER_EMAIL_MATRIX(oMotherComponent, true);

	}

	
	@Override
	public String get_cTABLE_NAME() throws myException
	{
		return this.cTableName;
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			FS__QUALIFIER_GRID_4_EMAIL_MAIN_ADRESS oCopy = new FS__QUALIFIER_GRID_4_EMAIL_MAIN_ADRESS(this.EXT_DB().get_oSQLField(), this.cTableName);
			oCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCopy));
			return oCopy;
		}
		catch (myException e)
		{
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}


	@Override
	public HashMap<String, Vector<XX_ActionAgent>> get_hmZusatzActionAgents4Checkboxes(Q_DB_CheckBoxGrid oGrid) throws myException
	{
		return null;
	}

	@Override
	public HashMap<String, Vector<XX_ActionValidator>> get_hmZusatzGlobalValidators(Q_DB_CheckBoxGrid oGrid) throws myException
	{
		return null;
	}


	@Override
	public void Format_CB(Q_DEF_CheckBox oFormatedCheckBox) throws myException
	{
		
	}


	@Override
	public String get_cCLASS_KEY()
	{
		return Q_QUALIFIER_KEYS.QUALIFIER_KEY_EMAIL_VERWENDUNGSTYP;
	}

	
	
	public void Preset_Values(Vector<String> vMAILTYPEN) {
		for (String cMAILTYP: this.get_hmQ_DEF_CheckBoxen().keySet()) {
			this.get_hmQ_DEF_CheckBoxen().get(cMAILTYP).setSelected(vMAILTYPEN.contains(cMAILTYP));
		}
	}

	
	
	
	
	
	
}
