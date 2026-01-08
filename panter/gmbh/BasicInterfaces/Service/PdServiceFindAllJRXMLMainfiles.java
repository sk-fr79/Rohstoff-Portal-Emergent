/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 03.02.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.io.File;
import java.util.Comparator;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 03.02.2020
 *
 */
public class PdServiceFindAllJRXMLMainfiles {

	/**
	 * @author martin
	 * @date 03.02.2020
	 *
	 */
	public PdServiceFindAllJRXMLMainfiles() {
	}
	
	public VEK<String> getAllJrxmlMainFiles() {
		VEK<String> reports = new VEK<>();
		
		VEK<File> dirs = new VEK<>();
		dirs._a(new File(bibALL.get_REPORTPATH_LISTEN_MANDANT()));
		dirs._a(new File(bibALL.get_REPORTPATH_MANDANT()));
		dirs._a(new File(bibALL.get_REPORTPATH_LISTEN_ALLE()));
		dirs._a(new File(bibALL.get_REPORTPATH_ALLE()));
		
		
		for (File dir: dirs) {
			if (dir.exists() && dir.isDirectory()) {
				File[] arr = dir.listFiles();
				
				for (File f: arr) {
					if (!f.isDirectory() && f.canRead() && f.exists() && f.getName().toUpperCase().endsWith("JRXML")) {
						reports._addIfNotIN(f.getName());
					}
				}
			}
		}
		
		return reports;
	}

	
	public HMAP<String,String> getAllJrxmlMainFilesHMAP(boolean ordered) {
		VEK<String> reports = this.getAllJrxmlMainFiles();

		if (ordered) {
			reports.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					String oo1 = S.NN(o1);
					String oo2 = S.NN(o2);
					return oo1.toUpperCase().compareTo(oo2.toUpperCase());
				}
				
			});
		}
		
		HMAP<String, String> map = new HMAP<>();
		for (String rep: reports) {
			map._put(rep, rep);
		}
		
		return map;
	}

	
}
