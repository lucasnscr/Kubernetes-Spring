package service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import converter.DTOConverter;
import dto.ProductDTO;
import exception.CategoryNotFoundException;
import exception.ProductNotFoundException;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.CategoryRepository;
import repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository){
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}
	

		
	public List<ProductDTO> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}
	
	public List<ProductDTO> getProductByCategoryId(Long categoryId) {
		List<Product> products = productRepository.getProductByCategory(categoryId);
		return products.stream().map(DTOConverter::convert).collect(Collectors.toList());		
	}
		
	public ProductDTO findByProductIdentifier(String  productIdentifier) {
		Product product = productRepository.findByProductIdentifier(productIdentifier);
		if (product != null) {
			return DTOConverter.convert(product);
		}
		throw new ProductNotFoundException();
	}
	
	public ProductDTO save(ProductDTO productDTO) {
		Boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());
		if (!existsCategory) {
			throw new CategoryNotFoundException();
		}				
		Product product = productRepository.save(Product.convert(productDTO));
		return DTOConverter.convert(product);
	}
	
	public ProductDTO delete(long ProductId) throws ProductNotFoundException {
		Optional<Product> Product = productRepository.findById(ProductId);
		if (Product.isPresent()) {
			productRepository.delete(Product.get());
		}
		throw new ProductNotFoundException();
	}
	
}
