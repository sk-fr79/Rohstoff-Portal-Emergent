package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class B_LIST_ComponentMAP extends E2_ComponentMAP {

	private RECORD_BEWEGUNG  		recBewegung = null;
	private B_LIST_SQL_FieldMAP  	oSQLFM = null;
	
	public B_LIST_ComponentMAP(B_LIST_SQL_FieldMAP o_SQLFM) throws myException {
		super(o_SQLFM);
		
		this.oSQLFM = o_SQLFM;
		
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(new MyE2_DB_Label_INROW(		this.oSQLFM.get_(_DB.BEWEGUNG$ID_BEWEGUNG), 70), 
														new MyE2_String("ID"), true, true);
		
		this.add_Component(new MyE2_DB_Label_INROW(this.oSQLFM.get_(B__CONST.COLKEY_AUSGANGSORT), 70), 
														new MyE2_String("Ausgang der Warenbewegung"), true, true);
		
		this.add_Component(new MyE2_DB_Label_INROW(this.oSQLFM.get_(B__CONST.COLKEY_SORTE), 70), 
														new MyE2_String("Transportgegenstand/-Sorte"), true, true);

		this.add_Component(new MyE2_DB_Label_INROW(this.oSQLFM.get_(B__CONST.COLKEY_ZIELORT), 70), 
														new MyE2_String("Ziel der Warenbewegung"), true, true);
		
//		this.add_Component(B__CONST.COLKEY_AUSGANGSORT, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()), new MyE2_String("Ausgang der Warenbewegung"));
//		this.add_Component(B__CONST.COLKEY_SORTE, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()), new MyE2_String("Was wird transportiert"));
//		this.add_Component(B__CONST.COLKEY_ZIELORT, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()), new MyE2_String("Ziel der Warenbewegung"));
//		
//		
		
		
		
		//die breite der listenspalten festlegen
		this.get__Comp(_DB.BEWEGUNG$ID_BEWEGUNG).EXT().set_oColExtent(new Extent(80));
		this.get__Comp(B__CONST.COLKEY_AUSGANGSORT).EXT().set_oColExtent(new Extent(300));
		this.get__Comp(B__CONST.COLKEY_SORTE).EXT().set_oColExtent(new Extent(300));
		this.get__Comp(B__CONST.COLKEY_ZIELORT).EXT().set_oColExtent(new Extent(300));
		
		//this.add_oSubQueryAgent(new B_LIST_ComponentMAP_QueryAgent_Ausfaltung());
		
		
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try {
			return new B_LIST_ComponentMAP(this.oSQLFM);
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getOriginalMessage());
		}
	}
	
	
	/**
	 * 
	 * @return recBEWEGUNG from this map
	 * @throws myException
	 */
	public RECORD_BEWEGUNG  get_recBewegung() throws myException {
		if (this.recBewegung==null) {
			this.recBewegung = new RECORD_BEWEGUNG(this.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		}
		return this.recBewegung;
	}
	
	
}
