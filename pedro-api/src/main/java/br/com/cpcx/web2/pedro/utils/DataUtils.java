package br.com.cpcx.web2.pedro.utils;

import java.time.LocalDate;
import java.time.Period;

public class DataUtils {

    private DataUtils(){

    }

    public static Integer calcularIdade(LocalDate dataNascimento){
        Period peridoEntreDatas = Period.between(dataNascimento, LocalDate.now());
        return peridoEntreDatas.getYears();
    }

    public static boolean isMaiorIdade(LocalDate dataNascimento){
        Integer idade = DataUtils.calcularIdade(dataNascimento);
        return idade >= 18;
    }

}
