package modelo;

import java.util.List;

public class Compra {
    
    private int id;
    private Cliente cliente;
    private int idPago;
    private String fecha;
    private Double monto;
    private String estado;

    private List<Carrito>detallecompras;

    public Compra() {
    }

    public Compra(int id, Cliente cliente, int idPago, String fecha, Double monto, String estado, List<Carrito> detallecompras) {
        this.id = id;
        this.cliente = cliente;
        this.idPago = idPago;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.detallecompras = detallecompras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Carrito> getDetallecompras() {
        return detallecompras;
    }

    public void setDetallecompras(List<Carrito> detallecompras) {
        this.detallecompras = detallecompras;
    }



}
