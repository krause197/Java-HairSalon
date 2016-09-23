import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Date;

public class Stylist {
  private int id;
  private String name;
;

  public Stylist(String name, int stylistId) {
    name = name;
    stylistId = stylistId;
    this.save();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylistId;
  }
