package mustafacomu;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;  
import javax.imageio.ImageIO;
public class Test_CRC {
////////////////////////////



//class CRCExample {  
    static int vv = 0 , xx = 0 , ii=0 , u = 0 , bn = 0 , counter1T = 0, counter1F = 0 , t=0 , n=10 ,z=0 , pat1 = 0 , pat2 = 0 , index = 0;
    static int size = 10, ToRead_pat2 = 0 , counter2T = 0, counter2F = 0 , firstPatt = 0 , secondPatt = 0 , deeChqange = 0 ;
    static StringBuilder sb;
    static double rPat = 0.0 , result = 0.0;
    static boolean Print_with_Error = false;
    public static void main(String args[]) {  
        try{
        File file = new File("C:\\app\\image.jpg");
        BufferedImage img = ImageIO.read(file);
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ImageIO.write(img, "jpeg", b);
        
        byte[] jpgByteArray = b.toByteArray();
        
        sb = new StringBuilder();
        
        for (byte by : jpgByteArray) {
            sb.append(Integer.toBinaryString(by & 0xFF));            
            ii++;
        }
        
        ii = sb.length() / 10;
            //System.out.println("Enter Firts  Pattern: ");
        
            retNumber();
            System.out.println("Pattern 1 Without Error = " + counter1T);
            System.out.println("Pattern 1 With Error = " + counter1F);
            rPat = (double)counter1F/(40482) ;
            System.out.println("Percent of Error for pattern 1 is: "+ (rPat*100) );
            
            System.out.println();
            
            ToRead_pat2=50;        
            u = z = t=0;
            n=10;
            firstPatt = 0;
            counter1T = counter1F = 0;
           // System.out.print("Enter Second  Pattern: ");
            retNumber();               
            System.out.println("Pattern 2 Without Error = " + counter1T);
            System.out.println("Pattern 2 With Error = " + counter1F);            
            rPat = (double)counter1F/(40482) ;
            System.out.println("Percent of Error for pattern 2 is: "+ (rPat*100) );
            
            Print_with_Error = true;
            System.out.println("\n\nNow we will make random change in recive data");
            u = z = t=0;
            n=10;
            vv = 0;
            firstPatt = 0;
            counter1T = counter1F = 0;
            retNumber();               
            System.out.println("Pattern 1 Without Error = " + counter1T);
            System.out.println("Pattern 1 With Error = " + counter1F);            
            rPat = (double)counter1F/(40482) ;
            result = rPat*100;
            System.out.println("Percent of Error for pattern 1 is: "+ (result) );
            
            System.out.println();
            
            ToRead_pat2=50;        
            u = z = t=0;
            n=10;
            firstPatt = 0;
            counter1T = counter1F = 0;
            retNumber();               
            System.out.println("Pattern 2 Without Error = " + counter1T);
            System.out.println("Pattern 2 With Error = " + counter1F);            
            rPat = (double)counter1F/(40482) ;
            System.out.println("Percent of Error for pattern 2 is: "+ (rPat*100) );
            
            if (result > (rPat*100) )
                System.out.println("\nPattern one is more Accurate");
            else
                System.out.println("\nPattern two is more Accurate");
        }catch (Exception ex) {
            System.out.println("Done");           
        }
        
    }
    static int[] divideDataWithDivisor(int oldData[], int divisor[]) { 

        int rem[] = new int[divisor.length];  
        int i;  
        int data[] = new int[oldData.length + divisor.length]; 
       // System.arraycopy(sb, size, data, t, bn);
        System.arraycopy(oldData, 0, data, 0, oldData.length);  
        System.arraycopy(data, 0, rem, 0, divisor.length);  

        for(i = 0; i < oldData.length; i++) {  
            if(rem[0] == 1) {  
                for(int j = 1; j < divisor.length; j++) {  
                    rem[j-1] = xorOperation(rem[j], divisor[j]);  
                }  
            }  
            else {  
                for(int j = 1; j < divisor.length; j++) {  
                    rem[j-1] = xorOperation(rem[j], 0);  
                }  
            } 
            rem[divisor.length-1] = data[i+divisor.length]; // first we will take all bits from first then from second bit and so on
        }  
        return rem;  
    }  
    static int xorOperation(int x, int y) {  
        if(x == y) {  
            return 0;
        }  
        return 1;  
    }  
    static void receiveData(int data[], int divisor[]) {  
       boolean che = true;
        int rem[] = divideDataWithDivisor(data, divisor);  
        for(int i = 0; i < rem.length; i++) {  
            if(rem[i] != 0) {  
                che = false; //number with error
            }  
        } 
        if(che == true){
            counter1T++;//number_witout_error
        }
        else if(che == false){
            counter1F++;
        }
    }  

    private static void retNumber() {
        int data[] = new int[size];

        
        int[] divisor = {};
            int brr = 0;
        while( z != ii){
            sb.setCharAt(t, '0');//first every group should be zero 

            String sCopy = sb.substring(t, n);
            z++;
            n+=10;
            t= t + 10;
            bn = 0;
            u=0;
            int y5 = sCopy.length();
            while( y5 != 0){
                if(sCopy.charAt(u)=='1')
                    bn=1;
                else if (sCopy.charAt(u) == '0')
                    bn=0;
                y5--;
                data[u] = bn;
                u++;
            }  
        String sPAT1 = null ;
        int u = 0;
        if(firstPatt == 0 ){
            int b1 = 0 ;
            vv++;
            firstPatt++;
            if(ToRead_pat2 == 50){
                System.out.println("Enter Second  Pattern: ");
                u = 2;
                ToRead_pat2++;
            }

        if(vv == 1){
            u = 1;
            System.out.println("Enter Firts  Pattern: ");
        }
       /* if (u == 2){
            //System.out.print("Enter Second  Pattern: ");
        }
        else if (u == 1){
            //System.out.print("Enter Firts  Pattern: ");
        }*/
        Scanner sca = new Scanner(System.in);
        sPAT1 = sca.next();
        
        b1 = sPAT1.length();

        u  = 0;
        bn = 0;
        divisor = new int[b1];
        int inde = 0;

        while( b1 != 0){
                if(sPAT1.charAt(u)=='1')
                    bn=1;
                else if (sPAT1.charAt(u) == '0')
                    bn=0;
                b1--;
                u++;
                divisor[inde] = bn;
                inde++;
            }
        }

        int rem[] = divideDataWithDivisor(data, divisor);
        int sentData[] = new int[data.length + rem.length - 1];
        int gINDEX = 0;
        int f1,f2;

        if(Print_with_Error){

            Random rand = new Random();
            f1 = rand.nextInt(10);
            f2 = rand.nextInt(2);
            if(deeChqange <=10){
            data[f1] = f2;
            f1 = rand.nextInt(10);// because the group contain 10 index
            f2 = rand.nextInt(2);// to store 0 or 1 random
            data[f1] = f2;
            deeChqange++;
        }
        else{
            deeChqange = 0;
            data[f1] = f2;
            f1 = rand.nextInt(10);
            f2 = rand.nextInt(2);
            data[f1] = f2;
        }
        //else
          //  deeChqange=0;
        }
        
        for(int i = 0; i < data.length; i++) {  
            sentData [gINDEX]= data[i];  
            //System.out.print(data[i]);
            gINDEX++;
        }  
        for(int i = 0; i < rem.length-1; i++) {  
            sentData [gINDEX] = rem[i]; 
            //System.out.print(rem[i]);
            gINDEX++;
        }
        
        receiveData(sentData, divisor);
        
        }
    }
    
}  


