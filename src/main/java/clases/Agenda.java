package clases;

import java.sql.Date;

public class Agenda {
    
    private int id;
    private int id_maquina;
    private int id_mant_prev;
    private Date fecha_inicio;
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(int id_maquina) {
        this.id_maquina = id_maquina;
    }

    public int getId_mant_prev() {
        return id_mant_prev;
    }

    public void setId_mant_prev(int id_mant_prev) {
        this.id_mant_prev = id_mant_prev;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
