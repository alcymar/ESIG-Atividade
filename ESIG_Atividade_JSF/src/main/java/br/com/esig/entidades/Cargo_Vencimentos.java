package br.com.esig.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@org.hibernate.annotations.Entity
@Entity
@Table(name = "Cargo_Vencimentos")
public class Cargo_Vencimentos implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Cargo_ID", referencedColumnName = "ID")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "Vencimento_ID", referencedColumnName = "ID")
    private Vencimentos vencimento;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Vencimentos getVencimento() {
        return vencimento;
    }

    public void setVencimento(Vencimentos vencimento) {
        this.vencimento = vencimento;
    }
    
    public Cargo_Vencimentos() {}
    
    
}
