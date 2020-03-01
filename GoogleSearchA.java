
package googlesearcha;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class GoogleSearchA {
    
    public static void dosyayaKaydet(String aranan, ArrayList<String> liste){
        try{
            PrintWriter pw = new PrintWriter(
                "/home/ecir/NetBeansProjects/GoogleSearchA/Linkler.txt","UTF-8");
            
            pw.write("Arama İfadesi : " + aranan + "\n");
            for(int i = 0; i < liste.size(); i++)
                pw.write(liste.get(i) + "\n");
            
            pw.close();
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(UnsupportedEncodingException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void aramaYap(){
        Scanner scn = new Scanner(System.in);
        String arananKelime, devam, aramaUrl;
        int secenek;
        ArrayList<String> urls = new ArrayList<String>();
        while(true){
            urls.clear();
            System.out.print("Aramak İstediğiniz İfade : ");
            arananKelime = scn.nextLine();
            aramaUrl = "https://www.google.com/search" + 
                    "?q=" + arananKelime + "&num=10";
            int sira = 1;
            try{
                Document document = Jsoup.connect(aramaUrl).get();
                Elements elements = document.getElementsByClass("r");
                for (int i = 0; i < elements.size(); i++) {
                    String link = elements.get(i).child(0).attr("href");
                    if(!link.isEmpty()){
                        System.out.printf("%d. %s \n", sira, link);
                        sira++;
                    }
                    urls.add(link);
                }
                dosyayaKaydet(arananKelime, urls);
                System.out.print("Açmak İstediğiniz Link : ");
                secenek = Integer.parseInt(scn.nextLine());
                String[] browseUrl = {"firefox", urls.get(secenek-1)};
                Runtime.getRuntime().exec(browseUrl);
                System.out.print("Devam Etmek İstiyor musunuz ?(E/H)");
                devam = scn.nextLine();
                if(devam.equals("H") || devam.equals("h"))
                    break;
                
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            
        }
    }
    
    public static void  listele(){
        Scanner scn = new Scanner(System.in);
        File file = new File(
                "/home/ecir/NetBeansProjects/GoogleSearchA/Linkler.txt");
        int secim, satir = 1;
        String line;
        ArrayList<String> urls = new ArrayList<String>();
        
        try{
            BufferedReader bf = new BufferedReader(new FileReader(file));
            System.out.println("Aranan Kelime = " + bf.readLine());
            
            while((line = bf.readLine()) != null){
                System.out.printf("%d. %s \n", satir, line);
                urls.add(line);
                satir++;
            }
            System.out.print("Açmak İstediğiniz Site");
            secim = scn.nextInt();
            String[] browseUrl = {"firefox", urls.get(secim -1)};
            Runtime.getRuntime().exec(browseUrl);
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int secenek;
        
        while(true){
            System.out.println("Seçenekler ");
            System.out.println("1. Arama Yap");
            System.out.println("2. Son Aramayı Listele");
            System.out.println("3. Çıkış");
            
            System.out.print("Seçiminiz : ");
            secenek = scn.nextInt();
            
            if(secenek == 1)
                aramaYap();
            else if(secenek == 2)
                listele();
            else
                break;
            
        }
        System.out.println("Program Sona Erdi");
        
        
        
    }
    
}
