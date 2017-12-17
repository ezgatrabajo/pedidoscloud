package adaptivex.pedidoscloud.Core;

import android.content.Context;
import android.widget.Toast;

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


    public boolean isDownloadDatabase() {
        boolean isdownloaddatabase = false;
        try {
            ParameterController pc = new ParameterController(ctx);

            Parameter p= pc.abrir().findById(GlobalValues.getINSTANCIA().PARAM_DOWNLOAD_DATABASE);

            if (p==null){
                p = new Parameter();
                p.setId(GlobalValues.getINSTANCIA().PARAM_DOWNLOAD_DATABASE);
                p.setValor_texto("N");
                pc.abrir().agregar(p);
            }

            if (p.getValor_texto().equals("Y")){
                isdownloaddatabase= true;
            }
            return isdownloaddatabase;
        }catch(Exception e ){
            Toast.makeText(ctx,"Error: " + e.getMessage().toString(),Toast.LENGTH_LONG).show();
            return false;
        }
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
