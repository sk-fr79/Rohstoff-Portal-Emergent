/**
 * panter.gmbh.Echo2.RB.COMP.SearchDialog
 * @author manfred
 * @date 10.08.2017
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchDialog;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.dataTools.IF_Field;

/**
 * @author manfred
 * @date 10.08.2017
 *
 */
public class SearchDialog_ResultColumn {
	
	public static final E2_MutableStyle result_column_style_normal 			= MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch();
	public static final E2_MutableStyle result_column_style_bold_normal 	= MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontBold());
	public static final E2_MutableStyle result_column_style_kursive 		= MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontItalic());
	public static final E2_MutableStyle result_column_style_bold_kursive 	= MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Umbruch(new E2_FontBoldItalic());
	
	
	IF_Field			_Field;
	String				_FieldName;
	private boolean				_isPK;
	MyE2_String  		_Heading;
	
	String  			_ColName;
	int 				_width;
	
	E2_MutableStyle 	_StyleHeader;
	E2_MutableStyle		_StyleData;
	
	MyE2_String 		_ToolTip;
	Alignment           _AlignmentHeader = new Alignment(Alignment.LEFT,Alignment.TOP);
	Alignment			_AlignmentData = new Alignment(Alignment.LEFT,Alignment.TOP);
	Color				_bgColHeader = new E2_ColorBase(-20);
	Color 				_bgColColumn = new E2_ColorBase();
	
	
	
	
	
	/**
	 * @author manfred
	 * @date 10.08.2017
	 *
	 */
	public SearchDialog_ResultColumn() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * eine neue Result Column. 
	 * Default Alignment: LEFT/TOP
	 * Default Style Heading: bold
	 * Default Style Data: normal
	 * 
	 * @author manfred
	 * @date 14.08.2017
	 *
	 * @param _field
	 * @param _heading
	 * @param _colName
	 * @param _width
	 * @param _style
	 * @param _toolTip
	 */
	public SearchDialog_ResultColumn(
			IF_Field _field,
			String _heading, 
			String _colName, 
			int _width 
			
			) {
		
		super();
		this._Field 	= 	_field;
		if (_Field != null){
			_isPK = _Field.is_PK();
		}
		
		this._Heading 	= 	_heading != null ? new MyE2_String(_heading) : new MyE2_String("<?>");
		this._ColName 	= 	_colName;
		this._width 	= 	_width;
		
		this.set_Alignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		this.set_StyleHeader(result_column_style_bold_normal);
		this.set_StyleData(result_column_style_normal);
		
	}

	
	
	/**
	 * eine neue Result Column. 
	 * Default Alignment: LEFT/TOP
	 * Default Style Heading: bold
	 * Default Style Data: normal
	 * 
	 * @author manfred
	 * @date 20.09.2017
	 *
	 * @param fieldname
	 * @param isPK
	 * @param _heading
	 * @param _colName
	 * @param _width
	 */
	public SearchDialog_ResultColumn(
			String _heading, 
			String _colName, 
			boolean isPK,
			int _width 
			) {
		
		super();
		this._Field 	= 	null;
		this._isPK 		= 	isPK;
		
		this._Heading 	= 	_heading != null ? new MyE2_String(_heading) : new MyE2_String("<?>");
		this._ColName 	= 	_colName;
		this._width 	= 	_width;
		
		this.set_Alignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		this.set_StyleHeader(result_column_style_bold_normal);
		this.set_StyleData(result_column_style_normal);

		
	}

	
	
	
	// getter / setter

	public MyE2_String get_Heading() {
		return _Heading;
	}


	public SearchDialog_ResultColumn set_Heading(MyE2_String _Heading) {
		this._Heading = _Heading;
		return this;
	}


	public String get_ColName() {
		return _ColName;
	}


	public SearchDialog_ResultColumn set_ColName(String _ColName) {
		this._ColName = _ColName;
		return this;
	}


	public int get_width() {
		return _width;
	}


	public SearchDialog_ResultColumn set_width(int _width) {
		this._width = _width;
		return this;
	}


	public MyE2_String get_ToolTip() {
		return _ToolTip;
	}


	public SearchDialog_ResultColumn set_ToolTip(MyE2_String _ToolTip) {
		this._ToolTip = _ToolTip;
		return this;
	}

	/**
	 * Setzt Alignment für Header und Data
	 * @author manfred
	 * @date 20.09.2017
	 *
	 * @param _align
	 * @return
	 */
	public SearchDialog_ResultColumn set_Alignment(Alignment _align){
		this._AlignmentHeader = _align;
		this._AlignmentData = _align;
		return this;
	}
	
	
	public Alignment get_AlignmentHeader() {
		return _AlignmentHeader;
	}


	public SearchDialog_ResultColumn set_AlignmentHeader(Alignment _AlignmentHeader) {
		this._AlignmentHeader = _AlignmentHeader;
		return this;
	}


	public Alignment get_AlignmentData() {
		return _AlignmentData;
	}


	public SearchDialog_ResultColumn set_AlignmentData(Alignment _AlignmentData) {
		this._AlignmentData = _AlignmentData;
		return this;
	}


	public Color get_bgColHeader() {
		return _bgColHeader;
	}


	public SearchDialog_ResultColumn set_bgColHeader(Color _bgColHeader) {
		this._bgColHeader = _bgColHeader;
		return this;
	}


	public Color get_bgColColumn() {
		return _bgColColumn;
	}


	public SearchDialog_ResultColumn set_bgColColumn(Color _bgColColumn) {
		this._bgColColumn = _bgColColumn;
		return this;
	}


	public E2_MutableStyle get_StyleHeader() {
		return _StyleHeader;
	}


	public SearchDialog_ResultColumn set_StyleHeader(E2_MutableStyle _StyleHeader) {
		this._StyleHeader = _StyleHeader;
		return this;
	}


	public E2_MutableStyle get_StyleData() {
		return _StyleData;
	}


	public SearchDialog_ResultColumn set_StyleData(E2_MutableStyle _StyleData) {
		this._StyleData = _StyleData;
		return this;
	}


	public boolean isPK() {
		return _isPK;
	}


	public SearchDialog_ResultColumn setPK(boolean _isPK) {
		this._isPK = _isPK;
		return this;
	}





	
}
