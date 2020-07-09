package com.company;

public class Main {

    public static void main(String[] args) {
        MyBig a = new MyBig("123123123123123123");
        MyBig b = new MyBig("10757000");
        a.print();
        b.print();
        MyBig c = MyBig.add(a, b);
        MyBig d = MyBig.subtract(a, b);
        MyBig e = MyBig.multiply(a, b);
        System.out.println("+ :" + c.toString());
        System.out.println("- :" + d.toString());
        System.out.println("* :" + e.toString());
    }
}
