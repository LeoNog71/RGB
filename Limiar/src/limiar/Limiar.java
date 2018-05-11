/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package limiar;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author nogueira
 */
public class Limiar {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
         try {
            String pasta= "";
            JFileChooser jfc=new JFileChooser();
            
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                pasta= jfc.getSelectedFile().getAbsoluteFile().toString();            
                System.out.println(pasta);
            }
            
            
            
            BufferedImage imagem = ImageIO.read(new File(pasta));
            
            int altura = imagem.getHeight();
            int largura = imagem.getWidth();
            Double media= 0.0;
            Boolean[][] mascaraBoleean = new Boolean[altura][largura];
            int tomDeCinza;
            int pixels[] = new int[altura * largura];
            WritableRaster r = imagem.getRaster();
            
            for(int i = 0;i < altura;i++){
                for(int j = 0;j<largura;j++){
                    
                    r.getPixel(j, i, pixels);
                    
                    media +=new Color(imagem.getRGB(j, i)).getRed();
                    media +=new Color(imagem.getRGB(j, i)).getGreen();
                    media +=new Color(imagem.getRGB(j, i)).getBlue();
                    media = media/3;
            
                    
                    tomDeCinza = media.intValue();
                    
                    pixels[0] = tomDeCinza;
                    pixels[1] = tomDeCinza;
                    pixels[2] = tomDeCinza;
                    
                    if(tomDeCinza > 254){
                        int p[] = {0,0,0}; 
                        r.setPixel(j, i, p);
                        //r.setPixel(j, i, pixels);
                    }
                    
                    
                    
                   
                    //mascaraBoleean[i][j] = tomDeCinza > 128;
                }
                media = 0.0;
            }
            
        //salvando a imagem
        imagem.setData(r);
        
        try {
              ImageIO.write(imagem, "png", new File("/home/nogueira/Imagens/tonsDecinza.png"));
        } catch (IOException ex) {
               System.out.println("Erro :"+ex);
        } catch (Exception ex) {
               System.out.println("Erro 2:"+ex);
        }
         } catch (IOException ex) {
             System.out.println(ex);
        }
    }
    
}
