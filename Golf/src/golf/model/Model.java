package golf.model;

import com.spanishinquisition.functions.IAuth;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Michał Słowikowski.
 */
public class Model {
    public User currentUser;
    public IAuth authorization;
    public boolean canContinue = false;

    public Model(){
        authorization = new IAuth() {};
        currentUser = new User();
    }


    public StringProperty nameProperty(){
        return new SimpleStringProperty("Name");
    }
}
