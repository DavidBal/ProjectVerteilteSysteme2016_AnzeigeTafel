import java.io.*;
import java.util.*;

public class database {
	public static void main(String[] args)throws IOException{

		Scanner scanUser=new Scanner(System.in);
		Scanner scanPw=new Scanner(System.in);
		//ArrayList<String> list = new 

		String zeile="";
		String user="";
		String pw="";
		String eingabe="";
		boolean erfolgreich=false;
		boolean treffer=false;
		
		do{
			
			FileReader fr = new FileReader("user.txt");
			BufferedReader br = new BufferedReader(fr);
		
			System.out.println("username:");
			user=scanUser.nextLine();

			System.out.println("Passwort:");
			pw=scanPw.nextLine();

			eingabe=user+"//"+pw;
			zeile = br.readLine();

			do{
				zeile = br.readLine();
				if(zeile==null){
					break;
				
					
						
				}
				if(zeile.equals(eingabe)){
					treffer=true;
					System.out.println("login erfolgreich\n");
				}				
				
				if(zeile==null)
					break;

			}while(zeile!=null&&!treffer);
			
			if(!treffer){
				System.out.println("login fehlgeschlagen\n");
				//br.close;
			}
			
		}while(!treffer);

		erfolgreich=true;
		
		//br.close();
		scanUser.close();
		scanPw.close();
	
		//return erfolgreich;
	}
}
