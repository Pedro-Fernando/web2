package br.com.cpcx.web2.pedro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("developer")
public class MinhaConfiguracao {

    @Bean("beanConfiguration")
    public CommandLineRunner executar(){
        return args -> System.out.println("Ambiente de Desenvolvimento");

    }
}
