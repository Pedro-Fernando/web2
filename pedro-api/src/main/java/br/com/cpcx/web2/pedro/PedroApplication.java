package br.com.cpcx.web2.pedro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PedroApplication {

    @Autowired
    @Value("${application.name}")
    private String varComValorConfiguration;

//    @Autowired
//    @Qualifier("beanConfiguration")
//    private String varComValorConfiguration;

    @GetMapping("/profile")
    public String hello(){
        return varComValorConfiguration;
    }


    public static void main(String[] args) {
        SpringApplication.run(PedroApplication.class, args);
    }

}
