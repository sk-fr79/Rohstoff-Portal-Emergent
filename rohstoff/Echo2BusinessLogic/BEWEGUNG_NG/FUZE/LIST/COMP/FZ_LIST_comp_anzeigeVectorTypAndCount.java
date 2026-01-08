package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.E2_GridLabel;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_onlyWhenVisisble;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.RECORD_BEWEGUNG_VEKTOR_SPEC;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_STATUS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;

public class FZ_LIST_comp_anzeigeVectorTypAndCount extends	MyE2_DB_PlaceHolder_onlyWhenVisisble implements MyE2IF_IsMarkable {

	private FZ_LIST_btShowallVectorsInBewegung label = null;
	
	private static int[] breite = {80,15,15,15};
	
	private E2_NavigationList   naviList = null;
	
	public FZ_LIST_comp_anzeigeVectorTypAndCount(SQLField osqlField, E2_NavigationList p_naviList) throws myException {
		super(osqlField);
		this.naviList = p_naviList;
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	}


	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		
		this.removeAll();
		
		RECORD_BEWEGUNG_VEKTOR_SPEC recVect = (RECORD_BEWEGUNG_VEKTOR_SPEC)this.EXT().get_oComponentMAP().get_Record4MainTable();
		
		ENUM_VEKTOR_TYP typ = ENUM_VEKTOR_TYP.find_typ(S.NN(value_from_db));
		if (typ==null) {
			this.label=new FZ_LIST_btShowallVectorsInBewegung("<-->", recVect,this.naviList);
		} else {
			this.label=new FZ_LIST_btShowallVectorsInBewegung(new MyE2_String(typ.user_text()).CTrans(),recVect,this.naviList);
		}
		this.set_Spalten(FZ_LIST_comp_anzeigeVectorTypAndCount.breite);
		
		GridLayoutData glRech = MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(1,1,1,1));
		glRech.setBackground(Color.GREEN);
		GridLayoutData glGut = MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(1,1,1,1));
		glGut.setBackground(Color.YELLOW);
		
		GridLayoutData glUndef = MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(1,1,1,1));
		glUndef.setBackground(Color.RED);
		
		this.add(this.label,FZ__CONST.gl);
		if (recVect.is_Storniert_or_Deleted()) {
			this.add(new showLabel("*",new E2_ColorAlarm(),null, new MyE2_String("Storniert oder gelöscht").CTrans()));
			this.add(new showLabel("*",new E2_ColorAlarm(),null, new MyE2_String("Storniert oder gelöscht").CTrans()));
		} else {
			this.add(new showLabel(""+recVect.countGutschriftPos(),recVect.countGutschriftPos()>0?new E2_ColorHelpBackground():new E2_ColorBase(),null, new MyE2_String("Anzahl Gutschriftspositionen").CTrans()));
			this.add(new showLabel(""+recVect.countRechPos(),recVect.countRechPos()>0?new E2_ColorMaskHighlight():new E2_ColorBase(), null, new MyE2_String("Anzahl Rechnungspositionen").CTrans()));
		}
		
		//jetzt den status anzeigen
		MyE2_Grid status = new showLabel(" ", new E2_ColorBase(), null, new MyE2_String("").CTrans());
		if 			(recVect.get_STATUS_cF_NN("").equals(ENUM_VEKTOR_STATUS.STORNIERT.db_val())) {
			status = new showLabel("S", new E2_ColorGray(200),new E2_FontBoldItalic(), new MyE2_String("Status: storniert").CTrans());
		} else if  	(recVect.get_STATUS_cF_NN("").equals(ENUM_VEKTOR_STATUS.GEPLANT.db_val())) {
			status = new showLabel("P", new E2_ColorHelpBackground(), new E2_FontBoldItalic(), new MyE2_String("Status: geplant").CTrans());
		} else if 	(recVect.get_STATUS_cF_NN("").equals(ENUM_VEKTOR_STATUS.AKTIV.db_val())) {
			status = new showLabel("A", new E2_ColorMaskHighlight(),new E2_FontBoldItalic(), new MyE2_String("Status: aktiv").CTrans());
		}
		//deleted wird separat behandelt
		if 	(recVect.is_DELETED_YES()) {
			status = new showLabel("*", new E2_ColorAlarm(), new E2_FontPlain(), new MyE2_String("Status: gelöscht").CTrans());
		}
		this.add(status);

	}

//	private MyE2_Grid getGridWithLabel(MyE2_Label label, Color colBackground) {
//		MyE2_Grid grid = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_BLACK_BORDER_NO_INSETS_W100());
//		grid.add(label,MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(0,0,0,0),colBackground,1,1));
//		return grid;
//	}
	
	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy 	{
		try {
			return new FZ_LIST_comp_anzeigeVectorTypAndCount(this.EXT_DB().get_oSQLField(), this.naviList);
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	@Override
	public void make_Look_Deleted(boolean bIsDeleted) {
		this.label.make_Look_Deleted(bIsDeleted);
	}

	@Override
	public void setForeColorActive(Color ForeColor) {
		this.label.setForeColorActive(ForeColor);
	}

	@Override
	public void setFontActive(Font font) {
		this.label.setFontActive(font);
	}

	@Override
	public Color get_Unmarked_ForeColor() {
		return this.label.get_Unmarked_ForeColor();
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.label.get_Unmarked_Font();
	}
	
	
	
	private class showLabel extends E2_GridLabel {

		public showLabel(String text, Color  color, Font font, String toolTips) {
			super();
			this.set_passiv()
				.set_width(15)
				.set_font(font)
				.set_alignment(new Alignment(Alignment.CENTER, Alignment.CENTER))
				.set_text(text).set_color_background(color)
				.set_border(new Border(1, Color.BLACK, Border.STYLE_SOLID))
				.set_tooltips(toolTips)
				.set_aktiv();
		}
		
	}
	 
	
	
	
}
