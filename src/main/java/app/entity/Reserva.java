package app.entity;

import java.io.*;
import javax.persistence.*;
import java.util.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import cronapi.rest.security.CronappSecurity;


/**
* Classe que representa a tabela RESERVA
* @generated
*/
@Entity
@Table(name = "\"RESERVA\"")
@XmlRootElement
@CronappSecurity
@JsonFilter("app.entity.Reserva")
public class Reserva implements Serializable {

    /**
    * UID da classe, necessário na serialização
    * @generated
    */
    private static final long serialVersionUID = 1L;

    /**
    * @generated
    */
    @Id
    @Column(name = "id", nullable = false, insertable=true, updatable=true)
        private java.lang.String id = UUID.randomUUID().toString().toUpperCase();

    /**
    * @generated
    */
    @ManyToOne
    @JoinColumn(name="fk_user", nullable = false, referencedColumnName = "id", insertable=true, updatable=true, foreignKey = @ForeignKey(name = ""))
        
        private User user;

    /**
    * @generated
    */
    @ManyToOne
    @JoinColumn(name="fk_livro", nullable = false, referencedColumnName = "id", insertable=true, updatable=true, foreignKey = @ForeignKey(name = ""))
        
        private Livro livro;

    /**
    * @generated
    */
    @Temporal(TemporalType.DATE)
    @Column(name = "datareserva", nullable = false, unique = false, insertable=true, updatable=true)
        
        private java.util.Date datareserva = Calendar.getInstance().getTime();

    /**
    * @generated
    */
    @Temporal(TemporalType.DATE)
    @Column(name = "dataemprestimo", nullable = true, unique = false, insertable=true, updatable=true)
        
        private java.util.Date dataemprestimo;

    /**
    * Construtor
    * @generated
    */
    public Reserva(){
    }

    /**
    * Obtém id
    * return id
    * @generated
    */
    
    public java.lang.String getId(){
        return this.id;
    }

    /**
    * Define id
    * @param id id
    * @generated
    */
    public Reserva setId(java.lang.String id){
        this.id = id;
        return this;
    }
    /**
    * Obtém user
    * return user
    * @generated
    */
    
    public User getUser(){
        return this.user;
    }

    /**
    * Define user
    * @param user user
    * @generated
    */
    public Reserva setUser(User user){
        this.user = user;
        return this;
    }
    /**
    * Obtém livro
    * return livro
    * @generated
    */
    
    public Livro getLivro(){
        return this.livro;
    }

    /**
    * Define livro
    * @param livro livro
    * @generated
    */
    public Reserva setLivro(Livro livro){
        this.livro = livro;
        return this;
    }
    /**
    * Obtém datareserva
    * return datareserva
    * @generated
    */
    
    public java.util.Date getDatareserva(){
        return this.datareserva;
    }

    /**
    * Define datareserva
    * @param datareserva datareserva
    * @generated
    */
    public Reserva setDatareserva(java.util.Date datareserva){
        this.datareserva = datareserva;
        return this;
    }
    /**
    * Obtém dataemprestimo
    * return dataemprestimo
    * @generated
    */
    
    public java.util.Date getDataemprestimo(){
        return this.dataemprestimo;
    }

    /**
    * Define dataemprestimo
    * @param dataemprestimo dataemprestimo
    * @generated
    */
    public Reserva setDataemprestimo(java.util.Date dataemprestimo){
        this.dataemprestimo = dataemprestimo;
        return this;
    }

    /**
    * @generated
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
Reserva object = (Reserva)obj;
        if (id != null ? !id.equals(object.id) : object.id != null) return false;
        return true;
    }

    /**
    * @generated
    */
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}