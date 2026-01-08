package panter.gmbh.Echo2.__BASIC_COMPONENTS.IMAGE_HANDLING;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.StreamImageReference;

/**
 * Aktuell nur experimentell - MUSS NOCH AUSGEBAUT WERDEN
 * 
 * 
 * Klasse erweitert die Echoe StreamImageRefrence damit man Bilderinformationen extrahieren kann aus einem 
 * BufferedImage
 * 
 * 
 * @author manfred
 * @date 15.10.2015
 *
 */
public class IMG_StreamImageReference  extends StreamImageReference{
	private String id = ApplicationInstance.generateSystemId();
	private static final int BUFFER_SIZE = 4096;
	
	private Integer m_newWidth 		= null;
	private Integer	m_newHeight 	= null;
	private String 	m_sFileName 	= null;
	
	public IMG_StreamImageReference(String sFilename) {
		m_sFileName = sFilename;
	}
	
	
	public IMG_StreamImageReference(String sFilename, Integer width, Integer height){
		m_sFileName = sFilename;
		m_newHeight = height;
		m_newWidth  = width;
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
    private int m_height = 0;
	private int m_width = 0;

    public void render(OutputStream out) throws IOException {
    	File inputFile = new File (m_sFileName);
    	
    	String contentType = Files.probeContentType(inputFile.toPath());
    	
    	FileInputStream in = new FileInputStream(m_sFileName);
    	
		BufferedImage inputImage = ImageIO.read(in);
		BufferedImage inputImage2 = inputImage;

		if (m_newWidth != null && m_newHeight != null){
			inputImage2 = Scalr.resize(inputImage, Scalr.Method.SPEED,Scalr.Mode.FIT_TO_WIDTH,m_newWidth, m_newHeight,Scalr.OP_ANTIALIAS);
		}
		
		m_height = inputImage2.getHeight();
		m_width = inputImage2.getWidth();
		
		ImageIO.write(inputImage2, "jpg", out );
		
		out.flush();
		in.close();
    }

		
	

}
