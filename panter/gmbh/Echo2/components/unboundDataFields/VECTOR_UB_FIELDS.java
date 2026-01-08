package panter.gmbh.Echo2.components.unboundDataFields;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;




public class VECTOR_UB_FIELDS extends Vector<IF_UB_Fields>
{
	
	private String cNameListFalseFields = "";
	
	
	public IF_UB_Fields get_(int i)
	{
		return (IF_UB_Fields)this.get(i);
	}
	
	
	public boolean get_bFieldsHasChanged() throws myException	{
		boolean bRueck = false;
		
		for (int i=0;i<this.size();i++)
		{
			bRueck = bRueck || this.get_(i).get_bFieldHasChanged();
		}
		return bRueck;
	}


	
	public MyE2_MessageVector get_MV_AllFieldsAreOK_ShowErrorInput() throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		this.cNameListFalseFields = "";   // reset
		
		for (int i=0;i<this.size();i++)
		{
			MyE2_MessageVector oMVField = this.get_(i).get_MV_InputOK();
			oMV.add_MESSAGE(oMVField);
			
//			//debug
//			System.out.println("----- "+this.get_(i).get_cDBFieldName());
//			//debug
			
			this.get_(i).mark_ErrorInput(oMVField.get_bIsOK());
			if (oMVField.get_bHasAlarms())
			{
				this.cNameListFalseFields += " <"+this.get_(i).get_cDBFieldName()+"> ";
			}
		}
		return oMV;
	}

	
	public String get_cInsertFieldsBlock() throws myException
	{
		String cRueck = "";
		
		for (int i=0;i<this.size();i++)
		{
			cRueck += this.get_(i).get_cDBFieldName()+",";
		}

		// letztes , weg
		if (cRueck.endsWith(","))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
	}
	
	
	public String get_cInsertValuesBlock() throws myException
	{
		String cRueck = "";
		
		for (int i=0;i<this.size();i++)
		{
			cRueck += this.get_(i).get_cInsertValuePart()+",";
		}

		// letztes , weg
		if (cRueck.endsWith(","))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
	}
	

	public String get_cUpdateBlock(boolean bOnlyChangedFields) throws myException
	{
		String cRueck = "";
		
		for (int i=0;i<this.size();i++)
		{
			if (bOnlyChangedFields)
			{
				if (this.get_(i).get_bFieldHasChanged())
					cRueck += this.get_(i).get_cUpdatePart()+",";
			}
			else
			{
				cRueck += this.get_(i).get_cUpdatePart()+",";
			}
		}

		// letztes , weg
		if (cRueck.endsWith(","))
			cRueck = cRueck.substring(0,cRueck.length()-1);
		
		return cRueck;
	}
	
	
	// prueft textfelder, ob diese gefuellt sind, checkboxen liefern immer einen wert
	public boolean get_bAllTextFieldsAreFilled() throws myException
	{
		boolean bRueck = true;
		
		for (int i=0;i<this.size();i++)
		{
			if (!(this.get_(i) instanceof UB_CheckBox))
			{
				if (this.get_(i).get_cInsertValuePart().toUpperCase().trim().equals("NULL"))
				{
					bRueck = false;
					break;
				}
			}
		}

		return bRueck;
	}


	public String getCNameListFalseFields()
	{
		return cNameListFalseFields;
	}

	public void set_StyleForInput(boolean bEnabled)
	{
		for (IF_UB_Fields oField: this)
		{
			oField.set_StyleForInput(bEnabled);
		}
	}
	
}
