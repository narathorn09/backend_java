package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

@RestController
public class ProductController {
	
	private List<Product> data = new ArrayList<Product>();
	
	@GetMapping("/product")
	public List<Product> getProduct() {
		return data;
	}
	
	@GetMapping("/product/{productId}")
	public Product getProductById(@PathVariable("productId") Integer productId) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getProductId().equals(productId)) {
				return data.get(i);
			}
		}
		return null;
	}
	
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product body) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getProductId() == body.getProductId()) {
				return null;
			}
		}
		data.add(body);
		return body;
	}
	
	@PutMapping("/product/{productId}")
	public String updateProduct(@PathVariable("productId") Integer productId, @RequestBody Product body) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getProductId().equals(productId)) {
				body.setProductId(productId);
	            data.set(i, body);
				return "Update Product Success";
			}
		}
		return "Error Update Product";
	}
	
	@DeleteMapping("/product/{productId}")
	public String deleteProduct(@PathVariable("productId") Integer productId) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getProductId().equals(productId)) {
				data.remove(i);
				return "Delete Product Success";
			}
		}
		return "Error Delete Product";
	}
	
	@GetMapping("/searchProduct")
	public List<Product> getProductBySearch(
	        @RequestParam("productName") String productName,
	        @RequestParam("productDetail") String productDetail
	) {
	    List<Product> listFound = new ArrayList<>();

	    for (Product product : data) {
	        if ((product.getProductName().contains(productName) && productName !=  "") || 
	        	(product.getProductDetail().contains(productDetail) && productDetail !=  "")) {
	            listFound.add(product);
	        }
	    }

	    return listFound;
	}

	
	
}
