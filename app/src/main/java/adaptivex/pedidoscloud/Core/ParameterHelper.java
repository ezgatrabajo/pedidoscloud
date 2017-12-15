package adaptivex.pedidoscloud.Core;

import android.content.Context;

import adaptivex.pedidoscloud.Config.GlobalValues;
import adaptivex.pedidoscloud.Controller.ParameterController;
import adaptivex.pedidoscloud.Model.Parameter;

/**
 * Created by ezequiel on 3/12/2017.
 */

public class ParameterHelper {

    private Context ctx;

    public ParameterHelper(){

    }
    public ParameterHelper(Context ctx){
        this.ctx = ctx;
    }
    public boolean booleanGetStockPrecios(){
        boolean resultado = false;
        //Leer de la tabla parametros cada servicio
        ParameterController pc = new ParameterController(ctx);
        Parameter p = new Parameter();
        p = pc.abrir().findById(GlobalValues.getINSTANCIA().PARAM_GET_STOCK_PRECIOS);
        if (p!=null){
            //Si parametro es Y, se debe activar el servicio
            if (p.getValor_texto().equals("Y")) {
                resultado = true;
            };
        }
        return resultado;
    }

    public String getServiceStockPrecios(){
        String respuesta = "N";
        //Leer de la tabla parametros cada servicio
        ParameterController pc = new ParameterController(ctx);
        Parameter p = new Parameter();
        p = pc.abrir().findById(GlobalValues.getINSTANCIA().PARAM_GET_STOCK_PRECIOS);
        if (p!=null){
            //Si parametro es Y, se debe activar el servicio
            if (p.getValor_texto().equals("Y")){
                respuesta = p.getValor_texto();
            };
        }
        return respuesta;
    }

    public boolean isServiceStockPrecioWorking(){
        boolean respuesta = false;
        //Leer de la tabla parametros cada servicio
        ParameterController pc = new ParameterController(ctx);
        Parameter p = new Parameter();
        p = pc.abrir().findById(GlobalValues.getINSTANCIA().PARAM_SERVICE_STOCK_PRECIOS_WORKING);
        pc.cerrar();
        if (p!=null){
            //Si parametro es Y, se debe activar el servicio
            if (p.getValor_texto().equals("Y")){
                respuesta = true;

            };
        }
        return respuesta;
    }
    public void setServiceStockPrecioWorking(String working){

        //Leer de la tabla parametros cada servicio
        ParameterController pc = new ParameterController(ctx);
        Parameter p = new Parameter();
        p = pc.abrir().findById(GlobalValues.getINSTANCIA().PARAM_SERVICE_STOCK_PRECIOS_WORKING);
        if (p!=null){
            p.setValor_texto(working);
            pc.modificar(p);
            pc.cerrar();
        }
    }

}
