package core.io.biblioteca.config;

import core.io.biblioteca.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Profile("test")
@RequiredArgsConstructor
public class DBConfig {

    private final DBService dbService;

    @Bean
    public DBService instanciaDB(){
        this.dbService.instanciaDB();
        return dbService;
    }
}
