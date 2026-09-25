package business;

public class Aplicacao implements IAplicacao {
	protected double montante, ganho;
	@Override
	public void calcularRendimento(float valorAplicado, int prazo, float taxa) {
		this.montante = valorAplicado * (Math.pow(1+taxa, prazo));
		this.ganho = montante - valorAplicado;
	}
	public double getMontante() {
		return montante;
	}
	public double getGanho() {
		return ganho;
	}

}
