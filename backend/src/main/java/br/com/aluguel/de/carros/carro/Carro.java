package br.com.aluguel.de.carros.carro;

import javax.persistence.*;

@Entity
@Table(name = "carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Long idAlugador;
    private float kmsRodados;
    private String documentoCarro;
    private String tipoCombustivel;
    private String marca;
    private String modelo;
    private String placa;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAlugador() {
        return idAlugador;
    }

    public void setIdAlugador(Long idAlugador) {
        this.idAlugador = idAlugador;
    }

    public float getKmsRodados() {
        return kmsRodados;
    }

    public void setKmsRodados(float kmsRodados) {
        this.kmsRodados = kmsRodados;
    }

    public String getDocumentoCarro() {
        return documentoCarro;
    }

    public void setDocumentoCarro(String documentoCarro) {
        this.documentoCarro = documentoCarro;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
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

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", idAlugador=" + idAlugador +
                ", kmsRodados=" + kmsRodados +
                ", documentoCarro='" + documentoCarro + '\'' +
                ", tipoCombustivel='" + tipoCombustivel + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
