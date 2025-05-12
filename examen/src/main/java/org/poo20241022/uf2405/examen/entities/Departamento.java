package org.poo20241022.uf2405.examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//@Data
//@NoArgsConstructor
@Table(name="DEPARTMENTS")
public class Departamento {
	
	private @Id @Column(name="DEPARTMENT_ID")
	Integer id;
	@Column(name="DEPARTMENT_NAME") 
	private String nombre;
	@Column(name="MANAGER_ID") private String idGestor;
	
	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private Direccion direccion;

	public Departamento() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdGestor() {
		return idGestor;
	}

	public void setIdGestor(String idGestor) {
		this.idGestor = idGestor;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", idGestor=" + idGestor + ", direccion=" + direccion
				+ "]";
	}
	
	

}
