package com.foro.last.Controlador;

import com.foro.last.Dominio.TablasRepositorios.RegistroTema;
import com.foro.last.Dominio.Interfaz.TemasForo;
import com.foro.last.Dominio.Temas.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/topicos")
public class ControladorTemas {
    private final TemasForo repositorioForo;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ControladorTemas(TemasForo repositorioForo) {
        this.repositorioForo = repositorioForo;
    }

    @PostMapping
    public ResponseEntity <ExtraccionConUrl> registroForo(@RequestBody @Valid Foro Foro,
                                                    UriComponentsBuilder builder) {
        Estado estado = Estado.Pendiente;
        RegistroTema registro = repositorioForo.save(new RegistroTema(Foro, estado));

        String fechaformateada = registro.getFecha().format(formatter);

        DatosRegistro datosRegistro = new DatosRegistro(registro.getTopico(),
                registro.getTitulo(), registro.getCurso(), registro.getEstado(),
                registro.getMensaje(), registro.getProfesor(), fechaformateada);

        URI url = builder.path("/topicos/{topico}").buildAndExpand(registro.getTopico()).toUri();

        ExtraccionConUrl extraer = new ExtraccionConUrl(datosRegistro, url);

        return ResponseEntity.created(url).body(ExtraccionConUrl.class.cast(extraer));

    }

    // Problematicas Actuales
    @GetMapping
    public ResponseEntity<Page<DatosExtraidos>> listadotopicos(@PageableDefault(size = 5) Pageable pagina){
        return ResponseEntity.ok(repositorioForo.findByActivoTrue(pagina).map(DatosExtraidos::new));
    }

    // Actualizar problematicas
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRegistro> actualizarForo(@RequestBody @Valid DatosActualizados actualizados){
        RegistroTema extraer = repositorioForo.getReferenceById(actualizados.topico());
        extraer.actualizarDatos(actualizados);
        if (extraer.getActivo() == true){
            String fechaformateada = extraer.getFecha().format(formatter);
            return ResponseEntity.ok(new DatosRegistro(extraer.getTopico(),
                    extraer.getTitulo(), extraer.getCurso(), extraer.getEstado(), extraer.getMensaje(),
                    extraer.getProfesor(), fechaformateada));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    // Eliminar problematica
    @DeleteMapping
    @Transactional
    public ResponseEntity<RegistroTema> eliminarForo(@RequestBody @Valid ManejoTopicos manejoTopicos){
        RegistroTema eliminar = repositorioForo.getReferenceById(manejoTopicos.topico());
        eliminar.desactivarTopico();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{topico}")
    public ResponseEntity<DatosRegistro> retornarTopico(@PathVariable Long topico){
        RegistroTema busqueda = repositorioForo.getReferenceById(topico);
        String fechaformateada = busqueda.getFecha().format(formatter);
        var datosTopicos = new DatosRegistro(busqueda.getTopico(), busqueda.getTitulo(),
                busqueda.getCurso(), busqueda.getEstado(), busqueda.getMensaje(),
                busqueda.getProfesor(), fechaformateada);
        return ResponseEntity.ok(datosTopicos);
    }
}

