package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_onlyWhenVisisble;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.RECORD_BEWEGUNG_VEKTOR_SPEC;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;

public class FZ_LIST_comp_VisualizeAtomStart extends MyE2_DB_PlaceHolder_onlyWhenVisisble implements MyE2IF_IsMarkable {

	private static int[] spalten = {350,22,22};
	private 			FZ_addon_AdressComponents_generator 	adressCompFactory = 		null;
	
	public FZ_LIST_comp_VisualizeAtomStart(SQLField osqlField) throws myException {
		super(osqlField);
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.adressCompFactory = new FZ_addon_AdressComponents_generator(this);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {

		this.removeAll();

		this.set_Spalten(FZ_LIST_comp_VisualizeAtomStart.spalten);
		
		RECORD_BEWEGUNG_VEKTOR_SPEC recRow = (RECORD_BEWEGUNG_VEKTOR_SPEC)this.EXT().get_oComponentMAP().get_Record4MainTable();
		adressCompFactory.fill(recRow.get_first_startAdress());
		this.add(adressCompFactory.get__label_with_geografic_adress_text(),FZ__CONST.gl);
		this.add(adressCompFactory.get__infoButton(),FZ__CONST.gl);
		this.add(adressCompFactory.get__editButton(),FZ__CONST.gl);
		this.add(adressCompFactory.get__label_with_juristic_adress_addon(),FZ__CONST.gl);
		this.add(new MyE2_Label(""),FZ__CONST.gl);
		this.add(new MyE2_Label(""),FZ__CONST.gl);
	}


	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy 	{
		try {
			return new FZ_LIST_comp_VisualizeAtomStart(this.EXT_DB().get_oSQLField());
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
		this.adressCompFactory.make_Look_Deleted(bIsDeleted);
	}

	@Override
	public void setForeColorActive(Color ForeColor) {
		this.adressCompFactory.setForeColorActive(ForeColor);
	}

	@Override
	public void setFontActive(Font Font) {
		this.adressCompFactory.setFontActive(Font);
	}

	@Override
	public Color get_Unmarked_ForeColor() {
		return this.adressCompFactory.get_Unmarked_ForeColor();
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.adressCompFactory.get_Unmarked_Font();
	}

}
