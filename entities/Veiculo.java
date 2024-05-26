package entities;
import java.time.Year;

public abstract class Veiculo {
    String marca;
    String modelo;
    int ano;
    private String placa;

    public abstract double calcularImposto();

    public static final int MAX_TEMPO_USO = 30;

    @Override
    public String toString() {
        return " - Marca: " + this.getMarca() + " - Modelo: " + this.getModelo() + " - Ano: " + this.getAno();
    }

    
    public Veiculo(){
        this("","" ,1990, "");
    }
    public Veiculo(String marca, String modelo, int ano, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }
    public final int calcularTempoDeUso(){
        int anoAtual = Year.now().getValue();
        //int tempoUso = anoAtual - this.ano;
        //return tempoUso;
        return this.calcularTempoDeUso(anoAtual);
    }

    public int calcularTempoDeUso(int anoBase) {
        int tempoUso = anoBase - this.ano;
        return tempoUso;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    void ligar(){
        System.out.println("Veículo ligado!");
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

}