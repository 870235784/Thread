package com.tca.thread.static_;

class Student {
	static int age = 16;
	static String gender = "male";
	
	static {
		System.out.println("Student Class initializing...");
	}
}

public class StaticDemo {
	
	/*
	 * 访问Student.class.hashcode()，此时只完成了Student类的加载，准备，但是初始化并没有完成—
	 * 	即此时没有给Student的静态成员变量进行初始化，此时静态成员变量还是零值，static代码块也没有执行
	 * 
	 * 当访问Student某个静态成员变量(age)时，此时才会对Student类做初始化
	 * 
	 * 因此该例中，访问Student.class.hashcode()时，Student的static代码块并没有执行！而是在
	 * 访问Student.age时才执行
	 */
	public static void main(String[] args) {
		System.out.println(Student.class.hashCode());
		System.out.println(Student.age);
		System.out.println(Student.gender);
	}
}
