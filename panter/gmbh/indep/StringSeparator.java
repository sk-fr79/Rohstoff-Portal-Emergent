package panter.gmbh.indep;

import java.util.StringTokenizer;
import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;



public class StringSeparator extends Vector<String>
{

	public StringSeparator(String cToSeparate, String Separator) throws myException
	{
		super();
		if (bibALL.isEmpty(cToSeparate) || Separator == null || Separator.equals(""))
			throw new myException("StringSeparator:Construtor:Empty Strings not allowed !");
		
		StringTokenizer oToken = new StringTokenizer(cToSeparate,Separator);
		
		while (oToken.hasMoreTokens())
			this.add(oToken.nextToken());
		
	}
	
	
	public String get_(int i)
	{
		return (String)this.get(i);
	}
	
}
