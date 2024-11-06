package sw.goku.servidor.bussiness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sw.goku.servidor.bussiness.repository.entity.Proveedor;
import sw.goku.servidor.bussiness.service.ProveedorService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {
    private final ProveedorService proveedorService;

    // Endpoint para agregar proveedor (solo accesible para ADMIN con permiso ADD_PROVIDER)
    @PreAuthorize("hasAuthority('ADD_PROVIDER')")
    @PostMapping("/agregar")
    public Proveedor agregarProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.agregarProveedor(proveedor);
    }

    // Endpoint para buscar proveedor por RUC (accesible para roles con permisos SEARCH_PROVIDER o LIST_PROVIDERS)
    @PreAuthorize("hasAnyAuthority('SEARCH_PROVIDER', 'LIST_PROVIDERS')")
    @GetMapping("/buscar/{ruc}")
    public Proveedor buscarPorRuc(@PathVariable String ruc) {
        return proveedorService.buscarPorRuc(ruc);
    }

    // Endpoint para listar todos los proveedores (accesible para roles con permiso LIST_PROVIDERS)
    @PreAuthorize("hasAuthority('LIST_PROVIDERS')")
    @GetMapping("/listar")
    public List<Proveedor> listarProveedores() {
        return proveedorService.listarProveedores();
    }

    // Endpoint para eliminar proveedor por RUC (solo accesible para roles con permiso DELETE_PROVIDER)
    @PreAuthorize("hasAuthority('DELETE_PROVIDER')")
    @DeleteMapping("/eliminar/{ruc}")
    public void eliminarPorRuc(@PathVariable String ruc) {
        proveedorService.eliminarPorRuc(ruc);
    }

    // Endpoint para filtrar proveedores por fecha de registro (solo accesible para ADMIN)
    @PreAuthorize("hasAuthority('FILTER_PROVIDERS')")
    @GetMapping("/filtrar")
    public List<Proveedor> filtrarPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return proveedorService.filtrarPorFechas(fechaInicio, fechaFin);
    }
}
