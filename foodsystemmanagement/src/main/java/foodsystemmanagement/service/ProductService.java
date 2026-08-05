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
	//削除
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
	//検索
	public List<Product> search(String keyword){
		System.out.println("検索文字："+keyword);
		
		List<Product>list=productRepository.findByProductNameContaining(keyword);
		System.out.println("件数："+list.size());
		return list;
	}
	//重複チェック
	public boolean existsProductNumber(String productNumber) {
		return productRepository.findByProductNumber(productNumber) != null;
	}

}
