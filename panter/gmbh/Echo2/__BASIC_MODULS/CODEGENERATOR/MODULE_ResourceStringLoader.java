package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import java.io.IOException;
import java.io.InputStream;

import panter.gmbh.indep.exceptions.myException;


public class MODULE_ResourceStringLoader
{
	String cTextInFile = null;

	public MODULE_ResourceStringLoader(String DefFileNAME) 	throws myException
	{
		super();
		this.cTextInFile = loadTextResource(DefFileNAME);
	}
	
	
	private String loadTextResource(String fname)	throws myException
	{
		String ret = null;
		InputStream is = getResourceStream("panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.TEMPLATES.MODULES", fname);
		try
		{
			if (is != null)
			{
				StringBuffer sb = new StringBuffer();
				while (true)
				{
					int c = is.read();
					if (c == -1)
					{
						break;
					}
					sb.append((char) c);
				}
				is.close();
				ret = sb.toString();
			}
			else
			{
				throw new myException("E2_ResourceStringLoader:loadTextResource:Error opening resourcefile: "+"/panter/gmbh/Echo2/__BASIC_MODULS/CODEGENERATOR/TEMPLATES/RECORDS/"+fname+"");
			}
		}
		catch (IOException ex)
		{
			throw new myException("E2_ResourceStringLoader:loadTextResource:IOException with resourcefile: "+"/panter/gmbh/Echo2/__BASIC_MODULS/CODEGENERATOR/TEMPLATES/RECORDS/"+fname+":"+ex.getLocalizedMessage());
		}
		return ret;
	}

	private InputStream getResourceStream(String pkgname, String fname)
	{
		String resname = "/" + pkgname.replace('.', '/') + "/" + fname;
		Class clazz = getClass();
		InputStream is = clazz.getResourceAsStream(resname);
		return is;
	}

	public String get_cText()
	{
		return this.cTextInFile;
	}
	
}
