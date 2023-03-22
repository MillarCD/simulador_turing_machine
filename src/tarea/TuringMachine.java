package tarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TuringMachine extends JFrame {
	
	private JPanel panelPrincipal;
	private String frase;
	

	// ------------------------------ Panel y componentes par ingresar los datos iniciales

	
	
	private JLabel labelEstadoInicial;
	private JTextField campoEstadoInicial;
	private JButton botonEstadoInicial;
	
	private JLabel labelEstadoFinal;
	private JTextField campoEstadoFinal;
	private JButton botonEstadoFinal1;
	private JButton botonEstadoFinal2;
	
	private JLabel labelPalabra;
	private JTextField campoPalabra;
	private JButton botonFinalizar;
	
	private JLabel labelTransicion;
	private JTextField campoTransicion;
	private JButton botonTransicion;
	
	/*
	private JPanel panelTransiciones;
	private JLabel labelEstado1;
	private JLabel labelEstado2;
	private JLabel labelEstado3;
	private JLabel labelEstado4;
	private JLabel labelEstado5;
	private JLabel labelEstado6;
	private JTextField campoEstadoActual;
	private JTextField campoSimboloActual;
	private JTextField campoEstadoSiguiente;
	private JTextField campoSimboloSiguiente;
	private JComboBox cajaDireccion;
	private JButton botonAgregarTransicion;
	private JButton botonAgregarUltimo;*/
	
	//-----------------------------------------------------Panel para agregar mas palabras, eliminar maquina y ver resultado
	private JLabel labelResultado;
	private JLabel labelPalabraNueva;
	private JTextField campoPalabraNueva;
	private JButton botonAgregarPalabraNueva;
	private JButton botonInsertarMaquina;
	
	
	//----------------- variables para maquina de turing
	private String estadoInicial;
	private HashSet<String> estadosFinales;
	private HashMap<String, String> transiciones;
	private String key;
	private String valor;
	private String palabra;
	
	
	//--------------------- variables que se usan para el estilo
	static Color COLOR_PANEL = new Color(70,70,70);
	static Color COLOR_FONDO = new Color(139,196,124);
	static Color COLOR_FUENTE_BOTON = new Color(70,70,70);
	static Color COLOR_FUENTE = new Color(198,253,184);
	static Font FUENTE = new Font("Serif",Font.CENTER_BASELINE,20);
	
	public TuringMachine() {
		super("Maquina de Turing");
		this.setSize(900,425);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		panelPrincipal.setBackground(COLOR_PANEL);
		this.add(panelPrincipal);
		
		
		//--------------------------------Inicializa panel para insertar los datos
		
		//---------------------- agregar estado inicial
		labelEstadoInicial = new JLabel("Estado Inicial (qn):");
		personalizarLabel(labelEstadoInicial,0,50,50);
		
		campoEstadoInicial = new JTextField("");
		campoEstadoInicial.setBounds(300, 50, 300, 50);
		botonEstadoInicial = new JButton("Agregar");
		personalizarBoton(botonEstadoInicial,600,50,50);
		
		botonEstadoInicial.addActionListener(new ActionListener(){ // funcion que guarda el estado inicial cuando se apreta el boton
			public void actionPerformed(ActionEvent e){
				estadoInicial = campoEstadoInicial.getText();
				campoEstadoInicial.setText("");	
				panelPrincipal.remove(botonEstadoInicial);
				panelPrincipal.updateUI();	// actualiza el panel
				
				JLabel completado1 = new JLabel("Guardado");
				personalizarLabel(completado1,600,50,50);
				panelPrincipal.add(completado1);
			}
		});
		
		//---------------------- Agragar estado final		
		labelEstadoFinal = new JLabel("Estado Final (qn):");
		personalizarLabel(labelEstadoFinal,0,125,50);
		campoEstadoFinal = new JTextField("");
		campoEstadoFinal.setBounds(300,125 ,300,50);
		botonEstadoFinal1 = new JButton("Agregar");
		personalizarBoton(botonEstadoFinal1,600,125,25);
		botonEstadoFinal2 = new JButton("Agregar ultimo");
		personalizarBoton(botonEstadoFinal2,600,150,25);
		
		
		botonEstadoFinal1.addActionListener(new ActionListener(){ // funcion que guarda la palabra cuando se apreta el boton
			public void actionPerformed(ActionEvent e){
				estadosFinales.add(campoEstadoFinal.getText());	// agrega el estado final al arraylist
				campoEstadoFinal.setText(""); // borra lo que se ingreso del TextField				
			}
		});
		botonEstadoFinal2.addActionListener(new ActionListener(){ // funcion que guarda la palabra cuando se apreta el boton
			public void actionPerformed(ActionEvent e){
				estadosFinales.add(campoEstadoFinal.getText());
				campoEstadoFinal.setText("");
				panelPrincipal.remove(botonEstadoFinal1);	// remueve los componentes
				panelPrincipal.remove(botonEstadoFinal2);
				panelPrincipal.updateUI();	// actualiza el panel
				JLabel completado2 = new JLabel("Estados Finales guardados");
				personalizarLabel(completado2,600,125,50);
				panelPrincipal.add(completado2);
			}
		});

		// nueva transicion
		labelTransicion = new JLabel("Transicion:");
		personalizarLabel(labelTransicion,0,200,50);
		
		campoTransicion = new JTextField("");
		campoTransicion.setBounds(300, 200, 300, 50);
		botonTransicion = new JButton("Agregar");
		personalizarBoton(botonTransicion,600,200,50);
		
		botonTransicion.addActionListener(new ActionListener(){ // funcion que guarda el estado inicial cuando se apreta el boton
			public void actionPerformed(ActionEvent e){
				String listaTransiciones[] = campoTransicion.getText().split("/");
				for(String valores:listaTransiciones) {
					String valor[] = valores.split("-");
					transiciones.put(valor[0],valor[1]);	// guarda los elementos en el hashmap
				}
				System.out.println(transiciones);
				
				campoTransicion.setText("");	
				panelPrincipal.remove(botonTransicion);
				panelPrincipal.updateUI();	// actualiza el panel
				
				JLabel completado3 = new JLabel("Guardado");
				personalizarLabel(completado3,600,50,50);
				panelPrincipal.add(completado3);
			}
		});
		// ------------------------ agrega transiciones
		/*
		panelTransiciones = new JPanel();
		panelTransiciones.setBounds(0, 200, 600,50);
		panelTransiciones.setBackground(COLOR_PANEL);
		labelEstado1 = new JLabel("δ(");
		personalizarLabel(labelEstado1,0,175,50); // la posicion de los label no afecta ya que estaran puestos en un panel con layout
		campoEstadoActual = new JTextField(2);
		labelEstado2 = new JLabel(",");
		personalizarLabel(labelEstado2,0,175,50);
		campoSimboloActual = new JTextField(2);
		labelEstado3 = new JLabel(") = (");
		personalizarLabel(labelEstado3,0,175,50);
		campoEstadoSiguiente = new JTextField(2);
		labelEstado4 = new JLabel(",");
		personalizarLabel(labelEstado4,0,175,50);
		campoSimboloSiguiente = new JTextField(2);
		labelEstado5 = new JLabel(",");
		personalizarLabel(labelEstado5,0,175,50);
		cajaDireccion = new JComboBox();
		cajaDireccion.addItem("D");
		cajaDireccion.addItem("I");
		labelEstado6 = new JLabel(")");
		personalizarLabel(labelEstado6,0,175,50);
		botonAgregarTransicion = new JButton("Agregar Transicion");
		personalizarBoton(botonAgregarTransicion,600,200,25);
		botonAgregarUltimo = new JButton("Agregar ultimo");
		personalizarBoton(botonAgregarUltimo,600,225,25);
		botonAgregarTransicion.addActionListener(new ActionListener(){ // funcion que guarda la transicion en el hashing key=(qn,Simbolo) valor=(qn,simbolo,direccion)
			public void actionPerformed(ActionEvent e){
				key = campoEstadoActual.getText()+","+campoSimboloActual.getText();
				valor = campoEstadoSiguiente.getText()+","+campoSimboloSiguiente.getText()+","+(String)cajaDireccion.getSelectedItem();
				transiciones.put(key,valor);	// guarda los elementos en el hashmap
				
				campoEstadoActual.setText("");	// borra los textos ingresados
				campoSimboloActual.setText("");
				campoEstadoSiguiente.setText("");
				campoSimboloSiguiente.setText("");
			}
		});
		botonAgregarUltimo.addActionListener(new ActionListener(){ // funcion que guarda la transicion en el hashing key=(qn,Simbolo) valor=(qn,simbolo,direccion)
			public void actionPerformed(ActionEvent e){
				key = campoEstadoActual.getText()+","+campoSimboloActual.getText();
				valor = campoEstadoSiguiente.getText()+","+campoSimboloSiguiente.getText()+","+(String)cajaDireccion.getSelectedItem();
				transiciones.put(key,valor);	// guarda los elementos en el hashmap
				
				campoEstadoActual.setText("");	// borra los textos ingresados
				campoSimboloActual.setText("");
				campoEstadoSiguiente.setText("");
				campoSimboloSiguiente.setText("");
			//	panelAgregarTransicion.removeAll();	// remueve los componentes
				panelPrincipal.remove(botonAgregarTransicion);
				panelPrincipal.remove(botonAgregarUltimo);				
				panelPrincipal.updateUI();	// actualiza el panel

				JLabel completado3 = new JLabel("Transiciones guardados");
				personalizarLabel(completado3,600,200,50);
				panelPrincipal.add(completado3);
				
			}
		});
		*/
		// ------------------------- Agrega la palabra de entrada
		labelPalabra = new JLabel("Palabra de entrada:");
		personalizarLabel(labelPalabra,0,275,50);
		campoPalabra = new JTextField("");
		campoPalabra.setBounds(300, 275, 300,50);
		botonFinalizar = new JButton("Agregar y comenzar");
		personalizarBoton(botonFinalizar,600,275,50);
		botonFinalizar.addActionListener(new ActionListener(){ // funcion que guarda la palabra cuando se apreta el boton
			public void actionPerformed(ActionEvent e){
				palabra = campoPalabra.getText();
				campoPalabra.setText("");
				panelPrincipal.removeAll();	// remueve los componentes
				panelPrincipal.updateUI();	// actualiza el panel
				
				if (validarPalabra())			// valida si la MT acepta la palabra
					frase = "La palabra: '"+palabra+"' ha sido aceptada por la maquina de Turing";
				else
					frase = "La palabra: '"+palabra+"' ha sido rechazada por la maquina de Turing";
				labelResultado.setText(frase);
				
				panelVerResultado();
			}
		});
	
		//-----------------------------------------------------------Panel para agregar mas palabras, eliminar maquina y ver resultado
		labelResultado = new JLabel("");
		personalizarLabel(labelResultado,100,50,50);
		labelResultado.setSize(850,50);
		labelPalabraNueva = new JLabel("Agregar palabra:");
		personalizarLabel(labelPalabraNueva,0,200,50);
		campoPalabraNueva = new JTextField(20);
		campoPalabraNueva.setBounds(300,200,300,50);
		botonAgregarPalabraNueva = new JButton("Agregar");
		personalizarBoton(botonAgregarPalabraNueva,600,200,50);
		botonInsertarMaquina = new JButton("Volver a ingresar Maquina de Turing");
		personalizarBoton(botonInsertarMaquina,175,300,50);
		botonInsertarMaquina.setSize(600,50);
		botonAgregarPalabraNueva.addActionListener(new ActionListener(){ // funcion que guarda la palabra cuando se apreta el boton
			public void actionPerformed(ActionEvent e){
				palabra = campoPalabraNueva.getText();
				campoPalabraNueva.setText("");
			
				if (validarPalabra())			// valida si la MT acepta la palabra
					frase = "La palabra: '"+palabra+"' ha sido aceptada por la maquina de Turing";
				else
					frase = "La palabra: '"+palabra+"' ha sido rechazada por la maquina de Turing";
				labelResultado.setText(frase);
			}
		});
		botonInsertarMaquina.addActionListener(new ActionListener(){ // funcion que guarda la palabra cuando se apreta el boton
			public void actionPerformed(ActionEvent e){
				// remueve el panel de ver resultados para luego volver a poner el de ingresar datos
				panelPrincipal.removeAll();
				panelPrincipal.updateUI();
				panelInsertarMaquinaTuring();
				
			}
		});
		
		panelInsertarMaquinaTuring();
		
	
		this.setVisible(true);
	}
	
	private void panelInsertarMaquinaTuring() {
		// Datos para la maquina de turing
		estadosFinales = new HashSet<String>();
		transiciones = new HashMap<String,String>();
		
		// estadp Inicial
		panelPrincipal.add(labelEstadoInicial);
		panelPrincipal.add(campoEstadoInicial);
		panelPrincipal.add(botonEstadoInicial);
		
		// estados finales
		panelPrincipal.add(labelEstadoFinal);
		panelPrincipal.add(campoEstadoFinal);
		panelPrincipal.add(botonEstadoFinal1);
		panelPrincipal.add(botonEstadoFinal2);
		
		
		panelPrincipal.add(labelTransicion);
		panelPrincipal.add(campoTransicion);
		panelPrincipal.add(botonTransicion);
		// transiciones
/*		panelTransiciones.add(labelEstado1);
		panelTransiciones.add(campoEstadoActual);
		panelTransiciones.add(labelEstado2);
		panelTransiciones.add(campoSimboloActual);
		panelTransiciones.add(labelEstado3);
		panelTransiciones.add(campoEstadoSiguiente);
		panelTransiciones.add(labelEstado4);
		panelTransiciones.add(campoSimboloSiguiente);
		panelTransiciones.add(labelEstado5);
		panelTransiciones.add(cajaDireccion);
		panelTransiciones.add(labelEstado6);			
		panelPrincipal.add(panelTransiciones);
		panelPrincipal.add(botonAgregarTransicion);
		panelPrincipal.add(botonAgregarUltimo);*/
			
		// palabra de entrada
		panelPrincipal.add(labelPalabra);
		panelPrincipal.add(campoPalabra);
		panelPrincipal.add(botonFinalizar);
	
		
	}
	
	/* crea el panel que mostrara si la palabra es aceptada por la maquina de turing
		da la opcion de ingresar una nueva palabra o de ingresar una nueva MT
	*/
	private void panelVerResultado() {
		panelPrincipal.add(labelResultado);
		panelPrincipal.add(labelPalabraNueva);
		panelPrincipal.add(campoPalabraNueva);
		panelPrincipal.add(botonAgregarPalabraNueva);
		panelPrincipal.add(botonInsertarMaquina);
	}
	
	private boolean validarPalabra() {
		char [] palabraMod = (palabra+"BBB").toCharArray();
		String estadoActual = estadoInicial;
		int indiceActual = 0;
		char simboloActual = palabraMod[indiceActual];
		String obtenerTransicion = estadoActual+","+simboloActual;
		String [] nuevosDatos;
		
		while(transiciones.get(obtenerTransicion)!= null) { // comprueba si existe una nueva transicion para realizar
			nuevosDatos = transiciones.get(obtenerTransicion).split(","); //separa los datos de la transicion
			
			estadoActual = nuevosDatos[0];		// actualiza al estado actual
			//reemplaza el caracter en la posicion actual por el que dice la transicion
			palabraMod[indiceActual] = nuevosDatos[1].charAt(0);
			
			if(nuevosDatos[2].equals("D")) 
				indiceActual++;
			else 
				indiceActual--;
			
			if(indiceActual>=0 && indiceActual<palabraMod.length)	// comprueba si el indice esta dentro del rango de la palabra
				simboloActual = palabraMod[indiceActual];	// cambia al simbolo que esta en la posicion del cabezal
			else
				return false;
			
			obtenerTransicion = estadoActual+","+simboloActual;
		}
		
		if(estadosFinales.contains(estadoActual)) // si es estado final, entonces la palabra ha sido aceptada
			return true;
		else {
			System.out.println("no se encontro la tansicion: "+obtenerTransicion);
			return false;
		}
	}
	
	
	private void personalizarBoton(JButton boton,int x,int y,int alto) {
		boton.setBackground(COLOR_FONDO);		// color del fondo
		boton.setForeground(COLOR_FUENTE_BOTON);		// color de la fuente
		boton.setFont(FUENTE);
		boton.setBounds(x,y, 300, alto); //x y ancho alto
		
	}
	private void personalizarLabel(JLabel label,int x,int y,int alto) {
		label.setBackground(COLOR_FONDO);		// color del fondo
		label.setForeground(COLOR_FUENTE);		// color de la fuente
		label.setFont(FUENTE);
		label.setBounds(x,y, 300, alto); //x y ancho alto
	}
	
	public static void main(String[] args) {
		TuringMachine MT = new TuringMachine();

	}

}