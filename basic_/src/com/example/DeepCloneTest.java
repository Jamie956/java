package com.example;

import java.io.*;

public class DeepCloneTest {
    public static void main(String[] args) {
        try {
            User user = new User();
            user.setAddress(new Address("stress1"));
            User clone = (User) user.deepClone();

            System.out.println(user == clone);
            System.out.println(user.getAddress() == clone.getAddress());//深克隆克隆引用类型

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class User implements Serializable {
    private static final long serialVersionUID = -3307269962764425802L;
    public Address address;

    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        return ois.readObject();
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}

class Address implements Serializable {
    private static final long serialVersionUID = -4537716904357183030L;
    public String stress;
    public Address(String stress) {
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