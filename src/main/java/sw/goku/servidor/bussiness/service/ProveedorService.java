package sw.goku.servidor.bussiness.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sw.goku.servidor.bussiness.repository.dao.ProveedorDao;
import sw.goku.servidor.bussiness.repository.entity.Proveedor;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorService {
    private final ProveedorDao proveedorDao;

    public Proveedor agregarProveedor(Proveedor proveedor) {
        proveedor.setFechaRegistro(LocalDate.now());
        return proveedorDao.save(proveedor);
    }

    public Proveedor buscarPorRuc(String ruc) {
        return proveedorDao.findByRuc(ruc);
    }

    public List<Proveedor> listarProveedores() {
        return proveedorDao.findAll();
    }

    public void eliminarPorRuc(String ruc) {
        Proveedor proveedor = proveedorDao.findByRuc(ruc);
        if (proveedor != null) {
            proveedorDao.delete(proveedor);
        }
    }

    public List<Proveedor> filtrarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return proveedorDao.findByFechaRegistroBetween(fechaInicio, fechaFin);
    }
}
