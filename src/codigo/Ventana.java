/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
//JFreeChart Imports
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Marta
 * 
 * //API key; AIzaSyC_Dqccdq_pULB9hvTQY_V44N8i7Ye4cOQ
 * 
 * //URL; https://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034&key=YOUR_API_KEY
 * //https://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034%7C40.463372,%20-3.998461&key=AIzaSyC_Dqccdq_pULB9hvTQY_V44N8i7Ye4cOQ
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    
//    Perfil chart = new Perfil("Elevación" , "Perfil del Terreno Entre Dos Puntos");
      Perfil chart;
      
      double[] datos; //Array que llenamos y le pasamos al gráfico
      int contador; //Contador para el array del JSON
    
    
    public Ventana() {
        initComponents();
        
        

//      chart.pack( );
//      RefineryUtilities.centerFrameOnScreen( chart );
//      chart.setVisible( false );
//      chart.setAlwaysOnTop(true);
      
      
        
        
    }
    
    public String[] parseaJSON(String lat1, String lon1, String lat2, String lon2){
       String[] resultado = new String[contador];
       int contadorArray=0;
       String url = "https://maps.googleapis.com/maps/api/elevation/json?locations="+ lat1 + "," + lat2 + "%7C"+ lat2 + ",%20" + lon2 + "&key=AIzaSyC_Dqccdq_pULB9hvTQY_V44N8i7Ye4cOQ";


       double lati = Double.valueOf(lat1);
       double longi= Double.valueOf(lon1);
       
         double a= Double.valueOf(lat1);
         double b= Double.valueOf(lon1);
         double c= Double.valueOf(lat2);
         double d= Double.valueOf(lon2);
         
         int latitude1 =  (int)a;
         int longitude1 = (int)b;
         int latitude2 = (int)c;
         int longitude2 = (int)d;
         boolean latIgual= false;
         boolean lonIgual = false;
       
		try {

                        
                        for(int i=0; i<resultado.length;i++){
                            String valorLatitude = String.valueOf(lati);
                            String valorLongitude = String.valueOf(longi);
                            
                            String url2 = "https://maps.googleapis.com/maps/api/elevation/json?locations="+valorLatitude+ ","+valorLongitude+"&key=AIzaSyC_Dqccdq_pULB9hvTQY_V44N8i7Ye4cOQ";
                           
                            String elevacionJson = IOUtils.toString(new URL(url2));
			    JSONObject elevacionJsonObject = (JSONObject) JSONValue.parseWithException(elevacionJson);
			   // get the title
			   System.out.println(elevacionJsonObject.get("status"));
			   // get the data
			   JSONArray elevacionArray = (JSONArray) elevacionJsonObject.get("results");
			   // get the first genre
			   JSONObject elevacion1;
                           elevacion1 = (JSONObject) elevacionArray.get(0);
                           resultado[contadorArray]= elevacion1.get("elevation").toString();
                           contadorArray++;
                           
                           if(latitude1 == latitude2){
                             latIgual = true;
                           }             
                           if(longitude1 == longitude2){
                             lonIgual = true;
                           }
                           if(latitude1 < latitude2 && latIgual == false){
                              latitude1++;
                              lati++;
                           }else if(latitude1 > latitude2 && latIgual == false){
                               latitude2++;
                               lati--;
                           }

                           if(longitude1 < longitude2 && lonIgual == false){
                              longitude1++;
                              longi++;
                           }else if(longitude1 > longitude2 && lonIgual == false){
                              longitude2++;
                              longi--;
                           }
                           
                           System.out.println(resultado[i]);
                        }
                      
                        
                        
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
                
                return resultado;
	}
    
    //Método para pasar el array de strings a double.
    public double[] modificaArray(String[] elevacion){
        
        double[] elev = new double[elevacion.length];
        
        for(int i=0; i<elev.length; i++){
            elev[i]=Double.valueOf(elevacion[i]);
        }
        datos=elev;
        
        return elev;
    
    }
    
     public DefaultCategoryDataset createDataset(double[] elev) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      
      int puntoDelMapa=0;
      
 
      for(int i=0; i<elev.length;i++){
          String puntoMapa = String.valueOf(puntoDelMapa);
          dataset.addValue(elev[i], "Kilometers", puntoMapa);
          puntoDelMapa++;
      }
      
////      dataset.addValue( elev[0] , "schools" , "0" );
////      dataset.addValue( elev[1] , "schools" , "1" );
//      
      return dataset;
     
   }
     
     //Método para obtener un contador.
     
     public int contadorInt(String lat1, String lon1, String lat2, String lon2){
         double a= Double.valueOf(lat1);
         double b= Double.valueOf(lon1);
         double c= Double.valueOf(lat2);
         double d= Double.valueOf(lon2);
         
         int latitude1 =  (int)a;
         int longitude1 = (int)b;
         int latitude2 = (int)c;
         int longitude2 = (int)d;
         boolean latIgual= false;
         boolean lonIgual = false;
         
         
         while(latitude1!=latitude2 || longitude1 != longitude2){
              if(latitude1 == latitude2){
                latIgual = true;
              }             
              if(longitude1 == longitude2){
                lonIgual = true;
              }
              if(latitude1 < latitude2 && latIgual == false){
                 latitude1++;
              }else if(latitude1 > latitude2 && latIgual == false){
                  latitude2++;
              }
              
              if(longitude1 < longitude2 && lonIgual == false){
                 longitude1++;
              }else if(longitude1 > longitude2 && lonIgual == false){
                 longitude2++;
              }
              
              contador++;
                
         }
         
         return contador;
     }
     
     //Reservado
     
//     public String[] parseaJSON(String lat1, String lon1, String lat2, String lon2){
//       String[] resultado = new String[contador];
//       int contadorArray=0;
//       String url = "https://maps.googleapis.com/maps/api/elevation/json?locations="+ lat1 + "," + lat2 + "%7C"+ lat2 + ",%20" + lon2 + "&key=AIzaSyC_Dqccdq_pULB9hvTQY_V44N8i7Ye4cOQ";
////       String url2 = "https://maps.googleapis.com/maps/api/elevation/json?locations="+latitude1+ ","+longitude1+"&key=AIzaSyC_Dqccdq_pULB9hvTQY_V44N8i7Ye4cOQ";
//
//       
//       
//		try {
//			String elevacionJson = IOUtils.toString(new URL(url));
//			JSONObject elevacionJsonObject = (JSONObject) JSONValue.parseWithException(elevacionJson);
//			// get the title
//			System.out.println(elevacionJsonObject.get("status"));
//			// get the data
//			JSONArray elevacionArray = (JSONArray) elevacionJsonObject.get("results");
//			// get the first genre
//			JSONObject elevacion1;
//                       // JSONObject elevacion2 = (JSONObject) elevacionArray.get(1);
//                        for(int i=0; i<resultado.length; i++){
//                            elevacion1 = (JSONObject) elevacionArray.get(i);
//                            resultado[i]= elevacion1.get("elevation").toString();
//                            System.out.println(resultado[i]);
//                        }
//                        
//      
//                        
//			
//		} catch (IOException | ParseException e) {
//			e.printStackTrace();
//		}
//                
//                return resultado;
//	}
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Cargar Perfil");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jTextField1.setText("-104.9847034");

        jTextField2.setText("39.7391536");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LONGITUD");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LATITUD");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LONGITUD");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LATITUD");

        jTextField3.setText("-3.998461");

        jTextField4.setText("40.463372");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Coordenadas Punto 2");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Coordenadas Punto 1");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ELEVACIÓN");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ELEVACIÓN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5))))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(282, 282, 282)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here:
        //Obtenemos los parámetros deseados a partir de las cajas.
        String lat= jTextField2.getText();
        String lon= jTextField1.getText();
        String lat2 = jTextField4.getText();
        String lon2= jTextField3.getText();
        
        contadorInt(lat,lon, lat2, lon2);
        System.out.println("Valor de contador" +contador);
        
        
        //Obtenemos los datos del JSON pasándole los parámetros deseados y los guardamos en un array
        //de strings.
        String[] elevacion = parseaJSON(lat,lon, lat2, lon2);
        //Ponemos los datos de elevación en sus respectivas cajas.
        jTextField6.setText(elevacion[0]);
        jTextField5.setText(elevacion[1]);
        //Vamos a pasar de string a double los valores del array para poder trabajar con ellos y elaborar la gráfica.
        double[] elev = modificaArray(elevacion);
        //Creamos el DataSet que le vamos a pasar como parámetro de entrada al constructor.
        DefaultCategoryDataset dataSet = createDataset(elev);
        
        
        //Construimos el gráfico.
       
        chart= new Perfil("Elevación" , "Perfil del Terreno Entre Dos Puntos", dataSet);
        
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
        chart.setAlwaysOnTop(true);
        
        //
        
        
         
      
        
        
       // chart.obtenerDatos(elev);
        
      
        
        //Hacemos visible el chart.
        //chart.setVisible(true);
        
        
    }//GEN-LAST:event_jButton2MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
