package core.strategy;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Date;

public interface Helpler {
    public String StringSupport();
    public JSONPObject JsSupport();
    public Date DateSupport ();
    public void SqlSupport();
}
