import java.util.*;

public class TennisGame {
	Player A=new Player('A', 0,0,0, 0);
	Player B=new Player('B',0,0,0, 0);

	public boolean tieBreaker = false;
	
	public void calculateScore(String s)
	{
		char ch[]=s.toCharArray();
	   	for(int i=0;i<ch.length;i++)
	    {
			if(tieBreaker == false){
				if(ch[i]=='A')
				{
					A.incrementPoints();

				}
				else{
					B.incrementPoints();

				}
				if(A.getPoints()>B.getPoints() && A.getPoints()>3 && Math.abs(A.getPoints()-B.getPoints())>=2)
				{
					A.setPoints(0);B.setPoints(0);
					A.incrementGames();
				}
				if(A.getPoints()<B.getPoints() && B.getPoints()>3 && Math.abs(A.getPoints()-B.getPoints())>=2)
				{
					A.setPoints(0);B.setPoints(0);
					B.incrementGames();
				}
				if(A.getGame()>B.getGame() && A.getGame()>=6 && Math.abs(A.getGame()-B.getGame())>=2)
				{
					A.setGame(0);B.setGame(0);
					A.incrementSets();
				}
				if(A.getGame()<B.getGame() && B.getGame()>=6 && Math.abs(A.getGame()-B.getGame())>=2)
				{
					A.setGame(0);B.setGame(0);
					B.incrementSets();
				}
				if(A.getGame()==B.getGame() && A.getGame()==6) {
					tieBreaker = true;

				}
			}
			else{
				if(ch[i] == 'A') {
					A.incrementTiePoints();
				}else {
					B.incrementTiePoints();
				}
				if(A.getTiePoints()>B.getTiePoints() && A.getTiePoints()>=6 && Math.abs(A.getTiePoints()-B.getTiePoints())>=2){
					A.setGame(0);B.setGame(0);
					A.incrementSets();
					tieBreaker = false;
					A.setTiePoints(0);
					B.setTiePoints(0);
				}
				else if(A.getTiePoints()<B.getTiePoints() && B.getTiePoints()>=6 && Math.abs(A.getTiePoints()-B.getTiePoints())>=2){
					A.setGame(0);B.setGame(0);
					B.incrementSets();
					tieBreaker = false;
					A.setTiePoints(0);
					B.setTiePoints(0);
				}
			}
	   	}

	   if((A.getPoints()>=3 && Math.abs(A.getPoints() - B.getPoints()) < 2) || (B.getPoints()>=3 && Math.abs(A.getPoints() - B.getPoints()) < 2)){
	   		if(A.getPoints() > B.getPoints()){A.setPoints(45); B.setPoints(40);}
	   		else if(A.getPoints() < B.getPoints()){B.setPoints(45); A.setPoints(40);}
	   		else if(A.getPoints() == B.getPoints()){B.setPoints(40); A.setPoints(40);}
	   }
	   
	   if(A.getPoints()==2)
		   A.setPoints(30);
	   if(A.getPoints()==1)
		   A.setPoints(15);
	   if(B.getPoints()==2)
		   B.setPoints(30);
	   if(B.getPoints()==1)
		   B.setPoints(15);
	}

	public static void main(String args[])
	{
		TennisGame tg=new TennisGame();
		tg.calculateScore("AAAABBBBAAAABBBBAAAABBBBAAAABBBBAABB");
		//tg.calculateScore("AAAABBBBAAAABBBBAAAABBBBAAAABBBBAAAABBBBAAAABBBBABABABABABABABBB");  //TieBreaker
		System.out.println("Players: "+ "A" +" "+"B");
		System.out.println("Sets:    "+tg.A.getSet()+" "+tg.B.getSet() );
		System.out.println("Game:    "+tg.A.getGame()+" "+tg.B.getGame() );
		System.out.println("Points: "+tg.A.getPoints()+" "+tg.B.getPoints() );
		if(tg.tieBreaker == true)
				System.out.println("TiePoints: "+tg.A.getTiePoints()+" "+tg.B.getTiePoints() );
	}
}
