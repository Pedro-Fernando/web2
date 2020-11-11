package br.com.cpcx.web2.pedro.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidarUsuarioAspect {

//    @Before("execution(public * br.com.cpcx.web2.pedro.controller..*(..)) && @annotation(br.com.cpcx.web2.pedro.annotaition.IsUsuarioAutorizado)")
    public void validarLoginESenha(JoinPoint joinPoint){
//        todo estudar JoinPoint para  pegar os valores da anotação
//        primeiro - pegar método que use minha annotation
//        segundo  - Olhar parametro do método que use minha annotation
//        terceiro - pegar esse valor e validar
        System.out.println("Teste\n\n\n\n\n");
    }
}
