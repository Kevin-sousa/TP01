package tp01;
import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;

public class Relatorio_meteorologico {
	
	public static Scanner get = new Scanner(System.in);
	public static double[][][] data = new double[10][12][31];
	public static DecimalFormat mascara = new DecimalFormat("0.000");
	
	static public void main(String[] args) throws InterruptedException 
	{
		Random gera_temp = new Random();
		for (int i = 0; i < 31; i++) {
	        data[9][0][i] = gera_temp.nextInt(50);
			System.out.println(data[9][0][i]);

	     }
		int opcao = 0; 
		while (opcao != 6) {
		LimpaTela(2);
		System.out.println("bem vindo ao sistema de controle meteorológico, para continuar selecione uma das seguintes opções:\n");
		System.out.println("Digite (1) - cadastrar a temperatura de um determinado mês");
		System.out.println("Digite (2) - para calcular a temperatura média do mes/ano");
		System.out.println("Digite (3) - encontrar a temperatura mínima do mês/ano");
		System.out.println("Digite (4) - encontrar a temperatura máxima do mês/ano");
		System.out.println("Digite (5) - gerar um relatório meteorológico de determinado mês/ano");
		System.out.println("Digite (6) - para encerrar o programa");
		opcao = get.nextInt();
		
        

        switch (opcao) {
        case 1:
            entrada_das_temperaturas(entrada_mes_ano());
            break;

        case 2:
            System.out.println("A temperatura média do mês informado foi: " + mascara.format(temperatura_media(entrada_mes_ano())));
            break;

        case 3:
        	temperatura_minima_com_dia(entrada_mes_ano());
            break;
        
        case 4:
        	temperatura_maxima_com_dia(entrada_mes_ano());
            break;
            
        case 5:
            relatorio(entrada_mes_ano());
            break;
            
        case 6:
        	System.out.println("obrigado por usar nossas soluções em software, tchau :)");
            break;
            
        default:
            System.out.print("\nOpção Inválida!");
            break;
    }}}
		
	
	public static int[] entrada_mes_ano() {
		 
			
		int[] mes_ano = new int[2];
		
		System.out.println("Digite o ano (2011 a 2020):");
		mes_ano[1] = get.nextInt();
		while(!validaAno(mes_ano[1])) {
			System.out.println("Ano inválido, informe um ano entre 2011 a 2020:");
			mes_ano[1] = get.nextInt();	
		}
		mes_ano[1] = mes_ano[1] - 2011;
		
		System.out.println("Digite o mês (1 a 12):");
		mes_ano[0] = get.nextInt();
		while(!validaMes(mes_ano[0])) {
			System.out.println("Mês inválido, informe o número corespondente ao mês (1 a 12):");
			mes_ano[0] = get.nextInt();
				
		}
		mes_ano[0] = mes_ano[0] - 1;
		return mes_ano;
	
	   
	}
	
	public static void entrada_das_temperaturas(int[] mes_ano) {
		
		int dia = dias_por_mes(mes_ano);
		LimpaTela(20);
		for(int i = 0; i < dia; i++) {
			   System.out.println("Informe a temperatura do dia " + (i + 1) + " do mês " + (mes_ano[0] + 1) + ":\n");
			   data[mes_ano[1]][mes_ano[0]][i] =  get.nextDouble();
			   while(!validatemp(data[mes_ano[1]][mes_ano[0]][i])) {
				   System.out.print("Temperatura Inválida, informe uma temperatura valida para o dia " + (i + 1) + "do mês " + (mes_ano[0] + 1) + ":");
				   data[mes_ano[1]][mes_ano[0]][i] =  get.nextDouble();
			   }
		   }
	}
	
	public static int dias_por_mes(int[] mes_ano) {
		
		int dia = 0;
		int mes = mes_ano[0] + 1;
		int ano = mes_ano[1] + 2011;
				
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10|| mes == 12) {
	    	dia = 31;
	    }else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
	    	 dia = 30;
	    }else if (mes == 2) {
	    	if((ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0))) {
        		dia = 29;
	    	}else { 
        		dia= 28;
	    	}
	    }
		
		return dia;
	}
	
	public static double temperatura_media(int[] mes_ano) {
		int dia = dias_por_mes(mes_ano);
		double resultado = 0;
		for(int i = 0; i < dia; i++) {
			resultado += data[mes_ano[1]][mes_ano[0]][i];
		}
		return resultado/dia;
	}
	
	public static double temperatura_minima(int[] mes_ano) {
		int dia = dias_por_mes(mes_ano);
		double minima = 201;
		for(int i = 0; i < dia; i++) {
			if (minima > data[mes_ano[1]][mes_ano[0]][i]) {
				minima = data[mes_ano[1]][mes_ano[0]][i];
			}
		}if( minima == 201) {
			minima = 0;
		}
		return minima;
	}
	
	public static double temperatura_minima_com_dia(int[] mes_ano) {
		int dia = dias_por_mes(mes_ano);
		double minima = 201;
		for(int i = 0; i < dia; i++) {
			if (minima > data[mes_ano[1]][mes_ano[0]][i]) {
				minima = data[mes_ano[1]][mes_ano[0]][i];
			}
		}if( minima == 201) {
			minima = 0;
		}
		System.out.println("A temperatura mínima do ano " + (mes_ano[1] + 2011) + " no mes " + (mes_ano[0]+1) + " foi:" + minima);
		System.out.println("E foi registrada no(s) dia(s):");
		for(int i = 0; i < dia; i++) {
			if(minima == data[mes_ano[1]][mes_ano[0]][i]){
				System.out.println((i+1) + "/" + (mes_ano[0] + 1) + "/" + (mes_ano[1] + 2011));
			}
		}
		return minima;
	}
	
	public static double temperatura_maxima(int[] mes_ano) {
		int dia = dias_por_mes(mes_ano);
		double maxima = -274;
		
		for(int i = 0; i < dia; i++) {
			if (maxima < data[mes_ano[1]][mes_ano[0]][i]) {
				maxima = data[mes_ano[1]][mes_ano[0]][i];
			}
		}if( maxima == -274) {
			maxima = 0;
		}
		return maxima;
	}
	
	public static void temperatura_maxima_com_dia(int[] mes_ano) {
		int dia = dias_por_mes(mes_ano);
		double maxima = -274;
		
		for(int i = 0; i < dia; i++) {
			if (maxima < data[mes_ano[1]][mes_ano[0]][i]) {
				maxima = data[mes_ano[1]][mes_ano[0]][i];
			}
			
		}if( maxima == -274) {
			maxima = 0;
		}
		System.out.println("A temperatura máxima do ano " + (mes_ano[1] + 2011) + " no mes " + (mes_ano[0]+1) + " foi:" + maxima);
		System.out.println("E foi registrada no(s) dia(s):");
		for(int i = 0; i < dia; i++) {
			if(maxima == data[mes_ano[1]][mes_ano[0]][i]){
				System.out.println((i+1) + "/" + (mes_ano[0] + 1) + "/" + (mes_ano[1] + 2011));
			}
		}
	}
	
	public static void relatorio(int[] mes_ano) {
		int dia = dias_por_mes(mes_ano);
		LimpaTela(3);
		for(int i = 0; i < dia; i++) {
			System.out.println("A temperatura media do dia " + (i + 1) + " do mês " + (mes_ano[0] + 1) + " foi: " + data[mes_ano[1]][mes_ano[0]][i]);
		}
		LimpaTela(5);
		System.out.println("A media de temperatura do mes foi: " + mascara.format(temperatura_media(mes_ano)));
		System.out.println("A temperatura mínima regitrada no mês : " + temperatura_minima(mes_ano));
		System.out.println("A temperatura máxima regitrada no mês :" + temperatura_maxima(mes_ano));
	
	}
	
	// validacoes 
	
	static boolean validatemp(double temp) {
		if (temp >= -273.15 && temp <= 200) {
			return true;
		} else {
			return false;
		}
	}
	
	static boolean validaAno(int ano) {
		if (ano > 2010 && ano < 2021) {
			return true;
		} else {
			return false;
		}
	}
	static boolean validaMes(int mes) {
		if (mes > 0 && mes < 13) {
			return true;
		} else {
			return false;
		}
	}
	
	//espacamento na saida de dados
	static public void LimpaTela(int x) 
	{
		for(int i = 0; i < x; i++)
		System.out.println();
	}
	
}