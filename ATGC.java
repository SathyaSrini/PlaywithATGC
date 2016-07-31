//<editor-fold defaultstate="collapsed" desc="comment">
package atgc;

import java.io.*;
import java.util.Scanner;

public class ATGC
{
    
    String inputString;
    int resultOption;
    
    //===================== Get and Set option - Compression/Decompression =======//
    
    int getOptionFromUser()
    {
        
        //Option :1 - ATGC Compression
        //Option : 2 - ATGC Decompression
        
        Scanner s = new Scanner(System.in);
        
        System.out.print("Enter \n 1 for ATGC Compression \n 2 for ATGC Decompression: "  );
        String option = s.nextLine();
        switch (option) {
            case "1":
                return 1;
            case "2":
                return 2;
            default:
                return 0;
        }
    }
    void setInputOption(String inputStr)
    {
        //set the input option from the user
        resultOption = Integer.parseInt(inputStr);
    }
    
    //=================== Get binary string input for Compression/Decompression ==//
    
    void getInputFromUser() throws UnsupportedEncodingException
    {
        Scanner s = new Scanner(System.in);
        if(resultOption == 1) //Comrpession
        {
            System.out.print("Enter \n the string for compression :");
            String inputForCompression = s.nextLine();
            
            this.ATGCCompression(inputForCompression, resultOption);
            
        }
        else if(resultOption == 2) //Decompression
        {
            System.out.print("Enter \n the string for decompression :");
            String inputForDecompression = s.nextLine();
            this.ATGCDecompression(inputForDecompression);
            
        }
        
    }
    
    //==================== Validates the binry string input ======================//
    
    boolean validateInput(String inputStr)
    {
        boolean validationResult = false;
        
        int inputStringLength = inputStr.length();
        
        if(inputStringLength>0)
        {
            if(resultOption==1)
            {
                //Compression
                
                validationResult = true;
                if(inputStringLength%7==0)
                {
                    //System.out.println("Validates");
                }
                else
                {
                    validationResult = false;
                    System.out.println("Error");
                }
            }
            else
            {
                //Decompression
                
                if(inputStringLength%2==0)
                {
                    //System.out.println("Validates");
                    validationResult = true;
                }
                else
                {
                    System.out.println("Error");
                    validationResult = false;
                }
            }
            
        }
        else
        {
            validationResult = false;
        }
        
        return validationResult;
        
    }
    
    //================================== ATGC COMPRESSION =======================//
    void ATGCCompression(String input, int k) throws UnsupportedEncodingException
    {
        boolean validate = this.validateInput(input);
        //validate = true;
        
        if(validate)
        {
            String asciiText = "";
            char nextChar;
            int subStringIndex = 0;
            String result = "";
            String subStr = "";
            int subStringLength = 0;
            int value =0;
            
            int inputStringLength = input.length();
            
            int noOfStrings = inputStringLength/7;
            //System.out.println(noOfStrings);
            
            //String Extraction - 7 bits at a time
            for(int i=0;i<noOfStrings;i++)
            {
                subStr = input.substring(subStringIndex,subStringIndex+7);
                subStringLength = subStr.length();
                
                //System.out.println("Substring : "+subStr);
                //System.out.println("Substring : "+subStringLength);
                
                subStringIndex+=7;
                
                //System.out.println("Substring: "+subStr);
                //String Compression using ATGCCompres1sion
                for(int j=0;j<subStringLength;j++)
                {
                    
                    nextChar = subStr.charAt(j);
                    
                    int temp = nextChar-48;
                    //System.out.println("Int:"+temp);
                    
                    int tempValue = (int)(temp*Math.pow(2,subStringLength-j-1));
                    //System.out.println("Val:"+tempValue);
                    
                    value+=tempValue;
                    //System.out.println("ToTVal:"+value);
                    //subStringLength--;
                }
                String returnCharacter = this.compressedStringInterpretor(value);
                result+=returnCharacter;
                value =0;
                //System.out.println(value);
                
            }
            
            System.out.println("ATGC Compressed Result:"+result);
            
        }
    }
    
    String compressedStringInterpretor(int partialInput)
    {
        String singleVal;
        
        switch(partialInput)
        {
            case 65:singleVal  = "00"; //"A"
            break;
            case 67 :singleVal = "01";//"C"
            break;
            case 71 :singleVal = "10";//"G"
            break;
            case 84 :singleVal = "11";//"T"
            break;
            default :singleVal = "EE";//"Error"
            break;
                
        }
        // System.out.print(singleVal);
        return singleVal;
    }
    //================================== ATGC COMPRESSION =======================//
    
    //================================== ATGC DECOMPRESSION ====================//
    void ATGCDecompression(String input)
    {
        boolean validate = this.validateInput(input);
        //validate = true;
        
        if(validate)
        {
            String asciiText = "";
            char nextChar;
            int subStringIndex = 0;
            String result = "";
            String subStr = "";
            int subStringLength = 0;
            int value =0;
            
            int inputStringLength = input.length();
            
            int noOfStrings = inputStringLength/2;
            //System.out.println("noOfStrings: "+ noOfStrings);
            
            //String Extraction - 2 bits at a time
            for(int i=0;i<noOfStrings;i++)
            {
                subStr = input.substring(subStringIndex,subStringIndex+2);
                subStringLength = subStr.length();
                
                //           System.out.println("Substring : "+subStr);
                //           System.out.println("Substring : "+subStringLength);
                
                subStringIndex+=2;
                
               // System.out.println("Substring: "+subStr);
                
                //String DeCompression using ATGCDeCompression
                
                String returnString = this.deCompressedStringInterpretor(Integer.valueOf(subStr));
                result+=returnString;
                
                
            }
            
            System.out.println("ATGC Decompressed Result:"+result);
            
        }
        
        
    }
    
    String deCompressedStringInterpretor(int partialInput)
    {
        String singleVal;
        
        switch(partialInput)
        {
            case 00:singleVal = "A"; //"A"
            break;
            case 01 :singleVal = "C";//"C"
            break;
            case 10:singleVal = "G";//"G"
            break;
            case 11:singleVal = "T";//"T"
            break;
            default :singleVal="EE";//"Error"
            break;
                
        }
        // System.out.print(singleVal);
        return singleVal;
        
    }
    //================================== ATGC DECOMPRESSION ====================//
    
    public static void main(String []args) throws UnsupportedEncodingException
    {
        
        ATGC obj = new ATGC();
        
        int result = obj.getOptionFromUser();
        
        if(result ==1) //Compression
        {
            obj.setInputOption(String.valueOf(result));
            obj.getInputFromUser();
        }
        else if(result ==2)//Decompression
        {
            obj.setInputOption(String.valueOf(result));
            obj.getInputFromUser();
        }
        else
        {
            
            //Recursive implementation to get a valid input option from the user
            
            System.out.println("Please enter a valid input: 1 - ATGC Compression 2- ATGC Decompression");
            
            result = obj.getOptionFromUser();
            
            boolean flag = ((result!=1) && (result !=2));
            
            while(flag)
            {
                
                result = obj.getOptionFromUser();
                
                
            }
            
            obj.setInputOption(String.valueOf(result));
            obj.getInputFromUser();
            
        }
        
        
        //System.out.println(obj.resultOption);
        
    }
}
//</editor-fold>
