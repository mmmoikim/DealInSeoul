package domain;

public class Lottery {
	
	public int issueLottery(){
		int money = -1;
		
		double rand;
		
	for(int i=0; i < 14; i++)
		{
		rand = Math.random()*139;
		if(rand >=0 && rand < 20)
		{
			money = 800;
		}
		else if(rand >=20 && rand < 50){
			money = 1500;
		}else if(rand >=50 && rand < 100){
			money = 3500;
		}else if(rand >=100 && rand < 120){
			money = 5000;
		}else if(rand >=120 && rand < 135){
			money = 7000;
		}else if(rand >=135 && rand < 138){
			money = 10000;
		}else if(rand >=138 && rand < 139){
			money = 20000;
		}
	}
		
		return money;
	}

}
