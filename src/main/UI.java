package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.SystemColor;


public class UI extends JFrame implements ActionListener{
	Plugins plugin;
	DefaultListModel<String> decorators = new DefaultListModel<String>();
	DefaultListModel<String> SelectedDecorators =new DefaultListModel<String>();
	JList<String> decoratorList;
	JList<String> selectedList;
	JButton insertButton;
	JButton removeButton;
	JButton upButton;
	JButton downButton;
	JButton doneButton;
	JButton refreshButton;
	
	public UI(Plugins plugin) {
		initializeComponents();
		initializePlugins(plugin);
	}

	private void initializePlugins(Plugins plugin) {
		this.plugin = plugin;
		setOptions();
	}

	private void setOptions() {
		this.plugin.LoadingPlugins();
		addRange(this.plugin.getPlugins());
	}

	private void initializeComponents() {
		getContentPane().setLayout(null);
		decoratorList = new JList<String>(decorators);
		decoratorList.setBounds(0, 46, 300, 302);
		getContentPane().add(decoratorList);
			 
		selectedList = new JList<String>(SelectedDecorators);
		selectedList.setBounds(400, 46, 280, 302);
		getContentPane().add(selectedList);
		
		insertButton = new JButton(">");
		insertButton.addActionListener(this);
		insertButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		insertButton.setBounds(300, 112, 100, 25);
		getContentPane().add(insertButton);
		
		removeButton = new JButton("<");
		removeButton.addActionListener(this);
		removeButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		removeButton.setBounds(300, 150, 100, 25);
		getContentPane().add(removeButton);
		
		upButton = new JButton("Up");
		upButton.addActionListener(this);
		upButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		upButton.setBounds(300, 212, 100, 25);
		getContentPane().add(upButton);
		
		downButton = new JButton("Down");
		downButton.addActionListener(this);
		downButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		downButton.setBounds(300, 254, 100, 25);
		getContentPane().add(downButton);
		
		doneButton = new JButton("Make pizza!");
		doneButton.addActionListener(this);
		doneButton.setBackground(SystemColor.activeCaption);
		doneButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		doneButton.setBounds(0, 350, 680, 48);
		getContentPane().add(doneButton);
		
		refreshButton = new JButton("Reload Ingredients");
		refreshButton.addActionListener(this);
		refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		refreshButton.setBackground(SystemColor.activeCaption);
		refreshButton.setBounds(0, -3, 680, 48);
		getContentPane().add(refreshButton);
		
		this.setSize(698, 445);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void reset() {
		SelectedDecorators.removeAllElements();
		System.out.println("-------------------------");
	}
	
	public List<String> getDecorators(){
		List<String> selectedDecoratos = new ArrayList<String>();
		
		for(int i = 0; i <= this.SelectedDecorators.getSize() - 1; i++) {
			selectedDecoratos.add(this.SelectedDecorators.get(i) + "Decorator");
		}
		return selectedDecoratos;
	}
	
	public void addRange(String[] pluginNames) {
		for (String plugin : pluginNames) {
			
			String formatedPlugin = plugin.split("Decorator")[0];
			
			if(decorators.indexOf(formatedPlugin) == -1) {
				decorators.addElement(formatedPlugin);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == insertButton) {
			if(decoratorList.getSelectedValue() != null) {
				if(SelectedDecorators.size() - 1 >= 1 && selectedList.getSelectedIndex() != -1) {
					SelectedDecorators.add(selectedList.getSelectedIndex() + 1, decoratorList.getSelectedValue());
				}else {
					SelectedDecorators.addElement(decoratorList.getSelectedValue());
				}
				
			}
		}
		
		if(e.getSource() == removeButton) {
			if(selectedList.getSelectedValue() != null) {
				int index = selectedList.getSelectedIndex();
				SelectedDecorators.remove(index);
			}
		}
		
		if(e.getSource() == upButton) {
			if(selectedList.getSelectedValue() != null && selectedList.getSelectedIndex() != 0) {
				int index = selectedList.getSelectedIndex();
				String value = selectedList.getSelectedValue();
				
				SelectedDecorators.set(index, SelectedDecorators.get(index -1));
				SelectedDecorators.set(index-1, value);
				
				selectedList.setSelectedIndex(index -1);
			}
		}
		
		if(e.getSource() == downButton) {
			if(selectedList.getSelectedValue() != null && selectedList.getSelectedIndex() != SelectedDecorators.getSize() - 1) {
				int index = selectedList.getSelectedIndex();
				String value = selectedList.getSelectedValue();
				
				SelectedDecorators.set(index, SelectedDecorators.get(index+1));
				SelectedDecorators.set(index+1, value);
				
				selectedList.setSelectedIndex(index +1);
			}
		}
		
		if(e.getSource() == doneButton) {
			if(SelectedDecorators.getSize() >= 1) {
				try {
					this.plugin.executeDecorator(getDecorators());
					this.reset();
				}catch(Exception er) {
					System.out.println("ops");
				}
				
			}
		}
		
		if(e.getSource() == refreshButton) {
			this.SelectedDecorators.removeAllElements();
			this.decorators.removeAllElements();
			setOptions();
		}
	}
}
