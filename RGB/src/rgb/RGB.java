/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgb;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author nogueira
 */
public class RGB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            int [] red = new int[256];
            int [] green = new int[256];
            int [] blue = new int[256];
            
            for(int i = 0;i < altura;i++){
                for(int j = 0;j<largura;j++){
                    red[new Color(imagem.getRGB(j, i)).getRed()]++;
                    green[new Color(imagem.getRGB(j, i)).getGreen()]++;
                    blue[new Color(imagem.getRGB(j, i)).getBlue()]++;  
                }
            }
            
            
            
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i<256 ;i++) {
            String aux = String.valueOf(i);
            dataset.addValue(red[i], "R", aux);
            dataset.addValue(green[i], "G", aux);
            dataset.addValue(blue[i], "B", aux);
        }
        
        
        JFreeChart chart = ChartFactory.createLineChart("Histograma","intensidade","frequencia",
                dataset,PlotOrientation.VERTICAL,true, true, false);
        
       chart.getCategoryPlot().getRenderer(0).setSeriesPaint(0, Color.RED);
        chart.getCategoryPlot().getRenderer(0).setSeriesPaint(1, Color.GREEN);
        chart.getCategoryPlot().getRenderer(0).setSeriesPaint(2, Color.BLUE);
        
        OutputStream arquivo = new FileOutputStream("grafico.png");
        ChartUtilities.writeChartAsPNG(arquivo, chart, 1600, 900);
        arquivo.close();
        
        
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(RGB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
