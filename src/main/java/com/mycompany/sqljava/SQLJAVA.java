package com.mycompany.sqljava;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;

public class SQLJAVA {

    public static void main(String[] args) {
        /*
        CConexion objetoConexion = new CConexion();
        objetoConexion.estableceConexion();
        */
        try {
            UIManager.setLookAndFeel( new FlatMacLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        InterfazDietas form = new InterfazDietas();
        form.setVisible(true);
        
    }
}
