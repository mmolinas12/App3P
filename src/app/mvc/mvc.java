/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.mvc;

import app.controlador.ctlLogin;
import app.controlador.ctlRegistrar;
import app.modelo.consultaLogin;
import app.modelo.consultaRegistrar;
import app.modelo.mdLogin;
import app.modelo.mdRegistrar;
import app.vista.vistaLogin;
import app.vista.vistaRegistrar;

/**
 *
 * @author mmoli
 */
public class mvc {

    public static void main(String[] args) {

        mdLogin model = new mdLogin();
        consultaLogin modelC = new consultaLogin();
        vistaLogin view = new vistaLogin();
        ctlLogin ctrl = new ctlLogin(view, model, modelC);
        
        ctrl.iniciar();
        view.setVisible(true);

        
        /*
        mdRegistrar model_1 = new mdRegistrar();
        consultaRegistrar modelC_1 = new consultaRegistrar();
        vistaRegistrar view_1 = new vistaRegistrar();
        ctlRegistrar ctrl_1 = new ctlRegistrar(view_1, model_1, modelC_1);

        ctrl_1.iniciar();
        view_1.setVisible(true);
        */

    }
    
}
