package experiment;
//create an interface
interface BatterypoweredDevice {
	int CheckBattery();
}
class SmartSecurityCamera implements BatterypoweredDevice{
	String CamID;
	int battery;
	
	//created a constructor
	public SmartSecurityCamera(String ID,int battery) {
		this.CamID=ID;
		this.battery=battery;
	}
	
	public int CheckBattery() {
		return battery;
	}
	
	void chargeBattery() {
		battery=100;
		System.out.println("the battery is charged 100%");
		
	}
	
	void UseBattery(int amount) {
		battery=battery-amount;
		System.out.println("The Amount Of battery used now="+amount+"%");
		System.out.println("Current Battery="+battery+"%");
	}
	
	void display() {
		System.out.println("Camera ID:"+CamID);
		System.out.println("Battery Level:"+battery+"%");
	}
}


public class SecurityCamera{
	public static void main(String[] args) {
		SmartSecurityCamera c=new SmartSecurityCamera("CAM123",80);
		c.display();
		
		System.out.println();
		System.out.println("Current Battery Level:"+c.CheckBattery()+"%");
		System.out.println();
		c.UseBattery(20);
		System.out.println();
		c.display();
		System.out.println();
		c.chargeBattery();
		System.out.println();
		c.UseBattery(50);
		SmartSecurityCamera c1=new SmartSecurityCamera("CAM789",40);
		System.out.println();
		System.out.println("Current Battery Level:"+c.CheckBattery()+"%");
		System.out.println();
		c.UseBattery(20);
		System.out.println();
		c.display();
		System.out.println();
		c.chargeBattery();
		System.out.println();
		c.UseBattery(50);
		}
}