package com.pb.test.intraflowmatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.*;

public class PersonalNameElementsIntraFlowCompareDirect {

  public static boolean WetherErrorOrNot=false;
  
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
	  
	  
	  //String Path = args[0];
    //String C1 = Path + args[1];
    //String C2 = Path + args[2];
		String C1="C:\\Users\\va003to\\OneDrive - Pitney Bowes\\Spectrum\\Spectrum20.1\\bdq\\hive\\intraflow\\del\\IntraFlow_Name_Data_recordid_byspectrumTC1.csv";
		String C2="C:\\Users\\va003to\\OneDrive - Pitney Bowes\\Spectrum\\Spectrum20.1\\bdq\\hive\\intraflow\\del\\BDQ_Intraflow_Output_byhive_TC1.csv";
	  System.out.println("Comparing files " + C1 +" and "+ C2); 
	  
		HashMap<Integer, PersonalNameElements> File1 = new HashMap<Integer, PersonalNameElements>();
		HashMap<Integer, PersonalNameElements> File2 = new HashMap<Integer, PersonalNameElements>();
		
		HashMap<String, ArrayList<String>> mMap1 = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> mMap2 = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> Keys = new ArrayList<String>();
		//D:\Spectrum\Spectrum 11.0\Big Data Quality\HiveUDF\IntraFlowMatch\IntraFlow_Name_Data_recordid_byhiveTC1.csv
		// File by spectrum
		
		
		//GenerateMashMap(File1,mMap1, "D:\\Spectrum\\Spectrum 11.0\\Big Data Quality\\IntraFlow\\testdata\\IntraFlow_Name_Data_recordid_byspectrumTC3.csv");
		GenerateMashMap(File1,mMap1, C1);
		                           //D:\Spectrum\Spectrum 11.0\Big Data Quality\HiveUDF\IntraFlowMatch\TestData\ExpressMatchIssueIntraFlowMatch
		//GenerateMashMap(File1,mMap1, "D:\\Spectrum\\Spectrum 11.0\\Big Data Quality\\HiveUDF\\IntraFlowMatch\\000000_0");
		//D:\Spectrum\Spectrum 11.0\Big Data Quality\HiveUDF\IntraFlowMatch\IntraFlow_Name_Data_recordid_bybdqTC1.csv
		// File by BDQ
		//GenerateMashMap(File2,mMap2, "D:\\Spectrum\\Spectrum 11.0\\Big Data Quality\\HiveUDF\\IntraFlowMatch\\IntraFlow_Name_Data_recordid_bybdqTC1.csv");
		//GenerateMashMap(File2,mMap2, "D:\\Spectrum\\Spectrum 11.0\\Big Data Quality\\IntraFlow\\testdata\\IntraFlow_Name_Data_recordid_bybdqTC3a.csv");
		GenerateMashMap(File2,mMap2, C2);
		
		CompareHashMap(File1, File2,Keys);
		
		RemoveDuplicateFromArrayList(Keys);
	
		
		CollectionNumberVerification(mMap1,mMap2,Keys);
		
	 if (!WetherErrorOrNot)
	 {
	   		System.out.println("Both files matched");
	 }
	 else
	 {
	   System.out.println("Both files did not matched review logs");
	 
	 }
		

	}

	public static void CollectionNumberVerification(HashMap<String, ArrayList<String>> smMap1,HashMap<String, ArrayList<String>> smMap2,ArrayList<String> sKeys)
	
	{
	for (String temp : sKeys) {
			
			
			String arr[] = temp.split(";"); 
			
			//Collections.sort(listofcountries);
						
			ArrayList<String> value1 = smMap1.get(arr[0]);
			ArrayList<String> value2 = smMap2.get(arr[1]);
			
			Collections.sort(value1);
			
			Collections.sort(value2);
			
			
		
			if (!value1.equals(value2))
			{
			  WetherErrorOrNot=true;
				System.out.println(value1);
				System.out.println(value2);
				System.out.println( arr[0] + " unqueal " + arr[1]);
			}
			
			
					
		}
		
		
		
	}
	
	public static void RemoveDuplicateFromArrayList (ArrayList<String> Keps)
	{
		
		Object[] st = Keps.toArray();
	      for (Object s : st) {
	        if (Keps.indexOf(s) != Keps.lastIndexOf(s)) {
	        	Keps.remove(Keps.lastIndexOf(s));
	         }
	      }
	}
	
	public static void GenerateCollectionHashMap( HashMap<String, ArrayList<String>> ObjeName1,String FilePath) throws NumberFormatException, IOException 
	{

		try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) 
		{
			String line; String delims = ","; 
			
			while ((line = br.readLine()) != null) {
				String arr[] = line.split(delims); 
				if (ObjeName1.containsKey(arr[2]))
				{
					ObjeName1.get(arr[2]).add(arr[4]);
				}
				else
				{
					ObjeName1.put(arr[2], new ArrayList<String>());
				}
						
			//	ArrKeys.add(arr[0]+ ";"+ arr[2]);
				
				
			}

		}

	}
	
	
	public static void GenerateMashMap(HashMap<Integer, PersonalNameElements> ObjeName,HashMap<String, ArrayList<String>> mmMap1 ,String FilePath) throws NumberFormatException, IOException 
	{

		try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) 
		{
			String line; String delims = ","; 
			
			while ((line = br.readLine()) != null) {
				String arr[] = line.split(delims); 
				PersonalNameElements A = new PersonalNameElements(arr[0],arr[1], arr[2],arr[3], arr[4],arr[5],arr[6],arr[7],arr[8]);
				ObjeName.put(Integer.valueOf(arr[9]), A);
				
				if (mmMap1.containsKey(arr[2]))
				{
					mmMap1.get(arr[2]).add(arr[4]);
				}
				else
				{
					mmMap1.put(arr[2], new ArrayList<String>());
					mmMap1.get(arr[2]).add(arr[4]);
				}
				
				
				
			}
				
			//Collections.sort(mmMap1);
		}

	}
	
	private static void CompareHashMap(	HashMap<Integer, PersonalNameElements> ObjeName1, HashMap<Integer, PersonalNameElements> ObjeName2,ArrayList<String> Keyss)
	{
		
	  
	  HashMap<String, String> MatchRecordTypeValidValues = new HashMap<String, String>();
	  MatchRecordTypeValidValues.put( "U", "Unique");
	  MatchRecordTypeValidValues.put( "D", "Duplicate");
	  MatchRecordTypeValidValues.put("S", "Suspect" );
	  
	  HashMap<String, String> ExpressTypeValidValues = new HashMap<String, String>();
	  ExpressTypeValidValues.put( "Y", "Yes");
    
	  
		Iterator<Entry<Integer, PersonalNameElements>> it = ObjeName1.entrySet().iterator();
	
		
		while (it.hasNext())
		{
			
			
			Map.Entry<Integer, PersonalNameElements> pair = (Map.Entry<Integer, PersonalNameElements>)it.next();
			PersonalNameElements var = ObjeName1.get(pair.getKey());

			if (ObjeName2.containsKey(pair.getKey()))

			{

				PersonalNameElements var2 = ObjeName2.get(pair.getKey());
			
				Keyss.add(var.getCollectionNumber()+";"+var2.getCollectionNumber());
				
				//System.out.println(var.getCollectionNumber()+";"+var2.getCollectionNumber());
			//	break;
				
				if (!var.getScore().equals(var2.getScore())) {
					if (var2.getMatchRecordType().equals("U") || var2.getMatchRecordType().equals("S"))
					{	
						if (!var2.getScore().equals("0"))
						{
						  WetherErrorOrNot=true;
						 System.out.println(pair.getKey()
							+ " of File 1 record is Suspect or unique but its Match score is not zero");
						}
						
					}
					else 
					{
					  WetherErrorOrNot=true;
						System.out.println(pair.getKey()
								+ " of File 1 Match score field does not match");
						
					}
										
				}
				
				if (!var.getName().equals(var2.getName())) {
				  WetherErrorOrNot=true;
				  System.out.println(pair.getKey()
							+ " of File 1 Name type does not match");
				}
				
				if (!var.getFirstName().equals(var2.getFirstName())) {
				  WetherErrorOrNot=true;
				  System.out.println(pair.getKey()
              + " of File 1 First Name type does not match");
        }
				
				if (!var.getFirstName().equals(var2.getFirstName())) {
				  WetherErrorOrNot=true;
				  System.out.println(pair.getKey()
							+ " of File 1 Middle name  does not match");
				}
				
				
				if (!var.getLastName().equals(var2.getLastName())) {
				  WetherErrorOrNot=true;
				  System.out.println(pair.getKey()
							+ " of File 1 LastName type does not match");
				}
				
			
				
				if (! var.getMatchRecordType().equals(MatchRecordTypeValidValues.get(var2.getMatchRecordType()))) {
				  WetherErrorOrNot=true;
				  System.out.println(pair.getKey()
							+ " of File 1 Match record type does not match");
				}
				
       if (!var.getExpressMatchIdentified().equals(var2.getExpressMatchIdentified())) {
          
          if (! var.getExpressMatchIdentified().equals(ExpressTypeValidValues.get(var2.getExpressMatchIdentified()))) {
            WetherErrorOrNot=true;
            System.out.println(pair.getKey()
                + " of File 1 Match Express key does not match");
          }
          
        }
				
				if (!var.getMatchKey().equals(var2.getMatchKey())) {
				  WetherErrorOrNot=true;
				  System.out.println(pair.getKey()
							+ " of File 1 matchkey field does not match");
				}
				

			} else {
			  WetherErrorOrNot=true;
			  System.out.println(pair.getKey()
						+ " of File 1 does not exists in file 2");

			}
				
		}
		
		//System.out.println(Keyss);
		
		Iterator<Entry<Integer, PersonalNameElements>> it2 = ObjeName2.entrySet().iterator();
		while (it2.hasNext()) {
			Map.Entry<Integer, PersonalNameElements> pair2 = (Map.Entry<Integer, PersonalNameElements>)it2.next();
			if (!ObjeName1.containsKey(pair2.getKey())) {
				System.out.println(pair2.getKey()
						+ " of File 2 does not exists in file 1");
			}
		}

	}	
}
	
