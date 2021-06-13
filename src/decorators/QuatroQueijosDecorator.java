package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

public class QuatroQueijosDecorator extends PizzaDecorator {
	
	public QuatroQueijosDecorator(PizzaComponent decorated) {
		this.decorated = decorated;
	}
	
	@Override
	public void preparar() {
		decorated.preparar();
		System.out.println("Adicionando os Quatro queijos");
	}
}
