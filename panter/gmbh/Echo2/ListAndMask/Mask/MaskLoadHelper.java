/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.Mask;

import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;


public class MaskLoadHelper 
{
	private Vector<Naming> 			vNames = new Vector<Naming>();
	private E2_ComponentMAP 		oMapOfMask = null;
	private MyDataRecordHashMap  	oHashMapOfValuesToFill = null;
	
	private Vector<ValueDirect>  	vFreeValues = new Vector<ValueDirect>();
	

	public MaskLoadHelper(E2_ComponentMAP mapOfMask, MyDataRecordHashMap hashMapOfValuesToFill)
	{
		super();
		oMapOfMask = mapOfMask;
		oHashMapOfValuesToFill = hashMapOfValuesToFill;
	}

	/**
	 * @param nameInComponentMAP
	 * @param nameInDataRecordHashMAP
	 * @param bDisableFromInteractive
	 */
	public void addFieldToFill(String nameInComponentMAP, String nameInDataRecordHashMAP, boolean bDisableFromInteractive)
	{
		this.vNames.add(new Naming(nameInComponentMAP,nameInDataRecordHashMAP,bDisableFromInteractive));			
	}
	
	
	
	/**
	 * @param cNameField
	 */
	public void addFieldToFill(String cNameField)
	{
		this.vNames.add(new Naming(cNameField,cNameField,false));			
	}
	
	
	
	/**
	 * @param cNameField
	 * @param bDisableFromInteractive
	 */
	public void addFieldToFill(String cNameField,boolean bDisableFromInteractive)
	{
		this.vNames.add(new Naming(cNameField,cNameField,bDisableFromInteractive));			
	}

	

	/**
	 * @param cNameField
	 * @param cValueFormated
	 * @param bDisableFromInteractive
	 */
	public void addDirectValue(String cNameField, String cValueFormated,boolean bDisableFromInteractive)
	{
		this.vFreeValues.add(new ValueDirect(cNameField,cValueFormated,bDisableFromInteractive));			
	}

	
	
	

	public void FillFields( ) throws myException
	{
		//zuerst die Names-liste abarbeiten
		for (int i=0;i<vNames.size();i++)
		{
			Naming oName = (Naming)vNames.get(i);
			String cNameInCompMap = oName.get_cNameInComponentMAP();
			String cNameInHashMap = oName.get_cNameInDataRecordHashMAP();
			
			if (this.oMapOfMask.get__Comp(oName.get_cNameInComponentMAP()) instanceof MyE2IF__DB_Component)
			{
				if (this.oMapOfMask.get__Comp(cNameInCompMap) instanceof MyE2_DB_SelectField)
				{
					((MyE2_DB_SelectField)this.oMapOfMask.get__Comp(cNameInCompMap)).set_ActiveValue(this.oHashMapOfValuesToFill.get_FormatedValue(cNameInHashMap));
				}
				else
				{
					((MyE2IF__DB_Component)this.oMapOfMask.get__Comp(cNameInCompMap)).set_cActualMaskValue(this.oHashMapOfValuesToFill.get_FormatedValue(cNameInHashMap));
					if (this.oMapOfMask.get__Comp(cNameInCompMap) instanceof MyE2_DB_MaskSearchField)
					{
						((MyE2_DB_MaskSearchField)this.oMapOfMask.get__Comp(cNameInCompMap)).FillLabelWithDBQuery(this.oHashMapOfValuesToFill.get_UnFormatedValue(cNameInHashMap));
					}
				}
			}
			
			if (oName.get_bDisableFromInteractive())
			{
				this.oMapOfMask.get__Comp(oName.get_cNameInComponentMAP()).EXT().set_bDisabledFromInteractive(true);
				this.oMapOfMask.get__Comp(oName.get_cNameInComponentMAP()).set_bEnabled_For_Edit(false);
			}
			
		}

		//zuerst die vFreeValues-liste abarbeiten
		for (int i=0;i<vFreeValues.size();i++)
		{
			ValueDirect 	oValueDirect = 			(ValueDirect)vFreeValues.get(i);
			String 			cNameInCompMap = 	oValueDirect.get_cNameInComponentMAP();
			String 			cValue = 			oValueDirect.get_cValueDirect();
			
			if (this.oMapOfMask.get__Comp(oValueDirect.get_cNameInComponentMAP()) instanceof MyE2IF__DB_Component)
			{
				if (this.oMapOfMask.get__Comp(cNameInCompMap) instanceof MyE2_DB_SelectField)
				{
					((MyE2_DB_SelectField)this.oMapOfMask.get__Comp(cNameInCompMap)).set_ActiveValue(cValue);
				}
				else
				{
					((MyE2IF__DB_Component)this.oMapOfMask.get__Comp(cNameInCompMap)).set_cActualMaskValue(cValue);
					if (this.oMapOfMask.get__Comp(cNameInCompMap) instanceof MyE2_DB_MaskSearchField)
					{
						((MyE2_DB_MaskSearchField)this.oMapOfMask.get__Comp(cNameInCompMap)).FillLabelWithDBQuery(cValue);
					}
				}
			}
			
			if (oValueDirect.get_bDisableFromInteractive())
			{
				this.oMapOfMask.get__Comp(oValueDirect.get_cNameInComponentMAP()).EXT().set_bDisabledFromInteractive(true);
				this.oMapOfMask.get__Comp(oValueDirect.get_cNameInComponentMAP()).set_bEnabled_For_Edit(false);
			}
			
		}
		
		
		
	}
	
	
	
	/*
	 * hilfsklasse fuer die befuellung einer komponente nach dem laden eines wertes 
	 */
	private class Naming
	{
		private String cNameInComponentMAP = null;
		private String cNameInDataRecordHashMAP = null;
		private boolean DisableFromInteractive = false;
		
		public Naming(String nameInComponentMAP, String nameInDataRecordHashMAP, boolean bDisableFromInteractive) 
		{
			super();
			cNameInComponentMAP = nameInComponentMAP;
			cNameInDataRecordHashMAP = nameInDataRecordHashMAP;
			this.DisableFromInteractive = bDisableFromInteractive;
		}

		public String get_cNameInComponentMAP() 
		{
			return cNameInComponentMAP;
		}
		public String get_cNameInDataRecordHashMAP() 
		{
			return cNameInDataRecordHashMAP;
		}
		public boolean get_bDisableFromInteractive() 
		{
			return DisableFromInteractive;
		}
	}
	

	
	/*
	 * hilfsklasse fuer die befuellung einer komponente nach dem laden eines wertes 
	 */
	private class ValueDirect
	{
		private String 	cNameInComponentMAP = null;
		private String 	cValueDirect = null;
		private boolean DisableFromInteractive = false;
		
		public ValueDirect(String nameInComponentMAP, String ValueDirect, boolean bDisableFromInteractive) 
		{
			super();
			cNameInComponentMAP = 			nameInComponentMAP;
			cValueDirect = 					ValueDirect;
			this.DisableFromInteractive = 	bDisableFromInteractive;
		}

		public String get_cNameInComponentMAP() 
		{
			return cNameInComponentMAP;
		}
		public String get_cValueDirect() 
		{
			return cValueDirect;
		}
		public boolean get_bDisableFromInteractive() 
		{
			return DisableFromInteractive;
		}
	}
	

	
	
}