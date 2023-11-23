package ar.com.avillucas.tp.entidades;

import java.util.Date;

public class SetDatos {



    /**
     * "2020-02-01",
     * -0.0882144558778446
     */
    private Date fecha;
    private Double valor;

    public SetDatos() {
    }

    public SetDatos(Date fecha, Double valor) {
        this.fecha = fecha;
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }


    @Override
    public String toString() {
        //TODO mejorar con formato  de fecha y texto
        return "fehca: " + this.fecha.toString() + " valor: " + this.valor.toString();
    }

    @Override
    public int hashCode() {
        return this.fecha.hashCode() * 15;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        SetDatos o = (SetDatos) obj;
        return this.fecha.equals(o.fecha) && this.valor.equals(o.valor);
    }
}
