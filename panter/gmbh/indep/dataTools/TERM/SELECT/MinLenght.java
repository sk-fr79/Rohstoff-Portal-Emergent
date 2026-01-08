package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.indep.dataTools.IF_Field;

public class MinLenght extends FieldTerm {

	
	private StringBuffer 	c_fill_string = new StringBuffer();
	private int 		  	i_len = 0;
	private Character       fill_char = null;
	
	public MinLenght(IF_Field field, int minLenght, Character c_fill_4_empty) {
		super(field);
		
		this.fill_char = c_fill_4_empty;
		this.i_len = minLenght;
		
		for (int i=0;i<minLenght;i++) {
			this.c_fill_string.append(this.fill_char);
		}
		//DEBUG.System_println("Füllstring="+this.c_fill_string.toString());
	}
	
	
	@Override
	public String s()  {
		return "NVL(LPAD("+this.get_field().fn()+","+this.i_len+",'"+this.fill_char+"'),'"+this.c_fill_string.toString()+"')";
	}

	public String t()  {
		return "NVL(LPAD("+this.get_field().fn()+","+this.i_len+",'"+this.fill_char+"'),'"+this.c_fill_string.toString()+"')";
	}
	
}
