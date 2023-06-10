package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAcces.abstracts.BrandRepository;
import kodlama.io.rentACar.entites.concretes.Brand;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service //BU SINIF BİR BUSİNESS NESNESİDİR
public class BrandManager  implements BrandService {
	 private BrandRepository brandRepository;
	 private ModelMapperService modelMapperService;
	 private BrandBusinessRules brandBusinessRules;
	 

	

	@Override
	public List<GetAllBrandsResponse> getAll() {
		List<Brand> brands= brandRepository.findAll();
	/*	List<GetAllBrandsResponse> brandsResponse= new ArrayList<GetAllBrandsResponse>();
		//list brandı getalla çevirmek için for ile gezip o anki brands değerini gettallbrandsa eşitleyip içine ekliyorz
		for(Brand brand: brands) {
			GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
			responseItem.setId(brand.getId());
			responseItem.setName(brand.getName());
			brandsResponse.add(responseItem);
			// şimmdi bu işi model mapper ile yapacağız
		} */
				//aynı işlemi aşşağıda map desteği ile yapacağız ne kadar field olursa olsun eşitler
				//stream for gibi verilen nesneyi gezer  ve map işlemi ile eşitleriz
		List<GetAllBrandsResponse> brandsResponse= brands.stream()
			 .map(brand->this.modelMapperService.forResponse()
			 .map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
		
		return brandsResponse;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		/*Brand brand = new Brand();
	//	brand.setName(createBrandRequest.getName()); //bir sürü set işlemi yapmak yerine
		// mapper service ile create yani oluşturulması istedğimiz değeri DB nesesi branda otomatik
		/olarak mapper ile atama yapıyoruz */
		this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		
		this.brandRepository.save(brand);
		
	}

	@Override
	public GetByIdBrandResponse getById(int id) {
		Brand brand =  this.brandRepository.findById(id).orElseThrow();
		
		GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
		
		return response;
	}

	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);//id de aldığı için updatebrand clasından update gerçekleştirir
	}

	@Override
	public void delete(int id) {
		
		this.brandRepository.deleteById(id);
		
	}

}
