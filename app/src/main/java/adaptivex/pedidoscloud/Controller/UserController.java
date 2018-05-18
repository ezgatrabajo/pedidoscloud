package adaptivex.pedidoscloud.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import adaptivex.pedidoscloud.Config.GlobalValues;
import adaptivex.pedidoscloud.Model.User;
import adaptivex.pedidoscloud.Model.UserDataBaseHelper;
import adaptivex.pedidoscloud.Servicios.HelperUser;

/**
 * Created by ezequiel on 28/05/2016.
 */
public class UserController extends AppController {
    private User user;
    private User[] users;
    private boolean wreturn;
    private String tabla = "User";




    //Obtener datos del usuario guardado en la base de datos
    public User getUserDB(){
        try{
            UserController pc = new UserController(this.getContext());
            User u = pc.abrir().findUser();
            return u;
        }catch(Exception e){
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }








    public boolean add(User userParam){

        //cargar valores
        ContentValues valores = new ContentValues();
        valores.put("username",userParam.getUsername());
        valores.put("email", userParam.getEmail());
        valores.put("password",userParam.getPassword());

        try {
            this.getConn().add(tabla, valores);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wreturn;

    }

    public boolean login(String username, String pass){
        boolean result = false;
        HelperUser hu = new HelperUser(this.getContext());
        //hu.setUser(this.getUser());
        hu.execute();


        return result;

    }
    public boolean isUserLogin(){
        if (GlobalValues.getINSTANCIA().getUSER_ID_LOGIN() < 1){
            return false;
        }else{
            return true;
        }
    }


    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }





    /*      DATABASE        */
    private Context context;
    private UserDataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserController(Context context)
    {
        this.context = context;
    }

    public UserController abrir() throws SQLiteException
    {
        dbHelper = new UserDataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public long addDB(User item)
    {
        try {
            ContentValues valores = new ContentValues();

            valores.put(UserDataBaseHelper.ID, item.getId());
            valores.put(UserDataBaseHelper.USERNAME, item.getUsername());
            valores.put(UserDataBaseHelper.ENTIDAD_ID, item.getEntidad_id());
            valores.put(UserDataBaseHelper.GROUP_ID, item.getGroup_id());
            valores.put(UserDataBaseHelper.EMAIL, item.getEmail());
            valores.put(UserDataBaseHelper.LOCALIDAD, item.getLocalidad());
            valores.put(UserDataBaseHelper.CALLE, item.getCalle());
            valores.put(UserDataBaseHelper.NRO, item.getNro());
            valores.put(UserDataBaseHelper.PISO, item.getPiso());
            valores.put(UserDataBaseHelper.TELEFONO, item.getTelefono());
            valores.put(UserDataBaseHelper.CONTACTO, item.getContacto());
            valores.put(UserDataBaseHelper.CONTACTO, item.getContacto());
            valores.put(UserDataBaseHelper.LOGUED, item.getLogued());
            valores.put(UserDataBaseHelper.ID_ANDROID, GlobalValues.getINSTANCIA().ID_ANDROID);

            return db.insert(UserDataBaseHelper.TABLE_NAME, null, valores);
        }catch (Exception e){
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return -1;
        }

    }



    public void editDB(User item)
    {
        try {
            String[] argumentos = new String[] {String.valueOf(GlobalValues.getINSTANCIA().ID_ANDROID)};

            ContentValues valores = new ContentValues();

            valores.put(UserDataBaseHelper.ID, item.getId());

            valores.put(UserDataBaseHelper.USERNAME, item.getUsername());
            valores.put(UserDataBaseHelper.ENTIDAD_ID, item.getEntidad_id());
            valores.put(UserDataBaseHelper.GROUP_ID, item.getGroup_id());
            valores.put(UserDataBaseHelper.EMAIL, item.getEmail());
            valores.put(UserDataBaseHelper.LOCALIDAD, item.getLocalidad());
            valores.put(UserDataBaseHelper.CALLE, item.getCalle());
            valores.put(UserDataBaseHelper.NRO, item.getNro());
            valores.put(UserDataBaseHelper.PISO, item.getPiso());
            valores.put(UserDataBaseHelper.TELEFONO, item.getTelefono());
            valores.put(UserDataBaseHelper.CONTACTO, item.getContacto());
            valores.put(UserDataBaseHelper.LOGUED, item.getLogued());
            valores.put(UserDataBaseHelper.ID_ANDROID, GlobalValues.getINSTANCIA().ID_ANDROID);

            db.update(UserDataBaseHelper.TABLE_NAME,
                    valores,
                    UserDataBaseHelper.ID_ANDROID + " = ?",
                    argumentos);
        }catch (Exception e){
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    public void deleteDB(User item)
    {
        try{

            db.delete(UserDataBaseHelper.TABLE_NAME, null, null);
        }catch (Exception e){
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public User findById(Integer id)
    {
        User registro = new User();
        String[] campos = {
                UserDataBaseHelper.ID,
                UserDataBaseHelper.USERNAME,
                UserDataBaseHelper.ENTIDAD_ID,
                UserDataBaseHelper.GROUP_ID,
                UserDataBaseHelper.EMAIL,
                UserDataBaseHelper.LOCALIDAD,
                UserDataBaseHelper.CALLE,
                UserDataBaseHelper.NRO,
                UserDataBaseHelper.PISO,
                UserDataBaseHelper.TELEFONO,
                UserDataBaseHelper.CONTACTO
        };
        String[] argumentos = {String.valueOf(id)};

        Cursor resultado = db.query(UserDataBaseHelper.TABLE_NAME, campos,
                UserDataBaseHelper.ID + " = ?", argumentos, null, null, null);
        if (resultado != null)
        {
            resultado.moveToFirst();
            registro = parseObjectFromRecord(resultado);
        }
        return registro;
    }

    public User findUser()
    {
        User registro = new User();
        String[] campos = {
                UserDataBaseHelper.ID,
                UserDataBaseHelper.USERNAME,
                UserDataBaseHelper.ENTIDAD_ID,
                UserDataBaseHelper.GROUP_ID,
                UserDataBaseHelper.EMAIL,
                UserDataBaseHelper.LOCALIDAD,
                UserDataBaseHelper.CALLE,
                UserDataBaseHelper.NRO,
                UserDataBaseHelper.PISO,
                UserDataBaseHelper.TELEFONO,
                UserDataBaseHelper.CONTACTO,
                UserDataBaseHelper.LOGUED,
        };
        String[] argumentos = new String[] {String.valueOf(GlobalValues.getINSTANCIA().ID_ANDROID)};

        Cursor resultado = db.query(UserDataBaseHelper.TABLE_NAME, campos,
                UserDataBaseHelper.ID_ANDROID + " = ?", argumentos, null, null, null);
        if (resultado != null)
        {
            registro = parseObjectFromRecord(resultado);
        }
        return registro;
    }



    public User parseObjectFromRecord(Cursor c ){
        try{
            User object = new User();
            if (c!=null){
                    c.moveToFirst();
                    object.setId(c.getInt(c.getColumnIndex(UserDataBaseHelper.ID)));

                    object.setUsername(c.getString(c.getColumnIndex(UserDataBaseHelper.USERNAME)));
                    object.setEntidad_id(c.getInt(c.getColumnIndex(UserDataBaseHelper.ENTIDAD_ID)));
                    object.setGroup_id(c.getInt(c.getColumnIndex(UserDataBaseHelper.GROUP_ID)));
                    object.setEmail(c.getString(c.getColumnIndex(UserDataBaseHelper.EMAIL)));
                    object.setLocalidad(c.getString(c.getColumnIndex(UserDataBaseHelper.LOCALIDAD)));
                    object.setCalle(c.getString(c.getColumnIndex(UserDataBaseHelper.CALLE)));
                    object.setNro(c.getString(c.getColumnIndex(UserDataBaseHelper.NRO)));
                    object.setPiso(c.getString(c.getColumnIndex(UserDataBaseHelper.PISO)));
                    object.setTelefono(c.getString(c.getColumnIndex(UserDataBaseHelper.TELEFONO)));
                    object.setContacto(c.getString(c.getColumnIndex(UserDataBaseHelper.CONTACTO)));
                    object.setLogued(c.getString(c.getColumnIndex(UserDataBaseHelper.LOGUED)));
                }

            return object;
        }catch(Exception e){
            Toast.makeText(context,"Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }






}
