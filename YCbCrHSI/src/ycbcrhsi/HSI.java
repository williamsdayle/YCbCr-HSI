/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ycbcrhsi;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author willi
 */
public class HSI {

    BufferedImage imagem;
    double teta;
    int largura = 0;
    int altura = 0;
    double R;
    double G;
    double B;
    BufferedImage imagemHSI;
   

    public HSI() throws IOException {
        imagem = ImageIO.read(new File("image.jpg"));
        altura = imagem.getHeight();
        largura = imagem.getWidth();
        imagemHSI = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        

    }

    public double menor(double um, double dois, double tres) {
        double var = 0;
        if (um < dois && um < tres) {
            var = um;
        }
        if (dois < um && dois < tres) {
            var = dois;
        }
        if (tres < dois && tres < um) {
            var = tres;
        }
        return var;
    }

    public void getHSI() throws IOException {
        
        Color HSI = null;
        double tempe = 0;
        
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                Color temp = new Color(imagem.getRGB(i, j));
                R = temp.getRed();
                G = temp.getGreen();
                B = temp.getBlue();
                double valorR = R / 255;
                double valorG = G / 255;
                double valorB = B / 255;

                double numerador = 1/2 *((valorR - valorG) + (valorR - valorB));
                double denumerador = Math.sqrt((Math.pow((valorR - valorG), 2) + (valorR - valorB) * (valorG - valorB)));
                if (numerador < 0) {
                    numerador = numerador * -1;
                }
                if (denumerador < 0) {
                    denumerador = denumerador * -1;
                }
                double valorAngulo = Math.acos(numerador/denumerador+0.000001);
                if(valorB<=valorG){
                    
                    tempe = valorAngulo;
                
                }
                if(valorB > valorG){
                    double x = 360 - valorAngulo;
                    x = x/360;                    
                    tempe = x;
                }
                
               double valorS = 1 - (3/(R+G+B))*menor(R,G,B);
               
               double valorI = (valorR+valorB+valorG)/3;
               tempe = tempe * 100;
               valorI = valorI * 100;
               valorS = valorS * 100;
               
               HSI = new Color((int)tempe,(int)valorS,(int)valorI);
               imagemHSI.setRGB(i, j, HSI.getRGB());
                
            }

        }
        ImageIO.write(imagemHSI, "jpg", new File("ImagemHSI.jpg"));
        
    }

}
