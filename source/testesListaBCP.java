import java.io.*;
import java.util.*;

class testesListaBCP{



    public static void main(String[]args){
        String PC = "0";
        String estadoProcesso = "Pronto";
        String prioridade = "2";
        String estadoRegistradorX = "78";
        String estadoRegistradorY = "2";
        String regiaoProcesso = "2";


        LinhaBCP linhaA = new LinhaBCP();

        linhaA.setterPC("4");
        linhaA.setterEstadoProcesso(estadoProcesso);
        linhaA.setterPrioridade(prioridade);
        linhaA.setterEstadoRegistradorX(estadoRegistradorX);
        linhaA.setterEstadoRegistradorY(estadoRegistradorY);
        linhaA.setterRegiaoProcesso(regiaoProcesso);


        

        ArrayList<LinhaBCP> indexes = new ArrayList<LinhaBCP>();

        indexes.add(linhaA);




        System.out.println(indexes.get(0).getterEstadoProcesso());

    }
}