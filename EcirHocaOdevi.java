package ecirhocaodevi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EcirHocaOdevi {

    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner tara = new Scanner(System.in);
        boolean devamEt = true;
        PrintWriter printWriter = new PrintWriter("example.txt");
        
        while (devamEt) {
            
            String secenekler = "\n1: Arama \n2: Son Aramalar\n3: Çıkış";
            System.out.println(secenekler);
            System.out.println("\nİşlem seçiniz: ");
            int islem = tara.nextInt();
        
        switch (islem) {
            case 1:
                System.out.println("\nNe aramak istersiniz: ");
                String arananKelime = tara.next();
                String url = "https://www.google.com.tr/search?q="+arananKelime+"&num=100";
                int sayac = 0;
                try {
                    
                    Document document = Jsoup.connect(url).get();
                    Elements sonuclar = document.getElementsByClass("iUh30");
                    printWriter.write(arananKelime + "\n");
                    
                    System.out.println("");
                    
                    for (Element arg : sonuclar) {
                        
                        if (arg.hasClass("iUh30 bc")) {
                            continue;
                        }
                        
                        if (sayac==10) {
                            break;
                        }
                        
                        String yeniDegisken = (arg).toString().substring(20).replace("</cite>", "");
                        
                        System.out.println(yeniDegisken);
                        sayac++;
                    }
                    
                } catch (Exception e) {
                    
                    
                }   break;
            case 2:
                 try {
                    printWriter.close();
                    
                    FileReader fileReader = new FileReader("/home/muhendiskedibey/aramaGecmisi.txt");
                    String line;
                    
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    System.out.println("");
                    
                    while ((line = bufferedReader.readLine()) != null) {                        
                        System.out.println(line);
                    }
                    
                    bufferedReader.close();
                    
                } catch (FileNotFoundException ex) {
                    
                    System.out.println("\nDosya bulunamadı");
                    
                } catch (IOException ex) {
                    
                    Logger.getLogger(EcirHocaOdevi.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
            case 3:
                System.out.println("\nÇıkış yapılıyor...");
                devamEt = false;
                break;
            default:
                System.out.println("\nBöyle bir işlem yok!");
                break;
        }
            
        }
    }
    
}
