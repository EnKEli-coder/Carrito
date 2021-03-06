package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompraDAO {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r =0;

    public int GenerarCompra(Compra compra){
        int idCompras;
        String sql ="insert into compras(idCliente, idPago, FechaCompras, Monto, Estado)values(?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareCall(sql);
            ps.setInt(1, compra.getCliente().getId());
            ps.setString(2, compra.getFecha());
            ps.setDouble(3, compra.getMonto());
            ps.setString(4, compra.getEstado());
            ps.setInt(5, compra.getIdPago());
            r = ps.executeUpdate();

            sql="Select @@IDENTITY AS idCompras";
            rs=ps.executeQuery(sql);
            rs.next();
            idCompras=rs.getInt("idCompras");
            rs.close();

            for (Carrito detalle : compra.getDetallecompras()) {
                sql = "insert into detalle_compras(idProducto, idCompras, Cantidad, PrecioCompra)values(?,?,?,?)";
                ps=con.prepareStatement(sql);
                ps.setInt(1, detalle.getIdProducto());
                ps.setInt(2, idCompras);
                ps.setInt(3, detalle.getCantidad());
                ps.setDouble(4, detalle.getPrecioCompra());
                r = ps.executeUpdate();
            }
        }catch(Exception e){

        }
        return r;
    }

}
