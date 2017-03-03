package com.ecryption.pkg;

import java.util.*;
import java.math.*;
class RSA //Defining the Class as RSA
{
 static double primefactphi(int n)
    {
        HashMap<Integer,Integer> m = new HashMap<>();

     for(int i=2;i<n;i++)
//abc
     {

         if(n%i==0)
         {
             int count=0;
             while(n%i==0)
             {
                 n=n/i;
                 count++;
             }
             m.put(i,count);
         }
     }if(n!=1) m.put(n,1); 
     Set<Map.Entry<Integer,Integer>> values = m.entrySet();
    /* for(Map.Entry<Integer,Integer> e: values)
     {
         System.out.println(e.getKey()+"  :  "+e.getValue()); 
     }*/
     double mul=1;
     for(Map.Entry<Integer,Integer> e: values)
     {
         if(e.getValue()>1)
         {
            //e.getValue()= e.getValue()-1;
            //m.put(e.getKey(), e.getValue() - 1);
             mul = mul*(Math.pow(e.getKey(),e.getValue()-1));
         } 
         else
         {
           //  m.put(e.getKey()-1, e.getValue());
              mul = mul*(Math.pow(e.getKey()-1,e.getValue()));
         }
     }
    // System.out.println(mul);
     return (mul-1);
    }
    public static void main(String ar[])
    {
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
      System.out.println("Enter the Values of P and Q Greater than 300 Decimals");
        int p= sc.nextInt();
        int q= sc.nextInt();
        System.out.println("Enter the plain Text");
        int pl =sc.nextInt();
        int n=(p*q);
        int phi = (p-1)*(q-1);
        System.out.println("Value of e");
        int e= sc.nextInt();
        
        BigInteger f= new BigInteger(""+e);
        BigInteger resu = f.pow((int)primefactphi(phi));
      //  System.out.println("f =  "+resu);
        BigInteger d=(resu.remainder(new BigInteger(""+(int)phi)));

     /*   
       double f = Math.pow(e,(primefactphi(phi)-1));
        double d= f % phi;*/
        System.out.println("d =  "+d);
        System.out.println("HEre the encryption starts");
        double s=Math.pow(pl,e);
        double c= s%n;
        System.out.println("Encrypted is "+ c);
      System.out.println("HEre the decryption starts");
      BigInteger i=new BigInteger(""+(int)c);
      BigInteger result=i.pow(d.intValue());
      System.out.println(result.remainder(new BigInteger(""+(int)n)));  
    }
}
