/*
 * @author: Suyash Kumar
 * http://suyashkumar.com
 * TAL RVD Verification
 * 
 * A quick/dirty class that handles all necessary Tal Verification tasks. 
 * The class handles RVD searching (looks for the next two amino acids after the VAIAS amino acid sequence declared below). 
 * Note that the VAIAS sequence is currently codon-specific to most TALE kits. So it potentially is not optimized for your codon system. I'll add this soon
 * Enjoy! 
 * 
 * Released under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License
 * http://creativecommons.org/licenses/by-nc-sa/4.0/deed.en_US
 */
package TalVerification;
import java.util.*;
import javax.swing.JOptionPane;
public class TalRVDVerification {
	
	private String TalString; 
	private ArrayList<String> RVD; //This ArrayList holds the current sequence of RVDs
	private final String VAIAS="GTGGCTATCGCCAGC"; //This is the DNA sequence for the VAIAS amino acids 
	private int rcIndex=0;
	private ArrayList<String> revComp;
	
	public TalRVDVerification(){
		
		RVD=new ArrayList<String>(); //Initializes our RVD-holding ArrayList 
		revComp=new ArrayList<String>(); 
		
	}
	/*
	 * Simply returns the RVD sequence of a TAL based on a sequenced DNA input (plus strand only right now). 
	 * 
	 * @param DNA the input DNA string of the sequenced TAL
	 * @return the RVD sequence as a string directly from the RVD ArrayList
	 */
	public String resolveDNA (String DNA, boolean revComp){
		String RVDSeq=""; //Will hold RVD sequence to return
		int index0=0; //Start VAIAS search from this index
		int index1=0;
		String nextRVD=""; //Temporarily holds the next RVD to be added to the sequence
		DNA=DNA.replace(" ", ""); //Remove all spaces from input DNA sequence 
		
		if (revComp){
			DNA=reverseComplement(DNA);
		}
		
		while (index0 != -1){
			
			index1=DNA.indexOf(VAIAS,index0); //Find occurrence of VAIAS starting from index0
			
			nextRVD=DNAToRVD(DNA.substring(index1+VAIAS.length(),index1+VAIAS.length()+10)); //Resolve Next RVD
			
			// If no RVD match was found after a VAIAS repeat, possibly a dirty read, so flag this/notify user
			if (nextRVD.equals("00")){
				System.out.println("Note: Unrecognized or no RVD after a VAIAS. Check Chromotogram. At Index: " + (index1+VAIAS.length()) );
				JOptionPane.showMessageDialog(null,"Note: Potentially messy RVD after a VAIAS. Check Chromotogram. Near Base: " + index1 );
				System.out.println(DNA.substring(index1+VAIAS.length(),index1+VAIAS.length()+10));
				break;
			}
			else {
				RVD.add(nextRVD); //Add new RVD to RVD sequence. 
				index0=index1+4; //Set new search Index. Plus 4 from previous to be conservative. 
				if (DNA.indexOf(VAIAS,index0)==-1){
					index0=-1; //Exit case
					break; //Exit
				}
			}
			
			
		}
		//RVDSeq=RVDOutput(RVD);
		RVDSeq=RVD.toString();
		RVD.clear();		
		
		return RVDSeq;
		
	}
	/*
	 * Takes an ArrayList holding RVDs and outputs them into a nice string.
	 * 
	 * @param ArrayList holding RVDs
	 * @return the RVD sequence based on formatting below
	 */
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
	/*
	 * Converts a set of two codons into the corresponding amino acids that characterize a RVD.  
	 * 
	 * @param DNA Sequence (at least 6 bases. Don't send in enough bases to potentially have two RVDs in the mix--that'd be bad)
	 * @return the corresponding RVD found in the input DNA sequence
	 */
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
	public String reverseComplement(String DNA){
		String output="";
		DNA.toUpperCase();
		
		  for(int i=0;i<DNA.length();i++){
			String current=DNA.substring(i,i+1);
			
			if (current.equals("A")){
				output=output+"T";
			}
			else if(current.equals("T")){
				output=output+"A";
			}
			else if(current.equals("C")){
				output=output+"G";
			}
			else if(current.equals("G")){
				output=output+"C";
				
			}
		  }
			
			return new StringBuilder(output).reverse().toString();
	}
	
}
