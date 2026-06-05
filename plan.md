refactorizar con los siguientes ejemplos de estrucutura:

-- COntroller

package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.lp.iii.api.entity.Almacen;
import proyecto.lp.iii.api.service.IAlmacenService;

@RestController
@RequestMapping("/api")
public class AlmacenController {
	@Autowired
	private IAlmacenService serviceAlmacen;

	@GetMapping("/almacenes")
	public List<Almacen> buscarTodos() {
		return serviceAlmacen.buscarTodos();
	}

	@PostMapping("/almacenes")
	public Almacen guardar(@RequestBody Almacen registro) {
		serviceAlmacen.guardar(registro);
		return registro;
	}

	@PutMapping("/almacenes")
	public Almacen modificar(@RequestBody Almacen registro) {
		serviceAlmacen.modificar(registro);
		return registro;
	}

	@GetMapping("/almacenes/{id}")
	public Optional<Almacen> buscarId(@PathVariable("id") Integer id) {
		return serviceAlmacen.buscarId(id);
	}

	@DeleteMapping("/almacenes/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceAlmacen.eliminar(id);
		return "Registro Eliminado";
	}
}



-- entity

package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "almacenes")
@SQLDelete(sql = "UPDATE almacenes SET estado=0 WHERE id_almacenes=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_almacenes",
        "id_sedes",
        "nombre_almacen",
        "ubicacion",
        "capacidad",
        "estado"
})
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_almacenes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;

    @Column(name = "nombre_almacen", nullable = false, length = 100)
    private String nombre_almacen;

    @Column(length = 255)
    private String ubicacion;

    @Column
    private Integer capacidad;

    @Column
    private Integer estado = 1;

    public Integer getId_almacenes() {
        return id_almacenes;
    }

    public void setId_almacenes(Integer id_almacenes) {
        this.id_almacenes = id_almacenes;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public String getNombre_almacen() {
        return nombre_almacen;
    }

    public void setNombre_almacen(String nombre_almacen) {
        this.nombre_almacen = nombre_almacen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Almacen [id_almacenes=" + id_almacenes + ", id_sedes=" + id_sedes + ", nombre_almacen="
                + nombre_almacen + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + ", estado=" + estado
                + "]";
    }
}

-- repository

package proyecto.lp.iii.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.lp.iii.api.entity.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {

}

en service 

package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.Almacen;

public interface IAlmacenService {
    List<Almacen> buscarTodos();

    void guardar(Almacen almacen);

    void modificar(Almacen almacen);

    Optional<Almacen> buscarId(Integer id);

    void eliminar(Integer id);
}


en service/jpa

package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Almacen;
import proyecto.lp.iii.api.repository.AlmacenRepository;
import proyecto.lp.iii.api.service.IAlmacenService;

@Service
public class AlmacenService implements IAlmacenService {
    @Autowired
    private AlmacenRepository repoAlmacen;

    public List<Almacen> buscarTodos() {
        return repoAlmacen.findAll();
    }

    public void guardar(Almacen almacen) {
        repoAlmacen.save(almacen);
    }

    public void modificar(Almacen almacen) {
        repoAlmacen.save(almacen);
    }

    public Optional<Almacen> buscarId(Integer id) {
        return repoAlmacen.findById(id);
    }

    public void eliminar(Integer id) {
        repoAlmacen.deleteById(id);
    }
}



