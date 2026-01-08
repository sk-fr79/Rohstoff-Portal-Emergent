package panter.gmbh.basics4project.specialViews;

import java.io.IOException;
import java.io.InputStream;

import panter.gmbh.indep.exceptions.myException;


public class VIEW_ResourceStringLoader
{
	String cTextInFile = null;
	private static final String m_Path = "panter.gmbh.basics4project.specialViews.TextualViewDefinitions";

	public VIEW_ResourceStringLoader(String DefFileNAME) 	throws myException
	{
		super();
		this.cTextInFile = loadTextResource(DefFileNAME);
	}
	
	
	private String loadTextResource(String fname)	throws myException
	{
		String ret = null;
		InputStream is = getResourceStream(m_Path, fname);
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
				throw new myException("E2_ResourceStringLoader:loadTextResource:Error opening resourcefile: "+m_Path+fname+"");
			}
		}
		catch (IOException ex)
		{
			throw new myException("E2_ResourceStringLoader:loadTextResource:IOException with resourcefile: " + m_Path + fname + ":" + ex.getLocalizedMessage());
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
