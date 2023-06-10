package kodlama.io.rentACar.dataAcces.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entites.concretes.Brand;

public interface BrandRepository  extends JpaRepository<Brand,Integer> {
		boolean existsByName(String name);  //spring jpa keywords
}
