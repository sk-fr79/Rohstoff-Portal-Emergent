package panter.gmbh.Echo2.Container.xmlDefTools;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import panter.gmbh.indep.exceptions.myException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class XStreamLoaderListDefs
{
	private ListDef_NG oList = null;
	
	
	public XStreamLoaderListDefs(String cXmlNameWithPath) throws myException
	{
		super();
		File oFile = new File(cXmlNameWithPath);
		if (!oFile.exists())
		{
			throw new myException("XStreamLoaderListDef:Constructor:Error: XML-File: "+cXmlNameWithPath+" not found !!!");
		}
		else
		{
			try
			{
				String cFile = FileUtils.readFileToString(oFile,"UTF8");
				XStream oXS = new XStream(new Dom4JDriver());
				oXS.alias("listendefinition",ListDef_NG.class);
				oXS.alias("felddefinition",ListDef_NG.FieldDef.class);
				oXS.alias("spaltendefinition",ListDef_NG.ListColumn.class);
				oXS.alias("suchdefinition",ListDef_NG.SearchDef.class);
				oXS.alias("selektorcheckboxdefinition",ListDef_NG.SelectorDefCheckBox.class);
				oXS.alias("selektorcheckboxanausdefinition",ListDef_NG.SelectorDefCheckBoxOnOff.class);
				oXS.alias("selektordropdowndefinition",ListDef_NG.SelectorDefDropDown.class);
				this.oList = (ListDef_NG) oXS.fromXML(cFile);
			}
			catch (IOException ex)
			{
				throw new myException(cXmlNameWithPath+": XStreamLoaderListDef:Constructor: IOError:"+ ex.getLocalizedMessage(),ex);
			}
			catch (XStream.InitializationException ex)
			{
				throw new myException(cXmlNameWithPath+": XStreamLoaderListDef:Constructor: XStream.InitializationException:"+ ex.getLocalizedMessage(),ex);
			}
			catch (Exception ex)
			{
				throw new myException(cXmlNameWithPath+": XStreamLoaderListDef:Constructor: XStream.InitializationException:"+ ex.getLocalizedMessage(),ex);
			}

		}
		
	}


	
	

	
	
	
	
	public ListDef_NG get_oList()
	{
		return oList;
	}

	
}
