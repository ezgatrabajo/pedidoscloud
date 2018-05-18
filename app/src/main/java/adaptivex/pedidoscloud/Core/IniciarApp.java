package adaptivex.pedidoscloud.Core;

/**
 * Created by Ezequiel on 05/03/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import adaptivex.pedidoscloud.Config.GlobalValues;
import adaptivex.pedidoscloud.Controller.ClienteController;
import adaptivex.pedidoscloud.Controller.ParameterController;
import adaptivex.pedidoscloud.Controller.ProductoController;
import adaptivex.pedidoscloud.Controller.UserController;
import adaptivex.pedidoscloud.Model.CategoriaDataBaseHelper;
import adaptivex.pedidoscloud.Model.ClienteDataBaseHelper;
import adaptivex.pedidoscloud.Model.HojarutaDataBaseHelper;
import adaptivex.pedidoscloud.Model.HojarutadetalleDataBaseHelper;
import adaptivex.pedidoscloud.Model.MarcaDataBaseHelper;
import adaptivex.pedidoscloud.Model.MemoDataBaseHelper;
import adaptivex.pedidoscloud.Model.Parameter;
import adaptivex.pedidoscloud.Model.ParameterDataBaseHelper;
import adaptivex.pedidoscloud.Model.PedidoDataBaseHelper;
import adaptivex.pedidoscloud.Model.PedidodetalleDataBaseHelper;
import adaptivex.pedidoscloud.Model.ProductoDataBaseHelper;
import adaptivex.pedidoscloud.Model.User;
import adaptivex.pedidoscloud.Model.UserDataBaseHelper;
import adaptivex.pedidoscloud.Servicios.HelperCategorias;
import adaptivex.pedidoscloud.Servicios.HelperClientes;
import adaptivex.pedidoscloud.Servicios.HelperHojarutas;
import adaptivex.pedidoscloud.Servicios.HelperMarcas;
import adaptivex.pedidoscloud.Servicios.HelperMemo;
import adaptivex.pedidoscloud.Servicios.HelperProductos;

public  class IniciarApp  {
    private Context context;


    public IniciarApp(Context c ){
        //leer valor de parametro
        setContext(c);
    }
    public void logout(){
        try{
            UserController uc = new UserController(this.getContext());
            User u = uc.abrir().findUser();
            if (u != null){
                u.setLogued("N");
                uc.abrir().editDB(u);
            }
        }catch(Exception e){
            Log.d("IniciarAPP", e.getMessage());
        }
    }


    public  boolean  iniciarBD(){
        try{
            SQLiteDatabase db;
            //Tablas
            ClienteDataBaseHelper dba = new ClienteDataBaseHelper(this.getContext());
            db = dba.getWritableDatabase();
            db.execSQL(dba.DROP_TABLE);
            db.execSQL(dba.CREATE_TABLE);
            db.close();

            ProductoDataBaseHelper dbp = new ProductoDataBaseHelper(this.getContext());
            db = dbp.getWritableDatabase();
            db.execSQL(dbp.DROP_TABLE);
            db.execSQL(dbp.CREATE_TABLE);
            db.close();

            MarcaDataBaseHelper m = new MarcaDataBaseHelper(getContext());
            db = m.getWritableDatabase();
            db.execSQL(m.DROP_TABLE);
            db.execSQL(m.CREATE_TABLE);
            db.close();

            CategoriaDataBaseHelper ca = new CategoriaDataBaseHelper(getContext());
            db = ca.getWritableDatabase();
            db.execSQL(ca.DROP_TABLE);
            db.execSQL(ca.CREATE_TABLE);
            db.close();

            PedidoDataBaseHelper pe = new PedidoDataBaseHelper(getContext());
            db = pe.getWritableDatabase();
            db.execSQL(pe.DROP_TABLE);
            db.execSQL(pe.CREATE_TABLE);
            db.close();


            PedidodetalleDataBaseHelper ped = new PedidodetalleDataBaseHelper(getContext());
            db = ped.getWritableDatabase();
            db.execSQL(ped.DROP_TABLE);
            db.execSQL(ped.CREATE_TABLE);
            db.close();



            ParameterDataBaseHelper par = new ParameterDataBaseHelper(getContext());
            db = par.getWritableDatabase();
            db.execSQL(par.DROP_TABLE);
            db.execSQL(par.CREATE_TABLE);
            db.close();


            UserDataBaseHelper userdb = new UserDataBaseHelper(getContext());
            db = userdb.getWritableDatabase();
            db.execSQL(userdb.DROP_TABLE);
            db.execSQL(userdb.CREATE_TABLE);
            db.close();

            return true;
        }catch(Exception e ){
            Log.println(Log.DEBUG,"IniciarrApp: ", e.getMessage());
            Toast.makeText(context,"IniciarApp:"+e.getMessage(), Toast.LENGTH_LONG);
            return false;
        }

    }



    public boolean isInstalled(){
        //Leer Archivo de sistema el parametro INSTALLED
        try {
            boolean respuesta = false;
            ParameterController pc = new ParameterController(getContext());
            Parameter p = new Parameter();
            p = pc.abrir().findByNombre(GlobalValues.getINSTANCIA().PARAM_INSTALLED);
            if (p != null) {
                if (p.getValor_texto().equals("Y")) {
                    respuesta = true;
                }
            }
            return respuesta;
        }catch(Exception e ){
            Log.d("IniciarApp", e.getMessage());
            return false;
        }
    }

    public boolean setInstalledDatabase(){
        //Leer Archivo de sistema el parametro INSTALLED
        try {
            boolean respuesta = false;
            ParameterController pc = new ParameterController(getContext());
            Parameter p = new Parameter();
            p = pc.abrir().findByNombre(GlobalValues.getINSTANCIA().PARAM_INSTALLED);
            if (p != null) {
                p.setValor_texto("Y");
                pc.abrir().modificar(p);
                respuesta = true;
            }
            return respuesta;
        }catch(Exception e ){
            Log.d("IniciarApp", e.getMessage());
            return false;
        }
    }



    public boolean loginRemember(User user){
        try{
        /* Lee parametros, y los setea con el valor del usuario. Si no existen, los crea */
            boolean respuesta = true;
            //ParameterController pc = new ParameterController(this.getContext());
            UserController uc = new UserController(this.getContext());

            User u = uc.abrir().findUser();

            user.setLogued("Y");
            if (u == null){
                uc.abrir().addDB(user);
            }else{
                uc.abrir().editDB(user);
            }

            return respuesta;
        }catch(Exception e){
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

    }


    public boolean isLoginRemember(){
        try{
            boolean respuesta = false;
            UserController uc = new UserController(this.getContext());
            User u = uc.abrir().findUser();

            if (u!=null){
                if(u.getLogued().equals("Y")){
                    respuesta = true;
                    GlobalValues.getINSTANCIA().setUserlogued(u);
                };
            }
            return respuesta;
        }catch(Exception e){
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public boolean crearParametersLogin(){
        try{
            ParameterController pc = new ParameterController(this.getContext());

            Parameter p = new Parameter();
            p.setNombre("user_id");
            p.setValor_integer(GlobalValues.getINSTANCIA().getUserlogued().getId());
            pc.abrir().agregar(p);

            p = new Parameter();
            p.setNombre("entidad_id");
            p.setValor_integer(GlobalValues.getINSTANCIA().getUserlogued().getEntidad_id());
            pc.abrir().agregar(p);

            p = new Parameter();
            p.setNombre("group_id");
            p.setValor_integer(GlobalValues.getINSTANCIA().getUserlogued().getGroup_id());
            pc.abrir().agregar(p);

            return true;
        }catch(Exception e){
            Log.d("IniciarApp", e.getMessage());
            return false;
        }
    }

    public boolean downloadDatabase(){
        try {
            HelperMarcas m = new HelperMarcas(getContext());
            m.execute();

            HelperCategorias ca = new HelperCategorias(getContext());
            ca.execute();

            HelperClientes c = new HelperClientes(getContext());
            c.execute();

            HelperProductos p = new HelperProductos(getContext());
            p.execute();

            HelperHojarutas hd = new HelperHojarutas(getContext());
            hd.execute();

            HelperMemo hp = new HelperMemo(getContext());
            hp.execute();

            ParameterController pc  = new ParameterController(getContext());
            Parameter param1 = pc.abrir().findByNombre(GlobalValues.getINSTANCIA().PARAM_DOWNLOAD_DATABASE);
            param1.setValor_texto("Y");
            pc.abrir().modificar(param1);
            pc.cerrar();

            return true;
        }catch (Exception e ){
          Log.d("IniciarAPP", e.getMessage());
            Toast.makeText(context, "Error " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean isDatabaseDownload(){
        try {
            boolean respuesta = false;
            ClienteController cc = new ClienteController(getContext());
            ProductoController proc = new ProductoController(getContext());
            if (cc.count() < 1 || proc.count() < 1) {
                respuesta = false;
            } else {
                respuesta = true;
            }
            return respuesta;
        }catch(Exception e ){
            Log.d("IniciarApp", e.getMessage());
            return false;
        }

    }


    public boolean descargarData(){
        /* Va a descargar toda la info desde el sitio web */
        //Marcas
        try {
            HelperMarcas hm = new HelperMarcas(getContext());
            hm.execute();

            //Categorias
            HelperCategorias hc = new HelperCategorias(getContext());
            hc.execute();

            HelperProductos hp = new HelperProductos(getContext());
            hp.execute();

            HelperClientes hcli = new HelperClientes(getContext());
            hcli.execute();
            return true;

        }catch(Exception e ){
            Log.d("IniciarApp", e.getMessage());
            return false;
        }
    }

    public void descargarImagenes(){

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
