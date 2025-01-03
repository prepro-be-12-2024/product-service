package vn.test.hub.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "vn.test.hub.*")
@EnableJpaRepositories(basePackages = {"vn.test.hub.product.datasource.repo"})
@EntityScan(basePackages = "vn.test.hub.product.datasource.entity")
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}