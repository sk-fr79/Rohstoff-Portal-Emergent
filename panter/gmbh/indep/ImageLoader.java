package panter.gmbh.indep;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.StreamImageReference;


/**
 * private Klasse zum lesen der Datei in ene StreamImageReference
 * @author manfred
 *
 */
public class ImageLoader  extends StreamImageReference {
	private String id = ApplicationInstance.generateSystemId();
	private static final int BUFFER_SIZE = 4096;
	
	private String m_sFileName = null;
	
	public ImageLoader(String sFilename) {
		m_sFileName = sFilename;
	}
	
	/**
     * @see nextapp.echo2.app.RenderIdSupport#getRenderId()
     */
    public String getRenderId() {
        return id;
    }
	
    /**
     * @see nextapp.echo2.app.StreamImageReference#getContentType()
     */
    public String getContentType() {
        return "image/jpeg";
    }
	
    @Override
    public Extent getWidth() {
    	return super.getWidth();
    }
    
    @Override
    public Extent getHeight() {
    	return super.getHeight();
    }
    
    
    /**
     * @see nextapp.echo2.app.StreamImageReference#render(java.io.OutputStream)
     */
    public void render(OutputStream out) throws IOException {
        InputStream in = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        
        try {
        	
            in = new FileInputStream(m_sFileName);
            do {
                bytesRead = in.read(buffer);
                if (bytesRead > 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } while (bytesRead > 0);
        } finally {
            if (in != null) { try { in.close(); } catch (IOException ex) { } } 
        }
    }

}


	


