package sw.goku.servidor.bussiness.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw.goku.servidor.bussiness.repository.entity.Proveedor;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProveedorDao extends JpaRepository<Proveedor, Long> {
    Proveedor findByRuc(String ruc);
    List<Proveedor> findByFechaRegistroBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
