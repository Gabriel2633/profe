package model;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Cliente {

	private int id;
	private String nombre;
	private String apellidos;
	private Date fnacimiento;
	
	public Cliente(int id, String nombre, String apellidos, Date fnacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fnacimiento = fnacimiento;
	}
	public Cliente(String nombre, String apellidos, String fnacimiento) {
		super();
		this.nombre=nombre;
		this.apellidos=apellidos;
		setFnacimiento(fnacimiento);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFnacimiento() {
		return fnacimiento;
	}
	public String getFnacimientoToString() {
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 return df.format(fnacimiento);
	}
	public void setFnacimiento(Date fnacimiento) {
		this.fnacimiento = fnacimiento;
	}
	public void setFnacimiento(String fnacimiento) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			this.fnacimiento = format.parse(fnacimiento);
		} catch (ParseException e) {
			e.printStackTrace();
		}			
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fnacimiento=" + fnacimiento
				+ "]";
	}
	
	
}
