package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

public class AtumDecorator extends PizzaDecorator{
	
	public AtumDecorator(PizzaComponent decorated) {
		this.decorated = decorated;
	}
	
	@Override
	public void preparar() {
		decorated.preparar();
		System.out.println("Adicionando o Atum");
	}

}
