package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;



@CronapiMetaData(type = "blockly")
@CronappSecurity
public class FuncoesUsuario {

public static final int TIMEOUT = 300;

/**
 *
 * @return Var
 */
// FuncoesUsuario
public static Var LoginDoUsuario() throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    return
cronapi.util.Operations.getCurrentUserName();
   }
 }.call();
}

/**
 *
 * @return Var
 */
// Descreva esta função...
public static Var IdDoUsuarioLogado() throws Exception {
 return new Callable<Var>() {

   private Var lista = Var.VAR_NULL;

   public Var call() throws Exception {

    lista =
    cronapi.database.Operations.query(Var.valueOf("app.entity.User"),Var.valueOf("select u.id from User u where u.normalizedUserName = :normalizedUserName"),Var.valueOf("normalizedUserName",
    cronapi.text.Operations.normalize(
    Var.valueOf(LoginDoUsuario()))));
    return
cronapi.list.Operations.getFirst(lista);
   }
 }.call();
}

}

