package entities;
public final class Moto extends Veiculo {
    private boolean partidaEletrica;

    @Override
    public double calcularImposto() {
        return 500.0;
    }

    @Override
    public String toString() {
        var partida = this.getPartidaEletrica() ? "Sim" : "Não";
        return super.toString() + " - Partida Elétrica: " + partida + "\n";
    }

    public boolean getPartidaEletrica() {
        return partidaEletrica;
    }

    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }

    public Moto(String marca, String modelo, int ano, String placa, boolean partidaEletrica) {
        super(marca, modelo, ano, placa);
        this.partidaEletrica = partidaEletrica;
    }
    
    
}
