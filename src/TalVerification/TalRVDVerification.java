package TalVerification;
import java.util.*;
import javax.swing.JOptionPane;
public class TalRVDVerification {
	
	private String TalString;
	private ArrayList<String> RVD;
	private final String VAIAS="GTGGCTATCGCCAGC";
	
	public TalRVDVerification(){
		
		RVD=new ArrayList<String>();
		
	}

	public String resolveDNA (String DNA){
		String RVDSeq="";
		int index0=0;
		int index1=0;
		String nextRVD="";
		DNA=DNA.replace(" ", "");
		
		while (index0 != -1){
			
			index1=DNA.indexOf(VAIAS,index0); //Find occurrence of VAIAS starting from index0
			
			nextRVD=DNAToRVD(DNA.substring(index1+VAIAS.length(),index1+VAIAS.length()+10)); //Resolve Next RVD
			
			if (nextRVD.equals("00")){
				System.out.println("Note: Unrecognized or no RVD after a VAIAS. Check Chromotogram. At Index: " + (index1+VAIAS.length()) );
				JOptionPane.showMessageDialog(null,"Note: Potentially messy RVD after a VAIAS. Check Chromotogram. Near Base: " + index1 );
				System.out.println(DNA.substring(index1+VAIAS.length(),index1+VAIAS.length()+10));
				break;
			}
			else {
				RVD.add(nextRVD); //Add new RVD to RVD sequence. 
				index0=index1+4; //Set new search Index. Plus 4 to be conservative. 
				if (DNA.indexOf(VAIAS,index0)==-1){
					index0=-1; //Exit
					break; //Exit
				}
			}
			
			
		}
		//RVDSeq=RVDOutput(RVD);
		RVDSeq=RVD.toString();
		RVD.clear();		
		
		return RVDSeq;
		
	}
	
	public String RVDOutput(ArrayList<String> RVD){
		
		String output="";
		
		for (int i=0;i<RVD.size();i++){
			if (i<RVD.size()-1){
				output=output+""+(i+1)+": "+RVD.get(i)+"  ";
			}
			else {
				output=output+""+(i+1)+": "+RVD.get(i)+" ";
			}
			
			
		}
		
		
		return output;
		
	}
	
	public String DNAToRVD(String DNA){
		
		if(DNA.contains("CACGAT")){
			System.out.println("HD");
			return "HD";
			
		}
		else if(DNA.contains("AACATT")){
			return "NI";
		}
		else if(DNA.contains("AACAAT")){
			return "NN";
		}
		else if(DNA.contains("AACGGT")){
			return "NG";
		}
		else {
			return "00"; //No match found
		}
		
		
	}
	
}
