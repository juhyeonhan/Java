package example;

class Payment{
	int total;
	int basePay;
	int hour;
	
	public Payment(int basePay, int hour) {
		this.basePay = basePay;
		this.hour = hour;
		total = basePay*hour;
	}
	public void disp() {
		System.out.println(basePay);
		System.out.println(hour);
		System.out.println(total);
	}
}

public class ex_practice {

	public static void main(String[] args) {
		Payment pay = new Payment(8590, 10);
		pay.disp();
	}

}
