package br.com.aluguel.de.carros.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroServiceImpl implements CarroService {
    private CarroRepository repository;

    @Autowired
    public CarroServiceImpl(CarroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Carro> todos() {
        return repository.findAll();
    }

    @Override
    public Optional<Carro> carro(Long id) {
        return repository.findById(id);
    }

    @Override
    public Carro novo(Carro carro) {
        carro.setModelo(carro.getModelo());
        return repository.save(carro);
    }

    @Override
    public Carro atualiza(Carro carro) {
        Carro carroAtualizado = repository.save(carro);
        //repository.reflesh(carroAtualizado);
        return carroAtualizado;
    }

    @Override
    public boolean deleta(Long id) {
        Optional<Carro> carroOpt = carro(id);
        if (carroOpt.isPresent()) {
            repository.delete(carroOpt.get());
            return true;
        }
        return false;
    }
}
