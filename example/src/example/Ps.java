package example;

class Person{
	
	int age;
	String name;
	String address;
	int height;
	
	public Person(){
		System.out.println("Person");
	}
	
	public Person(int age, String name) {
		this.age=age;
		this.name=name;
	}
	
	void disp() {
		System.out.println(name);
	}
	
}


public class Ps {

	public static void main(String[] args) {
	
		Person p1=new Person();
		p1.name="pp";
		System.out.println(p1.name);
		Person p2=new Person(23, "ppp");
		p2.name="ppppp";
		p2.disp();
	}

}
