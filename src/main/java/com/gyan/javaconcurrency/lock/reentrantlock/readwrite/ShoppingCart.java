package com.gyan.javaconcurrency.lock.reentrantlock.readwrite;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private List<String> products = new ArrayList<>();
	public String getProduct(int i) {
		return products.get(i);
	}
	
	public void addProduct(String name) {
		products.add(name);
	}
}
