
public class Game {
	
	int pos;
	String input;
	int len;
	int setScore[] = new int[2];
	int gameScore[] = new int[2];
	int points[] = new int[2];
	
	public Game(String input) {
		this.input = input;
		pos = 0;
		len = input.length();
	}
	
	public boolean checkDeuce() {
		return (points[0]==3 && points[0] == points[1]); 
	}
	
	public void handleDeuce() {
		int aIndex = input.indexOf("AA",pos+1);
		int bIndex = input.indexOf("BB",pos+1);
		if(aIndex>0){
			if(bIndex>0 && aIndex<bIndex) {
				gameScore[0] += 1;
				pos = aIndex+1;
				resetPoints();
			}
			else if(bIndex < 0) {
				gameScore[0] += 1;
				pos = aIndex+1;
				resetPoints();
			}
		}
		else if(bIndex>0){
			if(aIndex>0 && bIndex<aIndex) {
				gameScore[1] += 1;
				pos = bIndex+1;
				resetPoints();
			}
			else if(aIndex < 0) {
				gameScore[0] += 1;
				pos = bIndex+1;
				resetPoints();
			}
		}
	}
	
	public void updatePoints(char player) {
		if(player == 'A')
			points[0] += 1;
		else
			points[1] += 1;
		
		if (checkDeuce()) {
			handleDeuce();
		}
		
		else {
			
			int status = isGameWon();
			if(status == 0 || status == 1) {
				updateGameScore(status);
				resetPoints();
			}	
		}
	}

	private void updateGameScore(int status) {
		gameScore[status] += 1;
		int status1 = isSetWon();
		if(status1 == 0 || status1 == 1) {
			updateSetScore(status1);
			resetGameScore();
		}
	}

	public int isSetWon(){
        if(((setScore[0] >= 6) && (Math.abs(setScore[0] - setScore[1]) >= 2)) || ((setScore[1] >= 6) && (Math.abs(setScore[0] - setScore[1]) >= 2))){
            if(setScore[0] > setScore[1]){return 0;}
            else{return 1;}
        }
        else{return -1;}
        
    }

	private void resetGameScore() {
		gameScore[0] = 0;
		gameScore[1] = 0;
	}

	private void updateSetScore(int status) {
		setScore[status] += 1;
	}

	private int isGameWon(){
        boolean flag = false;
        if((points[0] == 4) || (points[1] == 4)){
            if(Math.abs(points[0] - points[1]) >= 2){
                flag = true;
            }
        }
        if(flag == false){return -1;}
        else{
            if(points[0] > points[1]){return 0;}
            else{return 1;}
        }
    }

	private void resetPoints() {
		points[0] = 0;
		points[1] = 0;
	}
	
	public static void main(String args[]) {
		Game obj = new Game("ABABABAAB");
		while(obj.pos < obj.len) {
			obj.updatePoints(obj.input.charAt(obj.pos));
			obj.pos++;
		}
		int util[] = {0,15,30,40,50};
		
		System.out.println("player: A B");
		System.out.println("sets: "+obj.setScore[0]+" "+obj.setScore[1]);
		System.out.println("gamess: "+obj.gameScore[0]+" "+obj.gameScore[1]);
		System.out.println("points: "+util[obj.points[0]]+" "+util[obj.points[1]]);		
	}
}
