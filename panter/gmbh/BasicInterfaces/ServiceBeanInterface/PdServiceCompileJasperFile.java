/**
 * 
 */
package panter.gmbh.BasicInterfaces.ServiceBeanInterface;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface PdServiceCompileJasperFile {
	
	/**
	 * 
	 * @param pathToJasper absolute path to the jasperFile (including separators at start and end)
	 * @param jasperFile filename of the jasperFile (incl. *.jrxml)
	 * @return
	 * @throws myException
	 */
	public boolean isCompileNeeded(String pathToJasper, String jasperFile) throws myException;

	
	/**
	 * 
	 * @param pathToJasper absolute path to the jasperFile (including separators at start and end)
	 * @param jasperFile filename of the jasperFile (incl. *.jrxml)
	 * @return
	 * @throws myException
	 */
	public boolean compileJRXMLtoJasper(String pathToJasper, String jasperFile) throws myException;
	
	
	


}
