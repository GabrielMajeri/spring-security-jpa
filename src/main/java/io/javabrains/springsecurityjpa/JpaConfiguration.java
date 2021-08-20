package io.javabrains.springsecurityjpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class JpaConfiguration {
}
