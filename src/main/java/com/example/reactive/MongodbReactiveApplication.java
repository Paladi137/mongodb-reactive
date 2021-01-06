package com.example.reactive;



import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.reactive.model.Employee;
import com.example.reactive.repository.EmployeeRepository;


@SpringBootApplication
public class MongodbReactiveApplication {
	
	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository) {

		return args -> {
			employeeRepository
					.deleteAll()
			.subscribe(null, null, () -> {

				Stream.of(new Employee(UUID.randomUUID().toString(),
						"ram", 23000L),new Employee(UUID.randomUUID().toString(),
						"Swathi", 13000L),new Employee(UUID.randomUUID().toString(),
						"saidulu", 20000L),new Employee(UUID.randomUUID().toString(),
						"venkat", 53000L)
						)
						.forEach(employee -> {
				employeeRepository
						.save(employee)
						.subscribe(System.out::println);

						});

			})
			;
		};
}
	public static void main(String[] args) {
		SpringApplication.run(MongodbReactiveApplication.class, args);
	}

}
