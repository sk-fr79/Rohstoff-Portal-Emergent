package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_COMP_Selfield_externalComp extends RB_SelectField {

	private static final String STRPACK ="calledByName.maskRenderer";

	@SuppressWarnings("rawtypes")
	public MC_DES_COMP_Selfield_externalComp(IF_Field field,int i_width) throws myException {
		super(field);
		this.setWidth(new Extent(i_width));
		Class[] classList;
		TreeMap <String, String> extracted_classlist;
		try {
			classList = getClasses(STRPACK);
			extracted_classlist = new TreeMap <String, String>();
			for(int i = 0;i<classList.length;i++) {
				if(	!
					(classList[i].getSimpleName().contains("IF_external_comp") ^
					(classList[i].getSimpleName().contains("RENDER_VERSION")) ))
						 {
			
					extracted_classlist.put(classList[i].getSimpleName(),  classList[i].getCanonicalName());
				}
			}
			
			String[][] final_list = new String[extracted_classlist.size()][2];
			int i = 0;
			for(String key : extracted_classlist.keySet()) {
				final_list[i][0] = key;
				final_list[i][1] = extracted_classlist.get(key);
				i++;
			}
			
			this.set_ListenInhalt(bibARR.add_emtpy_db_value_inFront(final_list), false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	@SuppressWarnings("rawtypes")
	public static Class[] getClasses(String pckgname) throws ClassNotFoundException {
		ArrayList<Class> classes = new ArrayList<Class>();
		// Get a File object for the package
		File directory = null;
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			String path = pckgname.replace('.', '/');
			URL resource = cld.getResource(path);
			if (resource == null) {
				throw new ClassNotFoundException("No resource for " + path);
			}
			directory = new File(resource.getFile());
		}
		catch (NullPointerException x) {
			throw new ClassNotFoundException(pckgname + " (" + directory + ") does not appear to be a valid package");
		}
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					classes.add(Class.forName(pckgname + '.' + files[i].substring(0, files[i].length() - 6)));
				}
			}
		}
		else {
			throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
		}
		Class[] classesA = new Class[classes.size()];
		classes.toArray(classesA);
		return classesA;
	}

}
