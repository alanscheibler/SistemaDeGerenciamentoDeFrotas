package service;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

import entities.Veiculo;
import entities.Carro;

public class VeiculoService{
    private List<Veiculo> veiculosDB;
    private Scanner scan;

    public List<Veiculo> getVeiculosDB() {
        return veiculosDB;
    }

    public VeiculoService() {
        this.veiculosDB = new ArrayList<>();
        this.scan = new Scanner(System.in);
    }

    public Veiculo save(Veiculo veiculo) throws Exception{
        if (veiculo == null)
            throw new Exception("Objeto nulo.");
        
        if (veiculo.getModelo()==null || veiculo.getModelo().isEmpty())
            throw new Exception("Campo 'Modelo' não pode ser branco.");

        if (veiculo.getMarca()==null || veiculo.getMarca().isEmpty())
            throw new Exception("Campo 'Marca' não pode ser branco.");

        if (veiculo.getPlaca()==null || veiculo.getPlaca().isEmpty())
            throw new Exception("Campo 'Placa' não pode ser branco.");

        if (veiculo.getAno() == 0)
            throw new Exception("O ano não pode ser igual a 0.");

        if (veiculo instanceof Carro) {
            Carro carro = (Carro) veiculo;
            if (carro.getNumeroPortas() == 0){
                throw new Exception("O número de portas não pode ser igual a 0.");
            }
            
        for (Veiculo veiculo2 : veiculosDB) {
            if (veiculo2.getPlaca().equalsIgnoreCase(veiculo.getPlaca())) {
                throw new Exception("Placa já cadastrada.");
            }
            }
        }

        veiculosDB.add(veiculo);
        return veiculo;
    }

    public void imprimirVeiculos(){
        cls();
        List<Veiculo> veiculos = getVeiculosDB();
        System.out.println("2 - LISTAR TODOS OS VEÍCULOS:");
        
        if (veiculos.isEmpty()){
            System.err.println("Nenhum veículo cadastrado.");
        }else{
            int contador = 1;
            for (Veiculo veiculo : veiculos) {
                System.out.print("Veículo: " + contador + veiculo);
                contador++;
            }
        }
    }
    public Optional<Veiculo> placaVeiculo(String placa){
        return veiculosDB.stream().filter(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa)).findFirst();
    }

    public void pesquisarVeiculo(){
        cls();
        System.out.print("3 - PESQUISAR VEÍCULO POR PLACA:" + "\n" + "Informe a placa que deseja pesquisar: ");
        String placaPesquisada = scan.nextLine(); 
        Optional <Veiculo> veiculo = placaVeiculo(placaPesquisada);
        if (veiculo.isPresent()){
            System.out.println(veiculo.get().toString());
        }else{
            System.out.println("Veículo não encontrado com a placa informada");
        }
        continuar();
    }

    public void removerVeiculo(String placa) throws Exception {
        Optional<Veiculo> veiculo = placaVeiculo(placa);
        if (veiculo.isPresent()){
            veiculosDB.remove(veiculo.get());
        }else{
            throw new Exception("Veículo não encontrado.");
        }
    }

    public void excluirVeiculo() {
        cls();
        System.out.println("4 - EXCLUIR VEÍCULO:");
        imprimirVeiculos();
        System.out.print("Informe a placa do veículo que deseja REMOVER: ");
        String placaExcluir = scan.nextLine();
        try {
            removerVeiculo(placaExcluir);
            System.out.println("Veículo removido som sucesso.");
        } catch (Exception e) {
            System.out.println("Veículo não encontrado com a placa informada.");
        }
        continuar();
    }  

    public void cls(){
        System.out.println("\33[H\033[2J");
    }

    public void continuar() {
        System.out.print("Pressione ENTER para voltar ao menu inicial.");
        scan.nextLine();
    }

    
}
