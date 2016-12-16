package repositorios;

import clases.ciudades;
import clases.pacientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import proyecto.conexion;

/**
 *
 * @author usuario
 */
public class repositoriopacientes {
    conexion con=new conexion();
     Connection conex=con.conexion();
     
     
    
    public void guardar(pacientes pact){
        PreparedStatement pstm = null;
   try{
       
       pstm = conex.prepareStatement("INSERT INTO clientes(cedula,nombre,fecha,sexo,altura,peso,tipo_sangre,alergias,telefono,domicilio,e_mail,id_ciudad)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
       pstm.setString(1,pact.getCedula());
       pstm.setString(2,pact.getNombres());
       pstm.setDate(3,pact.getFechanacimineto());
       pstm.setString(4,pact.getSexo() );
       pstm.setString(5,pact.getAltura() );
       pstm.setString(6,pact.getPeso() );
       pstm.setString(7,pact.getTiposangre() );       
       pstm.setString(8,pact.getAlergias() ); 
       pstm.setString(9,pact.getTelefono() );
       pstm.setString(10,pact.getDomicilio() );
       pstm.setString(11,pact.getE_mail() );
       pstm.setInt(12,pact.getCiudad().getId_ciudad());

       pstm.executeUpdate();
       JOptionPane.showMessageDialog(null,"Registro grabado exitosamente");
       
      
    }catch (SQLException exc){
      System.out.println("Error: " + exc.getMessage() );  
    }
    }
    //consultar
    public pacientes getPaciente(String cedula){
        pacientes pac=null;
         String sql="";
         sql="SELECT clientes.*,ciudades.* FROM clientes "+"INNER JOIN ciudades on clientes.id_ciudad=ciudades.id_ciudad where clientes";
         try{
          PreparedStatement pst=conex.prepareStatement(sql);
          pst.setString(1, "%"+cedula+"%");
          
          pst.execute();
          ResultSet rs = pst.executeQuery();
          while (rs.next()){
                ciudades ciud = new ciudades(rs.getInt(14),rs.getString(15));
                pac=new pacientes(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),ciud);
          }
             
          return pac ;
         }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }  
    return null;     
    }
    //eliminar
    public void eliminar(int id){
        PreparedStatement pst = null;
        try{
            pst = conex.prepareStatement("delete from clientes where id_cliente=?");
            pst.setInt(1, id);
            pst.executeQuery();
            JOptionPane.showMessageDialog(null,"registro elimidado exitosamente");
           
        }catch(SQLException exc){
            
            System.out.println(exc.getMessage());
        }
    } 
    //modificar
    public void modificar(String cedula, pacientes pact ){
         PreparedStatement pst = null;
        try{
            pst=conex.prepareStatement("update clientes set nombre=?,fecha=?,altura=?,peso=?,alergias=?,telefono=?,domicilio=?,e_mail=?,id_ciudad=? where id_cliente=?");
              
              pst.setString(1, pact.getNombres());
              pst.setDate(2, pact.getFechanacimineto());
              pst.setString(3, pact.getAltura());
              pst.setString(4, pact.getPeso());
              pst.setString(5, pact.getAlergias());
              pst.setString(6, pact.getTelefono());
              pst.setString(7, pact.getDomicilio());
              pst.setString(8, pact.getE_mail());
              pst.setInt(9, pact.getCiudad().getId_ciudad());
         
              pst.executeUpdate();
              JOptionPane.showMessageDialog(null,"registro grabado exitosamente");
              
        }catch(SQLException exc){
            System.out.println(exc.getMessage());
        }   
    }
 
}