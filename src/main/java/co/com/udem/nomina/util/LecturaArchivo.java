package co.com.udem.nomina.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import co.com.udem.nomina.dto.EmpleadoDTO;

public class LecturaArchivo {
	
	private static final Logger logger = LogManager.getLogger(LecturaArchivo.class);
	private Hashtable<String, EmpleadoDTO> listaEmpleados = new Hashtable<String, EmpleadoDTO>();
	private int cantidadRegistros = 0;
	
	public String leerArchivo() {
		BasicConfigurator.configure();
		File archivoNomina = new File("/Users/walterllano/Documents/nominaEmpleados.txt");
		Scanner scanner = null;
		String mensaje = "";
		try {
			scanner = new Scanner(archivoNomina);
			while(scanner.hasNextLine()) {
				String registro = scanner.nextLine();
				leerRegistro(registro);
				cantidadRegistros++;
			}
		} catch (FileNotFoundException e) {
			mensaje = "El archivo no está en la ruta especificada";
			//logger.error(e.getMessage());
		}finally {
			if(scanner != null) {
				scanner.close();
			}
			
		}
		
		return mensaje;
		
	}

	private void leerRegistro(String registro) {
		Scanner scanner = new Scanner(registro);
		scanner.useDelimiter(",");
		while(scanner.hasNext()) {
			EmpleadoDTO empleadoDTO = new EmpleadoDTO();
			empleadoDTO.setNombres(scanner.next());
			empleadoDTO.setApellidos(scanner.next());
			String cedula = scanner.next();
			empleadoDTO.setCedula(cedula);
			empleadoDTO.setDepartamento(scanner.next());
			empleadoDTO.setSalario(Double.parseDouble(scanner.next()));
			listaEmpleados.put(cedula, empleadoDTO);
		}
		imprimirEmpleadosArchivo(listaEmpleados);
		scanner.close();
	}
	
	private void imprimirEmpleadosArchivo(Hashtable<String, EmpleadoDTO> listaEmpleados) {
		BasicConfigurator.configure();
		logger.info(listaEmpleados);
		
	}
	
	public int devolverCantidadRegistros() {
		return cantidadRegistros;
	}

}
