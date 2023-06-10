package kodlama.io.rentACar.dataAcces.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entites.concretes.Model;

public interface ModelRepository  extends JpaRepository<Model, Integer>{

}
