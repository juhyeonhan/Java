package example;

class Phone{
	String name;
	String no;
	String add;
	
	public Phone(String n, String num ) {
		this.name = n;
		this.no = num;
		add=this.name+this.no;
	}
	public void disp() {
		System.out.println(this.add);
	}
	
	
	
	
}
	


public class ex_class {

	public static void main(String[] args) {
		Phone p = new Phone("gk", "owk");
		p.disp();

	}

}
