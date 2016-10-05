import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylists.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylists.all());
      model.put("clients", Clients.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Clients.all());
      model.put("stylists", Stylists.all());
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist-input.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String detail = request.queryParams("detail");
      Stylists stylist = new Stylists(name, detail);
      response.redirect("/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylists.all());
      model.put("template", "templates/client-input.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String notes = request.queryParams("notes");
      int stylistId = Integer.parseInt(request.queryParams("stylist_id"));
      Clients client = new Clients(name, notes, stylistId);
      response.redirect("/clients");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylist_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":stylist_id"));
      model.put("stylist", Stylists.find(id));
      model.put("clients", Clients.allForStylist(id));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylist_id/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":stylist_id"));
      model.put("stylist", Stylists.find(id));
      model.put("template", "templates/client-input.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylist_id/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String notes = request.queryParams("notes");
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      Clients client = new Clients(name, notes, stylist_id);
      response.redirect("/stylists/" + stylist_id);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      model.put("stylist", Stylists.find(id));
      model.put("template", "templates/stylist-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String detail = request.queryParams("detail");
      int stylist_id = Integer.parseInt(request.params(":id"));
      Stylists.update(stylist_id, name, detail);
      response.redirect("/stylists/" + stylist_id);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Stylists.delete(id);
      response.redirect("/stylists");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylist_id/clients/:client_id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int client_id = Integer.parseInt(request.params(":client_id"));
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      model.put("client", Clients.find(client_id));
      model.put("stylist", Stylists.find(stylist_id));
      model.put("template", "templates/client-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylist_id/clients/:client_id/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String notes = request.queryParams("notes");
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      int client_id = Integer.parseInt(request.params(":client_id"));
      Clients.update(client_id, name, notes, stylist_id);
      response.redirect("/stylists/" + stylist_id);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylist_id/clients/:client_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int client_id = Integer.parseInt(request.params(":client_id"));
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      Clients.delete(client_id);
      response.redirect("/stylists/" + stylist_id);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
