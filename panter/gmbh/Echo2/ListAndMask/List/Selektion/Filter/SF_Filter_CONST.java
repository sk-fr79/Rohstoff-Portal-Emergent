package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;
 
public class SF_Filter_CONST {
	
	public static int BREITE_ODER_BESCHRIFTUNG = 	40;
	public static int BREITE_FIELDSELEKTOR = 		300;
	public static int BREITE_COMPARESELEKTOR = 		100;
	public static int BREITE_INPUTBLOCK = 			300;
	public static int BREITE_DEL_BUTTON = 			20;
	public static int BREITE_OK_BUTTON = 			20;

	public static int[] get_i_breiten() {
		int[] i_breiten = new int[6];
		i_breiten[0]=SF_Filter_CONST.BREITE_ODER_BESCHRIFTUNG+5;
		i_breiten[1]=SF_Filter_CONST.BREITE_FIELDSELEKTOR+5;
		i_breiten[2]=SF_Filter_CONST.BREITE_COMPARESELEKTOR+5;
		i_breiten[3]=SF_Filter_CONST.BREITE_INPUTBLOCK+5;
		i_breiten[4]=SF_Filter_CONST.BREITE_DEL_BUTTON+5;
		i_breiten[5]=SF_Filter_CONST.BREITE_OK_BUTTON+5;
	
		return i_breiten;
	}
	
	
	public static void transfer_one_into_two(Vector<SF_andBlock> one, Vector<SF_andBlock> two) throws myException{
		two.clear();
		for (SF_andBlock and_b: one) {
			two.add(and_b.get_and_block_copy());
		}
	}
	
}
