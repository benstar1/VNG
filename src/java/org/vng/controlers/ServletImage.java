/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vng.controlers;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
//mport java.util.logging.Logger;
 //import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AAKAKPO
 */
public class ServletImage extends HttpServlet {
 //Logger logger = Logger.getLogger(ServletImage.class.getName());
    String cheminabsolu="";
    
    
    
    @Override
    public void init() throws ServletException
	{	
            
		super.init();		//To change body of generated methods, choose Tools | Templates.
                cheminabsolu=getInitParameter("cheminabsolu");
                System.out.println("Racine : "+cheminabsolu);
        
        }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{	
		try 
		{
 
			DataOutput output = new DataOutputStream( response.getOutputStream() );
			response.setContentType("image/jpeg");
 
			String nomImage = request.getParameter("imageName");
                           
			File file = null;
 
			FileInputStream in = null;			
 
			String filePath = nomImage;
                        System.out.println(" Chemin image dans la servlet : "+filePath);
 
			file = new File(filePath);				
 
			/*
			 * Dans le cas ou l'image n'est pas présente dans le répertoire
			 * On affiche une image par defaut 'Image Introuuvable'
			 */
			if(!file.exists())
			{
                            
				String path = request.getServletContext().getRealPath("")+"/resources/AppliGeo/AppliGeoImage/defaultuserimage.jpg";
                                System.out.println("chemin par defaut "+path);
				file = new File(path);				  
			}
 
			in = new FileInputStream(file);
 
			response.setContentLength((int)file.length());
 
			byte buffer[]=new byte[4096];
			int nbLecture;
 
			while( (nbLecture = in.read(buffer)) != -1 )
			{				
				output.write(buffer,0,nbLecture);					
			}	
 
			in.close();
 
		}
		catch (IOException e)
		{
                    System.out.println("Erreur image : "+e);
		//    logger.error("erreur lors du renvoie du fichier jpg",e);		
		}		
	}	
}
