package rohstoff.Echo2BusinessLogic._TAX.RULES;

import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


/**
 * eine pseudocheckbox, die mehrfache eingaben eines sachverhaltes erlaubt und die immer
 * angekreutzt ist, wenn die darunterliegende Zahl > 0 ist
 * @author martin
 *
 */
public class TR__MASK_CompDublette extends MyE2_CheckBox  implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy{
	
	
	private MyE2EXT__DB_Component 		oEXTDB=new MyE2EXT__DB_Component(this);
	private long   					iActualValue = 0l;				
	
	public TR__MASK_CompDublette(SQLField osqlField) throws myException
	{
		super(new MyE2_String("Mehrfache Basiswerte erlauben"),new MyE2_String("Zulassen, dass mehrfache Steuerdefinitionen mit gleicher Vergleichsbasis, aber unterschiedlichem Ergebnis erfasst werden können"));
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		this.add_oActionAgent(new ownActionAgentSucheNaechste());
		
	}

	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		this.iActualValue = 0;
		if (!bSetDefault)
		{
			this.setSelected(false);
			return;
		}
		
		this.EXT_DB().set_cLASTActualDBValueFormated(""+this.iActualValue);
		this.EXT_DB().set_cLASTActualMaskValue(""+this.iActualValue);
		
	}

	

	public String get_cActualMaskValue() throws myException
	{
		return ""+this.iActualValue;
	}

	
	public String get_cActualDBValueFormated() throws myException
	{
		return this.get_cActualMaskValue();
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		MyLong oValue = new MyLong(bibALL.ReplaceTeilString(cText,".",""));
		
		if (!oValue.get_bOK()) {
			throw new myException("TR__MASK_CompDublette:set_cActualMaskValue:Not allowed value:"+cText);
		}

		this.iActualValue = oValue.get_lValue();
		this.setSelected(this.iActualValue>0);  

	}

	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("TR__MASK_CompDublette:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		MyLong oValue = new MyLong(bibALL.ReplaceTeilString(cText,".",""));
		
		if (!oValue.get_bOK()) {
			throw new myException("TR__MASK_CompDublette:set_cActualMaskValue:Not allowed value:"+cText);
		}

		this.iActualValue = oValue.get_lValue();
		
		this.setSelected(this.iActualValue>0);  
		
		this.EXT_DB().set_cLASTActualDBValueFormated(""+this.iActualValue);
	}

	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	public boolean get_bIsComplexObject()
	{
		return false;
	}

	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP) throws myException
	{
		return null;
	}

	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}

	public void set_EXT_DB(MyE2EXT__DB_Component oeXT_DB)
	{
		this.oEXTDB = oeXT_DB;
	}

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		return null;
	}

	
	public void set_DescriptionAsCheckboxText(boolean bTranslated)
	{
		this.setText(new MyE2_String(this.EXT_DB().get_oSQLField().get_cFieldLabelForUser().COrig(),bTranslated).CTrans());
	}
	
	
	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		this.setEnabled(bVoraussetzung);
		
		this.set_Icons(this.isEnabled());
	}


	private class ownActionAgentSucheNaechste extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			TR__MASK_CompDublette oThis = TR__MASK_CompDublette.this;
			
			if (!oThis.isSelected()) {
				oThis.iActualValue = 0;
			} else {
				String cQUERY = "SELECT NVL(MAX("+_DB.HANDELSDEF$VERSIONSZAEHLER+"),0)+1 FROM "+_DB.HANDELSDEF;
				
				String cWert = bibALL.ReplaceTeilString(bibDB.EinzelAbfrage(cQUERY),".","");
				
				MyLong lWert = new MyLong(cWert);
			
				oThis.iActualValue = lWert.get_lValue();
			}
		}
	}
	
}
