package com.company;

public class Main {

    public static void main(String[] args) {
	System.out.println(Pesel.check("96041511876"));
	System.out.println(Pesel.check("12343245433"));
	System.out.println(Pesel.check("123at245e43"));
	System.out.println(Pesel.check("123AD4sr543"));
	System.out.println(Pesel.check("123AD4sr543dsa"));
    }
}
