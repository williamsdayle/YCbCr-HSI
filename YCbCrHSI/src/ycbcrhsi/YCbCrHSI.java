package ycbcrhsi;

import java.io.IOException;

/**
 *
 * @author willi
 */
public class YCbCrHSI {

    
    public static void main(String[] args) throws IOException {
        HSI teste = new HSI();
        teste.getHSI();
        System.out.println("Imagem HSI Processada");
        YCbCr teste2 = new YCbCr();
        teste2.gerarYCbCr();
        System.out.println("Imagem YCbCr Processada");
    }
    
}
