package com.foro.last.Dominio.Interfaz;

import com.foro.last.Dominio.TablasRepositorios.RegistroTema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemasForo extends JpaRepository<RegistroTema, Long> {
    Page<RegistroTema> findByActivoTrue(Pageable pagina);


}
