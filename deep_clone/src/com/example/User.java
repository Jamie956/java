package com.example;

import java.io.*;

public class User implements Serializable {

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
