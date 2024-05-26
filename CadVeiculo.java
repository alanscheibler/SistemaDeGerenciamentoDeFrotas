import java.util.Scanner;

import entities.Carro;
import entities.Moto;
import entities.Veiculo;
import service.VeiculoService;


public class CadVeiculo {
    private static Scanner scan;
    private static VeiculoService veiculoService;
    public static void main (String[] args) {
        scan = new Scanner(System.in);
        veiculoService = new VeiculoService();
        int opcao ;
        do {
            veiculoService.cls();
            System.out.println("Sistema de gerenciamento de Frotas"+"\n"+"Escolha uma das opções:");
            System.out.print("  1 - Cadastrar novo veículo."+"\n"+"  2 - Listar todos os veículos."+"\n"+"  3 - Pesquisar veículo por placa."+"\n"+"  4 - Remover veículo."+"\n"+"  0 - Sair."+"\n"+"Digite a opção desejada: ");
            do {
                if (scan.hasNextInt()){
                    opcao = scan.nextInt();
                    if (opcao >= 0 && opcao <= 4){
                        break;
                    }
                }
                scan.nextLine();
                System.out.print("Digite um número dentro das opções acima: ");
            } while (true);

            scan.nextLine();

            switch (opcao) {
                case 1:
                    save();
                    break;

                case 2:
                    veiculoService.imprimirVeiculos();
                    veiculoService.continuar();
                    break;

                case 3:
                    veiculoService.pesquisarVeiculo();
                    break;
            
                case 4:
                    veiculoService.excluirVeiculo();
                    break;

                case 0:
                    veiculoService.cls();
                    System.out.println("Volte logo!");
                    opcao = 0;
                    break;
        }
        } while (opcao != 0);
    }

    public static void save(){
        Veiculo veiculoAdd;
        veiculoService.cls();
      
        System.out.print("1 - CADASTRAR NOVO VEÍCULO"+ "\n" +"Digite o tipo do veículo a ser cadastrado:"+ "\n"+ "  (1) - Carro" + "\n" + "  (2) - Moto"+ "\n" +"Digite a opção desejada: ");
        int tipoVeiculo;
        do {
            if (scan.hasNextInt()){
                tipoVeiculo = scan.nextInt();
                if (tipoVeiculo >= 1 && tipoVeiculo <=2)
                break;
            }
            scan.nextLine();
            System.out.print("Digite um número dentro das opções acima: ");
        } while (true);
        scan.nextLine();
        
        System.out.print("Digite a marca do veículo: ");
        String marca = scan.nextLine();
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scan.nextLine();
        
        int ano = 0;
        boolean anoValido = false;
        while (!anoValido) {
            System.out.print("Digite o ano do veículo: ");
            if (scan.hasNextInt()){
                ano = scan.nextInt();
                scan.nextLine();
                anoValido = true;
            }else {
                System.out.println("Ano inválido. Por favor, digite um número inteiro.");
                scan.nextLine();
            }
        }
                
        System.out.print("Digite a placa do veículo: ");
        String placa = scan.nextLine();

        if (tipoVeiculo == 1){
            System.out.print("Digite o número de portas: ");
            int numeroPortas = scan.nextInt();
            scan.nextLine();
            veiculoAdd = new Carro (marca, modelo, ano, placa, numeroPortas);
        }else {
            System.out.print("A moto possui partida elétrica? (1) - Sim // (2) - Não: ");
            int partidaEletrica = scan.nextInt();
            scan.nextLine();
            boolean partida = partidaEletrica == 1 ? true : false;
            veiculoAdd = new Moto (marca, modelo, ano, placa, partida);
        }

        try{
            veiculoService.save(veiculoAdd);
            System.out.println("Veiculo cadastrado com sucesso!");
        }catch(Exception e) {
            System.out.println("NÃO FOI POSSÍVEL CADASTRAR O VEÍCULO:");
            System.out.println(e.getMessage());
        }
        veiculoService.continuar();
    }
}
