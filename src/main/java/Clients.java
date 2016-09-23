import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Date;

public class Clients {
  private int id;
  private String name;

  public Client(String _name) {
    name = _name;
    this.save();
  }

  public int getId() {
    return id;
  }


  public String getName() {
    return name;
  }
