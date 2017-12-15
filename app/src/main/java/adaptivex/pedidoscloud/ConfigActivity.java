package adaptivex.pedidoscloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import adaptivex.pedidoscloud.Config.GlobalValues;
import adaptivex.pedidoscloud.Controller.ParameterController;
import adaptivex.pedidoscloud.Core.ParameterHelper;
import adaptivex.pedidoscloud.Model.Parameter;
import adaptivex.pedidoscloud.Servicios.HelperMemo;
import adaptivex.pedidoscloud.Servicios.IntentServiceStockPrecios;

public class ConfigActivity extends AppCompatActivity {
    protected ToggleButton tb;
    protected Intent intentServiceStockPrecios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tb = (ToggleButton) findViewById(R.id.toggleButtonStockPrecios);
        findViewById(R.id.toggleButtonStockPrecios).setOnClickListener(mClickListener);
        intentServiceStockPrecios = new Intent(ConfigActivity.this, IntentServiceStockPrecios.class);
        ParameterHelper ph = new ParameterHelper(getBaseContext());
        tb.setChecked(ph.booleanGetStockPrecios());
    }
    View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            try{

                ParameterController pc = new ParameterController(getBaseContext());
                Parameter p = new Parameter();
                p = pc.abrir().findById(GlobalValues.getINSTANCIA().PARAM_GET_STOCK_PRECIOS);

                switch (v.getId()){
                    //StockPrecios
                    case R.id.toggleButtonStockPrecios:
                        if (tb.isChecked()){
                            if (p!=null){
                                p.setValor_texto("Y");
                                pc.abrir().modificar(p);
                                pc.cerrar();
                            }
                             getBaseContext().startService(intentServiceStockPrecios);
                        }else{
                            getBaseContext().stopService(intentServiceStockPrecios);
                            if (p!=null){
                                p.setValor_texto("N");
                                pc.abrir().modificar(p);
                                pc.cerrar();
                            }
                        }
                        break;

                    case R.id.btnDescargarRecordatorio:
                        HelperMemo hm = new HelperMemo(getBaseContext());
                        hm.execute();

                        break;
                }
            }catch(Exception e){
                Toast.makeText(getBaseContext(), "Error " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }

        }
    };

}
