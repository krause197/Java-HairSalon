import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Clients {
  private int id;
  private String name;
  private String note;
  private int stylistId;

  public Client(String name, String note, String stylistId) {
    this.name = name;
    this.note = note;
    this.stylistId = stylistId;

    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, note, stylistId) VALUES (:name, :note, :stylistId);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("note", this.note)
      .addParameter("stylistId", this.stylistId)
      .executeUpdate()
      .getKey();
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getNote() {
    return note;
  }

  public String getStylistId() {
    return stylistId;
  }
  
}
