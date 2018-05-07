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

public class YCbCr {

    BufferedImage imagem;
    BufferedImage imagemYCbCr;
    
    int largura = 0;
    int altura = 0;
    int R, G, B;
    double Y, Cb, Cr;

    double[][] matriz = new double[3][3];

    public YCbCr() throws IOException {
        imagem = ImageIO.read(new File("image.jpg"));
        matriz[0][0] = 0.2568;
        matriz[0][1] = 0.5041;
        matriz[0][2] = 0.0979;
        matriz[1][0] = -0.1482;
        matriz[1][1] = -0.2910;
        matriz[1][2] = 0.4392;
        matriz[2][0] = 0.4392;
        matriz[2][1] = -0.3678;
        matriz[2][2] = -0.0714;
        altura = imagem.getHeight();
        largura = imagem.getWidth();
        imagemYCbCr = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        

    }

    public void gerarYCbCr() throws IOException {
        for (int i = 0; i < largura -1; i++) {
            for (int j = 0; j < altura - 1; j++) {
                
                Color c = new Color(imagem.getRGB(i, j));
                this.B = c.getBlue();
                this.R = c.getRed();
                this.G = c.getGreen();
                
                int linha = 0;
                int coluna = 0;

                Y = matriz[linha][coluna] * R;
                coluna++;
                Y += matriz[linha][coluna] * G;
                coluna++;
                Y += matriz[linha][coluna] * B;
                Y += 16;
                linha++;
                coluna = 0;
                Cb = matriz[linha][coluna] * R;
                coluna++;
                Cb += matriz[linha][coluna] * G;
                coluna++;
                Cb += matriz[linha][coluna] * B;
                Cb += 128;
                linha++;
                coluna = 0;
                Cr = matriz[linha][coluna] * R;
                coluna++;
                Cr += matriz[linha][coluna] * G;
                coluna++;
                Cr += matriz[linha][coluna] * B;
                Cr += 128;
                Color corYCbCr = new Color((int)Y,(int)Cb,(int)Cr);
                
                imagemYCbCr.setRGB(i, j, corYCbCr.getRGB());
                

            }

        }

        ImageIO.write(imagemYCbCr,"jpg", new File("ImagemYCbCr.jpg"));
        

    }

}
