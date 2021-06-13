package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

public class CalabresaDecorator extends PizzaDecorator {

	public CalabresaDecorator(PizzaComponent decorated) {
		this.decorated = decorated;
	}
	
	@Override
	public void preparar() {
		decorated.preparar();
		System.out.println("Adicionando a Calabresa");
	}

}
