package adaptivex.pedidoscloud.Config;

import adaptivex.pedidoscloud.Model.User;

/**
 * Created by ezequiel on 26/06/2016.
 * VARIABLES DE APLICACION, INTERNA ENTRE ACTIVIDADES, VA
 */
public class GlobalValues {

    private static GlobalValues INSTANCIA;


    public  static final String ACTION_GET_STOCK_PRECIOS = "1";


    //Variables de Login user
    private int USER_ID_LOGIN;
    private User userlogued;

    private int ActualFragment;
    public static final int LISTADOPRODUCTOS = 1;
    public static final int LISTADOCLIENTES = 2;
    public static final int LISTADOMARCAS = 3;
    public static final int LISTADOCATEGORIAS = 4;
    public static final int LISTADOPEDIDOS = 5;
    public static final int LISTADOPEDIDODETALLES = 6;
    public static final int DETALLEPEDIDO = 7;
    public static final int PRODUCTODETALLE = 8;
    public static final int LISTADOHOJARUTA = 9;
    public static final int HOME = 10;
    public static final int CONFIGURACION = 10;

    //ERRORES
    public static final int RETURN_OK = 1;
    public static final int RETURN_ERROR_200 = 200;
    public static final int RETURN_ERROR_404 = 404;
    public static final int RETURN_ERROR_99 = 99;
    public static final int RETURN_ERROR = 20;

    //Estados de un pedido
    public static final int consPedidoEstadoNuevo = 0;
    public static final int consPedidoEstadoEnviado = 1;
    public static final int consPedidoEstadoPreparado = 2;
    public static final int consPedidoEstadoEntregado = 3;
    public static final int consPedidoEstadoTodos = 99;

    //Estados de un pedido
    public static final int ESTADO_NUEVO     = 0;
    public static final int ESTADO_ENVIADO   = 1;
    public static final int ESTADO_PREPARADO = 2;
    public static final int ESTADO_ENTREGADO = 3;
    public static final int ESTADO_TODOS     = 99;

    public static final int ENVIAR_PEDIDO = 1;
    public static final int ENVIAR_PEDIDOSPENDIENTES = 2;

    //CONSTANTERS HELPER PEDIDOS
    public static final int OPTION_HELPER_ENVIO_PEDIDO = 1;
    public static final int OPTION_HELPER_ENVIO_PEDIDOS_PENDIENTES = 2;



    private int PEDIDO_ACTION_VALUE;
    public static final int PEDIDO_ACTION_DELETE = 1;
    public static final int PEDIDO_ACTION_SEND   = 2;
    public static final int PEDIDO_ACTION_VIEW   = 3;

    public static final int LUNES               = 1;
    public static final int MARTES              = 2;
    public static final int MIERCOLES           = 3;
    public static final int JUEVES              = 4;
    public static final int VIERNES             = 5;
    public static final int SABADO              = 6;
    private int diaSelecionado;


    public static final Integer ID_ANDROID = 1;

    //DATOS DE USUARIO
    public static final String PARAM_USERID         = "PARAM_USERNAME";
    public static final String PARAM_EMAIL          = "PARAM_EMAIL";
    public static final String PARAM_GROUPID        = "PARAM_GROUPID";
    public static final String PARAM_ENTIDADID      = "PARAM_ENTIDADID";
    public static final String PARAM_USERNAME       = "PARAM_USERNAME";
    public static final String PARAM_INSTALLED      = "PARAM_INSTALLED";
    public static final String PARAM_EMPRESA_ID     = "PARAM_EMPRESA_ID";
    public static final String PARAM_CONFIGFILE     = "PARAM_CONFIGFILE";
    public static final String PARAM_REINICIARAPP   = "PARAM_REINICIARAPP";

    public static final String PARAM_LOCALIDAD                      = "PARAM_LOCALIDAD";
    public static final String PARAM_CALLE                          = "PARAM_CALLE";
    public static final String PARAM_NRO                            = "PARAM_NRO";
    public static final String PARAM_PISO                           = "PARAM_PISO";
    public static final String PARAM_CONTACTO                       = "PARAM_CONTACTO";
    public static final String PARAM_TELEFONO                       = "PARAM_TELEFONO";

    public static final String PARAM_SERVICE_STOCK_PRECIOS_ACTIVATE = "PARAM_SERVICE_STOCK_PRECIOS_ACTIVATE";
    public static final String PARAM_SERVICE_STOCK_PRECIOS_WORKING  = "PARAM_SERVICE_STOCK_PRECIOS_WORKING";

    public static final String PARAM_DOWNLOAD_DATABASE              = "PARAM_DOWNLOAD_DATABASE";
    public static final String PARAM_SERVICE_ENVIO_PEDIDOS_ACTIVATE = "PARAM_SERVICE_ENVIO_PEDIDOS_ACTIVATE";






    public static final String VALUE_SERVICE_STOCK_PRECIOS_WORKING = "Y";
    public static final String VALUE_SERVICE_STOCK_PRECIOS_WORKING_NOT = "N";

    public static  String[] ESTADOS = {"Nuevo","Enviado","Preparado","Entregado"};

    //Variables globales para Generar Pedido
    private long vgPedidoIdActual;
    private long vgPedidoSeleccionado;
    private int vgClienteSelecionado;
    private boolean vgFlagMenuNuevoPedido;
    private int ESTADO_ID_SELECCIONADO;
    public int PEDIDO_ID_ACTUAL;
    public int CLIENTE_ID_PEDIDO_ACTUAL;

    public boolean IS_MEMO;

    //Valores impuestos
    public static final Double consIva = 21.00;





    public  static GlobalValues getINSTANCIA() {

        if (INSTANCIA==null) {

            INSTANCIA=new GlobalValues();
        }
        return INSTANCIA;
    }



    public int getActualFragment() {
        return ActualFragment;
    }

    public void setActualFragment(int gvActualFragment) {
        ActualFragment = gvActualFragment;
    }

    public long getVgPedidoIdActual() {
        return vgPedidoIdActual;
    }

    public boolean isUserAuthenticated(){
        if (getUserlogued()==null){
            return false;
        }else{
            return true;
        }

    }
    public void setVgPedidoIdActual(long vgPedidoIdActual) {
        this.vgPedidoIdActual = vgPedidoIdActual;
    }

    public int getVgClienteSelecionado() {
        return vgClienteSelecionado;
    }

    public void setVgClienteSelecionado(int vgClienteSelecionado) {
        this.vgClienteSelecionado = vgClienteSelecionado;
    }

    public boolean isVgFlagMenuNuevoPedido() {
        return vgFlagMenuNuevoPedido;
    }

    public void setVgFlagMenuNuevoPedido(boolean vgFlagMenuNuevoPedido) {
        this.vgFlagMenuNuevoPedido = vgFlagMenuNuevoPedido;
    }

    public int getUSER_ID_LOGIN() {
        return USER_ID_LOGIN;
    }

    public void setUSER_ID_LOGIN(int USER_ID_LOGIN) {
        this.USER_ID_LOGIN = USER_ID_LOGIN;
    }

    public long getVgPedidoSeleccionado() {
        return vgPedidoSeleccionado;
    }

    public void setVgPedidoSeleccionado(long vgPedidoSeleccionado) {
        this.vgPedidoSeleccionado = vgPedidoSeleccionado;
    }

    public int getESTADO_ID_SELECCIONADO() {
        return ESTADO_ID_SELECCIONADO;
    }

    public void setESTADO_ID_SELECCIONADO(int ESTADO_ID_SELECCIONADO) {
        this.ESTADO_ID_SELECCIONADO = ESTADO_ID_SELECCIONADO;
    }

    public int getPEDIDO_ACTION_VALUE() {
        return PEDIDO_ACTION_VALUE;
    }

    public void setPEDIDO_ACTION_VALUE(int PEDIDO_ACTION_VALUE) {
        this.PEDIDO_ACTION_VALUE = PEDIDO_ACTION_VALUE;
    }

    public int getDiaSelecionado() {
        return diaSelecionado;
    }

    public void setDiaSelecionado(int diaSelecionado) {
        this.diaSelecionado = diaSelecionado;
    }

    public User getUserlogued() {
        return userlogued;
    }

    public void setUserlogued(User userlogued) {
        this.userlogued = userlogued;
    }



}
