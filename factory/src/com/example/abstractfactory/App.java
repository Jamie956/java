package com.example.abstractfactory;

public class App {
	public static void main(String[] args) {
		ArmFactory ａkFactory = new AKFactory();
		
		Bullet bullet = ａkFactory.produceBullet();
		bullet.load();
		
		Gun gun = ａkFactory.produceGun();
		gun.shooting();
	}
}
