package br.com.aluguel.de.carros.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(path = "/carro")
public class CarroController {
    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<?> todos() {
        List<Carro> todos = service.todos();
        List<CarroDto> listaDto = CarroTransform.converteEntidadesEmDtos(todos);
        return new ResponseEntity<List<CarroDto>>(listaDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> carro(@PathVariable Long id) {
        Optional<Carro> carroOpt = service.carro(id);
        if (carroOpt.isPresent()) {
            CarroDto dto = CarroTransform.converteEntidadeEmDto(carroOpt.get());
            return new ResponseEntity<CarroDto>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> salva(@RequestBody CarroDto dto) {
        Carro carro = CarroTransform.converteDtoEmEntidade(dto);
        Carro carroSalvo = service.novo(carro);
        dto = CarroTransform.converteEntidadeEmDto(carroSalvo);
        return new ResponseEntity<CarroDto>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualiza(@RequestBody CarroDto dto) throws InterruptedException {
        Carro carro = CarroTransform.converteDtoEmEntidade(dto);
        Carro carroAtualizado = service.atualiza(carro);
        dto = CarroTransform.converteEntidadeEmDto(carroAtualizado);
        return new ResponseEntity<CarroDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        boolean carroRemovido = service.deleta(id);
        if (carroRemovido)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
