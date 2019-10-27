
import java.io.*;
import java.util.*;

class Preparador {

    private static String valorInicialX = "Y=0";
    private static String valorInicialY = "X=0";

    //private static LinkedList<String> BCP = new LinkedList();

    public static ArrayList<Integer> buscadorDePrioridade(int indiceDoProcesso) throws Exception {

        String enderecoPrioridades = "../processos/prioridades.txt" ;

        File file = new File(enderecoPrioridades);
        Scanner sc = new Scanner(file);
        ArrayList<Integer> todasPrioridades = new ArrayList<Integer>();

        int j = 0;
        while (sc.hasNextLine()){
            //System.out.println(sc.nextLine());
            todasPrioridades.add(Integer.parseInt(sc.nextLine()));
            j++;
        }

        //int prioridadeEncontrada = ;

        return todasPrioridades;
    }
 
    public static ArrayList<String> agrupadorDeCodigo(String endereco) throws Exception  {


        File file = new File(endereco);
        Scanner sc = new Scanner(file);
        ArrayList<String> codigo = new ArrayList<String>();
        
        int j = 0;
        while (sc.hasNextLine()){
            //System.out.println(sc.nextLine());
            codigo.add(sc.nextLine());
            j++;
        }

        return codigo;

    } 
    public static void imputProcessos()  throws Exception {

        String numeroDoProcesso = "01";

        int indiceDoProcesso = 0; 

        ArrayList<Integer> prioridadeDoProcesso = new ArrayList<Integer>();

        String enderecoProcesso = "../processos/"+numeroDoProcesso+".txt" ;

        ArrayList<String> subrotina = new ArrayList<String>();

        Processo processA = new Processo();

        subrotina = agrupadorDeCodigo(enderecoProcesso); 

        prioridadeDoProcesso = buscadorDePrioridade(indiceDoProcesso);

        System.out.println(prioridadeDoProcesso.get(indiceDoProcesso));

        // processA.mudaValores(valorInicialY);
        // processA.mudaValores(valorInicialX);

        String nomeDoProcesso = processA;

        System.out.println(nomeDoProcesso);

        // System.out.println(processA.x);
        // System.out.println(processA.y);


           
    }
	public static void main(String [] args) throws Exception {

        imputProcessos();

	}
}
