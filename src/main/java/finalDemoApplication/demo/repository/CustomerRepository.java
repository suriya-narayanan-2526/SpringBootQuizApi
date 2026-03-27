package finalDemoApplication.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import finalDemoApplication.demo.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
   Optional<CustomerEntity> findByName(String name);
}
