package com.example;

public class ShallowClone {
    public static void main(String[] args) {
        try {
            User1 user = new User1();
            user.setAddress(new Address1("stress1"));
            User1 clone = (User1) user.clone();

            System.out.println(user == clone);
            System.out.println(user.getAddress() == clone.getAddress());//浅克隆不克隆引用类型

        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class Address1 {
    public String stress;

    public Address1(String stress) {
        super();
        // TODO Auto-generated constructor stub
        this.stress = stress;
    }

    public String getStress() {
        return stress;
    }

    public void setStress(String stress) {
        this.stress = stress;
    }

}

class User1 implements Cloneable {

    public Address1 address;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    public Address1 getAddress() {
        return address;
    }

    public void setAddress(Address1 address) {
        this.address = address;
    }


}