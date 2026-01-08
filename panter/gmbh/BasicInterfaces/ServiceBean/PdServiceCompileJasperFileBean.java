/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBean;

import java.io.File;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceCompileJasperFile;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class PdServiceCompileJasperFileBean implements PdServiceCompileJasperFile {

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceCompileJasperFile#isCompiledFileOutdated(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isCompileNeeded(String pathToJasper, String jasperFile) throws myException {
		boolean bCompileNeeded = false;
		
		
		if (!jasperFile.trim().toLowerCase().endsWith(".jrxml")) {
			throw new myException("Filename MUST end with .jxml");
		}
		if (! new File(pathToJasper+jasperFile).exists()) {
			throw new myException("File "+pathToJasper+jasperFile+" does not exist !");
		}
		
		String jasperBaseName = jasperFile.substring(0,jasperFile.toLowerCase().indexOf(".jrxml"));
		
		
		File fJasper = new File(pathToJasper+jasperBaseName+".jasper");
		File fJrxml = new File(pathToJasper+jasperFile);
		
		if (fJasper.exists() && (!fJrxml.exists()))		{
			bCompileNeeded = false;
		}
		
		if (fJasper.exists() && fJrxml.exists()) {
			bCompileNeeded = fJrxml.lastModified()>fJasper.lastModified();
		}
		
		if ((!fJasper.exists()) && fJrxml.exists())	{
			bCompileNeeded = true;
		}
		
		return bCompileNeeded;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceCompileJasperFile#compileJRXMLtoJasper(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean compileJRXMLtoJasper(String pathToJasper, String jasperFile) throws myException {
		
		if (!jasperFile.trim().toLowerCase().endsWith(".jrxml")) {
			throw new myException("Filename MUST end with .jxml");
		}
		
		if (!pathToJasper.endsWith(File.separator)) {
			pathToJasper=pathToJasper+File.separator;
		}
		
		String jasperBaseName = jasperFile.substring(0,jasperFile.toLowerCase().indexOf(".jrxml"));
		
		try {
			JasperCompileManager.compileReportToFile(pathToJasper+jasperFile, pathToJasper+jasperBaseName+".jasper");
			bibMSG.MV()._addInfoUT("Report was compiled: "+pathToJasper+jasperBaseName+".jasper");
			return true;
		} catch (JRException e) {
			bibMSG.MV()._addAlarmUT("Report Compile-Error: "+pathToJasper+jasperBaseName+".jasper");

			e.printStackTrace();
			return false;
		}
	}

}
