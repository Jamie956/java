package com.example.abstractfactory;

public class AKFactory implements ArmFactory {
	@Override
	public Gun produceGun() {
		return new AKGun();
	}

	@Override
	public Bullet produceBullet() {
		return new AKBullet();
	}
}
