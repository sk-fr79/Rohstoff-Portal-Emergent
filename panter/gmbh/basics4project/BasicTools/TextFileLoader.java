package panter.gmbh.basics4project.BasicTools;

import java.io.IOException;
import java.io.InputStream;

import panter.gmbh.indep.exceptions.myException;


public class TextFileLoader  {
	
	private String path = null;;
	private String name = null;
	
	String cTextInFile = null;
	
	
	public TextFileLoader(String path, String name) 	throws myException {
		super();
		this.path = path;
		this.name = name;
		this.cTextInFile = loadTextResource(this.name);
	}

	
	@SuppressWarnings("rawtypes")
	public TextFileLoader(Class classe, String name) 	throws myException {
		super();
		this.path = classe.getPackage().getName(); //  .getProtectionDomain().getCodeSource().  .getLocation().getPath();
		this.name = name;
		this.cTextInFile = loadTextResource(this.name);
	}
	
	
	
	private String loadTextResource(String fname)	throws myException
	{
		String ret = null;
		InputStream is = getResourceStream(this.path, fname);
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
				throw new myException(this," opening resourcefile: "+this.path+fname+"");
			}
		}
		catch (IOException ex)
		{
			throw new myException(this,"loadTextResource:IOException with resourcefile: " + this.path + fname + ":" + ex.getLocalizedMessage());
		}
		return ret;
	}


	public String get_loadedText() {
		return this.cTextInFile;
	}
	
	
	private InputStream getResourceStream(String pkgname, String fname) {
		String resname = "/" + pkgname.replace('.', '/') + "/" + fname;
		@SuppressWarnings("rawtypes")
		Class clazz = getClass();
		InputStream is = clazz.getResourceAsStream(resname);
		return is;
	}

	
	
}
