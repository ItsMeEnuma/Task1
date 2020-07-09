package com.company;

import java.util.ArrayList;


import java.util.Stack;

public class MyBig {

    private ArrayList<Integer> data = new ArrayList<>();

    public MyBig(String str) {
        for(int i = 0; i < str.length(); i++){
            int c = (int)str.charAt(i) - 48;
            data.add(c);
        }
    }

    public MyBig(){
        data.add(0);
    }

    private MyBig(ArrayList<Integer> data){
        this.data = (ArrayList<Integer>) data.clone();
    }

    public void print() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            result.append(data.get(i));
        }
        System.out.println(result.toString());
    }

    public int getNum(int index){
        if(index < 0 || index >= data.size())
            throw new Error();

        return data.get(index);
    }

    public void normalize(){
        while(data.size() > 1 && data.get(0) == 0){
            data.remove(0);
        }
    }

    public void add(MyBig second){
        MyBig a = this.clone();
        MyBig b = second.clone();

        if(MyBig.compare(a, b) == -1){
            MyBig tmp = a;
            a = b;
            b = tmp;
        }

        Stack<Integer> stack = new Stack<>();

        int ost = 0;

        for(int i = a.size() - 1; i >= a.size() - b.size(); i--){
            int res = (a.getNum(i) + b.getNum(i - a.size() + b.size()) + ost) % 10;
            ost = a.getNum(i) + b.getNum(i - a.size() + b.size()) + ost >= 10 ? 1 : 0;
            stack.add(res);
        }

        for(int i = a.size() - b.size() - 1; i >= 0; i--){
            int res = (a.getNum(i) + ost) % 10;
            ost = a.getNum(i) + ost >= 10 ? 1 : 0;
            stack.add(res);
        }

        if(ost > 0) stack.add(1);

        ArrayList<Integer> result = new ArrayList<>();
        while(!stack.isEmpty())
            result.add(stack.pop());

        data = result;
    }

    public static MyBig add(MyBig first, MyBig second) {
        MyBig a = first.clone();
        MyBig b = second.clone();
        a.add(b);
        return a;
    }

    public void subtract(MyBig second) {
        MyBig a = this.clone();
        MyBig b = second.clone();

        if (MyBig.compare(a, b) == -1) {
            throw new Error();
        }

        Stack<Integer> stack = new Stack<>();

        boolean flag = false;

        for (int i = a.size() - 1; i >= a.size() - b.size(); i--) {
            int res = a.getNum(i) - b.getNum(i - a.size() + b.size());
            if (flag) {
                if (res > 0) {
                    flag = false;
                    res--;
                } else {
                    res += 9;
                }
            } else {
                if (res < 0) {
                    flag = true;
                    res += 10;
                }
            }
            stack.add(res);
        }

        for (int i = a.size() - b.size() - 1; i >= 0; i--) {
            int res = a.getNum(i);
            if (flag) {
                if (res > 0) {
                    flag = false;
                    res--;
                } else {
                    res += 9;
                }
            } else {
                if (res < 0) {
                    flag = true;
                    res += 10;
                }
            }
            stack.add(res);
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while(!stack.isEmpty())
            nums.add(stack.pop());

        data = nums;
        this.normalize();
    }

    public static MyBig subtract(MyBig first, MyBig second) {
        MyBig a = first.clone();
        MyBig b = second.clone();
        a.subtract(b);
        return a;
    }

    public int size(){
        return data.size();
    }

    public void multiply(MyBig second){
        MyBig a = this.clone();
        MyBig b = second.clone();
        if(b.isZero()) {
            data.clear();
            data.add(0);
            return;
        }

        for(int i = 0; i < b.toInt() - 1; i++){
            this.add(a);
        }
    }

    public static MyBig multiply(MyBig first, MyBig second) {
        MyBig a = first.clone();
        MyBig b = second.clone();
        a.multiply(b);
        return a;
    }

    public int compare(MyBig a) {
        if (this.size() > a.size()) {
            return 1;
        }
        if (this.size() < a.size()) {
            return -1;
        }

        for (int i = 0; i < this.size(); i++) {
            if(this.getNum(i) > a.getNum(i)){
                return 1;
            }
            if (this.size() < a.size()) {
                return -1;
            }
        }
        return 0;
    }

    public static int compare(MyBig a, MyBig b) {
        // 1 a > b, -1 b > a, 0 a = b
        if (a.size() > b.size()) {
            return 1;
        }
        if (a.size() < b.size()) {
            return -1;
        }

        for (int i = 0; i < a.size(); i++) {
            if(a.getNum(i) > b.getNum(i)){
                return 1;
            }
            if (a.size() < b.size()) {
                return -1;
            }
        }
        return 0;
    }

    public int toInt(){
        int res = 0;
        int d = 1;
        for(int i = size() - 1; i >= 0; i--){
            res += data.get(i) * d;
            d *= 10;
        }
        return res;
    }

    public boolean isZero(){
        return data.size() == 1 && data.get(0) == 0;
    }

    public MyBig clone(){
        return new MyBig(data);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(Integer a : data){
            builder.append(a);
        }
        return builder.toString();
    }
}
