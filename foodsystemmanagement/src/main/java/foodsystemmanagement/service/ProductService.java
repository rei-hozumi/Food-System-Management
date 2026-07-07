package foodsystemmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import foodsystemmanagement.entity.Product;
import foodsystemmanagement.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	//商品一覧取得
	public List<Product>findAll(){
		return productRepository.findAll();
	}
	//登録
	public void save(Product product) {
		productRepository.save(product);
	}
	//編集
	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow();
	}
	public void update(Product product) {
		productRepository.save(product);
	}
}
