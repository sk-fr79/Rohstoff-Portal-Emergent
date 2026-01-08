package panter.gmbh.Echo2;

import java.io.IOException;
import java.io.InputStream;

import panter.gmbh.indep.exceptions.myException;


public class E2_ResourceStringLoader
{
	String cTextInFile = null;

	public E2_ResourceStringLoader(String cXMLResourceFileName) 	throws myException
	{
		super();
		this.cTextInFile = loadTextResource(cXMLResourceFileName);
	}
	
	
	private String loadTextResource(String fname)	throws myException
	{
		String ret = null;
		InputStream is = getResourceStream("/resources/ListDefXML/", fname+".xml");
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
				throw new myException("E2_ResourceStringLoader:loadTextResource:Error opening resourcefile: "+"/resources/ListDefXML/"+fname+".xml");
			}
		}
		catch (IOException ex)
		{
			throw new myException("E2_ResourceStringLoader:loadTextResource:IOException with resourcefile: "+"/resources/ListDefXML/"+fname+".xml :: "+ex.getLocalizedMessage());
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
