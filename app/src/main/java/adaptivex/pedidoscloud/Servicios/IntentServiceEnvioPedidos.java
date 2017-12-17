package adaptivex.pedidoscloud.Servicios;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.HashMap;

import adaptivex.pedidoscloud.Config.GlobalValues;
import adaptivex.pedidoscloud.Controller.PedidoController;
import adaptivex.pedidoscloud.Model.Producto;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentServiceEnvioPedidos extends IntentService {

    private Context ctx;
    private HashMap<String,String> registro;
    private Producto producto;
    private int respuesta; //1=ok, 200=error
    private int opcion; //1 enviar Post Producto
    private ProgressDialog pDialog;
    private PedidoController pedidoCtr;
    private HelperPedidos hp;
    public IntentServiceEnvioPedidos() {
        super("IntentServiceStockPrecios");
    }

    public static void startActionGetStockPrecios(Context context) {
        Intent intent = new Intent(context, IntentServiceEnvioPedidos.class);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

        while(true) {
            final String action = intent.getAction();
            try {
                //Buscar todos los pedidos pendientes de envio
                hp = new HelperPedidos(getBaseContext(), GlobalValues.getINSTANCIA().OPTION_HELPER_ENVIO_PEDIDO_TODOS);
                hp.execute();

                Thread.sleep(10000);


            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }catch(Exception e ){
                Toast.makeText(getBaseContext(), "Error " +e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }


            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
}
