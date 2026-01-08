package panter.gmbh.Echo2;

import java.io.File;

import panter.gmbh.indep.LoadImage;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class LoadLogo extends LoadImage 
{
	public LoadLogo() throws myException
	{
		super(File.separator+bibALL.get_WEBROOTPATH()+File.separator+"bilder"+File.separator+bibALL.get_RECORD_MANDANT().get_LOGONAME_cUF());
	}

	public static String get_CompleteLogoPath() throws myException
	{
		return File.separator+bibALL.get_WEBROOTPATH()+File.separator+"bilder"+File.separator+bibALL.get_RECORD_MANDANT().get_LOGONAME_cUF();
	}
	
}
