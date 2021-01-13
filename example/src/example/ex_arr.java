package example;

class animal {
	String name;
	int age;
	
	public animal() {
		
	}
	public animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
}

class Dog extends animal{
	public Dog(String name, int age) {
		super(name, age);
	}
	public void sound() {
		System.out.println("dd");
	}
	public void disp() {
		System.out.println(name+":"+age);
	}
}


public class ex_arr {

	public static void main(String[] args){
		Dog d1 = new Dog("aa",10);
		d1.sound();
		d1.disp();
		
		
		
	}



}

