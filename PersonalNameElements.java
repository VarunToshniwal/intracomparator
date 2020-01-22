package com.pb.test.intraflowmatch;

public class PersonalNameElements {

	private String MatchRecordType;
	private String Score;
	private String CollectionNumber;
	private String ExpressMatchIdentified;
	private String Name;
	private String FirstName;
	private String LastName;
	private String MatchKey;
	private String MiddleName;

	PersonalNameElements(String MatchRecordType,String Score, String CollectionNumber,String ExpressMatchIdentified,String Name,String FirstName ,String LastName,String MatchKey,String MiddleName)
	{
		
		this.Score=Score;
		this.MatchRecordType=MatchRecordType;
		this.CollectionNumber=CollectionNumber;
		this.ExpressMatchIdentified=ExpressMatchIdentified;
		this.MatchKey=MatchKey;
		this.Name=Name;
		this.FirstName=FirstName;
		this.MiddleName=MiddleName;
		this.LastName=LastName;
		
	}
	
	
	public String getName() {
		return Name;
	}

	 public String getFirstName() {
	    return FirstName;
	  }

	  public String getMiddleName() {
	    return MiddleName;
	  }

	  public String getLastName() {
	    return LastName;
	  }

	
	public String getScore() {
		return Score;
	}

	public String getMatchRecordType() {
		return MatchRecordType;
	}

	public String getCollectionNumber() {
		return CollectionNumber;
	}

	public String getMatchKey() {
		return MatchKey;
	}

	public String getExpressMatchIdentified() {
		return ExpressMatchIdentified;
	}
	

}
