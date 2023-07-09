package t3h.project.java.shop.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;
import t3h.project.java.shop.CommonData.model.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing
public class JpaConfig {

//    @Bean
//    AuditorAware<String> auditorAware(){
//        return new AuditorAwareImpl();
//    }

    @Component
    public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {

    }
}
