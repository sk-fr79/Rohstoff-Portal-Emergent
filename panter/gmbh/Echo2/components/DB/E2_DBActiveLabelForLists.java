package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_ChangeValue;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * Allgemeiner Button fuer Listen, ersetzt die MyE2_DB_labelInRow - komponenten
 * @author martin
 *
 */
public class E2_DBActiveLabelForLists extends E2_Button implements MyE2IF__DB_Component,  MyE2IF_IsMarkable {

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	
	private Font  					unmarkedFont = new E2_FontPlain();
	private Color  					unmarkedColor = new Color(0,0 ,0);   //SCHWARZ
	
	private IF_ChangeValue<String, String>    changerStringString = null;

	private String unChangedValue = null;
	
	
	public E2_DBActiveLabelForLists(SQLField osqlField) throws myException {
		super();
		
		if (osqlField == null) {
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");
		}

		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
		
		this._aaa(new ownActionAgent());
	}


	public E2_DBActiveLabelForLists(SQLField osqlField, MutableStyle oStyle) throws myException {
		this(osqlField);
		this.setStyle(oStyle);
	}


//	//konstuctor fuer die copy-methode
//	protected E2_DBActiveLabelForLists() {
//		super();
//	}

	/*
	 * diese funktion wird hier auser gefecht gesetzt 
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		if (this.EXT().get_oComponentMAP() != null) {
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null)
				this.setEnabled(false);
		}
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault)	{
		this.setText("");
		this.EXT_DB().set_cLASTActualDBValueFormated("");
		this.EXT_DB().set_cLASTActualMaskValue("");
	}

	@Override
	public String get_cActualMaskValue() throws myException	{
		return null;
	}

	@Override
	public String get_cActualDBValueFormated() throws myException {
		return null;
	}

	@Override
	public void set_cActualMaskValue(String cText) throws myException {
		this.unChangedValue=cText;

		
		
		if (this.changerStringString!=null) {
			Rec21 rec = null;
			if (this.EXT().get_oComponentMAP() instanceof E2_ComponentMAP_V2) {
				rec = ((E2_ComponentMAP_V2)this.EXT().get_oComponentMAP()).getRec21();
			}
			
			String changedVal = this.changerStringString.change(cText,rec);
			this.EXT_DB().set_cLASTActualMaskValue(changedVal);
			this.setText(changedVal);
		} else {
			this.EXT_DB().set_cLASTActualMaskValue(cText);
			this.setText(cText);
		}
	}

	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		this.set_cActualMaskValue(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(null);
	}

	@Override
	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	@Override
	public boolean get_bIsComplexObject()	{
		return false;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP) throws myException {
		return null;
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException	{
		return null;
	}

	@Override
	public MyE2EXT__DB_Component EXT_DB(){
		return this.oEXTDB;
	}

	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)	{
		this.oEXTDB = oEXT_DB;
	}

	
	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		try {
			E2_DBActiveLabelForLists cop= new E2_DBActiveLabelForLists(this.oEXTDB.get_oSQLField());
			cop.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(cop));
			cop.setStyle(this.getStyle());
			cop._setChangerStringString(this.changerStringString);
			cop.setFont(this.getFont());
			cop.setLineWrap(this.isLineWrap());
			cop.setAlignment(this.getAlignment());
			cop.setTextAlignment(this.getTextAlignment());
			
			
			return cop;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	
	@Override
	public void make_Look_Deleted(boolean bIsDeleted)	{
		
		Font oDelFontDeleted = bibE2.get_Font4DeletedLinesInLists();
		Font oDelFontNormal = bibE2.get_Font4NormalLists();
		
		if (!bIsDeleted) {
			this.setFont(oDelFontNormal);
		} else {
			this.setFont(oDelFontDeleted);
		}
	}

	@Override
	public void setForeColorActive(Color color) {
		if (color!=null) {
			this.unmarkedColor=color;
		}
		this.setForeground(this.unmarkedColor);
	}


	@Override
	public void setFontActive(Font font) {
		if (font!=null) {
			this.unmarkedFont=font;
		}
		this.setFont(this.unmarkedFont);
	}


	
	@Override
	public Color get_Unmarked_ForeColor() {
		return this.unmarkedColor;
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.unmarkedFont;
	}


	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException	{
			if (EXT().get_oComponentMAP() != null) {
				EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
			}
		}
	}



	public IF_ChangeValue<String, String> getChangerStringString() {
		return changerStringString;
	}


	public E2_DBActiveLabelForLists _setChangerStringString(IF_ChangeValue<String, String> changerStringString) {
		this.changerStringString = changerStringString;
		return this;
	}


	public String getUnChangedValue() {
		return unChangedValue;
	}

	
	

}
