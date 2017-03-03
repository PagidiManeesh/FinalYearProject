package com.ecryption.pkg;
/**
 *@authour Maneesh
 */
/**
 * 
public class RSAText {

}*/
import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class RSAText {
    private int BitSize;
    private BigInteger PrimeP,PrimeQ,PhiN,PrivateKey;
    private BigInteger ModN,PublicKey;
        RSAText(int UbitSize)/*Key Generator Function 
        here the keys are generated randomly using inbuild functions   */
        {
            BitSize = UbitSize;
            if(true)
            {
                PrimeP = BigInteger.probablePrime(BitSize, new Random());// Generating key P
                /*Returns a positive BigInteger that is probably prime, with the specified bitLength.
                 The probability that a BigInteger returned by this method is composite does not exceed 2-100
                 Eg :probablePrime(123, new Random() Returns a random prime number of size 123 bit  */
                PrimeQ = BigInteger.probablePrime(BitSize, new Random());//Generating key Q
                
                ModN = PrimeP.multiply(PrimeQ);//n=pq multiply function is used to multiply the numbers
                
                PhiN = PrimeP.subtract(BigInteger.valueOf(1)).multiply(PrimeQ.subtract(BigInteger.valueOf(1)));//subtract 1 from each big Integer p and q and multiply phi(n)=(p-1)(q-1)
                // Next choose e, coprime to and less than PhiN ,,1 < e < ϕ(n),gcd(e,ϕ(n))=1, e is Public Key
                do/* Here the public key is genetated and within the specified above conditions*/
                {
                    PublicKey = new BigInteger(2 * BitSize, new Random());
                    if((PublicKey.compareTo(PhiN) == -1) && (PublicKey.compareTo(BigInteger.ONE) == 1) && (PublicKey.gcd(PhiN).compareTo(BigInteger.ONE) == 0))
                        break;// comparing with phin such that weather the e is greater than or not and comparing with one and finding the Gcd of e and phin its gcd sould be equal to 1 
                } while (true);
                PrivateKey = PublicKey.modInverse(PhiN);// generating the private key de ≡ 1 (mod ϕ(n)).
            }
            System.out.println("Bit="+UbitSize+"\n");
            System.out.println("P=" + PrimeP.toString()+"\n");
            System.out.println("Q=" + PrimeQ.toString()+"\n");
            System.out.println("n=" + ModN.toString()+"\n");
            System.out.println("phiN=" + PhiN.toString()+"\n");
            System.out.println("e=" + PublicKey.toString()+"\n");
            System.out.println("d=" + PrivateKey.toString()+"\n");
        }
 
      /*  public BigInteger GetPrivateKey()
        {
            return PrivateKey;
        }
        public BigInteger GetPublicKey()
        {
            return PublicKey;
        }
        public BigInteger GetModulus()
        {
            return ModN;
        }
        public String encryptHexStr(String sHexStr)
        {
            return encryptMessage(sHexStr, PhiN, PrivateKey);
        }
        public String encryptHexStr(String sHexStr,BigInteger ModN,BigInteger Key)
        {
            return encryptMessage(sHexStr,ModN,Key);
        }
        public String decryptHexStr(String sHexStr,BigInteger ModN,BigInteger Key)
        {
            return decryptMessage(sHexStr,ModN,Key);
        }*/
        public String encryptPlainStrToHex(String sPlainStr)//the method(1 Called method) accepts the plain string and then it invokes encyptmessage with parameter as other function which converts as string 
        {
            return encryptMessage(convertStringToHex(sPlainStr),ModN, PrivateKey);//this function returns encypted message encyptmessage with parameter as other function which converts as string 
            
        }
        public String decryptHexCipherToPlainMsg(String sHexCipherMsg)/* returns the decrypted message
        cipher text message is passed and then converthextostring is invoked with decryptmessage  function as a parameter */
        {
            return convertHexToString(decryptMessage(sHexCipherMsg,ModN,PublicKey));//this function returns hexstring  converthextostring is invoked with decryptmessage  function as a parameter
        }
        private String encryptMessage(String sHexString, BigInteger N, BigInteger e)
        {
            //System.out.println(":::::encrypt Function Started::::::");
            if(sHexString.length()==0 || sHexString == null)
                return null;
            int iMaxCharLenInOneStr = BitSize/2;
            if (iMaxCharLenInOneStr <= sHexString.length())
            {
                String sRetOutStr = "";//return string
                String sOutStr = null;// local string
                int iBeginIndex = 0;
                int iEndIndex = iMaxCharLenInOneStr - 1;
                while (iBeginIndex < sHexString.length())
                {
                    if (iEndIndex < sHexString.length()) {
                        sOutStr = (new BigInteger(sHexString.substring(iBeginIndex, iEndIndex), 16)).modPow(e, N).toString(16);/*
                      ***BigInteger(str, radix):  Translates the String representation of a BigInteger in the specified radix into a BigInteger. The String representation consists of optional
                       minus or plus sign followed by a sequence of one or more digits in the specified radix. The character-
                       to-digit mapping is provided by Character.digit. The String may not contain any extraneous characters (whitespace, for example).
                      ***substring(s,e) retuns substring starting from s to e
                      *** calculate m^e mod n  
                       */
                        iBeginIndex = iEndIndex;
                        iEndIndex += (iMaxCharLenInOneStr - 1);
                    }
                    else
                    {
                        sOutStr = (new BigInteger(sHexString.substring(iBeginIndex), 16)).modPow(e, N).toString(16);
                        iBeginIndex = sHexString.length();
                    }
                    if(sOutStr.length() < iMaxCharLenInOneStr)
                    {
                        int iLen = iMaxCharLenInOneStr - sOutStr.length();
                        for(int k = 0;k < iLen;k++)
                            sOutStr = "0" + sOutStr;
                    }
                    sRetOutStr += sOutStr;
                }
                return sRetOutStr;
            }
            else
                return (new BigInteger(sHexString, 16)).modPow(e, N).toString(16);
            //System.out.println(":::::encrypt Function Ended::::::");
        }
        private String decryptMessage(String sHexString, BigInteger N, BigInteger d)
        {
            //System.out.println(":::::decrypt Function Started::::::");
            if(sHexString.length()==0 || sHexString == null)
                return null;
            int iMaxCharLenInOneStr = BitSize/2;
            if (iMaxCharLenInOneStr < sHexString.length())
            {
                String sRetOutStr = "";
                int iBeginIndex = 0;
                int iEndIndex = iMaxCharLenInOneStr;
                while (iBeginIndex < sHexString.length())
                {
                    if (iEndIndex < sHexString.length())
                    {
                        sRetOutStr += (new BigInteger(sHexString.substring(iBeginIndex, iEndIndex), 16)).modPow(d, N).toString(16);
                        iBeginIndex = iEndIndex;
                        iEndIndex += iMaxCharLenInOneStr;
                    }
                    else
                    {
                        sRetOutStr += (new BigInteger(sHexString.substring(iBeginIndex), 16)).modPow(d, N).toString(16);
                        break;
                    }
                }
                return sRetOutStr;
            }
            else
                return (new BigInteger(sHexString, 16)).modPow(d, N).toString(16);
        }
        public String convertStringToHex(String str)
        {
            char[] chars = str.toCharArray();
            StringBuilder hex = new StringBuilder();
            for(int i = 0; i < chars.length; i++){
                hex.append(Integer.toHexString((int)chars[i]));
            }
            return hex.toString();
        }
        public String convertHexToString(String hex)
        {
            StringBuilder sb = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            //49204c6f7665204a617661 split into two characters 49, 20, 4c...
            for( int i=0; i<hex.length()-1; i+=2 ){
                //grab the hex in pairs
                String output = hex.substring(i, (i + 2));
                //convert hex to decimal
                int decimal = Integer.parseInt(output, 16);
                //convert the decimal to character
                sb.append((char)decimal);
                temp.append(decimal);
            }
            //System.out.println("Decimal : " + temp.toString());
            return sb.toString();
        }
 
    public static void main(String[] args) {
        int UbitSize = 512;
        System.out.println("Enter The BIT SIZE  Greater than 10 \n");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        UbitSize = sc.nextInt();
        System.out.println("\n");	
        try
        {
        	long start1 = System.currentTimeMillis();
            RSAText r = new RSAText(UbitSize);
            long time1 = System.currentTimeMillis() - start1;
            System.out.println("Total Key Generation time is  "+time1+"  milliSeconds");
            System.out.println("Note: The message M must be smaller than the modulus N");
            System.out.println("Enter message: ");
            System.out.println();
            String input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
            long start = System.currentTimeMillis();
            String sHexCipherText = r.encryptPlainStrToHex(input);
            String sPlainText = r.decryptHexCipherToPlainMsg(sHexCipherText);
            long time = System.currentTimeMillis() - start;
            System.out.println();
            System.out.println("\n");
            System.out.println("CipherText: " + sHexCipherText);
            System.out.println("\n");
            System.out.println("PlainText/Original message: "+sPlainText);
            System.out.println("Total Encryption and decryption time is  "+time+"  milliSeconds");
        } catch (Exception va) {
        	 va.printStackTrace();
             System.out.println(va.getMessage());
        }
 
    }
}
 

