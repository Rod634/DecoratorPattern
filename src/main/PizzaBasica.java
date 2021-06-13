package main;

import interfaces.PizzaComponent;

public class PizzaBasica implements PizzaComponent {

	@Override
	public void preparar() {
		System.out.println("Preparando massa + molho + queijo");
	}

}
