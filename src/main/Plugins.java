package main;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import interfaces.PizzaComponent;

public class Plugins {
	private String []plugins;
	private File currentDir;
	private URL[] jars;
	private URLClassLoader ulc;
	
	public void LoadingPlugins() {
		currentDir = new File("./src/plugins");
		plugins = currentDir.list();
  	}
	
	public void executeDecorator(List<String> decorators) throws MalformedURLException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		
		jars = new URL[plugins.length];
		
		for (int i = 0; i < plugins.length; i++)
		{ 
		  jars[i] = (new File("./src/plugins" + plugins[i])).toURL(); 
		}

		ulc = new URLClassLoader(jars);
		
		PizzaComponent[] pizzaComponents = new PizzaComponent[decorators.size()];
		
		pizzaComponents[decorators.size() - 1] = getInstance(decorators.get(decorators.size() - 1), ulc, new PizzaBasica());
		for(int i = decorators.size() - 2; i >= 0; i--) {
			pizzaComponents[i] = getInstance(decorators.get(i), ulc, pizzaComponents[i+1]);
		}
		
		pizzaComponents[0].preparar();
	}
	
	public String[] getPlugins() {
		return this.plugins;
	}

	private PizzaComponent getInstance(String decoratorName, URLClassLoader ulc, PizzaComponent pizzaInstance) throws InstantiationException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		
		return (PizzaComponent) Class.forName("decorators" + "." + decoratorName.split("\\.")[0], true, ulc).getDeclaredConstructor(PizzaComponent.class).newInstance(pizzaInstance);
	}

}
