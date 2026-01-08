package rohstoff.Echo2BusinessLogic.FIBU_KONTEN;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_AND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class REC_FILTER_AND_ext extends RECORD_FILTER_AND
{

	public REC_FILTER_AND_ext() throws myException {
		super();
	}

	public REC_FILTER_AND_ext(long lID_Unformated, MyConnection Conn) throws myException {
		super(lID_Unformated, Conn);
	}

	public REC_FILTER_AND_ext(long lID_Unformated) throws myException {
		super(lID_Unformated); 
	}

	public REC_FILTER_AND_ext(MyConnection Conn) throws myException 	{
		super(Conn);
	}

	public REC_FILTER_AND_ext(MyRECORD recordOrig) {
		super(recordOrig);
	}

	public REC_FILTER_AND_ext(RECORD_FILTER_OR recordOrig) 	{
		super(recordOrig);
	}

	public REC_FILTER_AND_ext(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException 	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public REC_FILTER_AND_ext(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException 	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	
	/*
	 * spezielle methode um eine Infoliste der or-bedingungen innerhalb einer and-Regel in der liste zu zeigen
	 */
	public MyE2_Grid get_Info_Grid() throws myException {
		
		int[] iWitdht= {50,50,300,100,100,300};
		
		MyE2_Grid  gridRET = new MyE2_Grid(iWitdht, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		GridLayoutData  glCentered = 	MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,1,2,1));
		GridLayoutData  glLeft = 		MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,1,2,1));
		
		RECLIST_FILTER_OR  rlOR = this.get_DOWN_RECORD_LIST_FILTER_OR_id_filter_and(null, _DB.FILTER_OR$ID_FILTER_OR,true);

		int i = 0;
		for (RECORD_FILTER_OR rO: rlOR.values()) {
			i++;
			//spalte0
/*			MyE2_Label labPosNeg = (rO.is_CONDITION_POSITIVE_NO()?new MyE2_Label("NICHT",new E2_FontItalic(-2)):new MyE2_Label(""));
			gridRET.add(labPosNeg, glCentered);
*/		
			MyE2_Label labOr = new MyE2_Label((i > 1 ? "ODER" : ""),new E2_FontItalic(-2));
			gridRET.add(labOr, glCentered);

			//spalte1
			gridRET.add(new MyE2_Label("("+rO.get_CONDITION_LEFT_TYPE_cUF_NN("")+")",new E2_FontPlain(-4)), glCentered);

			//spalte2
			gridRET.add(new MyE2_Label(rO.get_CONDITION_LEFT_cF_NN(""),new E2_FontPlain(-2)), glLeft);

			
			//spalte3
			String cCompReadable = S.NN(FK_CONST.get_hmCompareValues().get(rO.get_CONDITION_OP_cUF_NN("")));
			if (rO.is_CONDITION_POSITIVE_NO()) {
				cCompReadable = "NICHT "+cCompReadable;
			}
			gridRET.add(new MyE2_Label(cCompReadable, new E2_FontPlain(-2)), glCentered);

			//spalte4
			gridRET.add(new MyE2_Label("("+rO.get_CONDITION_RIGHT_TYPE_cUF_NN("")+")",new E2_FontPlain(-4)), glCentered);

			//spalte5
			gridRET.add(new MyE2_Label(rO.get_CONDITION_RIGHT_cF_NN(""),new E2_FontPlain(-2)), glLeft);
		}
		
		return gridRET;
	}
	
	
	
}
