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
      String note = request.queryParams("note");
      int stylistId = Integer.parseInt(request.queryParams("stylist_id"));
      Clients client = new Clients(name, note, stylistId);
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
      String note = request.queryParams("note");
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      Clients client = new Clients(name, note, stylist_id);
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

    get("/stylists/:stylist_id/clients/:clientsid/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int clientsid = Integer.parseInt(request.params(":clientsid"));
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      model.put("client", Clients.find(clientsid));
      model.put("stylist", Stylists.find(stylist_id));
      model.put("template", "templates/client-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:stylist_id/clients/:clientsid/edit", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String note = request.queryParams("note");
      int stylist_id = Integer.parseInt(request.params(":stylist_id"));
      int clientsid = Integer.parseInt(request.params(":clientsid"));
      Clients.update(clientsid, name, note, stylist_id);
      response.redirect("/stylists/" + stylist_id);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:stylist_id/clients/:clientsid/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int clientsid = Integer.parseInt(request.params(":clientsid"));
      Clients client = Clients.find(clientsid);
      int stylist_id = client.getStylistId();
      Clients.delete(clientsid);
      response.redirect("/stylists/" + stylist_id);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
