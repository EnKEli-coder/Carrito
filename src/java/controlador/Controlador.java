/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Carrito;
import modelo.Cliente;
import modelo.Producto;
import ModeloDAO.ProductoDAO;
import modelo.Pago;


public class Controlador extends HttpServlet {

    ProductoDAO pdao=new ProductoDAO();
    Producto p=new Producto();
    List<Producto> productos=new ArrayList<>();
   
    List<Carrito> listaCarrito=new ArrayList<>();
    int item;
    double totalPagar=0.0;
    int cantidad=1;

    int idp;
    Carrito car;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String accion=request.getParameter("accion");
        productos=pdao.listar();
        switch(accion){
            case "AgregarCarrito":
                int pos=0;
                cantidad =1;
                idp=Integer.parseInt(request.getParameter("id"));
                p=pdao.listarId(idp);
                if(listaCarrito.size() >0){
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if(idp==listaCarrito.get(i).getIdProducto()){
                            pos =i;
                        }
                    }
                    if(idp==listaCarrito.get(pos).getIdProducto()){
                        cantidad=listaCarrito.get(pos).getCantidad()+cantidad;
                        double subtotal=listaCarrito.get(pos).getPrecioCompra()*cantidad;
                        listaCarrito.get(pos).setCantidad(cantidad);
                        listaCarrito.get(pos).setSubTotal(subtotal);
                    }else{
                        item=item+1;
                        car=new Carrito();
                        car.setItem(item);
                        car.setIdProducto(p.getId());
                        car.setNombre(p.getNombres());
                        car.setDescripcion(p.getDescripcion());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad*p.getPrecio());
                        listaCarrito.add(car);
                    }
                }else{
                    item=item+1;
                    car=new Carrito();
                    car.setItem(item);
                    car.setIdProducto(p.getId());
                    car.setNombre(p.getNombres());
                    car.setDescripcion(p.getDescripcion());
                    car.setPrecioCompra(p.getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(cantidad*p.getPrecio());
                    listaCarrito.add(car);
                }
                request.setAttribute("contador",listaCarrito.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "Delete":
                int idproducto = Integer.parseInt(request.getParameter("idp"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if (listaCarrito.get(i).getIdProducto() == idproducto) {
                        listaCarrito.remove(i);
                    }
                }
                break;
            case "ActualizarCantidad":
                int idpro= Integer.parseInt(request.getParameter("idp"));
                int cant=Integer.parseInt(request.getParameter("Cantidad"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if(listaCarrito.get(i).getIdProducto()==idpro){
                        listaCarrito.get(i).setCantidad(cant);
                        double st=listaCarrito.get(i).getPrecioCompra()*cant;
                        listaCarrito.get(i).setSubTotal(st);
                    }
                }
                break;
            case "Carrito":
                totalPagar=0.0;
                request.setAttribute("carrito", listaCarrito);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar=totalPagar+listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "Comprar":
                totalPagar = 0.0;
                idp=Integer.parseInt(request.getParameter("id"));
                p=pdao.listarId(idp);
                item=item+1;
                car=new Carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombre(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad*p.getPrecio());
                listaCarrito.add(car);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar=totalPagar+listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("contador",listaCarrito.size());
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "GenerarCompra":
                Cliente cliente = new Cliente();
                Pago pago = new Pago();
                break;
            default:
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
            try{processRequest(request, response);}catch(Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try{processRequest(request, response);}catch(Exception e){}
            
            String accion = request.getParameter("accion");

            switch (accion) {
                case "Delete":
                    int idproducto = Integer.parseInt(request.getParameter("idp"));
                    for (int i = 0; i < listaCarrito.size(); i++) {
                        if (listaCarrito.get(i).getIdProducto() == idproducto) {
                            listaCarrito.remove(i);
                        }
                    }
                    break;
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
